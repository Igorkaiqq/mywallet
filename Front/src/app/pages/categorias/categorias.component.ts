import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../../service/categoria/categoria.service';
import { SubcategoriaService } from '../../service/subcategoria/subcategoria.service';
import { TipoTransacaoService } from '../../service/tipoTransacao/tipo-transacao.service';
import { Categoria } from '../../models/categoria/categoria';
import { Subcategoria } from '../../models/subcategoria/subcategoria';
import { TipoTransacao } from '../../models/tipoTransacao/tipo-transacao';
import { NgClass, NgForOf, NgIf } from "@angular/common";
import { ActionButtonsComponent } from "../../components/actions/actions-buttons/actions-buttons.component";
import { EditarCategoriaComponent } from "../../overlay/editar-categoria/editar-categoria.component";
import { MatDialog } from "@angular/material/dialog";
import { CriarCategoriaComponent } from "../../overlay/criar-categoria/criar-categoria.component";
import { CriarSubcategoriaComponent } from "../../overlay/criar-subcategoria/criar-subcategoria.component";
import { Meta } from '@angular/platform-browser';
import {MetasFinanceiras} from "../../models/metas/metas";
import {CriarMetasComponent} from "../../overlay/criar-metas/criar-metas.component";
import {MetasService} from "../../service/metas/metasService";

@Component({
  selector: 'app-categorias',
  templateUrl: './categorias.component.html',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    NgClass,
    ActionButtonsComponent
  ],
  styleUrls: ['./categorias.component.css']
})
export class CategoriasComponent implements OnInit {
  categoriasReceita: Categoria[] = [];
  categoriasDespesa: Categoria[] = [];
  subcategoriasMap: { [key: string]: Subcategoria[] } = {};
  expandedCategories: Set<string> = new Set<string>();

  constructor(
    private categoriaService: CategoriaService,
    private subcategoriaService: SubcategoriaService,
    private tipoTransacaoService: TipoTransacaoService,
    private metasService: MetasService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.buscarTiposTransacao();
  }

  buscarTiposTransacao(): void {
    this.tipoTransacaoService.getTiposTransacao().subscribe((tipos: TipoTransacao[]) => {
      tipos.forEach((tipo: TipoTransacao) => {
        this.categoriaService.getCategoriasPorUsuarioId(tipo.id).subscribe((categorias: Categoria[]) => {
          categorias.forEach((categoria: Categoria) => {
            this.subcategoriaService.getSubcategoriasPorCategoriaId(categoria.id).subscribe((subcategorias: Subcategoria[]) => {
              this.subcategoriasMap[categoria.id] = subcategorias;
            });
          });

          if (tipo.tipoTransacaoEnum === 'RECEITA') {
            this.categoriasReceita = categorias;
          } else if (tipo.tipoTransacaoEnum === 'DESPESA') {
            this.categoriasDespesa = categorias;
          }
        }, error => {
          console.error('Erro ao buscar categorias', error);
        });
      });
    });
  }

  toggleCategory(categoriaId: string): void {
    if (this.expandedCategories.has(categoriaId)) {
      this.expandedCategories.delete(categoriaId);
    } else {
      this.expandedCategories.add(categoriaId);
    }
  }

  isCategoryExpanded(categoriaId: string): boolean {
    return this.expandedCategories.has(categoriaId);
  }

  registrarCategoria(tipoTransacao: 'RECEITA' | 'DESPESA'): void {
    const dialogRef = this.dialog.open(CriarCategoriaComponent, {
      data: { tipoTransacao }
    });

    dialogRef.afterClosed().subscribe((novaCategoria: Categoria | undefined) => {
      if (novaCategoria) {
        this.categoriaService.registrarCategoria(novaCategoria).subscribe(() => {
          this.buscarTiposTransacao();
        }, error => {
          console.error('Erro ao registrar categoria:', error);
        });
      }
    });
  }

  editarCategoria(categoria: Categoria): void {
    const dialogRef = this.dialog.open(EditarCategoriaComponent, {
      data: { currentName: categoria.nome }
    });

    dialogRef.afterClosed().subscribe((novoNome: string | undefined) => {
      if (novoNome) {
        const categoriaAtualizada = { ...categoria, nome: novoNome };
        this.categoriaService.atualizarCategoria(categoriaAtualizada).subscribe(() => {
          this.buscarTiposTransacao();
        }, error => {
          console.error('Erro ao atualizar categoria:', error);
        });
      }
    });
  }

  excluirCategoria(categoria: Categoria): void {
    this.categoriaService.excluirCategoria(categoria.id).subscribe(() => {
      this.buscarTiposTransacao();
    }, error => {
      console.error('Erro ao excluir categoria:', error);
    });
  }

  registrarSubcategoria(categoria: Categoria): void {
    const dialogRef = this.dialog.open(CriarSubcategoriaComponent, {
      data: { categoriaNome: categoria.nome }
    });

    dialogRef.afterClosed().subscribe((novaSubcategoria: Subcategoria | undefined) => {
      if (novaSubcategoria) {
        novaSubcategoria.categoriaUsuarioId = categoria.id;
        this.subcategoriaService.registrarSubcategoria(novaSubcategoria).subscribe(() => {
          this.buscarTiposTransacao();
        }, error => {
          console.error('Erro ao registrar subcategoria:', error);
        });
      }
    });
  }

  registrarMeta(meta : MetasFinanceiras): void{
    const dialogRef = this.dialog.open(CriarMetasComponent, {
      data: { valorMeta: meta.valor }
    });

    dialogRef.afterClosed().subscribe((novaMeta: MetasFinanceiras | undefined) => {
      if (novaMeta) {
        novaMeta.valor = meta.valor;
        this.metasService.registrarMeta(novaMeta).subscribe(() => {
          this.buscarTiposTransacao();
        }, error => {
          console.error('Erro ao registrar Meta:', error);
        });
      }
    });
  }

  editarSubcategoria(subcategoria: Subcategoria): void {
    const dialogRef = this.dialog.open(EditarCategoriaComponent, {
      data: { currentName: subcategoria.nome }
    });

    dialogRef.afterClosed().subscribe((novoNome: string | undefined) => {
      if (novoNome) {
        const subCategoriaAtualizada = { ...subcategoria, nome: novoNome };
        this.subcategoriaService.atualizarsubCategoria(subCategoriaAtualizada).subscribe(() => {
          this.buscarTiposTransacao();
        }, error => {
          console.error('Erro ao atualizar subcategoria:', error);
        });
      }
    });
  }

  excluirSubcategoria(subcategoria: Subcategoria): void {
    this.subcategoriaService.excluirsubCategoria(subcategoria.id).subscribe(() => {
      this.buscarTiposTransacao();
    }, error => {
      console.error('Erro ao excluir subcategoria:', error);
    });
  }
}
