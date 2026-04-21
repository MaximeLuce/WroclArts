package pl.edu.pwr.tkubik.ism.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organizations")
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "nameOrga")
    private String nameOrga;

    @Column(name = "krsNumber")
    private String krsNumber;

    @Column(name = "subscriptionDate")
    private LocalDate subscriptionDate;

    @Column(name = "status")
    private String status;

    //  many-to-many relationship was modelled with the use of intermediate table "event_creations"
    //  for which a separate entity class EventCreation was declared
    //@OneToMany(mappedBy = "organization")
    //private Set<EventCreation> creations = new HashSet<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventCreation> creations = new HashSet<>();



    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameOrga() {
        return nameOrga;
    }

    public void setNameOrga(String nameOrga) {
        this.nameOrga = nameOrga;
    }

    public String getKrsNumber() {
        return krsNumber;
    }

    public void setKrsNumber(String krsNumber) {
        this.krsNumber = krsNumber;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<EventCreation> getCreations() {
        return creations;
    }

    public void setCreations(Set<EventCreation> creations) {
        this.creations = creations;
    }

    // constructors

    public OrganizationEntity() {
    }

    public OrganizationEntity(String email, String nameOrga, String krsNumber, LocalDate subscriptionDate, String status) {
        this.email = email;
        this.nameOrga = nameOrga;
        this.krsNumber = krsNumber;
        this.subscriptionDate = subscriptionDate;
        this.status = status;
    }
}