package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.TablaEspecifica;

public interface TablaEspecificaDAO {

	TablaEspecifica findTablaEspecificaById(Integer tablaEspecificaID);

	List<TablaEspecifica> findTablaEspecificasByTablaGeneralId(
			Integer tablaGeneralID);

	List<TablaEspecifica> findTablaEspecificaByConsulta(String consulta,
			Object[] params);

	List<TablaEspecifica> findTablaEspecificaByConsultaSinParametros(
			String consulta);
	

}
