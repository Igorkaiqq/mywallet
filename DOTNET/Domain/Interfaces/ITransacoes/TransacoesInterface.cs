using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Entity.Entidades;

namespace Domain.Interfaces.ITransacoes
{
    public interface TransacoesInterface : Generics.InterfaceGeneric<Transacoes>
    {

        Task<List<Transacoes>> ListarTransacoesPorUsuario(Guid idUsuario);
        Task<List<Transacoes>> ListarTransacoesPorUsuarioECategoria(Guid idUsuario, Guid idCategoria);
        Task<List<Transacoes>> ListarTransacoesPorUsuarioESubcategoria(Guid idUsuario, Guid idSubcategoria);
        Task<List<Transacoes>> ListarTransacoesPorUsuarioEMetodoPagamento(Guid idUsuario, Guid idMetodoPagamento);
        Task<List<Transacoes>> ListarTransacoesPorUsuarioETipoTransacao(Guid idUsuario, Guid idTipoTransacao);
        Task<List<Transacoes>> ListarTransacoesPorUsuarioEData(Guid idUsuario, DateTime data);
        Task<List<Transacoes>> ListarTransacoesPorUsuarioDentroIntervaloDeDatas(Guid idUsuario, DateTime dataInicial, DateTime dataFinal);

    }
}
