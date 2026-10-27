package online.event.ticketing.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.event.ticketing.domain.OrderStatus;
import online.event.ticketing.domain.TicketOrder;
import online.event.ticketing.repository.TicketOrderRepository;

@Service 
public class TicketOrderService {
    
    @Autowired 
    private TicketOrderRepository ticketOrderRepository;

    public String saveTicketOrder(TicketOrder ticketOrder) {
        ticketOrderRepository.save(ticketOrder);
        return "ticketOrder have been saved succuesfully";
    }

    public List<TicketOrder> allTicketOrders() {
        List<TicketOrder> ticketOrders = ticketOrderRepository.findAll();

        return ticketOrders;
    }

    public String deleteTicketOrder(UUID id) {
        boolean tick = ticketOrderRepository.existsById(id);
        if (!tick) {
            return "the TicketOrder with that id is not exist";
        } else {
            ticketOrderRepository.deleteById(id);
            return "the ticketOrder with id of " + id + " deleted successfully";
        }
    }


    public String updateTicketOrder(UUID id, TicketOrder ticketOrder) {
        Optional<TicketOrder> tick = ticketOrderRepository.findById(id);

        if(tick.isEmpty()) {
            return "the ticketOrder with that id is not exist";
        } else {
            TicketOrder theTick = tick.get();

            theTick.setEventTitle(ticketOrder.getEventTitle());
            theTick.setOrderDate(ticketOrder.getOrderDate());
            theTick.setQuantity(ticketOrder.getQuantity());
            theTick.setStatus(ticketOrder.getStatus());
            theTick.setBuyer(ticketOrder.getBuyer());
            theTick.setOrganizer(ticketOrder.getOrganizer());

            ticketOrderRepository.save(theTick);

            return "the ticketOrder with this id " + id + " have been updated succeffly";
        }
    }

    public List<TicketOrder> findByStatus(OrderStatus status) {
        List<TicketOrder> allStatu = ticketOrderRepository.findByStatusOrderByOrderDateDesc(status);

        return allStatu;
    }

    public List<TicketOrder> betweenDates(LocalDate start, LocalDate end) {
        List<TicketOrder> result = ticketOrderRepository.findByOrderDateBetweenOrderByOrderDate(start, end);
        return result;
    }
}
