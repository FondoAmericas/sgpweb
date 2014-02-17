package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.CuentaCorriente;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Evaluacion;
import pe.com.fondam.sgp.core.domain.Evaluador;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.IndicadorMarcoLogico;
import pe.com.fondam.sgp.core.domain.Institucion;
import pe.com.fondam.sgp.core.domain.MarcoLogico;
import pe.com.fondam.sgp.core.domain.Perfil;
import pe.com.fondam.sgp.core.domain.ResumenProyecto;
import pe.com.fondam.sgp.core.domain.TmpDatoProyecto;
import pe.com.fondam.sgp.core.domain.TmpInstitucion;
import pe.com.fondam.sgp.core.domain.TmpPerfil;
import pe.com.fondam.sgp.core.domain.UbicacionProyecto;
import pe.com.fondam.sgp.core.domain.Usuario;

public interface EvaluarService {
	List<Evaluador> findEvaluadorByTipoEvaluacion(Integer evaluadorID,Integer tipoEvaluacion);
	
	Usuario findUsuarioById(Integer id);
	
	List<DatoProyecto> findDatoProyecto();
	
	List<DatoProyecto> findDatoProyectoByProgramaID(Integer id);
	 
	List<Evaluacion> findEvaluacionByEvaluadorId(Integer idProyecto,Integer idTipoEvaluacion);
	
	void saveEvaluacion(Evaluacion evaluacion);
	
	TmpInstitucion verificarEstadoTMPinstitucional(Integer idInstitucion);
	
	TmpPerfil verificarEstadoTMPperfil(Integer idPerfil);

	DatoProyecto findDatoProyectoById(Integer idProyecto);

	Evaluador findEvaluadorByDatoUsuarioIDAndTipoEvaluacionAndProyectoID(Evaluador evaluador);

	Evaluacion findEvaluacion(Evaluacion eval);

	Evaluacion updateEvaluacion(Evaluacion evaluacion);
	
	List<Evaluador> findEvaluadorByTipoEvaluacionByFiltroPrograma(Integer evaluadorID, Integer tipoEvaluacion, int idFiltro, int valor);

	
	FuenteFinanciadora findFuenteFinanciadoraByIdDatoProyecto(Integer id);

	Institucion findInstitucionByID(Integer institucionID);

	void updateInstitucion(Institucion institucion);

	Evaluador findEvaluadorByID(Integer idEvaluador);
	
    Perfil findPerfilByDatoProyectoID(int proyectoID);
	
	public List<UbicacionProyecto> findUbicacionProyectoByIdProyecto(Integer datoProyectoId);
	
	public List<BeneficiariosPorResultado> findBeneficiariosPorResultadoByIdPerfil(Integer idPerfil);
	
	public List<FuenteFinanciadora> findFuenteFinanciadorasByIdDatoProy(Integer id);
	
	public List<Institucion> findInstitucion();
	
	public List<ResumenProyecto> findResumenProyectoByIdDatoProy(Integer id);
	
	public List<DatoProyecto> findDatoProyectoByProgramaIDbyNomProy(Integer idPrograma,String nomProy);
	
	public List<DatoProyecto> findDatoProyectoByCodProy(Integer idPrograma,String CodProy);
	
	public DatoProyecto updateDatoProyecto(DatoProyecto datoProyecto);
	
	//public void saveCuentaCorriente(CuentaCorriente cuentaCorriente);
	
	public void saveUbicacionProyecto(UbicacionProyecto ubicacionProyecto);
	
	public void saveBeneficiariosPorResultado(BeneficiariosPorResultado beneficiariosPorResultado);
	
	public List<CuentaCorriente> findCuentaCorrienteByIdDatoProyecto(int idDatoProyecto);
	
	public void deleteCuentaCorriente(CuentaCorriente cuentaCorriente);
	
	public void deleteUbicacionProyectoo(UbicacionProyecto ubicacionProyecto);
	
	public void deleteBeneficiariosPorResultado(BeneficiariosPorResultado beneficiariosPorResultado);
	
	public List<BeneficiariosPorResultado> findBeneficiariosPorResultadoByIdUbicacionProyecto(Integer idUbicacionProyecto);
	
	public void saveInstitucion(Institucion institucion);
	
	public void saveFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora);
	
	public void deleteFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora);
	
	public void deleteInstitucion(Institucion institucion);
	
	//public void deleteResumenProyecto(ResumenProyecto resumenProyecto);
	
	public void saveMarcoLogico(MarcoLogico marcoLogico);
	
	public void saveIndicadorMarcoLogico(IndicadorMarcoLogico indicadorMarcoLogico);
	
	Evaluacion findEvaluacionByDatoProyectoIDbyTipoEvaluacion(
			DatoProyecto dtoProy, int tipoEvaluacion, int datoUsuario);
	
	double obtenerNotaDeTercerEvaluador(TmpDatoProyecto tmpDatoProyecto);

	int verificarSiFaltanEvaluacionParaPerfil(TmpDatoProyecto tmpDatoProyecto);

	int verificarSiFaltanEvaluacionParaDatoProyecto(DatoProyecto datoProyecto);

	double obtenerNotaEvaluacionPerfil(TmpDatoProyecto tmpDatoProyecto);

	double obtenerNotaEvaluacionDatoProyecto(DatoProyecto datoProyecto);
	
	public Institucion findInstitucionByRUC(String ruc);

	public List<CuentaCorriente> verificarSiCuentaCorrienteEstaReferencidaPorTablas(List<CuentaCorriente> lstCuentaCorriente);

	public List<BeneficiariosPorResultado> verificarSiBeneficiarioEstaReferenciadoPorTablas(List<BeneficiariosPorResultado> listBeneficiariosPorResultado);

	boolean verificarSiUbicacionProyectoEstaReferenciado(Integer ubicacionProyectoID);

	List<Evaluacion> findEvaluacionByDatoProyectoIDAndTipoEvaluacionId(
			Integer datoProyectoID, int institucion);

	
	
}
