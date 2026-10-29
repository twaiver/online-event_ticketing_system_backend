package online.event.ticketing.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import online.event.ticketing.domain.OrderStatus;
import online.event.ticketing.domain.TicketOrder;
import online.event.ticketing.repository.IssuedTicketRepository;
import online.event.ticketing.repository.TicketOrderRepository;

@Service 
public class TicketOrderService {
    
    @Autowired 
    private TicketOrderRepository ticketOrderRepository;

    @Autowired 
    private IssuedTicketRepository issuedTicketRepository;

    public String saveTicketOrder(TicketOrder ticketOrder) {
        LocalDate nowDate = LocalDate.now();
        if(ticketOrder.getOrderDate().isBefore(nowDate)) {
            return "Ticket order date cannot be in past";
        }else {
            
            boolean check = ticketOrderRepository.existsByOrganizerAndOrderDateAndStatusNot(ticketOrder.getOrganizer(), ticketOrder.getOrderDate(), OrderStatus.REFUNDED);

            if(check) {
                return "Organizer already has an order on that date";
            } else {
                ticketOrder.setStatus(OrderStatus.PENDING);
                ticketOrderRepository.save(ticketOrder);
                return "ticketOrder have been saved succussfully";
            }
        }
    }

    public List<TicketOrder> allTicketOrders() {
        List<TicketOrder> ticketOrders = ticketOrderRepository.findAll();

        return ticketOrders;
    }

    public String deleteTicketOrder(UUID id) {
        boolean tick = ticketOrderRepository.existsById(id);
        if (!tick) {
            return "the ticket order with that id is not exist";
        } else {

            TicketOrder ord = new TicketOrder();
            ord.setId(id);
            boolean checkIsAvail = issuedTicketRepository.existsByOrderTicket(ord);
            if(checkIsAvail) {
                return "That ticket order has issued tickets, delete them first";
            } else {
                ticketOrderRepository.deleteById(id);
                return "the ticket order deleted successfully";
            }
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

            return "the ticketOrder with this id " + id + " have been updated successfully";
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

    public String updateBasedOnStatus(UUID id, OrderStatus status) {
        Optional<TicketOrder> one = ticketOrderRepository.findById(id);
        if(one.isEmpty()) {
            return "the ticket order with that id does not exist";
        } else {
            TicketOrder two = one.get();
            if(two.getStatus().equals(OrderStatus.PENDING) && !(status.equals(OrderStatus.PROCESSING) || status.equals(OrderStatus.REFUNDED))) {
                return "Cannot change status from " + two.getStatus() + " to " + status;
            } else if(two.getStatus().equals(OrderStatus.PROCESSING) && !(status.equals(OrderStatus.PAID) || status.equals(OrderStatus.REFUNDED))){
                return "Cannot change status from " + two.getStatus() + " to " + status;
            } else if(two.getStatus().equals(OrderStatus.PAID) || two.getStatus().equals(OrderStatus.REFUNDED)){
                return "Cannot change status from " + two.getStatus() + " to " + status;
            } else {
                two.setStatus(status);
                ticketOrderRepository.save(two);
                return "Ticket order status updated successfully";
            }
        }
    }

    public List<Object[]> groupBy() {
        List<Object[]> theData = ticketOrderRepository.groundBy();

        return theData;
    }

    public String refundTicket(LocalDate date, UUID id) {
        int result = ticketOrderRepository.updatedRows(id, date);

        return result + " ticket orders cancelled";
    }

    public Page<TicketOrder> pagingTicket(int page, int size, String sort) {

        String[] theStrings = sort.split(","); 

        String one = theStrings[0];
        String order = theStrings[1];
        
        Direction direction = order.equals("desc") ? Direction.DESC : Direction.ASC;
        Pageable sendThis = PageRequest.of(page, size, Sort.by(direction, one));

        Page<TicketOrder> result = ticketOrderRepository.findAll(sendThis);
        
        return result;
    }
}
