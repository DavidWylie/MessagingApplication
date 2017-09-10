package messaging.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    Producer producer;

    @Autowired
    public Controller(Producer producer) {
        this.producer = producer;
    }

    @GetMapping(path = "send/{messageCount}")
    public String sendMessage(@PathVariable(name="messageCount") int messageCount) {
        try {
            producer.run(messageCount);
        } catch (Exception e) {
           return e.getMessage();
        }

        return "Sent";
    }
}
