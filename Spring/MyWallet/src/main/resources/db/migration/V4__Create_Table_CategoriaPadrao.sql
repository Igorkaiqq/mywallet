CREATE TABLE CategoriaPadrao (
                                 id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                                 TipoTransacaoId UNIQUEIDENTIFIER NOT NULL,
                                 Nome NVARCHAR(70) NOT NULL,
                                 StatusRegistro NVARCHAR(15) NOT NULL
                                 FOREIGN KEY (TipoTransacaoId) REFERENCES TipoTransacao(id)
);