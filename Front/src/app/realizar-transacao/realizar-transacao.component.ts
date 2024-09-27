import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../service/categoria/categoria.service';
import { SubcategoriaService } from '../service/subcategoria/subcategoria.service';
import { MetodoPagamentoService } from '../service/metodoPagamento/metodo-pagamento.service';
import { TransacaoService } from '../service/transacao/transacao.service';
import {TipoTransacaoService} from "../service/tipoTransacao/tipo-transacao.service";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {CURRENCY_MASK_CONFIG, CurrencyMaskConfig, CurrencyMaskModule} from "ng2-currency-mask";
import {Router} from "@angular/router";

interface TipoTransacao {
  tipoTransacaoEnum: string;
  id: string;
}

interface Categoria {
  id: string;
  nome: string;
}

interface Subcategoria {
  id: string;
  nome: string;
}

interface MetodoPagamento {
  metodoPagamento: string;
  id: string;
}

const CustomCurrencyMaskConfig: CurrencyMaskConfig = {
  align: "left",
  allowNegative: true,
  decimal: ",",
  precision: 2,
  prefix: "R$ ",
  suffix: "",
  thousands: "."
};

@Component({
  selector: 'app-realizar-transacao',
  templateUrl: './realizar-transacao.component.html',
  styleUrls: ['./realizar-transacao.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, CurrencyMaskModule],
  providers: [
    { provide: CURRENCY_MASK_CONFIG, useValue: CustomCurrencyMaskConfig }
  ],
})
export class RealizarTransacaoComponent implements OnInit {
  transacao: {
    usuarioId: string,
    tipoTransacaoId: string | null,
    valor: string,
    categoriaId: string | null,
    subcategoriaId: string | null,
    metodoPagamentoId: string | null,
    descricao: string
  } = {
    usuarioId: '',
    tipoTransacaoId: null,
    valor: '0',
    categoriaId: null,
    subcategoriaId: null,
    metodoPagamentoId: null,
    descricao: ''
  };

  tiposTransacao: TipoTransacao[] = [];
  categorias: Categoria[] = [];
  subcategorias: Subcategoria[] = [];
  metodosPagamento: MetodoPagamento[] = [];

  constructor(
    private categoriaService: CategoriaService,
    private subcategoriaService: SubcategoriaService,
    private metodoPagamentoService: MetodoPagamentoService,
    private transacaoService: TransacaoService,
    private tipotransacaoService: TipoTransacaoService,
    private router: Router
  ) { }

  ngOnInit(): void {

    const usuarioLogado = sessionStorage.getItem('usuarioLogado');
    if (usuarioLogado) {
      const usuario = JSON.parse(usuarioLogado);
      this.transacao.usuarioId = usuario.id;
    } else {
      console.error('Usuário não logado');
    }

    this.tipotransacaoService.getTiposTransacao().subscribe(tipos => {
      this.tiposTransacao = tipos;
      sessionStorage.setItem('tiposTransacao', JSON.stringify(tipos));
    });

    this.metodoPagamentoService.getMetodosPagamento().subscribe(metodos => {
      this.metodosPagamento = metodos;
      sessionStorage.setItem('metodosPagamento', JSON.stringify(metodos));
    });
  }


  onTipoTransacaoChange(event: any): void {

    const tipoTransacaoId = this.transacao.tipoTransacaoId;

    if (tipoTransacaoId){
      this.categoriaService.getCategoriasPorUsuarioId(tipoTransacaoId).subscribe(categorias => {
        this.categorias = categorias;
        sessionStorage.setItem('categorias', JSON.stringify(categorias));
      }, error => {
        console.error('Erro ao buscar categorias', error);
      });
    }
  }

  onCategoriaChange(event: any): void {

    const categoriaId = this.transacao.categoriaId;

    if (categoriaId){
      this.subcategoriaService.getSubcategoriasPorCategoriaId(categoriaId).subscribe(subcategorias => {
        this.subcategorias = subcategorias;
        sessionStorage.setItem('subcategorias', JSON.stringify(subcategorias));
      }, error => {
        console.error('Erro ao buscar subcategorias', error);
      });
      }
  }

  salvarTransacao(): void {

    const valorNumerico = String(this.transacao.valor).replace('R$', '').replace('.', '').replace(',', '.').trim();
    this.transacao.valor = valorNumerico;

    this.transacaoService.salvarTransacao(this.transacao).subscribe(response => {
      console.log('Transação salva com sucesso', response);
      this.router.navigate(['/wallet']);
    }, error => {
      console.error('Erro ao salvar transação', error);
    });
  }

}
