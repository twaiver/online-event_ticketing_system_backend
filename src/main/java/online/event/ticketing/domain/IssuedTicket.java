package online.event.ticketing.domain;

import java.time.LocalDate;
import java.util.UUID;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "issued_ticket")
public class IssuedTicket {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private String tierName;
    private String seatNote;
    private int serialNumber;
    private LocalDate issuedDate;

    @ManyToOne 
    @JoinColumn (name = "ticket_order_id")
    private TicketOrder orderTicket;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public String getTierName() {
        return tierName;
    }

    public void setSeatNote(String seatNote) {
        this.seatNote = seatNote;
    }

    public String getSeatNote() {
        return seatNote;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setOrderTicket(TicketOrder orderTicket) {
        this.orderTicket = orderTicket;
    }

    public TicketOrder getOrderTicket() {
        return orderTicket;
    }


}
