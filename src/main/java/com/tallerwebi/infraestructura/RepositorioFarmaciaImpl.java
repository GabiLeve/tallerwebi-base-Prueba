package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Farmacia;
import com.tallerwebi.dominio.RepositorioFarmacia;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

import static java.awt.AWTEventMulticaster.add;


@Repository
public class RepositorioFarmaciaImpl implements RepositorioFarmacia {

    @Autowired
    SessionFactory sessionFactory;
    /*
        SELECT * FROM Farmacia WHERE nombre like 'farma%'
     */

    @Override
    public List<Farmacia> buscarFarmaciasPorNombre(String nombre){
        var session = sessionFactory.getCurrentSession();
        return session.createCriteria(Farmacia.class)
        .add(Restrictions.like("nombre", nombre + "%"))
                .list();
    }

}
