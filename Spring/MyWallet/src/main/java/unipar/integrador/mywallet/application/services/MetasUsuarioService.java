package unipar.integrador.mywallet.application.services;


import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.dto.metas.MetasUsuarioDTO;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoDTO;
import unipar.integrador.mywallet.application.entities.MetasUsuarioEntity;
import unipar.integrador.mywallet.application.entities.TransacaoEntity;
import unipar.integrador.mywallet.application.exception.EntityNotFoundException;
import unipar.integrador.mywallet.application.interfaces.IMetasUsuario;
import unipar.integrador.mywallet.infrastructure.repository.MetasUsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MetasUsuarioService implements IMetasUsuario {


    private final MetasUsuarioRepository metasUsuarioRepository;

    public MetasUsuarioService(MetasUsuarioRepository metasUsuarioRepository) {
        this.metasUsuarioRepository = metasUsuarioRepository;
    }

    @Override
    public MetasUsuarioEntity save(MetasUsuarioDTO dto) {
        return null;
    }

    @Override
    public Optional<MetasUsuarioEntity> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<MetasUsuarioEntity> findAll() {
        return List.of();
    }

    @Override
    public MetasUsuarioEntity update(UUID id, MetasUsuarioDTO dto) {
        MetasUsuarioEntity existing = metasUsuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meta com ID " + id + " não encontrada."));

        existing.setValor(dto.valor());

        return metasUsuarioRepository.save(existing);
    }
}
