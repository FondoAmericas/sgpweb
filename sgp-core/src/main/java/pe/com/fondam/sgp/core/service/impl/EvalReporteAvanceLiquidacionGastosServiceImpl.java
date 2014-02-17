package pe.com.fondam.sgp.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.DatoProyectoBean;
import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.dao.ActividadDAO;
import pe.com.fondam.sgp.core.dao.ActividadDetallePagoDAO;
import pe.com.fondam.sgp.core.dao.ActivoDAO;
import pe.com.fondam.sgp.core.dao.CronogramaMetaPorActividadDAO;
import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.dao.DesembolsoDAO;
import pe.com.fondam.sgp.core.dao.DetalleEstadoCabeceraDAO;
import pe.com.fondam.sgp.core.dao.DetallePagoLiquidacionDAO;
import pe.com.fondam.sgp.core.dao.LiquidacionGastoDAO;
import pe.com.fondam.sgp.core.dao.MetaPorActividadDAO;
import pe.com.fondam.sgp.core.dao.PagoLiquidacionDAO;
import pe.com.fondam.sgp.core.dao.ProgramaDAO;
import pe.com.fondam.sgp.core.dao.ReporteAvanceDAO;
import pe.com.fondam.sgp.core.dao.TablaEspecificaDAO;
import pe.com.fondam.sgp.core.domain.ActividadDetallePago;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.DetallePagoLiquidacion;
import pe.com.fondam.sgp.core.domain.InformeFinal;
import pe.com.fondam.sgp.core.domain.LiquidacionGasto;
import pe.com.fondam.sgp.core.domain.PagoLiquidacion;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.PropuestaTransferencia;
import pe.com.fondam.sgp.core.domain.ReporteAvance;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.service.ActividadDetallePagoService;
import pe.com.fondam.sgp.core.service.AvanceResultadoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaCostoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorActividadService;
import pe.com.fondam.sgp.core.service.DetallePagoLiquidacionService;
import pe.com.fondam.sgp.core.service.EvalReporteAvanceLiquidacionGastosService;
import pe.com.fondam.sgp.core.service.InformeFinalService;
import pe.com.fondam.sgp.core.service.LiquidacionGastoService;
import pe.com.fondam.sgp.core.service.ObservacionService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaService;
import pe.com.fondam.sgp.core.service.SolicitaRpRfService;
import pe.com.fondam.sgp.core.util.CommonUtilities;


@Service
public class EvalReporteAvanceLiquidacionGastosServiceImpl implements EvalReporteAvanceLiquidacionGastosService{
	
	//************************** Inyecciones *****************************//
	
	@Resource
	TablaEspecificaDAO tablaEspecificaDAO;
	
	@Resource
	ProgramaDAO programaDAO;
	
	@Resource
	DatoProyectoDAO datoProyectoDAO;
	
	@Resource
	LiquidacionGastoDAO liquidacionGastoDAO;
	
	@Resource
	ReporteAvanceDAO reporteAvanceDAO;
	
	@Resource
	DetalleEstadoCabeceraDAO detalleEstadoCabeceraDAO;
	
	@Resource
	AvanceResultadoActividadService avanceResultadoActividadService;
	
	@Resource
	CronogramaMetaPorActividadService cronogramaMetaPorActividadService;
	
	@Resource
	CronogramaMetaPorActividadDAO cronogramaMetaPorActividadDAO;
	
	@Resource 
	MetaPorActividadDAO metaPorActividadDAO;
	
	@Resource
	PagoLiquidacionDAO pagoLiquidacionDAO;

	@Resource
	DetallePagoLiquidacionDAO detallePagoLiquidacionDAO;
	
	@Resource
	DetallePagoLiquidacionService detallePagoLiquidacionService;
	
	@Resource
	ActivoDAO activoDAO;
	
	@Resource
	ActividadDAO actividadDAO;
	
	@Resource
	ActividadDetallePagoDAO actividadDetallePagoDAO;
	
	@Resource
	DesembolsoDAO desembolsoDAO;
	
	@Resource
	PropuestaTransferenciaService propuestaTransferenciaService;
	
	@Resource
	InformeFinalService informeFinalService;
	
	@Resource
	SolicitaRpRfService solicitaRpRfService;
	
	@Resource
	LiquidacionGastoService liquidacionGastoService;

	@Resource
	CronogramaCostoActividadService cronogramaCostoActividadService;
	
	@Resource
	ActividadDetallePagoService actividadDetallePagoService;
	
	@Resource
	ObservacionService observacionService;
	//************************** metodos *****************************//
	
	public String getListModalidadFinanciamientoToArrayJson(){
		List<TablaEspecifica> listModalidadFinan;
		listModalidadFinan = tablaEspecificaDAO.findTablaEspecificasByTablaGeneralId(FondamConstans.INT_MODALIDAD_FINANCIAMIENTO);
		
		JSONArray jsonArrayModFinan = new JSONArray();
		JSONObject jsonObjectModFinan = null;
		
		for(int i=0; i<listModalidadFinan.size(); i++){
			jsonObjectModFinan = new JSONObject();
			  
			jsonObjectModFinan.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, listModalidadFinan.get(i).getTablaEspecificaID() != null ? 
					listModalidadFinan.get(i).getTablaEspecificaID() : FondamConstans.EMPTY_STRING );
			
			jsonObjectModFinan.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, listModalidadFinan.get(i).getDescripcionCabecera() != null ?
					listModalidadFinan.get(i).getDescripcionCabecera() : FondamConstans.EMPTY_STRING  );
			
			jsonArrayModFinan.add(jsonObjectModFinan);
		}
		
		return jsonArrayModFinan.toString();
		
	}
	
	public String getListProgramaByModalidadFinanToArrayJson(int modalidadFinan){
		List <Programa> listPrograma;
		
		listPrograma = programaDAO.findProgramaByModFinan(modalidadFinan);
		
		JSONArray jsonArrayPrograma = new JSONArray();
		JSONObject jsonObjectPrograma = null;
		
		for(int i=0; i<listPrograma.size(); i++){
			
			jsonObjectPrograma = new JSONObject();
			
			jsonObjectPrograma.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, listPrograma.get(i).getProgramaID());
			
			jsonObjectPrograma.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, listPrograma.get(i).getNombrePrograma());
			
			jsonArrayPrograma.add(jsonObjectPrograma);
		}
		
		return jsonArrayPrograma.toString();
		
	}
	
	public List<DatoProyectoBean> getLstProyectosPorProgramaConReporteAvanceLiquidacionGastos(String modalidadFinanID, String ProgramaID,
			String codProyecto, String nomProyecto){
		
		List<DatoProyectoBean> lstDatoProyBean = new ArrayList<DatoProyectoBean>();
		DatoProyectoBean datoProyectoBean;
		List<DatoProyecto> lstDatoProy = new ArrayList<DatoProyecto>();
		
		if(CommonUtilities.isNullOrBlank(codProyecto) && CommonUtilities.isNullOrBlank(nomProyecto)){//buscar por ProgramaID
			lstDatoProy = datoProyectoDAO.findDatoProyectoByProgramaID(CommonUtilities.toInt(ProgramaID));
		}else if(CommonUtilities.isNotNullOrBlank(codProyecto) && CommonUtilities.isNullOrBlank(nomProyecto)){//buscar por ProgramaID y codProyecto 
			lstDatoProy = datoProyectoDAO.findDatoProyectoByProgramaIDbyCodProyecto(CommonUtilities.toInt(ProgramaID), codProyecto);
		}else if(CommonUtilities.isNullOrBlank(codProyecto) && CommonUtilities.isNotNullOrBlank(nomProyecto)){//buscar por ProgramaID y nomProyecto
			lstDatoProy = datoProyectoDAO.findDatoProyectoByProgramaIDbyNomProy(CommonUtilities.toInt(ProgramaID), nomProyecto);
		}
		
		for (DatoProyecto datoProyecto : lstDatoProy) {
			datoProyectoBean = new DatoProyectoBean();
			datoProyectoBean.setDatoProyectoID(datoProyecto.getDatoProyectoID());
			datoProyectoBean.setNombreProyecto(datoProyecto.getNombreProyecto());
			datoProyectoBean.setCodigoProyecto(datoProyecto.getCodigoProyecto());
			datoProyectoBean.setDuracionProyecto(datoProyecto.getDuracionProyecto());
			datoProyectoBean.setCantidadPeriodo(datoProyecto.getCantidadPeriodo());
			datoProyectoBean.setCantLiqPorEvaluar(
			liquidacionGastoDAO.getCantidadLiquidacionGastosByProyectoIdByEstadoLiqGenerada(datoProyecto.getDatoProyectoID())
			);
			datoProyectoBean.setCantReportesPorEval(			
			reporteAvanceDAO.getCantidadReporteAvanceByProyectoIdByEstadoRepAvanceGenerado(datoProyecto.getDatoProyectoID())
			);
			
			List<ReporteAvance> lstReporteAvance = reporteAvanceDAO.findReporteAvanceByProyectoIdByEstadoRepAvanceGenerado(datoProyecto.getDatoProyectoID());
			for(ReporteAvance reporteAvance : lstReporteAvance){
				reporteAvance.setEstRepAvanceDesc(
						(detalleEstadoCabeceraDAO.findDetalleEstadoCabeceraById(reporteAvance.getFkIdDetalleEstadoCabEstRepAvance())).getDescripEstado()
						);
				reporteAvance.setRepAvanceDesc("Reporte de Avance "+reporteAvance.getPeriodo());
			}
						
			datoProyectoBean.setLstRepAvance(lstReporteAvance);
			
		    List<LiquidacionGasto> lstLiquidacionGasto = liquidacionGastoDAO.findLiquidacionGastosByProyectoIdByEstadoLiqGenerada(datoProyecto.getDatoProyectoID());
		    for(LiquidacionGasto liquidacionGasto : lstLiquidacionGasto){
		    	liquidacionGasto.setEstLiqGastoDesc(
		    			(detalleEstadoCabeceraDAO.findDetalleEstadoCabeceraById(liquidacionGasto.getFkIdDetalleEstadoCabEstLiqGasto())).getDescripEstado()
		    			);
		    	liquidacionGasto.setLiqGastoDesc("Liquidacion Gasto "+liquidacionGasto.getPeriodo()+"."+liquidacionGasto.getNumLiqParcial());
		    }
		    
		    datoProyectoBean.setLstLiqGasto(lstLiquidacionGasto);
			lstDatoProyBean.add(datoProyectoBean);
			
		}
		return lstDatoProyBean;
	}
	
	public ReporteAvance getReporteAvanceById(Integer reporteAvanceId) {
		
		
		ReporteAvance objReporteAvance = reporteAvanceDAO.findReporteAvanceById(reporteAvanceId);
		
		objReporteAvance.setEstRepAvanceDesc(
				(detalleEstadoCabeceraDAO.findDetalleEstadoCabeceraById(objReporteAvance.getFkIdDetalleEstadoCabEstRepAvance())).getDescripEstado()		
		);
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fechaInicio = formateador.format(objReporteAvance.getFechaInicio());
		String fechaFin = formateador.format(objReporteAvance.getFechaFin());
		
		objReporteAvance.setFechaInicioString(fechaInicio);
		objReporteAvance.setFechaFinString(fechaFin);
		objReporteAvance.setCantObservacionesRelevantes(observacionService.findObservacionesRelevatesAlDocumento(objReporteAvance.getDatoProyecto().getDatoProyectoID(),objReporteAvance.getReporteAvanceID(),FondamConstans.TABLA_CLASE_NOMBRE_REPORTE_AVANCE));
		
		return objReporteAvance;
	}
	
	public List<PagoLiquidacion> findPagoLiquidacionByLiquidacionGastoID(Integer liquidacionGastoID){
		
		return pagoLiquidacionDAO.findPagoLiquidacionByLiquidacionGastoID(liquidacionGastoID);
	}
	
	public List<PagoLiquidacion> serializarListPagoLiquidacion(List<PagoLiquidacion> lstPagoLiquidacion){
		
		for (PagoLiquidacion pagoLiquidacion : lstPagoLiquidacion) {
			pagoLiquidacion.setTipoMonedaDesc(tablaEspecificaDAO.findTablaEspecificaById(pagoLiquidacion.getFkIdtablaespTipoMoneda()).getDescripcionCabecera());
			pagoLiquidacion.setTipoComprobantePagoDesc(tablaEspecificaDAO.findTablaEspecificaById(pagoLiquidacion.getFkIdtablaespTipoComprobantePago()).getDescripcionCabecera());
			pagoLiquidacion.setLstDetallePagoLiquidacion(detallePagoLiquidacionService.llenaDetallePagoLiquidacionCompleto(detallePagoLiquidacionDAO.findDetallePagoLiquidacionByPagoLiquidacionID(pagoLiquidacion.getPagoLiquidacionID())));
			
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			pagoLiquidacion.setFechaEmisionString(formateador.format(pagoLiquidacion.getFechaEmision()));
			
		}
		
		return lstPagoLiquidacion;
	}
	
	public PagoLiquidacion serializarEstructuraPago(int pagoLiquidacionID){
		
		TablaEspecifica tablaEspecifica;
		PagoLiquidacion pagoLiquidacion =  pagoLiquidacionDAO.findPagoLiquidacionById(pagoLiquidacionID);
		
		//obtener: tipoMonedaDesc , tipoComprobantePagoDesc , bancoDesc , chekeCobradoDesc (PagoLiquidacion)
		pagoLiquidacion.setTipoMonedaDesc(
				(tablaEspecificaDAO.findTablaEspecificaById(pagoLiquidacion.getFkIdtablaespTipoMoneda())).getDescripcionCabecera()
		);		
		pagoLiquidacion.setTipoComprobantePagoDesc(
				(tablaEspecificaDAO.findTablaEspecificaById(pagoLiquidacion.getFkIdtablaespTipoComprobantePago())).getDescripcionCabecera()
		);
		
		tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(
				( desembolsoDAO.findDesembolsoById(pagoLiquidacion.getDesembolsoID()) ).getCuentaCorriente().getFkIdtablaespBanco()
		);
		
		pagoLiquidacion.setBancoDesc(
				tablaEspecifica.getDescripcionCabecera()
		);
		
		if(pagoLiquidacion.getChequeCobrado() == 1){
			pagoLiquidacion.setChekeCobradoDesc("si");
		}else{
			pagoLiquidacion.setChekeCobradoDesc("no");
		}
		
		
		List<DetallePagoLiquidacion> lstDetallePagoLiquidacion = null;		
		lstDetallePagoLiquidacion = detallePagoLiquidacionDAO.findDetallePagoLiquidacionByPagoLiquidacionID(pagoLiquidacion.getPagoLiquidacionID());
		
		//obtener: unidadMedidaDesc , estadoPagoLiquidacionDesc , activoDesc , lstActividadDetallePago (lstDetallePagoLiquidacion)
		for(int i=0; i<lstDetallePagoLiquidacion.size(); i++){
			lstDetallePagoLiquidacion.get(i).setUnidadMedidaDesc(
					(tablaEspecificaDAO.findTablaEspecificaById(lstDetallePagoLiquidacion.get(i).getFkIdtablaespUnidadMedida())).getDescripcionCabecera()		
			);
			
			lstDetallePagoLiquidacion.get(i).setEstadoPagoLiquidacionDesc(
					(detalleEstadoCabeceraDAO.findDetalleEstadoCabeceraById(lstDetallePagoLiquidacion.get(i).getFkIdDetalleEstadoCabEstadoPagoLiq())
					).getDescripEstado()
			);
			lstDetallePagoLiquidacion.get(i).setActivoDesc(
					(activoDAO.findActivoById(lstDetallePagoLiquidacion.get(i).getActivo().getActivoID())).getDescripcionActivo()
			);
			
			//llenar lista de actividad detalle pago
			List <ActividadDetallePago> lstActividadDetallePago = actividadDetallePagoDAO.
			              findActividadDetallePagoByDetallePagoLiquidacionID(lstDetallePagoLiquidacion.get(i).getDetallePagoLiquidacionID());
			
			//obtener: actividadDesc , tipoMonedaDesc
			for(int j=0; j<lstActividadDetallePago.size(); j++){
				lstActividadDetallePago.get(j).setActividadDesc(
						(actividadDAO.findActividadById(lstActividadDetallePago.get(j).getFkIdactividades())).getNombreActividad()
				);
				
				lstActividadDetallePago.get(j).setTipoMonedaDesc(
						(tablaEspecificaDAO.findTablaEspecificaById(lstActividadDetallePago.get(j).getFkIdtablaespTipoMoneda())).getDescripcionCabecera()
				);
			
			}
			
			lstDetallePagoLiquidacion.get(i).setLstActividadDetallePago(lstActividadDetallePago);
			
		}
				
		pagoLiquidacion.setLstDetallePagoLiquidacion(lstDetallePagoLiquidacion);
		
		return pagoLiquidacion;
	}
	
	public void saveEvalRepAvance(ReporteAvance reporteAvance,int estadoRepAvance){
		
		reporteAvance.setFkIdDetalleEstadoCabEstRepAvance(estadoRepAvance);
		reporteAvanceDAO.updateReporteAvance(reporteAvance);
		
	}
	
	public void saveEvalLiqGasto(LiquidacionGasto liquidacionGasto,	int estadoLiqGasto){
		
		String estadoPrefijo= detalleEstadoCabeceraDAO.findDetalleEstadoCabeceraById(estadoLiqGasto).getPrefijoEstado();
		if(estadoPrefijo.equals(FondamConstans.PREFIJO_ESTADO_LIQUIDACION_GASTO_APROBADO)){
			//pongo ejecutado a los cronograma costo actividad
			marcarEjecutadoEnCronogramaCostoActividad(liquidacionGasto);
		}
		liquidacionGasto.setFkIdDetalleEstadoCabEstLiqGasto(estadoLiqGasto);
		liquidacionGastoDAO.updateLiquidacionGasto(liquidacionGasto);
		
	}

	private void marcarEjecutadoEnCronogramaCostoActividad(
			LiquidacionGasto liquidacionGasto) {
		
		liquidacionGasto= liquidacionGastoService.llenaLiquidacionGastoCompletoConListas(liquidacionGasto);
	
		List<CronogramaCostoActividad> listCronogramaCostoActividad= new ArrayList<CronogramaCostoActividad>();
		
		//lleno la lista de cronograma costo actividad que se presentan en esta liquidacion  
		for (PagoLiquidacion pagoLiquidacion : liquidacionGasto.getListPagoLiquidacion()) {
			for (DetallePagoLiquidacion detallePagoLiquidacion : pagoLiquidacion.getLstDetallePagoLiquidacion()) {
				for (ActividadDetallePago actividadDetallePago : detallePagoLiquidacion.getLstActividadDetallePago()) {
					listCronogramaCostoActividad.add(cronogramaCostoActividadService.llenaCronogramaCostoActividadCompleto( cronogramaCostoActividadService.findCronogramaCostoActividadById(actividadDetallePago.getCronogramaCostoActividad().getCronogramaCostoActividadID())));
				}
			}
		}
		
		//pongo ejecutado en cronograma costo actividad dependiendo de la actividad detalle pago (montos)
		for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
			double montoCronogramaCostoActividad= cronogramaCostoActividad.getCantidad()*cronogramaCostoActividad.getCostoActividad().getPrecioUnitario();
			
			List<ActividadDetallePago> listActividadDetallePago = actividadDetallePagoService.findActividadDetallePagoByCronogramaCostoActividadId(cronogramaCostoActividad.getCronogramaCostoActividadID());
			double montoLiquidado=0;
			for (ActividadDetallePago actividadDetallePago : listActividadDetallePago) {
				montoLiquidado+=actividadDetallePago.getMontoGastado();
			}
			
			if(montoLiquidado>=montoCronogramaCostoActividad){
				cronogramaCostoActividad.setEjecutado(1);
				cronogramaCostoActividadService.updateCronogramaCostoActividad(cronogramaCostoActividad);
			}
		}
	}

	@Override
	public List<DatoProyectoBean> getLstProyectosPorProgramaConPropuestaEInforme(String modalidadFinanID, String ProgramaID,
			String codProyecto, String nomProyecto) {
		
		List<DatoProyectoBean> lstDatoProyBean = new ArrayList<DatoProyectoBean>();
		DatoProyectoBean datoProyectoBean;
		List<DatoProyecto> lstDatoProy = new ArrayList<DatoProyecto>();
		
		if(CommonUtilities.isNullOrBlank(codProyecto) && CommonUtilities.isNullOrBlank(nomProyecto)){//buscar por ProgramaID
			lstDatoProy = datoProyectoDAO.findDatoProyectoByProgramaID(CommonUtilities.toInt(ProgramaID));
		}else if(CommonUtilities.isNotNullOrBlank(codProyecto) && CommonUtilities.isNullOrBlank(nomProyecto)){//buscar por ProgramaID y codProyecto 
			lstDatoProy = datoProyectoDAO.findDatoProyectoByProgramaIDbyCodProyecto(CommonUtilities.toInt(ProgramaID), codProyecto);
		}else if(CommonUtilities.isNullOrBlank(codProyecto) && CommonUtilities.isNotNullOrBlank(nomProyecto)){//buscar por ProgramaID y nomProyecto
			lstDatoProy = datoProyectoDAO.findDatoProyectoByProgramaIDbyNomProy(CommonUtilities.toInt(ProgramaID), nomProyecto);
		}
		
		for (DatoProyecto datoProyecto : lstDatoProy) {
			datoProyectoBean = new DatoProyectoBean();
			datoProyectoBean.setDatoProyectoID(datoProyecto.getDatoProyectoID());
			datoProyectoBean.setNombreProyecto(datoProyecto.getNombreProyecto());
			datoProyectoBean.setCodigoProyecto(datoProyecto.getCodigoProyecto());
			datoProyectoBean.setDuracionProyecto(datoProyecto.getDuracionProyecto());
			datoProyectoBean.setCantidadPeriodo(datoProyecto.getCantidadPeriodo());
			
			PropuestaTransferencia propuestaTransferencia=propuestaTransferenciaService.findPropuestaTransferenciaByDatoProyectoId(datoProyecto.getDatoProyectoID());
			datoProyectoBean.setPropuestaTransferencia(propuestaTransferencia);
			datoProyectoBean.setCantPropuestaTransferencia(propuestaTransferencia!=null?1:0);
			
			InformeFinal informeFinal=informeFinalService.findInformeFinalByDatoProyectoId(datoProyecto.getDatoProyectoID());
			datoProyectoBean.setInformeFinal(informeFinal);
			datoProyectoBean.setCantInformeFinal(informeFinal!=null?1:0);

		lstDatoProyBean.add(datoProyectoBean);
			
		}
		return lstDatoProyBean;
	
	}	
	
	@Override
	public List<DatoProyectoBean> getLstProyectosPorProgramaConSolicitudRpRf(String modalidadFinanID, String ProgramaID,
			String codProyecto, String nomProyecto) {
		
		List<DatoProyectoBean> lstDatoProyBean = new ArrayList<DatoProyectoBean>();
		DatoProyectoBean datoProyectoBean;
		List<DatoProyecto> lstDatoProy = new ArrayList<DatoProyecto>();
		
		if(CommonUtilities.isNullOrBlank(codProyecto) && CommonUtilities.isNullOrBlank(nomProyecto)){//buscar por ProgramaID
			lstDatoProy = datoProyectoDAO.findDatoProyectoByProgramaID(CommonUtilities.toInt(ProgramaID));
		}else if(CommonUtilities.isNotNullOrBlank(codProyecto) && CommonUtilities.isNullOrBlank(nomProyecto)){//buscar por ProgramaID y codProyecto 
			lstDatoProy = datoProyectoDAO.findDatoProyectoByProgramaIDbyCodProyecto(CommonUtilities.toInt(ProgramaID), codProyecto);
		}else if(CommonUtilities.isNullOrBlank(codProyecto) && CommonUtilities.isNotNullOrBlank(nomProyecto)){//buscar por ProgramaID y nomProyecto
			lstDatoProy = datoProyectoDAO.findDatoProyectoByProgramaIDbyNomProy(CommonUtilities.toInt(ProgramaID), nomProyecto);
		}
		
		for (DatoProyecto datoProyecto : lstDatoProy) {
			datoProyectoBean = new DatoProyectoBean();
			datoProyectoBean.setDatoProyectoID(datoProyecto.getDatoProyectoID());
			datoProyectoBean.setNombreProyecto(datoProyecto.getNombreProyecto());
			datoProyectoBean.setCodigoProyecto(datoProyecto.getCodigoProyecto());
			datoProyectoBean.setDuracionProyecto(datoProyecto.getDuracionProyecto());
			datoProyectoBean.setCantidadPeriodo(datoProyecto.getCantidadPeriodo());
			datoProyectoBean.setListSolicitaRpRf(solicitaRpRfService.llenaListSolicitaRpRfCompleto(solicitaRpRfService.findSolicitaRpRfByDatoProyectoId(datoProyecto.getDatoProyectoID())));

		lstDatoProyBean.add(datoProyectoBean);
			
		}
		return lstDatoProyBean;
	
	}	
}
