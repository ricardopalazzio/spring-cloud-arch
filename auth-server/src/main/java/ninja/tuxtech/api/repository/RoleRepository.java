package ninja.tuxtech.api.repository;

import ninja.tuxtech.api.entity.Role;
import ninja.tuxtech.api.entity.enumerator.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}