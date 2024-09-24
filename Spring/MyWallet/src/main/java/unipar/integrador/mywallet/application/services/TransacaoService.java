package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoDTO;
import unipar.integrador.mywallet.application.entities.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
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

        TransacaoEntity transacao = new TransacaoEntity();
        transacao.setId(UUID.randomUUID());

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(dto.usuarioId());
        transacao.setUsuario(usuario);

        TipoTransacaoEntity tipoTransacao = new TipoTransacaoEntity();
        tipoTransacao.setId(dto.tipoTransacaoId());
        transacao.setTipoTransacao(tipoTransacao);

        CategoriaUsuarioEntity categoriaUsuario = new CategoriaUsuarioEntity();
        categoriaUsuario.setId(dto.categoriaId());
        transacao.setCategoriaUsuario(categoriaUsuario);

        SubcategoriaUsuarioEntity subcategoriaUsuario = new SubcategoriaUsuarioEntity();
        subcategoriaUsuario.setId(dto.subcategoriaId());
        transacao.setSubcategoriaUsuario(subcategoriaUsuario);

        MetodoPagamentoEntity metodoPagamento = new MetodoPagamentoEntity();
        metodoPagamento.setId(dto.metodoPagamentoID());
        transacao.setMetodoPagamento(metodoPagamento);

        transacao.setValor(dto.valor());
        transacao.setData(new Date());
        transacao.setDescricao(dto.descricao());
        transacao.setStatusRegistro(StatusRegistroEnum.ATIVO);


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
    public TransacaoEntity update(TransacaoEntity transacao) {;
        return transacaoRepository.save(transacao);
    }

    @Override
    public void deleteById(UUID id) {

        TransacaoEntity transacaoEntity = transacaoRepository.findById(id).orElseThrow();
        transacaoEntity.setStatusRegistro(StatusRegistroEnum.DELETADO);
        transacaoRepository.save(transacaoEntity);
    }
}
