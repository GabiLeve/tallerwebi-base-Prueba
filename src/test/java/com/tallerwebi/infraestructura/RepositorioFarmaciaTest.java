package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.Direccion;
import com.tallerwebi.dominio.Farmacia;
import com.tallerwebi.dominio.Localidad;
import com.tallerwebi.dominio.RepositorioFarmacia;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioFarmaciaTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    RepositorioFarmacia repo;

    @Test
    @Transactional
    @Rollback
    public void puedoObtenerUnaListaDeFarmaciasConLikeNombre() {
        //givenTengoUnaListaDeFarmacias();
        Localidad llocalidad1 = givenTengoUnaLocalidad("tapiales");
        Localidad llocalidad2 = givenTengoUnaLocalidad("san justo");

        givenTengoUnaFarmacia("farmacity", "asturias", 123, llocalidad1);
        givenTengoUnaFarmacia("farmaOnline", "donovan", 654, llocalidad2);
        givenTengoUnaFarmacia("dr. Ahorro", "ugarte", 789, llocalidad1);
        givenTengoUnaFarmacia("natal", "rivadavia", 963, llocalidad2);

        List<Farmacia> farmacias = whenBuscoFarmaciaPorNombre("farma");
        thenEncuentroFarmacias(farmacias, 2);
    }
    //Finaliza el mét0do y, por la combinación de @Transactional y @Rollback,
    //    hace un rollback de la transacción, deshaciendo los cambios
    //    realizados por givenTengoUnaListaDeFarmacias().

//    private void givenTengoUnaListaDeFarmacias() {
//        Farmacia farmacia1 = new Farmacia("farmacity");
//        Farmacia farmacia2 = new Farmacia("farmaOnline");
//        Farmacia farmacia3 = new Farmacia("dr. Ahorro");
//        Farmacia farmacia4 = new Farmacia("natal");
//
//        sessionFactory.getCurrentSession().save(farmacia1);
//        sessionFactory.getCurrentSession().save(farmacia2);
//        sessionFactory.getCurrentSession().save(farmacia3);
//        sessionFactory.getCurrentSession().save(farmacia4);
//    }

    private List<Farmacia> whenBuscoFarmaciaPorNombre(String nombre) {
        List<Farmacia> farmacias = repo.buscarFarmaciasPorNombre(nombre);
        return farmacias;
    }

    private void thenEncuentroFarmacias(List<Farmacia> farmacias, Integer cantidadEsperada) {
        assertThat(farmacias.size(), equalTo(cantidadEsperada));
    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarFarmaciasPorCalle(){
        Localidad llocalidad1 = givenTengoUnaLocalidad("tapiales");
        Localidad llocalidad2 = givenTengoUnaLocalidad("san justo");

        givenTengoUnaFarmacia("farmacity", "asturias", 123, llocalidad2);
        givenTengoUnaFarmacia("farmaOnline", "donovan", 654, llocalidad2);
        givenTengoUnaFarmacia("dr. Ahorro", "ugarte", 789, llocalidad1);
        givenTengoUnaFarmacia("natal", "rivadavia", 963, llocalidad1);

        List<Farmacia> farmacias = whenBuscoFarmaciaPorCalle("rivadavia");

        thenEncuentroFarmacias(farmacias, 1);
    }

    private Localidad givenTengoUnaLocalidad(String nombre) { //localidad debe guardarse a ella misma
        Localidad localidad = new Localidad();// se crea una localidad
        localidad.setNombre(nombre); //se gusrda el nombre
        sessionFactory.getCurrentSession().save(localidad); // se guarda en db
        return localidad; // se retorna la localidad
    }

    private List<Farmacia> whenBuscoFarmaciaPorCalle(String calle) {
        List<Farmacia> farmacias = repo.buscarFarmaciasPorCalle(calle);
        return farmacias;
    }

    private void givenTengoUnaFarmacia(String nombreFarmacia, String calle, Integer numero, Localidad localidad){
        Direccion direccion = new Direccion(calle, numero, localidad);
        //sessionFactory.getCurrentSession().save(direccion); remplazado por cascade.all (cuando se guarde la farmacia se guardara su direccion asociada
        Farmacia farmacia1 = new Farmacia(nombreFarmacia, direccion);
        sessionFactory.getCurrentSession().save(farmacia1);
    };

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarFarmaciasPorNombreDeLocalidad(){
        Localidad tapiales = givenTengoUnaLocalidad("tapiales");
        Localidad sanJusto = givenTengoUnaLocalidad("san justo");

        givenTengoUnaFarmacia("farmacity", "asturias", 123, sanJusto);
        givenTengoUnaFarmacia("farmaOnline", "donovan", 654, sanJusto);
        givenTengoUnaFarmacia("dr. Ahorro", "ugarte", 789, tapiales);
        givenTengoUnaFarmacia("natal", "rivadavia", 963, tapiales);

        List<Farmacia> farmacias = whenBuscoFarmaciaPorNombreDeLocalidad(tapiales.getNombre());

        thenEncuentroFarmacias(farmacias, 2);
    }

    private List<Farmacia> whenBuscoFarmaciaPorNombreDeLocalidad(String localidad) {
        List<Farmacia> farmacias = repo.buscarFarmaciasPorNombreDeLocalidad(localidad);
        return farmacias;
    }


    @Test
    @Transactional
    @Rollback
    public void puedoBuscarFarmaciasPorLocalidad(){
        Localidad tapiales = givenTengoUnaLocalidad("tapiales");
        Localidad sanJusto = givenTengoUnaLocalidad("san justo");

        givenTengoUnaFarmacia("farmacity", "asturias", 123, sanJusto);
        givenTengoUnaFarmacia("farmaOnline", "donovan", 654, sanJusto);
        givenTengoUnaFarmacia("dr. Ahorro", "ugarte", 789, tapiales);
        givenTengoUnaFarmacia("natal", "rivadavia", 963, tapiales);

        List<Farmacia> farmacias = whenBuscoFarmaciaPorLocalidad(tapiales);

        thenEncuentroFarmacias(farmacias, 2);
    }

    private List<Farmacia> whenBuscoFarmaciaPorLocalidad(Localidad localidad) {
        List<Farmacia> farmacias = repo.buscarFarmaciasPorLocalidad(localidad);
        return farmacias;
    }
}