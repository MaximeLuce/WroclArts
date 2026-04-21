package pl.edu.pwr.tkubik.ism.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "event_registrations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"event_id", "user_id"}) // Vérifie bien la présence des { } ici !
})
public class EventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @Column(name = "date_registration")
    private LocalDate dateRegistration;

    // getters and setters

    public Long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public LocalDate getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(LocalDate dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    // constructors

    public EventRegistration() {
    }

    public EventRegistration(UserEntity user, EventEntity event, LocalDate dateRegistration) {
        this.user = user;
        this.event = event;
        this.dateRegistration = dateRegistration;
    }
}