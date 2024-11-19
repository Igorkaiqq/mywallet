import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_BASE_URL} from '../../app.config';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) {}

  getDespesas(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/despesas`);
  }

  getReceitas(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/receitas`);
  }
}
