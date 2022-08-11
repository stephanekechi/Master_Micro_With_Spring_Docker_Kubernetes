package com.bank.accounts.controller;

import com.bank.accounts.config.AccountsServiceConfig;
import com.bank.accounts.model.Accounts;
import com.bank.accounts.model.Customer;
import com.bank.accounts.model.Properties;
import com.bank.accounts.repository.AccountsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final AccountsServiceConfig accountsServiceConfig;

    @Autowired
    public AccountsController(final AccountsRepository accountsRepository, final AccountsServiceConfig accountsServiceConfig) {
        this.accountsRepository = accountsRepository;
        this.accountsServiceConfig = accountsServiceConfig;
    }

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        Properties properties = new Properties(accountsServiceConfig.getMsg(), accountsServiceConfig.getBuildVersion(),
                accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());

        return objectWriter.writeValueAsString(properties);
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
