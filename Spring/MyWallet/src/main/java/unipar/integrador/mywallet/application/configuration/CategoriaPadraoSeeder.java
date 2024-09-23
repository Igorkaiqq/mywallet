package unipar.integrador.mywallet.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import unipar.integrador.mywallet.application.configuration.categoria.CategoriaDespesa;
import unipar.integrador.mywallet.application.configuration.categoria.CategoriaReceita;
import unipar.integrador.mywallet.application.entities.CategoriaPadraoEntity;
import unipar.integrador.mywallet.application.entities.TipoTransacaoEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.infrastructure.repository.CategoriaPadraoRepository;
import unipar.integrador.mywallet.infrastructure.repository.TipoTransacaoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Order(3)
@Component
public class CategoriaPadraoSeeder implements CommandLineRunner {

    @Autowired
    CategoriaPadraoRepository categoriaPadraoRepository;

    @Autowired
    TipoTransacaoRepository tipoTransacaoRepository;

    @Autowired
    CategoriaReceita categoriaReceita;

    @Autowired
    CategoriaDespesa categoriaDespesa;

    @Override
    public void run(String... args) throws Exception {

        processarCategorias(categoriaReceita.getCategoriasReceitaPadrao());
        processarCategorias(categoriaDespesa.getCategoriasDespesaPadrao());

    }

    private void processarCategorias(List<CategoriaPadraoEntity> categorias) {
        for (CategoriaPadraoEntity categoria : categorias) {
            Optional<CategoriaPadraoEntity> categoriaExistente = categoriaPadraoRepository.findById(categoria.getId());

            if (categoriaExistente.isPresent()) {
                CategoriaPadraoEntity existente = categoriaExistente.get();
                if (   !existente.getNome().equals(categoria.getNome()) ||
                        existente.getTipoTransacao().getId().equals(categoria.getTipoTransacao().getId()) ||
                        existente.getStatusRegistro() != categoria.getStatusRegistro()
                ){
                    existente.setNome(categoria.getNome());
                    existente.setTipoTransacao(categoria.getTipoTransacao());
                    existente.setStatusRegistro(categoria.getStatusRegistro());
                    categoriaPadraoRepository.save(existente);
                    System.out.println("Categoria atualizada: " + categoria.getNome());
                }
            } else {
                categoriaPadraoRepository.save(categoria);
                System.out.println("Categoria inserida: " + categoria.getNome());
            }
        }
    }


}
