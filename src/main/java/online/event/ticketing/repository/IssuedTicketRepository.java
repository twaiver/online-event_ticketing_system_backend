package online.event.ticketing.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import online.event.ticketing.domain.IssuedTicket;
import online.event.ticketing.domain.TicketOrder;

@Repository 
public interface IssuedTicketRepository extends JpaRepository<IssuedTicket, UUID> {
    
    @Query (value = "SELECT i FROM IssuedTicket i WHERE i.orderTicket.buyer.email = :email ORDER BY i.issuedDate DESC")
    List<IssuedTicket> findByEmail(@Param ("email") String email);

    boolean existsByOrderTicket(TicketOrder order);
}
