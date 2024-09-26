import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-criar-conta',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './cadastro-usuario.component.html',
  styleUrls: ['./cadastro-usuario.component.css']
})
export class CadastroUsuarioComponent implements OnInit {
  criarContaForm = new FormGroup({
    nome: new FormControl('', Validators.required),
    username: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
    telefone: new FormControl('', Validators.required),
    cpf: new FormControl('', [Validators.required, Validators.pattern(/^\d{11}$/)]), // CPF com 11 dígitos
    genero: new FormControl(''),
    dataNascimento: new FormControl('', Validators.required),
    pergunta1: new FormControl('', Validators.required),
    pergunta2: new FormControl('', Validators.required)
  });

  private httpClient = inject(HttpClient);

  ngOnInit(): void {
    this.httpClient.get('http://localhost:8080').subscribe({
      next: (response) => {
        console.log(response)
      }
    })
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

  onSubmit() {
    if (this.criarContaForm.valid) {
      this.httpClient.post('http://localhost:8080', {usuario : this.criarContaForm.value}).subscribe({
        next: (response) => {
          console.log(response);
        }
      });
    } else {
      console.log('Form inválido');
    }
  }

  onCancel() {
    this.criarContaForm.reset();
  }

  constructor(private router: Router) {}

  goToLogin() {
    this.router.navigate(['']);
  }
}
