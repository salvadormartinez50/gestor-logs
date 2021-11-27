package com.banorte.logs.exceptions;

import com.banorte.logs.enums.CodigoError;
import com.banorte.logs.models.ErrorMessageResponseDTO;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DatabaseExceptionHandler implements ExceptionMapper<DatabaseException> {

    @Inject
    Logger log;

    public Response toResponse(DatabaseException exception) {
        log.error("Error al conectarse a la base de datos: ".concat(exception.getMessage()));
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessageResponseDTO("Existe un error al registrar información en base.", CodigoError.ERR_BASE_DATA)).build();
    }
}
