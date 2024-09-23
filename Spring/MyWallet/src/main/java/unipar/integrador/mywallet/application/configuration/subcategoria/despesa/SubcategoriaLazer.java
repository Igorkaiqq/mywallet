package unipar.integrador.mywallet.application.configuration.subcategoria.despesa;

import org.springframework.beans.factory.annotation.Autowired;
import unipar.integrador.mywallet.application.entities.CategoriaPadraoEntity;
import unipar.integrador.mywallet.application.entities.SubcategoriaPadraoEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.infrastructure.repository.CategoriaPadraoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class SubcategoriaLazer {

    @Autowired
    private CategoriaPadraoRepository categoriaPadraoRepository;

    public List<SubcategoriaPadraoEntity> getSubcategorias() {

        CategoriaPadraoEntity categoria = categoriaPadraoRepository.findById(UUID.fromString("AD2D5829-165F-4567-80C1-373C92B716A1"))
                .orElseThrow(() -> new IllegalArgumentException("Categoria Lazer não encontrada."));

        List<SubcategoriaPadraoEntity> subcategorias = Arrays.asList(
                new SubcategoriaPadraoEntity(UUID.fromString("0c3e17d4-2f3b-4f8f-b5f4-3747b86f45ee"), categoria, "Cinema", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("d1a1f77f-5127-43ef-8f9a-bf3282fb7b36"), categoria, "Viagens", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("733c5c61-e6f2-46ba-92c5-d68f7e5bfb71"), categoria, "Shows e Eventos", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("6dfc7c36-68ea-4a3c-b6d7-7fbd7f510432"), categoria, "Jogos e Entretenimento", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("8c4c68c9-9955-4b72-a9d5-6c6e58d9231e"), categoria, "Passeios ao Ar Livre", StatusRegistroEnum.ATIVO)
        );

        return subcategorias;
    }

}
