package controller.interceptor;

import model.CategoryModel;
import model.StateModel;
import model.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 11/29/16.
 */
public class BaseWebInterceptor extends HandlerInterceptorAdapter {
    protected static List<State> stateList = new ArrayList<>();
    @Autowired
    StateModel stateModel;
    @PostConstruct
    public void initStateModel(){
        stateList = stateModel.getAll();
        System.out.println("*********** USA State loaded in static variable ******************");
    }

}
