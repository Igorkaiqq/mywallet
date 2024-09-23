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
public class SubcategoriaSaude {

    @Autowired
    private CategoriaPadraoRepository categoriaPadraoRepository;

    public List<SubcategoriaPadraoEntity> getSubcategorias() {

        CategoriaPadraoEntity categoria = categoriaPadraoRepository.findById(UUID.fromString("A297CA0C-CC76-49D4-81A7-DD060D10D11F"))
                .orElseThrow(() -> new IllegalArgumentException("Categoria Saúde não encontrada."));

        List<SubcategoriaPadraoEntity> subcategorias = Arrays.asList(
                new SubcategoriaPadraoEntity(UUID.fromString("1a13bfb6-1244-4d3b-bad7-5e4eb19f6de4"), categoria, "Consultas Médicas", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("69b00d3f-308e-4410-8a30-c7f92df1076e"), categoria, "Medicamentos", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("73bb1b92-6d98-46b3-aaf0-3623ddc1915f"), categoria, "Exames Laboratoriais", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("a77223b0-7e5e-4fa5-8403-d5697349f1d6"), categoria, "Plano de Saúde", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("0b3a8fc9-89da-4d68-9a38-5006a1d87271"), categoria, "Terapias e Tratamentos", StatusRegistroEnum.ATIVO)
        );

        return subcategorias;
    }

}
