import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../../service/categoria/categoria.service';
import { SubcategoriaService } from '../../service/subcategoria/subcategoria.service';
import { MetodoPagamentoService } from '../../service/metodoPagamento/metodo-pagamento.service';
import { TransacaoService } from '../../service/transacao/transacao.service';
import {TipoTransacaoService} from "../../service/tipoTransacao/tipo-transacao.service";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {CURRENCY_MASK_CONFIG, CurrencyMaskConfig, CurrencyMaskModule} from "ng2-currency-mask";
import {Router} from "@angular/router";
import {ContaBancariaService} from "../../service/contaBancaria/conta-bancaria.service";

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

interface ContaBancaria {
  id: string;
  nome: string;
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
    tipoTransacaoId: string | null,
    valor: string,
    categoriaId: string | null,
    subcategoriaId: string | null,
    metodoPagamentoId: string | null,
    contaBancariaId: string | null,
    descricao: string
  } = {
    tipoTransacaoId: null,
    valor: '0',
    categoriaId: null,
    subcategoriaId: null,
    metodoPagamentoId: null,
    contaBancariaId: null,
    descricao: ''
  };

  tiposTransacao: TipoTransacao[] = [];
  categorias: Categoria[] = [];
  subcategorias: Subcategoria[] = [];
  metodosPagamento: MetodoPagamento[] = [];
  contasBancarias: ContaBancaria[] = [];

  successMessage: string = '';
  errorMessages: string[] = [];

  constructor(
    private categoriaService: CategoriaService,
    private subcategoriaService: SubcategoriaService,
    private metodoPagamentoService: MetodoPagamentoService,
    private transacaoService: TransacaoService,
    private tipotransacaoService: TipoTransacaoService,
    private contaBancariaService: ContaBancariaService,
    private router: Router
  ) { }

  ngOnInit(): void {

    this.tipotransacaoService.getTiposTransacao().subscribe(tipos => {
      this.tiposTransacao = tipos;
      sessionStorage.setItem('tiposTransacao', JSON.stringify(tipos));
    });

    this.metodoPagamentoService.getMetodosPagamento().subscribe(metodos => {
      this.metodosPagamento = metodos;
      sessionStorage.setItem('metodosPagamento', JSON.stringify(metodos));
    });

    this.contaBancariaService.getContasBancarias().subscribe(contas => {
      this.contasBancarias = contas;
      sessionStorage.setItem('contasBancarias', JSON.stringify(contas));
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

    this.transacaoService.salvarTransacao(this.transacao).subscribe({
      next: (response) => {
        this.successMessage = 'Transação realizada com sucesso!';
        this.errorMessages = [];
        setTimeout(() => {
          this.router.navigate(['/wallet']);
        }, 2000)
      },
      error: (error) => {
        if (error.error) {
          const errors = error.error;
          this.errorMessages = Object.keys(errors).map((key) => errors[key]);
        } else {
          this.errorMessages = ['Erro ao realizar a transação. Tente novamente.'];
        }
        this.successMessage = '';
      }
    });
  }

  cadastrarNovaContaBancaria(): void {
    this.router.navigate(['/cadastrar-conta-bancaria']);
  }

}
