package ayiman.gio.rest;

import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import ayiman.gio.core.enums.ActiveStatus;
import ayiman.gio.core.exceptions.ErrorResponse;
import ayiman.gio.core.exceptions.NullParameterException;
import ayiman.gio.core.helpers.Const;
import ayiman.gio.entities.dtos.OrganisationDto;
import ayiman.gio.services.OrganisationService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/organisation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrganisationRest {
    @Inject
    OrganisationService organisationService;

    @GET
    @Operation(operationId = "getAll", summary = "Retrieve all organisations in database")
    @APIResponse(responseCode = "200", description = "Organisations successfully retrivied", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = OrganisationDto.class)))
    public Response findAll() {
        Log.info("REQUEST ENDPOINT: OrganisationRest.getAll()");
        return Response.ok(organisationService.findAll(), MediaType.APPLICATION_JSON).build();
    } 

    @GET
    @Path("/find/{guid}")
    @Operation(operationId = "getOne", summary = "Retrieve one organisation in database")
    @APIResponse(responseCode = "200", description = "Organisation successfully retrivied", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = OrganisationDto.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    public Response get(@PathParam("guid") UUID guid) {
        Log.info("REQUEST ENDPOINT: OrganisationRest.get(" + guid + ")");

        if (guid == null) {
            Log.error("ERROR REQUEST ENDPOINT: OrganisationRest.get()");
            Log.error(Const.GIVEN_NULL_PARAMETER);
            throw new NullParameterException();
        }

        return Response.ok(organisationService.getOne(guid), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/activate-desactivate/")
    @Operation(operationId = "activate", summary = "Activate or desactive one organisation in database")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    public Response activate(@QueryParam("guid") UUID guid, @QueryParam("status") @DefaultValue("ACTIVE") ActiveStatus status) {
        Log.info("REQUEST ENDPOINT: OrganisationRest.activate(" + guid + ")");

        if (guid == null) {
            Log.error("ERROR REQUEST ENDPOINT: OrganisationRest.activate()");
            Log.error(Const.GIVEN_NULL_PARAMETER);
            throw new NullParameterException();
        }

        return Response.ok(organisationService.activateOrDesactive(guid, status), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Operation(operationId = "create", summary = "Insert one organisation inside the database")
    @APIResponse(responseCode = "200", description = "Organisation successfully created", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = OrganisationDto.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    public Response create(@Valid OrganisationDto organisationDto) {
        Log.info("REQUEST ENDPOINT: OrganisationRest.created(" + organisationDto + ")");

        if (organisationDto == null) {
            Log.error("ERROR REQUEST ENDPOINT: OrganisationRest.created()");
            Log.error(Const.GIVEN_NULL_PARAMETER);
            throw new NullParameterException();
        }

        OrganisationDto organisation = organisationService.create(organisationDto);

        return Response.ok(organisation, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Operation(operationId = "update", summary = "update one organisation from the database")
    @APIResponse(responseCode = "200", description = "Organisation successfully updated", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = OrganisationDto.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    public Response update(@Valid OrganisationDto organisationDto) {
        Log.info("REQUEST ENDPOINT: OrganisationRest.updated(" + organisationDto + ")");

        if (organisationDto == null) {
            Log.error("ERROR REQUEST ENDPOINT: OrganisationRest.updated()");
            Log.error(Const.GIVEN_NULL_PARAMETER);
            throw new NullParameterException();
        }

        OrganisationDto organisation = organisationService.update(organisationDto);
        return Response.ok(organisation, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{guid}")
    @Operation(operationId = "update", summary = "Remove one organisation from the database")
    @APIResponse(responseCode = "200", description = "Organisation successfully removed")
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    public Response delete(@PathParam("guid") UUID guid) {
        Log.info("REQUEST ENDPOINT: OrganisationRest.delete(" + guid + ")");

        if (guid == null) {
            Log.error("ERROR REQUEST ENDPOINT: OrganisationRest.delete()");
            Log.error(Const.GIVEN_NULL_PARAMETER);
            throw new NullParameterException();
        }

        return Response.ok(organisationService.delete(guid)).build();
    }

}
