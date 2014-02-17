package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.RecursoUtilizado;
import pe.com.fondam.sgp.core.domain.RecursoUtilizadoBean;

public interface RecursoUtilizadoService {

	RecursoUtilizado updateRecursoUtilizado(RecursoUtilizado recursoUtilizado);

	List<RecursoUtilizadoBean> findRecursoUtilizadoBeanByBienId(Integer bienID);
	
	List<RecursoUtilizado> findRecursoUtilizadoByBienId(Integer bienID);

	void deleteRecursoUtilizado(Integer recursoUtilizadoId);

}
