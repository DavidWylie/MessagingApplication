package messaging.producer;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import messaging.model.RabbitMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
public class Producer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void run(int number) throws Exception {
        log.info("Sending message...");
        int i = 0;
        while (i <= number) {
            log.info(Integer.toString(i) + " of " + Integer.toString(number));
            UUID id = UUID.randomUUID();
            String phoneNumber = getExampleNumber(getCountry());
            rabbitTemplate.convertAndSend("telephone-data", new RabbitMessage(id.toString(), phoneNumber));
            i++;
        }
    }

    private String getCountry() {
        List<String> regions = new ArrayList<String>(PhoneNumberUtil.getInstance().getSupportedRegions());
        int position = ThreadLocalRandom.current().nextInt(0, regions.size());
        return regions.get(position);
    }

    private String getExampleNumber(String countryCode) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.getExampleNumber(countryCode);
        return phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
    }
}
