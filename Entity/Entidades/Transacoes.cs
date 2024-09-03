using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Entity.Enums;

namespace Entity.Entidades
{
    public class Transacoes : Base
    {

        [ForeignKey("Usuario")]
        [Column(Order = 3)]
        public Guid UsuarioId { get; set; }

        public virtual Usuarios Usuario { get; set; }


        [ForeignKey("Categoria")]
        [Column(Order = 4)]
        public Guid CategoriaId { get; set; }
        public virtual CategoriaUsuario CategoriaUsuario { get; set; }


        [ForeignKey("Subcategoria")]
        [Column(Order = 5)]
        public Guid SubcategoriaId { get; set; }
        public virtual SubcategoriaUsuario SubcategoriaUsuario { get; set; }


        [ForeignKey("TipoTransacao")]
        [Column(Order = 6)]
        public Guid TipoTransacaoId { get; set; }
        public virtual TipoTransacao TipoTransacao { get; set; }


        [ForeignKey("MetodoPagamento")]
        [Column(Order = 7)]
        public Guid MetodoPagamentoId { get; set; }
        public virtual MetodoPagamento MetodoPagamento { get; set; }

        public double Valor { get; set; }
        public DateTime DataTransacao { get; set; }
        public string Observacoes { get; set; }
    }

}
