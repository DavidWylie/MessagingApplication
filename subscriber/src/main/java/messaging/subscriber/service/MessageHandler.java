package messaging.subscriber.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.Map;

public interface MessageHandler{

    @RabbitListener(queues = {"telephone-data"} )
    void handleMessage(byte[] data);

    Map<String,Integer> getData();
}
