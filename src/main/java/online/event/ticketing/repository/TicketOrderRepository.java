package online.event.ticketing.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import online.event.ticketing.domain.OrderStatus;
import online.event.ticketing.domain.Organizer;
import online.event.ticketing.domain.TicketOrder;

@Repository 
public interface TicketOrderRepository extends JpaRepository<TicketOrder, UUID> {

    List<TicketOrder> findByStatusOrderByOrderDateDesc(OrderStatus status);
    List<TicketOrder> findByOrderDateBetweenOrderByOrderDate(LocalDate start, LocalDate end);

    boolean existsByOrganizerAndOrderDateAndStatusNot(Organizer organizer, LocalDate orderDate, OrderStatus status);
}
