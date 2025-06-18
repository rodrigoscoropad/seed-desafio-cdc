create table author(
    id serial primary key,
    name text not null,
    email text not null,
    description varchar(200) not null,
    created_at timestamp with time zone,
    updated_at timestamp with time zone
)