import {Component, OnInit} from '@angular/core';
import {TooltipItem} from "chart.js";
import {ChartModule} from "primeng/chart";
import {NgForOf} from "@angular/common";
import {DashboardService} from "../../service/dashboard/dashboard.service";
import {Receita} from "../../models/receita/receita";
import {forkJoin} from "rxjs";
import {TipoTransacao} from "../../models/tipoTransacao/tipo-transacao";
import {CategoriaService} from "../../service/categoria/categoria.service";
import {TipoTransacaoService} from "../../service/tipoTransacao/tipo-transacao.service";
import {Categoria} from "../../models/categoria/categoria";

@Component({
  selector: 'app-tela-inicial',
  standalone: true,
  imports: [
    ChartModule,
    NgForOf
  ],
  templateUrl: './tela-inicial.component.html',
  styleUrl: './tela-inicial.component.css'
})
export class TelaInicialComponent implements OnInit {
  categoriasDespesa: Categoria[] = [];
  maioresReceitas: any[] = [];
  maioresDespesas: any[] = [];
  bancos: any[] = [];
  receita: Receita = {} as Receita;

  constructor(
    private dashboardService: DashboardService,
    private tipoTransacaoService: TipoTransacaoService,
    private categoriaService: CategoriaService
  ) {
  }

  chartsData: any[] = [];

  ngOnInit(): void {
    this.renderCategoryCharts();
    this.buscarResumo();
    this.buscarMaioresDespesas();
    this.buscarMaioresReceitas();
    this.buscarConta();
    this.buscarTiposTransacao();
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
                  return `${tooltipItem.label}: ${tooltipItem.raw}%`;
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

  buscarResumo() {
    this.dashboardService.getResumo().subscribe({
      next: (resumo) => {
        this.receita = resumo;
      },
      error: (error) => {
        console.error('Erro ao buscar resumo: ', error);
      }
    });
  }

  buscarMaioresReceitas() {
    this.dashboardService.getMaioresReceitas().subscribe({
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

  buscarMaioresDespesas() {
    this.dashboardService.getMaioresDespesas().subscribe({
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

}
