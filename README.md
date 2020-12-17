# Dragon's Lair Comics
#Team Members:
 Jesse Schrack, Joseph Maxwell, Jackson Cunningham, Logan Sortino, Kyle Zyle, Matthew Noah

### Design
Dragon’s Lair currently uses a DOS text-based client-side program.  The original program was developed 30 years ago and is currently incompatible with modern hardware.  In order to keep the program functional, they must run the program in DOSBOX, an emulator.  While this fix does work, there have been occasional failures where the program fails to operate as intended.  Beyond the potential for imminent failure, the old program is purely a client-side program and with multiple locations, it cannot share information between stores.  This makes communicating between stores difficult should a customer come to one store when they made a pull list in the other.

We decided to build a JavaSwing application that connects to a remote database. The application is made to handle and view a list of customers, orders, catalog, and user accounts. The database is setup on a SQL server on a separate computer. This allows for the application to be used in any store so long as it is connected to the database. 

### Setup Instructions
There are three key components that make-up the program.  They are the UI front-end, Admin login manager, and SQL database back-end.  The front-end and admin components run the same way and require the same tools to function.  They are compiled into runnable java.jar files.  The only changes required on the computer to run them is that they were compiled under jdk15.  To run the program, you must change the environment path for java to the bin folder within the jdk15 folder provided alongside the programs.

As for the back-end database, that requires far more in order to set-up.  The back-end database runs on Microsoft SQL Server Express Edition and uses Microsoft SQL Server Management Studio as the interface.  Those two programs are freely available online at microsoft.com.  Once downloaded, most of the set-up will be through SSMS(SQL Server Management Studio).  Once installed, by default SSMS will sign in using windows authentication as Owner.

Right-click Databases folder in the Object Explorer

Select New Database

Enter name for database 

Select Ok to confirm

Open the instruction_sheet.sql file

To the left of Execute, change Master to the new database.

Hit f5 to run the instruction_sheet

Follow instructions to open remote connection in SSMS

How to enable remote connections to SQL Server | by Nishan Chathuranga Wickramarathna | Medium

Open port forwarding in router to host computer

CMD: ipconfig

Write a note of IPv4 Address under Ethernet adapter Ethernet

Sign into router at 192.168.1.1

Go to WAN -> Virtual Server/Port Forwarding

Add two lines titled SQL server and SQL browser

Go back to SSMS and open the Security folder

Right-click logins and create new login

Assign a name and change Windows authentication to SQL Server authentication

Create a long and difficult password, 
This will be stored in the config.ini file, so save for later

Under User Mapping check the box for the database

Test the connection using SSMS on a different computer

Server Name: [host external IP address]

Can be found by typing “what is my IP” in web browser on host computer

SQL Server authentication

Login: [User Name]

Password: [Password]





### Additional Libraries Used
Org.apache.poi : Exporting to Excel


Com.itextpdf.text : Exporting to PDF

