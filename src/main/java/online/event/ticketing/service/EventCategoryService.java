package online.event.ticketing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.event.ticketing.domain.EventCategory;
import online.event.ticketing.repository.EventCategoryRepository;

@Service 
public class EventCategoryService {
    
    @Autowired 
    private EventCategoryRepository eventCategoryRepository;

    public String saveEvent(EventCategory eventCategory) {
        eventCategoryRepository.save(eventCategory);
        return "EventCategory have been saved succuesfully";
    }

    public List<EventCategory> allEvents() {
        List<EventCategory> eventCategorys = eventCategoryRepository.findAll();

        return eventCategorys;
    }

    public String deleteEvent(UUID id) {
        boolean eve = eventCategoryRepository.existsById(id);
        if (!eve) {
            return "the EventCategory with that id is not exist";
        } else {
            eventCategoryRepository.deleteById(id);
            return "the EventCategory with id of " + id + " deleted successfully";
        }
    }


    public String updateEvent(UUID id, EventCategory eventCategory) {
        Optional<EventCategory> event = eventCategoryRepository.findById(id);

        if(event.isEmpty()) {
            return "the EventCategory with that id is not exist";
        } else {
            EventCategory myEvent = event.get();
            myEvent.setName(eventCategory.getName());
            eventCategoryRepository.save(myEvent);
            return "the EventCategory with this id " + id + " have been updated succeffly";
        }
    }
}
