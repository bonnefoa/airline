--
-- Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
-- 
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- 
--         http://www.apache.org/licenses/LICENSE-2.0
-- 
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

DROP TABLE PILOT IF EXISTS;
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

DROP TABLE FLIGHT IF EXISTS;
CREATE TABLE FLIGHT
(
Number                    VarChar (100)  PRIMARY KEY,
Origin                    VarChar (100),
Destination                      VarChar (100),
DepartureHour                   Int,
DepartureMinute                 Int,
ArrivalHour                     Int,
ArrivalMinute                   Int,
DayChange                       Boolean
);

DROP TABLE APPAREL IF EXISTS;
CREATE TABLE APPAREL
(
Code                    VarChar (100) PRIMARY KEY,
Capacity                        Int,
Name                       VarChar (100)
);

DROP TABLE PLANE IF EXISTS;
CREATE TABLE PLANE
(
Number                     Int PRIMARY KEY,
Type                       VarChar (100),
OnDuty                        Int,
Name                       VarChar (100),
FlyingDuration                  Int
);

DROP TABLE ASSIGNMENT IF EXISTS;
CREATE TABLE ASSIGNMENT
(
Number                        Int PRIMARY KEY,
PilotNumber                     Int,
PlaneNumber                     Int,
FlightDate                       Timestamp,
Passengers                      Int,
FlightNumber                    VarChar (100)
);
