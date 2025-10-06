package mentorship.roadmap.microservices.service_c.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "messages")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    private String content;
}