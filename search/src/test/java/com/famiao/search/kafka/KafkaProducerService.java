package com.famiao.search.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @desc
 * @author famiao:曾斌
 * @version 创建时间：Jul 8, 2019 2:44:34 PM
 */
public class KafkaProducerService {
    //private static Logger LOG = LoggerFactory.getLogger(KafkaProducerService.class);

    @SuppressWarnings("resource")
	public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.1:9092,192.168.1.2:9092,192.168.1.3:9092");
        props.put("retries", 3);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 1; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("tests", "11", "今天天气不错哟yoyo=======>" + i);
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e != null) {
                        System.out.println("the producer has a error:" + e.getMessage());
                    } else {
                        System.out.println("The offset of the record we just sent is: " + metadata.offset());
                        System.out.println("The partition of the record we just sent is: " + metadata.partition());
                    }
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}