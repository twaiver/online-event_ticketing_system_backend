package online.event.ticketing.domain;


import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name = "venue")

public class Venue {

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private int venueNumber;

    private String name;

    public UUID getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setVenueNumber(int venueNumber) {
        this.venueNumber = venueNumber;
    }

    public int getVenueNumber() {
        return venueNumber;
    }
}