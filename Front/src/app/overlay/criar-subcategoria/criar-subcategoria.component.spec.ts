import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CriarSubcategoriaComponent } from './criar-subcategoria.component';

describe('CriarSubcategoriaComponent', () => {
  let component: CriarSubcategoriaComponent;
  let fixture: ComponentFixture<CriarSubcategoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CriarSubcategoriaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CriarSubcategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
