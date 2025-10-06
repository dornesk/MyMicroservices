package mentorship.roadmap.microservices.service_b.service;

import mentorship.roadmap.microservices.service_b.dto.MessageDTO;
import mentorship.roadmap.microservices.service_b.model.MessageCache;
import mentorship.roadmap.microservices.service_b.testfactory.MessageTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class MessageServiceImplTest {

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

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

        // Мокируем RedisTemplate
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        // Мокируем цепочку WebClient
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(BodyInserter.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Void.class)).thenReturn(Mono.empty());
    }

    @Test
    @DisplayName("Преобразование DTO в Entity и сохранение в Redis")
    void testCacheMessage() {
        MessageDTO dto = MessageTestFactory.createDefaultMessageDTO();
        MessageCache cacheEntity = MessageTestFactory.createDefaultMessageCache();
        dto.setType(mentorship.roadmap.microservices.service_b.model.MessageType.IMPORTANT);

        when(mapper.toEntity(dto)).thenReturn(cacheEntity);

        service.processMessage(dto);

        verify(mapper).toEntity(dto);
        verify(valueOperations).set(anyString(), eq(cacheEntity), any());
    }

    @Test
    @DisplayName("Отправка сообщения в сервис C – вызов WebClient по правильной цепочке")
    void testSendtoServiceC() {
        MessageDTO dto = MessageTestFactory.createDefaultMessageDTO();

        service.processMessage(dto);

        verify(webClient).post();
        verify(requestBodyUriSpec).uri("/api/save");
        verify(requestBodySpec).body(any(BodyInserter.class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(Void.class);
    }
}