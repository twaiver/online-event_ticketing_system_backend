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

import online.event.ticketing.domain.TicketOrder;
import online.event.ticketing.service.TicketOrderService;

@RestController 
@RequestMapping (value = "/api/ticketorders")
public class TicketOrderController {
    
    @Autowired 
    private TicketOrderService ticketOrderService;

    @PostMapping (value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveTicketOrder(@RequestBody  TicketOrder ticketOrder) {
        String res = ticketOrderService.saveTicketOrder(ticketOrder);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping (value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allTicketOrders() {
        List<TicketOrder> ticketOrders = ticketOrderService.allTicketOrders();
        return new ResponseEntity<>(ticketOrders, HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTicketOrder(@PathVariable UUID id) {
        String res = ticketOrderService.deleteTicketOrder(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping (value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTicketOrder(@RequestBody  TicketOrder ticketOrder, @PathVariable UUID id) {
        String res = ticketOrderService.updateTicketOrder(id, ticketOrder);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
