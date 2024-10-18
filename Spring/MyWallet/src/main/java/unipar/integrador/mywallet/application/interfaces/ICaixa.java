package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.entities.CaixaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICaixa {

    CaixaEntity save(CaixaEntity dto);
    Optional<CaixaEntity> findById(UUID id);
    List<CaixaEntity> findAll();
    CaixaEntity update(CaixaEntity caixa);

}
