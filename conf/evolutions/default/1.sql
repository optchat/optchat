# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table chat_message (
  message_id                bigint,
  message                   varchar(255),
  user_id                   bigint)
;

create table user (
  user_id                   bigint not null,
  name                      varchar(255) not null,
  mail                      varchar(255) not null,
  constraint pk_user primary key (user_id))
;

create sequence user_seq;

alter table chat_message add constraint fk_chat_message_user_1 foreign key (user_id) references user (user_id) on delete restrict on update restrict;
create index ix_chat_message_user_1 on chat_message (user_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists chat_message;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_seq;

