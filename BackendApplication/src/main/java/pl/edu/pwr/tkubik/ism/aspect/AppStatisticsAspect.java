package pl.edu.pwr.tkubik.ism.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.edu.pwr.tkubik.ism.model.Event;
import pl.edu.pwr.tkubik.ism.service.StatisticsManager;

import java.util.List;

@Aspect
@Component
public class AppStatisticsAspect {

    @Autowired
    private StatisticsManager statisticsManager;

    // get all methods in 'api' excluding stats
    @Pointcut("execution(public * pl.edu.pwr.tkubik.ism.api.*.*(..)) && !execution(public * pl.edu.pwr.tkubik.ism.api.StatisticsController.*(..))")
    public void allApiMethods() {}

    // before, we ++ counter
    @Before("allApiMethods()")
    public void countApiUsage(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        statisticsManager.recordMethodCall(methodName);
    }

    //get findAllEvents
    //@Pointcut("execution(public * pl.edu.pwr.tkubik.ism.api.EventsController.findAllEvents(..))")
    //public void findAllEventsMethod() {}
    @Pointcut("execution(public * pl.edu.pwr.tkubik.ism.api.*Controller.*findAllEvents*(..))")
    public void findAllEventsMethod() {}

    // after, we analyse the result
    @AfterReturning(pointcut = "findAllEventsMethod()", returning = "responseEntity")
    public void analyzeEventsData(JoinPoint joinPoint, ResponseEntity<List<Event>> responseEntity) {
        if (responseEntity != null && responseEntity.getBody() != null) {
            List<Event> events = responseEntity.getBody();

            // we search through events to get the maximum price
            for (Event event : events) {
                if (event.getPrice() != null) {
                    statisticsManager.updateHighestPrice(event.getPrice());
                }
            }
            System.out.println("Aspect found ! Analyze of " + events.size() + " events.");
        }
    }
}