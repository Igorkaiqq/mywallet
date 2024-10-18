package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.entities.ContaBancariaEntity;
import unipar.integrador.mywallet.application.interfaces.IContaBancaria;
import unipar.integrador.mywallet.infrastructure.repository.ContaBancariaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContaBancariaService implements IContaBancaria {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Override
    public ContaBancariaEntity save(ContaBancariaEntity contaBancaria) {
        return contaBancariaRepository.save(contaBancaria);
    }

    @Override
    public Optional<ContaBancariaEntity> findById(UUID id) {
        return contaBancariaRepository.findById(id);
    }

    @Override
    public List<ContaBancariaEntity> findAll() {
        return contaBancariaRepository.findAll();
    }

    @Override
    public ContaBancariaEntity update(ContaBancariaEntity contaBancaria) {
        return contaBancariaRepository.save(contaBancaria);
    }
}
