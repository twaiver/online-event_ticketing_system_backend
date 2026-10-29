package online.event.ticketing.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import online.event.ticketing.domain.OrderStatus;
import online.event.ticketing.domain.Organizer;
import online.event.ticketing.domain.TicketOrder;

@Repository 
public interface TicketOrderRepository extends JpaRepository<TicketOrder, UUID> {

    List<TicketOrder> findByStatusOrderByOrderDateDesc(OrderStatus status);
    List<TicketOrder> findByOrderDateBetweenOrderByOrderDate(LocalDate start, LocalDate end);

    boolean existsByOrganizerAndOrderDateAndStatusNot(Organizer organizer, LocalDate orderDate, OrderStatus status);

    @Query (value = "SELECT t.status, COUNT (t) AS cou FROM TicketOrder t GROUP BY t.status")
    List<Object[]> groundBy();

    @Transactional 
    @Modifying 
    @Query (value = "UPDATE TicketOrder t SET t.status = 'REFUNDED' WHERE NOT t.status = 'PAID' AND t.orderDate = :date AND t.organizer.id = :id")
    int updatedRows(@Param ("id") UUID id,@Param ("date") LocalDate date);
}
