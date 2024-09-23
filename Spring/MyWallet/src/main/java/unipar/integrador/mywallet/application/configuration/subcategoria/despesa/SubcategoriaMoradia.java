package unipar.integrador.mywallet.application.configuration.subcategoria.despesa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unipar.integrador.mywallet.application.entities.CategoriaPadraoEntity;
import unipar.integrador.mywallet.application.entities.SubcategoriaPadraoEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.infrastructure.repository.CategoriaPadraoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class SubcategoriaMoradia {

    @Autowired
    private CategoriaPadraoRepository categoriaPadraoRepository;

    public List<SubcategoriaPadraoEntity> getSubcategorias() {

        CategoriaPadraoEntity categoria = categoriaPadraoRepository.findById(UUID.fromString("AB01EE1D-58D4-4D69-9FEF-09AA1E5D1F70"))
                .orElseThrow(() -> new IllegalArgumentException("Categoria Moradia não encontrada."));

        List<SubcategoriaPadraoEntity> subcategorias = Arrays.asList(
                new SubcategoriaPadraoEntity(UUID.fromString("29c4c982-5f21-48ea-a92e-7d6bdaed92f3"), categoria, "Aluguel", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("2a69df42-704f-4c63-8c82-37a6cbf59485"), categoria, "Energia Elétrica", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("b4260e98-6b39-4624-9b77-925e31b482d4"), categoria, "Água e Saneamento", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("6c93e3f6-b03b-4b2e-92f5-b2e2f045b39f"), categoria, "Condomínio", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("80b1e1ad-7b92-47c7-bbdf-491c09c74bb6"), categoria, "Reparos e Manutenção", StatusRegistroEnum.ATIVO)
        );

        return subcategorias;
    }

}
