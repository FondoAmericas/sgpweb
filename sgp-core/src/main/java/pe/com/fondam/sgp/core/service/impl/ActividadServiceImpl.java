package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.ActividadDAO;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.service.ActividadService;
import pe.com.fondam.sgp.core.service.CostoActividadService;
import pe.com.fondam.sgp.core.service.MetaPorActividadService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class ActividadServiceImpl implements ActividadService {

	/************** inyecciones ***************/
	@Resource
	ActividadDAO actividadDAO;
	
	@Resource
	MetaPorActividadService metaPorActividadService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	CostoActividadService costoActividadService;
	
	/************** metodos ***************/
	@Override
	public List<Actividad> findActividadXResultadoId(Integer resultadoId) {
		 /*String consulta =" from Actividad where resultado.resultadoID = ? ";
		 
		 Object[] params = new  Object[1];
		 params[0]= resultadoId;
		 */
		return actividadDAO.findActividadByResultadoID(resultadoId);
	}

	@Override
	public Actividad findActividadById(Integer actividadID) {
		return actividadDAO.findActividadById(actividadID);
	}

	@Override
	public void deleteActividad(Actividad actividad) {
		actividad = actividadDAO.findActividadById(actividad.getActividadID());
		actividadDAO.deleteActividad(actividad);
	}

	@Override
	public Actividad updateActividad(Actividad actividad) {
		return actividadDAO.updateActividad(actividad);
	}

	@Override
	public List<Actividad> llenaActividadCompletaMeta(List<Actividad> listActividad) {

		for (Actividad actividad : listActividad) {
			actividad.setListMetaPorActividad(metaPorActividadService.llenaMetaPorActividadCompletaCronograma( metaPorActividadService.findMetaPorActividadXActividadId(actividad.getActividadID())));
			actividad.setDescripcionTipoActividad(tablaEspecificaService.findTablaEspecificaById(actividad.getFkIdtablaespTipoActividad()).getDescripcionCabecera());
		}
		return listActividad;
	}
	
	@Override
	public List<Actividad> llenaActividadCompletaCosto(List<Actividad> listActividad) {

		for (Actividad actividad : listActividad) {
			actividad.setListCostoActividad(costoActividadService.llenaCostoPorActividadCompletaCronograma (costoActividadService.findCostoActividadByActividadID(actividad.getActividadID())));
			actividad.setDescripcionTipoActividad(tablaEspecificaService.findTablaEspecificaById(actividad.getFkIdtablaespTipoActividad()).getDescripcionCabecera());
		}
		return listActividad;
	}
}
