create table IF NOT EXISTS users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(200) not null,
	enabled boolean not NULL,
	details varchar(50)
);

create table IF NOT EXISTS authorities (
	username varchar_ignorecase(50) not NULL PRIMARY key,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);