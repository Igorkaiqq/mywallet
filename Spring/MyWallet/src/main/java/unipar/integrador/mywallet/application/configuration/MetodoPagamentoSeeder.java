package unipar.integrador.mywallet.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import unipar.integrador.mywallet.application.configuration.metodoPagamento.MetodoPagamento;
import unipar.integrador.mywallet.application.entities.MetodoPagamentoEntity;
import unipar.integrador.mywallet.infrastructure.repository.MetodoPagamentoRepository;

import java.util.List;
import java.util.Optional;

@Order(2)
@Component
public class MetodoPagamentoSeeder implements CommandLineRunner {

    @Autowired
    private MetodoPagamentoRepository metodoPagamentoRepository;

    @Autowired
    private MetodoPagamento metodoPagamento;

    @Override
    public void run(String... args) throws Exception {
        processarMetodoPagamento(metodoPagamento.getMetodosPagamento());
    }

    public void processarMetodoPagamento(List<MetodoPagamentoEntity> metodosPagamento) {
        for (MetodoPagamentoEntity metodoPagamento : metodosPagamento) {
            Optional<MetodoPagamentoEntity> metodoPagamentoExistente = metodoPagamentoRepository.findById(metodoPagamento.getId());

            if (metodoPagamentoExistente.isPresent()) {
                MetodoPagamentoEntity existente = metodoPagamentoExistente.get();
                if (   !existente.getMetodoPagamento().name().equals(metodoPagamento.metodoPagamento.name()) ||
                        existente.getStatusRegistro() != metodoPagamento.getStatusRegistro()
                ){
                    existente.setMetodoPagamento(metodoPagamento.getMetodoPagamento());
                    existente.setStatusRegistro(metodoPagamento.getStatusRegistro());
                    metodoPagamentoRepository.save(existente);
                    System.out.println("MetodoPagamento atualizado: " + metodoPagamento.getMetodoPagamento());
                }
            } else {
                metodoPagamentoRepository.save(metodoPagamento);
                System.out.println("MetodoPagamento inserido: " + metodoPagamento.getMetodoPagamento());
            }
        }
    }

}
