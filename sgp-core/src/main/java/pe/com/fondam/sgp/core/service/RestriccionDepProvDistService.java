package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.RestriccionDepProvDist;

public interface RestriccionDepProvDistService {

	List<RestriccionDepProvDist> findRestriccionDepProvDistByProgramaId(
			int programaId);

}
