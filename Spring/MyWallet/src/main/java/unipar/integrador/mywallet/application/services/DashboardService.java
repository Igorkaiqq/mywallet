package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.dto.dashboard.BancosDTO;
import unipar.integrador.mywallet.application.dto.dashboard.ReceitasDTO;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoUsuarioDTO;
import unipar.integrador.mywallet.application.entities.TipoTransacaoEntity;
import unipar.integrador.mywallet.application.entities.TransacaoEntity;
import unipar.integrador.mywallet.application.enums.TipoTransacaoEnum;
import unipar.integrador.mywallet.application.interfaces.IDashboard;
import unipar.integrador.mywallet.infrastructure.repository.ContaBancariaRepository;
import unipar.integrador.mywallet.infrastructure.repository.TipoTransacaoRepository;
import unipar.integrador.mywallet.infrastructure.repository.TransacaoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DashboardService implements IDashboard {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private TipoTransacaoRepository tipoTransacaoRepository;

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    public UUID getUsuarioAutenticadoId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return UUID.fromString(authentication.getName());
    }

    @Override
    public ReceitasDTO findResumo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        UUID usuarioId = getUsuarioAutenticadoId();

        TipoTransacaoEntity receita = tipoTransacaoRepository.findByTipoTransacaoEnum(TipoTransacaoEnum.RECEITA);
        TipoTransacaoEntity despesa = tipoTransacaoRepository.findByTipoTransacaoEnum(TipoTransacaoEnum.DESPESA);

        List<TransacaoEntity> receitas = transacaoRepository.
                findByUsuarioIdAndTipoTransacao_IdAndDataBetween(usuarioId, receita.getId(), dataInicio, dataFim);
        List<TransacaoEntity> despesas = transacaoRepository.
                findByUsuarioIdAndTipoTransacao_IdAndDataBetween(usuarioId, despesa.getId(), dataInicio, dataFim);

        double totalReceitas = receitas.stream().mapToDouble(TransacaoEntity::getValor).sum();
        double totalDespesas = despesas.stream().mapToDouble(TransacaoEntity::getValor).sum();

        double saldo = totalReceitas - totalDespesas;
        return new ReceitasDTO(totalReceitas, totalDespesas, saldo);
    }

    @Override
    public List<BancosDTO> findBancos() {
        return contaBancariaRepository.findTop4ByUsuarioIdOrderBySaldoDesc(getUsuarioAutenticadoId())
                .stream()
                .map(contaBancaria -> new BancosDTO(
                        contaBancaria.getId(),
                        contaBancaria.getNome(),
                        contaBancaria.getSaldo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransacaoUsuarioDTO> findMaioresReceitas(LocalDateTime dataInicio, LocalDateTime dataFim) {

        UUID usuarioId = getUsuarioAutenticadoId();
        TipoTransacaoEntity receita = tipoTransacaoRepository.findByTipoTransacaoEnum(TipoTransacaoEnum.RECEITA);
        List<TransacaoEntity> transacoes = transacaoRepository.
                findTop5ByUsuarioIdAndTipoTransacao_IdAndDataBetweenOrderByValorDesc(usuarioId, receita.getId(), dataInicio, dataFim);
        
        return transacoes.stream()
                .map(transacao -> new TransacaoUsuarioDTO(
                        transacao.getId(),
                        transacao.getData(),
                        transacao.getTipoTransacao().getTipoTransacaoEnum().name(),
                        transacao.getCategoriaUsuario().getNome(),
                        transacao.getSubcategoriaUsuario().getNome(),
                        transacao.getDescricao(),
                        transacao.getValor()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransacaoUsuarioDTO> findMaioresDespesas(LocalDateTime dataInicio, LocalDateTime dataFim) {

        TipoTransacaoEntity despesa = tipoTransacaoRepository.findByTipoTransacaoEnum(TipoTransacaoEnum.DESPESA);

        UUID usuarioId = getUsuarioAutenticadoId();
        List<TransacaoEntity> transacoes = transacaoRepository.
                findTop5ByUsuarioIdAndTipoTransacao_IdAndDataBetweenOrderByValorDesc(usuarioId, despesa.getId(), dataInicio, dataFim);

        return transacoes.stream()
                .map(transacao -> new TransacaoUsuarioDTO(
                        transacao.getId(),
                        transacao.getData(),
                        transacao.getTipoTransacao().getTipoTransacaoEnum().name(),
                        transacao.getCategoriaUsuario().getNome(),
                        transacao.getSubcategoriaUsuario().getNome(),
                        transacao.getDescricao(),
                        transacao.getValor()))
                .collect(Collectors.toList());
    }
}
