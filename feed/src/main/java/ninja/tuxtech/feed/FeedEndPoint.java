package ninja.tuxtech.feed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedEndPoint {

    @GetMapping
    public List findAll() {
        return List.of("Java", "React");

    }

}
