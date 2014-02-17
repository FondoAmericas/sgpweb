package pe.com.fondam.sgp.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.dao.ActividadDAO;
import pe.com.fondam.sgp.core.dao.ActividadDetallePagoDAO;
import pe.com.fondam.sgp.core.dao.CostoActividadDAO;
import pe.com.fondam.sgp.core.dao.CronogramaCostoActividadDAO;
import pe.com.fondam.sgp.core.dao.CuentaCorrienteDAO;
import pe.com.fondam.sgp.core.dao.DatoPlanOperativoDAO;
import pe.com.fondam.sgp.core.dao.DesembolsoDAO;
import pe.com.fondam.sgp.core.dao.DetalleDesembolsoDAO;
import pe.com.fondam.sgp.core.dao.DetalleEstadoCabeceraDAO;
import pe.com.fondam.sgp.core.dao.FuenteFinanciadoraDAO;
import pe.com.fondam.sgp.core.dao.InstitucionDAO;
import pe.com.fondam.sgp.core.dao.ResultadoDAO;
import pe.com.fondam.sgp.core.dao.TablaEspecificaDAO;
import pe.com.fondam.sgp.core.dao.UsuarioDAO;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.ActividadDetallePago;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.CuentaCorriente;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.DetalleDesembolso;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.Institucion;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.TipoCambio;
import pe.com.fondam.sgp.core.domain.Usuario;
import pe.com.fondam.sgp.core.service.SolicitarDesembolsoService;
import pe.com.fondam.sgp.core.service.TipoCambioService;
import pe.com.fondam.sgp.core.util.CommonUtilities;
import pe.com.fondam.sgp.core.util.UtilList;



@Service
public class SolicitarDesembolsoServiceImpl implements SolicitarDesembolsoService{

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
	private InstitucionDAO institucionDAO;
	
	@Resource
	private UsuarioDAO usuarioDAO;
	
	@Resource
	private CuentaCorrienteDAO cuentaCorrienteDAO;
	
	@Resource
	private ActividadDetallePagoDAO actividadDetallePagoDAO;
	
	@Resource
	private DetalleEstadoCabeceraDAO detalleEstadoCabeceraDAO;
	
	@Resource
	private DetalleDesembolsoDAO detalleDesembolsoDAO;
	
	@Resource
	private TipoCambioService tipoCambioService;
	
	
	protected final Log logger = LogFactory.getLog(SolicitarDesembolsoServiceImpl.class);
	
	@Override
	public DatoPlanOperativo findDatoPlanOperativoByDatoProyectoID2(
			Integer datoProyectoID) {
		return datoPlanOperativoDAO.findDatoPlanOperativoByDatoProyectoID2(datoProyectoID);
	}
	
	@Override
	public List<Resultado> findListResultadoByDatoPlanOperativoID(Integer datoPlanOperativoID){
		
		return resultadoDAO.findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		
	}
	
	public List<Actividad> findActividadByResultadoID(Integer resultadoID) {
		
		return actividadDAO.findActividadByResultadoID(resultadoID);
	}
	
	public List<CostoActividad> findCostoActividadByActividadID(Integer actividadID) {
		return costoActividadDAO.findCostoActividadByActividadID(actividadID);
	}
	
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadIdAndFuenteFinanciadoraID(Integer costoActividadID,Integer fuenteFinanciadoraID){
		return cronogramaCostoActividadDAO.findCronogramaCostoActividadByCostoActividadIdAndFuenteFinanciadoraID(costoActividadID,fuenteFinanciadoraID);
	}

	
	/*
	 * 
	 * 
	
	@Column(name = "Resultado_ID")
	private Integer resultadoID;

	@Column(name = "codigo_resultado")
	private Integer codigoResultado;

	@Column(name = "definicion_resultado")
	private String definicionResultado;

	@Column(name = "supuesto_resultado")
	private String supuestoResultado;

	@Column(name = "meta_resultado")
	private Integer metaResultado;

	@Column(name = "fk_idtablaesp_estrato")
	private Integer fkIdtablaespEstrato;

	@Column(name = "duracion_meses")
	private Integer duracionMeses;

	 * 
	 * */
	
	public String convertirListResultadoToArrayJson(List <Resultado> listResultado){
		
		JSONArray jsonArrayResultado = new JSONArray();
		
		JSONObject jsonObjectResultado = null;
		
		for(int i=0; i<listResultado.size(); i++){
			
			jsonObjectResultado = new JSONObject();
			
			if(listResultado.get(i).getResultadoID() != null){
				jsonObjectResultado.accumulate("resultadoID", listResultado.get(i).getResultadoID());
				jsonObjectResultado.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, listResultado.get(i).getResultadoID());
			}else{
				jsonObjectResultado.accumulate("resultadoID", FondamConstans.EMPTY_STRING);
				jsonObjectResultado.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, FondamConstans.EMPTY_STRING);
			}
				
			if(listResultado.get(i).getCodigoResultado() != null){
				jsonObjectResultado.accumulate("codigoResultado", listResultado.get(i).getCodigoResultado());
			}else{
				jsonObjectResultado.accumulate("codigoResultado", FondamConstans.EMPTY_STRING);
			}
			
			if(listResultado.get(i).getDefinicionResultado() != null){
				jsonObjectResultado.accumulate("definicionResultado", listResultado.get(i).getDefinicionResultado());
				jsonObjectResultado.accumulate("text", listResultado.get(i).getDefinicionResultado());
			}else{
				jsonObjectResultado.accumulate("definicionResultado", FondamConstans.EMPTY_STRING);
			}
			
			if(listResultado.get(i).getSupuestoResultado() != null){
				jsonObjectResultado.accumulate("supuestoResultado", listResultado.get(i).getSupuestoResultado());
			}else{
				jsonObjectResultado.accumulate("supuestoResultado", FondamConstans.EMPTY_STRING);
			}
			
			if(listResultado.get(i).getMetaResultado() != null){
				jsonObjectResultado.accumulate("metaResultado", listResultado.get(i).getMetaResultado());
			}else{
				jsonObjectResultado.accumulate("metaResultado", FondamConstans.EMPTY_STRING);
			}
			if(listResultado.get(i).getDuracionMeses() != null){
				jsonObjectResultado.accumulate("duracionMeses", listResultado.get(i).getDuracionMeses());
			}else{
				jsonObjectResultado.accumulate("duracionMeses", FondamConstans.EMPTY_STRING);
			}
			
			jsonArrayResultado.add(jsonObjectResultado);
		}
		
		return jsonArrayResultado.toString();

	}
	
	
	
	
	
	
	/* 
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Actividad_ID")
	private Integer actividadID;

	@Column(name = "codigo_actividad")
	private Integer codigoActividad;

	@Column(name = "fk_idtablaesp_tipo_actividad")
	private Integer fkIdtablaespTipoActividad;

	@Column(name = "nombre_actividad")
	private String nombreActividad;

	@Column(name = "descripcion_actividad")
	private String descripcionActividad;

	@Column(name = "duracion_meses")
	private Integer duracionMeses;

	@Column(name = "fk_idtablaesp_actividad_transf")
	private Integer fkIdtablaespActividadTransf;

	@ManyToOne
	@JoinColumn(name = "Resultado_ID", referencedColumnName = "Resultado_ID")
	private Resultado resultado;
  
	  
	  
	 */
	
	
	public String convertirListActividadToArrayJson(List<Actividad> listActividad){
		
		JSONArray jsonArrayActividad = new JSONArray();
		
		JSONObject jsonObjectActividad = null;
		
		for(int i=0; i<listActividad.size(); i++){
			
			jsonObjectActividad = new JSONObject();
			
			if(listActividad.get(i).getActividadID() != null){
				jsonObjectActividad.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, listActividad.get(i).getActividadID());
			}else{
				jsonObjectActividad.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, FondamConstans.EMPTY_STRING);
			}
			
			if(listActividad.get(i).getNombreActividad() != null){
				jsonObjectActividad.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, listActividad.get(i).getNombreActividad());
			}else{
				jsonObjectActividad.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, FondamConstans.EMPTY_STRING);
			}
			
			jsonArrayActividad.add(jsonObjectActividad);
		}
		
		return jsonArrayActividad.toString();
		
	}

/*	
public String convertirListResultadoToArrayJsonAuto(List <CamposCBXForm> listResultado){
		
		JSONArray jsonArrayResultado = new JSONArray();
		
		JSONObject jsonObjectCBX = null;
		
		for(int i=0; i<listResultado.size(); i++){
			
			jsonObjectCBX = new JSONObject();
			
			jsonObjectCBX.accumulate("value", listResultado.get(i).getValue());
			jsonObjectCBX.accumulate("text", listResultado.get(i).getText());
			jsonArrayResultado.add(jsonObjectCBX);
		}
		
		return jsonArrayResultado.toString();

	}
*/


	
	public String convertirListCostoActividadToArrayJson(List<CostoActividad> listCostoActividad){
				
		JSONArray jsonArrayCostoActividad = new JSONArray();
		
		JSONObject jsonObjectCostoActividad = null;
		
		TablaEspecifica tablaEspecifica = null;
		
		String descUnidadMedida=null;
		
		for(int i=0; i<listCostoActividad.size(); i++){
			
			jsonObjectCostoActividad = new JSONObject();

			jsonObjectCostoActividad.accumulate("costoActividadID", listCostoActividad.get(i).getCostoActividadID() != null ?
					 listCostoActividad.get(i).getCostoActividadID() : FondamConstans.EMPTY_STRING );
			jsonObjectCostoActividad.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, listCostoActividad.get(i).getCostoActividadID() != null ?
					 listCostoActividad.get(i).getCostoActividadID() : FondamConstans.EMPTY_STRING );
			
			if(listCostoActividad.get(i).getCantidadTotal() != null && listCostoActividad.get(i).getFkIdtablaespUnidadMedida() != null
					&& listCostoActividad.get(i).getPrecioUnitario() != null){
				tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(listCostoActividad.get(i).getFkIdtablaespUnidadMedida());
				descUnidadMedida = tablaEspecifica.getDescripcionCabecera(); 
				String descCBXCostoActividad =  listCostoActividad.get(i).getCantidadTotal() + " - " + descUnidadMedida;
				jsonObjectCostoActividad.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, descCBXCostoActividad);						
				jsonObjectCostoActividad.accumulate("cantidadTotal", CommonUtilities.toString(listCostoActividad.get(i).getCantidadTotal()));
				jsonObjectCostoActividad.accumulate("fkIdtablaespUnidadMedida", listCostoActividad.get(i).getFkIdtablaespUnidadMedida());
				jsonObjectCostoActividad.accumulate("descUnidadMedida", descUnidadMedida);
				jsonObjectCostoActividad.accumulate("precioUnitario",CommonUtilities.toString(CommonUtilities.toDouble(listCostoActividad.get(i).getPrecioUnitario())));
				Double canTotal = CommonUtilities.toDouble(listCostoActividad.get(i).getCantidadTotal());
				Double precioUni = listCostoActividad.get(i).getPrecioUnitario();
				Double saldoTotalCostAct = canTotal * precioUni;
				jsonObjectCostoActividad.accumulate("saldoTotalCostAct", CommonUtilities.toString(saldoTotalCostAct));
			}else{
				jsonObjectCostoActividad.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, FondamConstans.EMPTY_STRING);
				jsonObjectCostoActividad.accumulate("cantidadTotal", FondamConstans.EMPTY_STRING);
				jsonObjectCostoActividad.accumulate("descUnidadMedida", FondamConstans.EMPTY_STRING);
				jsonObjectCostoActividad.accumulate("precioUnitario", FondamConstans.EMPTY_STRING);
			}
			/*
			if(listCostoActividad.get(i).getFkIdtablaespTipoMonedaCt() != null){
				tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(listCostoActividad.get(i).getFkIdtablaespTipoMonedaCt());
				jsonObjectCostoActividad.accumulate("fkIdtablaespTipoMonedaCt", tablaEspecifica.getDescripcionCabecera() );
			}else{
				jsonObjectCostoActividad.accumulate("fkIdtablaespTipoMonedaCt", FondamConstans.EMPTY_STRING );
			}
			*/
			
			/*
			if(listCostoActividad.get(i).getFkIdtablaespTipoMonedaPu() != null){
				tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(listCostoActividad.get(i).getFkIdtablaespTipoMonedaPu());
				jsonObjectCostoActividad.accumulate("fkIdtablaespTipoMonedaPu", tablaEspecifica.getDescripcionCabecera() );
			}else{
				jsonObjectCostoActividad.accumulate("fkIdtablaespTipoMonedaPu", FondamConstans.EMPTY_STRING );
			}
			*/
			jsonObjectCostoActividad.accumulate("actividadID", listCostoActividad.get(i).getActividad().getActividadID() != null ?
					CommonUtilities.toString(listCostoActividad.get(i).getActividad().getActividadID()) : FondamConstans.EMPTY_STRING );
					
			jsonArrayCostoActividad.add(jsonObjectCostoActividad);
		}
		
		return jsonArrayCostoActividad.toString();
		
	}

	
	
	/*

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Cronograma_Costo_Actividad_ID")
	private Integer cronogramaCostoActividadID;

	@ManyToOne
	@JoinColumn(name = "Costo_Actividad_ID", referencedColumnName = "Costo_Actividad_ID")
	private CostoActividad costoActividad;

	@Column(name = "periodo")
	private String periodo;

	@Column(name = "estado_liquidacion")
	private String estadoLiquidacion;

	@Column(name = "cantidad")
	private Integer cantidad;

	@ManyToOne
	@JoinColumn(name = "Fuente_Financiadora_ID", referencedColumnName = "Fuente_Financiadora_ID")
	private FuenteFinanciadora fuenteFinanciadora;

	@Column(name = "Detalle_Pago_Liquidacion_ID")
	private Integer detallePagoLiquidacionID;

	 */
	
	
	
	@SuppressWarnings("unchecked")
	public String convertirListCronogramaCostoActividadToArrayJson(List<CronogramaCostoActividad> listCronogramaCostoActividad,String jsonObjectCostoActividad){
		
		//int costoActividadID   = JSONObject.fromObject(jsonObjectCostoActividad).getInt("costoActividadID");
		double precioUnitario = JSONObject.fromObject(jsonObjectCostoActividad).getDouble("precioUnitario");
		int unidadMedidaID = JSONObject.fromObject(jsonObjectCostoActividad).getInt("fkIdtablaespUnidadMedida");
		
		listCronogramaCostoActividad = (List<CronogramaCostoActividad>) UtilList.orderAscList(listCronogramaCostoActividad, "periodo");
		
		JSONArray jsonArrayCronoCostoActividad = new JSONArray();
		
		JSONObject jsonObjectCronoCostoActividad = null;
		
		if(listCronogramaCostoActividad != null && listCronogramaCostoActividad.size()>0){
		
			for(int i=0; i<listCronogramaCostoActividad.size(); i++){
				
				jsonObjectCronoCostoActividad = new JSONObject();
				
				if(listCronogramaCostoActividad.get(i).getCantidad() > 0){
				
				if(listCronogramaCostoActividad.get(i).getCronogramaCostoActividadID() != null){
					jsonObjectCronoCostoActividad.accumulate("cronogramaCostoActividadID", listCronogramaCostoActividad.get(i).getCronogramaCostoActividadID());
				}else{
					jsonObjectCronoCostoActividad.accumulate("cronogramaCostoActividadID", FondamConstans.EMPTY_STRING);
				}
				
				if(listCronogramaCostoActividad.get(i).getCronogramaCostoActividadID() != null){
					jsonObjectCronoCostoActividad.accumulate("periodo", listCronogramaCostoActividad.get(i).getPeriodo());
				}else{
					jsonObjectCronoCostoActividad.accumulate("periodo", FondamConstans.EMPTY_STRING);
				}
				
				if(listCronogramaCostoActividad.get(i).getCronogramaCostoActividadID() != null){
					jsonObjectCronoCostoActividad.accumulate("cantidad", listCronogramaCostoActividad.get(i).getCantidad());
					
					TablaEspecifica tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(unidadMedidaID);
					String descUnidadMedida = tablaEspecifica.getDescripcionCabecera();
					
					jsonObjectCronoCostoActividad.accumulate("cantidadDesUnidadMedida",listCronogramaCostoActividad.get(i).getCantidad() 
							+" - "+ descUnidadMedida);
				}else{
					jsonObjectCronoCostoActividad.accumulate("cantidad", FondamConstans.EMPTY_STRING);
					jsonObjectCronoCostoActividad.accumulate("cantidadDesUnidadMedida", FondamConstans.EMPTY_STRING);
				}
				
				if(listCronogramaCostoActividad.get(i).getCronogramaCostoActividadID() != null){
					jsonObjectCronoCostoActividad.accumulate("estadoLiquidacion", listCronogramaCostoActividad.get(i).getEstadoLiquidacion());
				}else{
					jsonObjectCronoCostoActividad.accumulate("estadoLiquidacion", FondamConstans.EMPTY_STRING);
				}
				
				String descMoneda = FondamConstans.EMPTY_STRING;
				if(listCronogramaCostoActividad.get(i).getCostoActividad().getFkIdtablaespTipoMoneda() != null){
					TablaEspecifica tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(listCronogramaCostoActividad.get(i).getCostoActividad().getFkIdtablaespTipoMoneda());
					descMoneda = tablaEspecifica.getDescripcionCabecera();
					jsonObjectCronoCostoActividad.accumulate("descMoneda", descMoneda);
				}else{
					jsonObjectCronoCostoActividad.accumulate("descMoneda", FondamConstans.EMPTY_STRING);
				}
				
				double dblSaldoProg=0.0;
				if(listCronogramaCostoActividad.get(i).getCantidad() != null){
					dblSaldoProg = listCronogramaCostoActividad.get(i).getCantidad() * precioUnitario;
					String strSaldoProg = CommonUtilities.toString(dblSaldoProg); 
					
					jsonObjectCronoCostoActividad.accumulate("saldoProgramado", 
							strSaldoProg);
					
					jsonObjectCronoCostoActividad.accumulate("saldoProgramadoDesc", 
							strSaldoProg+" "+descMoneda);										
				}else{
					jsonObjectCronoCostoActividad.accumulate("saldoProgramado", FondamConstans.EMPTY_STRING);
				}
				
				//traer todas las actividad_detalle pago y sumar su monto gastado, en funcion a un getCronogramaCostoActividadID, para obtener el saldo disponible
				List <ActividadDetallePago> listActDetPago = actividadDetallePagoDAO.findActividadDetallePagoByCronogramaCostoAct(listCronogramaCostoActividad.get(i).getCronogramaCostoActividadID());
				double dblMontoGastado = 0.0;
				if(listActDetPago.size() > 0){
					for(ActividadDetallePago actividadDetallePago : listActDetPago){
						dblMontoGastado = dblMontoGastado + actividadDetallePago.getMontoGastado();
					}					
				}					
				if(dblSaldoProg > dblMontoGastado){
					jsonObjectCronoCostoActividad.accumulate("saldoDisponible",	CommonUtilities.toString(dblSaldoProg - dblMontoGastado));
					
					jsonObjectCronoCostoActividad.accumulate("saldoDisponibleDesc",	CommonUtilities.toString(dblSaldoProg - dblMontoGastado)+" "+descMoneda);
				}else{
					jsonObjectCronoCostoActividad.accumulate("saldoDisponible",	FondamConstans.STR_ZERO);
				}
				
     			jsonArrayCronoCostoActividad.add(jsonObjectCronoCostoActividad);
     			
     			
			}
			}
			
		}
		
		return jsonArrayCronoCostoActividad.toString();
	}

	@Override
	public String convertirListTablaEspecificaToArrayJsonCBX(List<TablaEspecifica> listTipoDesembolso) {
		
		JSONArray jsonData = new JSONArray();
		
		JSONObject jsonObjectElementTable = new JSONObject();
		
		if(listTipoDesembolso != null && listTipoDesembolso.size() > 0){
			for(int i=0; i<listTipoDesembolso.size(); i++){
				
				jsonObjectElementTable = new JSONObject();
				
				if(listTipoDesembolso.get(i).getTablaEspecificaID() != null){
					jsonObjectElementTable.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, listTipoDesembolso.get(i).getTablaEspecificaID());
				}else{
					jsonObjectElementTable.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, FondamConstans.EMPTY_STRING);
				}
				
				if(listTipoDesembolso.get(i).getDescripcionCabecera() != null){
					jsonObjectElementTable.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, listTipoDesembolso.get(i).getDescripcionCabecera());
				}else{
					jsonObjectElementTable.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, FondamConstans.EMPTY_STRING);
				}
				
				jsonData.add(jsonObjectElementTable);
			}
			
		}
		
		return jsonData.toString();
	}
	
	
/*
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getCronogramaCostoActividadByCostoActividadID_JSONArray(Integer costoActividadID){
		JSONArray resultArrayJSON = new JSONArray();
		JSONObject jsonObject;
		List listCronoCostoAct = cronogramaCostoActividadDAO.findCronogramaCostoActividadByCostoActividadID(costoActividadID);
		
		if(listCronoCostoAct != null && !listCronoCostoAct.isEmpty()){
			Object[] arrObj = null, arrObjTemp=null;					
			List lstTemp = null;
			for(Iterator it=listCronoCostoAct.iterator(); it.hasNext();){
				arrObj = (Object[])it.next();
				
				jsonObject = new JSONObject();
				
				jsonObject.accumulate("cronogramaCostoActividadID", arrObj[0] != null ? arrObj[0].toString() : FondamConstans.EMPTY_STRING);
				
				jsonObject.accumulate("costoActividadID", arrObj[1] != null ? arrObj[1].toString() : FondamConstans.EMPTY_STRING);
				
				jsonObject.accumulate("periodo", arrObj[2] != null ? arrObj[2].toString() : FondamConstans.EMPTY_STRING);
				
				jsonObject.accumulate("estadoLiquidacion", arrObj[3] != null ? arrObj[3].toString() : FondamConstans.EMPTY_STRING);
				
				resultArrayJSON.add(jsonObject);
			}
		}
	
		return resultArrayJSON.toString();
	}
*/
	
	
	public List<Desembolso> findDesembolsoUltimaVersionByDatoProyectoID(Integer datoProyectoID){
		return desembolsoDAO.findDesembolsoUltimaVersionByDatoProyectoID(datoProyectoID);
		
		
	}
	
	public String convertirListEstadosDesembolsoToArrayJsonCBX(List<DetalleEstadoCabecera> listEstadosDesembolso){
		
		JSONArray jsonData = new JSONArray();
		
		JSONObject jsonObjectEstadoDesembolso = new JSONObject();
		
		if(listEstadosDesembolso != null && listEstadosDesembolso.size()>0){
				for(int i=0; i<listEstadosDesembolso.size(); i++){
					
					jsonObjectEstadoDesembolso = new JSONObject();
					
					jsonObjectEstadoDesembolso.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX,listEstadosDesembolso.get(i).getDetalleEstadoCabeceraID() != null ?
							listEstadosDesembolso.get(i).getDetalleEstadoCabeceraID() : FondamConstans.EMPTY_STRING);
					jsonObjectEstadoDesembolso.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX,listEstadosDesembolso.get(i).getDescripEstado() != null ?
							listEstadosDesembolso.get(i).getDescripEstado() : FondamConstans.EMPTY_STRING);
					
					jsonData.add(jsonObjectEstadoDesembolso);
				}
		}
		return jsonData.toString();
	}
	
	public String convertirListDesembolsoToArrayJson(List<Desembolso> listDesembolso){
		JSONObject jsonObjectDesembolso;
		Desembolso desembolso;
		TablaEspecifica tablaEspecifica = null;
		FuenteFinanciadora fuenteFinanciadora = null;
		CuentaCorriente cuentaCorriente = null;
		JSONArray jsonData = new JSONArray();
		DetalleEstadoCabecera detalleEstadoCabecera=null;
		
		if(CommonUtilities.isNotNullOrBlank(listDesembolso) && listDesembolso.size()>0){
			for(int i=0; i<listDesembolso.size(); i++){
				    desembolso = new Desembolso();
					desembolso = listDesembolso.get(i);
					jsonObjectDesembolso = new JSONObject();
					if(CommonUtilities.isNotNullOrBlank(desembolso.getDesembolsoID())){
						jsonObjectDesembolso.accumulate("desembolsoID", CommonUtilities.convertNumberToString(desembolso.getDesembolsoID()));
					}else{
						jsonObjectDesembolso.accumulate("desembolsoID", FondamConstans.EMPTY_STRING);
					}
					
					if(CommonUtilities.isNotNullOrBlank(desembolso.getFkIdtablaespTipoDesembolso())){						
						jsonObjectDesembolso.accumulate("fkIdtablaespTipoDesembolso", CommonUtilities.convertNumberToString(desembolso.getFkIdtablaespTipoDesembolso()));
						tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(desembolso.getFkIdtablaespTipoDesembolso());
						jsonObjectDesembolso.accumulate("tipoDesembolsoDesc", tablaEspecifica.getDescripcionCabecera());
					}else{
						jsonObjectDesembolso.accumulate("fkIdtablaespTipoDesembolso", FondamConstans.EMPTY_STRING);
						jsonObjectDesembolso.accumulate("tipoDesembolsoDesc", FondamConstans.EMPTY_STRING);
					}
					
					TablaEspecifica teTipoMoneda;
					if(CommonUtilities.isNotNullOrBlank(desembolso.getFkIdtablaespTipoMoneda())){
						jsonObjectDesembolso.accumulate("fkIdtablaespTipoMoneda",
								CommonUtilities.convertNumberToString(desembolso.getFkIdtablaespTipoMoneda()));
						teTipoMoneda = tablaEspecificaDAO.findTablaEspecificaById(desembolso.getFkIdtablaespTipoMoneda());
						jsonObjectDesembolso.accumulate("tipoMonedaDesc", teTipoMoneda.getDescripcionCabecera());
						
					}else{
						jsonObjectDesembolso.accumulate("fkIdtablaespTipoMoneda", FondamConstans.EMPTY_STRING);
						jsonObjectDesembolso.accumulate("tipoMonedaDesc", FondamConstans.EMPTY_STRING);
					}
					
		
					if(CommonUtilities.isNotNullOrBlank(desembolso.getMontoSolicitado()) && desembolso.getMontoSolicitado()>0){
						String montoSol= CommonUtilities.convertNumberToString(desembolso.getMontoSolicitado());										
					
						jsonObjectDesembolso.accumulate("montoSolicitado", montoSol);
					}else{
						jsonObjectDesembolso.accumulate("montoSolicitado", FondamConstans.EMPTY_STRING);
					}
					
					if(CommonUtilities.isNotNullOrBlank(desembolso.getMontoAutorizado()) && desembolso.getMontoAutorizado()>0){
						
						/*tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(desembolso.getFkIdtablaespTipoMonedaMa());*/
						
						String montoAuto= CommonUtilities.convertNumberToString(desembolso.getMontoAutorizado());										
						
						/*jsonObjectDesembolso.accumulate("montoAutorizado", montoAuto+" "+tablaEspecifica.getDescripcionCabecera());*/
						
						jsonObjectDesembolso.accumulate("montoAutorizado", montoAuto);
						
						
					}else{
						jsonObjectDesembolso.accumulate("montoAutorizado", "0.0");
					}
					
					//obtener ultimo desembolso
					List <Desembolso> lstDesembolso = desembolsoDAO.findDesembolsoUltimaVersionByDatoProyectoID(desembolso.getDatoProyecto().getDatoProyectoID());//el primero de la lista es el utimo desembolso
					//sumar sus montos gastados
					List <ActividadDetallePago> lstActDetPago = actividadDetallePagoDAO.findActividadDetallePagoByDesembolsoID(lstDesembolso.get(0).getDesembolsoID());
					double montoGastadoTot=0;
					if(CommonUtilities.isNotNullOrBlank(lstActDetPago)){
						
							for(ActividadDetallePago objActDetPago : lstActDetPago){
								montoGastadoTot = montoGastadoTot + objActDetPago.getMontoGastado();						
							}
							
							if(lstDesembolso.get(0).getMontoAutorizado() >  montoGastadoTot){ //ultimo desembolso : lstDesembolso.get(0)
								jsonObjectDesembolso.accumulate("saldoDesembolsoAnteriorView",
										CommonUtilities.toString(lstDesembolso.get(0).getMontoAutorizado() - montoGastadoTot));
							}else{
								jsonObjectDesembolso.accumulate("saldoDesembolsoAnteriorView",
										CommonUtilities.toString(lstDesembolso.get(0).getMontoAutorizado()));
							}								
					}else{
						jsonObjectDesembolso.accumulate("saldoDesembolsoAnteriorView" , FondamConstans.EMPTY_STRING );
					}
					
					if(CommonUtilities.isNotNullOrBlank(desembolso.getSaldoDesembolsoAnterior())){
						jsonObjectDesembolso.accumulate("saldoDesembolsoAnterior", desembolso.getSaldoDesembolsoAnterior());
					}else{
						jsonObjectDesembolso.accumulate("saldoDesembolsoAnterior", FondamConstans.EMPTY_STRING);
					}
					
					
					if(CommonUtilities.isNotNullOrBlank(desembolso.getPeriodo())){
						jsonObjectDesembolso.accumulate("periodo", desembolso.getPeriodo());
					}else{
						jsonObjectDesembolso.accumulate("periodo", FondamConstans.EMPTY_STRING);
					}
					
					if(CommonUtilities.isNotNullOrBlank(desembolso.getFkIdtablaespEstDesembolso())){
						jsonObjectDesembolso.accumulate("fkIdtablaespEstDesembolso", 
								CommonUtilities.convertNumberToString(desembolso.getFkIdtablaespEstDesembolso()));
					}else{
						jsonObjectDesembolso.accumulate("fkIdtablaespEstDesembolso", FondamConstans.EMPTY_STRING);
					}
					
					if(CommonUtilities.isNotNullOrBlank(desembolso.getVersionDePeriodo())){
						jsonObjectDesembolso.accumulate("versionDePeriodo", 
								CommonUtilities.convertNumberToString(desembolso.getVersionDePeriodo()));
					}else{
						jsonObjectDesembolso.accumulate("versionDePeriodo", FondamConstans.EMPTY_STRING);
					}
					
					if(CommonUtilities.isNotNullOrBlank(desembolso.getRubroEspecial())){
						jsonObjectDesembolso.accumulate("rubroEspecialID", 
								CommonUtilities.convertNumberToString(desembolso.getRubroEspecial().getRubroEspecialID()));
					}else{
						jsonObjectDesembolso.accumulate("rubroEspecialID", FondamConstans.EMPTY_STRING);
					}
					
					if(CommonUtilities.isNotNullOrBlank(desembolso.getCuentaCorriente().getCuentaCorrienteID())){
						jsonObjectDesembolso.accumulate("cuentaCorrienteID", 
								CommonUtilities.convertNumberToString(desembolso.getCuentaCorriente().getCuentaCorrienteID()));
						cuentaCorriente = cuentaCorrienteDAO.findCuentaCorrienteById(desembolso.getCuentaCorriente().getCuentaCorrienteID());
						jsonObjectDesembolso.accumulate("cuentaCorrienteDesc", cuentaCorriente.getNumeroCuenta());
					}else{
						jsonObjectDesembolso.accumulate("cuentaCorrienteID", FondamConstans.EMPTY_STRING);
						jsonObjectDesembolso.accumulate("cuentaCorrienteDesc", FondamConstans.EMPTY_STRING);
					}
					
					if(CommonUtilities.isNotNullOrBlank(desembolso.getFuenteFinanciadora().getFuenteFinanciadoraID())){
						jsonObjectDesembolso.accumulate("fuenteFinanciadoraID", 
								CommonUtilities.convertNumberToString(desembolso.getFuenteFinanciadora().getFuenteFinanciadoraID()));
						fuenteFinanciadora = fuenteFinanciadoraDAO.findFuenteFinanciadoraById(desembolso.getFuenteFinanciadora().getFuenteFinanciadoraID());
						jsonObjectDesembolso.accumulate("fuenteFinanciadoraDesc", fuenteFinanciadora.getInstitucion().getNombreInstitucion());
					}else{
						jsonObjectDesembolso.accumulate("fuenteFinanciadoraID", FondamConstans.EMPTY_STRING);
						jsonObjectDesembolso.accumulate("fuenteFinanciadoraDesc", FondamConstans.EMPTY_STRING);
					}
					
					if(CommonUtilities.isNotNullOrBlank(desembolso.getDatoProyecto().getDatoProyectoID())){
												
						jsonObjectDesembolso.accumulate("datoProyectoID", 
								CommonUtilities.convertNumberToString(desembolso.getDatoProyecto().getDatoProyectoID()));
												
					}else{
						jsonObjectDesembolso.accumulate("datoProyectoID", FondamConstans.EMPTY_STRING);
					}
					
					if(CommonUtilities.isNotNullOrBlank(desembolso.getFkIdtablaespEstDesembolso())){
						
						detalleEstadoCabecera = detalleEstadoCabeceraDAO.findDetalleEstadoCabeceraById(desembolso.getFkIdtablaespEstDesembolso());
						jsonObjectDesembolso.accumulate("estadoDesembolso", 
								detalleEstadoCabecera.getDescripEstado() != null ? detalleEstadoCabecera.getDescripEstado() : FondamConstans.EMPTY_STRING);
					}else{
						jsonObjectDesembolso.accumulate("estadoDesembolso", FondamConstans.EMPTY_STRING);
					}
					
					
			  jsonData.add(jsonObjectDesembolso);
			}
		}//fin if
		
	
		return jsonData.toString();
		
	}
	
	public String convertirListFuenteFinanToArrayJsonCBX(List<FuenteFinanciadora> listFuenteFinan){
		
		JSONArray jsonData = new JSONArray();
		
		JSONObject jsonObjectFuenteFinan = new JSONObject();
		
		
		if(listFuenteFinan != null && listFuenteFinan.size() > 0){
			for(int i=0; i<listFuenteFinan.size(); i++){
				
				Institucion institucion = institucionDAO.findInstitucionById(listFuenteFinan.get(i).getInstitucion().getInstitucionID());
				jsonObjectFuenteFinan = new JSONObject();		
				
				jsonObjectFuenteFinan.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, listFuenteFinan.get(i).getFuenteFinanciadoraID() != null ? 
						CommonUtilities.toString(listFuenteFinan.get(i).getFuenteFinanciadoraID()) : FondamConstans.EMPTY_STRING );
				jsonObjectFuenteFinan.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, institucion.getNombreInstitucion() != null ? 
						institucion.getNombreInstitucion() : FondamConstans.EMPTY_STRING );
				jsonObjectFuenteFinan.accumulate("rucInstitucion", institucion.getRucInstitucion() != null ? 
						institucion.getRucInstitucion() : FondamConstans.EMPTY_STRING );
												
				jsonData.add(jsonObjectFuenteFinan);
			}
			
		}
		
		return jsonData.toString();
		
	}
	
	
	public String convertirListCuentaCorrienteToArrayJsonCBX(List<CuentaCorriente> listCuentaCorriente){
		JSONArray jsonData = new JSONArray();
		
		JSONObject jsonObjectCuentaCorriente = new JSONObject();
		
		
		if(listCuentaCorriente != null && listCuentaCorriente.size() > 0){
			for(int i=0; i<listCuentaCorriente.size(); i++){
				
				jsonObjectCuentaCorriente = new JSONObject();		
				
				jsonObjectCuentaCorriente.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, listCuentaCorriente.get(i).getCuentaCorrienteID() != null ? 
						CommonUtilities.toString(listCuentaCorriente.get(i).getCuentaCorrienteID()) : FondamConstans.EMPTY_STRING );
				jsonObjectCuentaCorriente.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, listCuentaCorriente.get(i).getNumeroCuenta() != null ? 
						listCuentaCorriente.get(i).getNumeroCuenta() : FondamConstans.EMPTY_STRING );
												
				jsonData.add(jsonObjectCuentaCorriente);
			}
			
		}
		
		return jsonData.toString();
		
	}
	
	public String getPerfilUsuario(Integer datoUsuarioID){
		List<Usuario> lstUsuario = usuarioDAO.findUsuarioByDatoUsuarioID(datoUsuarioID);
		int perfilUsuario = 0;
		for(Usuario usuario : lstUsuario){
			if(usuario.getPerfilUsuario().getPerfilUsuarioID() == FondamConstans.INT_PERFIL_EJECUTOR || 
					usuario.getPerfilUsuario().getPerfilUsuarioID() == FondamConstans.INT_PERFIL_DAFI){
				perfilUsuario = usuario.getPerfilUsuario().getPerfilUsuarioID(); break;
			}
		}
		return CommonUtilities.toString(perfilUsuario);
	}
	
	
	//public void saveSolicitudDesembolso(Map<String, String> parametrosSolDesembolso,List<TipoCambio> listaTipoCambio){
		public void saveSolicitudDesembolso(Map<String, String> parametrosSolDesembolso, JSONArray jsonListaTipoCambio){
		
		try {
			Desembolso desembolso = new Desembolso(); 
			desembolso.setFkIdtablaespTipoDesembolso(CommonUtilities.toInt(parametrosSolDesembolso.get("cbxTipoDesembolso")));
			//desembolso.setTipoCambio(CommonUtilities.toDouble(parametrosSolDesembolso.get("txtTipoCambio")));
			//desembolso.setFechaTipoCambio(CommonUtilities.convertStringtoDate(parametrosSolDesembolso.get("txtFechaTipoCambio")));
			desembolso.setMontoSolicitado(CommonUtilities.toDouble(parametrosSolDesembolso.get("txtMontoSolicitado")));
			desembolso.setFkIdtablaespTipoMoneda(CommonUtilities.toInt(parametrosSolDesembolso.get("cbxTipoMoneda")));
			
			//desembolso.setMontoAutorizado(0.0);//CommonUtilities.toDouble(parametrosSolDesembolso.get("txtMontoAutorizado")) //cambiar a nulo en la base datos
			//desembolso.setFkIdtablaespTipoMonedaMa(new Integer(null));//CommonUtilities.toInt(parametrosSolDesembolso.get("cbxTipoMoneda2")) //cambiar a nulo en la base datos
			desembolso.setSaldoDesembolsoAnterior(CommonUtilities.toDouble(parametrosSolDesembolso.get("txtSaldoDesembolsoAnterior")));
			desembolso.setPeriodo(parametrosSolDesembolso.get("cbxCantPeriodoProy"));
			desembolso.setFkIdtablaespEstDesembolso(CommonUtilities.toInt(parametrosSolDesembolso.get("cbxEstadoDesembolso")));
			desembolso.setVersionDePeriodo(CommonUtilities.toInt(parametrosSolDesembolso.get("txtVersionDesembolso")));
			DatoProyecto datoProyecto = new DatoProyecto();
			datoProyecto.setDatoProyectoID(CommonUtilities.toInt(parametrosSolDesembolso.get("datoProyectoID")));
			desembolso.setDatoProyecto(datoProyecto);
			FuenteFinanciadora fuenteFinanciadora = new FuenteFinanciadora();
			fuenteFinanciadora.setFuenteFinanciadoraID(CommonUtilities.toInt(parametrosSolDesembolso.get("cbxFuenteFinanciadora")));
			desembolso.setFuenteFinanciadora(fuenteFinanciadora);
			CuentaCorriente cuentaCorriente = new CuentaCorriente();
			cuentaCorriente.setCuentaCorrienteID(CommonUtilities.toInt(parametrosSolDesembolso.get("cbxCuentaCorriente")));
			desembolso.setCuentaCorriente(cuentaCorriente);
			//RubroEspecial rubroEspecial = new RubroEspecial();
			//rubroEspecial.setRubroEspecialID(1);/////////////////////////////de donde saco este dato????
			//desembolso.setRubroEspecial(rubroEspecial);
			desembolsoDAO.saveDesembolso(desembolso);
			
			DetalleDesembolso detalleDesembolso;
			CronogramaCostoActividad cronogramaCostoActividad;
			JSONArray jsonDetSolDesembolso = (JSONArray) JSONSerializer.toJSON(parametrosSolDesembolso.get("arrayDetalleSolicitudDesembolso"));	    
		    for(int i=0; i<jsonDetSolDesembolso.size(); i++){
		        /* 
		    	logger.info("obj : "+jsonDetSolDesembolso.getJSONObject(i).toString());
		    	logger.info("obj detalle solicitud desembolso: "+jsonDetSolDesembolso.getJSONObject(i).getDouble("montoSolicitado"));
		    	logger.info("obj detalle solicitud desembolso: "+jsonDetSolDesembolso.getJSONObject(i).getInt("tipoMonedaID"));
		    	logger.info("obj detalle solicitud desembolso: "+jsonDetSolDesembolso.getJSONObject(i).getInt("cronogramaCostoActividadID"));
		    	*/
		    	detalleDesembolso = new DetalleDesembolso();
		    	detalleDesembolso.setDesembolso(desembolso);
		    	detalleDesembolso.setMontoSolicitado(jsonDetSolDesembolso.getJSONObject(i).getDouble("montoSolicitado"));
		    	detalleDesembolso.setFkIdtablaespTipoMonedaMs(jsonDetSolDesembolso.getJSONObject(i).getInt("tipoMonedaID"));
		    	cronogramaCostoActividad = new CronogramaCostoActividad();
		    	cronogramaCostoActividad.setCronogramaCostoActividadID(jsonDetSolDesembolso.getJSONObject(i).getInt("cronogramaCostoActividadID"));
		    	detalleDesembolso.setCronogramaCostoActividadID(cronogramaCostoActividad);
		    	
		    	detalleDesembolsoDAO.saveDetalleDesembolso(detalleDesembolso);
		    	
		    }
		    
		    //grabar tipo cambio
		     DatoPlanOperativo datoPlanOperativo = null;
		     TipoCambio tipoCambio;
		     for(int i=0; i<jsonListaTipoCambio.size(); i++){
		    	 JSONObject obj = new JSONObject();
		    	 obj = jsonListaTipoCambio.getJSONObject(i);
		    	 
			     tipoCambio = new TipoCambio();
			     tipoCambio.setIngresoUsuario(1);
			     tipoCambio.setTipoCambioID(null);
			     tipoCambio.setDatoPlanOperativo(datoPlanOperativo);
			     tipoCambio.setDesembolso(desembolso);
			     tipoCambio.setTipoCambio(obj.getDouble("tipoCambio"));
			      tipoCambio.setFechaTipoCambio(CommonUtilities.getCurrentDate());
			     tipoCambio.setFkIdtablaespTipoMonedaConvertDE(obj.getInt("tipoMonedaConvertDE"));
			     tipoCambio.setFkIdtablaespTipoMonedaConvertA(obj.getInt("tipoMonedaConvertA"));
			     
			     tipoCambioService.createTipoCambio(tipoCambio);		    	 
		     }

		} catch (Throwable e) {
			logger.info("error saveSolicitudDesembolso : "+e);
		}
		
	}
	

	public String getUltimaVersionDesembolsoByDatoProyectoIDByPeriodo(int datoProyectoID,String periodo){
		List <Desembolso> lstDesembolso = desembolsoDAO.findDesembolsoByDatoProyectoIDByPeriodo(datoProyectoID, periodo);
		
		if(lstDesembolso != null && lstDesembolso.size()>0){
			return CommonUtilities.toString(lstDesembolso.get(0).getVersionDePeriodo()+1);
		}else{
			return "1";//primer desembolso para el periodo seleccionado de un proyecto
		}
	}

}
