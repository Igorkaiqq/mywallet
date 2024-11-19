CREATE TABLE MetasUsuario (
                              Id UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,  
                              UsuarioId UNIQUEIDENTIFIER NOT NULL,
                              CategoriaId UNIQUEIDENTIFIER NOT NULL,
                              Valor DECIMAL(18, 2) NOT NULL,
                              StatusRegistro NVARCHAR(50) NOT NULL,
                              CONSTRAINT FK_Metas_Usuario FOREIGN KEY (UsuarioId) REFERENCES Usuario(Id),
                              CONSTRAINT FK_Metas_Categoria FOREIGN KEY (CategoriaId) REFERENCES CategoriaUsuario(Id)
);