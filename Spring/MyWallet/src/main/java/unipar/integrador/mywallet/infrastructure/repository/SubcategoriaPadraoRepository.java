package unipar.integrador.mywallet.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipar.integrador.mywallet.application.entities.SubcategoriaPadraoEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubcategoriaPadraoRepository extends JpaRepository<SubcategoriaPadraoEntity, UUID> {

    List<SubcategoriaPadraoEntity> findAllByStatusRegistro(StatusRegistroEnum statusRegistroEnum);
    List<SubcategoriaPadraoEntity> findAllByStatusRegistroAndCategoriaPadraoId(StatusRegistroEnum statusRegistroEnum, UUID categoriaPadraoId);

}
