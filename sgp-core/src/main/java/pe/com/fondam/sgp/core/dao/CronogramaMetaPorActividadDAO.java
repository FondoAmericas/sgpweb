package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.CronogramaMetaPorActividad;

/**
*
* @author Zolanch Távara
*/
public interface CronogramaMetaPorActividadDAO {

	void saveCronorgramaMetaPorActividad(CronogramaMetaPorActividad cronorgramaMetaPorActividad);
	
	CronogramaMetaPorActividad updateCronorgramaMetaPorActividad(CronogramaMetaPorActividad cronorgramaMetaPorActividad);
	
	void deleteCronorgramaMetaPorActividad(CronogramaMetaPorActividad cronorgramaMetaPorActividad);
	
	CronogramaMetaPorActividad findCronorgramaMetaPorActividadById(Integer id);
	
	List<CronogramaMetaPorActividad> findCronorgramaMetaPorActividades();

	List<CronogramaMetaPorActividad> findCronogramaMetaPorActividadXConsulta(
			String consulta, Object[] params);
	
	List<CronogramaMetaPorActividad> findCronorgramaMetaPorActividadesByMetaPorActivdadId(Integer metaPorActivadId);

}
