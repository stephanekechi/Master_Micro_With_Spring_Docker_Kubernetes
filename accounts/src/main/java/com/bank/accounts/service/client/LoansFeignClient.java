package com.bank.accounts.service.client;

import com.bank.accounts.model.Customer;
import com.bank.accounts.model.Loans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Stephane Nganou
 */
@FeignClient("loans") // "The String 'loans' represents the application I want to make the HTTP requests"
public interface LoansFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/loans/", consumes = "application/json")
    List<Loans> getLoansDetails(/*@RequestHeader("greatnessbank-correlation-id") String correlationId, */@RequestBody Customer customer);
}
