package pl.edu.pwr.tkubik.ism.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import pl.edu.pwr.tkubik.ism.model.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EventsController implements EventApi {

    @Autowired
    private NativeWebRequest nativeWebRequest = null;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(nativeWebRequest);
    }

    // adding parameters inside the signature
    @Override
    public ResponseEntity<List<Event>> findAllEvents(Double lat, Double lng, Integer radius, String category, String keyword, LocalDate date) {
        if (getRequest().isPresent()) {
            List<Event> eventsList = new ArrayList<>();

            Event e1 = new Event();
            e1.setEventId(1L);
            e1.setTitle("Pottery Workshop");
            e1.setDate("2026-10-14");
            e1.setAddress("Nadodrze");
            e1.setCategory("Crafts");
            e1.setStatus("Published");

            eventsList.add(e1);

            return new ResponseEntity<>(eventsList, HttpStatus.OK);
        }

        return EventApi.super.findAllEvents(lat, lng, radius, category, keyword, date);
    }
}