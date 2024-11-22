import {Inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_BASE_URL} from "../../app.config";
import {Observable} from "rxjs";
import {Categoria} from "../../models/categoria/categoria";

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) {
  }

  getResumo(dataInicio: string, dataFim: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/dashboard/resumo`, {
      params: {
        dataInicio: dataInicio,
        dataFim: dataFim
      }
    });
  }

  getBancos(): Observable<any> {
    return this.http.get(`${this.apiUrl}/dashboard/bancos`);
  }

  getMaioresReceitas(dataInicio: string, dataFim: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/dashboard/receitas`, {
      params: {
        dataInicio: dataInicio,
        dataFim: dataFim
      }
    });
  }

  getMaioresDespesas(dataInicio: string, dataFim: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/dashboard/despesas`, {
      params: {
        dataInicio: dataInicio,
        dataFim: dataFim
      }
    });
  }

  getMetas(id: String): Observable<any> {
    return this.http.get(`${this.apiUrl}/metas-usuario/categoria/${id}`);
  }

  getTransacoes(startDate: string, endDate: string): Observable<any[]> {
    return this.http.get<any[]>('/transacoes', {
      params: {startDate, endDate}
    });
  }


}
