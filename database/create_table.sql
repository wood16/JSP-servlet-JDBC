use new_servlet;

CREATE TABLE role(
	id bigint NOT NULL auto_increment,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    createddate timestamp null,
    modifieddate timestamp null,
    createdby VARCHAR(255) null,
    modifiedby VARCHAR(255) null,
    primary Key(id)

);

CREATE TABLE user(
	id bigint NOT NULL auto_increment,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NULL,
    status int NOT NULL,
    roleid bigint NOT NULL,
    createddate timestamp null,
    modifieddate timestamp null,
	createdby VARCHAR(255) null,
    modifiedby VARCHAR(255) null,
    primary Key(id)
);

ALTER TABLE user ADD constraint fk_user_role foreign key (roleid) references role(id);

CREATE TABLE news(
	id bigint NOT NULL auto_increment,
    title VARCHAR(255) NOT NULL,
    thumbnail VARCHAR(255) NULL,
    shortdescription text NULL,
    content text NULL,
    categoryid bigint NOT NULL,
    createddate timestamp null,
    modifieddate timestamp null,
	createdby VARCHAR(255) null,
    modifiedby VARCHAR(255) null,
    primary Key(id)
);

CREATE TABLE category(
	id bigint NOT NULL auto_increment,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    createddate timestamp null,
    modifieddate timestamp null,
	createdby VARCHAR(255) null,
    modifiedby VARCHAR(255) null,
    primary Key(id)
);

ALTER TABLE news ADD constraint fk_news_category foreign key (categoryid) references category(id);

CREATE TABLE comment (
	id bigint NOT NULL auto_increment,
    content text NOT NULL,
    user_id bigint NOT NULL,
    news_id bigint NOT NULL,
    createddate timestamp null,
    modifieddate timestamp null,
	createdby VARCHAR(255) null,
    modifiedby VARCHAR(255) null,
    primary Key(id)
);

ALTER TABLE comment ADD constraint fk_comment_user foreign key (user_id) references user(id);
ALTER TABLE comment ADD constraint fk_comment_news foreign key (news_id) references news(id);