package myapps;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class CustomPartitioner implements Partitioner
{
   public void configure(Map<String, ?> var1){

    }
  public  int partition(String var1, Object var2, byte[] var3, Object var4, byte[] var5, Cluster var6){
      return 0;
  }

   public void close(){

   }
}
