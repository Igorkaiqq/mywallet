CREATE TABLE SubcategoriaUsuario (
                                     id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                                     UsuarioId UNIQUEIDENTIFIER NOT NULL,
                                     CategoriaUsuarioid UNIQUEIDENTIFIER NOT NULL,
                                     SubcategoriaPadraoId UNIQUEIDENTIFIER NULL,
                                     Nome NVARCHAR(70) NOT NULL,
                                     StatusRegistro NVARCHAR(15) NOT NULL
                                     FOREIGN KEY (UsuarioId) REFERENCES Usuario(id),
                                     FOREIGN KEY (CategoriaUsuarioid) REFERENCES CategoriaUsuario(id),
                                     FOREIGN KEY (SubcategoriaPadraoId) REFERENCES SubcategoriaPadrao(id)
);