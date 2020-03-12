package com.mintbank.kafkaproducerexercise.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CardThirdPartyResponseCountry {

    private String numeric;
    private String alpha2;
    private String name;
    private String emoji;
    private String currency;
    private String latitude;
    private String longitude;
}
