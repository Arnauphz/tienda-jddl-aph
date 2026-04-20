package es.iesclaradelrey.da2d1a.tiendajddlaph.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración personalizada de Spring MVC para el módulo web.
 */
@Configuration
public class ConfiguracionWeb implements WebMvcConfigurer {

    /**
     * Registra controladores de vista automáticos para rutas sin lógica de negocio.
     * Mapea la URL "/condiciones" a la plantilla "condiciones".
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/condiciones")
                .setViewName("condiciones");
    }
}