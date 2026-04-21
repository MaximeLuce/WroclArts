package pl.edu.pwr.tkubik.ism.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// https://www.baeldung.com/spring-cors
@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                // adding this line because of CORS error using PUT for a specific event
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}