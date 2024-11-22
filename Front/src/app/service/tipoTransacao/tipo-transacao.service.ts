import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {API_BASE_URL} from "../../app.config";
import {Transacao} from "../../models/transacao/transacao";

@Injectable({
  providedIn: 'root'
})
export class TipoTransacaoService {

  private apiBaseUrl = API_BASE_URL;

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) { }

  getTiposTransacao(): Observable<any> {
    return this.http.get(`${this.apiUrl}/tipo-transacao`);
  }

  buscarTransacoesPorCategoria(categoriaId: string, startDate: string, endDate: string): Observable<Transacao[]> {
    return this.http.get<Transacao[]>(`${this.apiUrl}/transacao/categoria/${categoriaId}`, {
      params: { startDate, endDate }
    });
  }

}
