package pl.edu.pwr.tkubik.ism.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import pl.edu.pwr.tkubik.ism.model.AdminStatusUpdate;
import pl.edu.pwr.tkubik.ism.model.Event;
import pl.edu.pwr.tkubik.ism.model.Organization;
import pl.edu.pwr.tkubik.ism.model.Product;

import java.util.Optional;

@RestController
public class AdminController implements AdminApi {

    @Autowired
    private NativeWebRequest nativeWebRequest = null;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(nativeWebRequest);
    }

    @Override
    public ResponseEntity<Organization> adminUpdateOrganizationStatus(Long orgId, AdminStatusUpdate adminStatusUpdate) {
        System.out.println("The admin has changed the status of Organization " + orgId + " to: " + adminStatusUpdate.getStatus());
        System.out.println("Reason given : " + adminStatusUpdate.getReason());

        Organization org = new Organization();
        org.setOrgId(orgId);
        org.setEmail("contact@organization.pl");
        org.setKrsNumber("0000000000");
        // We apply the new status suggested by the admin
        org.setStatus(adminStatusUpdate.getStatus());

        return new ResponseEntity<>(org, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Event> adminDeleteEvent(Long eventId) {
        System.out.println("The admin has removed event n° " + eventId + " because of ToS violation.");

        Event deletedEvent = new Event();
        deletedEvent.setEventId(eventId);
        deletedEvent.setTitle("Deleted Event");
        deletedEvent.setStatus("Cancelled by Admin");

        return new ResponseEntity<>(deletedEvent, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> adminDeleteProduct(Long productId) {
        System.out.println("The admin has deleted the product n°" + productId + " because of ToS violation.");

        Product deletedProduct = new Product();
        deletedProduct.setProductId(productId);
        deletedProduct.setName("Deleted Product");
        deletedProduct.setStatus("Removed");

        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }
}