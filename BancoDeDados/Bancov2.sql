-- Generated by Oracle SQL Developer Data Modeler 21.4.2.059.0838
--   at:        2022-09-16 10:51:54 BRT
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE


CREATE TABLE cartao_credito (
    id_cartao_credito INTEGER NOT NULL,
    numero_credito    INTEGER NOT NULL,
    cvv               NUMBER(3,0) NOT NULL,
    fatura            NUMBER(38, 2) NOT NULL,
    data_vencimento   DATE NOT NULL,
    juros_credito     NUMBER(5, 2) NOT NULL,
    limite_credito    NUMBER(38, 2) NOT NULL,
    id_cliente        INTEGER NOT NULL
);

ALTER TABLE cartao_credito ADD CONSTRAINT cartao_credito_pk PRIMARY KEY ( id_cartao_credito );

ALTER TABLE cartao_credito ADD CONSTRAINT cartao_credito_num_un UNIQUE ( numero_credito );

ALTER TABLE cartao_credito ADD CONSTRAINT cartao_credito_limite_check CHECK ( fatura < limite_credito );

CREATE SEQUENCE seq_cartao_credito
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
    cpf             VARCHAR2(14),
    cnpj            VARCHAR2(20),
    nome            VARCHAR2(38) NOT NULL,
    sobrenome       VARCHAR2(100),
    telefone        NUMBER(38) NOT NULL,
    email           VARCHAR2(38) NOT NULL,
    endereco        VARCHAR2(100) NOT NULL,
    cep             VARCHAR2(9)  NOT NULL,
    sexo            VARCHAR2(9),
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
                                                
 ALTER TABLE clientes
    ADD CONSTRAINT sexo_check_fisico CHECK ( ( ( cpf IS NOT NULL )
                                             AND ( sexo IS NOT NULL ) )
                                           OR ( ( cnpj IS NOT NULL )
                                                AND ( sexo IS NULL ) ) );
                                               
 ALTER TABLE clientes
  ADD CONSTRAINT sexo_check CHECK ((sexo='MASCULINO')OR(sexo='FEMININO'));
                                                
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

CREATE TABLE USUARIOS(
     ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
     numero INTEGER NOT NULL,
     senha VARCHAR2(100) NOT NULL
);

alter table usuarios add constraint usuarios_pk primary key (id);

-- PARA INSERIR: insert into usuarios(numero,senha) values (NUMERO DA CONTA,SENHA);

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
    limite_valor   NUMBER(38, 2) NOT NULL,
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
        
ALTER TABLE USUARIOS 
    ADD CONSTRAINT usuarios_fk FOREIGN KEY (numero)
        REFERENCES conta_corrente (numero);
        
--Populando Clientes

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    '443.434.555-15',
    NULL,
    'Ricardo',
    'Rodriges',
    1199146335,
    'ricaro@gmail.com',
    'Rua Domingos Marino 1182',
    '15810-090',
    'MASCULINO',
    TO_DATE('1972/11/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    '554.541.950-06',
    NULL,
    'Rodolfo',
    'Dias',
    1180406528,
    'rodolfo@gmail.com',
    'Rua Irmã Efigênia 255',
    '04017-060',
    'MASCULINO',
    TO_DATE('1977/10/15 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    '331.995.460-13',
    NULL,
    'João',
    'Fernandez',
    1178809888,
    'joaofe@gmail.com',
    'Rua José de La Morena 430',
    '15061-753',
    'MASCULINO',
    TO_DATE('1972/11/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    '009.028.500-08',
    NULL,
    'Maria',
    'Gomes',
    1961783730,
    'magomes@gmail.com',
    'Avenida Américo Brasiliense 661',
    '13405-244',
    'FEMININO',
    TO_DATE('1992/04/25 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    '562.526.100-89',
    NULL,
    'Bob',
    'Rogers',
    8486483629,
    'bobrogers@hotmail.com',
    'Rua Abel Cabral 1265',
    '59080-550',
    'MASCULINO',
    TO_DATE('1972/11/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    '522.718.820-36',
    NULL,
    'Joel',
    'Genuino',
    4395552068,
    'joel@ig.com',
    'Rua Guaíra 929',
    '86188-520',
    'MASCULINO',
    TO_DATE('2000/11/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    NULL,
    '26.152.361/0001-47',
    'Dia',
    NULL,
    1162783347,
    'dia@dia.com',
    'Rua Bento Fernandes 1014',
    '07160-050',
    NULL,
    NULL
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    NULL,
    '01.344.082/0001-54',
    'Adminitração LTA',
    NULL,
    6759949168,
    'admDourados@gmail.com',
    'Rua José Ademar R. Perdomo 1084',
    '79814-090',
    NULL,
    NULL
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    NULL,
    '34.772.365/0001-56',
    'Pão de Açucar',
    NULL,
    5599422771,
    'paodeacucar@gmail.com',
    'Travessa Caroba 1766',
    '98805-785',
    NULL,
    NULL
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    NULL,
    '63.823.510/0001-89',
    'Razer',
    NULL,
    3875917026,
    'razer@hotmail.com',
    'Rua Francisco Gonçalves Santos 1580',
    '39401-430',
    NULL,
    NULL
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    '372.091.150-05',
    NULL,
    'Donal',
    'H. Rich',
    3103026774,
    'DonaldHRich@teleworm.us',
    '3122 Libby Street',
    '90291',
    'MASCULINO',
    TO_DATE('1973/09/24 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    '537.924.930-20',
    NULL,
    'Mateus',
    'Oliveira Lima',
    1163696465,
    'MateusOliveiraLima@dayrep.com',
    'Estrada da Alpina 351',
    '06278-020',
    'MASCULINO',
    TO_DATE('1974/07/23 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    '207.206.570-40',
    NULL,
    'Murilo ',
    'Cunha Barbosa',
    1239358114,
    'MuriloCunhaBarbosa@dayrep.com',
    'Rua Vinte e Oito de Setembro 833',
    '12282-270',
    'MASCULINO',
    TO_DATE('1978/05/22 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    '617.227.140-77',
    NULL,
    'Guilherme',
    'Rodrigues Cardoso',
    2133858881,
    'GuilhermeRodriguesCardoso@rhyta.com',
    'Rua Sete 496',
    '24738-522',
    'MASCULINO',
    TO_DATE('1967/10/18 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

INSERT INTO clientes VALUES (
    seq_clientes.NEXTVAL,
    '122.681.360-78',
    NULL,
    'Mariana',
    'Almeida Goncalves',
    8549209837,
    'MarianaAlmeidaGoncalves@armyspy.com',
    'Vila Neza 1405',
    '60540-281',
    'FEMININO',
    TO_DATE('1991/12/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss')
);

--Populando Conta_Conrrente

INSERT INTO conta_corrente VALUES (
    seq_conta_corrente.NEXTVAL,
    52245692,
    1300.00,
    0.05,
    sysdate,
    1,
    'ricaro@gmail.com'
);

INSERT INTO conta_corrente VALUES (
    seq_conta_corrente.NEXTVAL,
    49138857,
    1300.00,
    0.05,
    TO_DATE('2022/09/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    2,
    '554.541.950-06'
);

INSERT INTO conta_corrente VALUES (
    seq_conta_corrente.NEXTVAL,
    84251919,
    5909.90,
    0.05,
    TO_DATE('2020/11/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    3,
    'bobrogers@hotmail.com'
);

INSERT INTO conta_corrente VALUES (
    seq_conta_corrente.NEXTVAL,
    87307954,
    1300.00,
    0.05,
    sysdate,
    4,
    'magomes@gmail.com'
);

INSERT INTO conta_corrente VALUES (
    seq_conta_corrente.NEXTVAL,
    69603459,
    8990.90,
    0.05,
    TO_DATE('2021/12/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    5,
    NULL
);

INSERT INTO conta_corrente VALUES (
    seq_conta_corrente.NEXTVAL,
    82637708,
    1477.00,
    0.05,
    TO_DATE('2018/09/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    6,
    NULL
);

INSERT INTO conta_corrente VALUES (
    seq_conta_corrente.NEXTVAL,
    24150589,
    9756.00,
    0.05,
    TO_DATE('2019/07/13 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    7,
    NULL
);

INSERT INTO conta_corrente VALUES (
    seq_conta_corrente.NEXTVAL,
    79803778,
    12128.00,
    0.05,
    TO_DATE('2017/06/06 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    8,
    NULL
);

INSERT INTO conta_corrente VALUES (
    seq_conta_corrente.NEXTVAL,
    15007619,
    1545.55,
    0.05,
    TO_DATE('2016/04/25 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    9,
    NULL
);

INSERT INTO conta_corrente VALUES (
    seq_conta_corrente.NEXTVAL,
    74728804,
    13789.99,
    0.05,
    TO_DATE('2019/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    10,
    'razer@hotmail.com'
);

-- Populando Conta_Poupanca

INSERT INTO conta_poupanca VALUES (
    seq_conta_poupanca.NEXTVAL,
    36082159,
    1232.56,
    0.01,
    TO_DATE('2019/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2019/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    1
);

INSERT INTO conta_poupanca VALUES (
    seq_conta_poupanca.NEXTVAL,
    61216444,
    177.56,
    0.01,
    TO_DATE('2010/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2020/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    2
);

INSERT INTO conta_poupanca VALUES (
    seq_conta_poupanca.NEXTVAL,
    32029779,
    3232.56,
    0.01,
    TO_DATE('2019/12/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2019/12/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    3
);

INSERT INTO conta_poupanca VALUES (
    seq_conta_poupanca.NEXTVAL,
    53655095,
    6783.56,
    0.01,
    TO_DATE('2022/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2022/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    4
);

INSERT INTO conta_poupanca VALUES (
    seq_conta_poupanca.NEXTVAL,
    44336697,
    1232.56,
    0.01,
    TO_DATE('2017/01/04 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2017/02/04 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    5
);

INSERT INTO conta_poupanca VALUES (
    seq_conta_poupanca.NEXTVAL,
    23820100,
    87522.56,
    0.01,
    TO_DATE('2017/05/23 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2017/05/23 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    6
);

INSERT INTO conta_poupanca VALUES (
    seq_conta_poupanca.NEXTVAL,
    94314595,
    31243.96,
    0.01,
    TO_DATE('2018/08/29 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2018/08/29 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    7
);

INSERT INTO conta_poupanca VALUES (
    seq_conta_poupanca.NEXTVAL,
    62807303,
    57554.86,
    0.01,
    TO_DATE('2019/04/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2019/04/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    8
);

INSERT INTO conta_poupanca VALUES (
    seq_conta_poupanca.NEXTVAL,
    45755749,
    8167.45,
    0.01,
    TO_DATE('2022/02/08 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2022/02/08 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    9
);

INSERT INTO conta_poupanca VALUES (
    seq_conta_poupanca.NEXTVAL,
    79153443,
    30423.32,
    0.01,
    TO_DATE('2016/04/28 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2016/04/28 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    10
);

-- Populando Documento_Conta

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    52623,
    1,
    NULL,
    1
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    27715,
    2,
    NULL,
    2
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    26158,
    3,
    NULL,
    3
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    17450,
    4,
    NULL,
    4
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    52062,
    5,
    NULL,
    5
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    30171,
    6,
    NULL,
    6
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    18967,
    7,
    NULL,
    7
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    12503,
    8,
    NULL,
    8
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    14109,
    9,
    NULL,
    9
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    88967,
    10,
    NULL,
    10
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    77106,
    1,
    1,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    69464,
    2,
    2,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    94727,
    3,
    3,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    75205,
    4,
    4,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    46380,
    5,
    5,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    63408,
    6,
    6,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    40100,
    7,
    7,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    43918,
    8,
    8,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    48442,
    9,
    9,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    21965,
    10,
    10,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    52623,
    11,
    1,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    27715,
    12,
    2,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    26158,
    13,
    3,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    17450,
    14,
    4,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    52062,
    15,
    5,
    NULL
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    77106,
    11,
    NULL,
    1
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    69464,
    12,
    NULL,
    2
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    94727,
    13,
    NULL,
    3
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    75205,
    14,
    NULL,
    4
);

INSERT INTO documento_conta VALUES (
    seq_documento_conta.NEXTVAL,
    46380,
    15,
    NULL,
    5
);

--Populando Emprestimos

INSERT INTO emprestimos VALUES (
    seq_emprestimos.NEXTVAL,
    81757,
    0.15,
    20,
    TO_DATE('2019/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2019/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    1
);

INSERT INTO emprestimos VALUES (
    seq_emprestimos.NEXTVAL,
    22650,
    0.2,
    25,
    TO_DATE('2017/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2017/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    2
);

INSERT INTO emprestimos VALUES (
    seq_emprestimos.NEXTVAL,
    48610,
    0.25,
    27,
    TO_DATE('2018/06/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2018/07/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    4
);

INSERT INTO emprestimos VALUES (
    seq_emprestimos.NEXTVAL,
    60915,
    0.15,
    10,
    TO_DATE('2015/04/01 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2015/05/01 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    5
);

INSERT INTO emprestimos VALUES (
    seq_emprestimos.NEXTVAL,
    24929,
    0.2,
    5,
    TO_DATE('2020/12/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2021/01/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    7
);

INSERT INTO emprestimos VALUES (
    seq_emprestimos.NEXTVAL,
    14818,
    0.17,
    11,
    TO_DATE('2019/03/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2019/04/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    10
);

INSERT INTO emprestimos VALUES (
    seq_emprestimos.NEXTVAL,
    78329,
    0.19,
    22,
    TO_DATE('2019/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2019/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    11
);

INSERT INTO emprestimos VALUES (
    seq_emprestimos.NEXTVAL,
    998,
    0.14,
    37,
    TO_DATE('2017/10/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2017/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    1
);

INSERT INTO emprestimos VALUES (
    seq_emprestimos.NEXTVAL,
    19875,
    0.11,
    42,
    TO_DATE('2015/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2015/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    4
);

INSERT INTO emprestimos VALUES (
    seq_emprestimos.NEXTVAL,
    77896,
    0.16,
    15,
    TO_DATE('2016/08/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2016/09/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    5
);

--Populando Financiamentos

INSERT INTO financiamentos VALUES (
    seq_financiamento.NEXTVAL,
    8585,
    1400,
    27,
    0.27,
    3000,
    TO_DATE('2019/10/31', 'yyyy/mm/dd'),
    TO_DATE('2021/10/31', 'yyyy/mm/dd'),
    1
);

INSERT INTO financiamentos VALUES (
    seq_financiamento.NEXTVAL,
    37829,
    1700,
    32,
    0.20,
    0,
    TO_DATE('2019/11/30', 'yyyy/mm/dd'),
    TO_DATE('2022/10/31', 'yyyy/mm/dd'),
    2
);

INSERT INTO financiamentos VALUES (
    seq_financiamento.NEXTVAL,
    51466,
    3900,
    20,
    0.15,
    3500,
    TO_DATE('2016/05/31', 'yyyy/mm/dd'),
    TO_DATE('2021/10/31', 'yyyy/mm/dd'),
    3
);

INSERT INTO financiamentos VALUES (
    seq_financiamento.NEXTVAL,
    78893,
    1700,
    15,
    0.30,
    7000,
    TO_DATE('2014/07/30', 'yyyy/mm/dd'),
    TO_DATE('2018/10/31', 'yyyy/mm/dd'),
    4
);

INSERT INTO financiamentos VALUES (
    seq_financiamento.NEXTVAL,
    13749,
    3558,
    82,
    0.07,
    8871,
    TO_DATE('2018/06/15', 'yyyy/mm/dd'),
    TO_DATE('2021/10/31', 'yyyy/mm/dd'),
    3
);

INSERT INTO financiamentos VALUES (
    seq_financiamento.NEXTVAL,
    44540,
    6841,
    18,
    0.20,
    2239,
    TO_DATE('2017/04/16', 'yyyy/mm/dd'),
    TO_DATE('2022/11/30', 'yyyy/mm/dd'),
    2
);

INSERT INTO financiamentos VALUES (
    seq_financiamento.NEXTVAL,
    32626,
    7226,
    50,
    0.99,
    165,
    TO_DATE('2016/08/29', 'yyyy/mm/dd'),
    TO_DATE('2024/10/31', 'yyyy/mm/dd'),
    7
);

INSERT INTO financiamentos VALUES (
    seq_financiamento.NEXTVAL,
    7586,
    9899,
    40,
    0.09,
    468,
    TO_DATE('2015/11/15', 'yyyy/mm/dd'),
    TO_DATE('2028/12/31', 'yyyy/mm/dd'),
    9
);

INSERT INTO financiamentos VALUES (
    seq_financiamento.NEXTVAL,
    61959,
    7896,
    57,
    0.70,
    9728,
    TO_DATE('2019/12/31', 'yyyy/mm/dd'),
    TO_DATE('2025/11/30', 'yyyy/mm/dd'),
    10
);

INSERT INTO financiamentos VALUES (
    seq_financiamento.NEXTVAL,
    8210,
    5958,
    51,
    0.56,
    4607,
    TO_DATE('2018/10/03', 'yyyy/mm/dd'),
    TO_DATE('2024/10/30', 'yyyy/mm/dd'),
    11
);

--Populando Cartao de Debito

INSERT INTO cartao_debito VALUES (
    seq_cartao_debito.NEXTVAL,
    248199,
    0,
    1
);

INSERT INTO cartao_debito VALUES (
    seq_cartao_debito.NEXTVAL,
    903538,
    0,
    2
);

INSERT INTO cartao_debito VALUES (
    seq_cartao_debito.NEXTVAL,
    699293,
    3500,
    3
);

INSERT INTO cartao_debito VALUES (
    seq_cartao_debito.NEXTVAL,
    506755,
    2000,
    4
);

INSERT INTO cartao_debito VALUES (
    seq_cartao_debito.NEXTVAL,
    831486,
    1500,
    5
);

INSERT INTO cartao_debito VALUES (
    seq_cartao_debito.NEXTVAL,
    237614,
    0,
    6
);

INSERT INTO cartao_debito VALUES (
    seq_cartao_debito.NEXTVAL,
    731213,
    0,
    7
);

INSERT INTO cartao_debito VALUES (
    seq_cartao_debito.NEXTVAL,
    239028,
    4550,
    8
);

INSERT INTO cartao_debito VALUES (
    seq_cartao_debito.NEXTVAL,
    113782,
    0,
    9
);

INSERT INTO cartao_debito VALUES (
    seq_cartao_debito.NEXTVAL,
    475835,
    9900,
    10
);

-- Populando Cartao_Credito

INSERT INTO cartao_credito VALUES (
    seq_cartao_credito.NEXTVAL,
    663941,
    123,
    5022,
    TO_DATE('2022/10/03', 'yyyy/mm/dd'),
    0.23,
    120000,
    1
);

INSERT INTO cartao_credito VALUES (
    seq_cartao_credito.NEXTVAL,
    205818,
    322,
    3905,
    TO_DATE('2022/10/07', 'yyyy/mm/dd'),
    0.22,
    120000,
    2
);

INSERT INTO cartao_credito VALUES (
    seq_cartao_credito.NEXTVAL,
    888290,
    463,
    1569,
    TO_DATE('2022/10/08', 'yyyy/mm/dd'),
    0.20,
    1600,
    3
);

INSERT INTO cartao_credito VALUES (
    seq_cartao_credito.NEXTVAL,
    984681,
    159,
    8374,
    TO_DATE('2022/10/13', 'yyyy/mm/dd'),
    0.13,
    120000,
    4
);

INSERT INTO cartao_credito VALUES (
    seq_cartao_credito.NEXTVAL,
    420910,
    157,
    5652,
    TO_DATE('2022/10/23', 'yyyy/mm/dd'),
    0.33,
    9999,
    5
);

INSERT INTO cartao_credito VALUES (
    seq_cartao_credito.NEXTVAL,
    198254,
    739,
    3948,
    TO_DATE('2022/10/13', 'yyyy/mm/dd'),
    0.40,
    7800,
    6
);

INSERT INTO cartao_credito VALUES (
    seq_cartao_credito.NEXTVAL,
    788792,
    269,
    349,
    TO_DATE('2022/10/15', 'yyyy/mm/dd'),
    0.32,
    15000,
    7
);

INSERT INTO cartao_credito VALUES (
    seq_cartao_credito.NEXTVAL,
    594243,
    433,
    4785,
    TO_DATE('2022/10/04', 'yyyy/mm/dd'),
    0.56,
    30000,
    8
);

INSERT INTO cartao_credito VALUES (
    seq_cartao_credito.NEXTVAL,
    825963,
    105,
    3429,
    TO_DATE('2022/10/08', 'yyyy/mm/dd'),
    0.15,
    100000,
    9
);

INSERT INTO cartao_credito VALUES (
    seq_cartao_credito.NEXTVAL,
    825965,
    115,
    34366,
    TO_DATE('2022/10/09', 'yyyy/mm/dd'),
    0.15,
    120000,
    10
);

--Populando PIX

INSERT INTO pix VALUES (
    seq_pix.NEXTVAL,
    'magomes@gmail.com',
    '695.169.910-09',
    8955,
    TO_DATE('2022/09/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    0,
    NULL,
    4
);

INSERT INTO pix VALUES (
    seq_pix.NEXTVAL,
    'ricaro@gmail.com',
    '327.632.620-58',
    6844,
    TO_DATE('2021/10/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    0,
    NULL,
    1
);

INSERT INTO pix VALUES (
    seq_pix.NEXTVAL,
    '554.541.950-06',
    '758.636.350-10',
    810,
    TO_DATE('2020/05/14 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    3500,
    NULL,
    4
);

INSERT INTO pix VALUES (
    seq_pix.NEXTVAL,
    'bobrogers@hotmail.com',
    '361.579.100-20',
    301,
    TO_DATE('2022/04/27 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    1000,
    NULL,
    3
);

INSERT INTO pix VALUES (
    seq_pix.NEXTVAL,
    'razer@hotmail.com',
    '465.808.060-52',
    1269,
    TO_DATE('2021/02/22 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    0,
    NULL,
    10
);

INSERT INTO pix VALUES (
    seq_pix.NEXTVAL,
    'magomes@gmail.com',
    '045.339.420-59',
    3576,
    TO_DATE('2019/10/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    0,
    NULL,
    4
);

INSERT INTO pix VALUES (
    seq_pix.NEXTVAL,
    'ricaro@gmail.com',
    '185.638.210-91',
    3592,
    TO_DATE('2022/11/30 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    0,
    NULL,
    1
);

INSERT INTO pix VALUES (
    seq_pix.NEXTVAL,
    '554.541.950-06',
    '482.618.090-05',
    2935,
    TO_DATE('2021/12/15 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    3500,
    NULL,
    4
);

INSERT INTO pix VALUES (
    seq_pix.NEXTVAL,
    'bobrogers@hotmail.com',
    '557.096.190-94',
    152,
    TO_DATE('2020/12/31 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    1000,
    NULL,
    3
);

INSERT INTO pix VALUES (
    seq_pix.NEXTVAL,
    'razer@hotmail.com',
    '770.155.240-58',
    4830,
    TO_DATE('2022/07/21 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    0,
    NULL,
    10
);

-- Populando Maquininha

INSERT INTO maquininha VALUES (
    seq_maquininha.NEXTVAL,
    0.13,
    566236,
    812,
    1
);

INSERT INTO maquininha VALUES (
    seq_maquininha.NEXTVAL,
    0.12,
    277833,
    897,
    2
);

INSERT INTO maquininha VALUES (
    seq_maquininha.NEXTVAL,
    0.10,
    827832,
    210,
    3
);

INSERT INTO maquininha VALUES (
    seq_maquininha.NEXTVAL,
    0.09,
    923461,
    419,
    4
);

INSERT INTO maquininha VALUES (
    seq_maquininha.NEXTVAL,
    0.13,
    379349,
    957,
    5
);

INSERT INTO maquininha VALUES (
    seq_maquininha.NEXTVAL,
    0.12,
    78307,
    845,
    6
);

INSERT INTO maquininha VALUES (
    seq_maquininha.NEXTVAL,
    0.11,
    586877,
    593,
    7
);

INSERT INTO maquininha VALUES (
    seq_maquininha.NEXTVAL,
    0.10,
    315135,
    232,
    8
);

INSERT INTO maquininha VALUES (
    seq_maquininha.NEXTVAL,
    0.13,
    667023,
    755,
    9
);

INSERT INTO maquininha VALUES (
    seq_maquininha.NEXTVAL,
    0.14,
    666687,
    643,
    10
);

COMMIT;



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
