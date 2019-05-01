package codemeek.dropwizard.example.resources;
import codemeek.dropwizard.example.DropConfiguration;
import codemeek.dropwizard.example.models.CacheObject;
import codemeek.dropwizard.example.models.DummyObject;
import codemeek.dropwizard.example.service.CacheService;
import codemeek.dropwizard.example.service.DemoService;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Path("/cache")
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
public class CacheResource {
	private static Logger log = LoggerFactory.getLogger(CacheResource.class);
    private final DropConfiguration dropConfiguration;
    private final CacheService cacheService;

    public CacheResource(DropConfiguration dropConfiguration, CacheService cacheService) {
        this.dropConfiguration = dropConfiguration;
        this.cacheService = cacheService;
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,String> getAppName(@QueryParam("key") String key){
        log.info("Key from get API is "+key);
        String value = cacheService.getValue(key);
        Map<String,String> map = new HashMap<>();
        map.put(key,value);
        return map;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void setDataInCache(CacheObject cacheObject){
    	if(cacheObject != null) {
        cacheService.setValue(cacheObject.getKey(), cacheObject.getValue());
        }
    	else {
    		log.info("Cache object is null");
    	}
        
    }

    
}
