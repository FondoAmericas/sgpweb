package pe.com.fondam.sgp.web.controller.principal;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.bean.IndicadorMarcoLogicoBean;
import pe.com.fondam.sgp.core.bean.ValidaCantidadesCompletasPlanOperativo;
import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.ActividadDetallePago;
import pe.com.fondam.sgp.core.domain.ActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.CategoriaActividad;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorActividad;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorResultado;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.IndicadorMarcoLogico;
import pe.com.fondam.sgp.core.domain.IndicadorResultado;
import pe.com.fondam.sgp.core.domain.InfraestructuraPo;
import pe.com.fondam.sgp.core.domain.Institucion;
import pe.com.fondam.sgp.core.domain.MarcoLogico;
import pe.com.fondam.sgp.core.domain.MetaPorActividad;
import pe.com.fondam.sgp.core.domain.PartidaEspecifica;
import pe.com.fondam.sgp.core.domain.PartidaGenerica;
import pe.com.fondam.sgp.core.domain.PersonalTecnicoAdministrativo;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.domain.RubroGenerico;
import pe.com.fondam.sgp.core.domain.SolicitaRpRf;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.TipoCambio;
import pe.com.fondam.sgp.core.form.planOperativo.ActividadForm;
import pe.com.fondam.sgp.core.form.planOperativo.CostoActividadForm;
import pe.com.fondam.sgp.core.form.planOperativo.IndicadorForm;
import pe.com.fondam.sgp.core.form.planOperativo.MetaActividadForm;
import pe.com.fondam.sgp.core.form.planOperativo.PlanOperativoForm;
import pe.com.fondam.sgp.core.form.planOperativo.ResultadoForm;
import pe.com.fondam.sgp.core.helper.PlanOperativoHelper;
import pe.com.fondam.sgp.core.service.ActividadDetallePagoService;
import pe.com.fondam.sgp.core.service.ActividadService;
import pe.com.fondam.sgp.core.service.ActivoService;
import pe.com.fondam.sgp.core.service.BeneficiariosPorResultadoService;
import pe.com.fondam.sgp.core.service.CostoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaCostoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorActividadService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorResultadoService;
import pe.com.fondam.sgp.core.service.DatoPlanOperativoService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DesembolsoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.IndicadorMarcoLogicoService;
import pe.com.fondam.sgp.core.service.IndicadorResultadoService;
import pe.com.fondam.sgp.core.service.InfraestructuraPoService;
import pe.com.fondam.sgp.core.service.MarcoLogicoService;
import pe.com.fondam.sgp.core.service.MetaPorActividadService;
import pe.com.fondam.sgp.core.service.PersonalTecnicoAdministrativoService;
import pe.com.fondam.sgp.core.service.PlanOperativoService;
import pe.com.fondam.sgp.core.service.ReportService;
import pe.com.fondam.sgp.core.service.ResultadoService;
import pe.com.fondam.sgp.core.service.RubroGenericoService;
import pe.com.fondam.sgp.core.service.SolicitaRpRfService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.TipoActividadObligatoriaProgramaService;
import pe.com.fondam.sgp.core.service.TipoCambioService;
import pe.com.fondam.sgp.core.service.UtilService;
import pe.com.fondam.sgp.core.util.UtilList;
import pe.com.fondam.sgp.core.util.UtilValidate;
import pe.com.fondam.sgp.core.view.planOperativo.BeneficiarioResultadoView;
import pe.com.fondam.sgp.core.view.planOperativo.CostoActividadView;
import pe.com.fondam.sgp.core.view.planOperativo.CronogramaCostoActividad2View;
import pe.com.fondam.sgp.core.view.planOperativo.CronogramaMetaActividadView;
import pe.com.fondam.sgp.core.view.planOperativo.IndicadorResultadoView;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class PlanOperativoController {

	private final Log logger = LogFactory.getLog(PlanOperativoController.class);
	private static Long incrementResultadoId = 0L;
	private static List<TablaEspecifica> listaEstratos = new ArrayList<TablaEspecifica>();
	private static List<TablaEspecifica> listaTipoIndicadorResultado = new ArrayList<TablaEspecifica>();
	private static List<TablaEspecifica> listaUnidadMedida = new ArrayList<TablaEspecifica>();
	private static List<TablaEspecifica> listaTipoBeneficiario = new ArrayList<TablaEspecifica>();
	private static List<TablaEspecifica> listaTipoActividad = new ArrayList<TablaEspecifica>();
	private static List<TablaEspecifica> listaActividadTransferencia = new ArrayList<TablaEspecifica>();
	private static List<TablaEspecifica> listaTipoIndicadorActividad = new ArrayList<TablaEspecifica>();
	private static List<TablaEspecifica> listaTipoMoneda = new ArrayList<TablaEspecifica>();

	// *********** inyecciones ********************//
	@Resource
	private PlanOperativoService planOperativoService;

	@Resource
	private UtilService utilService;

	@Resource
	private ReportService reportService;

	@Resource
	private PlanOperativoHelper planOperativoHelper;

	@Resource
	private TipoCambioService tipoCambioService;

	@Resource
	ActivoService activoService;

	@Resource
	TablaEspecificaService tablaEspecificaService;

	@Resource
	CronogramaMetaPorActividadService cronogramaMetaPorActividadService;

	@Resource
	PersonalTecnicoAdministrativoService personalTecnicoAdministrativoService;

	@Resource
	MetaPorActividadService metaPorActividadService;

	@Resource
	ResultadoService resultadoService;

	@Resource
	MarcoLogicoService marcoLogicoService;

	@Resource
	IndicadorMarcoLogicoService indicadorMarcoLogicoService;

	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;

	@Resource
	DatoPlanOperativoService datoPlanOperativoService;

	@Resource
	InfraestructuraPoService infraestructuraPoService;

	@Resource
	RubroGenericoService rubroGenericoService;

	@Autowired
	ActividadService actividadService;

	@Autowired
	BeneficiariosPorResultadoService beneficiariosPorResultadoService;

	@Autowired
	CostoActividadService costoActividadService;

	@Autowired
	IndicadorResultadoService indicadorResultadoService;

	@Autowired
	CronogramaMetaPorResultadoService cronogramaMetaPorResultadoService;

	@Autowired
	CronogramaCostoActividadService cronogramaCostoActividadService;

	@Resource
	SolicitaRpRfService solicitaRpRfService;

	@Resource
	DatoProyectoService datoProyectoService;

	@Resource
	TipoActividadObligatoriaProgramaService tipoActividadObligatoriaProgramaService;
	
	public MetaPorActividadService getMetaPorActividadService() {
		return metaPorActividadService;
	}

	public void setMetaPorActividadService(
			MetaPorActividadService metaPorActividadService) {
		this.metaPorActividadService = metaPorActividadService;
	}

	public PlanOperativoService getPlanOperativoService() {
		return planOperativoService;
	}

	public void setPlanOperativoService(
			PlanOperativoService planOperativoService) {
		this.planOperativoService = planOperativoService;
	}

	public UtilService getUtilService() {
		return utilService;
	}

	public void setUtilService(UtilService utilService) {
		this.utilService = utilService;
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public PlanOperativoHelper getPlanOperativoHelper() {
		return planOperativoHelper;
	}

	public void setPlanOperativoHelper(PlanOperativoHelper planOperativoHelper) {
		this.planOperativoHelper = planOperativoHelper;
	}

	@Resource
	DesembolsoService desembolsoService;

	@Resource
	ActividadDetallePagoService actividadDetallePagoService;

	// ************* metodos ********************//
	@RequestMapping(value = "/principal/showPlanOperativoNew.jspx", method = RequestMethod.GET)
	public ModelAndView showPlanOperativoNew(
			HttpServletRequest request,
			@RequestParam(required = false, value = "datoProyectoID") Integer datoProyectoID,
			@RequestParam(required = false, value = "mensaje") String mensaje,
			@RequestParam(required = false, value = "calculaValidaciones") String calculaValidaciones,
			@RequestParam(required = false, value = "totalValidaciones") Integer totalValidaciones) {

		String alterMessage = null;
		PlanOperativoForm planOperativo = null;
		// List<TipoCambio> listaTipoCambio = null;
		Map<String, Object> model = new HashMap<String, Object>();

		List<DetalleEstadoCabecera> listDetalleEstadoCabecera = detalleEstadoCabeceraService
				.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estplo");
		List<DetalleEstadoCabecera> listDetalleEstadoCabeceraTemp = new ArrayList<DetalleEstadoCabecera>();

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		// logger.info("userSession = " + userSession);
		if (UtilValidate.isNotEmpty(userSession)) {
			datoProyectoID = userSession.getDatoProyectoID();
			// logger.info("datoProyectoID = " + datoProyectoID);
		}

		if (UtilValidate.isNotEmpty(datoProyectoID)) {
			DatoProyecto datoProyecto = planOperativoService
					.findDatoProyectoByDatoProyectoID(datoProyectoID);

			if (UtilValidate.isNotEmpty(datoProyecto)) {
				// Integer estadoId =
				// datoProyecto.getFkIddetallestadocabEstproy();
				// if (13 == estadoId.intValue()) {
				if (datoProyecto.getFkIddetallestadocabEstproy() == 13) {
					planOperativo = planOperativoService
							.obtenerPlanOperativoByProyectoID(datoProyectoID);
					if (planOperativo != null) {
						// listaTipoCambio =
						// tipoCambioService.findTipoCambioByDatoPlanOperativoID(planOperativo.getDatoPlanOperativoID());
						model.put(
								"estadoPlanOperativo",
								detalleEstadoCabeceraService
										.findDetalleEstadoCabeceraById(
												planOperativo
														.getEstadoPlanOperativo())
										.getPrefijoEstado());
						for (DetalleEstadoCabecera detalleEstadoCabecera : listDetalleEstadoCabecera) {
							if ((detalleEstadoCabecera.getPrefijoEstado()
									.equals("elab"))
									|| (detalleEstadoCabecera
											.getPrefijoEstado().equals("peval"))
									|| (detalleEstadoCabecera
											.getPrefijoEstado()
											.equals(detalleEstadoCabeceraService
													.findDetalleEstadoCabeceraById(
															planOperativo
																	.getEstadoPlanOperativo())
													.getPrefijoEstado()))) {
								listDetalleEstadoCabeceraTemp
										.add(detalleEstadoCabecera);
							}
						}

						// busco que proyecto tenga al menos un desembolso
						// aprobado para mostrar la opcion de solicitar Rp y Rf
						Integer cantDesembolso = desembolsoService
								.findDesembolsoByDatoProyectoIDYAprobado(
										datoProyectoID,
										FondamConstans.PREFIJO_ESTADO_DESEMBOLSO_APROBADO,
										FondamConstans.PREFIJO_ESTADO_DESEMBOLSO);
						model.put("cantDesembolso", cantDesembolso);
						// Solicitud de reprogramacion y reformulacion
						datoProyecto.setListSolicitaRpRf(solicitaRpRfService
								.findSolicitaRpRfByDatoProyectoIdByVersionPo(
										datoProyectoID,
										planOperativo.getVersion()));
						model.put("cantSolicitaRpRf", datoProyecto
								.getListSolicitaRpRf().size());
						if (datoProyecto.getListSolicitaRpRf().size() > 0) {
							model.put("solicitaRpRf", datoProyecto
									.getListSolicitaRpRf().get(0));
						}
					}
				} else {
					alterMessage = "El Proyecto no esta aprobado";
				}
			} else {
				alterMessage = "El datoProyectoID " + datoProyectoID
						+ " no existe en la base de datos";
			}
		} else {
			alterMessage = "El usuario no tiene un Proyecto Asociado";
		}
		loadPlanOperativoDetailList();

		model.put("datoProyectoID", datoProyectoID);
		model.put("planOperativo", planOperativo);
		// model.put("listaTipoCambio", listaTipoCambio);
		model.put("listaTipoMoneda", listaTipoMoneda);
		model.put("alterMessage", alterMessage);
		model.put("listEstadoPlanOperativo", listDetalleEstadoCabeceraTemp);
		model.put(
				"listEstadoSolicitudRpRf",
				detalleEstadoCabeceraService
						.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estrprf"));
		model.put("mensaje", mensaje);
		//model.put("mensajeTablaValidaciones", mensajeTablaValidaciones);
		if(calculaValidaciones!=null){
		//if(calculaValidaciones.equals("si") ){
			DatoPlanOperativo datoPlanOperativo = planOperativoService
			.llenaPlanOperativoCompleto(planOperativoService
					.findPlanOperativoByID(planOperativo.getDatoPlanOperativoID()));
			model.put("listValidaCantidadesCompletasPlanOperativo",llenaCajasValidacion( validaCantidadesPlanOperativo(datoPlanOperativo)));
		//}
		}
		model.put("totalValidaciones", totalValidaciones);
		model.put("funcionalidadSelect", "showPlanOperativoNew.jspx");

		model.put("cantMuestraMensajeObs", 1);

		return new ModelAndView("showPlanOperativoNew", model);
	}

	private List<ValidaCantidadesCompletasPlanOperativo> llenaCajasValidacion(
			List<ValidaCantidadesCompletasPlanOperativo> listValidaCantidadesPlanOperativo) {
		
		for (ValidaCantidadesCompletasPlanOperativo validaCantidadesCompletasPlanOperativo : listValidaCantidadesPlanOperativo) {
			validaCantidadesCompletasPlanOperativo.setCantidadCostoActividad(validaCantidadesCompletasPlanOperativo.getCantidadCostoActividad()==null?"-":validaCantidadesCompletasPlanOperativo.getCantidadCostoActividad());
			validaCantidadesCompletasPlanOperativo.setCantidadMetaPorActividad(validaCantidadesCompletasPlanOperativo.getCantidadMetaPorActividad()==null?"-":validaCantidadesCompletasPlanOperativo.getCantidadMetaPorActividad());
			validaCantidadesCompletasPlanOperativo.setCantidadMetaPorResultado(validaCantidadesCompletasPlanOperativo.getCantidadMetaPorResultado()==null?"-":validaCantidadesCompletasPlanOperativo.getCantidadMetaPorResultado());
			validaCantidadesCompletasPlanOperativo.setCodigoActividad(validaCantidadesCompletasPlanOperativo.getCodigoActividad()==null?"-":validaCantidadesCompletasPlanOperativo.getCodigoActividad());
		}
		
		return listValidaCantidadesPlanOperativo;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/showActividad", method = RequestMethod.GET)
	public ModelAndView showActividad(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID,
			@RequestParam(required = false, value = "mensaje") String mensaje,
			@RequestParam(required = false, value = "estadoPlanOperativo") String estadoPlanOperativo) {

		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);
		List<ActividadForm> listActividad = getPlanOperativoService()
				.obtenerListaActividadByResultadoID(resultadoID);
		if (listActividad != null && !listActividad.isEmpty()) {
			listActividad = (List<ActividadForm>) UtilList.orderAscList(
					listActividad, "codigoActividad");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		loadPlanOperativoDetailList();
		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("listActividad", listActividad);

		model.put("listaTipoActividad", listaTipoActividad);
		model.put("listaActividadTransferencia", listaActividadTransferencia);
		model.put("listaTipoIndicadorActividad", listaTipoIndicadorActividad);
		model.put("listaUnidadMedida", listaUnidadMedida);
		model.put("mensaje", mensaje);
		model.put("estadoPlanOperativo", estadoPlanOperativo);

		return new ModelAndView("showActividad", model);
	}

	@RequestMapping(value = "/principal/showBeneficiarioResultado", method = RequestMethod.GET)
	public ModelAndView showBeneficiarioResultado(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID,
			@RequestParam(required = false, value = "mensaje") String mensaje,
			@RequestParam(required = false, value = "estadoPlanOperativo") String estadoPlanOperativo) {

		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);
		List<BeneficiarioResultadoView> listBeneficiarioResultadoView = getPlanOperativoService()
				.findBeneficiarioResultadoByResultadoID(resultadoID);
		
		Map<String, Object> model = new HashMap<String, Object>();
		loadPlanOperativoDetailList();
		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("listaTipoBeneficiario", listaTipoBeneficiario);
		model.put("listaEstratos", listaEstratos);
		model.put("mensaje", mensaje);
		model.put("listBeneficiarioResultadoView",
				listBeneficiarioResultadoView);
		model.put("estadoPlanOperativo", estadoPlanOperativo);

		return new ModelAndView("showBeneficiarioResultado", model);
	}

	@RequestMapping(value = "/principal/showBeneficiarioProyecto", method = RequestMethod.GET)
	public ModelAndView showBeneficiarioProyecto(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID) {

		Map<String, Object> model = new HashMap<String, Object>();

		PlanOperativoForm planOperativo = planOperativoService
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		Integer idPerfil = planOperativoService.findPerfilByDatoProyectoID(
				planOperativo.getDatoProyectoID()).getPerfilID();
		List<BeneficiarioResultadoView> listaBeneficiarioProyecto = planOperativoService
				.findBeneficiariosPorResultado(idPerfil);// planOperativoService.findBeneficiariosPorResultadoByIdPerfil(idPerfil);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);
		// List<BeneficiarioResultadoView> listBeneficiarioResultadoView =
		// getPlanOperativoService().findBeneficiarioResultadoByResultadoID(resultadoID);

		// loadPlanOperativoDetailList();
		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		// model.put("listaTipoBeneficiario", listaTipoBeneficiario);
		// model.put("listaEstratos", listaEstratos);
		model.put("listaBeneficiarioProyecto", listaBeneficiarioProyecto);

		return new ModelAndView("showBeneficiarioProyecto", model);
	}

	@RequestMapping(value = "/principal/showCronogramaResultado", method = RequestMethod.GET)
	public ModelAndView showCronogramaResultado(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID,
			@RequestParam(required = false, value = "mensaje") String mensaje,
			@RequestParam(required = false, value = "estadoPlanOperativo") String estadoPlanOperativo) {

		// String estratoMetaResultado =
		// request.getParameter("estratoMetaResultado");
		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		List<Integer> listPeriodos = new ArrayList<Integer>();
		for (int i = 1; i <= planOperativo.getDatoProyecto()
				.getCantidadPeriodo(); i++) {
			listPeriodos.add(i);
		}
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);
		List<CronogramaMetaPorResultado> listCronogramaResultadoView = getPlanOperativoService()
				.findListaMetaResultadoByResultadoID(resultadoID);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("listCronogramaResultadoView",
				UtilList.orderAscList(listCronogramaResultadoView, "periodo"));
		model.put("listPeriodos", listPeriodos);
		model.put("mensaje", mensaje);
		model.put("estadoPlanOperativo", estadoPlanOperativo);
		return new ModelAndView("showCronogramaResultado", model);
	}

	@RequestMapping(value = "/principal/showIndicadorResultado", method = RequestMethod.GET)
	public ModelAndView showIndicadorResultado(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID,
			@RequestParam(required = false, value = "mensaje") String mensaje,
			@RequestParam(required = false, value = "estadoPlanOperativo") String estadoPlanOperativo) {

		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);
		List<IndicadorResultadoView> listIndicadorResultadoView = getPlanOperativoService()
				.findIndicadorResultadoViewByResultadoID(resultadoID);
		Map<String, Object> model = new HashMap<String, Object>();
		loadPlanOperativoDetailList();
		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("listaTipoIndicadorResultado", listaTipoIndicadorResultado);
		model.put("listaUnidadMedida", listaUnidadMedida);
		model.put("mensaje", mensaje);
		model.put("listIndicadorResultadoView", listIndicadorResultadoView);
		model.put("estadoPlanOperativo", estadoPlanOperativo);

		return new ModelAndView("showIndicadorResultado", model);
	}

	@RequestMapping(value = "/principal/showCostoActividad", method = RequestMethod.GET)
	public ModelAndView showCostoActividad(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID,
			@RequestParam(required = true, value = "actividadID") Integer actividadID,
			// @RequestParam(required = false, value = "categoriaActividadID")
			// Integer categoriaActividadID,
			// @RequestParam(required = false, value = "partidaGenericaID")
			// Integer partidaGenericaID,
			@RequestParam(required = false, value = "alterMessage") String alterMessage,
			@RequestParam(required = false, value = "mensaje") String mensaje,
			@RequestParam(required = false, value = "estadoPlanOperativo") String estadoPlanOperativo) {

		//logger.info("showCostoActividad inicio");

		double costoTotal = 0;
		double financiamientoTotal = 0;

		loadPlanOperativoDetailList();
		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);
		ActividadForm actividad = getPlanOperativoService()
				.obtenerActividadByActividadID(actividadID);
		List<CostoActividadForm> listCostoActividad = getPlanOperativoService()
				.obtenerListaCostoActividadByActividadID(actividadID);

		costoTotal = planOperativoService
				.obtenerCostoTotalCostoActividadByDatoPlanOperativoID(datoPlanOperativoID);
		DatoPlanOperativo datoPlanOperativo = planOperativoService
				.findPlanOperativoByID(datoPlanOperativoID);
		financiamientoTotal = planOperativoService
				.obtenerFinanciamientoTotalByDatoPlanOperativo(datoPlanOperativo);

		Integer tipoActividadId = actividad.getTipoActividadId();
		//logger.info("tipoActividadId = " + tipoActividadId);
		List<CategoriaActividad> listaCategoriaActividad = getUtilService()
				.listaCategoriaActividadByTipoActividad(tipoActividadId);
		//logger.info("listaCategoriaActividad = " + listaCategoriaActividad);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("actividad", actividad);
		model.put("listCostoActividad", listCostoActividad);
		model.put("alterMessage", alterMessage);
		model.put("listaCategoriaActividad", listaCategoriaActividad);

		model.put("listaUnidadMedida", listaUnidadMedida);
		model.put("listaTipoMoneda", listaTipoMoneda);
		model.put("costoTotal", costoTotal);
		model.put("financiamientoTotal", financiamientoTotal);
		model.put("mensaje", mensaje);
		model.put("estadoPlanOperativo", estadoPlanOperativo);
		return new ModelAndView("showCostoActividad", model);
	}

	@RequestMapping(value = "/principal/showMetaActividad", method = RequestMethod.GET)
	public ModelAndView showMetaActividad(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID,
			@RequestParam(required = true, value = "actividadID") Integer actividadID,
			@RequestParam(required = false, value = "alterMessage") String alterMessage,
			@RequestParam(required = false, value = "mensaje") String mensaje,
			@RequestParam(required = false, value = "estadoPlanOperativo") String estadoPlanOperativo) {

		logger.info("showMetaActividad inicio");

		loadPlanOperativoDetailList();

		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);
		ActividadForm actividad = getPlanOperativoService()
				.obtenerActividadByActividadID(actividadID);
		List<MetaActividadForm> listaMetaActividad = planOperativoService
				.obtenerListaMetaActividadByActividadID(actividadID);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("actividad", actividad);
		model.put("listaMetaActividad", listaMetaActividad);
		model.put("alterMessage", alterMessage);
		model.put("mensaje", mensaje);
		model.put("listaUnidadMedida", listaUnidadMedida);
		model.put("listaTipoIndicadorActividad", listaTipoIndicadorActividad);
		model.put("estadoPlanOperativo", estadoPlanOperativo);

		return new ModelAndView("showMetaActividad", model);
	}

	@RequestMapping(value = "/principal/showCronogramaCostoActividad", method = RequestMethod.GET)
	public ModelAndView showCronogramaCostoActividad(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID,
			@RequestParam(required = true, value = "actividadID") Integer actividadID,
			@RequestParam(required = true, value = "costoActividadID") Integer costoActividadID,
			@RequestParam(required = false, value = "estadoPlanOperativo") String estadoPlanOperativo) {

		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);
		ActividadForm actividad = getPlanOperativoService()
				.obtenerActividadByActividadID(actividadID);
		CostoActividadView costoActividad = getPlanOperativoService()
				.findCostoActividadViewByID(costoActividadID);
		List<CronogramaCostoActividad2View> listCronogramaCostoActividad2View = getPlanOperativoService()
				.listCronogramaCostoActividad2ViewByCostoActividadID(
						costoActividadID);

		Integer totalesCantidadTotal = 0;
		Double totalesCostoTotal = (double) 0;
		for (CronogramaCostoActividad2View cronogramaCostoActividad2View : listCronogramaCostoActividad2View) {
			totalesCantidadTotal = totalesCantidadTotal
					+ cronogramaCostoActividad2View.getCantidadTotal();
			totalesCostoTotal = totalesCostoTotal
					+ cronogramaCostoActividad2View.getCostoTotal();
		}
		//logger.info("totalesCantidadTotal = " + totalesCantidadTotal);
		//logger.info("totalesCostoTotal = " + totalesCostoTotal);

		List<Integer> periodos = new ArrayList<Integer>();
		Integer cantidadPerido = Integer.parseInt(planOperativo
				.getCantidadPerido());
		Integer count = 1;
		while (count <= cantidadPerido) {
			periodos.add(count);
			count++;
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("actividad", actividad);
		model.put("costoActividad", costoActividad);
		model.put("totalesCantidadTotal", totalesCantidadTotal);
		model.put("totalesCostoTotal", totalesCostoTotal);
		model.put("cantidadPeridoX2", cantidadPerido * 2);
		model.put("periodos", periodos);
		model.put("listCronogramaCostoActividad",
				listCronogramaCostoActividad2View);
		model.put("estadoPlanOperativo", estadoPlanOperativo);

		return new ModelAndView("showCronogramaCostoActividad", model);
	}

	@RequestMapping(value = "/principal/showCronogramaMetaActividad", method = RequestMethod.GET)
	public ModelAndView showCronogramaMetaActividad(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID,
			@RequestParam(required = true, value = "actividadID") Integer actividadID,
			@RequestParam(required = true, value = "metaActividadID") Integer metaActividadID,
			@RequestParam(required = false, value = "estadoPlanOperativo") String estadoPlanOperativo) {

		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);
		ActividadForm actividad = getPlanOperativoService()
				.obtenerActividadByActividadID(actividadID);
		MetaActividadForm metaActivdad = getPlanOperativoService()
				.obtenerMetaPorActividadById(metaActividadID);
		List<CronogramaMetaActividadView> listCronogramaMetaActividadView = planOperativoService
				.listCronogramaMetaActividadViewByMetaActividadID(metaActividadID);

		Integer totalesCantidadTotal = 0;
		for (CronogramaMetaActividadView cronogramaMetaActividadView : listCronogramaMetaActividadView) {
			totalesCantidadTotal = totalesCantidadTotal
					+ cronogramaMetaActividadView.getCantidadTotal();
		}
		logger.info("totalesCantidadTotal = " + totalesCantidadTotal);

		List<Integer> periodos = new ArrayList<Integer>();
		Integer cantidadPerido = Integer.parseInt(planOperativo
				.getCantidadPerido());
		Integer count = 1;
		while (count <= cantidadPerido) {
			periodos.add(count);
			count++;
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("actividad", actividad);
		model.put("metaActivdad", metaActivdad);
		model.put("totalesCantidadTotal", totalesCantidadTotal);
		model.put("cantidadPeridoX2", cantidadPerido * 2);
		model.put("periodos", periodos);
		model.put("listCronogramaMetaActividadView",
				listCronogramaMetaActividadView);
		model.put("estadoPlanOperativo", estadoPlanOperativo);

		return new ModelAndView("showCronogramaMetaActividad", model);
	}

	@RequestMapping(value = "/principal/cargarCantidadPeriodo", method = RequestMethod.POST)
	public ModelAndView cargarCantidadPeriodo(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID,
			@RequestParam(required = true, value = "actividadID") Integer actividadID,
			@RequestParam(required = true, value = "costoActividadID") Integer costoActividadID,
			@RequestParam(required = true, value = "fuenteFinanciadoraID") Integer fuenteFinanciadoraID) {

		// logger.info("cargarCantidadPeriodo inicio");

		// logger.info("costoActividadID = " + costoActividadID);

		Integer datoProyectoID = null;
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		//logger.info("userSession = " + userSession);
		if (UtilValidate.isNotEmpty(userSession)) {
			datoProyectoID = userSession.getDatoProyectoID();
			//logger.info("datoProyectoID = " + datoProyectoID);
		}

		List<Institucion> listInstitucion = new ArrayList<Institucion>();

		int totalCantidad = 0;
		List<FuenteFinanciadora> listFuenteFinanciadora = getPlanOperativoService()
				.findFuenteFinanciadoraByDatoProyectoID(datoProyectoID);
		for (FuenteFinanciadora fuenteFinanciadora : listFuenteFinanciadora) {
			Institucion institucion = fuenteFinanciadora.getInstitucion();
			listInstitucion.add(institucion);

			if (!fuenteFinanciadora.getFuenteFinanciadoraID().equals(
					fuenteFinanciadoraID)) {
				List<CronogramaCostoActividad> listaCronogramaCostoActividad = planOperativoService
						.findCronogramaCostoActividadByCostoActividadID(
								costoActividadID,
								fuenteFinanciadora.getFuenteFinanciadoraID());
				for (CronogramaCostoActividad cronogramaCostoActividad : listaCronogramaCostoActividad) {
					totalCantidad += cronogramaCostoActividad.getCantidad();
				}
			}
		}

		//
		double costoTotalByFuenteFinanciadora = 0;
		double financiamientoTotal = 0;

		costoTotalByFuenteFinanciadora = planOperativoService
				.obtenerCostoTotalCostoActividadByDatoPlanOperativoIDAndFuenteFinanciera(
						datoPlanOperativoID, fuenteFinanciadoraID,
						costoActividadID);
		DatoPlanOperativo datoPlanOperativo = planOperativoService
				.findPlanOperativoByID(datoPlanOperativoID);
		financiamientoTotal = planOperativoService
				.obtenerFinanciamientoTotalByDatoPlanOperativoAndFuenteFinanciera(
						datoPlanOperativo, fuenteFinanciadoraID);

		Map<String, Object> model = new HashMap<String, Object>();
		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);
		ActividadForm actividad = getPlanOperativoService()
				.obtenerActividadByActividadID(actividadID);
		CostoActividadView costoActividad = getPlanOperativoService()
				.findCostoActividadViewByID(costoActividadID);

		List<CronogramaCostoActividad> listaCronogramaCostoActividad = planOperativoService
				.findCronogramaCostoActividadByCostoActividadID(
						costoActividadID, fuenteFinanciadoraID);

		List<Integer> periodos = new ArrayList<Integer>();
		Integer cantidadPerido = Integer.parseInt(planOperativo
				.getCantidadPerido());
		Integer count = 1;
		while (count <= cantidadPerido) {
			periodos.add(count);
			count++;
		}

		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("actividad", actividad);
		model.put("costoActividad", costoActividad);
		model.put("periodos", periodos);
		model.put("cantidadPerido", cantidadPerido);
		model.put("listFuenteFinanciadora", listFuenteFinanciadora);
		model.put("listInstitucion", listInstitucion);
		model.put("fuenteFinanciadoraID", fuenteFinanciadoraID);
		model.put("totalCantidad", totalCantidad);
		model.put("costoTotalByFuenteFinanciadora",
				costoTotalByFuenteFinanciadora);
		model.put("financiamientoTotal", financiamientoTotal);
		if (listaCronogramaCostoActividad.size() > 0) {
			model.put("operacion", "update");
			model.put("listaCronogramaCostoActividad",
					listaCronogramaCostoActividad);
		}

		logger.info("cargarCantidadPeriodo fin");
		return new ModelAndView("createCronogramaCostoActividad", model);

	}

	@RequestMapping(value = "/principal/showActividades", method = RequestMethod.GET)
	public ModelAndView showActividades() {

		return new ModelAndView("showActividades");
	}

	@RequestMapping(value = "/principal/createResultado", method = RequestMethod.GET)
	public ModelAndView createResultado(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = false, value = "mensaje") String mensaje) {

		// logger.info("createResultado inicio");
		Map<String, Object> model = new HashMap<String, Object>();
		loadPlanOperativoDetailList();
		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		int duracionProyecto = planOperativoService
				.obtenerDatoPlanOperativoByID(datoPlanOperativoID)
				.getDatoProyecto().getDuracionProyecto();
		model.put("planOperativo", planOperativo);
		model.put("listaUnidadMedida", listaUnidadMedida);
		model.put("duracionProyecto", duracionProyecto);
		model.put("mensaje", mensaje);
		// logger.info("createResultado fin");
		return new ModelAndView("createResultado", model);
	}

	@RequestMapping(value = "/principal/modificarResultado.jspx")
	public ModelAndView modificarResultado(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = false, value = "resultadoId") Integer resultadoId) {

		Map<String, Object> model = new HashMap<String, Object>();
		loadPlanOperativoDetailList();
		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		int duracionProyecto = planOperativoService
				.obtenerDatoPlanOperativoByID(datoPlanOperativoID)
				.getDatoProyecto().getDuracionProyecto();
		Resultado resultado = resultadoService
				.llenaResultadoCompleto(resultadoService
						.findResultadoByID(resultadoId));

		model.put("planOperativo", planOperativo);
		model.put("listaUnidadMedida", listaUnidadMedida);
		model.put("duracionProyecto", duracionProyecto);
		model.put("resultado", resultado);

		return new ModelAndView("createResultado", model);
	}

	@RequestMapping(value = "/principal/createMarcoLogicoIndicadores")
	public ModelAndView createMarcoLogicoIndicadores(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoProyectoID") Integer datoProyectoID,
			@RequestParam(required = false, value = "estadoPlanOperativo") String estadoPlanOperativo) {

		Map<String, Object> model = new HashMap<String, Object>();
		MarcoLogico marcoLogico = marcoLogicoService
				.findMarcoLogicoByDatoProyectoID(datoProyectoID);

		// creo marco logico vacio con el id de DatoProyectoId
		if (marcoLogico == null) {
			marcoLogico = marcoLogicoService
					.saveMarcoLogicoVacio(datoProyectoID);
		}

		// request.getSession().removeAttribute("listIndicadorMarcoLogico");

		model.put("marcoLogico", marcoLogico);
		model.put("listUnidadMedida", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("UNMD"));
		model.put("listIndicadorMarcoLogicoBean",
				llenalistIndicadorMarcoLogicoBean(marcoLogico));
		model.put("datoProyectoID", datoProyectoID);
		model.put("estadoPlanOperativo", estadoPlanOperativo);

		return new ModelAndView("createMarcoLogicoIndicadores", model);
	}

	@RequestMapping(value = "/principal/createInfraestructura.jspx")
	public ModelAndView createInfraestructura(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = false, value = "estadoPlanOperativo") String estadoPlanOperativo) {

		Map<String, Object> model = new HashMap<String, Object>();

		// creo infraestructura vacio con el id de datoPlanOperativoID
		/*
		 * InfraestructuraPo infraestructuraPo = infraestructuraPoService
		 * .findInfraestructuraPoByDatoPlanOperativoId(datoPlanOperativoID);//
		 * marcoLogicoService.findMarcoLogicoByDatoProyectoID(datoProyectoID);
		 * if (infraestructuraPo == null) { infraestructuraPo =
		 * infraestructuraPoService
		 * .saveInfraestructuraPoVacio(datoPlanOperativoID); }
		 * if(infraestructuraPo.getActivo()!=null){
		 * 
		 * List<Activo> listActivo = activoService
		 * .findActivoByCategoriaActivoId(infraestructuraPo.getActivo()
		 * .getFkIdtablaespCategoriaActivo()); model.put("listActivo",
		 * listActivo); }
		 */

		model.put("datoPlanOperativoID", datoPlanOperativoID);
		model.put("listCategoriaActivo", utilService.listaCategoriaActivo());
		// model.put("infraestructuraPo", infraestructuraPo);
		model.put("estadoPlanOperativo", estadoPlanOperativo);

		return new ModelAndView("createInfraestructuraPo", model);
	}

	@RequestMapping(value = "/principal/cargaGrillaRecursoEjecutor.jspx")
	public ModelAndView cargaGrillaRecursoEjecutor(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = false, value = "estadoPlanOperativo") String estadoPlanOperativo) {

		Map<String, Object> model = new HashMap<String, Object>();

		DatoPlanOperativo datoPlanOperativo = datoPlanOperativoService
				.findDatoPlanOperativoByID(datoPlanOperativoID);
		List<InfraestructuraPo> listInfraestructuraPo = infraestructuraPoService
				.findInfraestructuraPoByDatoProyectoId(datoPlanOperativo
						.getDatoProyecto().getDatoProyectoID());
		model.put("listInfraestructuraPo", listInfraestructuraPo);

		return new ModelAndView("divGrillaInfraestructuraPo", model);
	}

	@RequestMapping(value = "/principal/createTipoCambio", method = RequestMethod.GET)
	public ModelAndView createTipoCambio(
			HttpServletRequest request,
			@RequestParam(required = false, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = false, value = "desembolsoID") Integer desembolsoID,
			@RequestParam(required = false, value = "mensaje") String mensaje) {

		logger.info("createTipoCambio inicio");

		PlanOperativoForm datoPlanOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);

		Map<String, Object> model = new HashMap<String, Object>();
		loadPlanOperativoDetailList();
		model.put("datoPlanOperativoID", datoPlanOperativoID);
		model.put("desembolsoID", desembolsoID);
		model.put("action1", "actionSaveTipoCambio.jspx");
		model.put("action2", "showPlanOperativoNew.jspx");
		model.put("tipoRefresh",
				FondamConstans.RECARGA_TIPO_CAMBIO_DESDE_BASE_DATOS);
		model.put("listaTipoMoneda", listaTipoMoneda);
		model.put("tipoMonedaID", datoPlanOperativo.getIdTipoMoneda());
		model.put("mensaje", mensaje);
		logger.info("createTipoCambio fin");
		return new ModelAndView("createTipoCambio", model);
	}

	@RequestMapping(value = "/principal/actionDeleteTipoCambio")
	public String actionDeleteTipoCambio(HttpServletRequest request) {
		logger.info("actionDeleteTipoCambio inicio");
		Integer tipoCambioID = Integer.parseInt(request
				.getParameter("tipoCambioID"));
		TipoCambio tipoCambio = tipoCambioService
				.findTipoCambioById(tipoCambioID);
		TipoCambio tipoCambio2 = tipoCambioService
				.findTipoCambioById(tipoCambioID + 1);

		String mensaje = "";
		String resultado = "redirect:showPlanOperativoNew.jspx?";

		try {
			if (tipoCambio != null) {
				if (tipoCambio.getDatoPlanOperativo().getDatoPlanOperativoID() == tipoCambio2
						.getDatoPlanOperativo().getDatoPlanOperativoID()) {
					boolean indicador = false;
					List<Resultado> listResultado = resultadoService
							.findResultadoXDatoPlanOperativoID(tipoCambio
									.getDatoPlanOperativo()
									.getDatoPlanOperativoID());
					for (Resultado resultado2 : listResultado) {
						List<Actividad> lista = actividadService
								.findActividadXResultadoId(resultado2
										.getResultadoID());
						if (lista != null && lista.size() > 0) {
							indicador = true;
							break;
						}

					}

					if (!indicador) {
						tipoCambioService.deleteTipoCambio(tipoCambio);
						tipoCambioService.deleteTipoCambio(tipoCambio2);
						mensaje = "El tipo de cambio se elimino correctamente.";
					} else {
						mensaje = "No se puede eliminar el tipo de cambio porque tiene actividades registradas.";
					}

				} else {
					mensaje = "Error al eliminar el tipo de cambio.";
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		logger.info("actionDeleteTipoCambio fin");
		return resultado + "mensaje=" + mensaje;
	}

	@RequestMapping(value = "/principal/createActividadMeta", method = RequestMethod.GET)
	public ModelAndView createActividadMeta(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID) {
		logger.info("createActividadMeta inicio");
		Map<String, Object> model = new HashMap<String, Object>();
		loadPlanOperativoDetailList();

		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);

		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("listaTipoActividad", listaTipoActividad);
		model.put("listaActividadTransferencia", listaActividadTransferencia);
		model.put("listaTipoIndicadorActividad", listaTipoIndicadorActividad);
		model.put("listaUnidadMedida", listaUnidadMedida);
		logger.info("createActividadMeta fin");
		return new ModelAndView("createActividadMeta", model);
	}

	@RequestMapping(value = "/principal/createBeneficiarioResultado", method = RequestMethod.GET)
	public ModelAndView createBeneficiarioResultado(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID) {
		logger.info("createBeneficiarioResultado inicio");
		Map<String, Object> model = new HashMap<String, Object>();
		loadPlanOperativoDetailList();

		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);

		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("listaTipoBeneficiario", listaTipoBeneficiario);
		model.put("listaEstratos", listaEstratos);
		logger.info("createBeneficiarioResultado fin");
		return new ModelAndView("createBeneficiarioResultado", model);
	}

	@RequestMapping(value = "/principal/createCronogramaResultado", method = RequestMethod.GET)
	public ModelAndView createCronogramaResultado(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID) {
		logger.info("createCronogramaResultado inicio");
		Map<String, Object> model = new HashMap<String, Object>();
		loadPlanOperativoDetailList();

		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);

		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		logger.info("createCronogramaResultado fin");
		return new ModelAndView("createCronogramaResultado", model);
	}

	@RequestMapping(value = "/principal/createIndicadorResultado", method = RequestMethod.GET)
	public ModelAndView createIndicadorResultado(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID) {
		logger.info("createIndicadorResultado inicio");
		Map<String, Object> model = new HashMap<String, Object>();
		loadPlanOperativoDetailList();

		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);

		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("listaTipoIndicadorResultado", listaTipoIndicadorResultado);
		model.put("listaUnidadMedida", listaUnidadMedida);
		logger.info("createIndicadorResultado fin");
		return new ModelAndView("createIndicadorResultado", model);
	}

	@RequestMapping(value = "/principal/createCostoActividad", method = RequestMethod.GET)
	public ModelAndView createCostoActividad(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID,
			@RequestParam(required = true, value = "actividadID") Integer actividadID,
			@RequestParam(required = false, value = "categoriaActividadID") Integer categoriaActividadID,
			@RequestParam(required = false, value = "partidaGenericaID") Integer partidaGenericaID,
			@RequestParam(required = false, value = "alterMessage") String alterMessage) {

		logger.info("createCostoActividad inicio");

		logger.info("categoriaActividadID = " + categoriaActividadID);
		logger.info("partidaGenericaID = " + partidaGenericaID);
		Integer categoriaActividadIDSelected = -1;
		Integer partidaGenericaIDSelected = -1;
		Integer valorRubro = 0;

		loadPlanOperativoDetailList();
		Map<String, Object> model = new HashMap<String, Object>();

		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);
		ActividadForm actividad = getPlanOperativoService()
				.obtenerActividadByActividadID(actividadID);

		Integer tipoActividadId = actividad.getTipoActividadId();
		logger.info("tipoActividadId = " + tipoActividadId);
		List<CategoriaActividad> listaCategoriaActividad = getUtilService()
				.listaCategoriaActividadByTipoActividad(tipoActividadId);
		logger.info("listaCategoriaActividad = " + listaCategoriaActividad);

		List<RubroGenerico> listaRubroGenerico = new ArrayList<RubroGenerico>();
		List<PartidaGenerica> listaPartidaGenerica = new ArrayList<PartidaGenerica>();
		List<PartidaEspecifica> listaPartidaEspecifica = new ArrayList<PartidaEspecifica>();
		if (UtilValidate.isNotEmpty(categoriaActividadID)) {
			categoriaActividadIDSelected = categoriaActividadID;
			logger.info("categoriaActividadIDSelected = "
					+ categoriaActividadIDSelected);
			if (FondamConstans.CATEGORIA_ACTIVIDAD_ID_INVERSION
					.equals(categoriaActividadID.toString())) {
				listaRubroGenerico = getUtilService()
						.listaRubroGenericoByCategoriaActividad(
								categoriaActividadID);
				valorRubro = 1;
			}
			listaPartidaGenerica = getUtilService()
					.listaPartidaGenericaByCategoriaActividad(
							categoriaActividadID);
			if (UtilValidate.isNotEmpty(partidaGenericaID)) {
				partidaGenericaIDSelected = partidaGenericaID;
				logger.info("partidaGenericaIDSelected = "
						+ partidaGenericaIDSelected);
				listaPartidaEspecifica = getUtilService()
						.listaPartidaEspecificaByPartidaGenerica(
								partidaGenericaID);
			}

		}
		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("actividad", actividad);
		model.put("valorRubro", valorRubro);
		model.put("categoriaActividadIDSelected", categoriaActividadIDSelected);
		model.put("partidaGenericaIDSelected", partidaGenericaIDSelected);
		model.put("alterMessage", alterMessage);
		model.put("listaCategoriaActividad", listaCategoriaActividad);
		model.put("listaRubroGenerico", listaRubroGenerico);
		model.put("listaPartidaGenerica", listaPartidaGenerica);
		model.put("listaPartidaEspecifica", listaPartidaEspecifica);
		model.put("listaUnidadMedida", listaUnidadMedida);
		model.put("listaTipoMoneda", listaTipoMoneda);

		logger.info("createCostoActividad fin");
		return new ModelAndView("createCostoActividad", model);
	}

	@RequestMapping(value = "/principal/createCronogramaCostoActividad", method = RequestMethod.GET)
	public ModelAndView createCronogramaCostoActividad(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID,
			@RequestParam(required = true, value = "actividadID") Integer actividadID,
			@RequestParam(required = true, value = "costoActividadID") Integer costoActividadID,
			@RequestParam(required = false, value = "mensaje") String mensaje) {

		//logger.info("createCronogramaCostoActividad inicio");

		//logger.info("costoActividadID = " + costoActividadID);

		Integer datoProyectoID = null;
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		//logger.info("userSession = " + userSession);
		if (UtilValidate.isNotEmpty(userSession)) {
			datoProyectoID = userSession.getDatoProyectoID();
			//logger.info("datoProyectoID = " + datoProyectoID);
		}

		List<Institucion> listInstitucion = new ArrayList<Institucion>();

		List<FuenteFinanciadora> listFuenteFinanciadora = getPlanOperativoService()
				.findFuenteFinanciadoraByDatoProyectoID(datoProyectoID);
		for (FuenteFinanciadora fuenteFinanciadora : listFuenteFinanciadora) {
			Institucion institucion = fuenteFinanciadora.getInstitucion();
			listInstitucion.add(institucion);
		}

		Map<String, Object> model = new HashMap<String, Object>();
		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);
		ActividadForm actividad = getPlanOperativoService()
				.obtenerActividadByActividadID(actividadID);
		CostoActividadView costoActividad = getPlanOperativoService()
				.findCostoActividadViewByID(costoActividadID);

		List<Integer> periodos = new ArrayList<Integer>();
		Integer cantidadPerido = Integer.parseInt(planOperativo
				.getCantidadPerido());
		Integer count = 1;
		while (count <= cantidadPerido) {
			periodos.add(count);
			count++;
		}
		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("actividad", actividad);
		model.put("costoActividad", costoActividad);
		model.put("periodos", periodos);
		model.put("cantidadPerido", cantidadPerido);
		model.put("listFuenteFinanciadora", listFuenteFinanciadora);
		model.put("listInstitucion", listInstitucion);
		model.put("mensaje", mensaje);
		//logger.info("createCronogramaCostoActividad fin");
		return new ModelAndView("createCronogramaCostoActividad", model);
	}

	@RequestMapping(value = "/principal/createCronogramaMetaActividad", method = RequestMethod.GET)
	public ModelAndView createCronogramaMetaActividad(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "resultadoID") Integer resultadoID,
			@RequestParam(required = true, value = "actividadID") Integer actividadID,
			@RequestParam(required = true, value = "metaActividadID") Integer metaActividadID,
			@RequestParam(required = false, value = "mensaje") String mensaje) {

		logger.info("createCronogramaMetaActividad inicio");

		logger.info("metaActividadID = " + metaActividadID);

		Integer datoProyectoID = null;
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		logger.info("userSession = " + userSession);
		if (UtilValidate.isNotEmpty(userSession)) {
			datoProyectoID = userSession.getDatoProyectoID();
			logger.info("datoProyectoID = " + datoProyectoID);
		}

		Map<String, Object> model = new HashMap<String, Object>();
		PlanOperativoForm planOperativo = getPlanOperativoService()
				.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		ResultadoForm resultado = getPlanOperativoService()
				.obtenerResultadoByResultadoID(resultadoID);
		ActividadForm actividad = getPlanOperativoService()
				.obtenerActividadByActividadID(actividadID);
		MetaActividadForm metaActivdad = getPlanOperativoService()
				.obtenerMetaPorActividadById(metaActividadID);
		List<CronogramaMetaActividadView> listCronogramaMetaActividadView = planOperativoService
				.listCronogramaMetaActividadViewByMetaActividadID(metaActividadID);

		List<Integer> periodos = new ArrayList<Integer>();
		Integer cantidadPerido = Integer.parseInt(planOperativo
				.getCantidadPerido());
		Integer count = 1;
		while (count <= cantidadPerido) {
			periodos.add(count);
			count++;
		}
		model.put("planOperativo", planOperativo);
		model.put("resultado", resultado);
		model.put("actividad", actividad);
		model.put("metaActivdad", metaActivdad);
		model.put("periodos", periodos);
		model.put("cantidadPerido", cantidadPerido);
		model.put("mensaje", mensaje);
		if (listCronogramaMetaActividadView.get(0).getListPeriodo().size() > 0) {
			model.put("operacion", "update");
			model.put("listCronogramaMetaActividadView",
					listCronogramaMetaActividadView);
		}

		logger.info("showCronogramaMetaActividad fin");
		return new ModelAndView("createCronogramaMetaActividad", model);
	}

	@RequestMapping(value = "/principal/actionSaveCostoActividad")
	public String actionSaveCostoActividad(HttpServletRequest request) {
		//logger.info("actionSaveCostoActividad inicio");

		Integer rubroGenericoID = null;

		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		Integer actividadID = Integer.parseInt(request
				.getParameter("actividadID"));
		Integer categoriaActividadID = Integer.parseInt(request
				.getParameter("categoriaActividadID"));
		String stringRubroGenericoId = request.getParameter("rubroGenericoID");
		if (UtilValidate.isNotEmpty(stringRubroGenericoId)) {
			rubroGenericoID = Integer.parseInt(stringRubroGenericoId);
		}
		Integer partidaGenericaID = Integer.parseInt(request
				.getParameter("partidaGenericaID"));
		Integer partidaEspecificaID = Integer.parseInt(request
				.getParameter("partidaEspecificaID"));
		Integer unidadMedidaId = Integer.parseInt(request
				.getParameter("unidadMedidaId"));
		Integer cantidadTotal = Integer.parseInt(request
				.getParameter("cantidadTotal"));
		Integer tipoMonedaId = Integer.parseInt(request
				.getParameter("tipoMonedaId"));
		Double precioUnitario = Double.parseDouble(request
				.getParameter("precioUnitario"));
		String detallePartidaGenerica = request
				.getParameter("detallePartidaGenerica");
		String costoActividadID=request.getParameter("costoActividadID");


		CostoActividad costoActividad = new CostoActividad();

		costoActividad.setCostoActividadID(costoActividadID == null ||costoActividadID.equals("") ? null
				: Integer.valueOf(costoActividadID));
		Actividad actividad = getPlanOperativoService().obtenerActividadByID(
				actividadID);
		CategoriaActividad categoriaActividad = getPlanOperativoService()
				.obtenerCategoriaActividadByID(categoriaActividadID);
		RubroGenerico rubroGenerico = null;
		if (UtilValidate.isNotEmpty(rubroGenericoID)) {
			rubroGenerico = getPlanOperativoService().obtenerRubroGenericoByID(
					rubroGenericoID);
		}
		PartidaGenerica partidaGenerica = getPlanOperativoService()
				.obtenerPartidaGenericaByID(partidaGenericaID);
		PartidaEspecifica partidaEspecifica = getPlanOperativoService()
				.obtenerPartidaEspecificaByID(partidaEspecificaID);

		costoActividad.setActividad(actividad);
		costoActividad.setCategoriaActividad(categoriaActividad);
		costoActividad.setRubroGenerico(rubroGenerico);
		costoActividad.setPartidaGenerica(partidaGenerica);
		// costoActividad.setGrupoPartida(partidaGenerica.getGrupoPartida());
		costoActividad.setPartidaEspecifica(partidaEspecifica);
		costoActividad.setFkIdtablaespUnidadMedida(unidadMedidaId);
		costoActividad.setCantidadTotal(cantidadTotal);
		costoActividad.setFkIdtablaespTipoMoneda(tipoMonedaId);
		costoActividad.setPrecioUnitario(precioUnitario);
		costoActividad.setDetallePartidaGenerica(detallePartidaGenerica);
		costoActividad.setEstadoEliminado(1);

		// validacion
		Double montoGrabadoDB = getPlanOperativoHelper()
				.sumCostoActividadByDatoPlanOperativoID(datoPlanOperativoID);
		Double montoGrabar = cantidadTotal * precioUnitario;
		Double montoValidar = montoGrabadoDB + montoGrabar;
		Double montoFuenteFinanciadoras = getPlanOperativoHelper()
				.sumMontoFinanciadoByFuenteFinanciadoras(datoPlanOperativoID);
		String alterMessage = null;
		String redirectPage = null;
		try {
			if (montoValidar.intValue() <= montoFuenteFinanciadoras.intValue()) {
				getPlanOperativoService().createCostoActividad(costoActividad);
				redirectPage = "redirect:showCostoActividad.jspx?datoPlanOperativoID="
						+ datoPlanOperativoID
						+ "&resultadoID="
						+ resultadoID
						+ "&actividadID=" + actividadID;
			} else {
				alterMessage = "No se puede grabar el CostoActividad [Actualmente tiene ingresado  "
						+ montoGrabadoDB
						+ " en la BD, el monto a grabar es "
						+ montoValidar
						+ " , la suma es "
						+ montoValidar
						+ " , supera lo permitido el maximo permitido por fuentes que es "
						+ montoFuenteFinanciadoras + " ]";
				//logger.info("alterMessage = " + alterMessage);
				redirectPage = "redirect:createCostoActividad.jspx?datoPlanOperativoID="
						+ datoPlanOperativoID
						+ "&resultadoID="
						+ resultadoID
						+ "&actividadID="
						+ actividadID
						+ "&alterMessage="
						+ alterMessage;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		logger.info("redirectPage = " + redirectPage);
		logger.info("actionSaveCostoActividad fin");
		return redirectPage;
	}

	@RequestMapping(value = "/principal/actionDeleteCostoActividad")
	public String actionDeleteCostoActividad(HttpServletRequest request) {
		// logger.info("actionDeleteCostoActividad inicio");

		Integer costoActividadID = Integer.parseInt(request
				.getParameter("costoActividadID"));
		CostoActividad costoActividad = costoActividadService
				.findCostoActividadById(costoActividadID);

		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		Integer actividadID = Integer.parseInt(request
				.getParameter("actividadID"));

		String mensaje = "";
		String resultado = "redirect:showCostoActividad.jspx?datoPlanOperativoID="
				+ datoPlanOperativoID
				+ "&resultadoID="
				+ resultadoID
				+ "&actividadID=" + actividadID;

		try {
			if (costoActividad != null) {
				List<CronogramaCostoActividad> listCronogramaCostoActividad = cronogramaCostoActividadService
						.findCronogramaCostoActividadByCostoActividadID(costoActividadID);
				/*
				 * if (!cronogramasCostoActividad.isEmpty()) { return resultado
				 * +
				 * "&mensaje=No se puede eliminar el costo actividad porque tiene cronogramas costo actividad registradas."
				 * ; }
				 * costoActividadService.deleteCostoActividad(costoActividad);
				 * mensaje = "El costo actividad se elimino correctamente.";
				 */
				Integer cantCCAEnProcesoDeLiquidacion = 0;
				for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
					List<ActividadDetallePago> listActividadDetallePago = actividadDetallePagoService
							.findActividadDetallePagoByCronogramaCostoActividadId(cronogramaCostoActividad
									.getCronogramaCostoActividadID());
					if (listActividadDetallePago.size() > 0) {
						cantCCAEnProcesoDeLiquidacion += 1;
					}
				}

				if (cantCCAEnProcesoDeLiquidacion == 0) {
					for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
						cronogramaCostoActividadService
								.deleteCronogramaCostoActividad(cronogramaCostoActividad
										.getCronogramaCostoActividadID());
						// cronogramaMetaPorActividadService.deleteCronorgramaMetaPorActividad(cronogramaMetaPorActividad.getCronogramaMetaPorActividadID());
					}

					costoActividadService.deleteCostoActividad(costoActividad);
					mensaje = "El costo de la actividad se elimino correctamente.";
				} else {
					return resultado
							+ "&mensaje=El costo de la actividad tiene cronogramas ya declarados en liquidaciones y no se puede eliminar.";
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		logger.info("actionDeleteCostoActividad fin");
		return resultado + "&mensaje=" + mensaje;
	}

	@RequestMapping(value = "/principal/actionSaveCronogramaCostoActividad")
	public String actionSaveCronogramaCostoActividad(HttpServletRequest request) {
		logger.info("actionSaveCronogramaCostoActividad inicio");

		logger.info("getParameterMap = " + request.getParameterMap());

		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		Integer actividadID = Integer.parseInt(request
				.getParameter("actividadID"));
		Integer costoActividadID = Integer.parseInt(request
				.getParameter("costoActividadID"));
		Integer cantidadPerido = Integer.parseInt(request
				.getParameter("cantidadPerido"));
		Integer fuenteFinanciadoraID = Integer.parseInt(request
				.getParameter("fuenteFinanciadoraID"));
		String operacion = request.getParameter("operacion");
		Integer count = 1;
		String mensaje = "Se registro correctamente el cronograma";
		if (!("update").equals(operacion)) {

			Map<Object, Object> inputMap = new HashMap<Object, Object>();
			while (count <= cantidadPerido) {
				String periodo = request.getParameter("periodo" + count);
				String cantidad = request.getParameter("cantidadPeriodo"
						+ count);
				// int cantidadPeriodo=0;
				if (cantidad.length() > 0) {
					inputMap.put(periodo, Integer.parseInt(cantidad));
				} else {
					inputMap.put(periodo, 0);
				}
				count++;
			}
			CronogramaCostoActividad cronogramaCostoActividad = null;
			CostoActividad costoActividad = getPlanOperativoService()
					.findCostoActividadByID(costoActividadID);
			FuenteFinanciadora fuenteFinanciadora = getPlanOperativoService()
					.findFuenteFinanciadoraById(fuenteFinanciadoraID);
			try {
				logger.info("Iniciando Grabacion");
				count = 1;
				while (count <= cantidadPerido) {
					logger.info("Grabando registro por periodo" + count);
					String periodo = count.toString();
					Integer cantidad = (Integer) inputMap.get(periodo);
					cronogramaCostoActividad = new CronogramaCostoActividad();
					cronogramaCostoActividad.setCostoActividad(costoActividad);
					cronogramaCostoActividad.setPeriodo(periodo);
					cronogramaCostoActividad.setCantidad(cantidad);
					cronogramaCostoActividad
							.setFuenteFinanciadora(fuenteFinanciadora);
					cronogramaCostoActividad.setEjecutado(0);
					cronogramaCostoActividad.setEstadoLiquidacion("0");
					cronogramaCostoActividad.setEstadoEliminado(1);
					getPlanOperativoService().createCronogramaCostoActividad(
							cronogramaCostoActividad);
					count++;
				}
				logger.info("Finalizando Grabacion");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("ERROR =" + e);
				mensaje = "Se produjo un error";
			}

		} else {
			mensaje = "Se actualizo correctamente el cronograma";
			List<CronogramaCostoActividad> listaCronogramaCostoActividad = planOperativoService
					.findCronogramaCostoActividadByCostoActividadID(
							costoActividadID, fuenteFinanciadoraID);
			for (CronogramaCostoActividad cronogramaCostoActividad : listaCronogramaCostoActividad) {
				String cantidad = request.getParameter("cantidadPeriodo"
						+ count);
				// Integer cantidadPeriodo=0;
				if (cantidad.length() > 0) {
					// cantidadPerido=Integer.parseInt(cantidad);
					cronogramaCostoActividad.setCantidad(Integer
							.parseInt(cantidad));
				} else {
					cronogramaCostoActividad.setCantidad(0);
				}
				getPlanOperativoService().updateCronogramaCostoActividad(
						cronogramaCostoActividad);
				count++;
			}

		}
		logger.info("actionSaveCronogramaCostoActividad fin");
		return "redirect:createCronogramaCostoActividad.jspx?datoPlanOperativoID="
				+ datoPlanOperativoID
				+ "&resultadoID="
				+ resultadoID
				+ "&actividadID="
				+ actividadID
				+ "&costoActividadID="
				+ costoActividadID + "&mensaje=" + mensaje;
	}

	@RequestMapping(value = "/principal/actionSaveCronogramaMetaActividad")
	public String actionSaveCronogramaMetaActividad(HttpServletRequest request) {
		logger.info("actionSaveCronogramaMetaActividad inicio");
		logger.info("getParameterMap = " + request.getParameterMap());

		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		Integer actividadID = Integer.parseInt(request
				.getParameter("actividadID"));
		Integer metaActividadID = Integer.parseInt(request
				.getParameter("metaActividadID"));
		Integer cantidadPerido = Integer.parseInt(request
				.getParameter("cantidadPerido"));
		String operacion = request.getParameter("operacion");
		Integer count = 1;
		String mensaje = "Se registro correctamente el cronograma";
		if (!("update").equals(operacion)) {

			Map<Object, Object> inputMap = new HashMap<Object, Object>();
			while (count <= cantidadPerido) {
				String periodo = request.getParameter("periodo" + count);
				String cantidad = request.getParameter("cantidadPeriodo"
						+ count);
				// int cantidadPeriodo=0;
				if (cantidad.length() > 0) {
					inputMap.put(periodo, Integer.parseInt(cantidad));
				} else {
					inputMap.put(periodo, 0);
				}

				count++;
			}
			CronogramaMetaPorActividad cronogramaMetaPorActividad = null;
			MetaPorActividad metaPorActividad = metaPorActividadService
					.findMetaPorActividadById(metaActividadID); // planOperativoService.findMetaPorActividadByActividadId(actividadID);

			try {
				logger.info("Iniciando Grabacion");
				count = 1;
				while (count <= cantidadPerido) {
					logger.info("Grabando registro por periodo" + count);
					String periodo = count.toString();
					Integer cantidad = (Integer) inputMap.get(periodo);
					cronogramaMetaPorActividad = new CronogramaMetaPorActividad();
					cronogramaMetaPorActividad.setPeriodo(periodo);
					cronogramaMetaPorActividad
							.setCantidadMetaActividadProgPorPeriodo(cantidad);
					cronogramaMetaPorActividad
							.setMetaPorActividad(metaPorActividad);
					cronogramaMetaPorActividad.setEstadoEliminado(1);
					cronogramaMetaPorActividad.setPeriodoReportado(0);// 0 = no
																		// reportado
																		// , 1=
																		// reportado

					planOperativoService
							.createCronogramaMetaActividad(cronogramaMetaPorActividad);
					count++;
				}
				logger.info("Finalizando Grabacion");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("ERROR =" + e);
				mensaje = "Se produjo un error";
			}

		} else {
			mensaje = "Se actualizo correctamente el cronograma";
			List<CronogramaMetaPorActividad> listaCronogramaMetaActividad = planOperativoService
					.findCronogramaMetaActividadByMetaActividadID(metaActividadID);
			for (CronogramaMetaPorActividad cronogramaMetaPorActividad : listaCronogramaMetaActividad) {
				String cantidad = request.getParameter("cantidadPeriodo"
						+ count);
				// Integer cantidadPeriodo=0;
				if (cantidad.length() > 0) {
					// cantidadPerido=Integer.parseInt(cantidad);
					cronogramaMetaPorActividad
							.setCantidadMetaActividadProgPorPeriodo(Integer
									.parseInt(cantidad));
				} else {
					cronogramaMetaPorActividad
							.setCantidadMetaActividadProgPorPeriodo(0);
				}

				planOperativoService
						.updateCronogramaMetaPorActividad(cronogramaMetaPorActividad);
				count++;
			}

		}

		logger.info("actionSaveCronogramaMetaActividad fin");

		return "redirect:createCronogramaMetaActividad.jspx?datoPlanOperativoID="
				+ datoPlanOperativoID
				+ "&resultadoID="
				+ resultadoID
				+ "&actividadID="
				+ actividadID
				+ "&metaActividadID="
				+ metaActividadID + "&mensaje=" + mensaje;
	}

	@RequestMapping(value = "/principal/actionSaveResultado")
	public String actionSaveResultado(HttpServletRequest request) {
		// logger.info("actionSaveResultado inicio");
		// Integer datoProyectoID =
		// Integer.parseInt(request.getParameter("datoProyectoID"));
		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		String definicionResultado = request
				.getParameter("definicionResultado");
		String supuestoResultado = request.getParameter("supuestoResultado");
		Integer metaResultado = Integer.parseInt(request
				.getParameter("metaResultado"));
		Integer unidadMedidaId = Integer.parseInt(request
				.getParameter("unidadMedidaId"));
		Integer duracionMeses = Integer.parseInt(request
				.getParameter("duracionMeses"));
		String resultadoId = request.getParameter("resultadoId");

		DatoPlanOperativo datoPlanOperativo = getPlanOperativoService()
				.obtenerDatoPlanOperativoByID(datoPlanOperativoID);
		Resultado resultado = new Resultado();
		Resultado resultadoTemp = new Resultado();
		if (!resultadoId.equals("")) {
			resultado.setResultadoID(Integer.valueOf(resultadoId));

			resultadoTemp = resultadoService.findResultadoByID(Integer
					.valueOf(resultadoId));
			resultado.setCodigoResultado(resultadoTemp.getCodigoResultado());
		}
		resultado.setDefinicionResultado(definicionResultado);
		resultado.setSupuestoResultado(supuestoResultado);
		resultado.setMetaResultado(metaResultado);
		resultado.setFkIdtablaespUnidadMedida(unidadMedidaId);
		resultado.setDuracionMeses(duracionMeses);
		resultado.setDatoPlanOperativo(datoPlanOperativo);
		resultado.setEstadoEliminado(1);

		String mensaje = "Se registro correctamente el resultado";

		try {

			if (duracionMeses > datoPlanOperativo.getDatoProyecto()
					.getDuracionProyecto()) {
				mensaje = "La duracion ("
						+ duracionMeses
						+ ") del resultado no puede pasar la duracion del proyecto ("
						+ datoPlanOperativo.getDatoProyecto()
								.getDuracionProyecto() + ")";
				logger.info(mensaje);
			} else {
				getPlanOperativoService().createResultado(resultado);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
			mensaje = "Se produjo un error";
		}
		logger.info("actionSaveResultado fin");
		return "redirect:createResultado.jspx?datoPlanOperativoID= "
				+ datoPlanOperativoID + "&mensaje=" + mensaje;
	}

	@RequestMapping(value = "/principal/actionDeleteResultado")
	public String actionDeleteResultado(HttpServletRequest request) {
		logger.info("actionSaveResultado inicio");
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		Resultado resultado = resultadoService.findResultadoByID(resultadoID);
		String mensaje = "";

		try {
			if (resultado != null) {
				List<Actividad> actividades = actividadService
						.findActividadXResultadoId(resultadoID);
				if (!actividades.isEmpty()) {
					return "redirect:showPlanOperativoNew.jspx?mensaje=No se puede eliminar el resultado porque tiene actividades registradas.";
				}

				List<BeneficiariosPorResultado> beneficiarios = beneficiariosPorResultadoService
						.findBeneficiariosPorResultadoByResultadoID(resultadoID);
				if (!beneficiarios.isEmpty()) {
					return "redirect:showPlanOperativoNew.jspx?mensaje=No se puede eliminar el resultado porque tiene beneficiarios registrados.";
				}

				List<IndicadorResultadoView> indicadores = planOperativoService
						.findIndicadorResultadoViewByResultadoID(resultadoID);
				if (indicadores != null && !indicadores.isEmpty()) {
					return "redirect:showPlanOperativoNew.jspx?mensaje=No se puede eliminar el resultado porque tiene indicadores registrados.";
				}

				List<CronogramaMetaPorResultado> cronogramasMetaPorResultado = planOperativoService
						.findCronogramaMetaPorResultadoByResultadoID(resultadoID);
				if (!cronogramasMetaPorResultado.isEmpty()) {
					return "redirect:showPlanOperativoNew.jspx?mensaje=No se puede eliminar el resultado porque tiene cronograma meta resultados.";
				}

				planOperativoService.deleteResultado(resultadoID);
				mensaje = "El resultado se elimino correctamente.";

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		logger.info("actionSaveResultado fin");
		return "redirect:showPlanOperativoNew.jspx?mensaje=" + mensaje;
	}

	@RequestMapping(value = "/principal/actionSaveTipoCambio")
	public ModelAndView actionSaveTipoCambio(HttpServletRequest request) {

		logger.info("actionSaveTipoCambio inicio");

		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer desembolsoID = (request.getParameter("desembolsoID") == "") ? null
				: Integer.parseInt(request.getParameter("desembolsoID"));
		double tipo_cambio = Double.parseDouble(request
				.getParameter("tipoCambio"));
		Integer tipoMonedaDeId = Integer.parseInt(request
				.getParameter("tipoMonedaDeId"));
		Integer tipoMonedaAId = Integer.parseInt(request
				.getParameter("tipoMonedaAId"));

		logger.info("datoPlanOperativoID = " + datoPlanOperativoID);
		logger.info("datoPlanOperativoID = " + desembolsoID);
		logger.info("tipoCambio = " + tipo_cambio);
		logger.info("tipoMonedaDeId = " + tipoMonedaDeId);
		logger.info("tipoMonedaAId = " + tipoMonedaAId);

		try {

			DatoPlanOperativo datoPlanOperativo = null;
			Desembolso desembolso = null;

			// para registrar un tipo de cambio en el plan operativo
			if (datoPlanOperativoID != null) {
				datoPlanOperativo = planOperativoService
						.obtenerDatoPlanOperativoByID(datoPlanOperativoID);
			}

			// para registrar un tipo de cambio en el desembolso
			if (desembolsoID != null) {
				desembolso = null;// llamar al service de desembolo para obtener
									// el desembolso por desembolsoID
			}

			TipoCambio tipoCambio = new TipoCambio();
			tipoCambio.setTipoCambioID(null);
			tipoCambio.setDatoPlanOperativo(datoPlanOperativo);
			tipoCambio.setDesembolso(desembolso);
			tipoCambio.setTipoCambio(tipo_cambio);
			tipoCambio.setFechaTipoCambio(Calendar.getInstance().getTime());
			tipoCambio.setFkIdtablaespTipoMonedaConvertDE(tipoMonedaDeId);
			tipoCambio.setFkIdtablaespTipoMonedaConvertA(tipoMonedaAId);

			tipoCambioService.createTipoCambio(tipoCambio);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}

		logger.info("actionSaveTipoCambio fin");
		return new ModelAndView("createTipoCambio");
	}

	@RequestMapping(value = "/principal/actionSaveBeneficiarioResultado")
	public String actionSaveBeneficiarioResultado(HttpServletRequest request) {
		// logger.info("actionSaveBeneficiarioResultado inicio");

		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		Integer tipoBeneficiarioId = Integer.parseInt(request
				.getParameter("tipoBeneficiarioId"));
		String caracteristicas = request.getParameter("caracteristicas");
		Integer cantidadProgramado = Integer.parseInt(request
				.getParameter("cantidadProgramado"));
		Integer estratoId = Integer.parseInt(request.getParameter("estratoId"));
		String descripcion = request.getParameter("descripcion");
		String beneficiariosPorResultadoID = request.getParameter("beneficiariosPorResultadoID");

		Resultado resultado = getPlanOperativoService().obtenerResultadoByID(
				resultadoID);
		BeneficiariosPorResultado beneficiariosPorResultado = new BeneficiariosPorResultado();
		
		beneficiariosPorResultado.setBeneficiariosPorResultadoID(beneficiariosPorResultadoID == null ||beneficiariosPorResultadoID.equals("") ? null
				: Integer.valueOf(beneficiariosPorResultadoID));
		beneficiariosPorResultado.setResultado(resultado);
		beneficiariosPorResultado
				.setFkIdtablaespTipoBeneficiario(tipoBeneficiarioId);
		beneficiariosPorResultado.setCaracteristicasPoblacion(caracteristicas);
		beneficiariosPorResultado.setCantidadProgramado(cantidadProgramado);
		beneficiariosPorResultado.setFkidtablaespEstrato(estratoId);
		beneficiariosPorResultado.setDescripcion(descripcion);
		beneficiariosPorResultado.setEstadoEliminado(1);
		try {
			getPlanOperativoService().createBeneficiarioResultado(
					beneficiariosPorResultado);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		logger.info("actionSaveBeneficiarioResultad fin");
		return "redirect:showBeneficiarioResultado.jspx?datoPlanOperativoID= "
				+ datoPlanOperativoID + "&resultadoID=" + resultadoID;
	}

	@RequestMapping(value = "/principal/actionDeleteBeneficiario")
	public String actionDeleteBeneficiario(HttpServletRequest request) {
		//logger.info("actionDeleteBeneficiario inicio");

		Integer beneficiariosPorResultadoID = Integer.parseInt(request
				.getParameter("beneficiariosPorResultadoID"));
		BeneficiariosPorResultado beneficiariosPorResultado = beneficiariosPorResultadoService
				.findBeneficiariosPorResultadoById(beneficiariosPorResultadoID);
		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		String mensaje = "";
		String resultado = "redirect:showBeneficiarioResultado.jspx?datoPlanOperativoID="
				+ datoPlanOperativoID + "&resultadoID=" + resultadoID;
		try {

			if (beneficiariosPorResultado != null) {
				beneficiariosPorResultadoService
						.deleteBeneficiariosPorResultado(beneficiariosPorResultado);
				mensaje = "El beneficiario se elimino correctamente.";
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		//logger.info("actionDeleteBeneficiario fin");
		return resultado + "&mensaje=" + mensaje;
	}

	@RequestMapping(value = "/principal/actionSaveBeneficiarioProyecto")
	public String actionSaveBeneficiarioProyecto(HttpServletRequest request) {
		logger.info("actionSaveBeneficiarioProyecto inicio");

		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		String chkregistro[] = request.getParameterValues("chkregistro");

		// PlanOperativoForm planOperativo =
		// planOperativoService.obtenerPlanOperativoByDatoPlanOperativoID(datoPlanOperativoID);
		Resultado resultado = getPlanOperativoService().obtenerResultadoByID(
				resultadoID);

		logger.info("datoPlanOperativoID = " + datoPlanOperativoID);
		logger.info("resultadoID = " + resultadoID);

		// Integer idPerfil =
		// planOperativoService.findPerfilByDatoProyectoID(planOperativo.getDatoProyectoID()).getPerfilID();
		// List<BeneficiarioResultadoView> listaBeneficiarioProyecto =
		// planOperativoService.findBeneficiariosPorResultadoByIdPerfil(idPerfil);
		List<BeneficiarioResultadoView> listBeneficiarioResultadoView = getPlanOperativoService()
				.findBeneficiarioResultadoByResultadoID(resultadoID);

		try {
			for (String id : chkregistro) {

				BeneficiariosPorResultado beneficiariosPorResultado = planOperativoService
						.findBeneficiariosPorResultadoById(Integer.parseInt(id));
				boolean indicador = true;
				for (BeneficiarioResultadoView beneficiarioResultadoView : listBeneficiarioResultadoView) {
					if (beneficiarioResultadoView.getCantidadProgramado()
							.intValue() == beneficiariosPorResultado
							.getCantidadProgramado().intValue()
							&& beneficiarioResultadoView
									.getCaracteristicasPoblacion()
									.equals(beneficiariosPorResultado
											.getCaracteristicasPoblacion())
							&& beneficiarioResultadoView.getDescripcion()
									.equals(beneficiariosPorResultado
											.getDescripcion())
							&& beneficiarioResultadoView.getEstratoId()
									.intValue() == beneficiariosPorResultado
									.getFkidtablaespEstrato().intValue()
							&& beneficiarioResultadoView
									.getTipoBeneficiarioId().intValue() == beneficiariosPorResultado
									.getFkIdtablaespTipoBeneficiario()
									.intValue()) {
						indicador = false;
						logger.error("ERROR - Insertando Beneficiario "
								+ beneficiariosPorResultado
										.getBeneficiariosPorResultadoID()
								+ " de Proyecto ya existente en el Resultado "
								+ resultado.getResultadoID());
						break;
					}
				}

				if (indicador) {
					beneficiariosPorResultado.setResultado(resultado);
					beneficiariosPorResultadoService
							.updateBeneficiariosPorResultado(beneficiariosPorResultado);

					/*
					 * BeneficiariosPorResultado beneficiariosPorResultadoTmp =
					 * new BeneficiariosPorResultado();
					 * beneficiariosPorResultadoTmp
					 * .setCantidadProgramado(beneficiariosPorResultado
					 * .getCantidadProgramado());
					 * beneficiariosPorResultadoTmp.setCaracteristicasPoblacion
					 * (beneficiariosPorResultado
					 * .getCaracteristicasPoblacion());
					 * beneficiariosPorResultadoTmp
					 * .setDescripcion(beneficiariosPorResultado
					 * .getDescripcion());
					 * beneficiariosPorResultadoTmp.setFkidtablaespEstrato
					 * (beneficiariosPorResultado.getFkidtablaespEstrato());
					 * beneficiariosPorResultadoTmp
					 * .setFkIdtablaespTipoBeneficiario
					 * (beneficiariosPorResultado
					 * .getFkIdtablaespTipoBeneficiario());
					 * beneficiariosPorResultadoTmp.setResultado(resultado);
					 * getPlanOperativoService
					 * ().createBeneficiarioResultado(beneficiariosPorResultadoTmp
					 * );
					 */
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		logger.info("actionSaveBeneficiarioProyecto fin");
		return "redirect:showBeneficiarioProyecto.jspx?datoPlanOperativoID= "
				+ datoPlanOperativoID + "&resultadoID=" + resultadoID;
	}

	@RequestMapping(value = "/principal/actionSaveIndicadorResultado")
	public String actionSaveIndicadorResultado(HttpServletRequest request) {
		// logger.info("actionSaveIndicadorResultado inicio");

		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		Integer tipoIndicadorId = Integer.parseInt(request
				.getParameter("tipoIndicadorId"));
		String definicionIndicador = request
				.getParameter("definicionIndicador");
		Integer unidadMedidaId = Integer.parseInt(request
				.getParameter("unidadMedidaId"));
		String medioVerificacion = request.getParameter("medioVerificacion");
		Integer situacionActual = Integer.parseInt(request
				.getParameter("situacionActual"));
		Integer situacionFinal = Integer.parseInt(request
				.getParameter("situacionFinal"));
		String metodoCalculo = request.getParameter("metodoCalculo");
		String indicadorResultadoID = request
				.getParameter("indicadorResultadoID");

		Resultado resultado = getPlanOperativoService().obtenerResultadoByID(
				resultadoID);
		IndicadorResultado indicadorResultado = new IndicadorResultado();

		if (!indicadorResultadoID.equals("")) {
			indicadorResultado.setIndicadorResultadoID(Integer
					.valueOf(indicadorResultadoID));
		}
		indicadorResultado.setResultado(resultado);
		indicadorResultado
				.setFkIdtablaespTipoIndicadorResultado(tipoIndicadorId);
		indicadorResultado.setDefinicionIndicador(definicionIndicador);
		indicadorResultado.setFkIdtablaespUnidadMedida(unidadMedidaId);
		indicadorResultado.setMedioVerificacion(medioVerificacion);
		indicadorResultado.setSituacionActual(situacionActual);
		indicadorResultado.setSituacionFinal(situacionFinal);
		indicadorResultado.setMetodoCalculo(metodoCalculo);
		indicadorResultado.setEstadoEliminado(1);

		try {
			getPlanOperativoService().createIndicadorResultado(
					indicadorResultado);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		logger.info("actionSaveIndicadorResultado fin");
		return "redirect:showIndicadorResultado.jspx?datoPlanOperativoID= "
				+ datoPlanOperativoID + "&resultadoID=" + resultadoID;
	}

	@RequestMapping(value = "/principal/actionDeleteIndicador")
	public String actionDeleteIndicador(HttpServletRequest request) {
		logger.info("actionDeleteIndicador inicio");

		Integer indicadorResultadoID = Integer.parseInt(request
				.getParameter("indicadorResultadoID"));
		IndicadorResultado indicadorResultado = indicadorResultadoService
				.findIndicadorResultadoById(indicadorResultadoID);
		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		String mensaje = "";
		String resultado = "redirect:showIndicadorResultado.jspx?datoPlanOperativoID="
				+ datoPlanOperativoID + "&resultadoID=" + resultadoID;
		try {

			if (indicadorResultado != null) {
				indicadorResultadoService
						.deleteIndicadorResultado(indicadorResultado);
				mensaje = "El indicador se elimino correctamente.";
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		logger.info("actionDeleteIndicador fin");
		return resultado + "&mensaje=" + mensaje;
	}

	@RequestMapping(value = "/principal/actionSaveCronogramaResultado")
	public String actionSaveCronogramaResultado(HttpServletRequest request) {
		// logger.info("actionSaveCronogramaResultado inicio");

		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		Integer avanceMeta = Integer.parseInt(request
				.getParameter("avanceMeta"));
		Integer periodo = Integer.parseInt(request.getParameter("sltPeriodo"));
		String cronogramaMetaPorResultadoID = request
				.getParameter("cronogramaMetaPorResultadoID");

		boolean inPeriodoExistente = false;
		int sumAvanceMeta = 0;
		String mensaje = "";

		Resultado resultado = getPlanOperativoService().obtenerResultadoByID(
				resultadoID);

		List<CronogramaMetaPorResultado> listaCronogramaMetaPorResultado = planOperativoService
				.findCronogramaMetaPorResultadoByResultadoID(resultadoID);

		CronogramaMetaPorResultado cronogramaMetaPorResultadoTemp = new CronogramaMetaPorResultado();
		if (!cronogramaMetaPorResultadoID.equals("")) {
			cronogramaMetaPorResultadoTemp = cronogramaMetaPorResultadoService
					.findCronogramaMetaPorResultadoById(Integer
							.valueOf(cronogramaMetaPorResultadoID));
		}

		for (CronogramaMetaPorResultado cronogramaMetaPorResultado : listaCronogramaMetaPorResultado) {
			if (!cronogramaMetaPorResultado.getCronogramaMetaPorResultadoID()
					.equals(cronogramaMetaPorResultadoTemp
							.getCronogramaMetaPorResultadoID())) {
				if (cronogramaMetaPorResultado.getPeriodo().equals(
						periodo.toString())) {
					inPeriodoExistente = true;
				}
				sumAvanceMeta += cronogramaMetaPorResultado.getAvanceMeta();
			}
		}

		if (periodo <= 0) {
			// logger.info("Periodo incorrecto.");
			mensaje = "Periodo incorrecto.";
		} else if (periodo > resultado.getDatoPlanOperativo().getDatoProyecto()
				.getCantidadPeriodo()) {
			// logger.info("El periodo supera a la cantidad de periodos del proyecto.");
			mensaje = "El periodo supera a la cantidad de periodos del proyecto.";
		} else if (inPeriodoExistente) {
			// logger.info("El periodo ingresado ya se encuentra registrado.");
			mensaje = "El periodo ingresado ya se encuentra registrado.";
		} else if ((sumAvanceMeta + avanceMeta) > resultado.getMetaResultado()) {
			// logger.info("El avance meta supera la meta del resultado.");
			mensaje = "El avance meta supera la meta del resultado.";
		} else {

			CronogramaMetaPorResultado cronogramaMetaPorResultado = new CronogramaMetaPorResultado();
			if (!cronogramaMetaPorResultadoID.equals("")) {
				cronogramaMetaPorResultado
						.setCronogramaMetaPorResultadoID(Integer
								.valueOf(cronogramaMetaPorResultadoID));
			}
			cronogramaMetaPorResultado.setResultado(resultado);
			cronogramaMetaPorResultado.setAvanceMeta(avanceMeta);
			cronogramaMetaPorResultado.setPeriodo(periodo.toString());
			cronogramaMetaPorResultado.setEstadoEliminado(1);
			try {
				getPlanOperativoService().createCronogramaResultado(
						cronogramaMetaPorResultado);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("ERROR =" + e);
			}
		}
		// logger.info("actionSaveCronogramaResultado fin");
		return "redirect:showCronogramaResultado.jspx?datoPlanOperativoID= "
				+ datoPlanOperativoID + "&resultadoID=" + resultadoID
				+ "&mensaje=" + mensaje;
	}

	@RequestMapping(value = "/principal/actionDeleteCronogramaResultado")
	public String actionDeleteCronogramaResultado(HttpServletRequest request) {
		//logger.info("actionDeleteCronogramaResultado inicio");
		Integer cronogramaMetaPorResultadoID = Integer.parseInt(request
				.getParameter("cronogramaMetaPorResultadoID"));
		CronogramaMetaPorResultado cronogramaMetaPorResultado = cronogramaMetaPorResultadoService
				.findCronogramaMetaPorResultadoById(cronogramaMetaPorResultadoID);
		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		String mensaje = "";
		String resultado = "redirect:showCronogramaResultado.jspx?datoPlanOperativoID="
				+ datoPlanOperativoID + "&resultadoID=" + resultadoID;
		try {

			if (cronogramaMetaPorResultado != null) {
				cronogramaMetaPorResultadoService
						.deleteCronogramaMetaPorResultado(cronogramaMetaPorResultado);
				mensaje = "El periodo se elimino correctamente.";
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		//logger.info("actionDeleteCronogramaResultado fin");
		return resultado + "&mensaje=" + mensaje;
	}

	@RequestMapping(value = "/principal/actionSaveActividad")
	public String actionSaveActividad(HttpServletRequest request) {
		// logger.info("actionSaveActividad inicio");
		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		String nombreActividad = request.getParameter("nombreActividad");
		String descripcionActividad = request
				.getParameter("descripcionActividad");
		Integer duracionMeses = Integer.parseInt(request
				.getParameter("duracionMeses"));
		String actividadID = request.getParameter("actividadID");

		Integer tipoActividadId = null;
		Resultado resultado = getPlanOperativoService().obtenerResultadoByID(
				resultadoID);
		Actividad actividad = new Actividad();
		if (resultado.getCodigoResultado().intValue() == 0) {
			tipoActividadId = FondamConstans.TIPO_ACTIVIDAD_ID_ADMINISTRATIVA;
		} else {
			tipoActividadId = FondamConstans.TIPO_ACTIVIDAD_ID_OPERATIVA;
		}
		// logger.info("tipoActividadId = " + tipoActividadId);
		if (!actividadID.equals("")) {
			actividad.setActividadID(Integer.valueOf(actividadID));
			actividad.setCodigoActividad(actividadService.findActividadById(
					Integer.valueOf(actividadID)).getCodigoActividad());
		}
		actividad.setFkIdtablaespTipoActividad(tipoActividadId);
		actividad.setNombreActividad(nombreActividad);
		actividad.setDescripcionActividad(descripcionActividad);
		actividad.setDuracionMeses(duracionMeses);
		actividad.setResultado(resultado);
		actividad.setEstadoEliminado(1);

		DatoPlanOperativo datoPlanOperativo = planOperativoService
				.findPlanOperativoByID(datoPlanOperativoID);

		try {
			if (duracionMeses > datoPlanOperativo.getDatoProyecto()
					.getDuracionProyecto()) {
				logger.info("La duracion ("
						+ duracionMeses
						+ ") de la actividad no puede pasar la duracion del resultado ("
						+ datoPlanOperativo.getDatoProyecto()
								.getDuracionProyecto() + ")");
			} else {
				getPlanOperativoService().createActividad(actividad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		logger.info("actionSaveActividad fin");
		return "redirect:showActividad.jspx?datoPlanOperativoID="
				+ datoPlanOperativoID + "&resultadoID=" + resultadoID;
	}

	@RequestMapping(value = "/principal/actionDeleteActividad")
	public String actionDeleteActividad(HttpServletRequest request) {
		logger.info("actionDeleteActividad inicio");

		Integer actividadID = Integer.parseInt(request
				.getParameter("actividadID"));
		Actividad actividad = actividadService.findActividadById(actividadID);
		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		String mensaje = "";
		String resultado = "redirect:showActividad.jspx?datoPlanOperativoID="
				+ datoPlanOperativoID + "&resultadoID=" + resultadoID;
		try {
			if (actividad != null) {

				List<MetaPorActividad> metasPorActivad = metaPorActividadService
						.findMetasPorActividadByActividadId(actividadID);
				if (!metasPorActivad.isEmpty()) {
					return resultado
							+ "&mensaje=No se puede eliminar la actividad porque tiene meta actividad registradas.";
				}

				List<CostoActividad> costosActividad = costoActividadService
						.findCostoActividadByActividadID(actividadID);
				if (!costosActividad.isEmpty()) {
					return resultado
							+ "&mensaje=No se puede eliminar la actividad porque tiene costo actividad registradas.";
				}

				actividadService.deleteActividad(actividad);
				mensaje = "La actividad se elimino correctamente.";

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		logger.info("actionDeleteActividad fin");
		return resultado + "&mensaje=" + mensaje;
	}

	@RequestMapping(value = "/principal/actionSaveMetaActividad")
	public String actionSaveMetaActividad(HttpServletRequest request) {
		// logger.info("actionSaveMetaActividad inicio");
		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		Integer actividadID = Integer.parseInt(request
				.getParameter("actividadID"));
		Integer unidadMedidaId = Integer.parseInt(request
				.getParameter("unidadMedidaId"));
		Integer cantidadMetaActividad = Integer.parseInt(request
				.getParameter("cantidadMetaActividad"));
		/*
		 * String contribucionProposito = request
		 * .getParameter("contribucionProposito");
		 */
		Integer tipoIndicadorActividadId = Integer.parseInt(request
				.getParameter("tipoIndicadorActividadId"));

		String metaPorActividadID = request.getParameter("metaActividadID");

		MetaPorActividad metaPorActividad = new MetaPorActividad();

		metaPorActividad
				.setMetaPorActividadID(metaPorActividadID == null ||metaPorActividadID.equals("") ? null
						: Integer.valueOf(metaPorActividadID));
		metaPorActividad.setFkIdtablaespUnidadMedida(unidadMedidaId);
		metaPorActividad.setCantidadMetaActividad(cantidadMetaActividad);
		// metaPorActividad.setContribucionProposito(contribucionProposito);
		metaPorActividad
				.setFkIdtablaespTipoIndicadorActividad(tipoIndicadorActividadId);
		metaPorActividad.setEstadoEliminado(1);

		Actividad actividad = new Actividad();
		actividad.setActividadID(actividadID);
		metaPorActividad.setActividad(actividad);

		try {
			getPlanOperativoService().createMetaActividad(metaPorActividad);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		logger.info("actionSaveMetaActividad fin");
		return "redirect:showMetaActividad.jspx?datoPlanOperativoID="
				+ datoPlanOperativoID + "&resultadoID=" + resultadoID
				+ "&actividadID=" + actividadID;
	}

	@RequestMapping(value = "/principal/actionDeleteMetaActividad")
	public String actionDeleteMetaActividad(HttpServletRequest request) {
		// logger.info("actionDeleteActividad inicio");

		Integer metaActividadID = Integer.parseInt(request
				.getParameter("metaActividadID"));
		MetaPorActividad metaPorActividad = metaPorActividadService
				.findMetaPorActividadById(metaActividadID);

		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoID"));
		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));
		Integer actividadID = Integer.parseInt(request
				.getParameter("actividadID"));

		String mensaje = "";
		String resultado = "redirect:showMetaActividad.jspx?datoPlanOperativoID="
				+ datoPlanOperativoID
				+ "&resultadoID="
				+ resultadoID
				+ "&actividadID=" + actividadID;
		try {
			if (metaPorActividad != null) {

				List<CronogramaMetaPorActividad> listCronogramasMetaPorActividad = planOperativoService
						.findCronogramaMetaActividadByMetaActividadID(metaActividadID);
				/*
				 * if (!cronogramasMetaPorActividad.isEmpty()) { return
				 * resultado +
				 * "&mensaje=No se puede eliminar la meta actividad porque tiene cronogramas meta actividad egistradas."
				 * ; }
				 */
				Integer cantCMAReportados = 0;
				for (CronogramaMetaPorActividad cronogramaMetaPorActividad : listCronogramasMetaPorActividad) {
					if (cronogramaMetaPorActividad.getPeriodoReportado() == 1) {
						cantCMAReportados += 1;
					}
				}

				if (cantCMAReportados == 0) {
					for (CronogramaMetaPorActividad cronogramaMetaPorActividad : listCronogramasMetaPorActividad) {
						cronogramaMetaPorActividadService
								.deleteCronorgramaMetaPorActividad(cronogramaMetaPorActividad
										.getCronogramaMetaPorActividadID());
					}

					metaPorActividadService
							.deleteMetaPorActividad(metaPorActividad);
					mensaje = "La actividad se elimino correctamente.";
				} else {
					return resultado
							+ "&mensaje=La meta actividad tiene cronogramas ya reportados y no se puede eliminar.";
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR =" + e);
		}
		// logger.info("actionDeleteActividad fin");
		return resultado + "&mensaje=" + mensaje;
	}

	@RequestMapping(value = "/principal/actionAddIndicador")
	public String actionAddIndicador(HttpServletRequest request) {
		logger.info("actionAddIndicador");

		Integer resultadoID = Integer.parseInt(request
				.getParameter("resultadoID"));

		Integer tipoIndicadorId = Integer.parseInt(request
				.getParameter("tipoIndicadorId"));
		String definicionIndicador = request
				.getParameter("definicionIndicador");
		Integer unidadMedidaId = Integer.parseInt(request
				.getParameter("unidadMedidaId"));
		String medioVerificacion = request.getParameter("medioVerificacion");
		Integer situacionActual = Integer.parseInt(request
				.getParameter("situacionActual"));
		Integer situacionFinal = Integer.parseInt(request
				.getParameter("situacionFinal"));
		Integer logroAlcanzado = Integer.parseInt(request
				.getParameter("logroAlcanzado"));
		String observacion = request.getParameter("observacion");

		logger.info("resultadoID = " + resultadoID);
		logger.info("tipoIndicadorId = " + tipoIndicadorId);
		logger.info("definicionIndicador = " + definicionIndicador);
		logger.info("unidadMedidaId = " + unidadMedidaId);
		logger.info("medioVerificacion = " + medioVerificacion);
		logger.info("situacionActual = " + situacionActual);
		logger.info("situacionFinal = " + situacionFinal);
		logger.info("logroAlcanzado = " + logroAlcanzado);
		logger.info("observacion = " + observacion);

		IndicadorForm indicador = new IndicadorForm();
		indicador.setTipoIndicadorId(tipoIndicadorId);
		indicador.setDefinicionIndicador(definicionIndicador);
		indicador.setUnidadMedidaId(unidadMedidaId);
		indicador.setMedioVerificacion(medioVerificacion);
		indicador.setSituacionActual(situacionActual);
		indicador.setSituacionFinal(situacionFinal);
		indicador.setLogroAlcanzado(logroAlcanzado);
		indicador.setObservacion(observacion);

		Integer indicadorResultadoID = null;
		try {
			indicadorResultadoID = getPlanOperativoService().createIndicador(
					resultadoID, indicador);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		logger.info("indicadorResultadoID = " + indicadorResultadoID);

		return "redirect:showPlanOperativoDetail.jspx?resultadoID="
				+ resultadoID;
	}

	@RequestMapping(value = "/principal/actionAddBeneficiario")
	public String actionAddBeneficiario(HttpServletRequest request) {
		logger.info("actionAddBeneficiario");
		Integer resultadoID = 9997;
		return "redirect:showPlanOperativoDetail.jspx?resultadoID="
				+ resultadoID;
	}

	@RequestMapping(value = "/principal/actionAddCronograma")
	public String actionAddCronograma(HttpServletRequest request) {
		logger.info("actionAddCronograma");
		Integer resultadoID = 9998;
		return "redirect:showPlanOperativoDetail.jspx?resultadoID="
				+ resultadoID;
	}

	@RequestMapping(value = "/principal/actionAddActividad")
	public String actionAddActividad(HttpServletRequest request) {
		logger.info("actionAddActividad");
		Integer resultadoID = 9999;
		return "redirect:showPlanOperativoDetail.jspx?resultadoID="
				+ resultadoID;
	}

	@RequestMapping(value = "/principal/showPlanOperativo", method = RequestMethod.GET)
	public ModelAndView showPlanOperativo(
			HttpServletRequest request,
			@RequestParam(required = false, value = "datoPlanOperativoID") Integer datoPlanOperativoID) {
		// String alterMessage = null;
		PlanOperativoForm planOperativo = null;
		if (UtilValidate.isNotEmpty(datoPlanOperativoID)) {
			planOperativo = planOperativoService
					.mostrarPlanOperativoProyecto(datoPlanOperativoID);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("planOperativo", planOperativo);
		// model.put("alterMessage", alterMessage);
		return new ModelAndView("mostrarPlanOperativo", model);
	}

	@RequestMapping(value = "/principal/createPlanOperativo", method = RequestMethod.GET)
	public String createPlanOperativo(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoProyectoID") Integer datoProyectoID,
			@RequestParam(required = true, value = "tipoMonedaId") Integer tipoMonedaId) {
		//logger.info("datoProyectoID = " + datoProyectoID);
		try {
			createDataPlanOperativo(datoProyectoID, tipoMonedaId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR = " + e);
		}
		return "redirect:showPlanOperativoNew.jspx?datoProyectoID= "
				+ datoProyectoID;
	}

	private void createDataPlanOperativo(Integer datoProyectoID,
			Integer tipoMonedaId) throws Exception {
		//boolean isCodigoResultadoCreated = false;

		DatoProyecto datoProyecto = getPlanOperativoService()
				.findDatoProyectoByDatoProyectoID(datoProyectoID);
		
		Integer programaID = datoProyecto.getPrograma().getProgramaID();
		// busco todas las versiones de plan operativo para sacar que version
		// viene
		List<DatoPlanOperativo> listDatoPlanOperativo = datoPlanOperativoService
				.findDatoPlanOperativoByDatoProyectoID(datoProyectoID);

		DatoPlanOperativo datoPlanOperativo = new DatoPlanOperativo();
		datoPlanOperativo.setVersion(String.valueOf(listDatoPlanOperativo
				.size() + 1));
		datoPlanOperativo
				.setFkIdDetalleEstadoCabEstadoPlanOper(detalleEstadoCabeceraService
						.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraByPrefijoDetalleEstado(
								"estplo", "elab").getDetalleEstadoCabeceraID());
		datoPlanOperativo.setDatoProyecto(datoProyecto);
		datoPlanOperativo.setFkIdtablaespTipoMoneda(tipoMonedaId);
		datoPlanOperativo.setEstadoEliminado(1);
		
		// creo plan operativo
		getPlanOperativoService()
				.createPlanOperativo(datoPlanOperativo);

		//logger.info("datoPlanOperativoID = " + datoPlanOperativoID);

		// creo lo obligatorio
		if (programaID != null) {
			createResultadoAndActividadesObligatorias(datoPlanOperativo,
					programaID);
			
		}
		/*List<Resultado> listResultado = getPlanOperativoService()
				.listResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		for (Resultado resultado : listResultado) {
			Integer codigoResultado = resultado.getCodigoResultado();
			if (0 == codigoResultado.intValue()) {
				isCodigoResultadoCreated = true;
				break;
			}
		}
		if (!isCodigoResultadoCreated) {
			createResultadoAdministrativo(datoPlanOperativo);
		}*/
	}
/*
	private void createResultadoAdministrativo(
			DatoPlanOperativo datoPlanOperativo) throws Exception {
		Resultado resultado = new Resultado();
		resultado.setCodigoResultado(0);
		resultado.setDefinicionResultado("Administrativa");
		resultado.setSupuestoResultado("Administrativa");
		resultado.setDatoPlanOperativo(datoPlanOperativo);
		getPlanOperativoService().createResultado(resultado);
	}*/

	private void createResultadoAndActividadesObligatorias(
			DatoPlanOperativo datoPlanOperativo, Integer programaID)
			throws Exception {
		
		List<ActividadObligatoriaPrograma> listActividadObligatoriaPrograma =llenaListActividadObligatoriaProgramaCompleto( getPlanOperativoService()
				.findListActividadObligatoriaProgramaByProgramaId(programaID));

		//Integer tipoResultadoActividadIdOld = 0;
		Resultado resultado = null;
		List<Integer> listResultadoObligatorioId= new ArrayList<Integer>();
		
		for (ActividadObligatoriaPrograma actividadObligatoriaPrograma : listActividadObligatoriaPrograma) {
			Integer existeId = 0;
			for (Integer id : listResultadoObligatorioId) {
				if(id == actividadObligatoriaPrograma.getTipoActividadObligatoriaPrograma().getFkIdtablaespTipoResulActivoOblig()){
					existeId=1;
					break;
				}
			}
			
			if(existeId==0){
				listResultadoObligatorioId.add(actividadObligatoriaPrograma.getTipoActividadObligatoriaPrograma().getFkIdtablaespTipoResulActivoOblig());
			}
		}
		
		for (Integer id : listResultadoObligatorioId) {
			resultado = new Resultado();
			resultado.setCodigoResultado(0);
			resultado.setDefinicionResultado(tablaEspecificaService.findTablaEspecificaById(id).getDescripcionCabecera());
			resultado.setSupuestoResultado(tablaEspecificaService.findTablaEspecificaById(id).getDescripcionCabecera());
			resultado.setDatoPlanOperativo(datoPlanOperativo);
			resultado.setMetaResultado(1);
			resultado.setDuracionMeses(datoPlanOperativo.getDatoProyecto().getDuracionProyecto() );
			resultado.setEstadoEliminado(1);
			
			resultado=resultadoService.updateResultado(resultado);
			//getPlanOperativoService().createResultado(resultado);

			for (ActividadObligatoriaPrograma actividadObligatoriaPrograma : listActividadObligatoriaPrograma) {
				if(id == actividadObligatoriaPrograma.getTipoActividadObligatoriaPrograma().getFkIdtablaespTipoResulActivoOblig()){
					Actividad actividad = new Actividad();
					List<Actividad> listActividad= actividadService.findActividadXResultadoId(resultado.getResultadoID());
					
					//String codigoActividad = resultado.getCodigoResultado()+"."+listActividad.size()+1 ;
					actividad.setCodigoActividad(listActividad.size()+1);
					actividad.setFkIdtablaespTipoActividad(FondamConstans.TIPO_ACTIVIDAD_ID_ADMINISTRATIVA);
					actividad.setNombreActividad(actividadObligatoriaPrograma.getTipoActividadObligatoriaPrograma().getDescripcion());
					actividad.setDescripcionActividad(actividadObligatoriaPrograma.getTipoActividadObligatoriaPrograma().getDescripcion());
					actividad.setDuracionMeses(datoPlanOperativo.getDatoProyecto().getDuracionProyecto() );
					actividad.setEstadoEliminado(1);
					actividad.setResultado(resultado);

					actividadService.updateActividad(actividad);
				}
			}
		}
		
		
		
		/*
		for (ActividadObligatoriaPrograma actividadObligatoriaPrograma : listActividadObligatoriaPrograma) {
			TipoActividadObligatoriaPrograma tipoActividadObligatoriaPrograma = actividadObligatoriaPrograma.getTipoActividadObligatoriaPrograma();
			Integer tipoResultadoActividadId = tipoActividadObligatoriaPrograma.getFkIdtablaespTipoResulActivoOblig();

			String resultadoDescripcion = getPlanOperativoService().obtenerDescripcionTablaEspecifica(tipoResultadoActividadId);
			Actividad actividad = null;
			
			if (tipoResultadoActividadIdOld.intValue() != tipoResultadoActividadId
					.intValue()) {
				resultado = new Resultado();
				resultado.setCodigoResultado(0);
				resultado.setDefinicionResultado(resultadoDescripcion);
				resultado.setSupuestoResultado(resultadoDescripcion);
				resultado.setDatoPlanOperativo(datoPlanOperativo);
				resultado.setMetaResultado(1);
				resultado.setDuracionMeses(datoPlanOperativo.getDatoProyecto()
						.getCantidadPeriodo());
				getPlanOperativoService().createResultado(resultado);

				actividad = new Actividad();
				actividad
						.setFkIdtablaespTipoActividad(FondamConstans.TIPO_ACTIVIDAD_ID_ADMINISTRATIVA);
				actividad.setNombreActividad(tipoActividadObligatoriaPrograma
						.getDescripcion());
				actividad
						.setDescripcionActividad(tipoActividadObligatoriaPrograma
								.getDescripcion());
				actividad.setDuracionMeses(1);
				actividad.setResultado(resultado);

				MetaPorActividad metaPorActividad = new MetaPorActividad();
				metaPorActividad.setFkIdtablaespUnidadMedida(1);
				metaPorActividad.setCantidadMetaActividad(1);

				getPlanOperativoService().createActividad(actividad);
				getPlanOperativoService().createMetaActividad(metaPorActividad);
			}
			if (resultado != null) {
				if (tipoResultadoActividadIdOld.intValue() == tipoResultadoActividadId
						.intValue()) {
					actividad = new Actividad();
					actividad
							.setFkIdtablaespTipoActividad(FondamConstans.TIPO_ACTIVIDAD_ID_ADMINISTRATIVA);
					actividad
							.setNombreActividad(tipoActividadObligatoriaPrograma
									.getDescripcion());
					actividad
							.setDescripcionActividad(tipoActividadObligatoriaPrograma
									.getDescripcion());
					actividad.setDuracionMeses(1);
					actividad.setResultado(resultado);

					MetaPorActividad metaPorActividad = new MetaPorActividad();
					metaPorActividad.setFkIdtablaespUnidadMedida(1);
					metaPorActividad.setCantidadMetaActividad(1);

					getPlanOperativoService().createActividad(actividad);
					getPlanOperativoService().createMetaActividad(
							metaPorActividad);
				}

			}
			tipoResultadoActividadIdOld = tipoResultadoActividadId;
		}*/
	}

	private List<ActividadObligatoriaPrograma> llenaListActividadObligatoriaProgramaCompleto(
			List<ActividadObligatoriaPrograma> listActividadObligatoriaPrograma) {
		
		for (ActividadObligatoriaPrograma actividadObligatoriaPrograma : listActividadObligatoriaPrograma) {
			actividadObligatoriaPrograma.setTipoActividadObligatoriaPrograma(tipoActividadObligatoriaProgramaService.findTipoActividadObligatoriaProgramaById(actividadObligatoriaPrograma.getTipoActividadObligatoriaPrograma().getTipoActividadObligatoriaProgramaID()));
		}
		return listActividadObligatoriaPrograma;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/addPlanOperativoResultado", method = RequestMethod.POST)
	public ModelAndView addPlanOperativoResultado(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoProyectoID") Integer datoProyectoID) {
		List<ResultadoForm> listResultado = (List<ResultadoForm>) request
				.getSession().getAttribute("listResultado");
		String definicionResultado = request
				.getParameter("definicionResultado");
		String supuestoResultado = request.getParameter("supuestoResultado");
		String metaResultado = request.getParameter("metaResultado");
		String estratoId = request.getParameter("estratoId");
		String duracionMeses = request.getParameter("duracionMeses");

		Integer tablaEspecificaID = Integer.parseInt(estratoId);
		TablaEspecifica estrato = getUtilService().obtenerTablaEspecificaById(
				tablaEspecificaID);
		String estratoNombre = estrato.getDescripcionCabecera();

		if (UtilValidate.isEmpty(listResultado)) {
			listResultado = new ArrayList<ResultadoForm>();
		}
		ResultadoForm resultadoForm = setDataResultadoForm(definicionResultado,
				supuestoResultado, Integer.parseInt(metaResultado),
				tablaEspecificaID, estratoNombre,
				Integer.parseInt(duracionMeses));
		listResultado.add(resultadoForm);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("datoProyectoID", datoProyectoID);
		model.put("listResultado", listResultado);
		model.put("listaEstratos", listaEstratos);
		request.getSession().setAttribute("listResultado", listResultado);

		return new ModelAndView("createPlanOperativo", model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/removeResultado")
	public ModelAndView removeResultado(HttpServletRequest request,
			@RequestParam("resultadoID") Integer resultadoID) {
		logger.info("param received resultadoID = " + resultadoID);
		List<ResultadoForm> listResultado = (List<ResultadoForm>) request
				.getSession().getAttribute("listResultado");
		int index = 0;
		if (UtilValidate.isNotEmpty(listResultado)) {
			for (ResultadoForm resultado : listResultado) {
				if (resultadoID.intValue() == resultado.getResultadoID()
						.intValue()) {
					listResultado.remove(index);
					break;
				}
				index++;
			}
		}
		request.getSession().setAttribute("listResultado", listResultado);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listaEstratos", listaEstratos);
		model.put("listResultado", listResultado);
		return new ModelAndView("createPlanOperativo", model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/actionSavePlanOperativo")
	public String actionSavePlanOperativo(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoProyectoID") Integer datoProyectoID) {
		logger.info("actionSavePlanOperativo");

		String version = "1000-0001";
		List<ResultadoForm> listResultado = (List<ResultadoForm>) request
				.getSession().getAttribute("listResultado");
		PlanOperativoForm planOperativoForm = new PlanOperativoForm();
		planOperativoForm.setVersion(version);
		planOperativoForm.setEstadoPlanOperativo(1);
		planOperativoForm.setListResultadoForm(listResultado);
		planOperativoForm.setDatoProyectoID(datoProyectoID);
		try {
			getPlanOperativoService().createPlanOperativo(planOperativoForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:showPlanOperativoNew.jspx?datoProyectoID= "
				+ datoProyectoID;
	}

	@RequestMapping(value = "/principal/editPlanOperativo", method = RequestMethod.GET)
	public ModelAndView editPlanOperativo(@RequestParam("id") Integer id) {
		logger.info("[PlanOperativoController] editPlanOperativo start");
		logger.info("Valor recepcionado es id " + id);

		PlanOperativoForm planOperativoForm = planOperativoService
				.mostrarPlanOperativoDatoPlanOperativoID(id);

		logger.info("[PlanOperativoController] editPlanOperativo finish");
		return new ModelAndView("planOperativo/editPlanOperativo",
				"planOperativo", planOperativoForm);

	}

	@RequestMapping(value = "/principal/showPlanOperativoDetail", method = RequestMethod.GET)
	public ModelAndView showPlanOperativoDetail(
			HttpServletRequest request,
			@RequestParam(required = false, value = "resultadoID") Integer resultadoID) {

		logger.info("received resultadoID = " + resultadoID);

		loadPlanOperativoDetailList();

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("resultadoID", resultadoID);
		model.put("listaEstratos", listaEstratos);
		model.put("listaTipoIndicadorResultado", listaTipoIndicadorResultado);
		model.put("listaUnidadMedida", listaUnidadMedida);
		model.put("listaTipoBeneficiario", listaTipoBeneficiario);
		model.put("listaTipoActividad", listaTipoActividad);
		model.put("listaActividadTransferencia", listaActividadTransferencia);
		return new ModelAndView("showPlanOperativoDetail", model);
	}

	@RequestMapping(value = "/principal/cargaIndicadorMarcoLogico")
	public ModelAndView cargaIndicadorMarcoLogico(
			@RequestParam(required = false, value = "indicadorMarcoLogicoID") Integer indicadorMarcoLogicoID,
			@RequestParam(required = false, value = "txtIndicador") String indicador,
			@RequestParam(required = false, value = "txtdefinicionIndicador") String definicionIndicador,
			@RequestParam(required = false, value = "cbxUnidadMedida") Integer unidadMedidaId,
			@RequestParam(required = false, value = "txtMedioVerificacion") String medioVerificacion,
			@RequestParam(required = false, value = "txtSituacionActual") Integer situacionActual,
			@RequestParam(required = false, value = "txtSituacionFinal") Integer situacionFinal,
			@RequestParam(required = false, value = "txtMetodoCalculo") String metodoCalculo,
			@RequestParam(required = false, value = "marcoLogicoID") Integer marcoLogicoID,
			@RequestParam(required = false, value = "datoProyectoID") Integer datoProyectoID,
			@RequestParam(required = false, value = "flagElimina") String flagElimina,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if (indicador != null) {
			IndicadorMarcoLogico indicadorMarcoLogico = new IndicadorMarcoLogico();
			if (indicadorMarcoLogicoID != 0) {
				indicadorMarcoLogico
						.setIndicadorMarcoLogicoID(indicadorMarcoLogicoID);
			}
			indicadorMarcoLogico.setDefinicionIndicador(definicionIndicador);
			indicadorMarcoLogico.setIndicador(indicador);
			indicadorMarcoLogico.setMedioVerificacion(medioVerificacion);
			indicadorMarcoLogico.setMetodoCalculo(metodoCalculo);
			indicadorMarcoLogico
					.setSituacionActualDescripcion(tablaEspecificaService
							.findTablaEspecificaById(unidadMedidaId)
							.getDescripcionCabecera());
			indicadorMarcoLogico.setSituacionActural(situacionActual);
			indicadorMarcoLogico.setSituacionFinal(situacionFinal);
			indicadorMarcoLogico.setUnidadMedida(unidadMedidaId);
			indicadorMarcoLogico.setMarcoLogico(marcoLogicoService
					.findMarcoLogicoByID(marcoLogicoID));

			indicadorMarcoLogicoService
					.updateIndicadorMarcoLogico(indicadorMarcoLogico);
		}
		if (flagElimina != null) {
			indicadorMarcoLogicoService
					.deleteIndicadorMarcoLogico(indicadorMarcoLogicoID);
		}
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("listIndicadorMarcoLogico",
				llenalistIndicadorMarcoLogicoBean(marcoLogicoService
						.findMarcoLogicoByID(marcoLogicoID)));

		return new ModelAndView("grillaIndicadorMarcoLogico", model);
	}

	@RequestMapping(value = "/principal/grabarMarcoLogico.jspx")
	public void grabarMarcoLogico(
			@RequestParam(required = true, value = "marcoLogicoID") Integer marcoLogicoID,
			@RequestParam(required = true, value = "resumenEjecutivo") String resumenEjecutivo,
			@RequestParam(required = true, value = "descripcionFin") String descripcionFin,
			@RequestParam(required = true, value = "supuestoFin") String supuestoFin,
			@RequestParam(required = true, value = "descripcionProposito") String descripcionProposito,
			@RequestParam(required = true, value = "supuestoProposito") String supuestoProposito,
			HttpServletRequest request, HttpServletResponse response) {

		MarcoLogico marcoLogico = marcoLogicoService
				.findMarcoLogicoByID(marcoLogicoID);

		marcoLogico.setResumenEjecutivo(resumenEjecutivo);
		marcoLogico.setFinDescrip(descripcionFin);
		marcoLogico.setFinSupuesto(supuestoFin);
		marcoLogico.setPropositoDescrip(descripcionProposito);
		marcoLogico.setPropositoSupuesto(supuestoProposito);
		marcoLogicoService.updateMarcoLogico(marcoLogico);

	}

	@RequestMapping(value = "/principal/grabarCantidadMetaResultado.jspx")
	public void grabarCantidadMetaResultado(
			@RequestParam(required = true, value = "cantidadMetaResultado") Integer cantidadMetaResultado,
			@RequestParam(required = true, value = "resultadoId") Integer resultadoId,
			HttpServletRequest request, HttpServletResponse response) {

		Resultado resultado = resultadoService.findResultadoByID(resultadoId);

		resultado.setMetaResultado(cantidadMetaResultado);
		resultadoService.updateResultado(resultado);

	}

	@RequestMapping(value = "/principal/grabarInfraestructura.jspx")
	public void grabarInfraestructura(
			@RequestParam(required = false, value = "infraestructuraPOID") Integer infraestructuraPOID,
			@RequestParam(required = true, value = "descripcion") String descripcion,
			@RequestParam(required = true, value = "ubicacion") String ubicacion,
			@RequestParam(required = false, value = "activo") Integer activoId,
			@RequestParam(required = false, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			HttpServletRequest request, HttpServletResponse response) {

		InfraestructuraPo infraestructuraPo = new InfraestructuraPo();

		if (infraestructuraPOID != null) {
			infraestructuraPo = infraestructuraPoService
					.findInfraestructuraPoById(infraestructuraPOID);
		}

		if (activoId != 0) {
			infraestructuraPo.setActivo(activoService.findActivoById(activoId));
		}
		infraestructuraPo.setDescripcion(descripcion);
		infraestructuraPo.setUbicacion(ubicacion);
		infraestructuraPo.setDatoProyecto(datoPlanOperativoService
				.findDatoPlanOperativoByID(datoPlanOperativoID)
				.getDatoProyecto());

		infraestructuraPoService.updateInfraestructuraPo(infraestructuraPo);
	}

	@RequestMapping(value = "/principal/eliminarRecursoEjecutor.jspx")
	public void eliminarRecursoEjecutor(
			@RequestParam(required = false, value = "infraestructuraPOID") Integer infraestructuraPOID,
			HttpServletRequest request, HttpServletResponse response) {

		infraestructuraPoService.deleteInfraestructuraPo(infraestructuraPOID);
	}

	@RequestMapping(value = "/principal/cambiaEstadoValidaInformacion.jspx")
	public String cambiaEstadoValidaInformacion(HttpServletRequest request) {

		Integer datoProyectoID = Integer.parseInt(request
				.getParameter("datoProyectoID"));
		Integer datoPlanOperativoID = Integer.parseInt(request
				.getParameter("datoPlanOperativoId"));
		Integer estado = Integer.parseInt(request
				.getParameter("sltEstadoPlanOperativo"));

		DatoPlanOperativo datoPlanOperativo = planOperativoService
				.llenaPlanOperativoCompleto(planOperativoService
						.findPlanOperativoByID(datoPlanOperativoID));

		// ******** validacion de ingreso de cantidades a tablas de plan
		// operativo//
		List<ValidaCantidadesCompletasPlanOperativo> listValidaCantidadesCompletasPlanOperativo = validaCantidadesPlanOperativo(datoPlanOperativo);

		int totalValidaciones = listValidaCantidadesCompletasPlanOperativo
				.size();
		String mensaje = "";
		//String mensajeTablaValidaciones = "";
		if (totalValidaciones > 0) {
			mensaje = "Existen algunos cronogramas que debe completar";
			//mensajeTablaValidaciones = cargaMensaje(listValidaCantidadesCompletasPlanOperativo);
		} else {
			datoPlanOperativo.setFkIdDetalleEstadoCabEstadoPlanOper(estado);
			datoPlanOperativoService.updateDatoPlanOperativo(datoPlanOperativo);
		}

		return "redirect:showPlanOperativoNew.jspx?datoProyectoID="
				+ datoProyectoID + "&mensaje=" + mensaje
				+ "&calculaValidaciones=si"
				+ "&totalValidaciones=" + totalValidaciones;
	}

	@RequestMapping(value = "/principal/createPersonalTecnicoAdministrativo")
	public ModelAndView cuerpoPersonalTecnicoAdministrativo(
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = false, value = "estadoPlanOperativo") String estadoPlanOperativo,
			HttpServletRequest request, HttpServletResponse response) {

		// logger.info("datoPlanOperativoID = " + datoPlanOperativoID);
		DatoPlanOperativo datoPlanOperativo = planOperativoService
				.findPlanOperativoByID(datoPlanOperativoID);
		List<PersonalTecnicoAdministrativo> listPersonalTecnicoReemplazado = personalTecnicoAdministrativoService
				.findPersonalTecnicoAdministrativoByDatoProyectoId(datoPlanOperativo
						.getDatoProyecto().getDatoProyectoID());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("datoPlanOperativoID", datoPlanOperativoID);
		model.put("listFormacionProfesional", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("FMPF"));
		model.put("listTiempoDedicado", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("TMDD"));
		model.put("listEtapaPersonal", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("ETPT"));
		model.put("listPersonalTecnicoReemplazado",
				listPersonalTecnicoReemplazado);
		model.put("listTipoMoneda", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("TMND"));
		model.put("listInstitucion", planOperativoService
				.findFuenteFinanciadoraByDatoProyectoID(datoPlanOperativo
						.getDatoProyecto().getDatoProyectoID()));
		model.put("estadoPlanOperativo", estadoPlanOperativo);

		return new ModelAndView("createPersonalTecnicoAdministrativo", model);
	}

	@RequestMapping(value = "/principal/actionSavePersonalTecnicoAdministrativo")
	public String actionSavePersonalTecnicoAdministrativo(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "nombreCompleto") String nombreCompleto,
			@RequestParam(required = true, value = "sltFormacionProfesional") Integer sltFormacionProfesional,
			@RequestParam(required = true, value = "sltTiempoDedicado") Integer sltTiempoDedicado,
			@RequestParam(required = true, value = "responsabilidad") String responsabilidad,
			@RequestParam(required = true, value = "porcentajeParticipacion") Integer porcentajeParticipacion,
			@RequestParam(required = true, value = "sltEtapaPersonal") Integer sltEtapaPersonal,
			@RequestParam(required = false, value = "sltPersonalTecnicoReemplazado") Integer sltPersonalTecnicoReemplazado,
			@RequestParam(required = true, value = "salario") Double salario,
			@RequestParam(required = true, value = "sltTipoMoneda") Integer sltTipoMoneda,
			@RequestParam(required = true, value = "sltInstitucion") Integer sltInstitucion,
			@RequestParam(required = false, value = "personalTecnicoAdministrativoID") Integer personalTecnicoAdministrativoID) {

		logger.info("actionSavePersonalTecnicoAdministrativo inicio");

		try {
			DatoPlanOperativo datoPlanOperativo = planOperativoService
					.obtenerDatoPlanOperativoByID(datoPlanOperativoID);

			if (datoPlanOperativo != null) {
				PersonalTecnicoAdministrativo personalTA = new PersonalTecnicoAdministrativo();
				if (personalTecnicoAdministrativoID != null) {
					personalTA
							.setPersonalTecnicoAdministrativoID(personalTecnicoAdministrativoID);
				}
				// personalTA.setDatoPlanOperativo(datoPlanOperativo);
				personalTA
						.setFkIdpersonalTecnicoAdmReemplazo(sltPersonalTecnicoReemplazado);
				personalTA
						.setFkIdtablaespEtapaPersonalTecnico(sltEtapaPersonal);
				personalTA
						.setFkIdtablaespFormacionProfesional(sltFormacionProfesional);
				personalTA.setFkIdtablaespTiempoDedicado(sltTiempoDedicado);
				personalTA.setFkIdtablaespTipoMoneda(sltTipoMoneda);
				Institucion institucion = new Institucion();
				institucion.setInstitucionID(sltInstitucion);
				personalTA.setInstitucion(institucion);
				personalTA.setNombreCompleto(nombreCompleto);
				personalTA.setSalarioMensual(salario);
				personalTA.setResponsabilidad(responsabilidad);
				personalTA.setPorcentageParticipacion(porcentajeParticipacion);
				personalTA.setDatoProyecto(datoPlanOperativo.getDatoProyecto());

				personalTecnicoAdministrativoService
						.updatePersonalTecnicoAdministrativo(personalTA);
				logger.info("se registro correctamente el personal tecnico administrativo");
			}

		} catch (Exception e) {
			logger.error("Se produjo un error.", e);
		}
		logger.info("actionSavePersonalTecnicoAdministrativo fin");
		return "redirect:createPersonalTecnicoAdministrativo.jspx?datoPlanOperativoID="
				+ datoPlanOperativoID;

	}

	@RequestMapping(value = "/principal/eliminarPersonal.jspx")
	public String eliminarPersonal(
			HttpServletRequest request,
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer datoPlanOperativoID,
			@RequestParam(required = true, value = "personalTecnicoAdministrativoID") Integer personalTecnicoAdministrativoID) {

		personalTecnicoAdministrativoService
				.deletePersonalTecnicoAdministrativo(personalTecnicoAdministrativoID);

		return "redirect:createPersonalTecnicoAdministrativo.jspx?datoPlanOperativoID="
				+ datoPlanOperativoID;

	}

	@RequestMapping(value = "/principal/cargarComboPlanOperativo.jspx")
	public void cargarComboPlanOperativo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String metodo = request.getParameter("metodo");

		PrintWriter out = null;
		out = response.getWriter();

		if (metodo.equals("rubroGenerico")) {
			out.println("<option value='0'>--Rubro Generico--</option>");

			// int
			// categoriaActividadID=Integer.parseInt(request.getParameter("categoriaActividadID"));
			// actividadID=Integer.parseInt(request.getParameter("actividadID"));
			List<RubroGenerico> listRubroGenerico = getUtilService()
					.listaRubroGenericoByCategoriaActividad(
							Integer.parseInt(request
									.getParameter("categoriaActividadID")));// liquidacionGastoService.findActidadByResultadoID(resultadoID);
			if (!listRubroGenerico.isEmpty()) {
				for (RubroGenerico rubroGenerico : listRubroGenerico) {
					out.printf("<option  value='%1s'>%2s</option>",
							rubroGenerico.getIdcabeceraRubrogenerico(),
							rubroGenerico.getDescripCabeceraRubroGenerico());
				}
			}
		} else if (metodo.equals("rubroEspecifico")) {
			out.println("<option value='0'>--Rubro Especifico--</option>");

			// int
			// categoriaActividadID=Integer.parseInt(request.getParameter("categoriaActividadID"));
			// actividadID=Integer.parseInt(request.getParameter("actividadID"));
			List<RubroGenerico> listRubroGenerico = getUtilService()
					.listaRubroEspecificoByCategoriaActividadByRubroGenerico(
							Integer.parseInt(request
									.getParameter("categoriaActividadID")),
							Integer.parseInt(request
									.getParameter("cabeceraRubroGenericoID")));// liquidacionGastoService.findActidadByResultadoID(resultadoID);
			if (!listRubroGenerico.isEmpty()) {
				for (RubroGenerico rubroGenerico : listRubroGenerico) {
					out.printf("<option  value='%1s'>%2s</option>",
							rubroGenerico.getRubroGenericoID(), rubroGenerico
									.getDescripEspecificacionRubroGenerico());
				}
			}
		} else if (metodo.equals("partidaGenerica")) {
			out.println("<option value='0'>--Partida Generica--</option>");
			// categoriaActividadID=Integer.parseInt(request.getParameter("categoriaActividadID"));
			List<PartidaGenerica> listPartidaGenerica = getUtilService()
					.listaPartidaGenericaByCategoriaActividad(
							Integer.parseInt(request
									.getParameter("categoriaActividadID")));
			if (!listPartidaGenerica.isEmpty()) {
				for (PartidaGenerica partidaGenerica : listPartidaGenerica) {
					out.printf("<option value='%1s'>%2s</option>",
							partidaGenerica.getPartidaGenericaID(),
							partidaGenerica.getDescripcionPartidaGenerica());
				}
			}

		} else if (metodo.equals("partidaEspecifica")) {
			out.println("<option value='0'>--Partida Especifica--</option>");

			List<PartidaEspecifica> listPartidaEspecifica = getUtilService()
					.listaPartidaEspecificaByPartidaGenerica(
							Integer.parseInt(request
									.getParameter("partidaGenericaID")));

			if (!listPartidaEspecifica.isEmpty()) {
				for (PartidaEspecifica partidaEspecifica : listPartidaEspecifica) {
					out.printf("<option value='%1s'>%2s</option>",
							partidaEspecifica.getPartidaEspecificaID(),
							partidaEspecifica.getDescripcionPartidaEspecifica());
				}
			}
		}

	}

	@RequestMapping(value = "/principal/grabarSolicitudRpRf.jspx")
	public void grabarSolicitudRpRf(
			@RequestParam(required = false, value = "estadoSolicitudRpRf") Integer estadoSolicitudRpRf,
			@RequestParam(required = true, value = "versionPo") String versionPo,
			@RequestParam(required = true, value = "observacionDeSolicitud") String observacionDeSolicitud,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			@RequestParam(required = true, value = "fechaSolicitud") String fechaSolicitud,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException {

		SolicitaRpRf solicitaRpRf = new SolicitaRpRf();

		solicitaRpRf.setDatoProyecto(datoProyectoService
				.findDatoProyectoById(datoProyectoId));
		solicitaRpRf.setFkIdDetalleEstadoCabRpRf(estadoSolicitudRpRf);
		solicitaRpRf
				.setObservacionDeSolicitud(observacionDeSolicitud != "" ? observacionDeSolicitud
						: "Sin observaciones del ejecutor.");
		solicitaRpRf.setVersionPo(versionPo);

		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = formateador.parse(fechaSolicitud);
		solicitaRpRf.setFechaSolicitud(fecha);

		solicitaRpRfService.updateSolicitaRpRf(solicitaRpRf);
	}

	// ********************* listas ************************//
	/*
	private String cargaMensaje(
			List<ValidaCantidadesCompletasPlanOperativo> listValidaCantidadesCompletasPlanOperativo) {
		String mensajeTablaValidaciones = "";
		int flag = 0;
		String style;
		for (ValidaCantidadesCompletasPlanOperativo valida : listValidaCantidadesCompletasPlanOperativo) {
			if (flag % 2 == 0) {
				style = "f2";
			} else {
				style = "f1";
			}

			mensajeTablaValidaciones += "<tr class=\""
					+ style
					+ "\"><td style=\"text-align: center;\"><label>"
					+ valida.getCodigoResultado()
					+ "</label></td>"
					+ "<td style=\"text-align: center;\"><label>"
					+ (valida.getCodigoActividad() == null ? "--" : valida
							.getCodigoActividad())
					+ "</label></td>"
					+ "<td style=\"text-align: center;\"><label>"
					+ (valida.getCantidadMetaPorResultado() == null ? "--"
							: valida.getCantidadMetaPorResultado())
					+ "</label></td>"
					+ "<td style=\"text-align: center;\"><label>"
					+ (valida.getCantidadMetaPorActividad() == null ? "--"
							: valida.getCantidadMetaPorActividad())
					+ "</label></td>"
					+ "<td style=\"text-align: center;\"><label>"
					+ (valida.getCantidadCostoActividad() == null ? "--"
							: valida.getCantidadCostoActividad())
					+ "</label></td>" + "</tr>";
			flag += 1;
		}

		return mensajeTablaValidaciones;
	}
*/
	
	private List<ValidaCantidadesCompletasPlanOperativo> validaCantidadesPlanOperativo(
			DatoPlanOperativo datoPlanOperativo) {

		List<ValidaCantidadesCompletasPlanOperativo> listValidaCantidadesCompletasPlanOperativo = new ArrayList<ValidaCantidadesCompletasPlanOperativo>();
		List<Resultado> listResultado = datoPlanOperativo.getListResultado();
		// conteo para metas por resultado
		for (Resultado resultado : listResultado) {
			Integer cantMetaResultado = resultado.getMetaResultado();
			Integer cantCronogramaMetaResultado = 0;

			if (resultado.getCodigoResultado() != 0) {
				for (CronogramaMetaPorResultado cronogramaMetaPorResultado : resultado
						.getListCronogramaMetaPorResultado()) {
					cantCronogramaMetaResultado += cronogramaMetaPorResultado
							.getAvanceMeta();
				}
				if (cantMetaResultado > cantCronogramaMetaResultado) {
					ValidaCantidadesCompletasPlanOperativo valida = new ValidaCantidadesCompletasPlanOperativo();
					valida.setCodigoResultado(String.valueOf(resultado
							.getCodigoResultado()));
					valida.setDescripcionResultado(resultado
							.getDefinicionResultado());
					valida.setCantidadMetaPorResultado(resultado.getMetaResultado()
							+ " "
							+ tablaEspecificaService.findTablaEspecificaById(resultado.getFkIdtablaespUnidadMedida())
									.getDescripcionCabecera());

					valida.setDetalleValidacion("CronogramaMetaPorResultado");

					listValidaCantidadesCompletasPlanOperativo.add(valida);
				}

				for (Actividad actividad : resultado.getListActividad()) {
					for (CostoActividad costoActividad : actividad
							.getListCostoActividad()) {
						Integer cantidadCostoActividad = costoActividad
								.getCantidadTotal();
						Integer cantidadCronogramaCostoActividad = 0;
						for (CronogramaCostoActividad cronogramaCostoActividad : costoActividad
								.getListCronogramaCostoActividad()) {
							cantidadCronogramaCostoActividad += cronogramaCostoActividad
									.getCantidad();
						}
						if (cantidadCostoActividad > cantidadCronogramaCostoActividad) {
							ValidaCantidadesCompletasPlanOperativo valida = new ValidaCantidadesCompletasPlanOperativo();
							valida.setCodigoResultado(String.valueOf(resultado
									.getCodigoResultado()));
							valida.setDescripcionResultado(resultado
									.getDefinicionResultado());
							valida.setCodigoActividad(String.valueOf(actividad
									.getCodigoActividad()));
							valida.setDescripcionActividad(actividad
									.getDescripcionActividad());
							valida.setCostoActividadId(costoActividad
									.getCostoActividadID());
							valida.setCantidadCostoActividad(costoActividad
									.getCantidadTotal()
									+ " "
									+ tablaEspecificaService
											.findTablaEspecificaById(
													costoActividad
															.getFkIdtablaespUnidadMedida())
											.getDescripcionCabecera());
							valida.setDetalleValidacion("CronogramaCostoActividad");

							listValidaCantidadesCompletasPlanOperativo
									.add(valida);
						}
					}

					for (MetaPorActividad metaPorActividad : actividad
							.getListMetaPorActividad()) {
						Integer cantidadMetaPorActividad = metaPorActividad
								.getCantidadMetaActividad();
						Integer cantidadCronogramaMetaPorActividad = 0;
						for (CronogramaMetaPorActividad cronogramaMetaPorActividad : metaPorActividad
								.getListCronogramaMetaPorActividad()) {
							cantidadCronogramaMetaPorActividad += cronogramaMetaPorActividad
									.getCantidadMetaActividadProgPorPeriodo();
						}
						if (cantidadMetaPorActividad > cantidadCronogramaMetaPorActividad) {
							ValidaCantidadesCompletasPlanOperativo valida = new ValidaCantidadesCompletasPlanOperativo();
							valida.setCodigoResultado(String.valueOf(resultado
									.getCodigoResultado()));
							valida.setDescripcionResultado(resultado
									.getDefinicionResultado());
							valida.setCodigoActividad(String.valueOf(actividad
									.getCodigoActividad()));
							valida.setDescripcionActividad(actividad
									.getDescripcionActividad());
							valida.setMetaPorActividadId(metaPorActividad
									.getMetaPorActividadID());
							valida.setCantidadMetaPorActividad(metaPorActividad
									.getCantidadMetaActividad()
									+ " "
									+ tablaEspecificaService
											.findTablaEspecificaById(
													metaPorActividad
															.getFkIdtablaespUnidadMedida())
											.getDescripcionCabecera());
							valida.setDetalleValidacion("CronogramaMetaPorActividad");

							listValidaCantidadesCompletasPlanOperativo
									.add(valida);
						}
					}
				}
			}
		}

		return listValidaCantidadesCompletasPlanOperativo;
	}

	private void loadPlanOperativoDetailList() {

		if (listaEstratos.isEmpty()) {
			logger.info("listaEstratos is empty load dropdown");
			listaEstratos = getUtilService().listaEstratos();
		}
		if (listaTipoIndicadorResultado.isEmpty()) {
			logger.info("listaTipoIndicadorResultado is empty load dropdown");
			listaTipoIndicadorResultado = getUtilService()
					.listaTipoIndicadorResultado();
		}
		if (listaUnidadMedida.isEmpty()) {
			logger.info("listaUnidadMedida is empty load dropdown");
			listaUnidadMedida = getUtilService().listaUnidadMedida();
		}
		if (listaTipoBeneficiario.isEmpty()) {
			logger.info("listaTipoBeneficiario is empty load dropdown");
			listaTipoBeneficiario = getUtilService().listaTipoBeneficiario();
		}
		if (listaTipoActividad.isEmpty()) {
			logger.info("listaTipoActividad is empty load dropdown");
			listaTipoActividad = getUtilService().listaTipoActividad();
		}
		if (listaActividadTransferencia.isEmpty()) {
			logger.info("listaActividadTransferencia is empty load dropdown");
			listaActividadTransferencia = getUtilService()
					.listaActividadTransferencia();
		}
		if (listaTipoIndicadorActividad.isEmpty()) {
			logger.info("listaTipoIndicadorActividad is empty load dropdown");
			listaTipoIndicadorActividad = getUtilService()
					.listaTipoIndicadorActividad();
		}
		if (listaTipoMoneda.isEmpty()) {
			logger.info("listaTipoMoneda is empty load dropdown");
			listaTipoMoneda = getUtilService().listaTipoMoneda();
		}

	}

	private ResultadoForm setDataResultadoForm(String definicionResultado,
			String supuestoResultado, Integer metaResultado, Integer estratoId,
			String estratoNombre, Integer duracionMeses) {
		Long newResultadoId = ++incrementResultadoId;
		ResultadoForm resultadoForm = new ResultadoForm();
		resultadoForm.setResultadoID(newResultadoId.intValue());
		resultadoForm.setDefinicionResultado(definicionResultado);
		resultadoForm.setSupuestoResultado(supuestoResultado);
		resultadoForm.setMetaResultado(metaResultado);
		resultadoForm.setEstratoId(estratoId);
		resultadoForm.setEstratoNombre(estratoNombre);
		resultadoForm.setDuracionMeses(duracionMeses);
		return resultadoForm;
	}

	public List<IndicadorMarcoLogicoBean> llenalistIndicadorMarcoLogicoBean(
			MarcoLogico marcoLogico) {

		// listIndicadorMarcoLogico =
		// evaluarService.findIndicadorMarcoLogicoByIdMarcoLogico(idMarcoLogico);
		List<IndicadorMarcoLogico> listIndicadorMarcoLogico = indicadorMarcoLogicoService
				.findIndicadorMarcoLogicoByIdMarcoLogico(marcoLogico
						.getMarcoLogicoID());
		List<IndicadorMarcoLogicoBean> listIndicadorMarcoLogicoBean = new ArrayList<IndicadorMarcoLogicoBean>();

		for (IndicadorMarcoLogico indicadorMarcoLogico : listIndicadorMarcoLogico) {
			IndicadorMarcoLogicoBean indicadorMarcoLogicoBean = new IndicadorMarcoLogicoBean();
			indicadorMarcoLogicoBean
					.setIndicadorMarcoLogicoID(indicadorMarcoLogico
							.getIndicadorMarcoLogicoID());
			indicadorMarcoLogicoBean
					.setDefinicionIndicador(indicadorMarcoLogico
							.getDefinicionIndicador());
			indicadorMarcoLogicoBean.setIndicador(indicadorMarcoLogico
					.getIndicador());
			indicadorMarcoLogicoBean.setMedioVerificacion(indicadorMarcoLogico
					.getMedioVerificacion());
			indicadorMarcoLogicoBean.setMetodoCalculo(indicadorMarcoLogico
					.getMetodoCalculo());
			indicadorMarcoLogicoBean.setSituacionActural(indicadorMarcoLogico
					.getSituacionActural());
			indicadorMarcoLogicoBean
					.setSituacionActualDescripcion(indicadorMarcoLogico
							.getSituacionActural()
							+ " - "
							+ tablaEspecificaService.findTablaEspecificaById(
									indicadorMarcoLogico.getUnidadMedida())
									.getDescripcionCabecera());
			indicadorMarcoLogicoBean.setSituacionFinal(indicadorMarcoLogico
					.getSituacionFinal());
			indicadorMarcoLogicoBean
					.setSituacionFinalDescripcion(indicadorMarcoLogico
							.getSituacionFinal()
							+ " - "
							+ tablaEspecificaService.findTablaEspecificaById(
									indicadorMarcoLogico.getUnidadMedida())
									.getDescripcionCabecera());
			indicadorMarcoLogicoBean.setUnidadMedida(indicadorMarcoLogico
					.getUnidadMedida());
			indicadorMarcoLogicoBean.setMarcoLogico(indicadorMarcoLogico
					.getMarcoLogico().getMarcoLogicoID());
			listIndicadorMarcoLogicoBean.add(indicadorMarcoLogicoBean);
		}
		return listIndicadorMarcoLogicoBean;
	}

}
