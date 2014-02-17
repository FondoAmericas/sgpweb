package pe.com.fondam.sgp.web.controller.principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.bean.DatoProyectoBean;
import pe.com.fondam.sgp.core.bean.ProgramaBean2;
import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.ActividadDetallePago;
import pe.com.fondam.sgp.core.domain.AvanceResultadoActividad;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorActividad;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorResultado;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.IndicadorResultado;
import pe.com.fondam.sgp.core.domain.MetaPorActividad;
import pe.com.fondam.sgp.core.domain.ReporteAvanceBeneficiario;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.domain.SolicitaRpRf;
import pe.com.fondam.sgp.core.form.planOperativo.PlanOperativoForm;
import pe.com.fondam.sgp.core.service.ActividadDetallePagoService;
import pe.com.fondam.sgp.core.service.ActividadService;
import pe.com.fondam.sgp.core.service.AvanceResultadoActividadService;
import pe.com.fondam.sgp.core.service.BeneficiariosPorResultadoService;
import pe.com.fondam.sgp.core.service.BienService;
import pe.com.fondam.sgp.core.service.ConclusionService;
import pe.com.fondam.sgp.core.service.CostoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaCostoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorActividadService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorResultadoService;
import pe.com.fondam.sgp.core.service.DatoPlanOperativoService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.DirectivaBeneficiarioService;
import pe.com.fondam.sgp.core.service.EfectoProyectoService;
import pe.com.fondam.sgp.core.service.EvalReporteAvanceLiquidacionGastosService;
import pe.com.fondam.sgp.core.service.EvaluacionFinalService;
import pe.com.fondam.sgp.core.service.IndicadorResultadoService;
import pe.com.fondam.sgp.core.service.InformeFinalService;
import pe.com.fondam.sgp.core.service.InfraestructuraPoService;
import pe.com.fondam.sgp.core.service.LeccionApendidaService;
import pe.com.fondam.sgp.core.service.MaterialProducidoService;
import pe.com.fondam.sgp.core.service.MetaPorActividadService;
import pe.com.fondam.sgp.core.service.OrgBienTranferidoService;
import pe.com.fondam.sgp.core.service.OrganizacionService;
import pe.com.fondam.sgp.core.service.PlanOperativoService;
import pe.com.fondam.sgp.core.service.ProblemaSolucionService;
import pe.com.fondam.sgp.core.service.ProgramaService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaService;
import pe.com.fondam.sgp.core.service.ReporteAvanceBeneficiarioService;
import pe.com.fondam.sgp.core.service.ReporteAvanceService;
import pe.com.fondam.sgp.core.service.ResultadoService;
import pe.com.fondam.sgp.core.service.SolicitaRpRfService;
import pe.com.fondam.sgp.core.service.UbicacionProyectoService;
import pe.com.fondam.sgp.core.util.UtilValidate;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.controller.security.SecurityController;
import pe.com.fondam.sgp.web.session.UserSession;


@Controller
public class AutorizarSolicitudRpRfController {

	//****************** inyecciones ***************************//
	@Resource
	PropuestaTransferenciaService propuestaTransferenciaService;	
	
	@Resource
	InformeFinalService informeFinalService;
	
	@Resource
	DatoProyectoService datoProyectoService;
	
	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	@Resource
	DirectivaBeneficiarioService directivaBeneficiarioService;
	
	@Resource
	OrgBienTranferidoService orgBienTranferidoService; 
	
	@Resource
	BienService bienService;
	
	@Resource
	OrganizacionService organizacionService;
	
	@Resource
	BeneficiariosPorResultadoService beneficiariosPorResultadoService; 
	
	@Resource
	UbicacionProyectoService ubicacionProyectoService;
	
	@Resource
	ConclusionService conclusionService;
	
	@Resource
	LeccionApendidaService leccionApendidaService;
	
	@Resource
	EvaluacionFinalService evaluacionFinalService;
	
	@Resource
	MaterialProducidoService materialProducidoService;
	
	@Resource
	EfectoProyectoService efectoProyectoService;
	
	@Resource
	ProblemaSolucionService problemaSolucionService;
	
	@Resource
	ReporteAvanceService reporteAvanceService;
	
	@Resource
	PlanOperativoService planOperativoService;
	
	@Resource
	ProgramaService programaService;
	
	@Resource
	EvalReporteAvanceLiquidacionGastosService evalReporteAvanceLiquidacionGastosService;
	
	@Resource
	SolicitaRpRfService solicitaRpRfService;
	
	@Resource
	DatoPlanOperativoService datoPlanOperativoService;
	
	@Resource
	InfraestructuraPoService infraestructuraPoService;
	
	@Resource
	ResultadoService resultadoService;
	
	@Resource
	IndicadorResultadoService indicadorResultadoService;
	
	@Resource
	CronogramaMetaPorResultadoService cronogramaMetaPorResultadoService;
	
	@Resource
	ActividadService actividadService;
	
	@Resource
	ReporteAvanceBeneficiarioService reporteAvanceBeneficiarioService;
	
	@Resource
	CostoActividadService costoActividadService;
	
	@Resource
	CronogramaCostoActividadService cronogramaCostoActividadService;
	
	@Resource
	ActividadDetallePagoService actividadDetallePagoService;
	
	@Resource
	MetaPorActividadService metaPorActividadService;
	
	@Resource
	AvanceResultadoActividadService avanceResultadoActividadService;
	
	@Resource
	CronogramaMetaPorActividadService cronogramaMetaPorActividadService;
	
	
	protected final Log logger = LogFactory.getLog(AutorizarSolicitudRpRfController.class);
	Map<String, Object> model = new HashMap<String, Object>();


	//****************** metodos  ***************************//
	@RequestMapping(value = "/principal/showAutorizarSolicitudRpRf.jspx")
	public ModelAndView showAutorizarSolicitudRpRf(HttpServletRequest request,
			HttpServletResponse response) {

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		if (userSession == null) {
			return SecurityController.autenticateErrorRedirect(request);
		}
		model.put("funcionalidadSelect", "showAutorizarSolicitudRpRf.jspx");
		return new ModelAndView("mostrarAutorizarSolicitudRpRf",model);
	}
	
	@RequestMapping(value = "/principal/showCuerpoAutorizarRpRf.jspx")
	public ModelAndView showCuerpoAutorizarRpRf(HttpServletRequest request,
			HttpServletResponse response) {
			
		return new ModelAndView("divCuerpoEvalPropuestaEInforme",model);
	}

	@RequestMapping(value = "/principal/showGrillaAutorizarRpRf.jspx")
	public ModelAndView showGrillaAutorizarRpRf(HttpServletRequest request, HttpServletResponse response) {

		String cbxModalidadFinan = request.getParameter("cbxModalidadFinan");
		logger.info("parametros grilla: cbxModalidadFinan :"+cbxModalidadFinan);
		
		String cbxPrograma = request.getParameter("cbxPrograma");
		logger.info("parametros grilla: cbxPrograma :"+cbxPrograma);
		
		String txtCodProyecto = request.getParameter("txtCodProyecto");
		logger.info("parametros grilla: txtCodProyecto :"+txtCodProyecto);
		
		String txtNomProyecto = request.getParameter("txtNomProyecto");
		logger.info("parametros grilla: txtNomProyecto :"+txtNomProyecto);
		
				
		List<DatoProyectoBean>lstProyectosPorPrograma = evalReporteAvanceLiquidacionGastosService.getLstProyectosPorProgramaConSolicitudRpRf(
				cbxModalidadFinan,cbxPrograma,txtCodProyecto,txtNomProyecto);
		
		model.put("lstProyectosPorPrograma",lstProyectosPorPrograma);
	
		return new ModelAndView("divGrillaAutorizarRpRf",model);
	}

	@RequestMapping(value = "/principal/showProyectoSeleccionado.jspx")
	public ModelAndView showProyectoSeleccionado(
			@RequestParam(required = false, value = "mensaje") String mensaje,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			@RequestParam(required = false, value = "versionPo")String versionPo,
			@RequestParam(required = false, value = "solicitaRpRfId") Integer solicitaRpRfId,
			HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		DatoProyecto datoProyecto = datoProyectoService
				.findDatoProyectoById(datoProyectoId);
		PlanOperativoForm planOperativo = null;
		if (UtilValidate.isNotEmpty(datoProyectoId)) {
			planOperativo = planOperativoService.llenaPlanOperativoTotal(planOperativoService
					.obtenerPlanOperativoByProyectoIDByVersion(datoProyectoId,versionPo));
		}
		
		ProgramaBean2 programaBean = programaService.llenarProgramaBean(datoProyecto
				.getPrograma());
		List<DetalleEstadoCabecera> listDetalleEstadoCabecera = detalleEstadoCabeceraService
				.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estplo");
		List<DetalleEstadoCabecera> listDetalleEstadoCabeceraTemp = new ArrayList<DetalleEstadoCabecera>();
		for (DetalleEstadoCabecera detalleEstadoCabecera : listDetalleEstadoCabecera) {
			if ((detalleEstadoCabecera.getPrefijoEstado().equals("peval"))
					|| (detalleEstadoCabecera.getPrefijoEstado().equals("apro"))
					|| (detalleEstadoCabecera.getPrefijoEstado()
							.equals("recha"))
					|| (detalleEstadoCabecera.getPrefijoEstado().equals("elab"))) {
				listDetalleEstadoCabeceraTemp.add(detalleEstadoCabecera);
			}
		}
		
		//Solicitud de reprogramacion y reformulacion
		SolicitaRpRf solicitaRpRf= solicitaRpRfService.llenaSolicitaRpRfCompleto(solicitaRpRfService.findSolicitaRpRfById(solicitaRpRfId));
		model.put("solicitaRpRf",solicitaRpRf);
		model.put("prefijoEstadoSolicitud",solicitaRpRf.getPrefijoEstadoRpRf());
		
		//UserSession userSession= (UserSession) request.getSession().getAttribute("USER_SESSION");

		//model.put("userSession", userSession);
		model.put("datoProyectoID", datoProyectoId);
		model.put("listaEstadoCabecera", listDetalleEstadoCabeceraTemp);
		model.put("listEstadoSolicitudDesembolso", detalleEstadoCabeceraService
				.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estrprf"));
		model.put("proyectoBean", datoProyecto);
		model.put("planOperativo", planOperativo);
		model.put("programa", programaBean);
		
		return new ModelAndView("mostrarProyectoSeleccionado", model);
	}
	
	@RequestMapping(value = "/principal/grabarEstadoSolicitudRpRf.jspx")
	public void grabarEstadoSolicitudRpRf(
			@RequestParam(required = true, value = "estadoSolicitud") Integer estadoSolicitud,
			@RequestParam(required = true, value = "fechaAprobacion") String fechaAprobacion,
			@RequestParam(required = true, value = "solicitaRpRfId") Integer solicitaRpRfId,
			@RequestParam(required = true, value = "versionPo") Integer versionPo,
			@RequestParam(required = true, value = "datoPlanOperativoId") Integer datoPlanOperativoId,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException {

		//grabo la aprobacion de la solicitud 
		SolicitaRpRf solicitaRpRf = solicitaRpRfService.findSolicitaRpRfById(solicitaRpRfId);

		solicitaRpRf.setFkIdDetalleEstadoCabRpRf(estadoSolicitud);

		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = formateador.parse(fechaAprobacion);
		solicitaRpRf.setFechaAprobacion(fecha);

		solicitaRpRfService.updateSolicitaRpRf(solicitaRpRf);
		
		DetalleEstadoCabecera detalleEstadoCabecera= detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(estadoSolicitud);
		
		if(detalleEstadoCabecera.getPrefijoEstado().equals("apro")){
			DatoPlanOperativo datoPlanOperativo	= datoPlanOperativoService.findDatoPlanOperativoByID(datoPlanOperativoId);
			
			DatoPlanOperativo datoPlanOperativoNEW = llenaDatoPlanOperativoNEW(datoPlanOperativo);
			
			datoPlanOperativoNEW = datoPlanOperativoService.updateDatoPlanOperativo(datoPlanOperativoNEW);
			
			datoPlanOperativo.setEstadoEliminado(0);
			datoPlanOperativoService.updateDatoPlanOperativo(datoPlanOperativo);
			
			//busco la lista de resultados
			List<Resultado> listResultado = resultadoService.findResultadoXDatoPlanOperativoID(datoPlanOperativoId);
		
			for (Resultado resultado : listResultado) {
				Resultado resultado_NEW = new Resultado();
				resultado_NEW= llenaResultadoNEW(resultado,datoPlanOperativoNEW);
				
				resultado_NEW=resultadoService.updateResultado(resultado_NEW);
				
				resultado.setEstadoEliminado(0);
				resultadoService.updateResultado(resultado);
				
				//busco indicador por resultado, cronograma de meta por resultado, beneficiarios por resultado, actividad
				//indicador por resultado
				List<IndicadorResultado> listIndicadorResultado = indicadorResultadoService.findIndicadorResultadoByResultadoID(resultado.getResultadoID());
				for (IndicadorResultado indicadorResultado : listIndicadorResultado) {
					IndicadorResultado indicadorResultado_NEW=new IndicadorResultado();
					indicadorResultado_NEW= llenaIndicadorResultadoNEW(indicadorResultado,resultado_NEW);
					
					indicadorResultado_NEW= indicadorResultadoService.updateIndicadorResultado(indicadorResultado_NEW);
					
					indicadorResultado.setEstadoEliminado(0);
					indicadorResultadoService.updateIndicadorResultado(indicadorResultado);
				}
				
				//cronograma de meta por resultado
				List<CronogramaMetaPorResultado> listCronogramaMetaPorResultado=cronogramaMetaPorResultadoService.findCronogramaMetaPorResultadoByResultadoID(resultado.getResultadoID());
				for (CronogramaMetaPorResultado cronogramaMetaPorResultado : listCronogramaMetaPorResultado) {
					CronogramaMetaPorResultado cronogramaMetaPorResultado_NEW= new CronogramaMetaPorResultado();
					cronogramaMetaPorResultado_NEW= llenaCronogramaMetaPorResultadoNEW(cronogramaMetaPorResultado,resultado_NEW);
					
					cronogramaMetaPorResultado_NEW= cronogramaMetaPorResultadoService.updateCronogramaMetaPorResultado(cronogramaMetaPorResultado_NEW);
					
					cronogramaMetaPorResultado.setEstadoEliminado(0);
					cronogramaMetaPorResultadoService.updateCronogramaMetaPorResultado(cronogramaMetaPorResultado);
				}
				
				//beneficiarios por resultado
				List<BeneficiariosPorResultado> listBeneficiariosPorResultado=beneficiariosPorResultadoService.findBeneficiariosPorResultadoByResultadoID(resultado.getResultadoID());
				for (BeneficiariosPorResultado beneficiariosPorResultado : listBeneficiariosPorResultado) {
					BeneficiariosPorResultado beneficiariosPorResultado_NEW = new BeneficiariosPorResultado();
					beneficiariosPorResultado_NEW = llenaBeneficiariosPorResultadoNEW(beneficiariosPorResultado,resultado_NEW);
					
					beneficiariosPorResultado_NEW = beneficiariosPorResultadoService.updateBeneficiariosPorResultado(beneficiariosPorResultado_NEW);
					
					beneficiariosPorResultado.setEstadoEliminado(0);
					beneficiariosPorResultadoService.updateBeneficiariosPorResultado(beneficiariosPorResultado);
					
					//reporte avance beneficiarios
					List<ReporteAvanceBeneficiario> listReporteAvanceBeneficiario = reporteAvanceBeneficiarioService.findReporteAvanceBeneficiarioXBeneficiariosPorResultadoId(beneficiariosPorResultado.getBeneficiariosPorResultadoID());
					for (ReporteAvanceBeneficiario reporteAvanceBeneficiario : listReporteAvanceBeneficiario) {
						ReporteAvanceBeneficiario reporteAvanceBeneficiario_NEW =new ReporteAvanceBeneficiario();
						reporteAvanceBeneficiario_NEW = llenaReporteAvanceBeneficiarioNEW(reporteAvanceBeneficiario,beneficiariosPorResultado_NEW);
						
						reporteAvanceBeneficiario_NEW = reporteAvanceBeneficiarioService.updateReporteAvanceBeneficiario(reporteAvanceBeneficiario_NEW);
						
						reporteAvanceBeneficiario.setEstadoEliminado(0);
						reporteAvanceBeneficiarioService.updateReporteAvanceBeneficiario(reporteAvanceBeneficiario);
					}
				}
				
				//actividad
				List<Actividad> listActividad= actividadService.findActividadXResultadoId(resultado.getResultadoID());
				for (Actividad actividad : listActividad) {
					Actividad actividad_NEW =  new Actividad();
					actividad_NEW = llenaActividadNEW(actividad,resultado_NEW);
					actividad_NEW = actividadService.updateActividad(actividad_NEW);
					
					actividad.setEstadoEliminado(0);
					actividadService.updateActividad(actividad);
					
					//costo actividad
					List<CostoActividad> listCostoActividad = costoActividadService.findCostoActividadByActividadID(actividad.getActividadID());
					for (CostoActividad costoActividad : listCostoActividad) {
						CostoActividad costoActividad_NEW = new CostoActividad();
						costoActividad_NEW = llenaCostoActividadNEW(costoActividad,actividad_NEW);
						costoActividad_NEW = costoActividadService.updateCostoActividad(costoActividad_NEW);
						
						costoActividad.setEstadoEliminado(0);
						costoActividadService.updateCostoActividad(costoActividad);
						
						//cronograma costo actividad
						List<CronogramaCostoActividad> listCronogramaCostoActividad= cronogramaCostoActividadService.findCronogramaCostoActividadByCostoActividadID(costoActividad.getCostoActividadID());
						for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
							CronogramaCostoActividad cronogramaCostoActividad_NEW = new CronogramaCostoActividad();
							cronogramaCostoActividad_NEW = llenaCronogramaCostoActividadNEW(cronogramaCostoActividad,costoActividad_NEW);
							cronogramaCostoActividad_NEW = cronogramaCostoActividadService.updateCronogramaCostoActividad(cronogramaCostoActividad_NEW);
							
							cronogramaCostoActividad.setEstadoEliminado(0);
							cronogramaCostoActividadService.updateCronogramaCostoActividad(cronogramaCostoActividad);
							
							// actividad detalle pago
							List<ActividadDetallePago> listActividadDetallePago=actividadDetallePagoService.findActividadDetallePagoByCronogramaCostoActividadId(cronogramaCostoActividad.getCronogramaCostoActividadID());
							for (ActividadDetallePago actividadDetallePago : listActividadDetallePago) {
								ActividadDetallePago actividadDetallePago_NEW = new ActividadDetallePago();
								actividadDetallePago_NEW = llenaActividadDetallePagoNEW(actividadDetallePago,cronogramaCostoActividad_NEW);
								actividadDetallePago_NEW = actividadDetallePagoService.updateActividadDetallePago(actividadDetallePago_NEW);
								
								actividadDetallePago.setEstadoEliminado(0);
								actividadDetallePagoService.updateActividadDetallePago(actividadDetallePago);
								
							}
						}						
					}
					
					//meta actividad
					List<MetaPorActividad> listMetaPorActividad = metaPorActividadService.findMetasPorActividadByActividadId(actividad.getActividadID());
					for (MetaPorActividad metaPorActividad : listMetaPorActividad) {
						MetaPorActividad metaPorActividad_NEW = new MetaPorActividad();
						metaPorActividad_NEW = llenaMetaPorActividadNEW(metaPorActividad,actividad_NEW);
						metaPorActividad_NEW = metaPorActividadService.updateMetaPorActividad(metaPorActividad_NEW);
						
						metaPorActividad.setEstadoEliminado(0);
						metaPorActividadService.updateMetaPorActividad(metaPorActividad);
						
						//avance resultados actividad
						List<AvanceResultadoActividad> listAvanceResultadoActividad =llenaAvanceResultadoActividadConReporte( avanceResultadoActividadService.findAvanceResultadoActividadByMetaActividadIdXReporteAprobado(metaPorActividad.getMetaPorActividadID(),FondamConstans.PREFIJO_ESTADO_REPORTE_AVANCE_APROBADO));
						for (AvanceResultadoActividad avanceResultadoActividad : listAvanceResultadoActividad) {
							AvanceResultadoActividad avanceResultadoActividad_NEW = new AvanceResultadoActividad();
							avanceResultadoActividad_NEW = llenaAvanceResultadoActividadNEW(avanceResultadoActividad,metaPorActividad_NEW);
							avanceResultadoActividad_NEW = avanceResultadoActividadService.updateAvanceResultadoActividad(avanceResultadoActividad_NEW);
							
							avanceResultadoActividad.setEstadoEliminado(0);
							avanceResultadoActividadService.updateAvanceResultadoActividad(avanceResultadoActividad);
							
						}	
						
						//cronograma meta por actividad
						List<CronogramaMetaPorActividad> listCronogramaMetaPorActividad = cronogramaMetaPorActividadService.findCronogramaMetaPorActividadXMetaPorActividadId(metaPorActividad.getMetaPorActividadID());
						for (CronogramaMetaPorActividad cronogramaMetaPorActividad : listCronogramaMetaPorActividad) {
							CronogramaMetaPorActividad cronogramaMetaPorActividad_NEW = new CronogramaMetaPorActividad();
							cronogramaMetaPorActividad_NEW = llenaCronogramaMetaPorActividadNEW(cronogramaMetaPorActividad,metaPorActividad_NEW,listAvanceResultadoActividad);
							cronogramaMetaPorActividad_NEW = cronogramaMetaPorActividadService.updateCronogramaMetaPorActividad(cronogramaMetaPorActividad_NEW);
							
							cronogramaMetaPorActividad.setEstadoEliminado(0);
							cronogramaMetaPorActividadService.updateCronogramaMetaPorActividad(cronogramaMetaPorActividad);
							
						}
					}	
				}
			}			
		}
	}


	//******************** listas  ************************//
	private DatoPlanOperativo llenaDatoPlanOperativoNEW(
			DatoPlanOperativo datoPlanOperativo) {

		DatoPlanOperativo datoPlanOperativoNew = new DatoPlanOperativo();
		
		datoPlanOperativoNew.setDatoPlanOperativoID(null);
		datoPlanOperativoNew.setDatoProyecto(datoProyectoService.findDatoProyectoById( datoPlanOperativo.getDatoProyecto().getDatoProyectoID()));
		datoPlanOperativoNew.setEstadoEliminado(1);
		datoPlanOperativoNew.setFkIdDetalleEstadoCabEstadoPlanOper(detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraByPrefijoDetalleEstado(FondamConstans.PREFIJO_ESTADO_PLAN_OPERATIVO,FondamConstans.PREFIJO_ESTADO_PLAN_OPERATIVO_RP_RF).getDetalleEstadoCabeceraID());
		datoPlanOperativoNew.setFkIdtablaespTipoMoneda(datoPlanOperativo.getFkIdtablaespTipoMoneda());
		
		Integer version=Integer.valueOf(datoPlanOperativo.getVersion())+1;
		datoPlanOperativoNew.setVersion(String.valueOf(version));
		
		return datoPlanOperativoNew;
	}

	private Resultado llenaResultadoNEW(Resultado resultado, DatoPlanOperativo datoPlanOperativoNEW) {
		
		Resultado resultado_NEW = new Resultado();
		
		//preparo resultado new para el nuevo PO
		resultado_NEW.setResultadoID(null);
		resultado_NEW.setCodigoResultado(resultado.getCodigoResultado());
		resultado_NEW.setDefinicionResultado(resultado.getDefinicionResultado());
		resultado_NEW.setSupuestoResultado(resultado.getSupuestoResultado());
		resultado_NEW.setMetaResultado(resultado.getMetaResultado());
		resultado_NEW.setFkIdtablaespUnidadMedida(resultado.getFkIdtablaespUnidadMedida());
		resultado_NEW.setDuracionMeses(resultado.getDuracionMeses());
		resultado_NEW.setDatoPlanOperativo(datoPlanOperativoNEW);
		resultado_NEW.setEstadoEliminado(1);
		
		return resultado_NEW;
	}

	private IndicadorResultado llenaIndicadorResultadoNEW(
		IndicadorResultado indicadorResultado, Resultado resultado_NEW) {

		IndicadorResultado indicadorResultado_NEW= new IndicadorResultado();
		
		indicadorResultado_NEW.setIndicadorResultadoID(null);
		indicadorResultado_NEW.setFkIdtablaespTipoIndicadorResultado(indicadorResultado.getFkIdtablaespTipoIndicadorResultado());
		indicadorResultado_NEW.setDefinicionIndicador(indicadorResultado.getDefinicionIndicador());
		indicadorResultado_NEW.setFkIdtablaespUnidadMedida(indicadorResultado.getFkIdtablaespUnidadMedida());
		indicadorResultado_NEW.setMedioVerificacion(indicadorResultado.getMedioVerificacion());
		indicadorResultado_NEW.setSituacionActual(indicadorResultado.getSituacionActual());
		indicadorResultado_NEW.setSituacionFinal(indicadorResultado.getSituacionFinal());
		indicadorResultado_NEW.setLogroAlcanzado(indicadorResultado.getLogroAlcanzado());
		indicadorResultado_NEW.setObservacion(indicadorResultado.getObservacion());
		indicadorResultado_NEW.setResultado(resultado_NEW);
		indicadorResultado_NEW.setMetodoCalculo(indicadorResultado.getMetodoCalculo());
		indicadorResultado_NEW.setEstadoEliminado(1);
	
		return indicadorResultado_NEW;
}

	private CronogramaMetaPorResultado llenaCronogramaMetaPorResultadoNEW(
			CronogramaMetaPorResultado cronogramaMetaPorResultado,
			Resultado resultado_NEW) {

		CronogramaMetaPorResultado cronogramaMetaPorResultadoNEW= new CronogramaMetaPorResultado();
		
		cronogramaMetaPorResultadoNEW.setCronogramaMetaPorResultadoID(null);
		cronogramaMetaPorResultadoNEW.setAvanceMeta(cronogramaMetaPorResultado.getAvanceMeta());
		cronogramaMetaPorResultadoNEW.setPeriodo(cronogramaMetaPorResultado.getPeriodo());
		cronogramaMetaPorResultadoNEW.setResultado(resultado_NEW);
		cronogramaMetaPorResultadoNEW.setEstadoEliminado(1);
		
		return cronogramaMetaPorResultadoNEW;
	}

	private BeneficiariosPorResultado llenaBeneficiariosPorResultadoNEW(
			BeneficiariosPorResultado beneficiariosPorResultado, Resultado resultado_NEW) {
		
		BeneficiariosPorResultado beneficiariosPorResultadoNEW = new BeneficiariosPorResultado();

		beneficiariosPorResultadoNEW.setBeneficiariosPorResultadoID(null);
		beneficiariosPorResultadoNEW.setFkIdtablaespTipoBeneficiario(beneficiariosPorResultado.getFkIdtablaespTipoBeneficiario());
		beneficiariosPorResultadoNEW.setCaracteristicasPoblacion(beneficiariosPorResultado.getCaracteristicasPoblacion());
		beneficiariosPorResultadoNEW.setCantidadProgramado(beneficiariosPorResultado.getCantidadProgramado());
		beneficiariosPorResultadoNEW.setFkidtablaespEstrato(beneficiariosPorResultado.getFkidtablaespEstrato());
		beneficiariosPorResultadoNEW.setDescripcion(beneficiariosPorResultado.getDescripcion());
		beneficiariosPorResultadoNEW.setResultado(resultado_NEW);
		beneficiariosPorResultadoNEW.setUbicacionProyecto(beneficiariosPorResultado.getUbicacionProyecto());
		beneficiariosPorResultadoNEW.setEstadoEliminado(1);
		
		return beneficiariosPorResultadoNEW;
	}

	private ReporteAvanceBeneficiario llenaReporteAvanceBeneficiarioNEW(
			ReporteAvanceBeneficiario reporteAvanceBeneficiario, BeneficiariosPorResultado beneficiariosPorResultado_NEW) {

		ReporteAvanceBeneficiario reporteAvanceBeneficiarioNEW =  new ReporteAvanceBeneficiario();
		
		reporteAvanceBeneficiarioNEW.setReporteAvanceBeneficiarioID(null);
		reporteAvanceBeneficiarioNEW.setCantidadLograda(reporteAvanceBeneficiario.getCantidadLograda());
		reporteAvanceBeneficiarioNEW.setReporteAvance(reporteAvanceBeneficiario.getReporteAvance());
		reporteAvanceBeneficiarioNEW.setBeneficiariosPorResultado(beneficiariosPorResultado_NEW);
		reporteAvanceBeneficiarioNEW.setEstadoEliminado(1);
		
		return reporteAvanceBeneficiarioNEW;
	}
	
	private Actividad llenaActividadNEW(Actividad actividad,
		Resultado resultado_NEW) {
	
		Actividad actividadNEW =  new Actividad();
	actividadNEW.setActividadID(null);	
	actividadNEW.setCodigoActividad(actividad.getCodigoActividad());
	actividadNEW.setFkIdtablaespTipoActividad(actividad.getFkIdtablaespTipoActividad());
	actividadNEW.setNombreActividad(actividad.getNombreActividad());
	actividadNEW.setDescripcionActividad(actividad.getDescripcionActividad());
	actividadNEW.setDuracionMeses(actividad.getDuracionMeses());
	actividadNEW.setFkIdtablaespActividadTransf(actividad.getFkIdtablaespActividadTransf());
	actividadNEW.setResultado(resultado_NEW);
	actividadNEW.setEstadoEliminado(1);
	
		return actividadNEW;
}
	
	private CostoActividad llenaCostoActividadNEW(
			CostoActividad costoActividad, Actividad actividad_NEW) {

		CostoActividad costoActividadNEW = new CostoActividad();
		
		costoActividadNEW.setCostoActividadID(null);
		costoActividadNEW.setMontoGastado(costoActividad.getMontoGastado());
		costoActividadNEW.setFkIdtablaespUnidadMedida(costoActividad.getFkIdtablaespUnidadMedida());
		costoActividadNEW.setCantidadTotal(costoActividad.getCantidadTotal());
		costoActividadNEW.setPrecioUnitario(costoActividad.getPrecioUnitario());
		costoActividadNEW.setFkIdtablaespTipoMoneda(costoActividad.getFkIdtablaespTipoMoneda());
		costoActividadNEW.setDetallePartidaGenerica(costoActividad.getDetallePartidaGenerica());
		costoActividadNEW.setDetalleRubroGenerico(costoActividad.getDetalleRubroGenerico());
		costoActividadNEW.setActividad(actividad_NEW);
		costoActividadNEW.setRubroGenerico(costoActividad.getRubroGenerico());
		costoActividadNEW.setPartidaEspecifica(costoActividad.getPartidaEspecifica());
		costoActividadNEW.setPartidaGenerica(costoActividad.getPartidaGenerica());
		costoActividadNEW.setCategoriaActividad(costoActividad.getCategoriaActividad());
		costoActividadNEW.setEstadoEliminado(1);
		
		return costoActividadNEW;
	}

	private CronogramaCostoActividad llenaCronogramaCostoActividadNEW(
			CronogramaCostoActividad cronogramaCostoActividad,
			CostoActividad costoActividad_NEW) {
		
		CronogramaCostoActividad cronogramaCostoActividadNEW = new CronogramaCostoActividad();
		cronogramaCostoActividadNEW.setCronogramaCostoActividadID(null);
		cronogramaCostoActividadNEW.setCostoActividad(costoActividad_NEW);
		cronogramaCostoActividadNEW.setPeriodo(cronogramaCostoActividad.getPeriodo());
		cronogramaCostoActividadNEW.setEstadoLiquidacion(cronogramaCostoActividad.getEstadoLiquidacion());
		cronogramaCostoActividadNEW.setCantidad(cronogramaCostoActividad.getCantidad());
		cronogramaCostoActividadNEW.setFuenteFinanciadora(cronogramaCostoActividad.getFuenteFinanciadora());
		cronogramaCostoActividadNEW.setDetallePagoLiquidacionID(cronogramaCostoActividad.getDetallePagoLiquidacionID());
		cronogramaCostoActividadNEW.setEjecutado(cronogramaCostoActividad.getEjecutado());
		cronogramaCostoActividadNEW.setEstadoEliminado(1);
		
		return cronogramaCostoActividadNEW;
	}

	private ActividadDetallePago llenaActividadDetallePagoNEW(
			ActividadDetallePago actividadDetallePago,
			CronogramaCostoActividad cronogramaCostoActividad_NEW) {

		ActividadDetallePago actividadDetallePagoNEW=new ActividadDetallePago();
		actividadDetallePagoNEW.setActividadDetallePagoID(null);
		actividadDetallePagoNEW.setFkIdactividades(actividadDetallePago.getFkIdactividades());
		actividadDetallePagoNEW.setPorcentajeMontoTotal(actividadDetallePago.getPorcentajeMontoTotal());
		actividadDetallePagoNEW.setMontoGastado(actividadDetallePago.getMontoGastado());
		actividadDetallePagoNEW.setMontoTotalDeCostoActividad(actividadDetallePago.getMontoTotalDeCostoActividad());
		actividadDetallePagoNEW.setFkIdtablaespTipoMoneda(actividadDetallePago.getFkIdtablaespTipoMoneda());
		actividadDetallePagoNEW.setCronogramaCostoActividad(cronogramaCostoActividad_NEW);
		actividadDetallePagoNEW.setDesembolso(actividadDetallePago.getDesembolso());
		actividadDetallePagoNEW.setDetallePagoLiquidacion(actividadDetallePago.getDetallePagoLiquidacion());
		actividadDetallePagoNEW.setDatoProyectoID(actividadDetallePago.getDatoProyectoID());
		actividadDetallePagoNEW.setEstadoEliminado(1);
		
		return actividadDetallePagoNEW;
	}

	private MetaPorActividad llenaMetaPorActividadNEW(
			MetaPorActividad metaPorActividad, Actividad actividad_NEW) {

		MetaPorActividad metaPorActividadNEW = new MetaPorActividad();
		metaPorActividadNEW.setFkIdtablaespTipoIndicadorActividad(metaPorActividad.getFkIdtablaespTipoIndicadorActividad());
		metaPorActividadNEW.setFkIdtablaespUnidadMedida(metaPorActividad.getFkIdtablaespUnidadMedida());
		metaPorActividadNEW.setCantidadMetaActividad(metaPorActividad.getCantidadMetaActividad());
		metaPorActividadNEW.setCantidadMetaActividadEjecutada(metaPorActividad.getCantidadMetaActividadEjecutada());
		metaPorActividadNEW.setLogroAlcanzado(metaPorActividad.getLogroAlcanzado());
		metaPorActividadNEW.setContribucionProposito(metaPorActividad.getContribucionProposito());
		metaPorActividadNEW.setActividad(actividad_NEW);
		metaPorActividadNEW.setEstadoEliminado(1);
		
		return metaPorActividadNEW;
	}

	private List<AvanceResultadoActividad> llenaAvanceResultadoActividadConReporte(
			List<AvanceResultadoActividad> listAvanceResultadoActividad) {

		for (AvanceResultadoActividad avanceResultadoActividad : listAvanceResultadoActividad) {
			avanceResultadoActividad.setReporteAvance(reporteAvanceService.findReporteAvanceById(avanceResultadoActividad.getReporteAvance().getReporteAvanceID()));
		}
		return listAvanceResultadoActividad;
	}
	
	private AvanceResultadoActividad llenaAvanceResultadoActividadNEW(
			AvanceResultadoActividad avanceResultadoActividad,
			MetaPorActividad metaPorActividad_NEW) {
		
		AvanceResultadoActividad avanceResultadoActividadNEW= new AvanceResultadoActividad();
		avanceResultadoActividadNEW.setAvanceResultadoActividadID(null);
		avanceResultadoActividadNEW.setCantidadAvanceEjecutado(avanceResultadoActividad.getCantidadAvanceEjecutado());
		avanceResultadoActividadNEW.setDescripcionActividad(avanceResultadoActividad.getDescripcionActividad());
		avanceResultadoActividadNEW.setResumenEjecutivoPeriodo(avanceResultadoActividad.getResumenEjecutivoPeriodo());
		avanceResultadoActividadNEW.setElementoVerificacion(avanceResultadoActividad.getElementoVerificacion());
		avanceResultadoActividadNEW.setObservaciones(avanceResultadoActividad.getObservaciones());
		avanceResultadoActividadNEW.setEjecutado(avanceResultadoActividad.getEjecutado());
		avanceResultadoActividadNEW.setReporteAvance(avanceResultadoActividad.getReporteAvance());
		avanceResultadoActividadNEW.setMetaPorActividad(metaPorActividad_NEW);
		avanceResultadoActividadNEW.setEstadoEliminado(1);
		
		return avanceResultadoActividadNEW;
	}
	
	private CronogramaMetaPorActividad llenaCronogramaMetaPorActividadNEW(
			CronogramaMetaPorActividad cronogramaMetaPorActividad,
			MetaPorActividad metaPorActividad_NEW,
			List<AvanceResultadoActividad> listAvanceResultadoActividad) {
		
		CronogramaMetaPorActividad cronogramaMetaPorActividadNEW = new CronogramaMetaPorActividad();
		cronogramaMetaPorActividadNEW.setCronogramaMetaPorActividadID(null);
		cronogramaMetaPorActividadNEW.setPeriodo(cronogramaMetaPorActividad.getPeriodo());
		
		//pregunto si periodo del cronograma mandado esta reportado
		if(cronogramaMetaPorActividad.getPeriodoReportado()==0){
			Integer periodoReportado=cronogramaMetaPorActividad.getPeriodoReportado();
			Integer cantidadMetaActividadProgPorPeriodo = cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo();
			//hago loop a lo reportado para saber si existe el periodo del cronograma enviado para actualizarlo con lo reportado en el avance
			for (AvanceResultadoActividad avanceResultadoActividad : listAvanceResultadoActividad) {
				if(avanceResultadoActividad.getReporteAvance().getPeriodo().equals(cronogramaMetaPorActividad.getPeriodo())){
					periodoReportado=1;
					cantidadMetaActividadProgPorPeriodo=avanceResultadoActividad.getCantidadAvanceEjecutado();
					break;
				}
			}
			cronogramaMetaPorActividadNEW.setPeriodoReportado(periodoReportado);
			cronogramaMetaPorActividadNEW.setCantidadMetaActividadProgPorPeriodo(cantidadMetaActividadProgPorPeriodo);
		//si el periodo reportado es 1, se copia lo mismo
		}else if(cronogramaMetaPorActividad.getPeriodoReportado()==1){
			cronogramaMetaPorActividadNEW.setPeriodoReportado(cronogramaMetaPorActividad.getPeriodoReportado());
			cronogramaMetaPorActividadNEW.setCantidadMetaActividadProgPorPeriodo(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo());
		}
		
		cronogramaMetaPorActividadNEW.setCantidadMetaActividadProgPorPeriodoEjecutada(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodoEjecutada());
		cronogramaMetaPorActividadNEW.setMetaPorActividad(metaPorActividad_NEW);
		cronogramaMetaPorActividadNEW.setEstadoEliminado(cronogramaMetaPorActividad.getEstadoEliminado());
		
		return cronogramaMetaPorActividadNEW;
	}
	
}
