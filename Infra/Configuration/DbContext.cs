using Entity.Entidades;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace Infra.Configuration
{
    public class DbContext : IdentityDbContext<Usuarios, IdentityRole<Guid>, Guid>
    {
        public DbContext(DbContextOptions<DbContext> options) : base(options)
        {
        }

        public DbSet<CategoriaPadrao> CategoriaPadrao { get; set; }
        public DbSet<CategoriaUsuario> CategoriaUsuario { get; set; }
        public DbSet<SubcategoriaPadrao> SubcategoriaPadrao { get; set; }
        public DbSet<SubcategoriaUsuario> SubcategoriaUsuario { get; set; }
        public DbSet<MetodoPagamento> MetodoPagamento { get; set; }
        public DbSet<TipoTransacao> TipoTransacao { get; set; }
        public DbSet<Transacoes> Transacoes { get; set; }
        public DbSet<Usuarios> Usuarios { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Usuarios>().ToTable("Usuarios").HasKey(p => p.Id);
            base.OnModelCreating(modelBuilder);

            modelBuilder.ApplyConfiguration(new CategoriaPadraoConfiguration());
            modelBuilder.ApplyConfiguration(new CategoriaUsuarioConfiguration());
            modelBuilder.ApplyConfiguration(new SubcategoriaPadraoConfiguration());
            modelBuilder.ApplyConfiguration(new SubcategoriaUsuarioConfiguration());
            modelBuilder.ApplyConfiguration(new MetodoPagamentoConfiguration());
            modelBuilder.ApplyConfiguration(new TipoTransacaoConfiguration());
            modelBuilder.ApplyConfiguration(new TransacoesConfiguration());
            modelBuilder.ApplyConfiguration(new UsuariosConfiguration());

        }

    }
}