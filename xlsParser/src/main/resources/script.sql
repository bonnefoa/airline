CREATE TABLE PILOT
(
PilotNumber                     Int PRIMARY KEY,
LastName                        VarChar (100),
Address                 VarChar (100),
Salary                  Int,
Premium                 Int,
HiringDate                      Timestamp,
FirstName                       VarChar (100)
);

INSERT INTO PILOT (LastName, Address, Salary, Premium, HiringDate, FirstName, PilotNumber) VALUES ('Pilot1',' ',24000,0,'1992-03-01 00:00:00.0','fN1',1333);
INSERT INTO PILOT (LastName, Address, Salary, Premium, HiringDate, FirstName, PilotNumber) VALUES ('Pilot2',' ',18600,5580,'1992-03-12 00:00:00.0','fN2',6589);
INSERT INTO PILOT (LastName, Address, Salary, Premium, HiringDate, FirstName, PilotNumber) VALUES ('Pilot3',' ',15600,16000,'1993-04-01 00:00:00.0','fN3',7100);
INSERT INTO PILOT (LastName, Address, Salary, Premium, HiringDate, FirstName, PilotNumber) VALUES ('Pilot4',' ',22670,NULL,'1992-12-12 00:00:00.0','fN4',3452);
INSERT INTO PILOT (LastName, Address, Salary, Premium, HiringDate, FirstName, PilotNumber) VALUES ('Pilot5',' ',18700,NULL,'1992-12-28 00:00:00.0','fN5',3421);
INSERT INTO PILOT (LastName, Address, Salary, Premium, HiringDate, FirstName, PilotNumber) VALUES ('Pilot6',' ',22680,8600,'1992-12-01 00:00:00.0','fN6',6548);
INSERT INTO PILOT (LastName, Address, Salary, Premium, HiringDate, FirstName, PilotNumber) VALUES ('Pilot7',' ',19000,0,'1990-02-01 00:00:00.0','fN7',1243);
INSERT INTO PILOT (LastName, Address, Salary, Premium, HiringDate, FirstName, PilotNumber) VALUES ('Pilot8',' ',21850,9850,'1992-02-01 00:00:00.0','fN8',5643);
INSERT INTO PILOT (LastName, Address, Salary, Premium, HiringDate, FirstName, PilotNumber) VALUES ('Pilot9',' ',23150,NULL,'1992-05-15 00:00:00.0','fN9',6723);
INSERT INTO PILOT (LastName, Address, Salary, Premium, HiringDate, FirstName, PilotNumber) VALUES ('Pilot10',' ',17600,NULL,'1992-11-20 00:00:00.0','fN10',8843);
INSERT INTO PILOT (LastName, Address, Salary, Premium, HiringDate, FirstName, PilotNumber) VALUES ('Pilot11',' ',18650,NULL,'1993-07-15 00:00:00.0','fN11',3465);

CREATE TABLE FLIGHT
(
FlightNumber                    VarChar (100)  PRIMARY KEY,
Origin                    VarChar (100),
Destination                      VarChar (100),
DepartureHour                   Int,
DepartureMinute                 Int,
ArrivalHour                     Int,
ArrivalMinute                   Int,
DayChange                       Boolean
);

CREATE TABLE APPAREL
(
Code                    VarChar (100) PRIMARY KEY,
Capacity                        Int,
TradeName                       VarChar (100)
);

CREATE TABLE PLANE
(
PlaneNumber                     Int PRIMARY KEY,
PlaneType                       VarChar (100),
DutyYear                        Int,
PlaneName                       VarChar (100),
FlyingDuration                  Int
);

CREATE TABLE ASSIGNMENT
(
AssignmentNumber                        Int PRIMARY KEY,
PilotNumber                     Int,
PlaneNumber                     Int,
FlightDay                       Timestamp,
PassengersNumber                        Int,
FlightNumber                    VarChar (100)
);
