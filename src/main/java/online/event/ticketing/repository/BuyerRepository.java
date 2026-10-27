package online.event.ticketing.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import online.event.ticketing.domain.Buyer;

@Repository 
public interface BuyerRepository extends JpaRepository<Buyer, UUID> {
    boolean existsByEmail(String email);
    List<Buyer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}
