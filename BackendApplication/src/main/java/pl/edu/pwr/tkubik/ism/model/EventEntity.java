package pl.edu.pwr.tkubik.ism.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "address")
    private String address;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Double price;

    @Column(name = "numberOfPlaces")
    private Integer numberOfPlaces;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    //  many-to-many relationship was modelled with the use of intermediate table "event_registrations" and "event_creations"
    //  for which a separate entity class EventRegistration and EventCreation was declared
    //@OneToMany(mappedBy = "event")
    //private Set<EventRegistration> registrations = new HashSet<>();

    //@OneToMany(mappedBy = "event")
    //private Set<EventCreation> creators = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventRegistration> registrations = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventCreation> creators = new HashSet<>();

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(Integer numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<EventRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<EventRegistration> registrations) {
        this.registrations = registrations;
    }

    public Set<EventCreation> getCreators() {
        return creators;
    }

    public void setCreators(Set<EventCreation> creators) {
        this.creators = creators;
    }

    // constructors

    public EventEntity() {
    }

    public EventEntity(String title, LocalDate date, LocalTime time, String address, String category, Double price, Integer numberOfPlaces, String type, String status) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.address = address;
        this.category = category;
        this.price = price;
        this.numberOfPlaces = numberOfPlaces;
        this.type = type;
        this.status = status;
    }
}