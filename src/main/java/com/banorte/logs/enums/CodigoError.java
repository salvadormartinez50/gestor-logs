package com.banorte.logs.enums;

public enum CodigoError {
    ERR_BASE_DATA("ERR-BASE-DATA"),
    NTF_CIERRE_DATA("NTF-CIERRE-DATA"),
    NTF_CIERRE_ORIGEN("NTF-CIERRE-ORIGEN");

    private final String errorCode;

    private CodigoError(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
