using Entity.Entidades;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Infra.Configuration;

public class SubcategoriaUsuarioConfiguration : IEntityTypeConfiguration<SubcategoriaUsuario>
{
    public void Configure(EntityTypeBuilder<SubcategoriaUsuario> builder)
    {
        builder.ToTable(nameof(SubcategoriaUsuario));
        builder.HasKey(x => x.Id);
        builder.Property(x => x.Nome).IsRequired().HasMaxLength(70);
        builder.Property(x => x.Codigo).IsRequired().ValueGeneratedOnAdd();
        builder.Property(x => x.StatusRegistro).IsRequired().HasDefaultValue(true);
        builder.HasOne(x => x.CategoriaUsuario)
            .WithMany()
            .HasForeignKey(x => x.CategoriaId);
    }
}