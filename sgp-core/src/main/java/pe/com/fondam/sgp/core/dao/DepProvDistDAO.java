package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DepProvDist;

public interface DepProvDistDAO {

	void saveDepProvDist(DepProvDist depProvDist);

	DepProvDist updateDepProvDist(DepProvDist depProvDist);

	void deleteDepProvDist(DepProvDist depProvDist);

	DepProvDist findDepProvDistById(Integer id);

	List<DepProvDist> findDepProvDistritos(int modo,String idDepartamento,String idProvincia,String idDistrito);
	//List<DepProvDist> findDepProvDistritos();

	List<DepProvDist> findDepProvDist();

	List<DepProvDist> findDepProvDistXConsulta(String consulta, Object[] params);

	List<DepProvDist> findUbigeo(int flag, String idDepartamento,
			String idProvincia, String idDistrito);

	}
