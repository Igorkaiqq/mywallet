package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Transacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoEntity {

    @Id
    @Column(name = "Id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "UsuarioId", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "TipoTransacaoId", nullable = false)
    private TipoTransacaoEntity tipoTransacao;

    @ManyToOne
    @JoinColumn(name = "CategoriaUsuarioId", nullable = false)
    private CategoriaUsuarioEntity categoriaUsuario;

    @ManyToOne
    @JoinColumn(name = "SubcategoriaUsuarioId", nullable = false)
    private SubcategoriaUsuarioEntity subcategoriaUsuario;

    @ManyToOne
    @JoinColumn(name = "MetodoPagamentoId", nullable = false)
    private MetodoPagamentoEntity metodoPagamento;

    @ManyToOne
    @JoinColumn(name = "ContaBancariaId", nullable = false)
    private ContaBancariaEntity contaBancaria;

    @Column(name = "Valor", nullable = false)
    private Double valor;

    @Column(name = "Data", nullable = false)
    @CreationTimestamp
    private LocalDate data;

    @Column(name = "Descricao", length = 200)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;

}
