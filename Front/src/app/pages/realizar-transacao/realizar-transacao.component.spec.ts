import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RealizarTransacaoComponent} from './realizar-transacao.component';

describe('RealizarTransacaoComponent', () => {
  let component: RealizarTransacaoComponent;
  let fixture: ComponentFixture<RealizarTransacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RealizarTransacaoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RealizarTransacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
