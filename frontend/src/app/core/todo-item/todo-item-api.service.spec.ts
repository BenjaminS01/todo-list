import { TestBed } from '@angular/core/testing';

import { TodoItemApiService } from './todo-item-api.service';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { TodoItem } from './todo-item.model';
import { provideHttpClient } from '@angular/common/http';

describe('TodoItemApi', () => {
  let service: TodoItemApiService;
  let httpTesting: HttpTestingController;

  beforeEach(() => {
        TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    });
    service = TestBed.inject(TodoItemApiService);
    httpTesting = TestBed.inject(HttpTestingController);
  });

    afterEach(() => {
    httpTesting.verify();
  });

  it('should load all todo items and map completionDate to Date', () => {
    let result: TodoItem[] | undefined;
    service.getAll().subscribe(items => result = items);

    const req = httpTesting.expectOne('/api/todos');
    expect(req.request.method).toBe('GET');
    req.flush([{ id: '1', description: '50 Liegestütze', completionDate: '20.06.2026' }]);

    expect(result!.length).toBe(1);
    expect(result![0].completionDate).toEqual(new Date(2026, 5, 20));
  });

  it('should send DELETE request with correct id', () => {
    service.delete('1').subscribe();

    const req = httpTesting.expectOne('/api/todos/1');
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });

});
