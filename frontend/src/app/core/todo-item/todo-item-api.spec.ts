import { TestBed } from '@angular/core/testing';

import { TodoItemApi } from './todo-item-api';

describe('TodoItemApi', () => {
  let service: TodoItemApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TodoItemApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
