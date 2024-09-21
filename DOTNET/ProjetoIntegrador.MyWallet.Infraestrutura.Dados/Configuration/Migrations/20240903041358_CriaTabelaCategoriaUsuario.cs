using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Infra.Configuration.Migrations
{
    /// <inheritdoc />
    public partial class CriaTabelaCategoriaUsuario : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "CategoriaUsuario",
                columns: table => new
                {
                    UsuarioId = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    TipoTransacaoId = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    Id = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    Nome = table.Column<string>(type: "nvarchar(70)", maxLength: 70, nullable: false),
                    Codigo = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1")
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CategoriaUsuario", x => x.Id);
                    table.ForeignKey(
                        name: "FK_CategoriaUsuario_TipoTransacao_TipoTransacaoId",
                        column: x => x.TipoTransacaoId,
                        principalTable: "TipoTransacao",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_CategoriaUsuario_Usuarios_UsuarioId",
                        column: x => x.UsuarioId,
                        principalTable: "Usuarios",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_CategoriaUsuario_TipoTransacaoId",
                table: "CategoriaUsuario",
                column: "TipoTransacaoId");

            migrationBuilder.CreateIndex(
                name: "IX_CategoriaUsuario_UsuarioId",
                table: "CategoriaUsuario",
                column: "UsuarioId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "CategoriaUsuario");
        }
    }
}
