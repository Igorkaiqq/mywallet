import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../service/login/login.service';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { RouterLink} from "@angular/router";
import {LoginCredentials} from "../../models/usuario/login";
import {NgIf} from "@angular/common";

@Component({
  selector: 'login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [ReactiveFormsModule, RouterLink, NgIf]
})
export class LoginComponent {

  loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private loginService: LoginService, private router: Router) {
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

        this.router.navigate(['/wallet']);
      },
      error => {
        alert('Usuário ou senha inválidos');
      }
    );
  }

  goToRegister() {
    this.router.navigate(['/cadastrar-usuario']);
  }

}
