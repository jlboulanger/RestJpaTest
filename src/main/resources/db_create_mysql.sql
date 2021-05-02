
create table personne (
   id integer(12) auto_increment primary key ,
   ps_nom varchar(60),
   ps_num_ss varchar(60)
);

create table voiture (
    id integer(12) primary key auto_increment,
    vt_serie varchar(60),
    owner_id integer(12),
    foreign key (owner_id) references personne (id) on delete set null
);
