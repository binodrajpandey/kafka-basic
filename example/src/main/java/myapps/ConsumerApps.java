package myapps;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerApps
{
    public static Logger logger = LoggerFactory.getLogger(ConsumerApps.class);

    public static void main(String[] args)
    {
        Properties properties = new Properties();

        String topicName = StringConstant.TOPIC_NAME;
        KafkaConsumer<String, User> consumer=null;
        try
        {
            InputStream input = new FileInputStream(StringConstant.CONSUMER_CONFIG_LOCATION);
            properties.load(input);
            consumer = new KafkaConsumer<>(properties);
            RebalanceListener rebalanceListener=new RebalanceListener(consumer);
            consumer.subscribe(Arrays.asList(topicName),rebalanceListener);
            while (true)
            {
                ConsumerRecords<String, User> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, User> record : records)
                {

                    logger.info("{}", record.value().getName());
                    rebalanceListener.addOffset(record.topic(),record.partition(),record.offset());
                }
              //  consumer.commitAsync();//what if re-balance occurs after 50 record? what if exception occurs after processing 50 records?

            }

        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }finally
        {
           // consumer.commitSync();
            consumer.close();
        }
    }
}
