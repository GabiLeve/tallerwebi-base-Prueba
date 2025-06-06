package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioFarmacia {
    List<Farmacia> buscarFarmaciasPorNombre(String nombre);
}
