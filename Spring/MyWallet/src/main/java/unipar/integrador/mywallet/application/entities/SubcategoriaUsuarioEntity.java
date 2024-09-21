package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

@Entity
@Table(name = "SubcategoriaUsuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SubcategoriaUsuarioEntity extends Base {

        @ManyToOne
        @JoinColumn(name = "UsuarioId", nullable = false)
        private UsuarioEntity usuarioEntity;

        @ManyToOne
        @JoinColumn(name = "CategoriaUsuarioId", nullable = false)
        private CategoriaUsuarioEntity categoriaUsuario;

        @ManyToOne
        @JoinColumn(name = "SubcategoriaPadraoId", nullable = true)
        private CategoriaPadraoEntity subcategoriaPadrao;

        @Column(name = "Nome", length = 70, nullable = false)
        private String nome;

        @Enumerated(EnumType.STRING)
        @Column(name = "StatusRegistro", nullable = false)
        private StatusRegistroEnum statusRegistro;

}
