BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Vrijeme" (
	"vrijeme_id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"dolazak"	TEXT,
	"odlazak"	TEXT,
	"datum"	TEXT,
	"radnik_id"	INTEGER
);
CREATE TABLE IF NOT EXISTS "Radnik" (
	"ID"	INTEGER NOT NULL,
	"Ime"	TEXT NOT NULL,
	"Prezime"	TEXT NOT NULL,
	"Username"	TEXT NOT NULL,
	"Password"	TEXT NOT NULL,
	"Manager_id"	INTEGER,
	PRIMARY KEY("ID")
);
INSERT INTO "Vrijeme" VALUES (1,'08:00','16:00','01.01.2000.',3);
INSERT INTO "Vrijeme" VALUES (2,'10:00','18:00','04.05.2000.',3);
INSERT INTO "Radnik" VALUES (1,'Mujo','Mujic','mmujic','test',NULL);
INSERT INTO "Radnik" VALUES (2,'Suljo','Suljic','ssuljic','test',NULL);
INSERT INTO "Radnik" VALUES (3,'Bill','Gates','bgates','test',1);
COMMIT;
