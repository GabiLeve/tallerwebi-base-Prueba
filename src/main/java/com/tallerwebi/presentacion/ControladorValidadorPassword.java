package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorValidadorPassword {

    public ModelAndView validarFortaleza(String password) {
        long cantidadNumeros = password.chars().filter(Character::isDigit).count();
        long cantidadCaracterEspecial = password.chars().filter(c -> !Character.isLetterOrDigit(c)).count();

        if(password.length() >= 8 && cantidadNumeros >= 4 && cantidadCaracterEspecial >= 1) {
            ModelMap modelo = new ModelMap();
            String mensaje = "Su password es FUERTE";
            modelo.put("mesajeValidacion", mensaje);
            return new ModelAndView("vista-fortaleza", modelo);
        }
        return new ModelAndView("vista-fortaleza");
    }
}
