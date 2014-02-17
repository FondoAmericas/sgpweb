package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.EfectoProyecto;


public interface EfectoProyectoDAO {

	void saveEfectoProyecto(EfectoProyecto efectoProyecto);
	
	EfectoProyecto updateEfectoProyecto(EfectoProyecto efectoProyecto);
	
	void deleteEfectoProyecto(EfectoProyecto efectoProyecto);
	
	EfectoProyecto findEfectoProyectoById(Integer id);
	
	List<EfectoProyecto> findEfectoProyectosByConsulta(String consulta, Object[] params);

}
