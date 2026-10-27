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

import online.event.ticketing.domain.Venue;
import online.event.ticketing.service.VenueService;

@RestController 
@RequestMapping (value = "/api/venues")
public class VenueController {
    
    @Autowired 
    private VenueService venueService;

    @PostMapping (value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveVenue(@RequestBody  Venue venue) {
        String res = venueService.saveVenue(venue);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping (value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allVenues() {
        List<Venue> venues = venueService.allVenues();
        return new ResponseEntity<>(venues, HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteVenue(@PathVariable UUID id) {
        String res = venueService.deleteVenue(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping (value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateVenue(@RequestBody  Venue venue, @PathVariable UUID id) {
        String res = venueService.updateVenue(id, venue);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping (value = "/free", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> freeVenues(){
        
        List<Venue> venues = venueService.freeVenue();
        
        return new ResponseEntity<>(venues, HttpStatus.OK);
    }
}
