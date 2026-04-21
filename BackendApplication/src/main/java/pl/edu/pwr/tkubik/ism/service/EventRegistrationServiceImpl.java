package pl.edu.pwr.tkubik.ism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pwr.tkubik.ism.model.*;
import pl.edu.pwr.tkubik.ism.repository.EventRegistrationRepository;
import pl.edu.pwr.tkubik.ism.repository.EventRepository;
import pl.edu.pwr.tkubik.ism.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventRegistrationServiceImpl implements EventRegistrationService {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    // please note, that here we operate on DTO types (not Entities)
    // please note, that EventRegistrationDTO has no corresponding Entity

    @Override
    public EventRegistrationDTO addEventRegistration(EventRegistrationDTO erDTO) {
        // OPTION B : Vérification préalable de l'existence de l'inscription
        if (eventRegistrationRepository.existsByEventIdAndUserId(erDTO.getEventId(), erDTO.getUserId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "L'utilisateur est déjà inscrit à cet événement.");
        }

        Optional<UserEntity> ou = userRepository.findById(erDTO.getUserId());
        if (ou.isPresent()) {
            Optional<EventEntity> oe = eventRepository.findById(erDTO.getEventId());
            if (oe.isPresent()) {
                EventRegistration savedEntity = eventRegistrationRepository.save(
                        new EventRegistration(ou.get(), oe.get(), erDTO.getDateRegistration())
                );
                erDTO.setId(savedEntity.getId());
                return erDTO;
            }
        }
        return null;
    }

    @Override
    public List<EventRegistrationDTO> findAllEventRegistrations() {
        return eventRegistrationRepository.findAll().stream()
                .map(er -> new EventRegistrationDTO(
                        er.getId(),
                        er.getUser().getId(),
                        er.getEvent().getId(),
                        er.getDateRegistration()
                )).collect(Collectors.toList());
    }

    @Override
    public List<EventOccupancyDTO> getEventOccupancies() {
        // convert SQL response to EventOccupancyDTO
        return eventRegistrationRepository.getUsersEventCounts().stream()
                .map(t -> new EventOccupancyDTO(
                        (String) t.get(0), // title of event
                        ((Number) t.get(1)).longValue() // counter
                )).collect(Collectors.toList());
    }

    @Override
    public EventRegistration addRegistration(EventRegistration registration) {
        return eventRegistrationRepository.save(registration);
    }
}