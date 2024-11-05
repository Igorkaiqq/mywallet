CREATE TABLE UsuarioRole (
                             UsuarioId UNIQUEIDENTIFIER NOT NULL,
                             RoleId BIGINT NOT NULL,
                             PRIMARY KEY (UsuarioId, RoleId),
                             FOREIGN KEY (UsuarioId) REFERENCES Usuario(Id) ON DELETE CASCADE,
                             FOREIGN KEY (RoleId) REFERENCES Role(RoleId) ON DELETE CASCADE
);
