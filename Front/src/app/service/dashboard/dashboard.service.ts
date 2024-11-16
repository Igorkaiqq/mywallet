import {Inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_BASE_URL} from "../../app.config";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) { }

  getResumo(): Observable<any> {
    return this.http.get(`${this.apiUrl}/dashboard/resumo`);
  }

  getBancos(): Observable<any> {
    return this.http.get(`${this.apiUrl}/dashboard/bancos`);
  }

  getMaioresReceitas():  Observable<any> {
    return this.http.get(`${this.apiUrl}/dashboard/receitas`);
  }

  getMaioresDespesas():  Observable<any> {
    return this.http.get(`${this.apiUrl}/dashboard/despesas`);
  }

}
