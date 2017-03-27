DROP TABLE IF EXISTS profile_image;
DROP TABLE IF EXISTS chat_image;

CREATE TABLE profile_image
(
    image_id int(11) NOT NULL AUTO_INCREMENT,
    image_path varchar(400) NOT NULL,
    account_id int(11) NOT NULL,
    PRIMARY KEY (image_id)
);

CREATE TABLE chat_image
(
    image_id int(11) NOT NULL AUTO_INCREMENT,
    image_path varchar(400) NOT NULL,
    account_id_1 int(11),
    account_id_2 int(11),
    PRIMARY KEY (image_id)
);