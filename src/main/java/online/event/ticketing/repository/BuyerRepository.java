package online.event.ticketing.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import online.event.ticketing.domain.Buyer;

@Repository 
public interface BuyerRepository extends JpaRepository<Buyer, UUID> {
    boolean existsByEmail(String email);
    List<Buyer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query (value = "SELECT DISTINCT t.buyer FROM TicketOrder t WHERE t.organizer.id = :id")
    List<Buyer> allBuyerByOrganizer(@Param ("id") UUID organizer);
}
