CREATE TABLE USERS (
  USER_ID INT NOT NULL AUTO_INCREMENT,
  USERNAME VARCHAR (255) NOT NULL UNIQUE,
  PASSWORD VARCHAR (255),
  PRIMARY KEY (USER_ID)
);