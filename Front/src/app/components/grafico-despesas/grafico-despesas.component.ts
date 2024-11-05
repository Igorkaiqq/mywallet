import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { ApiService } from '../../service/graficos/api.service';

@Component({
  selector: 'app-grafico-despesas',
  templateUrl: './grafico-despesas.component.html'
})
export class GraficoDespesasComponent implements OnInit {
  public chart: any;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getDespesas().subscribe(data => {
      this.chart = new Chart('graficoDespesasCanvas', {
        type: 'bar', // Tipo do gráfico
        data: {
          labels: data.map((item: any) => item.nome),
          datasets: [
            {
              label: 'Despesas',
              data: data.map((item: any) => item.valor),
              borderWidth: 1
            }
          ]
        },
        options: {
          responsive: true
        }
      });
    });
  }
}
