package online.event.ticketing.domain;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity 
@Table (name = "event_category")
public class EventCategory {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private String name;


    @ManyToMany (mappedBy = "eventCategories")
    @JsonIgnore 
    List<Organizer> organizers;

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOrganizers(List<Organizer> organizers) {
        this.organizers = organizers;
    }

    public List<Organizer> getOrganizers() {
        return organizers;
    }
}
