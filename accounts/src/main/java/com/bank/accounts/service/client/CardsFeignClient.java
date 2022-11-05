package com.bank.accounts.service.client;

import com.bank.accounts.model.Cards;
import com.bank.accounts.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Stephane Nganou
 */
@FeignClient("cards") // "The String 'cards' represents the application I want to make the HTTP requests"
public interface CardsFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/cards/", consumes = "application/json")
    List<Cards> getCardsDetails(@RequestBody Customer customer);
}
