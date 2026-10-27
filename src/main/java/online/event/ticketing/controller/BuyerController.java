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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import online.event.ticketing.domain.Buyer;
import online.event.ticketing.service.BuyerService;

@RestController 
@RequestMapping (value = "/api/buyers")
public class BuyerController {
    
    @Autowired 
    private BuyerService buyerService;

    @PostMapping (value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveBuyer(@RequestBody  Buyer buyer) {
        String res = buyerService.saveBuyer(buyer);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping (value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allBuyers() {
        List<Buyer> buyers = buyerService.allBuyers();
        return new ResponseEntity<>(buyers, HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteBuyer(@PathVariable UUID id) {
        String res = buyerService.deleteBuyer(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping (value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateBuyer(@PathVariable UUID id, @RequestBody Buyer buyer) {
        String res = buyerService.updateBuyer(id, buyer);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping (value = "/by-last-name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByLastName(@RequestParam ("lastName") String lastName){
        List<Buyer> theBuyer = buyerService.findByLastName(lastName);
        return new ResponseEntity<>(theBuyer, HttpStatus.OK);
    }
}
