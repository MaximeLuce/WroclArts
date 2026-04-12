package pl.edu.pwr.tkubik.ism.service;

import org.springframework.lang.Nullable;
import pl.edu.pwr.tkubik.ism.model.EventCreationDTO;
import pl.edu.pwr.tkubik.ism.model.OrganizationStatsDTO; // Nom supposé pour ton DTO d'agrégation
import pl.edu.pwr.tkubik.ism.model.EventCreation;

import java.util.List;

public interface EventCreationService {

    @Nullable
    public EventCreationDTO addEventCreation(EventCreationDTO ecDTO);

    public List<EventCreationDTO> findAllEventCreations();

    public List<OrganizationStatsDTO> getOrganizationEventCounts();

    public EventCreation addCreation(EventCreation creation);
}