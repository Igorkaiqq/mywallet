package unipar.integrador.mywallet.application.configuration.metodoPagamento;

import org.springframework.stereotype.Component;
import unipar.integrador.mywallet.application.entities.MetodoPagamentoEntity;
import unipar.integrador.mywallet.application.enums.MetodoPagamentoEnum;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class MetodoPagamento {

    public List<MetodoPagamentoEntity> getMetodosPagamento() {

        List<MetodoPagamentoEntity> metodosPagamento = Arrays.asList(
                new MetodoPagamentoEntity(UUID.fromString("8B8404DD-59D7-4EE2-A655-29899F691E99"), MetodoPagamentoEnum.DINHEIRO, StatusRegistroEnum.ATIVO),
                new MetodoPagamentoEntity(UUID.fromString("91EE9B20-2D46-4C77-946F-2AE1AFE2A3A0"), MetodoPagamentoEnum.CARTAO_CREDITO, StatusRegistroEnum.ATIVO),
                new MetodoPagamentoEntity(UUID.fromString("2BDECE45-6FE6-42A5-B902-66DA2AAD20E3"), MetodoPagamentoEnum.CARTAO_DEBITO, StatusRegistroEnum.ATIVO),
                new MetodoPagamentoEntity(UUID.fromString("D9823DB9-F4F0-489D-B99E-0F3FAA593504"), MetodoPagamentoEnum.PIX, StatusRegistroEnum.ATIVO)
        );

        return metodosPagamento;
    }

}
