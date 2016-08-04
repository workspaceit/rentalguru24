package controller.service;

import controller.BaseHttp;
import helper.ServiceResponse;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mi on 8/1/16.
 */

public class BaseService extends BaseHttp{
    public BaseService() {

        this.serviceResponse = new ServiceResponse();
    }
}
