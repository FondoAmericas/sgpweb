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

import pe.com.fondam.sgp.core.bean.ApreciacionResultadoBean;
import pe.com.fondam.sgp.core.bean.AvanceResultadoActividadBean;
import pe.com.fondam.sgp.core.bean.BeneficiariosPorResultadoBean;
import pe.com.fondam.sgp.core.bean.PersonalTecnicoAdministrativoBean;
import pe.com.fondam.sgp.core.bean.ProblemaSolucionBean;
import pe.com.fondam.sgp.core.bean.ReporteAvanceBean;
import pe.com.fondam.sgp.core.bean.ReporteAvanceBeneficiarioBean;
import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.ApreciacionResultado;
import pe.com.fondam.sgp.core.domain.AvanceResultadoActividad;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.MetaPorActividad;
import pe.com.fondam.sgp.core.domain.PersonalTecnicoAdministrativo;
import pe.com.fondam.sgp.core.domain.ProblemaSolucion;
import pe.com.fondam.sgp.core.domain.ReporteAvance;
import pe.com.fondam.sgp.core.domain.ReporteAvanceBeneficiario;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.UbicacionProyecto;
import pe.com.fondam.sgp.core.service.ActividadService;
import pe.com.fondam.sgp.core.service.ApreciacionResultadoService;
import pe.com.fondam.sgp.core.service.AvanceResultadoActividadService;
import pe.com.fondam.sgp.core.service.BeneficiariosPorResultadoService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorActividadService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DepProvDistService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.EstadoCabeceraService;
import pe.com.fondam.sgp.core.service.FuenteFinanciadoraService;
import pe.com.fondam.sgp.core.service.InstitucionService;
import pe.com.fondam.sgp.core.service.MetaPorActividadService;
import pe.com.fondam.sgp.core.service.PersonalTecnicoAdministrativoService;
import pe.com.fondam.sgp.core.service.PlanOperativoService;
import pe.com.fondam.sgp.core.service.ProblemaSolucionService;
import pe.com.fondam.sgp.core.service.ProyectoService;
import pe.com.fondam.sgp.core.service.ReporteAvanceBeneficiarioService;
import pe.com.fondam.sgp.core.service.ReporteAvanceService;
import pe.com.fondam.sgp.core.service.ResultadoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.UbicacionProyectoService;
import pe.com.fondam.sgp.core.service.UtilService;
import pe.com.fondam.sgp.core.util.UtilList;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.controller.security.SecurityController;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class ReporteAvanceController {
	protected final Log logger = LogFactory
			.getLog(ReporteAvanceController.class);
	// ************ Inyecciones ****************
	@Resource
	TablaEspecificaService tablaEspecificaService;

	@Resource
	ProyectoService proyectoService;

	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;

	@Resource
	DatoProyectoService datoProyectoService;

	@Resource
	ReporteAvanceService reporteAvanceService;

	@Resource
	ProblemaSolucionService problemaSolucionService;

	@Resource
	ApreciacionResultadoService apreciacionResultadoService;

	@Resource
	BeneficiariosPorResultadoService beneficiariosPorResultadoService;

	@Resource
	UbicacionProyectoService ubicacionProyectoService;

	@Resource
	ResultadoService resultadoService;

	@Resource
	DepProvDistService depProvDistService;

	@Resource
	ReporteAvanceBeneficiarioService reporteAvanceBeneficiarioService;

	@Resource
	EstadoCabeceraService estadoCabeceraService;

	@Resource
	ActividadService actividadService;

	@Resource
	MetaPorActividadService metaPorActividadService;

	@Resource
	CronogramaMetaPorActividadService cronogramaMetaPorActividadService;

	@Resource
	AvanceResultadoActividadService avanceResultadoActividadService;

	@Resource
	PersonalTecnicoAdministrativoService personalTecnicoAdministrativoService;

	@Resource
	FuenteFinanciadoraService fuenteFinanciadoraService;

	@Resource
	PlanOperativoService planOperativoService;

	@Resource
	InstitucionService institucionService;

	@Resource
	UtilService utilService;
	
	
	// ************ metodos ****************
	@RequestMapping(value = "/principal/showReporteAvance")
	public ModelAndView showReporteAvance(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		if (userSession == null) {
			return SecurityController.autenticateErrorRedirect(request);
		}
		
		//busco el prefijo del estado del plan operativo
		String estadoPrefijoPlanOperativo="xxxx";
		DatoPlanOperativo planOperativo=planOperativoService.findPlanOperativoByDatoProyectoID(userSession.getDatoProyectoID());
		if(planOperativo!=null){
			estadoPrefijoPlanOperativo=detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(planOperativo.getFkIdDetalleEstadoCabEstadoPlanOper()).getPrefijoEstado();
		}
		model.put("estadoPrefijoPlanOperativo",estadoPrefijoPlanOperativo);
		
		model.put("funcionalidadSelect", "showReporteAvance.jspx");
		model.put("datoProyectoId",userSession.getDatoProyectoID());

		model.put("cantMuestraMensajeObs",1);
		
		return new ModelAndView("mostrarReporteAvance", model);
	}

	@RequestMapping(value = "/principal/showCuerpoReporteAvance")
	public ModelAndView showCuerpoReporteAvance(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);

		List<Integer> listPeriodo = new ArrayList<Integer>();
		DatoProyecto datoProyecto = proyectoService
				.findDatoProyectoById(userSession.getDatoProyectoID());
		for (int i = 0; i < datoProyecto.getCantidadPeriodo(); i++) {
			listPeriodo.add(i + 1);
		}
		model.put("listPeriodo", listPeriodo);

		List<DetalleEstadoCabecera> listDetalleEstadoCabecera = detalleEstadoCabeceraService
				.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estrpav");
		model.put("listDetalleEstadoCabecera", listDetalleEstadoCabecera);

		model.put("datoProyectoId", userSession.getDatoProyectoID());
		return new ModelAndView("divCuerpoReporteAvance", model);
	}

	@RequestMapping(value = "/principal/showGrillaReporteAvance")
	public ModelAndView showGrillaReporteAvance(
			@RequestParam(required = true, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		List<ReporteAvance> listReporteAvance = reporteAvanceService
				.findReporteAvanceXDatoProyectoId(datoProyectoId);
		model.put("listReporteAvance",
				llenaListReporteAvanceBean(listReporteAvance));
		
		//estados
		List<DetalleEstadoCabecera> lstDetEstCab = utilService.listarDetalleEstadoCabeceraByPrefijo(FondamConstans.PREFIJO_ESTADO_REPORTE_AVANCE);
		//DetalleEstadoCabecera detalleEstadoCabeceraFinal;	
		List<DetalleEstadoCabecera> lstDetEstCabFinal = new ArrayList<DetalleEstadoCabecera>();
		for(DetalleEstadoCabecera detalleEstadoCabecera : lstDetEstCab ){
			if(detalleEstadoCabecera.getPrefijoEstado().equals("repgen") ||detalleEstadoCabecera.getPrefijoEstado().equals("eval") ){
				DetalleEstadoCabecera detalleEstadoCabeceraFinal = new DetalleEstadoCabecera();
				detalleEstadoCabeceraFinal.setDescripEstado(detalleEstadoCabecera.getDescripEstado());
				detalleEstadoCabeceraFinal.setDetalleEstadoCabeceraID(detalleEstadoCabecera.getDetalleEstadoCabeceraID());	
				lstDetEstCabFinal.add(detalleEstadoCabeceraFinal);
			}
		}

		model.put("lstDetEstCabFinal", lstDetEstCabFinal);
		
		return new ModelAndView("divGrillaReporteAvance", model);
	}

	@RequestMapping(value = "/principal/grabarReporteAvance")
	public void grabarReporteAvance(
			@RequestParam(required = true, value = "sltPeriodo") Integer sltPeriodo,
			@RequestParam(required = true, value = "sltEstadoReporteAvance") Integer sltEstadoReporteAvance,
			@RequestParam(required = true, value = "resumenReporteAvance") String resumenReporteAvance,
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			@RequestParam(required = true, value = "fechaInicio") String fechaInicio,
			@RequestParam(required = true, value = "fechaFin") String fechaFin,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException {

		// Map<String, Object> model = new HashMap<String, Object>();

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);

		ReporteAvance reporteAvance = new ReporteAvance();
		if (reporteAvanceId != 0) {
			reporteAvance.setReporteAvanceID(reporteAvanceId);
		}
		reporteAvance.setDatoProyecto(datoProyectoService
				.findDatoProyectoById(userSession.getDatoProyectoID()));
		reporteAvance.setFkIdDetalleEstadoCabEstRepAvance(34);// Reporte
																// Generado
																// //sltEstadoReporteAvance);
		reporteAvance.setPeriodo(String.valueOf(sltPeriodo));
		reporteAvance
				.setResumen(resumenReporteAvance.length() == 0 ? "Resumen no ingresado aun."
						: resumenReporteAvance);

		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaIni = formateador.parse(fechaInicio);
		Date fechaF = formateador.parse(fechaFin);

		reporteAvance.setFechaInicio(fechaIni);
		reporteAvance.setFechaFin(fechaF);

		reporteAvanceService.saveReporteAvance(reporteAvance);
		
	}
	
	@RequestMapping(value = "/principal/grabarEstadoReporteAvance")
	public void grabarEstadoReporteAvance(
			@RequestParam(required = true, value = "sltEstadoReporteAvance") Integer sltEstadoReporteAvance,
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException {

		// Map<String, Object> model = new HashMap<String, Object>();

		

		ReporteAvance reporteAvance = reporteAvanceService.findReporteAvanceById(reporteAvanceId);
		reporteAvance.setFkIdDetalleEstadoCabEstRepAvance(sltEstadoReporteAvance);
		
		reporteAvanceService.saveReporteAvance(reporteAvance);
		
	}

	@RequestMapping(value = "/principal/cuerpoProblemasSoluciones")
	public ModelAndView cuerpoProblemasSoluciones(
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			@RequestParam(required = true, value = "estado") String estado,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("reporteAvance",
				reporteAvanceService.findReporteAvanceById(reporteAvanceId));
		model.put("estado", estado);
		
		return new ModelAndView("divCuerpoProblemaSolucion", model);
	}

	@RequestMapping(value = "/principal/grillaProblemasSoluciones")
	public ModelAndView grillaProblemasSoluciones(
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		// para traer todos los problemas solucion
		ReporteAvance reporteAvance = reporteAvanceService
				.findReporteAvanceById(reporteAvanceId);
		// List<ReporteAvance> listReporteAvance =
		// reporteAvanceService.findReporteAvanceXDatoProyectoId(reporteAvance.getDatoProyecto().getDatoProyectoID());
		List<ProblemaSolucionBean> listProblemaSolucionBean = reporteAvanceService
				.llenaListProblemaSolucionBean(problemaSolucionService
						.findProblemaSolucionXReporteAvanceId(reporteAvanceId));

		model.put("listProblemaSolucion", listProblemaSolucionBean);
		model.put("reporteAvance", reporteAvance);

		return new ModelAndView("divGrillaProblemaSolucion", model);
	}

	@RequestMapping(value = "/principal/mostrarDetalleProblemasSoluciones")
	public ModelAndView mostrarDetalleProblemasSoluciones(
			@RequestParam(required = true, value = "problemaSolucionId") Integer problemaSolucionId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		// para traer todos los problemas solucion
		ProblemaSolucion problemaSolucion = problemaSolucionService
				.findProblemaSolucionById(problemaSolucionId);

		model.put("problemaSolucion", problemaSolucion);

		return new ModelAndView("divMuestraDetalleProblemaSolucion", model);
	}

	@RequestMapping(value = "/principal/grabarProblemaSolucion")
	public void grabarProblemaSolucion(
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			// @RequestParam(required = true, value = "problemaRelevante")
			// Integer problemaRelevante,
			@RequestParam(required = true, value = "problemaSolucionID") Integer problemaSolucionID,
			@RequestParam(required = true, value = "problema") String problema,
			@RequestParam(required = true, value = "solucion") String solucion,
			@RequestParam(required = true, value = "resultado") String resultado,
			@RequestParam(required = true, value = "comentario") String comentario,
			HttpServletRequest request, HttpServletResponse response) {

		// Map<String, Object> model = new HashMap<String, Object>();
		// para traer todos los problemas solucion
		ProblemaSolucion problemaSolucion = new ProblemaSolucion();
		
		if(problemaSolucionID!=null){
			problemaSolucion.setProblemaSolucionID(problemaSolucionID);
		}
		problemaSolucion.setComentario(comentario.length() > 0 ? comentario
				: "Sin comentario.");
		problemaSolucion.setProblema(problema.length() > 0 ? problema
				: "Sin problema.");
		
		ReporteAvance reporteAvance=reporteAvanceService
		.findReporteAvanceById(reporteAvanceId);
		problemaSolucion.setReporteAvance(reporteAvance);
		problemaSolucion.setResultado(resultado.length() > 0 ? resultado
				: "Sin resultado.");
		problemaSolucion.setSolucion(solucion.length() > 0 ? solucion
				: "Sin solución.");
		problemaSolucion.setProblemaRelevanteAlProy(0);// 0 no relevante, 1
														// relevante
		problemaSolucion.setDatoProyecto(datoProyectoService.findDatoProyectoById(reporteAvance.getDatoProyecto().getDatoProyectoID()));

		problemaSolucionService.updateProblemaSolucion(problemaSolucion);
	}

	@RequestMapping(value = "/principal/eliminarProblemaSolucion.jspx")
	public void eliminarProblemaSolucion(
			@RequestParam(required = true, value = "problemaSolucionID") Integer problemaSolucionID,
			HttpServletRequest request, HttpServletResponse response) {

		//ProblemaSolucion problemaSolucion = problemaSolucionService.findProblemaSolucionById(problemaSolucionID);
		
		problemaSolucionService.deleteProblemaSolucion(problemaSolucionID);
	}
	
	@RequestMapping(value = "/principal/cuerpoApresiacionResultados")
	public ModelAndView cuerpoApresiacionResultados(
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			@RequestParam(required = true, value = "estado") String estado,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("reporteAvance",
				reporteAvanceService.findReporteAvanceById(reporteAvanceId));

		List<TablaEspecifica> listTipoApresiacionResultado = tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("ARRA");
		model.put("listTipoApresiacionResultado", listTipoApresiacionResultado);
		model.put("estado", estado);
		return new ModelAndView("divCuerpoApresiacionResultados", model);
	}

	@RequestMapping(value = "/principal/grillaApresiacionResultados")
	public ModelAndView grillaApresiacionResultados(
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		// List<ApreciacionResultado> listApreciacionResultado = ;
		// ReporteAvance reporteAvance = ;

		model.put(
				"listApreciacionResultado",
				reporteAvanceService
						.llenaListApreciacionResultadoBean(apreciacionResultadoService
								.findApreciacionResultadoXReporteAvanceId(reporteAvanceId)));
		model.put("reporteAvance",
				reporteAvanceService.findReporteAvanceById(reporteAvanceId));
		return new ModelAndView("divGrillaApreciacionResultado", model);
	}

	@RequestMapping(value = "/principal/mostrarDetalleApresiacionResultado")
	public ModelAndView mostrarDetalleApresiacionResultado(
			@RequestParam(required = true, value = "reporteAvanceID") Integer reporteAvanceID,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		// para traer todos los problemas solucion
		List<ApreciacionResultadoBean> listApreciacionResultadoBean = reporteAvanceService
				.llenaListApreciacionResultadoBean(apreciacionResultadoService
						.findApreciacionResultadoXReporteAvanceId(reporteAvanceID));

		model.put("listApreciacionResultadoBean", listApreciacionResultadoBean);

		return new ModelAndView("divMuestraDetalleApreciacionResultado", model);
	}

	@RequestMapping(value = "/principal/grabarApresiacionResultado")
	public void grabarApresiacionResultado(
			@RequestParam(required = true, value = "apreciacionResultadoID") Integer apreciacionResultadoID,
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			@RequestParam(required = true, value = "sltTipoApresiacion") Integer sltTipoApresiacion,
			@RequestParam(required = true, value = "comentario") String comentario,
			HttpServletRequest request, HttpServletResponse response) {

		// Map<String, Object> model = new HashMap<String, Object>();
		// para traer todos los problemas solucion
		ApreciacionResultado apreciacionResultado = new ApreciacionResultado();
		
		apreciacionResultado.setApreciacionResultadoID(apreciacionResultadoID);
		apreciacionResultado.setComentario(comentario);
		apreciacionResultado
				.setFkIdtablaespApreciacionResultadoRa(sltTipoApresiacion);
		apreciacionResultado.setReporteAvance(reporteAvanceService
				.findReporteAvanceById(reporteAvanceId));

		apreciacionResultadoService
				.updateApreciacionResultado(apreciacionResultado);
	}

	@RequestMapping(value = "/principal/eliminarApresiacionResultado.jspx")
	public void eliminarApresiacionResultado(
			@RequestParam(required = true, value = "apreciacionResultadoID") Integer apreciacionResultadoID,
			HttpServletRequest request, HttpServletResponse response) {

		apreciacionResultadoService
				.deleteApreciacionResultado(apreciacionResultadoID);
	}
	
	@RequestMapping(value = "/principal/cuerpoBeneficiariosResultado")
	public ModelAndView cuerpoBeneficiariosResultado(
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			@RequestParam(required = true, value = "estado") String estado,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("reporteAvance",
				reporteAvanceService.findReporteAvanceById(reporteAvanceId));

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);

		List<Resultado> listResultado = resultadoService
				.findResultadoXDatoProyectoID(userSession.getDatoProyectoID());
		model.put("listResultado", listResultado);

		List<TablaEspecifica> listTipoBeneficiario = tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("TPBF");
		model.put("listTipoBeneficiario", listTipoBeneficiario);

		List<TablaEspecifica> listEstrato = tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("ESTT");
		model.put("listEstrato", listEstrato);
		model.put("estado", estado);
		return new ModelAndView("divCuerpoBeneficiariosResultado", model);
	}

	@RequestMapping(value = "/principal/grillaBeneficiarios")
	public ModelAndView grillaBeneficiarios(
			@RequestParam(required = false, value = "reporteAvanceId") Integer reporteAvanceId,
			HttpServletRequest request, HttpServletResponse response) {

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);

		Map<String, Object> model = new HashMap<String, Object>();

		List<UbicacionProyecto> listUbicacionProyecto = ubicacionProyectoService
				.findUbicacionProyectoXDatoProyectoId(userSession
						.getDatoProyectoID());

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
		model.put("reporteAvanceId", reporteAvanceId);

		return new ModelAndView("divGrillaBeneficiarios", model);
	}

	@RequestMapping(value = "/principal/grabarBeneficiario")
	public void grabarBeneficiario(
			@RequestParam(required = true, value = "sltResultado") Integer sltResultadoId,
			@RequestParam(required = true, value = "sltTipoBeneficiario") Integer sltTipoBeneficiarioId,
			@RequestParam(required = true, value = "cantidadProgramada") Integer cantidadProgramada,
			@RequestParam(required = true, value = "sltEstrato") Integer sltEstratoId,
			@RequestParam(required = true, value = "caracteristica") String caracteristica,
			@RequestParam(required = true, value = "descripcion") String descripcion,
			@RequestParam(required = true, value = "cbxDistrito") Integer depProvDistId,
			// @RequestParam(required = true, value = "localizacion") String
			// localizacion,
			HttpServletRequest request, HttpServletResponse response) {

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);

		UbicacionProyecto ubicacionProyecto = ubicacionProyectoService
				.findUbicacionProyectoXDatoProyectoIdXDepProvDistId(
						userSession.getDatoProyectoID(), depProvDistId);

		BeneficiariosPorResultado beneficiariosPorResultado = new BeneficiariosPorResultado();
		beneficiariosPorResultado.setCantidadProgramado(cantidadProgramada);
		beneficiariosPorResultado.setCaracteristicasPoblacion(caracteristica);
		beneficiariosPorResultado.setDescripcion(descripcion);
		beneficiariosPorResultado.setFkidtablaespEstrato(sltEstratoId);
		beneficiariosPorResultado
				.setFkIdtablaespTipoBeneficiario(sltTipoBeneficiarioId);
		beneficiariosPorResultado.setResultado(resultadoService
				.findResultadoByID(sltResultadoId));
		beneficiariosPorResultado.setUbicacionProyecto(ubicacionProyecto);
		beneficiariosPorResultado.setEstadoEliminado(1);

		beneficiariosPorResultadoService
				.saveBeneficiariosPorResultado(beneficiariosPorResultado);

	}

	@RequestMapping(value = "/principal/cuerpoBeneficiariosResultadoLogrado")
	public ModelAndView cuerpoBeneficiariosResultadoLogrado(
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			@RequestParam(required = true, value = "beneficiarioPorResultadoId") Integer beneficiarioPorResultadoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("reporteAvanceId", reporteAvanceId);

		BeneficiariosPorResultadoBean beneficiariosPorResultadoBean = beneficiariosPorResultadoService
				.llenaBeneficiariosPorResultadoBean(beneficiariosPorResultadoService
						.findBeneficiariosPorResultadoById(beneficiarioPorResultadoId));
		model.put("beneficiariosPorResultadoBean",
				beneficiariosPorResultadoBean);

		return new ModelAndView("divCuerpoBeneficiariosResultadoLogrado", model);
	}

	@RequestMapping(value = "/principal/grabarCantidadLograda")
	public void grabarCantidadLograda(
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			@RequestParam(required = true, value = "beneficiariosPorResultadoID") Integer beneficiarioPorResultadoId,
			@RequestParam(required = true, value = "cantidadLograda") Integer cantidadLograda,
			HttpServletRequest request, HttpServletResponse response) {

		// Map<String, Object> model = new HashMap<String, Object>();

		ReporteAvanceBeneficiario reporteAvanceBeneficiario = new ReporteAvanceBeneficiario();

		reporteAvanceBeneficiario
				.setBeneficiariosPorResultado(beneficiariosPorResultadoService
						.findBeneficiariosPorResultadoById(beneficiarioPorResultadoId));
		reporteAvanceBeneficiario.setCantidadLograda(cantidadLograda);
		reporteAvanceBeneficiario.setReporteAvance(reporteAvanceService
				.findReporteAvanceById(reporteAvanceId));
		reporteAvanceBeneficiario.setEstadoEliminado(1);

		reporteAvanceBeneficiarioService
				.saveReporteAvanceBeneficiario(reporteAvanceBeneficiario);
	}

	@RequestMapping(value = "/principal/eliminarReporteAvanceBeneficiario.jspx")
	public void eliminarReporteAvanceBeneficiario(
			@RequestParam(required = true, value = "reporteAvanceBeneficiarioID") Integer reporteAvanceBeneficiarioID,
			HttpServletRequest request, HttpServletResponse response) {

			reporteAvanceBeneficiarioService.deleteReporteAvanceBeneficiario(reporteAvanceBeneficiarioID);
	}
	
	@RequestMapping(value = "/principal/avanceResultadosActividad")
	public ModelAndView avanceResultadosActividad(
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			@RequestParam(required = true, value = "estado") String estado,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("reporteAvance",
				reporteAvanceService.findReporteAvanceById(reporteAvanceId));
		model.put("estado", estado);

		return new ModelAndView("divAvanceResultadosActividad", model);
	}

	@RequestMapping(value = "/principal/cuerpoAvanceResultadosActividad.jspx")
	public ModelAndView  cuerpoAvanceResultadosActividad(
			@RequestParam(required = false, value = "reporteAvanceId") Integer reporteAvanceId,
			//@RequestParam(required = false, value = "resultadoId") Integer resultadoId,
			//@RequestParam(required = false, value = "actividadId") Integer actividadId,
			@RequestParam(required = false, value = "metasXActividadId") Integer metasXActividadId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("reporteAvanceId", reporteAvanceId);

		UserSession userSession = (UserSession) request.getSession().getAttribute(SgpWebConstants.SESSION_USER);

		List<Resultado> listResultado = resultadoService.findResultadoXDatoProyectoID(userSession.getDatoProyectoID());
		model.put("listResultado", listResultado);
		//if (resultadoId != null) {
			//List<Actividad> listActividad = actividadService.findActividadXResultadoId(resultadoId);
			//model.put("listActividad", listActividad);
			//model.put("resultadoId", resultadoId);

			//if (actividadId != null) {
				/*List<MetaPorActividadBean> listMetaPorActividadBean = metaPorActividadService.llenaListMetaPorActividadBean(metaPorActividadService
						.findMetaPorActividadXActividadId(actividadId));
				model.put("listMetaPorActividadBean", listMetaPorActividadBean);
				model.put("actividadId", actividadId);

		List<CronogramaMetaPorActividadBean> listCronogramaMetaPorActividadBean = new ArrayList<CronogramaMetaPorActividadBean>();
				if (metasXActividadId != null) {
					listCronogramaMetaPorActividadBean = cronogramaMetaPorActividadService.llenaCronogramaMetaPorActividadBean(
							cronogramaMetaPorActividadService
									.findCronogramaMetaPorActividadXMetaPorActividadId(metasXActividadId),
							metaPorActividadService
									.findMetaPorActividadById(metasXActividadId));
					model.put("listCronogramaMetaPorActividadBean", UtilList
							.orderAscList(listCronogramaMetaPorActividadBean,
									"periodo"));
					//model.put("metasXActividadId", metasXActividadId);
					model.put("unidadMedida", tablaEspecificaService.findTablaEspecificaById(metaPorActividadService
									.findMetaPorActividadById(metasXActividadId).getFkIdtablaespUnidadMedida()).getDescripcionCabecera());
				}*/
	//		}
		//}
		return new ModelAndView("divCuerpoAvanceResultadosActividad", model);
	}

	@RequestMapping(value = "/principal/grillaAvanceResultadoActividad")
	public ModelAndView grillaAvanceResultadoActividad(
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			@RequestParam(required = false, value = "estado") String estado,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		List<AvanceResultadoActividad> listAvanceResultadoActividad = avanceResultadoActividadService
				.findAvanceResultadoActividadXReporteAvanceId(reporteAvanceId);
		ReporteAvance reporteAvance = reporteAvanceService
				.findReporteAvanceById(reporteAvanceId);

		model.put(
				"listAvanceResultadoActividadBean",
				avanceResultadoActividadService
						.llenaListAvanceResultadoActividadBean(listAvanceResultadoActividad));
		model.put("reporteAvance", reporteAvance);
		model.put("estado", estado);
		return new ModelAndView("divGrillaAvanceResultadoActividad", model);
	}

	@RequestMapping(value = "/principal/grabarAvanceResultadoActividad")
	public void grabarAvanceResultadoActividad(
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			@RequestParam(required = true, value = "avanceResultadoActividadId") Integer avanceResultadoActividadId, 
			@RequestParam(required = true, value = "metaActividadId") Integer metaActividadId,
			@RequestParam(required = true, value = "cantidadAvanceEjecutado") Integer cantidadAvanceEjecutado,
			@RequestParam(required = true, value = "descripcionActividad") String descripcionActividad,
			@RequestParam(required = true, value = "resumenEjecutivo") String resumenEjecutivo,
			@RequestParam(required = true, value = "elementoVerificacion") String elementoVerificacion,
			@RequestParam(required = true, value = "observaciones") String observaciones,
			HttpServletRequest request, HttpServletResponse response) {

		// Map<String, Object> model = new HashMap<String, Object>();

		AvanceResultadoActividad avanceResultadoActividad = new AvanceResultadoActividad();

		avanceResultadoActividad.setAvanceResultadoActividadID(avanceResultadoActividadId);
		avanceResultadoActividad
				.setCantidadAvanceEjecutado(cantidadAvanceEjecutado);
		avanceResultadoActividad
				.setMetaPorActividad(metaPorActividadService.findMetaPorActividadById(metaActividadId));
		avanceResultadoActividad.setDescripcionActividad(descripcionActividad);
		avanceResultadoActividad.setEjecutado(0);
		avanceResultadoActividad.setElementoVerificacion(elementoVerificacion);
		avanceResultadoActividad.setObservaciones(observaciones);
		avanceResultadoActividad.setReporteAvance(reporteAvanceService
				.findReporteAvanceById(reporteAvanceId));
		avanceResultadoActividad.setResumenEjecutivoPeriodo(resumenEjecutivo);
		avanceResultadoActividad.setEstadoEliminado(1);

		avanceResultadoActividadService
				.updateAvanceResultadoActividad(avanceResultadoActividad);

	}

	@RequestMapping(value = "/principal/eliminarAvanceResultadoActividad.jspx")
	public void eliminarAvanceResultadoActividad(
			@RequestParam(required = true, value = "avanceResultadoActividadId") Integer avanceResultadoActividadId, 
			HttpServletRequest request, HttpServletResponse response) {

		avanceResultadoActividadService
				.deleteAvanceResultadoActividad(avanceResultadoActividadId);

	}
	
	@RequestMapping(value = "/principal/mostrarDetalleAvanceResultadoActividad")
	public ModelAndView mostrarDetalleAvanceResultadoActividad(
			@RequestParam(required = true, value = "avanceResultadoActividadId") Integer avanceResultadoActividadId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		// para traer todos los avance resultado actividad
		AvanceResultadoActividadBean avanceResultadoActividadBean = llenaAvanceResultadoActividadBean(avanceResultadoActividadService
				.findAvanceResultadoActividadById(avanceResultadoActividadId));

		model.put("avanceResultadoActividadBean", avanceResultadoActividadBean);

		return new ModelAndView("divMuestraDetalleAvanceResultadosActividad",
				model);
	}

	@RequestMapping(value = "/principal/cuerpoPersonalTecnicoAdministrativo")
	public ModelAndView cuerpoPersonalTecnicoAdministrativo(
			@RequestParam(required = true, value = "reporteAvanceId") Integer reporteAvanceId,
			@RequestParam(required= true, value="estado")String estado,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("reporteAvance",
				reporteAvanceService.findReporteAvanceById(reporteAvanceId));

		model.put("listFormacionProfesional", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("FMPF"));
		model.put("listTiempoDedicado", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("TMDD"));
		model.put("listEtapaPersonal", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("ETPT"));

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		model.put(
				"listPersonalTecnicoReemplazado",
				personalTecnicoAdministrativoService
						.findPersonalTecnicoReemplazadoXDatoProyectoId(userSession
								.getDatoProyectoID()));
		model.put("listTipoMoneda", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("TMND"));

		model.put("listInstitucion", planOperativoService
				.findFuenteFinanciadoraByDatoProyectoID(userSession
						.getDatoProyectoID()));
model.put("estado", estado);
		return new ModelAndView("divCuerpoPersonalTecnicoAdministrativo", model);
	}

	@RequestMapping(value = "/principal/grillaPersonalTecnicoAdministrativo")
	public ModelAndView grillaPersonalTecnicoAdministrativo(
			@RequestParam(required = false, value = "reporteAvanceId") Integer reporteAvanceId,
			HttpServletRequest request, HttpServletResponse response) {

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);

		Map<String, Object> model = new HashMap<String, Object>();

		List<PersonalTecnicoAdministrativoBean> listPersonalTecnicoAdministrativoBean = llenarListPersonalTecnicoAdministrativoBean(personalTecnicoAdministrativoService
				.findPersonalTecnicoAdministrativoByDatoProyectoId(userSession
						.getDatoProyectoID()));

		model.put("listPersonalTecnicoAdministrativoBean",
				listPersonalTecnicoAdministrativoBean);
		model.put("reporteAvanceId", reporteAvanceId);

		return new ModelAndView("divGrillaPersonalTecnicoAdministrativo", model);
	}

	@RequestMapping(value = "/principal/grabarPersonalTecnico.jspx")
	public void grabarPersonalTecnico(
			@RequestParam(required = false, value = "reporteAvanceId") Integer reporteAvanceId,
			@RequestParam(required = false, value = "personalTecnicoAdministrativoID") Integer personalTecnicoAdministrativoID,
			@RequestParam(required = false, value = "nombreCompleto") String nombreCompleto,
			@RequestParam(required = false, value = "sltFormacionProfesional") Integer sltFormacionProfesional,
			@RequestParam(required = false, value = "sltTiempoDedicado") Integer sltTiempoDedicado,
			@RequestParam(required = false, value = "responsabilidad") String responsabilidad,
			@RequestParam(required = false, value = "porcentajeParticipacion") Integer porcentajeParticipacion,
			@RequestParam(required = false, value = "sltEtapaPersonal") Integer sltEtapaPersonal,
			@RequestParam(required = false, value = "sltPersonalTecnicoReemplazado") Integer sltPersonalTecnicoReemplazado,
			@RequestParam(required = false, value = "salario") Double salario,
			@RequestParam(required = false, value = "sltTipoMoneda") Integer sltTipoMoneda,
			@RequestParam(required = false, value = "sltInstitucion") Integer sltInstitucion,
			HttpServletRequest request, HttpServletResponse response) {

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);

		PersonalTecnicoAdministrativo personalTecnicoAdministrativo = new PersonalTecnicoAdministrativo();

		personalTecnicoAdministrativo.setPersonalTecnicoAdministrativoID(personalTecnicoAdministrativoID);
		personalTecnicoAdministrativo.setDatoProyecto(datoProyectoService.findDatoProyectoById(userSession.getDatoProyectoID()));
		personalTecnicoAdministrativo.setNombreCompleto(nombreCompleto);
		personalTecnicoAdministrativo.setFkIdtablaespFormacionProfesional(sltFormacionProfesional);
		personalTecnicoAdministrativo.setResponsabilidad(responsabilidad);
		personalTecnicoAdministrativo.setFkIdtablaespTiempoDedicado(sltTiempoDedicado);
		personalTecnicoAdministrativo.setPorcentageParticipacion(porcentajeParticipacion);
		personalTecnicoAdministrativo.setFkIdtablaespEtapaPersonalTecnico(sltEtapaPersonal);
		if(sltPersonalTecnicoReemplazado!=0){
			personalTecnicoAdministrativo.setFkIdpersonalTecnicoAdmReemplazo(sltPersonalTecnicoReemplazado);
		}
		personalTecnicoAdministrativo.setSalarioMensual(salario);
		personalTecnicoAdministrativo.setFkIdtablaespTipoMoneda(sltTipoMoneda);
		personalTecnicoAdministrativo.setInstitucion(institucionService.findInstitucionById(sltInstitucion));
		//personalTecnicoAdministrativo.setReporteAvance(reporteAvanceService.findReporteAvanceById(reporteAvanceId));
				
		personalTecnicoAdministrativoService.updatePersonalTecnicoAdministrativo(personalTecnicoAdministrativo);

	}
	
	// ************************ listas **************************
	private List<PersonalTecnicoAdministrativoBean> llenarListPersonalTecnicoAdministrativoBean(
			List<PersonalTecnicoAdministrativo> listPersonalTecnicoAdministrativo) {

		List<PersonalTecnicoAdministrativoBean> listPersonalTecnicoAdministrativoBean = new ArrayList<PersonalTecnicoAdministrativoBean>();

		for (PersonalTecnicoAdministrativo personalTecnicoAdministrativo : listPersonalTecnicoAdministrativo) {
			listPersonalTecnicoAdministrativoBean
					.add(llenarPersonalTecnicoAdministrativoBean(personalTecnicoAdministrativo));
		}

		return listPersonalTecnicoAdministrativoBean;
	}

	private PersonalTecnicoAdministrativoBean llenarPersonalTecnicoAdministrativoBean(
			PersonalTecnicoAdministrativo personalTecnicoAdministrativo) {

		PersonalTecnicoAdministrativoBean personalTecnicoAdministrativoBean = new PersonalTecnicoAdministrativoBean();
		/*if(personalTecnicoAdministrativo.getDatoPlanOperativo().equals(null)){
		personalTecnicoAdministrativoBean
		.setDatoPlanOperativo(planOperativoService
				.findPlanOperativoByID(personalTecnicoAdministrativo
						.getDatoPlanOperativo()
						.getDatoPlanOperativoID()));
		}*/
		personalTecnicoAdministrativoBean.setDatoProyecto(personalTecnicoAdministrativo.getDatoProyecto());
		personalTecnicoAdministrativoBean
				.setFkIdtablaespEtapaPersonalTecnico(personalTecnicoAdministrativo
						.getFkIdtablaespEtapaPersonalTecnico());
		personalTecnicoAdministrativoBean
				.setEtapaPersonalTecnicoNombre(tablaEspecificaService
						.findTablaEspecificaById(
								personalTecnicoAdministrativo
										.getFkIdtablaespEtapaPersonalTecnico())
						.getDescripcionCabecera());
		personalTecnicoAdministrativoBean
				.setFkIdpersonalTecnicoAdmReemplazo(personalTecnicoAdministrativo
						.getFkIdpersonalTecnicoAdmReemplazo());
		personalTecnicoAdministrativoBean
				.setPersonalTecnicoAdmReemplazoNombre(personalTecnicoAdministrativo
						.getFkIdpersonalTecnicoAdmReemplazo() == 0 ? "No hay reemplazo"
						: personalTecnicoAdministrativoService
								.findPersonalTecnicoAdministrativoById(
										personalTecnicoAdministrativo
												.getFkIdpersonalTecnicoAdmReemplazo())
								.getNombreCompleto());
		personalTecnicoAdministrativoBean
				.setFkIdtablaespFormacionProfesional(personalTecnicoAdministrativo
						.getFkIdtablaespFormacionProfesional());
		personalTecnicoAdministrativoBean
				.setFormacionProfesionalNombre(tablaEspecificaService
						.findTablaEspecificaById(
								personalTecnicoAdministrativo
										.getFkIdtablaespFormacionProfesional())
						.getDescripcionCabecera());
		personalTecnicoAdministrativoBean
				.setFkIdtablaespTiempoDedicado(personalTecnicoAdministrativo
						.getFkIdtablaespTiempoDedicado());
		personalTecnicoAdministrativoBean
				.setTiempoDedicadoNombre(tablaEspecificaService
						.findTablaEspecificaById(
								personalTecnicoAdministrativo
										.getFkIdtablaespTiempoDedicado())
						.getDescripcionCabecera());
		personalTecnicoAdministrativoBean
				.setFkIdtablaespTipoMoneda(personalTecnicoAdministrativo
						.getFkIdtablaespTipoMoneda());
		personalTecnicoAdministrativoBean
				.setTipoMonedaNombre(tablaEspecificaService
						.findTablaEspecificaById(
								personalTecnicoAdministrativo
										.getFkIdtablaespTipoMoneda())
						.getDescripcionCabecera());
		personalTecnicoAdministrativoBean.setInstitucion(institucionService
				.findInstitucionById(personalTecnicoAdministrativo
						.getInstitucion().getInstitucionID()));
		personalTecnicoAdministrativoBean
				.setNombreCompleto(personalTecnicoAdministrativo
						.getNombreCompleto());
		personalTecnicoAdministrativoBean
				.setPersonalTecnicoAdministrativoID(personalTecnicoAdministrativo
						.getPersonalTecnicoAdministrativoID());
		personalTecnicoAdministrativoBean
				.setPorcentageParticipacion(personalTecnicoAdministrativo
						.getPorcentageParticipacion());
		//personalTecnicoAdministrativoBean.setReporteAvance(reporteAvanceService.findReporteAvanceById(personalTecnicoAdministrativo.getReporteAvance().getReporteAvanceID()));
		personalTecnicoAdministrativoBean
				.setResponsabilidad(personalTecnicoAdministrativo
						.getResponsabilidad());
		personalTecnicoAdministrativoBean
				.setSalarioMensual(personalTecnicoAdministrativo
						.getSalarioMensual());

		return personalTecnicoAdministrativoBean;
	}

	/*
	 * private List<AvanceResultadoActividadBean>
	 * llenaListAvanceResultadoActividadBean( List<AvanceResultadoActividad>
	 * listAvanceResultadoActividad) {
	 * 
	 * List<AvanceResultadoActividadBean> listAvanceResultadoActividadBean= new
	 * ArrayList<AvanceResultadoActividadBean>(); for (AvanceResultadoActividad
	 * avanceResultadoActividad : listAvanceResultadoActividad) {
	 * AvanceResultadoActividadBean avanceResultadoActividadBean =
	 * llenaAvanceResultadoActividadBean(avanceResultadoActividad);
	 * 
	 * listAvanceResultadoActividadBean.add(avanceResultadoActividadBean); }
	 * return listAvanceResultadoActividadBean; }
	 */
	private AvanceResultadoActividadBean llenaAvanceResultadoActividadBean(
			AvanceResultadoActividad avanceResultadoActividad) {

		AvanceResultadoActividadBean avanceResultadoActividadBean = new AvanceResultadoActividadBean();

		avanceResultadoActividadBean
				.setAvanceResultadoActividadID(avanceResultadoActividad
						.getAvanceResultadoActividadID());
		avanceResultadoActividadBean
				.setCantidadAvanceEjecutado(avanceResultadoActividad
						.getCantidadAvanceEjecutado());

		MetaPorActividad metaPorActividad = metaPorActividadService
				.findMetaPorActividadById(avanceResultadoActividad
						.getMetaPorActividad()
						.getMetaPorActividadID());
		//metaPorActividad.setMetaPorActividad(metaPorActividadService.findMetaPorActividadById(cronogramaMetaPorActividad.getMetaPorActividad().getMetaPorActividadID()));
		metaPorActividad.setActividad(
						actividadService
								.findActividadById(metaPorActividad.getActividad()
										.getActividadID()));
		metaPorActividad.getActividad()
				.setResultado(
						resultadoService
								.findResultadoByID(metaPorActividad.getActividad()
										.getResultado().getResultadoID()));

		avanceResultadoActividadBean
				.setMetaPorActividad(metaPorActividad);

		avanceResultadoActividadBean
				.setDescripcionUnidadMedida(tablaEspecificaService
						.findTablaEspecificaById(
								metaPorActividad.getFkIdtablaespUnidadMedida())
						.getDescripcionCabecera());
		avanceResultadoActividadBean.setPeriodo(avanceResultadoActividad.getReporteAvance().getPeriodo());

		avanceResultadoActividadBean
				.setDescripcionActividad(avanceResultadoActividad
						.getDescripcionActividad().length() < 200 ? avanceResultadoActividad
						.getDescripcionActividad() : avanceResultadoActividad
						.getDescripcionActividad().substring(0, 200));
		avanceResultadoActividadBean.setEjecutado(avanceResultadoActividad
				.getEjecutado());
		avanceResultadoActividadBean
				.setElementoVerificacion(avanceResultadoActividad
						.getElementoVerificacion().length() < 200 ? avanceResultadoActividad
						.getElementoVerificacion() : avanceResultadoActividad
						.getElementoVerificacion().substring(0, 200));
		avanceResultadoActividadBean.setObservaciones(avanceResultadoActividad
				.getObservaciones().length() < 200 ? avanceResultadoActividad
				.getObservaciones() : avanceResultadoActividad
				.getObservaciones().substring(0, 200));

		avanceResultadoActividadBean.setReporteAvance(reporteAvanceService
				.findReporteAvanceById(avanceResultadoActividad
						.getReporteAvance().getReporteAvanceID()));
		avanceResultadoActividadBean
				.setResumenEjecutivoPeriodo(avanceResultadoActividad
						.getResumenEjecutivoPeriodo().length() < 200 ? avanceResultadoActividad
						.getResumenEjecutivoPeriodo()
						: avanceResultadoActividad.getResumenEjecutivoPeriodo()
								.substring(0, 200));

		return avanceResultadoActividadBean;
	}

	/*
	private List<CronogramaMetaPorActividadBean> llenaCronogramaMetaPorActividadBean(
			List<CronogramaMetaPorActividad> listCronogramaMetaPorActividad,
			MetaPorActividad metaPorActividad) {

		List<CronogramaMetaPorActividadBean> listCronogramaMetaPorActividadBean = new ArrayList<CronogramaMetaPorActividadBean>();

		for (CronogramaMetaPorActividad cronogramaMetaPorActividad : listCronogramaMetaPorActividad) {
			CronogramaMetaPorActividadBean cronogramaMetaPorActividadBean = new CronogramaMetaPorActividadBean();
			if (cronogramaMetaPorActividad
					.getCantidadMetaActividadProgPorPeriodo() != 0) {
				cronogramaMetaPorActividadBean
						.setCantidadMetaActividadProgPorPeriodo(cronogramaMetaPorActividad
								.getCantidadMetaActividadProgPorPeriodo());
				cronogramaMetaPorActividadBean
						.setCronogramaMetaPorActividadID(cronogramaMetaPorActividad
								.getCronogramaMetaPorActividadID());
				cronogramaMetaPorActividadBean
						.setDescripcionUnidadMedida(tablaEspecificaService
								.findTablaEspecificaById(
										metaPorActividad
												.getFkIdtablaespUnidadMedida())
								.getDescripcionCabecera());
				cronogramaMetaPorActividadBean
						.setMetaPorActividad(metaPorActividad);
				cronogramaMetaPorActividadBean
						.setPeriodo(cronogramaMetaPorActividad.getPeriodo());

				listCronogramaMetaPorActividadBean
						.add(cronogramaMetaPorActividadBean);
			}
		}
		return listCronogramaMetaPorActividadBean;
	}*/

	/*
	private List<MetaPorActividadBean> llenaListMetaPorActividadBean(
			List<MetaPorActividad> listMetaPorActividad) {

		List<MetaPorActividadBean> listMetaPorActividadBean = new ArrayList<MetaPorActividadBean>();

		for (MetaPorActividad metaPorActividad : listMetaPorActividad) {
			MetaPorActividadBean metaPorActividadBean = new MetaPorActividadBean();

			metaPorActividadBean.setActividad(actividadService
					.findActividadById(metaPorActividad.getActividad()
							.getActividadID()));
			metaPorActividadBean.setCantidadMetaActividad(metaPorActividad
					.getCantidadMetaActividad());
			metaPorActividadBean
					.setCantidadMetaActividadEjecutada(metaPorActividad
							.getCantidadMetaActividadEjecutada());
			metaPorActividadBean.setContribucionProposito(metaPorActividad
					.getContribucionProposito());
			if (metaPorActividad.getFkIdtablaespTipoIndicadorActividad() != null) {
				metaPorActividadBean
						.setFkIdtablaespTipoIndicadorActividad(metaPorActividad
								.getFkIdtablaespTipoIndicadorActividad());
				metaPorActividadBean
						.setDescripcionTipoIndicadorActividad(tablaEspecificaService
								.findTablaEspecificaById(
										metaPorActividad
												.getFkIdtablaespTipoIndicadorActividad())
								.getDescripcionCabecera());
			}
			metaPorActividadBean.setFkIdtablaespUnidadMedida(metaPorActividad
					.getFkIdtablaespUnidadMedida());
			metaPorActividadBean
					.setDescripcionUnidadMedida(tablaEspecificaService
							.findTablaEspecificaById(
									metaPorActividad
											.getFkIdtablaespUnidadMedida())
							.getDescripcionCabecera());
			metaPorActividadBean.setLogroAlcanzado(metaPorActividad
					.getLogroAlcanzado());
			metaPorActividadBean.setMetaPorActividadID(metaPorActividad
					.getMetaPorActividadID());

			listMetaPorActividadBean.add(metaPorActividadBean);
		}

		return listMetaPorActividadBean;
	}*/

	/*
	 * private List<BeneficiariosPorResultadoBean>
	 * llenaListBeneficiariosPorResultadoBean( List<BeneficiariosPorResultado>
	 * listBeneficiariosPorResultado) {
	 * 
	 * List<BeneficiariosPorResultadoBean> listBeneficiariosPorResultadoBean =
	 * new ArrayList<BeneficiariosPorResultadoBean>(); for
	 * (BeneficiariosPorResultado beneficiariosPorResultado :
	 * listBeneficiariosPorResultado) {
	 * 
	 * BeneficiariosPorResultadoBean beneficiariosPorResultadoBean =
	 * llenaBeneficiariosPorResultadoBean(beneficiariosPorResultado);
	 * 
	 * listBeneficiariosPorResultadoBean .add(beneficiariosPorResultadoBean); }
	 * return listBeneficiariosPorResultadoBean; }
	 */

	/*
	 * private BeneficiariosPorResultadoBean llenaBeneficiariosPorResultadoBean(
	 * BeneficiariosPorResultado beneficiariosPorResultado) {
	 * 
	 * BeneficiariosPorResultadoBean beneficiariosPorResultadoBean = new
	 * BeneficiariosPorResultadoBean();
	 * 
	 * beneficiariosPorResultadoBean
	 * .setBeneficiariosPorResultadoID(beneficiariosPorResultado
	 * .getBeneficiariosPorResultadoID()); beneficiariosPorResultadoBean
	 * .setCantidadProgramado(beneficiariosPorResultado
	 * .getCantidadProgramado()); beneficiariosPorResultadoBean
	 * .setCaracteristicasPoblacion(beneficiariosPorResultado
	 * .getCaracteristicasPoblacion());
	 * beneficiariosPorResultadoBean.setDescripcion(beneficiariosPorResultado
	 * .getDescripcion()); beneficiariosPorResultadoBean
	 * .setDescripcionEstrato(tablaEspecificaService .findTablaEspecificaById(
	 * beneficiariosPorResultado .getFkidtablaespEstrato())
	 * .getDescripcionCabecera()); beneficiariosPorResultadoBean
	 * .setDescripcionTipoBeneficiario(tablaEspecificaService
	 * .findTablaEspecificaById( beneficiariosPorResultado
	 * .getFkIdtablaespTipoBeneficiario()) .getDescripcionCabecera());
	 * beneficiariosPorResultadoBean
	 * .setFkidtablaespEstrato(beneficiariosPorResultado
	 * .getFkidtablaespEstrato()); beneficiariosPorResultadoBean
	 * .setFkIdtablaespTipoBeneficiario(beneficiariosPorResultado
	 * .getFkIdtablaespTipoBeneficiario());
	 * beneficiariosPorResultadoBean.setDepartamento(depProvDistService
	 * .findDescripcionDepProvDist("depa", beneficiariosPorResultado
	 * .getUbicacionProyecto().getDepProvDist()));
	 * beneficiariosPorResultadoBean.setProvincia(depProvDistService
	 * .findDescripcionDepProvDist("prov", beneficiariosPorResultado
	 * .getUbicacionProyecto().getDepProvDist()));
	 * beneficiariosPorResultadoBean.setDistrito(depProvDistService
	 * .findDescripcionDepProvDist("dist", beneficiariosPorResultado
	 * .getUbicacionProyecto().getDepProvDist()));
	 * beneficiariosPorResultadoBean.
	 * setDetalleUbicacion(beneficiariosPorResultado
	 * .getUbicacionProyecto().getDetalleUbicacion());
	 * 
	 * if (beneficiariosPorResultado.getResultado() != null) {
	 * beneficiariosPorResultadoBean .setDescripcionResultado(resultadoService
	 * .findResultadoByID( beneficiariosPorResultado.getResultado()
	 * .getResultadoID()) .getDefinicionResultado());
	 * beneficiariosPorResultadoBean
	 * .setResultadoID(beneficiariosPorResultado.getResultado()
	 * .getResultadoID()); } else { beneficiariosPorResultadoBean
	 * .setDescripcionResultado
	 * ("No se asigno resultado para estos beneficiarios."); }
	 * 
	 * beneficiariosPorResultadoBean
	 * .setFkIdtablaespTipoBeneficiario(beneficiariosPorResultado
	 * .getFkIdtablaespTipoBeneficiario());
	 * 
	 * beneficiariosPorResultadoBean
	 * .setListReporteAvanceBeneficiarioBean(llenaListReporteAvanceBeneficiarioBean
	 * (reporteAvanceBeneficiarioService
	 * .findReporteAvanceBeneficiarioXBeneficiariosPorResultadoId
	 * (beneficiariosPorResultado .getBeneficiariosPorResultadoID())));
	 * 
	 * return beneficiariosPorResultadoBean; }
	 */

	@SuppressWarnings({ "unchecked", "unused" })
	private List<ReporteAvanceBeneficiarioBean> llenaListReporteAvanceBeneficiarioBean(
			List<ReporteAvanceBeneficiario> listReporteAvanceBeneficiario) {

		List<ReporteAvanceBeneficiarioBean> listReporteAvanceBeneficiarioBean = new ArrayList<ReporteAvanceBeneficiarioBean>();
		for (ReporteAvanceBeneficiario reporteAvanceBeneficiario : listReporteAvanceBeneficiario) {
			ReporteAvanceBeneficiarioBean reporteAvanceBeneficiarioBean = new ReporteAvanceBeneficiarioBean();

			reporteAvanceBeneficiarioBean
					.setCantidadLograda(reporteAvanceBeneficiario
							.getCantidadLograda());
			reporteAvanceBeneficiarioBean
					.setReporteAvanceBeneficiarioID(reporteAvanceBeneficiario
							.getReporteAvanceBeneficiarioID());
			reporteAvanceBeneficiarioBean
					.setPeriodoReporte(reporteAvanceService
							.findReporteAvanceById(
									reporteAvanceBeneficiario
											.getReporteAvance()
											.getReporteAvanceID()).getPeriodo());
			reporteAvanceBeneficiarioBean.setReporteAvance(reporteAvanceService
					.findReporteAvanceById(reporteAvanceBeneficiario
							.getReporteAvance().getReporteAvanceID()));

			listReporteAvanceBeneficiarioBean
					.add(reporteAvanceBeneficiarioBean);
		}
		return (List<ReporteAvanceBeneficiarioBean>) UtilList.orderAscList(
				listReporteAvanceBeneficiarioBean, "periodoReporte");
	}

	/*
	 * private List<ApreciacionResultadoBean> llenaListApreciacionResultadoBean(
	 * List<ApreciacionResultado> listApreciacionResultado) {
	 * 
	 * List<ApreciacionResultadoBean> listApreciacionResultadoBean = new
	 * ArrayList<ApreciacionResultadoBean>(); for (ApreciacionResultado
	 * apreciacionResultado : listApreciacionResultado) {
	 * ApreciacionResultadoBean apreciacionResultadoBean =
	 * llenaApreciacionResultadoBean(apreciacionResultado);
	 * 
	 * listApreciacionResultadoBean.add(apreciacionResultadoBean); } return
	 * listApreciacionResultadoBean; }
	 */
	/*
	 * private ApreciacionResultadoBean llenaApreciacionResultadoBean(
	 * ApreciacionResultado apreciacionResultado) {
	 * 
	 * ApreciacionResultadoBean apreciacionResultadoBean = new
	 * ApreciacionResultadoBean();
	 * 
	 * apreciacionResultadoBean.setApreciacionResultadoID(apreciacionResultado
	 * .getApreciacionResultadoID());
	 * apreciacionResultadoBean.setComentario(apreciacionResultado
	 * .getComentario().length() < 200 ? apreciacionResultado .getComentario() :
	 * apreciacionResultado.getComentario() .substring(0, 350));
	 * apreciacionResultadoBean
	 * .setFkIdtablaespApreciacionResultadoRa(apreciacionResultado
	 * .getFkIdtablaespApreciacionResultadoRa()); apreciacionResultadoBean
	 * .setDescripcionTipoApreciacionResultadoRa(tablaEspecificaService
	 * .findTablaEspecificaById( apreciacionResultado
	 * .getFkIdtablaespApreciacionResultadoRa()) .getDescripcionCabecera());
	 * apreciacionResultadoBean.setReporteAvance(reporteAvanceService
	 * .findReporteAvanceById(apreciacionResultado.getReporteAvance()
	 * .getReporteAvanceID()));
	 * 
	 * return apreciacionResultadoBean; }
	 */

	/*
	 * private List<ProblemaSolucionBean> llenaListProblemaSolucionBean(
	 * List<ProblemaSolucion> listProblemaSolucion) {
	 * 
	 * List<ProblemaSolucionBean> listProblemaSolucionBean = new
	 * ArrayList<ProblemaSolucionBean>();
	 * 
	 * for (ProblemaSolucion problemaSolucion : listProblemaSolucion) {
	 * ProblemaSolucionBean problemaSolucionBean = new ProblemaSolucionBean();
	 * 
	 * problemaSolucionBean.setComentario(problemaSolucion.getComentario()
	 * .length() < 200 ? problemaSolucion.getComentario() :
	 * problemaSolucion.getComentario().substring(0, 200));
	 * problemaSolucionBean.setProblema(problemaSolucion.getProblema() .length()
	 * < 200 ? problemaSolucion.getProblema() :
	 * problemaSolucion.getProblema().substring(0, 200));
	 * problemaSolucionBean.setProblemaRelevanteAlProy(problemaSolucion
	 * .getProblemaRelevanteAlProy());
	 * problemaSolucionBean.setProblemaSolucionID(problemaSolucion
	 * .getProblemaSolucionID());
	 * problemaSolucionBean.setReporteAvance(reporteAvanceService
	 * .findReporteAvanceById(problemaSolucion.getReporteAvance()
	 * .getReporteAvanceID()));
	 * problemaSolucionBean.setResultado(problemaSolucion.getResultado()
	 * .length() < 200 ? problemaSolucion.getResultado() :
	 * problemaSolucion.getResultado().substring(0, 200));
	 * problemaSolucionBean.setSolucion(problemaSolucion.getSolucion() .length()
	 * < 200 ? problemaSolucion.getSolucion() :
	 * problemaSolucion.getSolucion().substring(0, 200));
	 * 
	 * listProblemaSolucionBean.add(problemaSolucionBean); } return
	 * listProblemaSolucionBean; }
	 */

	private List<ReporteAvanceBean> llenaListReporteAvanceBean(
			List<ReporteAvance> listReporteAvance) {

		List<ReporteAvanceBean> listReporteAvanceBean = new ArrayList<ReporteAvanceBean>();

		for (ReporteAvance reporteAvance : listReporteAvance) {
			ReporteAvanceBean reporteAvanceBean = new ReporteAvanceBean();

			reporteAvanceBean.setReporteAvanceID(reporteAvance
					.getReporteAvanceID());
			reporteAvanceBean.setDatoProyecto(reporteAvance.getDatoProyecto());
			reporteAvanceBean.setFkIdDetalleEstadoCabEstRepAvance(reporteAvance
					.getFkIdDetalleEstadoCabEstRepAvance());
			reporteAvanceBean
					.setDescripcionEstadoRepAvance(detalleEstadoCabeceraService
							.findDetalleEstadoCabeceraById(
									reporteAvance
											.getFkIdDetalleEstadoCabEstRepAvance())
							.getDescripEstado());
			reporteAvanceBean
			.setPrefijoEstadoRepAvance(detalleEstadoCabeceraService
					.findDetalleEstadoCabeceraById(
							reporteAvance
									.getFkIdDetalleEstadoCabEstRepAvance()).getPrefijoEstado());
			reporteAvanceBean.setPeriodo(reporteAvance.getPeriodo());
			reporteAvanceBean.setResumen(reporteAvance.getResumen());

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			reporteAvanceBean.setFechaInicio(formato.format(reporteAvance
					.getFechaInicio()));
			reporteAvanceBean.setFechaFin(formato.format(reporteAvance
					.getFechaFin()));

			listReporteAvanceBean.add(reporteAvanceBean);
		}
		return listReporteAvanceBean;
	}

}
