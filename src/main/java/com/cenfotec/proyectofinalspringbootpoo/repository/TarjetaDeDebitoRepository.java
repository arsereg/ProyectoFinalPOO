package com.cenfotec.proyectofinalspringbootpoo.repository;


import com.cenfotec.proyectofinalspringbootpoo.models.Cuenta;
import com.cenfotec.proyectofinalspringbootpoo.models.TarjetaDeDebito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarjetaDeDebitoRepository extends JpaRepository<TarjetaDeDebito, Long>{


    List<TarjetaDeDebito> findAllByCuentaIn(List<Cuenta> cuenta);

}
