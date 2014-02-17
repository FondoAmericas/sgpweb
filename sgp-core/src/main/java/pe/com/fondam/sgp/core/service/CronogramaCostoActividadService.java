package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;

public interface CronogramaCostoActividadService {

	CronogramaCostoActividad findCronogramaCostoActividadById(Integer cronogramaCostoActividadID);

	List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadIdByFuenteFinanciadoraIdByCantidadMayorCero(Integer costoActividadId,Integer fuenteFinanciadoraId);
	
	List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadID(Integer costoActividadID);

	List<CronogramaCostoActividad> findCronogramaCostoActividadByFuenteFinanciamientoIDMenosCostoActividadId(
			Integer fuenteFinanciadoraID, Integer costoActividadID);

	CronogramaCostoActividad llenaCronogramaCostoActividadCompleto(
			CronogramaCostoActividad findCronogramaCostoActividadById);

	CronogramaCostoActividad updateCronogramaCostoActividad(
			CronogramaCostoActividad cronogramaCostoActividad);

	void deleteCronogramaCostoActividad(Integer cronogramaCostoActividadID);

	List<CronogramaCostoActividad> llenaMontoLiquidadoHastaElMomentoDeCCA(
			List<CronogramaCostoActividad> listCronogramaCostoActividad);
}
