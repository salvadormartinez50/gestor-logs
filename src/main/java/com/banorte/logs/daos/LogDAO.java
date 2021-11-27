package com.banorte.logs.daos;

import com.banorte.logs.models.GetLogRequestDTO;
import com.banorte.logs.models.GetLogResponseDTO;
import com.banorte.logs.models.SaveLogResponseDTO;
import com.banorte.logs.models.LoggerCoreDTO;

public interface LogDAO {

    public SaveLogResponseDTO guardarLog(LoggerCoreDTO loggerCoreDTO);

    public GetLogResponseDTO getLog(GetLogRequestDTO getLogRequestDTO);
}
