-- Gerado por Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   em:        2022-09-14 14:17:30 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

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

insert into conta_corrente_fisica values (1,1,'443.434.555-15','Rodriges',1300.00,'Masculino',sysdate);
insert into conta_corrente_fisica values (2,2,'554.541.950-06','Dias',1300.00,'Masculino',TO_DATE('2020/10/15 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_fisica values (3,3,'331.995.460-13','Fernandez',1300.00,'Masculino', TO_DATE('2021/11/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_fisica values (4,4,'009.028.500-08','Gomes',1300.00,'Feminino',TO_DATE('2022/04/25 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_fisica values (5,5,'562.526.100-89','Rogers',1300.00,'Masculino',TO_DATE('2021/11/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_fisica values (6,6,'522.718.820-36','Genuino',1300.00,'Masculino',TO_DATE('2020/11/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_fisica values (7,7,'372.091.150-05','H. Rich',1300.00,'Masculino',TO_DATE('2021/09/24 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_fisica values (8,8,'537.924.930-20','Oliveira Lima',1300.00,'Masculino',TO_DATE('2019/07/23 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_fisica values (9,9,'207.206.570-40','Cunha Barbosa',1300.00,'Masculino',TO_DATE('2018/05/22 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_fisica values (10,10,'617.227.140-77','Rodrigues Cardoso',1300.00,'Masculino',TO_DATE('2017/10/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));

-- Populando Conta Juridica

insert into conta_corrente_juridica values (11,1,'26.152.361/0001-47',82615,TO_DATE('2022/04/25 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_juridica values (12,2,'01.344.082/0001-54',264805,TO_DATE('2021/05/24 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_juridica values (13,3,'34.772.365/0001-56',8180614,TO_DATE('2020/06/27 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_juridica values (14,4,'63.823.510/0001-89',78398,TO_DATE('2019/07/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_juridica values (15,5,'78.467.557/0001-24',70045,TO_DATE('2019/07/05 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_juridica values (16,6,'45.639.521/0001-21',615788,TO_DATE('2020/10/07 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_juridica values (17,7,'22.297.758/0001-76',345715,TO_DATE('2021/12/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_juridica values (18,8,'32.801.052/0001-53',54011,TO_DATE('2020/12/27 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_juridica values (19,9,'36.243.893/0001-43',53743,TO_DATE('2022/01/23 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
insert into conta_corrente_juridica values (20,10,'37.573.315/0001-38',7347,TO_DATE('2021/08/12 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));

-- Populando Cliente

insert into cliente values (1,'Ricardo',1199146335,'ricaro@gmail.com','Rua Domingos Marino 1182',1,null,895466,1);
insert into cliente values (2,'Rodolfo',1180406528,'rodolfo@gmail.com','Rua Irmã Efigênia 255',2,null,895467,1);
insert into cliente values (3,'João',1178809888,'joaofe@gmail.com','Rua José de La Morena 430',3,null,895468,1);
insert into cliente values (4,'Maria',1961783730,'magomes@gmail.com','Avenida Américo Brasiliense 661',4,null,895469,1);
insert into cliente values (5,'Bob',8486483629,'bobrogers@hotmail.com','Rua Abel Cabral 1265',5,null,895470,1);
insert into cliente values (6,'Joel',4395552068,'joel@ig.com','Rua Guaíra 929',6,null,895476,1);
insert into cliente values (7,'Donal',3103026774,'DonaldHRich@teleworm.us','3122 Libby Street',7,null,895486,1);
insert into cliente values (8,'Mateus',1163696465,'MateusOliveiraLima@dayrep.com','Estrada da Alpina 351',8,null,899466,1);
insert into cliente values (9,'Murilo',1239358114,'MuriloCunhaBarbosa@dayrep.com','Rua Vinte e Oito de Setembro 833',9,null,865466,1);
insert into cliente values (10,'Guilherme',2133858881,'GuilhermeRodriguesCardoso@rhyta.com','Rua Sete 496',10,null,675466,1);
insert into cliente values (11,'Dia',1162783347,'dia@dia.com','Rua Bento Fernandes 1014',null,11,395466,1);
insert into cliente values (12,'Adminitração LTA',6759949168,'admDourados@gmail.com','Rua José Ademar R. Perdomo 1084',null,12,555666,1);
insert into cliente values (13,'Pão de Açucar',1199146335,'paodeacucar@gmail.com','Travessa Caroba 1766',null,13,395436,1);
insert into cliente values (14,'Razer',3875917026,'razer@hotmail.com','Rua Francisco Gonçalves Santos 1580',null,14,992456,1);
insert into cliente values (15,'HyperX',3103026774,'hyperx@gmail.com','Vila Neza 1405',null,15,915443,1);
insert into cliente values (16,'DELL',1197166536,'dell@gmail.com','Rua Sete 500',null,16,905066,1);
insert into cliente values (17,'MICROSOFT',1199146335,'mircosoft@microsoft.com','Rua Domingos Mariana 1382',null,17,555787,1);
insert into cliente values (18,'Emporio Informatica',1199146335,'emporioinfo@gmail.com','Rua Dr Cesar 1512',null,18,555789,1);
insert into cliente values (19,'Buggati',1199146335,'buggati@hotmail.com','AV Braz Leme 1189',null,19,566789,1);
insert into cliente values (20,'CVC',1199146335,'cvc@gmail.com','Rua Leonardo Pardilho 1504',null,20,569789,1);

-- Populando Poupanca

insert into poupanca values (1,13148,0.01,TO_DATE('2017/11/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),TO_DATE('2022/10/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),null,1);
insert into poupanca values (2,78947,0.01,TO_DATE('2018/11/19 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),TO_DATE('2022/10/19 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),null,2);
insert into poupanca values (3,31927,0.01,TO_DATE('2019/11/20 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),TO_DATE('2022/10/20 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),null,3);
insert into poupanca values (4,5298,0.01,TO_DATE('2020/11/15 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),TO_DATE('2022/10/15 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),null,4);
insert into poupanca values (5,96743,0.01,TO_DATE('2021/11/17 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),TO_DATE('2022/10/17 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),null,5);
insert into poupanca values (6,65439,0.01,TO_DATE('2016/11/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),TO_DATE('2022/10/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),11,null);
insert into poupanca values (7,64644,0.01,TO_DATE('2017/11/14 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),TO_DATE('2022/10/14 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),12,null);
insert into poupanca values (8,94574,0.01,TO_DATE('2018/11/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),TO_DATE('2022/10/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),13,null);
insert into poupanca values (9,26405,0.01,TO_DATE('2019/11/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),TO_DATE('2022/10/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),14,null);
insert into poupanca values (10,53331,0.01,TO_DATE('2020/11/13 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),TO_DATE('2022/10/13 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),15,null);

-- Populando Cartao de Credito

INSERT INTO cartao_de_credito VALUES (
    1,
    663941,
    5022,
    TO_DATE('2022/10/03', 'yyyy/mm/dd'),
    0.23,
    120000,
    null,
    1
);

INSERT INTO cartao_de_credito VALUES (
    2,
    205818,
    3905,
    TO_DATE('2022/10/07', 'yyyy/mm/dd'),
    0.22,
    120000,
    null,
    2
);

INSERT INTO cartao_de_credito VALUES (
    3,
    888290,
    1569,
    TO_DATE('2022/10/08', 'yyyy/mm/dd'),
    0.20,
    1600,
    null,
    3
);

INSERT INTO cartao_de_credito VALUES (
    4,
    984681,
    8374,
    TO_DATE('2022/10/13', 'yyyy/mm/dd'),
    0.13,
    120000,
    null,
    4
);

INSERT INTO cartao_de_credito VALUES (
    5,
    420910,
    5652,
    TO_DATE('2022/10/23', 'yyyy/mm/dd'),
    0.33,
    9999,
    null,
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
    null
);

INSERT INTO cartao_de_credito VALUES (
    7,
    788792,
    349,
    TO_DATE('2022/10/15', 'yyyy/mm/dd'),
    0.32,
    15000,
    12,
    null
);

INSERT INTO cartao_de_credito VALUES (
    8,
    594243,
    4785,
    TO_DATE('2022/10/04', 'yyyy/mm/dd'),
    0.56,
    30000,
    13,
    null
);

INSERT INTO cartao_de_credito VALUES (
    9,
    825963,
    3429,
    TO_DATE('2022/10/08', 'yyyy/mm/dd'),
    0.15,
    100000,
    14,
    null
);

INSERT INTO cartao_de_credito VALUES (
    10,
    825965,
    34366,
    TO_DATE('2022/10/09', 'yyyy/mm/dd'),
    0.15,
    120000,
    15,
    null
);

-- Populando Cartao de Debito

INSERT INTO cartao_debito VALUES (
    1,
    248199,
    0,
    null,
    1
);

INSERT INTO cartao_debito VALUES (
    2,
    903538,
    0,
    null,
    2
);

INSERT INTO cartao_debito VALUES (
    3,
    699293,
    3500,
    null,
    3
);

INSERT INTO cartao_debito VALUES (
    4,
    506755,
    2000,
    null,
    4
);

INSERT INTO cartao_debito VALUES (
    5,
    831486,
    1500,
    null,
    5
);

INSERT INTO cartao_debito VALUES (
    6,
    237614,
    0,
    11,
    null
);

INSERT INTO cartao_debito VALUES (
    7,
    731213,
    0,
    12,
    null
);

INSERT INTO cartao_debito VALUES (
    8,
    239028,
    4550,
    13,
    null
);

INSERT INTO cartao_debito VALUES (
    9,
    113782,
    0,
    14,
    null
);

INSERT INTO cartao_debito VALUES (
    10,
    475835,
    9900,
    15,
    null
);

-- Populando Emprestimos

INSERT INTO emprestimos VALUES (
    1,
    81757,
    0.15,
    20,
    TO_DATE('2019/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2019/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    null,
    1
);

INSERT INTO emprestimos VALUES (
    2,
    22650,
    0.2,
    25,
    TO_DATE('2017/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2017/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    null,
    2
);

INSERT INTO emprestimos VALUES (
    3,
    48610,
    0.25,
    27,
    TO_DATE('2018/06/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2018/07/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    null,
    4
);

INSERT INTO emprestimos VALUES (
    4,
    60915,
    0.15,
    10,
    TO_DATE('2015/04/01 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2015/05/01 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    null,
    5
);

INSERT INTO emprestimos VALUES (
    5,
    24929,
    0.2,
    5,
    TO_DATE('2020/12/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2021/01/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    null,
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
    null
);

INSERT INTO emprestimos VALUES (
    7,
    78329,
    0.19,
    22,
    TO_DATE('2019/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2019/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    12,
    null
);

INSERT INTO emprestimos VALUES (
    8,
    998,
    0.14,
    37,
    TO_DATE('2017/10/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2017/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    13,
    null
);

INSERT INTO emprestimos VALUES (
    9,
    19875,
    0.11,
    42,
    TO_DATE('2015/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2015/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    12,
    null
);

INSERT INTO emprestimos VALUES (
    10,
    77896,
    0.16,
    15,
    TO_DATE('2016/08/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2016/09/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    15,
    null
);

-- Populando Fianciamento

COMMIT;



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            10
-- CREATE INDEX                             0
-- ALTER TABLE                             41
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
