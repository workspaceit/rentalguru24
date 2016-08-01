package controller;

import controller.helper.ServiceResponse;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mi on 8/1/16.
 */
@RestController("baseHttp")
public class BaseHttp {
   public ServiceResponse serviceResponse;

    public BaseHttp() {
        this.serviceResponse = new ServiceResponse();
    }
}
