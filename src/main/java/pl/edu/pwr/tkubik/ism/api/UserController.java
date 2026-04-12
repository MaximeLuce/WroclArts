package pl.edu.pwr.tkubik.ism.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.tkubik.ism.api.UserApi;
import pl.edu.pwr.tkubik.ism.model.LoginRequest;
import pl.edu.pwr.tkubik.ism.model.Review;
import pl.edu.pwr.tkubik.ism.model.SupportTicket;
import pl.edu.pwr.tkubik.ism.model.UserRegistration;
import pl.edu.pwr.tkubik.ism.model.UserEntity;
import pl.edu.pwr.tkubik.ism.service.UserService;

import java.time.LocalDate;

@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    // POST /auth/login
    @Override
    public ResponseEntity<LoginRequest> loginUser(LoginRequest loginRequest) {
        // NOT IMPLEMENTED, we suppose by default that the user is connected
        return new ResponseEntity<>(loginRequest, HttpStatus.OK);
    }

    // POST /users/register
    @Override
    public ResponseEntity<UserRegistration> registerUser(UserRegistration userRegistration) {
        // instance Entity of the DB
        UserEntity newEntity = new UserEntity();

        // mapping DTO to Entity)
        newEntity.setEmail(userRegistration.getEmail());
        newEntity.setUserName(userRegistration.getUsername());
        // we assume that password is not hash
        newEntity.setPassword(userRegistration.getPassword());
        newEntity.setSubscriptionDate(LocalDate.now());
        newEntity.setStatus("ACTIVE");

        // use service to save the new user in DB
        UserEntity savedEntity = userService.addUser(newEntity);

        // return DTO with a 200 OK status
        return new ResponseEntity<>(userRegistration, HttpStatus.OK);
    }

    // POST /reviews
    @Override
    public ResponseEntity<Review> postReview(Review review) {
        // NOT IMPLEMENTED, return 200 OK by default
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    // POST /support/tickets
    @Override
    public ResponseEntity<SupportTicket> submitSupportTicket(SupportTicket supportTicket) {
        // NOT IMPLEMENTED, return 200 OK by default
        return new ResponseEntity<>(supportTicket, HttpStatus.OK);
    }
}