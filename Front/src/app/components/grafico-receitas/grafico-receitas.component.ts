import {Component, OnInit} from '@angular/core';
import {Chart} from 'chart.js';
import {ApiService} from '../../service/graficos/api.service';

@Component({
  selector: 'app-grafico-receitas',
  templateUrl: './grafico-receitas.component.html'
})
export class GraficoReceitasComponent implements OnInit {
  public chart: any;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getReceitas().subscribe(data => {
      this.chart = new Chart('graficoReceitasCanvas', {
        type: 'line',
        data: {
          labels: data.map((item: any) => item.nome),
          datasets: [
            {
              label: 'Receitas',
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
