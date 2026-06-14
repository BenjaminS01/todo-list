import { Component, signal } from '@angular/core';
import { TodoListComponent } from "./features/todo-list/todo-list";

@Component({
  selector: 'app-root',
  imports: [TodoListComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  protected readonly title = signal('todo');
}
