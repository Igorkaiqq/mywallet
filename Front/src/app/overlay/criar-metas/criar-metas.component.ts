import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from '@angular/material/input';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";


@Component({
  selector: 'app-criar-metas',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './criar-metas.component.html',
  styleUrl: './criar-metas.component.css'
})
export class CriarMetasComponent implements OnInit{
  metasForm: FormGroup;
  valor: number;


  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<CriarMetasComponent>,
    @Inject (MAT_DIALOG_DATA) public data: { valor: number}
  ){
  this.valor = Number(data.valor);
  this.metasForm = this.fb.group({
    valor: ['', Validators.required],
  })
  }

  ngOnInit(): void {  }

  onSubmit(): void{
    if(this.metasForm.valid){
      this.dialogRef.close(this.metasForm.value);
    }
  }

  onClose(): void{
    this.dialogRef.close();
  }
}
