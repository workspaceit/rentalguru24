package ConsoleTesting;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static java.lang.System.out;

/**
 * Created by mi on 9/21/16.
 */
public class Test {
    public static void main(String args[]){


    }
    double CoordDistance(double latitude1, double longitude1, double latitude2, double longitude2)
    {
        return 6371 * Math.acos(
                Math.sin(latitude1) * Math.sin(latitude2)
                        + Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longitude2 - longitude1));
    }
}



