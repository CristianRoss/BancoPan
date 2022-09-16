CREATE TABLE clientes (
    id_cliente      INTEGER NOT NULL,
    cpf             VARCHAR2(13),
    cnpj            VARCHAR2(20),
    nome            VARCHAR2(38) NOT NULL,
    sobrenome       VARCHAR2(38),
    email           VARCHAR2(38) NOT NULL,
    endereco        VARCHAR2(38) NOT NULL,
    data_nascimento DATE
);

ALTER TABLE clientes
    ADD CONSTRAINT arc_cleintes CHECK ( ( ( cpf IS NOT NULL )
                                          AND ( cnpj IS NULL ) )
                                        OR ( ( cnpj IS NOT NULL )
                                             AND ( cpf IS NULL ) ) );
                                             
ALTER TABLE clientes
    ADD CONSTRAINT sobrenome_check CHECK ( ( ( cnpj IS NOT NULL )
                                          AND ( sobrenome IS NULL ) )
                                        OR ( ( sobrenome IS NOT NULL )
                                             AND ( cnpj IS NULL ) ) );

ALTER TABLE clientes ADD CONSTRAINT clientes_pk PRIMARY KEY ( id_cliente );

ALTER TABLE clientes ADD CONSTRAINT clientes_cpf_un UNIQUE ( cpf );

ALTER TABLE clientes ADD CONSTRAINT clientes_cnpj_un UNIQUE ( cnpj );

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

CREATE TABLE documento_conta (
    id_documento      INTEGER NOT NULL,
    numero            INTEGER NOT NULL,
    id_cliente        INTEGER NOT NULL,
    id_poupanca       INTEGER,
    id_conta_corrente INTEGER
);

ALTER TABLE documento_conta
    ADD CONSTRAINT fkarc_contas CHECK ( ( ( id_poupanca IS NOT NULL )
                                          AND ( id_conta_corrente IS NULL ) )
                                        OR ( ( id_conta_corrente IS NOT NULL )
                                             AND ( id_poupanca IS NULL ) ) );

ALTER TABLE documento_conta ADD CONSTRAINT documento_conta_pk PRIMARY KEY ( id_documento );

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

CREATE TABLE maquininha (
    id_maquininha  INTEGER NOT NULL,
    taxas          NUMBER(38, 2) NOT NULL,
    vendas         NUMBER(38) NOT NULL,
    vendas_por_mes NUMBER(38) NOT NULL,
    id_cliente     INTEGER NOT NULL
);

ALTER TABLE maquininha ADD CONSTRAINT maquininha_pk PRIMARY KEY ( id_maquininha );

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
