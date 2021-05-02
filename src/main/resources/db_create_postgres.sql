# docker run -p 5432:5432 -id -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=test-db -v /var/run/postgresql:/var/run/postgresql --name postgres12 postgres:12-alpine
# psql -U postgres -d test_db -p 5432

create sequence personne_id_seq;
create table personne (
   id bigint not null default nextval('personne_id_seq') PRIMARY KEY,
   ps_nom varchar(60),
   ps_num_ss varchar(60)
);
alter sequence personne_id_seq owned by personne.id;

create sequence voiture_id_seq;
create table voiture (
    id bigint not null default nextval('voiture_id_seq') PRIMARY KEY,
    vt_serie varchar(60),
    owner_id bigint,
    foreign key (owner_id) references personne (id) on delete set null
);
alter sequence voiture_id_seq owned by voiture.id;
