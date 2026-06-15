import { Component, input, output } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { TodoItem } from '../../../core/todo-item/todo-item.model';
import { DatePipe } from '@angular/common';
import { APP_CONSTANTS } from '../../../core/constants';

@Component({
  selector: 'app-todo-item',
  imports: [MatCardModule, MatIconModule, MatButtonModule, DatePipe],
  templateUrl: './todo-item.html',
  styleUrl: './todo-item.scss',
})
export class TodoItemComponent {
  protected readonly constants = APP_CONSTANTS;
  item = input.required<TodoItem>();
  delete = output<string>();

  onDelete(): void {
    this.delete.emit(this.item().id);
  }
}
