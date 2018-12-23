package ninja.tuxtech.api.repository;

import ninja.tuxtech.api.entity.Address;
import ninja.tuxtech.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address , Long> {
}
