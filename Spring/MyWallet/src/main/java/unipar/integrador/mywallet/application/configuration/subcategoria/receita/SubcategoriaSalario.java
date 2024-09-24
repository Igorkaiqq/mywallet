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
public class SubcategoriaSalario {

    @Autowired
    private CategoriaPadraoRepository categoriaPadraoRepository;

    public List<SubcategoriaPadraoEntity> getSubcategorias() {

        CategoriaPadraoEntity categoria = categoriaPadraoRepository.findById(UUID.fromString("36C95388-D1EB-418A-8149-D427D7499CE8"))
                .orElseThrow(() -> new IllegalArgumentException("Categoria Salário não encontrada."));

        List<SubcategoriaPadraoEntity> subcategorias = Arrays.asList(
                new SubcategoriaPadraoEntity(UUID.fromString("E1441D64-D6DE-4097-A976-8CA439C05EDA"), categoria, "Salário Base", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("0F9EA058-B0BB-42A3-91F9-A8879E0D9B0D"), categoria, "13º Salário", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("36F45674-7BC0-487A-A839-A6CD088BE63B"), categoria, "Bonificações", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("757CE2A1-61E2-4C6A-896A-953759987FEC"), categoria, "Hora Extra", StatusRegistroEnum.ATIVO),
                new SubcategoriaPadraoEntity(UUID.fromString("BE5F1142-32F4-44B1-B3A3-6BBDD3DCEF7D"), categoria, "Comissões", StatusRegistroEnum.ATIVO)
        );

        return subcategorias;
    }

}
