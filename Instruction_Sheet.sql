--This is a Comment
--Thank you for reading this, this SQL file was created by Joseph Maxwell
--This SQL file is designed to build a new database from nothing.
--If you are doing this, it should either be when you are moving to a new computer or the previous database has failed.
--Please run this file in SQL Server Management Studio, all instructions are given with this program in mind

--Quick start: to run a statement, you select and highlight the statement and press f5
--             A stament is a list of commands ended by a ";", so please highlight from the beginning to the ";".
--If you get an error about permissions, check that you are running in administrator mode.

--WARNING: DROP will delete ALL data in the database, please back-up the database first if possible
--1:To start, we should start with a clean slate, DROP DATABASE will remove all traces of the database and allow for a clean reconstruction.
DROP DATABASE DLC;

--2:Create new(empty) database
CREATE DATABASE DLC;

--3:Selects the database to be affected by future changes
--NOTE: Always run this line at least once before modifying the database
USE DLC;

--4:Creates tables and defines the variables used within each
--highlight from Start to END and press F5
--START A1
CREATE TABLE "Catalog" (
  "Publisher" varchar(50),
  "Distributor" varchar(50),
  "Catalog ID" varchar(50),
  "Description" varchar(100),--varchar(x) standands for variable character (x amount), this example is a string of up to 50 characters
  "Issue" varchar(50),--currently I assume that this is the primary key for the table, because it should be unique among all items
  "Disct? Sub" BIT,--BIT is used as a boolean true/false statement
  "Form" BIT,
  CONSTRAINT PK_Issue PRIMARY KEY CLUSTERED ("Distributor", "Calalog ID")
);

CREATE TABLE "Order" (
  "Store Code" varchar(10),
  "Customer Code" int FOREIGN KEY REFERENCES "Customer"("Customer Code"),
  "Distributor" varchar(50),
  "Calalog ID" varchar(50)FOREIGN KEY REFERENCES "Catalog"("Catalog ID"),
  "Description" varchar(100),
  "Issue Start" int,--int is any whole number -1,0,1,2,3 up to 2,147,483,647
  "Issue End" int,
  "Quantity" int,
  "Cost" money,
  CONSTRAINT PK_Store_Customer PRIMARY KEY CLUSTERED ("Store Code", "Customer Code")
);


CREATE TABLE "Customer" (
  "Store Code" varchar(10),
  "Customer Code" int IDENTITY(1,1) Primary Key,
  "Last Name" varchar(10),
  "First Name" varchar(10),
  "Email" varchar(30),
  "Address-1" varchar(20),
  "Address-2" varchar(20),
  "City" varchar(20),
  "State" varchar(20),
  "ZIP" int,
  "Post Code" varchar(10),
  "Phone #1" varchar(15),
  "Phone Home" varchar(15),
  "Customer ID" varchar(10),
  "Exp. Date" date,--this is a special datatype that handles the formatting for date yyyy/mm/dd
  "Discount %" decimal(2,2),--deciaml allows for defined number sizes, in this case it can handle xx.xx numbers
  CONSTRAINT PK_Customer PRIMARY KEY CLUSTERED ("Customer Code")
);
--END A1