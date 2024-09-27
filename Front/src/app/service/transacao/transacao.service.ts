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

    console.log(transacao.usuarioId)
    console.log(transacao.tipoTransacaoId)
    console.log(transacao.categoriaId)
    console.log(transacao.subcategoriaId)
    console.log(transacao.metodoPagamentoId)
    console.log(transacao.valor)
    console.log(transacao.descricao)

    return this.http.post(`${this.apiUrl}/transacao`, transacao, {
      headers: { 'Content-Type': 'application/json' }
    });
  }

}
