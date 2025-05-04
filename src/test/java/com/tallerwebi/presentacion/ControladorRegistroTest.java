package com.tallerwebi.presentacion;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;


@Controller
public class ControladorRegistroTest {

    /* Para registrar a un usuario necesito mail y pass
     si no exite mail el registro deberia fallar
     si no existe contraseña el registro deberia fallar
     */

    private String email = "leve@gmail.com";
    private String password = "123";

    //en este test definimos que la manera de contrastar que el registro fue exitoso es si devuelve una vista llamada 'login', porque nos planteamos que es lo que el usuario deberia ver que sucede luego de que se registrara correctamente. Y generalmente, una vez completado el registro, el nuevo usuario es redireccionado a la vosta del 'login' para que ingrese con las credenciales recien creadas
    @Test
    public void conEmailYPasswordElRegistroEsExitoso(){
        // preparación --> given() 'dado'
            givenNoExisteUsuario();
        // ejecución --> when() 'cuando'
            ModelAndView mav = whenRegistroUsuario(email, password);
        // comprovación/validación --> then() 'entonces'
            thenElRegistroEsExitoso(mav);
    }
    @Test
    public void siNoHayEmailElRegistroFalla(){
        //given
        givenNoExisteUsuario();
        //when
        ModelAndView mav= whenRegistroUsuario("", password);
        //then
        thenElRegistroFalla(mav, "Debe ingresar un email para registrarse");
    }
    @Test
    public void siNoHayPasswordElRegistroFalla(){
        //given
        givenNoExisteUsuario();
        //when
        ModelAndView mav= whenRegistroUsuario(email, "");
        //then
        thenElRegistroFalla(mav, "Debe ingresar un password para registrarse");
    }

    private void givenNoExisteUsuario() {
    }


    //Quiero probar una clase controladora, por lo que para testearla voy a necesitar un objeto controlador que pertenezca a esa clase. ¿quien va a registrar el usuario? --> un controlador
    private ModelAndView whenRegistroUsuario(String email, String password) {
        ControladorRegistro controladorRegistro = new ControladorRegistro();
        //supongo que mi objeto tendria que tener un metodo que me permita registrar
        //que dato puede necesitar este metodo registrar()
        //Todoo metodo de un controlador, por ser un action, devuelve un ModelAndView
        ModelAndView mav= controladorRegistro.registrar(email, password);
        return mav;
    }

    private void thenElRegistroEsExitoso(ModelAndView mav) {
        assertThat(mav.getViewName(), equalToIgnoringCase("login"));
    }

    private void thenElRegistroFalla(ModelAndView mav, String mensaje) {
        assertThat(mav.getViewName(), equalToIgnoringCase("registro"));
        //si hay un mensaje de error lo voy a tener dentro de getModel() dentro del ModelAndView, compusto por una clave y un valor
        assertThat(mav.getModel().get("mensajeError").toString(), equalToIgnoringCase(mensaje));
    }


}
