package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.bean.ResumenProyectoBean;
import pe.com.fondam.sgp.core.domain.ResumenProyecto;



public interface ResumenProyectoService {

	ResumenProyecto updateResumenProyecto(ResumenProyecto resumenProyecto);

	ResumenProyecto findResumenProyectoById(Integer resumenProyectoId);

	void deleteResumenProyecto(Integer resumenProyectoId);
	
	List<ResumenProyectoBean> listadoResumenProyecto(
			Integer datoProyectoID);

}
