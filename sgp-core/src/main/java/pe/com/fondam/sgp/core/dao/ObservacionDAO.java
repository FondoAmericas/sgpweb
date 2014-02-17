package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.Observacion;


public interface ObservacionDAO {

	void saveObservacion(Observacion observacion);

	Observacion updateObservacion(Observacion observacion);

	void delete(Observacion observacion);

	Observacion findObservacionById(Integer id);

	List<Observacion> findObservacionByConsulta(String consulta, Object[] params);
	
	
	
}
