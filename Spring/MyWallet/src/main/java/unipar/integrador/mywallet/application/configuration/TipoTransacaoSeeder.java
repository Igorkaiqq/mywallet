package unipar.integrador.mywallet.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import unipar.integrador.mywallet.application.configuration.tipoTransacao.TipoTransacao;
import unipar.integrador.mywallet.application.entities.TipoTransacaoEntity;
import unipar.integrador.mywallet.infrastructure.repository.TipoTransacaoRepository;

import java.util.List;
import java.util.Optional;

@Order(1)
@Component
public class TipoTransacaoSeeder implements CommandLineRunner {

    @Autowired
    private TipoTransacaoRepository tipoTransacaoRepository;

    @Autowired
    TipoTransacao tipoTransacao;

    @Override
    public void run(String... args) throws Exception {

        processarTipoTransacao(tipoTransacao.getTiposTransacao());

    }

    public void processarTipoTransacao(List<TipoTransacaoEntity> tiposTransacao){

        for (TipoTransacaoEntity tipoTransacao : tiposTransacao) {
            Optional<TipoTransacaoEntity> tipoExistente = tipoTransacaoRepository.findById(tipoTransacao.getId());
            if (tipoExistente.isPresent()) {
                TipoTransacaoEntity existente = tipoExistente.get();
                if (!existente.getTipoTransacaoEnum().equals(tipoTransacao.getTipoTransacaoEnum()) ||
                        existente.getStatusRegistro() != tipoTransacao.getStatusRegistro()) {
                    existente.setTipoTransacaoEnum(tipoTransacao.getTipoTransacaoEnum());
                    existente.setStatusRegistro(tipoTransacao.getStatusRegistro());
                    tipoTransacaoRepository.save(existente);
                    System.out.println("Tipo de transação atualizado: " + tipoTransacao.getTipoTransacaoEnum());
                }
            } else {
                tipoTransacaoRepository.save(tipoTransacao);
                System.out.println("Tipo de transação inserido: " + tipoTransacao.getTipoTransacaoEnum());
            }

        }

    }

}
