package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.CostoActividad;

public interface CostoActividadService {
	
	List<CostoActividad> findCostoActividadByActividadID(Integer actividadID);

	CostoActividad findCostoActividadById(Integer costoActividadID);
	
	void deleteCostoActividad(CostoActividad costoActividad);

	CostoActividad updateCostoActividad(CostoActividad costoActividad);

	List<CostoActividad> llenaCostoPorActividadCompletaCronograma(
			List<CostoActividad> listCostoActividad);

}
