package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorLoginEj {

    //asocio una URL a un métodoo de Java
    // @RequestMapping le dice a Spring MVC, Cuando llegue una petición HTTP a tal URL (en este caso la URL terminada en /verVistaLogin), quiero que ejecutes este métodoo
    @RequestMapping("/verVistaLogin")
    public ModelAndView mostrarLogin(){

        return null;
    }
}
