package com.cenfotec.proyectofinalspringbootpoo.configuration;

import com.cenfotec.proyectofinalspringbootpoo.BusinessLogic;
import com.cenfotec.proyectofinalspringbootpoo.models.Cuenta;
import com.cenfotec.proyectofinalspringbootpoo.models.Usuario;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ServerConfiguration implements ApplicationListener<ApplicationReadyEvent> {

    private final BusinessLogic logicaNegocio;

    public ServerConfiguration(BusinessLogic logicaNegocio) {
        this.logicaNegocio = logicaNegocio;
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Usuario miUsuario = logicaNegocio.buscarUsuarioPorMail("esandoval@mail.com");
        if(miUsuario == null){
             miUsuario = logicaNegocio.crearUsuario(Usuario.builder()
                    .nombre("Esteban")
                    .apellido("Sandoval")
                    .email("esandoval@mail.com")
                    .fechaNacimiento(LocalDate.of(1990, 5, 3))
                    .telefono("50689798959")
                    .build());
            long userId = miUsuario.getUsuarioId();

            logicaNegocio.crearCuenta(userId, "1234", 500);
            logicaNegocio.crearCuenta(userId, "6465", 200);
            logicaNegocio.crearCuenta(userId, "9795", 50);
        }

    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
