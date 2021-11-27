package com.banorte.logs.services;

import com.banorte.logs.daos.LogDAO;
import com.banorte.logs.models.GetLogRequestDTO;
import com.banorte.logs.models.GetLogResponseDTO;
import com.banorte.logs.models.SaveLogRequestDTO;
import com.banorte.logs.models.SaveLogResponseDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LogServiceImpl implements LogService {

	@Inject
	LogDAO logDAO;

	@Override
	public SaveLogResponseDTO guardarLog(SaveLogRequestDTO saveLogRequestDTO) {
		return logDAO.guardarLog(saveLogRequestDTO.loggerCoreDTO);
	}

	@Override
	public GetLogResponseDTO getLog(GetLogRequestDTO getLogRequestDTO) {
		return logDAO.getLog(getLogRequestDTO);
	}
}
