package pl.edu.pwr.tkubik.ism;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass=true)
@SpringBootApplication
public class WroclArtsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WroclArtsApplication.class, args);
    }

}
