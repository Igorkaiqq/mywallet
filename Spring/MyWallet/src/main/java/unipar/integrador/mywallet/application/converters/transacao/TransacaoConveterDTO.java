package unipar.integrador.mywallet.application.converters.transacao;

import unipar.integrador.mywallet.application.dto.transacao.TransacaoDTO;
import unipar.integrador.mywallet.application.entities.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransacaoConveterDTO {

    public static TransacaoEntity toEntity(TransacaoDTO dto) {
        TransacaoEntity transacao = new TransacaoEntity();
        transacao.setId(UUID.randomUUID());

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
        metodoPagamento.setId(dto.metodoPagamentoId());
        transacao.setMetodoPagamento(metodoPagamento);

        transacao.setValor(dto.valor());
        System.out.println("Data do DTO: " + dto.data());
        transacao.setData(dto.data().atStartOfDay());
        System.out.println("Data depois: " + transacao.getData());
        transacao.setDescricao(dto.descricao());
        transacao.setStatusRegistro(StatusRegistroEnum.ATIVO);

        return transacao;
    }

}
