import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [FormsModule]
})
export class LoginComponent {
  emailOuUsername: string = '';
  senha: string = '';

  constructor(private loginService: LoginService, private router: Router) {}

  onSubmit() {
    const credentials = {
      emailOuUsername: this.emailOuUsername,
      senha: this.senha
    };

    this.loginService.login(credentials).subscribe(
      response => {
        this.router.navigate(['/wallet']);
      },
      error => {
        alert('Usuário ou senha inválidos');
      }
    );
  }
}
