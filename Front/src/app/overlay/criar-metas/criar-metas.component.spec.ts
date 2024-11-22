import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CriarMetasComponent} from './criar-metas.component';

describe('CriarMetasComponent', () => {
  let component: CriarMetasComponent;
  let fixture: ComponentFixture<CriarMetasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CriarMetasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CriarMetasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
