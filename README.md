create database greenwich then run command
```sql
create table category
(
category_id int auto_increment
primary key,
name        varchar(255) null
)
engine = MyISAM;

create table comment
(
id              int auto_increment
primary key,
comment_content varchar(255) null,
created_by      varchar(255) null,
created_date    datetime     null,
idea_id         int          null
)
engine = MyISAM;

create index FKpo553b3rappx4h6o9lb6lr7xy
on comment (idea_id);

create table department
(
department_id int auto_increment
primary key,
name          varchar(255) null
)
engine = MyISAM;

create table idea
(
idea_id       int auto_increment
primary key,
author        varchar(255) null,
brief         varchar(60)  null,
content       varchar(500) null,
file_location varchar(255) null,
tag           varchar(255) null,
category_id   int          not null,
topic_id      int          not null
)
engine = MyISAM;

create index FKkdpjl9o1e5asu8o4cjy3ixgpr
on idea (category_id);

create index FKl7g1ixug1qc29v5atg6f34n2g
on idea (topic_id);

create table reaction
(
id      int auto_increment
primary key,
is_like bit    not null,
idea_id int    null,
user_id bigint null
)
engine = MyISAM;

create index FK52m11aguxqx0uje12knj056jw
on reaction (user_id);

create index FKrrtgoxmyrq7dlf6nhfrl6xdy3
on reaction (idea_id);

create table roles
(
id   int auto_increment
primary key,
name varchar(20) null
)
engine = MyISAM;

create table topic
(
topic_id      int auto_increment
primary key,
closure       datetime     null,
final_closure datetime     null,
name          varchar(255) null
)
engine = MyISAM;

create table user_roles
(
user_id bigint not null,
role_id int    not null,
primary key (user_id, role_id)
)
engine = MyISAM;

create index FKh8ciramu9cc9q3qcqiv4ue8a6
on user_roles (role_id);

create table users
(
user_id       bigint auto_increment
primary key,
email         varchar(50)  null,
password      varchar(120) null,
username      varchar(20)  null,
department_id int          null,
constraint UK6dotkott2kjsp8vw4d0m25fb7
unique (email),
constraint UKr43af9ap4edm43mmtq01oddj6
unique (username)
)
engine = MyISAM;

create index FKfi832e3qv89fq376fuh8920y4
on users (department_id);

```