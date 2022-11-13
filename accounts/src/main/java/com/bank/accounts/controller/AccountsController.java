package com.bank.accounts.controller;

import com.bank.accounts.config.AccountsServiceConfig;
import com.bank.accounts.model.Accounts;
import com.bank.accounts.model.Cards;
import com.bank.accounts.model.Customer;
import com.bank.accounts.model.CustomerDetails;
import com.bank.accounts.model.Loans;
import com.bank.accounts.model.Properties;
import com.bank.accounts.repository.AccountsRepository;
import com.bank.accounts.service.client.CardsFeignClient;
import com.bank.accounts.service.client.LoansFeignClient;
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

import java.util.List;


/**
 * @author Stephane Nganou
 */
@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountsController {

    private final AccountsRepository accountsRepository;
    private final AccountsServiceConfig accountsServiceConfig;
    private final LoansFeignClient loansFeignClient;
    private final CardsFeignClient cardsFeignClient;

    @Autowired
    public AccountsController(final AccountsRepository accountsRepository, final AccountsServiceConfig accountsServiceConfig,
                              final LoansFeignClient loansFeignClient, final CardsFeignClient cardsFeignClient) {
        this.accountsRepository = accountsRepository;
        this.accountsServiceConfig = accountsServiceConfig;
        this.loansFeignClient = loansFeignClient;
        this.cardsFeignClient = cardsFeignClient;
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

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        Properties properties = new Properties(accountsServiceConfig.getMsg(), accountsServiceConfig.getBuildVersion(),
                accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());

        return objectWriter.writeValueAsString(properties);
    }

    @PostMapping("/myCustomerDetails")
    public CustomerDetails myCustomerDetails(@RequestBody Customer customer) {

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        List<Loans> loans = loansFeignClient.getLoansDetails(customer);
        List<Cards> cards = cardsFeignClient.getCardsDetails(customer);

        return new CustomerDetails(accounts, loans, cards);
    }

}
