package unipar.integrador.mywallet.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipar.integrador.mywallet.application.entities.CategoriaPadraoEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoriaPadraoRepository extends JpaRepository<CategoriaPadraoEntity, UUID> {

    List<CategoriaPadraoEntity> findAllByStatusRegistro(StatusRegistroEnum statusRegistroEnum);

}
