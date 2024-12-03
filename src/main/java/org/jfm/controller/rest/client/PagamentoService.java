package org.jfm.controller.rest.client;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jfm.controller.rest.dto.PagamentoCreateDto;
import org.jfm.controller.rest.dto.PagamentoDto;


@Path("api/pagamento")
@RegisterRestClient(configKey = "pagamento-api")
public interface PagamentoService {

  @POST
  PagamentoDto criar(@RequestBody(description = "Dados para pagamento", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagamentoCreateDto.class))) PagamentoCreateDto pagamento);
}
