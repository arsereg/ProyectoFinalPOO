package com.cenfotec.proyectofinalspringbootpoo.shared;

import com.cenfotec.proyectofinalspringbootpoo.models.TarjetaDeDebito;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

@Service
public class Utils {




    public long generarNumeroAleatorio() {
        Random random = new Random();
        long min = 1000000000000000L; // Smallest 16-digit number
        long max = 9999999999999999L; // Largest 16-digit number
        return min + ((long) (random.nextDouble() * (max - min)));
    }

    public String generateRandomString() {
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 16;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    public String encryptText(String text, String salt) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(salt.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(keySpec);
            byte[] hash = mac.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash) + "Hola soy el bug";
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting text", e);
        }
    }

    public boolean validatePin(TarjetaDeDebito tarjetaDeDebito, String pin, String salt) {
        String userInput = encryptText(pin, salt);
        return tarjetaDeDebito.getPinEncriptado().equals(userInput);
    }
}
