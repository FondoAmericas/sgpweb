package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.TablaEspecifica;

public interface TablaEspecificaService {
	
	public List<TablaEspecifica> listTablaEspecificaByTablaGeneralId(Integer id);
	public TablaEspecifica findTablaEspecificaById(Integer tablaEspecificaID);
	List<TablaEspecifica> findTablaEspecificabyPrefijoTablaGeneral(
			String prefijo);
	public int findIdByDescripcionCabecera(String descripcionCabecera);

}
