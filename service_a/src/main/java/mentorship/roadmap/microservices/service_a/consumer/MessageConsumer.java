package mentorship.roadmap.microservices.service_a.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper = new ObjectMapper();

//    @KafkaListener(topics = "in", groupId = "service_a")
//    public void listen(@Valid MessageDTO dto) {
//        log.info("Received message in Kafka consumer with id {}", dto.getId());
//        messageService.saveMessage(dto);
//        messageService.sendtoServiceB(dto);
//        log.info("Processed message in Kafka consumer with id {}", dto.getId());
//    }

    @KafkaListener(topics = "in", groupId = "service_a")
    public void listen(String message) {
        try {
            MessageDTO dto = objectMapper.readValue(message, MessageDTO.class);
            log.info("Received message in Kafka consumer with id {}", dto.getId());
            messageService.saveMessage(dto);
            try {
                messageService.sendtoServiceB(dto);
            } catch (Exception e) {
                log.error("Ошибка при отправке сообщения в Service B", e);
                // Логика пропуска или обработки ошибки
            }
            log.info("Processed message in Kafka consumer with id {}", dto.getId());
        } catch (JsonProcessingException e) {
            log.error("Error parsing message from Kafka: {}", message, e);
        }
    }
}
