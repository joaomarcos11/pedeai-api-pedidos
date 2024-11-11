package org.jfm.controller.rest.restclient;

import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jfm.controller.rest.dto.ClienteDto;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "cliente-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ClienteServiceClient {

  @GET
  @Path("clientes/{id}")
  ClienteDto buscarPorId(@PathParam("id") @Parameter(description = "Id do cliente", example = "63a59178-39f8-4a28-a2c7-989a57ca7b54") UUID clienteId);
}
