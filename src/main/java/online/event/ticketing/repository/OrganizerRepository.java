package online.event.ticketing.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import online.event.ticketing.domain.Organizer;

@Repository 
public interface OrganizerRepository extends JpaRepository<Organizer, UUID> {
    boolean existsByEmail(String email);

    @Query (value = "SELECT O FROM Organizer O JOIN O.eventCategories E WHERE LOWER(E.name) = LOWER(?1)")
    List<Organizer> allOrganizerByCategory(@Param ("name") String name);
}
