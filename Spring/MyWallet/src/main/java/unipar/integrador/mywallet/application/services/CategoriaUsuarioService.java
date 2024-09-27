package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.dto.categoriaUsuario.CategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.entities.CategoriaPadraoEntity;
import unipar.integrador.mywallet.application.entities.CategoriaUsuarioEntity;
import unipar.integrador.mywallet.application.entities.TipoTransacaoEntity;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.interfaces.ICategoriaUsuario;
import unipar.integrador.mywallet.infrastructure.repository.CategoriaUsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoriaUsuarioService implements ICategoriaUsuario {

    @Autowired
    private CategoriaUsuarioRepository categoriaUsuarioRepository;


    @Override
    public CategoriaUsuarioEntity save(CategoriaUsuarioDTO dto) {
        CategoriaUsuarioEntity categoriaUsuario = new CategoriaUsuarioEntity();
        categoriaUsuario.setId(UUID.randomUUID());

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(dto.usuarioId());
        categoriaUsuario.setUsuarioEntity(usuarioEntity);

        TipoTransacaoEntity tipoTransacaoEntity = new TipoTransacaoEntity();
        tipoTransacaoEntity.setId(dto.tipoTransacao());
        categoriaUsuario.setTipoTransacaoEntity(tipoTransacaoEntity);

        CategoriaPadraoEntity categoriaPadraoEntity = null;
        if (dto.categoriaPadrao() != null) {
            categoriaPadraoEntity = new CategoriaPadraoEntity();
            categoriaPadraoEntity.setId(dto.categoriaPadrao());
        }
        categoriaUsuario.setCategoriaPadraoEntity(categoriaPadraoEntity);

        categoriaUsuario.setNome(dto.nome());
        categoriaUsuario.setStatusRegistro(dto.statusRegistro());

        return categoriaUsuarioRepository.save(categoriaUsuario);
    }



    @Override
    public Optional<CategoriaUsuarioEntity> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<CategoriaUsuarioEntity> findAll() {
        return List.of();
    }

    @Override
    public CategoriaUsuarioEntity update(CategoriaUsuarioEntity categoriaUsuario) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public List<CategoriaUsuarioDTO> findAllCategoriasUsuariosAtivas() {
        return categoriaUsuarioRepository.findByStatusRegistro(StatusRegistroEnum.ATIVO).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CategoriaUsuarioDTO> findByUsuarioId(UUID usuarioId) {
        return categoriaUsuarioRepository.findByUsuarioEntityId(usuarioId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CategoriaUsuarioDTO convertToDto(CategoriaUsuarioEntity categoriaUsuarioEntity) {
        return new CategoriaUsuarioDTO(
                categoriaUsuarioEntity.getId(),
                categoriaUsuarioEntity.getUsuarioEntity().getId(),
                categoriaUsuarioEntity.getTipoTransacaoEntity().getId(),
                categoriaUsuarioEntity.getCategoriaPadraoEntity() != null ? categoriaUsuarioEntity.getCategoriaPadraoEntity().getId() : null,
                categoriaUsuarioEntity.getNome(),
                categoriaUsuarioEntity.getStatusRegistro()
        );
    }

    public Optional<CategoriaUsuarioEntity> findByUsuarioIdAndCategoriaPadraoId(UUID usuarioId, UUID categoriaPadraoId) {
        return categoriaUsuarioRepository.findByUsuarioEntityIdAndCategoriaPadraoEntityId(usuarioId, categoriaPadraoId);
    }


    public boolean isCategoriaRegistrada(UUID usuarioId, UUID categoriaPadraoId) {
        return categoriaUsuarioRepository.existsByUsuarioEntityIdAndCategoriaPadraoEntityId(usuarioId, categoriaPadraoId);
    }


}

