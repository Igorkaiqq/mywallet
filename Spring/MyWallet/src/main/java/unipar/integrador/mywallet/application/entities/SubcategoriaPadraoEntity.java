package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

@Entity
@Table(name = "SubcategoriaPadrao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SubcategoriaPadraoEntity extends Base {

    @ManyToOne
    @JoinColumn(name = "SubcategoriaPadraoId", nullable = true)
    private CategoriaPadraoEntity categoriaPadrao;

    @Column(name = "Nome", length = 70, nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;

}
