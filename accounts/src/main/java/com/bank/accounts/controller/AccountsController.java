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
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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

    /**
     * Method for testing RateLimiter Pattern
     */
    @GetMapping("/sayHello")
    @RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
    public String sayHello() {
        return "Hello, Welcome to Greatness Bank";
    }

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        log.info("Requesting properties '/properties'");

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountsServiceConfig.getMsg(), accountsServiceConfig.getBuildVersion(),
                accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());

        return objectWriter.writeValueAsString(properties);
    }

    @PostMapping("/")
    public Accounts getAccountsDetails(@RequestBody Customer customer) {
        log.info("Requesting account '/' with: {}", customer);

        return accountsRepository.findByCustomerId(customer.getCustomerId());
    }

    @PostMapping("/myCustomerDetails")
    @CircuitBreaker(name = "detailsForCustomerSupportApp", fallbackMethod = "myCustomerDetailsFallback")
    @Retry(name = "retryForCustomerDetails")
    public CustomerDetails myCustomerDetails(/*@RequestHeader("greatnessbank-correlation-id") String correlationId, */@RequestBody Customer customer) {

        log.info("Requesting CustomerDetails '/myCustomerDetails' with: {}", customer);
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        List<Loans> loans = loansFeignClient.getLoansDetails(/*correlationId, */customer);
        List<Cards> cards = cardsFeignClient.getCardsDetails(/*correlationId, */customer);

        return new CustomerDetails(accounts, loans, cards);
    }

    private CustomerDetails myCustomerDetailsFallback(Customer customer, Throwable throwableObject) {

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());

        return new CustomerDetails(accounts, null, null);

    }

    private String sayHelloFallback(Throwable t) {
        return "Hi, here sayHelloFallback. Welcome to Greatness bank";
    }

}
