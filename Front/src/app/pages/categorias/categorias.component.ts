import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../../service/categoria/categoria.service';
import { SubcategoriaService } from '../../service/subcategoria/subcategoria.service';
import { TipoTransacaoService } from '../../service/tipoTransacao/tipo-transacao.service';
import { Categoria } from '../../models/categoria/categoria';
import { Subcategoria } from '../../models/subcategoria/subcategoria';
import { TipoTransacao } from '../../models/tipoTransacao/tipo-transacao';
import {NgClass, NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-categorias',
  templateUrl: './categorias.component.html',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    NgClass
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
    private tipoTransacaoService: TipoTransacaoService
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
}
