package online.event.ticketing.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import online.event.ticketing.domain.OrderStatus;
import online.event.ticketing.domain.TicketOrder;
import online.event.ticketing.service.TicketOrderService;

@RestController 
@RequestMapping (value = "/api/ticket-orders")
public class TicketOrderController {
    
    @Autowired 
    private TicketOrderService ticketOrderService;

    @PostMapping (value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveTicketOrder(@RequestBody  TicketOrder ticketOrder) {
        String res = ticketOrderService.saveTicketOrder(ticketOrder);

        if(res.equals("Ticket order date cannot be in past")) {
            return new ResponseEntity<>(res, HttpStatus.valueOf(400));
        } else if(res.equals("Organizer already has an order on that date")) {
            return new ResponseEntity<>(res, HttpStatus.valueOf(409));
        } else {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @GetMapping (value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allTicketOrders() {
        List<TicketOrder> ticketOrders = ticketOrderService.allTicketOrders();
        return new ResponseEntity<>(ticketOrders, HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTicketOrder(@PathVariable UUID id) {
        String res = ticketOrderService.deleteTicketOrder(id);
        if(res.equals("the ticket order with that id is not exist")) {
            return new ResponseEntity<>(res, HttpStatus.valueOf(404));
        } else if(res.equals("That ticket order has issued tickets, delete them first")) {
            return new ResponseEntity<>(res, HttpStatus.valueOf(409));
        } else {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @PutMapping (value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTicketOrder(@RequestBody  TicketOrder ticketOrder, @PathVariable UUID id) {
        String res = ticketOrderService.updateTicketOrder(id, ticketOrder);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping (value = "/by-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByStatus(@RequestParam ("status") OrderStatus status){

        List<TicketOrder> allLists = ticketOrderService.findByStatus(status);

        return new ResponseEntity<>(allLists, HttpStatus.OK);
    }

    @GetMapping (value = "/between", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> betweenDates(@RequestParam ("start") LocalDate start, @RequestParam ("end") LocalDate end){
        List<TicketOrder> result = ticketOrderService.betweenDates(start, end);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchExchange (value = "/{id}/status")
    public ResponseEntity<?> patchByStatus(@PathVariable UUID id, @RequestParam ("status") OrderStatus status){

        String res = ticketOrderService.updateBasedOnStatus(id, status);
        if(res.equals("Ticket order status updated successfully")) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else if(res.equals("the ticket order with that id does not exist")) {
            return new ResponseEntity<>(res, HttpStatus.valueOf(404 ));
        } else {
            return new ResponseEntity<>(res, HttpStatus.valueOf(400));
        }
        
    }

    @GetMapping (value = "/stats/by-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> groupBy() {
        List<Object[]> theData = ticketOrderService.groupBy();

        return new ResponseEntity<>(theData, HttpStatus.OK);
    }

    @PatchMapping (value = "/cancel-day")
    public ResponseEntity<?> refundedTicket(@RequestParam ("organizerId") UUID id, @RequestParam ("date") LocalDate date) {
        String result = ticketOrderService.refundTicket(date, id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping (value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> pagingTicket(@RequestParam ("page") int page, 
    @RequestParam  ("size") int size, @RequestParam  ("sort") String sort) {
        Page<TicketOrder> theData = ticketOrderService.pagingTicket(page, size, sort);

        return new ResponseEntity<>(theData, HttpStatus.OK);
    }
}
