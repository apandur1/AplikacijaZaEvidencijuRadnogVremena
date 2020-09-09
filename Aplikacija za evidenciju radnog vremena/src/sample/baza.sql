BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "vrijeme" (
	"vrijeme_id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"dolazak"	TEXT,
	"odlazak"	TEXT,
	"datum"	TEXT,
	"radnik_id"	INTEGER
);
CREATE TABLE IF NOT EXISTS "radnik" (
	"id"	INTEGER NOT NULL,
	"ime"	TEXT NOT NULL,
	"prezime"	TEXT NOT NULL,
	"username"	TEXT NOT NULL,
	"password"	TEXT NOT NULL,
	"manager_id"	INTEGER,
	PRIMARY KEY("id")
);
INSERT INTO "vrijeme" VALUES (1,'08:00','16:00','01.01.2000.',3);
INSERT INTO "vrijeme" VALUES (2,'10:00','18:00','04.05.2000.',3);
INSERT INTO "radnik" VALUES (1,'Mujo','Mujic','mmujic','test',NULL);
INSERT INTO "radnik" VALUES (2,'Suljo','Suljic','ssuljic','test',NULL);
INSERT INTO "radnik" VALUES (3,'Bill','Gates','bgates','test',1);
COMMIT;
