package pl.edu.pwr.tkubik.ism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pwr.tkubik.ism.model.EventEntity;
import pl.edu.pwr.tkubik.ism.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    // please note, that here we operate on Entity type (not DTO)
    @Override
    public EventEntity addEvent(EventEntity event) {
        return eventRepository.save(event);
    }

    @Override
    public EventEntity deleteEventById(long id) {
        Optional<EventEntity> e = eventRepository.findById(id);
        // e.ifPresent(EventEntity -> eventRepository.deleteBtId(id));
        // What if there is no event ?
        if (e.isPresent()) {
            eventRepository.deleteById(id);
            return e.get();
        }
        // return null if there is no event
        return null;
    }

    @Override
    public List<EventEntity> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public EventEntity updateEvent(EventEntity event) {
        // save() run update if existed, insert if not
        return eventRepository.save(event);
    }

    @Override
    public EventEntity findEventById(long id) {
        // we return null if there is no event
        Optional<EventEntity> event = eventRepository.findById(id);
        return event.orElse(null);
    }
}