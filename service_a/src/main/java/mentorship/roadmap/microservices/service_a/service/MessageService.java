package mentorship.roadmap.microservices.service_a.service;


import mentorship.roadmap.microservices.service_a.dto.MessageDTO;

public interface MessageService {
    void saveMessage(MessageDTO dto);
    void sendtoServiceB(MessageDTO dto);
}
