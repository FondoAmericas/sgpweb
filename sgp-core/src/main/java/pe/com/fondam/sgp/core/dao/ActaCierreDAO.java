package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.ActaCierre;
import pe.com.fondam.sgp.core.domain.Activo;

public interface ActaCierreDAO {
	void saveActaCierre(ActaCierre actaCierre);

	Activo updateActaCierre(ActaCierre actaCierre);

	void deleteActaCierre(ActaCierre actaCierre);

	Activo findActaCierreById(Integer id);

	List<ActaCierre> findActaCierre();
}
