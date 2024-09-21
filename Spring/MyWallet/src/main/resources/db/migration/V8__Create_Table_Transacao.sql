CREATE TABLE Transacao (
                            id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                            UsuarioId UNIQUEIDENTIFIER NOT NULL,
                            TipoTransacaoId UNIQUEIDENTIFIER NOT NULL,
                            CategoriaUsuarioId UNIQUEIDENTIFIER NOT NULL,
                            SubcategoriaUsuarioId UNIQUEIDENTIFIER NOT NULL,
                            MetodoPagamentoId UNIQUEIDENTIFIER NOT NULL,
                            Valor FLOAT NOT NULL,
                            Data DATETIME NOT NULL,
                            Descricao NVARCHAR(200),
                            StatusRegistro NVARCHAR(15) NOT NULL
                            FOREIGN KEY (UsuarioId) REFERENCES Usuario(id),
                            FOREIGN KEY (TipoTransacaoId) REFERENCES TipoTransacao(id),
                            FOREIGN KEY (CategoriaUsuarioId) REFERENCES CategoriaUsuario(id),
                            FOREIGN KEY (SubcategoriaUsuarioId) REFERENCES SubcategoriaUsuario(id),
                            FOREIGN KEY (MetodoPagamentoId) REFERENCES MetodoPagamento(id)
);