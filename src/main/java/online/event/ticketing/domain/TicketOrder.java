package online.event.ticketing.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "ticket_order")
public class TicketOrder {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate orderDate;

    @Enumerated (EnumType.STRING)
    private OrderStatus status;
    private String eventTitle;
    private int quantity;

    @ManyToOne 
    @JoinColumn (name = "buyer_id")
    private Buyer buyer;
    
    @ManyToOne 
    @JoinColumn (name = "organizer_id")
    private Organizer organizer;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setEventTitle(String title) {
        this.eventTitle = title;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Organizer getOrganizer() {
        return organizer;
    }



}
