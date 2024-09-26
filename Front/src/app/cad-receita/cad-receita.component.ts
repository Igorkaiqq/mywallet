import { Component } from '@angular/core';
import { RouterLink } from '@angular/router'; 
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cad-receita',
  standalone: true, 
  imports: [RouterLink, CommonModule], 
  templateUrl: './cad-receita.component.html',
  styleUrls: ['./cad-receita.component.css']
})
export class CadReceitaComponent {

  selectedDate: string = '';
selectedLaunch: string = '';

selectDate(option: string) {
  this.selectedDate = option;
}

selectLaunch(option: string) {
  this.selectedLaunch = option;
}

constructor(private router: Router) {}

goCancel() {
  this.router.navigate(['/wallet']);
}
}
