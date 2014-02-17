package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.IngresoPropio;
import pe.com.fondam.sgp.core.domain.InspeccionAdmin;

/**
*
* @author Zolanch Távara
*/
public interface InspeccionAdminDAO {
	void saveInspeccionAdmin(InspeccionAdmin inspeccionAdmin);

	IngresoPropio updateInspeccionAdmin(
			InspeccionAdmin inspeccionAdmin);

	void deleteInspeccionAdmin(InspeccionAdmin inspeccionAdmin);

	InspeccionAdmin findInspeccionAdminById(Integer id);

	List<InspeccionAdmin> findInspeccionAdmins();
}
