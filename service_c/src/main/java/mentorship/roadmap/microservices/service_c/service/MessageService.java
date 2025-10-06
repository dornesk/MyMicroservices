package mentorship.roadmap.microservices.service_c.service;

import mentorship.roadmap.microservices.service_c.dto.MessageDTO;

public interface MessageService {
    void saveAndPublish(MessageDTO dto);
}