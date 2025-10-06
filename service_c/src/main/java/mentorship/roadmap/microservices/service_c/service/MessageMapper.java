package mentorship.roadmap.microservices.service_c.service;

import mentorship.roadmap.microservices.service_c.dto.MessageDTO;
import mentorship.roadmap.microservices.service_c.model.MessageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageEntity toEntity(MessageDTO dto);
    MessageDTO toDto(MessageEntity entity);
}