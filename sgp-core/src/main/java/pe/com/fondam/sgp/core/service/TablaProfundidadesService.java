package pe.com.fondam.sgp.core.service;

import pe.com.fondam.sgp.core.domain.TablaProfundidades;

public interface TablaProfundidadesService {

	TablaProfundidades findTablaProfundidadesServiceById(
			Integer tablaProfundidadId);

	String findProfundidadText(TablaProfundidades tablaProfundidades);

}
