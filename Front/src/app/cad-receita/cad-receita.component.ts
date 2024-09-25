import { Component } from '@angular/core';
import { RouterLink } from '@angular/router'; 
import { CommonModule } from '@angular/common';

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

}
