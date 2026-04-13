package es.iesclaradelrey.da2d1a.tiendajddlaph.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
//la funcion de esta clase es atender peticiones de inicio, el objetivo es devolver una vista
public class InicioController {
    @GetMapping("/")
    public String mostrarVista(){
        return "index";
    }

}
