package ninja.tuxtech.api.endpoint;

import ninja.tuxtech.api.entity.User;
import ninja.tuxtech.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserEndPoint extends CrudEndPoint<User , Long>{

    @Autowired
    public UserEndPoint(UserRepository repository) {
        super(repository);
    }
}
