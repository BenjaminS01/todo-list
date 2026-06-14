import { HttpClient } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { map, Observable } from 'rxjs';
import { TodoItem } from './todo-item.model';


@Service()
export class TodoItemApiService {
    private readonly http = inject(HttpClient);

    private readonly apiUrl = '/api/todos';

    getAll(): Observable<TodoItem[]> {
        return this.http.get<any[]>(this.apiUrl).pipe(
            map(restItems => {
                return restItems.map(restItem => this.mapToTodoItem(restItem))
            })
        );
    }

    delete(id: string): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }

    private mapToTodoItem(restItem: any): TodoItem {
        return {
                id: restItem.id,
                description: restItem.description,
                completionDate: this.parseDate(restItem.completionDate)
        }
    }

    private parseDate(dateStr: string): Date {
        const [day, month, year] = dateStr.split('.');
        return new Date(+ year, + month - 1, + day);
    }
}
