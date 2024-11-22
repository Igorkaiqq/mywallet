import {Component, inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {NgxMaskDirective, NgxMaskPipe} from 'ngx-mask';
import {UsuarioService} from '../../service/usuario/usuario.service';
import {CommonModule} from '@angular/common';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatNativeDateModule} from '@angular/material/core';
import {NotificationService} from "../../service/notification/notification.service";

@Component({
  selector: 'app-criar-conta',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgxMaskDirective,
    NgxMaskPipe,
    CommonModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    MatNativeDateModule
  ],
  templateUrl: './cadastro-usuario.component.html',
  styleUrls: ['./cadastro-usuario.component.css']
})
export class CadastroUsuarioComponent implements OnInit {
  criarContaForm: FormGroup;

  private usuarioService = inject(UsuarioService);
  private router = inject(Router);

  errorMessages: string[] = [];

  constructor(
    private notificationService: NotificationService
  ) {
    this.criarContaForm = new FormBuilder().group({
      nome: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(6)]],
      telefone: ['', Validators.required],
      cpf: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
      genero: [''],
      dataNascimento: ['', Validators.required],
      perguntaSecreta: ['', Validators.required],
      respostaSecreta: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {

    this.criarContaForm.markAllAsTouched();

    if (this.criarContaForm.valid) {
      this.usuarioService.cadastrarUsuario(this.criarContaForm.value).subscribe({
        next: (response) => {
          console.log('Cadastro realizado com sucesso', response);
          this.notificationService.showSuccess('Conta criada com sucesso!');
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 2000);
        },
        error: (error) => {
          if (error.error) {
            const errors = error.error;
            this.errorMessages = Object.keys(errors).map((key) => errors[key]);
          } else {
            this.errorMessages = ['Erro ao realizar o cadastro de conta. Tente novamente.'];
          }
          this.notificationService.showError(this.formatErrorMessages(this.errorMessages));
        }
      });
    } else {
      console.log("Formulário de cadastro de usuário inválido");
    }
  }

  private formatErrorMessages(errorMessages: string[]): string {
    return errorMessages.join('\n');
  }

  onCancel(): void {
    this.criarContaForm.reset();
    this.router.navigate(['/login']);
  }
}
