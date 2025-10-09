package mentorship.roadmap.microservices.service_a.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentorship.roadmap.microservices.service_a.dto.MessageDTO;
import mentorship.roadmap.microservices.service_a.model.MessageEntity;
import mentorship.roadmap.microservices.service_a.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository repository;
    private final WebClient webClient;
    private final MessageMapper mapper;

    //private static final String SERVICE_B_URL = "http://service_b/api/process";

    @Override
    public void saveMessage(MessageDTO dto) {
        log.info("Saving message with id {}", dto.getId());
        MessageEntity entity = mapper.toEntity(dto);
        repository.save(entity);
        log.debug("Saved entity: {}", entity);
    }

    @Override
    public void sendtoServiceB(MessageDTO dto) {
        log.info("Sending message to Service B with id {}", dto.getId());
        webClient.post()
                .uri("/api/process")
                .body(BodyInserters.fromValue(dto))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        log.info("Message with id {} successfully sent to Service B", dto.getId());
    }
}
