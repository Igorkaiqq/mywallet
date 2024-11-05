import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GraficoReceitasComponent } from './grafico-receitas.component';

describe('GraficoReceitasComponent', () => {
  let component: GraficoReceitasComponent;
  let fixture: ComponentFixture<GraficoReceitasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GraficoReceitasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GraficoReceitasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
