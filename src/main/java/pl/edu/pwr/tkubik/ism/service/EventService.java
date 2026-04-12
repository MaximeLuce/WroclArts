package pl.edu.pwr.tkubik.ism.service;

import pl.edu.pwr.tkubik.ism.model.EventEntity;

import java.util.List;

public interface EventService {
    public EventEntity addEvent(EventEntity event);
    public EventEntity deleteEventById(long id);
    public List<EventEntity> findAllEvents();
    public EventEntity updateEvent(EventEntity event);
    public EventEntity findEventById(long id);

}