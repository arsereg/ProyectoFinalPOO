package com.cenfotec.proyectofinalspringbootpoo.models;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioId", nullable = false)
    private Long UsuarioId;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;
    @Column(name = "telefono")
    private String telefono;

}
