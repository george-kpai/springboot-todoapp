
CREATE TABLE IF NOT EXISTS User (
	user_id INT PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(50) NOT NULL, 
	name VARCHAR(50) NOT NULL, 
	password VARCHAR(50)
);