package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.bean.MetaPorActividadBean;
import pe.com.fondam.sgp.core.domain.MetaPorActividad;

public interface MetaPorActividadService {

	List<MetaPorActividad> findMetaPorActividadXActividadId(Integer actividadId);
	MetaPorActividad findMetaPorActividadById(Integer metasXActividadId);
	List<MetaPorActividad> findMetasPorActividadByActividadId(Integer actividadID);
	void deleteMetaPorActividad(MetaPorActividad metaPorActividad);
	MetaPorActividad updateMetaPorActividad(
			MetaPorActividad metaPorActividad);
	List<MetaPorActividadBean> llenaListMetaPorActividadBean(
			List<MetaPorActividad> listMetaPorActividad);
	MetaPorActividadBean llenaMetaPorActividadBean(
			MetaPorActividad listMetaPorActividad);
	List<MetaPorActividad> llenaMetaPorActividadCompletaCronograma(
			List<MetaPorActividad> listMetaPorActividad);

}
