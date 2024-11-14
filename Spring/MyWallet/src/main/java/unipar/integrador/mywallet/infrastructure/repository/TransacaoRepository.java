package unipar.integrador.mywallet.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unipar.integrador.mywallet.application.entities.TransacaoEntity;

import java.util.List;
import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity, UUID> {

    List<TransacaoEntity> findByUsuario_IdOrderByDataDesc(UUID usuarioId);

}
