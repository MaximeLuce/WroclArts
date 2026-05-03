package pl.edu.pwr.tkubik.ism.model;

import java.time.LocalDate;

public class EventRegistrationDTO {

    private Long id;
    private Long userId;
    private Long eventId;
    private LocalDate dateRegistration;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public LocalDate getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(LocalDate dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    // constructors

    public EventRegistrationDTO() {
    }

    public EventRegistrationDTO(Long id, Long userId, Long eventId, LocalDate dateRegistration) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.dateRegistration = dateRegistration;
    }
}