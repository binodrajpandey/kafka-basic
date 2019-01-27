package myapps;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class UserDeserializer implements Deserializer<User>
{
    public void configure(Map<String, ?> var1, boolean var2)
    {

    }

    public User deserialize(String var1, byte[] userArr)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            String json=new String(userArr);
            System.out.println(json);
            return objectMapper.readValue(userArr, User.class);

        }
        catch (IOException ex)
        {
            System.out.println("could not deserialize json in to user object");
            ex.printStackTrace();
            return null;
        }
    }

    public void close()
    {

    }
}
