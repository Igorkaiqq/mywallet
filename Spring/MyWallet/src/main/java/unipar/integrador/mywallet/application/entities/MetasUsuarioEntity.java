package unipar.integrador.mywallet.application.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.UUID;

@Entity
@Table(name = "MetasUsuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetasUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "UsuarioId", nullable = false)
    private UsuarioEntity usuarioEntity;

    @ManyToOne
    @JoinColumn(name = "CategoriaId", nullable = false)
    private CategoriaUsuarioEntity categoriaId;

    @Column(name = "Valor", length = 70, nullable = false)
    private double valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;

}
