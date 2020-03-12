package com.mintbank.kafkaproducerexercise.Repository;


import com.mintbank.kafkaproducerexercise.Model.Entity.Card;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card, Long> {
    Card findOneByBin(String bin);
    List<Card> findAll();

}
