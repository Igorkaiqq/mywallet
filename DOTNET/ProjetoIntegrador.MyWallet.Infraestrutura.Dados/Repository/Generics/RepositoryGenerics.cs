using Domain.Interfaces.Generics;
using Infra.Configuration;
using Microsoft.EntityFrameworkCore;
using DbContext = Infra.Configuration.DbContext;

namespace Infra.Repository.Generics;

public class RepositoryGenerics<T> : InterfaceGeneric<T> where T : class
{

    private readonly DbContextOptions<DbContext> _optionsBuilder;

    public RepositoryGenerics()
    {
        _optionsBuilder = new DbContextOptions<DbContext>();
    }

    public async Task Add(T entity)
    {
        using (var data = new DbContext(_optionsBuilder))
        {
            await data.Set<T>().AddAsync(entity);
            await data.SaveChangesAsync();
        }
    }

    public async Task Update(T entity)
    {
        using (var data = new DbContext(_optionsBuilder))
        {
            data.Set<T>().Update(entity);
            await data.SaveChangesAsync();
        }
    }

    public async Task Delete(T entity)
    {
        using (var data = new DbContext(_optionsBuilder))
        {
            data.Set<T>().Remove(entity);
            await data.SaveChangesAsync();
        }
    }

    public async Task<T> GetById(Guid id)
    {
        using (var data = new DbContext(_optionsBuilder))
        {
            return await data.Set<T>().FindAsync(id);

        }
    }

    public async Task<List<T>> List()
    {
        using (var data = new DbContext(_optionsBuilder))
        {
             return await data.Set<T>().ToListAsync();

        }
    }
}