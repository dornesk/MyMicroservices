package mentorship.roadmap.microservices.service_c.testfactory;

import mentorship.roadmap.microservices.service_c.dto.MessageDTO;
import mentorship.roadmap.microservices.service_c.model.MessageEntity;
import mentorship.roadmap.microservices.service_c.model.MessageType;

public class MessageTestFactory {

    public static MessageDTO createDefaultMessageDTO() {
        return MessageDTO.builder()
                .id("1")
                .type(MessageType.NORMAL)
                .content("Default test message")
                .build();
    }

    public static MessageEntity createDefaultMessageEntity() {
        MessageDTO dto = createDefaultMessageDTO();
        return MessageEntity.builder()
                //в Entity id — Long, чтобы не возникало проблем, ставим null для автогенерации
                .id(null)
                .type(dto.getType())
                .content(dto.getContent())
                .build();
    }
}