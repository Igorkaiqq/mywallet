package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.UUID;

@Entity
@Table(name = "SubcategoriaUsuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaUsuarioEntity {

        @Id
        @Column(name = "Id", nullable = false, updatable = false)
        private UUID id;

        @ManyToOne
        @JoinColumn(name = "UsuarioId", nullable = false)
        private UsuarioEntity usuarioEntity;

        @ManyToOne
        @JoinColumn(name = "CategoriaUsuarioId", nullable = false)
        private CategoriaUsuarioEntity categoriaUsuario;

        @ManyToOne
        @JoinColumn(name = "SubcategoriaPadraoId", nullable = true)
        private SubcategoriaPadraoEntity subcategoriaPadrao;

        @Column(name = "Nome", length = 70, nullable = false)
        private String nome;

        @Enumerated(EnumType.STRING)
        @Column(name = "StatusRegistro", nullable = false)
        private StatusRegistroEnum statusRegistro;

}
