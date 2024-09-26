// src/app/login.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface LoginDTO {
  emailOuUsername: string;
  senha: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'http://localhost:8080/api/v1/usuario/login';

  constructor(private http: HttpClient) {}

  login(credentials: LoginDTO): Observable<any> {
    return this.http.post(this.apiUrl, credentials);
  }
}
