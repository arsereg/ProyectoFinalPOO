package com.cenfotec.proyectofinalspringbootpoo.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarjetaDeDebito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TarjetaDeDebitoId", nullable = false)
    private Long TarjetaDeDebitoId;
    @Column(name = "numero")
    private Long numero;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "salt")
    private String salt;
    @Column(name = "pinEncriptado")
    private String pinEncriptado;
    @Column(name = "intentos")
    private Integer intentos;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

}
