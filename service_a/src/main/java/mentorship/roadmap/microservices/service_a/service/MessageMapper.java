package mentorship.roadmap.microservices.service_a.service;


import mentorship.roadmap.microservices.service_a.dto.MessageDTO;
import mentorship.roadmap.microservices.service_a.model.MessageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageEntity toEntity(MessageDTO dto);
    MessageDTO toDTO(MessageEntity entity);
}
