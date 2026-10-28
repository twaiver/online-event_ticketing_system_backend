package online.event.ticketing.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import online.event.ticketing.domain.EventCategory;
import online.event.ticketing.domain.Organizer;
import online.event.ticketing.domain.Venue;

@Repository 
public interface OrganizerRepository extends JpaRepository<Organizer, UUID> {
    boolean existsByEmail(String email);

    @Query (value = "SELECT O FROM Organizer O JOIN O.eventCategories E WHERE LOWER(E.name) = LOWER(?1)")
    List<Organizer> allOrganizerByCategory(@Param ("name") String name);

    @Query (value = "SELECT e FROM EventCategory e WHERE e.id = :id")
    List<EventCategory> checkEventCategory(@Param ("id") UUID id);

    boolean existsByEventCategoriesAndId(EventCategory eventCategory, UUID id);

    @Query (value = "SELECT v FROM Venue v WHERE v.venueNumber = :venue")
    Venue venue(@Param ("venue") int venueNumber);

    boolean existsByVenue(Venue venue);
}
