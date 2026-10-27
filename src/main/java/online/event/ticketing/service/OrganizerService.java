package online.event.ticketing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.event.ticketing.domain.EventCategory;
import online.event.ticketing.domain.Organizer;
import online.event.ticketing.repository.OrganizerRepository;

@Service 
public class OrganizerService {
    
    @Autowired 
    private OrganizerRepository organizerRepository;

    public String saveOrganizer(Organizer organizer) {
        boolean isAvaialble = organizerRepository.existsByEmail(organizer.getEmail());
        if(isAvaialble) {
            return "the organizer with that email " + organizer.getEmail() + " is already available";
        } else {
            organizerRepository.save(organizer);
            return "Organizer have been saved succuesfully";
        }
    }

    public List<Organizer> allOrganizers() {
        List<Organizer> organizers = organizerRepository.findAll();

        return organizers;
    }

    public String deleteOrganizer(UUID id) {
        boolean orga = organizerRepository.existsById(id);
        if (!orga) {
            return "the Organizer with that id is not exist";
        } else {
            organizerRepository.deleteById(id);
            return "the Organizer with id of " + id + " deleted successfully";
        }
    }


    public String updateOrganizer(UUID id, Organizer organizer) {
        Optional<Organizer> orga = organizerRepository.findById(id);

        if(orga.isEmpty()) {
            return "the Organizer with that id is not exist";
        } else {
            Organizer newOrgani = orga.get();
            List<EventCategory> listToSend = newOrgani.getEventCategories();

            for(EventCategory d : organizer.getEventCategories()) {
                listToSend.add(d);
            }

            newOrgani.setEmail(organizer.getEmail());
            newOrgani.setFirstName(organizer.getFirstName());
            newOrgani.setLastName(organizer.getLastName());
            newOrgani.setEventCategories(listToSend);
            newOrgani.setVenue(organizer.getVeneu());

            organizerRepository.save(newOrgani);

            return "the Organizer with this id " + id + " have been updated succeffly";
        }
    }
}
