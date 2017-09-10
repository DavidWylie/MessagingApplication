package messaging.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import messaging.model.RabbitMessage;

import java.util.UUID;

@Component
public class Producer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void run() throws Exception {
        System.out.println("Sending message...");
        int i = 0;
        while (i < 3) {
            UUID id = UUID.randomUUID();
            rabbitTemplate.convertAndSend("telephone-data", new RabbitMessage(id.toString(), "+441612344343"));
            Thread.sleep(100);
            i++;
        }
    }
}
