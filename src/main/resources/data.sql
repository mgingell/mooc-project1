
merge into users key(username) values ('bob', '$2a$10$ePME9nzD96.a.DkUN4uTouEBOeSNhdErukvzSDspM/QVr/hrrOwgG', TRUE, 'more data');
merge into authorities key(username) values ('bob', 'ROLE_USER');

merge into users key(username) values ('mike', '$2a$10$RpN.FRFv5cjBWZtkFhkHOuJAbeR1DSIw7VLK6LOzO6dI9nO430Jwa', TRUE, 'more data');
merge into authorities key(username) values ('mike', 'ROLE_USER');

merge into users key(username) values ('sara', '$2a$10$k2vBUKC5wQD4WB9xw9VB1.XUYxFxTS1bjH3dUeb4XIiLpAPlMLfbG', TRUE, 'extra data');
merge into authorities key(username) values ('sara', 'ROLE_ADMIN');
