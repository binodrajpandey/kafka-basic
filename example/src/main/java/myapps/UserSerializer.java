package myapps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class UserSerializer implements Serializer<User>
{
   public void configure(Map<String, ?> var1, boolean var2){

    }

   public byte[] serialize(String var1, User user){
       ObjectMapper objectMapper=new ObjectMapper();
       try{
         return   objectMapper.writeValueAsString(user).getBytes();

       }catch (JsonProcessingException ex){
           System.out.println("couldn't parse user to json");
       }catch (Exception ex){
           System.out.println("unknown exception while parsing user to json");
       }
       return null;
    }



   public void close(){

    }
}
