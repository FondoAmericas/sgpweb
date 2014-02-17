package pe.com.fondam.sgp.core.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DatoUsuarioDAO;
import pe.com.fondam.sgp.core.dao.EstadoTransaccionDAO;
import pe.com.fondam.sgp.core.dao.TablaClaseDAO;
import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.domain.EstadoTransaccion;
import pe.com.fondam.sgp.core.domain.TablaClase;
import pe.com.fondam.sgp.core.service.AuditoriaService;
import pe.com.fondam.sgp.core.util.UtilValidate;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

	protected final Log logger = LogFactory.getLog(AuditoriaServiceImpl.class);

	@Resource
	private TablaClaseDAO tablaClaseDAO;

	@Resource
	private EstadoTransaccionDAO estadoTransaccionDAO;

	@Resource
	private DatoUsuarioDAO datoUsuarioDAO;

	public TablaClaseDAO getTablaClaseDAO() {
		return tablaClaseDAO;
	}

	public void setTablaClaseDAO(TablaClaseDAO tablaClaseDAO) {
		this.tablaClaseDAO = tablaClaseDAO;
	}

	public EstadoTransaccionDAO getEstadoTransaccionDAO() {
		return estadoTransaccionDAO;
	}

	public void setEstadoTransaccionDAO(
			EstadoTransaccionDAO estadoTransaccionDAO) {
		this.estadoTransaccionDAO = estadoTransaccionDAO;
	}

	public DatoUsuarioDAO getDatoUsuarioDAO() {
		return datoUsuarioDAO;
	}

	public void setDatoUsuarioDAO(DatoUsuarioDAO datoUsuarioDAO) {
		this.datoUsuarioDAO = datoUsuarioDAO;
	}

	@Override
	public void grabarEstadoTablas(Integer datoUsuarioID, Integer estadoID,
			String nombreClase, Integer valorId) throws Exception {

		logger.info("grabarEstadoTablas: Start");

		String message = null;
		validateParams(datoUsuarioID, estadoID, nombreClase, valorId);

		DatoUsuario datoUsuario = getDatoUsuarioDAO().findDatoUsuarioById(
				datoUsuarioID);

		TablaClase tablaClase = getTablaClaseDAO().findByClaseNombre(
				nombreClase);

		if (UtilValidate.isEmpty(datoUsuario)) {
			message = "El datoUsuarioID " + datoUsuarioID + " No Existe";
			logger.error(message);
			throw new Exception(message);
		}
		if (UtilValidate.isEmpty(tablaClase)) {
			message = "El nombreClase " + nombreClase + " No Existe";
			logger.error(message);
			throw new Exception(message);
		}

		EstadoTransaccion estadoTransaccion = new EstadoTransaccion();
		estadoTransaccion.setFechaTransaccion(new Date());
		estadoTransaccion.setFkIdDetalleEstadoCabEstadoEval(estadoID);
		estadoTransaccion.setValorTablaId(valorId.longValue());
		estadoTransaccion.setTablaClase(tablaClase);
		estadoTransaccion.setDatoUsuario(datoUsuario);

		getEstadoTransaccionDAO().saveEstadoTransaccion(estadoTransaccion);

		logger.info("grabarEstadoTablas: Finish");
	}

	private void validateParams(Integer datoUsuarioID, Integer estadoID,
			String nombreClase, Integer valorId) throws Exception {
		logger.info("validateParams: Start");
		String message = null;
		logger.info("param received datoUsuarioID = " + datoUsuarioID);
		logger.info("param received estadoID = " + estadoID);
		logger.info("param received nombreClase = " + nombreClase);
		logger.info("param received valorId = " + valorId);

		if (UtilValidate.isEmpty(datoUsuarioID)) {
			message = "El datoUsuarioID " + datoUsuarioID + " Es nulo";
			logger.error(message);
			throw new Exception(message);
		}
		if (UtilValidate.isEmpty(estadoID)) {
			message = "El estadoID " + estadoID + " Es nulo";
			logger.error(message);
			throw new Exception(message);
		}
		if (UtilValidate.isEmpty(nombreClase)) {
			message = "El nombreClase " + nombreClase + " Es nulo";
			logger.error(message);
			throw new Exception(message);
		}
		if (UtilValidate.isEmpty(valorId)) {
			message = "El valorId " + valorId + " Es nulo";
			logger.error(message);
			throw new Exception(message);
		}
		logger.info("validateParams: Finish");
	}

}
