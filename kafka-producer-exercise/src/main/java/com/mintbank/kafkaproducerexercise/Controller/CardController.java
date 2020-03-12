package com.mintbank.kafkaproducerexercise.Controller;

import com.mintbank.kafkaproducerexercise.Model.Response.CardHitApiResponse;
import com.mintbank.kafkaproducerexercise.Model.Response.CardVerificationApiResponse;
import com.mintbank.kafkaproducerexercise.Service.CardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card-scheme")
public class CardController {


    private CardService cardService = new CardService();
    private static final String TOPIC = "com.ng.vela.even.card_verified";



    @RequestMapping(path = "/verify/{bin}", produces = "application/json" , method = RequestMethod.GET)
    public CardVerificationApiResponse getCardVerification(@PathVariable String bin) throws Exception {
        CardVerificationApiResponse cardVerificationApiResponse = cardService.getCardVerification(bin);
        return cardVerificationApiResponse;
    }

    @RequestMapping(path = "/stats", produces = "application/json" , method = RequestMethod.GET)
    public CardHitApiResponse getCardVerificationNumberOfHits(
            @RequestParam("start") String start,
            @RequestParam("limit") String limit) throws Exception {
        CardHitApiResponse cardVerificationApiResponse =
                cardService.getCardVerificationNumberOfHits(start, limit);
        return cardVerificationApiResponse;
    }

}
