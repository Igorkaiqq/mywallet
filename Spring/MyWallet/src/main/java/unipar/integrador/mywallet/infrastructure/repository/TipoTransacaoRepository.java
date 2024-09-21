package unipar.integrador.mywallet.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipar.integrador.mywallet.application.entities.TipoTransacaoEntity;
import unipar.integrador.mywallet.application.enums.TipoTransacaoEnum;

import java.util.UUID;

@Repository
public interface TipoTransacaoRepository extends JpaRepository<TipoTransacaoEntity, UUID> {

    TipoTransacaoEntity findByTipoTransacaoEnum(TipoTransacaoEnum tipoTransacaoEnum);

}
