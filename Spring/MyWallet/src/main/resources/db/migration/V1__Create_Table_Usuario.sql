CREATE TABLE Usuario (
                         id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                         Nome NVARCHAR(70) NOT NULL,
                         Username NVARCHAR(70) NOT NULL UNIQUE,
                         Email NVARCHAR(70) NOT NULL UNIQUE,
                         Senha NVARCHAR(255) NOT NULL,
                         Telefone NVARCHAR(15) NOT NULL UNIQUE,
                         Cpf NVARCHAR(14) NOT NULL UNIQUE,
                         Genero NVARCHAR(10) NOT NULL,
                         DataNascimento DATETIME NOT NULL,
                         DataCadastro DATETIME NOT NULL,
                         PerguntaSecreta NVARCHAR(70) NOT NULL,
                         RespostaSecreta NVARCHAR(70) NOT NULL,
                         StatusRegistro NVARCHAR(15) NOT NULL
);
