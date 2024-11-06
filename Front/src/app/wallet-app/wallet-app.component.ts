import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {TransacaoService} from "../service/transacao/transacao.service";
import {CommonModule, CurrencyPipe, DatePipe} from "@angular/common";

@Component({
  selector: 'app-wallet',
  standalone: true,
  imports: [ReactiveFormsModule, CurrencyPipe, DatePipe, CommonModule],
  templateUrl: './wallet-app.component.html',
  styleUrls: ['./wallet-app.component.css']
})
export class WalletAppComponent implements OnInit {
  walletAppForm = new FormGroup({
    saldo: new FormControl(15000.00, Validators.required),
    receitas: new FormControl(8000.00, Validators.required),
    despesas: new FormControl(3000.00, Validators.required)
  });

  transacoes: any[] = [];

  categoriasDespesas = [
    { nome: 'Moradia', valor: 1200.00, icone: 'bi-house' },
    { nome: 'Cartão', valor: 800.00, icone: 'bi-credit-card' },
    { nome: 'Transporte', valor: 500.00, icone: 'bi-car-front' },
    { nome: 'Lazer', valor: 400.00, icone: 'bi-tree' }
  ];

  topDespesas = [
    { nome: 'Aluguel', valor: 1200.00 },
    { nome: 'Mercado', valor: 600.00 },
    { nome: 'Transporte', valor: 300.00 },
    { nome: 'Entretenimento', valor: 200.00 },
    { nome: 'Academia', valor: 100.00 }
  ];

  topReceitas = [
    { nome: 'Salário', valor: 5000.00 },
    { nome: 'Freelance', valor: 2000.00 },
    { nome: 'Investimentos', valor: 1000.00 }
  ];

  ngOnInit(): void {
    this.buscarTransacoes();
  }

<<<<<<< main
  onSubmit() {
    if (this.walletAppForm.valid) {
      console.log('Form Data: ', this.walletAppForm.value);

    } else {
      console.log('Form inválido');
    }
=======
  goToCadTransacao() {
    this.router.navigate(['/realizar-transacao']);
>>>>>>> local
  }

  onCancel() {
    this.walletAppForm.reset();
  }

<<<<<<< main
  constructor(
    private router: Router,
    private transacaoService: TransacaoService
  ) {}

  goToCadTransacao() {
    this.router.navigate(['/realizar-transacao']);
=======
  goLogin() {
    this.router.navigate(['']);
>>>>>>> local
  }

  goLogin(){
    this.router.navigate(['']);
  }

  buscarTransacoes() {

      this.transacaoService.buscarTransacoesPorUsuarioId().subscribe({
        next: (transacoes) => {
          this.transacoes = transacoes;
        },
        error: (error) => {
          console.error('Erro ao buscar transações: ', error);
        }
      });
    }
}
