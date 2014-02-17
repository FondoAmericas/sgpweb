package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.BeneficiariosPorResultadoDAO;
import pe.com.fondam.sgp.core.dao.CuentaCorrienteDAO;
import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.dao.DesembolsoDAO;
import pe.com.fondam.sgp.core.dao.EvaluacionDAO;
import pe.com.fondam.sgp.core.dao.EvaluadorDAO;
import pe.com.fondam.sgp.core.dao.FuenteFinanciadoraDAO;
import pe.com.fondam.sgp.core.dao.IndicadorMarcoLogicoDAO;
import pe.com.fondam.sgp.core.dao.InstitucionDAO;
import pe.com.fondam.sgp.core.dao.MarcoLogicoDAO;
import pe.com.fondam.sgp.core.dao.PerfilDAO;
import pe.com.fondam.sgp.core.dao.ReporteAvanceBeneficiarioDAO;
import pe.com.fondam.sgp.core.dao.ResumenProyectoDAO;
import pe.com.fondam.sgp.core.dao.TmpEvaluacionDAO;
import pe.com.fondam.sgp.core.dao.UbicacionProyectoDAO;
import pe.com.fondam.sgp.core.dao.UsuarioDAO;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.CuentaCorriente;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.Evaluacion;
import pe.com.fondam.sgp.core.domain.Evaluador;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.IndicadorMarcoLogico;
import pe.com.fondam.sgp.core.domain.Institucion;
import pe.com.fondam.sgp.core.domain.MarcoLogico;
import pe.com.fondam.sgp.core.domain.Perfil;
import pe.com.fondam.sgp.core.domain.ReporteAvanceBeneficiario;
import pe.com.fondam.sgp.core.domain.ResumenProyecto;
import pe.com.fondam.sgp.core.domain.TmpDatoProyecto;
import pe.com.fondam.sgp.core.domain.TmpEvaluacion;
import pe.com.fondam.sgp.core.domain.TmpInstitucion;
import pe.com.fondam.sgp.core.domain.TmpPerfil;
import pe.com.fondam.sgp.core.domain.UbicacionProyecto;
import pe.com.fondam.sgp.core.domain.Usuario;
import pe.com.fondam.sgp.core.service.EvaluarService;
import pe.com.fondam.sgp.core.util.CommonUtilities;
@Service
public class EvaluarServiceImpl implements EvaluarService {
	@Resource
	private EvaluadorDAO  evaluadorDAO;
	
	@Resource
	private MarcoLogicoDAO  marcoLogicoDAO;
	
	@Resource
	private EvaluacionDAO  evaluacionDAO;
	
	@Resource
	private UsuarioDAO usuarioDAO;
	
	@Resource
	private DatoProyectoDAO datoProyectoDAO;
	
	@Resource
	private FuenteFinanciadoraDAO fuenteFinanciadoraDAO;
	
	@Resource
	private InstitucionDAO institucionDAO;
	
	@Resource
	private TmpEvaluacionDAO tmpEvaluacionDAO;
	
	@Resource
	private PerfilDAO perfilDAO;
	
	@Resource
	private UbicacionProyectoDAO ubicacionProyectoDAO;
	
	@Resource
	private BeneficiariosPorResultadoDAO beneficiariosPorResultadoDAO;
	
	@Resource
	private ResumenProyectoDAO resumenProyectoDAO;
	
	@Resource
	private CuentaCorrienteDAO cuentaCorrienteDAO;

	@Resource
	private IndicadorMarcoLogicoDAO indicadorMarcoLogicoDAO;
	
	@Resource
	private DesembolsoDAO desembolsoDAO;
	
	@Resource
	private ReporteAvanceBeneficiarioDAO reporteAvanceBeneficiarioDAO;
	
	
	@Override
	public List<Evaluador> findEvaluadorByTipoEvaluacion(Integer evaluadorID,Integer tipoEvaluacion) {
		
		return 	evaluadorDAO.findEvaluadorByTipoEvaluacion(evaluadorID, tipoEvaluacion);

	}
	
	public Usuario findUsuarioById(Integer id){
		return usuarioDAO.findUsuarioById(id);
		
	}

	@Override
	public List<DatoProyecto> findDatoProyecto() {
		
		return datoProyectoDAO.findDatoProyecto() ;
	}

	@Override
	public List<DatoProyecto> findDatoProyectoByProgramaID(Integer id) {
		
		return datoProyectoDAO.findDatoProyectoByProgramaID(id);
	}

	@Override
	public List<Evaluacion> findEvaluacionByEvaluadorId(Integer idProyecto,
			Integer idTipoEvaluacion) {

		return evaluacionDAO.findEvaluacionByEvaluadorId(idProyecto, idTipoEvaluacion);
	}

	

	@Override
	public TmpInstitucion verificarEstadoTMPinstitucional(Integer idInstitucion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TmpPerfil verificarEstadoTMPperfil(Integer idPerfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DatoProyecto findDatoProyectoById(Integer idProyecto) {
	
		return datoProyectoDAO.findDatoProyectoById(idProyecto);
	}

	@Override
	public Evaluador findEvaluadorByDatoUsuarioIDAndTipoEvaluacionAndProyectoID(Evaluador evaluador) {
		
		return evaluadorDAO.findEvaluadorByDatoUsuarioIDAndTipoEvaluacionAndProyectoID(evaluador);
	}

	@Override
	public void saveEvaluacion(Evaluacion evaluacion) {
		evaluacionDAO.saveEvaluacion(evaluacion);
		
	}

	@Override
	public Evaluacion findEvaluacion(Evaluacion eval) {
		
		return evaluacionDAO.findEvaluacion(eval);
	}

	@Override
	public Evaluacion findEvaluacionByDatoProyectoIDbyTipoEvaluacion(DatoProyecto dtoProy, int tipoEvaluacion, int datoUsuario) {
		return evaluacionDAO.findEvaluacionByDatoProyectoIDbyTipoEvaluacion(dtoProy, tipoEvaluacion,datoUsuario);
	}

	@Override
	public Evaluacion updateEvaluacion(Evaluacion evaluacion) {
		return evaluacionDAO.updateEvaluacion(evaluacion);
		
	}

	@Override
	public List<Evaluador> findEvaluadorByTipoEvaluacionByFiltroPrograma(Integer evaluadorID, Integer tipoEvaluacion, int idFiltro, int valor) {
		
		return evaluadorDAO.findEvaluadorByTipoEvaluacionByFiltroPrograma(evaluadorID, tipoEvaluacion, idFiltro, valor);
	}

	@Override
	public FuenteFinanciadora findFuenteFinanciadoraByIdDatoProyecto(Integer id) {
		
		return fuenteFinanciadoraDAO.findFuenteFinanciadoraByIdDatoProyecto(id);
	}

	@Override
	public Institucion findInstitucionByID(Integer institucionID) {
		
		return institucionDAO.findInstitucionById(institucionID);
	}

	@Override
	public void updateInstitucion(Institucion institucion) {
		institucionDAO.updateInstitucion(institucion);
		
	}

	@Override
	public int verificarSiFaltanEvaluacionParaPerfil(
			TmpDatoProyecto tmpDatoProyecto) {
	List<Evaluador> listaEvaluador=null;
	
	listaEvaluador=evaluadorDAO.obtenerEvaluadorByProgramaByTipoEvaluacion(tmpDatoProyecto.getPrograma().getProgramaID(),174);
	
	List<TmpEvaluacion>  listaTMPEvaluacion=tmpEvaluacionDAO.findTmpEvaluacionByProgramaIDbyTipoEvaluacionPerfil(tmpDatoProyecto.getPrograma().getProgramaID());

	if (listaEvaluador.size()!=0 && listaTMPEvaluacion.size()!=0) {
		
	
		if (listaEvaluador.size()== listaTMPEvaluacion.size()) {
			return 1;
		}else {
			return 0;
		}
	}else {
		return 0;
	}
	
		
	}
	@Override
	public int verificarSiFaltanEvaluacionParaDatoProyecto(
			DatoProyecto datoProyecto) {
	List<Evaluador> listaEvaluador=null;
	
	listaEvaluador=evaluadorDAO.obtenerEvaluadorByProgramaByTipoEvaluacion(datoProyecto.getPrograma().getProgramaID(),175);
	
	List<Evaluacion>  listaEvaluacion=evaluacionDAO.findEvaluacionByProgramaIDbyTipoEvaluacionDatoProyecto(datoProyecto.getPrograma().getProgramaID());

	if (listaEvaluador.size()!=0 && listaEvaluacion.size()!=0) {
		
	
		if (listaEvaluador.size()== listaEvaluacion.size()) {
			return 1;
		}else {
			return 0;
		}
	}else {
		return 0;
	}
	
		
	}

	@Override
	public double obtenerNotaEvaluacionPerfil(TmpDatoProyecto tmpDatoProyecto) {
		List<TmpEvaluacion>  listaTMPEvaluacion=tmpEvaluacionDAO.findTmpEvaluacionByProgramaIDbyTipoEvaluacionPerfil(tmpDatoProyecto.getPrograma().getProgramaID());
		double nota=0;
		if (listaTMPEvaluacion.size()!=0) {
			if (listaTMPEvaluacion.size()>1) {
				double suma = 0;
	
				if (((listaTMPEvaluacion.get(0).getCalificacion()- listaTMPEvaluacion.get(1).getCalificacion())<=10) ||
					(listaTMPEvaluacion.get(1).getCalificacion()- listaTMPEvaluacion.get(0).getCalificacion()<=10)){
					suma=listaTMPEvaluacion.get(0).getCalificacion()+ listaTMPEvaluacion.get(1).getCalificacion();
					nota=suma/2;
					System.out.println("notaaaaaa>>"+nota);
					return nota; 
					
				}else {
				return 0;
				
				}	
			} else {
				 nota= listaTMPEvaluacion.get(0).getCalificacion();

			}
		}
	return nota;
	}
	@Override
	public double obtenerNotaDeTercerEvaluador(TmpDatoProyecto tmpDatoProyecto) {
		List<TmpEvaluacion>  listaTMPEvaluacion=tmpEvaluacionDAO.findTmpEvaluacionByProgramaIDbyTipoEvaluacionPerfil(tmpDatoProyecto.getPrograma().getProgramaID());
		double nota=0;
		if (listaTMPEvaluacion.size()==3) {
			
				double suma = 0;

					suma=listaTMPEvaluacion.get(0).getCalificacion()+ listaTMPEvaluacion.get(1).getCalificacion();
					nota=suma/2;
					double notaTercerEvaludor=listaTMPEvaluacion.get(0).getCalificacion();
					nota=(nota+notaTercerEvaludor)/2;
					return nota; 
					
				
			} else {
				 nota=0;

			}
		
	return nota;
	}

	@Override
	public Evaluador findEvaluadorByID(Integer idEvaluador) {
	return evaluadorDAO.findEvaluadorByID(idEvaluador);
	}
	
	
	
	
	public Perfil findPerfilByDatoProyectoID(int proyectoID){
		return perfilDAO.findPerfilByDatoProyectoID(proyectoID);
		
	}
	
	public List<UbicacionProyecto> findUbicacionProyectoByIdProyecto(Integer datoProyectoId){
		return ubicacionProyectoDAO.findUbicacionProyectoByIdProyecto(datoProyectoId);
		
	}
	
	public List<BeneficiariosPorResultado> findBeneficiariosPorResultadoByIdPerfil(Integer idPerfil){
		return beneficiariosPorResultadoDAO.findBeneficiariosPorResultadoByIdPerfil(idPerfil);
		
	}
	
	public List<Institucion> findInstitucion(){
		return institucionDAO.findInstitucion();
	}
	
	public List<FuenteFinanciadora> findFuenteFinanciadorasByIdDatoProy(Integer id){
		return fuenteFinanciadoraDAO.findFuenteFinanciadorasByIdDatoProy(id);
	}
	
	public List<ResumenProyecto> findResumenProyectoByIdDatoProy(Integer id){
		return resumenProyectoDAO.findResumenProyectoByIdDatoProy(id);
	}
	
	public List<DatoProyecto> findDatoProyectoByProgramaIDbyNomProy(Integer idPrograma,String nomProy) {
		return datoProyectoDAO.findDatoProyectoByProgramaIDbyNomProy(idPrograma, nomProy);
	}
	
	public List<DatoProyecto> findDatoProyectoByCodProy(Integer idPrograma,String CodProy) {
		return datoProyectoDAO.findDatoProyectoByCodProy(idPrograma, CodProy);
	}
	
	public DatoProyecto updateDatoProyecto(DatoProyecto datoProyecto) {	
		return datoProyectoDAO.updateDatoProyecto(datoProyecto);
	}

	public void saveUbicacionProyecto(UbicacionProyecto ubicacionProyecto) {
		ubicacionProyectoDAO.saveUbicacionProyecto(ubicacionProyecto);
		
	}
	
	public void saveBeneficiariosPorResultado(BeneficiariosPorResultado beneficiariosPorResultado) {
		beneficiariosPorResultadoDAO.saveBeneficiariosPorResultado(beneficiariosPorResultado);
	}
	
	public List<CuentaCorriente> findCuentaCorrienteByIdDatoProyecto(int idDatoProyecto) {
		return cuentaCorrienteDAO.findCuentaCorrienteByIdDatoProyecto(idDatoProyecto);
	}
	
	public void deleteCuentaCorriente(CuentaCorriente cuentaCorriente) {
		cuentaCorrienteDAO.deleteCuentaCorriente(cuentaCorriente);

	}

	public void deleteUbicacionProyectoo(UbicacionProyecto ubicacionProyecto){
		ubicacionProyectoDAO.deleteUbicacionProyectoo(ubicacionProyecto);
	}
	
	public void deleteBeneficiariosPorResultado(BeneficiariosPorResultado beneficiariosPorResultado) {
		beneficiariosPorResultadoDAO.deleteBeneficiariosPorResultado(beneficiariosPorResultado);
	}
	
	public List<BeneficiariosPorResultado> findBeneficiariosPorResultadoByIdUbicacionProyecto(Integer idUbicacionProyecto){
		return beneficiariosPorResultadoDAO.findBeneficiariosPorResultadoByIdUbicacionProyecto(idUbicacionProyecto);	
	}
	
	public void saveInstitucion(Institucion institucion) {
		institucionDAO.saveInstitucion1(institucion);
	}
	
	public void saveFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora) {
		fuenteFinanciadoraDAO.saveFuenteFinanciadora(fuenteFinanciadora);
			
	}
	
	public void deleteFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora) {
		fuenteFinanciadoraDAO.deleteFuenteFinanciadora(fuenteFinanciadora);
		
	}
	
	public void deleteInstitucion(Institucion institucion) {
		institucionDAO.deleteInstitucion(institucion);
		
	}
	
	/*public void saveResumenProyecto(ResumenProyecto resumenProyecto) {
		resumenProyectoDAO.saveResumenProyecto(resumenProyecto);
		
	}*/
	/*
	public void deleteResumenProyecto(ResumenProyecto resumenProyecto) {
		resumenProyectoDAO.deleteResumenProyecto(resumenProyecto);
		
	}*/
	
	public void saveMarcoLogico(MarcoLogico marcoLogico) {
		marcoLogicoDAO.saveMarcoLogico(marcoLogico);
		
	}
	
	public void saveIndicadorMarcoLogico(IndicadorMarcoLogico indicadorMarcoLogico) {
		
		indicadorMarcoLogicoDAO.saveIndicadorMarcoLogico(indicadorMarcoLogico);
	}

	@Override
	public double obtenerNotaEvaluacionDatoProyecto(DatoProyecto datoProyecto) {
		List<Evaluacion>  listaEvaluacion=evaluacionDAO.findEvaluacionByProgramaIDbyTipoEvaluacionDatoProyecto(datoProyecto.getPrograma().getProgramaID());
		double nota=0;
		if (listaEvaluacion.size()!=0) {
			if (listaEvaluacion.size()>1) {
				double suma = 0;
					for (int i = 0; i < listaEvaluacion.size(); i++) {
						suma+=listaEvaluacion.get(i).getCalificacion();
							
					}
					nota=suma/listaEvaluacion.size();
					System.out.println("notaaaaaa>>"+nota);
					return nota; 
					
				}	
			}		
	return nota;

	}

	public Institucion findInstitucionByRUC(String ruc) {
		return institucionDAO.findInstitucionByRUC(ruc);
	}
	

	public List<CuentaCorriente> verificarSiCuentaCorrienteEstaReferencidaPorTablas(List<CuentaCorriente> lstCuentaCorriente){
		for(int i=0; i<lstCuentaCorriente.size(); i++){
			List<Desembolso> lstDesembolso = desembolsoDAO.findDesembolsoByCuentaCorrienteID(lstCuentaCorriente.get(i).getCuentaCorrienteID());
			if(lstDesembolso.size()>0){
				lstCuentaCorriente.get(i).setFlagReferencia(1);
			}else{
				lstCuentaCorriente.get(i).setFlagReferencia(0);
			}
		}
		return lstCuentaCorriente;
	}

	
	public List<BeneficiariosPorResultado> verificarSiBeneficiarioEstaReferenciadoPorTablas(List<BeneficiariosPorResultado> listBeneficiariosPorResultado){
		
		for(int i=0; i<listBeneficiariosPorResultado.size(); i++){
			
			List<ReporteAvanceBeneficiario>lstReporteAvanceBeneficiario = reporteAvanceBeneficiarioDAO.findReporteAvanceBeneficiarioByBeneficiariosPorResultadoId(listBeneficiariosPorResultado.get(i).getBeneficiariosPorResultadoID());
			
			if(lstReporteAvanceBeneficiario.size()>0){
				listBeneficiariosPorResultado.get(i).setFlagReferencia(true);
			}else{
				listBeneficiariosPorResultado.get(i).setFlagReferencia(false);
			}
		}
		
		return listBeneficiariosPorResultado;
	}
	
	public boolean verificarSiUbicacionProyectoEstaReferenciado(Integer ubicacionProyectoID){
		
		//UbicacionProyecto ubicacionProyecto = ubicacionProyectoDAO.findUbicacionProyectoById(ubicacionProyectoID);
		List<BeneficiariosPorResultado> lstBenPorResul = beneficiariosPorResultadoDAO.findBeneficiariosPorResultadoByIdUbicacionProyecto(ubicacionProyectoID);
		boolean val=false;
		if(CommonUtilities.isNotNullOrBlank(lstBenPorResul)){
			for(int i=0; i<lstBenPorResul.size(); i++){
				
	            List<ReporteAvanceBeneficiario>lstReporteAvanceBeneficiario = reporteAvanceBeneficiarioDAO.findReporteAvanceBeneficiarioByBeneficiariosPorResultadoId(lstBenPorResul.get(i).getBeneficiariosPorResultadoID());
				
				if(lstReporteAvanceBeneficiario.size()>0){
					val = true;
					break;
				}
			}			
		}				
		return val;
	}

	@Override
	public List<Evaluacion> findEvaluacionByDatoProyectoIDAndTipoEvaluacionId(
			Integer datoProyectoID, int tipoEvaluacionId) {
		
		String consulta = "from Evaluacion where datoProyecto.datoProyectoID=? and evaluador.fkIdtablaespTipoEvaluacion=?";
		Object[] params = new Object[2];
		params[0] = datoProyectoID;
		params[1] = tipoEvaluacionId;
		
		return evaluacionDAO.findConsulta(consulta,params);
	}
	
}
