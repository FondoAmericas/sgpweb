package pe.com.fondam.sgp.core.dao;

import pe.com.fondam.sgp.core.domain.EstadoCabecera;

public interface EstadoCabeceraDAO {
void saveEstadoCabecera(EstadoCabecera estadoCabecera);
	
	EstadoCabecera findEstadoCabeceraById(Integer id);
	

	int findPrefijoEstadoCabeceraByID(String prefijo);

	EstadoCabecera findEstadoCabeceraByPrefijo(String prefijo);


}
