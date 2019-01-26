# kafka-basic
#Steps to start kafka in window
1. Start zookeeper server
`bin/windows/zookeeper-server-start.bat config/zookeeper.properties`
2. start kafka broker
`bin/windows/kafka-server-start.bat ./config/server.properties`
3. To create a topic, go to windows folder and hit the following command.
`./kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic streams-wordcount-output --config cleanup.policy=compact`
4. Create a producer by in same folder
`./kafka-console-producer.bat --broker-list localhost:9092 --topic test`
5. Create subscriber being at same folder
` ./kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic streams-wordcount-output \
    --from-beginning \
    --formatter kafka.tools.DefaultMessageFormatter \
    --property print.key=true \
    --property print.value=true \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer`
