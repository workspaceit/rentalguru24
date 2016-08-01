package helper;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

/**
 * Created by mi on 8/1/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServiceResponse {

    public ResponseStat responseStat;
    public Object       responseData;

    public ServiceResponse() {
        this.responseStat = new ResponseStat();
        this.responseData = new Object();
    }

    public class ResponseStat {
        public boolean status;
        public boolean isLogin;
        public String  msg;
        public ArrayList<RequestError> requestErrors;

        public ResponseStat() {
            this.status = true;
            this.isLogin = true;
            this.msg = "";
            this.requestErrors = new ArrayList<RequestError>();
        }

    }
    public class RequestError{
        public String params;
        public String msg;
        RequestError() {
            this.params = "";
            this.msg = "";
        }
    }
    public Object getResponseData() {
        System.out.println(this.responseData.getClass());
        return (this.responseData.getClass().getSimpleName().equals("Object"))?null:this.responseData;

    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }
}
