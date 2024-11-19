import {Component, OnInit} from '@angular/core';
import {CategoriaService} from '../../service/categoria/categoria.service';
import {SubcategoriaService} from '../../service/subcategoria/subcategoria.service';
import {MetodoPagamentoService} from '../../service/metodoPagamento/metodo-pagamento.service';
import {TransacaoService} from '../../service/transacao/transacao.service';
import {TipoTransacaoService} from "../../service/tipoTransacao/tipo-transacao.service";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {CURRENCY_MASK_CONFIG, CurrencyMaskModule} from "ng2-currency-mask";
import {Router} from "@angular/router";
import {ContaBancariaService} from "../../service/contaBancaria/conta-bancaria.service";
import {Transacao} from "../../models/transacao/transacao";
import {TipoTransacao} from "../../models/tipoTransacao/tipo-transacao";
import {Categoria} from "../../models/categoria/categoria";
import {Subcategoria} from "../../models/subcategoria/subcategoria";
import {MetodoPagamento} from "../../models/metodoPagamento/metodo-pagamento";
import {ContaBancaria} from "../../models/contaBancaria/conta-bancaria";
import {CustomCurrencyMaskConfig} from "../../config/currency-mask";

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

  transacao: Transacao = {
    tipoTransacaoId: '',
    categoriaId: '',
    subcategoriaId: '',
    metodoPagamentoId: '',
    contaBancariaId: '',
    valor: '0',
    descricao: '',
    data: ''
  }

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

    this.buscarTiposTransacao();
    this.buscarMetodosPagamento();
    this.buscarContasBancarias();

  }

  private buscarTiposTransacao(): void {
    this.tipotransacaoService.getTiposTransacao().subscribe(tipos => {
      this.tiposTransacao = tipos;
    });
  }

  private buscarMetodosPagamento(): void {
    this.metodoPagamentoService.getMetodosPagamento().subscribe(metodos => {
      this.metodosPagamento = metodos;
    });
  }

  private buscarContasBancarias(): void {
    this.contaBancariaService.getContasBancarias().subscribe(contas => {
      this.contasBancarias = contas
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

    const valorNumerico = String(this.transacao.valor)
      .replace('R$', '')
      .replace('.', '')
      .replace(',', '.')
      .trim();

    this.transacao.valor = valorNumerico;

    this.transacaoService.salvarTransacao(this.transacao).subscribe({
      next: (response) => {
        this.successMessage = 'Transação realizada com sucesso!';
        this.errorMessages = [];
        setTimeout(() => {
          this.router.navigate(['/tela-inicial']);
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
