use barsdb;

-- 11. Enforce referential integrality (Constraints) --

-- Bar Phone
create table bar_phone
(
    bar_id int(32) auto_increment primary key not null,
    phone varchar(45)
);

-- Index for Bar Phone
create index bar_phone_index
  on bar_phone (bar_id);

-- Bar Email
create table bar_email
(
    bar_id int(32) not null,
    email varchar(45),
    primary key (bar_id)
);

-- Index for Bar Email
create index bar_email_index
  on bar_email (bar_id);

-- Bar Info
create table bar_info
(
    bar_id int(32) not null,
    bar_name varchar(20),
    address varchar(45),
    city varchar(45),
    state varchar(45),
    zip integer,
    primary key (bar_id)
);

-- 12. Include various database views, Indexes
create index bar_info_index
  on bar_info (bar_id);

-- Bar Features
create table bar_features
(
    bar_id int(32) not null,
    has_dj varchar(1),
    has_food varchar(1),
    has_outdoor_area varchar(1),
    has_beer_on_tap varchar(1),
    primary key (bar_id)
);

-- Index for Bar Features
create index bar_features_index
  on bar_features (bar_id);


-- Bar Ratings
create table bar_ratings
(
    bar_id int(32) not null,
    overall_rating integer,
    food_rating integer,
    beer_rating integer,
    cocktail_rating integer,
    primary key (bar_id)
);

-- Index for Bar Ratings
create index bar_ratings_index
	on bar_ratings (bar_id);

