package mentorship.roadmap.microservices.service_a.service;

import mentorship.roadmap.microservices.service_a.dto.MessageDTO;
import mentorship.roadmap.microservices.service_a.model.MessageEntity;
import mentorship.roadmap.microservices.service_a.repository.MessageRepository;
import mentorship.roadmap.microservices.service_a.testfactory.MessageTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class MessageServiceImplTest {

    @Mock
    private MessageRepository repository;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private MessageMapper mapper;

    @InjectMocks
    private MessageServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Преобразование DTO в Entity и сохранение")
    void testSaveMessage() {
        MessageDTO dto = MessageTestFactory.createDefaultMessageDTO();
        MessageEntity entity = MessageTestFactory.createDefaultMessageEntity();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(any(MessageEntity.class))).thenReturn(entity);

        service.saveMessage(dto);

        verify(mapper).toEntity(dto);
        verify(repository).save(entity);
    }

    @Test
    @DisplayName("Отправка сообщения в сервис B – вызов WebClient по правильной цепочке")
    void testSendtoServiceB() {
        MessageDTO dto = MessageTestFactory.createDefaultMessageDTO();

        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(BodyInserter.class))).thenReturn(requestHeadersSpec);  // замокали body()
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Void.class)).thenReturn(Mono.empty());

        service.sendtoServiceB(dto);

        verify(webClient).post();
        verify(requestBodyUriSpec).uri(anyString());
        verify(requestBodySpec).body(any(BodyInserter.class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(Void.class);
    }
}
