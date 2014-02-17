package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.bean.CronogramaMetaPorActividadBean;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorActividad;
import pe.com.fondam.sgp.core.domain.MetaPorActividad;

public interface CronogramaMetaPorActividadService {

	List<CronogramaMetaPorActividad> findCronogramaMetaPorActividadXMetaPorActividadId(Integer metasXActividadId);
	CronogramaMetaPorActividad findCronogramaMetaPorActividadById(Integer cronogramaMetaActividadId);	
	//void deleteCronorgramaMetaPorActividad(CronogramaMetaPorActividad cronorgramaMetaPorActividad);
	void deleteCronorgramaMetaPorActividad(Integer cronorgramaMetaPorActividadId);
	CronogramaMetaPorActividad updateCronogramaMetaPorActividad(
			CronogramaMetaPorActividad cronogramaMetaPorActividad);
	List<CronogramaMetaPorActividadBean> llenaCronogramaMetaPorActividadBean(
			List<CronogramaMetaPorActividad> listCronogramaMetaPorActividad,
			MetaPorActividad metaPorActividad);

}
