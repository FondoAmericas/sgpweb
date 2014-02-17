package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.CronogramaMetaPorActividadBean;
import pe.com.fondam.sgp.core.dao.CronogramaMetaPorActividadDAO;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorActividad;
import pe.com.fondam.sgp.core.domain.MetaPorActividad;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorActividadService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class CronogramaMetaPorActividadServiceImpl implements CronogramaMetaPorActividadService {

	/************************* inyecciones **************************/
	@Resource
	CronogramaMetaPorActividadDAO cronogramaMetaPorActividadDAO;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	/************************* metodos **************************/
	@Override
	public List<CronogramaMetaPorActividad> findCronogramaMetaPorActividadXMetaPorActividadId(Integer metasXActividadId) {
		
		String consulta =" from CronogramaMetaPorActividad where metaPorActividad.metaPorActividadID = ? ";
		Object[] params = new Object[1];
		params[0]= metasXActividadId;
		return cronogramaMetaPorActividadDAO.findCronogramaMetaPorActividadXConsulta(consulta,params);
	}

	@Override
	public CronogramaMetaPorActividad findCronogramaMetaPorActividadById(Integer cronogramaMetaActividadId) {
		return cronogramaMetaPorActividadDAO.findCronorgramaMetaPorActividadById(cronogramaMetaActividadId);
	}

	/*
	@Override
	public void deleteCronorgramaMetaPorActividad(CronogramaMetaPorActividad cronorgramaMetaPorActividad) {
		cronogramaMetaPorActividadDAO.deleteCronorgramaMetaPorActividad(cronorgramaMetaPorActividad);
	}*/
	@Override
	public void deleteCronorgramaMetaPorActividad(Integer cronorgramaMetaPorActividadId) {
		cronogramaMetaPorActividadDAO.deleteCronorgramaMetaPorActividad(cronogramaMetaPorActividadDAO.findCronorgramaMetaPorActividadById(cronorgramaMetaPorActividadId));
	}

	@Override
	public CronogramaMetaPorActividad updateCronogramaMetaPorActividad(
			CronogramaMetaPorActividad cronogramaMetaPorActividad) {
		
		return cronogramaMetaPorActividadDAO.updateCronorgramaMetaPorActividad(cronogramaMetaPorActividad);
	}

	
	//************** listas  ***************************//
	@Override
	public List<CronogramaMetaPorActividadBean> llenaCronogramaMetaPorActividadBean(
			List<CronogramaMetaPorActividad> listCronogramaMetaPorActividad,
			MetaPorActividad metaPorActividad) {

		List<CronogramaMetaPorActividadBean> listCronogramaMetaPorActividadBean = new ArrayList<CronogramaMetaPorActividadBean>();

		for (CronogramaMetaPorActividad cronogramaMetaPorActividad : listCronogramaMetaPorActividad) {
			CronogramaMetaPorActividadBean cronogramaMetaPorActividadBean = new CronogramaMetaPorActividadBean();
			if (cronogramaMetaPorActividad
					.getCantidadMetaActividadProgPorPeriodo() != 0) {
				cronogramaMetaPorActividadBean
						.setCantidadMetaActividadProgPorPeriodo(cronogramaMetaPorActividad
								.getCantidadMetaActividadProgPorPeriodo());
				cronogramaMetaPorActividadBean
						.setCronogramaMetaPorActividadID(cronogramaMetaPorActividad
								.getCronogramaMetaPorActividadID());
				cronogramaMetaPorActividadBean
						.setDescripcionUnidadMedida(tablaEspecificaService
								.findTablaEspecificaById(
										metaPorActividad
												.getFkIdtablaespUnidadMedida())
								.getDescripcionCabecera());
				cronogramaMetaPorActividadBean
						.setMetaPorActividad(metaPorActividad);
				cronogramaMetaPorActividadBean
						.setPeriodo(cronogramaMetaPorActividad.getPeriodo());

				listCronogramaMetaPorActividadBean
						.add(cronogramaMetaPorActividadBean);
			}
		}
		return listCronogramaMetaPorActividadBean;
	}
}
