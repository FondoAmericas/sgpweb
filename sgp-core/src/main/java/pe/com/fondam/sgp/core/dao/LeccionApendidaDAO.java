package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.LeccionApendida;


public interface LeccionApendidaDAO {

	void saveLeccionApendida(LeccionApendida leccionApendida);

	LeccionApendida updateLeccionApendida(
			LeccionApendida leccionApendida);

	void deleteLeccionApendida(LeccionApendida leccionApendida);

	LeccionApendida findLeccionApendidaById(Integer id);

	List<LeccionApendida> findLeccionApendidaByConsulta(String consulta,Object[] params);


}
