CREATE TABLE Caixa (
                       id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                       UsuarioId UNIQUEIDENTIFIER NOT NULL,
                       SaldoTotal FLOAT NOT NULL,
                       StatusRegistro NVARCHAR(15) NOT NULL,
                       FOREIGN KEY (UsuarioId) REFERENCES Usuario(id)
);