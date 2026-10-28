package online.event.ticketing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.event.ticketing.domain.EventCategory;
import online.event.ticketing.domain.Organizer;
import online.event.ticketing.domain.Venue;
import online.event.ticketing.repository.OrganizerRepository;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    public String saveOrganizer(Organizer organizer) {
        boolean isAvaialble = organizerRepository.existsByEmail(organizer.getEmail());
        if (isAvaialble) {
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

        if (orga.isEmpty()) {
            return "the Organizer with that id is not exist";
        } else {
            Organizer newOrgani = orga.get();
            List<EventCategory> listToSend = newOrgani.getEventCategories();

            for (EventCategory d : organizer.getEventCategories()) {
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

    public List<Organizer> allByCategories(String name) {
        List<Organizer> result = organizerRepository.allOrganizerByCategory(name);

        return result;
    }

    public String addEvent(UUID organizer, UUID category) {
        Optional<Organizer> check = organizerRepository.findById(organizer);

        if (check.isEmpty()) {
            return "The organizer with that id does not exist";
        } else {
            List<EventCategory> eve = organizerRepository.checkEventCategory(category);
            if (eve.isEmpty()) {
                return "That event category does not exist";
            } else {
                EventCategory cat = new EventCategory();
                cat.setId(category);
                boolean isAlready = organizerRepository.existsByEventCategoriesAndId(cat, organizer);
                if (isAlready) {
                    return "The organizer already has that event category";
                } else {
                    List<EventCategory> sendThis = check.get().getEventCategories();
                    sendThis.add(cat);
                    check.get().setEventCategories(sendThis);
                    
                    organizerRepository.save(check.get());
                    return "Event category added to the organizer";
                }
            }
        }
    }

    public String changeVenue(UUID organizer, int venueNumber) {
        Optional<Organizer> org = organizerRepository.findById(organizer);

        if(org.isEmpty()) {
            return "The organizer with that id does not exist";
        } else {
            Venue venue = organizerRepository.venue(venueNumber);

            if(venue == null) {
                return "The venue with that venueNumber does not exist";
            } else {
                boolean checkIsTaken = organizerRepository.existsByVenue(venue);

                if(checkIsTaken) {
                    return "That venue with is taken by another organizer";
                } else {
                    org.get().setVenue(venue);
                    organizerRepository.save(org.get());
                    return "Venue changed successfully";
                }
            }
        }
    }
}
