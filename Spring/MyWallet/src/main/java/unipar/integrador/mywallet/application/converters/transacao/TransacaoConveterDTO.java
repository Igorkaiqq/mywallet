package unipar.integrador.mywallet.application.converters.transacao;

import unipar.integrador.mywallet.application.dto.transacao.TransacaoDTO;
import unipar.integrador.mywallet.application.entities.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class TransacaoConveterDTO {

    public static TransacaoEntity toEntity(TransacaoDTO dto) {
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
        transacao.setData(LocalDate.now());
        transacao.setDescricao(dto.descricao());
        transacao.setStatusRegistro(StatusRegistroEnum.ATIVO);

        return transacao;
    }

}
