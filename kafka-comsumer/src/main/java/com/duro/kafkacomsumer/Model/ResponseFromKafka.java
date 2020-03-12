package com.duro.kafkacomsumer.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ResponseFromKafka {

    private String success;
    private PaylaodResponse paylaodResponse;
}
