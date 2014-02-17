package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DepProvDist;

public interface DepProvDistService {

	DepProvDist findListDepProvDistById(Integer depProvDistID);

	DepProvDist findListDepartamentos(String departamentoId);

	DepProvDist findListProvincia(String departamentoId, String provinciaId);

	DepProvDist findListDistrito(String departamentoId, String provinciaId,
			String distritoId);

	String findDescripcionDepProvDist(String descripcion, DepProvDist depProvDistID);

	List<DepProvDist> findListProvinciaByDepartamentoId(String departamentoId);

	List<DepProvDist> findListProvinciaByDepartamentoIdBuProvinciaId(
			String departamentoId, String provinciaId);

}
