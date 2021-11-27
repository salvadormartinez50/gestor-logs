package com.banorte.logs.controllers;

import com.banorte.logs.enums.CodigoError;
import com.banorte.logs.models.ErrorMessageResponseDTO;
import com.banorte.logs.models.SaveLogRequestDTO;
import com.banorte.logs.services.LogService;
import com.banorte.logs.validators.EmptyInfo;
import com.banorte.logs.validators.OrigenInfo;
import org.apache.http.HttpStatus;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/CommonInfrastructure/ProxyServices")
@Consumes("application/json")
@Produces("application/json")
public class SaveLogController {

    @Inject
    LogService logService;

    @Inject
    Validator validator;

    @POST
    @Path("/LoggerSrv")
    public Response guardarLog(SaveLogRequestDTO saveLogRequestDTO) {
        Set<ConstraintViolation<SaveLogRequestDTO>> violations = validator.validate(saveLogRequestDTO, EmptyInfo.class);
        if (!violations.isEmpty()) {
            return generarRespuestaError(violations, CodigoError.NTF_CIERRE_DATA);
        }
        violations = validator.validate(saveLogRequestDTO, OrigenInfo.class);
        if (!violations.isEmpty()) {
            return generarRespuestaError(violations, CodigoError.NTF_CIERRE_ORIGEN);
        }
        return Response.ok(logService.guardarLog(saveLogRequestDTO)).build();
    }

    private Response generarRespuestaError(Set<ConstraintViolation<SaveLogRequestDTO>> violations, CodigoError codigoError) {
        return Response.status(HttpStatus.SC_BAD_REQUEST).entity(new ErrorMessageResponseDTO(violations,
                codigoError)).build();
    }
}
