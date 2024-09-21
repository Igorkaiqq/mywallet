using Entity.Entidades;

namespace Domain.Interfaces.ISubcategoria;

public interface SubcategoriaUsuarioInterface : Generics.InterfaceGeneric<SubcategoriaUsuario>
{

    Task<List<SubcategoriaUsuario>> ListarSubcategoriasUsuario(Guid idUsuario);
    Task<List<SubcategoriaUsuario>> ListarSubcategoriaPorCategoria(Guid idUsuario, Guid idCategoria);
    Task<List<SubcategoriaUsuario>> ListarSubcategoriaPorTipoTransacao(Guid idUsuario, int tipoTransacao);

}