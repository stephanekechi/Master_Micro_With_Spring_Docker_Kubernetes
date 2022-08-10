package com.bank.accounts.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author Stephane Nganou
 */
@Entity
@Data
@ToString
public class Customer {

    @Column(name = "customer_id")
    @Id
    private long customerId;

    @Column
    private String name;

    @Column
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "create_date")
    private LocalDate createDate;
}
