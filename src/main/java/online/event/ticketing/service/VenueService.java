package online.event.ticketing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.event.ticketing.domain.Venue;
import online.event.ticketing.repository.VenueRepository;

@Service 
public class VenueService {
    
    @Autowired 
    private VenueRepository venueRepository;

    public String saveVenue(Venue venue) {
        venueRepository.save(venue);
        return "venue have been saved succuesfully";
    }

    public List<Venue> allVenues() {
        List<Venue> venues = venueRepository.findAll();

        return venues;
    }

    public String deleteVenue(UUID id) {
        boolean myVen = venueRepository.existsById(id);
        if (!myVen) {
            return "the Venue with that id is not exist";
        } else {
            venueRepository.deleteById(id);
            return "the venue with id of " + id + " deleted successfully";
        }
    }


    public String updateVenue(UUID id, Venue venue) {
        Optional<Venue> myVen = venueRepository.findById(id);

        if(myVen.isEmpty()) {
            return "the venue with that id is not exist";
        } else {
            Venue sendThis = myVen.get();
            sendThis.setName(venue.getName());
            sendThis.setVenueNumber(venue.getVenueNumber());


            venueRepository.save(sendThis);

            return "the venue with this id " + id + " have been updated succeffly";
        }
    }
}
