package unipar.integrador.mywallet.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID>{
    Optional<UsuarioEntity> findById(UUID id);
}
