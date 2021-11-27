package com.banorte.logs.models;

import com.banorte.logs.validators.EmptyInfo;
import com.banorte.logs.validators.ProcessIdInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class GetLogRequestDTO {

    @JsonProperty("I_PROCESS_ID")
    @NotBlank(message = "El parámetro processID es requerido.", groups = EmptyInfo.class)
    @Pattern(message = "El valor permitido para el campo processID no es válido.",
            regexp = "\\bAMIS-SINC-MOTIVPEND|AMIS-SINC-MOTIVPENDCP|AMIS-SINC-TERMINOATNCP|AMIS-SINC-IMGSWEB|AMIS-SINC-FOLIOSINIESTROWEB|AMIS-SINC-DATSINIESTROWEB|AMIS-SINC-DATSINIESTROCPTEWEB|AMIS-SINC-DATSINIESTRO|AMIS-SINC-VOUCHER|AMIS-SINC-ORDENESPAGO|AMIS-SINC-TERMINOATN|AMIS-SINC-FOTOGRAFIAS|AMIS-SINC-INFAJUSTADOR|AMIS-SINC-VINCULARFOLIOCPTE|AMIS-SINC-DATCONTRAPARTES|AMIS-SINC-REFOLEOCPTE|AMIS-SINC-TERMINOATNWEB\\b", groups = ProcessIdInfo.class)
    public String processId;
}
