package mentorship.roadmap.microservices.service_c.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentorship.roadmap.microservices.service_c.dto.MessageDTO;
import mentorship.roadmap.microservices.service_c.model.MessageEntity;
import mentorship.roadmap.microservices.service_c.repository.MessageRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private static final String TOPIC_OUT = "out";

    private final MessageRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MessageMapper mapper;

    @Override
    public void saveAndPublish(MessageDTO dto) {
        log.info("Saving message with id {}", dto.getId());
        MessageEntity entity = mapper.toEntity(dto);
        log.info("Mapped entity id: {}", entity.getId());
        if (entity.getId() == null) {
            throw new IllegalArgumentException("ID is null after mapping! Check DTO and Mapper.");
        }
        repository.save(entity);
        log.info("Message saved in DB: {}", entity.getId());

        kafkaTemplate.send(TOPIC_OUT, dto.getContent())
                .thenAccept(result -> log.info("Published message to Kafka topic {}: {}", TOPIC_OUT, result.getProducerRecord().value()))
                .exceptionally(ex -> {
                    log.error("Failed to publish message to Kafka", ex);
                    return null;
                });
    }
}