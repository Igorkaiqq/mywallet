import {Component} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {LoginService} from '../../service/login/login.service';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {LoginCredentials} from "../../models/usuario/login";
import {NgIf} from "@angular/common";
import {NotificationService} from "../../service/notification/notification.service";

@Component({
  selector: 'login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [ReactiveFormsModule, RouterLink, NgIf]
})
export class LoginComponent {

  loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private router: Router,
    private notificationService: NotificationService
  ) {
    this.loginForm = this.formBuilder.group({
      emailOuUsername: ['', Validators.required],
      senha: ['', Validators.required]
    });
  }

  onSubmit() {

    if (this.loginForm.invalid) {
      alert('Preencha todos os campos');
      return;
    }

    const credentials: LoginCredentials = this.loginForm.value;

    this.loginService.login(credentials).subscribe(
      response => {
        this.notificationService.showSuccess('Login efetuado com sucesso');
        this.router.navigate(['/tela-inicial']);
      },
      error => {
        this.notificationService.showError('Usuário ou senha inválidos');
      }
    );
  }

  goToRegister() {
    this.router.navigate(['/cadastrar-usuario']);
  }

}
