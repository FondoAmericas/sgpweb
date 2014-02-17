package pe.com.fondam.sgp.web.controller.principal;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.bean.BeneficiariosPorResultadoBean;
import pe.com.fondam.sgp.core.bean.CargaFormularioBean;
import pe.com.fondam.sgp.core.bean.DatoProyectoBean;
import pe.com.fondam.sgp.core.bean.ProblemaSolucionBean;
import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.AvanceResultadoActividad;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.CompromisoPago;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.IngresoPropio;
import pe.com.fondam.sgp.core.domain.LiquidacionGasto;
import pe.com.fondam.sgp.core.domain.PagoLiquidacion;
import pe.com.fondam.sgp.core.domain.PersonalTecnicoAdministrativo;
import pe.com.fondam.sgp.core.domain.ReporteAvance;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.UbicacionProyecto;
import pe.com.fondam.sgp.core.service.ApreciacionResultadoService;
import pe.com.fondam.sgp.core.service.AvanceResultadoActividadService;
import pe.com.fondam.sgp.core.service.BeneficiariosPorResultadoService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.EvalReporteAvanceLiquidacionGastosService;
import pe.com.fondam.sgp.core.service.LiquidacionGastoService;
import pe.com.fondam.sgp.core.service.PersonalTecnicoAdministrativoService;
import pe.com.fondam.sgp.core.service.ProblemaSolucionService;
import pe.com.fondam.sgp.core.service.ReporteAvanceService;
import pe.com.fondam.sgp.core.service.UbicacionProyectoService;
import pe.com.fondam.sgp.core.service.UtilService;
import pe.com.fondam.sgp.core.util.CommonUtilities;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.controller.security.SecurityController;
import pe.com.fondam.sgp.web.session.UserSession;


@Controller
public class EvalReporteAvanceLiquidacionGastosController {

	@Resource
	private UtilService utilService;
	
	@Resource
	private ReporteAvanceService reporteAvanceService;
	
	@Resource
	private ProblemaSolucionService problemaSolucionService;
	
	@Resource
	private AvanceResultadoActividadService avanceResultadoActividadService;
	
	@Resource
	private ApreciacionResultadoService apreciacionResultadoService;
	
	@Resource
	private UbicacionProyectoService ubicacionProyectoService;
	
	@Resource
	private BeneficiariosPorResultadoService beneficiariosPorResultadoService;
	
	@Resource
	private PersonalTecnicoAdministrativoService personalTecnicoAdministrativoService;
	
	@Resource
	private LiquidacionGastoService liquidacionGastoService;
	
	@Resource
	private DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	@Resource
	DatoProyectoService datoProyectoService;
	
	
	@Resource
	private EvalReporteAvanceLiquidacionGastosService evalReporteAvanceLiquidacionGastosService;
	
	protected final Log logger = LogFactory.getLog(EvalReporteAvanceLiquidacionGastosController.class);
	/*private UserSession userSession;
	private DatoUsuario datoUsuario=new DatoUsuario();
	private DatoProyecto datoProyecto=new DatoProyecto();
	private DatoPlanOperativo datoPlanOperativo = new DatoPlanOperativo();
	private Resultado resultado = new Resultado();
	private String perfilUsuario;
	private String rubroEspecialID;*/
	Map<String, Object> model = new HashMap<String, Object>();
	ReporteAvance objReporteAvanceInner;
	LiquidacionGasto objLiquidacionGastoInner;
	
	
	
	@RequestMapping(value = "/principal/showEvalLiqGastosInformeAvance.jspx")
	public ModelAndView showReporteAvance(HttpServletRequest request,
			HttpServletResponse response) {

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		if (userSession == null) {
			return SecurityController.autenticateErrorRedirect(request);
		}
		model.put("funcionalidadSelect", "showEvalLiqGastosInformeAvance.jspx");
		return new ModelAndView("mostrarEvalReporteAvanceLiquidacionGastos",model);
	}
	
	/*
	@RequestMapping(value = "/principal/showEvalLiqGastosInformeAvance.jspx")
	public ModelAndView showEvalReporteAvanceLiquidacionGastos(HttpServletRequest request) {
			
		try {
			userSession = (UserSession) request.getSession().getAttribute(SgpWebConstants.SESSION_USER);
			if (userSession == null) {
				return SecurityController.autenticateErrorRedirect(request);
			}
			
			UserSession userSession = (UserSession) request.getSession().getAttribute(SgpWebConstants.SESSION_USER);
			if (userSession == null) {
				return SecurityController.autenticateErrorRedirect(request);
			}
			
			datoUsuario=new DatoUsuario();
			datoUsuario=crearProgramaService.findDatoUsuarioById(userSession.getDatoUsuarioID());   
			logger.info("datoUsuario.getDatoProyectoID() : "+datoUsuario.getDatoProyectoID());
			
			//datos del proyecto
			datoProyecto=evaluarService.findDatoProyectoById(datoUsuario.getDatoProyectoID());
			logger.info("datoProyecto.getDatoProyectoID() : "+datoProyecto.getDatoProyectoID());

			//datos del plan operativo
			//datoPlanOperativo = solicitarDesembolsoService.findDatoPlanOperativoByDatoProyectoID2(datoUsuario.getDatoProyectoID());
			//logger.info("datoPlanOperativo.getDatoPlanOperativoID() : "+datoPlanOperativo.getDatoPlanOperativoID());
			
			//perfil de usuario
			//perfilUsuario = solicitarDesembolsoService.getPerfilUsuario(datoUsuario.getDatoUsuarioID());
			//logger.info("perfilUsuario : "+perfilUsuario);
			
			
			//model.put("perfilUsuario",perfilUsuario);
			//model.put("periodoProyecto",datoProyecto.getCantidadPeriodo());
			

		} catch (Exception e) {
			logger.error("Error en showEvalLiqGastosInformeAvance.jspx");
		}
		
		return new ModelAndView("mostrarEvalReporteAvanceLiquidacionGastos",model);
		
	}
	*/
	
	@RequestMapping(value = "/principal/cargaComboModalidadFinan")
	public void cargaComboModalidadFinan(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String jsonArrayModalidadFinanciera = evalReporteAvanceLiquidacionGastosService.getListModalidadFinanciamientoToArrayJson();

		logger.info("data combobox modalidad financiamiento: "+jsonArrayModalidadFinanciera);
		
        response.getWriter().write(jsonArrayModalidadFinanciera);
	}
	
	@RequestMapping(value = "/principal/cargarCBXPrograma")
	public void cargarCBXActividad(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String idModalidadFinan = request.getParameter("idModalidadFinan");
		String jsonArrayProgramas;
		if(CommonUtilities.isNotNullOrBlank(idModalidadFinan)){
			jsonArrayProgramas = evalReporteAvanceLiquidacionGastosService.getListProgramaByModalidadFinanToArrayJson(CommonUtilities.toInt(idModalidadFinan));		
		}else{
			jsonArrayProgramas = "[]";
		}

		logger.info("data combobox programa: "+jsonArrayProgramas);
		response.getWriter().write(jsonArrayProgramas);
	} 
	
	@RequestMapping(value = "/principal/showCuerpoEvalReporteAvanLiqGastos")
	public ModelAndView showCuerpoReporteAvance(HttpServletRequest request,
			HttpServletResponse response) {

		/*try {
			userSession = (UserSession) request.getSession().getAttribute(SgpWebConstants.SESSION_USER);
			if (userSession == null) {
				return SecurityController.autenticateErrorRedirect(request);
			}
			
			datoUsuario=new DatoUsuario();
			datoUsuario=crearProgramaService.findDatoUsuarioById(userSession.getDatoUsuarioID());   
			logger.info("datoUsuario.getDatoProyectoID() : "+datoUsuario.getDatoProyectoID());
			
			//datos del proyecto
			datoProyecto=evaluarService.findDatoProyectoById(datoUsuario.getDatoProyectoID());
			logger.info("datoProyecto.getDatoProyectoID() : "+datoProyecto.getDatoProyectoID());

			//datos del plan operativo
			//datoPlanOperativo = solicitarDesembolsoService.findDatoPlanOperativoByDatoProyectoID2(datoUsuario.getDatoProyectoID());
			//logger.info("datoPlanOperativo.getDatoPlanOperativoID() : "+datoPlanOperativo.getDatoPlanOperativoID());
			
			//perfil de usuario
			//perfilUsuario = solicitarDesembolsoService.getPerfilUsuario(datoUsuario.getDatoUsuarioID());
			//logger.info("perfilUsuario : "+perfilUsuario);
			
			
			//model.put("perfilUsuario",perfilUsuario);
			//model.put("periodoProyecto",datoProyecto.getCantidadPeriodo());
			

		} catch (Exception e) {
			logger.error("Error en showEvalLiqGastosInformeAvance.jspx");
		}
		*/
		return new ModelAndView("divCuerpoEvalReporteAvanLiqGastos",model);
		//model.put("datoProyectoId", userSession.getDatoProyectoID());
		//return new ModelAndView("divCuerpoEvalReporteAvanLiqGastos", model);
	}

	@RequestMapping(value = "/principal/showGrillaEvalReporteAvanLiqGastos")
	public ModelAndView showGrillaReporteAvance(HttpServletRequest request, HttpServletResponse response) {

		String cbxModalidadFinan = request.getParameter("cbxModalidadFinan");
		logger.info("parametros grilla: cbxModalidadFinan :"+cbxModalidadFinan);
		
		String cbxPrograma = request.getParameter("cbxPrograma");
		logger.info("parametros grilla: cbxPrograma :"+cbxPrograma);
		
		String txtCodProyecto = request.getParameter("txtCodProyecto");
		logger.info("parametros grilla: txtCodProyecto :"+txtCodProyecto);
		
		String txtNomProyecto = request.getParameter("txtNomProyecto");
		logger.info("parametros grilla: txtNomProyecto :"+txtNomProyecto);
		
				
		List<DatoProyectoBean>lstProyectosPorPrograma = evalReporteAvanceLiquidacionGastosService.getLstProyectosPorProgramaConReporteAvanceLiquidacionGastos(
				cbxModalidadFinan,cbxPrograma,txtCodProyecto,txtNomProyecto);
		
		model.put("lstProyectosPorPrograma",lstProyectosPorPrograma);
	
		/*Map<String, Object> model = new HashMap<String, Object>();
		List<ReporteAvance> listReporteAvance = reporteAvanceService
				.findReporteAvanceXDatoProyectoId(datoProyectoId);
		model.put("listReporteAvance",
				llenaListReporteAvanceBean(listReporteAvance));

		return new ModelAndView("divGrillaEvalReporteAvanLiqGastos", model);*/
		
		return new ModelAndView("divGrillaEvalReporteAvanLiqGastos",model);
	}

	
	//REPORTE AVANCE---------------------------------------------------------------------------------
	@RequestMapping(value = "/principal/showDatosReporteAvance")
	public ModelAndView showDatosReporteAvance(HttpServletRequest request) {
		String reporteAvanceID = request.getParameter("reporteAvanceID");
        String reporteAvanceDesc = request.getParameter("reporteAvanceDesc");
		        
        objReporteAvanceInner = new ReporteAvance();
        objReporteAvanceInner = evalReporteAvanceLiquidacionGastosService.getReporteAvanceById(CommonUtilities.toInt(reporteAvanceID));
		
		List<DetalleEstadoCabecera> lstDetEstCab = utilService.listarDetalleEstadoCabeceraByPrefijo(FondamConstans.PREFIJO_ESTADO_REPORTE_AVANCE);
		DetalleEstadoCabecera detalleEstadoCabeceraFinal;	
		List<DetalleEstadoCabecera> lstDetEstCabFinal = new ArrayList<DetalleEstadoCabecera>();
		for(DetalleEstadoCabecera detalleEstadoCabecera : lstDetEstCab ){
			if((detalleEstadoCabecera.getDetalleEstadoCabeceraID() != FondamConstans.ESTADO_REPORTE_GENERADO)&&
					(detalleEstadoCabecera.getDetalleEstadoCabeceraID() != FondamConstans.ESTADO_REPORTE_EN_ELABORACION)){
				detalleEstadoCabeceraFinal = new DetalleEstadoCabecera();
				detalleEstadoCabeceraFinal.setDescripEstado(detalleEstadoCabecera.getDescripEstado());
				detalleEstadoCabeceraFinal.setDetalleEstadoCabeceraID(detalleEstadoCabecera.getDetalleEstadoCabeceraID());	
				lstDetEstCabFinal.add(detalleEstadoCabeceraFinal);
			}
		}
		
		Map<String, Object> modelRepAvance = new HashMap<String, Object>();
		
		modelRepAvance.put("objReporteAvance", objReporteAvanceInner);
		modelRepAvance.put("lstDetEstCab", lstDetEstCabFinal);
		modelRepAvance.put("reporteAvanceDesc", reporteAvanceDesc);
		modelRepAvance.put("estReporteAvanceCod", objReporteAvanceInner.getFkIdDetalleEstadoCabEstRepAvance());
		modelRepAvance.put("estReporteAvancePrefijo", detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(objReporteAvanceInner.getFkIdDetalleEstadoCabEstRepAvance()).getPrefijoEstado());
				
		return new ModelAndView("datosReporteAvance", modelRepAvance);
	}
	
	@RequestMapping(value = "/principal/showProblemasSoluciones")
	public ModelAndView showProblemasSoluciones(HttpServletRequest request) {
		String reporteAvanceID = request.getParameter("reporteAvanceID");
		String estReporteAvancePrefijo = request.getParameter("estReporteAvancePrefijo");

		
		List<ProblemaSolucionBean> listProblemaSolucionBean = reporteAvanceService.llenaListProblemaSolucionBean(problemaSolucionService
				.findProblemaSolucionXReporteAvanceId(CommonUtilities.toInt(reporteAvanceID)));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listProblemaSolucion", listProblemaSolucionBean);
		model.put("reporteAvance",reporteAvanceService.findReporteAvanceById(Integer.valueOf(reporteAvanceID)));

		model.put("estReporteAvancePrefijo",estReporteAvancePrefijo);
		
		return new ModelAndView("showProblemasSoluciones", model);
	}
	
	@RequestMapping(value = "/principal/showAvanceResultadosActividad")
	public ModelAndView showAvanceResultadosActividad(HttpServletRequest request) {
		String reporteAvanceID = request.getParameter("reporteAvanceID");

		Map<String, Object> model = new HashMap<String, Object>();
		List<AvanceResultadoActividad> listAvanceResultadoActividad = avanceResultadoActividadService.
		                             findAvanceResultadoActividadXReporteAvanceId(CommonUtilities.toInt(reporteAvanceID));
		ReporteAvance reporteAvance = reporteAvanceService.findReporteAvanceById(CommonUtilities.toInt(reporteAvanceID));

		model.put("listAvanceResultadoActividadBean",avanceResultadoActividadService.llenaListAvanceResultadoActividadBean(listAvanceResultadoActividad));
	    model.put("reporteAvance", reporteAvance);
	    
	    String estReporteAvancePrefijo = request.getParameter("estReporteAvancePrefijo");
		model.put("estReporteAvancePrefijo",estReporteAvancePrefijo);
		
		return new ModelAndView("showAvanceResultadosActividad", model);
	}
	
	@RequestMapping(value = "/principal/showApreciacionResultados")
	public ModelAndView showApreciacionResultados(HttpServletRequest request) {
		String reporteAvanceID = request.getParameter("reporteAvanceID");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listApreciacionResultado",
				reporteAvanceService.llenaListApreciacionResultadoBean(apreciacionResultadoService
						.findApreciacionResultadoXReporteAvanceId(CommonUtilities.toInt(reporteAvanceID))));
		model.put("reporteAvance", reporteAvanceService
				.findReporteAvanceById(CommonUtilities.toInt(reporteAvanceID)));

		String estReporteAvancePrefijo = request.getParameter("estReporteAvancePrefijo");
		model.put("estReporteAvancePrefijo",estReporteAvancePrefijo);
		
		return new ModelAndView("showApreciacionResultados", model);
	}
	
	@RequestMapping(value = "/principal/showBeneficiarios")
	public ModelAndView showBeneficiarios(HttpServletRequest request) {
		String reporteAvanceID = request.getParameter("reporteAvanceID");
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		List<UbicacionProyecto> listUbicacionProyecto = ubicacionProyectoService
				.findUbicacionProyectoXDatoProyectoId(objReporteAvanceInner.getDatoProyecto().getDatoProyectoID());
		
		List<BeneficiariosPorResultado> listBeneficiariosPorResultado = new ArrayList<BeneficiariosPorResultado>();
		for (UbicacionProyecto ubicacionProyecto : listUbicacionProyecto) {
			List<BeneficiariosPorResultado> listBeneficiariosPorResultadoTemp = new ArrayList<BeneficiariosPorResultado>();
			listBeneficiariosPorResultadoTemp = beneficiariosPorResultadoService
					.findBeneficiariosPorResultadoXUbicacionProyectoId(ubicacionProyecto
							.getUbicacionProyectoID());
			for (BeneficiariosPorResultado beneficiariosPorResultado : listBeneficiariosPorResultadoTemp) {
				listBeneficiariosPorResultado.add(beneficiariosPorResultado);
			}
		}
		
		List<BeneficiariosPorResultadoBean> listBeneficiariosPorResultadoBean = beneficiariosPorResultadoService.llenaListBeneficiariosPorResultadoBean(listBeneficiariosPorResultado);
		model.put("listBeneficiariosPorResultadoBean",
				listBeneficiariosPorResultadoBean);
		model.put("reporteAvanceId", reporteAvanceID);
		model.put("reporteAvance", reporteAvanceService.findReporteAvanceById(Integer.valueOf(reporteAvanceID)));

		String estReporteAvancePrefijo = request.getParameter("estReporteAvancePrefijo");
		model.put("estReporteAvancePrefijo",estReporteAvancePrefijo);
		
		return new ModelAndView("showBeneficiarios", model);
	}
	
	@RequestMapping(value = "/principal/showPersonalTecnicoAdministrativo")
	public ModelAndView showPersonalTecnicoAdministrativo(HttpServletRequest request) {
		
		Integer reporteAvanceId=Integer.parseInt(request.getParameter("reporteAvanceID"));
		DatoProyecto datoProyecto = reporteAvanceService.findReporteAvanceById(reporteAvanceId).getDatoProyecto(); 
		Map<String, Object> model = new HashMap<String, Object>();
		List <PersonalTecnicoAdministrativo> listPersonalTecnicoAdministrativo = personalTecnicoAdministrativoService.
		findPersonalTecnicoAdministrativoByDatoProyectoId(datoProyecto.getDatoProyectoID());
		
		//ReporteAvance reporteAvance = reporteAvanceService.findReporteAvanceById(CommonUtilities.toInt(reporteAvanceID));

		model.put("lstPersonalTecAdm",listPersonalTecnicoAdministrativo);
	    model.put("reporteAvance", reporteAvanceService.findReporteAvanceById(reporteAvanceId));
		
	    String estReporteAvancePrefijo = request.getParameter("estReporteAvancePrefijo");
		model.put("estReporteAvancePrefijo",estReporteAvancePrefijo);
		
		return new ModelAndView("showPersonalTecnicoAdministrativo", model);
	}
	
	
	//LIQUIDACION GASTOS---------------------------------------------------------------------------------
	Map<String, Object> modelLiqGastos = new HashMap<String, Object>();
	@RequestMapping(value = "/principal/showDatosLiquidacionGastos")
	public ModelAndView showDatosLiquidacionGastos(HttpServletRequest request) {
		String liquidacionGastosID = request.getParameter("liquidacionGastoID");

		objLiquidacionGastoInner = new LiquidacionGasto();
		
		objLiquidacionGastoInner =liquidacionGastoService.llenaLiquidacionGastoCompleto(liquidacionGastoService.findLiquidacionGastoByID(CommonUtilities.toInt(liquidacionGastosID)));
		//objLiquidacionGastoInner.setDatoProyecto(datoProyectoService.findDatoProyectoById(objLiquidacionGastoInner.getDatoProyecto().getDatoProyectoID()));
		modelLiqGastos.put("resultadoGrabacion",0);
		modelLiqGastos.put("estLiquidacionGastoPrefijo",detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(objLiquidacionGastoInner.getFkIdDetalleEstadoCabEstLiqGasto()).getPrefijoEstado());
		
		
		datosLiqGastos();
		
		return new ModelAndView("datosLiquidacionGastos", modelLiqGastos);
	}
	
	void datosLiqGastos(){
			
		List<DetalleEstadoCabecera> lstDetEstCab = utilService.listarDetalleEstadoCabeceraByPrefijo(FondamConstans.PREFIJO_ESTADO_LIQUIDACION_GASTO);
		DetalleEstadoCabecera detalleEstadoCabeceraFinal;	
		List<DetalleEstadoCabecera> lstDetEstCabFinal = new ArrayList<DetalleEstadoCabecera>();
		for(DetalleEstadoCabecera detalleEstadoCabecera : lstDetEstCab ){
			if((detalleEstadoCabecera.getDetalleEstadoCabeceraID() != FondamConstans.ESTADO_LIQUIDACION_GASTO_GENERADA) &&
					(detalleEstadoCabecera.getDetalleEstadoCabeceraID() != FondamConstans.ESTADO_LIQUIDACION_GASTO_ELABORACION)){
				detalleEstadoCabeceraFinal = new DetalleEstadoCabecera();
				detalleEstadoCabeceraFinal.setDescripEstado(detalleEstadoCabecera.getDescripEstado());
				detalleEstadoCabeceraFinal.setDetalleEstadoCabeceraID(detalleEstadoCabecera.getDetalleEstadoCabeceraID());	
				lstDetEstCabFinal.add(detalleEstadoCabeceraFinal);
			}
		}
						
		modelLiqGastos.put("objLiquidacionGasto", objLiquidacionGastoInner);
		modelLiqGastos.put("lstDetEstCab", lstDetEstCabFinal);
		modelLiqGastos.put("estadoActualLiqGasto", (detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(objLiquidacionGastoInner.getFkIdDetalleEstadoCabEstLiqGasto())).getDescripEstado());
		modelLiqGastos.put("estadoLiqCod", objLiquidacionGastoInner.getFkIdDetalleEstadoCabEstLiqGasto());
		
	}
		
	@RequestMapping(value = "/principal/showIngresosPropios")
	public ModelAndView showIngresosPropios(HttpServletRequest request) {
        
        List<TablaEspecifica> listTipoComprobante=utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO);
        List<TablaEspecifica> listTipoMoneda=utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_MONEDA);
        List<Resultado> listResultado=liquidacionGastoService.findResultadoByDatoProyectoID(CommonUtilities.toInt(objLiquidacionGastoInner.getDatoProyecto().getDatoProyectoID()));

        List<IngresoPropio> listIngresoPropio= liquidacionGastoService.findIngresoPropioByLiquidacionGasto(objLiquidacionGastoInner.getLiquidacionGastoID());

	    Map<String, Object> model = new HashMap<String, Object>();
	    
	    model.put("liquidacionGastoID",objLiquidacionGastoInner.getLiquidacionGastoID());
	    model.put("liquidacionGasto",objLiquidacionGastoInner);
	    model.put("listResultado",listResultado);
	    model.put("listIngresoPropio", listIngresoPropio);
	    model.put("listTipoComprobante", listTipoComprobante);
	    model.put("listTipoMoneda", listTipoMoneda);
	    model.put("estLiquidacionGastoPrefijo", request.getParameter("estLiquidacionGastoPrefijo"));
		
		return new ModelAndView("showIngresosPropios", model);
	}
	
	@RequestMapping(value = "/principal/showFormulariosLlenos")
	public ModelAndView showFormulariosLlenos(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<String, Object>();
	    
	    model.put("liquidacionGastoID",request.getParameter("liquidacionGastoID"));
	    model.put("estado", request.getParameter("estLiquidacionGastoPrefijo"));
		
	    CargaFormularioBean cargaFormularioBean= new CargaFormularioBean();
  		model.put("cargaFormularioBean",cargaFormularioBean);
  		
		return new ModelAndView("mostrarFormulariosEval", model);
	}
	
	@RequestMapping(value = "/principal/showCompromisosPago")
	public ModelAndView showCompromisosPago(HttpServletRequest request) {

		//LiquidacionGasto liquidacionGasto=liquidacionGastoService.findLiquidacionGastoByID(objLiquidacionGastoInner.getLiquidacionGastoID());
		List<CompromisoPago> listCompromisoPago= liquidacionGastoService.findCompromisoPagoByLiquidacionGasto(objLiquidacionGastoInner.getLiquidacionGastoID());
		List<CronogramaCostoActividad> listPeriodo=liquidacionGastoService.findCronogramaCostoActividadByFuenteFinanciadoraID(objLiquidacionGastoInner.getFuenteFinanciadora().getFuenteFinanciadoraID());
		 List<Resultado> listResultado=liquidacionGastoService.findResultadoByDatoProyectoID(objLiquidacionGastoInner.getDatoProyecto().getDatoProyectoID());
        request.getSession().setAttribute("listCompromisoPago", listCompromisoPago);
	      Map<String, Object> model = new HashMap<String, Object>();
	      model.put("liquidacionGastoID",objLiquidacionGastoInner.getLiquidacionGastoID());
		  //model.put("compromisoPagoID",0);
		  model.put("listUnidadMedida",utilService.listaUnidadMedida());
		  model.put("listTipoMoneda",utilService.listaTipoMoneda());
		  model.put("listPeriodo",listPeriodo);
		  model.put("listResultado",listResultado);
		  model.put("liquidacionGasto",objLiquidacionGastoInner);
		  model.put("estLiquidacionGastoPrefijo", request.getParameter("estLiquidacionGastoPrefijo"));
		
		return new ModelAndView("showCompromisosPago", model);
	}
	
	@RequestMapping(value = "/principal/showPagosRealizados")
	public ModelAndView showPagosRealizados(HttpServletRequest request) {
		
		List <PagoLiquidacion> lstPagoLiquidacion = evalReporteAvanceLiquidacionGastosService.serializarListPagoLiquidacion(evalReporteAvanceLiquidacionGastosService.findPagoLiquidacionByLiquidacionGastoID(objLiquidacionGastoInner.getLiquidacionGastoID()));
		
		//List <PagoLiquidacion> lstPagoLiquidacionFinal = evalReporteAvanceLiquidacionGastosService.serializarListPagoLiquidacion(lstPagoLiquidacion);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("lstPagoLiquidacion", lstPagoLiquidacion);
		model.put("estLiquidacionGastoPrefijo", request.getParameter("estLiquidacionGastoPrefijo"));
		
		return new ModelAndView("showPagosRealizados", model);
	}
	
	@RequestMapping(value = "/principal/showPagoRealizado")
	public ModelAndView showPagoRealizado(HttpServletRequest request) {
		
		String pagoLiquidacionID = request.getParameter("pagoLiquidacionID");

		PagoLiquidacion pagoLiquidacion = evalReporteAvanceLiquidacionGastosService.serializarEstructuraPago(CommonUtilities.toInt(pagoLiquidacionID));
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("pagoLiquidacion", pagoLiquidacion);
		model.put("estLiquidacionGastoPrefijo", request.getParameter("estLiquidacionGastoPrefijo"));
		//model.put("lstDetallepagoLiquidacion", "");
		
		return new ModelAndView("showPagoRealizado", model);
	}

   //GUARDAR EVALUACION DE LIQUIDACION DE GASTOS Y REPORTE DE AVANCE
	
	@RequestMapping(value = "/principal/saveEvalReporteAvance", method = RequestMethod.POST)
	public ModelAndView saveEvalReporteAvance(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String estadoRepAvance = request.getParameter("cbxEstadoRepAvance");
		evalReporteAvanceLiquidacionGastosService.saveEvalRepAvance(objReporteAvanceInner,CommonUtilities.toInt(estadoRepAvance));
		objReporteAvanceInner = new ReporteAvance();
		
		model.put("resultadoGrabacion", 1);
		model.put("estReporteAvancePrefijo", detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(Integer.valueOf(estadoRepAvance)).getPrefijoEstado());
		
		 return new ModelAndView("datosReporteAvance",model);
	}
	
	
	@RequestMapping(value = "/principal/saveEvalLiquidacionGasto", method = RequestMethod.POST)
	public ModelAndView saveEvalLiquidacionGasto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String estadoLiqGasto = request.getParameter("cbxEstadoLiqGasto");
		
		evalReporteAvanceLiquidacionGastosService.saveEvalLiqGasto(objLiquidacionGastoInner,CommonUtilities.toInt(estadoLiqGasto));
		
		//objLiquidacionGastoInner = new LiquidacionGasto();
		modelLiqGastos.put("estLiquidacionGastoPrefijo", detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(Integer.valueOf(estadoLiqGasto)));
		modelLiqGastos.put("resultadoGrabacion",1);
		
		datosLiqGastos();
		
		return new ModelAndView("datosLiquidacionGastos", modelLiqGastos);
	}
	

}
