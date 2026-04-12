package pl.edu.pwr.tkubik.ism.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "subscriptionDate")
    private LocalDate subscriptionDate;

    @Column(name = "status")
    private String status;

    //  many-to-many relationship was modelled with the use of intermediate table "event_registrations" and "event_creations"
    //  for which a separate entity class EventRegistration and EventCreation was declared
    @OneToMany(mappedBy = "user")
    private Set<EventRegistration> registrations = new HashSet<>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Set<EventRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<EventRegistration> registrations) {
        this.registrations = registrations;
    }

    // constructors

    public UserEntity() {
    }

    public UserEntity(String email, String userName, String userPassword, LocalDate subscriptionDate, String status) {
        this.email = email;
        this.userName = userName;
        this.password = userPassword;
        this.subscriptionDate = subscriptionDate;
        this.status = status;
    }
}