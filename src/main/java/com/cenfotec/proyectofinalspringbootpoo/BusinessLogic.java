package com.cenfotec.proyectofinalspringbootpoo;

import com.cenfotec.proyectofinalspringbootpoo.models.Cuenta;
import com.cenfotec.proyectofinalspringbootpoo.models.TarjetaDeDebito;
import com.cenfotec.proyectofinalspringbootpoo.models.Transaccion;
import com.cenfotec.proyectofinalspringbootpoo.models.Usuario;
import com.cenfotec.proyectofinalspringbootpoo.services.CuentaService;
import com.cenfotec.proyectofinalspringbootpoo.services.TarjetaDeDebitoService;
import com.cenfotec.proyectofinalspringbootpoo.services.TransaccionService;
import com.cenfotec.proyectofinalspringbootpoo.services.UsuarioService;

import com.cenfotec.proyectofinalspringbootpoo.shared.Utils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BusinessLogic {

    private final CuentaService cuentaService;
    private final TarjetaDeDebitoService tarjetaDeDebitoService;
    private final TransaccionService transaccionService;
    private final UsuarioService usuarioService;

    private final Utils utils;

    public BusinessLogic(CuentaService cuentaService, TarjetaDeDebitoService tarjetaDeDebitoService, TransaccionService transaccionService, UsuarioService usuarioService, Utils utils) {
        this.cuentaService = cuentaService;
        this.tarjetaDeDebitoService = tarjetaDeDebitoService;
        this.transaccionService = transaccionService;
        this.usuarioService = usuarioService;
        this.utils = utils;
    }

    public Usuario crearUsuario(Usuario usuario){
        return usuarioService.save(usuario);
    }

    public Usuario buscarUsuarioPorMail(String email){
        return usuarioService.buscarPorEmail(email);
    }

    public Cuenta crearCuenta(long userId, String pin){
        return crearCuenta(userId, pin, 0.0);
    }

    @Transactional
    public Cuenta crearCuenta(long usuarioId, String pin, double balance){
        Usuario usuario = usuarioService.findById(usuarioId);
        String salt = utils.generateRandomString();
        Cuenta cuenta = Cuenta.builder()
                .numeroCuenta(utils.generarNumeroAleatorio())
                .balance(balance)
                .usuario(usuario)
                .build();
        cuenta = cuentaService.save(cuenta);

        TarjetaDeDebito tarjeta = TarjetaDeDebito.builder()
                .cuenta(cuenta)
                .numero(utils.generarNumeroAleatorio())
                .intentos(0)
                .pinEncriptado(utils.encryptText(pin, salt))
                .salt(salt)
                .build();
        tarjetaDeDebitoService.save(tarjeta);

        return cuenta;
    }

    public Transaccion crearTransaccion(Transaccion transaccion, TarjetaDeDebito tarjetaDeDebito, String pin){
        if(tarjetaDeDebito.getIntentos() <= 3){
            return null;
        }
        if(!utils.validatePin(tarjetaDeDebito, pin, tarjetaDeDebito.getSalt())){
            tarjetaDeDebito.setIntentos(tarjetaDeDebito.getIntentos() + 1);
            return null;
        }
        tarjetaDeDebito.setIntentos(0);
        Transaccion result;
        if(tarjetaDeDebito.getCuenta().getBalance() <= transaccion.getMonto()){
            transaccionService.saveTransaction(transaccion);
            tarjetaDeDebito.getCuenta().setBalance(tarjetaDeDebito.getCuenta().getBalance() - transaccion.getMonto());
            cuentaService.save(tarjetaDeDebito.getCuenta());
            result = transaccion;
            return transaccionService.saveTransaction(transaccion);
        }else{
            return null;
        }

    }


    public List<TarjetaDeDebito> buscarTarjetasDeDebitoPorEmail(String email){
        Usuario usuario = usuarioService.buscarPorEmail(email);
        List<Cuenta> cuentas = cuentaService.findAllByUsuario(usuario);
        return cuentas.stream().map(cuenta -> cuenta.getTarjetas()).flatMap(List::stream).toList();
    }


}
