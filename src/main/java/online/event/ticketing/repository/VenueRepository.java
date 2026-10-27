package online.event.ticketing.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import online.event.ticketing.domain.Venue;

@Repository 
public interface VenueRepository extends JpaRepository<Venue, UUID> {
    
    @Query (value = "SELECT V FROM Venue V WHERE NOT EXISTS (SELECT O FROM Organizer O WHERE O.venue = V)")
    List<Venue> freeVenues();
}
