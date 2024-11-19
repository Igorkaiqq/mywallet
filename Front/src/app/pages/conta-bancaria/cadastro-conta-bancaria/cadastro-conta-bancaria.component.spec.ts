import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CadastroContaBancariaComponent} from './cadastro-conta-bancaria.component';

describe('CadastroContaBancariaComponent', () => {
  let component: CadastroContaBancariaComponent;
  let fixture: ComponentFixture<CadastroContaBancariaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroContaBancariaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastroContaBancariaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
