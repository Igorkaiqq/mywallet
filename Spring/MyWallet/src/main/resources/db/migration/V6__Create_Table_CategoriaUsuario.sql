CREATE TABLE CategoriaUsuario (
                                  id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                                  UsuarioId UNIQUEIDENTIFIER NOT NULL,
                                  TipoTransacaoId UNIQUEIDENTIFIER NOT NULL,
                                  CategoriaPadraoId UNIQUEIDENTIFIER NULL,
                                  Nome NVARCHAR(70) NOT NULL,
                                  StatusRegistro NVARCHAR(15) NOT NULL
                                  FOREIGN KEY (UsuarioId) REFERENCES Usuario(id),
                                  FOREIGN KEY (TipoTransacaoId) REFERENCES TipoTransacao(id),
                                  FOREIGN KEY (CategoriaPadraoId) REFERENCES CategoriaPadrao(id)
);