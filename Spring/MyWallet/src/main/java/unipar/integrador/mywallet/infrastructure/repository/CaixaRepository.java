package unipar.integrador.mywallet.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unipar.integrador.mywallet.application.entities.CaixaEntity;

import java.util.UUID;

public interface CaixaRepository extends JpaRepository<CaixaEntity, UUID> {
}
