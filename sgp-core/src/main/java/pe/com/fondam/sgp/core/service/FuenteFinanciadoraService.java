package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;

public interface FuenteFinanciadoraService {

	FuenteFinanciadora findFuenteFinanciadoraById(int fuenteFinanciadoraId);

	List<FuenteFinanciadora> findFuenteFinanciadoraByDatoProyectoId(Integer idProyecto);

	List<FuenteFinanciadora> llenaFuenteFinanciadoraCompleto(
			List<FuenteFinanciadora> listFuenteFinanciadora);

	FuenteFinanciadora findFuenteFinanciadoraByDatoProyectoIdByEjecutor(
			Integer datoProyectoID);

}
