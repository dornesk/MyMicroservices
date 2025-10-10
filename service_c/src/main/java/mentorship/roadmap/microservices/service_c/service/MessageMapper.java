package mentorship.roadmap.microservices.service_c.service;

import mentorship.roadmap.microservices.service_c.dto.MessageDTO;
import mentorship.roadmap.microservices.service_c.model.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mapping(source = "id", target = "id")
    MessageEntity toEntity(MessageDTO dto);

    MessageDTO toDto(MessageEntity entity);
}