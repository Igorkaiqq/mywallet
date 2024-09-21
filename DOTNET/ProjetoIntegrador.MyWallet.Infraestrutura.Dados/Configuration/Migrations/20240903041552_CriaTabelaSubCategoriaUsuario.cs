using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Infra.Configuration.Migrations
{
    /// <inheritdoc />
    public partial class CriaTabelaSubCategoriaUsuario : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "SubcategoriaUsuario",
                columns: table => new
                {
                    CategoriaId = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    Id = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    Nome = table.Column<string>(type: "nvarchar(70)", maxLength: 70, nullable: false),
                    Codigo = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1")
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_SubcategoriaUsuario", x => x.Id);
                    table.ForeignKey(
                        name: "FK_SubcategoriaUsuario_CategoriaUsuario_CategoriaId",
                        column: x => x.CategoriaId,
                        principalTable: "CategoriaUsuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_SubcategoriaUsuario_CategoriaId",
                table: "SubcategoriaUsuario",
                column: "CategoriaId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "SubcategoriaUsuario");
        }
    }
}
