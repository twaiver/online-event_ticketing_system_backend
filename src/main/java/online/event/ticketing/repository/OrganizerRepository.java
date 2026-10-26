package online.event.ticketing.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import online.event.ticketing.domain.Organizer;

@Repository 
public interface OrganizerRepository extends JpaRepository<Organizer, UUID> {
    
}
