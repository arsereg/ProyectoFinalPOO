package com.cenfotec.proyectofinalspringbootpoo.repository;

import com.cenfotec.proyectofinalspringbootpoo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

}
