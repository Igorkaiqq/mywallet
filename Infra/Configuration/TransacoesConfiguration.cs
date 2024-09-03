using Entity.Entidades;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Infra.Configuration;

public class TransacoesConfiguration : IEntityTypeConfiguration<Transacoes>
{
    public void Configure(EntityTypeBuilder<Transacoes> builder)
    {
        builder.ToTable(nameof(Transacoes));
        builder.HasKey(x => x.Id);
        builder.Property(x => x.Codigo).IsRequired().ValueGeneratedOnAdd();
        builder.Property(x => x.Valor).IsRequired();
        builder.Property(x => x.DataTransacao).IsRequired();
        builder.Property(x => x.Observacoes).HasMaxLength(200);

        builder.HasOne(x => x.Usuario)
            .WithMany()
            .HasForeignKey(x => x.UsuarioId)
            .OnDelete(DeleteBehavior.Restrict);

        builder.HasOne(x => x.CategoriaUsuario)
            .WithMany()
            .HasForeignKey(x => x.CategoriaId)
            .OnDelete(DeleteBehavior.Restrict);

        builder.HasOne(x => x.SubcategoriaUsuario)
            .WithMany()
            .HasForeignKey(x => x.SubcategoriaId)
            .OnDelete(DeleteBehavior.Restrict);

        builder.HasOne(x => x.TipoTransacao)
            .WithMany()
            .HasForeignKey(x => x.TipoTransacaoId)
            .OnDelete(DeleteBehavior.Restrict);

        builder.HasOne(x => x.MetodoPagamento)
            .WithMany()
            .HasForeignKey(x => x.MetodoPagamentoId)
            .OnDelete(DeleteBehavior.Restrict);

    }
}