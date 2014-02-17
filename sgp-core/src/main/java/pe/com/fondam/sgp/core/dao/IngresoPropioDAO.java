package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.IngresoPropio;

/**
*
* @author Zolanch Távara
*/
public interface IngresoPropioDAO {
	void saveIngresoPropio(IngresoPropio ingresoPropio);

	IngresoPropio updateIngresoPropio(
			IngresoPropio ingresoPropio);

	void deleteIngresoPropio(IngresoPropio ingresoPropio);

	IngresoPropio findIngresoPropioById(Integer id);

	List<IngresoPropio> findIngresoPropio(String queryString, Object[] params);

	}
