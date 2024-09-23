package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.*;
import unipar.integrador.mywallet.application.enums.MetodoPagamentoEnum;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.UUID;

@Entity
@Table(name = "MetodoPagamento")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagamentoEntity {

    @Id
    @Column(name = "Id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "MetodoPagamento", nullable = false)
    @Enumerated(EnumType.STRING)
    public MetodoPagamentoEnum metodoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;

}
