create table limit_reservation_archive
(
    uuid uuid primary key,
    client_id bigint,
    create_time timestamp not null,
    finish_time timestamp not null,
    transaction_status varchar(255) not null
);
