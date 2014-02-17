package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.CostoActividad;

public interface CostoActividadDAO {

	void saveCostoActividad(CostoActividad costoActividad);

	CostoActividad updateCostoActividad(CostoActividad costoActividad);

	CostoActividad findCostoActividadById(Integer id);

	List<CostoActividad> findCostoActividadByActividadID(Integer actividadID);

	List<CostoActividad> findCostoActividadByActividadIDAndPartidaGenericaID(Integer actividadID, Integer partidaGenericaID);

	List<CostoActividad> findCostoActividad(String queryString, Object[] params);
	
	void deleteCostoActividad(CostoActividad costoActividad);

}
