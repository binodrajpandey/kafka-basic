package myapps;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerApps
{
    public static void main(String[] args){
        Properties properties=new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("acks","all");
        properties.put("retries",0);
        properties.put("key.serializer",
                  "org.apache.kafka.common.serialization.StringSerializer");

//        properties.put("value.serializer",
//                  "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer",
                       "myapps.UserSerializer");
        String topicName="streams-wordcount-output";
        Producer<String,User> producer=new KafkaProducer<String, User>(properties);
        for(char i='a';i<='z';i++){
            System.out.println("record sent");
            producer.send(new ProducerRecord<>(topicName,String.valueOf(i),new User(i,"name"+i,"address"+i)));

        }
        producer.close();
    }
}
