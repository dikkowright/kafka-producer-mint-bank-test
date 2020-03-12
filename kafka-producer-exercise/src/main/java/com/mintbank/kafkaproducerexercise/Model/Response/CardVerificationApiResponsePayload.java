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
public class CardVerificationApiResponsePayload {

    private String scheme;
    private String type;
    private String bank;
}
