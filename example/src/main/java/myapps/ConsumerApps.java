package myapps;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerApps
{
    public static void main(String[] args){
        Properties properties=new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("group.id","test");
        properties.put("enable.auto.commit",true);
        properties.put("auto.commit.interval.ms","1000");
        properties.put("key.deserializer",
                  "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put("value.deserializer",
//                  "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer",
                       "myapps.UserDeserializer");
        KafkaConsumer<String,User> consumer=new KafkaConsumer<String, User>(properties);
        String topicName="streams-wordcount-output";

        consumer.subscribe(Arrays.asList(topicName));
        while(true){
           ConsumerRecords<String,User> records= consumer.poll(100);
           for(ConsumerRecord<String,User> record:records){
               try{
                   System.out.println(record.value().getName());
               }catch (Exception ex){

               }

           }
        }
    }
}
