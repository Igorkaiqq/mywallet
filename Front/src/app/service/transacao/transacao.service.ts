import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_BASE_URL} from "../../app.config";

@Injectable({
  providedIn: 'root'
})
export class TransacaoService {
  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) {}

  salvarTransacao(transacao: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/transacao`, transacao);
  }

  buscarTransacoesPorUsuarioId(): Observable<any> {
    return this.http.get(`${this.apiUrl}/transacao/usuario`);
  }

}
