package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.ActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.Perfil;
import pe.com.fondam.sgp.core.domain.RestricionPrograma;
import pe.com.fondam.sgp.core.domain.SubAreaTematica;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.TipoActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.TmpDatoProyecto;


public interface IngresarPerfilService{
	List<TablaEspecifica> listAreaTematica();

	List<SubAreaTematica> listSubAreaTematica();

	List<TipoActividadObligatoriaPrograma> findTipoActividadObligatoriaProgramasByIdtablaespTipo(Integer id);
	
	void saveRestriccionPrograma(RestricionPrograma restricionPrograma);
	void saveActividadObligatoriaPrograma(ActividadObligatoriaPrograma actividadObligatoriaPrograma);
	public List<TmpDatoProyecto> findTmpDatoProyectoByProgramaIDMaxNroOrdenDatoProyecto(Integer idPrograma);
	
	Perfil findPerfilByDatoProyectoID(int proyectoID);
	public List<TmpDatoProyecto> findTmpDatoProyectoByNomProyectoAndProgramaID(String nombreProyecto, Integer idPrograma);

}
