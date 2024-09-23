package unipar.integrador.mywallet.application.configuration.subcategoria.receita;

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
public class SubcategoriaOutros {

    @Autowired
    private CategoriaPadraoRepository categoriaPadraoRepository;

    public List<SubcategoriaPadraoEntity> getSubcategorias() {

        CategoriaPadraoEntity categoria = categoriaPadraoRepository.findById(UUID.fromString("70EC698F-B733-447D-933B-C6B11A67F3E1"))
                .orElseThrow(() -> new IllegalArgumentException("Categoria Outros não encontrada."));

        List<SubcategoriaPadraoEntity> subcategorias = Arrays.asList(
                new SubcategoriaPadraoEntity(UUID.fromString("506A1065-555F-4815-A72C-256D5EAC8515"), categoria, "Freelancer", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("43A05849-5B2C-4F7D-8B86-1C833F6F7AB6"), categoria, "Aluguel", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("6AC5F845-6CE4-4E44-B9AA-44457706CC9A"), categoria, "Presentes", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("9277BA86-3026-4AB6-B928-BECDD4D9F54A"), categoria, "Vendas de Itens", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("8E0B85EB-5CAE-472B-BAC7-BDE778E7B611"), categoria, "Prêmios", StatusRegistroEnum.ATIVO)
        );

        return subcategorias;
    }

}
