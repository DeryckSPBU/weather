package org.test.exception;

import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;
import org.jboss.logging.Logger;

public class RestExceptionMapper implements ResponseExceptionMapper<RuntimeException> {

    private static final Logger LOG = Logger.getLogger(RestExceptionMapper.class);
    @Override
    public RuntimeException toThrowable(Response response) {
        return switch (response.getStatus()) {
            case 404 -> {
                LOG.error("Request failed with 404 status code");
                yield new NotFoundException("Couldn't find the data");
            }
            case 401 -> {
                LOG.error("Request failed with 401 status code");
                yield new AuthException("Couldn't retrieve the data");
            }
            default -> null;
        };
    }
}
