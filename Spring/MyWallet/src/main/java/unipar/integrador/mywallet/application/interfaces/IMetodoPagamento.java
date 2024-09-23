package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.dto.metodoPagamento.MetodoPagamentoDTO;
import unipar.integrador.mywallet.application.dto.subcategoriaUsuario.SubcategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.entities.MetodoPagamentoEntity;
import unipar.integrador.mywallet.application.entities.SubcategoriaUsuarioEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IMetodoPagamento {

    MetodoPagamentoEntity save(MetodoPagamentoDTO dto);
    Optional<MetodoPagamentoEntity> findById(UUID id);
    List<MetodoPagamentoEntity> findAll();
    MetodoPagamentoEntity update(MetodoPagamentoEntity metodoPagamento);

}
