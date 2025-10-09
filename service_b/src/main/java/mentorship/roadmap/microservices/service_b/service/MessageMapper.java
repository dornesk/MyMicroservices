package mentorship.roadmap.microservices.service_b.service;

import mentorship.roadmap.microservices.service_b.dto.MessageDTO;
import mentorship.roadmap.microservices.service_b.model.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    //@Mapping(target = "id", ignore = true) // чтобы не маппить id при создании сущности
    MessageEntity toEntity(MessageDTO dto);

    MessageDTO toDTO(MessageEntity entity);
}