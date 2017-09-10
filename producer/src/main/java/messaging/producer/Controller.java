package messaging.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    Producer producer;

    @Autowired
    public Controller(Producer producer) {
        this.producer = producer;
    }

    @GetMapping(path = "send")
    public String sendMessage() {
        try {
            producer.run();
        } catch (Exception e) {
           return e.getMessage();
        }

        return "Sent";
    }
}
