package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.dto.tipoTransacao.TipoTransacaoDTO;
import unipar.integrador.mywallet.application.entities.TipoTransacaoEntity;
import unipar.integrador.mywallet.application.interfaces.ITipoTransacao;
import unipar.integrador.mywallet.infrastructure.repository.TipoTransacaoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TipoTransacaoService implements ITipoTransacao {

    @Autowired
    TipoTransacaoRepository tipoTransacaoRepository;

    @Override
    public TipoTransacaoEntity save(TipoTransacaoDTO dto) {
        return null;
    }

    @Override
    public Optional<TipoTransacaoEntity> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<TipoTransacaoEntity> findAll() {
        return tipoTransacaoRepository.findAll();
    }

    @Override
    public TipoTransacaoEntity update(TipoTransacaoEntity tipoTransacao) {
        return null;
    }
}
