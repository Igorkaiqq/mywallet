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
import {MatFormField} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatDialogModule} from "@angular/material/dialog";
import {NotificationService} from "../../service/notification/notification.service";

@Component({
  selector: 'app-realizar-transacao',
  templateUrl: './realizar-transacao.component.html',
  styleUrls: ['./realizar-transacao.component.css'],
  standalone: true,
  imports: [CommonModule,
    FormsModule,
    CurrencyMaskModule,
    MatFormField,
    MatSelect,
    MatOption,
    MatRadioButton,
    MatRadioGroup,
    MatDatepickerToggle,
    MatDatepicker,
    MatDatepickerInput,
    MatSnackBarModule,
    MatDialogModule
  ],
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
    valor: 0,
    descricao: '',
    data: ''
  };

  tiposTransacao: TipoTransacao[] = [];
  categorias: Categoria[] = [];
  subcategorias: Subcategoria[] = [];
  metodosPagamento: MetodoPagamento[] = [];
  contasBancarias: ContaBancaria[] = [];

  dataEscolhida: string = 'hoje';
  mostrarCalendario: boolean = false;

  errorMessages: string[] = [];

  dataMaxima: string = '';

  constructor(
    private categoriaService: CategoriaService,
    private subcategoriaService: SubcategoriaService,
    private metodoPagamentoService: MetodoPagamentoService,
    private transacaoService: TransacaoService,
    private tipotransacaoService: TipoTransacaoService,
    private contaBancariaService: ContaBancariaService,
    private router: Router,
    private notificationService: NotificationService
  ) { }

  ngOnInit(): void {
    this.definirDataPadrao();
    this.buscarTiposTransacao();
    this.buscarMetodosPagamento();
    this.buscarContasBancarias();
    this.definirDataMaxima();
  }

  definirDataMaxima(): void {
    const hoje = new Date();
    this.dataMaxima = hoje.toISOString().split('T')[0];
  }

    definirDataPadrao(): void {
    const hoje = new Date();
    this.transacao.data = hoje.toISOString().split('T')[0];
  }

  onDataChange(event: any): void {

    if (this.dataEscolhida === 'hoje') {
      const hoje = new Date(new Date().getTime());
      this.transacao.data = hoje.toISOString().split('T')[0];
      this.mostrarCalendario = false;
    } else if (this.dataEscolhida === 'ontem') {
      const ontem = new Date(new Date().getTime());
      ontem.setDate(ontem.getDate() - 1);
      this.transacao.data = ontem.toISOString().split('T')[0];
      this.mostrarCalendario = false;
    } else {
      this.mostrarCalendario = true;
    }
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
      this.contasBancarias = contas;
    });
  }

  onTipoTransacaoChange(event: any): void {
    const tipoTransacaoId = this.transacao.tipoTransacaoId;

    if (tipoTransacaoId) {
      this.categoriaService.getCategoriasPorUsuarioId(tipoTransacaoId).subscribe(categorias => {
        this.categorias = categorias;
        sessionStorage.setItem('categorias', JSON.stringify(categorias));
      }, error => {
        console.error('Erro ao buscar categorias', error);
      });
    } else {
      this.categorias = [];
      this.subcategorias = [];
      this.transacao.categoriaId = '';
      this.transacao.subcategoriaId = '';
    }
  }

  onCategoriaChange(event: any): void {
    const categoriaId = this.transacao.categoriaId;

    if (categoriaId) {
      this.subcategoriaService.getSubcategoriasPorCategoriaId(categoriaId).subscribe(subcategorias => {
        this.subcategorias = subcategorias;
        sessionStorage.setItem('subcategorias', JSON.stringify(subcategorias));
      }, error => {
        console.error('Erro ao buscar subcategorias', error);
      });
    } else {
      this.subcategorias = [];
      this.transacao.subcategoriaId = '';
    }
  }


  salvarTransacao(): void {
    this.transacaoService.salvarTransacao(this.transacao).subscribe({
      next: (response) => {
        this.notificationService.showSuccess('Transação realizada com sucesso!');
        this.errorMessages = [];
        setTimeout(() => {
          this.router.navigate(['/tela-inicial']);
        }, 2000);
      },
      error: (error) => {
        if (error.error) {
          const errors = error.error;
          this.errorMessages = Object.keys(errors).map((key) => errors[key]);
        } else {
          this.errorMessages = ['Erro ao realizar a transação. Tente novamente.'];
        }

        this.notificationService.showError(this.formatErrorMessages(this.errorMessages));
      }
    });
  }

  private formatErrorMessages(errorMessages: string[]): string {
    return errorMessages.join('\n');
  }

  cadastrarNovaContaBancaria(): void {
    this.router.navigate(['/cadastrar-conta-bancaria']);
  }
}
