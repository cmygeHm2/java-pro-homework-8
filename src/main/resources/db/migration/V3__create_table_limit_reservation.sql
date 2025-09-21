create table limit_reservation
(
    uuid uuid primary key,
    client_id bigint,
    sum numeric(18, 2),
    create_time timestamp not null
);




