package mentorship.roadmap.microservices.service_a.service;


import mentorship.roadmap.microservices.service_a.dto.MessageDTO;
import mentorship.roadmap.microservices.service_a.model.MessageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageEntity toEntity(MessageDTO dto);
    MessageDTO toDTO(MessageEntity entity);
//
//    public MessageEntity toEntity(MessageDTO dto) {
//        if (dto == null) return null;
//        return MessageEntity.builder()
//                .id(dto.getId())
//                .type(dto.getType())
//                .content(dto.getContent())
//                .build();
//    }
//
//    public MessageDTO toDto(MessageEntity entity) {
//        if (entity == null) return null;
//        return MessageDTO.builder()
//                .id(entity.getId())
//                .type(entity.getType())
//                .content(entity.getContent())
//                .build();
//    }
}
