using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entity.Entidades
{
    public class CategoriaUsuario : Base
    {


        [ForeignKey("Usuario")]
        [Column(Order = 3)]
        public Guid UsuarioId { get; set; }
        public virtual Usuarios Usuario { get; set; }

        [ForeignKey("TipoTransacao")]
        [Column(Order = 4)]
        public Guid TipoTransacaoId { get; set; }
        public virtual TipoTransacao TipoTransacao { get; set; }

        public string Nome { get; set; }

    }
}
