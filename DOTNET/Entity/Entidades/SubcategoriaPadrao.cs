using System.ComponentModel.DataAnnotations.Schema;

namespace Entity.Entidades;

public class SubcategoriaPadrao : Base
{
    [ForeignKey("categoriaPadrao")]
    [Column(Order = 3)]
    public Guid CategoriaId { get; set; }
    public virtual CategoriaPadrao CategoriaPadrao { get; set; }

    public string Nome { get; set; }

}