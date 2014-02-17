package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;

public interface FuenteFinanciadoraDAO {
	void saveFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora);

	FuenteFinanciadora updateFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora);

	void deleteFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora);

	FuenteFinanciadora findFuenteFinanciadoraById(Integer id);

	List<FuenteFinanciadora> findFuenteFinanciadoras();
	
	FuenteFinanciadora findFuenteFinanciadoraByIdDatoProyecto(Integer id);
	
	List<FuenteFinanciadora> findFuenteFinanciadorasByIdDatoProy(Integer id);
	
	List<FuenteFinanciadora> findFuenteFinanciadoraByDatoProyectoID(Integer datoProyectoID);

	FuenteFinanciadora findFuenteFinanciadoraByDatoProyectoIDByInstitucionID(Integer datoProyectoID, Integer institucionID);

	List<FuenteFinanciadora> findFuenteFinanciadoraByConsulta(String consulta,
			Object[] params);
	
}
