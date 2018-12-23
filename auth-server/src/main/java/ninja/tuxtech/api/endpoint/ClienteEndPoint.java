package ninja.tuxtech.api.endpoint;

import ninja.tuxtech.api.entity.Cliente;
import ninja.tuxtech.api.entity.User;
import ninja.tuxtech.api.repository.ClienteRepository;
import ninja.tuxtech.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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
}
