package com.cenfotec.proyectofinalspringbootpoo.services;

import com.cenfotec.proyectofinalspringbootpoo.models.Usuario;
import com.cenfotec.proyectofinalspringbootpoo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Usuario findById(long id){
        return usuarioRepository.findById(id).orElse(null);
    }

}
