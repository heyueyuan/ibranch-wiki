create database ibranchWiki;

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