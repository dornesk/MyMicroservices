package mentorship.roadmap.microservices.service_b.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mentorship.roadmap.microservices.service_b.dto.MessageDTO;
import mentorship.roadmap.microservices.service_b.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/process")
    @ResponseStatus(HttpStatus.OK)
    public void processMessage(@RequestBody @Valid MessageDTO dto) {
        messageService.processMessage(dto);
    }
}