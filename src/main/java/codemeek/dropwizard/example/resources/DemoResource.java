package codemeek.dropwizard.example.resources;

import codemeek.dropwizard.example.DropConfiguration;
import codemeek.dropwizard.example.models.DummyObject;
import codemeek.dropwizard.example.service.DemoService;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jitendragangwar on 5/1/17.
 */
@Slf4j
@Path("/demo/")
@Consumes(MediaType.APPLICATION_JSON)
public class DemoResource {
    private final DropConfiguration dropConfiguration;
    private final DemoService demoService;

    public DemoResource(DropConfiguration dropConfiguration, DemoService demoService) {
        this.dropConfiguration = dropConfiguration;
        this.demoService = demoService;
    }

    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,String> getAppName(){
        //log.info("application name is :: "+dropConfiguration.getAppName());
        Map<String,String> map = new HashMap<>();
        map.put("name",dropConfiguration.getAppName());
        return map;
    }

    @POST
    @Path("/dummy")
    @Produces(MediaType.APPLICATION_JSON)
    public DummyObject getDummyObject(DummyObject dummyObject){
        DummyObject dummyObject1 = demoService.dummyFunctionWithObjectParam(dummyObject);
        return dummyObject1;
    }

    
}
