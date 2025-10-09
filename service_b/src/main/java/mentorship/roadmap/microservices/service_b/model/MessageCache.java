package mentorship.roadmap.microservices.service_b.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageCache {
    private String id;

    private MessageType type;

    private String content;
}