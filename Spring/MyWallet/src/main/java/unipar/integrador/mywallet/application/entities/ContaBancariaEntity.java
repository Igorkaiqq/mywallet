package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.UUID;

@Entity
@Table(name = "ContaBancaria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContaBancariaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "UsuarioId", nullable = false)
    private UsuarioEntity usuario;

    @Column(name = "Nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "Saldo", nullable = false)
    private Double saldo;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;
}
