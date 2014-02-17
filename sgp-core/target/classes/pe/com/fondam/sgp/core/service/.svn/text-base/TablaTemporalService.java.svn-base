package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DepProvDist;
import pe.com.fondam.sgp.core.domain.TmpActividadPerfil;
import pe.com.fondam.sgp.core.domain.TmpBeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.TmpDatoProyecto;
import pe.com.fondam.sgp.core.domain.TmpEvaluacion;
import pe.com.fondam.sgp.core.domain.TmpFuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.TmpImagenOArchivo;
import pe.com.fondam.sgp.core.domain.TmpInstitucion;
import pe.com.fondam.sgp.core.domain.TmpMetaPerfil;
import pe.com.fondam.sgp.core.domain.TmpPerfil;
import pe.com.fondam.sgp.core.domain.TmpResumenProyecto;
import pe.com.fondam.sgp.core.domain.TmpUbicacionProyecto;

public interface TablaTemporalService {
	 void saveTmpDatoProyecto(TmpDatoProyecto tmpDatoProyecto);
	 void saveDepProvDist(DepProvDist depProvDist);
	 void saveTmpUbicacionProyecto(TmpUbicacionProyecto tmpUbicacionProyecto);
	 public void saveTmpBeneficiariosPorResultado(TmpBeneficiariosPorResultado tmpBeneficiariosPorResultado);
	 void saveTmpInstitucion(TmpInstitucion tmpInstitucion);
	 public void saveTmpFuenteFinanciadora(TmpFuenteFinanciadora tmpFuenteFinanciadora);
	 void saveTmpPerfil(TmpPerfil tmpPerfil);
	 void saveTmpImagenOArchivo(TmpImagenOArchivo tmpImagenOArchivo);
	 public List<TmpImagenOArchivo> findTmpImagenOArchivoByPerfilByID(int idPerfil);
	 public TmpImagenOArchivo findTmpImagenOArchivoById(Integer id);
	 void saveTmpResumenProyecto(TmpResumenProyecto tmpResumenProyecto);
	 void saveTmpActividadPerfil(TmpActividadPerfil tmpActividadPerfil);
	 void saveTmpMetaPerfil(TmpMetaPerfil tmpMetaPerfil);
	 List<TmpFuenteFinanciadora> findTmpFuenteFinanciadoraByTmpDatoProyectoByEstadoInstitucion(Integer idPrograma);
	 TmpDatoProyecto findTmpDatoProyectoById(Integer idProyecto);
	 List<TmpDatoProyecto> findTmpDatoProyectoByProgramaID(Integer idPrograma);
	 TmpEvaluacion findTmpEvaluacionById(Integer id);
	 TmpPerfil findTmpPerfilByTmpDatoProyectoID(Integer idProyecto);
	 TmpFuenteFinanciadora	findTmpFuenteFinanciadoraByTmpIdDatoProyecto(Integer idProyecto);
	 TmpInstitucion findTmpInstitucionById(Integer id);
	 void saveTmpEvaluacion(TmpEvaluacion tmpEvaluacion);
	 TmpEvaluacion updateTmpEvaluacion(TmpEvaluacion tmpEvaluacion);
	 TmpEvaluacion findTmpEvaluacion(TmpEvaluacion evaluacion);
	List<TmpDatoProyecto> findTmpDatoProyectoByEstadoPerfil(Integer idPrograma);
	void saveInformacionTMPatablas(Integer tmpDatoProyectoId);
	void deleteTMP(Integer tmpDatoProyectoId);
	void updateTmpInstitucion(TmpInstitucion tmpInstituc);
	void updateTmpPerfil(TmpPerfil tmpPerfil);
	List<TmpActividadPerfil> findTmpActividadPerfilByPerfilID(Integer tmpPerfilID);
	TmpEvaluacion findTmpEvaluacionByTmpDatoProyectoIDbyTipoEvaluacion(Integer idProyecto, int tipoEvaluacion);
	//void updateTmpEvaluacion(TmpEvaluacion evaluacion);
	List<TmpResumenProyecto> findTmpResumenProyectoByTmpDatoProyectoID(
			Integer idProyecto);
	List<TmpEvaluacion> findListTmpEvaluacionByTmpDatoProyectoIDByTipoEvaluacion(
			Integer idProyecto, int tipoEvaluacion);




}
