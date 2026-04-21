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

    @Override
    public EventEntity addEvent(EventEntity event) {
        return eventRepository.save(event);
    }

    @Override
    public EventEntity deleteEventById(long id) {
        Optional<EventEntity> e = eventRepository.findById(id);
        if (e.isPresent()) {
            eventRepository.deleteById(id);
            return e.get();
        }
        return null;
    }

    @Override
    public List<EventEntity> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public EventEntity updateEvent(EventEntity event) {
        return eventRepository.save(event);
    }

    @Override
    public EventEntity findEventById(long id) {
        Optional<EventEntity> event = eventRepository.findById(id);
        return event.orElse(null);
    }
}