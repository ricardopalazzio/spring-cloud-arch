package ninja.tuxtech.api.endpoint;

import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.*;
import ninja.tuxtech.api.entity.Cliente;
import ninja.tuxtech.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Joins({ @Join( path = "address" , alias = "address")})
@Or({
        @Spec(path="name", params="name", spec= Like.class),
        @Spec(path="surname", params="name", spec= Like.class),
        @Spec(path = "address.street" , params="address", spec= Like.class )
})
@JoinFetch(paths = { "address" })
interface ClienteSpec extends Specification<Cliente> {
}

@RestController
@RequestMapping("/cliente")
public class ClienteEndPoint extends CrudEndPoint<Cliente, Long>{


    ClienteRepository repository;

    @Autowired
    public ClienteEndPoint(ClienteRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @GetMapping("/withAddres")
    public ResponseEntity<?> withAddres(){
        return  ResponseEntity.ok(this.repository.listWithAddres());
    }


    @GetMapping("/search")
    public ResponseEntity<?> all( ClienteSpec spec , Pageable pageable){
        return ResponseEntity.ok(repository.findAll(spec , pageable));
    }
}