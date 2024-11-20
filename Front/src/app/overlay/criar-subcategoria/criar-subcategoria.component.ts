import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";

@Component({
  selector: 'app-criar-subcategoria',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './criar-subcategoria.component.html',
  styleUrls: ['./criar-subcategoria.component.css']
})
export class CriarSubcategoriaComponent implements OnInit {
  subcategoriaForm: FormGroup;
  categoriaNome: string;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<CriarSubcategoriaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { categoriaNome: string }
  ) {
    this.categoriaNome = data.categoriaNome;
    this.subcategoriaForm = this.fb.group({
      nome: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.subcategoriaForm.valid) {
      this.dialogRef.close(this.subcategoriaForm.value);
    }
  }

  onClose(): void {
    this.dialogRef.close();
  }
}
