package myapps;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RebalanceListener implements ConsumerRebalanceListener
{
    private KafkaConsumer consumer;
    private Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();

    public RebalanceListener(KafkaConsumer consumer)
    {
        this.consumer = consumer;
    }

    public Map<TopicPartition, OffsetAndMetadata> getCurrentOffsets()
    {
        return currentOffsets;
    }

    public void addOffset(String topic, int partition, long offset)
    {
        currentOffsets.put(new TopicPartition(topic, partition), new OffsetAndMetadata(offset, "commit"));
    }

    public void onPartitionsRevoked(Collection<TopicPartition> partitions)
    {
        System.out.println("Following partition Revoked....");
        for (TopicPartition partition : partitions)
        {
            System.out.println(partition.partition());
        }
        System.out.println("Following partitions committed....");
        for (TopicPartition partition : currentOffsets.keySet())
        {
            System.out.println(partition.partition());
        }
        consumer.commitSync();
        currentOffsets.clear();
    }

    public void onPartitionsAssigned(Collection<TopicPartition> partitions)
    {
        System.out.println("following partitions Assigned........");
        for (TopicPartition partition : partitions)
        {
            System.out.println(partition.partition());
        }
    }

}
