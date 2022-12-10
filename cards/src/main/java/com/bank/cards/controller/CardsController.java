package com.bank.cards.controller;

import com.bank.cards.config.CardsServiceConfig;
import com.bank.cards.model.Cards;
import com.bank.cards.model.Customer;
import com.bank.cards.model.Properties;
import com.bank.cards.repository.CardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/cards")
@Slf4j
public class CardsController {
    private final CardsRepository cardsRepository;
    private final CardsServiceConfig cardsServiceConfig;

    @Autowired
    public CardsController(final CardsRepository cardsRepository, final CardsServiceConfig cardsServiceConfig) {
        this.cardsRepository = cardsRepository;
        this.cardsServiceConfig = cardsServiceConfig;
    }

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        log.info("Requesting properties '/properties'");

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardsServiceConfig.getMsg(), cardsServiceConfig.getBuildVersion(),
                cardsServiceConfig.getMailDetails(), cardsServiceConfig.getActiveBranches());

        return objectWriter.writeValueAsString(properties);
    }

    @PostMapping("/")
    public List<Cards> getCards(@RequestHeader("greatnessbank-correlation-id") String correlationId, @RequestBody Customer customer) {
        log.info("Requesting Cards '/' with: {}", customer);

        return cardsRepository.findByCustomerId(customer.getCustomerId());
    }
}
