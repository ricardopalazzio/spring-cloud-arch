package ninja.tuxtech.api.endpoint;

import ninja.tuxtech.api.entity.Address;
import ninja.tuxtech.api.entity.User;
import ninja.tuxtech.api.repository.AddressRepository;
import ninja.tuxtech.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressEndPoint extends CrudEndPoint<Address, Long>{

    @Autowired
    public AddressEndPoint(AddressRepository repository) {
        super(repository);
    }
}
