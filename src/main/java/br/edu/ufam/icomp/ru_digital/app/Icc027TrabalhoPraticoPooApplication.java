package br.edu.ufam.icomp.ru_digital.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.edu.ufam.icomp.ru_digital")
@EnableJpaRepositories(basePackages = "br.edu.ufam.icomp.ru_digital.features")
@EntityScan(basePackages = "br.edu.ufam.icomp.ru_digital.entities")
public class Icc027TrabalhoPraticoPooApplication {

    public static void main(String[] args) {
        SpringApplication.run(Icc027TrabalhoPraticoPooApplication.class, args);
    }

}
