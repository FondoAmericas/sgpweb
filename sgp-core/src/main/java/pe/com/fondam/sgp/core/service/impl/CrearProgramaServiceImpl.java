package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.ActividadObligatoriaProgramaDAO;
import pe.com.fondam.sgp.core.dao.DatoUsuarioDAO;
import pe.com.fondam.sgp.core.dao.DepProvDistDAO;
import pe.com.fondam.sgp.core.dao.ImagenOArchivoDAO;
import pe.com.fondam.sgp.core.dao.ProgramaDAO;
import pe.com.fondam.sgp.core.dao.PuntajeEvaluacionDAO;
import pe.com.fondam.sgp.core.dao.RestriccionDepProvDistDAO;
import pe.com.fondam.sgp.core.dao.RestriccionSubAreaTematicaDAO;
import pe.com.fondam.sgp.core.dao.RestricionProgramaDAO;
import pe.com.fondam.sgp.core.dao.SubAreaTematicaDAO;
import pe.com.fondam.sgp.core.dao.TipoActividadObligatoriaProgramaDAO;
import pe.com.fondam.sgp.core.dao.TipoPeriodoDAO;
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
import pe.com.fondam.sgp.core.service.CrearProgramaService;

@Service
public class CrearProgramaServiceImpl implements CrearProgramaService {

	@Resource
	private ProgramaDAO programaDAO;

	@Resource
	private TipoPeriodoDAO tipoPeriodoDAO;

	@Resource
	private TipoActividadObligatoriaProgramaDAO tipoActividadObligatoriaProgramaDAO;

	@Resource
	private PuntajeEvaluacionDAO puntajeEvaluacionDAO;

	@Resource
	private RestricionProgramaDAO restricionProgramaDAO;

	@Resource
	private ActividadObligatoriaProgramaDAO actividadObligatoriaProgramaDAO;
	
	@Resource
	private ImagenOArchivoDAO imagenOArchivoDAO;

	@Resource
	private DatoUsuarioDAO datoUsuarioDAO;

	@Resource
	private SubAreaTematicaDAO subAreaTematicaDAO;

	@Resource
	private DepProvDistDAO depProvDistDAO;

	@Resource
	private RestriccionDepProvDistDAO restriccionDepProvDistDAO;

	@Resource
	private RestriccionSubAreaTematicaDAO restriccionSubAreaTematicaDAO;

	public ProgramaDAO getProgramaDAO() {
		return programaDAO;
	}

	public void setProgramaDAO(ProgramaDAO programaDAO) {
		this.programaDAO = programaDAO;
	}

	public TipoPeriodoDAO getTipoPeriodoDAO() {
		return tipoPeriodoDAO;
	}

	public void setTipoPeriodoDAO(TipoPeriodoDAO tipoPeriodoDAO) {
		this.tipoPeriodoDAO = tipoPeriodoDAO;
	}

	public TipoActividadObligatoriaProgramaDAO getTipoActividadObligatoriaProgramaDAO() {
		return tipoActividadObligatoriaProgramaDAO;
	}

	public void setTipoActividadObligatoriaProgramaDAO(
			TipoActividadObligatoriaProgramaDAO tipoActividadObligatoriaProgramaDAO) {
		this.tipoActividadObligatoriaProgramaDAO = tipoActividadObligatoriaProgramaDAO;
	}

	public PuntajeEvaluacionDAO getPuntajeEvaluacionDAO() {
		return puntajeEvaluacionDAO;
	}

	public void setPuntajeEvaluacionDAO(
			PuntajeEvaluacionDAO puntajeEvaluacionDAO) {
		this.puntajeEvaluacionDAO = puntajeEvaluacionDAO;
	}

	public RestricionProgramaDAO getRestricionProgramaDAO() {
		return restricionProgramaDAO;
	}

	public void setRestricionProgramaDAO(
			RestricionProgramaDAO restricionProgramaDAO) {
		this.restricionProgramaDAO = restricionProgramaDAO;
	}

	public ActividadObligatoriaProgramaDAO getActividadObligatoriaProgramaDAO() {
		return actividadObligatoriaProgramaDAO;
	}

	public void setActividadObligatoriaProgramaDAO(
			ActividadObligatoriaProgramaDAO actividadObligatoriaProgramaDAO) {
		this.actividadObligatoriaProgramaDAO = actividadObligatoriaProgramaDAO;
	}

	public ImagenOArchivoDAO getImagenOArchivoDAO() {
		return imagenOArchivoDAO;
	}

	public void setImagenOArchivoDAO(ImagenOArchivoDAO imagenOArchivoDAO) {
		this.imagenOArchivoDAO = imagenOArchivoDAO;
	}

	public DatoUsuarioDAO getDatoUsuarioDAO() {
		return datoUsuarioDAO;
	}

	public void setDatoUsuarioDAO(DatoUsuarioDAO datoUsuarioDAO) {
		this.datoUsuarioDAO = datoUsuarioDAO;
	}

	public SubAreaTematicaDAO getSubAreaTematicaDAO() {
		return subAreaTematicaDAO;
	}

	public void setSubAreaTematicaDAO(SubAreaTematicaDAO subAreaTematicaDAO) {
		this.subAreaTematicaDAO = subAreaTematicaDAO;
	}

	public DepProvDistDAO getDepProvDistDAO() {
		return depProvDistDAO;
	}

	public void setDepProvDistDAO(DepProvDistDAO depProvDistDAO) {
		this.depProvDistDAO = depProvDistDAO;
	}

	public RestriccionDepProvDistDAO getRestriccionDepProvDistDAO() {
		return restriccionDepProvDistDAO;
	}

	public void setRestriccionDepProvDistDAO(
			RestriccionDepProvDistDAO restriccionDepProvDistDAO) {
		this.restriccionDepProvDistDAO = restriccionDepProvDistDAO;
	}

	@Override
	public List<Programa> listProgramas() {

		return programaDAO.findProgramas();
	}

	@Override
	public List<TipoPeriodo> listTipoPeriodos() {

		return tipoPeriodoDAO.findTipoPeriodos();
	}

	@Override
	public List<TipoActividadObligatoriaPrograma> findTipoActividadObligatoriaProgramasByIdtablaespTipo(
			Integer id) {

		return tipoActividadObligatoriaProgramaDAO
				.findTipoActividadObligatoriaProgramasByIdtablaespTipo(id);
	}

	@Override
	public Integer savePrograma(Programa programa) {
		return programaDAO.savePrograma(programa);

	}

	@Override
	public void updatePrograma(Programa programa) {
		programaDAO.updatePrograma(programa);

	}

	@Override
	public void savePuntajeEvaluacion(PuntajeEvaluacion puntajeEvaluacion) {
		puntajeEvaluacionDAO.savePuntajeEvaluacion(puntajeEvaluacion);
	}

	@Override
	public void saveRestriccionPrograma(RestricionPrograma restricionPrograma) {
		restricionProgramaDAO.saveRestricionPrograma(restricionPrograma);

	}

	@Override
	public void deleteRestriccionPrograma(RestricionPrograma restricionPrograma) {
		restricionProgramaDAO.deleteRestricionPrograma(restricionPrograma);

	}

	@Override
	public RestricionPrograma updateRestriccionPrograma(
			RestricionPrograma restricionPrograma) {
		return restricionProgramaDAO
				.updateRestricionPrograma(restricionPrograma);

	}

	@Override
	public List<RestricionPrograma> findListRestricionProgramaById(Integer id) {
		return restricionProgramaDAO.findListRestricionProgramaById(id);
	}

	@Override
	public void saveActividadObligatoriaPrograma(
			ActividadObligatoriaPrograma actividadObligatoriaPrograma) {
		actividadObligatoriaProgramaDAO
				.saveActividadObligatoriaPrograma(actividadObligatoriaPrograma);
	}

	@Override
	public void deleteActividadObligatoriaPrograma(
			ActividadObligatoriaPrograma actividadObligatoriaPrograma) {
		actividadObligatoriaProgramaDAO
				.deleteActividadObligatoriaPrograma(actividadObligatoriaPrograma);

	}

	@Override
	public ActividadObligatoriaPrograma updateActividadObligatoriaPrograma(
			ActividadObligatoriaPrograma actividadObligatoriaPrograma) {

		return actividadObligatoriaProgramaDAO
				.updateActividadObligatoriaPrograma(actividadObligatoriaPrograma);
	}

	@Override
	public Programa findProgramaById(Integer id) {

		return programaDAO.findProgramaById(id);
	}

	@Override
	public void deletePrograma(Programa programa) {
		programaDAO.deletePrograma(programa);
	}

	@Override
	public List<ActividadObligatoriaPrograma> findListActividadObligatoriaProgramaByProgramaId(
			Integer id) {

		return actividadObligatoriaProgramaDAO
				.findListActividadObligatoriaProgramaByProgramaId(id);

	}

	@Override
	public List<PuntajeEvaluacion> findPuntajeEvaluacionByProgramaID(Integer id) {

		return puntajeEvaluacionDAO.findPuntajeEvaluacionByProgramaID(id);
	}

	@Override
	public PuntajeEvaluacion updatePuntajeEvaluacion(
			PuntajeEvaluacion puntajeEvaluacion) {

		return puntajeEvaluacionDAO.updatePuntajeEvaluacion(puntajeEvaluacion);
	}

	@Override
	public void deletePuntajeEvaluacion(PuntajeEvaluacion puntajeEvaluacion) {
		puntajeEvaluacionDAO.deletePuntajeEvaluacion(puntajeEvaluacion);

	}

	@Override
	public PuntajeEvaluacion findPuntajeEvaluacionBytipoEvaluacionByProgramaID(
			Integer ProgramaID, Integer tipoEvaluacion) {

		return puntajeEvaluacionDAO
				.findPuntajeEvaluacionBytipoEvaluacionByProgramaID(ProgramaID,
						tipoEvaluacion);
	}

	@Override
	public void saveImagenOArchivo(ImagenOArchivo imagenOArchivo) {
		imagenOArchivoDAO.saveImagenOArchivo(imagenOArchivo);

	}

	@Override
	public ImagenOArchivo findProgramaByArchivoImagen(int programa) {
		return imagenOArchivoDAO.findImagenOarchivoByPrograma(programa);

	}

	@Override
	public void deleteImagenOArchivo(ImagenOArchivo archivoImagen) {
		imagenOArchivoDAO.deleteImagenOArchivo(archivoImagen);

	}

	public DatoUsuario findDatoUsuarioById(Integer datoUsuarioID) {
		return datoUsuarioDAO.findDatoUsuarioById(datoUsuarioID);
	}

	@Override
	public List<SubAreaTematica> findSubAreaTematicaByDescripcion() {

		return subAreaTematicaDAO.findSubAreaTematicaByDescripcion();
	}

	@Override
	public DepProvDist findDepProvDistById(Integer id) {

		return depProvDistDAO.findDepProvDistById(id);
	}

	@Override
	public void saveRestriccionDepProvDist(
			RestriccionDepProvDist restriccionDepProvDist) {
		restriccionDepProvDistDAO
				.saveRestriccionDepProvDist(restriccionDepProvDist);

	}

	@Override
	public List<RestriccionDepProvDist> findListRestriccionDepProvDistByProgramaId(
			Integer idPrograma) {
		// TODO Auto-generated method stub
		return restriccionDepProvDistDAO
				.findListRestriccionDepProvDistByProgramaId(idPrograma);
	}

	@Override
	public List<DepProvDist> findDepProvDist() {

		return depProvDistDAO.findDepProvDist();
	}

	@Override
	public void deleteRestriccionDepProvDist(int idRestriccionDepProvDist) {
		restriccionDepProvDistDAO
				.deleteRestriccionDepProvDist(idRestriccionDepProvDist);

	}

	@Override
	public SubAreaTematica findSubAreaTematicaById(int idRestriccion) {

		return subAreaTematicaDAO.findSubAreaTematicaById(idRestriccion);
	}

	@Override
	public void saveRestriccionSubAreaTematica(
			RestriccionSubAreaTematica restriccionSubAreaTematica) {
		restriccionSubAreaTematicaDAO
				.saveRestriccionSubAreaTematica(restriccionSubAreaTematica);

	}

	@Override
	public List<SubAreaTematica> findSubAreaTematica() {

		return subAreaTematicaDAO.findSubAreaTematica();
	}

	@Override
	public List<RestriccionSubAreaTematica> findListRestriccionSubAreaTematicayProgramaId(
			Integer idPrograma) {
		return restriccionSubAreaTematicaDAO
				.findListRestriccionSubAreaTematicayProgramaId(idPrograma);
	}

	@Override
	public void deleteRestriccionSubAreaTematica(int idSubAreaTematica) {
		restriccionSubAreaTematicaDAO
				.deleteRestriccionSubAreaTematica(idSubAreaTematica);

	}

	public List<Programa> findProgramaByNombre(String nombrePrograma) {
		return programaDAO.findProgramaByNombre(nombrePrograma);
	}

	public List<Programa> findProgramaByModFinan(int modFinan) {
		return programaDAO.findProgramaByModFinan(modFinan);
	}

	public List<Programa> findProgramaByTipoCuenta(int tipoCuenta) {
		return programaDAO.findProgramaByTipoCuenta(tipoCuenta);
	}

	@Override
	public void updateImagenOArchivo(ImagenOArchivo imagenOArchivo) {
		imagenOArchivoDAO.updateImagenOArchivo(imagenOArchivo);

	}

	@Override
	public List<DepProvDist> findUbigeo(int flag, String departamentoID,
			String provinciaID, String distritoID) {
		return depProvDistDAO.findUbigeo(flag, departamentoID, provinciaID,
				distritoID);
	}

}
