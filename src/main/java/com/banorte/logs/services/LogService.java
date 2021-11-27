package com.banorte.logs.services;

import com.banorte.logs.models.GetLogRequestDTO;
import com.banorte.logs.models.GetLogResponseDTO;
import com.banorte.logs.models.SaveLogRequestDTO;
import com.banorte.logs.models.SaveLogResponseDTO;

public interface LogService {

    public SaveLogResponseDTO guardarLog(SaveLogRequestDTO saveLogRequestDTO);

    public GetLogResponseDTO getLog(GetLogRequestDTO getLogRequestDTO);
}
