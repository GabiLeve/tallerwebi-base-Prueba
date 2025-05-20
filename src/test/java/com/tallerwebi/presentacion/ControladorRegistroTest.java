package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.PasswordLongitudIncorrectaException;
import com.tallerwebi.dominio.ServicioRegistro;
import com.tallerwebi.dominio.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.*;


@Controller
public class ControladorRegistroTest {

    /* Para registrar a un usuario necesito mail y pass
     si no exite mail el registro deberia fallar
     si no existe contraseña el registro deberia fallar
     */

    private String email = "leve@gmail.com";
    private String password = "123";

    ServicioRegistro servicioRegistro = mock(ServicioRegistro.class);

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
        ControladorRegistro controladorRegistro = new ControladorRegistro(servicioRegistro);
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

    @Test
    public void siLaPasswordTieneMenosDeCincoCaracteresFalla(){
        givenNoExisteUsuario();
        // Seteo el comportamiento del mock (servicioRegistro) "lanzar esta excepcion cuando se ejecute este codigo"
        doThrow(PasswordLongitudIncorrectaException.class).when(servicioRegistro)
                .registrar("email@email.com", "1234");

        ModelAndView mav = whenRegistroUsuario("email@email.com", "1234");
        thenElRegistroFalla(mav, "La password debe tener al menos cinco caracteres");

        // ejemplo: "retornar un Usuario cuando se ejecute este codigo"
        //when(servicioRegistro.registrar(email, password).thenReturn(new Usuario()));

        // ejemplo: "retornar un Usuario cuando se ejecute este codigo"
        //when(servicioRegistro.registrar(email, password).thenReturn(null));

        // ejemplo (como esta hecho pero esta es otra forma):
        //"retornar una excepcion cuando se ejecute este codigo"
        //when(servicioRegistro.registrar(email, password).thenThrow(PasswordLongitudIncorrectaException.class));

    }


}
