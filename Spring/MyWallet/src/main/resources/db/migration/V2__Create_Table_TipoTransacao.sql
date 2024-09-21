CREATE TABLE TipoTransacao (
                               id UNIQUEIDENTIFIER PRIMARY KEY,
                               TipoTransacao NVARCHAR(50) NOT NULL,
                               StatusRegistro NVARCHAR(15) NOT NULL
);