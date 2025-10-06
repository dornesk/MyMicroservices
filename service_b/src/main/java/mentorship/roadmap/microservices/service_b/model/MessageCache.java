package mentorship.roadmap.microservices.service_b.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageCache {
    private int id;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    private String content;
}