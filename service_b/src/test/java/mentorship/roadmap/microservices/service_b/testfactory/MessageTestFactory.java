package mentorship.roadmap.microservices.service_b.testfactory;

import mentorship.roadmap.microservices.service_b.dto.MessageDTO;
import mentorship.roadmap.microservices.service_b.model.MessageEntity;
import mentorship.roadmap.microservices.service_b.model.MessageType;

public class MessageTestFactory {
    public static MessageDTO createDefaultMessageDTO() {
        return MessageDTO.builder()
                .id("1")
                .type(MessageType.NORMAL)  // существующий тип из enum сервиса B
                .content("Default test message")
                .build();
    }

    public static MessageEntity createDefaultMessageCache() {
        MessageDTO dto = createDefaultMessageDTO();
        return MessageEntity.builder()
                .id(dto.getId())
                .type(dto.getType())
                .content(dto.getContent())
                .build();
    }
}