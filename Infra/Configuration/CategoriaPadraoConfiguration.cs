using Entity.Entidades;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Infra.Configuration;

public class CategoriaPadraoConfiguration : IEntityTypeConfiguration<CategoriaPadrao>
{
    public void Configure(EntityTypeBuilder<CategoriaPadrao> builder)
    {
        builder.ToTable(nameof(CategoriaPadrao));
        builder.HasKey(x => x.Id);
        builder.Property(x => x.Codigo).IsRequired().ValueGeneratedOnAdd();
        builder.Property(x => x.Nome).IsRequired().HasMaxLength(70);
        builder.HasOne(x => x.TipoTransacao)
            .WithMany()
            .HasForeignKey(x => x.TipoTransacaoId);
    }
}