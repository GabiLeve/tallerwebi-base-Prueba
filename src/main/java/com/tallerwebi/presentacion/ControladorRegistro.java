package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.PasswordLongitudIncorrectaException;
import com.tallerwebi.dominio.ServicioRegistro;
import com.tallerwebi.dominio.Usuario;
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
        try{
            servicioRegistro.registrar(email, password);
        } catch (PasswordLongitudIncorrectaException e) {
            ModelMap modelo = new ModelMap();
            modelo.put("mensajeError", "La password debe tener al menos cinco caracteres");
            return new ModelAndView("registro", modelo);
        }
        catch (Exception e) {
            ModelMap modelo = new ModelMap();
            modelo.put("mensajeError", "Ha ocurrido un error al registrar");
            return new ModelAndView("registro", modelo);
        }

        return new ModelAndView("login");
    }
}
