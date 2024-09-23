package unipar.integrador.mywallet.application.dto.tipoTransacao;

import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.enums.TipoTransacaoEnum;

public record TipoTransacaoDTO(TipoTransacaoEnum tipoTransacaoEnum, StatusRegistroEnum statusRegistroEnum) {
}
