package online.event.ticketing.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.event.ticketing.domain.IssuedTicket;
import online.event.ticketing.domain.OrderStatus;
import online.event.ticketing.domain.TicketOrder;
import online.event.ticketing.repository.IssuedTicketRepository;
import online.event.ticketing.repository.TicketOrderRepository;

@Service 
public class IssuedTicketService {
    
    @Autowired 
    private IssuedTicketRepository issuedTicketRepository;

    @Autowired 
    private TicketOrderRepository ticketOrderRepository;

    public String saveIssue(UUID orderId, IssuedTicket issuedTicket) {
        
        Optional<TicketOrder> check = ticketOrderRepository.findById(orderId);

        if(!check.isEmpty()) {
            if(check.get().getStatus().equals(OrderStatus.PAID)) {
                issuedTicket.setOrderTicket(check.get());
                issuedTicket.setIssuedDate(LocalDate.now());
                issuedTicketRepository.save(issuedTicket);
                return "Issued ticket saved successfully";
            } else {
            return "The issue tickets are only for PAID ticket orders";
            }
        } else {
            return "the ticket order with that id does not exit";
        }


    }

    public List<IssuedTicket> toSendAll() {
        List<IssuedTicket> all = issuedTicketRepository.findAll();

        return all;
    }

    public List<IssuedTicket> findByEmail(String email) {
        List<IssuedTicket> result = issuedTicketRepository.findByEmail(email);
        return result;
    }

    public String deleteIssued(UUID id) {
        
        boolean check = issuedTicketRepository.existsById(id);

        if(check) {
            issuedTicketRepository.deleteById(id);
            return "Issued ticket deleted successfully";
        } else {
            return "The issue with that id does not exist";
        }
    }


}
