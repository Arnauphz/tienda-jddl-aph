package es.iesclaradelrey.da2d1a.tiendajddlaph.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "es.iesclaradelrey.da2d1a.tiendajddlaph.common",
                "es.iesclaradelrey.da2d1a.tiendajddlaph.web"
        }
)
@EnableJpaRepositories(
        basePackages = "es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories"
)
@EntityScan(
        basePackages = "es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities"
)
public class TiendaJddlAphWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaJddlAphWebApplication.class, args);
    }

}
