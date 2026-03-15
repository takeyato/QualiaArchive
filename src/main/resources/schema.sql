create table schedule (
    id bigint primary key,
    title varchar(255),
    start_at timestamp,
    end_at timestamp,
    place varchar(255),
    backgroundcolor varchar(20)
);
