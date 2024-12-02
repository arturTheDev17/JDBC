create table tb_clube
(
    idClube      int auto_increment
        primary key,
    nome         varchar(150) not null,
    dataFundacao varchar(150) not null
);

create table tb_clubeliga
(
    idLiga  int not null,
    idClube int not null,
    primary key (idClube, idLiga),
    constraint tb_clubeliga_ibfk_1
        foreign key (idLiga) references tb_liga (idLiga)
            on update cascade on delete cascade,
    constraint tb_clubeliga_ibfk_2
        foreign key (idClube) references tb_clube (idClube)
            on update cascade on delete cascade
);
#
# create index idLiga
#     on tb_clubeliga (idLiga);

create table tb_jogador
(
    idJogador int auto_increment
        primary key,
    nome      varchar(150) not null,
    idade     int          not null,
    idClube   int          null,
    posicao   varchar(150) null,
    constraint tb_jogador_ibfk_1
        foreign key (idClube) references tb_clube (idClube)
            on update cascade on delete cascade
);

create table tb_liga
(
    idLiga      int auto_increment
        primary key,
    nome        varchar(150) not null,
    anoFundacao int          not null
);

create table tb_treinador
(
    idTreinador int auto_increment
        primary key,
    nome        varchar(150) not null,
    idClube     int          not null,
    experiencia int          not null,
    constraint tb_treinador_ibfk_1
        foreign key (idClube) references tb_clube (idClube)
            on update cascade on delete cascade
);



