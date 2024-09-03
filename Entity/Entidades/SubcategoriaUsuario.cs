using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entity.Entidades
{
    public class SubcategoriaUsuario : Base
    {

        [ForeignKey("CategoriaUsuario")]
        [Column(Order = 3)]
        public Guid CategoriaId { get; set; }
        public virtual CategoriaUsuario CategoriaUsuario { get; set; }

        public string Nome { get; set; }

    }
}
