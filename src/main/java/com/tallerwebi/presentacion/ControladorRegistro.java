package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioRegistro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {


    private ServicioRegistro servicioRegistro;

    public ControladorRegistro(ServicioRegistro servicioRegistro) {
        this.servicioRegistro = servicioRegistro;
    }

    public ModelAndView registrar(String email, String password) {

        if (email.isEmpty() || password.isEmpty()) {
            ModelMap modelo = new ModelMap();
            String mensaje = email.isEmpty()
                    ?"Debe ingresar un email para registrarse"
                    :"Debe ingresar un password para registrarse";
            modelo.put("mensajeError", mensaje);

            return new ModelAndView("registro", modelo);
        }
        servicioRegistro.registrar(email, password);

        return new ModelAndView("login");
    }
}
