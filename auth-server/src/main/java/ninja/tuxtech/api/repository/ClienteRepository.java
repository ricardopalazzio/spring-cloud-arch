package ninja.tuxtech.api.repository;

import ninja.tuxtech.api.entity.Cliente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedEntityGraph;
import javax.validation.constraints.Null;
import java.util.List;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente ,  Long> {


     @EntityGraph(value = "clienteWithAddress" , type = EntityGraph.EntityGraphType.LOAD)
     @Query("from Cliente")
     List<Cliente> listWithAddres();
}
