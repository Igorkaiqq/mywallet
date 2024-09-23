package unipar.integrador.mywallet.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private CategoriaPadraoSeeder categoriaPadraoSeeder;

    @Autowired
    private TipoTransacaoSeeder tipoTransacaoSeeder;

    @Autowired
    private SubcategoriaPadraoSeeder subcategoriaPadraoSeeder;

    @Autowired
    private MetodoPagamentoSeeder metodoPagamentoSeeder;


    @Override
    public void run(String... args) throws Exception {
        tipoTransacaoSeeder.run(args);
        metodoPagamentoSeeder.run(args);
        categoriaPadraoSeeder.run(args);
        subcategoriaPadraoSeeder.run(args);
    }
}
