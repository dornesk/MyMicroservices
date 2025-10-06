package mentorship.roadmap.microservices.service_b.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentorship.roadmap.microservices.service_b.dto.MessageDTO;
import mentorship.roadmap.microservices.service_b.model.MessageCache;
import mentorship.roadmap.microservices.service_b.model.MessageType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private static final String REDIS_KEY_PREFIX = "message:";
    private static final Duration TTL = Duration.ofMinutes(5);

    private final RedisTemplate<String, Object> redisTemplate;
    private final WebClient webClient;
    private final MessageMapper mapper;

    @Override
    public void processMessage(MessageDTO dto) {
        log.info("Processing message with id {}", dto.getId());

        if (dto.getType() == null) {
            log.warn("Message type is null, skipping Redis cache");
        } else if (dto.getType().equals(MessageType.IMPORTANT)) {
            MessageCache cacheEntity = mapper.toEntity(dto);
            String key = REDIS_KEY_PREFIX + dto.getId();
            redisTemplate.opsForValue().set(key, cacheEntity, TTL);
            log.info("Cached message with id {} in Redis with TTL {} minutes", dto.getId(), TTL.toMinutes());
        }

        webClient.post()
                .uri("/api/save")
                .body(BodyInserters.fromValue(dto))
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        log.info("Sent message with id {} to Service C", dto.getId());
    }
}