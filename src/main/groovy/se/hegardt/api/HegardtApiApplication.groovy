package se.hegardt.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class HegardtApiApplication {

    static void main(String[] args) {
        SpringApplication.run(HegardtApiApplication, args)
    }

}
