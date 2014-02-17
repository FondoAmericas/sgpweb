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

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.ActividadDetallePago;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.DetalleDesembolso;
import pe.com.fondam.sgp.core.domain.LiquidacionGasto;
import pe.com.fondam.sgp.core.domain.TipoCambio;
import pe.com.fondam.sgp.core.service.ActividadDetallePagoService;
import pe.com.fondam.sgp.core.service.ActividadService;
import pe.com.fondam.sgp.core.service.CostoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaCostoActividadService;
import pe.com.fondam.sgp.core.service.CuentaCorrienteService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DesembolsoService;
import pe.com.fondam.sgp.core.service.DetalleDesembolsoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.EvaluarService;
import pe.com.fondam.sgp.core.service.FuenteFinanciadoraService;
import pe.com.fondam.sgp.core.service.LiquidacionGastoService;
import pe.com.fondam.sgp.core.service.PlanOperativoService;
import pe.com.fondam.sgp.core.service.ResultadoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.TipoCambioService;
import pe.com.fondam.sgp.core.util.UtilList;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.controller.security.SecurityController;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class SolicitarDesembolsoController {

	@Resource
	private TablaEspecificaService tablaEspecificaService;

	@Resource
	private EvaluarService evaluarService;

	@Resource
	TipoCambioService tipoCambioService;

	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;

	@Resource
	PlanOperativoService planOperativoService;

	@Resource
	ActividadDetallePagoService actividadDetallePagoService;

	@Resource
	DesembolsoService desembolsoService;

	@Resource
	DetalleDesembolsoService detalleDesembolsoService;

	@Resource
	CronogramaCostoActividadService cronogramaCostoActividadService;

	@Resource
	CostoActividadService costoActividadService;

	@Resource
	ActividadService actividadService;

	@Resource
	ResultadoService resultadoService;

	@Resource
	DatoProyectoService datoProyectoService;

	@Resource
	FuenteFinanciadoraService fuenteFinanciadoraService;

	@Resource
	CuentaCorrienteService cuentaCorrienteService;
	
	@Resource
	LiquidacionGastoService liquidacionGastoService;
	
	
	protected final Log logger = LogFactory
			.getLog(SolicitarDesembolsoController.class);

	//private DatoUsuario datoUsuario = new DatoUsuario();
	private DatoProyecto datoProyecto = new DatoProyecto();
	private DatoPlanOperativo datoPlanOperativo = new DatoPlanOperativo();

	Map<String, Object> model = new HashMap<String, Object>();

	/*************************** metodos ********************************************/
	@RequestMapping(value = "/principal/showSolicitarDesembolso.jspx")
	public ModelAndView showSolicitarDesembolso(HttpServletRequest request) {
		try {

			UserSession userSession = (UserSession) request.getSession()
					.getAttribute(SgpWebConstants.SESSION_USER);
			if (userSession == null) {
				return SecurityController.autenticateErrorRedirect(request);
			}

			limpiarListasEnSesion(request);

			// datos del plan operativo
			datoPlanOperativo = planOperativoService
					.findPlanOperativoByDatoProyectoID(userSession.getDatoProyectoID());// solicitarDesembolsoService.findDatoPlanOperativoByDatoProyectoID2(datoUsuario.getDatoProyectoID());
			datoProyecto = datoProyectoService.findDatoProyectoById(userSession.getDatoProyectoID());
			
			//logger.info("datoPlanOperativo.getDatoPlanOperativoID() : "+ datoPlanOperativo.getDatoPlanOperativoID());
			
			//liquidaciones sin aprobar
			List<LiquidacionGasto> listLiquidacionGastoSinAprobar=liquidacionGastoService.findLiquidacionGastoByDatoProyectoIDSinAprobar(userSession.getDatoProyectoID());
			model.put("cantLiquidacionesSinAprobar",listLiquidacionGastoSinAprobar.size());
			
			//busco el prefijo del estado del plan operativo
			String estadoPrefijoPlanOperativo="xxxx";
			DatoPlanOperativo planOperativo=planOperativoService.findPlanOperativoByDatoProyectoID(userSession.getDatoProyectoID());
			if(planOperativo!=null){
				estadoPrefijoPlanOperativo=detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(planOperativo.getFkIdDetalleEstadoCabEstadoPlanOper()).getPrefijoEstado();
			}
			model.put("estadoPrefijoPlanOperativo",estadoPrefijoPlanOperativo);			
			
			model.put("datoPlanOperativo", datoPlanOperativo);
			model.put("cantidadPeriodosProyecto",
					datoProyecto.getCantidadPeriodo());
			model.put("tipoMonedaPlanOpe",
					datoPlanOperativo.getFkIdtablaespTipoMoneda());
			// model.put("listaTipoCambio", listaTipoCambio);
			model.put(
					"listEstadoDesembolso",
					detalleEstadoCabeceraService
							.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estdese"));
			model.put("listTipoDesembolso", tablaEspecificaService
					.findTablaEspecificabyPrefijoTablaGeneral("TPDB"));
			model.put("listTipoMoneda", tablaEspecificaService
					.findTablaEspecificabyPrefijoTablaGeneral("TMND"));
			model.put("listCuentaCorriente", evaluarService
					.findCuentaCorrienteByIdDatoProyecto(userSession.getDatoProyectoID()));
			model.put("listFuenteFinanciadoras", evaluarService
					.findFuenteFinanciadorasByIdDatoProy(userSession.getDatoProyectoID()));
			model.put("datoProyectoId", userSession.getDatoProyectoID());

			model.put("funcionalidadSelect", "showSolicitarDesembolso.jspx");
			
			List<Desembolso> listDesembolso = llenaInformacionDesembolso(desembolsoService
					.findDesembolsoByDatoProyectoID(userSession.getDatoProyectoID()));
			
			model.put("listDesembolso", listDesembolso);
			
			model.put("cantMuestraMensajeObs",1);

		} catch (Exception e) {
			logger.error("Error en showSolicitarDesembolso.jspx");
		}

		return new ModelAndView("mostrarSolicitarDesembolso", model);

	}

	@RequestMapping(value = "/principal/cargaSaldoAnterior.jspx")
	public void cargaSaldoAnterior(
			@RequestParam(required = true, value = "datoProyectoId") Integer datoProyectoId,
			@RequestParam(required = true, value = "fuenteFinanciadoraId") Integer fuenteFinanciadoraId,
			HttpServletRequest request, HttpServletResponse response) {

		try {
		// calculo de saldo de desembolsos anteriores
		List<ActividadDetallePago> listActividadDetallePago = actividadDetallePagoService
				.findActividadDetallePagoByDatoProyectoIdByFuenteFinanciadoraId(datoProyectoId,fuenteFinanciadoraId);
		double montoGastado = 0;
		for (ActividadDetallePago actividadDetallePago : listActividadDetallePago) {
			montoGastado = montoGastado
					+ actividadDetallePago.getMontoGastado();
		}

		List<Desembolso> listDesembolso = llenaInformacionDesembolso(desembolsoService
				.findDesembolsoByDatoProyectoIDByFuenteFinanciadoraId(datoProyectoId, fuenteFinanciadoraId));
		double montoAutorizado = 0;
		for (Desembolso desembolso : listDesembolso) {
			montoAutorizado = montoAutorizado
					+ desembolso.getMontoAutorizado();
		}
		Double saldoDesembolsoAnterior=montoAutorizado - montoGastado;
					
			String resultado ="<input name=\"txtSaldoDesembolsoAnterior\" id=\"txtSaldoDesembolsoAnterior\""+
						"type=\"text\" disabled=\"disabled\" style=\"width: 90px;\" value=\""+saldoDesembolsoAnterior+"\" />";
			
			PrintWriter out = response.getWriter();
			out.print(resultado);
			out.close();
		} catch (Exception e) {
			logger.error("Error en cargaSaldoAnterior.jspx");
		}
	}
	
	@RequestMapping(value = "/principal/mostrarDetalleCronogramaCostoActividad.jspx")
	public ModelAndView mostrarDetalleCronogramaCostoActividad(
			@RequestParam(required = true, value = "datoPlanOperativoId") Integer datoPlanOperativoId,
			@RequestParam(required = true, value = "fuenteFinanciadoraId") Integer fuenteFinanciadoraId,
			HttpServletRequest request) {
		try {
			// List<Resultado> listResultado =
			// resultadoService.findResultadoXDatoPlanOperativoID(datoPlanOperativoId);
			model.put("listResultado", resultadoService
					.findResultadoXDatoPlanOperativoID(datoPlanOperativoId));
			model.put("fuenteFinanciadoraId", fuenteFinanciadoraId);
		} catch (Exception e) {
			logger.error("Error en mostrarDetalleCronogramaCostoActividad.jspx");
		}

		return new ModelAndView("mostrarDetalleCronogramaCostoActividad", model);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/cargarGrillaCronogramaCostoActividad.jspx")
	public void cargarGrillaCronogramaCostoActividad(
			@RequestParam(required = true, value = "costoActividadId") Integer costoActividadId,
			@RequestParam(required = true, value = "fuenteFinanciadoraId") Integer fuenteFinanciadoraId,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CronogramaCostoActividad> listCronogramaCostoActividad = llenaInfoListCronogramaCostoActividad(cronogramaCostoActividadService
					.findCronogramaCostoActividadByCostoActividadIdByFuenteFinanciadoraIdByCantidadMayorCero(
							costoActividadId, fuenteFinanciadoraId));

			String grillaResultado = null;
			if (listCronogramaCostoActividad.size() > 0) {
				listCronogramaCostoActividad = (List<CronogramaCostoActividad>) UtilList
						.orderAscList(listCronogramaCostoActividad, "periodo");
				int contador = 1;
				for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
					String cebraGrilla;
					String opcionSeleccionar;
					if (cronogramaCostoActividad.getEstadoLiquidacion().equals(
							"1")) {
						opcionSeleccionar = "Liquidado";
					} else {
						opcionSeleccionar = "<a href=\"javascript:seleccionarCronograma('"
								+ cronogramaCostoActividad
										.getCronogramaCostoActividadID()
								+ "','"
								+ cronogramaCostoActividad.getMontoPorGastar()
								+ "')\" class=\"linkSelecciona\">Seleccionar</a>";
					}
					if (contador % 2 == 0) {
						cebraGrilla = "f1";
					} else {
						cebraGrilla = "f2";
					}
					grillaResultado = grillaResultado
							+ "<tr class=\""
							+ cebraGrilla
							+ "\">"
							+ "<td style=\"text-align: center; width: 10%;\"><label>"
							+ cronogramaCostoActividad.getPeriodo()
							+ "</label></td>"
							+ "<td style=\"text-align: center; width: 20%;\"><label>"
							+ cronogramaCostoActividad.getCantidad()
							+ " "
							+ cronogramaCostoActividad
									.getDescripcionUnidadMedida()
							+ "<br/>"
							+ cronogramaCostoActividad.getPartidaGenerica().getDescripcionPartidaGenerica()
							+" - "+cronogramaCostoActividad.getPartidaEspecifica().getDescripcionPartidaEspecifica()
							+ "</label></td>"
							+ "<td style=\"text-align: left; width: 25%;\"><label>"
							+ cronogramaCostoActividad.getFuenteFinanciadora()
									.getInstitucion().getNombreInstitucion()
							+ "</label></td>"
							+ "<td style=\"text-align: center; width: 20%;\"><label>"
							+ cronogramaCostoActividad.getCantidad()
									* cronogramaCostoActividad
											.getPrecioUnitario()
							+ "  "
							+ cronogramaCostoActividad
									.getDescripcionTipoMoneda()
							+ "</label></td>"
							+ "<td style=\"text-align: center; width: 20%;\"><label>"
							+ cronogramaCostoActividad.getMontoPorGastar()
							+ "  "
							+ cronogramaCostoActividad
									.getDescripcionTipoMoneda()
							+ "</label></td>"
							+ "<td style=\"text-align: center; width: 5%;\"><label>"
							+ opcionSeleccionar + "</label></td>" + "</tr>";
					contador = contador + 1;
				}
			} else {
				grillaResultado = "<td colspan=\"6\" style=\"text-align: center;\"><label>No se encontraron resultados</label></td>";
			}

			PrintWriter out = response.getWriter();
			out.print(grillaResultado);
			out.close();

		} catch (Exception e) {
			logger.error("Error en mostrarGrillaCronogramaCostoActividad.jspx");
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/cargarGrillaDetalleCronogramaSeleccionado.jspx")
	public void cargarGrillaDetalleCronogramaSeleccionado(
			@RequestParam(required = true, value = "cronogramaCostoActividadId") Integer cronogramaCostoActividadId,
			@RequestParam(required = true, value = "montoPorGastar") double montoPorGastar,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			// traigo el cronograma por id y le pongo informacion que falta
			CronogramaCostoActividad cronogramaCostoActividad = llenaInfoCronogramaCostoActividad(cronogramaCostoActividadService
					.findCronogramaCostoActividadById(cronogramaCostoActividadId));

			// guardo en sesion -->
			// request.getSession().setAttribute("listCronogramaCostoActividad",
			// listCronogramaCostoActividad)
			// recupero de session
			// -->request.getSession().getAttribute("listCronogramaCostoActividad")

			List<CronogramaCostoActividad> listCronogramaCostoActividad = new ArrayList<CronogramaCostoActividad>();
			if (request.getSession().getAttribute(
					"listCronogramaCostoActividad") != null) {
				listCronogramaCostoActividad = (List<CronogramaCostoActividad>) request
						.getSession().getAttribute(
								"listCronogramaCostoActividad");
			}
			listCronogramaCostoActividad.add(cronogramaCostoActividad);

			if (listCronogramaCostoActividad.size() > 0) {
				listCronogramaCostoActividad = (List<CronogramaCostoActividad>) UtilList
						.orderAscList(listCronogramaCostoActividad, "periodo");

				String grillaResultado = armaDetalleCronogramaCostoActividad(listCronogramaCostoActividad);

				PrintWriter out = response.getWriter();
				out.print(grillaResultado);
				out.close();
			}
			request.getSession().setAttribute("listCronogramaCostoActividad",
					listCronogramaCostoActividad);

		} catch (Exception e) {
			logger.error("Error en cargarGrillaDetalleCronogramaSeleccionado.jspx");
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/deleteRegistroGrillaDetalleCronogramaSeleccionado.jspx")
	public void deleteRegistroGrillaDetalleCronogramaSeleccionado(
			@RequestParam(required = true, value = "cronogramaCostoActividadId") Integer cronogramaCostoActividadId,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			List<CronogramaCostoActividad> listCronogramaCostoActividad = (List<CronogramaCostoActividad>) request
					.getSession().getAttribute("listCronogramaCostoActividad");
			CronogramaCostoActividad cronogramaCostoActividad = new CronogramaCostoActividad();
			// int indiceElimina=0 ;
			for (CronogramaCostoActividad cronogramaCostoActividadTemp : listCronogramaCostoActividad) {
				if (cronogramaCostoActividadTemp
						.getCronogramaCostoActividadID() == cronogramaCostoActividadId) {
					cronogramaCostoActividad = cronogramaCostoActividadTemp;
					break;
				}
			}
			listCronogramaCostoActividad.remove(cronogramaCostoActividad);

			listCronogramaCostoActividad = (List<CronogramaCostoActividad>) UtilList
					.orderAscList(listCronogramaCostoActividad, "periodo");

			String grillaResultado = armaDetalleCronogramaCostoActividad(listCronogramaCostoActividad);
			PrintWriter out = response.getWriter();
			out.print(grillaResultado);
			out.close();

			request.getSession().setAttribute("listCronogramaCostoActividad",
					listCronogramaCostoActividad);

		} catch (Exception e) {
			logger.error("Error en deleteRegistroGrillaDetalleCronogramaSeleccionado.jspx");
		}
	}

	@RequestMapping(value = "/principal/createTipoCambioDesembolso.jspx", method = RequestMethod.GET)
	public ModelAndView createTipoCambioDesembolso(HttpServletRequest request) {

		logger.info("createTipoCambio inicio");
		Map<String, Object> model = new HashMap<String, Object>();

		model.put(
				"listaTipoMonedaDe",
				tablaEspecificaService
						.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_TIPO_MONEDA));
		model.put(
				"listaTipoMonedaA",
				tablaEspecificaService
						.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_TIPO_MONEDA));
		
		return new ModelAndView("createTipoCambio2", model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/cargarGrillaTipoCambio.jspx")
	public void cargarGrillaTipoCambio(
			@RequestParam(required = true, value = "tipoCambio") double tipoCambio,
			@RequestParam(required = true, value = "de") Integer de,
			@RequestParam(required = true, value = "a") Integer a,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			List<TipoCambio> listTipoCambio = new ArrayList<TipoCambio>();

			TipoCambio objTipoCambio = new TipoCambio();
			objTipoCambio.setTipoCambio(tipoCambio);
			objTipoCambio.setFkIdtablaespTipoMonedaConvertDE(de);
			objTipoCambio.setTipoMonedaDENombre(tablaEspecificaService
					.findTablaEspecificaById(de).getDescripcionCabecera());
			objTipoCambio.setFkIdtablaespTipoMonedaConvertA(a);
			objTipoCambio.setTipoMonedaANombre(tablaEspecificaService
					.findTablaEspecificaById(a).getDescripcionCabecera());

			// SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			// Date fecha = new Date();
			// String fecha2 = formato.format(fecha);
			// fecha=formato.parse(fecha2);
			objTipoCambio.setFechaTipoCambio(Calendar.getInstance().getTime());

			if (request.getSession().getAttribute("listTipoCambio") != null) {
				listTipoCambio = (List<TipoCambio>) request.getSession()
						.getAttribute("listTipoCambio");
			}
			listTipoCambio.add(objTipoCambio);

			String grillaResultado = armaDetalleTipoCambio(listTipoCambio);

			request.getSession().setAttribute("listTipoCambio", listTipoCambio);
			PrintWriter out = response.getWriter();
			out.print(grillaResultado);
			out.close();

		} catch (Exception e) {
			logger.error("Error en mostrarGrillaCronogramaCostoActividad.jspx");
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/deleteRegistroGrillaTipoCambio.jspx")
	public void deleteRegistroGrillaTipoCambio(
			@RequestParam(required = true, value = "de") Integer de,
			@RequestParam(required = true, value = "a") Integer a,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			List<TipoCambio> listTipoCambio = (List<TipoCambio>) request
					.getSession().getAttribute("listTipoCambio");
			TipoCambio tipoCambio = new TipoCambio();
			// int indiceElimina=0 ;
			for (TipoCambio tipoCambioTemp : listTipoCambio) {
				if ((tipoCambioTemp.getFkIdtablaespTipoMonedaConvertDE() == de)
						&& (tipoCambioTemp.getFkIdtablaespTipoMonedaConvertA() == a)) {
					tipoCambio = tipoCambioTemp;
					break;
				}
			}
			listTipoCambio.remove(tipoCambio);

			String grillaResultado = armaDetalleTipoCambio(listTipoCambio);
			PrintWriter out = response.getWriter();
			out.print(grillaResultado);
			out.close();

			request.getSession().setAttribute("listTipoCambio", listTipoCambio);

		} catch (Exception e) {
			logger.error("Error en deleteRegistroGrillaDetalleCronogramaSeleccionado.jspx");
		}
	}

	@RequestMapping(value = "/principal/registrarSolicitudDesembolso.jspx")
	public void registrarSolicitudDesembolso(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException {

		//Map<String, String> model = new HashMap<String, String>();

		// captura variables
		int fuenteFinanciadoraId = Integer.valueOf(request
				.getParameter("fuenteFinanciadoraId"));
		int tipoMonedaId = Integer
				.valueOf(request.getParameter("tipoMonedaId"));
		int estadoDesembolsoId = Integer.valueOf(request
				.getParameter("estadoDesembolsoId"));
		int tipoDesembolsoId = Integer.valueOf(request
				.getParameter("tipoDesembolsoId"));
		double saldoDesembolsoAnterior = Double.valueOf(request
				.getParameter("saldoDesembolsoAnterior"));
		double montoSolicitado = Double.valueOf(request
				.getParameter("montoSolicitado"));
		int cuentaCorrienteId = Integer.valueOf(request
				.getParameter("cuentaCorrienteId"));
		String periodo = request.getParameter("periodo");
		int versionPeriodo = Integer.valueOf(request
				.getParameter("versionPeriodo"));
		int datoProyectoId = Integer.valueOf(request
				.getParameter("datoProyectoId"));
		String arrayCronogramaSeleccionado = request
				.getParameter("arrayCronogramaSeleccionado");
		String arrayTipoCambio = request.getParameter("arrayTipoCambio");
		String fechaSolicitud =String.valueOf(request.getParameter("fechaSolicitud"));

		// llena objeto desembolso
		Desembolso desembolso = new Desembolso();
		desembolso.setFkIdtablaespTipoDesembolso(tipoDesembolsoId);
		desembolso.setMontoSolicitado(montoSolicitado);
		desembolso.setFkIdtablaespTipoMoneda(tipoMonedaId);
		desembolso.setSaldoDesembolsoAnterior(saldoDesembolsoAnterior);
		desembolso.setPeriodo(periodo);
		desembolso.setFkIdtablaespEstDesembolso(estadoDesembolsoId);
		desembolso.setVersionDePeriodo(versionPeriodo);
		desembolso.setDatoProyecto(datoProyectoService
				.findDatoProyectoById(datoProyectoId));
		desembolso.setFuenteFinanciadora(fuenteFinanciadoraService
				.findFuenteFinanciadoraById(fuenteFinanciadoraId));
		desembolso.setCuentaCorriente(cuentaCorrienteService
				.findCuentaCorrienteById(cuentaCorrienteId));

		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaSolicita = formateador.parse(fechaSolicitud);

		desembolso.setFechaSolicitud(fechaSolicita);

		// falta tipo de cambio y detalle de cronograma
		List<DetalleDesembolso> listDetalleDesembolso = llenaListCronogramaCostoActividad(
				arrayCronogramaSeleccionado, tipoMonedaId);
		List<TipoCambio> listTipoCambio = llenaListTipoCambio(arrayTipoCambio);

		desembolso = desembolsoService.updateDesembolso(desembolso);
		for (DetalleDesembolso detalleDesembolso : listDetalleDesembolso) {
			detalleDesembolso.setDesembolso(desembolso);
			detalleDesembolsoService.updateDetalleDesembolso(detalleDesembolso);
		}

		for (TipoCambio tipoCambio : listTipoCambio) {
			tipoCambio.setDesembolso(desembolso);
			tipoCambioService.createTipoCambio(tipoCambio);
		}

		limpiarListasEnSesion(request);
	}

	@RequestMapping(value = "/principal/cargaListaDesembolsos.jspx")
	public ModelAndView cargaListaDesembolsos(
			@RequestParam(required = true, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model=new HashMap<String, Object>();
		try {
			
			List<Desembolso> listDesembolso = llenaInformacionDesembolso(desembolsoService
					.findDesembolsoByDatoProyectoID(datoProyectoId));
			model.put("listDesembolso", listDesembolso);
			/*String grillaResultado = armaGrillaListaDesembolso(listDesembolso); // armaDetalleCronogramaCostoActividad(listCronogramaCostoActividad);

			PrintWriter out = response.getWriter();
			out.print(grillaResultado);
			out.close();*/
		} catch (Exception e) {
			logger.error("Error en cargaListaDesembolso.jspx");
		}
		
		return new ModelAndView("divGrillaListaDesembolso", model);
	}

	/************ listas *********************/
	private List<TipoCambio> llenaListTipoCambio(String arrayTipoCambio) {
		List<TipoCambio> listTipoCambio = new ArrayList<TipoCambio>();

		JSONArray jsonListTipoCambio = (JSONArray) JSONSerializer
				.toJSON(arrayTipoCambio);

		for (int i = 0; i < jsonListTipoCambio.size(); i++) {
			TipoCambio tipoCambio = new TipoCambio();

			tipoCambio.setTipoCambio(Double.valueOf(jsonListTipoCambio
					.getJSONObject(i).getString("tipoCambio")));
			tipoCambio.setFkIdtablaespTipoMonedaConvertDE(Integer
					.valueOf(jsonListTipoCambio.getJSONObject(i)
							.getString("de")));
			tipoCambio
					.setFkIdtablaespTipoMonedaConvertA(Integer
							.valueOf(jsonListTipoCambio.getJSONObject(i)
									.getString("a")));
			tipoCambio.setFechaTipoCambio(Calendar.getInstance().getTime());

			listTipoCambio.add(tipoCambio);
		}

		return listTipoCambio;
	}

	private List<DetalleDesembolso> llenaListCronogramaCostoActividad(
			String arrayCronogramaSeleccionado, int tipoMonedaId) {
		List<DetalleDesembolso> listDetalleDesembolso = new ArrayList<DetalleDesembolso>();

		JSONArray jsonlistDetalleDesembolso = (JSONArray) JSONSerializer
				.toJSON(arrayCronogramaSeleccionado);

		for (int i = 0; i < jsonlistDetalleDesembolso.size(); i++) {
			DetalleDesembolso detalleDesembolso = new DetalleDesembolso();

			detalleDesembolso
					.setCronogramaCostoActividadID(cronogramaCostoActividadService.findCronogramaCostoActividadById(Integer
							.valueOf(jsonlistDetalleDesembolso.getJSONObject(i)
									.getString("cronogramaCostoActividadId"))));
			detalleDesembolso.setMontoSolicitado(Double
					.valueOf(jsonlistDetalleDesembolso.getJSONObject(i)
							.getString("montoPorGastar")));
			detalleDesembolso.setFkIdtablaespTipoMonedaMs(tipoMonedaId);

			listDetalleDesembolso.add(detalleDesembolso);
		}

		return listDetalleDesembolso;
	}

	/*
	private String armaGrillaListaDesembolso(List<Desembolso> listDesembolso) {
		String grillaResultado = "";
		int contador = 1;
		//String fechaSolicitud;
		//SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		for (Desembolso desembolso : listDesembolso) {
			String cebraGrilla;
			//fechaSolicitud=formateador.format(desembolso.getFechaSolicitud());
			
			if (contador % 2 == 0) {
				cebraGrilla = "f1";
			} else {
				cebraGrilla = "f2";
			}
			
			grillaResultado = grillaResultado
					+ "<tr class=\""
					+ cebraGrilla
					+ "\">"
					+ "<td style=\"width: 3%; text-align: center;\"><a"
					+ " href=\"javascript:expandcollapse(\'div"
					+ desembolso.getDesembolsoID()
					+ "\', \'one\');\" > "
					+ "<img id=\'imgdiv"
					+ desembolso.getDesembolsoID()
					+ "\' border=\"0\" width=\"15px\" src=\"../images/Plus001.gif\" /> </a>"
					+ "</td>"
					+ "<td style=\"text-align: center; width: 4%;\"><label>"
					+ desembolso.getPeriodo()
					+ "</label></td>"
					+ "<td style=\"text-align: center; width: 4%;\"><label>"
					+ desembolso.getVersionDePeriodo()
					+ " </label></td>"
					+ "<td style=\"text-align: center; width: 17%;\"><label>"
					+ desembolso.getFuenteFinanciadora().getInstitucion()
							.getNombreInstitucion()
					+ "</label></td>"
					+ "<td style=\"text-align: center; width: 15;\"><label>"
					+ desembolso.getSaldoDesembolsoAnterior()
					+ "</label></td>"
					+ "<td style=\"text-align: center; width: 15%;\"><label>"
					+ desembolso.getMontoSolicitado()
					+ "  "
					+ desembolso.getDescripcionTipoMoneda()
					+ "</label></td>"
					+ "<td style=\"text-align: center; width: 15%;\"><label>"
					+ desembolso.getMontoAutorizado()
					+ "  "
					+ desembolso.getDescripcionTipoMoneda()
					+ "</label></td>"
					+ "<td style=\"text-align: center; width: 15%;\"><label>"
					+ desembolso.getCuentaCorriente().getNumeroCuenta()
					+ "</label>"
					+ "</td>"
					+ "<td style=\"text-align: center; width: 10%;\"><label>"
					+ desembolso.getDescripcionEstDesembolso()
					+ "</label></td>"
					+ "</tr>"
					+ "<tr class=\""
					+ cebraGrilla
					+ "\">"
					+ "<td colspan=\"100%\">"
					+ "<div id=\"div"
					+ desembolso.getDesembolsoID()
					+ "\""
					+ "style=\"display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;\">"
					+ "<div><label>"
					+ "Tipo de Desembolso: "
					+ desembolso.getDescripcionTipoDesembolso()
					+ "<br/>" 
					+ "Fecha de Solicitud: "
					+ desembolso.getFechaSolicitud() +" (dd/mm/aaaa)"
					+ "</label></div>"
					+ "<br />"
					+ "<table width=\"100%\">"
					+ "<caption>"
					+ "<label>Detalle de Desembolso</label>"
					+ "</caption>"
					+ "<thead>"
					+ "<tr>"
					+ "<td style=\"text-align: center; width: 25px;\"><label>Resultado</label>"
					+ "</td>"
					+ "<td style=\"text-align: center; width: 20px;\"><label>Actividad</label>"
					+ "</td>"
					+ "<td style=\"text-align: center; width: 15px;\"><label>Cantidad Total Actividad<br/>Rubro - Partida</label></td>"
					+ "<td style=\"text-align: center; width: 10px;\"><label>Periodo</label></td>"
					+ "<td style=\"text-align: center; width: 15px;\"><label>Cantidad<br>Periodo</label></td>"
					+ "<td style=\"text-align: center; width: 15px;\"><label>Monto</label></td>"
					+ "</tr> </thead><tbody>";
			int contadorInt = 1;
			for (DetalleDesembolso detalleDesembolso : desembolso
					.getListDetalleDesembolso()) {
				String cebraGrillaInt;

				if (contadorInt % 2 == 0) {
					cebraGrillaInt = "f1int";
				} else {
					cebraGrillaInt = "f2int";
				}
				grillaResultado += "<tr class=\""
						+ cebraGrillaInt
						+ "\">"
						+ "<td style=\"text-align: center; width: 25px;\"><label>"
						+ detalleDesembolso.getResultado()
								.getDefinicionResultado()
						+ "</label></td>"
						+ "<td style=\"text-align: center; width: 20px;\"><label>"
						+ detalleDesembolso.getActividad().getNombreActividad()
						+ "</label></td>"
						+ "<td style=\"text-align: center; width: 15px;\"><label>"
						+ detalleDesembolso.getCostoActividad()
								.getCantidadTotal()
						+ "  "
						+ detalleDesembolso.getCostoActividad()
								.getDescripcionUnidadMedida()
								+ "<br/>"+detalleDesembolso.getCostoActividad().getPartidaGenerica().getDescripcionPartidaGenerica()
					+" - "
					+ detalleDesembolso.getCostoActividad().getPartidaEspecifica().getDescripcionPartidaEspecifica()
						+ "</label></td>"
						+ "<td style=\"text-align: center; width: 10px;\"><label>"
						+ detalleDesembolso.getCronogramaCostoActividadID()
								.getPeriodo()
						+ "</label></td>"
						+ "<td style=\"text-align: center; width: 15px;\"><label>"
						+ detalleDesembolso.getCronogramaCostoActividadID()
								.getCantidad()
						+ "  "
						+ detalleDesembolso.getCostoActividad()
								.getDescripcionUnidadMedida()
						+ "</label></td>"
						+ "<td style=\"text-align: center; width: 15px;\"><label>"
						+ detalleDesembolso.getMontoSolicitado() + "  "
						+ detalleDesembolso.getDescripcionTipoMonedaMs()
						+ "</label></td>" + "</tr>";
				contadorInt += 1;
			}
			grillaResultado += "</tbody></table></div></td></tr>";

			contador += 1;
		}
		return grillaResultado;
	}
*/
	
	
	private void limpiarListasEnSesion(HttpServletRequest request) {
		// limpio grilla de detalle en session
		if (request.getSession().getAttribute("listCronogramaCostoActividad") != null) {
			request.getSession()
					.removeAttribute("listCronogramaCostoActividad");
		}
		// limpio grilla de tipo de cambio en session
		if (request.getSession().getAttribute("listTipoCambio") != null) {
			request.getSession().removeAttribute("listTipoCambio");
		}
	}

	private String armaDetalleTipoCambio(List<TipoCambio> listTipoCambio) {
		String grillaResultado = "";
		int contador = 1;

		for (TipoCambio tipoCambioTemp : listTipoCambio) {
			String cebraGrilla;

			if (contador % 2 == 0) {
				cebraGrilla = "f1";
			} else {
				cebraGrilla = "f2";
			}
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			String fecha2 = formato.format(tipoCambioTemp.getFechaTipoCambio());
			// fecha=formato.parse(fecha2);

			grillaResultado = grillaResultado + "<tr class=\"" + cebraGrilla
					+ "\">"
					+ "<td style=\"width: 30%; text-align: center; \"><label>"
					+ tipoCambioTemp.getTipoCambio() + "</label></td>"
					+ "<td style=\"width: 25%; text-align: center; \"><label>"
					+ fecha2 + "</label></td>"
					+ "<td style=\"width: 20%; text-align: center; \"><label>"
					+ tipoCambioTemp.getTipoMonedaDENombre() + "</label></td>"
					+ "<td style=\"width: 20%; text-align: center; \"><label>"
					+ tipoCambioTemp.getTipoMonedaANombre() + "</label></td>"
					+ "<td style=\"width: 5%; text-align: center; \"><label>"
					+ "<a href=\"javascript:eliminarSeleccionTipoCambio('"
					+ tipoCambioTemp.getFkIdtablaespTipoMonedaConvertDE()
					+ "','"
					+ tipoCambioTemp.getFkIdtablaespTipoMonedaConvertA()
					+ "')\" class=\"linkSelecciona\">Eliminar</a>"
					+ "</label></td>" + "</tr>";
			contador = contador + 1;
		}
		return grillaResultado;
	}

	private String armaDetalleCronogramaCostoActividad(
			List<CronogramaCostoActividad> listCronogramaCostoActividad) {
		int contador = 1;
		String grillaResultado = "";
		for (CronogramaCostoActividad cronogramaCostoActividadTemp : listCronogramaCostoActividad) {
			String cebraGrilla;

			if (contador % 2 == 0) {
				cebraGrilla = "f1";
			} else {
				cebraGrilla = "f2";
			}
			grillaResultado = grillaResultado
					+ "	<tr class=\""
					+ cebraGrilla
					+ "\">"
					+ "<td style=\"text-align: center; width: 20%;\"><label>"
					+ cronogramaCostoActividadTemp.getCostoActividad()
							.getActividad().getResultado()
							.getDefinicionResultado()
					+ "</label></td>"
					+ "<td style=\"text-align: center; width: 20%;\"><label>"
					+ cronogramaCostoActividadTemp.getCostoActividad()
							.getActividad().getDescripcionActividad()
					+ "</label></td>"
					+ "<td style=\"text-align: center; width: 15%;\"><label>"
					+ cronogramaCostoActividadTemp.getCostoActividad()
							.getCantidadTotal()
					+ " - "
					+ cronogramaCostoActividadTemp.getDescripcionUnidadMedida()
					+ "<br/>"+cronogramaCostoActividadTemp.getCostoActividad().getPartidaGenerica().getDescripcionPartidaGenerica()
					+" - "
					+ cronogramaCostoActividadTemp.getCostoActividad().getPartidaEspecifica().getDescripcionPartidaEspecifica()
					+ "</label></td>"
					+ "<td style=\"text-align: center; width: 10%;\"><label>"
					+ "Periodo "
					+ cronogramaCostoActividadTemp.getPeriodo()
					+ "</label></td>"
					+ "<td style=\"text-align: center; width: 15%;\"><label>"
					+ cronogramaCostoActividadTemp.getCantidad()
					+ " - "
					+ cronogramaCostoActividadTemp.getDescripcionUnidadMedida()
					+ "</label></td>"
					+ "<td style=\"text-align: center; width: 15%;\"><label>"
					+ cronogramaCostoActividadTemp.getMontoPorGastar()
					+ "  "
					+ cronogramaCostoActividadTemp.getDescripcionTipoMoneda()
					+ "</label></td>"
					+ "<td style=\"text-align: center; width: 5%;\"><label>"
					+ "<a href=\"javascript:eliminarSeleccionCronograma('"
					+ cronogramaCostoActividadTemp
							.getCronogramaCostoActividadID()
					+ "')\" class=\"linkSelecciona\">Eliminar</a>" + "</label>"
					+ "</td>" + "</tr>";
			contador = contador + 1;
		}
		return grillaResultado;
	}

	private List<CronogramaCostoActividad> llenaInfoListCronogramaCostoActividad(
			List<CronogramaCostoActividad> listCronogramaCostoActividad) {

		for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
			cronogramaCostoActividad = llenaInfoCronogramaCostoActividad(cronogramaCostoActividad);

		}
		return listCronogramaCostoActividad;
	}

	private CronogramaCostoActividad llenaInfoCronogramaCostoActividad(
			CronogramaCostoActividad cronogramaCostoActividad) {

		CostoActividad costoActividad = costoActividadService
				.findCostoActividadById(cronogramaCostoActividad
						.getCostoActividad().getCostoActividadID());
		cronogramaCostoActividad
				.setDescripcionUnidadMedida(tablaEspecificaService
						.findTablaEspecificaById(
								costoActividad.getFkIdtablaespUnidadMedida())
						.getDescripcionCabecera());
		cronogramaCostoActividad.setPrecioUnitario(costoActividad
				.getPrecioUnitario());
		cronogramaCostoActividad
				.setDescripcionTipoMoneda(tablaEspecificaService
						.findTablaEspecificaById(
								costoActividad.getFkIdtablaespTipoMoneda())
						.getDescripcionCabecera());
		
		cronogramaCostoActividad.setPartidaGenerica(cronogramaCostoActividad.getCostoActividad().getPartidaGenerica());
		cronogramaCostoActividad.setPartidaEspecifica(cronogramaCostoActividad.getCostoActividad().getPartidaEspecifica());
		
		double montoTotalPeriodo = cronogramaCostoActividad.getCantidad()
				* costoActividad.getPrecioUnitario();
		double montoPorGastar = calculaMontoPorGastar(
				actividadDetallePagoService.findActividadDetallePagoByCronogramaCostoActividadId(cronogramaCostoActividad
						.getCronogramaCostoActividadID()), montoTotalPeriodo);
		cronogramaCostoActividad.setMontoPorGastar(montoPorGastar);
		return cronogramaCostoActividad;
	}

	private double calculaMontoPorGastar(
			List<ActividadDetallePago> listActividadDetallePago,
			double montoTotalPeriodo) {

		double montoGastado = 0;
		for (ActividadDetallePago actividadDetallePago : listActividadDetallePago) {
			montoGastado = montoGastado
					+ actividadDetallePago.getMontoGastado();
		}
		return montoTotalPeriodo - montoGastado;
	}

	private List<Desembolso> llenaInformacionDesembolso(
			List<Desembolso> listDesembolso) {

		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

		for (Desembolso desembolso : listDesembolso) {
			desembolso
					.setListDetalleDesembolso(detalleDesembolsoService.llenaListDetalleDesembolso(detalleDesembolsoService
							.findDetalleDesembolsoByDesemboloId(desembolso
									.getDesembolsoID())));
			desembolso.setDescripcionEstDesembolso(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(desembolso.getFkIdtablaespEstDesembolso()).getDescripEstado());
			desembolso.setDescripcionTipoDesembolso(tablaEspecificaService
					.findTablaEspecificaById(
							desembolso.getFkIdtablaespTipoDesembolso())
					.getDescripcionCabecera());
			desembolso.setDescripcionTipoMoneda(tablaEspecificaService
					.findTablaEspecificaById(
							desembolso.getFkIdtablaespTipoMoneda())
					.getDescripcionCabecera());
			
			desembolso.setFechaSolicitudString(formateador.format(desembolso.getFechaSolicitud()));
			desembolso.setFechaAprobacionString(desembolso.getFechaAprobacion()!=null? formateador.format(desembolso.getFechaAprobacion()):"Sin fecha de Aprobacion");
		}
		return listDesembolso;
	}

	/*@SuppressWarnings("unchecked")
	private List<DetalleDesembolso> llenaListDetalleDesembolso(
			List<DetalleDesembolso> listDetalleDesembolso) {

		for (DetalleDesembolso detalleDesembolso : listDetalleDesembolso) {
			detalleDesembolso
					.setCronogramaCostoActividadID(cronogramaCostoActividadService
							.findCronogramaCostoActividadById(detalleDesembolso
									.getCronogramaCostoActividadID()
									.getCronogramaCostoActividadID()));
			detalleDesembolso
					.setCostoActividad(llenaInfoCostoActividad(costoActividadService
							.findCostoActividadById(detalleDesembolso
									.getCronogramaCostoActividadID()
									.getCostoActividad().getCostoActividadID())));
			detalleDesembolso.setActividad(actividadService
					.findActividadById(detalleDesembolso.getCostoActividad()
							.getActividad().getActividadID()));
			detalleDesembolso.setResultado(resultadoService
					.findResultadoByID(detalleDesembolso.getActividad()
							.getResultado().getResultadoID()));
			detalleDesembolso.setDescripcionTipoMonedaMs(tablaEspecificaService
					.findTablaEspecificaById(
							detalleDesembolso.getFkIdtablaespTipoMonedaMs())
					.getDescripcionCabecera());
		}
		return (List<DetalleDesembolso>) UtilList.orderAscList(
				listDetalleDesembolso, "cronogramaCostoActividadID.periodo");
	}

	private CostoActividad llenaInfoCostoActividad(CostoActividad costoActividad) {
		costoActividad.setDescripcionUnidadMedida(tablaEspecificaService
				.findTablaEspecificaById(
						costoActividad.getFkIdtablaespUnidadMedida())
				.getDescripcionCabecera());
		costoActividad.setDescripcionTipoMoneda(tablaEspecificaService
				.findTablaEspecificaById(
						costoActividad.getFkIdtablaespTipoMoneda())
				.getDescripcionCabecera());
		return costoActividad;
	}*/

}