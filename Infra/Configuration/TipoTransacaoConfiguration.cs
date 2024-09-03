using Entity.Entidades;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Infra.Configuration;

public class TipoTransacaoConfiguration : IEntityTypeConfiguration<TipoTransacao>
{
    public void Configure(EntityTypeBuilder<TipoTransacao> builder)
    {
        builder.ToTable(nameof(TipoTransacao));
        builder.HasKey(x => x.Id);
        builder.Property(x => x.Codigo).IsRequired().ValueGeneratedOnAdd();
        builder.Property(x => x.Nome).IsRequired();
    }
}