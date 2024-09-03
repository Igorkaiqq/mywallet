using Domain.Interfaces.IUsuarios;
using Microsoft.EntityFrameworkCore;
using DbContext = Infra.Configuration.DbContext;

namespace Infra.Repository.Usuarios;

public class UsuariosRepository : UsuariosInterface
{

    private readonly DbContext _dbContext;

    public UsuariosRepository(DbContext dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task Add(Entity.Entidades.Usuarios entity)
    {
        await _dbContext.AddAsync(entity);
        await _dbContext.SaveChangesAsync();
    }

    public async Task Update(Entity.Entidades.Usuarios entity)
    {
        _dbContext.Update(entity);
        await _dbContext.SaveChangesAsync();
    }

    public async Task Delete(Entity.Entidades.Usuarios entity)
    {
        _dbContext.Remove(entity);
        await _dbContext.SaveChangesAsync();
    }

    public async Task<Entity.Entidades.Usuarios> GetById(Guid id)
    {
        return await _dbContext.Usuarios.FindAsync(id);
    }

    public async  Task<List<Entity.Entidades.Usuarios>> List()
    {
        return await EntityFrameworkQueryableExtensions.ToListAsync(_dbContext.Set<Entity.Entidades.Usuarios>());
    }
}