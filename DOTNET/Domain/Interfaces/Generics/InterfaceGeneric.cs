using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Interfaces.Generics
{
    public interface InterfaceGeneric<T> where T : class
    {

        Task Add(T entity);
        Task Update(T entity);
        Task Delete(T entity);
        Task<T> GetById(Guid id);
        Task<List<T>> List();

    }
}
