package mentorship.roadmap.microservices.service_b.service;

import mentorship.roadmap.microservices.service_b.dto.MessageDTO;

public interface MessageService {
    void processMessage(MessageDTO dto);
}