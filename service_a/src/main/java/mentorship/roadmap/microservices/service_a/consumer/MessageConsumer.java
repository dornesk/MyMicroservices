package mentorship.roadmap.microservices.service_a.consumer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentorship.roadmap.microservices.service_a.dto.MessageDTO;
import mentorship.roadmap.microservices.service_a.service.MessageService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumer {
    private final MessageService messageService;

    @KafkaListener(topics = "in", groupId = "service_a_group")
    public void listen(@Valid MessageDTO dto) {
        log.info("Received message in Kafka consumer with id {}", dto.getId());
        messageService.saveMessage(dto);
        messageService.sendtoServiceB(dto);
        log.info("Processed message in Kafka consumer with id {}", dto.getId());
    }
}
