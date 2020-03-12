package com.mintbank.kafkaproducerexercise.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CardHitApiResponse {

    private String success;
    private String start;
    private String limit;
    private String size;
    private Map<String, String> payload;
}