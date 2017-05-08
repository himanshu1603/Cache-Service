package codemeek.dropwizard.example.filter;

import codemeek.dropwizard.example.DropApplication;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by jitendragangwar on 5/1/17.
 */

@Provider
public class RequestFilter implements ContainerRequestFilter{
    private static Logger log = LoggerFactory.getLogger(RequestFilter.class); // declared a request filter
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String transactionId = UUID.randomUUID().toString();
        MDC.put("id",transactionId);
        log.info("transaction id :: "+transactionId);
        requestContext.setProperty("ga-property",transactionId);
    }
}
