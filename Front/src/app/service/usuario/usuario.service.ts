import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {API_BASE_URL} from "../../app.config";

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiBaseUrl = API_BASE_URL;

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) { }

  cadastrarUsuario(usuario: any): Observable<any> {

    var usuarioDTO = {
      ...usuario, dataNascimento: new Intl.DateTimeFormat('pt-BR', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
      }).format(new Date(usuario.dataNascimento))
    }

    usuarioDTO.telefone = this.formatarTelefone(usuario.telefone);

    return this.http.post(`${this.apiUrl}/usuario`, usuarioDTO);
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


  formatarTelefone(telefone: string): string {

    const regex = /^(\d{2})(\d{5})(\d{4})$/;
    const match = telefone.match(regex);
    if (match) {
      return `(${match[1]}) ${match[2]}-${match[3]}`;
    }
    return telefone;

    }

}
