package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorClcular {

    @GetMapping("/calculadora")
    public ModelAndView mostrarCalculadora(){
        return new ModelAndView ("calculadora");
    }

    @PostMapping("/resultadoOperacion")
    public ModelAndView resolverOperacion(
            @RequestParam("valor1") int valor1,
            @RequestParam("valor2") int valor2) {

        int resultado = valor1 + valor2;

        ModelMap modelo = new ModelMap();
        modelo.put("valor1", valor1);
        modelo.put("valor2", valor2);
        modelo.put("resultado", resultado);

        return new ModelAndView("resultadoOperacion", modelo);
    }
}
