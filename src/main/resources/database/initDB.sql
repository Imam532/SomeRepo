/*==============================================================*/
/* DBMS name:      PostgreSQL 10                               */
/* Created on:     23.06.2020 19:19                          */
/*==============================================================*/

drop schema if exists payment;


/*==============================================================*/
/* User: payment                                                */
/*==============================================================*/
create schema payment;

/*==============================================================*/
/* Table: payments                                              */
/*==============================================================*/
create table payment.payments
(
    id                serial       not null,
    merchant_id       integer      null,
    invoice_id        integer      null,
    card_id           integer      null,
    amount_micros     money        null,
    currency          varchar(5)   null,
    status            int2         null,
    paysys_order_id   varchar(50)  null,
    created_at        timestamptz  null,
    paysys_order_date varchar(50)  null,
    auth_code         char(6)      null,
    card_num          varchar(21)  null,
    payment_url       varchar(500) null,
    expiration        timestamptz  null,
    uid               varchar(50)  null,
    constraint pk_payments primary key (id)
);

create table payment.registry_payments
(
    id                serial      not null,
    reg_file_id       integer     null,
    payment_id        integer     null,
    merchant_code     varchar(14) null,
    terminal          varchar(8)  null,
    payment_order_num integer     null,
    oper_date         timestamptz null,
    payment_oper_date timestamptz null,
    oper_sum          money       null,
    comiss_sum        money       null,
    payment_order_sum money       null,
    card_num          varchar(21) null,
    auth_code         char(6)     null,
    oper_type         varchar(50) null,
    constraint pk_registry_payments primary key (id)
);

create table payment.registry_file
(
    id            serial      not null,
    reg_file_name varchar(50) null,
    load_time     timestamptz null,
    status        int2        null,
    constraint pk_registry_file primary key (id)
);

alter table payment.registry_payments
    add constraint fk_payments_reference_payments foreign key (payment_id)
        references payment.payments (id)
        on delete restrict on update restrict;

alter table payment.registry_payments
    add constraint fk_payments_reference_registry_file foreign key (reg_file_id)
        references payment.registry_file (id)
        on delete restrict on update restrict;


/*==============================================================*/
/* populate           */
/*==============================================================*/
insert into payment.registry_file (reg_file_name, load_time, status)
values ('78XXXXXXXX_XXXXXX_XXXXXXXXXXXXX_171209_755928.xlsx', '2020-06-26 21:00:00.000000', '1'),
       ('77XXXXXXXX_XXXXXX_XXXXXXXXXXXXX_171209_755928.xlsx', '2020-06-26 21:00:00.000000', '0');


insert into payment.payments (merchant_id, invoice_id, card_id, amount_micros, currency, status, paysys_order_id,
                      created_at, paysys_order_date, auth_code, card_num, payment_url, expiration, uid)
values ('78', '22', '1', '4303,43', 'рубли',
        '1', '12345', '2017-12-07 21:00:00.000000 +00:00', '2017-12-07 21:00:00.000000 +00:00',
        'a', '543210XXXXXX1500', 'hhtps', '20.01.2022', 'ytrefhy'),
       ('78', '22', '2', '-1539,80', 'рубли',
        '1', '12345', '2017-12-07 21:00:00.000000 +00:00', '2017-12-07 21:00:00.000000 +00:00',
        's', '598765XXXXXX8792', 'hhtps', '20.01.2022', 'ytrefhy'),
       ('78', '22', '3', '-2262,20', 'рубли',
        '1', '12345', '2017-12-07 21:00:00.000000 +00:00', '2017-12-07 21:00:00.000000 +00:00',
        'd', '522333XXXXXX0033', 'hhtps', '20.01.2022', 'ytrefhy'),
       ('79', '22', '4', '-2262,20', 'рубли',
        '1', '12345', '2017-12-07 21:00:00.000000 +00:00', '2017-12-07 21:00:00.000000 +00:00',
        'd', '522333XXXXXX0033', 'hhtps', '20.01.2022', 'ytrefhy');

/*==============================================================*/
/* footer            */
/*==============================================================*/

/*==============================================================*/
/* grant permission for users to schemas         */
/*==============================================================*/
