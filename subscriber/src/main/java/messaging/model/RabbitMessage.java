package messaging.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RabbitMessage {
    private String id;

    private String telephoneNumber;
}
