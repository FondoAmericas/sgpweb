package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.Actividad;

public interface ActividadService {

	List<Actividad> findActividadXResultadoId(Integer resultadoId);

	Actividad findActividadById(Integer actividadID);
	
	void deleteActividad(Actividad actividad);

	Actividad updateActividad(Actividad actividad_NEW);

	List<Actividad> llenaActividadCompletaMeta(
			List<Actividad> listActividad);
	
	List<Actividad> llenaActividadCompletaCosto(
			List<Actividad> listActividad);

}
