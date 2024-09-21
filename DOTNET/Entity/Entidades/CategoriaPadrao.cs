using System.ComponentModel.DataAnnotations.Schema;

namespace Entity.Entidades;

public class CategoriaPadrao : Base
{
    [ForeignKey("TipoTransacao")]
    [Column(Order = 3)]
    public Guid TipoTransacaoId { get; set; }
    public virtual TipoTransacao TipoTransacao { get; set; }

    public string Nome { get; set; }
}