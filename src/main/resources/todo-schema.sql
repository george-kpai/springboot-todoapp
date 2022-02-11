DROP TABLE `Todo` IF EXISTS;
CREATE TABLE IF NOT EXISTS Todo (
	todo_id INT PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(55) NOT NULL, 
	date_added DATE,
	date_due DATE,
	complete BOOLEAN,
	user_id INT,
	FOREIGN KEY (user_id) REFERENCES User(user_id)
);