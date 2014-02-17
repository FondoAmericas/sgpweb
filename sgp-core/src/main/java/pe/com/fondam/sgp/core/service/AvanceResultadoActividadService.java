package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.bean.AvanceResultadoActividadBean;
import pe.com.fondam.sgp.core.domain.AvanceResultadoActividad;

public interface AvanceResultadoActividadService {

	void saveAvanceResultadoActividad(
			AvanceResultadoActividad avanceResultadoActividad);

	List<AvanceResultadoActividad> findAvanceResultadoActividadXReporteAvanceId(
			Integer reporteAvanceId);

	AvanceResultadoActividad findAvanceResultadoActividadById(
			Integer avanceResultadoActividadId);

	List<AvanceResultadoActividadBean> llenaListAvanceResultadoActividadBean(
			List<AvanceResultadoActividad> listAvanceResultadoActividad);

	List<AvanceResultadoActividad> findAvanceResultadoActividadByMetaActividadId(
			Integer metaPorActividadID);

	AvanceResultadoActividad updateAvanceResultadoActividad(
			AvanceResultadoActividad avanceResultadoActividad);

	List<AvanceResultadoActividad> findAvanceResultadoActividadByMetaActividadIdXReporteAprobado(
			Integer metaPorActividadID,
			String prefijoEstadoReporteAvanceAprobado);

	void deleteAvanceResultadoActividad(Integer avanceResultadoActividadId);

}
