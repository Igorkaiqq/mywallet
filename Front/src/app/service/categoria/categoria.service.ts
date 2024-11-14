import {Inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import  { Observable} from "rxjs";
import { API_BASE_URL} from "../../app.config";
import {Categoria} from "../../models/categoria/categoria";

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) { }

  getCategoriasPorUsuarioId(tipoTransacaoId: string): Observable<any> {

    return this.http.get(`${this.apiUrl}/categoria-usuario/tipoTransacao/${tipoTransacaoId}`);
  }

  atualizarCategoria(categoria: Categoria): Observable<any> {
    return this.http.put(`${this.apiUrl}/categoria-usuario/update`, categoria);
  }

  excluirCategoria(id: string) {
    return this.http.delete(`${this.apiUrl}/categoria-usuario/${id}`);
  }

  registrarCategoria(novaCategoria: Categoria) {
    return this.http.post(`${this.apiUrl}/categoria-usuario`, novaCategoria);
  }
}
