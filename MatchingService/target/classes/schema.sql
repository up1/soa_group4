DROP TABLE IF EXISTS USERS;

CREATE TABLE LIKES
(
    like_id int(11) NOT NULL AUTO_INCREMENT,
    id_user_like int(11) FOREIGN KEY (account_id) REFERENCES account(account_id),
    id_user_liked int(11) FOREIGN KEY (account_id) REFERENCES account(account_id),
    PRIMARY KEY (like_id)
);

CREATE TABLE ACCOUNTS
(
    account_id int(11) NOT NULL AUTO_INCREMENT,
    account_email varchar(100) NOT NULL,
    account_username varchar(100) NOT NULL,
    account_password varchar(100) NOT NULL,
    account_name varchar(100) NOT NULL,
    account_lastname varchar(100) NOT NULL,
    account_age int(3) NOT NULL,
    account_sex varchar(100) NOT NULL,
    account_sexual_taste varchar(100) NOT NULL,
    account_latitude double(11) NOT NULL,
    account_longtitude double(11) NOT NULL,
    account_location varchar(100),
    account_img_profile_path varchar(100) NOT NULL,
    account_descriptions varchar(1000),
    PRIMARY KEY (account_id)
);