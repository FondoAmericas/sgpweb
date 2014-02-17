package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.ResumenProyecto;

/**
*
* @author Zolanch Távara
*/
public interface ResumenProyectoDAO {

	void saveResumenProyecto(ResumenProyecto resumenProyecto);
	
	ResumenProyecto updateResumenProyecto(ResumenProyecto resumenProyecto);
	
	void deleteResumenProyecto(ResumenProyecto resumenProyecto);
	
	ResumenProyecto findResumenProyectoById(Integer id);
	
	List<ResumenProyecto> findResumenProyectoByConsulta(String consulta, Object[] params);
	
	List<ResumenProyecto> findResumenProyecto();
		
	public List<ResumenProyecto> findResumenProyectoByIdDatoProy(Integer id);
}
