package com.cenfotec.proyectofinalspringbootpoo.services;

import com.cenfotec.proyectofinalspringbootpoo.models.Transaccion;
import com.cenfotec.proyectofinalspringbootpoo.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;


    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public Transaccion saveTransaction(Transaccion transaction){
        return this.transaccionRepository.save(transaction);
    }
}
