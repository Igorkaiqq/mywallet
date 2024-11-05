package unipar.integrador.mywallet.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipar.integrador.mywallet.application.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNome(String roleAdmin);
}
