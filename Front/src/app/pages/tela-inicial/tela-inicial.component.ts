import {Component, OnInit} from '@angular/core';
import {TooltipItem} from "chart.js";
import {ChartModule} from "primeng/chart";
import {NgForOf} from "@angular/common";

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

  chartsData: any[] = [];

  ngOnInit(): void {
    this.renderCategoryCharts();
  }

  renderCategoryCharts() {
    const categories = [
      { name: 'Moradia', percentage: 57 },
      { name: 'Comunicação', percentage: 75 },
      { name: 'Alimentação', percentage: 70 },
      { name: 'Transporte', percentage: 44 },
      { name: 'Saúde', percentage: 67 },
      { name: 'Pessoais', percentage: 81 },
      { name: 'Educação', percentage: 100 },
      { name: 'Lazer', percentage: 55 }
    ];


    this.chartsData = categories.map(category => {
      return {
        data: {
          labels: [category.name, 'Outros'],
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
}
