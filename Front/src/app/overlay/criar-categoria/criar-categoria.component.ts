import {Component, Inject} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatIcon} from "@angular/material/icon";
import {MatFormField} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";
import {Transacao} from "../../models/transacao/transacao";
import {TipoTransacao} from "../../models/tipoTransacao/tipo-transacao";
import {TransacaoService} from "../../service/transacao/transacao.service";
import {TipoTransacaoService} from "../../service/tipoTransacao/tipo-transacao.service";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-criar-categoria',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatIcon,
    MatFormField,
    MatSelect,
    MatOption,
    MatButton,
    MatInput,
    MatIconButton,
    FormsModule,
    NgForOf
  ],
  templateUrl: './criar-categoria.component.html',
  styleUrl: './criar-categoria.component.css'
})
export class CriarCategoriaComponent {
  categoriaForm: FormGroup;

  transacao: Transacao = {
    tipoTransacaoId: '',
    categoriaId: '',
    subcategoriaId: '',
    metodoPagamentoId: '',
    contaBancariaId: '',
    valor: '0',
    descricao: ''
  }

  tiposTransacao: TipoTransacao[] = [];

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<CriarCategoriaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { tipoInicial: string },
    private tipoTransacaoService: TipoTransacaoService
  ) {
    this.categoriaForm = this.fb.group({
      tipoTransacao: [data.tipoInicial || 'RECEITA', Validators.required],
      nome: ['', Validators.required]
    });
  }

  ngOnInit(): void {

    this.buscarTiposTransacao();
  }

  private buscarTiposTransacao(): void {
    this.tipoTransacaoService.getTiposTransacao().subscribe(tipos => {
      this.tiposTransacao = tipos;
    });
  }

  onSubmit(): void {
    if (this.categoriaForm.valid) {
      this.dialogRef.close(this.categoriaForm.value);
    }
  }

  onClose(): void {
    this.dialogRef.close();
  }
}
