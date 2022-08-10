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
public class Accounts {

    @Column(name = "account_number")
    @Id
    private long accountNumber;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "create_date")
    private LocalDate createDate;
}
