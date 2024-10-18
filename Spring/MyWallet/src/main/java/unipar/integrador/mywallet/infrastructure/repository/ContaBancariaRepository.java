package unipar.integrador.mywallet.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unipar.integrador.mywallet.application.entities.ContaBancariaEntity;

import java.util.UUID;

public interface ContaBancariaRepository extends JpaRepository<ContaBancariaEntity, UUID> {
}
