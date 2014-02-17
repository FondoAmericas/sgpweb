package pe.com.fondam.sgp.web.controller.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorResultado;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.helper.PlanOperativoHelper;
import pe.com.fondam.sgp.core.report.ActividadReporteBean;
import pe.com.fondam.sgp.core.report.CostoActividadReporteBean;
import pe.com.fondam.sgp.core.report.CronogramaCostoActividadReporteBean;
import pe.com.fondam.sgp.core.report.FuenteFinanciadoraReporte;
import pe.com.fondam.sgp.core.report.RptContraPartida;
import pe.com.fondam.sgp.core.report.RptDesemboloRecursosDonacion;
import pe.com.fondam.sgp.core.report.RptPlanUsoRecursosOtrasFuentes;
import pe.com.fondam.sgp.core.report.RptResultados;
import pe.com.fondam.sgp.core.service.BeneficiariosPorResultadoService;
import pe.com.fondam.sgp.core.service.DatoPlanOperativoService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.FuenteFinanciadoraService;
import pe.com.fondam.sgp.core.service.InstitucionService;
import pe.com.fondam.sgp.core.service.RegistroPerfilService;
import pe.com.fondam.sgp.core.service.ReportService;
import pe.com.fondam.sgp.core.service.ReporteService;
import pe.com.fondam.sgp.core.service.ResultadoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.util.UtilList;
import pe.com.fondam.sgp.core.util.UtilValidate;
import pe.com.fondam.sgp.report.EngineReportService;
import pe.com.fondam.sgp.report.impl.EngineReportServiceImpl;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.session.UserSession;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class ReportPlanOperativoController {

	private final Log logger = LogFactory
			.getLog(ReportPlanOperativoController.class);

	//***************** fuentes *****************************//
	private static Font titFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,Font.BOLD); 
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 11,Font.BOLD);
	 private static Font norFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.NORMAL);
	 
	 
	// ***************** inyecciones ******************//
	@Resource
	private ReportService reportService;

	@Resource
	private PlanOperativoHelper planOperativoHelper;

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
	DatoProyectoService datoProyectoService;

	@Resource
	DatoPlanOperativoService datoPlanOperativoService;

	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;

	@Resource
	ResultadoService resultadoService;

	@Resource
	ReporteService reporteService;

	@Resource
	FuenteFinanciadoraService fuenteFinanciadoraService;
	
	@Resource
	InstitucionService institucionService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	BeneficiariosPorResultadoService beneficiariosPorResultadoService;	
	
	@Resource
	RegistroPerfilService registroPerfilService;
	
	
	// ***************** metodos ******************//
	@RequestMapping(value = "/principal/showMenuReporte")
	public ModelAndView showMenuReporte(HttpServletRequest request,
			HttpServletResponse resp) throws IOException {
		return new ModelAndView("muestraMenuReporte");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/reportCostoActividadFuente")
	public ModelAndView reportCostoActividadFuente(HttpServletRequest request,
			HttpServletResponse resp) throws IOException {
		Integer datoPlanOperativoID = obtenerDatoPlanOperativoID(request);
		List<FuenteFinanciadora> listFuenteFinanciadora = new ArrayList<FuenteFinanciadora>();
		List<Actividad> listActividad = new ArrayList<Actividad>();
		if (UtilValidate.isNotEmpty(datoPlanOperativoID)) {
			listFuenteFinanciadora = getPlanOperativoHelper()
					.getListFuenteFinanciadoraByDatoPlanOperativoID(
							datoPlanOperativoID);
			listActividad = getPlanOperativoHelper()
					.getListActividadByDatoPlanOperativoID(datoPlanOperativoID);
		}
		if (!listActividad.isEmpty()) {
			listActividad = (List<Actividad>) UtilList.orderAscList(
					listActividad, "actividadID");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listFuenteFinanciadora", listFuenteFinanciadora);
		model.put("listActividad", listActividad);
		return new ModelAndView("reportCostoActividadFuente", model);
	}

	@RequestMapping(value = "/principal/reportContribucionDonacionFuente")
	public ModelAndView reportContribucionDonacionFuente(
			HttpServletRequest request, HttpServletResponse resp)
			throws IOException {
		Integer datoPlanOperativoID = obtenerDatoPlanOperativoID(request);
		List<FuenteFinanciadora> listFuenteFinanciadora = new ArrayList<FuenteFinanciadora>();
		if (UtilValidate.isNotEmpty(datoPlanOperativoID)) {
			listFuenteFinanciadora = getPlanOperativoHelper()
					.getListFuenteFinanciadoraByDatoPlanOperativoID(
							datoPlanOperativoID);
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listFuenteFinanciadora", listFuenteFinanciadora);
		return new ModelAndView("reportContribucionDonacionFuente", model);
	}

	@RequestMapping(value = "/principal/reportPlanOperativoResultados", method = RequestMethod.GET)
	public ModelAndView reportPlanOperativoResultados(
			HttpServletRequest request, HttpServletResponse response) {
		// logger.info("Start ReportPlanOperativoController - reportPlanOperativoResultados");
		List<RptResultados> beanCollection = new ArrayList<RptResultados>();
		// Integer datoPlanOperativoID =
		// Integer.valueOf(request.getParameter("datoPlanOperativoID"));//obtenerDatoPlanOperativoID(request);
		// Integer detalleEstadoCabeceraID =
		// obtenerDetalleEstadoCabeceraID(datoPlanOperativoID);
		DatoPlanOperativo datoPlanOperativo = datoPlanOperativoService
				.findDatoPlanOperativoByID(Integer.valueOf(request
						.getParameter("datoPlanOperativoID")));

		if (UtilValidate.isNotEmpty(datoPlanOperativo)) {
			beanCollection = getReportService()
					.planOperativoResultadosByDatoPlanOperativoID(
							datoPlanOperativo.getDatoPlanOperativoID());
		}
		/** set sourceFileReportName and exportReportName **/
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("/");
		String reportPath = appPath + "WEB-INF/view/report/planoperativo/";
		String sourceFileReportName = reportPath + "Resultados.jrxml";
		String sourceFileSubReportName = reportPath
				+ "Resultados_subreport1.jrxml";
		String exportReportName = "Resultados-" + new Date().getTime() + ".pdf";
		String reportStatus = detalleEstadoCabeceraService
				.findDetalleEstadoCabeceraById(
						datoPlanOperativo
								.getFkIdDetalleEstadoCabEstadoPlanOper())
				.getDescripEstado();

		/** set parameters report values **/
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("REPORT_TITLE", "RESULTADOS");
		parameters.put("REPORT_COMPANY_NAME",
				FondamConstans.REPORT_COMPANY_NAME);
		parameters.put("REPORT_IMAGE", appPath + "images/fondam.jpg");
		parameters.put("SUBREPORT_DIR", reportPath);
		parameters.put("REPORT_STATUS", reportStatus);

		/** print report on web browser **/
		printReportWebToPdf(response, beanCollection, parameters,
				sourceFileReportName, sourceFileSubReportName, exportReportName);
		// logger.info("Finish ReportPlanOperativoController - reportPlanOperativoResultados");
		return null;
	}

	@RequestMapping(value = "/principal/reportPlanOperativoCronogramaActividades", method = RequestMethod.GET)
	public ModelAndView reportPlanOperativoCronogramaActividades(
			HttpServletRequest request, HttpServletResponse response)
			throws JRException {

		DatoPlanOperativo datoPlanOperativo = datoPlanOperativoService
				.findDatoPlanOperativoByID(Integer.valueOf(request
						.getParameter("datoPlanOperativoID")));
		DatoProyecto datoProyecto = datoProyectoService
				.findDatoProyectoById(datoPlanOperativo.getDatoProyecto()
						.getDatoProyectoID());

		List<Resultado> listResultado = resultadoService
				.llenaResultadoActividadesMeta(resultadoService
						.findResultadoXDatoPlanOperativoID(datoPlanOperativo
								.getDatoPlanOperativoID()));
		List<RptResultados> listRptResultados = reporteService
				.llenaRptResultadosReporteCronogramaActividades(listResultado);
		/*
		 * if(UtilValidate.isNotEmpty(datoPlanOperativo)){ listResultado =
		 * resultadoService.llenaResultadoActividades(resultadoService.
		 * findResultadoXDatoPlanOperativoID
		 * (datoPlanOperativo.getDatoPlanOperativoID())); //getReportService().
		 * planOperativoCronogramaActividadByDatoPlanOperativoID
		 * (datoPlanOperativo.getDatoPlanOperativoID()); }
		 */
		/** set sourceFileReportName and exportReportName **/
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("/");
		String reportPath = appPath + "WEB-INF/view/report/planoperativo/";

		String sourceFileReportName;
		if (datoProyecto.getCantidadPeriodo() > 8) {
			sourceFileReportName = reportPath + "CronogramaActividades2.jrxml";
		} else {
			sourceFileReportName = reportPath + "CronogramaActividades.jrxml";
		}

		String sourceFileSubReportName = reporteService
				.pathCronogramaActividadPorCantidadPeriodos(datoProyecto,
						reportPath);

		String exportReportName = "CronogramaActividades-"
				+ new Date().getTime() + ".pdf";
		String reportStatus = detalleEstadoCabeceraService
				.findDetalleEstadoCabeceraById(
						datoPlanOperativo
								.getFkIdDetalleEstadoCabEstadoPlanOper())
				.getDescripEstado();

		/** set parameters report values **/
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("REPORT_TITLE", "CRONOGRAMA DE ACTIVIDADES");
		parameters.put("REPORT_COMPANY_NAME",
				FondamConstans.REPORT_COMPANY_NAME);
		parameters.put("REPORT_IMAGE", appPath + "images/fondam.jpg");
		parameters.put("SUBREPORT_DIR", reportPath);
		parameters.put("REPORT_STATUS", reportStatus);

		/** print report on web browser **/
		printReportWebToPdf(response, listRptResultados, parameters,
				sourceFileReportName, sourceFileSubReportName, exportReportName);

		return null;
	}

	@RequestMapping(value = "/principal/reportPlanOperativoCostoActividadFuente", method = RequestMethod.GET)
	public void reportPlanOperativoCostoActividadFuente(
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer  datoPlanOperativoID,
			HttpServletRequest request, HttpServletResponse response)
			throws DocumentException, IOException {

		DatoPlanOperativo datoPlanOperativo = datoPlanOperativoService.findDatoPlanOperativoByID(datoPlanOperativoID);
		datoPlanOperativo.setDescripcionDetalleEstadoCabEstadoPlanOper(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(datoPlanOperativo.getFkIdDetalleEstadoCabEstadoPlanOper()).getDescripEstado());
		DatoProyecto datoProyecto = datoProyectoService.findDatoProyectoById(datoPlanOperativo.getDatoProyecto().getDatoProyectoID());

		List<Resultado> listResultado = resultadoService.llenaResultadoActividadesCosto(resultadoService.findResultadoXDatoPlanOperativoID(datoPlanOperativo
						.getDatoPlanOperativoID()));

		List<FuenteFinanciadoraReporte> listFuenteFinanciadoraReporte=llenaFuenteFinanciadoraReporte(listResultado, datoProyecto);
		
		
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("/");
		 
		String exportReportName = "CostoActividadFuente-"
				+ new Date().getTime() + ".pdf";
		
		Document doc = new Document(PageSize.A4_LANDSCAPE.rotate(), 10,10,5,5);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfWriter.getInstance(doc, baos);

		doc = reporteCostoActividad(doc,appPath,datoPlanOperativo,listFuenteFinanciadoraReporte );
		
		printReportePage(baos, response, exportReportName);

	}

	@RequestMapping(value = "/principal/reportPlanOperativoEstructuraInversionFinanciamiento", method = RequestMethod.GET)
	public void reportPlanOperativoEstructuraInversionFinanciamiento(
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer  datoPlanOperativoID,
			HttpServletRequest request, HttpServletResponse response) throws DocumentException, MalformedURLException, IOException {
		
		DatoPlanOperativo datoPlanOperativo = datoPlanOperativoService.findDatoPlanOperativoByID(datoPlanOperativoID);
		datoPlanOperativo.setDescripcionDetalleEstadoCabEstadoPlanOper(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(datoPlanOperativo.getFkIdDetalleEstadoCabEstadoPlanOper()).getDescripEstado());
		DatoProyecto datoProyecto = datoProyectoService.findDatoProyectoById(datoPlanOperativo.getDatoProyecto().getDatoProyectoID());

		//lleno los resultados del planoperativo
		List<Resultado> listResultado = resultadoService.llenaResultadoActividadesCosto(resultadoService.findResultadoXDatoPlanOperativoID(datoPlanOperativo
						.getDatoPlanOperativoID()));

		List<RptResultados> listRptResultadosReporteEstructuraInversionFinanciamiento=llenaRptResultadosReporteEstructuraInversionFinanciamiento(listResultado, datoProyecto);
		
		
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("/");
		 
		String exportReportName = "EstructuraInversionFinanciamiento-"
				+ new Date().getTime() + ".pdf";
		
		Document doc = new Document(PageSize.A4_LANDSCAPE.rotate(), 10,10,5,5);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfWriter.getInstance(doc, baos);

		doc = reporteEstructuraInversionFinanciamiento(doc,appPath,datoPlanOperativo,listRptResultadosReporteEstructuraInversionFinanciamiento, datoProyecto );
		
		printReportePage(baos, response, exportReportName);
		
	}

	/*
	@RequestMapping(value = "/principal/reportPlanOperativoContribucionDonacion", method = RequestMethod.GET)
	public ModelAndView reportPlanOperativoContribucionDonacion(
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Start ReportPlanOperativoController - reportPlanOperativoContribucionDonacion");
		List<RptContribucionDonacion> beanCollection = new ArrayList<RptContribucionDonacion>();
		Integer datoPlanOperativoID = obtenerDatoPlanOperativoID(request);
		Integer detalleEstadoCabeceraID = obtenerDetalleEstadoCabeceraID(datoPlanOperativoID);
		Integer fuenteFinanciadoraID = Integer.parseInt(request
				.getParameter("fuenteFinanciadoraID"));
		if (UtilValidate.isNotEmpty(datoPlanOperativoID)) {
			logger.info("datoPlanOperativoID = " + datoPlanOperativoID);
			logger.info("fuenteFinanciadoraID = " + fuenteFinanciadoraID);
			beanCollection = getReportService()
					.planOperativoContribucionDonacion(datoPlanOperativoID,
							fuenteFinanciadoraID);
			logger.info("beanCollection.size = " + beanCollection.size());
		}
		// set sourceFileReportName and exportReportName 
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("/");
		// appPath = "C:/workspace/sgp/sgp-webapp/src/main/webapp/";
		logger.info("appPath = " + appPath);
		String reportPath = appPath + "WEB-INF/view/report/planoperativo/";
		String sourceFileReportName = reportPath + "ContribucionDonacion.jrxml";
		String sourceFileSubReportName = reportPath
				+ "ContribucionDonacionDetalles.jrxml";
		String exportReportName = "ContribucionDonacion-"
				+ new Date().getTime() + ".pdf";
		String reportStatus = getReportService()
				.planOperativoEstadoDescripcion(detalleEstadoCabeceraID);
		// set parameters report values 
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("REPORT_TITLE", "3.4 CONTRIBUCION DE LA DONACION");
		parameters.put("REPORT_SUB_TITLE", "3.4.1 CONTRIBUCION DE : ");
		parameters.put("REPORT_COMPANY_NAME",
				FondamConstans.REPORT_COMPANY_NAME);
		parameters.put("REPORT_IMAGE", appPath + "images/fondam.jpg");
		parameters.put("SUBREPORT_DIR", reportPath);
		parameters.put("REPORT_STATUS", reportStatus);
		// print report on web browser 
		printReportWebToPdf(response, beanCollection, parameters,
				sourceFileReportName, sourceFileSubReportName, exportReportName);
		logger.info("Finish ReportPlanOperativoController - reportPlanOperativoContribucionDonacion");
		return null;
	}*/

	@RequestMapping(value = "/principal/reportPlanOperativoBeneficiario", method = RequestMethod.GET)
	public void reportPlanOperativoBeneficiario(
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer  datoPlanOperativoID,
			HttpServletRequest request, HttpServletResponse response) throws DocumentException, MalformedURLException, IOException {
		
		DatoPlanOperativo datoPlanOperativo = datoPlanOperativoService.findDatoPlanOperativoByID(datoPlanOperativoID);
		datoPlanOperativo.setDescripcionDetalleEstadoCabEstadoPlanOper(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(datoPlanOperativo.getFkIdDetalleEstadoCabEstadoPlanOper()).getDescripEstado());
		DatoProyecto datoProyecto = datoProyectoService.findDatoProyectoById(datoPlanOperativo.getDatoProyecto().getDatoProyectoID());

		//lleno los resultados del planoperativo
		List<Resultado> listResultado = resultadoService.llenaResultadoBeneficiarios(resultadoService.findResultadoXDatoPlanOperativoID(datoPlanOperativo
						.getDatoPlanOperativoID()));

		List<TablaEspecifica> listEstratos= tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_ESTRATOS);
		List<TablaEspecifica> listTipoBeneficiario= tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_TIPO_BENEFICIARIO);
		
		List<RptResultados> listRptResultadosReporteBeneficiario = llenaRptResultadosReporteBeneficiario(listResultado, datoProyecto, listEstratos, listTipoBeneficiario);
	
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("/");
		 
		String exportReportName = "Beneficiario-"
				+ new Date().getTime() + ".pdf";
		
		Document doc = new Document(PageSize.A4_LANDSCAPE.rotate(), 10,10,5,5);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfWriter.getInstance(doc, baos);

		doc = reporteBeneficiario(doc,appPath,datoPlanOperativo,listRptResultadosReporteBeneficiario, datoProyecto, listEstratos, listTipoBeneficiario );
		
		printReportePage(baos, response, exportReportName);
		
	}

	/*
	@RequestMapping(value = "/principal/reportPlanOperativoOperacionesCostosCronogramaResultados", method = RequestMethod.GET)
	public ModelAndView reportPlanOperativoOperacionesCostosCronogramaResultados(
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Start ReportPlanOperativoController - reportPlanOperativoOperacionesCostosCronogramaResultados");
		List<RptOperacionCostoCronogramaResultado> beanCollection = new ArrayList<RptOperacionCostoCronogramaResultado>();
		Integer datoPlanOperativoID = obtenerDatoPlanOperativoID(request);
		Integer detalleEstadoCabeceraID = obtenerDetalleEstadoCabeceraID(datoPlanOperativoID);
		if (UtilValidate.isNotEmpty(datoPlanOperativoID)) {
			beanCollection = getReportService()
					.planOperativoOperacionesCostosCronogramaResultados(
							datoPlanOperativoID);
		}
		// set sourceFileReportName and exportReportName 
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("/");
		String reportPath = appPath + "WEB-INF/view/report/planoperativo/";
		String sourceFileReportName = reportPath + "OperacionesYCostos3.jrxml";
		String exportReportName = "OperacionesCostosCronogramaResultados-"
				+ new Date().getTime() + ".pdf";
		String reportStatus = getReportService()
				.planOperativoEstadoDescripcion(detalleEstadoCabeceraID);
		// set parameters report values
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("REPORT_TITLE", "III. OPERACIONES Y COSTO");
		parameters.put("REPORT_SUB_TITLE", "3.1 CRONOGRAMA DE RESULTADOS");
		parameters.put("REPORT_COMPANY_NAME",
				FondamConstans.REPORT_COMPANY_NAME);
		parameters.put("REPORT_IMAGE", appPath + "images/fondam.jpg");
		parameters.put("REPORT_STATUS", reportStatus);
		// print report on web browser 
		printReportWebToPdf(response, beanCollection, parameters,
				sourceFileReportName, null, exportReportName);
		logger.info("Finish ReportPlanOperativoController - reportPlanOperativoOperacionesCostosCronogramaResultados");
		return null;
	}*/

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/reportPlanOperativoCronogramaResultados.jspx", method = RequestMethod.GET)
	public void reportPlanOperativoCronogramaResultados(
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer  datoPlanOperativoID,
			HttpServletRequest request, HttpServletResponse response) throws DocumentException, MalformedURLException, IOException {
		
		DatoPlanOperativo datoPlanOperativo = datoPlanOperativoService.findDatoPlanOperativoByID(datoPlanOperativoID);
		datoPlanOperativo.setDescripcionDetalleEstadoCabEstadoPlanOper(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(datoPlanOperativo.getFkIdDetalleEstadoCabEstadoPlanOper()).getDescripEstado());
		DatoProyecto datoProyecto = datoProyectoService.findDatoProyectoById(datoPlanOperativo.getDatoProyecto().getDatoProyectoID());

		//lleno los resultados del planoperativo
		List<Resultado> listResultado = resultadoService.llenaResultadoCronogramaMetas((List<Resultado>) UtilList.orderAscList(resultadoService.findResultadoXDatoPlanOperativoID(datoPlanOperativo.getDatoPlanOperativoID()),"codigoResultado"));

		//List<TablaEspecifica> listEstratos= tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_ESTRATOS);
		//List<TablaEspecifica> listTipoBeneficiario= tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_TIPO_BENEFICIARIO);
		
		List<RptResultados> listRptResultadosReporteCronogramaResultado = llenaRptResultadosReporteCronogramaResultadoMeta(listResultado, datoProyecto);
	
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("/");
		 
		String exportReportName = "CronogramaResultado-"
				+ new Date().getTime() + ".pdf";
		
		Document doc = new Document(PageSize.A4_LANDSCAPE.rotate(), 10,10,5,5);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfWriter.getInstance(doc, baos);

		doc = reporteCronogramaResultado(doc,appPath,datoPlanOperativo,listRptResultadosReporteCronogramaResultado, datoProyecto);
		
		printReportePage(baos, response, exportReportName);
		
	}
	
	@RequestMapping(value = "/principal/reportPlanOperativoContraPartida", method = RequestMethod.GET)
	public ModelAndView reportPlanOperativoContraPartida(
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Start ReportPlanOperativoController - reportPlanOperativoContrapartida");
		List<RptContraPartida> beanCollection = new ArrayList<RptContraPartida>();
		Integer datoPlanOperativoID = obtenerDatoPlanOperativoID(request);
		Integer detalleEstadoCabeceraID = obtenerDetalleEstadoCabeceraID(datoPlanOperativoID);
		beanCollection = getReportService().planOperativoContraPartida(
				datoPlanOperativoID);

		/** set sourceFileReportName and exportReportName **/
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("/");
		String reportPath = appPath + "WEB-INF/view/report/planoperativo/";
		String sourceFileReportName = reportPath + "ContraPartida.jrxml";
		String sourceFileSubReportName = reportPath
				+ "ContraPartidaDetalles.jrxml";
		String exportReportName = "ContraPartida-" + new Date().getTime()
				+ ".pdf";
		String reportStatus = getReportService()
				.planOperativoEstadoDescripcion(detalleEstadoCabeceraID);
		/** set parameters report values **/
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("REPORT_TITLE", "CONTRAPARTIDA");
		parameters.put("REPORT_COMPANY_NAME",
				FondamConstans.REPORT_COMPANY_NAME);
		parameters.put("REPORT_IMAGE", appPath + "images/fondam.jpg");
		parameters.put("SUBREPORT_DIR", reportPath);
		parameters.put("REPORT_STATUS", reportStatus);
		/** print report on web browser **/
		printReportWebToPdf(response, beanCollection, parameters,
				sourceFileReportName, sourceFileSubReportName, exportReportName);

		return null;
	}

	
	@RequestMapping(value = "/principal/reportPlanOperativoCostoActividadFuenteMontos", method = RequestMethod.GET)
	public void reportPlanOperativoCostoActividadFuenteMontos(
			@RequestParam(required = true, value = "datoPlanOperativoID") Integer  datoPlanOperativoID,
			HttpServletRequest request, HttpServletResponse response)
			throws DocumentException, IOException {

		DatoPlanOperativo datoPlanOperativo = datoPlanOperativoService.findDatoPlanOperativoByID(datoPlanOperativoID);
		datoPlanOperativo.setDescripcionDetalleEstadoCabEstadoPlanOper(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(datoPlanOperativo.getFkIdDetalleEstadoCabEstadoPlanOper()).getDescripEstado());
		DatoProyecto datoProyecto = datoProyectoService.findDatoProyectoById(datoPlanOperativo.getDatoProyecto().getDatoProyectoID());

		List<Resultado> listResultado = resultadoService.llenaResultadoActividadesCosto(resultadoService.findResultadoXDatoPlanOperativoID(datoPlanOperativo
						.getDatoPlanOperativoID()));

		List<FuenteFinanciadoraReporte> listFuenteFinanciadoraReporte=llenaFuenteFinanciadoraReporte(listResultado, datoProyecto);
		
		
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("/");
		 
		String exportReportName = "CostoActividadFuenteMontos-"
				+ new Date().getTime() + ".pdf";
		
		Document doc = new Document(PageSize.A4_LANDSCAPE.rotate(), 10,10,5,5);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfWriter.getInstance(doc, baos);

		doc = reporteCostoActividadMontos(doc,appPath,datoPlanOperativo,listFuenteFinanciadoraReporte );
		
		printReportePage(baos, response, exportReportName);

	}
	

	@RequestMapping(value = "/principal/reportPlanOperativoDesembolsoRecursosDonacion", method = RequestMethod.GET)
	public ModelAndView reportPlanOperativoDesembolsoRecursosDonacion(
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Start ReportPlanOperativoController - reportPlanOperativoDesembolsoRecursosDonacion");
		List<RptDesemboloRecursosDonacion> beanCollection = new ArrayList<RptDesemboloRecursosDonacion>();
		Integer datoPlanOperativoID = obtenerDatoPlanOperativoID(request);
		Integer detalleEstadoCabeceraID = obtenerDetalleEstadoCabeceraID(datoPlanOperativoID);
		beanCollection = reportService
				.planOperativoDesembolsoRecursosDonacion(datoPlanOperativoID);

		/** set sourceFileReportName and exportReportName **/
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("/");
		String reportPath = appPath + "WEB-INF/view/report/planoperativo/";
		String sourceFileReportName = reportPath
				+ "DesembolsoRecursoDonacion3.jrxml";
		String sourceFileSubReportName = reportPath
				+ "DesembolsoRecursoDonacionDetalles3.jrxml";
		String exportReportName = "DesembolsoRecursoDonacion-"
				+ new Date().getTime() + ".pdf";
		String reportStatus = getReportService()
				.planOperativoEstadoDescripcion(detalleEstadoCabeceraID);
		/** set parameters report values **/
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("REPORT_TITLE", "DESEMBOLOSO DE RECURSOS DE DONACION");
		parameters.put("REPORT_SUB_TITLE",
				"4.1.1 cronograma de Desembolsos de los Recursos de :");
		parameters.put("REPORT_COMPANY_NAME",
				FondamConstans.REPORT_COMPANY_NAME);
		parameters.put("REPORT_IMAGE", appPath + "images/fondam.jpg");
		parameters.put("SUBREPORT_DIR", reportPath);
		parameters.put("REPORT_STATUS", reportStatus);
		/** print report on web browser **/
		printReportWebToPdf(response, beanCollection, parameters,
				sourceFileReportName, sourceFileSubReportName, exportReportName);
		logger.info("Finish ReportPlanOperativoController - reportPlanOperativoDesembolsoRecursosDonacion");
		return null;
	}


	@RequestMapping(value = "/principal/reportPlanOperativoPlanUsoRecursosOtrasFuentes", method = RequestMethod.GET)
	public ModelAndView reportPlanOperativoPlanUsoRecursosOtrasFuentes(
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Start ReportPlanOperativoController - reportPlanOperativoPlanUsoRecursosOtrasFuentes");
		List<RptPlanUsoRecursosOtrasFuentes> beanCollection = new ArrayList<RptPlanUsoRecursosOtrasFuentes>();
		Integer datoPlanOperativoID = obtenerDatoPlanOperativoID(request);
		Integer detalleEstadoCabeceraID = obtenerDetalleEstadoCabeceraID(datoPlanOperativoID);
		if (UtilValidate.isNotEmpty(datoPlanOperativoID)) {
			beanCollection = getReportService()
					.planOperativoPlanUsoRecursosOtrasFuentes(
							datoPlanOperativoID);
		}
		/** set sourceFileReportName and exportReportName **/
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("/");
		String reportPath = appPath + "WEB-INF/view/report/planoperativo/";
		String sourceFileReportName = reportPath
				+ "PlanUsoRecursosOtrasFuentes.jrxml";
		String sourceFileSubReportName = reportPath
				+ "PlanUsoRecursosOtrasFuentesDetalles.jrxml";
		String exportReportName = "PlanUsoRecursosOtrasFuentes-"
				+ new Date().getTime() + ".pdf";
		String reportStatus = getReportService()
				.planOperativoEstadoDescripcion(detalleEstadoCabeceraID);
		/** set parameters report values **/
		Map<String, String> parameters = new HashMap<String, String>();
		parameters
				.put("REPORT_TITLE",
						"VI PLAN DE USO DE RECURSOS DE OTRAS FUENTES(ONG, Beneficiarios, Otros)");
		parameters.put("REPORT_SUB_TITLE",
				"6.1 CRONOGRAMA DE EMPLEO DE RECURSOS/ACTIVIDADES");
		parameters.put("REPORT_COMPANY_NAME",
				FondamConstans.REPORT_COMPANY_NAME);
		parameters.put("REPORT_IMAGE", appPath + "images/fondam.jpg");
		parameters.put("SUBREPORT_DIR", reportPath);
		parameters.put("REPORT_STATUS", reportStatus);
		/** print report on web browser **/
		printReportWebToPdf(response, beanCollection, parameters,
				sourceFileReportName, sourceFileSubReportName, exportReportName);
		logger.info("Finish ReportPlanOperativoController - reportPlanOperativoPlanUsoRecursosOtrasFuentes");
		return null;
	}

	
	private void printReportWebToPdf(HttpServletResponse response,
			Collection<?> beanCollection, Map<?, ?> parameters,
			String sourceFileReportName, String sourceFileSubReportName,
			String exportReportName) {
		// logger.info("Start printReportWebToPdf");
		try {
			// logger.info("sourceFileReportName = " + sourceFileReportName);
			// logger.info("exportReportName = " + exportReportName);

			EngineReportService engineReportService = new EngineReportServiceImpl();
			byte[] byteArray = null;
			if (sourceFileSubReportName != null) {
				// logger.info("exportReportWithSubReportBytePdf");
				byteArray = engineReportService
						.exportReportWithSubReportBytePdf(beanCollection,
								parameters, sourceFileReportName,
								sourceFileSubReportName);
			} else {
				// logger.info("exportReportBytePdf");
				byteArray = engineReportService.exportReportBytePdf(
						beanCollection, parameters, sourceFileReportName);
			}
			ServletOutputStream out = response.getOutputStream();
			response.setContentType("application/pdf");

			response.setHeader("Content-Disposition", "inline; filename = "
					+ exportReportName);
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);

			response.setContentLength(byteArray.length);
			out.write(byteArray, 0, byteArray.length);
			out.flush();
			out.close();
		} catch (JRException e) {
			e.printStackTrace();
			// logger.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			// logger.error(e);
		}
		// logger.info("Finsh printReportWebToPdf");
	}

	
	
	//********************************* metodos privados **********************************//
	private Integer obtenerDatoPlanOperativoID(HttpServletRequest request) {
		Integer datoProyectoID = null;
		Integer datoPlanOperativoID = null;
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		// logger.info("userSession = " + userSession);
		if (UtilValidate.isNotEmpty(userSession)) {
			datoProyectoID = userSession.getDatoProyectoID();
			// logger.info("datoProyectoID = " + datoProyectoID);
		}
		if (UtilValidate.isNotEmpty(datoProyectoID)) {
			datoPlanOperativoID = getPlanOperativoHelper()
					.getDatoPlanOperativoIDByDatoProyectoID(datoProyectoID);
		}
		return datoPlanOperativoID;
	}

	
	private Integer obtenerDetalleEstadoCabeceraID(Integer datoPlanOperativoID) {
		// logger.info("warren datoPlanOperativoID = " + datoPlanOperativoID);
		return getPlanOperativoHelper().getDetalleEstadoCabeceraID(
				datoPlanOperativoID);
	}

	// **************** metodos internos *******************************//

	
	private void printReportePage(ByteArrayOutputStream baos,
			HttpServletResponse response, String exportReportName)
			throws IOException {
		response.setContentType("application/pdf");

		response.setHeader("Content-Disposition", "inline; filename = "
				+ exportReportName);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentLength(baos.size());
		OutputStream out = response.getOutputStream();
		baos.writeTo(out);
		// out.write(byteArray, 0, byteArray.length);
		out.flush();
		out.close();

	}

	
	private static void agregarLineasEnBlanco(Paragraph parrafo, int nLineas) {
		for (int i = 0; i < nLineas; i++)
			parrafo.add(new Paragraph(" "));
	}

	
	private Document reporteCostoActividad(Document doc, String appPath, DatoPlanOperativo datoPlanOperativo,List<FuenteFinanciadoraReporte> listFuenteFinanciadoraReporte)
			throws DocumentException, MalformedURLException, IOException {
			
		Locale localeMoneda = null;
		
		switch (datoPlanOperativo.getFkIdtablaespTipoMoneda()) {
		case 168:
			//simboloMoneda="S/.";
			localeMoneda= new Locale("es");
			break;

		case 169:
			//simboloMoneda="US$";
			localeMoneda= new Locale("en","US");
			break;

		case 170:
			//simboloMoneda="€";
			localeMoneda= new Locale("fr");
			break;
	}
		NumberFormat nf = NumberFormat.getCurrencyInstance(localeMoneda);
		
		doc.open();
		Paragraph parrafoHoja = new Paragraph();
		
		Image img=Image.getInstance(appPath+"images/fondam.jpg");
		img.setAlignment(Image.ALIGN_LEFT);
		img.scaleAbsolute(120,70);//(newWidth, newHeight)
		parrafoHoja.add(img);
		parrafoHoja.setAlignment(Paragraph.ALIGN_LEFT);
		
		Chunk poEstado= new Chunk("Plan Operativo: ",catFont);
		Chunk poEstadoText= new Chunk( datoPlanOperativo.getDescripcionDetalleEstadoCabEstadoPlanOper(),norFont);
		
		parrafoHoja.add(poEstado);
		parrafoHoja.add(poEstadoText);
		parrafoHoja.setAlignment(Paragraph.ALIGN_RIGHT);
		
		agregarLineasEnBlanco(parrafoHoja, 1);
		doc.add(parrafoHoja);

		Paragraph parrafoTituloCentral = new Paragraph("Costo Fisico de Actividad Por Fuente Financiadora",titFont);
		parrafoTituloCentral.setAlignment(Paragraph.ALIGN_CENTER);
		
		agregarLineasEnBlanco(parrafoTituloCentral, 1);
		doc.add(parrafoTituloCentral);
		
		for (FuenteFinanciadoraReporte fuenteFinanciadoraReporte : listFuenteFinanciadoraReporte) {
			Paragraph parrafoDato= new Paragraph();
			
			Chunk poFuenteTitulo= new Chunk("Fuente Financiadora: ",titFont);
			Chunk poFuente= new Chunk(fuenteFinanciadoraReporte.getFuenteFinanciadoraDescripcion(),norFont);
			parrafoDato.add(poFuenteTitulo);
			parrafoDato.add(poFuente);
			parrafoDato.setAlignment(Paragraph.ALIGN_LEFT);
			
			
			//agregarLineasEnBlanco(parrafoDato, 1);
			doc.add(parrafoDato);
			
			for (ActividadReporteBean actividadReporteBean : fuenteFinanciadoraReporte.getListActividadReporteBean()) {
				Paragraph parrafoAct= new Paragraph();
				
				Chunk actTitulo= new Chunk(" * Costos desagregados de la Actividad: ",catFont);
				Chunk actDato= new Chunk(actividadReporteBean.getCodigoActividadString()+" " +actividadReporteBean.getNombreActividad(),norFont);
				parrafoAct.add(actTitulo);
				parrafoAct.add(actDato);
				parrafoAct.setAlignment(Paragraph.ALIGN_LEFT);
				
				
				agregarLineasEnBlanco(parrafoAct, 1);
				doc.add(parrafoAct);
				
				if(actividadReporteBean.getListCostoActividadReporteBean().size()>0){
				//tabla
				Integer cantCol=5+fuenteFinanciadoraReporte.getCantidadPeriodos();
				PdfPTable tabla= new PdfPTable(cantCol);
				
				//anchos de celdas de la tabla
				float[] medidaCeldas = new float[cantCol];
				medidaCeldas[0] = 2.50f;
				medidaCeldas[1] = 3.00f;
				medidaCeldas[2] = 1.50f;
				medidaCeldas[3] = 1.50f;
				medidaCeldas[4] = 1.50f;
				
				float medidaPeriodo=1.00f;
				for (int i = 5; i < cantCol; i++) {
					medidaCeldas[i]=medidaPeriodo;
				}
				tabla.setWidths(medidaCeldas);
				tabla.setWidthPercentage(100);
				
				 PdfPCell c1 = new PdfPCell(new Paragraph("Rubro",norFont));
				    c1.setRowspan(2);
				    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c1);

				    PdfPCell c2 = new PdfPCell(new Paragraph("Descripcion",norFont));
				    c2.setRowspan(2);
				    c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c2);

				    PdfPCell c3 = new PdfPCell(new Paragraph("Unidad",norFont));
				    c3.setRowspan(2);
				    c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c3);
				    
				    PdfPCell c4 = new PdfPCell(new Paragraph("Cantidad",norFont));
				    c4.setRowspan(2);
				    c4.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c4);
				    
				    PdfPCell c5 = new PdfPCell(new Paragraph("Precio",norFont));
				    c5.setRowspan(2);
				    c5.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c5);
				    
				    PdfPCell c6 = new PdfPCell(new Paragraph("Cronograma por Periodo",norFont));
				    c6.setColspan(fuenteFinanciadoraReporte.getCantidadPeriodos());
				    c6.setHorizontalAlignment(Element.ALIGN_CENTER);
				    tabla.addCell(c6);
				    
				    for (int i = 1; i <= fuenteFinanciadoraReporte.getCantidadPeriodos(); i++) {
				    	//Integer cab = 6+i; //i comienza en cero
				    	PdfPCell c = new PdfPCell(new Paragraph("P-"+i,norFont));
					    c.setHorizontalAlignment(Element.ALIGN_CENTER);
					    tabla.addCell(c);
					}
				    
				    for (CostoActividadReporteBean costoActividadReporteBean : actividadReporteBean.getListCostoActividadReporteBean()) {
				    	PdfPCell cInt = new PdfPCell(new Paragraph(costoActividadReporteBean.getPartidaGenericaDescripcion(),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(costoActividadReporteBean.getPartidaEspecificaDescripcion(),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(costoActividadReporteBean.getUnidadMedidaDescripcion(),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(String.valueOf(costoActividadReporteBean.getCantidad()),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(String.valueOf(nf.format(costoActividadReporteBean.getPrecioUnitario())),norFont));//String.valueOf(costoActividadReporteBean.getSimboloTipoMoneda())+"  "+String.valueOf(nf.format(costoActividadReporteBean.getPrecioUnitario())),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						for (CronogramaCostoActividadReporteBean cronogramaCostoActividadReporteBean : costoActividadReporteBean.getListCronogramaCostoActividadReporteBean()) {
							cInt = new PdfPCell(new Paragraph(cronogramaCostoActividadReporteBean.getCantidad(),norFont));
					    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
							tabla.addCell(cInt);
						}
					}				    
				    
				    doc.add(tabla);
				}else{
					Paragraph validacionTabla= new Paragraph();
					Chunk validaTablaDes= new Chunk("NO SE ENCONTRARON COSTOS PROGRAMADOS PARA ESTA ACTIVIDAD",titFont);
					validacionTabla.add(validaTablaDes);
					validacionTabla.setAlignment(Paragraph.ALIGN_CENTER);
					
					agregarLineasEnBlanco(validacionTabla, 1);
					doc.add(validacionTabla);
				}
				
				Paragraph parrafoActDes= new Paragraph();
				Chunk actTituloDes= new Chunk("Descripcion: ",catFont);
				Chunk actDatoDes= new Chunk(actividadReporteBean.getDescripcionActividad(),norFont);
				parrafoActDes.add(actTituloDes);
				parrafoActDes.add(actDatoDes);
				parrafoActDes.setAlignment(Paragraph.ALIGN_LEFT);
				
				agregarLineasEnBlanco(parrafoActDes, 1);
				doc.add(parrafoActDes);
				
			}
			doc.newPage();
		}
	
		doc.close();

		return doc;
	}
	
	
	private List<FuenteFinanciadoraReporte> llenaFuenteFinanciadoraReporte(
			List<Resultado> listResultado, DatoProyecto datoProyecto) {
		
		//busco las listas de fuentes financiadoras
		List<FuenteFinanciadoraReporte> listFuenteFinanciadoraReporte =llenaListFuenteFinanciadoraDeProyecto(datoProyecto);//new ArrayList<FuenteFinanciadoraReporte>();
				
		//relleno las fuentes con sus costos actividad
		for (FuenteFinanciadoraReporte fuenteFinanciadoraReporte : listFuenteFinanciadoraReporte) {			
		
			List<ActividadReporteBean> listActividadReporteBean = new ArrayList<ActividadReporteBean>();
			for (Resultado resultado : listResultado) {
			
			for (Actividad actividad : resultado.getListActividad()) {
				ActividadReporteBean actividadReporteBean= new ActividadReporteBean();	
				actividadReporteBean.setNombreActividad(actividad.getNombreActividad());
				actividadReporteBean.setDescripcionActividad(actividad.getDescripcionActividad());
				actividadReporteBean.setCodigoActividadString(resultado.getCodigoResultado()+"."+actividad.getCodigoActividad());
				
				List<CostoActividadReporteBean> listCostoActividadReporteBean = new ArrayList<CostoActividadReporteBean>();
				for (CostoActividad costoActividad : actividad.getListCostoActividad()) {
					CostoActividadReporteBean costoActividadReporteBean= new CostoActividadReporteBean();
				
					costoActividadReporteBean.setCantidad(costoActividad.getCantidadTotal());
					costoActividadReporteBean.setPartidaGenericaDescripcion(costoActividad.getDetallePartidaGenerica());
					costoActividadReporteBean.setPartidaEspecificaDescripcion(costoActividad.getPartidaEspecifica().getDescripcionPartidaEspecifica());
					costoActividadReporteBean.setPrecioUnitario(costoActividad.getPrecioUnitario());
					costoActividadReporteBean.setUnidadMedidaDescripcion(costoActividad.getDescripcionUnidadMedida());
					costoActividadReporteBean.setTipoMonedaDescripcion(costoActividad.getDescripcionTipoMoneda());
					
					switch (costoActividad.getFkIdtablaespTipoMoneda()) {
					case 168:
						costoActividadReporteBean.setSimboloTipoMoneda("S/.");
						break;

					case 169:
						costoActividadReporteBean.setSimboloTipoMoneda("US$");
						break;

					case 170:
						costoActividadReporteBean.setSimboloTipoMoneda("€");
						break;
}
										
					List<CronogramaCostoActividadReporteBean> listCronogramaCostoActividadReporteBean = new ArrayList<CronogramaCostoActividadReporteBean>();
					
					//if(costoActividad.getListCronogramaCostoActividad().size()>0){
					for (CronogramaCostoActividad cronogramaCostoActividad : costoActividad.getListCronogramaCostoActividad()) {
							CronogramaCostoActividadReporteBean cronogramaCostoActividadReporteBean =  null;
							//cronogramaCostoActividadReporteBean.setPeriodo(cronogramaCostoActividad.getPeriodo());*/
							
							String cantidad="-";
							if(fuenteFinanciadoraReporte.getFuenteFinanciadoraId().equals(cronogramaCostoActividad.getFuenteFinanciadora().getFuenteFinanciadoraID())){
								cronogramaCostoActividadReporteBean =  new CronogramaCostoActividadReporteBean();
								
								cronogramaCostoActividadReporteBean.setPeriodo(cronogramaCostoActividad.getPeriodo());
								
								cantidad= (cronogramaCostoActividad.getCantidad().equals(null)||cronogramaCostoActividad.getCantidad().equals(0) ? "-" : String.valueOf(cronogramaCostoActividad.getCantidad()));
								
								cronogramaCostoActividadReporteBean.setCantidad(cantidad);
								
							}

							if(cronogramaCostoActividadReporteBean!=null){
								listCronogramaCostoActividadReporteBean.add(cronogramaCostoActividadReporteBean);
							}
						}
				
					costoActividadReporteBean.setListCronogramaCostoActividadReporteBean(listCronogramaCostoActividadReporteBean);	
					
					//valido que costo actividad tenga cronograma
					if(costoActividadReporteBean.getListCronogramaCostoActividadReporteBean().size()>0){
						listCostoActividadReporteBean.add(costoActividadReporteBean);					
					}
				
				}
				
				actividadReporteBean.setListCostoActividadReporteBean(listCostoActividadReporteBean);	
				listActividadReporteBean.add(actividadReporteBean);
				}
			}
			
			fuenteFinanciadoraReporte.setListActividadReporteBean(listActividadReporteBean);	
		}
		
		return listFuenteFinanciadoraReporte;
	}

	
	private List<FuenteFinanciadoraReporte> llenaListFuenteFinanciadoraDeProyecto(
			DatoProyecto datoProyecto) {
		
		List<FuenteFinanciadora> listFuenteFinanciadora = fuenteFinanciadoraService.findFuenteFinanciadoraByDatoProyectoId(datoProyecto.getDatoProyectoID());
		Integer cantPeriodos = datoProyecto.getCantidadPeriodo();
		List<FuenteFinanciadoraReporte> listFuenteFinanciadoraReporte =new ArrayList<FuenteFinanciadoraReporte>();
		
		//busco las listas de fuentes financiadoras
		for (FuenteFinanciadora fuenteFinanciadora : listFuenteFinanciadora) {
			FuenteFinanciadoraReporte fuenteFinanciadoraReporte = new FuenteFinanciadoraReporte();
			fuenteFinanciadoraReporte.setCantidadPeriodos(cantPeriodos);
			fuenteFinanciadoraReporte.setFuenteFinanciadoraId(fuenteFinanciadora.getFuenteFinanciadoraID());
			fuenteFinanciadoraReporte.setFuenteFinanciadoraDescripcion(institucionService.findInstitucionById(fuenteFinanciadora.getInstitucion().getInstitucionID()).getNombreInstitucion());
		
			listFuenteFinanciadoraReporte.add(fuenteFinanciadoraReporte);
		}
		
		return listFuenteFinanciadoraReporte;
	}


	private List<RptResultados> llenaRptResultadosReporteEstructuraInversionFinanciamiento(
			List<Resultado> listResultado, DatoProyecto datoProyecto) {
		
		List<RptResultados> listRptResultados= new ArrayList<RptResultados>();
		
		//busco las listas de fuentes financiadoras
		//List<FuenteFinanciadoraReporte> listFuenteFinanciadoraReporte =llenaListFuenteFinanciadoraDeProyecto(datoProyecto);
		
		for (Resultado resultado : listResultado) {
			RptResultados rptResultados= new RptResultados();
			
			rptResultados.setDefinicionResultado(resultado.getDefinicionResultado());
			rptResultados.setCodigoResultado(resultado.getCodigoResultado());
			
			List<ActividadReporteBean> listActividadReporteBean= new ArrayList<ActividadReporteBean>();
			for (Actividad actividad : resultado.getListActividad()) {
				ActividadReporteBean actividadReporteBean= new ActividadReporteBean();
				
				actividadReporteBean.setCodigoActividadString(resultado.getCodigoResultado()+"."+actividad.getCodigoActividad());
				actividadReporteBean.setNombreActividad(actividad.getNombreActividad());
				
				//busco las listas de fuentes financiadoras para cada actividad
				List<FuenteFinanciadoraReporte> listFuenteFinanciadoraReporte =new ArrayList<FuenteFinanciadoraReporte>();
				listFuenteFinanciadoraReporte=llenaListFuenteFinanciadoraDeProyecto(datoProyecto);
				
				for (FuenteFinanciadoraReporte fuenteFinanciadoraReporte : listFuenteFinanciadoraReporte) {
					double montoTotalPorActividad=0;
					for (CostoActividad costoActividad : actividad.getListCostoActividad()) {
						for (CronogramaCostoActividad cronogramaCostoActividad : costoActividad.getListCronogramaCostoActividad()) {
							if(fuenteFinanciadoraReporte.getFuenteFinanciadoraId().equals(cronogramaCostoActividad.getFuenteFinanciadora().getFuenteFinanciadoraID())){
								montoTotalPorActividad += costoActividad.getPrecioUnitario()* cronogramaCostoActividad.getCantidad();
							}
						}
					}
					fuenteFinanciadoraReporte.setMontoTotalPorActividad(montoTotalPorActividad);
				}
				
				actividadReporteBean.setListFuenteFinanciadoraReporte(listFuenteFinanciadoraReporte);
				
				listActividadReporteBean.add(actividadReporteBean);			
			}
			rptResultados.setListActividadReporteBean(listActividadReporteBean);
			
			listRptResultados.add(rptResultados);
		}
		
		return listRptResultados;
	}
	
	
	private Document reporteEstructuraInversionFinanciamiento(
			Document doc,
			String appPath,
			DatoPlanOperativo datoPlanOperativo,
			List<RptResultados> listRptResultadosReporteEstructuraInversionFinanciamiento,
			DatoProyecto datoProyecto) throws MalformedURLException, IOException, DocumentException {
		

		List<FuenteFinanciadoraReporte> listFuenteFinanciadoraReporte = llenaListFuenteFinanciadoraDeProyecto(datoProyecto);
		
		doc.open();
		Paragraph parrafoHoja = new Paragraph();
		
		Image img=Image.getInstance(appPath+"images/fondam.jpg");
		img.setAlignment(Image.ALIGN_LEFT);
		img.scaleAbsolute(120,70);//(newWidth, newHeight)
		parrafoHoja.add(img);
		parrafoHoja.setAlignment(Paragraph.ALIGN_LEFT);
		
		Chunk poEstado= new Chunk("Plan Operativo: ",catFont);
		Chunk poEstadoText= new Chunk( datoPlanOperativo.getDescripcionDetalleEstadoCabEstadoPlanOper(),norFont);
		
		parrafoHoja.add(poEstado);
		parrafoHoja.add(poEstadoText);
		parrafoHoja.setAlignment(Paragraph.ALIGN_RIGHT);
		
		agregarLineasEnBlanco(parrafoHoja, 1);
		doc.add(parrafoHoja);

		Paragraph parrafoTituloCentral = new Paragraph("Estructura de Inversion por Fuente Financiadora",titFont);
		parrafoTituloCentral.setAlignment(Paragraph.ALIGN_CENTER);
		
		agregarLineasEnBlanco(parrafoTituloCentral, 1);
		doc.add(parrafoTituloCentral);
		
		Paragraph parrafoDato= new Paragraph();
		Locale localeMoneda = null;
			
		
		String simboloMoneda = "";
		switch (datoPlanOperativo.getFkIdtablaespTipoMoneda()) {
			case 168:
				simboloMoneda="S/.";
				localeMoneda= new Locale("es");
				break;

			case 169:
				simboloMoneda="US$";
				localeMoneda= new Locale("en","US");
				break;

			case 170:
				simboloMoneda="€";
				localeMoneda= new Locale("fr");
				break;
		}

			Chunk poRecurso= new Chunk("Recursos( "+simboloMoneda+" )",titFont);
			
			parrafoDato.add(poRecurso);
			parrafoDato.setAlignment(Paragraph.ALIGN_LEFT);
			
			
			agregarLineasEnBlanco(parrafoDato, 1);
			doc.add(parrafoDato);
			
				//tabla
				Integer cantCol=3+listFuenteFinanciadoraReporte.size();
				
				PdfPTable tabla= new PdfPTable(cantCol);
				//anchos de celdas de la tabla
				float[] medidaCeldas = new float[cantCol];
				medidaCeldas[0] = 4.50f;
				medidaCeldas[1] = 4.50f;
				
				float medidaPorFuente=1.50f;
				for (int i = 2; i < cantCol; i++) {
					medidaCeldas[i]=medidaPorFuente;
				}
				tabla.setWidths(medidaCeldas);
				tabla.setWidthPercentage(100);
				
				 PdfPCell c1 = new PdfPCell(new Paragraph("Resultados",catFont));
				    //c1.setRowspan(2);
				    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c1);

				    PdfPCell c2 = new PdfPCell(new Paragraph("Actividades",catFont));
				    //c2.setRowspan(2);
				    c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c2);

				    for (FuenteFinanciadoraReporte fuenteFinanciadoraReporte : listFuenteFinanciadoraReporte) {
				    	PdfPCell c3 = new PdfPCell(new Paragraph(fuenteFinanciadoraReporte.getFuenteFinanciadoraDescripcion(),catFont));
					    //c3.setRowspan(2);
					    c3.setHorizontalAlignment(Element.ALIGN_CENTER);
					    c3.setVerticalAlignment(Element.ALIGN_MIDDLE);
					    tabla.addCell(c3);
					}
				    
				    PdfPCell c4 = new PdfPCell(new Paragraph("Total",catFont));
				    //c3.setRowspan(2);
				    c4.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c4);
				    
				    NumberFormat nf = NumberFormat.getCurrencyInstance(localeMoneda);
				    //DecimalFormat df= new DecimalFormat("###,###.00");
				    
				    for (RptResultados rptResultados : listRptResultadosReporteEstructuraInversionFinanciamiento) {
				    	
				    	//valido que resultado tenga actividades
				    	if(rptResultados.getListActividadReporteBean().size()>0){
				    	PdfPCell cInt = new PdfPCell(new Paragraph("Resultado "+rptResultados.getCodigoResultado()+": "+rptResultados.getDefinicionResultado(),norFont));
				    	cInt.setRowspan(rptResultados.getListActividadReporteBean().size());
				    	cInt.setHorizontalAlignment(Element.ALIGN_LEFT);
						tabla.addCell(cInt);
						
						for (ActividadReporteBean actividadReporteBean : rptResultados.getListActividadReporteBean()) {
							PdfPCell cInt2 = new PdfPCell(new Paragraph(actividadReporteBean.getCodigoActividadString() +"  "+actividadReporteBean.getNombreActividad(),norFont));
					    	cInt2.setHorizontalAlignment(Element.ALIGN_LEFT);
							tabla.addCell(cInt2);
							
							//NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
							//DecimalFormat df= new DecimalFormat("###,###.00");
							
							double montoTotalActividad = 0;
							for (FuenteFinanciadoraReporte fuenteFinanciadoraReporte : actividadReporteBean.getListFuenteFinanciadoraReporte()) {
								cInt2 = new PdfPCell(new Paragraph(String.valueOf(fuenteFinanciadoraReporte.getMontoTotalPorActividad()==0?"-":nf.format(fuenteFinanciadoraReporte.getMontoTotalPorActividad())),norFont));
						    	cInt2.setHorizontalAlignment(Element.ALIGN_CENTER);
								tabla.addCell(cInt2);
								
								montoTotalActividad += fuenteFinanciadoraReporte.getMontoTotalPorActividad();
							}
							
							cInt2 = new PdfPCell(new Paragraph(String.valueOf(nf.format(montoTotalActividad)),norFont));
					    	cInt2.setHorizontalAlignment(Element.ALIGN_CENTER);
							tabla.addCell(cInt2);
						}
				    	}
					}
				    
				    doc.add(tabla);
				
				    Integer cantCol2=3+listFuenteFinanciadoraReporte.size();
					PdfPTable tablaTotales= new PdfPTable(cantCol2);
					tablaTotales.setWidths(medidaCeldas);
					tablaTotales.setWidthPercentage(100);
					
					PdfPCell c5 = new PdfPCell(new Paragraph("Total",catFont));
				    c5.setColspan(2);
				    c5.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tablaTotales.addCell(c5);
				    
				    double montoTotalFininanciadoPorProyecto=0;
				    for (RptResultados rptResultados : listRptResultadosReporteEstructuraInversionFinanciamiento) {
						for (ActividadReporteBean actividadReporteBean : rptResultados.getListActividadReporteBean()) {
							for (FuenteFinanciadoraReporte fuenteFinanciadoraReporte : actividadReporteBean.getListFuenteFinanciadoraReporte()) {
								for (FuenteFinanciadoraReporte fuenteFinanciadoraReporteTemp :  listFuenteFinanciadoraReporte) {
									if(fuenteFinanciadoraReporte.getFuenteFinanciadoraId().equals(fuenteFinanciadoraReporteTemp.getFuenteFinanciadoraId())){
										double montoTotalFinanciadoPorFuente=fuenteFinanciadoraReporteTemp.getMontoTotalFinanciadoPorFuente()+fuenteFinanciadoraReporte.getMontoTotalPorActividad();
										montoTotalFininanciadoPorProyecto +=fuenteFinanciadoraReporte.getMontoTotalPorActividad();
										fuenteFinanciadoraReporteTemp.setMontoTotalFinanciadoPorFuente(montoTotalFinanciadoPorFuente);
									}
								}
							}
						}
					}
				    
				    for (FuenteFinanciadoraReporte fuenteFinanciadoraReporteTemp :  listFuenteFinanciadoraReporte) {
				    	PdfPCell c6 = new PdfPCell(new Paragraph(String.valueOf(nf.format(fuenteFinanciadoraReporteTemp.getMontoTotalFinanciadoPorFuente())),catFont));
					    c6.setHorizontalAlignment(Element.ALIGN_CENTER);
					    c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
					    tablaTotales.addCell(c6);
					}
				    
				    PdfPCell c7 = new PdfPCell(new Paragraph(String.valueOf(nf.format(montoTotalFininanciadoPorProyecto)),catFont));
				    c7.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c7.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tablaTotales.addCell(c7);
				    
				    doc.add(tablaTotales);
				    
				/*
				Paragraph parrafoActDes= new Paragraph();
				Chunk actTituloDes= new Chunk("Descripcion: ",catFont);
				Chunk actDatoDes= new Chunk(actividadReporteBean.getDescripcionActividad(),norFont);
				parrafoActDes.add(actTituloDes);
				parrafoActDes.add(actDatoDes);
				parrafoActDes.setAlignment(Paragraph.ALIGN_LEFT);
				
				agregarLineasEnBlanco(parrafoActDes, 1);
				doc.add(parrafoActDes);
				*/
			//}
			//doc.newPage();
		//}
	
		doc.close();

		return doc;
	}

	
	private List<RptResultados> llenaRptResultadosReporteBeneficiario(
			List<Resultado> listResultado, DatoProyecto datoProyecto, List<TablaEspecifica> listEstrato, List<TablaEspecifica> listTipoBeneficiario) {
	
		List<RptResultados> listRptResultados= new ArrayList<RptResultados>();
		
		//busco los beneficiarios sin resultado
		List<BeneficiariosPorResultado> listBeneficiariosPorResultado = beneficiariosPorResultadoService.findBeneficiariosPorResultadoByPerfilID(registroPerfilService.findPerfilByDatoProyectoID(datoProyecto.getDatoProyectoID()).getPerfilID());
		
		if(listBeneficiariosPorResultado.size()>0){
		RptResultados rptResultadosVacio= new RptResultados();
		rptResultadosVacio.setCodigoResultado(0);
		rptResultadosVacio.setDefinicionResultado("Beneficiarios no asignados a algun resultado");
		
		List<BeneficiariosPorResultado> listBeneficiariosPorResultadoTemp =  new ArrayList<BeneficiariosPorResultado>();
		for (TablaEspecifica estrato : listEstrato) {
			for (TablaEspecifica tipoBeneficiario : listTipoBeneficiario) {
				
			BeneficiariosPorResultado beneficiariosPorResultadoTemp =  null;
			for (BeneficiariosPorResultado beneficiariosPorResultado : listBeneficiariosPorResultado) {
				if((beneficiariosPorResultado.getFkidtablaespEstrato().equals(estrato.getTablaEspecificaID()))&&
						(beneficiariosPorResultado.getFkIdtablaespTipoBeneficiario().equals(tipoBeneficiario.getTablaEspecificaID()))){
					beneficiariosPorResultadoTemp= new BeneficiariosPorResultado();
					
					beneficiariosPorResultadoTemp.setCantidadProgramado(beneficiariosPorResultado.getCantidadProgramado());
					beneficiariosPorResultadoTemp.setFkidtablaespEstrato(beneficiariosPorResultado.getFkidtablaespEstrato());
					beneficiariosPorResultadoTemp.setFkIdtablaespTipoBeneficiario(beneficiariosPorResultado.getFkIdtablaespTipoBeneficiario());
					break;
				}					
			}
			if(beneficiariosPorResultadoTemp == null){
				beneficiariosPorResultadoTemp =  new BeneficiariosPorResultado();
				beneficiariosPorResultadoTemp.setCantidadProgramado(0);
				beneficiariosPorResultadoTemp.setFkidtablaespEstrato(estrato.getTablaEspecificaID());
				beneficiariosPorResultadoTemp.setFkIdtablaespTipoBeneficiario(tipoBeneficiario.getTablaEspecificaID());					
			}
			listBeneficiariosPorResultadoTemp.add(beneficiariosPorResultadoTemp);
		}
		}
		
		rptResultadosVacio.setListBeneficiariosPorResultado(listBeneficiariosPorResultadoTemp);
		
		listRptResultados.add(rptResultadosVacio);
		}
		
			for (Resultado resultado : listResultado) {
			RptResultados rptResultados= new RptResultados();
			
			rptResultados.setDefinicionResultado(resultado.getDefinicionResultado());
			rptResultados.setCodigoResultado(resultado.getCodigoResultado());
			
			List<BeneficiariosPorResultado> listBeneficiariosPorResultadoTemp =  new ArrayList<BeneficiariosPorResultado>();
			for (TablaEspecifica estrato : listEstrato) {
				for (TablaEspecifica tipoBeneficiario : listTipoBeneficiario) {
					
				BeneficiariosPorResultado beneficiariosPorResultadoTemp =  null;
				for (BeneficiariosPorResultado beneficiariosPorResultado : resultado.getListBeneficiariosPorResultado()) {
					if((beneficiariosPorResultado.getFkidtablaespEstrato().equals(estrato.getTablaEspecificaID()))&&
							(beneficiariosPorResultado.getFkIdtablaespTipoBeneficiario().equals(tipoBeneficiario.getTablaEspecificaID()))){
						beneficiariosPorResultadoTemp= new BeneficiariosPorResultado();
						
						beneficiariosPorResultadoTemp.setCantidadProgramado(beneficiariosPorResultado.getCantidadProgramado());
						beneficiariosPorResultadoTemp.setFkidtablaespEstrato(beneficiariosPorResultado.getFkidtablaespEstrato());
						beneficiariosPorResultadoTemp.setFkIdtablaespTipoBeneficiario(beneficiariosPorResultado.getFkIdtablaespTipoBeneficiario());
						break;
					}					
				}
				if(beneficiariosPorResultadoTemp == null){
					beneficiariosPorResultadoTemp =  new BeneficiariosPorResultado();
					beneficiariosPorResultadoTemp.setCantidadProgramado(0);
					beneficiariosPorResultadoTemp.setFkidtablaespEstrato(estrato.getTablaEspecificaID());
					beneficiariosPorResultadoTemp.setFkIdtablaespTipoBeneficiario(tipoBeneficiario.getTablaEspecificaID());					
				}
				listBeneficiariosPorResultadoTemp.add(beneficiariosPorResultadoTemp);
			}
			}
			
			rptResultados.setListBeneficiariosPorResultado(listBeneficiariosPorResultadoTemp);//resultado.getListBeneficiariosPorResultado());
		
			listRptResultados.add(rptResultados);
		}
		
		return listRptResultados;
	}
	
	private Document reporteBeneficiario(Document doc, String appPath,
			DatoPlanOperativo datoPlanOperativo, List<RptResultados> listRptResultado,
			DatoProyecto datoProyecto, List<TablaEspecifica> listEstratos,
			List<TablaEspecifica> listTipoBeneficiario) throws DocumentException, MalformedURLException, IOException {
	
		/*
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Beneficiarios "));
		//LEADING = separacion entre lineas del documento
		writer.setInitialLeading(16);
		//Rectangle rct = new Rectangle(36, 54, 559, 788);
		//Definimos un nombre y un tamaño para el PageBox los nombres posibles son: “crop”, “trim”, “art” and “bleed”.
		//writer.setBoxSize(“art”, rct);
		 
		//Se crea una instancia de la clase que pinta la cabecera y el pie de pagina y se le asignan los eventos que ocurran en el <strong>PDF</strong>
		//La en la clase HeaderFooter se capturarar el evento onEndPage para pintarlos
		HeaderFooter event = new HeaderFooter(null, null);
		writer.setPageEvent(event);
		*/
		doc.open();
		Paragraph parrafoHoja = new Paragraph();
		
		Image img=Image.getInstance(appPath+"images/fondam.jpg");
		img.setAlignment(Image.ALIGN_LEFT);
		img.scaleAbsolute(130,70);//(newWidth, newHeight)
		parrafoHoja.add(img);
		parrafoHoja.setAlignment(Paragraph.ALIGN_LEFT);
		
		Chunk poEstado= new Chunk("Plan Operativo: ",catFont);
		Chunk poEstadoText= new Chunk( datoPlanOperativo.getDescripcionDetalleEstadoCabEstadoPlanOper(),norFont);
		
		//Header header= new 	Header("Cabecera", poEstado+" "+ poEstadoText);
		
		parrafoHoja.add(poEstado);
		parrafoHoja.add(poEstadoText);
		parrafoHoja.setAlignment(Paragraph.ALIGN_RIGHT);
		
		agregarLineasEnBlanco(parrafoHoja, 1);
		doc.add(parrafoHoja);

		Paragraph parrafoTituloCentral = new Paragraph("Beneficiario por Resultado",titFont);
		parrafoTituloCentral.setAlignment(Paragraph.ALIGN_CENTER);
		
		agregarLineasEnBlanco(parrafoTituloCentral, 1);
		doc.add(parrafoTituloCentral);
		
		//cantidad de columnas para la tabla
		Integer cantCol=1+listEstratos.size();
		
		for (TablaEspecifica tipoBeneficiario : listTipoBeneficiario) {
			Paragraph parrafoEstrato = new Paragraph(tipoBeneficiario.getDescripcionCabecera(),catFont);
			parrafoEstrato.setAlignment(Paragraph.ALIGN_LEFT);
			agregarLineasEnBlanco(parrafoEstrato, 1);
			doc.add(parrafoEstrato);
			
			//tabla
				PdfPTable tabla= new PdfPTable(cantCol);
				//anchos de celdas de la tabla
				float[] medidaCeldas = new float[cantCol];
				medidaCeldas[0] = 4.50f;
				
				float medidaPorFuente=1.50f;
				for (int i = 1; i < cantCol; i++) {
					medidaCeldas[i]=medidaPorFuente;
				}
				tabla.setWidths(medidaCeldas);
				tabla.setWidthPercentage(100);
				
				 PdfPCell c1 = new PdfPCell(new Paragraph("Resultados",catFont));
				    //c1.setRowspan(2);
				    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c1);

				    for (TablaEspecifica estratoCabecera : listEstratos) {
				    	PdfPCell c3 = new PdfPCell(new Paragraph(estratoCabecera.getDescripcionCabecera(),catFont));
					    //c3.setRowspan(2);
					    c3.setHorizontalAlignment(Element.ALIGN_CENTER);
					    c3.setVerticalAlignment(Element.ALIGN_MIDDLE);
					    tabla.addCell(c3);
					}
				    
				    			    
				    for (RptResultados rptResultados : listRptResultado) {
				    	
				    	//valido cantidad de resultados por tipo para ver filas
				    	Integer cantRows= 1;
				    	for (BeneficiariosPorResultado beneficiariosPorResultado : rptResultados.getListBeneficiariosPorResultado()) {
				    		if (tipoBeneficiario.getTablaEspecificaID().equals(beneficiariosPorResultado.getFkIdtablaespTipoBeneficiario())) {
								cantRows +=1;
							}
						}
				    	
				    	PdfPCell cInt = new PdfPCell(new Paragraph("Resultado "+rptResultados.getCodigoResultado()+": "+rptResultados.getDefinicionResultado(),norFont));
				    	//cInt.setRowspan(cantRows);
				    	cInt.setHorizontalAlignment(Element.ALIGN_LEFT);
				    	cInt.setVerticalAlignment(Element.ALIGN_MIDDLE);
						tabla.addCell(cInt);						
				    	
						 for (TablaEspecifica estratoCabecera : listEstratos) {
							 for (BeneficiariosPorResultado beneficiariosPorResultado : rptResultados.getListBeneficiariosPorResultado()) {
								if((beneficiariosPorResultado.getFkIdtablaespTipoBeneficiario().equals(tipoBeneficiario.getTablaEspecificaID())) &&
										(beneficiariosPorResultado.getFkidtablaespEstrato().equals(estratoCabecera.getTablaEspecificaID()))){
								 PdfPCell c3 = new PdfPCell(new Paragraph(String.valueOf(beneficiariosPorResultado.getCantidadProgramado()),norFont));
							    //c3.setRowspan(2);
							    c3.setHorizontalAlignment(Element.ALIGN_CENTER);
							    //c3.setVerticalAlignment(Element.ALIGN_MIDDLE);
							    tabla.addCell(c3);
								 }
							}
						 }
				    }
				   
				    doc.add(tabla);
				    //espacios en blanco
				    Paragraph parrafo= new Paragraph();
				    parrafo.add(new Paragraph(" "));
				    parrafo.add(new Paragraph(" "));
				    doc.add(parrafo);
	//doc.newPage();			    
	}	    
					
		doc.close();

		return doc;

	}
	
	private Document reporteCostoActividadMontos(Document doc, String appPath,
			DatoPlanOperativo datoPlanOperativo,
			List<FuenteFinanciadoraReporte> listFuenteFinanciadoraReporte) throws DocumentException, MalformedURLException, IOException {
	
		
		Locale localeMoneda = null;
		
		switch (datoPlanOperativo.getFkIdtablaespTipoMoneda()) {
		case 168:
			//simboloMoneda="S/.";
			localeMoneda= new Locale("es");
			break;

		case 169:
			//simboloMoneda="US$";
			localeMoneda= new Locale("en","US");
			break;

		case 170:
			//simboloMoneda="€";
			localeMoneda= new Locale("fr");
			break;
	}
		NumberFormat nf = NumberFormat.getCurrencyInstance(localeMoneda);
		
		doc.open();
		Paragraph parrafoHoja = new Paragraph();
		
		Image img=Image.getInstance(appPath+"images/fondam.jpg");
		img.setAlignment(Image.ALIGN_LEFT);
		img.scaleAbsolute(130,70);//(newWidth, newHeight)
		parrafoHoja.add(img);
		parrafoHoja.setAlignment(Paragraph.ALIGN_LEFT);
		
		Chunk poEstado= new Chunk("Plan Operativo: ",catFont);
		Chunk poEstadoText= new Chunk( datoPlanOperativo.getDescripcionDetalleEstadoCabEstadoPlanOper(),norFont);
		
		parrafoHoja.add(poEstado);
		parrafoHoja.add(poEstadoText);
		parrafoHoja.setAlignment(Paragraph.ALIGN_RIGHT);
		
		agregarLineasEnBlanco(parrafoHoja, 1);
		doc.add(parrafoHoja);

		Paragraph parrafoTituloCentral = new Paragraph("Costo Financiero de Actividad Por Fuente Financiadora",titFont);
		parrafoTituloCentral.setAlignment(Paragraph.ALIGN_CENTER);
		
		agregarLineasEnBlanco(parrafoTituloCentral, 1);
		doc.add(parrafoTituloCentral);
		
		for (FuenteFinanciadoraReporte fuenteFinanciadoraReporte : listFuenteFinanciadoraReporte) {
			Paragraph parrafoDato= new Paragraph();
			
			Chunk poFuenteTitulo= new Chunk("Fuente Financiadora: ",titFont);
			Chunk poFuente= new Chunk(fuenteFinanciadoraReporte.getFuenteFinanciadoraDescripcion(),norFont);
			parrafoDato.add(poFuenteTitulo);
			parrafoDato.add(poFuente);
			parrafoDato.setAlignment(Paragraph.ALIGN_LEFT);
			
			
			//agregarLineasEnBlanco(parrafoDato, 1);
			doc.add(parrafoDato);
			
			for (ActividadReporteBean actividadReporteBean : fuenteFinanciadoraReporte.getListActividadReporteBean()) {
				Paragraph parrafoAct= new Paragraph();
				
				Chunk actTitulo= new Chunk(" * Costos desagregados de la Actividad: ",catFont);
				Chunk actDato= new Chunk(actividadReporteBean.getCodigoActividadString()+" " +actividadReporteBean.getNombreActividad(),norFont);
				parrafoAct.add(actTitulo);
				parrafoAct.add(actDato);
				parrafoAct.setAlignment(Paragraph.ALIGN_LEFT);
				
				
				agregarLineasEnBlanco(parrafoAct, 1);
				doc.add(parrafoAct);
				
				if(actividadReporteBean.getListCostoActividadReporteBean().size()>0){
				//tabla
				Integer cantCol=5+fuenteFinanciadoraReporte.getCantidadPeriodos();
				PdfPTable tabla= new PdfPTable(cantCol);
				
				//anchos de celdas de la tabla
				float[] medidaCeldas = new float[cantCol];
				medidaCeldas[0] = 2.50f;
				medidaCeldas[1] = 3.00f;
				medidaCeldas[2] = 1.50f;
				medidaCeldas[3] = 1.50f;
				medidaCeldas[4] = 1.50f;
				
				float medidaPeriodo=1.00f;
				for (int i = 5; i < cantCol; i++) {
					medidaCeldas[i]=medidaPeriodo;
				}
				tabla.setWidths(medidaCeldas);
				tabla.setWidthPercentage(100);
				
				 PdfPCell c1 = new PdfPCell(new Paragraph("Rubro",norFont));
				    c1.setRowspan(2);
				    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c1);

				    PdfPCell c2 = new PdfPCell(new Paragraph("Descripcion",norFont));
				    c2.setRowspan(2);
				    c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c2);

				    PdfPCell c3 = new PdfPCell(new Paragraph("Unidad",norFont));
				    c3.setRowspan(2);
				    c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c3);
				    
				    PdfPCell c4 = new PdfPCell(new Paragraph("Cantidad",norFont));
				    c4.setRowspan(2);
				    c4.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c4);
				    
				    PdfPCell c5 = new PdfPCell(new Paragraph("Precio",norFont));
				    c5.setRowspan(2);
				    c5.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c5);
				    
				    PdfPCell c6 = new PdfPCell(new Paragraph("Cronograma por Periodo",norFont));
				    c6.setColspan(fuenteFinanciadoraReporte.getCantidadPeriodos());
				    c6.setHorizontalAlignment(Element.ALIGN_CENTER);
				    tabla.addCell(c6);
				    
				    for (int i = 1; i <= fuenteFinanciadoraReporte.getCantidadPeriodos(); i++) {
				    	//Integer cab = 6+i; //i comienza en cero
				    	PdfPCell c = new PdfPCell(new Paragraph("P-"+i,norFont));
					    c.setHorizontalAlignment(Element.ALIGN_CENTER);
					    tabla.addCell(c);
					}
				    
				    for (CostoActividadReporteBean costoActividadReporteBean : actividadReporteBean.getListCostoActividadReporteBean()) {
				    	PdfPCell cInt = new PdfPCell(new Paragraph(costoActividadReporteBean.getPartidaGenericaDescripcion(),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(costoActividadReporteBean.getPartidaEspecificaDescripcion(),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(costoActividadReporteBean.getUnidadMedidaDescripcion(),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(String.valueOf(costoActividadReporteBean.getCantidad()),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(String.valueOf(nf.format(costoActividadReporteBean.getPrecioUnitario())),norFont));//String.valueOf(costoActividadReporteBean.getSimboloTipoMoneda())+"  "+String.valueOf(nf.format(costoActividadReporteBean.getPrecioUnitario())),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						for (CronogramaCostoActividadReporteBean cronogramaCostoActividadReporteBean : costoActividadReporteBean.getListCronogramaCostoActividadReporteBean()) {
							cInt = new PdfPCell(new Paragraph(String.valueOf(cronogramaCostoActividadReporteBean.getCantidad()=="-"?cronogramaCostoActividadReporteBean.getCantidad(): nf.format(Double.valueOf(cronogramaCostoActividadReporteBean.getCantidad())*costoActividadReporteBean.getPrecioUnitario())),norFont));
					    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
							tabla.addCell(cInt);
						}
					}				    
				    
				    doc.add(tabla);
				  //espacios en blanco
				    Paragraph parrafo= new Paragraph();
				    parrafo.add(new Paragraph(" "));
				    parrafo.add(new Paragraph(" "));
				    doc.add(parrafo);
				    
				}else{
					Paragraph validacionTabla= new Paragraph();
					Chunk validaTablaDes= new Chunk("NO SE ENCONTRARON COSTOS PROGRAMADOS PARA ESTA ACTIVIDAD",titFont);
					validacionTabla.add(validaTablaDes);
					validacionTabla.setAlignment(Paragraph.ALIGN_CENTER);
					
					agregarLineasEnBlanco(validacionTabla, 1);
					doc.add(validacionTabla);
				}
				
				/*Paragraph parrafoActDes= new Paragraph();
				Chunk actTituloDes= new Chunk("Descripcion: ",catFont);
				Chunk actDatoDes= new Chunk(actividadReporteBean.getDescripcionActividad(),norFont);
				parrafoActDes.add(actTituloDes);
				parrafoActDes.add(actDatoDes);
				parrafoActDes.setAlignment(Paragraph.ALIGN_LEFT);
				
				agregarLineasEnBlanco(parrafoActDes, 1);
				doc.add(parrafoActDes);*/
				
			}
			doc.newPage();
		}
	
		doc.close();

		return doc;
	}

	@SuppressWarnings("unchecked")
	private List<RptResultados> llenaRptResultadosReporteCronogramaResultadoMeta(
			List<Resultado> listResultado, DatoProyecto datoProyecto) {
		
		List<RptResultados> listRptResultados= new ArrayList<RptResultados>();
		
		for (Resultado resultado : listResultado) {
			RptResultados rptResultados= new RptResultados();
			
			rptResultados.setDefinicionResultado(resultado.getDefinicionResultado());
			rptResultados.setCodigoResultado(resultado.getCodigoResultado());
			rptResultados.setMetaResultado(resultado.getMetaResultado());
			rptResultados.setDescripcionUnidadMedida(resultado.getDescripcionUnidadMedida());
			rptResultados.setDuracionMeses(resultado.getDuracionMeses());
			
			List<CronogramaMetaPorResultado> listCronogramaMetaPorResultado = new ArrayList<CronogramaMetaPorResultado>();
			for (int i = 1; i <= datoProyecto.getCantidadPeriodo(); i++) {
				CronogramaMetaPorResultado cronogramaMetaPorResultadoTemp =  null;
				for (CronogramaMetaPorResultado cronogramaMetaPorResultado : resultado.getListCronogramaMetaPorResultado()) {
					if(cronogramaMetaPorResultado.getPeriodo().equals(String.valueOf(i))){
						cronogramaMetaPorResultadoTemp= new CronogramaMetaPorResultado();
						
						cronogramaMetaPorResultadoTemp.setAvanceMeta(cronogramaMetaPorResultado.getAvanceMeta());
						cronogramaMetaPorResultadoTemp.setPeriodo(cronogramaMetaPorResultado.getPeriodo());
						break;
					}
				}
				
				if(cronogramaMetaPorResultadoTemp == null){
					cronogramaMetaPorResultadoTemp= new CronogramaMetaPorResultado();
					
					cronogramaMetaPorResultadoTemp.setAvanceMeta(0);
					cronogramaMetaPorResultadoTemp.setPeriodo(String.valueOf(i));					
				}
				
				listCronogramaMetaPorResultado.add(cronogramaMetaPorResultadoTemp);
			}
			rptResultados.setListCronogramaMetaPorResultado((List<CronogramaMetaPorResultado>) UtilList.orderAscList(listCronogramaMetaPorResultado,"periodo"));
			
			listRptResultados.add(rptResultados);
		}
		
		return listRptResultados;
	}
	
	private Document reporteCronogramaResultado(Document doc, String appPath,
			DatoPlanOperativo datoPlanOperativo,
			List<RptResultados> listRptResultadosReporteCronogramaResultado,
			DatoProyecto datoProyecto) throws DocumentException, MalformedURLException, IOException {
		
	//Locale localeMoneda = null;
		/*
		switch (datoPlanOperativo.getFkIdtablaespTipoMoneda()) {
		case 168:
			//simboloMoneda="S/.";
			localeMoneda= new Locale("es");
			break;

		case 169:
			//simboloMoneda="US$";
			localeMoneda= new Locale("en","US");
			break;

		case 170:
			//simboloMoneda="€";
			localeMoneda= new Locale("fr");
			break;
	}
		NumberFormat nf = NumberFormat.getCurrencyInstance(localeMoneda);
		*/
		doc.open();
		Paragraph parrafoHoja = new Paragraph();
		
		Image img=Image.getInstance(appPath+"images/fondam.jpg");
		img.setAlignment(Image.ALIGN_LEFT);
		img.scaleAbsolute(130,70);//(newWidth, newHeight)
		parrafoHoja.add(img);
		parrafoHoja.setAlignment(Paragraph.ALIGN_LEFT);
		
		Chunk poEstado= new Chunk("Plan Operativo: ",catFont);
		Chunk poEstadoText= new Chunk( datoPlanOperativo.getDescripcionDetalleEstadoCabEstadoPlanOper(),norFont);
		
		parrafoHoja.add(poEstado);
		parrafoHoja.add(poEstadoText);
		parrafoHoja.setAlignment(Paragraph.ALIGN_RIGHT);
		
		agregarLineasEnBlanco(parrafoHoja, 1);
		doc.add(parrafoHoja);

		Paragraph parrafoTituloCentral = new Paragraph("Operaciones y Costos",titFont);
		parrafoTituloCentral.setAlignment(Paragraph.ALIGN_CENTER);
		
		agregarLineasEnBlanco(parrafoTituloCentral, 1);
		doc.add(parrafoTituloCentral);
		
		//for (FuenteFinanciadoraReporte fuenteFinanciadoraReporte : listFuenteFinanciadoraReporte) {
			Paragraph parrafoDato= new Paragraph();
			
			Chunk poFuenteTitulo= new Chunk("Cronograma de Resultados: ",titFont);
			//Chunk poFuente= new Chunk(fuenteFinanciadoraReporte.getFuenteFinanciadoraDescripcion(),norFont);
			parrafoDato.add(poFuenteTitulo);
			//parrafoDato.add(poFuente);
			parrafoDato.setAlignment(Paragraph.ALIGN_LEFT);
			
			
			agregarLineasEnBlanco(parrafoDato, 1);
			doc.add(parrafoDato);
			
			/*
			for (ActividadReporteBean actividadReporteBean : fuenteFinanciadoraReporte.getListActividadReporteBean()) {
				Paragraph parrafoAct= new Paragraph();
				
				Chunk actTitulo= new Chunk(" * Costos desagregados de la Actividad: ",catFont);
				Chunk actDato= new Chunk(actividadReporteBean.getCodigoActividadString()+" " +actividadReporteBean.getNombreActividad(),norFont);
				parrafoAct.add(actTitulo);
				parrafoAct.add(actDato);
				parrafoAct.setAlignment(Paragraph.ALIGN_LEFT);
				
				
				agregarLineasEnBlanco(parrafoAct, 1);
				doc.add(parrafoAct);
				
				if(actividadReporteBean.getListCostoActividadReporteBean().size()>0){*/
				//tabla
				Integer cantCol=4+datoProyecto.getCantidadPeriodo();
				PdfPTable tabla= new PdfPTable(cantCol);
				
				//anchos de celdas de la tabla
				float[] medidaCeldas = new float[cantCol];
				medidaCeldas[0] = 5.00f;
				medidaCeldas[1] = 1.50f;
				medidaCeldas[2] = 2.00f;
				medidaCeldas[3] = 1.50f;
								
				float medidaPeriodo=1.00f;
				for (int i = 4; i < cantCol; i++) {
					medidaCeldas[i]=medidaPeriodo;
				}
				tabla.setWidths(medidaCeldas);
				tabla.setWidthPercentage(100);
				
				 PdfPCell c1 = new PdfPCell(new Paragraph("Resultado",norFont));
				    c1.setRowspan(2);
				    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c1);

				    PdfPCell c2 = new PdfPCell(new Paragraph("Meta",norFont));
				    c2.setRowspan(2);
				    c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c2);

				    PdfPCell c3 = new PdfPCell(new Paragraph("Unidad",norFont));
				    c3.setRowspan(2);
				    c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c3);
				    
				    PdfPCell c4 = new PdfPCell(new Paragraph("Tiempo Ejecucion (meses)",norFont));
				    c4.setRowspan(2);
				    c4.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c4);
				    /*
				    PdfPCell c5 = new PdfPCell(new Paragraph("Precio",norFont));
				    c5.setRowspan(2);
				    c5.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c5);
				    */
				    PdfPCell c6 = new PdfPCell(new Paragraph("Avances por Periodo(Unidades Fisicas)",norFont));
				    c6.setColspan(datoProyecto.getCantidadPeriodo());
				    c6.setHorizontalAlignment(Element.ALIGN_CENTER);
				    tabla.addCell(c6);
				    
				    for (int i = 1; i <= datoProyecto.getCantidadPeriodo(); i++) {
				    	//Integer cab = 6+i; //i comienza en cero
				    	PdfPCell c = new PdfPCell(new Paragraph(String.valueOf(i),norFont));
					    c.setHorizontalAlignment(Element.ALIGN_CENTER);
					    tabla.addCell(c);
					}
				    
				    for (RptResultados rptResultados : listRptResultadosReporteCronogramaResultado) {
				    	PdfPCell cInt = new PdfPCell(new Paragraph(rptResultados.getCodigoResultado()+" "+rptResultados.getDefinicionResultado(),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_LEFT);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(String.valueOf(rptResultados.getMetaResultado()),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(rptResultados.getDescripcionUnidadMedida(),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(String.valueOf(rptResultados.getDuracionMeses()),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						/*
						cInt = new PdfPCell(new Paragraph(String.valueOf(nf.format(costoActividadReporteBean.getPrecioUnitario())),norFont));//String.valueOf(costoActividadReporteBean.getSimboloTipoMoneda())+"  "+String.valueOf(nf.format(costoActividadReporteBean.getPrecioUnitario())),norFont));
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);*/
						
						for (CronogramaMetaPorResultado cronogramaMetaPorResultado : rptResultados.getListCronogramaMetaPorResultado()) {
							cInt = new PdfPCell(new Paragraph(String.valueOf(cronogramaMetaPorResultado.getAvanceMeta()!=0?cronogramaMetaPorResultado.getAvanceMeta():"-"),norFont));
					    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
							tabla.addCell(cInt);
						}
					}				    
				    
				    doc.add(tabla);
				/*}else{
					Paragraph validacionTabla= new Paragraph();
					Chunk validaTablaDes= new Chunk("NO SE ENCONTRARON COSTOS PROGRAMADOS PARA ESTA ACTIVIDAD",titFont);
					validacionTabla.add(validaTablaDes);
					validacionTabla.setAlignment(Paragraph.ALIGN_CENTER);
					
					agregarLineasEnBlanco(validacionTabla, 1);
					doc.add(validacionTabla);
				}
				
				Paragraph parrafoActDes= new Paragraph();
				Chunk actTituloDes= new Chunk("Descripcion: ",catFont);
				Chunk actDatoDes= new Chunk(actividadReporteBean.getDescripcionActividad(),norFont);
				parrafoActDes.add(actTituloDes);
				parrafoActDes.add(actDatoDes);
				parrafoActDes.setAlignment(Paragraph.ALIGN_LEFT);
				
				agregarLineasEnBlanco(parrafoActDes, 1);
				doc.add(parrafoActDes);
				
			}*/
			//doc.newPage();
		//}
	
		doc.close();

		return doc;
	}
}
