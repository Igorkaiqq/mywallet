using Entity.Entidades;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Infra.Configuration;

public class SubcategoriaPadraoConfiguration : IEntityTypeConfiguration<SubcategoriaPadrao>
{
    public void Configure(EntityTypeBuilder<SubcategoriaPadrao> builder)
    {
        builder.ToTable(nameof(SubcategoriaPadrao));
        builder.HasKey(x => x.Id);
        builder.Property(x => x.Nome).IsRequired().HasMaxLength(70);
        builder.Property(x => x.Codigo).IsRequired().ValueGeneratedOnAdd();
        builder.HasOne(x => x.CategoriaPadrao)
            .WithMany()
            .HasForeignKey(x => x.CategoriaId);
    }
}