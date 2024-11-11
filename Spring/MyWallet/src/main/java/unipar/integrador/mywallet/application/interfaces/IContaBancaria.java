package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.dto.contaBancaria.CadastroContaBancariaDTO;
import unipar.integrador.mywallet.application.entities.ContaBancariaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IContaBancaria {

    ContaBancariaEntity save(CadastroContaBancariaDTO dto);
    Optional<ContaBancariaEntity> findById(UUID id);
    List<ContaBancariaEntity> findAll();
    ContaBancariaEntity update(ContaBancariaEntity contaBancaria);

}
