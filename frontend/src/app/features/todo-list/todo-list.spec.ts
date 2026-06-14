import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoListComponent } from './todo-list';
import { TodoItem } from '../../core/todo-item/todo-item.model';
import { TodoItemApiService } from '../../core/todo-item/todo-item-api.service';
import { of } from 'rxjs';

describe('TodoList', () => {
  let component: TodoListComponent;
  let fixture: ComponentFixture<TodoListComponent>;
  let serviceMock: { getAll: ReturnType<typeof vi.fn>, delete: ReturnType<typeof vi.fn> };
  
  const mockItems: TodoItem[] = [
    { id: '1', description: 'Steuererklärung machen', completionDate: new Date(2026, 5, 20) }
  ];

  beforeEach(async () => {
    serviceMock = {
      getAll: vi.fn().mockReturnValue(of(mockItems)),
      delete: vi.fn().mockReturnValue(of(void 0))
    };
    await TestBed.configureTestingModule({
      imports: [TodoListComponent],
       providers: [
        { provide: TodoItemApiService, useValue: serviceMock }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(TodoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display todo items', () => {
    expect(fixture.nativeElement.textContent).toContain('Steuererklärung machen');
  });

  it('should show empty message when list is empty', () => {
    serviceMock.getAll.mockReturnValue(of([]));
    component.ngOnInit();
    fixture.detectChanges();
    expect(fixture.nativeElement.textContent).toContain('Keine Einträge vorhanden');
  });

  it('should remove item from list after delete', () => {
    component.onDelete('1');
    fixture.detectChanges();
    expect(component.items().length).toBe(0);
  });

});
