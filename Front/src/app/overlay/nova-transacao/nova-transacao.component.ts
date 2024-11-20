import {Component, Inject} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatButton} from "@angular/material/button";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatOption, MatSelect} from "@angular/material/select";
import {NgForOf} from "@angular/common";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-criar-movimentacao',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    MatFormField,
    MatInput,
    MatSelect,
    MatButton,
    NgForOf,
    MatOption,
    MatIcon
  ],
  templateUrl: './nova-transacao.component.html',
  styleUrls: ['./nova-transacao.component.css']
})
export class NovaTransacaoComponent {
  movimentacaoForm: FormGroup;

  categorias: string[] = ['Receita', 'Despesa'];

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<NovaTransacaoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.movimentacaoForm = this.fb.group({
      descricao: ['', Validators.required],
      valor: [0, Validators.required],
      data: [new Date(), Validators.required],
      categoria: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.movimentacaoForm.valid) {
      this.dialogRef.close(this.movimentacaoForm.value);
    }
  }

  onClose(): void {
    this.dialogRef.close();
  }
}
