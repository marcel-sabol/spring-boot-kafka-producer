package cz.wesecon.kafkaproducer;

import cz.wesecon.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private KafkaTemplate<Object, Object> template;

    @PostMapping(path = "/send/to/topic")
    public void sendMessage(@RequestBody MessageDto dto) {

        Message message = Message.newBuilder()
                .setName(dto.getName())
                .setType(dto.getType())
                .setAmount(dto.getAmount())
                .build();

        this.template.send("a_messages", message);
    }

}
