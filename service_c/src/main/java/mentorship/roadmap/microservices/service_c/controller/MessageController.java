package mentorship.roadmap.microservices.service_c.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mentorship.roadmap.microservices.service_c.dto.MessageDTO;
import mentorship.roadmap.microservices.service_c.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void saveMessage(@RequestBody @Valid MessageDTO dto) {
        messageService.saveAndPublish(dto);
    }
}
