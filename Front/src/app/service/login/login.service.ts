import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, tap} from "rxjs";
import {API_BASE_URL} from "../../app.config";
import {LoginResponseType} from "../../types/login-response.type";

interface LoginDTO {
  emailOuUsername: string;
  senha: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) { }

  login(credentials: LoginDTO): Observable<any> {
    return this.http.post<LoginResponseType>(`${this.apiUrl}/auth/login`, credentials).pipe(
      tap((value) => {
        localStorage.setItem('accessToken', value.accessToken);
        localStorage.setItem('expiresIn', value.expiresIn.toString());
      })
    )
  }
}
