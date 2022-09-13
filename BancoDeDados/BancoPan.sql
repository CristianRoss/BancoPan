-- Gerado por Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   em:        2022-09-13 10:43:55 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE cartao_de_credito (
    id_cartao_credito NUMBER(50) NOT NULL,
    numero_credito    NUMBER(50) NOT NULL,
    fatura            NUMBER(50, 2) NOT NULL,
    data_vencimento   DATE NOT NULL,
    juros_credito     NUMBER(6, 2) NOT NULL,
    limiite_credito   NUMBER(50, 2),
    id_conta_jur      NUMBER(50),
    id_conta_fisica   NUMBER(50)
);

ALTER TABLE cartao_de_credito
    ADD CONSTRAINT fkarc_credito CHECK ( ( ( id_conta_fisica IS NOT NULL )
                                           AND ( id_conta_jur IS NULL ) )
                                         OR ( ( id_conta_jur IS NOT NULL )
                                              AND ( id_conta_fisica IS NULL ) ) );

ALTER TABLE cartao_de_credito ADD CONSTRAINT cartao_de_credito_pk PRIMARY KEY ( id_cartao_credito );

ALTER TABLE cartao_de_credito ADD CONSTRAINT credito_numero_uk UNIQUE ( numero_credito );

CREATE TABLE cartao_debito (
    id_cartao_debito NUMBER(50) NOT NULL,
    numero_cartao    NUMBER(50) NOT NULL,
    limite           NUMBER(50, 2),
    id_conta_jur     NUMBER(50),
    id_conta_fisica  NUMBER(50)
);

ALTER TABLE cartao_debito
    ADD CONSTRAINT fkarc_debito CHECK ( ( ( id_conta_jur IS NOT NULL )
                                          AND ( id_conta_fisica IS NULL ) )
                                        OR ( ( id_conta_fisica IS NOT NULL )
                                             AND ( id_conta_jur IS NULL ) ) );

ALTER TABLE cartao_debito ADD CONSTRAINT cartao_debito_pk PRIMARY KEY ( id_cartao_debito );

ALTER TABLE cartao_debito ADD CONSTRAINT debito_numero_uk UNIQUE ( numero_cartao );

CREATE TABLE cliente (
    id_cliente        NUMBER(50) NOT NULL,
    nome              VARCHAR2(50) NOT NULL,
    sexo              VARCHAR2(9) NOT NULL,
    celular           NUMBER(15) NOT NULL,
    email             VARCHAR2(50) NOT NULL,
    endereco          VARCHAR2(60) NOT NULL,
    id_cliente_jur    NUMBER(50),
    id_cliente_fisica NUMBER(50)
);

ALTER TABLE cliente
    ADD CONSTRAINT fkarc_cliente CHECK ( ( ( id_cliente_jur IS NOT NULL )
                                           AND ( id_cliente_fisica IS NULL ) )
                                         OR ( ( id_cliente_fisica IS NOT NULL )
                                              AND ( id_cliente_jur IS NULL ) ) );

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( id_cliente );

CREATE TABLE conta_corrente_fisica (
    id_cliente      NUMBER(50) NOT NULL,
    id_conta_fisica NUMBER(50) NOT NULL,
    cpf             VARCHAR2(50) NOT NULL,
    sobrenome       VARCHAR2(50) NOT NULL,
    numero          NUMBER(50) NOT NULL,
    saldo           NUMBER(50) NOT NULL,
    status          NUMBER(1) NOT NULL,
    data_criacao    DATE NOT NULL
);

ALTER TABLE conta_corrente_fisica ADD CONSTRAINT conta_fisica_pk PRIMARY KEY ( id_cliente );

ALTER TABLE conta_corrente_fisica ADD CONSTRAINT conta_fisica_cpf_un UNIQUE ( cpf );

ALTER TABLE conta_corrente_fisica ADD CONSTRAINT id_fisica_uk UNIQUE ( id_conta_fisica );

CREATE TABLE conta_corrente_juridica (
    id_cliente   NUMBER(50) NOT NULL,
    id_conta_jur NUMBER(50) NOT NULL,
    numero       NUMBER(50) NOT NULL,
    saldo        NUMBER(52, 2) NOT NULL,
    data_criacao DATE NOT NULL,
    status       NUMBER(1) NOT NULL,
    cnpj         VARCHAR2(50) NOT NULL
);

ALTER TABLE conta_corrente_juridica ADD CONSTRAINT conta_juridica_pk PRIMARY KEY ( id_cliente );

ALTER TABLE conta_corrente_juridica ADD CONSTRAINT conta_corrente_juridica_pk UNIQUE ( id_conta_jur );

ALTER TABLE conta_corrente_juridica ADD CONSTRAINT conta_juridica_cnpj_uk UNIQUE ( cnpj );

CREATE TABLE emprestimos (
    id_emprestimo        NUMBER(50) NOT NULL,
    valor                NUMBER(50) NOT NULL,
    juros                NUMBER(52, 2) NOT NULL,
    data_de_realizacao   DATE NOT NULL,
    data_prazo_pagamento DATE NOT NULL,
    parcelas_emprestimo  NUMBER(5) NOT NULL,
    id_conta_jur         NUMBER(50),
    id_conta_fisica      NUMBER(50)
);

ALTER TABLE emprestimos
    ADD CONSTRAINT fkarc_emprestimos CHECK ( ( ( id_conta_jur IS NOT NULL )
                                               AND ( id_conta_fisica IS NULL ) )
                                             OR ( ( id_conta_fisica IS NOT NULL )
                                                  AND ( id_conta_jur IS NULL ) ) );

ALTER TABLE emprestimos ADD CONSTRAINT emprestimos_pk PRIMARY KEY ( id_emprestimo );

CREATE TABLE financiamento (
    id_financiamento NUMBER(50) NOT NULL,
    valor_total      NUMBER(50) NOT NULL,
    valor_parcelas   NUMBER(50) NOT NULL,
    qtd_parcelas     NUMBER(6) NOT NULL,
    juros            NUMBER(50, 2) NOT NULL,
    data_realizacao  DATE NOT NULL,
    data_fim         DATE NOT NULL,
    entrada          NUMBER(50),
    id_conta_jur     NUMBER(50),
    id_conta_fisica  NUMBER(50)
);

ALTER TABLE financiamento
    ADD CONSTRAINT fkarc_financiamento CHECK ( ( ( id_conta_fisica IS NOT NULL )
                                                 AND ( id_conta_jur IS NULL ) )
                                               OR ( ( id_conta_jur IS NOT NULL )
                                                    AND ( id_conta_fisica IS NULL ) ) );

ALTER TABLE financiamento ADD CONSTRAINT financiamento_pk PRIMARY KEY ( id_financiamento );

CREATE TABLE maquininha (
    id_maquininha   NUMBER(50) NOT NULL,
    taxa            NUMBER(52, 2) NOT NULL,
    vendas          NUMBER(50) NOT NULL,
    vendas_por_mes  NUMBER(50) NOT NULL,
    id_conta_jur    NUMBER(50),
    id_conta_fisica NUMBER(50)
);

ALTER TABLE maquininha
    ADD CONSTRAINT fkarc_maquininha CHECK ( ( ( id_conta_jur IS NOT NULL )
                                              AND ( id_conta_fisica IS NULL ) )
                                            OR ( ( id_conta_fisica IS NOT NULL )
                                                 AND ( id_conta_jur IS NULL ) ) );

ALTER TABLE maquininha ADD CONSTRAINT maquininha_pk PRIMARY KEY ( id_maquininha );

CREATE TABLE pix (
    id_pix          NUMBER(50) NOT NULL,
    chave_pix       VARCHAR2(50) NOT NULL,
    chave_destino   VARCHAR2(50) NOT NULL,
    limite          NUMBER(50),
    limite_horario  DATE,
    valor_pix       NUMBER(50, 2) NOT NULL,
    id_conta_jur    NUMBER(50),
    id_conta_fisica NUMBER(50)
);

ALTER TABLE pix
    ADD CONSTRAINT fkarc_pix CHECK ( ( ( id_conta_fisica IS NOT NULL )
                                       AND ( id_conta_jur IS NULL ) )
                                     OR ( ( id_conta_jur IS NOT NULL )
                                          AND ( id_conta_fisica IS NULL ) ) );

ALTER TABLE pix ADD CONSTRAINT pix_pk PRIMARY KEY ( id_pix );

CREATE TABLE poupanca (
    id_poupanca     NUMBER(50) NOT NULL,
    saldo_poupanca  NUMBER(50) NOT NULL,
    juros_poupanca  NUMBER(50, 2) NOT NULL,
    data_criacao    DATE NOT NULL,
    data_acrescimo  DATE NOT NULL,
    id_conta_jur    NUMBER(50),
    id_conta_fisica NUMBER(50)
);

ALTER TABLE poupanca
    ADD CONSTRAINT fkarc_poupanca CHECK ( ( ( id_conta_jur IS NOT NULL )
                                            AND ( id_conta_fisica IS NULL ) )
                                          OR ( ( id_conta_fisica IS NOT NULL )
                                               AND ( id_conta_jur IS NULL ) ) );

ALTER TABLE poupanca ADD CONSTRAINT poupanca_pk PRIMARY KEY ( id_poupanca );

ALTER TABLE cartao_de_credito
    ADD CONSTRAINT cartao_de_credito_fisica_fk FOREIGN KEY ( id_conta_fisica )
        REFERENCES conta_corrente_fisica ( id_conta_fisica );

ALTER TABLE cartao_de_credito
    ADD CONSTRAINT cartao_de_credito_juridica_fk FOREIGN KEY ( id_conta_jur )
        REFERENCES conta_corrente_juridica ( id_conta_jur );

ALTER TABLE cartao_debito
    ADD CONSTRAINT cartao_debito_fisica_fk FOREIGN KEY ( id_conta_fisica )
        REFERENCES conta_corrente_fisica ( id_conta_fisica );

ALTER TABLE cartao_debito
    ADD CONSTRAINT cartao_debito_juridica_fk FOREIGN KEY ( id_conta_jur )
        REFERENCES conta_corrente_juridica ( id_conta_jur );

ALTER TABLE cliente
    ADD CONSTRAINT cliente_fisica_fk FOREIGN KEY ( id_cliente_fisica )
        REFERENCES conta_corrente_fisica ( id_cliente );

ALTER TABLE cliente
    ADD CONSTRAINT cliente_juridica_fk FOREIGN KEY ( id_cliente_jur )
        REFERENCES conta_corrente_juridica ( id_cliente );

ALTER TABLE emprestimos
    ADD CONSTRAINT emprestimos_fisica_fk FOREIGN KEY ( id_conta_fisica )
        REFERENCES conta_corrente_fisica ( id_conta_fisica );

ALTER TABLE emprestimos
    ADD CONSTRAINT emprestimos_juridica_fk FOREIGN KEY ( id_conta_jur )
        REFERENCES conta_corrente_juridica ( id_conta_jur );

ALTER TABLE financiamento
    ADD CONSTRAINT financiamento_fisica_fk FOREIGN KEY ( id_conta_fisica )
        REFERENCES conta_corrente_fisica ( id_conta_fisica );

ALTER TABLE financiamento
    ADD CONSTRAINT financiamento_juridica_fk FOREIGN KEY ( id_conta_jur )
        REFERENCES conta_corrente_juridica ( id_conta_jur );

ALTER TABLE maquininha
    ADD CONSTRAINT maquininha_fisica_fk FOREIGN KEY ( id_conta_fisica )
        REFERENCES conta_corrente_fisica ( id_conta_fisica );

ALTER TABLE maquininha
    ADD CONSTRAINT maquininha_juridica_fk FOREIGN KEY ( id_conta_jur )
        REFERENCES conta_corrente_juridica ( id_conta_jur );

ALTER TABLE pix
    ADD CONSTRAINT pix_fisica_fk FOREIGN KEY ( id_conta_fisica )
        REFERENCES conta_corrente_fisica ( id_conta_fisica );

ALTER TABLE pix
    ADD CONSTRAINT pix_juridica_fk FOREIGN KEY ( id_conta_jur )
        REFERENCES conta_corrente_juridica ( id_conta_jur );

ALTER TABLE poupanca
    ADD CONSTRAINT poupanca_fisica_fk FOREIGN KEY ( id_conta_fisica )
        REFERENCES conta_corrente_fisica ( id_conta_fisica );

ALTER TABLE poupanca
    ADD CONSTRAINT poupanca_juridica_fk FOREIGN KEY ( id_conta_jur )
        REFERENCES conta_corrente_juridica ( id_conta_jur );



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            10
-- CREATE INDEX                             0
-- ALTER TABLE                             40
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
