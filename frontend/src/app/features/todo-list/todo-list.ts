import { Component, inject, signal } from '@angular/core';
import { TodoItemComponent } from "./todo-item/todo-item";
import { TodoItemApiService } from '../../core/todo-item/todo-item-api.service';
import { TodoItem } from '../../core/todo-item/todo-item.model';
import { APP_CONSTANTS } from '../../core/constants';

@Component({
  selector: 'app-todo-list',
  imports: [TodoItemComponent],
  templateUrl: './todo-list.html',
  styleUrl: './todo-list.scss',
})
export class TodoListComponent {
  protected readonly constants = APP_CONSTANTS;
  private readonly service = inject(TodoItemApiService)
  items = signal<TodoItem[]>([])

  ngOnInit(): void {
    this.service.getAll().subscribe(items => this.items.set(items))
  }
  //was bei create über api?

  onDelete(idParam: string): void {
    this.service.delete(idParam).subscribe(
      () => this.items.update(
        items => items.filter(item => item.id !== idParam)
    ))
  }
}
