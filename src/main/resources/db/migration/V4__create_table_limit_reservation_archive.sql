create table limit_reservation_archive
(
    uuid uuid primary key,
    client_id bigint,
    sum numeric(18, 2),
    create_time timestamp not null,
    finish_time timestamp not null,
    transaction_status varchar(255) not null
);
