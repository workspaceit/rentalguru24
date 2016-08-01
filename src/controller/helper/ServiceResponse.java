package controller.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;



import java.util.ArrayList;

/**
 * Created by mi on 8/1/16.
 */

//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Component
//@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@JsonInclude(JsonInclude.Include.NON_NULL)


//@Component("serviceResponse")
//@Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ServiceResponse  {

    public ResponseStat responseStat;
    public Object       responseData;

    public ServiceResponse() {
        System.out.println("ServiceResponse() Called");
        this.responseStat = new ResponseStat();
        this.responseData = new Object();
    }

    public class ResponseStat {
        public boolean status;
        public boolean isLogin;
        public String  msg;
        public ArrayList<RequestError> requestErrors;

        public ResponseStat() {
            System.out.println("ResponseStat() Called");
            this.status = true;
            this.isLogin = true;
            this.msg = "";
            this.requestErrors = new ArrayList<RequestError>();
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public boolean isLogin() {
            return isLogin;
        }

        public void setIsLogin(boolean isLogin) {
            this.isLogin = isLogin;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public ArrayList<RequestError> getRequestErrors() {
            return requestErrors;
        }

        public void setRequestErrors(ArrayList<RequestError> requestErrors) {
            this.requestErrors = requestErrors;
        }
    }
    public class RequestError{
        public String params;
        public String msg;
        RequestError() {
            this.params = "";
            this.msg = "";
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
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