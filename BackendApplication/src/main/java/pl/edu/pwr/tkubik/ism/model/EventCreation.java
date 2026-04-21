package pl.edu.pwr.tkubik.ism.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "event_creations")
public class EventCreation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    // getters and setters

    public Long getId() {
        return id;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    // constructors

    public EventCreation() {
    }

    public EventCreation(EventEntity event, OrganizationEntity organization, LocalDate dateCreation) {
        this.event = event;
        this.organization = organization;
        this.dateCreation = dateCreation;
    }
}