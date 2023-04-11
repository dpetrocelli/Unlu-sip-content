create table public.message (
    id uuid not null,
    created_date timestamp not null,
    last_modified_date timestamp not null,
    content text,
    primary key (id)
);

GRANT SELECT, INSERT, UPDATE ON TABLE public.message TO application;