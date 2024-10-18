ALTER TABLE Transacao
    ADD ContaBancariaId UNIQUEIDENTIFIER NOT NULL;

ALTER TABLE Transacao
    ADD FOREIGN KEY (ContaBancariaId) REFERENCES ContaBancaria(id);
