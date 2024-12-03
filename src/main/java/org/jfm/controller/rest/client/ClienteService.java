package org.jfm.controller.rest.client;

import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestResponse;
import org.jfm.controller.rest.dto.ClienteDto;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/clientes")
@RegisterRestClient(configKey="cliente-api")
public interface ClienteService {

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  ClienteDto buscarPorId(@PathParam("id") @Parameter(description = "Id do cliente", example = "63a59178-39f8-4a28-a2c7-989a57ca7b54", required = true) UUID id);
}
