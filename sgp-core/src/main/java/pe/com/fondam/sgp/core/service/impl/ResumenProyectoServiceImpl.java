package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.ResumenProyectoBean;
import pe.com.fondam.sgp.core.dao.ResumenProyectoDAO;
import pe.com.fondam.sgp.core.domain.ResumenProyecto;
import pe.com.fondam.sgp.core.service.RegistroPerfilService;
import pe.com.fondam.sgp.core.service.ResumenProyectoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class ResumenProyectoServiceImpl implements ResumenProyectoService {

	//************* inyecciones *********///
	@Resource
	ResumenProyectoDAO resumenProyectoDAO;
	
	@Resource
	RegistroPerfilService registroPerfilService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	//************* inyecciones *********///
	@Override
	public ResumenProyecto updateResumenProyecto(ResumenProyecto resumenProyecto) {
		return resumenProyectoDAO.updateResumenProyecto(resumenProyecto);	
	}


	@Override
	public ResumenProyecto findResumenProyectoById(Integer resumenProyectoId) {
		return resumenProyectoDAO.findResumenProyectoById(resumenProyectoId);
	}


	@Override
	public void deleteResumenProyecto(Integer resumenProyectoId) {
		resumenProyectoDAO.deleteResumenProyecto(resumenProyectoDAO.findResumenProyectoById(resumenProyectoId));
		
	}

	@Override
	public List<ResumenProyectoBean> listadoResumenProyecto(
			Integer datoProyectoID) {
		List<ResumenProyecto> listResumenProyecto = registroPerfilService
				.findResumenProyectoByIdDatoProyByTablaGeneralId(
						datoProyectoID, 31);// 31 es tipo de resumen para
											// proyecto

		List<ResumenProyectoBean> listResumenProyectoBean = new ArrayList<ResumenProyectoBean>();
		for (ResumenProyecto resumenProyecto : listResumenProyecto) {
			ResumenProyectoBean resumenProyectoBean = new ResumenProyectoBean();
			resumenProyectoBean.setResumenProyectoID(resumenProyecto
					.getResumenProyectoID());
			resumenProyectoBean.setFkIdtablaespTipoResumenProy(resumenProyecto
					.getFkIdtablaespTipoResumenProy());
			resumenProyectoBean
					.setDescripcionTipoResumenProy(tablaEspecificaService
							.findTablaEspecificaById(
									resumenProyecto
											.getFkIdtablaespTipoResumenProy())
							.getDescripcionCabecera());
			resumenProyectoBean.setFkIdTablaGeneral(resumenProyecto
					.getFkIdTablaGeneral());
			resumenProyectoBean.setDefinicion(resumenProyecto.getDefinicion());
			resumenProyectoBean.setDatoProyectoID(resumenProyecto
					.getDatoProyecto().getDatoProyectoID());
			listResumenProyectoBean.add(resumenProyectoBean);
		}
		return listResumenProyectoBean;
	}
}
