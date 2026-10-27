package online.event.ticketing.domain;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table (name = "organizer")
public class Organizer {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToMany 
    @JoinTable (name = "organizer_event_category", joinColumns = @JoinColumn (name = "organizer_id"), 
    inverseJoinColumns = @JoinColumn (name = "event_category_id"))
    private List<EventCategory> eventCategories;

    @ManyToOne 
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @OneToMany (mappedBy = "organizer")
    @JsonIgnore 
    private List<TicketOrder> ticketOrders;

    public UUID getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEventCategories(List<EventCategory> eventCategories) {
        this.eventCategories = eventCategories;
    }

    public List<EventCategory> getEventCategories() {
        return eventCategories;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Venue getVeneu() {
        return venue;
    }

    public void setTicketOrders(List<TicketOrder> ticketOrders) {
        this.ticketOrders = ticketOrders;
    }

    public List<TicketOrder> getTicketOrders() {
        return ticketOrders;
    }


}
