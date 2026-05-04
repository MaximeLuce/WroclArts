package pl.edu.pwr.tkubik.ism.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pwr.tkubik.ism.model.EventEntity; // Attention, on utilise Entity maintenant !
import pl.edu.pwr.tkubik.ism.service.StatisticsManager;

import java.util.List;

@Aspect
@Component
public class AppStatisticsAspect {

    @Autowired
    private StatisticsManager statisticsManager;

    // we look for ServiceImpl
    @Pointcut("execution(public * pl.edu.pwr.tkubik.ism.service.*ServiceImpl.*(..))")
    public void allServiceMethods() {}

    @Before("allServiceMethods()")
    public void countServiceUsage(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        // we avoid StatisticsManager
        if (methodName.contains("Statistics") || methodName.equals("getHighestEventPrice") || methodName.equals("getMethodUsagePercentages")) {
            return;
        }

        System.out.println("DEBUG AOP -> Service intercepté : " + methodName);
        statisticsManager.recordMethodCall(methodName);
    }

    // we loook for findAllEvents of EventServiceImpl
    @Pointcut("execution(public * pl.edu.pwr.tkubik.ism.service.EventServiceImpl.findAllEvents*(..))")
    public void findAllEventsServiceMethod() {}

    // we get bakc the direct return List<EventEntity>
    @AfterReturning(pointcut = "findAllEventsServiceMethod()", returning = "result")
    public void analyzeEventsData(JoinPoint joinPoint, Object result) {


        if (result instanceof List) {
            List<?> events = (List<?>) result;

            for (Object obj : events) {
                if (obj instanceof EventEntity) {
                    EventEntity event = (EventEntity) obj;
                    if (event.getPrice() != null) {
                        statisticsManager.updateHighestPrice(event.getPrice());
                    }
                }
            }

        }
    }
}