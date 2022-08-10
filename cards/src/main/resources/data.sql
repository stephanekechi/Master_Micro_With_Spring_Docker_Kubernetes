DROP TABLE IF EXISTS `cards`;

CREATE TABLE `cards`
(
    `card_id`          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer_id`      INT          NOT NULL,
    `card_number`      VARCHAR(100) NOT NULL,
    `card_type`        VARCHAR(100) NOT NULL,
    `total_limit`      INT          NOT NULL,
    `amount_used`      INT          NOT NULL,
    `available_amount` INT          NOT NULL,
    `create_date`      date         NOT NULL
);

INSERT INTO `cards` (`customer_id`, `card_number`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_date`)
VALUES (1,'67546XXXX8908', 'Credit', 10000, 500, 9500,'2022-01-01');

INSERT INTO `cards` (`customer_id`, `card_number`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_date`)
VALUES (1,'3567XXXX8903', 'Credit', 7500, 600, 6900,'2022-05-21');

INSERT INTO `cards` (`customer_id`, `card_number`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_date`)
VALUES (1,'55687XXXX8909', 'Credit', 20000, 4000, 16000,'2022-07-23');