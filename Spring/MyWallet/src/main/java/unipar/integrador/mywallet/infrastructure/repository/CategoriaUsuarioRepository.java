package unipar.integrador.mywallet.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipar.integrador.mywallet.application.dto.categoriaUsuario.CategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.entities.CategoriaUsuarioEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoriaUsuarioRepository extends JpaRepository<CategoriaUsuarioEntity, UUID> {

    List<CategoriaUsuarioEntity> findByStatusRegistro(StatusRegistroEnum statusRegistro);

    boolean existsByUsuarioEntityIdAndCategoriaPadraoEntityId(UUID usuarioId, UUID categoriaPadraoId);

}
