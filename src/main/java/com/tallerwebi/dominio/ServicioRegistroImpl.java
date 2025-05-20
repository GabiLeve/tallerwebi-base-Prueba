package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro{

    @Override
    public Usuario registrar(String email, String password) {
        if(password.length()<5){
            throw new PasswordLongitudIncorrectaException();
        }
        return new Usuario();
    }
}
