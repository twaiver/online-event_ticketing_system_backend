package online.event.ticketing.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.event.ticketing.domain.OrderStatus;
import online.event.ticketing.domain.Venue;
import online.event.ticketing.repository.BuyerRepository;
import online.event.ticketing.repository.IssuedTicketRepository;
import online.event.ticketing.repository.OrganizerRepository;
import online.event.ticketing.repository.TicketOrderRepository;
import online.event.ticketing.repository.VenueRepository;

@Service 
public class DashboardService {
    
    @Autowired 
    private BuyerRepository buyerRepository;

    @Autowired 
    private OrganizerRepository organizerRepository;

    @Autowired 
    private IssuedTicketRepository issuedTicketRepository;

    @Autowired 
    private TicketOrderRepository ticketOrderRepository;

    @Autowired 
    private VenueRepository venueRepository;

    public Map<String, Long> dashboardSummary() {

        Long buyer = buyerRepository.count();
        Long organizer = organizerRepository.count();
        Long issuedTicket = issuedTicketRepository.count();
        Long pending = ticketOrderRepository.countByStatus(OrderStatus.PENDING);
        Long ordersToday = ticketOrderRepository.countByOrderDate(LocalDate.now());
        
        List<Venue> allFreeVenue = venueRepository.freeVenues();
        Long freeVenues = Long.valueOf(allFreeVenue.size());

        Map<String, Long> myCont = new LinkedHashMap<>();
        myCont.put("buyers", buyer);
        myCont.put("organizers", organizer);
        myCont.put("freeVenues", freeVenues);
        myCont.put("ordersToday", ordersToday);
        myCont.put("pending", pending);
        myCont.put("issuedTickets", issuedTicket);

        return myCont;
    }
}
