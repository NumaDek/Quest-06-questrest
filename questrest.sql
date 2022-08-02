CREATE DATABASE questrest;

USE DATABASE questrest;

CREATE USER 'questrest'@'localhost' IDENTIFIED BY 'questrest';

GRANT ALL PRIVILEGES ON questrest.* TO 'questrest'@'localhost';

FLUSH PRIVILEGES;