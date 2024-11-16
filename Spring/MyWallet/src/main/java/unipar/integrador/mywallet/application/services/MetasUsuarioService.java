package unipar.integrador.mywallet.application.services;


import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.dto.metas.MetasUsuarioDTO;
import unipar.integrador.mywallet.application.entities.MetasUsuarioEntity;
import unipar.integrador.mywallet.application.interfaces.IMetasUsuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MetasUsuarioService implements IMetasUsuario {


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
    public MetasUsuarioEntity update(MetasUsuarioEntity metasUsuario) {
        return null;
    }
}
