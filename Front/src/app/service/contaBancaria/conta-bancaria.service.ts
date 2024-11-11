import {Inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {API_BASE_URL} from "../../app.config";

interface ContaBancaria {
  id: string;
  usuarioId: string;
  nome: string;
  saldo: number;
  statusRegistro: string;
}

@Injectable({
  providedIn: 'root'
})
export class ContaBancariaService {

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) { }

  getContasBancarias(): Observable<ContaBancaria[]> {
    return this.http.get<ContaBancaria[]>(`${this.apiUrl}/contas-bancarias`);
  }

  criarContaBancaria(conta: ContaBancaria): Observable<ContaBancaria> {
    return this.http.post<ContaBancaria>(`${this.apiUrl}/contas-bancarias`, conta);
  }
}
