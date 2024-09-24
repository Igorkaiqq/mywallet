package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.dto.transacao.TransacaoDTO;
import unipar.integrador.mywallet.application.entities.TransacaoEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITransacao {

    TransacaoEntity save(TransacaoDTO dto);
    Optional<TransacaoEntity> findById(UUID id);
    List<TransacaoEntity> findAll();
    TransacaoEntity update(TransacaoEntity Transacao);
    void deleteById(UUID id);

}
