package mentorship.roadmap.microservices.service_c.service;

import mentorship.roadmap.microservices.service_c.dto.MessageDTO;
import mentorship.roadmap.microservices.service_c.model.MessageEntity;
import mentorship.roadmap.microservices.service_c.testfactory.MessageTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.kafka.core.KafkaTemplate;
import mentorship.roadmap.microservices.service_c.repository.MessageRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MessageServiceImplTest {

    @Mock
    private MessageRepository repository;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private MessageMapper mapper;

    @InjectMocks
    private MessageServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Преобразование DTO в Entity и сохранение в базу")
    void testSaveMessage() {
        MessageDTO dto = MessageTestFactory.createDefaultMessageDTO();
        MessageEntity entity = MessageTestFactory.createDefaultMessageEntity();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(any(MessageEntity.class))).thenReturn(entity);

        service.saveAndPublish(dto);

        verify(mapper).toEntity(dto);
        verify(repository).save(entity);
    }

    @Test
    @DisplayName("Публикация сообщения в Kafka topic out")
    void testPublishToKafka() {
        MessageDTO dto = MessageTestFactory.createDefaultMessageDTO();

        when(mapper.toEntity(dto)).thenReturn(MessageTestFactory.createDefaultMessageEntity());
        when(repository.save(any(MessageEntity.class))).thenReturn(MessageTestFactory.createDefaultMessageEntity());

        service.saveAndPublish(dto);

        verify(kafkaTemplate).send(eq("out"), eq(dto.getContent()));
    }
}