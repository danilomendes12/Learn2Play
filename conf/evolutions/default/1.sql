# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comentario (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  creator_id                bigint,
  tutorial_id               bigint,
  constraint pk_comentario primary key (id))
;

create table distribution (
  id                        bigint auto_increment not null,
  game_id                   bigint,
  console_id                bigint,
  constraint pk_distribution primary key (id))
;

create table game (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_game primary key (id))
;

create table plataforma (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_plataforma primary key (id))
;

create table tutorial (
  id                        bigint auto_increment not null,
  text                      varchar(255),
  tutorial_image            longblob,
  recomendation_number      integer,
  user_id                   bigint,
  distribution_id           bigint,
  constraint pk_tutorial primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  nickname                  varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  profile_image             longblob,
  constraint pk_user primary key (id))
;

alter table comentario add constraint fk_comentario_creator_1 foreign key (creator_id) references user (id) on delete restrict on update restrict;
create index ix_comentario_creator_1 on comentario (creator_id);
alter table comentario add constraint fk_comentario_tutorial_2 foreign key (tutorial_id) references tutorial (id) on delete restrict on update restrict;
create index ix_comentario_tutorial_2 on comentario (tutorial_id);
alter table distribution add constraint fk_distribution_game_3 foreign key (game_id) references game (id) on delete restrict on update restrict;
create index ix_distribution_game_3 on distribution (game_id);
alter table distribution add constraint fk_distribution_console_4 foreign key (console_id) references plataforma (id) on delete restrict on update restrict;
create index ix_distribution_console_4 on distribution (console_id);
alter table tutorial add constraint fk_tutorial_user_5 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_tutorial_user_5 on tutorial (user_id);
alter table tutorial add constraint fk_tutorial_distribution_6 foreign key (distribution_id) references distribution (id) on delete restrict on update restrict;
create index ix_tutorial_distribution_6 on tutorial (distribution_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table comentario;

drop table distribution;

drop table game;

drop table plataforma;

drop table tutorial;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

