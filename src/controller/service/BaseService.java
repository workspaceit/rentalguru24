package controller.service;

import controller.BaseHttp;
import controller.helper.ServiceResponse;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mi on 8/1/16.
 */
@RestController("baseService")
public class BaseService extends BaseHttp{
    public BaseService() {
        System.out.println("Base Service");
        this.serviceResponse = new ServiceResponse();
    }
}
