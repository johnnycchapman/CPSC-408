use Assign3;

create table BarPhone
(
    Bar_ID int(32) auto_increment primary key not null,
    Phone varchar(45)
);

create table BarEmail
(
    Bar_ID int(32) not null,
    Email varchar(45),
    primary key (Bar_ID)
);

create table BarInfo
(
    Bar_ID int(32) not null,
    bar_name varchar(20),
    Address varchar(45),
    City varchar(45),
    State varchar(45),
    Zip integer,
    primary key (Bar_ID)
);

create table BarFeatures
(
    Bar_ID int(32) not null,
    Has_DJ varchar(1),
    Has_Food varchar(1),
    Has_Outdoor_Area varchar(1),
    Has_Beer_On_Tap varchar(1),
    primary key (Bar_ID)
);

create table BarRatings
(
    Bar_ID int(32) not null,
    Overall_Rating integer,
    Food_Rating integer,
    Beer_Rating integer,
    Cocktail_Rating integer,
    primary key (Bar_ID)
);
