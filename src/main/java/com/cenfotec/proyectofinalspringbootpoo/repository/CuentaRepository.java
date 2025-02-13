package com.cenfotec.proyectofinalspringbootpoo.repository;

import com.cenfotec.proyectofinalspringbootpoo.models.Cuenta;
import com.cenfotec.proyectofinalspringbootpoo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    List<Cuenta> findAllByUsuario(Usuario usuario);

}
