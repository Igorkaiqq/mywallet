using Entity.Entidades;
using Entity.Enums;

namespace Domain.Interfaces.ICategoria;

public interface CategoriaUsuarioInterface : Generics.InterfaceGeneric<CategoriaUsuario>
{
    Task<List<CategoriaUsuario>> ListarCategoriasUsuario(Guid idUsuario);
    Task<List<CategoriaUsuario>> ListarCategoriaPorTipoTransacao(Guid idUsuario, TipoTransacaoEnum tipoTransacao);
    Task DesativarCategoriaUsuario(Guid idCategoria, Guid idUsuario);
}