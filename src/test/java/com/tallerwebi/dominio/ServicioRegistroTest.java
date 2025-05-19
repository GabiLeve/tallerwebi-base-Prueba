package com.tallerwebi.dominio;

import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ServicioRegistroTest {

    /*si hay usuario y contraseña el registro es exitoso
    * si la contraseña tiene menos de 5 caracteres el registro falla
    */

    ServicioRegistro servicioRegistro = new ServicioRegistroImpl();
    @Test
    public void siHayEmailYPasswordElRegistroEsExitoso(){
        givenUsuarioNoExiste();
        Usuario usuarioCreado = whenRegistroUsuario("email@email.com", "12345");
        thenRegistroEsExitoso(usuarioCreado);
    }

    /*aca el servicio no nos envia a ninguna vista como para hacer las comprobaciones como los test anteriores.
    * Entonces el servicio podria devolver un objeto usuario si es que logro el registro exitoso*/
    private void thenRegistroEsExitoso(Usuario usuarioCreado) {
        assertThat(usuarioCreado, is(notNullValue()));
    }

    private Usuario whenRegistroUsuario(String email, String password) {
        return  servicioRegistro.registrar(email, password);

    }

    private void givenUsuarioNoExiste() {
    }

    @Test
    public void siLaPasswordTieneMenosDeCincoCaracteresFalla(){
        givenUsuarioNoExiste();
        Usuario usuarioCreado = whenRegistroUsuario("email@email.com", "1234");
        thenRegistroFalla(usuarioCreado);
    }

    private void thenRegistroFalla(Usuario usuarioCreado) {
        assertThat(usuarioCreado, is(nullValue()));
    }

}
