create database ibranchWiki;
use ibranchWiki;
drop table if exists `ebook`;
create table `ebook` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment 'Name',
    `category1_id` bigint comment 'Category_1',
    `category2_id` bigint comment 'Category_2',
    `description` varchar(200) comment 'Description',
    `cover` varchar(200) comment 'Cover',
    `doc_count` int comment 'The number of documents',
    `view_count` int comment 'The number of view',
    `vote_count` int comment 'The number of Thumbs',
    primary key (`id`)
) engine=innodb default charset=utf8mb4;

insert into `ebook` (id, name, description) values (1, 'Spring Boot', 'Java development framework');


drop table if exists `category`;
create table `category` (
    `id` bigint not null comment 'id',
    `parent` bigint not null default 0 comment 'ParentId',
    `name` varchar(50) not null comment 'Name',
    `sort` int comment 'sort',
    primary key (`id`)
) engine=innodb default charset=utf8mb4;

insert into `category` (id, parent, name, sort) values (100, 000, 'FrontEnd', 100);
insert into `category` (id, parent, name, sort) values (101, 100, 'Vue', 101);
insert into `category` (id, parent, name, sort) values (102, 100, 'HTML', 102);
insert into `category` (id, parent, name, sort) values (200, 000, 'Java', 200);
insert into `category` (id, parent, name, sort) values (201, 200, 'Spring', 201);


drop table if exists `document`;
create table `document` (
    `id` bigint not null,
    `ebook_id` bigint not null default 0,
    `parent` bigint not null default 0,
    `name` varchar(50) not null,
    `sort` int,
    `view_count` int default 0,
    `vote_count` int default 0,
    primary key (`id`)
) engine=innodb default charset=utf8mb4;

drop table if exists `user`;
create table `user` (
    `id` bigint not null,
    `login_name` varchar(50) not null,
    `name` varchar(50),
    `password` char(32) not null,
    primary key (`id`),
    unique key `login_name_unique` (`login_name`)
) engine=innodb default charset=utf8mb4;

drop table if exists `content`;
create table `content` (
    `id` bigint not null,
    `content` mediumtext not null,
    primary key (`id`)
)engine=innodb default charset=utf8mb4;

drop table if exists `ebook_snapshot`;
create table `ebook_snapshot` (
    `id` bigint auto_increment not null,
    `ebook_id` bigint not null default 0,
    `date` date not null,
    `view_count` int not null default 0,
    `vote_count` int not null default 0,
    `view_increase` int not null default 0,
    `vote_increase` int not null default 0,
    primary key (`id`)
) engine=innodb default charset=utf8mb4;