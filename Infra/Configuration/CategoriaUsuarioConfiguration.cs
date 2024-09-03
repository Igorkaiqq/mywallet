using Entity.Entidades;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Infra.Configuration;

public class CategoriaUsuarioConfiguration : IEntityTypeConfiguration<CategoriaUsuario>
{
    public void Configure(EntityTypeBuilder<CategoriaUsuario> builder)
    {
        builder.ToTable(nameof(CategoriaUsuario));
        builder.HasKey(x => x.Id);
        builder.Property(x => x.Codigo).IsRequired().ValueGeneratedOnAdd();
        builder.Property(x => x.Nome).IsRequired().HasMaxLength(70);
        builder.HasOne(x => x.Usuario)
            .WithMany()
            .HasForeignKey(x => x.UsuarioId);
        builder.HasOne(x => x.TipoTransacao)
            .WithMany()
            .HasForeignKey(x => x.TipoTransacaoId);
    }
}