package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DatoProyecto;

public interface DatoProyectoService {

	DatoProyecto findDatoProyectoById(Integer datoProyectoID);

	void updateDatoProyecto(DatoProyecto datoProyecto);

	List<DatoProyecto> findDatoProyectoByProgramaByNombre(int programaId,
			String filtroNombre);

	List<DatoProyecto>  findDatoProyectoByProgramaByCodigo(int programaId,
			String filtroCodigo);

	List<DatoProyecto> llenaDatoProyectoConDesembolsos(
			List<DatoProyecto> listDatoProyecto);

	List<DatoProyecto> findDatoProyectoByPrograma(int programaId);

}
