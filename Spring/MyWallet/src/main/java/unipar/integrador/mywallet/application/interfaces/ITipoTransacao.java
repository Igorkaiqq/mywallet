package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.dto.metodoPagamento.MetodoPagamentoDTO;
import unipar.integrador.mywallet.application.dto.tipoTransacao.TipoTransacaoDTO;
import unipar.integrador.mywallet.application.entities.MetodoPagamentoEntity;
import unipar.integrador.mywallet.application.entities.TipoTransacaoEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITipoTransacao {

    TipoTransacaoEntity save(TipoTransacaoDTO dto);
    Optional<TipoTransacaoEntity> findById(UUID id);
    List<TipoTransacaoEntity> findAll();
    TipoTransacaoEntity update(TipoTransacaoEntity tipoTransacao);

}
