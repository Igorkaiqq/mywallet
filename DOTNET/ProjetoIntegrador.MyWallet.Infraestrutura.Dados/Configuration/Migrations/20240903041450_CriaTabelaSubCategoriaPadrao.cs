using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Infra.Configuration.Migrations
{
    /// <inheritdoc />
    public partial class CriaTabelaSubCategoriaPadrao : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "SubcategoriaPadrao",
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
                    table.PrimaryKey("PK_SubcategoriaPadrao", x => x.Id);
                    table.ForeignKey(
                        name: "FK_SubcategoriaPadrao_CategoriaPadrao_CategoriaId",
                        column: x => x.CategoriaId,
                        principalTable: "CategoriaPadrao",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_SubcategoriaPadrao_CategoriaId",
                table: "SubcategoriaPadrao",
                column: "CategoriaId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "SubcategoriaPadrao");
        }
    }
}
