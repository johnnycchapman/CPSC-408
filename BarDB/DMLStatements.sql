-- DML Statements


-- Display Bar Info
SELECT *
FROM bar_info;

-- Display Bar Phone
SELECT bar_info.bar_id, bar_info.bar_name, bar_phone.phone
FROM bar_phone
INNER JOIN bar_info ON bar_info.bar_id = bar_phone.bar_id;

-- Display Bar Email
SELECT bar_info.bar_id, bar_info.bar_name, bar_email.email
FROM bar_email
INNER JOIN bar_info ON bar_info.bar_id = bar_email.bar_id;

-- Display Ratings
SELECT bar_info.bar_name,
        bar_ratings.overall_rating,
        bar_ratings.food_rating,
        bar_ratings.beer_rating,
        bar_ratings.cocktail_rating
FROM bar_ratings
INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id;

-- Display Features
SELECT bar_info.bar_name,
        bar_features.has_dj,
        bar_features.has_food,
        bar_features.has_outdoor_area,
        bar_features.has_beer_on_tap
FROM bar_features
INNER JOIN bar_info ON bar_info.bar_id = bar_features.bar_id;

-- Display Top 5 Overall Ratings
SELECT bar_info.bar_name,
        bar_ratings.overall_rating
FROM bar_ratings
INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id
WHERE overall_rating >= '3'
ORDER BY overall_rating DESC LIMIT 5;

-- Display Top 5 Food Ratings
SELECT bar_info.bar_name,
        bar_ratings.food_rating
FROM bar_ratings
INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id
WHERE food_rating >= '3'
ORDER BY food_rating DESC LIMIT 5;

-- Display Top 5 Beer Ratings
SELECT bar_info.bar_name,
        bar_ratings.beer_rating
FROM bar_ratings
INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id
WHERE beer_rating >= '3'
ORDER BY beer_rating DESC LIMIT 5;

-- Display Top 5 Cocktail Ratings
SELECT bar_info.bar_name,
        bar_ratings.cocktail_rating
FROM bar_ratings
INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id
WHERE cocktail_rating >= '3'
ORDER BY cocktail_rating DESC LIMIT 5;

-- Display Average Ratings
SELECT AVG(overall_rating) AS Overall,
        AVG(food_rating) AS Food,
        AVG(beer_rating) AS Beer,
        AVG(cocktail_rating) AS Cocktail
FROM bar_ratings;

-- Create a bar
INSERT INTO `bar_info`(bar_id,bar_name,address,city,state,zip) VALUES (?, ?, ?, ?, ?, ?);

-- Update bar name
UPDATE bar_info SET bar_name = ? WHERE bar_id in (?);

-- Update bar address
UPDATE bar_info SET address = ? WHERE bar_id in (?);

-- Update phone
UPDATE bar_phone SET phone = ? WHERE bar_id in (?);

-- Delete bar
DELETE FROM bar_info WHERE bar_id = ?;

-- Contact information
SELECT bar_info.bar_name,
        bar_phone.phone,
        bar_email.email
FROM bar_info
INNER JOIN bar_phone ON bar_phone.bar_id = bar_info.bar_id
INNER JOIN bar_email ON bar_email.bar_id = bar_info.bar_id;

-- Overview for the bar
SELECT bar_info.bar_name,
        bar_ratings.overall_rating,
        bar_features.has_beer_on_tap
FROM bar_info
INNER JOIN bar_ratings ON bar_ratings.bar_id = bar_info.bar_id
INNER JOIN bar_features ON bar_features.bar_id = bar_info.bar_id
ORDER BY bar_ratings.overall_rating DESC;
