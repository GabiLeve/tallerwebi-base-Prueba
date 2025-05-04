package com.tallerwebi.presentacion;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@Controller
public class ControladorEjer11Test {

    /*- FUERTE:
  - Al menos 8 caracteres.
  - Al menos 4 números.
  - Al menos 1 carácter especial (no letra ni número)*/
    @Test
    public void queLaPasswordSeaFuerte(){
        //give
        //when
        ModelAndView mav = whenValidoPassword("1234@abc");
        //then
        thenElPasswordCumpleRelglas(mav, "Su password es FUERTE");
    }

    private ModelAndView whenValidoPassword(String password) {
        ControladorValidadorPassword validador = new ControladorValidadorPassword();
        ModelAndView mav = validador.validarFortaleza(password);
        return mav;
    }
    private void thenElPasswordCumpleRelglas(ModelAndView mav, String resultadoValidacionEsperado) {
        assertThat(mav.getViewName().toString(), equalToIgnoringCase("vista-fortaleza"));
        assertThat(mav.getModel().get("mesajeValidacion").toString(), equalToIgnoringCase(resultadoValidacionEsperado));
    }

}
