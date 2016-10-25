package ConsoleTesting;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * Created by mi on 9/21/16.
 */
public class Test {
    public static void main(String args[]){
        HashMap<String,Object> params = new HashMap<>();
        String prevClause = " where ";
        params.put("title","assd");
        params.put("categoryId",11D);
        for(Map.Entry<String,Object> entry:params.entrySet()){
            System.out.println( entry.getKey()+" "+entry.getValue());
        }

    }

}



