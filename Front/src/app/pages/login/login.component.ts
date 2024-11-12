import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../service/login/login.service';
import { FormsModule } from '@angular/forms';
import { RouterLink} from "@angular/router";

@Component({
  selector: 'login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [FormsModule, RouterLink]
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
