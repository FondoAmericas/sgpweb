package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.ActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.domain.DepProvDist;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.PuntajeEvaluacion;
import pe.com.fondam.sgp.core.domain.RestriccionDepProvDist;
import pe.com.fondam.sgp.core.domain.RestriccionSubAreaTematica;
import pe.com.fondam.sgp.core.domain.RestricionPrograma;
import pe.com.fondam.sgp.core.domain.SubAreaTematica;
import pe.com.fondam.sgp.core.domain.TipoActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.TipoPeriodo;

public interface CrearProgramaService {

	List<Programa> listProgramas();

	List<TipoPeriodo> listTipoPeriodos();

	List<TipoActividadObligatoriaPrograma> findTipoActividadObligatoriaProgramasByIdtablaespTipo(Integer id);

	Programa findProgramaById(Integer id);

	Integer savePrograma(Programa programa);

	void updatePrograma(Programa programa);

	void savePuntajeEvaluacion(PuntajeEvaluacion puntajeEvaluacion);

	List<RestricionPrograma> findListRestricionProgramaById(Integer id);

	void saveRestriccionPrograma(RestricionPrograma restricionPrograma);

	void deleteRestriccionPrograma(RestricionPrograma restricionPrograma);

	RestricionPrograma updateRestriccionPrograma(RestricionPrograma restricionPrograma);

	void saveActividadObligatoriaPrograma(ActividadObligatoriaPrograma actividadObligatoriaPrograma);

	void deletePrograma(Programa programa);

	List<ActividadObligatoriaPrograma> findListActividadObligatoriaProgramaByProgramaId(
			Integer id);

	void deleteActividadObligatoriaPrograma(ActividadObligatoriaPrograma actividadObligatoriaPrograma);

	ActividadObligatoriaPrograma updateActividadObligatoriaPrograma(ActividadObligatoriaPrograma actividadObligatoriaPrograma);

	List<PuntajeEvaluacion> findPuntajeEvaluacionByProgramaID(Integer id);

	PuntajeEvaluacion updatePuntajeEvaluacion(PuntajeEvaluacion puntajeEvaluacion);

	void deletePuntajeEvaluacion(PuntajeEvaluacion puntajeEvaluacion);
	
	PuntajeEvaluacion findPuntajeEvaluacionBytipoEvaluacionByProgramaID(Integer ProgramaID, Integer tipoEvaluacion);

	void saveImagenOArchivo(ImagenOArchivo imagenOArchivo);

	void deleteImagenOArchivo(ImagenOArchivo archivoImagen);
	
	public DatoUsuario findDatoUsuarioById(Integer datoUsuarioID);

	public List<SubAreaTematica> findSubAreaTematicaByDescripcion();
	
	DepProvDist findDepProvDistById(Integer id);

	void saveRestriccionDepProvDist(RestriccionDepProvDist restriccionDepProvDist);

	List<RestriccionDepProvDist> findListRestriccionDepProvDistByProgramaId(
			Integer idPrograma);

	List<DepProvDist>  findDepProvDist();

	void deleteRestriccionDepProvDist(int idRestriccionDepProvDist);

	SubAreaTematica findSubAreaTematicaById(int idRestriccion);

	void saveRestriccionSubAreaTematica(
			RestriccionSubAreaTematica restriccionSubAreaTematica);

	List<SubAreaTematica> findSubAreaTematica();

	List<RestriccionSubAreaTematica> findListRestriccionSubAreaTematicayProgramaId(
			Integer idPrograma);

	void deleteRestriccionSubAreaTematica(int idSubAreaTematica);
	
	public List<Programa> findProgramaByNombre(String nombrePrograma);
	
	public List<Programa> findProgramaByModFinan(int modFinan);
	
	public List<Programa> findProgramaByTipoCuenta(int tipoCuenta);

	ImagenOArchivo findProgramaByArchivoImagen(int programa);

	void updateImagenOArchivo(ImagenOArchivo imagenOArchivo);

	List<DepProvDist> findUbigeo(int flag, String departamentoID, String provinciaID,
			String distritoID);
	
}
