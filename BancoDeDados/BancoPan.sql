-- Integrantes:
-- RM: 87896 - Cristian Dias Rossmann Martinelli
-- RM: 88865 - Diogo Osório França
-- RM: 89291 - Fábio Fernando Sousa
-- RM: 89355 - WENDEL EDUARDO MENDES GENUINO
-- RM: 86549 - Pedro Henrique Pacheco Faria


CREATE TABLE cartao_de_credito (
    id_cartao_credito INTEGER NOT NULL,
    numero            INTEGER NOT NULL,
    fatura            NUMBER(38, 2) NOT NULL,
    data_vencimento   DATE NOT NULL,
    juros_credito     NUMBER(6, 2) NOT NULL,
    limite            NUMBER(38, 2),
    id_conta_jur      INTEGER,
    id_conta_fisica   INTEGER
);

ALTER TABLE cartao_de_credito
    ADD CONSTRAINT fkarc_credito CHECK ( ( ( id_conta_fisica IS NOT NULL )
                                           AND ( id_conta_jur IS NULL ) )
                                         OR ( ( id_conta_jur IS NOT NULL )
                                              AND ( id_conta_fisica IS NULL ) ) );

ALTER TABLE cartao_de_credito ADD CONSTRAINT cartao_de_credito_pk PRIMARY KEY ( id_cartao_credito );

ALTER TABLE cartao_de_credito ADD CONSTRAINT credito_numero_uk UNIQUE ( numero );

CREATE TABLE cartao_debito (
    id_cartao_debito INTEGER NOT NULL,
    numero           INTEGER NOT NULL,
    limite           NUMBER(38, 2),
    id_conta_jur     INTEGER,
    id_conta_fisica  INTEGER
);

ALTER TABLE cartao_debito
    ADD CONSTRAINT fkarc_debito CHECK ( ( ( id_conta_jur IS NOT NULL )
                                          AND ( id_conta_fisica IS NULL ) )
                                        OR ( ( id_conta_fisica IS NOT NULL )
                                             AND ( id_conta_jur IS NULL ) ) );

ALTER TABLE cartao_debito ADD CONSTRAINT cartao_debito_pk PRIMARY KEY ( id_cartao_debito );

ALTER TABLE cartao_debito ADD CONSTRAINT debito_numero_uk UNIQUE ( numero );

CREATE TABLE cliente (
    id_cliente        INTEGER NOT NULL,
    nome              VARCHAR2(38) NOT NULL,
    celular           NUMBER(15) NOT NULL,
    email             VARCHAR2(38) NOT NULL,
    endereco          VARCHAR2(38) NOT NULL,
    id_cliente_fisica INTEGER,
    id_cliente_jur    INTEGER,
    numero            INTEGER,
    status            NUMBER(1) NOT NULL
);

ALTER TABLE cliente
    ADD CONSTRAINT fkarc_cliente CHECK ( ( ( id_cliente_jur IS NOT NULL )
                                           AND ( id_cliente_fisica IS NULL ) )
                                         OR ( ( id_cliente_fisica IS NOT NULL )
                                              AND ( id_cliente_jur IS NULL ) ) );

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( id_cliente );

ALTER TABLE cliente ADD CONSTRAINT cliente_numero_un UNIQUE ( numero );

CREATE TABLE conta_corrente_fisica (
    id_cliente      INTEGER NOT NULL,
    id_conta_fisica INTEGER NOT NULL,
    cpf             VARCHAR2(38) NOT NULL,
    sobrenome       VARCHAR2(38) NOT NULL,
    saldo           NUMBER(38, 2) NOT NULL,
    sexo            VARCHAR2(9) NOT NULL,
    data_criacao    DATE NOT NULL
);

ALTER TABLE conta_corrente_fisica ADD CONSTRAINT conta_fisica_pk PRIMARY KEY ( id_cliente );

ALTER TABLE conta_corrente_fisica ADD CONSTRAINT id_fisica_uk UNIQUE ( id_conta_fisica );

ALTER TABLE conta_corrente_fisica ADD CONSTRAINT conta_fisica_cpf_un UNIQUE ( cpf );

CREATE TABLE conta_corrente_juridica (
    id_cliente   INTEGER NOT NULL,
    id_conta_jur INTEGER NOT NULL,
    cnpj         VARCHAR2(38) NOT NULL,
    saldo        NUMBER(38, 2) NOT NULL,
    data_criacao DATE NOT NULL
);

ALTER TABLE conta_corrente_juridica ADD CONSTRAINT conta_juridica_pk PRIMARY KEY ( id_cliente );

ALTER TABLE conta_corrente_juridica ADD CONSTRAINT conta_corrente_juridica_pk UNIQUE ( id_conta_jur );

ALTER TABLE conta_corrente_juridica ADD CONSTRAINT conta_corrente_juridica__un UNIQUE ( cnpj );

CREATE TABLE emprestimos (
    id_emprestimo        INTEGER NOT NULL,
    valor                NUMBER(38, 2) NOT NULL,
    juros                NUMBER(4, 2) NOT NULL,
    qtd_parcelas         NUMBER(5) NOT NULL,
    data_de_realizacao   DATE NOT NULL,
    data_prazo_pagamento DATE NOT NULL,
    id_conta_jur         INTEGER,
    id_conta_fisica      INTEGER
);

ALTER TABLE emprestimos
    ADD CONSTRAINT fkarc_emprestimos CHECK ( ( ( id_conta_jur IS NOT NULL )
                                               AND ( id_conta_fisica IS NULL ) )
                                             OR ( ( id_conta_fisica IS NOT NULL )
                                                  AND ( id_conta_jur IS NULL ) ) );

ALTER TABLE emprestimos ADD CONSTRAINT emprestimos_pk PRIMARY KEY ( id_emprestimo );

CREATE TABLE financiamento (
    id_financiamento INTEGER NOT NULL,
    valor_total      NUMBER(38, 2) NOT NULL,
    valor_parcelas   NUMBER(38, 2) NOT NULL,
    qtd_parcelas     NUMBER(6) NOT NULL,
    juros            NUMBER(38, 2) NOT NULL,
    data_realizacao  DATE NOT NULL,
    data_fim         DATE NOT NULL,
    entrada          NUMBER(38, 2),
    id_conta_jur     INTEGER,
    id_conta_fisica  INTEGER
);

ALTER TABLE financiamento
    ADD CONSTRAINT fkarc_financiamento CHECK ( ( ( id_conta_fisica IS NOT NULL )
                                                 AND ( id_conta_jur IS NULL ) )
                                               OR ( ( id_conta_jur IS NOT NULL )
                                                    AND ( id_conta_fisica IS NULL ) ) );

ALTER TABLE financiamento ADD CONSTRAINT financiamento_pk PRIMARY KEY ( id_financiamento );

CREATE TABLE maquininha (
    id_maquininha   INTEGER NOT NULL,
    taxa            NUMBER(38, 2) NOT NULL,
    vendas          NUMBER(38) NOT NULL,
    vendas_por_mes  NUMBER(38) NOT NULL,
    id_conta_jur    INTEGER,
    id_conta_fisica INTEGER
);

ALTER TABLE maquininha
    ADD CONSTRAINT fkarc_maquininha CHECK ( ( ( id_conta_jur IS NOT NULL )
                                              AND ( id_conta_fisica IS NULL ) )
                                            OR ( ( id_conta_fisica IS NOT NULL )
                                                 AND ( id_conta_jur IS NULL ) ) );

ALTER TABLE maquininha ADD CONSTRAINT maquininha_pk PRIMARY KEY ( id_maquininha );

CREATE TABLE pix (
    id_pix          INTEGER NOT NULL,
    chave_pix       VARCHAR2(38) NOT NULL,
    chave_destino   VARCHAR2(38) NOT NULL,
    limite          NUMBER(38, 2),
    limite_horario  DATE,
    valor_pix       NUMBER(38, 2) NOT NULL,
    id_conta_jur    INTEGER,
    id_conta_fisica INTEGER
);

ALTER TABLE pix
    ADD CONSTRAINT fkarc_pix CHECK ( ( ( id_conta_fisica IS NOT NULL )
                                       AND ( id_conta_jur IS NULL ) )
                                     OR ( ( id_conta_jur IS NOT NULL )
                                          AND ( id_conta_fisica IS NULL ) ) );

ALTER TABLE pix ADD CONSTRAINT pix_pk PRIMARY KEY ( id_pix );

CREATE TABLE poupanca (
    id_poupanca     NUMBER(38) NOT NULL,
    saldo_poupanca  NUMBER(38, 2) NOT NULL,
    juros_poupanca  NUMBER(38, 2) NOT NULL,
    data_criacao    DATE NOT NULL,
    data_acrescimo  DATE NOT NULL,
    id_conta_jur    INTEGER,
    id_conta_fisica INTEGER
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
        
-- Populando Conta Fisica

INSERT INTO conta_corrente_fisica VALUES (
    1,
    1,
    '443.434.555-15',
    'Rodriges',
    1300.00,
    'Masculino',
    sysdate
);

INSERT INTO conta_corrente_fisica VALUES (
    2,
    2,
    '554.541.950-06',
    'Dias',
    300.00,
    'Masculino',
    TO_DATE('2020/10/15 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_fisica VALUES (
    3,
    3,
    '331.995.460-13',
    'Fernandez',
    4300.00,
    'Masculino',
    TO_DATE('2021/11/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_fisica VALUES (
    4,
    4,
    '009.028.500-08',
    'Gomes',
    7800.00,
    'Feminino',
    TO_DATE('2022/04/25 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_fisica VALUES (
    5,
    5,
    '562.526.100-89',
    'Rogers',
    900.00,
    'Masculino',
    TO_DATE('2021/11/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_fisica VALUES (
    6,
    6,
    '522.718.820-36',
    'Genuino',
    3200.00,
    'Masculino',
    TO_DATE('2020/11/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_fisica VALUES (
    7,
    7,
    '372.091.150-05',
    'H. Rich',
    5600.00,
    'Masculino',
    TO_DATE('2021/09/24 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_fisica VALUES (
    8,
    8,
    '537.924.930-20',
    'Oliveira Lima',
    6600.00,
    'Masculino',
    TO_DATE('2019/07/23 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_fisica VALUES (
    9,
    9,
    '207.206.570-40',
    'Cunha Barbosa',
    6606.00,
    'Masculino',
    TO_DATE('2018/05/22 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_fisica VALUES (
    10,
    10,
    '617.227.140-77',
    'Rodrigues Cardoso',
    1500.00,
    'Masculino',
    TO_DATE('2017/10/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

-- Populando Conta Juridica

INSERT INTO conta_corrente_juridica VALUES (
    11,
    11,
    '26.152.361/0001-47',
    82615,
    TO_DATE('2022/04/25 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_juridica VALUES (
    12,
    12,
    '01.344.082/0001-54',
    264805,
    TO_DATE('2021/05/24 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_juridica VALUES (
    13,
    13,
    '34.772.365/0001-56',
    8180614,
    TO_DATE('2020/06/27 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_juridica VALUES (
    14,
    14,
    '63.823.510/0001-89',
    78398,
    TO_DATE('2019/07/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_juridica VALUES (
    15,
    15,
    '78.467.557/0001-24',
    70045,
    TO_DATE('2019/07/05 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_juridica VALUES (
    16,
    16,
    '45.639.521/0001-21',
    615788,
    TO_DATE('2020/10/07 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_juridica VALUES (
    17,
    17,
    '22.297.758/0001-76',
    345715,
    TO_DATE('2021/12/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_juridica VALUES (
    18,
    18,
    '32.801.052/0001-53',
    54011,
    TO_DATE('2020/12/27 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_juridica VALUES (
    19,
    19,
    '36.243.893/0001-43',
    53743,
    TO_DATE('2022/01/23 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO conta_corrente_juridica VALUES (
    20,
    20,
    '37.573.315/0001-38',
    7347,
    TO_DATE('2021/08/12 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

-- Populando Cliente

INSERT INTO cliente VALUES (
    1,
    'Ricardo',
    1199146335,
    'ricaro@gmail.com',
    'Rua Domingos Marino 1182',
    1,
    NULL,
    895466,
    1
);

INSERT INTO cliente VALUES (
    2,
    'Rodolfo',
    1180406528,
    'rodolfo@gmail.com',
    'Rua Irmã Efigênia 255',
    2,
    NULL,
    895467,
    1
);

INSERT INTO cliente VALUES (
    3,
    'João',
    1178809888,
    'joaofe@gmail.com',
    'Rua José de La Morena 430',
    3,
    NULL,
    895468,
    1
);

INSERT INTO cliente VALUES (
    4,
    'Maria',
    1961783730,
    'magomes@gmail.com',
    'Avenida Américo Brasiliense 661',
    4,
    NULL,
    895469,
    1
);

INSERT INTO cliente VALUES (
    5,
    'Bob',
    8486483629,
    'bobrogers@hotmail.com',
    'Rua Abel Cabral 1265',
    5,
    NULL,
    895470,
    1
);

INSERT INTO cliente VALUES (
    6,
    'Joel',
    4395552068,
    'joel@ig.com',
    'Rua Guaíra 929',
    6,
    NULL,
    895476,
    1
);

INSERT INTO cliente VALUES (
    7,
    'Donal',
    3103026774,
    'DonaldHRich@teleworm.us',
    '3122 Libby Street',
    7,
    NULL,
    895486,
    1
);

INSERT INTO cliente VALUES (
    8,
    'Mateus',
    1163696465,
    'MateusOliveiraLima@dayrep.com',
    'Estrada da Alpina 351',
    8,
    NULL,
    899466,
    1
);

INSERT INTO cliente VALUES (
    9,
    'Murilo',
    1239358114,
    'MuriloCunhaBarbosa@dayrep.com',
    'Rua Vinte e Oito de Setembro 833',
    9,
    NULL,
    865466,
    1
);

INSERT INTO cliente VALUES (
    10,
    'Guilherme',
    2133858881,
    'GuilhermeRodriguesCardoso@rhyta.com',
    'Rua Sete 496',
    10,
    NULL,
    675466,
    1
);

INSERT INTO cliente VALUES (
    11,
    'Dia',
    1162783347,
    'dia@dia.com',
    'Rua Bento Fernandes 1014',
    NULL,
    11,
    395466,
    1
);

INSERT INTO cliente VALUES (
    12,
    'Adminitração LTA',
    6759949168,
    'admDourados@gmail.com',
    'Rua José Ademar R. Perdomo 1084',
    NULL,
    12,
    555666,
    1
);

INSERT INTO cliente VALUES (
    13,
    'Pão de Açucar',
    1199146335,
    'paodeacucar@gmail.com',
    'Travessa Caroba 1766',
    NULL,
    13,
    395436,
    1
);

INSERT INTO cliente VALUES (
    14,
    'Razer',
    3875917026,
    'razer@hotmail.com',
    'Rua Francisco Gonçalves Santos 1580',
    NULL,
    14,
    992456,
    1
);

INSERT INTO cliente VALUES (
    15,
    'HyperX',
    3103026774,
    'hyperx@gmail.com',
    'Vila Neza 1405',
    NULL,
    15,
    915443,
    1
);

INSERT INTO cliente VALUES (
    16,
    'DELL',
    1197166536,
    'dell@gmail.com',
    'Rua Sete 500',
    NULL,
    16,
    905066,
    1
);

INSERT INTO cliente VALUES (
    17,
    'MICROSOFT',
    1199146335,
    'mircosoft@microsoft.com',
    'Rua Domingos Mariana 1382',
    NULL,
    17,
    555787,
    1
);

INSERT INTO cliente VALUES (
    18,
    'Emporio Informatica',
    1199146335,
    'emporioinfo@gmail.com',
    'Rua Dr Cesar 1512',
    NULL,
    18,
    555789,
    1
);

INSERT INTO cliente VALUES (
    19,
    'Buggati',
    1199146335,
    'buggati@hotmail.com',
    'AV Braz Leme 1189',
    NULL,
    19,
    566789,
    1
);

INSERT INTO cliente VALUES (
    20,
    'CVC',
    1199146335,
    'cvc@gmail.com',
    'Rua Leonardo Pardilho 1504',
    NULL,
    20,
    569789,
    1
);

-- Populando Poupanca

INSERT INTO poupanca VALUES (
    1,
    13148,
    0.01,
    TO_DATE('2017/11/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2022/10/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    NULL,
    1
);

INSERT INTO poupanca VALUES (
    2,
    78947,
    0.01,
    TO_DATE('2018/11/19 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2022/10/19 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    NULL,
    2
);

INSERT INTO poupanca VALUES (
    3,
    31927,
    0.01,
    TO_DATE('2019/11/20 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2022/10/20 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    NULL,
    3
);

INSERT INTO poupanca VALUES (
    4,
    5298,
    0.01,
    TO_DATE('2020/11/15 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2022/10/15 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    NULL,
    4
);

INSERT INTO poupanca VALUES (
    5,
    96743,
    0.01,
    TO_DATE('2021/11/17 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2022/10/17 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    NULL,
    5
);

INSERT INTO poupanca VALUES (
    6,
    65439,
    0.01,
    TO_DATE('2016/11/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2022/10/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    11,
    NULL
);

INSERT INTO poupanca VALUES (
    7,
    64644,
    0.01,
    TO_DATE('2017/11/14 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2022/10/14 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    12,
    NULL
);

INSERT INTO poupanca VALUES (
    8,
    94574,
    0.01,
    TO_DATE('2018/11/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2022/10/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    13,
    NULL
);

INSERT INTO poupanca VALUES (
    9,
    26405,
    0.01,
    TO_DATE('2019/11/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2022/10/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    14,
    NULL
);

INSERT INTO poupanca VALUES (
    10,
    53331,
    0.01,
    TO_DATE('2020/11/13 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2022/10/13 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    15,
    NULL
);

--Populando PIX

INSERT INTO pix VALUES (
    1,
    'ricaro@gmail.com',
    '554.541.950-06',
    1000,
    NULL,
    380,
    NULL,
    1
);

INSERT INTO pix VALUES (
    2,
    '331.995.460-13',
    'dell@gmail.com',
    2000,
    NULL,
    480,
    NULL,
    3
);

INSERT INTO pix VALUES (
    3,
    '554.541.950-06',
    'bobrogers@hotmail.com',
    1000,
    NULL,
    380,
    NULL,
    2
);

INSERT INTO pix VALUES (
    4,
    'magomes@gmail.com',
    '554.541.950-06',
    1000,
    NULL,
    30,
    NULL,
    4
);

INSERT INTO pix VALUES (
    5,
    '372.091.150-05',
    'magomes@gmail.com',
    3000,
    NULL,
    550,
    NULL,
    7
);

INSERT INTO pix VALUES (
    6,
    'paodeacucar@gmail.com',
    '554.541.950-06',
    NULL,
    NULL,
    3080,
    13,
    NULL
);

INSERT INTO pix VALUES (
    7,
    'razer@hotmail.com',
    'dell@gmail.com',
    NULL,
    NULL,
    50000,
    14,
    NULL
);

INSERT INTO pix VALUES (
    8,
    'ricaro@gmail.com',
    'dell@gmail.com',
    1000,
    NULL,
    800,
    NULL,
    1
);

INSERT INTO pix VALUES (
    9,
    '372.091.150-05',
    'ricaro@gmail.com',
    1000,
    NULL,
    87,
    NULL,
    7
);

INSERT INTO pix VALUES (
    10,
    'magomes@gmail.com',
    '554.541.950-06',
    1000,
    NULL,
    580,
    NULL,
    4
);

-- Populando Cartao de Credito

INSERT INTO cartao_de_credito VALUES (
    1,
    663941,
    5022,
    TO_DATE('2022/10/03', 'yyyy/mm/dd'),
    0.23,
    120000,
    NULL,
    1
);

INSERT INTO cartao_de_credito VALUES (
    2,
    205818,
    3905,
    TO_DATE('2022/10/07', 'yyyy/mm/dd'),
    0.22,
    120000,
    NULL,
    2
);

INSERT INTO cartao_de_credito VALUES (
    3,
    888290,
    1569,
    TO_DATE('2022/10/08', 'yyyy/mm/dd'),
    0.20,
    1600,
    NULL,
    3
);

INSERT INTO cartao_de_credito VALUES (
    4,
    984681,
    8374,
    TO_DATE('2022/10/13', 'yyyy/mm/dd'),
    0.13,
    120000,
    NULL,
    4
);

INSERT INTO cartao_de_credito VALUES (
    5,
    420910,
    5652,
    TO_DATE('2022/10/23', 'yyyy/mm/dd'),
    0.33,
    9999,
    NULL,
    5
);

INSERT INTO cartao_de_credito VALUES (
    6,
    198254,
    3948,
    TO_DATE('2022/10/13', 'yyyy/mm/dd'),
    0.40,
    7800,
    11,
    NULL
);

INSERT INTO cartao_de_credito VALUES (
    7,
    788792,
    349,
    TO_DATE('2022/10/15', 'yyyy/mm/dd'),
    0.32,
    15000,
    12,
    NULL
);

INSERT INTO cartao_de_credito VALUES (
    8,
    594243,
    4785,
    TO_DATE('2022/10/04', 'yyyy/mm/dd'),
    0.56,
    30000,
    13,
    NULL
);

INSERT INTO cartao_de_credito VALUES (
    9,
    825963,
    3429,
    TO_DATE('2022/10/08', 'yyyy/mm/dd'),
    0.15,
    100000,
    14,
    NULL
);

INSERT INTO cartao_de_credito VALUES (
    10,
    825965,
    34366,
    TO_DATE('2022/10/09', 'yyyy/mm/dd'),
    0.15,
    120000,
    15,
    NULL
);

-- Populando Cartao de Debito

INSERT INTO cartao_debito VALUES (
    1,
    248199,
    0,
    NULL,
    1
);

INSERT INTO cartao_debito VALUES (
    2,
    903538,
    0,
    NULL,
    2
);

INSERT INTO cartao_debito VALUES (
    3,
    699293,
    3500,
    NULL,
    3
);

INSERT INTO cartao_debito VALUES (
    4,
    506755,
    2000,
    NULL,
    4
);

INSERT INTO cartao_debito VALUES (
    5,
    831486,
    1500,
    NULL,
    5
);

INSERT INTO cartao_debito VALUES (
    6,
    237614,
    0,
    11,
    NULL
);

INSERT INTO cartao_debito VALUES (
    7,
    731213,
    0,
    12,
    NULL
);

INSERT INTO cartao_debito VALUES (
    8,
    239028,
    4550,
    13,
    NULL
);

INSERT INTO cartao_debito VALUES (
    9,
    113782,
    0,
    14,
    NULL
);

INSERT INTO cartao_debito VALUES (
    10,
    475835,
    9900,
    15,
    NULL
);

-- Populando Emprestimos

INSERT INTO emprestimos VALUES (
    1,
    81757,
    0.15,
    20,
    TO_DATE('2019/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2019/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    NULL,
    1
);

INSERT INTO emprestimos VALUES (
    2,
    22650,
    0.2,
    25,
    TO_DATE('2017/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2017/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    NULL,
    2
);

INSERT INTO emprestimos VALUES (
    3,
    48610,
    0.25,
    27,
    TO_DATE('2018/06/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2018/07/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    NULL,
    4
);

INSERT INTO emprestimos VALUES (
    4,
    60915,
    0.15,
    10,
    TO_DATE('2015/04/01 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2015/05/01 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    NULL,
    5
);

INSERT INTO emprestimos VALUES (
    5,
    24929,
    0.2,
    5,
    TO_DATE('2020/12/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2021/01/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    NULL,
    7
);

INSERT INTO emprestimos VALUES (
    6,
    14818,
    0.17,
    11,
    TO_DATE('2019/03/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2019/04/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    11,
    NULL
);

INSERT INTO emprestimos VALUES (
    7,
    78329,
    0.19,
    22,
    TO_DATE('2019/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2019/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    12,
    NULL
);

INSERT INTO emprestimos VALUES (
    8,
    998,
    0.14,
    37,
    TO_DATE('2017/10/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2017/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    13,
    NULL
);

INSERT INTO emprestimos VALUES (
    9,
    19875,
    0.11,
    42,
    TO_DATE('2015/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2015/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    12,
    NULL
);

INSERT INTO emprestimos VALUES (
    10,
    77896,
    0.16,
    15,
    TO_DATE('2016/08/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2016/09/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    15,
    NULL
);

-- Populando Fianciamento

INSERT INTO financiamento VALUES (
    1,
    8585,
    1400,
    27,
    0.27,
    TO_DATE('2019/10/31', 'yyyy/mm/dd'),
    TO_DATE('2021/10/31', 'yyyy/mm/dd'),
    3000,
    NULL,
    1
);

INSERT INTO financiamento VALUES (
    2,
    37829,
    1700,
    32,
    0.20,
    TO_DATE('2019/11/30', 'yyyy/mm/dd'),
    TO_DATE('2022/10/31', 'yyyy/mm/dd'),
    0,
    NULL,
    2
);

INSERT INTO financiamento VALUES (
    3,
    51466,
    3900,
    20,
    0.15,
    TO_DATE('2016/05/31', 'yyyy/mm/dd'),
    TO_DATE('2021/10/31', 'yyyy/mm/dd'),
    3500,
    NULL,
    3
);

INSERT INTO financiamento VALUES (
    4,
    78893,
    1700,
    15,
    0.30,
    TO_DATE('2014/07/30', 'yyyy/mm/dd'),
    TO_DATE('2018/10/31', 'yyyy/mm/dd'),
    7000,
    NULL,
    4
);

INSERT INTO financiamento VALUES (
    5,
    13749,
    3558,
    82,
    0.07,
    TO_DATE('2018/06/15', 'yyyy/mm/dd'),
    TO_DATE('2021/10/31', 'yyyy/mm/dd'),
    8871,
    NULL,
    3
);

INSERT INTO financiamento VALUES (
    6,
    44540,
    6841,
    18,
    0.20,
    TO_DATE('2017/04/16', 'yyyy/mm/dd'),
    TO_DATE('2022/11/30', 'yyyy/mm/dd'),
    2239,
    12,
    NULL
);

INSERT INTO financiamento VALUES (
    7,
    32626,
    7226,
    50,
    0.99,
    TO_DATE('2016/08/29', 'yyyy/mm/dd'),
    TO_DATE('2024/10/31', 'yyyy/mm/dd'),
    165,
    17,
    NULL
);

INSERT INTO financiamento VALUES (
    8,
    7586,
    9899,
    40,
    0.09,
    TO_DATE('2015/11/15', 'yyyy/mm/dd'),
    TO_DATE('2028/12/31', 'yyyy/mm/dd'),
    468,
    18,
    NULL
);

INSERT INTO financiamento VALUES (
    9,
    61959,
    7896,
    57,
    0.70,
    TO_DATE('2019/12/31', 'yyyy/mm/dd'),
    TO_DATE('2025/11/30', 'yyyy/mm/dd'),
    9728,
    19,
    NULL
);

INSERT INTO financiamento VALUES (
    10,
    8210,
    5958,
    51,
    0.56,
    TO_DATE('2018/10/03', 'yyyy/mm/dd'),
    TO_DATE('2024/10/30', 'yyyy/mm/dd'),
    4607,
    20,
    NULL
);

-- Populando Maquininha

INSERT INTO maquininha VALUES (
    1,
    0.13,
    566236,
    812,
    11,
    NULL
);

INSERT INTO maquininha VALUES (
    2,
    0.12,
    277833,
    897,
    12,
    NULL
);

INSERT INTO maquininha VALUES (
    3,
    0.10,
    827832,
    210,
    13,
    NULL
);

INSERT INTO maquininha VALUES (
    4,
    0.09,
    923461,
    419,
    14,
    NULL
);

INSERT INTO maquininha VALUES (
    5,
    0.13,
    379349,
    957,
    15,
    NULL
);

INSERT INTO maquininha VALUES (
    6,
    0.12,
    78307,
    845,
    16,
    NULL
);

INSERT INTO maquininha VALUES (
    7,
    0.11,
    586877,
    593,
    17,
    NULL
);

INSERT INTO maquininha VALUES (
    8,
    0.10,
    315135,
    232,
    NULL,
    8
);

INSERT INTO maquininha VALUES (
    9,
    0.13,
    667023,
    755,
    NULL,
    9
);

INSERT INTO maquininha VALUES (
    10,
    0.14,
    666687,
    643,
    NULL,
    10
);

COMMIT;
