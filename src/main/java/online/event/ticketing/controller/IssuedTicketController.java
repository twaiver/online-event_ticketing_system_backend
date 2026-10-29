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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import online.event.ticketing.domain.IssuedTicket;
import online.event.ticketing.service.IssuedTicketService;

@RestController
@RequestMapping(value = "/api/issued-tickets")
public class IssuedTicketController {

    @Autowired
    private IssuedTicketService issuedTicketService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveIssue(@RequestParam("ticketOrderId") UUID id, @RequestBody IssuedTicket ticket) {
        String result = issuedTicketService.saveIssue(id, ticket);

        if (result.equals("the ticket order with that id does not exit")) {
            return new ResponseEntity<>(result, HttpStatus.valueOf(404));
        } else if (result.equals("The issue tickets are only for PAID ticket orders")) {
            return new ResponseEntity<>(result, HttpStatus.valueOf(404));
        } else {
            return new ResponseEntity<>(result, HttpStatus.valueOf(201));
        }
    }

    @GetMapping (value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findsTicketAll() {

        List<IssuedTicket> all = issuedTicketService.toSendAll();

        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping (value = "/buyer/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findsByEmail(@PathVariable String email) {

        List<IssuedTicket> all = issuedTicketService.findByEmail(email);

        return new ResponseEntity<>(all, HttpStatus.OK);
    }


    @DeleteMapping (value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteIssued(@PathVariable UUID id) {
        String delString = issuedTicketService.deleteIssued(id);

        if(delString.equals("The issue with that id does not exist")) {
            return new ResponseEntity<>(delString, HttpStatus.valueOf(404));
        } else {
            return new ResponseEntity<>(delString, HttpStatus.OK);
        }
    }


}
