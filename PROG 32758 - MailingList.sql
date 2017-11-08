/***********************************************************
* Create the MailingList database and all associated tables*
************************************************************/

DROP DATABASE IF EXISTS MailingList;

CREATE DATABASE MailingList;

USE MailingList;

CREATE TABLE Subscription(
	Email VARCHAR(50) NOT NULL,
	FirstName VARCHAR(50) NOT NULL,
	LastName VARCHAR(50) NOT NULL,
	Subscribed BOOLEAN DEFAULT True,
	LastUpdated TIMESTAMP,
	
	PRIMARY KEY(Email)
);

INSERT INTO Subscription 
  (FirstName, LastName, Email, Subscribed)
VALUES 
  ('Donna', 'Singh', 'dsingh1234@email.com', true),
  ('Jared', 'Chou', 'jared_chou@email.com', true),
  ('Adriana', 'Fernandez', 'fernandez_a@email.com', false),
  ('Miles', 'Fransen', 'miles_fransen3@email.com', false),
  ('Cindy', 'Ly', 'cindyly@email.com', true),
  ('Connor', 'MacDonald', 'cmac@email.com', true),
  ('Josef', 'Jurkowicz', 'josef81@email.com', true),
  ('Grigor', 'Falanov', 'falanov_grigor@email.com', true),
  ('Penn', 'Garsoman', 'gp793@email.com', false),
  ('Kendra', 'Thompson', 'kendra.thompson@email.com', true);
	
	