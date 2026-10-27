package online.event.ticketing.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import online.event.ticketing.domain.EventCategory;

@Repository 
public interface EventCategoryRepository extends JpaRepository<EventCategory, UUID> {
    
    @Query (value = "SELECT e FROM EventCategory e WHERE e.organizers IS EMPTY")
    List<EventCategory> unusedCategories();
}
