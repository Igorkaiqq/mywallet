import {CURRENCY_MASK_CONFIG, CurrencyMaskModule} from 'ng2-currency-mask';
import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {CommonModule} from '@angular/common';
import {ContaBancariaService} from '../../../service/contaBancaria/conta-bancaria.service';
import {FormsModule} from '@angular/forms';
import {CustomCurrencyMaskConfig} from '../../../config/currency-mask';
import {NotificationService} from "../../../service/notification/notification.service";

interface ContaBancaria {
  id: string;
  usuarioId: string;
  nome: string;
  saldo: number;
  statusRegistro: string;
}

@Component({
  selector: 'app-cadastro-conta-bancaria',
  templateUrl: './cadastro-conta-bancaria.component.html',
  styleUrls: ['./cadastro-conta-bancaria.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, CurrencyMaskModule],
  providers: [
    { provide: CURRENCY_MASK_CONFIG, useValue: CustomCurrencyMaskConfig }
  ]
})
export class CadastroContaBancariaComponent {
  contaBancaria: ContaBancaria = {
    id: '',
    usuarioId: '',
    nome: '',
    saldo: 0,
    statusRegistro: 'ATIVO'
  };

  successMessage: string = '';
  errorMessages: string[] = [];

  constructor(
    private contaBancariaService: ContaBancariaService,
    private router: Router,
    private notificationService: NotificationService
  ) { }

  salvarContaBancaria(): void {
    this.contaBancariaService.criarContaBancaria(this.contaBancaria).subscribe({
      next: (response) => {
        this.successMessage = 'Conta bancária cadastrada com sucesso!';
        this.notificationService.showSuccess(this.successMessage);
        this.errorMessages = [];
        setTimeout(() => {
          this.router.navigate(['/realizar-transacao']);
        }, 2000);
      },
      error: (error) => {
        if (error.error) {
          const errors = error.error;
          this.errorMessages = Object.keys(errors).map((key) => errors[key]);
        } else {
          this.errorMessages = ['Erro ao cadastrar a conta bancária. Tente novamente.'];
        }
        this.notificationService.showError(this.formatErrorMessages(this.errorMessages));
      }
    });
  }

  apenasLetras(event: KeyboardEvent): void {
    const regex = /^[A-Za-zÀ-ÖØ-öø-ÿ ]+$/;
    if (!regex.test(event.key)) {
      event.preventDefault();
    }
  }

  private formatErrorMessages(errorMessages: string[]): string {
    return errorMessages.join('\n');
  }

}
