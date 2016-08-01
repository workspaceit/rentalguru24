package controller.helper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * Created by mi on 8/1/16.
 */
@JsonAutoDetect
public class ServiceResponse {

    public ResponseStat responseStat = null;

    public Object       responseData = null;

    public ServiceResponse() {
        this.responseStat = new ResponseStat();
        this.responseData = new Object();
    }

    public class ResponseStat {
        public boolean status;
        public boolean isLogin;
        public String  msg;

        public ResponseStat() {
            this.status = true;
            this.isLogin = true;
            this.msg = "";
        }

    }

//    public String getResponseData() {
//        System.out.println("123456789");
//        ObjectMapper mapper = new ObjectMapper();
//
//       ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//
//
//        try {
//            return objectMapper.writeValueAsString(this.responseData);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }
}
