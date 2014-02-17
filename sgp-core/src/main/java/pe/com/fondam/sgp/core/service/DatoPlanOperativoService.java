package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;

public interface DatoPlanOperativoService {

	List<DatoPlanOperativo> findDatoPlanOperativoByDatoProyectoID(
			Integer datoProyectoID);

	DatoPlanOperativo updateDatoPlanOperativo(DatoPlanOperativo datoPlanOperativo);

	DatoPlanOperativo findDatoPlanOperativoByID(Integer datoPlanOperativoID);

}
