package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.Riesgo;

/**
*
* @author Zolanch Távara
*/
public interface RiesgoDAO {

	void saveRiesgo(Riesgo riesgo);
	

		List<Riesgo> findRiesgoByActividadID(int id);

}
