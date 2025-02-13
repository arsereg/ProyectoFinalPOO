package com.cenfotec.proyectofinalspringbootpoo.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CuentaId", nullable = false)
    private Long CuentaId;

    @Column(name = "numeroCuenta")
    private Long numeroCuenta;

    @Column(name = "balance")
    private double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @OneToMany(mappedBy = "TarjetaDeDebitoId", fetch = FetchType.EAGER)
    @Builder.Default
    private List<TarjetaDeDebito> tarjetas = new ArrayList<>();

    @OneToMany(mappedBy = "TransaccionId", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Transaccion> transacciones = new ArrayList<>();







}
