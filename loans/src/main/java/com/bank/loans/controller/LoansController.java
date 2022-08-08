package com.bank.loans.controller;

import com.bank.loans.model.Customer;
import com.bank.loans.model.Loans;
import com.bank.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    public LoansController(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    @PostMapping("/")
    public List<Loans> getAccountDetails(@RequestBody Customer customer) {

        return loansRepository.findByCustomerIdOrderByCreateDateDesc(customer.getCustomerId());
    }

}
