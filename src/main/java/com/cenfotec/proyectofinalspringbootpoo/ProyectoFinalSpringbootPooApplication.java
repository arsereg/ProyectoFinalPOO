package com.cenfotec.proyectofinalspringbootpoo;


import com.cenfotec.proyectofinalspringbootpoo.models.TarjetaDeDebito;
import com.cenfotec.proyectofinalspringbootpoo.models.Transaccion;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;
import java.util.List;



@SpringBootApplication
public class ProyectoFinalSpringbootPooApplication {

    private static BeanFactory beanFactory;

    public static void main(String[] args) {

        beanFactory = SpringApplication.run(ProyectoFinalSpringbootPooApplication.class, args);
        int casoSeleccionado = 3;
        switch(casoSeleccionado){
            case 1:
                // En este caso, todas las transacciones son exitosas,
                // debido a que los pines son correctos,
                // y las transacciones son inferiores al balance disponible en la cuenta
                casoUno();
                break;
            case 2:
                // En este caso, solo la primera transacción es exitosa,
                // Todos los pines son correctos,
                // pero la segunda y tercera transacción son superiores al balance disponible en la cuenta
                casoDos();
                break;
            case 3:
                // En este caso, todas las transacciones son fallidas,
                // debido a que los pines son incorrectos,
                casoTres();
                break;
            case 4:
                // En este caso, todas las transacciones son exitosas,
                // debido a que los pines son correctos,
                // En este caso, las primeras transacciones hacen un abono de 1000, por lo que el límite de compra se incrementa
                casoCuatro();
                break;
            case 5:
                // En este caso, solo la primer transacción es exitosa,
                // En este caso, las primeras transacciones hacen un abono de 1000,
                // por lo que el límite de compra se incrementa
                // pero solo la primer transacción hace una compra con un monto menor en su balance
                casoCinco();
                break;
            case 6:
                // En este caso, todas las transacciones son fallidas,
                // Se realizan 3 transacciones con pines incorrectos en la misma tarjeta, por lo que la tarjeta queda bloqueada
                // Esto hace que, en la cuarta transacción, a pesar de ingresar el pin correcto y un monto inferior al balance disponible,
                // la transacción sea fallida
                casoSeis();
                break;

            case 7:
                // En este caso, las primeras dos transacciones son fallidas, debido a que el pin es incorrecto,
                // la tercera transacción es exitosa, debido a que el pin es correcto
                // la cuarta transacción es fallida, debido a que el pin es incorrecto
                // la quinta transacción es exitosa, debido a que el pin es correcto
                casoSeis();
                break;
        }


    }


    public static void casoUno(){
        BusinessLogic logicaNegocio = beanFactory.getBean(BusinessLogic.class);

        String email = "esandoval@mail.com";
        // Pines correctos
        List<String> pines = List.of("1234", "6465", "9795");

        List<TarjetaDeDebito> tarjetas = logicaNegocio.buscarTarjetasDeDebitoPorEmail(email);

        Transaccion primeraTransaccion = Transaccion.builder()
                .monto(300.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion segundaTransaccion = Transaccion.builder()
                .monto(100.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion terceraTransaccion = Transaccion.builder()
                .monto(30.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion primerResultado = logicaNegocio.crearTransaccion(primeraTransaccion, tarjetas.get(0), pines.get(0));
        Transaccion segundoResultado = logicaNegocio.crearTransaccion(segundaTransaccion, tarjetas.get(1), pines.get(1));
        Transaccion tercerResultado = logicaNegocio.crearTransaccion(terceraTransaccion, tarjetas.get(2), pines.get(2));

        if(primerResultado != null){
            System.out.println("Transacción 1 exitosa");
        }else {
            System.out.println("Transacción 1 fallida");
        }

        if(segundoResultado != null){
            System.out.println("Transacción 2 exitosa");
        }else {
            System.out.println("Transacción 2 fallida");
        }

        if(tercerResultado != null){
            System.out.println("Transacción 3 exitosa");
        }else {
            System.out.println("Transacción 3 fallida");
        }

    }


    public static void casoDos(){
        BusinessLogic logicaNegocio = beanFactory.getBean(BusinessLogic.class);

        String email = "esandoval@mail.com";
        // Pines correctos
        List<String> pines = List.of("1234", "6465", "9795");

        List<TarjetaDeDebito> tarjetas = logicaNegocio.buscarTarjetasDeDebitoPorEmail(email);

        Transaccion primeraTransaccion = Transaccion.builder()
                .monto(300.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion segundaTransaccion = Transaccion.builder()
                .monto(700.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion terceraTransaccion = Transaccion.builder()
                .monto(1320.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion primerResultado = logicaNegocio.crearTransaccion(primeraTransaccion, tarjetas.get(0), pines.get(0));
        Transaccion segundoResultado = logicaNegocio.crearTransaccion(segundaTransaccion, tarjetas.get(1), pines.get(1));
        Transaccion tercerResultado = logicaNegocio.crearTransaccion(terceraTransaccion, tarjetas.get(2), pines.get(2));

        if(primerResultado != null){
            System.out.println("Transacción 1 exitosa");
        }else {
            System.out.println("Transacción 1 fallida");
        }

        if(segundoResultado != null){
            System.out.println("Transacción 2 exitosa");
        }else {
            System.out.println("Transacción 2 fallida");
        }

        if(tercerResultado != null){
            System.out.println("Transacción 3 exitosa");
        }else {
            System.out.println("Transacción 3 fallida");
        }

    }

    public static void casoTres(){
        BusinessLogic logicaNegocio = beanFactory.getBean(BusinessLogic.class);

        String email = "esandoval@mail.com";
        // Pines correctos
        List<String> pines = List.of("5555", "5555", "5555");

        List<TarjetaDeDebito> tarjetas = logicaNegocio.buscarTarjetasDeDebitoPorEmail(email);

        Transaccion primeraTransaccion = Transaccion.builder()
                .monto(300.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion segundaTransaccion = Transaccion.builder()
                .monto(100.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion terceraTransaccion = Transaccion.builder()
                .monto(30.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion primerResultado = logicaNegocio.crearTransaccion(primeraTransaccion, tarjetas.get(0), pines.get(0));
        Transaccion segundoResultado = logicaNegocio.crearTransaccion(segundaTransaccion, tarjetas.get(1), pines.get(1));
        Transaccion tercerResultado = logicaNegocio.crearTransaccion(terceraTransaccion, tarjetas.get(2), pines.get(2));

        if(primerResultado != null){
            System.out.println("Transacción 1 exitosa");
        }else {
            System.out.println("Transacción 1 fallida");
        }

        if(segundoResultado != null){
            System.out.println("Transacción 2 exitosa");
        }else {
            System.out.println("Transacción 2 fallida");
        }

        if(tercerResultado != null){
            System.out.println("Transacción 3 exitosa");
        }else {
            System.out.println("Transacción 3 fallida");
        }

    }

    public static void casoCuatro(){


        BusinessLogic logicaNegocio = beanFactory.getBean(BusinessLogic.class);

        String email = "esandoval@mail.com";
        // Pines correctos
        List<String> pines = List.of("1234", "6465", "9795");

        List<TarjetaDeDebito> tarjetas = logicaNegocio.buscarTarjetasDeDebitoPorEmail(email);

        for (int i = 0; i < 3; i++) {
            logicaNegocio.crearTransaccion(Transaccion.builder()
                    .monto(-1000.00)
                    .tienda("Zapatería")
                    .fecha(ZonedDateTime.now())
                    .build(), tarjetas.get(i), pines.get(i));
        }

        Transaccion primeraTransaccion = Transaccion.builder()
                .monto(800.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion segundaTransaccion = Transaccion.builder()
                .monto(600.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion terceraTransaccion = Transaccion.builder()
                .monto(400.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion primerResultado = logicaNegocio.crearTransaccion(primeraTransaccion, tarjetas.get(0), pines.get(0));
        Transaccion segundoResultado = logicaNegocio.crearTransaccion(segundaTransaccion, tarjetas.get(1), pines.get(1));
        Transaccion tercerResultado = logicaNegocio.crearTransaccion(terceraTransaccion, tarjetas.get(2), pines.get(2));

        if(primerResultado != null){
            System.out.println("Transacción 1 exitosa");
        }else {
            System.out.println("Transacción 1 fallida");
        }

        if(segundoResultado != null){
            System.out.println("Transacción 2 exitosa");
        }else {
            System.out.println("Transacción 2 fallida");
        }

        if(tercerResultado != null){
            System.out.println("Transacción 3 exitosa");
        }else {
            System.out.println("Transacción 3 fallida");
        }

    }

    public static void casoCinco(){


        BusinessLogic logicaNegocio = beanFactory.getBean(BusinessLogic.class);

        String email = "esandoval@mail.com";
        // Pines correctos
        List<String> pines = List.of("1234", "6465", "9795");

        List<TarjetaDeDebito> tarjetas = logicaNegocio.buscarTarjetasDeDebitoPorEmail(email);

        for (int i = 0; i < 3; i++) {
            logicaNegocio.crearTransaccion(Transaccion.builder()
                    .monto(-1000.00)
                    .tienda("Zapatería")
                    .fecha(ZonedDateTime.now())
                    .build(), tarjetas.get(i), pines.get(i));
        }

        Transaccion primeraTransaccion = Transaccion.builder()
                .monto(800.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion segundaTransaccion = Transaccion.builder()
                .monto(6000.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion terceraTransaccion = Transaccion.builder()
                .monto(4000.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion primerResultado = logicaNegocio.crearTransaccion(primeraTransaccion, tarjetas.get(0), pines.get(0));
        Transaccion segundoResultado = logicaNegocio.crearTransaccion(segundaTransaccion, tarjetas.get(1), pines.get(1));
        Transaccion tercerResultado = logicaNegocio.crearTransaccion(terceraTransaccion, tarjetas.get(2), pines.get(2));

        if(primerResultado != null){
            System.out.println("Transacción 1 exitosa");
        }else {
            System.out.println("Transacción 1 fallida");
        }

        if(segundoResultado != null){
            System.out.println("Transacción 2 exitosa");
        }else {
            System.out.println("Transacción 2 fallida");
        }

        if(tercerResultado != null){
            System.out.println("Transacción 3 exitosa");
        }else {
            System.out.println("Transacción 3 fallida");
        }

    }


    public static void casoSeis(){


        BusinessLogic logicaNegocio = beanFactory.getBean(BusinessLogic.class);

        String email = "esandoval@mail.com";

        String pinCorrecto = "1234";

        TarjetaDeDebito tarjeta = logicaNegocio.buscarTarjetasDeDebitoPorEmail(email).get(0);


        Transaccion primeraTransaccion = Transaccion.builder()
                .monto(50.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion segundaTransaccion = Transaccion.builder()
                .monto(75.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion terceraTransaccion = Transaccion.builder()
                .monto(80.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion cuartaTransaccion = Transaccion.builder()
                .monto(80.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion primerResultado = logicaNegocio.crearTransaccion(primeraTransaccion, tarjeta, "1111");
        Transaccion segundoResultado = logicaNegocio.crearTransaccion(segundaTransaccion, tarjeta, "1111");
        Transaccion tercerResultado = logicaNegocio.crearTransaccion(terceraTransaccion, tarjeta, "1111");
        Transaccion cuartoResultado = logicaNegocio.crearTransaccion(terceraTransaccion, tarjeta, pinCorrecto);

        if(primerResultado != null){
            System.out.println("Transacción 1 exitosa");
        }else {
            System.out.println("Transacción 1 fallida");
        }

        if(segundoResultado != null){
            System.out.println("Transacción 2 exitosa");
        }else {
            System.out.println("Transacción 2 fallida");
        }

        if(tercerResultado != null){
            System.out.println("Transacción 3 exitosa");
        }else {
            System.out.println("Transacción 3 fallida");
        }

        if(cuartoResultado != null){
            System.out.println("Transacción 4 exitosa");
        }else {
            System.out.println("Transacción 4 fallida");
        }

    }

    public static void casoSiete(){


        BusinessLogic logicaNegocio = beanFactory.getBean(BusinessLogic.class);

        String email = "esandoval@mail.com";

        String pinCorrecto = "1234";

        TarjetaDeDebito tarjeta = logicaNegocio.buscarTarjetasDeDebitoPorEmail(email).get(0);


        Transaccion primeraTransaccion = Transaccion.builder()
                .monto(50.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion segundaTransaccion = Transaccion.builder()
                .monto(75.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion terceraTransaccion = Transaccion.builder()
                .monto(80.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion cuartaTransaccion = Transaccion.builder()
                .monto(80.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion quintaTransaccion = Transaccion.builder()
                .monto(80.00)
                .tienda("Zapatería")
                .fecha(ZonedDateTime.now())
                .build();

        Transaccion primerResultado = logicaNegocio.crearTransaccion(primeraTransaccion, tarjeta, "1111");
        Transaccion segundoResultado = logicaNegocio.crearTransaccion(segundaTransaccion, tarjeta, "1111");
        Transaccion tercerResultado = logicaNegocio.crearTransaccion(terceraTransaccion, tarjeta, pinCorrecto);
        Transaccion cuartoResultado = logicaNegocio.crearTransaccion(terceraTransaccion, tarjeta, "1111");
        Transaccion quintoResultado = logicaNegocio.crearTransaccion(terceraTransaccion, tarjeta, pinCorrecto);

        if(primerResultado != null){
            System.out.println("Transacción 1 exitosa");
        }else {
            System.out.println("Transacción 1 fallida");
        }

        if(segundoResultado != null){
            System.out.println("Transacción 2 exitosa");
        }else {
            System.out.println("Transacción 2 fallida");
        }

        if(tercerResultado != null){
            System.out.println("Transacción 3 exitosa");
        }else {
            System.out.println("Transacción 3 fallida");
        }

        if(cuartoResultado != null){
            System.out.println("Transacción 4 exitosa");
        }else {
            System.out.println("Transacción 4 fallida");
        }

        if(quintoResultado != null){
            System.out.println("Transacción 5 exitosa");
        }else {
            System.out.println("Transacción 5 fallida");
        }

    }




}
