package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.TipoActividadObligatoriaPrograma;

/**
 * 
 * @author Zolanch Távara
 */
public interface TipoActividadObligatoriaProgramaDAO {

	void saveTipoActividadObligatoriaPrograma(
			TipoActividadObligatoriaPrograma tipoActividadObligatoriaP);

	TipoActividadObligatoriaPrograma updateTipoActividadObligatoriaPrograma(
			TipoActividadObligatoriaPrograma tipoActividadObligatoriaP);

	void deleteTipoActividadObligatoriaPrograma(
			TipoActividadObligatoriaPrograma tipoActividadObligatoriaP);

	TipoActividadObligatoriaPrograma findTipoActividadObligatoriaProgramaById(
			Integer id);

	List<TipoActividadObligatoriaPrograma> findTipoActividadObligatoriaProgramasByIdtablaespTipo(
			Integer idtipo);

}
