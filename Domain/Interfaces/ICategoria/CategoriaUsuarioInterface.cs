using Entity.Entidades;

namespace Domain.Interfaces.ICategoria;

public interface CategoriaUsuarioInterface : Generics.InterfaceGeneric<CategoriaUsuario>
{
    Task<List<CategoriaUsuario>> ListarCategoriasUsuario(Guid idUsuario);
    Task<List<CategoriaUsuario>> ListarCategoriaPorTipoTransacao(Guid idUsuario, int tipoTransacao);
}