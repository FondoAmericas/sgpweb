package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.dao.ActividadDAO;
import pe.com.fondam.sgp.core.dao.CostoActividadDAO;
import pe.com.fondam.sgp.core.dao.CronogramaCostoActividadDAO;
import pe.com.fondam.sgp.core.dao.CuentaCorrienteDAO;
import pe.com.fondam.sgp.core.dao.DatoPlanOperativoDAO;
import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.dao.DesembolsoDAO;
import pe.com.fondam.sgp.core.dao.DetalleDesembolsoDAO;
import pe.com.fondam.sgp.core.dao.FuenteFinanciadoraDAO;
import pe.com.fondam.sgp.core.dao.ProgramaDAO;
import pe.com.fondam.sgp.core.dao.ResultadoDAO;
import pe.com.fondam.sgp.core.dao.TablaEspecificaDAO;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.CuentaCorriente;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.DetalleDesembolso;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.service.AprobarDesembolsoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.SolicitarDesembolsoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.UtilService;
import pe.com.fondam.sgp.core.util.CommonUtilities;

@Service
public class AprobarDesembolsoServiceImpl implements AprobarDesembolsoService{

	
	@Resource
	private DatoPlanOperativoDAO  datoPlanOperativoDAO;

	@Resource
	private ResultadoDAO  resultadoDAO;
	
	@Resource
	private ActividadDAO actividadDAO;
	
	@Resource
	private CostoActividadDAO costoActividadDAO;
	
	@Resource
	private TablaEspecificaDAO tablaEspecificaDAO;
	
	@Resource
	private FuenteFinanciadoraDAO fuenteFinanciadoraDAO;
	
	@Resource
	private CronogramaCostoActividadDAO cronogramaCostoActividadDAO;
	
	@Resource
	private DesembolsoDAO desembolsoDAO;
	
	@Resource
	private CuentaCorrienteDAO cuentaCorrienteDAO;
	
	@Resource
	private DatoProyectoDAO datoProyectoDAO;

	@Resource
	private UtilService utilService;
	
	@Resource 
	private DetalleDesembolsoDAO detalleDesembolsoDAO;
	
	@Resource
	private DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	@Resource
	private TablaEspecificaService tablaEspecificaService;
	
	@Resource
	private SolicitarDesembolsoService solicitarDesembolsoService;
	
	@Resource
	private ProgramaDAO programaDAO; 
	
	protected final Log logger = LogFactory.getLog(AprobarDesembolsoServiceImpl.class);
	
	
	public String obtenerDesembolsosArrayJson(String opcionBusqueda, String filtroBusqueda, int programaID){
		
		TablaEspecifica tablaEspecifica = null;
		
		FuenteFinanciadora fuenteFinanciadora = null;
		
		CuentaCorriente cuentaCorriente = null;
		
		JSONArray jsonData = new JSONArray();
		
		JSONArray jsonArrayDesembolsosProyecto = new JSONArray();
		
		JSONObject jsonObjectDesembolsosProyecto;
		
		List <DatoProyecto> listProyecto=null;
		
		List <Desembolso> listDesembolso;
		
		if(opcionBusqueda.equals("1")){//Buscar por nombre de proyecto
			listProyecto = datoProyectoDAO.findDatoProyectoByNomProyByProgramaID(filtroBusqueda, programaID);
		}else if(opcionBusqueda.equals("2")){
			listProyecto = datoProyectoDAO.findDatoProyectoByCodProyByProgramaID(filtroBusqueda, programaID);			
		}

				for(int i=0; i<listProyecto.size(); i++){
					
					listDesembolso = desembolsoDAO.findDesembolsoByDatoProyectoID(listProyecto.get(i).getDatoProyectoID());
					
					for(int j=0; j<listDesembolso.size(); j++){
						
						jsonObjectDesembolsosProyecto = new JSONObject();
				
						jsonObjectDesembolsosProyecto.accumulate("nombreProyecto", listProyecto.get(i).getNombreProyecto() != null ?
							CommonUtilities.toString(listProyecto.get(i).getNombreProyecto()) : FondamConstans.EMPTY_STRING );						
						
						//obtener ejecutor
						fuenteFinanciadora = fuenteFinanciadoraDAO.findFuenteFinanciadoraByIdDatoProyecto(listProyecto.get(i).getDatoProyectoID());
						
						jsonObjectDesembolsosProyecto.accumulate("ejecutor", fuenteFinanciadora != null ?
								fuenteFinanciadora.getInstitucion().getNombreInstitucion() : FondamConstans.EMPTY_STRING );
						
						Resultado resultado = new Resultado();
						//armar objeto json DETALLE DESEMBOLSO
						if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getDesembolsoID() != null)){
							jsonObjectDesembolsosProyecto.accumulate("desembolsoID", CommonUtilities.toString(listDesembolso.get(j).getDesembolsoID()));
							List <DetalleDesembolso> listDetalleDesemb = detalleDesembolsoDAO.findDetalleDesembolsoByDesembolsoID(listDesembolso.get(j).getDesembolsoID());
							JSONObject jsonObjectDetalleDesembolso;
							JSONArray jsonArrayDetalleDesembolso = new JSONArray();
							for(int x=0; x<listDetalleDesemb.size(); x++){
								jsonObjectDetalleDesembolso= new JSONObject();
								
								jsonObjectDetalleDesembolso.accumulate("detalleDesembolsoID",CommonUtilities.toString(listDetalleDesemb.get(x).getDetalleDesembolsoID()));
								//jsonObjectDetalleDesembolso.accumulate("montoSolicitado", CommonUtilities.toString(listDetalleDesemb.get(x).getMontoSolicitado()));
								//jsonObjectDetalleDesembolso.accumulate("fkIdtablaespTipoMonedaMs", CommonUtilities.toString(listDetalleDesemb.get(x).getFkIdtablaespTipoMonedaMs()));
								
								tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(listDesembolso.get(j).getFkIdtablaespTipoMoneda());
								
								//jsonObjectDetalleDesembolso.accumulate("montoSolicitadoDetalle", 
								//CommonUtilities.toString(listDetalleDesemb.get(x).getMontoSolicitado())+ " " + tablaEspecifica.getDescripcionCabecera());
								
								jsonObjectDetalleDesembolso.accumulate("montoSolicitadoDet", 
										CommonUtilities.toString(listDetalleDesemb.get(x).getMontoSolicitado()));
								
								jsonObjectDetalleDesembolso.accumulate("tipoMonedaMsDesc", tablaEspecifica.getDescripcionCabecera());
								
								//jsonObjectDetalleDesembolso.accumulate("tipoMonedaDesc", tablaEspecifica.getDescripcionCabecera());
								jsonObjectDetalleDesembolso.accumulate("cronogramaCostoActividadID", CommonUtilities.toString(listDetalleDesemb.get(x).getCronogramaCostoActividadID().getCronogramaCostoActividadID()));
								
								//obtener datos cronograma costo actividad
								CronogramaCostoActividad cronogramaCostoActividad = cronogramaCostoActividadDAO.findCronogramaCostoActividadById(listDetalleDesemb.get(x).getCronogramaCostoActividadID().getCronogramaCostoActividadID());																
								jsonObjectDetalleDesembolso.accumulate("cantidad",CommonUtilities.toString(cronogramaCostoActividad.getCantidad()));
								jsonObjectDetalleDesembolso.accumulate("periodo",cronogramaCostoActividad.getPeriodo());
								
								//obtener datos costo actividad
								CostoActividad costoActividad = costoActividadDAO.findCostoActividadById(listDetalleDesemb.get(x).getCronogramaCostoActividadID().getCostoActividad().getCostoActividadID());
								tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(costoActividad.getFkIdtablaespUnidadMedida());
								String descUnidadMedida = tablaEspecifica.getDescripcionCabecera(); 
								String descCostoActividad =  costoActividad.getCantidadTotal() + " - " + descUnidadMedida;
								jsonObjectDetalleDesembolso.accumulate("descCostoAct",descCostoActividad);
								
								//obtener datos Actividad
								Actividad actividad = actividadDAO.findActividadById(costoActividad.getActividad().getActividadID());
								jsonObjectDetalleDesembolso.accumulate("descActividad",actividad.getDescripcionActividad());
								
								//obtener datos Resultado
								resultado = resultadoDAO.findResultadoById(actividad.getResultado().getResultadoID());
								jsonObjectDetalleDesembolso.accumulate("descResultado",resultado.getDefinicionResultado());
								
								jsonArrayDetalleDesembolso.add(jsonObjectDetalleDesembolso);
							}
							
							jsonObjectDesembolsosProyecto.accumulate("detalleDesembolso", jsonArrayDetalleDesembolso);
						}else{
							jsonObjectDesembolsosProyecto.accumulate("desembolsoID", CommonUtilities.toString( FondamConstans.EMPTY_STRING ));
						}
						
						
						//obtener version plan operativo verPlanOperativo
						if(CommonUtilities.isNotNullOrBlank(resultado)){
							DatoPlanOperativo datoPlanOperativo = datoPlanOperativoDAO.findDatoPlanOperativoById(resultado.getDatoPlanOperativo().getDatoPlanOperativoID());
							jsonObjectDesembolsosProyecto.accumulate("verPlanOperativo",datoPlanOperativo.getVersion());							
						}else{
							jsonObjectDesembolsosProyecto.accumulate("verPlanOperativo",FondamConstans.EMPTY_STRING);
						}

						
						jsonObjectDesembolsosProyecto.accumulate("periodo", CommonUtilities.toString(listDesembolso.get(j).getPeriodo()) != null ?
								CommonUtilities.toString(listDesembolso.get(j).getPeriodo()) : FondamConstans.EMPTY_STRING );					
						
						jsonObjectDesembolsosProyecto.accumulate("versionDePeriodo", CommonUtilities.toString(listDesembolso.get(j).getVersionDePeriodo()) != null ?
								CommonUtilities.toString(listDesembolso.get(j).getVersionDePeriodo()) : FondamConstans.EMPTY_STRING );
						
						
						if(CommonUtilities.toString(listDesembolso.get(j).getFkIdtablaespEstDesembolso()) != null){
							DetalleEstadoCabecera objDetEstCab = detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(listDesembolso.get(j).getFkIdtablaespEstDesembolso());
							jsonObjectDesembolsosProyecto.accumulate("idEstadoDesembolso", CommonUtilities.toString(objDetEstCab.getDetalleEstadoCabeceraID()) != null ?
									CommonUtilities.toString(objDetEstCab.getDetalleEstadoCabeceraID()) : FondamConstans.EMPTY_STRING );
							jsonObjectDesembolsosProyecto.accumulate("descEstadoDesembolso", CommonUtilities.toString(objDetEstCab.getDescripEstado()) != null ?
									CommonUtilities.toString(objDetEstCab.getDescripEstado()) : FondamConstans.EMPTY_STRING );
						}else{
							jsonObjectDesembolsosProyecto.accumulate("idEstadoDesembolso", FondamConstans.EMPTY_STRING);
							jsonObjectDesembolsosProyecto.accumulate("descEstadoDesembolso", FondamConstans.EMPTY_STRING);
						}
					
						/*if(CommonUtilities.isNotNullOrBlank(desembolso.getFkIdtablaespEstDesembolso())){
							jsonObjectDesembolso.accumulate("fkIdtablaespEstDesembolso", 
									CommonUtilities.convertNumberToString(desembolso.getFkIdtablaespEstDesembolso()));
						}else{
							jsonObjectDesembolso.accumulate("fkIdtablaespEstDesembolso", FondamConstans.EMPTY_STRING);
						}*/
						
						if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getFkIdtablaespTipoDesembolso())){						
							jsonObjectDesembolsosProyecto.accumulate("fkIdtablaespTipoDesembolso", CommonUtilities.convertNumberToString(listDesembolso.get(j).getFkIdtablaespTipoDesembolso()));
							tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(listDesembolso.get(j).getFkIdtablaespTipoDesembolso());
							jsonObjectDesembolsosProyecto.accumulate("tipoDesembolsoDesc", tablaEspecifica.getDescripcionCabecera());
						}else{
							jsonObjectDesembolsosProyecto.accumulate("fkIdtablaespTipoDesembolso", FondamConstans.EMPTY_STRING);
							jsonObjectDesembolsosProyecto.accumulate("tipoDesembolsoDesc", FondamConstans.EMPTY_STRING);
						}
						
						/*
						if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getTipoCambio())){
							jsonObjectDesembolsosProyecto.accumulate("tipoCambio",CommonUtilities.convertNumberToString(listDesembolso.get(j).getTipoCambio()));
						}else{
							jsonObjectDesembolsosProyecto.accumulate("tipoCambio", FondamConstans.EMPTY_STRING);
						}
						
						
						if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getFechaTipoCambio())){
							jsonObjectDesembolsosProyecto.accumulate("fechaTipoCambio", 
								CommonUtilities.convertDatetoString(listDesembolso.get(j).getFechaTipoCambio(), FondamConstans.INT_ONE));
						}else{
							jsonObjectDesembolsosProyecto.accumulate("fechaTipoCambio", FondamConstans.EMPTY_STRING);
						}
						*/
						
						
						if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getMontoSolicitado()) && listDesembolso.get(j).getMontoSolicitado()>0){
							tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(listDesembolso.get(j).getFkIdtablaespTipoMoneda());
							String montoSol= CommonUtilities.convertNumberToString(listDesembolso.get(j).getMontoSolicitado());										
							jsonObjectDesembolsosProyecto.accumulate("montoSolicitadoDesc",
									montoSol+" "+tablaEspecifica.getDescripcionCabecera());
							jsonObjectDesembolsosProyecto.accumulate("montoSolicitado", montoSol);							
						}else{
							jsonObjectDesembolsosProyecto.accumulate("montoSolicitado", FondamConstans.EMPTY_STRING);
						}
						
						
						/*
						if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getMontoSolicitado())){
							jsonObjectDesembolsosProyecto.accumulate("montoSolicitado",
									CommonUtilities.convertNumberToString(listDesembolso.get(j).getMontoSolicitado()));
						}else{
							jsonObjectDesembolsosProyecto.accumulate("montoSolicitado", FondamConstans.EMPTY_STRING);
						}
						
						if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getFkIdtablaespTipoMonedaMs())){
							jsonObjectDesembolsosProyecto.accumulate("fkIdtablaespTipoMonedaMs",
									CommonUtilities.convertNumberToString(listDesembolso.get(j).getFkIdtablaespTipoMonedaMs()));
							tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(listDesembolso.get(j).getFkIdtablaespTipoMonedaMs());
							jsonObjectDesembolsosProyecto.accumulate("tipoMonedaMsDesc", tablaEspecifica.getDescripcionCabecera());
							
						}else{
							jsonObjectDesembolsosProyecto.accumulate("fkIdtablaespTipoMonedaMs", FondamConstans.EMPTY_STRING);
							jsonObjectDesembolsosProyecto.accumulate("tipoMonedaMsDesc", FondamConstans.EMPTY_STRING);
						}
						*/
						
						
						
    					if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getMontoAutorizado())){
							jsonObjectDesembolsosProyecto.accumulate("montoAutorizado",
									CommonUtilities.convertNumberToString(listDesembolso.get(j).getMontoAutorizado()));			
						}else{
							jsonObjectDesembolsosProyecto.accumulate("montoAutorizado", FondamConstans.EMPTY_STRING);
						}
						
						if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getFkIdtablaespTipoMoneda())){
							jsonObjectDesembolsosProyecto.accumulate("fkIdtablaespTipoMoneda",
									CommonUtilities.convertNumberToString(listDesembolso.get(j).getFkIdtablaespTipoMoneda()));							
						}else{
							jsonObjectDesembolsosProyecto.accumulate("fkIdtablaespTipoMoneda", FondamConstans.EMPTY_STRING);
						}
						
						
						
						if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getSaldoDesembolsoAnterior())){
							jsonObjectDesembolsosProyecto.accumulate("saldoDesembolsoAnterior",
									CommonUtilities.convertNumberToString(listDesembolso.get(j).getSaldoDesembolsoAnterior()));
						}else{
							jsonObjectDesembolsosProyecto.accumulate("saldoDesembolsoAnterior", FondamConstans.EMPTY_STRING);
						}
						
						if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getRubroEspecial())){
							jsonObjectDesembolsosProyecto.accumulate("rubroEspecialID", 
									CommonUtilities.convertNumberToString(listDesembolso.get(j).getRubroEspecial().getRubroEspecialID()));
						}else{
							jsonObjectDesembolsosProyecto.accumulate("rubroEspecialID", FondamConstans.EMPTY_STRING);
						}
						
						
						if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getCuentaCorriente().getCuentaCorrienteID())){
							jsonObjectDesembolsosProyecto.accumulate("cuentaCorrienteID", 
									CommonUtilities.convertNumberToString(listDesembolso.get(j).getCuentaCorriente().getCuentaCorrienteID()));
							cuentaCorriente = cuentaCorrienteDAO.findCuentaCorrienteById(listDesembolso.get(j).getCuentaCorriente().getCuentaCorrienteID());
							jsonObjectDesembolsosProyecto.accumulate("cuentaCorrienteDesc", cuentaCorriente.getNumeroCuenta());
						}else{
							jsonObjectDesembolsosProyecto.accumulate("cuentaCorrienteID", FondamConstans.EMPTY_STRING);
							jsonObjectDesembolsosProyecto.accumulate("cuentaCorrienteDesc", FondamConstans.EMPTY_STRING);
						}
						
						if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getFuenteFinanciadora().getFuenteFinanciadoraID())){
							jsonObjectDesembolsosProyecto.accumulate("fuenteFinanciadoraID", 
									CommonUtilities.convertNumberToString(listDesembolso.get(j).getFuenteFinanciadora().getFuenteFinanciadoraID()));
							fuenteFinanciadora = fuenteFinanciadoraDAO.findFuenteFinanciadoraById(listDesembolso.get(j).getFuenteFinanciadora().getFuenteFinanciadoraID());
							jsonObjectDesembolsosProyecto.accumulate("fuenteFinanciadoraDesc", fuenteFinanciadora.getInstitucion().getNombreInstitucion());
						}else{
							jsonObjectDesembolsosProyecto.accumulate("fuenteFinanciadoraID", FondamConstans.EMPTY_STRING);
							jsonObjectDesembolsosProyecto.accumulate("fuenteFinanciadoraDesc", FondamConstans.EMPTY_STRING);
						}
						
						
						if(CommonUtilities.isNotNullOrBlank(listDesembolso.get(j).getDatoProyecto().getDatoProyectoID())){
							jsonObjectDesembolsosProyecto.accumulate("datoProyectoID", 
									CommonUtilities.convertNumberToString(listDesembolso.get(j).getDatoProyecto().getDatoProyectoID()));
						}else{
							jsonObjectDesembolsosProyecto.accumulate("datoProyectoID", FondamConstans.EMPTY_STRING);
						}
						
																					
						jsonArrayDesembolsosProyecto.add(jsonObjectDesembolsosProyecto);
						
					}//fin for desembolso
					
				}// fin for proyecto
				
		//}//fin if
		
		//combobox estados desembolso 
		List <DetalleEstadoCabecera> listEstadosDesembolso = utilService.listarDetalleEstadoCabeceraByPrefijo(FondamConstans.PREFIJO_ESTADO_DESEMBOLSO);

		JSONArray jsonArrayEstadosDesembolso = new JSONArray();
		
		JSONObject jsonObjectEstadoDesembolso = new JSONObject();
		
		if(listEstadosDesembolso != null && listEstadosDesembolso.size()>0){
				for(int i=0; i<listEstadosDesembolso.size(); i++){
					
					jsonObjectEstadoDesembolso = new JSONObject();
					if(listEstadosDesembolso.get(i).getDetalleEstadoCabeceraID() != FondamConstans.INT_EST_DESEMBOLSO_POR_EVALUAR){
						jsonObjectEstadoDesembolso.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX,listEstadosDesembolso.get(i).getDetalleEstadoCabeceraID() != null ?
								listEstadosDesembolso.get(i).getDetalleEstadoCabeceraID() : FondamConstans.EMPTY_STRING);
						jsonObjectEstadoDesembolso.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX,listEstadosDesembolso.get(i).getDescripEstado() != null ?
								listEstadosDesembolso.get(i).getDescripEstado() : FondamConstans.EMPTY_STRING);
						
						jsonArrayEstadosDesembolso.add(jsonObjectEstadoDesembolso);
					}
				}
		}
		
		//combobox tipo moneda
		List <TablaEspecifica> listTipoMoneda = tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_TIPO_MONEDA);
		 String jsonStrArrayTipoMoneda = solicitarDesembolsoService.convertirListTablaEspecificaToArrayJsonCBX(listTipoMoneda);
		 JSONArray jsonArrayTipoMoneda = (JSONArray) JSONSerializer.toJSON(jsonStrArrayTipoMoneda);	  
		
		jsonData.add(jsonArrayDesembolsosProyecto);//grilla desembolso por proyecto
		jsonData.add(jsonArrayEstadosDesembolso);//combobox estados desembolso 
		jsonData.add(jsonArrayTipoMoneda);//combobox tipo moneda
		
		return jsonData.toString();
		
	}
	
	/*
	public void saveAutorizacionDesembolso(Map<String, String> parametrosAutoSolDesembolso){
		
		try {
			Desembolso desembolso = desembolsoDAO.findDesembolsoById(CommonUtilities.toInt(parametrosAutoSolDesembolso.get("hiddenDesembolsoID"))); 
			
			if(CommonUtilities.toInt(parametrosAutoSolDesembolso.get("cbxEstadoDesembolso")) == FondamConstans.INT_EST_DESEMBOLSO_APROBADO){
				desembolso.setMontoAutorizado(CommonUtilities.toInt(parametrosAutoSolDesembolso.get("txtMontoAutorizado")));
				//desembolso.setFkIdtablaespTipoMonedaMa(CommonUtilities.toInt(parametrosAutoSolDesembolso.get("cbxTipoMoneda")));
				desembolso.setFkIdtablaespEstDesembolso(CommonUtilities.toInt(parametrosAutoSolDesembolso.get("cbxEstadoDesembolso")));
				
				SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaAprueba = formateador.parse(parametrosAutoSolDesembolso.get("fechaAutorizado"));
				desembolso.setFechaAprobacion(fechaAprueba);
			}else{
				desembolso.setFkIdtablaespEstDesembolso(CommonUtilities.toInt(parametrosAutoSolDesembolso.get("cbxEstadoDesembolso")));
			}
			
			desembolsoDAO.updateDesembolso(desembolso);
			
		} catch (Exception e) {
			logger.info("error saveAutorizacionDesembolso : "+e);
		}
		
		
	}
	*/

	public List<Programa> findProgramaByModFinan(int modFinan) {
		return programaDAO.findProgramaByModFinan(modFinan);
		
	}
	
	
	public String convertirListProgramaToArrayJson(List<Programa> lstPrograma){
		
			JSONArray jsonArrayPrograma = new JSONArray();
			
			JSONObject jsonObjectPrograma = null;
			
			for(int i=0; i<lstPrograma.size(); i++){
				
				jsonObjectPrograma = new JSONObject();
				
				if(lstPrograma.get(i).getProgramaID() != null){
					jsonObjectPrograma.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, lstPrograma.get(i).getProgramaID());
				}else{
					jsonObjectPrograma.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, FondamConstans.EMPTY_STRING);
				}
				
				if(lstPrograma.get(i).getNombrePrograma() != null){
					jsonObjectPrograma.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, lstPrograma.get(i).getNombrePrograma());
				}else{
					jsonObjectPrograma.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, FondamConstans.EMPTY_STRING);
				}
				
				jsonArrayPrograma.add(jsonObjectPrograma);
			}
			
			return jsonArrayPrograma.toString();
			
		
	}

	@Override
	public Desembolso updateAutorizacionDesembolso(Desembolso desembolso) {
		return desembolsoDAO.updateDesembolso(desembolso) ;
	}
	
}
