package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.converters.transacao.TransacaoConveterDTO;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoDTO;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoUsuarioDTO;
import unipar.integrador.mywallet.application.entities.ContaBancariaEntity;
import unipar.integrador.mywallet.application.entities.TipoTransacaoEntity;
import unipar.integrador.mywallet.application.entities.TransacaoEntity;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.enums.TipoTransacaoEnum;
import unipar.integrador.mywallet.application.exception.EntityNotFoundException;
import unipar.integrador.mywallet.application.interfaces.ITransacao;
import unipar.integrador.mywallet.infrastructure.repository.TransacaoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    @Autowired
    private UsuarioService usuarioService;

    public UUID getUsuarioAutenticadoId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return UUID.fromString(authentication.getName());
    }

    @Override
    public TransacaoEntity save(TransacaoDTO dto) {

        UUID usuarioId = getUsuarioAutenticadoId();

        TransacaoEntity transacao = TransacaoConveterDTO.toEntity(dto);

        UsuarioEntity usuario = usuarioService.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        transacao.setUsuario(usuario);

        ContaBancariaEntity contaBancaria = contaBancariaService.findById(dto.contaBancariaId())
                .orElseThrow(() -> new EntityNotFoundException("Conta Bancária não encontrada."));

        transacao.setContaBancaria(contaBancaria);

        double valorTransacao = dto.valor();

        TipoTransacaoEntity tipoTransacao = tipoTransacaoService.findById(dto.tipoTransacaoId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de Transação não encontrado."));

        if (tipoTransacao.getTipoTransacaoEnum() == TipoTransacaoEnum.RECEITA) {
            contaBancaria.setSaldo(contaBancaria.getSaldo() + valorTransacao);
        } else if (tipoTransacao.getTipoTransacaoEnum() == TipoTransacaoEnum.DESPESA) {
            contaBancaria.setSaldo(contaBancaria.getSaldo() - valorTransacao);
        }



        contaBancariaService.update(contaBancaria);
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
    public List<TransacaoUsuarioDTO> findByUsuarioId() {

        UUID usuarioId = getUsuarioAutenticadoId();

        return transacaoRepository.findByUsuario_IdOrderByDataDesc(usuarioId).stream()
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
