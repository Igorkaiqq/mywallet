package unipar.integrador.mywallet.application.configuration.categoria;

import org.springframework.stereotype.Component;
import unipar.integrador.mywallet.application.entities.CategoriaPadraoEntity;
import unipar.integrador.mywallet.application.entities.TipoTransacaoEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.infrastructure.repository.TipoTransacaoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class CategoriaDespesa {

    private TipoTransacaoRepository tipoTransacaoRepository;

    public List<CategoriaPadraoEntity> getCategoriasDespesaPadrao() {
        TipoTransacaoEntity despesaId = tipoTransacaoRepository.findById(UUID.fromString("694E9A75-5115-45C3-B5A9-BDD06D26A5C0"))
                .orElseThrow(() -> new IllegalArgumentException("Tipo de transação DESPESA não encontrado."));

        List<CategoriaPadraoEntity> categoriasDespesaPadrao = Arrays.asList(
                new CategoriaPadraoEntity(UUID.fromString("28C4411E-24EB-4528-B649-293225E8BBE9"), despesaId, "Alimentação", StatusRegistroEnum.ATIVO),
                new CategoriaPadraoEntity(UUID.fromString("4185ABBE-C9A2-49E1-B95A-3EED862003AF"), despesaId, "Transporte", StatusRegistroEnum.ATIVO),
                new CategoriaPadraoEntity(UUID.fromString("AB01EE1D-58D4-4D69-9FEF-09AA1E5D1F70"), despesaId, "Moradia", StatusRegistroEnum.ATIVO),
                new CategoriaPadraoEntity(UUID.fromString("C407FAEA-1FFE-44AC-8502-8EEC05DE9DA7"), despesaId, "Educação", StatusRegistroEnum.ATIVO),
                new CategoriaPadraoEntity(UUID.fromString("A297CA0C-CC76-49D4-81A7-DD060D10D11F"), despesaId, "Saúde", StatusRegistroEnum.ATIVO),
                new CategoriaPadraoEntity(UUID.fromString("AD2D5829-165F-4567-80C1-373C92B716A1"), despesaId, "Lazer", StatusRegistroEnum.ATIVO)
        );

        return categoriasDespesaPadrao;
    }

}
