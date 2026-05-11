package es.iesclaradelrey.da2d1a.tiendajddlaph.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "es.iesclaradelrey.da2d1a.tiendajddlaph.common",
                "es.iesclaradelrey.da2d1a.tiendajddlaph.security",
                "es.iesclaradelrey.da2d1a.tiendajddlaph.api"
        }
)
@EnableJpaRepositories(
        basePackages = "es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories"
)
@EntityScan(
        basePackages = "es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities"
)
public class TiendaJddlAphApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaJddlAphApiApplication.class, args);
    }
}