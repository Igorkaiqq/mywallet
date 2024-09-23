package unipar.integrador.mywallet.application.configuration.tipoTransacao;

import org.springframework.stereotype.Component;
import unipar.integrador.mywallet.application.entities.TipoTransacaoEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.enums.TipoTransacaoEnum;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class TipoTransacao {

    public List<TipoTransacaoEntity> getTiposTransacao(){

        List<TipoTransacaoEntity> tiposTransacao = Arrays.asList(
                new TipoTransacaoEntity(UUID.fromString("2B8285B8-97AD-4727-AF3A-E39D2ECE52E7"), TipoTransacaoEnum.RECEITA, StatusRegistroEnum.ATIVO),
                new TipoTransacaoEntity(UUID.fromString("694E9A75-5115-45C3-B5A9-BDD06D26A5C0"), TipoTransacaoEnum.DESPESA, StatusRegistroEnum.ATIVO)
        );

        return tiposTransacao;
    }

}
