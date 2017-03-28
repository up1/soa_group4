DROP TABLE IF EXISTS LIKES;
DROP TABLE IF EXISTS ACCOUNTS;

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
    account_latitude float(11) NOT NULL,
    account_longtitude float(11) NOT NULL,
    account_location varchar(100),
    account_img_profile_path varchar(100) NOT NULL,
    account_descriptions varchar(1000),
    PRIMARY KEY (account_id)
);
