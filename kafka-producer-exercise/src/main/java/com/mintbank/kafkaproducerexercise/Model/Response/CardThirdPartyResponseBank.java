package com.mintbank.kafkaproducerexercise.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CardThirdPartyResponseBank {

    private String name;
    private String url;
    private String phone;
    private String city;
}
