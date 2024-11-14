import {Component, EventEmitter, Output} from '@angular/core';
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-actions-buttons',
  standalone: true,
  imports: [
    MatIcon
  ],
  templateUrl: './actions-buttons.component.html',
  styleUrl: './actions-buttons.component.css'
})
export class ActionButtonsComponent {
  @Output() edit = new EventEmitter<void>();
  @Output() delete = new EventEmitter<void>();

  onEdit(): void {
    this.edit.emit();
  }

  onDelete(): void {
    this.delete.emit();
  }
}
