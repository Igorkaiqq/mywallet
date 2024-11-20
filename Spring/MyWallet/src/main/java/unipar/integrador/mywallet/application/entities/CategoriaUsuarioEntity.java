package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.UUID;

@Entity
@Table(name = "CategoriaUsuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaUsuarioEntity{

    @Id
    @Column(name = "Id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "UsuarioId", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioEntity;

    @ManyToOne
    @JoinColumn(name = "TipoTransacaoId", referencedColumnName = "id", nullable = false)
    private TipoTransacaoEntity tipoTransacaoEntity;

    @ManyToOne
    @JoinColumn(name = "CategoriaPadraoId", referencedColumnName = "id", nullable = true)
    private CategoriaPadraoEntity categoriaPadraoEntity;

    @Column(name = "Nome", length = 70, nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;

}
