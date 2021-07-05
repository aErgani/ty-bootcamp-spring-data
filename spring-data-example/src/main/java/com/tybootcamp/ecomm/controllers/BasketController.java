package com.tybootcamp.ecomm.controllers;

import com.tybootcamp.ecomm.entities.Basket;
import com.tybootcamp.ecomm.entities.BasketEntry;
import com.tybootcamp.ecomm.entities.Customer;
import com.tybootcamp.ecomm.entities.Product;
import com.tybootcamp.ecomm.repositories.BasketEntryRepository;
import com.tybootcamp.ecomm.repositories.BasketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(path = "/basket")
public class BasketController {
    private final BasketRepository _basketRepository;
    private final BasketEntryRepository _basketEntryRepository;

    public BasketController(BasketRepository basketRepository, BasketEntryRepository basketEntryRepository) { this._basketRepository = basketRepository; this._basketEntryRepository = basketEntryRepository; }

    @PostMapping(path = "/basket")
    public ResponseEntity<Basket> addNewCustomer(@Valid @RequestBody Set<BasketEntry> basketEntries, Customer customer){
        Basket basket = new Basket();
        basket = _basketRepository.save(basket);
        for (BasketEntry basketEntry:basketEntries) {
            basketEntry = _basketEntryRepository.save(basketEntry);
        }

        return new ResponseEntity<>(basket, HttpStatus.OK);
    }
}
