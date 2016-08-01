package controller.service;

import controller.BaseHttp;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mi on 8/1/16.
 */
@RequestMapping("/app")
public class BaseService extends BaseHttp{
    public BaseService() {
        System.out.println("Base Service");
     //   this.serviceResponse = new ServiceResponse();
    }
}
