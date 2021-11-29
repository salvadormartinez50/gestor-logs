package com.banorte.logs.daos;

import com.banorte.logs.exceptions.DatabaseException;
import com.banorte.logs.models.GetLogRequestDTO;
import com.banorte.logs.models.GetLogResponseDTO;
import com.banorte.logs.models.SaveLogResponseDTO;
import com.google.gson.Gson;
import com.banorte.logs.models.LoggerCoreDTO;
import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import java.beans.Statement;
import java.sql.*;
import java.text.ParseException;
import java.util.Date;

@ApplicationScoped
public class LogDAOImpl implements LogDAO {

	@Inject
	@DataSource("integracion")
	AgroalDataSource dataSource;

	@Inject
	Logger log;

	@Override
	public SaveLogResponseDTO guardarLog(LoggerCoreDTO loggerCoreDTO) {

		String responseCode = "ERROR";
		Gson gson = new Gson();
		log.info("La peticion de entrada es: ");
		log.info(gson.toJson(loggerCoreDTO));
		log.info((gson.toJson(loggerCoreDTO)).toString());
		try (Connection con = dataSource.getConnection();) {
			try (CallableStatement statement = con.prepareCall(
					"{CALL OSBLOGDV.PINT_SP_UPSERT_LOG_MESSAGE(:I_UUID,:I_BUSINESS_MSG_ID,:I_PROCESS_ID,:I_LOG_TYPE,:I_SENDER,:I_TARGET,:I_SUB_PROCESS,:I_INFO,:I_RESENDABLE_MSG,:I_START_DATE,:I_END_DATE,:I_REQUEST_MSG,:I_RESPONSE_MSG,?,?)}");) {

				statement.setString(1, loggerCoreDTO.uuid);
				statement.setString(2, loggerCoreDTO.businessMsgId);
				statement.setString(3, loggerCoreDTO.processId);
				statement.setString(4, loggerCoreDTO.type);
				statement.setString(5, loggerCoreDTO.origin);
				statement.setString(6, loggerCoreDTO.destination);
				statement.setString(7, loggerCoreDTO.subProcess);
				statement.setString(8, loggerCoreDTO.description);
				statement.setString(9, loggerCoreDTO.resendable);
				statement.setTimestamp(10, converterStringToTimestamp(loggerCoreDTO.dateStart));
				statement.setTimestamp(11, converterStringToTimestamp(loggerCoreDTO.dateEnd));
				Clob requestClob = con.createClob();
				Clob responseClob = con.createClob();
				log.info("Como llega el request :" + loggerCoreDTO.request);
				log.info("Como llega en response :" + loggerCoreDTO.response);
				if (loggerCoreDTO.type.equals("3")) {
					statement.setString(7, "Request");
					requestClob.setString(1, loggerCoreDTO.request);
				}
				else
					statement.setString(7, "Response");
					responseClob.setString(1, loggerCoreDTO.response);

				statement.setClob(12, requestClob);
				statement.setClob(13, responseClob);
				statement.registerOutParameter(14, Types.VARCHAR);
				statement.registerOutParameter(15, Types.VARCHAR);
				statement.execute();
				log.info("La respuesta de la base de datos es: ");
				responseCode = "OK".equals(statement.getString(14)) ? "1" : "0";
				log.info("{\"response_code\" : " + gson.toJson(responseCode) + "}");
				// log.info(statement.getClob(12));
				log.info(statement.getString(14));
				log.info(statement.getString(15));
			} catch (Exception exception) {
				throw new DatabaseException(exception.getMessage());
			}

		} catch (SQLException sqlException) {
			throw new DatabaseException(sqlException.getMessage());
		}

		return new SaveLogResponseDTO(responseCode);
	}

	@Override
	public GetLogResponseDTO getLog(GetLogRequestDTO getLogRequestDTO) {
		GetLogResponseDTO respuesta = new GetLogResponseDTO();
		log.info(respuesta);
		try (Connection con = dataSource.getConnection()) {
			try {
				CallableStatement statement = con
						.prepareCall("{CALL OSBLOGDV.PINT_GET_LOG_LEVEL(:I_PROCESS_ID,?,?,?)}");
				statement.setString(1, getLogRequestDTO.processId);
				statement.registerOutParameter(2, Types.VARCHAR);
				statement.registerOutParameter(3, Types.VARCHAR);
				statement.registerOutParameter(4, Types.VARCHAR);
				statement.execute();
				respuesta = crearGetLogResponse(statement);
				log.info(respuesta);
				return respuesta;

			} catch (Exception exception) {
				throw new DatabaseException(exception.getMessage());
			} finally {
				con.close();

			}
		} catch (SQLException sqlException) {
			throw new DatabaseException(sqlException.getMessage());
		}
	}

	private GetLogResponseDTO crearGetLogResponse(CallableStatement statement) throws SQLException {
		String level = statement.getString(2);
		String code = statement.getString(3);
		String message = statement.getString(4);
		log.info(message);
		return new GetLogResponseDTO(level, code, message);
	}

	private Timestamp converterStringToTimestamp(Date fecha) throws ParseException {
		return new Timestamp(fecha.getTime());
	}
}
