using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Infra.Configuration.Migrations
{
    /// <inheritdoc />
    public partial class AdicionaStatusRegistroSubcategoriaUsuario : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<bool>(
                name: "StatusRegistro",
                table: "SubcategoriaUsuario",
                type: "bit",
                nullable: false,
                defaultValue: true);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "StatusRegistro",
                table: "SubcategoriaUsuario");
        }
    }
}
