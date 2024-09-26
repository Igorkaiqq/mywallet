package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.converters.transacao.TransacaoConveterDTO;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoDTO;
import unipar.integrador.mywallet.application.entities.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.exception.EntityNotFoundException;
import unipar.integrador.mywallet.application.interfaces.ITransacao;
import unipar.integrador.mywallet.infrastructure.repository.TransacaoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransacaoService implements ITransacao {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Override
    public TransacaoEntity save(TransacaoDTO dto) {

        TransacaoEntity transacao = TransacaoConveterDTO.toEntity(dto);

        return transacaoRepository.save(transacao);
    }

    @Override
    public Optional<TransacaoEntity> findById(UUID id) {
        return transacaoRepository.findById(id);
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
    public List<TransacaoEntity> findByUsuarioId(UUID id) {
        return List.of();
    }

    @Override
    public List<TransacaoEntity> findByusuarioIdAndCategoriaId(UUID usuarioId, UUID categoriaId) {
        return List.of();
    }
}
