using Entity.Entidades;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Infra.Configuration;

public class UsuariosConfiguration : IEntityTypeConfiguration<Usuarios>
{
    public void Configure(EntityTypeBuilder<Usuarios> builder)
    {
        builder.ToTable(nameof(Usuarios));
        builder.HasKey(x => x.Id);
        builder.Property(x => x.Nome).IsRequired().HasMaxLength(70);
        builder.Property(x => x.DataNascimento).IsRequired();
        builder.Property(x => x.Genero).IsRequired().HasMaxLength(10);
        builder.Property(x => x.Telefone).IsRequired().HasMaxLength(15);
        builder.Property(x => x.PerguntaSeguranca).IsRequired().HasMaxLength(70);
        builder.Property(x => x.RespostaSeguranca).IsRequired().HasMaxLength(70);
        builder.Property(x => x.UserName).IsRequired().HasMaxLength(70);

    }
}