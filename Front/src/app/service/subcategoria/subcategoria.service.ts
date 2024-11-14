import {Inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import  { Observable} from "rxjs";
import { API_BASE_URL} from "../../app.config";
import {Subcategoria} from "../../models/subcategoria/subcategoria";
import {Categoria} from "../../models/categoria/categoria";

@Injectable({
  providedIn: 'root'
})
export class SubcategoriaService {

  private apiBaseUrl = API_BASE_URL;

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) { }

  getSubcategoriasPorCategoriaId(categoriaUsuarioId: string): Observable<any> {

   return this.http.get(`${this.apiUrl}/subcategoria-usuario/categoria-usuario/${categoriaUsuarioId}`);

  }

  atualizarsubCategoria(subcategoria: Subcategoria): Observable<any> {
    return this.http.put(`${this.apiUrl}/subcategoria-usuario/update`, subcategoria);
  }

  excluirsubCategoria(id: string) {
    return this.http.delete(`${this.apiUrl}/subcategoria-usuario/${id}`);
  }

  registrarSubcategoria(subcategoria: Subcategoria): Observable<any> {
    return this.http.post(`${this.apiUrl}/subcategoria-usuario`, subcategoria);
  }
}
