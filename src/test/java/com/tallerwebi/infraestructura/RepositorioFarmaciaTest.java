package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.Farmacia;
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
        givenTengoUnaListaDeFarmacias();
        List<Farmacia> farmacias = whenBuscoFarmaciaPorNombre("farma");
        thenEncuentroFarmacias(farmacias);
    }


    private void givenTengoUnaListaDeFarmacias() {
        Farmacia farmacia1 = new Farmacia("farmacity");
        Farmacia farmacia2 = new Farmacia("farmaOnline");
        Farmacia farmacia3 = new Farmacia("dr. Ahorro");
        Farmacia farmacia4 = new Farmacia("natal");

        sessionFactory.getCurrentSession().save(farmacia1);
        sessionFactory.getCurrentSession().save(farmacia2);
        sessionFactory.getCurrentSession().save(farmacia3);
        sessionFactory.getCurrentSession().save(farmacia4);
    }

    private List<Farmacia> whenBuscoFarmaciaPorNombre(String nombre) {
        List<Farmacia> farmacias = repo.buscarFarmaciasPorNombre(nombre);
        return farmacias;
    }

    private void thenEncuentroFarmacias(List<Farmacia> farmacias) {
        assertThat(farmacias.size(), equalTo(2));
    }


}