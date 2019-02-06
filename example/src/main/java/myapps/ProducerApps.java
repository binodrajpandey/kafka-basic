package myapps;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProducerApps
{
    public static Logger logger = LoggerFactory.getLogger(ProducerApps.class);

    public static void main(String[] args)
    {
        Properties properties = new Properties();
        String topicName = StringConstant.TOPIC_NAME;

        try
        {
            InputStream input = new FileInputStream(StringConstant.PRODUCER_CONFIG_LOCATION);
            properties.load(input);
            Producer<String, User> producer = new KafkaProducer<>(properties);
            User user;
            for (char i = 'a'; i <= 'z'; i++)
            {
                logger.info("record is being sent");
                user = new User(i, "name" + i, "address" + i);
                try
                {
                    producer.send(new ProducerRecord<>(topicName, String.valueOf(i), user));

                }
                catch (Exception ex)
                {
                    logger.error("Could not send user: {}", user);
                }

            }
            producer.close();
        }
        catch (IOException ex)
        {
            logger.error(ex.getMessage());
        }

    }
}
