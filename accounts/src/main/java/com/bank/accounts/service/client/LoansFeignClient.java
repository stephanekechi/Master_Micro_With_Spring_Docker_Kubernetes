package com.bank.accounts.service.client;

import com.bank.accounts.model.Customer;
import com.bank.accounts.model.Loans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Stephane Nganou
 */
@FeignClient("loans")
public interface LoansFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/loans/", consumes = "application/json")
    List<Loans> getLoansDetails(@RequestBody Customer customer);
}
