package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.TmpEvaluacion;

public interface TmpEvaluacionDAO {
	void saveTmpEvaluacion(TmpEvaluacion tmpEvaluacion);
	
	TmpEvaluacion updateTmpEvaluacion(TmpEvaluacion tmpEvaluacion);
	
	void deleteTmpEvaluacion(TmpEvaluacion tmpEvaluacion);
	
	TmpEvaluacion findTmpEvaluacionById(Integer id);

	TmpEvaluacion findTmpEvaluacion(TmpEvaluacion evaluacion);

	TmpEvaluacion findTmpEvaluacionByTipoEvaluacionByTmpDatoProyectoID(int tipoEvaluacion, Integer tmpDatoProyectoID);

	List<TmpEvaluacion> findTmpEvaluacionByTmpDatoProyectoID(Integer tmpDatoProyectoId);


	List<TmpEvaluacion> findTmpEvaluacionByProgramaIDbyTipoEvaluacionPerfil(
			Integer programaId);

	List<TmpEvaluacion> findByConsulta(String queryString, Object[] params);
}
