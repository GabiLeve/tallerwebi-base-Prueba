package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Farmacia;
import com.tallerwebi.dominio.Localidad;
import com.tallerwebi.dominio.RepositorioFarmacia;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Farmacia> buscarFarmaciasPorCalle(String calle){
        /*
            SELECT *
            FROM Farmacia f join Direccion d ON f.idDireccion = d.id
            WHERE d.calle = 'rivadavia';
         */

        var session = sessionFactory.getCurrentSession();
        return session.createCriteria(Farmacia.class)
                .createAlias("direccion", "d")
                .add(Restrictions.eq("d.calle",calle))
                .list();
        //return List.of();
    }

    /*
    SELECT *
    FROM Farmacia f join Direccion d ON f.idDireccion = d.id
                    join Localidad l ON d.idLocalidad = l.id
    WHERE l.nombre
     */
    @Override
    public List<Farmacia> buscarFarmaciasPorNombreDeLocalidad(String localidad) {
        var session = sessionFactory.getCurrentSession();
        return session.createCriteria(Farmacia.class)
                .createAlias("direccion", "d")
                .createAlias("d.localidad", "l")
                .add(Restrictions.eq("l.nombre",localidad))
                .list();
    }

    @Override
    public List<Farmacia> buscarFarmaciasPorLocalidad(Localidad localidad) {
//        var session = sessionFactory.getCurrentSession();
//        return session.createCriteria(Farmacia.class)
        return List.of();
    }

}
