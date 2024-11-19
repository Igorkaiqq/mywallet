package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.UUID;

@Entity
@Table(name = "CategoriaPadrao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaPadraoEntity{

    @Id
    @Column(name = "Id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "TipoTransacaoId", nullable = false)
    private TipoTransacaoEntity tipoTransacao;

    @Column(name = "Nome", length = 70, nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;

}
