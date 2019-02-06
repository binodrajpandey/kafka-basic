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
        try
        {
            InputStream input = new FileInputStream(StringConstant.CONSUMER_CONFIG_LOCATION);
            properties.load(input);
            KafkaConsumer<String, User> consumer = new KafkaConsumer<String, User>(properties);
            consumer.subscribe(Arrays.asList(topicName));
            while (true)
            {
                ConsumerRecords<String, User> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, User> record : records)
                {

                    logger.info("{}", record.value().getName());
                }

            }

        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }
    }
}
