create table Users(UserID int primary key auto_increment, firstName varchar(50), attack double, defence double,citizenship varchar(50)) Engine=InnoDB;


create table Accounts(AccountID int primary key auto_increment, Balance double) Engine=InnoDB;


create table mappings(ID int primary key auto_increment, UserId int,AccountId int, foreign key(UserId) references Users(UserID) on delete cascade,foreign key(AccountId) references Accounts(AccountID) on delete cascade) Engine=InnoDB; 


create table LocationTable (
	LocationID INT,
	Country VARCHAR(50)
);

create table EquipmentTable (
	EquipmentID INT,
	Name VARCHAR(50),
	Attack INT,
	Defence INT,
	Price INT
);

create table WeatherTable (
	WeatherID INT,
	Weather INT
);
