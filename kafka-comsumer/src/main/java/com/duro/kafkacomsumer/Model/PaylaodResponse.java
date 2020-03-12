package com.duro.kafkacomsumer.Model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

public class PaylaodResponse {

    private String visa;
    private String type;
    private String bank;
}
