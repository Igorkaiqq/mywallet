package unipar.integrador.mywallet.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unipar.integrador.mywallet.application.entities.TransacaoEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity, UUID> {

    List<TransacaoEntity> findByUsuario_IdOrderByDataDesc(UUID usuarioId);

    List<TransacaoEntity> findByUsuarioIdAndTipoTransacao_IdAndDataBetween(UUID usuarioId, UUID tipo, LocalDateTime dataInicio, LocalDateTime dataFim);

    List<TransacaoEntity> findTop5ByUsuarioIdAndTipoTransacao_IdAndDataBetweenOrderByValorDesc(UUID usuario_id, UUID tipoTransacao_id, LocalDateTime dataInicio, LocalDateTime dataFim);

}
