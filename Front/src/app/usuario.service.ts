import {Inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import  { Observable} from "rxjs";
import { API_BASE_URL} from "./app.config";

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiBaseUrl = API_BASE_URL;

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) { }

  cadastrarUsuario(usuario: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/usuario`, usuario);
  }

  buscarUsuarioPorId(id: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/usuario/${id}`);
  }

  buscarUsuarios(): Observable<any> {
    return this.http.get(`${this.apiUrl}/usuario`);
  }

  atualizarUsuario(usuario: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/usuario`, usuario);
  }

  deletarUsuario(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/usuario/${id}`);
  }

}
