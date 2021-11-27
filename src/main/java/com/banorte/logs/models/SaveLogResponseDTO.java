package com.banorte.logs.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveLogResponseDTO {

    @JsonProperty("response_code")
    public String responseCode;

    public SaveLogResponseDTO() {

    }

    public SaveLogResponseDTO(String responseCode) {
        this.responseCode = responseCode;
    }
}
