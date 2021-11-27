package com.banorte.logs.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetLogResponseDTO {

    @JsonProperty("o_level")
    public String level;
    @JsonProperty("o_code")
    public String code;
    @JsonProperty("o_message")
    public String message;

    public GetLogResponseDTO() {

    }

    public GetLogResponseDTO(String level, String code, String message) {
        this.level = level;
        this.code = code;
        this.message = message;
    }
}
