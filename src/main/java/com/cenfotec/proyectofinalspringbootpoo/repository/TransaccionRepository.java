package com.cenfotec.proyectofinalspringbootpoo.repository;

import com.cenfotec.proyectofinalspringbootpoo.models.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}
