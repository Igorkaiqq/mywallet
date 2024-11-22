import {Component, OnInit, ViewChild} from '@angular/core';
import {TransacaoService} from '../../service/transacao/transacao.service';
import {CommonModule, CurrencyPipe, DatePipe} from '@angular/common';
import {DropdownModule} from 'primeng/dropdown';
import {Table, TableModule} from 'primeng/table';
import {ButtonModule} from 'primeng/button';
import {TagModule} from 'primeng/tag';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from "@angular/forms";
import {InputIconModule} from "primeng/inputicon";
import {IconFieldModule} from "primeng/iconfield";
import {InputTextModule} from "primeng/inputtext";
import {PrimeNGConfig} from "primeng/api";
import {Router} from "@angular/router";

@Component({
  selector: 'app-movimentacao-dashboard',
  standalone: true,
  imports: [CommonModule, DropdownModule, TableModule, ButtonModule, TagModule, HttpClientModule, CurrencyPipe, DatePipe, FormsModule, InputIconModule, IconFieldModule, InputTextModule],
  templateUrl: './movimentacao-dashboard.component.html',
  styleUrls: ['./movimentacao-dashboard.component.css']
})
export class MovimentacaoDashboardComponent implements OnInit {
  transacoes: any[] = [];
  tipos: any[] = [];
  loading: boolean = true;

  searchValue: string = '';
  @ViewChild('dt') dt!: Table;

  constructor(
    private transacaoService: TransacaoService,
    private config: PrimeNGConfig,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.buscarTransacoes();
    this.tipos = [
      { label: 'RECEITA', value: 'RECEITA' },
      { label: 'DESPESA', value: 'DESPESA' }
    ];
    this.config.setTranslation({
      matchAll: 'Corresponder a todos',
      matchAny: 'Corresponder a qualquer',
      startsWith: 'Começa com',
      contains: 'Contém',
      notContains: 'Não contém',
      endsWith: 'Termina com',
      equals: 'Igual a',
      notEquals: 'Diferente de',
    });
  }

  buscarTransacoes() {
    this.transacaoService.buscarTransacoesPorUsuarioId().subscribe({
      next: (transacoes) => {
        this.transacoes = transacoes;
        this.loading = false;
      },
      error: (error) => {
        console.error('Erro ao buscar transações: ', error);
      }
    });
  }

  clear(table: any) {
    table.clear();
  }

  protected readonly HTMLInputElement = HTMLInputElement;

  applyFilter(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    this.dt.filterGlobal(inputElement.value, 'contains');
  }

  novaMovimentacao() {
    this.router.navigate([`/realizar-transacao`]);
  }

  goToDashboard() {
    this.router.navigate([`/tela-inicial`]);
  }
}
