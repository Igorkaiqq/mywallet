package unipar.integrador.mywallet.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipar.integrador.mywallet.application.entities.MetodoPagamentoEntity;

import java.util.UUID;

@Repository
public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamentoEntity, UUID> {
}
