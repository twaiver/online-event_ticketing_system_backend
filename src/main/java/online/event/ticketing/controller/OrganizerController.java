package online.event.ticketing.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.event.ticketing.domain.Organizer;
import online.event.ticketing.service.OrganizerService;

@RestController 
@RequestMapping (value = "/api/organizers")
public class OrganizerController {
    
    @Autowired 
    private OrganizerService organizerService;

    @PostMapping (value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOrganizer(@RequestBody  Organizer organizer) {
        String res = organizerService.saveOrganizer(organizer);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping (value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allOrganizers() {
        List<Organizer> organizers = organizerService.allOrganizers();
        return new ResponseEntity<>(organizers, HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteOrganizer(@PathVariable UUID id) {
        String res = organizerService.deleteOrganizer(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping (value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateOrganizer(@RequestBody  Organizer organizer, @PathVariable UUID id) {
        String res = organizerService.updateOrganizer(id, organizer);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
