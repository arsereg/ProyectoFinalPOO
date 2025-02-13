package com.cenfotec.proyectofinalspringbootpoo.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransaccionId", nullable = false)
    private Long TransaccionId;
    @Column(name = "transactionDate")
    private ZonedDateTime fecha;
    @Column(name = "monto")
    private Double monto;
    @Column(name = "tienda")
    private String tienda;

}
