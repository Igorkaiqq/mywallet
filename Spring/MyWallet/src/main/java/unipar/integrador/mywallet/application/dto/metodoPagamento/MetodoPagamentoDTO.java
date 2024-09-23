package unipar.integrador.mywallet.application.dto.metodoPagamento;

import unipar.integrador.mywallet.application.entities.MetodoPagamentoEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

public record MetodoPagamentoDTO(MetodoPagamentoEntity metodoPagamentoEntity, StatusRegistroEnum statusRegistroEnum) {
}
