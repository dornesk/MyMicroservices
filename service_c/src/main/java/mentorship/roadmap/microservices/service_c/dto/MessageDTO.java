package mentorship.roadmap.microservices.service_c.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import mentorship.roadmap.microservices.service_c.model.MessageType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    @NotNull
    private String id;

    @NotNull(message = "Type cannot be null")
    private MessageType type;

    @NotBlank(message = "Content cannot be blank")
    private String content;
}