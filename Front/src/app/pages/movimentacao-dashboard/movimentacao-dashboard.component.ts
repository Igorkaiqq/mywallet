import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import {TransacaoService} from "../../service/transacao/transacao.service";
import {CommonModule, CurrencyPipe, DatePipe} from "@angular/common";
import { Router } from '@angular/router';


@Component({
  selector: 'app-movimentacao-dashboard',
  standalone: true,
  imports: [ReactiveFormsModule, CurrencyPipe, DatePipe, CommonModule],
  templateUrl: './movimentacao-dashboard.component.html',
  styleUrls: ['./movimentacao-dashboard.component.css']
})
export class MovimentacaoDashboardComponent implements OnInit {
  walletAppForm = new FormGroup({
  });

  transacoes: any[] = [];


  ngOnInit(): void {
    this.buscarTransacoes();
  }

  onSubmit() {
    if (this.walletAppForm.valid) {
      console.log('Form Data: ', this.walletAppForm.value);

    } else {
      console.log('Form inválido');
    }
  }

  onCancel() {
    this.walletAppForm.reset();
  }

  constructor( private transacaoService: TransacaoService, private router: Router) {}

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

    goToDashboard(){
      this.router.navigate(['/wallet']);
    }
}
