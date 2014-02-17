package pe.com.fondam.sgp.web.controller.principal;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.bean.BeneficiariosPorResultadoBean;
import pe.com.fondam.sgp.core.bean.ConclusionBean;
import pe.com.fondam.sgp.core.bean.DatoProyectoBean;
import pe.com.fondam.sgp.core.bean.DirectivaBeneficiarioBean;
import pe.com.fondam.sgp.core.bean.EfectoProyectoBean;
import pe.com.fondam.sgp.core.bean.EvaluacionFinalBean;
import pe.com.fondam.sgp.core.bean.LeccionApendidaBean;
import pe.com.fondam.sgp.core.bean.MaterialProducidoBean;
import pe.com.fondam.sgp.core.bean.OrgBienTranferidoBean;
import pe.com.fondam.sgp.core.bean.OrganizacionBean;
import pe.com.fondam.sgp.core.bean.ProblemaSolucionBean;
import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.Bien;
import pe.com.fondam.sgp.core.domain.InformeFinal;
import pe.com.fondam.sgp.core.domain.PropuestaTransferencia;
import pe.com.fondam.sgp.core.domain.UbicacionProyecto;
import pe.com.fondam.sgp.core.service.BeneficiariosPorResultadoService;
import pe.com.fondam.sgp.core.service.BienService;
import pe.com.fondam.sgp.core.service.ConclusionService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.DirectivaBeneficiarioService;
import pe.com.fondam.sgp.core.service.EfectoProyectoService;
import pe.com.fondam.sgp.core.service.EvalReporteAvanceLiquidacionGastosService;
import pe.com.fondam.sgp.core.service.EvaluacionFinalService;
import pe.com.fondam.sgp.core.service.InformeFinalService;
import pe.com.fondam.sgp.core.service.LeccionApendidaService;
import pe.com.fondam.sgp.core.service.MaterialProducidoService;
import pe.com.fondam.sgp.core.service.ObservacionService;
import pe.com.fondam.sgp.core.service.OrgBienTranferidoService;
import pe.com.fondam.sgp.core.service.OrganizacionService;
import pe.com.fondam.sgp.core.service.ProblemaSolucionService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaService;
import pe.com.fondam.sgp.core.service.ReporteAvanceService;
import pe.com.fondam.sgp.core.service.UbicacionProyectoService;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.controller.security.SecurityController;
import pe.com.fondam.sgp.web.session.UserSession;


@Controller
public class EvalPropuestaEInformeController {

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
	private EvalReporteAvanceLiquidacionGastosService evalReporteAvanceLiquidacionGastosService;
	
	@Resource
	ObservacionService observacionService;
	
	protected final Log logger = LogFactory.getLog(EvalPropuestaEInformeController.class);
	Map<String, Object> model = new HashMap<String, Object>();


	//****************** metodos  ***************************//
	@RequestMapping(value = "/principal/showEvalPropuestaEInforme.jspx")
	public ModelAndView showEvalPropuestaEInforme(HttpServletRequest request,
			HttpServletResponse response) {

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		if (userSession == null) {
			return SecurityController.autenticateErrorRedirect(request);
		}
		model.put("funcionalidadSelect", "showEvalPropuestaEInforme.jspx");
		return new ModelAndView("mostrarEvalPropuestaEInforme",model);
	}
	
	@RequestMapping(value = "/principal/showCuerpoEvalPropuestaEInforme.jspx")
	public ModelAndView showCuerpoEvalPropuestaEInforme(HttpServletRequest request,
			HttpServletResponse response) {
			
		return new ModelAndView("divCuerpoEvalPropuestaEInforme",model);
	}

	@RequestMapping(value = "/principal/showGrillaEvalPropuestaEInforme")
	public ModelAndView showGrillaEvalPropuestaEInforme(HttpServletRequest request, HttpServletResponse response) {

		String cbxModalidadFinan = request.getParameter("cbxModalidadFinan");
		logger.info("parametros grilla: cbxModalidadFinan :"+cbxModalidadFinan);
		
		String cbxPrograma = request.getParameter("cbxPrograma");
		logger.info("parametros grilla: cbxPrograma :"+cbxPrograma);
		
		String txtCodProyecto = request.getParameter("txtCodProyecto");
		logger.info("parametros grilla: txtCodProyecto :"+txtCodProyecto);
		
		String txtNomProyecto = request.getParameter("txtNomProyecto");
		logger.info("parametros grilla: txtNomProyecto :"+txtNomProyecto);
		
				
		List<DatoProyectoBean>lstProyectosPorPrograma = evalReporteAvanceLiquidacionGastosService.getLstProyectosPorProgramaConPropuestaEInforme(
				cbxModalidadFinan,cbxPrograma,txtCodProyecto,txtNomProyecto);
		
		model.put("lstProyectosPorPrograma",lstProyectosPorPrograma);
	
		return new ModelAndView("divGrillaEvalPropuestaEInforme",model);
	}

	
	//Propuesta de Transferencia---------------------------------------------------------------------------------
	@RequestMapping(value = "/principal/showDatosPropuestaTransferencia.jspx")
	public ModelAndView showDatosReporteAvance(
			@RequestParam(required = true, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			HttpServletRequest request) {

        //PropuestaTransferencia propuestaTransferencia = propuestaTransferenciaService.findPropuestaTransferenciaById(propuestaTransferenciaID);
		
        model.put("lstEstadoPropuesta", detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estprtr"));
		//model.put("propuestaTransferencia", propuestaTransferenciaService.llenaPropuestaTransferenciaCompleto( propuestaTransferencia));
		
        PropuestaTransferencia propuestaTransferencia =propuestaTransferenciaService.findPropuestaTransferenciaById(propuestaTransferenciaId);
        propuestaTransferencia.setCantObservacionesRelevantes(observacionService.findObservacionesRelevatesAlDocumento(propuestaTransferencia.getDatoProyecto().getDatoProyectoID(),propuestaTransferencia.getPropuestaTransferenciaID(),FondamConstans.TABLA_CLASE_NOMBRE_PROPUESTA_TRANSFERENCIA));
        propuestaTransferencia.setDescripcionEstadoPropuesta(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(propuestaTransferencia.getFkIdDetalleEstadoCabEstInfPropTransfer()).getDescripEstado());
        
        model.put("propuestaTransferencia",propuestaTransferencia);
		
		return new ModelAndView("datosPropuestaTransferencia", model);
	}
	
	@RequestMapping(value = "/principal/cargaGrillaDirectivaBeneficiarioEvaluar")
	public ModelAndView cargaGrillaDirectivaBeneficiarioEvaluar(
			@RequestParam(required = false, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		
		// busco List<DirectivaBeneficiario>
		List<DirectivaBeneficiarioBean> listDirectivaBeneficiario =directivaBeneficiarioService.llenaListDirectivaBeneficiarioBean(directivaBeneficiarioService.llenaListDirectivaBeneficiario(directivaBeneficiarioService
				.findDirectivaBeneficiarioByPropuestaTransferenciaId(propuestaTransferenciaId)));
		model.put("listDirectivaBeneficiario", listDirectivaBeneficiario);
		return new ModelAndView("divGrillaDirectivaBeneficiarioEvaluar", model);
	}
	
	@RequestMapping(value = "/principal/cargaGrillaBeneficiarioPropuestaTransferenciaEvaluar")
	public ModelAndView cargaGrillaBeneficiarioPropuestaTransferenciaEvaluar(
			@RequestParam(required = false, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {


		Map<String, Object> model = new HashMap<String, Object>();

		List<UbicacionProyecto> listUbicacionProyecto = ubicacionProyectoService
				.findUbicacionProyectoXDatoProyectoId(datoProyectoId);

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

		List<BeneficiariosPorResultadoBean> listBeneficiariosPorResultadoBean = beneficiariosPorResultadoService
				.llenaListBeneficiariosPorResultadoBean(listBeneficiariosPorResultado);
		model.put("listBeneficiariosPorResultadoBean",
				listBeneficiariosPorResultadoBean);
		model.put("propuestaTransferenciaId", propuestaTransferenciaId);
		model.put("datoProyectoId", datoProyectoId);
		

		return new ModelAndView("divGrillaBeneficiariosPropuestaTransferenciaEvaluar",
				model);
	}

	@RequestMapping(value = "/principal/cargaGrillaOrganizacionEvaluar")
	public ModelAndView cargaGrillaOrganizacionEvaluar(
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			@RequestParam(required = false, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		//model.put("funcionalidadSelect","showGestionarPropuestaTransferencia.jspx");

		// busco List<Organizacion>
		List<OrganizacionBean> listOrganizacionBean = organizacionService.llenaListOrganizacionBean(organizacionService 
				.findOrganizacionByDatoProyectoId(datoProyectoId));
		model.put("listOrganizacion", listOrganizacionBean);
		model.put("propuestaTransferenciaId", propuestaTransferenciaId);
		model.put("datoProyectoId", datoProyectoId);
		
		return new ModelAndView("divGrillaOrganizacionEvaluar", model);
	}

	@RequestMapping(value = "/principal/cargaGrillaBienEvaluar")
	public ModelAndView cargaGrillaBienEvaluar(
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			@RequestParam(required = false, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		//model.put("funcionalidadSelect","showGestionarPropuestaTransferencia.jspx");

		// busco List<Organizacion>
		List<Bien> listBien = bienService 
				.findBienByDatoProyectoId(datoProyectoId);
		model.put("listBien", bienService.llenaListBien(listBien));
		model.put("datoProyectoId", datoProyectoId);
		model.put("propuestaTransferenciaId",propuestaTransferenciaId);
		
		return new ModelAndView("divGrillaBienEvaluar", model);
	}

	@RequestMapping(value = "/principal/cargaGrillaBienTransferidoEvaluar")
	public ModelAndView cargaGrillaBienTransferidoEvaluar(
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			@RequestParam(required = false, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		//model.put("funcionalidadSelect","showGestionarPropuestaTransferencia.jspx");

		// busco List<Organizacion>
		List<OrgBienTranferidoBean> listOrgBienTranferidoBean =orgBienTranferidoService.llenaListOrgBienTranferidoBean(orgBienTranferidoService.llenaListOrgBienTranferido( orgBienTranferidoService 
				.findOrgBienTranferidoByPropuestaTransferenciaId(propuestaTransferenciaId)));
		model.put("listOrgBienTranferido",listOrgBienTranferidoBean);
		model.put("datoProyectoId", datoProyectoId);
		model.put("propuestaTransferenciaId",propuestaTransferenciaId);
		
		return new ModelAndView("divGrillaBienTranferidoEvaluar", model);
	}
	
	@RequestMapping(value = "/principal/grabarEstadoPropuestaTransferencia.jspx")
	public void grabarPropuestaTransferencia(
			@RequestParam(required = true, value = "estadoPropuesta") Integer estadoPropuesta,
			@RequestParam(required = true, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		
		PropuestaTransferencia propuestaTransferencia= propuestaTransferenciaService.findPropuestaTransferenciaById(propuestaTransferenciaId);
		propuestaTransferencia.setFkIdDetalleEstadoCabEstInfPropTransfer(estadoPropuesta);
		//propuestaTransferencia.setPropuestaTransferenciaID(propuestaTransferenciaId);
		
		propuestaTransferenciaService.updatePropuestaTransferencia(propuestaTransferencia);
		
	}

	// Informe Final---------------------------------------------------------------------------------
	@RequestMapping(value = "/principal/showDatosInformeFinal.jspx")
	public ModelAndView showDatosInformeFinal(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			HttpServletRequest request) {
		

        model.put("lstEstadoInforme", detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estinf"));
		//model.put("propuestaTransferencia", propuestaTransferenciaService.llenaPropuestaTransferenciaCompleto( propuestaTransferencia));
		
        InformeFinal informeFinal = informeFinalService.findInformeFinalById(informeFinalId);
        informeFinal.setCantObservacionesRelevantes(observacionService.findObservacionesRelevatesAlDocumento(informeFinal.getDatoProyecto().getDatoProyectoID(),informeFinal.getInformeFinalID(),FondamConstans.TABLA_CLASE_NOMBRE_INFORME_FINAL));
        informeFinal.setDescripcionEstadoInformeFinal(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(informeFinal.getFkIdDetalleEstadoCabEstInfFinal()).getDescripEstado());
        
        model.put("informeFinal", informeFinal );
		
		return new ModelAndView("datosInformeFinal", model);
	}
	
	@RequestMapping(value = "/principal/cargaGrillaProblemasSolucionesEvaluar")
	public ModelAndView cargaGrillaProblemasSolucionesEvaluar(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		//model.put("funcionalidadSelect", "showGestionarInformeFinal.jspx");

		List<ProblemaSolucionBean> listProblemaSolucionBean = reporteAvanceService
				.llenaListProblemaSolucionBean(problemaSolucionService 
						.findProblemaSolucionByDatoProyectoId(datoProyectoId));

		model.put("listProblemaSolucion", listProblemaSolucionBean);
		model.put("cantProblemaSolucion", listProblemaSolucionBean.size());
		model.put("informeFinalId", informeFinalId);
		model.put("datoProyectoId", datoProyectoId);
		
		return new ModelAndView("divGrillaProblemaSolucionInformeFinalEvaluar", model);
	}
	
	@RequestMapping(value = "/principal/cargaGrillaEfectoProyectoEvaluar")
	public ModelAndView cargaGrillaEfectoProyectoEvaluar(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		//model.put("funcionalidadSelect", "showGestionarInformeFinal.jspx");

		List<EfectoProyectoBean> listEfectoProyectoBean =efectoProyectoService.llenaListEfectoProyectoBean (efectoProyectoService
				.llenaCompletaListEfectoProyecto(efectoProyectoService 
						.findEfectoProyectoByInformeFinalId(informeFinalId)));

		model.put("listEfectoProyecto", listEfectoProyectoBean);
		model.put("informeFinalId", informeFinalId);
		model.put("datoProyectoId", datoProyectoId);

		return new ModelAndView("divGrillaEfectoProyectoInformeFinalEvaluar", model);
	}

	@RequestMapping(value = "/principal/cargaGrillaMaterialProducidoEvaluar")
	public ModelAndView cargaGrillaMaterialProducidoEvaluar(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		//model.put("funcionalidadSelect", "showGestionarInformeFinal.jspx");

		List<MaterialProducidoBean> listMaterialProducidoBean =materialProducidoService.llenaListMaterialProducidoBean( materialProducidoService
				.llenaCompletaListMaterialProducido(materialProducidoService 
						.findMaterialProducidoByInformeFinalId(informeFinalId)));

		model.put("listMaterialProducido", listMaterialProducidoBean);
		model.put("informeFinalId", informeFinalId);
		model.put("datoProyectoId", datoProyectoId);

		return new ModelAndView("divGrillaMaterialProducidoInformeFinalEvaluar", model);
	}

	@RequestMapping(value = "/principal/cargaGrillaEvaluacionFinalEvaluar")
	public ModelAndView cargaGrillaEvaluacionFinalEvaluar(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		//model.put("funcionalidadSelect", "showGestionarInformeFinal.jspx");

		List<EvaluacionFinalBean> listEvaluacionFinalBean =evaluacionFinalService.llenaListEvaluacionFinalBean( evaluacionFinalService
				.llenaCompletaListEvaluacionFinal(evaluacionFinalService 
						.findEvaluacionFinalByInformeFinalId(informeFinalId)));

		model.put("listEvaluacionFinal", listEvaluacionFinalBean);
		model.put("informeFinalId", informeFinalId);
		model.put("datoProyectoId", datoProyectoId);

		return new ModelAndView("divGrillaEvaluacionFinalInformeFinalEvaluar", model);
	}
	
	@RequestMapping(value = "/principal/cargaGrillaLeccionAprendidaEvaluar")
	public ModelAndView cargaGrillaLeccionAprendidaEvaluar(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		//model.put("funcionalidadSelect", "showGestionarInformeFinal.jspx");

		List<LeccionApendidaBean> listLeccionApendidaBean =leccionApendidaService.llenaListLeccionApendidaBean( leccionApendidaService
				.llenaCompletaListLeccionApendida(leccionApendidaService 
						.findLeccionApendidaByInformeFinalId(informeFinalId)));

		model.put("listLeccionApendida", listLeccionApendidaBean);
		model.put("informeFinalId", informeFinalId);
		model.put("datoProyectoId", datoProyectoId);

		return new ModelAndView("divGrillaLeccionApendidaInformeFinalEvaluar", model);
	}
	
	@RequestMapping(value = "/principal/cargaGrillaConclucionInformeFinalEvaluar")
	public ModelAndView cargaGrillaConclucionInformeFinalEvaluar(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		//model.put("funcionalidadSelect", "showGestionarInformeFinal.jspx");

		List<ConclusionBean> listConclusionBean =conclusionService.llenaListConclusionBean (conclusionService
				.llenaCompletaListConclusion(conclusionService 
						.findconclusionByInformeFinalId(informeFinalId)));

		model.put("listConclusion", listConclusionBean);
		model.put("informeFinalId", informeFinalId);
		model.put("datoProyectoId", datoProyectoId);

		return new ModelAndView("divGrillaConclucionInformeFinalEvaluar", model);
	}

	@RequestMapping(value = "/principal/cargaGrillaOrganizacionInformeFinalEvaluar")
	public ModelAndView cargaGrillaOrganizacionInformeFinalEvaluar(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		//model.put("funcionalidadSelect","showGestionarPropuestaTransferencia.jspx");

		// busco List<Organizacion>
		List<OrganizacionBean> listOrganizacionBean = organizacionService.llenaListOrganizacionBean(organizacionService
				.findOrganizacionByDatoProyectoId(datoProyectoId));
		model.put("listOrganizacion", listOrganizacionBean);
		model.put("informeFinalId", informeFinalId);
		model.put("datoProyectoId", datoProyectoId);
		
		return new ModelAndView("divGrillaOrganizacionInformeFinalEvaluar", model);
	}

	@RequestMapping(value = "/principal/cargaGrillaBienInformeFinalEvaluar")
	public ModelAndView cargaGrillaBienInformeFinalEvaluar(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		//model.put("funcionalidadSelect","showGestionarPropuestaTransferencia.jspx");

		// busco List<Organizacion>
		List<Bien> listBien = bienService
				.findBienByDatoProyectoId(datoProyectoId);
		model.put("listBien", bienService.llenaListBien(listBien));
		model.put("informeFinalId", informeFinalId);
		model.put("datoProyectoId", datoProyectoId);
		
		return new ModelAndView("divGrillaBienInformeFinalEvaluar", model);
	}

	@RequestMapping(value = "/principal/grabarEstadoInformeFinal.jspx")
	public void grabarInformeFinal(
			@RequestParam(required = true, value = "estadoInforme") Integer estadoInforme,
			@RequestParam(required = true, value = "informeFinalId") Integer informeFinalId,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		
		InformeFinal informeFinal= informeFinalService.findInformeFinalById(informeFinalId);
		informeFinal.setFkIdDetalleEstadoCabEstInfFinal(estadoInforme);
		//informeFinal.setInformeFinalID(informeFinalId);
		
		informeFinalService.updateInformeFinal(informeFinal);
		
	}
}
