package unipar.integrador.mywallet.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import unipar.integrador.mywallet.application.entities.TipoTransacaoEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.enums.TipoTransacaoEnum;
import unipar.integrador.mywallet.infrastructure.repository.TipoTransacaoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TipoTransacaoSeeder implements CommandLineRunner {

    @Autowired
    private TipoTransacaoRepository tipoTransacaoRepository;

    @Override
    public void run(String... args) throws Exception {

        List<TipoTransacaoEntity> tiposTransacao = Arrays.asList(
                new TipoTransacaoEntity(UUID.fromString("2B8285B8-97AD-4727-AF3A-E39D2ECE52E7"), TipoTransacaoEnum.RECEITA, StatusRegistroEnum.ATIVO),
                new TipoTransacaoEntity(UUID.fromString("694E9A75-5115-45C3-B5A9-BDD06D26A5C0"), TipoTransacaoEnum.DESPESA, StatusRegistroEnum.ATIVO));

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
