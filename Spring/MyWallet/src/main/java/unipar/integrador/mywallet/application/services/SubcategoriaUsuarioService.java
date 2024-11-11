package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.dto.subcategoriaUsuario.SubcategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.entities.*;
import unipar.integrador.mywallet.application.interfaces.ISubcategoriaUsuario;
import unipar.integrador.mywallet.infrastructure.repository.SubcategoriaUsuarioRepository;
import unipar.integrador.mywallet.infrastructure.repository.CategoriaUsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubcategoriaUsuarioService implements ISubcategoriaUsuario {

    @Autowired
    private SubcategoriaUsuarioRepository subcategoriaUsuarioRepository;

    @Autowired
    private CategoriaUsuarioRepository categoriaUsuarioRepository;


    @Override
    public SubcategoriaUsuarioEntity save(SubcategoriaUsuarioDTO dto) {

        Optional<CategoriaUsuarioEntity> categoriaUsuarioOpt = categoriaUsuarioRepository.findById(dto.categoriaUsuarioId());
        if (categoriaUsuarioOpt.isEmpty() || !categoriaUsuarioOpt.get().getUsuarioEntity().getId().equals(dto.usuarioId())) {
            throw new IllegalArgumentException("Categoria não registrada para o usuário.");
        }

        SubcategoriaUsuarioEntity subcategoriaUsuario = new SubcategoriaUsuarioEntity();
        subcategoriaUsuario.setId(UUID.randomUUID());

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(dto.usuarioId());
        subcategoriaUsuario.setUsuarioEntity(usuario);
        subcategoriaUsuario.setCategoriaUsuario(categoriaUsuarioOpt.get());

        if (dto.subcategoriaPadraoId() != null) {
            SubcategoriaPadraoEntity subcategoriaPadrao = new SubcategoriaPadraoEntity();
            subcategoriaPadrao.setId(dto.subcategoriaPadraoId());
            subcategoriaUsuario.setSubcategoriaPadrao(subcategoriaPadrao);
        }

        subcategoriaUsuario.setNome(dto.nome());
        subcategoriaUsuario.setStatusRegistro(dto.statusRegistro());

        return subcategoriaUsuarioRepository.save(subcategoriaUsuario);
    }

    @Override
    public Optional<SubcategoriaUsuarioEntity> findById(UUID id) {
        return subcategoriaUsuarioRepository.findById(id);
    }

    @Override
    public List<SubcategoriaUsuarioEntity> findAll() {
        return subcategoriaUsuarioRepository.findAll();
    }

    @Override
    public SubcategoriaUsuarioEntity update(SubcategoriaUsuarioEntity subcategoriaUsuario) {
        return subcategoriaUsuarioRepository.save(subcategoriaUsuario);
    }

    @Override
    public void deleteById(UUID id) {
        subcategoriaUsuarioRepository.deleteById(id);
    }

    public SubcategoriaUsuarioDTO convertToDto(SubcategoriaUsuarioEntity subcategoriaUsuarioEntity) {
        return new SubcategoriaUsuarioDTO(
                subcategoriaUsuarioEntity.getUsuarioEntity().getId(),
                subcategoriaUsuarioEntity.getCategoriaUsuario().getId(),
                subcategoriaUsuarioEntity.getSubcategoriaPadrao() != null ? subcategoriaUsuarioEntity.getSubcategoriaPadrao().getId() : null,
                subcategoriaUsuarioEntity.getNome(),
                subcategoriaUsuarioEntity.getStatusRegistro()
        );
    }

    public Optional<SubcategoriaUsuarioEntity> findByUsuarioIdAndCategoriaUsuarioIdAndSubcategoriaPadraoId(
            UUID usuarioId, UUID categoriaUsuarioId, UUID subcategoriaPadraoId) {
        return subcategoriaUsuarioRepository.findByUsuarioEntityIdAndCategoriaUsuarioIdAndSubcategoriaPadraoId(
                usuarioId, categoriaUsuarioId, subcategoriaPadraoId);
    }

    public Optional<List<SubcategoriaUsuarioEntity>> findByCategoriaUsuarioId(UUID categoriaUsuarioId) {
        return subcategoriaUsuarioRepository.findByCategoriaUsuarioId(categoriaUsuarioId);
    }

}
