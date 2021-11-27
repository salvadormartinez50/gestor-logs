package com.banorte.logs.models;

import com.banorte.logs.validators.EmptyInfo;
import com.banorte.logs.validators.OrigenInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class LoggerCoreDTO {

    @JsonProperty("processId")
    public String processId;
    @JsonProperty("uuid")
    public String uuid;
    @JsonProperty("businessMsgId")
    @NotBlank(message = "Los parámetros origen, numeroSiniestro y folioAtencion son requeridos.", groups = EmptyInfo.class)
    public String businessMsgId;
    @JsonProperty("type")
    public String type;
    @JsonProperty("level")
    public String level;
    @JsonProperty("origin")
    @NotBlank(message = "Los parámetros origen, numeroSiniestro y folioAtencion son requeridos.", groups = EmptyInfo.class)
    @Pattern(message = "Los valores permitidos para el campo origen son: AMOVIL, CABINA y INT-CONSOLE",
            regexp = "\\bAMOVIL|CABINA|INT-CONSOLE\\b", groups = OrigenInfo.class)
    public String origin;
    @JsonProperty("destination")
    public String destination;
    @JsonProperty("numeroSiniestro")
    @NotBlank(message = "Los parámetros origen, numeroSiniestro y folioAtencion son requeridos.", groups = EmptyInfo.class)
    public String numeroSiniestro;
    @JsonProperty("serviceName")
    public String serviceName;
    @JsonProperty("user")
    public String user;
    @JsonProperty("resendable")
    public String resendable;
    @JsonProperty("description")
    public String description;
    @JsonProperty("serverIp")
    public String serverIp;
    @JsonProperty("dateStart")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public Date dateStart;
    @JsonProperty("dateEnd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public Date dateEnd;
    @JsonProperty("request")
    public String request;
    @JsonProperty("response")
    public String response;
}
