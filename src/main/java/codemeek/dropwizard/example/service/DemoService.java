package codemeek.dropwizard.example.service;

import codemeek.dropwizard.example.models.DummyObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jitendragangwar on 5/8/17.
 */
public class DemoService {
    private static Logger log = LoggerFactory.getLogger(DemoService.class);// declared a logger

    public void dummyFunction(){
        log.info("dummy function called");
    }
    public DummyObject dummyFunctionWithObjectParam(DummyObject dummyObject){
        log.info("dummy function with object called");
        log.info("FirstName :: "+dummyObject.getFirstName());
        log.info("LastName :: "+dummyObject.getLastName());
        return dummyObject;
    }
}
