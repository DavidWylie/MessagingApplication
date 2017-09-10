package messaging.subscriber.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import messaging.model.RabbitMessage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MessageHandlerImpl implements MessageHandler {
    private Map<String, Integer> dataReceived = new HashMap<>();
    private PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    @Override
    public void handleMessage(byte[] data) {
        RabbitMessage message = null;
        try {
            message = translateMessage(data);
            Phonenumber.PhoneNumber telephone =translateNumber(message.getTelephoneNumber());
            String country = phoneUtil.getRegionCodeForCountryCode(telephone.getCountryCode());
            int originalCount = dataReceived.getOrDefault(country,0);
            originalCount++;
             dataReceived.put(country,originalCount);
            log.info(message.toString());
        } catch (Exception e) {
            e.getMessage();
            log.error("Error procesisng message.");
        }
    }

    public Phonenumber.PhoneNumber translateNumber(String number) throws Exception {
        try {
            return phoneUtil.parse(number,"");
        } catch (NumberParseException e) {
            log.info("Failed to parse Phone number");
            throw new Exception("Error parsing Phone Number");
        }
    }

    public RabbitMessage translateMessage(byte[] data) throws IOException {
        String rawData = new String(data, StandardCharsets.UTF_8);
        ObjectMapper om = new ObjectMapper();
        return om.readValue(rawData,RabbitMessage.class);
    }

    @Override
    public Map<String,Integer> getData() {
        return dataReceived;
    }
}
