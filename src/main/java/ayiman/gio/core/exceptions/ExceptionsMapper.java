package ayiman.gio.core.exceptions;

import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

public class ExceptionsMapper {

    @Context
    private UriInfo uriInfo;

    @ServerExceptionMapper
    public Response mapException(EntityNotFoundException e) {
        ErrorResponse error = e.errorResponse;
        error.setPath(uriInfo.getAbsolutePath().toString());

        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }

    @ServerExceptionMapper
    public Response mapException(OrganisationNotActiveException e) {
        ErrorResponse error = e.errorResponse;
        error.setPath(uriInfo.getAbsolutePath().toString());

        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }

    @ServerExceptionMapper
    public Response mapException(NullParameterException e) {
        ErrorResponse error = e.errorResponse;
        error.setPath(uriInfo.getAbsolutePath().toString());

        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
    

}
