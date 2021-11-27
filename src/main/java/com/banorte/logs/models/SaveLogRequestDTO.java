package com.banorte.logs.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveLogRequestDTO {

    @JsonProperty("LoggerCore")
    public LoggerCoreDTO loggerCoreDTO;
}
