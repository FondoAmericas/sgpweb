package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.AvanceResultadoActividad;


public interface AvanceResultadoActividadDAO {

	void saveAvanceResultadoActividad(
			AvanceResultadoActividad avanceResultadoActividad);

	List<AvanceResultadoActividad> findAvanceResultadoActividadXConsulta(
			String consulta, Object[] params);

	AvanceResultadoActividad findAvanceResultadoActividadById(
			Integer avanceResultadoActividadId);

	AvanceResultadoActividad updateAvanceResultadoActividad(
			AvanceResultadoActividad avanceResultadoActividad);

	void deleteAvanceResultadoActividad(
			AvanceResultadoActividad avanceResultadoActividad);

}
