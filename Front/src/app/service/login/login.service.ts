import {Inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import  { Observable} from "rxjs";
import { API_BASE_URL} from "../../app.config";

interface LoginDTO {
  emailOuUsername: string;
  senha: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiBaseUrl = API_BASE_URL;

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) { }

  login(credentials: LoginDTO): Observable<any> {
    return this.http.post(`${this.apiUrl}/usuario/login`, credentials);
  }
}
