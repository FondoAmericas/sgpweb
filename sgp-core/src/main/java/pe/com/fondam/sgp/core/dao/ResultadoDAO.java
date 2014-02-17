package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.Resultado;

public interface ResultadoDAO {

	void saveResultado(Resultado resultado);

	Resultado updateResultado(Resultado resultado);

	void deleteResultado(Resultado resultado);

	Resultado findResultadoById(Integer id);

	List<Resultado> findListResultado();

	List<Resultado> findListResultadoByDatoPlanOperativoID(
			Integer datoPlanOperativoID);

	//List<Resultado> findListResultadoByDatoProyectoID(Integer datoPlanOperativoID);

	List<Resultado> findListResultadoXConsulta(String consulta, Object[] params);

}
