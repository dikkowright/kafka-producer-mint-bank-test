package com.mintbank.kafkaproducerexercise.Model.Request;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.mintbank.kafkaproducerexercise.Model.Response.CardVerificationApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KafkaProducerRequest {

    private String scheme;
    private String type;
    private String bank;

    public static KafkaProducerRequest fromCardVerificationApiResponse(CardVerificationApiResponse cardVerificationApiResponse){
        return KafkaProducerRequest
                .builder()
                .bank(cardVerificationApiResponse.getPayload().getBank())
                .scheme(cardVerificationApiResponse.getPayload().getScheme())
                .type(cardVerificationApiResponse.getPayload().getType())
                .build();
    }
}
