package messaging.subscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import messaging.subscriber.service.MessageHandler;

import java.util.Map;

@RestController
public class Controller {

    private MessageHandler messageHandler;

    @Autowired
    public Controller(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @GetMapping(path="/data")
    public Map<String,Integer> getData() {
        return messageHandler.getData();
    }
}
