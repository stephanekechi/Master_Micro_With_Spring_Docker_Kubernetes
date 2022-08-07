DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS accounts;

CREATE TABLE `customer`(
    `customer_id` int AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `mobile_number` varchar(20)  NOT NULL,
    `create_date` date DEFAULT NULL
);

CREATE TABLE `accounts`(
    `account_number` int AUTO_INCREMENT PRIMARY KEY,
    `customer_id` int NOT NULL,
    `account_type` varchar(100) NOT NULL,
    `branch_address` varchar(200) NOT NULL,
    `create_date` date DEFAULT NULL
);

INSERT INTO `customer` (`name`, `email`, `mobile_number`, `create_date`) VALUES ('Stephanekechi', 'stephanekechi@gmail.com', '+237886945', CURDATE());

INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_date`) VALUES (1, 123455, 'Savings', 'BP 9640 Yaoundé', CURDATE());