DROP TABLE IF EXISTS IMAGE;

CREATE TABLE IMAGE
(
    image_id int(11) NOT NULL AUTO_INCREMENT,
    image_type varchar(10) NOT NULL,
    image_name varchar(50) NOT NULL,
    image_path varchar(400) NOT NULL,
    account_id int(11) NOT NULL,
    PRIMARY KEY (image_id)
);
