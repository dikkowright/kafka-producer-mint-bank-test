package com.mintbank.kafkaproducerexercise.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author duro
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CardVerificationApiResponse {

    private String success;
    private CardVerificationApiResponsePayload payload;

    public static CardVerificationApiResponse fromThirdPartyVerification(
           CardThirdPartyResponse cardThirdPartyResponse){
       System.out.println("response for bank" + cardThirdPartyResponse.getBank());
        CardVerificationApiResponse cardVerificationApiResponse = new CardVerificationApiResponse();
       cardVerificationApiResponse.setSuccess("true");
        cardVerificationApiResponse.setPayload(fromThirdPartyVerificationForPayload(cardThirdPartyResponse));
       return cardVerificationApiResponse;
   }

    public static CardVerificationApiResponsePayload fromThirdPartyVerificationForPayload(
            CardThirdPartyResponse cardThirdPartyResponse){
        CardVerificationApiResponsePayload cardVerificationApiResponsePayload = new CardVerificationApiResponsePayload();
        cardVerificationApiResponsePayload.setBank(cardThirdPartyResponse.getBank().getName());
        cardVerificationApiResponsePayload.setScheme(cardThirdPartyResponse.getScheme());
       cardVerificationApiResponsePayload.setType(cardThirdPartyResponse.getType());
       return cardVerificationApiResponsePayload;
    }
}
