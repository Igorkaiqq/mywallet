import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatNativeDateModule } from '@angular/material/core';
import { MatMomentDateModule, MomentDateAdapter } from '@angular/material-moment-adapter';
import { DateAdapter } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import moment from 'moment'; // Para fácil manipulação de datas
import { ChartModule } from 'primeng/chart';
import {NgForOf, NgIf} from '@angular/common';

import { DashboardService } from '../../service/dashboard/dashboard.service';
import { CategoriaService } from '../../service/categoria/categoria.service';
import { TipoTransacaoService } from '../../service/tipoTransacao/tipo-transacao.service';
import { Receita } from '../../models/receita/receita';
import { Categoria } from '../../models/categoria/categoria';
import { TipoTransacao } from '../../models/tipoTransacao/tipo-transacao';
import { forkJoin } from 'rxjs';
import { TooltipItem } from "chart.js";

export const MY_DATE_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'DD/MM/YYYY',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@Component({
  selector: 'app-tela-inicial',
  standalone: true,
  imports: [
    ChartModule,
    NgForOf,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatMomentDateModule,  // Use o Moment Date Module em vez do MatNativeDateModule
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    NgIf
  ],
  templateUrl: './tela-inicial.component.html',
  styleUrls: ['./tela-inicial.component.css'],
  providers: [
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS },
    { provide: MAT_DATE_LOCALE, useValue: 'pt-BR' } // Configura o locale para português do Brasil
  ]
})
export class TelaInicialComponent implements OnInit {
  dateRange: FormGroup;
  categoriasDespesa: Categoria[] = [];
  maioresReceitas: any[] = [];
  maioresDespesas: any[] = [];
  bancos: any[] = [];
  receita: Receita = {} as Receita;
  showPresetOptions: boolean = false;

  constructor(
    private fb: FormBuilder,
    private dashboardService: DashboardService,
    private tipoTransacaoService: TipoTransacaoService,
    private categoriaService: CategoriaService
  ) {
    this.dateRange = this.fb.group({
      start: [moment().startOf('month').toDate()],
      end: [moment().endOf('month').toDate()],
    });
  }

  chartsData: any[] = [];

  ngOnInit(): void {
    this.renderCategoryCharts();
    this.buscarMaioresDespesas();
    this.buscarMaioresReceitas();
    this.buscarConta();
    this.buscarTiposTransacao();
    this.buscarResumo();

    this.dateRange.valueChanges.subscribe(value => {
      if (value.start && value.end) {
        this.onDateRangeChange(value.start, value.end);
      }
    });
  }

  formatarValor(valor: number): string {
    if (valor === null || valor === undefined) return '';
    return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }

  onDateRangeChange(start: Date, end: Date) {
    if (start && end) {
      this.showPresetOptions = false;
      this.buscarResumo(start, end);
      this.buscarMaioresReceitas(start, end);
      this.buscarMaioresDespesas(start, end);
      this.renderCategoryCharts();
    }
  }

  renderCategoryCharts() {
    const categories = [
      ...this.categoriasDespesa.map(categoria => ({ name: categoria.nome, percentage: Math.floor(Math.random() * 100) })),
    ];

    this.chartsData = categories.map(category => {
      return {
        data: {
          labels: [category.name, 'Meta estabelecida'],
          datasets: [
            {
              data: [category.percentage, 100 - category.percentage],
              backgroundColor: ['#42A5F5', '#66BB6A'],
              hoverBackgroundColor: ['#64B5F6', '#81C784']
            }
          ]
        },
        options: {
          responsive: true,
          maintainAspectRatio: true,
          plugins: {
            legend: {
              position: 'bottom',
              labels: {
                boxWidth: 10,
                padding: 5
              }
            },
            tooltip: {
              callbacks: {
                label: (tooltipItem: TooltipItem<'doughnut'>): string => {
                  return tooltipItem.label + ': ' + tooltipItem.parsed + '%';
                }
              }
            }
          },
          layout: {
            padding: {
              top: 10,
              bottom: 10
            }
          }
        }
      };
    });
  }

  buscarResumo(start?: Date, end?: Date) {
    const dataInicio = start ? moment(start).format('YYYY-MM-DD') : moment().startOf('month').format('YYYY-MM-DD');
    const dataFim = end ? moment(end).format('YYYY-MM-DD') : moment().endOf('month').format('YYYY-MM-DD');
    this.dashboardService.getResumo(dataInicio, dataFim).subscribe({
      next: (resumo) => {
        this.receita = resumo;
      },
      error: (error) => {
        console.error('Erro ao buscar resumo: ', error);
      }
    });
  }

  buscarMaioresReceitas(start?: Date, end?: Date) {
    const dataInicio = start ? moment(start).format('YYYY-MM-DD') : moment().startOf('month').format('YYYY-MM-DD');
    const dataFim = end ? moment(end).format('YYYY-MM-DD') : moment().endOf('month').format('YYYY-MM-DD');
    this.dashboardService.getMaioresReceitas(dataInicio, dataFim).subscribe({
      next: (receitas: any[]) => {
        this.maioresReceitas = receitas.map(receita => {
          return {
            ...receita,
            data: new Date(receita.data).toLocaleDateString('pt-BR', { day: '2-digit', month: '2-digit', year: '2-digit' })
          };
        });
      },
      error: (error) => {
        console.error('Erro ao buscar maiores receitas: ', error);
      }
    });
  }

  buscarMaioresDespesas(start?: Date, end?: Date) {
    const dataInicio = start ? moment(start).format('YYYY-MM-DD') : moment().startOf('month').format('YYYY-MM-DD');
    const dataFim = end ? moment(end).format('YYYY-MM-DD') : moment().endOf('month').format('YYYY-MM-DD');
    this.dashboardService.getMaioresDespesas(dataInicio, dataFim).subscribe({
      next: (despesas: any[]) => {
        this.maioresDespesas = despesas.map(despesa => {
          return {
            ...despesa,
            data: new Date(despesa.data).toLocaleDateString('pt-BR', { day: '2-digit', month: '2-digit', year: '2-digit' })
          };
        });
      },
      error: (error) => {
        console.error('Erro ao buscar maiores despesas: ', error);
      }
    });
  }

  buscarConta() {
    this.dashboardService.getBancos().subscribe({
      next: (banco) => {
        this.bancos = banco;
      },
      error: (error) => {
        console.error('Erro ao buscar transações: ', error);
      }
    });
  }

  buscarTiposTransacao(): void {
    this.tipoTransacaoService.getTiposTransacao().subscribe((tipos: TipoTransacao[]) => {
      const tipoDespesa = tipos.find(tipo => tipo.tipoTransacaoEnum === 'DESPESA');

      if (tipoDespesa) {
        const despesasObs = this.categoriaService.getCategoriasPorUsuarioId(tipoDespesa.id);

        forkJoin([despesasObs]).subscribe(([despesas]) => {
          this.categoriasDespesa = despesas;

          this.renderCategoryCharts();
        }, error => {
          console.error('Erro ao buscar categorias', error);
        });
      } else {
        console.error('Tipos de transação "DESPESA" não encontrado.');
      }
    }, error => {
      console.error('Erro ao buscar tipos de transação', error);
    });
  }

  onDateRangePickerOpened(): void {
    this.showPresetOptions = true; // Mostrar as opções quando o calendário for aberto
  }

  setPresetPeriod(period: string): void {
    let start: moment.Moment;
    let end: moment.Moment = moment(); // Default é a data atual

    switch (period) {
      case 'hoje':
        start = moment().startOf('day');
        end = moment().endOf('day');
        break;

      case 'ontem':
        start = moment().subtract(1, 'days').startOf('day');
        end = moment().subtract(1, 'days').endOf('day');
        break;

      case 'esta-semana':
        start = moment().startOf('week');
        end = moment().endOf('week');
        break;

      case 'semana-passada':
        start = moment().subtract(1, 'week').startOf('week');
        end = moment().subtract(1, 'week').endOf('week');
        break;

      case 'este-mes':
        start = moment().startOf('month');
        end = moment().endOf('month');
        break;

      case 'mes-passado':
        start = moment().subtract(1, 'month').startOf('month');
        end = moment().subtract(1, 'month').endOf('month');
        break;

      case 'ultimos-2-meses':
        start = moment().subtract(2, 'months').startOf('month');
        end = moment().endOf('month');
        break;

      case 'ultimos-3-meses':
        start = moment().subtract(3, 'months').startOf('month');
        end = moment().endOf('month');
        break;

      case 'ultimos-6-meses':
        start = moment().subtract(6, 'months').startOf('month');
        end = moment().endOf('month');
        break;

      default:
        start = moment().startOf('day');
        end = moment().endOf('day');
        break;
    }

    this.dateRange.patchValue({
      start: start.toDate(),
      end: end.toDate()
    });

    this.onDateRangeChange(start.toDate(), end.toDate());

    // Fechar as opções após escolher um período
    this.showPresetOptions = false;
  }

  onDateRangePickerClosed(): void {
    this.showPresetOptions = false; // Esconder as opções quando o calendário for fechado
  }
}
