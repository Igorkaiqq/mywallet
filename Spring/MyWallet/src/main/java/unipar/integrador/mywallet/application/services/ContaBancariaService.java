package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.dto.contaBancaria.CadastroContaBancariaDTO;
import unipar.integrador.mywallet.application.entities.CaixaEntity;
import unipar.integrador.mywallet.application.entities.ContaBancariaEntity;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.exception.EntityNotFoundException;
import unipar.integrador.mywallet.application.interfaces.IContaBancaria;
import unipar.integrador.mywallet.infrastructure.repository.ContaBancariaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContaBancariaService implements IContaBancaria {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;
    @Autowired
    private UsuarioService usuarioService;

    public UUID getUsuarioAutenticadoId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return UUID.fromString(authentication.getName());
    }

    @Override
    public ContaBancariaEntity save(CadastroContaBancariaDTO contaBancaria) {

        UUID usuarioId = getUsuarioAutenticadoId();

        UsuarioEntity usuario = usuarioService.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        ContaBancariaEntity contaBancariaEntity = new ContaBancariaEntity();
        contaBancariaEntity.setUsuario(usuario);
        contaBancariaEntity.setNome(contaBancaria.nome());
        contaBancariaEntity.setSaldo(contaBancaria.saldo());
        contaBancariaEntity.setStatusRegistro(StatusRegistroEnum.ATIVO);

        return contaBancariaRepository.save(contaBancariaEntity);
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
