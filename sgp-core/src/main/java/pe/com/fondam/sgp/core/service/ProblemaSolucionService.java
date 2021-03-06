package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.ProblemaSolucion;

public interface ProblemaSolucionService {

	List<ProblemaSolucion> findProblemaSolucionXReporteAvanceId(
			Integer reporteAvanceId);

	void saveProblemaSolucion(ProblemaSolucion problemaSolucion);

	ProblemaSolucion findProblemaSolucionById(Integer problemaSolucionId);

	List<ProblemaSolucion> findProblemaSolucionByDatoProyectoId(
			Integer datoProyectoId);

	ProblemaSolucion updateProblemaSolucion(ProblemaSolucion problemaSolucion);

	void deleteProblemaSolucion(Integer problemaSolucionId);

}
