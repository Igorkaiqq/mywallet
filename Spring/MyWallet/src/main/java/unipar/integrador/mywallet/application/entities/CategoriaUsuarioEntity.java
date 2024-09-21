package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

@Entity
@Table(name = "CategoriaUsuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoriaUsuarioEntity extends Base {

    @ManyToOne
    @JoinColumn(name = "UsuarioId", nullable = false)
    private UsuarioEntity usuarioEntity;

    @ManyToOne
    @JoinColumn(name = "TipoTransacaoId", nullable = false)
    private TipoTransacaoEntity tipoTransacaoEntity;

    @ManyToOne
    @JoinColumn(name = "CategoriaPadraoId", nullable = true)
    private CategoriaPadraoEntity categoriaPadraoEntity;

    @Column(name = "Nome", length = 70, nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;

}
