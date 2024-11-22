package unipar.integrador.mywallet.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipar.integrador.mywallet.application.entities.MetasUsuarioEntity;
import unipar.integrador.mywallet.application.enums.TipoTransacaoEnum;

import java.util.UUID;

@Repository
public interface MetasUsuarioRepository extends JpaRepository<MetasUsuarioEntity, UUID> {

    MetasUsuarioEntity findByCategoriaId_IdAndCategoriaId_TipoTransacaoEntity_Id(UUID categoriaId, UUID id);
}
