package pl.edu.pwr.tkubik.ism.model;

import java.time.LocalDate;

public class EventCreationDTO {

    private Long id;
    private Long eventId;
    private Long organizationId;
    private LocalDate dateCreation;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    // constructors

    public EventCreationDTO() {
    }

    public EventCreationDTO(Long id, Long eventId, Long organizationId, LocalDate dateCreation) {
        this.id = id;
        this.eventId = eventId;
        this.organizationId = organizationId;
        this.dateCreation = dateCreation;
    }
}