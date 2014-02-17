package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.IngresoPropio;
import pe.com.fondam.sgp.core.domain.InspeccionTecnica;

/**
*
* @author Zolanch Távara
*/
public interface InspeccionTecnicaDAO {
	void saveInspeccionTecnica(InspeccionTecnica inspeccionTecnica);

	InspeccionTecnica updateInspeccionTecnica(
			IngresoPropio inspeccionTecnica);

	void deleteInspeccionTecnica(InspeccionTecnica inspeccionTecnica);

	InspeccionTecnica findInspeccionTecnicaById(Integer id);

	List<InspeccionTecnica> findInspeccionTecnicas();
}
