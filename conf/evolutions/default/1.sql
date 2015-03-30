# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comentario (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  constraint pk_comentario primary key (id))
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




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table comentario;

drop table game;

drop table plataforma;

drop table tutorial;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

