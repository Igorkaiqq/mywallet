import {Inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import  { Observable} from "rxjs";
import { API_BASE_URL} from "../../app.config";

@Injectable({
  providedIn: 'root'
})
export class SubcategoriaService {

  private apiBaseUrl = API_BASE_URL;

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) { }

  getSubcategoriasUsuario(): Observable<any> {

    const usuarioLogado = sessionStorage.getItem('usuarioLogado');

    if (usuarioLogado) {

      const usuario = JSON.parse(usuarioLogado);
      const usuarioId = usuario.id;

      return this.http.get(`${this.apiUrl}/subcategoria-usuario`);
    } else {
      throw new Error('Usuário não logado');
    }
  }

  getSubcategoriasPorCategoriaId(categoriaUsuarioId: string): Observable<any> {

   return this.http.get(`${this.apiUrl}/subcategoria-usuario/categoria-usuario/${categoriaUsuarioId}`);

  }

}
