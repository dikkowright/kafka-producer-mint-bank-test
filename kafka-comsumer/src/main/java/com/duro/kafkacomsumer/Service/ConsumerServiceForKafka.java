package com.duro.kafkacomsumer.Service;


import com.duro.kafkacomsumer.Model.PaylaodResponse;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceForKafka {

    @KafkaListener(topics = "com.ng.vela.even.card_verified", groupId = "group_json",
            containerFactory = "payloadResponseKafkaListenerFactory")
    public void consumeJson(PaylaodResponse paylaodResponse){
        System.out.println("Consume JSON Message:" + paylaodResponse);
    }

}
