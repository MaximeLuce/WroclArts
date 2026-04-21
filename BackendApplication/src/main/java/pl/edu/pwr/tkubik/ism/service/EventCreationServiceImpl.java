package pl.edu.pwr.tkubik.ism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pwr.tkubik.ism.model.*;
import pl.edu.pwr.tkubik.ism.repository.EventCreationRepository;
import pl.edu.pwr.tkubik.ism.repository.EventRepository;
import pl.edu.pwr.tkubik.ism.repository.OrganizationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventCreationServiceImpl implements EventCreationService {

    @Autowired
    private EventCreationRepository eventCreationRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    // please note, that here we operate on DTO types (not Entities)
    // please note, that EventCreationDTO has no corresponding Entity

    @Override
    public EventCreationDTO addEventCreation(EventCreationDTO ecDTO) {
        Optional<EventEntity> oe = eventRepository.findById(ecDTO.getEventId());
        if(oe.isPresent()) {
            Optional<OrganizationEntity> oo = organizationRepository.findById(ecDTO.getOrganizationId());
            if (oo.isPresent()) {
                EventCreation savedEntity = eventCreationRepository.save(new EventCreation(oe.get(), oo.get(), ecDTO.getDateCreation()));
                ecDTO.setId(savedEntity.getId());
                return ecDTO;
            }
        }
        return null;
    }

    @Override
    public EventCreation addCreation(EventCreation creation) {
        // add data in the table "event_creations"
        return eventCreationRepository.save(creation);
    }

    @Override
    public List<EventCreationDTO> findAllEventCreations() {
        // we get all Entity and mapping into DTO
        return eventCreationRepository.findAll().stream()
                .map(ec -> new EventCreationDTO(
                        ec.getId(),
                        ec.getEvent().getId(),
                        ec.getOrganization().getId(),
                        ec.getDateCreation()
                )).collect(Collectors.toList());
    }

    @Override
    public List<OrganizationStatsDTO> getOrganizationEventCounts() {
        // get the SQL response and mapping into DTO
        return eventCreationRepository.getOrganizationEventCounts().stream().
                map(t -> new OrganizationStatsDTO(
                        (String) t.get(0), // orgName
                        ((Number) t.get(1)).longValue() // counter
                )).collect(Collectors.toList());
    }
}