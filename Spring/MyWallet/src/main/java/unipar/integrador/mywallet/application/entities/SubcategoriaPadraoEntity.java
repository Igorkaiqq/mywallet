package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.UUID;

@Entity
@Table(name = "SubcategoriaPadrao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaPadraoEntity {

    @Id
    @Column(name = "Id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "CategoriaPadraoId", nullable = true)
    private CategoriaPadraoEntity categoriaPadrao;

    @Column(name = "Nome", length = 70, nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;

}
