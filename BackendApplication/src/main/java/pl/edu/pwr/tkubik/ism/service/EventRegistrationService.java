package pl.edu.pwr.tkubik.ism.service;

import org.springframework.lang.Nullable;
import pl.edu.pwr.tkubik.ism.model.EventRegistrationDTO;
import pl.edu.pwr.tkubik.ism.model.EventOccupancyDTO; // Nom supposé pour ton DTO de statistiques
import pl.edu.pwr.tkubik.ism.model.EventRegistration;

import java.util.List;

public interface EventRegistrationService {

    @Nullable
    public EventRegistrationDTO addEventRegistration(EventRegistrationDTO erDTO);

    public List<EventRegistrationDTO> findAllEventRegistrations();

    public List<EventOccupancyDTO> getEventOccupancies();

    public EventRegistration addRegistration(EventRegistration registration);

    public void removeRegistration(EventRegistration registration);
}