using Entity.Entidades;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Infra.Configuration;

public class MetodoPagamentoConfiguration : IEntityTypeConfiguration<MetodoPagamento>
{
    public void Configure(EntityTypeBuilder<MetodoPagamento> builder)
    {
        builder.ToTable(nameof(MetodoPagamento));
        builder.HasKey(x => x.Id);
        builder.Property(x => x.Codigo).IsRequired().ValueGeneratedOnAdd();
        builder.Property(x => x.Nome).IsRequired().HasMaxLength(70);
    }
}