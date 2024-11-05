import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Chart } from 'chart.js';
import { ApiService } from '../../app/service/graficos/api.service';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet-app.component.html',
  styleUrls: ['./wallet-app.component.css']
})
export class WalletAppComponent implements OnInit {
  public receitaChart: any;
  public despesaChart: any;

  constructor(private apiService: ApiService, private router: Router) {}

  ngOnInit(): void {
    this.initReceitaChart();
    this.initDespesaChart();
  }

  // Método para navegação para a página de realizar transação
  goToCadTransacao() {
    this.router.navigate(['/realizar-transacao']);
  }

  goToMovimentacao(){
    this.router.navigate(['/movimentacoes']);
  }

  // Método para navegação para a página de login
  goLogin() {
    this.router.navigate(['']);
  }

  private initReceitaChart(): void {
    this.apiService.getReceitas().subscribe(data => {
      this.receitaChart = new Chart('graficoReceitasCanvas', {
        type: 'bar',
        data: {
          labels: data.map((item: any) => item.nome),
          datasets: [
            {
              label: 'Receitas',
              data: data.map((item: any) => item.valor),
              backgroundColor: 'rgba(0, 123, 255, 0.5)',
              borderColor: 'rgba(0, 123, 255, 1)',
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

  private initDespesaChart(): void {
    this.apiService.getDespesas().subscribe(data => {
      this.despesaChart = new Chart('graficoDespesasCanvas', {
        type: 'line',
        data: {
          labels: data.map((item: any) => item.nome),
          datasets: [
            {
              label: 'Despesas',
              data: data.map((item: any) => item.valor),
              backgroundColor: 'rgba(255, 99, 132, 0.5)',
              borderColor: 'rgba(255, 99, 132, 1)',
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
