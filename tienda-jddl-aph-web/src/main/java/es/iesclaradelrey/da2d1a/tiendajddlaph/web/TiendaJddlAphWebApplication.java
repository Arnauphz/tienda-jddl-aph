package es.iesclaradelrey.da2d1a.tiendajddlaph.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "es.iesclaradelrey.da2d1a.tiendajddlaph.common",
                "es.iesclaradelrey.da2d1a.tiendajddlaph.web"
        }
)
public class TiendaJddlAphWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaJddlAphWebApplication.class, args);
    }

}
