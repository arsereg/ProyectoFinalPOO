package com.cenfotec.proyectofinalspringbootpoo.services;

import com.cenfotec.proyectofinalspringbootpoo.models.Cuenta;
import com.cenfotec.proyectofinalspringbootpoo.models.TarjetaDeDebito;
import com.cenfotec.proyectofinalspringbootpoo.models.Usuario;
import com.cenfotec.proyectofinalspringbootpoo.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public Cuenta save(Cuenta tarjetaDeDebito){
        return cuentaRepository.save(tarjetaDeDebito);
    }

    public void addTarjeta(Long tarjetaId, TarjetaDeDebito tarjetaDeDebito){
        cuentaRepository.findById(tarjetaId).get().getTarjetas().add(tarjetaDeDebito);
        cuentaRepository.save(cuentaRepository.findById(tarjetaId).get());
    }

    public List<Cuenta> findAllByUsuario(Usuario usuario){
        return cuentaRepository.findAllByUsuario(usuario);
    }





}
