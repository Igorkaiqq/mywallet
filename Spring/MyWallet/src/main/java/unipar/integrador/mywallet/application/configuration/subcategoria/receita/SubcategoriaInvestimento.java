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
public class SubcategoriaInvestimento {

    @Autowired
    private CategoriaPadraoRepository categoriaPadraoRepository;

    public List<SubcategoriaPadraoEntity> getSubcategorias() {

        CategoriaPadraoEntity categoria = categoriaPadraoRepository.findById(UUID.fromString("9FC9EEAF-DB22-4BC7-A896-1B12F8E7A597"))
                .orElseThrow(() -> new IllegalArgumentException("Categoria Investimento não encontrada."));

        List<SubcategoriaPadraoEntity> subcategorias = Arrays.asList(
                new SubcategoriaPadraoEntity(UUID.fromString("AFA70C7B-9CDC-439B-885C-CA914B22B068"), categoria, "Ações", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("F2113F24-BCC3-4193-A56F-5F8DD1FA8AB1"), categoria, "Fundos Imobiliários", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("DB018A5B-6062-4F32-841A-D40EE689ED3B"), categoria, "Renda Fixa", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("C963936E-8073-4D4A-868C-22035F00C59E"), categoria, "Criptomoedas", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("A8C7664C-C764-4300-9F59-B9BFCD94DAFE"), categoria, "Tesouro Direto", StatusRegistroEnum.ATIVO)
        );

        return subcategorias;
    }

}
