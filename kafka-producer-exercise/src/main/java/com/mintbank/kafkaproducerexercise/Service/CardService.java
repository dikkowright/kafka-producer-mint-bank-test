package com.mintbank.kafkaproducerexercise.Service;


import com.mintbank.kafkaproducerexercise.Model.Entity.Card;
import com.mintbank.kafkaproducerexercise.Model.Request.KafkaProducerRequest;
import com.mintbank.kafkaproducerexercise.Model.Response.CardHitApiResponse;
import com.mintbank.kafkaproducerexercise.Model.Response.CardThirdPartyResponse;
import com.mintbank.kafkaproducerexercise.Model.Response.CardVerificationApiResponse;

import com.mintbank.kafkaproducerexercise.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CardService {



    @Autowired
    private CardRepository cardRepository;
    private RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "https://lookup.binlist.net/";
    private static final Integer DEFAULT_COUNT = 1;



    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "com.ng.vela.even.card_verified";

    public CardService() {
    }

    public CardService(CardRepository cardRepository)
    {
        this.cardRepository = cardRepository;
    }

    public CardService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void pushToKafka(CardVerificationApiResponse cardVerificationApiResponse){
        KafkaProducerRequest kafkaProducerRequest = KafkaProducerRequest.fromCardVerificationApiResponse(cardVerificationApiResponse);
        kafkaTemplate.send(TOPIC, String.valueOf(kafkaProducerRequest));
        System.out.println("Published Successful");
    }



    public CardVerificationApiResponse getCardVerification(
            String bin) throws Exception {
        String url = BASE_URL + bin;
        System.out.println("url " + url);
        CardThirdPartyResponse cardThirdPartyResponse =
                restTemplate.getForObject(url,CardThirdPartyResponse.class);
        System.out.println("response" + cardThirdPartyResponse);
        CardVerificationApiResponse cardVerificationApiResponse = CardVerificationApiResponse
                .fromThirdPartyVerification(cardThirdPartyResponse);
        saveResponse(cardVerificationApiResponse, bin);
        return cardVerificationApiResponse;
    }



    public void saveResponse(CardVerificationApiResponse cardVerificationApiResponse,  String bin){
        System.out.println("Check bin here" + bin);
        List<Card> fetchCards = cardRepository.findAll();
        Card filteredCard = fetchCards.stream()
                .filter(fetchCard-> fetchCard.getBin().equals(bin))
                .findAny()
                .orElse(null);
        if(filteredCard == null){
            Card cardToSave = new Card();
            cardToSave.setBank(cardVerificationApiResponse.getPayload().getBank());
            cardToSave.setScheme(cardVerificationApiResponse.getPayload().getScheme());
            cardToSave.setType(cardVerificationApiResponse.getPayload().getType());
            cardToSave.setCount(String.valueOf(DEFAULT_COUNT));
            cardToSave.setBin(bin);
            cardRepository.save(cardToSave);
        }else {
            String existingCountValue = filteredCard.getCount();
            Integer newCount = Integer.parseInt(existingCountValue) + 1;
            String newCountValue = String.valueOf(newCount);
            filteredCard.setCount(newCountValue);
            cardRepository.save(filteredCard);
        }
    }

    public CardHitApiResponse getCardVerificationNumberOfHits(String start, String limit) throws Exception {
        List<Card> fetchAllCardDetails = cardRepository.findAll();
        long count = fetchAllCardDetails.stream().count();
        Map<String, String> data = new HashMap<>();
        fetchAllCardDetails
                .stream()
                .filter(cardDetail-> cardDetail.getId().equals(start))
                .filter(cardDetail-> cardDetail.getId() <= Integer.parseInt(limit))
                .forEach(cardDetail->{
                   data.put(cardDetail.getBin(), cardDetail.getCount()) ;
                });
        CardHitApiResponse  cardHitApiResponse = new CardHitApiResponse();
        cardHitApiResponse.setPayload(data);
        cardHitApiResponse.setSuccess("true");
        cardHitApiResponse.setStart(start);
        cardHitApiResponse.setLimit(limit);
        cardHitApiResponse.setSize(String.valueOf(count));
        return cardHitApiResponse;
    }

}
