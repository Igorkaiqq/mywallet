package unipar.integrador.mywallet.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import unipar.integrador.mywallet.application.configuration.subcategoria.despesa.*;
import unipar.integrador.mywallet.application.configuration.subcategoria.receita.SubcategoriaInvestimento;
import unipar.integrador.mywallet.application.configuration.subcategoria.receita.SubcategoriaOutros;
import unipar.integrador.mywallet.application.configuration.subcategoria.receita.SubcategoriaSalario;
import unipar.integrador.mywallet.application.entities.CategoriaPadraoEntity;
import unipar.integrador.mywallet.application.entities.SubcategoriaPadraoEntity;
import unipar.integrador.mywallet.infrastructure.repository.CategoriaPadraoRepository;
import unipar.integrador.mywallet.infrastructure.repository.SubcategoriaPadraoRepository;

import java.util.List;
import java.util.Optional;

@Component
public class SubcategoriaPadraoSeeder  implements CommandLineRunner {

    @Autowired
    CategoriaPadraoRepository categoriaPadraoRepository;

    @Autowired
    SubcategoriaPadraoRepository subcategoriaPadraoRepository;

    @Autowired
    SubcategoriaSalario subcategoriaSalario;

    @Autowired
    SubcategoriaOutros subcategoriaOutros;

    @Autowired
    SubcategoriaInvestimento subcategoriaInvestimento;
    @Autowired
    SubcategoriaAlimentacao subcategoriaAlimentacao;
    @Autowired
    SubcategoriaEducacao subcategoriaEducacao;
    @Autowired
    SubcategoriaMoradia subcategoriaMoradia;
    @Autowired
    SubcategoriaSaude subcategoriaSaude;
    @Autowired
    SubcategoriaTransporte subcategoriaTransporte;

    public void run(String... args) throws Exception {

        processarSubcategorias(subcategoriaSalario.getSubcategorias());
        processarSubcategorias(subcategoriaOutros.getSubcategorias());
        processarSubcategorias(subcategoriaInvestimento.getSubcategorias());
        processarSubcategorias(subcategoriaAlimentacao.getSubcategorias());
        processarSubcategorias(subcategoriaEducacao.getSubcategorias());
        processarSubcategorias(subcategoriaMoradia.getSubcategorias());
        processarSubcategorias(subcategoriaSaude.getSubcategorias());
        processarSubcategorias(subcategoriaTransporte.getSubcategorias());

    }

    private void processarSubcategorias(List<SubcategoriaPadraoEntity> subcategorias) {
        for (SubcategoriaPadraoEntity subcategoria : subcategorias) {
            Optional<SubcategoriaPadraoEntity> subcategoriaExistente = subcategoriaPadraoRepository.findById(subcategoria.getId());

            if (subcategoriaExistente.isPresent()) {
                SubcategoriaPadraoEntity existente = subcategoriaExistente.get();
                if (!existente.getNome().equals(subcategoria.getNome()) ||
                        existente.getStatusRegistro() != subcategoria.getStatusRegistro()) {
                    existente.setNome(subcategoria.getNome());
                    existente.setStatusRegistro(subcategoria.getStatusRegistro());
                    subcategoriaPadraoRepository.save(existente);
                    System.out.println("Subcategoria atualizada: " + subcategoria.getNome());
                }
            } else {
                subcategoriaPadraoRepository.save(subcategoria);
                System.out.println("Subcategoria inserida: " + subcategoria.getNome());
            }
        }
    }

}
