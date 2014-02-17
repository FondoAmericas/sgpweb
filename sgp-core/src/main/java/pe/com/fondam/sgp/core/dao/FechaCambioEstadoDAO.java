package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.EfectoProyecto;
import pe.com.fondam.sgp.core.domain.FechaCambioEstado;

/**
 * 
 * @author Zolanch Távara
 */
public interface FechaCambioEstadoDAO {
	void saveFechaCambioEstado(FechaCambioEstado fechaCambioEstado);

	FechaCambioEstado updateFechaCambioEstado(
			FechaCambioEstado fechaCambioEstado);

	void deleteFechaCambioEstado(EfectoProyecto fechaCambioEstado);

	FechaCambioEstado findFechaCambioEstadoById(Integer id);

	List<FechaCambioEstado> findFechaCambioEstados();
}
