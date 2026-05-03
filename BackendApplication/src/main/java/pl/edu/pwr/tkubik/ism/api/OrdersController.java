package pl.edu.pwr.tkubik.ism.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import pl.edu.pwr.tkubik.ism.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrdersController implements OrderApi {

    @Autowired
    private NativeWebRequest nativeWebRequest = null;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(nativeWebRequest);
    }


    @Override
    public ResponseEntity<List<Order>> findAllOrders() {
        List<Order> orders = new ArrayList<>();

        Order o1 = new Order();
        o1.setOrderId(1L);
        o1.setStatus("Shipped");
        o1.setDeliveryMethod("Postal");
        o1.setTrackingId("PL987654321");

        orders.add(o1);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Order> findOrderById(Long orderId) {
        Order o = new Order();
        o.setOrderId(orderId);
        o.setStatus("Processing");
        o.setDeliveryMethod("In-Store Pickup");

        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Order> updateOrder(Long orderId, Order order) {
        // we fake the update
        order.setOrderId(orderId);
        System.out.println("Order n°" + orderId + " updated to : " + order.getStatus());

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}