CREATE TABLE ContaBancaria (
                               id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                               UsuarioId UNIQUEIDENTIFIER NOT NULL,
                               NomeBanco NVARCHAR(100) NOT NULL,
                               Saldo FLOAT NOT NULL,
                               StatusRegistro NVARCHAR(15) NOT NULL,
                               FOREIGN KEY (UsuarioId) REFERENCES Usuario(id)
);