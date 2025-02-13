package com.cenfotec.proyectofinalspringbootpoo.services;

import com.cenfotec.proyectofinalspringbootpoo.models.Cuenta;
import com.cenfotec.proyectofinalspringbootpoo.models.TarjetaDeDebito;
import com.cenfotec.proyectofinalspringbootpoo.repository.TarjetaDeDebitoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarjetaDeDebitoService {

    private final TarjetaDeDebitoRepository tarjetaDeDebitoRepository;

    public TarjetaDeDebitoService(TarjetaDeDebitoRepository tarjetaDeDebitoRepository) {
        this.tarjetaDeDebitoRepository = tarjetaDeDebitoRepository;
    }

    public TarjetaDeDebito save(TarjetaDeDebito tarjeta) {
        return tarjetaDeDebitoRepository.save(tarjeta);
    }

    public List<TarjetaDeDebito> buscarTarjetasPorCuenta(List<Cuenta> cuentas){
        return tarjetaDeDebitoRepository.findAllByCuentaIn(cuentas);
    }
}
