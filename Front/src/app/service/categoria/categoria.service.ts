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

    return this.http.get(`${this.apiUrl}/categoria-usuario/tipoTransacao/${tipoTransacaoId}`);
  }
}
