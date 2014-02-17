package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.domain.Evaluador;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.Usuario;

public interface InstalarComiteTecnicoService {
	List<DatoUsuario> findDatosUsuario();
	List<Usuario> findUsuarios();
	List<Evaluador> findEvaluador();
	Integer saveEvaluador(Evaluador evaluador);
	List<Evaluador> findEvaluadorByProgramaId(Integer id);
	List<Usuario> findUsuarioByPerfilUsuarioId(Integer id);

	List<Usuario> findUsuarioByNombre(String cadena);
	List<Programa> findProgramaByFiltro(Programa programa,Integer idFiltro);
	List<Evaluador> findComiteTecnicoByFiltro(Evaluador evaluador,Integer idFiltro);

	List<Evaluador> findComiteTecnicoByFiltro(Usuario usuario,
			Integer evaluacion, int idfiltro, String dato);
	void deleteEvaluador(Integer idEvaluador);

	void DeleteUsuarioDelEvaluadorAsignado(Integer datoUsuarioID,
			Integer programaID);

}
