import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MovimentacaoDashboardComponent} from './movimentacao-dashboard.component';


describe('MovimentacaoDashboardComponent', () => {
  let component: MovimentacaoDashboardComponent;
  let fixture: ComponentFixture<MovimentacaoDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MovimentacaoDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovimentacaoDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
