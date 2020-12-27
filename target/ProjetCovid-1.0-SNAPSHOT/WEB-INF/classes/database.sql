DROP DATABASE IF EXISTS coviddb;

CREATE DATABASE coviddb;
USE coviddb;

DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Friends;
DROP TABLE IF EXISTS Activity;
DROP TABLE IF EXISTS Lieu;
DROP TABLE IF EXISTS AskFriends;
DROP TABLE IF EXISTS Notifications;



CREATE TABLE Users (
                       idUser INTEGER PRIMARY KEY AUTO_INCREMENT,
                       login VARCHAR(50) NOT NULL UNIQUE,
                       firstname VARCHAR(50),
                       lastname VARCHAR(50),
                       password VARCHAR(999) NOT NULL,
                       dateCreation DATETIME,
                       dateNaissance DATETIME,
                       admin BOOL default false
) ;

CREATE TABLE Friends(
                        idFriend1 INTEGER NOT NULL,
                        idFriend2 INTEGER NOT NULL,
                        CONSTRAINT FriendsPK PRIMARY KEY (idFriend1, idFriend2),
                        FOREIGN KEY (idFriend1) REFERENCES Users(idUser) ON UPDATE CASCADE ON DELETE CASCADE,
                        FOREIGN KEY (idFriend2) REFERENCES Users(idUser) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE AskFriends (
                            idAskFriends INTEGER PRIMARY KEY AUTO_INCREMENT,
                            idUser INTEGER NOT NULL,
                            idAsker INTEGER NOT NULL,
                            FOREIGN KEY (idUser) REFERENCES Users(idUser) ON UPDATE CASCADE,
                            FOREIGN KEY (idAsker) REFERENCES Users(idUser) ON UPDATE CASCADE
);

CREATE TABLE Notifications (
                               idNotif INTEGER PRIMARY KEY AUTO_INCREMENT,
                               about TEXT NOT NULL,
                               dateSending DATETIME NOT NULL
) ;

CREATE TABLE Lieu (
                      idLieu INTEGER PRIMARY KEY AUTO_INCREMENT,
                      nom TEXT NOT NULL,
                      adresse TEXT NOT NULL,
                      coordonneesGPS TEXT NOT NULL
);

CREATE TABLE Activity (
                          idActivity INTEGER PRIMARY KEY AUTO_INCREMENT,
                          dateDebut DATETIME NOT NULL,
                          dateFin DATETIME NOT NULL,
                          idLieu INTEGER NOT NULL,
                          FOREIGN KEY (idLieu) REFERENCES Lieu(idLieu) ON UPDATE CASCADE
);