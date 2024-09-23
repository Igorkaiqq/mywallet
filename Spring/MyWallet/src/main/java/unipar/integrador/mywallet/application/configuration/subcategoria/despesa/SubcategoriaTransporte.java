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
public class SubcategoriaTransporte {

    @Autowired
    private CategoriaPadraoRepository categoriaPadraoRepository;

    public List<SubcategoriaPadraoEntity> getSubcategorias() {

        CategoriaPadraoEntity categoria = categoriaPadraoRepository.findById(UUID.fromString("4185ABBE-C9A2-49E1-B95A-3EED862003AF"))
                .orElseThrow(() -> new IllegalArgumentException("Categoria Transporte não encontrada."));

        List<SubcategoriaPadraoEntity> subcategorias = Arrays.asList(
                new SubcategoriaPadraoEntity(UUID.fromString("D4A1F93E-670F-4B54-9D8E-CA1B76EAC957"), categoria, "Combustível", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("FE574A48-88FB-4EB5-B3A0-B179CC16E8E6"), categoria, "Transporte Público", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("33CB8E4E-4D7C-4DF5-BE25-89AF56A665B6"), categoria, "Estacionamento", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("A8416249-DA34-467A-889D-8696562E635B"), categoria, "Manutenção Veicular", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("19E348DF-17B4-4E2F-9DC6-FCE982CF622D"), categoria, "Pedágio", StatusRegistroEnum.ATIVO)
        );

        return subcategorias;
    }

}
