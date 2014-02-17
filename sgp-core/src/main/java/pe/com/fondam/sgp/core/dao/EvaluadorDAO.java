package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.Evaluador;

public interface EvaluadorDAO {
	void saveEvaluador(Evaluador evaluador);

	List<Evaluador> findEvaluadorByDatoUsuarioIDAndProgramaID(Integer datoUsuarioID, Integer programaID);

	Evaluador updateEvaluador(Evaluador evaluador);

	void deleteEvaluador(Integer id);


	List<Evaluador> findEvaluador();

	List<Evaluador> findEvaluadorByProgramaId(Integer id);

	List<Evaluador> findComiteTecnicoByFiltro(Evaluador evaluador,
			Integer idFiltro);

	List<Evaluador> findEvaluadorByTipoEvaluacion(Integer evaluadorID,Integer tipoEvaluacion);


	List<Evaluador> findComiteTecnicoByFiltro(Integer usuarioID,
			Integer evaluacion, int idFiltro, String dato);


	Evaluador findEvaluadorByDatoUsuarioIDAndTipoEvaluacionAndProyectoID(
			Evaluador evaluador);

	List<Evaluador> findEvaluadorByTipoEvaluacionByFiltroPrograma(Integer evaluadorID, Integer tipoEvaluacion, int idFiltro, int valor);


	Evaluador findEvaluadorByID(Integer idEvaluador);

	List<Evaluador> obtenerEvaluadorByProgramaByTipoEvaluacion(
			Integer programaID, int tipoEvaluacion);

}
