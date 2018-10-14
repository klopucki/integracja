package pl.umcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UMCS - Integration Classes
 */
@RestController
@SpringBootApplication
public class App {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello!";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
