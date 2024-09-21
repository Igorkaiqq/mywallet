package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.*;
import unipar.integrador.mywallet.application.enums.MetodoPagamentoEnum;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

@Entity
@Table(name = "MetodoPagamento")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MetodoPagamentoEntity extends Base {

    @Column(name = "MetodoPagamento", nullable = false)
    @Enumerated(EnumType.STRING)
    public MetodoPagamentoEnum metodoPagamentoEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;

}
