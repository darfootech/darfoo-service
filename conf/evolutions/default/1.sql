# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bind (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  mac                       varchar(255) not null,
  timestamp                 bigint,
  due_date                  datetime,
  constraint uq_bind_mac unique (mac),
  constraint pk_bind primary key (id))
;

create table feed_back (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  username                  varchar(255),
  feedback                  varchar(255),
  timestamp                 bigint,
  due_date                  datetime,
  constraint pk_feed_back primary key (id))
;

create table menu (
  id                        bigint auto_increment not null,
  mac                       varchar(255),
  hostip                    varchar(255),
  uuid                      varchar(255),
  menuid                    integer,
  clickcount                bigint,
  constraint pk_menu primary key (id))
;

create table menu_time (
  id                        bigint auto_increment not null,
  mac                       varchar(255),
  hostip                    varchar(255),
  uuid                      varchar(255),
  menuid                    integer,
  timestamp                 bigint,
  due_date                  datetime,
  constraint pk_menu_time primary key (id))
;

create table resource (
  id                        bigint auto_increment not null,
  mac                       varchar(255),
  hostip                    varchar(255),
  uuid                      varchar(255),
  type                      varchar(255),
  resourceid                integer,
  clickcount                bigint,
  constraint pk_resource primary key (id))
;

create table resource_time (
  id                        bigint auto_increment not null,
  mac                       varchar(255),
  hostip                    varchar(255),
  uuid                      varchar(255),
  type                      varchar(255),
  resourceid                integer,
  timestamp                 bigint,
  due_date                  datetime,
  constraint pk_resource_time primary key (id))
;

create table search (
  id                        bigint auto_increment not null,
  searchcontent             varchar(255),
  searchtype                varchar(255),
  timestamp                 bigint,
  due_date                  datetime,
  constraint pk_search primary key (id))
;

create table tab (
  id                        bigint auto_increment not null,
  mac                       varchar(255),
  hostip                    varchar(255),
  uuid                      varchar(255),
  tabid                     integer,
  clickcount                bigint,
  constraint pk_tab primary key (id))
;

create table tab_time (
  id                        bigint auto_increment not null,
  mac                       varchar(255),
  hostip                    varchar(255),
  uuid                      varchar(255),
  tabid                     integer,
  timestamp                 bigint,
  due_date                  datetime,
  constraint pk_tab_time primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  username                  varchar(255) not null,
  password                  varchar(255),
  timestamp                 bigint,
  due_date                  datetime,
  constraint uq_user_username unique (username),
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table bind;

drop table feed_back;

drop table menu;

drop table menu_time;

drop table resource;

drop table resource_time;

drop table search;

drop table tab;

drop table tab_time;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

