package unipar.integrador.mywallet.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipar.integrador.mywallet.application.entities.SubcategoriaUsuarioEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubcategoriaUsuarioRepository extends JpaRepository<SubcategoriaUsuarioEntity, UUID> {

    Optional<SubcategoriaUsuarioEntity> findByUsuarioEntityIdAndCategoriaUsuarioIdAndSubcategoriaPadraoId(
            UUID usuarioId, UUID categoriaUsuarioId, UUID subcategoriaPadraoId);

    Optional<List<SubcategoriaUsuarioEntity>> findByCategoriaUsuarioIdAndStatusRegistro(UUID categoriaUsuarioId, StatusRegistroEnum statusRegistro);

}
