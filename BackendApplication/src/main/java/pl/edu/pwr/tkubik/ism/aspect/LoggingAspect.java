package pl.edu.pwr.tkubik.ism.aspect;

import jakarta.annotation.PostConstruct;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {


  @Before("@annotation(pl.edu.pwr.tkubik.ism.aspect.LogMethod)")
  public void logMethodExecution(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String params = Arrays.toString(joinPoint.getArgs());
    System.out.println("Method [" + method + "] gets called with parameters " + params);
  }

  @Around("@annotation(pl.edu.pwr.tkubik.ism.aspect.LogExecutionTime)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object proceed = joinPoint.proceed();
    long duration = System.currentTimeMillis() - startTime;
    System.out.println("Execution took [" + duration + "ms]");
    return proceed;
  }

    @PostConstruct
    public void init() {
        System.out.println("LoggingAspect loaded!");
    }
}
