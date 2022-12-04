package com.bank.loans.controller;

import com.bank.loans.config.LoansServiceConfig;
import com.bank.loans.model.Customer;
import com.bank.loans.model.Loans;
import com.bank.loans.model.Properties;
import com.bank.loans.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Stephane Nganou
 */
@RestController
@RequestMapping(value = "/loans")
public class LoansController {

    private final LoansRepository loansRepository;
    private final LoansServiceConfig loansServiceConfig;

    @Autowired
    public LoansController(final LoansRepository loansRepository,
                           final LoansServiceConfig loansServiceConfig) {

        this.loansRepository = loansRepository;
        this.loansServiceConfig = loansServiceConfig;
    }

    @GetMapping("/properties")
    public String getPropertiesDetails() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(loansServiceConfig.getMsg(), loansServiceConfig.getBuildVersion(),
                loansServiceConfig.getMailDetails(), loansServiceConfig.getActiveBranches());

        return objectWriter.writeValueAsString(properties);
    }

    @PostMapping("/")
    public List<Loans> getAccountDetails(@RequestHeader("greatnessbank-correlation-id") String correlationId, @RequestBody Customer customer) {

        return loansRepository.findByCustomerIdOrderByCreateDateDesc(customer.getCustomerId());
    }

}
