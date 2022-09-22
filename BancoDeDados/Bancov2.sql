-- Generated by Oracle SQL Developer Data Modeler 21.4.2.059.0838
--   at:        2022-09-16 10:51:54 BRT
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE


CREATE TABLE cartao_credito (
    id_cartao_credito INTEGER NOT NULL,
    numero_credito    INTEGER NOT NULL,
    fatura            NUMBER(38, 2) NOT NULL,
    data_vencimento   DATE NOT NULL,
    juros_credito     NUMBER(5, 2) NOT NULL,
    limite_credito    NUMBER(38, 2) NOT NULL,
    id_cliente        INTEGER NOT NULL
);

ALTER TABLE cartao_credito ADD CONSTRAINT cartao_credito_pk PRIMARY KEY ( id_cartao_credito );

ALTER TABLE cartao_credito ADD CONSTRAINT cartao_credito_num_un UNIQUE ( numero_credito );

CREATE SEQUENCE seq_cartao_crediito
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

CREATE TABLE cartao_debito (
    id_cartao_debito INTEGER NOT NULL,
    numero           INTEGER NOT NULL,
    limite           NUMBER(38, 2) NOT NULL,
    id_cliente       INTEGER NOT NULL
);


ALTER TABLE cartao_debito ADD CONSTRAINT cartao_debito_pk PRIMARY KEY ( id_cartao_debito );

ALTER TABLE cartao_debito ADD CONSTRAINT cartao_debito_num_un UNIQUE ( numero );

CREATE SEQUENCE seq_cartao_debito
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

CREATE TABLE clientes (
    id_cliente      INTEGER NOT NULL,
    cpf             VARCHAR2(15),
    cnpj            VARCHAR2(20),
    nome            VARCHAR2(38) NOT NULL,
    sobrenome       VARCHAR2(38),
    telefone        NUMBER(38) NOT NULL,
    email           VARCHAR2(38) NOT NULL,
    endereco        VARCHAR2(38) NOT NULL,
    data_nascimento DATE
);

ALTER TABLE clientes ADD CONSTRAINT clientes_pk PRIMARY KEY ( id_cliente );

ALTER TABLE clientes ADD CONSTRAINT clientes_cpf_un UNIQUE ( cpf );

ALTER TABLE clientes ADD CONSTRAINT clientes_cnpj_un UNIQUE ( cnpj );

ALTER TABLE clientes
    ADD CONSTRAINT arc_clientes CHECK ( ( ( cpf IS NOT NULL )
                                          AND ( cnpj IS NULL ) )
                                        OR ( ( cnpj IS NOT NULL )
                                             AND ( cpf IS NULL ) ) );
                                             
ALTER TABLE clientes
    ADD CONSTRAINT sobrenome_check CHECK ( ( ( cpf IS NOT NULL )
                                             AND ( sobrenome IS NOT NULL ) )
                                           OR ( ( cnpj IS NOT NULL )
                                                AND ( sobrenome IS NULL ) ) );
                                                
CREATE SEQUENCE seq_clientes
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

CREATE TABLE conta_corrente (
    id_conta_corrente INTEGER NOT NULL,
    numero            INTEGER NOT NULL,
    saldo             NUMBER(38, 2) NOT NULL,
    juros             NUMBER(5, 2) NOT NULL,
    data_criacao      DATE NOT NULL,
    id_cliente        INTEGER NOT NULL,
    chave_pix         VARCHAR2(38)
);

ALTER TABLE conta_corrente ADD CONSTRAINT conta_corrente_pk PRIMARY KEY ( id_conta_corrente );

ALTER TABLE conta_corrente ADD CONSTRAINT conta_corrente_num_un UNIQUE ( numero );

CREATE SEQUENCE seq_conta_corrente
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

CREATE TABLE conta_poupanca (
    id_poupanca    INTEGER NOT NULL,
    numero         INTEGER NOT NULL,
    saldo          NUMBER(38, 2) NOT NULL,
    juros          NUMBER(5, 2) NOT NULL,
    data_criacao   DATE NOT NULL,
    data_acrescimo DATE NOT NULL,
    id_cliente     INTEGER NOT NULL
);

ALTER TABLE conta_poupanca ADD CONSTRAINT conta_poupanca_pk PRIMARY KEY ( id_poupanca );

ALTER TABLE conta_poupanca ADD CONSTRAINT conta_poupanca_numero_un UNIQUE ( numero );

CREATE SEQUENCE seq_conta_poupanca
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

CREATE TABLE documento_conta (
    id_documento     INTEGER NOT NULL,
    numero           INTEGER NOT NULL,
    id_cliente       INTEGER NOT NULL,
    id_poupanca      INTEGER,
    id_conta_corrente INTEGER
);

ALTER TABLE documento_conta
    ADD CONSTRAINT fkarc_contas CHECK ( ( ( id_poupanca IS NOT NULL )
                                          AND ( id_conta_corrente IS NULL ) )
                                        OR ( ( id_conta_corrente IS NOT NULL )
                                             AND ( id_poupanca IS NULL ) ) );

ALTER TABLE documento_conta ADD CONSTRAINT documento_conta_pk PRIMARY KEY ( id_documento );

CREATE SEQUENCE seq_documento_conta
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

CREATE TABLE emprestimos (
    id_emprestimo   INTEGER NOT NULL,
    valor           NUMBER(38, 2) NOT NULL,
    juros           NUMBER(5, 2) NOT NULL,
    qtd_parcelas    INTEGER NOT NULL,
    data_realizacao DATE NOT NULL,
    dia_parcela     DATE NOT NULL,
    id_cliente      INTEGER NOT NULL
);

ALTER TABLE emprestimos ADD CONSTRAINT emprestimos_pk PRIMARY KEY ( id_emprestimo );

CREATE SEQUENCE seq_emprestimos
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

CREATE TABLE financiamentos (
    id_financiamento INTEGER NOT NULL,
    valor_total      NUMBER(38, 2) NOT NULL,
    valor_parcelas   NUMBER(38, 2) NOT NULL,
    qtd_parcelas     INTEGER NOT NULL,
    juros            NUMBER(5, 2) NOT NULL,
    entrada          NUMBER(38, 2) NOT NULL,
    data_realizacao  DATE NOT NULL,
    data_fim         DATE NOT NULL,
    id_cliente       INTEGER NOT NULL
);

ALTER TABLE financiamentos ADD CONSTRAINT financiamentos_pk PRIMARY KEY ( id_financiamento );

CREATE SEQUENCE seq_financiamento
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

CREATE TABLE maquininha (
    id_maquininha  INTEGER NOT NULL,
    taxas          NUMBER(38, 2) NOT NULL,
    vendas         NUMBER(38) NOT NULL,
    vendas_por_mes NUMBER(38) NOT NULL,
    id_cliente     INTEGER NOT NULL
);

ALTER TABLE maquininha ADD CONSTRAINT maquininha_pk PRIMARY KEY ( id_maquininha );

CREATE SEQUENCE seq_maquininha
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

CREATE TABLE pix (
    id_pix         INTEGER NOT NULL,
    chave_conta    VARCHAR2(38) NOT NULL,
    chave_destino  VARCHAR2(38) NOT NULL,
    valor          NUMBER(38, 2) NOT NULL,
    data           DATE NOT NULL,
    limite_valor   NUMBER(38, 2),
    limite_horario DATE,
    id_cliente     INTEGER NOT NULL
);

CREATE SEQUENCE seq_pix
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

ALTER TABLE pix ADD CONSTRAINT pix_pk PRIMARY KEY ( id_pix );

ALTER TABLE cartao_credito
    ADD CONSTRAINT cartao_credito_clientes_fk FOREIGN KEY ( id_cliente )
        REFERENCES clientes ( id_cliente );

ALTER TABLE cartao_debito
    ADD CONSTRAINT cartao_debito_clientes_fk FOREIGN KEY ( id_cliente )
        REFERENCES clientes ( id_cliente );

ALTER TABLE conta_corrente
    ADD CONSTRAINT conta_corrente_clientes_fk FOREIGN KEY ( id_cliente )
        REFERENCES clientes ( id_cliente );

ALTER TABLE conta_poupanca
    ADD CONSTRAINT conta_poupanca_clientes_fk FOREIGN KEY ( id_cliente )
        REFERENCES clientes ( id_cliente );

ALTER TABLE documento_conta
    ADD CONSTRAINT documento_clientes_fk FOREIGN KEY ( id_cliente )
        REFERENCES clientes ( id_cliente );

ALTER TABLE documento_conta
    ADD CONSTRAINT documento_conta_corrente_fk FOREIGN KEY ( id_conta_corrente )
        REFERENCES conta_corrente ( id_conta_corrente );

ALTER TABLE documento_conta
    ADD CONSTRAINT documento_poupanca_fk FOREIGN KEY ( id_poupanca )
        REFERENCES conta_poupanca ( id_poupanca );

ALTER TABLE emprestimos
    ADD CONSTRAINT emprestimos_clientes_fk FOREIGN KEY ( id_cliente )
        REFERENCES clientes ( id_cliente );

ALTER TABLE financiamentos
    ADD CONSTRAINT financiamentos_clientes_fk FOREIGN KEY ( id_cliente )
        REFERENCES clientes ( id_cliente );

ALTER TABLE maquininha
    ADD CONSTRAINT maquininha_clientes_fk FOREIGN KEY ( id_cliente )
        REFERENCES clientes ( id_cliente );

ALTER TABLE pix
    ADD CONSTRAINT pix_clientes_fk FOREIGN KEY ( id_cliente )
        REFERENCES clientes ( id_cliente );  
     



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             9
-- CREATE INDEX                             0
-- ALTER TABLE                             23
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
