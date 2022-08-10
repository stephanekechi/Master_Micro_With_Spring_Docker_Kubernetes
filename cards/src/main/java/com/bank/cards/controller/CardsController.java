package com.bank.cards.controller;

import com.bank.cards.model.Cards;
import com.bank.cards.model.Customer;
import com.bank.cards.repository.CardsRepository;
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
@RequestMapping("/cards")
public class CardsController {

    private final CardsRepository cardsRepository;

    @Autowired
    public CardsController(final CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    @PostMapping("/")
    public List<Cards> getCards(@RequestBody Customer customer) {

        return cardsRepository.findByCustomerId(customer.getCustomerId());
    }
}
