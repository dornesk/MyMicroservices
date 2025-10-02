package mentorship.roadmap.microservices.service_a.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import mentorship.roadmap.microservices.service_a.model.MessageType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private int id;

    @NonNull
    private MessageType type;

    @NonNull
    private String content;
}
