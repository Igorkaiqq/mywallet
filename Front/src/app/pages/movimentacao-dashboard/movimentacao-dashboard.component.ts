import {Component, OnInit} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {TransacaoService} from '../../service/transacao/transacao.service';
import {CommonModule, CurrencyPipe, DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {MatDialog} from "@angular/material/dialog";
import {NovaTransacaoComponent} from "../../overlay/nova-transacao/nova-transacao.component";

@Component({
  selector: 'app-movimentacao-dashboard',
  standalone: true,
  imports: [ReactiveFormsModule, CurrencyPipe, DatePipe, CommonModule],
  templateUrl: './movimentacao-dashboard.component.html',
  styleUrls: ['./movimentacao-dashboard.component.css']
})
export class MovimentacaoDashboardComponent implements OnInit {

  transacoes: any[] = [];
  filteredTransacoes: any[] = [];

  ngOnInit(): void {
    this.buscarTransacoes();
  }

  constructor(
    private transacaoService: TransacaoService,
    private router: Router,
    private dialog: MatDialog
  ) {}

  buscarTransacoes() {
    this.transacaoService.buscarTransacoesPorUsuarioId().subscribe({
      next: (transacoes) => {
        this.transacoes = transacoes;
        this.filteredTransacoes = transacoes;
      },
      error: (error) => {
        console.error('Erro ao buscar transações: ', error);
      }
    });
  }

  goToDashboard() {
    this.router.navigate(['/wallet']);
  }

  novaMovimentacao(): void {
    const dialogRef = this.dialog.open(NovaTransacaoComponent, {
      width: '500px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log('Nova movimentação criada:', result);
      }
    });
  }


}

