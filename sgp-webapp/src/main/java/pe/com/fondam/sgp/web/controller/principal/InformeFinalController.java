package pe.com.fondam.sgp.web.controller.principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.bean.ConclusionBean;
import pe.com.fondam.sgp.core.bean.EfectoProyectoBean;
import pe.com.fondam.sgp.core.bean.EvaluacionFinalBean;
import pe.com.fondam.sgp.core.bean.LeccionApendidaBean;
import pe.com.fondam.sgp.core.bean.MaterialProducidoBean;
import pe.com.fondam.sgp.core.bean.OrganizacionBean;
import pe.com.fondam.sgp.core.bean.ProblemaSolucionBean;
import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.Bien;
import pe.com.fondam.sgp.core.domain.Conclusion;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.EfectoProyecto;
import pe.com.fondam.sgp.core.domain.EvaluacionFinal;
import pe.com.fondam.sgp.core.domain.InformeFinal;
import pe.com.fondam.sgp.core.domain.InformeFinalBien;
import pe.com.fondam.sgp.core.domain.LeccionApendida;
import pe.com.fondam.sgp.core.domain.MaterialProducido;
import pe.com.fondam.sgp.core.domain.Organizacion;
import pe.com.fondam.sgp.core.domain.ProblemaSolucion;
import pe.com.fondam.sgp.core.domain.RecursoUtilizado;
import pe.com.fondam.sgp.core.service.ActivoService;
import pe.com.fondam.sgp.core.service.BienService;
import pe.com.fondam.sgp.core.service.ConclusionService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DescripcionEfService;
import pe.com.fondam.sgp.core.service.DetalleConcluIfService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.EfectoProyectoService;
import pe.com.fondam.sgp.core.service.EvaluacionFinalService;
import pe.com.fondam.sgp.core.service.FuenteFinanciadoraService;
import pe.com.fondam.sgp.core.service.InformeFinalBienService;
import pe.com.fondam.sgp.core.service.InformeFinalService;
import pe.com.fondam.sgp.core.service.LeccionApendidaService;
import pe.com.fondam.sgp.core.service.LiquidacionGastoService;
import pe.com.fondam.sgp.core.service.MaterialProducidoService;
import pe.com.fondam.sgp.core.service.OrgBienTranferidoService;
import pe.com.fondam.sgp.core.service.OrganizacionService;
import pe.com.fondam.sgp.core.service.ProblemaSolucionService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaBienService;
import pe.com.fondam.sgp.core.service.RecursoUtilizadoService;
import pe.com.fondam.sgp.core.service.ReporteAvanceService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.controller.security.SecurityController;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class InformeFinalController {

	// ********* inyecciones **************//
	@Resource
	private DetalleEstadoCabeceraService detalleEstadoCabeceraService;

	@Resource
	private TablaEspecificaService tablaEspecificaService;

	@Resource
	InformeFinalService informeFinalService;

	@Resource
	private DatoProyectoService datoProyectoService;

	@Resource
	private ReporteAvanceService reporteAvanceService;

	@Resource
	private ProblemaSolucionService problemaSolucionService;

	@Resource
	PropuestaTransferenciaBienService propuestaTransferenciaBienService;

	@Resource
	RecursoUtilizadoService recursoUtilizadoService;

	@Resource
	OrgBienTranferidoService orgBienTranferidoService;

	@Resource
	EfectoProyectoService efectoProyectoService;

	@Resource
	MaterialProducidoService materialProducidoService;

	@Resource
	DescripcionEfService descripcionEfService;

	@Resource
	EvaluacionFinalService evaluacionFinalService;

	@Resource
	LeccionApendidaService leccionApendidaService;

	@Resource
	ConclusionService conclusionService;

	@Resource
	DetalleConcluIfService detalleConcluIfService;
	
	@Resource
	OrganizacionService organizacionService;
	
	@Resource
	FuenteFinanciadoraService fuenteFinanciadoraService;
	
	@Resource
	BienService bienService;
	
	@Resource
	LiquidacionGastoService liquidacionGastoService;
	
	@Resource
	ActivoService activoService;
	
	@Resource
	InformeFinalBienService informeFinalBienService;
	
	
	// ********* metodos **************//
	@RequestMapping(value = "/principal/showGestionarInformeFinal")
	public ModelAndView showGestionarInformeFinal(HttpServletRequest request,
			HttpServletResponse response) {

		request.getSession().removeAttribute("listRecursoUtilizado");

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		if (userSession == null) {
			return SecurityController.autenticateErrorRedirect(request);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect", "showGestionarInformeFinal.jspx");

		
		// creo la propuesta de transferencia
		InformeFinal informeFinal = informeFinalService
				.findInformeFinalByDatoProyectoId(userSession
						.getDatoProyectoID());
		if (informeFinal == null) {
			informeFinal = new InformeFinal();

			informeFinal.setDatoProyecto(datoProyectoService
					.findDatoProyectoById(userSession.getDatoProyectoID()));
			informeFinal
					.setFkIdDetalleEstadoCabEstInfFinal(detalleEstadoCabeceraService
							.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraByPrefijoDetalleEstado(
									"estinf", "elab")
							.getDetalleEstadoCabeceraID());
			// informeFinal.setResultadoProyecto("Sin especificar");

			informeFinal = informeFinalService.updateInformeFinal(informeFinal);
		}

		model.put("informeFinal", informeFinal);
		model.put("datoProyectoId", userSession.getDatoProyectoID());

		List<DetalleEstadoCabecera> listDetalleEstadoCabecera = detalleEstadoCabeceraService
				.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estinf");
		List<DetalleEstadoCabecera> listDetalleEstadoCabeceraTemp = new ArrayList<DetalleEstadoCabecera>();
		for (DetalleEstadoCabecera detalleEstadoCabecera : listDetalleEstadoCabecera) {
			if ((detalleEstadoCabecera.getPrefijoEstado().equals("elab"))
					|| (detalleEstadoCabecera.getPrefijoEstado().equals("eval"))
					|| (detalleEstadoCabecera.getPrefijoEstado()
							.equals(detalleEstadoCabeceraService
									.findDetalleEstadoCabeceraById(
											informeFinal
													.getFkIdDetalleEstadoCabEstInfFinal())
									.getPrefijoEstado()))) {
				listDetalleEstadoCabeceraTemp.add(detalleEstadoCabecera);
			}
		}
		model.put("listDetalleEstadoCabecera", listDetalleEstadoCabeceraTemp);
		model.put(
				"listTipoEfectoProyecto",
				tablaEspecificaService
						.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_EFECTOS_PROYECTO));
		model.put(
				"listTipoMaterialProducido",
				tablaEspecificaService
						.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_TIPO_MATERIAL));
		model.put(
				"listTipoEvaluacion",
				tablaEspecificaService
						.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_EVALUACION_FINAL_CABECERA));
		model.put(
				"listTipoLeccion",
				tablaEspecificaService
						.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_TIPO_LECCION));
		model.put(
				"listTipoConclusion",
				tablaEspecificaService
						.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_CABECERA_CONCLUSION_IF));
		model.put(
				"listUnidadMedida",
				tablaEspecificaService
						.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_UNIDADES_MEDIDA));
		model.put(
				"listTipoMoneda",
				tablaEspecificaService
						.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_TIPO_MONEDA));
		model.put(
				"listCategoriaActivo",
				tablaEspecificaService
						.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_CATEGORIA_ACTIVO));
		model.put("listFuenteFinanciadora", liquidacionGastoService
				.findFuenteFinanciadoraByDatoProyectoID(userSession
						.getDatoProyectoID()));
		model.put(
				"listTipoBien",
				tablaEspecificaService
						.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_TIPO_BIEN));
		model.put(
				"listEstadoConservacion",
				detalleEstadoCabeceraService
						.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estcons"));
		model.put("estadoInformeFinal", detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(informeFinal.getFkIdDetalleEstadoCabEstInfFinal()).getPrefijoEstado());

		model.put("cantMuestraMensajeObs",1);
		
		return new ModelAndView("divInformeFinal", model);
	}

	@RequestMapping(value = "/principal/grabarInformeFinal.jspx")
	public String grabarInformeFinal(HttpServletRequest request) {

		Integer datoProyectoID = Integer.parseInt(request
				.getParameter("datoProyectoId"));
		Integer informeFinalId = Integer.parseInt(request
				.getParameter("informeFinalId"));
		String resultadoProyectoInformeFinal = request
				.getParameter("resultadoProyectoInformeFinal");
		Integer sltEstadoInformeFinal = Integer.parseInt(request
				.getParameter("sltEstadoInformeFinal"));

		InformeFinal informeFinal = new InformeFinal();

		informeFinal.setDatoProyecto(datoProyectoService
				.findDatoProyectoById(datoProyectoID));
		informeFinal.setFkIdDetalleEstadoCabEstInfFinal(sltEstadoInformeFinal);
		informeFinal.setInformeFinalID(informeFinalId);
		informeFinal.setResultadoProyecto(resultadoProyectoInformeFinal);

		informeFinalService.updateInformeFinal(informeFinal);

		return "redirect:showGestionarInformeFinal.jspx";
	}

	@RequestMapping(value = "/principal/cargaGrillaProblemasSoluciones")
	public ModelAndView cargaGrillaProblemasSoluciones(
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect", "showGestionarInformeFinal.jspx");

		List<ProblemaSolucionBean> listProblemaSolucionBean = reporteAvanceService
				.llenaListProblemaSolucionBean(problemaSolucionService
						.findProblemaSolucionByDatoProyectoId(datoProyectoId));

		model.put("listProblemaSolucion", listProblemaSolucionBean);
		model.put("cantProblemaSolucion", listProblemaSolucionBean.size());

		return new ModelAndView("divGrillaProblemaSolucionInformeFinal", model);
	}
	
	@RequestMapping(value = "/principal/mostrarDetalleProblemasSolucionesInformeFinal")
	public ModelAndView mostrarDetalleProblemasSolucionesInformeFinal(
			@RequestParam(required = true, value = "problemaSolucionId") Integer problemaSolucionId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		// para traer todos los problemas solucion
		ProblemaSolucion problemaSolucion = problemaSolucionService
				.findProblemaSolucionById(problemaSolucionId);

		model.put("problemaSolucion", problemaSolucion);

		return new ModelAndView(
				"divMuestraDetalleProblemaSolucionInformeFinal", model);
	}
	
	@RequestMapping(value = "/principal/grabarPSRelevanteAlProyecto")
	public void grabarPSRelevanteAlProyecto(
			@RequestParam(required = false, value = "problemaRelevante") Integer problemaRelevante,
			@RequestParam(required = false, value = "problemaSolucionID") Integer problemaSolucionID,
			HttpServletRequest request, HttpServletResponse response) {

		ProblemaSolucion problemaSolucion = problemaSolucionService
				.findProblemaSolucionById(problemaSolucionID);
		problemaSolucion.setProblemaRelevanteAlProy(problemaRelevante);

		problemaSolucionService.updateProblemaSolucion(problemaSolucion);
	}
	
	@RequestMapping(value = "/principal/cargaGrillaEfectoProyecto")
	public ModelAndView cargaGrillaEfectoProyecto(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect", "showGestionarInformeFinal.jspx");

		List<EfectoProyectoBean> listEfectoProyectoBean =efectoProyectoService.llenaListEfectoProyectoBean (efectoProyectoService
				.llenaCompletaListEfectoProyecto(efectoProyectoService
						.findEfectoProyectoByInformeFinalId(informeFinalId)));

		model.put("listEfectoProyecto", listEfectoProyectoBean);

		return new ModelAndView("divGrillaEfectoProyectoInformeFinal", model);
	}

	@RequestMapping(value = "/principal/grabarEfectoProyecto")
	public void grabarEfectoProyecto(
			@RequestParam(required = false, value = "sltTipoEfectoProyecto") Integer sltTipoEfectoProyecto,
			@RequestParam(required = false, value = "comentarioEfectoProyecto") String comentarioEfectoProyecto,
			@RequestParam(required = false, value = "efectoProyectoId") Integer efectoProyectoId,
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			HttpServletRequest request, HttpServletResponse response) {

		EfectoProyecto efectoProyecto = new EfectoProyecto();
		if (efectoProyectoId != 0) {
			efectoProyecto.setEfectoProyectoID(efectoProyectoId);
		}

		efectoProyecto.setComentario(comentarioEfectoProyecto);
		efectoProyecto.setFkidtablaespefectoProy(sltTipoEfectoProyecto);
		efectoProyecto.setInformeFinal(informeFinalService
				.findInformeFinalById(informeFinalId));

		efectoProyectoService.updateEfectoProyecto(efectoProyecto);
	}

	@RequestMapping(value = "/principal/grabarMaterialProducido")
	public void grabarMaterialProducido(
			@RequestParam(required = false, value = "materialProducidoId") Integer materialProducidoId,
			@RequestParam(required = false, value = "descripcionMaterialProducido") String descripcionMaterialProducido,
			@RequestParam(required = false, value = "cantidadMaterialProducido") Integer cantidadMaterialProducido,
			@RequestParam(required = false, value = "sltTipoMaterialProducido") Integer sltTipoMaterialProducido,
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			HttpServletRequest request, HttpServletResponse response) {

		MaterialProducido materialProducido = new MaterialProducido();
		if (materialProducidoId != 0) {
			materialProducido.setMaterialProducidoID(materialProducidoId);
		}
		materialProducido.setCantidad(cantidadMaterialProducido);
		materialProducido
				.setDescripcionMaterialProducido(descripcionMaterialProducido);
		materialProducido.setFkIdtablaespTipoMaterial(sltTipoMaterialProducido);
		materialProducido.setInformeFinal(informeFinalService
				.findInformeFinalById(informeFinalId));

		materialProducidoService.updateMaterialProducido(materialProducido);
	}
	
	@RequestMapping(value = "/principal/cargaGrillaMaterialProducido")
	public ModelAndView cargaGrillaMaterialProducido(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect", "showGestionarInformeFinal.jspx");

		List<MaterialProducidoBean> listMaterialProducidoBean =materialProducidoService.llenaListMaterialProducidoBean( materialProducidoService
				.llenaCompletaListMaterialProducido(materialProducidoService
						.findMaterialProducidoByInformeFinalId(informeFinalId)));

		model.put("listMaterialProducido", listMaterialProducidoBean);

		return new ModelAndView("divGrillaMaterialProducidoInformeFinal", model);
	}
	
	@RequestMapping(value = "/principal/grabarEvaluacionFinal")
	public void grabarEvaluacionFinal(
			@RequestParam(required = false, value = "sltDescripcionTipoEvaluacionFinal") Integer sltDescripcionTipoEvaluacionFinal,
			@RequestParam(required = false, value = "comentarioEvaluacionFinal") String comentarioEvaluacionFinal,
			@RequestParam(required = false, value = "evaluacionFinalId") Integer evaluacionFinalId,
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			HttpServletRequest request, HttpServletResponse response) {

		EvaluacionFinal evaluacionFinal = new EvaluacionFinal();
		if (evaluacionFinalId != 0) {
			evaluacionFinal.setEvaluacionFinalID(evaluacionFinalId);
		}
		evaluacionFinal.setComentario(comentarioEvaluacionFinal);
		evaluacionFinal.setDescripcionEf(descripcionEfService
				.findDescripcionEfById(sltDescripcionTipoEvaluacionFinal));
		evaluacionFinal.setInformeFinal(informeFinalService
				.findInformeFinalById(informeFinalId));

		evaluacionFinalService.updateEvaluacionFinal(evaluacionFinal);
	}

	@RequestMapping(value = "/principal/cargaGrillaEvaluacionFinal")
	public ModelAndView cargaGrillaEvaluacionFinal(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect", "showGestionarInformeFinal.jspx");

		List<EvaluacionFinalBean> listEvaluacionFinalBean =evaluacionFinalService.llenaListEvaluacionFinalBean( evaluacionFinalService
				.llenaCompletaListEvaluacionFinal(evaluacionFinalService
						.findEvaluacionFinalByInformeFinalId(informeFinalId)));

		model.put("listEvaluacionFinal", listEvaluacionFinalBean);

		return new ModelAndView("divGrillaEvaluacionFinalInformeFinal", model);
	}

	@RequestMapping(value = "/principal/grabarLeccionAprendida")
	public void grabarLeccionAprendida(
			@RequestParam(required = false, value = "sltTipoLeccion") Integer sltTipoLeccion,
			@RequestParam(required = false, value = "comentarioLeccionAprendida") String comentarioLeccionAprendida,
			@RequestParam(required = false, value = "leccionApendidaId") Integer leccionApendidaId,
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			HttpServletRequest request, HttpServletResponse response) {

		LeccionApendida leccionApendida = new LeccionApendida();
		if (leccionApendidaId != 0) {
			leccionApendida.setLeccionApendidaID(leccionApendidaId);
		}
		leccionApendida.setComentario(comentarioLeccionAprendida);
		leccionApendida.setFkIdtablaespTipoLeccion(sltTipoLeccion);
		leccionApendida.setInformeFinal(informeFinalService
				.findInformeFinalById(informeFinalId));

		leccionApendidaService.updateLeccionApendida(leccionApendida);
	}
	
	@RequestMapping(value = "/principal/cargaGrillaLeccionAprendida")
	public ModelAndView cargaGrillaLeccionAprendida(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect", "showGestionarInformeFinal.jspx");

		List<LeccionApendidaBean> listLeccionApendidaBean =leccionApendidaService.llenaListLeccionApendidaBean( leccionApendidaService
				.llenaCompletaListLeccionApendida(leccionApendidaService
						.findLeccionApendidaByInformeFinalId(informeFinalId)));

		model.put("listLeccionApendida", listLeccionApendidaBean);

		return new ModelAndView("divGrillaLeccionApendidaInformeFinal", model);
	}
	
	@RequestMapping(value = "/principal/grabarConclusion")
	public void grabarConclusion(
			@RequestParam(required = false, value = "sltDetalleTipoConclusion") Integer sltDetalleTipoConclusion,
			@RequestParam(required = false, value = "comentarioConclusionInformeFinal") String comentarioConclusionInformeFinal,
			@RequestParam(required = false, value = "conclucionId") Integer conclucionId,
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			HttpServletRequest request, HttpServletResponse response) {

		Conclusion conclusion = new Conclusion();
		if (conclucionId != 0) {
			conclusion.setConclusionID(conclucionId);
		}
		
		conclusion.setComentario(comentarioConclusionInformeFinal);
		conclusion.setDetalleConcluIf(detalleConcluIfService.findDetalleConcluIfById(sltDetalleTipoConclusion));
		conclusion.setInformeFinal(informeFinalService.findInformeFinalById(informeFinalId));
		
		conclusionService.updateConclusion(conclusion);
	}
	
	@RequestMapping(value = "/principal/cargaGrillaConclucionInformeFinal")
	public ModelAndView cargaGrillaConclucionInformeFinal(
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect", "showGestionarInformeFinal.jspx");

		List<ConclusionBean> listConclusionBean =conclusionService.llenaListConclusionBean (conclusionService
				.llenaCompletaListConclusion(conclusionService
						.findconclusionByInformeFinalId(informeFinalId)));

		model.put("listConclusion", listConclusionBean);

		return new ModelAndView("divGrillaConclucionInformeFinal", model);
	}

	@RequestMapping(value = "/principal/cargaGrillaOrganizacionInformeFinal")
	public ModelAndView cargaGrillaOrganizacionInformeFinal(
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect",
				"showGestionarPropuestaTransferencia.jspx");

		// busco List<Organizacion>
		List<OrganizacionBean> listOrganizacionBean = organizacionService.llenaListOrganizacionBean(organizacionService
				.findOrganizacionByDatoProyectoId(datoProyectoId));
		model.put("listOrganizacion", listOrganizacionBean);
		return new ModelAndView("divGrillaOrganizacionInformeFinal", model);
	}
	
	@RequestMapping(value = "/principal/grabarOrganizacionInformeFinal")
	public void grabarOrganizacionInformeFinal(
			@RequestParam(required = false, value = "nombreOrganizacion") String nombreOrganizacion,
			@RequestParam(required = false, value = "situacionFinal") String situacionFinal,
			@RequestParam(required = false, value = "organizacionId") Integer organizacionId,
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Organizacion organizacion = new Organizacion();
		organizacion.setNombreOrganizacion(nombreOrganizacion);
		organizacion.setSituacionFinal(situacionFinal);
		if (organizacionId != 0) {
			organizacion.setOrganizacionID(organizacionId);
		}
		organizacion.setInformeFinal(informeFinalService
				.findInformeFinalById(informeFinalId));
		organizacion.setDatoProyecto(datoProyectoService.findDatoProyectoById(datoProyectoId));

		organizacionService.updateOrganizacion(organizacion);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/cargaGrillaRecursoUtilizadoInformeFinal")
	public ModelAndView cargaGrillaRecursoUtilizadoInformeFinal(
			@RequestParam(required = false, value = "montoRecursoUtilizado") String montoRecursoUtilizado,
			@RequestParam(required = false, value = "sltTipoMonedaRecursoUtilizado") Integer sltTipoMonedaRecursoUtilizado,
			@RequestParam(required = false, value = "fuenteFinanciadora") Integer fuenteFinanciadora,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect",
				"showGestionarPropuestaTransferencia.jspx");
		List<RecursoUtilizado> listRecursoUtilizado = new ArrayList<RecursoUtilizado>();
		if (fuenteFinanciadora != null) {
			RecursoUtilizado recursoUtilizado = new RecursoUtilizado();
			recursoUtilizado
					.setFkIdtablaespTipoMoneda(sltTipoMonedaRecursoUtilizado);
			recursoUtilizado.setFuenteFinanciadora(fuenteFinanciadoraService
					.findFuenteFinanciadoraById(fuenteFinanciadora));
			recursoUtilizado.setMonto(Double.valueOf(montoRecursoUtilizado));
			recursoUtilizado.setDescripcionTipoMoneda(tablaEspecificaService
					.findTablaEspecificaById(sltTipoMonedaRecursoUtilizado)
					.getDescripcionCabecera());

			if (request.getSession().getAttribute("listRecursoUtilizado") != null) {
				listRecursoUtilizado = (List<RecursoUtilizado>) request
						.getSession().getAttribute("listRecursoUtilizado");
			}
			int existe = 0;
			String mensaje = "La Fuente Financiadora ya existe en la lista.";
			for (RecursoUtilizado recursoUtilizado2 : listRecursoUtilizado) {
				if (recursoUtilizado2.getFuenteFinanciadora()
						.getFuenteFinanciadoraID() == recursoUtilizado
						.getFuenteFinanciadora().getFuenteFinanciadoraID()) {
					existe = 1;
					model.put("mensaje", mensaje);
					break;
				}
			}
			if (existe == 0) {
				listRecursoUtilizado.add(recursoUtilizado);
				model.put("cantRecursoUtilizado", listRecursoUtilizado.size());
				request.getSession().setAttribute("listRecursoUtilizado",
						listRecursoUtilizado);
			}
		}
		model.put("listRecursoUtilizado", listRecursoUtilizado);
		return new ModelAndView("divGrillaRecursoUtilizadoInformeFinal", model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/eliminarRecursoUtilizadoIF.jspx")
	public void eliminarRecursoUtilizadoIF(
			@RequestParam(required = false, value = "fuenteFinanciadoraID") Integer fuenteFinanciadoraID,
			HttpServletRequest request, HttpServletResponse response) {

		List<RecursoUtilizado> listRecursoUtilizado = new ArrayList<RecursoUtilizado>();
		List<RecursoUtilizado> listRecursoUtilizadoTemp = new ArrayList<RecursoUtilizado>();
		
		if (request.getSession().getAttribute("listRecursoUtilizado") != null) {
			listRecursoUtilizado = (List<RecursoUtilizado>) request
					.getSession().getAttribute("listRecursoUtilizado");
		}
		
		for (RecursoUtilizado recursoUtilizado : listRecursoUtilizado) {
			if (recursoUtilizado.getFuenteFinanciadora()
					.getFuenteFinanciadoraID() != fuenteFinanciadoraID) {
				listRecursoUtilizadoTemp.add(recursoUtilizado);	
			}
		}
		
		request.getSession().setAttribute("listRecursoUtilizado",
					listRecursoUtilizadoTemp);
		
	}
	
	@RequestMapping(value = "/principal/cargaGrillaBienInformeFinal")
	public ModelAndView cargaGrillaBienInformeFinal(
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect",
				"showGestionarPropuestaTransferencia.jspx");

		// busco List<Organizacion>
		List<Bien> listBien = bienService
				.findBienByDatoProyectoId(datoProyectoId);
		model.put("listBien", bienService.llenaListBien(listBien));
		return new ModelAndView("divGrillaBienInformeFinal", model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/grabarBienInformeFinal")
	public void grabarBien(
			@RequestParam(required = false, value = "descripcionBien") String descripcionBien,
			@RequestParam(required = false, value = "sltTipoBien") Integer sltTipoBien,
			@RequestParam(required = false, value = "sltEstadoConservacion") Integer sltEstadoConservacion,
			@RequestParam(required = false, value = "cantidadTotal") Integer cantidadTotal,
			@RequestParam(required = false, value = "sltUnidadMedida") Integer sltUnidadMedida,
			@RequestParam(required = false, value = "cantidadSinTransferir") Integer cantidadSinTransferir,
			@RequestParam(required = false, value = "observacionBien") String observacionBien,
			@RequestParam(required = false, value = "localizacionUbicacion") String localizacionUbicacion,
			@RequestParam(required = false, value = "costoUnitario") Double costoUnitario,
			@RequestParam(required = false, value = "costoTotal") Double costoTotal,
			@RequestParam(required = false, value = "sltTipoMoneda") Integer sltTipoMoneda,
			@RequestParam(required = false, value = "activo") Integer activo,
			@RequestParam(required = false, value = "informeFinalId") Integer informeFinalId,
			@RequestParam(required = false, value = "bienId") Integer bienId,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Bien bien = new Bien();
		if (bienId != 0) {
			bien.setBienID(bienId);
		}
		bien.setActivo(activoService.findActivoById(activo));
		bien.setCantidadSinTransferir(cantidadSinTransferir);
		bien.setCantidadTotal(cantidadTotal);
		bien.setCostoTotal(costoTotal);
		bien.setCostoUnitario(costoUnitario);
		bien.setDatoProyectoId(datoProyectoId);
		bien.setDescripcionBien(descripcionBien);
		bien.setFkIdDetalleEstCabEstadoConservacion(sltEstadoConservacion);
		bien.setFkIdtablaespTipoBien(sltTipoBien);
		bien.setFkIdtablaespTipoMoneda(sltTipoMoneda);
		bien.setFkIdtablaespUnidadMedida(sltUnidadMedida);
		bien.setLocalizacionUbicacion(localizacionUbicacion);
		bien.setObservacion(observacionBien);

		bien = bienService.updateBien(bien);

		InformeFinalBien informeFinalBien = informeFinalBienService
				.findInformeFinalBienByInformeFinalIdByBienId(
						informeFinalId, bien.getBienID());
		if (informeFinalBien == null) {
			InformeFinalBien informeFinalBienSave = new InformeFinalBien();
			informeFinalBienSave.setBien(bien);
			informeFinalBienSave.setInformeFinal(informeFinalService
							.findInformeFinalById(informeFinalId));
			
			informeFinalBienService.updateInformeFinalBien(informeFinalBienSave);
		}

		List<RecursoUtilizado> listRecursoUtilizado = (List<RecursoUtilizado>) request
				.getSession().getAttribute("listRecursoUtilizado");

		for (RecursoUtilizado recursoUtilizado : listRecursoUtilizado) {
			recursoUtilizado.setBien(bien);
			recursoUtilizadoService.updateRecursoUtilizado(recursoUtilizado);

		}
		request.getSession().removeAttribute("listRecursoUtilizado");
	}

	@RequestMapping(value = "/principal/eliminarBienInformeFinal.jspx")
	public void eliminarBienInformeFinal(
			@RequestParam(required = false, value = "bienID") Integer bienID,
			HttpServletRequest request, HttpServletResponse response) {

		List<RecursoUtilizado> listRecursoUtilizado = recursoUtilizadoService.findRecursoUtilizadoByBienId(bienID);
		for (RecursoUtilizado recursoUtilizado : listRecursoUtilizado) {
			recursoUtilizadoService.deleteRecursoUtilizado(recursoUtilizado.getRecursoUtilizadoID());	
		}
		
		propuestaTransferenciaBienService.deletePropuestaTransferenciaBienByBienId(bienID);
		informeFinalBienService.deleteInformeFinalBienByBienId(bienID);
		bienService.deleteBien(bienID);
				
	}
	
	@RequestMapping(value = "/principal/eliminarRegistroInformeFinal")
	public void eliminarRegistroInformeFinal(
			@RequestParam(required = false, value = "idRegistro") Integer idRegistro,
			@RequestParam(required = false, value = "tablaOpcion") String tablaOpcion,
			HttpServletRequest request, HttpServletResponse response) {

		if(tablaOpcion.equals("efectoProyecto")){
			efectoProyectoService.deleteEfectoProyecto(idRegistro);
		}else if(tablaOpcion.equals("materialProducido")){
			materialProducidoService.deleteMaterialProducido(idRegistro);
		}else if(tablaOpcion.equals("organizacion")){
			organizacionService.deleteOrganizacion(idRegistro);
		}else if(tablaOpcion.equals("leccionApendida")){
			leccionApendidaService.deleteLeccionApendida(idRegistro);
		}else if(tablaOpcion.equals("evaluacionFinal")){
			evaluacionFinalService.deleteEvaluacionFinal(idRegistro);
		}else if(tablaOpcion.equals("conclusion")){
			conclusionService.deleteConclusion(idRegistro);
		}
	}
}
