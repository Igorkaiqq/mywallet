package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.dto.metodoPagamento.MetodoPagamentoDTO;
import unipar.integrador.mywallet.application.entities.MetodoPagamentoEntity;
import unipar.integrador.mywallet.application.interfaces.IMetodoPagamento;
import unipar.integrador.mywallet.infrastructure.repository.MetodoPagamentoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MetodoPagamentoService implements IMetodoPagamento {

    @Autowired
    MetodoPagamentoRepository metodoPagamentoRepository;

    @Override
    public MetodoPagamentoEntity save(MetodoPagamentoDTO dto) {
        return null;
    }

    @Override
    public Optional<MetodoPagamentoEntity> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<MetodoPagamentoEntity> findAll() {
        return metodoPagamentoRepository.findAll();
    }

    @Override
    public MetodoPagamentoEntity update(MetodoPagamentoEntity metodoPagamento) {
        return null;
    }
}
