package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.Date;

@Entity
@Table(name = "Transacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TransacaoEntity extends Base {

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

    @Column(name = "Valor", nullable = false)
    private Double valor;

    @Column(name = "Data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column(name = "Descricao", length = 200)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;

}
