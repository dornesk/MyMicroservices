package mentorship.roadmap.microservices.service_a.testfactory;

import mentorship.roadmap.microservices.service_a.dto.MessageDTO;
import mentorship.roadmap.microservices.service_a.model.MessageEntity;
import mentorship.roadmap.microservices.service_a.model.MessageType;

public class MessageTestFactory {
    public static MessageDTO createDefaultMessageDTO() {
        return MessageDTO.builder()
                .id(1)
                .type(MessageType.NORMAL)  // укажи существующий тип из enum, например DEFAULT
                .content("Default test message")
                .build();
    }

    public static MessageEntity createDefaultMessageEntity() {
        MessageDTO dto = createDefaultMessageDTO();
        return MessageEntity.builder()
                .id(String.valueOf(dto.getId()))
                .type(dto.getType())
                .content(dto.getContent())
                .build();
    }
}
