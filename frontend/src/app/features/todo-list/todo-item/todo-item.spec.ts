import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoItemComponent } from './todo-item';
import { TodoItem } from '../../../core/todo-item/todo-item.model';

describe('TodoItem', () => {
  let component: TodoItemComponent;
  let fixture: ComponentFixture<TodoItemComponent>;

  const mockItem: TodoItem = {
    id: '1',
    description: 'Öl wechsel',
    completionDate: new Date(2026, 5, 20)
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodoItemComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(TodoItemComponent);
    component = fixture.componentInstance;
    fixture.componentRef.setInput('item', mockItem);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display description', () => {
    expect(fixture.nativeElement.textContent).toContain('Öl wechsel');
  });

  it('should display completionDate formatted as DD.MM.YYYY', () => {
    expect(fixture.nativeElement.textContent).toContain('20.06.2026');
  });

  it('should emit item id when delete button is clicked', () => {
    let emittedId: string | undefined;
    component.delete.subscribe((id: string) => emittedId = id);

    fixture.nativeElement.querySelector('mat-card').click();

    expect(emittedId).toBe('1');
  });
});
