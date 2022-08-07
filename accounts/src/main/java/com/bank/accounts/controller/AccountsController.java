package com.bank.accounts.controller;

import com.bank.accounts.model.Accounts;
import com.bank.accounts.model.Customer;
import com.bank.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Stephane Nganou
 */
@RestController
public class AccountsController {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsController(final AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public Accounts getAccountsDetails(@RequestBody Customer customer) {

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());

        if (null != accounts) {
            return accounts;
        }

        return null;
    }
}
