using Domain.Interfaces.IMetodoPagamento;
using Entity.Entidades;
using Microsoft.EntityFrameworkCore;
using DbContext = Infra.Configuration.DbContext;

namespace Infra.Repository.MetodosPagamento;

public class MetodoPagamentoRepository : MetodoPagamentoInterface
{
    private readonly DbContext _dbContext;

    public MetodoPagamentoRepository(DbContext dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task Add(Entity.Entidades.MetodoPagamento entity)
    {
        await _dbContext.Set<MetodoPagamento>().AddAsync(entity);
        await _dbContext.SaveChangesAsync();
    }

    public async Task Update(MetodoPagamento entity)
    {
        _dbContext.Set<MetodoPagamento>().Update(entity);
        await _dbContext.SaveChangesAsync();
    }

    public async Task Delete(MetodoPagamento entity)
    {
        _dbContext.Set<MetodoPagamento>().Remove(entity);
        await _dbContext.SaveChangesAsync();
    }

    public async Task<MetodoPagamento> GetById(Guid id)
    {
        return await _dbContext.Set<MetodoPagamento>().FindAsync(id);
    }

    public async Task<List<MetodoPagamento>> List()
    {
        return await EntityFrameworkQueryableExtensions.ToListAsync(_dbContext.Set<MetodoPagamento>());
    }
}