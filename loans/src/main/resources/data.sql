DROP TABLE IF EXISTS loans;

CREATE TABLE `loans`
(
    `loan_number`        int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer_id`        int          NOT NULL,
    `start_date`         date         NOT NULL,
    `loan_type`          varchar(100) NOT NULL,
    `total_loan`         int          NOT NULL,
    `amount_paid`        int          NOT NULL,
    `outstanding_amount` int          NOT NULL,
    `create_date`        date         NOT NULL
);

INSERT INTO `loans` (`customer_id`, `start_date`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`,
                     `create_date`)
VALUES (1, '2020-08-13', 'Home', 200000, 50000, 150000, '2020-08-13');

INSERT INTO `loans` (`customer_id`, `start_date`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`,
                     `create_date`)
VALUES (1, '2021-01-01', 'Vehicule', 40000, 10000, 30000, '2021-01-01');

INSERT INTO `loans` (`customer_id`, `start_date`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`,
                     `create_date`)
VALUES (1, '2021-02-14', 'Home', 50000, 10000, 40000, '2021-02-14');

INSERT INTO `loans` (`customer_id`, `start_date`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`,
                     `create_date`)
VALUES (1, '2021-03-22', 'Home', 10000, 3500, 6500, '2021-03-22');