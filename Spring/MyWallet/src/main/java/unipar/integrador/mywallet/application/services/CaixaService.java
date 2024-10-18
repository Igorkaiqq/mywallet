package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.entities.CaixaEntity;
import unipar.integrador.mywallet.application.interfaces.ICaixa;
import unipar.integrador.mywallet.infrastructure.repository.CaixaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CaixaService implements ICaixa {

    @Autowired
    private CaixaRepository caixaRepository;

    @Override
    public CaixaEntity save(CaixaEntity caixa) {
        return caixaRepository.save(caixa);
    }

    @Override
    public Optional<CaixaEntity> findById(UUID id) {
        return caixaRepository.findById(id);
    }

    @Override
    public List<CaixaEntity> findAll() {
        return caixaRepository.findAll();
    }

    @Override
    public CaixaEntity update(CaixaEntity caixa) {
        return caixaRepository.save(caixa);
    }
}
