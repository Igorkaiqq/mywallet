package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.enums.TipoTransacaoEnum;

import java.util.UUID;

@Entity
@Table(name = "TipoTransacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoTransacaoEntity {

    @Id
    @Column(name = "Id", nullable = false, updatable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TipoTransacao", nullable = false)
    private TipoTransacaoEnum tipoTransacaoEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;

}
