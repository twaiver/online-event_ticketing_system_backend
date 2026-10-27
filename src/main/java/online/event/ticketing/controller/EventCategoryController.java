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

import online.event.ticketing.domain.EventCategory;
import online.event.ticketing.service.EventCategoryService;

@RestController 
@RequestMapping (value = "/api/event-categories")
public class EventCategoryController {
    
    @Autowired 
    private EventCategoryService eventCategoryService;

    @PostMapping (value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveEventCategory(@RequestBody  EventCategory eventCategory) {
        String res = eventCategoryService.saveEvent(eventCategory);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping (value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allEventCategorys() {
        List<EventCategory> eventCategorys = eventCategoryService.allEvents();
        return new ResponseEntity<>(eventCategorys, HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteEventCategory(@PathVariable UUID id) {
        String res = eventCategoryService.deleteEvent(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping (value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEventCategory(@RequestBody  EventCategory eventCategory, @PathVariable UUID id) {
        String res = eventCategoryService.updateEvent(id, eventCategory);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping (value = "/unused")
    public ResponseEntity<?> unused() {

        List<EventCategory> data = eventCategoryService.unused();

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
