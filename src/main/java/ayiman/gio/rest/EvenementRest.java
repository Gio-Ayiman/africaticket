package ayiman.gio.rest;

import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import ayiman.gio.core.enums.OpenCloture;
import ayiman.gio.core.exceptions.ErrorResponse;
import ayiman.gio.core.exceptions.NullParameterException;
import ayiman.gio.core.helpers.Const;
import ayiman.gio.entities.dtos.EvenementDto;
import ayiman.gio.services.EvenementService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/evenement")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EvenementRest {
    
    @Inject
    EvenementService evenementService;

    @GET
    @Path("find/{guid}")
    @Operation(operationId = "find", summary = "Retrieve one evenement in database")
    @APIResponse(responseCode = "200", description = "Evenement successfully retrivied", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = EvenementDto.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    public Response find(@PathParam("guid") UUID guid) {
        Log.info("REQUEST ENDPOINT: EvenementRest.get(" + guid + ")");

        if (guid == null) {
            Log.error("ERROR REQUEST ENDPOINT: EvenementRest.get()");
            Log.error(Const.GIVEN_NULL_PARAMETER);
            throw new NullParameterException();
        }

        EvenementDto evenementDto = evenementService.getOne(guid);
        return Response.ok(evenementDto).build();
    }

    @POST
    @Operation(operationId = "create", summary = "Insert one evenement inside the database")
    @APIResponse(responseCode = "200", description = "Evenement successfully created", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = EvenementDto.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    public Response create(@Valid EvenementDto evenementDto){
        Log.info("REQUEST ENDPOINT: EvenementRest.created(" + evenementDto + ")");

        if (evenementDto == null) {
            Log.error("ERROR REQUEST ENDPOINT: EvenementRest.created()");
            Log.error(Const.GIVEN_NULL_PARAMETER);
            throw new NullParameterException();
        }

        EvenementDto event = evenementService.create(evenementDto);
        return Response.ok(event).build();
    }

    @GET
    @Operation(operationId = "getAll", summary = "Retrieve all evenements in database")
    @APIResponse(responseCode = "200", description = "Evenements successfully retrivied", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = EvenementDto.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    public Response findAll() {
        Log.info("REQUEST ENDPOINT: EvenementRest.getAll()");
        return Response.ok(evenementService.findAll(), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/findAll/organisation/{guid}")
    @Operation(operationId = "getAllByOrganisation", summary = "Retrieve all evenements in database by organisation")
    @APIResponse(responseCode = "200", description = "Evenements successfully retrivied", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = EvenementDto.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    public Response findAllByOrganisation(@PathParam("guid") UUID guid) {
        Log.info("REQUEST ENDPOINT: EvenementRest.findAllByOrganisation(" + guid + ")");

        if (guid == null) {
            Log.error("ERROR REQUEST ENDPOINT: EvenementRest.findAllByOrganisation()");
            Log.error(Const.GIVEN_NULL_PARAMETER);
            throw new NullParameterException();
        }

        return Response.ok(evenementService.findAllByOrganisation(guid), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/open-close")
    @Operation(operationId = "openOrClose", summary = "Open or close one evenement from the database")
    @APIResponse(responseCode = "200", description = "Evenement successfully open or closed", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    public Response openOrClose(@QueryParam("guid") UUID guid, @QueryParam("action") OpenCloture action) {
        Log.info("REQUEST ENDPOINT: EvenementRest.openOrClose(" + guid + ", " + action + ")");

        if (guid == null || action == null) {
            Log.error("ERROR REQUEST ENDPOINT: EvenementRest.openOrClose()");
            Log.error(Const.GIVEN_NULL_PARAMETER);
            throw new NullParameterException();
        }

        return Response.ok(evenementService.openOrCloture(guid, action)).build();

    }


    @PUT
    @Operation(operationId = "update", summary = "Update one evenement from the database")
    @APIResponse(responseCode = "200", description = "Evenement successfully updated", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = EvenementDto.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    public Response update(@Valid EvenementDto evenementDto){
        Log.info("REQUEST ENDPOINT: EvenementRest.update(" + evenementDto + ")");

        if (evenementDto == null) {
            Log.error("ERROR REQUEST ENDPOINT: EvenementRest.update()");
            Log.error(Const.GIVEN_NULL_PARAMETER);
            throw new NullParameterException();
        }

        EvenementDto event = evenementService.update(evenementDto);
        return Response.ok(event).build();
    } 

    @DELETE
    @Path("{guid}")
    @Operation(operationId = "delete", summary = "Delete one evenement from the database")
    @APIResponse(responseCode = "200", description = "Evenement successfully deleted")
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    public Response update(@PathParam("guid") UUID guid){
        Log.info("REQUEST ENDPOINT: EvenementRest.delete(" + guid + ")");

        if (guid == null) {
            Log.error("ERROR REQUEST ENDPOINT: EvenementRest.delete()");
            Log.error(Const.GIVEN_NULL_PARAMETER);
            throw new NullParameterException();
        }

        return Response.ok(evenementService.delete(guid)).build();
    } 
}
