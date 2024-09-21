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
public class CategoriaReceita {

    private TipoTransacaoRepository tipoTransacaoRepository;

    public List<CategoriaPadraoEntity> getCategoriasReceitaPadrao() {
        TipoTransacaoEntity receitaId = tipoTransacaoRepository.findById(UUID.fromString("2B8285B8-97AD-4727-AF3A-E39D2ECE52E7"))
                .orElseThrow(() -> new IllegalArgumentException("Tipo de transação RECEITA não encontrado."));

        List<CategoriaPadraoEntity> categoriasReceitaPadrao = Arrays.asList(
                new CategoriaPadraoEntity(UUID.fromString("36C95388-D1EB-418A-8149-D427D7499CE8"), receitaId, "Salário", StatusRegistroEnum.ATIVO),
                new CategoriaPadraoEntity(UUID.fromString("9FC9EEAF-DB22-4BC7-A896-1B12F8E7A597"), receitaId, "Investimentos", StatusRegistroEnum.ATIVO),
                new CategoriaPadraoEntity(UUID.fromString("70EC698F-B733-447D-933B-C6B11A67F3E1"), receitaId, "Outros", StatusRegistroEnum.ATIVO)
        );

        return categoriasReceitaPadrao;
    }
}
