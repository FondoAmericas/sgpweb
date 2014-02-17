package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;

public interface DatoPlanOperativoDAO {

	void saveDatoPlanOperativo(DatoPlanOperativo datoPlanOperativo);

	DatoPlanOperativo updateDatoPlanOperativo(
			DatoPlanOperativo datoPlanOperativo);

	void deleteActivo(DatoPlanOperativo datoPlanOperativo);

	DatoPlanOperativo findDatoPlanOperativoById(Integer id);
	
	List<DatoPlanOperativo> findListDatoPlanOperativo();
	
	DatoPlanOperativo findDatoPlanOperativoByDatoProyectoID(Integer datoProyectoID);
	
	public DatoPlanOperativo findDatoPlanOperativoByDatoProyectoID2(Integer datoProyectoID);

	List<DatoPlanOperativo> findDatoPlanOperativoByConsulta(String consulta,
			Object[] params);

	DatoPlanOperativo findDatoPlanOperativoByDatoProyectoIDByVersion(
			Integer datoProyectoId, String versionPo);


}
