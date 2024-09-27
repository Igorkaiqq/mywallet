import { TestBed } from '@angular/core/testing';

import { TipoTransacaoService } from './tipo-transacao.service';

describe('TipoTransacaoService', () => {
  let service: TipoTransacaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TipoTransacaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
