import {Inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import  { Observable} from "rxjs";
import { API_BASE_URL} from "../../app.config";

@Injectable({
  providedIn: 'root'
})
export class TransacaoService {

  private apiBaseUrl = API_BASE_URL;

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) { }

  salvarTransacao(transacao: any): Observable<any> {

    return this.http.post(`${this.apiUrl}/transacao`, transacao, {
      headers: { 'Content-Type': 'application/json' }
    });
  }

  buscarTransacoesPorUsuarioId(): Observable<any> {

    const usuarioLogado = sessionStorage.getItem('usuarioLogado');

    if (usuarioLogado) {
      const usuario = JSON.parse(usuarioLogado);
      const usuarioId = usuario.id;

      return this.http.get(`${this.apiUrl}/transacao/usuario/${usuarioId}`);
    } else {
      throw new Error('Usuário não logado');
    }
  }

}
