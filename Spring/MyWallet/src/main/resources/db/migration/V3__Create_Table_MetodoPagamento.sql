CREATE TABLE MetodoPagamento (
                                 id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                                 MetodoPagamento NVARCHAR(50) NOT NULL,
                                 StatusRegistro NVARCHAR(15) NOT NULL
);