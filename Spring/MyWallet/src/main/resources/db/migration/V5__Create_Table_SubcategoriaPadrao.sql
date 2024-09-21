CREATE TABLE SubcategoriaPadrao (
                                    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                                    CategoriaPadraoId UNIQUEIDENTIFIER NOT NULL,
                                    Nome NVARCHAR(70) NOT NULL,
                                    StatusRegistro NVARCHAR(15) NOT NULL
                                    FOREIGN KEY (CategoriaPadraoId) REFERENCES CategoriaPadrao(id)
);