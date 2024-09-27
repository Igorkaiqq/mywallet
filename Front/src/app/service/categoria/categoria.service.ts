import {Inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import  { Observable} from "rxjs";
import { API_BASE_URL} from "../../app.config";

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) { }

  getCategoriasPorUsuarioId(tipoTransacaoId: string): Observable<any> {

    const usuarioLogado = sessionStorage.getItem('usuarioLogado');

    if (usuarioLogado) {
      const usuario = JSON.parse(usuarioLogado);
      const usuarioId = usuario.id;

      return this.http.get(`${this.apiUrl}/categoria-usuario/${usuarioId}/tipoTransacao/${tipoTransacaoId}`);
    } else {
      throw new Error('Usuário não logado');
    }
  }
}
