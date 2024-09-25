package unipar.integrador.mywallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
@Configuration
public class MyWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWalletApplication.class, args);
    }

}
