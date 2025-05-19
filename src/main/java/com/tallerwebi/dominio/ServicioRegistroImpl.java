package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro{

    @Override
    public Usuario registrar(String email, String password) {
        if(password.length()<5){
            return null;
        }
        return new Usuario();
    }
}
