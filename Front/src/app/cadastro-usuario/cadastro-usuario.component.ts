import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask';
import { UsuarioService } from '../usuario.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-criar-conta',
  standalone: true,
  imports: [ReactiveFormsModule, NgxMaskDirective, NgxMaskPipe, CommonModule],
  templateUrl: './cadastro-usuario.component.html',
  styleUrls: ['./cadastro-usuario.component.css']
})
export class CadastroUsuarioComponent implements OnInit {
  criarContaForm: FormGroup;

  private usuarioService = inject(UsuarioService);
  private router = inject(Router);

  constructor() {
    this.criarContaForm = new FormGroup({
      nome: new FormControl('', Validators.required),
      username: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      senha: new FormControl('', Validators.required),
      telefone: new FormControl('', Validators.required),
      cpf: new FormControl('', [Validators.required, Validators.pattern(/^\d{11}$/)]),
      genero: new FormControl(''),
      dataNascimento: new FormControl('', Validators.required),
      perguntaSecreta: new FormControl('', Validators.required),
      respostaSecreta: new FormControl('', Validators.required)
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    if (this.criarContaForm.valid) {
      this.criarContaForm.markAllAsTouched();
      console.log("Formulário de cadastro de usuário válido");

      this.usuarioService.cadastrarUsuario(this.criarContaForm.value).subscribe({
        next: (response) => {
          console.log('Cadastro realizado com sucesso', response);
          this.router.navigate(['/usuarios']);
        },
        error: (error) => {
          console.error('Erro no cadastro', error);
        }
      });
    } else {
      console.log("Formulário de cadastro de usuário inválido");
    }
  }

  onCancel() {
    this.criarContaForm.reset();
  }

  goToLogin() {
    this.router.navigate(['']);
  }
}
