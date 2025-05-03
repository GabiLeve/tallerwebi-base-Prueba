package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorLoginEj {

    //asocio una URL a un métodoo de Java
    // @RequestMapping le dice a Spring MVC, Cuando llegue una petición HTTP a tal URL (en este caso la URL terminada en /verVistaLogin), quiero que ejecutes este métodoo
    @RequestMapping("/verVistaLogin") //esto se escrib en el navegador
    public ModelAndView mostrarLogin(){

        return new ModelAndView("vistaLogin"); //se crea un objeto ModelAndView con el nombre "vistaLogin". Aca spring se encarga de armar la ruta a la vista. Configurado en SpringWebConfirg.java
        //"vistaLogin.html" hay que crear en WEB-INF/thymeleaf
    }

    // esto iria en ControladorArtista
    @RequestMapping("/verCanciones")
    public ModelAndView mostrarCanciones(){

        return new ModelAndView("canciones");
    }

}
