SHOW tables;

/*show all tables in my database.*/


select * from Accounts;

/*to get my Accounts table all information.*/

select balance from Accounts;

/*to get balance value from Accounts.*/

select balance from Accounts where account id =10;

/*to get id =10 balance value from Accounts.*/




select * from EquipmentTable;

/*to get my EquipmentTable table all information.*/

select attack from EquipmentTable;

/*to get attack from EquipmentTable table.*/

select attack from EquipmentTable where EquipmentID =10;

/*to get id =10 attack value from equipmentable.*/




select * from LocationTable;

/*to get my LocationTable table all information.*/


select country from LocationTable;

/*to get country from my LocationTable table all information.*/


select country from LocationTable where LocationID = 10;

/*to get location id =10 country from my LocationTable table .*/



select * from Users;

/*to get my Users table all information.*/

select firstname from Users;

/*to get firstname form Users table all information.*/

select firstname from Users where attack =10;

/*to get attack =10 Users firstname table all information.*/



select * from WeatherTable;

/*to get my WeatherTable table all information.*/

select weather from WeatherTable;

/*to get weather from my WeatherTable table all information.*/

select weather from WeatherTable where WeatherID=10;

/*to get weather id =10 from my WeatherTable table all information.*/




select * from mappings;

/*to get my mappings table all information.*/

select ID from mappings;

/*to get ID from my mappings table all information.*/

select ID from mappings where UserId=10;

/*to get ID  from mappings table when userid=10*/




select FirstName, attack, defence, citizenship, Balance 
from Users  join mappings  on UserID = UserId
join Accounts on AccountID = AccountId
where AccountID = 10;

/*to get AccountID all information from Users and Accounts */



SELECT firstName, AVG(Attack)
       FROM Users
       GROUP BY firstName;

/*to get AVG attack value from Users and group by firstname */


SELECT firstName, AVG(Defence)
       FROM Users
       GROUP BY firstName;

/*to get AVG defence value from Users and group by firstname */


SELECT*FROM Users
WHERE
    (Attack,UserID) > (50,100)
        
ORDER BY Attack,UserID
LIMIT 10;

/*to Attack>50 and USERID>100  from Users  limit 10 people and order by attack and UserID */



/*Data manipulation queries*/





insert into LocationTable (LocationID, Country) values (1001, 'MARS');

/*insert mars and id to locationtable*/

insert into EquipmentTable (EquipmentID, Name, Attack, Defence, Price) values (1001, 'Note9', 9, 85, 967);

/*insert note9 to equipmenttable*/

insert into WeatherTable (WeatherID, Weather) values (11, 1);

/*insert weatherid and degree to weathertable*/

insert into Users (UserID, FirstName, Attack, Defence, Citizenship) values (1001, 'Minnnie', 33, 98, 'Yemen');

/*insert User to usertable*/

insert into Accounts (AccountID, Balance) values (1001, 10710);

/*insert a account to accounttable*/

insert into mappings (ID, AccountId, UserId) values (1001, 1001, 1001);

/*insert a mapping to mappingtable*/


UPDATE Accounts SET Balance = 600 WHERE AccountID = 4;

UPDATE Accounts SET Balance = 700 WHERE AccountID = 5;

/*update account to balance*/

UPDATE mappings SET ID = 600 WHERE AccountId = 4;

UPDATE mappings SET ID = 700 WHERE AccountId = 5;

/*update mappings id*/

UPDATE Users SET Attack = 600 WHERE Defence = 4;

UPDATE Users SET Attack = 700 WHERE Defence = 5;

/*update User attack whe defence = ?*/

UPDATE WeatherTable SET Weather = 600 WHERE WeatherID = 4;

UPDATE WeatherTable SET Weather = 700 WHERE WeatherID = 5;

/*update Weather table  weather degree, weatherID = ?*/


UPDATE EquipmentTable SET Attack = 600 WHERE Defence = 4;

UPDATE EquipmentTable SET Attack = 700 WHERE Defence = 5;

/*update EquipmentTable  attack, defence = ?*/

UPDATE LocationTable SET Country = 'Russia' WHERE LocationID = 4;

UPDATE LocationTable SET Country = 'China' WHERE LocationID = 5;

/*update locationtable  country, locationID = ?*/


DELETE from mappings where AccountId = 10;

/*delete mappings table where accountID= 10 row*/

DELETE from LocationTable where LocationID = 10;

/*delete location table where ID= 10 row*/

DELETE from EquipmentTable where Attack = 10;

/*delete EquipmentTable where Attack = 10 row*/

DELETE from WeatherTable where WeatherID = 10;

/*delete WeatherTable where accountID= 10 row*/

DELETE from Accounts where AccountId = 10;

/*delete Accountss table where ID= 10 row*/
DELETE from Users where UserId = 10;

/*delete Users table where ID= 10 row*/


/*Indices*/

SELECT MIN(Attack),MAX(Defence)
  FROM Users;

/*get min attack and max defence from user, we can find most def person*/
SELECT MAX(Attack),MIN(Defence)
  FROM EquipmentTable;
/*get max attack and min defence from equipmentable, we can find most powerful equipment*/


