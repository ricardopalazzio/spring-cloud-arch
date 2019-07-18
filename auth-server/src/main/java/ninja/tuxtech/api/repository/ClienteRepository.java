package ninja.tuxtech.api.repository;

import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import ninja.tuxtech.api.entity.Cliente;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedEntityGraph;
import javax.validation.constraints.Null;
import java.util.List;


@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente ,  Long>  , JpaSpecificationExecutor<Cliente> {


     @EntityGraph(value = "clienteWithAddress" , type = EntityGraph.EntityGraphType.LOAD)
     @Query("from Cliente")
     List<Cliente> listWithAddres();

}