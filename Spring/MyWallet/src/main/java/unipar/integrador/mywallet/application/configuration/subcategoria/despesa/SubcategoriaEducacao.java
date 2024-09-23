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
public class SubcategoriaEducacao {

    @Autowired
    private CategoriaPadraoRepository categoriaPadraoRepository;

    public List<SubcategoriaPadraoEntity> getSubcategorias() {

        CategoriaPadraoEntity categoria = categoriaPadraoRepository.findById(UUID.fromString("C407FAEA-1FFE-44AC-8502-8EEC05DE9DA7"))
                .orElseThrow(() -> new IllegalArgumentException("Categoria Educação não encontrada."));

        List<SubcategoriaPadraoEntity> subcategorias = Arrays.asList(
                new SubcategoriaPadraoEntity(UUID.fromString("30d526fa-44f3-4c70-92f8-5f30949363f8"), categoria, "Cursos Online", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("0f39af22-03d4-4b5d-8bc7-5f42a50e8b11"), categoria, "Material Escolar", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("6f1be103-7d91-4fd6-a582-cfbfa14c6e08"), categoria, "Livros", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("8d17c0f9-4b30-4d53-b729-582b8457298e"), categoria, "Mensalidade Escolar/Universitária", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("0a62a36a-bb08-4e7c-b46d-c5e96d5d8d64"), categoria, "Aulas Particulares", StatusRegistroEnum.ATIVO)
        );

        return subcategorias;
    }

}
