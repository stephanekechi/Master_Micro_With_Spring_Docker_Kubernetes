package com.bank.accounts.controller;

import com.bank.accounts.model.Accounts;
import com.bank.accounts.model.Customer;
import com.bank.accounts.repository.AccountsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Stephane Nganou
 */
@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountsController {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsController(final AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @PostMapping("/")
    public Accounts getAccountsDetails(@RequestBody Customer customer) {

        log.info("Requesting account '/' with: {}", customer);
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());

        if (null != accounts) {
            return accounts;
        }

        return null;
    }
}
