package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.converters.transacao.TransacaoConveterDTO;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoDTO;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoUsuarioDTO;
import unipar.integrador.mywallet.application.entities.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.enums.TipoTransacaoEnum;
import unipar.integrador.mywallet.application.exception.EntityNotFoundException;
import unipar.integrador.mywallet.application.interfaces.ITransacao;
import unipar.integrador.mywallet.infrastructure.repository.TransacaoRepository;

import java.util.*;

@Service
public class TransacaoService implements ITransacao {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    ContaBancariaService contaBancariaService;

    @Autowired
    CaixaService caixaService;

    @Autowired
    TipoTransacaoService tipoTransacaoService;

    @Override
    public TransacaoEntity save(TransacaoDTO dto) {

        TransacaoEntity transacao = TransacaoConveterDTO.toEntity(dto);

        ContaBancariaEntity contaBancaria = contaBancariaService.findById(dto.contaBancariaId())
                .orElseThrow(() -> new EntityNotFoundException("Conta Bancária não encontrada."));

        CaixaEntity caixa = caixaService.findById(dto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Caixa não encontrado para o usuário."));

        double valorTransacao = dto.valor();
        TipoTransacaoEntity tipoTransacao = tipoTransacaoService.findById(dto.tipoTransacaoId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de Transação não encontrado."));

        if (tipoTransacao.getTipoTransacaoEnum() == TipoTransacaoEnum.RECEITA) {
            contaBancaria.setSaldo(contaBancaria.getSaldo() + valorTransacao);
            caixa.setSaldoTotal(caixa.getSaldoTotal() + valorTransacao);
        } else if (tipoTransacao.getTipoTransacaoEnum() == TipoTransacaoEnum.DESPESA) {
            contaBancaria.setSaldo(contaBancaria.getSaldo() - valorTransacao);
            caixa.setSaldoTotal(caixa.getSaldoTotal() - valorTransacao);
        }

        contaBancariaService.update(contaBancaria);
        caixaService.update(caixa);
        return transacaoRepository.save(transacao);
    }

    @Override
    public Optional<TransacaoEntity> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<TransacaoEntity> findAll() {
        return transacaoRepository.findAll();
    }

    @Override
    public TransacaoEntity update(UUID id, TransacaoDTO dto) {
        TransacaoEntity existingTransacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transação com ID " + id + " não encontrada."));

        existingTransacao.setValor(dto.valor());
        existingTransacao.setDescricao(dto.descricao());

        return transacaoRepository.save(existingTransacao);
    }

    @Override
    public void deleteById(UUID id) {

        TransacaoEntity transacaoEntity = transacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transação não encontrada."));
        transacaoEntity.setStatusRegistro(StatusRegistroEnum.DELETADO);
        transacaoRepository.save(transacaoEntity);
    }

    @Override
    public List<TransacaoUsuarioDTO> findByUsuarioId(UUID id) {

        return transacaoRepository.findByUsuario_Id(id).stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public List<TransacaoEntity> findByusuarioIdAndCategoriaId(UUID usuarioId, UUID categoriaId) {
        return List.of();
    }


    public TransacaoUsuarioDTO convertToDto(TransacaoEntity transacao) {
        return new TransacaoUsuarioDTO(
                transacao.getId(),
                transacao.getData(),
                transacao.getTipoTransacao().getTipoTransacaoEnum().name(),
                transacao.getCategoriaUsuario().getNome(),
                transacao.getSubcategoriaUsuario().getNome(),
                transacao.getDescricao(),
                transacao.getValor()
        );
    }

}
