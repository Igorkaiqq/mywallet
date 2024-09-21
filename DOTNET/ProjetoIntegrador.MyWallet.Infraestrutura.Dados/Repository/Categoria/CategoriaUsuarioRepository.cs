using Domain.Interfaces.ICategoria;
using Entity.Entidades;
using Entity.Enums;
using Microsoft.EntityFrameworkCore;
using DbContext = Infra.Configuration.DbContext;

namespace Infra.Repository.Categoria;

public class CategoriaUsuarioRepository : CategoriaUsuarioInterface
{

    private readonly DbContext _dbContext;

    public CategoriaUsuarioRepository(DbContext dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task Add(CategoriaUsuario entity)
    {
        await _dbContext.AddAsync(entity);
        await _dbContext.SaveChangesAsync();
    }

    public async Task Update(CategoriaUsuario entity)
    {
        _dbContext.Update(entity);
        await _dbContext.SaveChangesAsync();
    }

    public async Task Delete(CategoriaUsuario entity)
    {
        _dbContext.Remove(entity);
        await _dbContext.SaveChangesAsync();
    }

    public async Task<CategoriaUsuario> GetById(Guid id)
    {
        return await _dbContext.CategoriaUsuario.FindAsync(id);
    }

    public async Task<List<CategoriaUsuario>> List()
    {
        return await EntityFrameworkQueryableExtensions.ToListAsync(_dbContext.Set<CategoriaUsuario>());
    }

    public async Task<List<CategoriaUsuario>> ListarCategoriasUsuario(Guid idUsuario)
    {
        return await _dbContext.CategoriaUsuario
            .Where(c => c.UsuarioId == idUsuario && c.StatusRegistro == true)
            .ToListAsync();
    }

    public async Task<List<CategoriaUsuario>> ListarCategoriaPorTipoTransacao(Guid idUsuario, TipoTransacaoEnum tipoTransacao)
    {
        return await _dbContext.CategoriaUsuario
            .Where(c => c.UsuarioId == idUsuario && c.TipoTransacao.Equals(tipoTransacao) && c.StatusRegistro == true)
            .ToListAsync();
    }


    public async Task DesativarCategoriaUsuario(Guid idCategoria, Guid idUsuario)
    {

        var categoriaUsuario = await _dbContext.CategoriaUsuario
            .FirstOrDefaultAsync(c => c.Id == idCategoria && c.UsuarioId == idUsuario);

        if (categoriaUsuario != null)
        {

            categoriaUsuario.StatusRegistro = false;

            await _dbContext.SaveChangesAsync();
        }
        else
        {

            throw new Exception("Categoria do usuário não encontrada.");
        }
    }

}