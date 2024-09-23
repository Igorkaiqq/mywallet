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
public class SubcategoriaAlimentacao {

    @Autowired
    private CategoriaPadraoRepository categoriaPadraoRepository;

    public List<SubcategoriaPadraoEntity> getSubcategorias() {

        CategoriaPadraoEntity categoria = categoriaPadraoRepository.findById(UUID.fromString("28C4411E-24EB-4528-B649-293225E8BBE9"))
                .orElseThrow(() -> new IllegalArgumentException("Categoria Alimentação não encontrada."));

        List<SubcategoriaPadraoEntity> subcategorias = Arrays.asList(
                new SubcategoriaPadraoEntity(UUID.fromString("c01a5d2b-4a30-44b5-baa7-564f0b9f445e"), categoria, "Supermercado", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("7202c87a-9475-4e97-a69f-b10e9b8f4d92"), categoria, "Restaurante", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("e3be3d6f-5c02-4536-b5fd-9a92d33bdb7b"), categoria, "Lanches", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("11a56b97-b12d-451e-b5d1-6da5b7e4389e"), categoria, "Compras Online de Comida", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("02e414fa-c7b3-4516-b1a2-48b914e0c2a1"), categoria, "Refeição em Viagem", StatusRegistroEnum.ATIVO)
        );

        return subcategorias;
    }

}
