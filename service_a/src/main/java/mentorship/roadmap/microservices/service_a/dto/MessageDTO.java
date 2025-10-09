package mentorship.roadmap.microservices.service_a.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import mentorship.roadmap.microservices.service_a.model.MessageType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String id;

    @NotNull(message = "Type cannot be null")
    private MessageType type;

    @NotBlank(message = "Content of message cannot be blank")
    private String content;
}
