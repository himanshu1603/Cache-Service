package codemeek.dropwizard.example.resources;

import com.google.inject.AbstractModule;

import codemeek.dropwizard.example.service.CacheService;
import codemeek.dropwizard.example.service.GACache;

public class CacheModule extends AbstractModule {
		  @Override 
		  protected void configure() {

		     /*
		      * This tells Guice that whenever it sees a dependency on a GACache,
		      * it should satisfy the dependency using a CacheService.
		      */
		    bind(GACache.class).to(CacheService.class);

		  }
		
}
