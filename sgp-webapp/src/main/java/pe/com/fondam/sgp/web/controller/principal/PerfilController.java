
package pe.com.fondam.sgp.web.controller.principal;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.bean.ImagenOArchivoTempBean;
import pe.com.fondam.sgp.core.bean.MontosAcumulados;
import pe.com.fondam.sgp.core.bean.ProgramaBean;
import pe.com.fondam.sgp.core.bean.RestriccionDepProvDistBean;
import pe.com.fondam.sgp.core.bean.RestriccionSubAreaTematicaBean;
import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.DepProvDist;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;
import pe.com.fondam.sgp.core.domain.Institucion;
import pe.com.fondam.sgp.core.domain.Perfil;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.RestriccionDepProvDist;
import pe.com.fondam.sgp.core.domain.RestriccionSubAreaTematica;
import pe.com.fondam.sgp.core.domain.ResumenProyecto;
import pe.com.fondam.sgp.core.domain.SubAreaTematica;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.TmpBeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.TmpDatoProyecto;
import pe.com.fondam.sgp.core.domain.TmpFuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.TmpImagenOArchivo;
import pe.com.fondam.sgp.core.domain.TmpInstitucion;
import pe.com.fondam.sgp.core.domain.TmpPerfil;
import pe.com.fondam.sgp.core.domain.TmpResumenProyecto;
import pe.com.fondam.sgp.core.domain.TmpUbicacionProyecto;
import pe.com.fondam.sgp.core.domain.Usuario;
import pe.com.fondam.sgp.core.service.CrearProgramaService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.EvaluarService;
import pe.com.fondam.sgp.core.service.IngresarPerfilService;
import pe.com.fondam.sgp.core.service.RegistroPerfilService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.TablaTemporalService;
import pe.com.fondam.sgp.core.util.CommonUtilities;
import pe.com.fondam.sgp.web.InOutFiles.FileUploadDownload;
import pe.com.fondam.sgp.web.InOutFiles.FileUploadListener;
import pe.com.fondam.sgp.web.InOutFiles.LinkFile;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.controller.security.SecurityController;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class PerfilController {

	// **************** inyecciones ************************//
	@Resource
	private RegistroPerfilService registroPerfilService;

	@Resource
	private TablaEspecificaService tablaEspecificaService;

	@Resource
	private DetalleEstadoCabeceraService detalleEstadoCabeceraService;

	@Resource
	private IngresarPerfilService ingresarPerfilService;

	@Resource
	private TablaTemporalService tablaTemporalService;

	@Resource
	private EvaluarService evaluarService;

	@Resource
	private CrearProgramaService crearProgramaService;

	@Resource
	SecurityController securityController;

	public void setRegistroPerfilService(
			RegistroPerfilService registroPerfilService) {
		this.registroPerfilService = registroPerfilService;
	}

	public RegistroPerfilService getRegistroPerfilService() {
		return registroPerfilService;
	}

	public void setTablaEspecificaService(
			TablaEspecificaService tablaEspecificaService) {
		this.tablaEspecificaService = tablaEspecificaService;
	}

	public TablaEspecificaService getTablaEspecificaService() {
		return tablaEspecificaService;
	}

	public DetalleEstadoCabeceraService getDetalleEstadoCabeceraService() {
		return detalleEstadoCabeceraService;
	}

	public void setDetalleEstadoCabeceraService(
			DetalleEstadoCabeceraService detalleEstadoCabeceraService) {
		this.detalleEstadoCabeceraService = detalleEstadoCabeceraService;
	}

	FileUploadDownload fileUploadDownload = new FileUploadDownload();

	// listas
	List<DepProvDist> listDepartamentos;
	List<DepProvDist> listProvincias;
	List<DepProvDist> listDistritos;
	List<TablaEspecifica> listEstratos;
	List<TablaEspecifica> listModalidadFinanciera;
	List<TablaEspecifica> listAreasTematicas;
	List<TablaEspecifica> listUnidadesMedida;
	List<Programa> listProgramas;
	List<ProgramaBean> listProgramasFinal;
	List<TablaEspecifica> listTipoResumenProyecto;
	List<SubAreaTematica> listSubAreaTematica;
	List<TablaEspecifica> listFuenteFinanciadora;
	List<TablaEspecifica> tablaEspecificaListTipoFormatoArchivo;
	List<TablaEspecifica> listTipoMoneda;

	/*********** DATOS DEL FORMULARIO *************/
	// --DATOS DE PROYECTO--
	String txtProyecto;
	String cbxModalidadFinan;
	int cbxPrograma;
	String cbxAreaTematica;
	int cbxSubAreaTematica;
	String cbxSubNivel;
	int txtDuracion;
	// ---
	Date fechaActual = new Date();
	SimpleDateFormat mes = new SimpleDateFormat("MM");
	String txtMes = mes.format(fechaActual);
	SimpleDateFormat anio = new SimpleDateFormat("yy");
	String txtAnio = anio.format(fechaActual);
	// --UBICACION DEL PROYECTO--
	String txtListadoUbicacionBeneficiarios;
	// --DATOS DE INSTITUCION--
	String txtInstitucionFuenFinan;
	// --DATOS PERFIL--
	String txtFondo;
	String txtContrapartida;
	String txtCofinanciador;
	String txtDuracionPerfil;
	// --IMAGEN O ARCHIVO--
	// String nombreArchivo;
	// String extension;//buscar en la tabla especifica
	byte[] imagen;
	static String nombreArchivoUP = "nulo";

	String txtListadoResumenProyecto;

	// **************** metodos ************************//
	public PerfilController() {
		}
	
	protected final Log logger = LogFactory.getLog(PerfilController.class);

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
	@RequestMapping(value = "/principal/showCrearPerfil.jspx")
	public ModelAndView showCrearPerfil(HttpServletRequest request,
			HttpServletResponse resp) throws IOException {
		securityController.removeSessionAttribute(request);
		Map<String, Object> model = new HashMap<String, Object>();

		request.getSession().removeAttribute("listResumenProyecto");
		
		model.put("listMoneda", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("TMND"));
		model.put("listFuenteFinanciadora", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("TFFN"));
		model.put("listTipoResumenPerfil", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("TRPF"));
		model.put("listEstrato", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("ESTT"));
		model.put("listTipoBeneficiario", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("TPBF"));
		model.put("listModalidadFinanciamiento", tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_MODALIDAD_FINANCIAMIENTO));
		
		ImagenOArchivoTempBean imagenOArchivoTempBean= new ImagenOArchivoTempBean();
		model.put("imagenOArchivoTempBean",imagenOArchivoTempBean);
		
		return new ModelAndView("mostrarCrearPerfil", model);

	}

	@RequestMapping(value = "/principal/cargaCombosDepProvDist")
	public void cargaCombosDepProvDist(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// restriccion con id
		// todos depProvDist
		// creas bean
		// if y sacas dep, prov , dist

		listDepartamentos = registroPerfilService.findDepProvDistritos(1, "0",
				"0", "0");
		listProvincias = registroPerfilService.findDepProvDistritos(2, "0",
				"0", "0");
		listDistritos = registroPerfilService.findDepProvDistritos(3, "0", "0",
				"0");
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, listDepartamentos);
		jsonArray.add(1, listProvincias);
		jsonArray.add(2, listDistritos);
		response.getWriter().write(jsonArray.toString());
	}

	@RequestMapping(value = "/principal/cargaCombosEstratoSocial")
	public void cargaCombosEstratoSocial(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		listEstratos = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(8);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, listEstratos);
		response.getWriter().write(jsonArray.toString());
	}

	@RequestMapping(value = "/principal/cargaComboModFinanProg")
	public void cargaComboModalidadFinan(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		listModalidadFinanciera = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(2);
		listadoProgramasFinal();
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, listModalidadFinanciera);
		jsonArray.add(1, listProgramasFinal);
		logger.info("modalidad finan y programa :::" + jsonArray.toString());
		response.getWriter().write(jsonArray.toString());
	}

	@RequestMapping(value = "/principal/cargaComboTipoResumenProyecto")
	public void cargaComboTipoResumenProyecto(
			@RequestParam(required = true, value = "tablaGeneralId") Integer tablaGeneralId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		listTipoResumenProyecto = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(tablaGeneralId);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, listTipoResumenProyecto);
		response.getWriter().write(jsonArray.toString());
	}

	@RequestMapping(value = "/principal/cargaCombosAreasTematicas")
	public void cargaCombosAreasTematicas(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		listAreasTematicas = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(4);
		listSubAreaTematica = ingresarPerfilService.listSubAreaTematica();
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, listAreasTematicas);
		jsonArray.add(1, listSubAreaTematica);
		response.getWriter().write(jsonArray.toString());
	}

	@RequestMapping(value = "/principal/cargarRestriccionSubAreaTematica")
	public void cargarRestriccionSubAreaTematica(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String idPrograma = request.getParameter("hiddenIdPrograma");
		List<RestriccionSubAreaTematica> listRestriccionSubAreaTematica = crearProgramaService
				.findListRestriccionSubAreaTematicayProgramaId(Integer
						.parseInt(idPrograma));
		List<RestriccionSubAreaTematicaBean> listRestriccionSubAreaTematicaBean = new ArrayList<RestriccionSubAreaTematicaBean>();
		RestriccionSubAreaTematicaBean restriccionSubAreaTematicaBean;
		for (int i = 0; i < listRestriccionSubAreaTematica.size(); i++) {
			restriccionSubAreaTematicaBean = new RestriccionSubAreaTematicaBean();
			restriccionSubAreaTematicaBean
					.setRestriccionsubareatematicaID(listRestriccionSubAreaTematica
							.get(i).getRestriccionsubareatematicaID());
			restriccionSubAreaTematicaBean
					.setPrograma(listRestriccionSubAreaTematica.get(i)
							.getPrograma().getProgramaID());
			restriccionSubAreaTematicaBean
					.setSubAreaTematica(listRestriccionSubAreaTematica.get(i)
							.getSubAreaTematica().getSubAreaTematicaID());
			listRestriccionSubAreaTematicaBean
					.add(restriccionSubAreaTematicaBean);
		}

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, listRestriccionSubAreaTematicaBean);
		response.getWriter().write(jsonArray.toString());

	}

	@RequestMapping(value = "/principal/cargarRestriccionDepProvDist")
	public void cargarRestriccionDepProvDist(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String idPrograma = request.getParameter("hiddenIdPrograma");
		List<RestriccionDepProvDist> listRestriccionDepProvDist = crearProgramaService
				.findListRestriccionDepProvDistByProgramaId(Integer
						.parseInt(idPrograma));
		List<RestriccionDepProvDistBean> listRestriccionDepProvDistBean = new ArrayList<RestriccionDepProvDistBean>();
		RestriccionDepProvDistBean restriccionDepProvDistBean;

		for (int i = 0; i < listRestriccionDepProvDist.size(); i++) {
			restriccionDepProvDistBean = new RestriccionDepProvDistBean();
			restriccionDepProvDistBean
					.setRestriccionDepProvdistID(listRestriccionDepProvDist
							.get(i).getRestricciondepprovdistID());
			restriccionDepProvDistBean.setPrograma(listRestriccionDepProvDist
					.get(i).getPrograma().getProgramaID());
			restriccionDepProvDistBean
					.setDepProvDist(listRestriccionDepProvDist.get(i)
							.getDepProvDist().getDepProvDistID());
			listRestriccionDepProvDistBean.add(restriccionDepProvDistBean);
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(listRestriccionDepProvDistBean);
		response.getWriter().write(jsonArray.toString());

	}

	@RequestMapping(value = "/principal/restringirEntidadEjecutoraPorPrograma", method = RequestMethod.GET)
	public void restringirEntidadEjecutoraPorPrograma(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String nombreProy = request.getParameter("par1");
		String codProg = request.getParameter("par2");
		// logger.info("nombreProy : "+nombreProy);
		// logger.info("codProg : "+codProg);
		List<TmpDatoProyecto> listTMPDatoProy;
		JSONObject objJson = new JSONObject();
		listTMPDatoProy = ingresarPerfilService
				.findTmpDatoProyectoByNomProyectoAndProgramaID(nombreProy,
						Integer.parseInt(codProg));
		boolean existe = false;
		if (listTMPDatoProy.size() > 0) {
			existe = true;
		}

		if (existe) {
			objJson.accumulate("existe", "si");
		} else {

			objJson.accumulate("existe", "no");
		}
		// logger.info("objJson.toString()----------------------> : "+objJson.toString());

		response.getWriter().write(objJson.toString());

	}

	@RequestMapping(value = "/principal/cargaComboUnidadMedida")
	public void cargaComboUnidadMedida(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		listUnidadesMedida = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(5);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, listUnidadesMedida);
		response.getWriter().write(jsonArray.toString());
	}

	@RequestMapping(value = "/principal/obtenerDatosFondam")
	public void obtenerDtosFondam(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONObject objJsonIns = new JSONObject();
		// String ruc = request.getParameter("ruc");
		Institucion ins = evaluarService
				.findInstitucionByRUC(FondamConstans.RUCFONDAM);

		if (ins != null) {

			objJsonIns.accumulate("txtInstitucion", ins.getNombreInstitucion());
			objJsonIns.accumulate("txtRuc", ins.getRucInstitucion());
			objJsonIns.accumulate("txtDireccion", ins.getDireccion());
			objJsonIns.accumulate("txtTelefono", ins.getTelefono());
			objJsonIns.accumulate("txtRepLegal", ins.getRepresentanteLegal());
			objJsonIns.accumulate("txtContacto", ins.getContacto());
			objJsonIns.accumulate("txtCorreo", ins.getCorreo());
			objJsonIns.accumulate("txaObservacion",
					ins.getObservacionDeInstitucion());
			objJsonIns.accumulate("estado", "si");
			response.getWriter().write(objJsonIns.toString());

		} else {
			objJsonIns.accumulate("estado", "no");
			response.getWriter().write(objJsonIns.toString());
		}

	}

	@RequestMapping(value = "/principal/cargaComboTipoFuenteFinanciadora.jspx")
	public void cargaComboTipoFuenteFinanciadora(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<TablaEspecifica> listFuenteFinanciadoraFinal = new ArrayList<TablaEspecifica>();
		TablaEspecifica tablaEspecifica;
		listFuenteFinanciadora = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(30);
		for (int i = 0; i < listFuenteFinanciadora.size(); i++) {
			if (CommonUtilities.toInt(listFuenteFinanciadora.get(i)
					.getTablaEspecificaID()) != FondamConstans.PK_FUENTE_FINANCIADORA_FONDAM) {
				tablaEspecifica = new TablaEspecifica();
				tablaEspecifica = listFuenteFinanciadora.get(i);
				listFuenteFinanciadoraFinal.add(tablaEspecifica);
			}
		}

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, listFuenteFinanciadoraFinal);
		logger.info("-----------cargaComboTipoFuenteFinanciadora.jspx "
				+ jsonArray.toString());
		response.getWriter().write(jsonArray.toString());
	}

	@RequestMapping(value = "/principal/cargaComboTipoMoneda.jspx")
	public void cargaComboTipoMoneda(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		listTipoMoneda = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(25);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, listTipoMoneda);
		logger.info("-----------cargaComboTipoMoneda.jspx "
				+ jsonArray.toString());
		response.getWriter().write(jsonArray.toString());
	}

	// VALIDAR UPLOAD
	@RequestMapping(value = "/principal/validarUpload.jspx")
	public void validarUpload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// nombreArchivoUP
		JSONObject nomFile = new JSONObject();
		nomFile.accumulate("fileName", nombreArchivoUP);
		response.getWriter().write(nomFile.toString());
	}

	/* upload******************************************* */
	@RequestMapping(value = "/principal/showArchivoUploadPerfil.jspx")
	public ModelAndView showArchivoUpload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		nombreArchivoUP = "nulo";
		String peticion = "archivoUploadPerfil.jspx";
		String archivoResp = "false";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("peticion", peticion);
		model.put("archivoResp", archivoResp);
		return new ModelAndView("mostrarArchivoUpload", model);
	}

	@RequestMapping(value = "/principal/archivoUploadPerfil.jspx", method = RequestMethod.POST)
	public ModelAndView archivoUpload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// fileUploadDownload=new FileUploadDownload();

		fileUploadDownload.archivoUpload(request, response);
		String archivoResp = "true";
		nombreArchivoUP = fileUploadDownload.getNombreArchivo()
				+ fileUploadDownload.getExtension();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("archivoResp", archivoResp);
		model.put("nombreArchivoUP", nombreArchivoUP);
		return new ModelAndView("mostrarArchivoUpload", model);
	}

	@RequestMapping(value = "/principal/ajaxUpload.jspx", method = RequestMethod.GET)
	public void porcentajeUpload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		FileUploadListener listener = null;
		StringBuffer buffy = new StringBuffer();
		long bytesRead = 0, contentLength = 0;

		System.out.println("entro metodo GET!!!!!!!!!!!");

		// Make sure the session has started
		if (session == null) {
			return;
		} else if (session != null) {
			// Check to see if we've created the listener object yet
			listener = (FileUploadListener) session.getAttribute("LISTENER");

			if (listener == null) {
				return;
			} else {
				// Get the meta information
				bytesRead = listener.getBytesRead();
				contentLength = listener.getContentLength();
			}
		}

		/*
		 * XML Response Code
		 */
		response.setContentType("text/xml");

		buffy.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
		buffy.append("<response>\n");
		buffy.append("\t<bytes_read>" + bytesRead + "</bytes_read>\n");
		buffy.append("\t<content_length>" + contentLength
				+ "</content_length>\n");

		// Check to see if we're done
		if (bytesRead == contentLength) {
			buffy.append("\t<finished />\n");

			// No reason to keep listener in session since we're done
			session.setAttribute("LISTENER", null);
		} else {
			// Calculate the percent complete
			long percentComplete = ((100 * bytesRead) / contentLength);

			buffy.append("\t<percent_complete>" + percentComplete
					+ "</percent_complete>\n");
		}

		buffy.append("</response>\n");

		response.getWriter().write(buffy.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/* download******************************************* */
	/*@RequestMapping(value = "/principal/showArchivoDownloadPerfil.jspx")
	public ModelAndView showArchivoDownloadPerfil(
			@RequestParam(required = false, value = "datoProyectoId") String datoProyectoId,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		tablaEspecificaListTipoFormatoArchivo = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(40);// listado de
															// extensiones
		// fileUploadDownload=new FileUploadDownload();
		// UserSession userSession =
		// (UserSession)request.getSession().getAttribute(SgpWebConstants.SESSION_USER);
		// Usuario usuario =
		// evaluarService.findUsuarioById(userSession.getUsuarioID());
		ImagenOArchivo imagenOArchivo = registroPerfilService
				.findImagenOarchivoByIdDatoProyecto(2);// listado de archivos de
														// acuerdo a un proyecto
		List<LinkFile> listLinkFile = new ArrayList<LinkFile>();
		LinkFile linkFile;
		if (imagenOArchivo != null) {
			linkFile = new LinkFile();
			linkFile.setId(imagenOArchivo.getImagenOArchivoID());
			linkFile.setNombre(imagenOArchivo.getDescripcionDocImg());
			String extension = fileUploadDownload.getArchivoExtensionById(
					tablaEspecificaListTipoFormatoArchivo,
					imagenOArchivo.getFkIdtablaespTipoArchivo());
			linkFile.setExtension(extension);
			listLinkFile.add(linkFile);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listLinkFile", listLinkFile);
		model.put("variable", "downloadImagenArchivoPerfil.jspx");
		return new ModelAndView("mostrarArchivoDownload", model);
	}

	@RequestMapping(value = "/principal/downloadImagenArchivoPerfil.jspx")
	public void downloadImagenArchivoPerfil(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// fileUploadDownload=new FileUploadDownload();

		String idArchivo = request.getParameter("param");
		ImagenOArchivo imagenOArchivo = new ImagenOArchivo();
		imagenOArchivo = registroPerfilService.findImagenOArchivoById(Integer
				.parseInt(idArchivo));
		//tablaEspecificaListTipoFormatoArchivo = tablaEspecificaService.listTablaEspecificaByTablaGeneralId(40);// listado de
															// extensiones
		//String extension = fileUploadDownload.getArchivoExtensionById(tablaEspecificaListTipoFormatoArchivo,imagenOArchivo.getFkIdtablaespTipoArchivo());
		//String archivo = imagenOArchivo.getDescripcionDocImg() + extension;
		//fileUploadDownload.downloadArchivo(request, response, archivo,imagenOArchivo.getImagen(),ex);
		//tablaEspecificaListTipoFormatoArchivo = tablaEspecificaService.listTablaEspecificaByTablaGeneralId(40);// listado de
		// extensiones
//String extension = fileUploadDownload.getArchivoExtensionById(tablaEspecificaListTipoFormatoArchivo,imagenOArchivo.getFkIdtablaespTipoArchivo());
String extension = tablaEspecificaService.findTablaEspecificaById(imagenOArchivo.getFkIdtablaespTipoArchivo()).getDescripcionCabecera();
String archivo = imagenOArchivo.getDescripcionDocImg() +"."+ extension;
fileUploadDownload.downloadArchivo(request, response, archivo,
imagenOArchivo.getImagen(),extension);
	}*/

	@RequestMapping(value = "/principal/cargarRestriccionDuracionProyecto.jspx")
	public void cargarRestriccionDuracionProyecto(
			@RequestParam(required = false, value = "idPrograma") Integer idPrograma,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//String idPrograma = request.getParameter("cbxPrograma");
		Programa prog = crearProgramaService.findProgramaById(idPrograma);
		System.out.println("cargarRestriccionDuracionProyecto.jspx :"
				+ prog.getNombrePrograma());
		JSONObject obj = new JSONObject();
		obj.accumulate("codProg", prog.getProgramaID());
		obj.accumulate("duracionProg", prog.getDuracionPrograma());
		response.getWriter().write(obj.toString());

	}
	
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value = "/principal/registrarPerfil", method = RequestMethod.POST)
	public ModelAndView registrarPerfil(
			@ModelAttribute("imagenOArchivoTempBean") ImagenOArchivoTempBean imagenOArchivoTempBean,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String nombreArchivo ="";
		String extensionArchivo ="";
					txtProyecto = request.getParameter("txtProyecto");
					cbxModalidadFinan = request.getParameter("txtProyecto");
					cbxPrograma = Integer.valueOf(request.getParameter("cbxPrograma"));
					cbxAreaTematica = request.getParameter("cbxAreaTematica");
					cbxSubAreaTematica = Integer.valueOf(request.getParameter("cbxSubAreaTematica"));
					cbxSubNivel = request.getParameter("cbxSubNivel");
					txtDuracion = Integer.valueOf(request.getParameter("txtDuracion"));
					nombreArchivo = request.getParameter("nombreArchivo");
					extensionArchivo = request.getParameter("extension");

		// --DATOS PERFIL--
					txtFondo = request.getParameter("txtFondo");
					txtContrapartida = request.getParameter("txtContrapartida");
					txtCofinanciador = request.getParameter("txtCofinanciador");

				// --UBICACION DEL PROYECTO / BENEFICIARIOS POR RESULTADO--
					txtListadoUbicacionBeneficiarios = request.getParameter("txtListadoUbicacionBeneficiarios");

				// --DATOS DE INSTITUCION / FUENTE FINANCIADORA--
					txtInstitucionFuenFinan = request.getParameter("txtListadoInstitucionFuenFinan");

				// --RESUMEN PROYECTO--
					txtListadoResumenProyecto = request.getParameter("txtListadoResumenProyecto");

		/*System.out.println("--GRABA DATOS DE PROYECTO--");
		*/
		TmpDatoProyecto tmpDatoProyecto = new TmpDatoProyecto();
		tmpDatoProyecto.setDuracionProyecto(txtDuracion);
		Programa programax = crearProgramaService.findProgramaById(cbxPrograma);
		//int numMesesTipPeriodo = programax.getTipoPeriodo().getNumeroMeses();
		int cociente = txtDuracion / programax.getTipoPeriodo().getNumeroMeses();
		int residuo = txtDuracion % programax.getTipoPeriodo().getNumeroMeses();
		int cantPeriodo = 0;
		if (residuo == 0) {
			cantPeriodo = cociente;
		} else {
			cantPeriodo = cociente + 1;
		}
		tmpDatoProyecto.setCantidadPeriodo(cantPeriodo);
		tmpDatoProyecto.setNombreProyecto(txtProyecto);
		// traer el ultimo proyecto de un determinado programa y sumarle 1
		List listMax = ingresarPerfilService
				.findTmpDatoProyectoByProgramaIDMaxNroOrdenDatoProyecto(cbxPrograma);
		//System.out.println("tamano de la lista del proyecto maximo conforme a un programa:"+ listMax.size());
		int MaxOrdenDatoProyecto;
		int NuevoNroOrdenDatoProyecto = 0;
		if (listMax.size() == 0) {
			NuevoNroOrdenDatoProyecto = 1;
		} else {
			TmpDatoProyecto tmpDPMax = (TmpDatoProyecto) listMax.get(0);
			MaxOrdenDatoProyecto = tmpDPMax.getNumeroOrdenDatoProyecto();
			//System.out.println("numero de orden maximo de proyecto conforme a un programa:"+ MaxOrdenDatoProyecto);
			NuevoNroOrdenDatoProyecto = MaxOrdenDatoProyecto + 1;
		}
		tmpDatoProyecto.setNumeroOrdenDatoProyecto(NuevoNroOrdenDatoProyecto);
		//System.out.println("nuevo numero de orden de proyecto conforme a un programa:"+ NuevoNroOrdenDatoProyecto);
		// codigo compuesto
		// int codModalidadFinan= Integer.parseInt(cbxModalidadFinan);
		//System.out.println("cbxModalidadFinan: " + cbxModalidadFinan);

		String codMF = programax.getIdentificadorModFinan();

		/*System.out.println("mes actual: " + txtMes);
		System.out.println("ano actual: " + txtAnio);*/
		String codigoProyecto = codMF + "." + NuevoNroOrdenDatoProyecto + "."
				+ cbxAreaTematica + "." + txtMes + "." + txtAnio;
		tmpDatoProyecto.setCodigoProyecto(codigoProyecto);
		//System.out.println("codigo proyecto: " + codigoProyecto);
		tmpDatoProyecto.setFkIddetallestadocabEstproy(12);// estado proyecto
															// 12:En revision
		SubAreaTematica subAreaTematica = new SubAreaTematica();
		subAreaTematica.setSubAreaTematicaID(cbxSubAreaTematica);
		tmpDatoProyecto.setSubAreaTematica(subAreaTematica);
		Programa programa = new Programa();
		programa.setProgramaID(cbxPrograma);
		tmpDatoProyecto.setProgramaID(programa);
		tmpDatoProyecto
				.setFlagPasoTablaNormal(FondamConstans.FLAG_NO_PASO_TABLA_NORMAL);
		tablaTemporalService.saveTmpDatoProyecto(tmpDatoProyecto);

		/*System.out.println("--GRABA DATOS PERFIL--");*/
		TmpPerfil tmpPerfil = new TmpPerfil();
		tmpPerfil.setMontoSolicitadoFondam(CommonUtilities
				.isNotNullOrBlank(txtFondo) ? Double.parseDouble(txtFondo)
				: 0.0);
		tmpPerfil.setMontoContrapartida(CommonUtilities
				.isNotNullOrBlank(txtFondo) ? Double
				.parseDouble(txtContrapartida) : 0.0);
		tmpPerfil.setMontoCofinanciador(CommonUtilities
				.isNotNullOrBlank(txtFondo) ? Double
				.parseDouble(txtCofinanciador) : 0.0);
		tmpPerfil.setDuracionMeses(txtDuracion);
		tmpPerfil.setFkIddetallestadocabEstperfil(4);// estado perfil 4: por
														// evaluar
		tmpPerfil.setTMPDatoProyectoID(tmpDatoProyecto);
		tablaTemporalService.saveTmpPerfil(tmpPerfil);

		/*
		System.out.println("--GRABA IMAGEN O ARCHIVO--");
		*/

		int codExtension = tablaEspecificaService.findIdByDescripcionCabecera(extensionArchivo);// 234;
		
		if(codExtension!=0){
			// Guardar el archivo en la base de datos
			TmpImagenOArchivo tmpImagenOArchivo = new TmpImagenOArchivo();
			//tmpImagenOArchivo.setDescripcionDocImg(fileUploadDownload.getNombreArchivo());
			tmpImagenOArchivo.setDescripcionDocImg(nombreArchivo);
			tmpImagenOArchivo.setFkIdtablaespTipoArchivo(codExtension);
			tmpImagenOArchivo.setImagen(imagenOArchivoTempBean.getImagenODocumento());
			tmpImagenOArchivo.setTmpPerfil(tmpPerfil);
			tablaTemporalService.saveTmpImagenOArchivo(tmpImagenOArchivo);
		}
		
		//System.out.println("--GRABA UBICACION DEL PROYECTO / BENEFICIARIOS POR RESULTADO--");
		JSONArray jsonListadoUbicacionBeneficiarios = (JSONArray) JSONSerializer
				.toJSON(txtListadoUbicacionBeneficiarios);
		TmpUbicacionProyecto tmpUbicacionProyecto;
		TmpBeneficiariosPorResultado tmpBeneficiariosPorResultado;
		for (int i = 0; i < jsonListadoUbicacionBeneficiarios.size(); i++) {
			JSONObject ObjJsonUbicacion = (JSONObject) jsonListadoUbicacionBeneficiarios
					.getJSONObject(i).getJSONObject("ubicacion");
			System.out.println("txtObjUbicacion: " + ObjJsonUbicacion);
			DepProvDist depProvDist = new DepProvDist();
			tmpUbicacionProyecto = new TmpUbicacionProyecto();
			String txaLoc = ObjJsonUbicacion.getString("txaLoc");
			tmpUbicacionProyecto.setDetalleUbicacion(CommonUtilities
					.isNotNullOrBlank(txaLoc) ? txaLoc : null);
			depProvDist.setDepProvDistID(ObjJsonUbicacion
					.getInt("DepProvDistID"));
			tmpUbicacionProyecto.setDepProvDist(depProvDist);// es un objeto
			tmpUbicacionProyecto.setTMPDatoProyecto(tmpDatoProyecto);// es un
																		// objeto
			tablaTemporalService.saveTmpUbicacionProyecto(tmpUbicacionProyecto);
			System.out.println("grabar ubicacion: "
					+ tmpUbicacionProyecto.getDetalleUbicacion());
			// si hay beneficiarios
			System.out.println("es array?? "
					+ jsonListadoUbicacionBeneficiarios.getJSONObject(i)
							.getString("beneficiarios"));
			if (!jsonListadoUbicacionBeneficiarios.getJSONObject(i)
					.getString("beneficiarios").equalsIgnoreCase("null")) {
				JSONArray arrayJsonBeneficiarios = (JSONArray) jsonListadoUbicacionBeneficiarios
						.getJSONObject(i).getJSONArray("beneficiarios");
				for (int x = 0; x < arrayJsonBeneficiarios.size(); x++) {
					System.out.println("BENEFICIARIOS: "
							+ arrayJsonBeneficiarios.get(x));
					JSONObject objJsonBeneficiario = (JSONObject) arrayJsonBeneficiarios
							.getJSONObject(x);
					// llenar beneficiarios
					tmpBeneficiariosPorResultado = new TmpBeneficiariosPorResultado();
					tmpBeneficiariosPorResultado
							.setFkIdtablaespTipoBeneficiario(objJsonBeneficiario
									.getInt("cbxTipoBenValue"));
					tmpBeneficiariosPorResultado
							.setCaracteristicasPoblacion(objJsonBeneficiario
									.getString("txaCaracPoblacion"));
					tmpBeneficiariosPorResultado
							.setCantidadProgramado(objJsonBeneficiario
									.getInt("txtCantBen"));
					tmpBeneficiariosPorResultado
							.setFkidtablaespEstrato(objJsonBeneficiario
									.getInt("cbxEstSocBenValue"));
					tmpBeneficiariosPorResultado
							.setDescripcion(objJsonBeneficiario
									.getString("txaDescripcionPoblacion"));
					tmpBeneficiariosPorResultado.setTMPPerfil(tmpPerfil);// es
																			// un
																			// objeto
					tmpBeneficiariosPorResultado
							.setTmpubicacionproyecto(tmpUbicacionProyecto);// es
																			// un
																			// objeto
					tablaTemporalService
							.saveTmpBeneficiariosPorResultado(tmpBeneficiariosPorResultado);
					//System.out.println("grabar beneficiario: "+ tmpBeneficiariosPorResultado.getCantidadProgramado());
				}
			}

		}

		//System.out.println("--GRABA DATOS DE INSTITUCION / FUENTE FINANCIADORA--");
		JSONArray jsonListadoInstitucionFuenFinan = (JSONArray) JSONSerializer
				.toJSON(txtInstitucionFuenFinan);
		TmpInstitucion tmpInstitucion;
		TmpFuenteFinanciadora tmpFuenteFinanciadora;
		for (int i = 0; i < jsonListadoInstitucionFuenFinan.size(); i++) {
			JSONObject ObjJsonInstitucion = (JSONObject) jsonListadoInstitucionFuenFinan
					.getJSONObject(i).getJSONObject("institucion");
			System.out.println("ObjJsonInstitucion: " + ObjJsonInstitucion);
			tmpInstitucion = new TmpInstitucion();
			// tmpActividadPerfil.setNombreActividad(ObjJsonActividad.getString("txtActividad"));
			tmpInstitucion.setNombreInstitucion(ObjJsonInstitucion
					.getString("txtInstitucion"));
			tmpInstitucion.setRucInstitucion(ObjJsonInstitucion
					.getString("txtRuc"));
			tmpInstitucion.setDireccion(ObjJsonInstitucion
					.getString("txtDireccion"));
			tmpInstitucion.setTelefono(ObjJsonInstitucion
					.getString("txtTelefono"));
			tmpInstitucion.setRepresentanteLegal(ObjJsonInstitucion
					.getString("txtRepLegal"));
			tmpInstitucion.setContacto(ObjJsonInstitucion
					.getString("txtContacto"));
			tmpInstitucion.setObservacionDeInstitucion(ObjJsonInstitucion
					.getString("txaObservacion"));
			tmpInstitucion.setCorreo(ObjJsonInstitucion.getString("txtCorreo"));
			tmpInstitucion.setFkIdDetalleEstadoCabEstInstitucion(1);// estado 1:
																	// por
																	// evaluar
			tablaTemporalService.saveTmpInstitucion(tmpInstitucion);
			
			// si hay fuente financiadoras
			if (!jsonListadoInstitucionFuenFinan.getJSONObject(i)
					.getString("fuenFinan").equalsIgnoreCase("null")) {
				JSONObject ObjJsonFuenteFinanciera = (JSONObject) jsonListadoInstitucionFuenFinan
						.getJSONObject(i).getJSONObject("fuenFinan");
				//System.out.println("ObjJsonFuenteFinanciera: "+ ObjJsonFuenteFinanciera);
				tmpFuenteFinanciadora = new TmpFuenteFinanciadora();
				tmpFuenteFinanciadora.setDefineSiEsEjecutor(Integer
						.parseInt(ObjJsonFuenteFinanciera
								.getString("cbxInstitucionEjecutora")));
				tmpFuenteFinanciadora
						.setFkIdtablaespTipoFuenteFinanciadora(Integer
								.parseInt(ObjJsonFuenteFinanciera
										.getString("cbxTipoFuenteFinan")));
				tmpFuenteFinanciadora.setMontoFinanciado(Double
						.parseDouble(ObjJsonFuenteFinanciera
								.getString("txtMontoFinan")));
				tmpFuenteFinanciadora.setFkIdtablaespTipoMoneda(Integer
						.parseInt(ObjJsonFuenteFinanciera
								.getString("cbxTipoMoneda")));
				tmpFuenteFinanciadora.setTMPDatoProyectoID(tmpDatoProyecto);
				tmpFuenteFinanciadora.setTMPInstitucion(tmpInstitucion);
				tablaTemporalService
						.saveTmpFuenteFinanciadora(tmpFuenteFinanciadora);
			}

		}

		System.out.println("--GRABA RESUMEN PROYECTO----");
		JSONArray jsonListadoResumenProyecto = (JSONArray) JSONSerializer
				.toJSON(txtListadoResumenProyecto);
		TmpResumenProyecto tmpResumenProyecto;
		for (int i = 0; i < jsonListadoResumenProyecto.size(); i++) {
			String cbxTipoResumenProyecto = jsonListadoResumenProyecto
					.getJSONObject(i).getString("tipoResumenProyecto");
			String txaResumenPerfil = jsonListadoResumenProyecto.getJSONObject(
					i).getString("resumenProyecto");
			//System.out.println("tipoResumenProyecto: "+ cbxTipoResumenProyecto);
			//System.out.println("resumenProyecto: " + txaResumenPerfil);
			tmpResumenProyecto = new TmpResumenProyecto();
			tmpResumenProyecto.setDefinicion(txaResumenPerfil);
			tmpResumenProyecto.setFkIdtablaespTipoResumenProy(Integer
					.parseInt(cbxTipoResumenProyecto));
			tmpResumenProyecto.setFkIdTablaGeneral(32);// id de tipo resumen
														// proyecto
			tmpResumenProyecto.setTmpDatoProyectoID(tmpDatoProyecto);
			tablaTemporalService.saveTmpResumenProyecto(tmpResumenProyecto);
		}

		// System.out.println("tmpDatoProyecto.getTMPDatoProyectoID(): "+tmpDatoProyecto.getTMPDatoProyectoID());
		if (programax.getReqEvalInst() == 0 && programax.getReqEvalTec() == 0) {
			tablaTemporalService.saveInformacionTMPatablas(tmpDatoProyecto
					.getTMPDatoProyectoID());
		}
		request.getSession().removeAttribute("listResumenProyecto");
		return new ModelAndView("cerrarSesion");
		// return new ModelAndView("mostrarCrearPerfil");

	}

	String mensaje = "no";
	List<TablaEspecifica> listTipoResumen = null;

	@RequestMapping(value = "/principal/showGestionarPerfil.jspx")
	public ModelAndView showGestionarPerfil(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		Usuario usuario = evaluarService.findUsuarioById(userSession
				.getUsuarioID());
		listTipoResumen = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(32);// tipo de resumen para
															// perfil
		Perfil perfil = registroPerfilService
				.findPerfilByDatoProyectoID(usuario.getDatoUsuario()
						.getDatoProyectoID());
		List<ResumenProyecto> listResumenProyecto = registroPerfilService
				.findResumenProyectoByIdDatoProyByTablaGeneralId(usuario
						.getDatoUsuario().getDatoProyectoID(), 32);

		if (listResumenProyecto != null && listResumenProyecto.size() != 0) {
			request.getSession().setAttribute("listResumenProyecto",
					listResumenProyecto);
		} else {
			request.getSession().setAttribute("listResumenProyecto",
					listResumenProyecto);
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("perfil", perfil);
		String estado = detalleEstadoCabeceraService
				.findDetalleEstadoCabeceraById(
						perfil.getFkIddetallestadocabEstperfil())
				.getPrefijoEstado();
		model.put("listTipoResumen", listTipoResumen);
		model.put("estadoPerfil", estado);
		model.put("mensaje", "no");
		model.put("funcionalidadSelect", "showGestionarPerfil.jspx");

		model.put("cantMuestraMensajeObs",1);
		
		return new ModelAndView("mostrarGestionarPerfil", model);
	}

	@RequestMapping(value = "/principal/actionActualizarGestionarPerfil.jspx")
	public ModelAndView actionActualizarGestionarPerfil(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		// Usuario usuario =
		// evaluarService.findUsuarioById(userSession.getUsuarioID());
		Perfil perfil = registroPerfilService
				.findPerfilByDatoProyectoID(userSession.getDatoProyectoID());
		perfil.setDuracionMeses(Integer.parseInt(request.getParameter(
				"duracionMeses").toString()));
		perfil.setMontoCofinanciador(Double.parseDouble(request.getParameter(
				"montoCofinanciador").toString()));
		perfil.setMontoContrapartida(Double.parseDouble(request.getParameter(
				"montoContrapartida").toString()));
		perfil.setMontoSolicitadoFondam(Double.parseDouble(request
				.getParameter("montoSolicitadoFondam").toString()));

		perfil = registroPerfilService.updatePerfil(perfil);

		// fileUploadDownload=new FileUploadDownload();
		fileUploadDownload.recogeParam(request);
		// String nomFile=request.getParameter("nomFile").toString();

		if (fileUploadDownload.getNombreArchivo() != null) { // ||
																// fileUploadDownload.getNombreArchivo()!=""
																// ||fileUploadDownload.getExtension()!=null)
																// {
			ImagenOArchivo imagenOArchivo = registroPerfilService
					.findImagenOarchivoByPerfilId(perfil.getPerfilID());
			registroPerfilService.deleteImagenOArchivo(imagenOArchivo);
				
			System.out.println("nombreArchivo: "
					+ fileUploadDownload.getNombreArchivo());
			System.out.println("extension: "
					+ fileUploadDownload.getExtension());

			int codExtension;

			if ((".pdf").equalsIgnoreCase(fileUploadDownload.getExtension())) {// codigos(pdf=234
																				// /
																				// doc=236)

				codExtension = 234;
			} else {
				codExtension = 236;
			}
			// crear archivo, convertir a array de byte
			File uploadedFile = new File(fileUploadDownload.getFilePath(),
					fileUploadDownload.getNombreArchivo()
							+ fileUploadDownload.getExtension());
			byte archivoByte[] = FileUploadDownload
					.convertirFileToArrayByte(uploadedFile);
			System.out.println("tamano de archivo al guardar en la BD :"
					+ archivoByte.length);

			// Guardar el archivo en la base de datos
			ImagenOArchivo imagenOAr = new ImagenOArchivo();
			imagenOAr.setDescripcionDocImg(fileUploadDownload
					.getNombreArchivo());
			imagenOAr.setFkIdtablaespTipoArchivo(codExtension);
			imagenOAr.setImagen(archivoByte);
			// Perfil
			// perfil=registroPerfilService.findPerfilByDatoProyectoID(proyectoID);
			imagenOAr.setPerfil(perfil);
			crearProgramaService.saveImagenOArchivo(imagenOAr);

		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("perfil", perfil);
		model.put("listTipoResumen", listTipoResumen);
		model.put("mensaje", "Se modifico los montos del Perfil exitosamente");

		// return new ModelAndView("mostrarGestionarPerfil",model);
		return new ModelAndView("principalMain");
	}

	@RequestMapping(value = "/principal/actionEliminarResumenProyecto.jspx")
	public ModelAndView actionEliminarResumenProyecto(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		Integer idResumenProyecto = Integer.parseInt(request.getParameter(
				"idResumenProyecto").toString());

		registroPerfilService.deleteResumenProyecto(idResumenProyecto);
		mensaje = "Se elimino el resumen exitosamente";

		return showGestionarPerfil(request, response);
	}

	@RequestMapping(value = "/principal/actionAgregarResumenProyecto.jspx")
	public ModelAndView actionAgregarResumenProyecto(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		Usuario usuario = evaluarService.findUsuarioById(userSession
				.getUsuarioID());

		Integer idTipoResumen = Integer.parseInt(request.getParameter(
				"idTipoResumen").toString());
		String definicion = request.getParameter("definicionR").toString();

		ResumenProyecto resumenProyecto = new ResumenProyecto();
		resumenProyecto.setDefinicion(definicion);
		resumenProyecto.setFkIdtablaespTipoResumenProy(idTipoResumen);
		resumenProyecto.setFkIdTablaGeneral(tablaEspecificaService
				.findTablaEspecificaById(idTipoResumen).getTablaGeneral()
				.getTablaGeneralID());
		DatoProyecto datoProyecto = evaluarService.findDatoProyectoById(usuario
				.getDatoUsuario().getDatoProyectoID());
		resumenProyecto.setDatoProyecto(datoProyecto);

		registroPerfilService.saveResumenProyecto(resumenProyecto);

		mensaje = "Se registro el resumen exitosamente";
		return showGestionarPerfil(request, response);
	}

	/* download******************************************* */
	@RequestMapping(value = "/principal/showImagenArchivoDownloadGestionarPerfil.jspx")
	public ModelAndView showImagenArchivoDownloadGestionarPerfil(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// fileUploadDownload=new FileUploadDownload();
		tablaEspecificaListTipoFormatoArchivo = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(40);// listado de
															// extensiones
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		Perfil perfil = registroPerfilService
				.findPerfilByDatoProyectoID(userSession.getDatoProyectoID());
		// Integer
		// perfilId=Integer.parseInt(request.getParameter("perfilId").toString());
		 ImagenOArchivo  imagenOArchivo = registroPerfilService
				.findImagenOarchivoByPerfilId(perfil.getPerfilID());// listado
																	// de
																	// archivos
																	// de
																	// acuerdo a
																	// un perfil
		List<LinkFile> listLinkFile = new ArrayList<LinkFile>();
		LinkFile linkFile;
		if (imagenOArchivo != null) {
			linkFile = new LinkFile();
			linkFile.setId(imagenOArchivo.getImagenOArchivoID());
			linkFile.setNombre(imagenOArchivo.getDescripcionDocImg());
			String extension = fileUploadDownload.getArchivoExtensionById(
					tablaEspecificaListTipoFormatoArchivo,
					imagenOArchivo.getFkIdtablaespTipoArchivo());
			linkFile.setExtension(extension);
			listLinkFile.add(linkFile);
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listLinkFile", listLinkFile);
		model.put("variable", "downloadImagenArchivoGestionarPerfil.jspx");
		return new ModelAndView("mostrarArchivoDownload", model);
	}

	@RequestMapping(value = "/principal/downloadImagenArchivoGestionarPerfil.jspx")
	public void downloadImagenArchivoGestionarPerfil(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// fileUploadDownload=new FileUploadDownload();
		String idArchivo = request.getParameter("param");
		ImagenOArchivo imagenOArchivo = new ImagenOArchivo();
		imagenOArchivo = registroPerfilService.findImagenOArchivoById(Integer
				.parseInt(idArchivo));
		//tablaEspecificaListTipoFormatoArchivo = tablaEspecificaService.listTablaEspecificaByTablaGeneralId(40);// listado de
		// extensiones
		//String extension = fileUploadDownload.getArchivoExtensionById(tablaEspecificaListTipoFormatoArchivo,imagenOArchivo.getFkIdtablaespTipoArchivo());
		String extension = tablaEspecificaService.findTablaEspecificaById(imagenOArchivo.getFkIdtablaespTipoArchivo()).getDescripcionCabecera();
		String archivo = imagenOArchivo.getDescripcionDocImg() +"."+ extension;
		fileUploadDownload.downloadArchivo(request, response, archivo,
		imagenOArchivo.getImagen(),extension);
	}

	@RequestMapping(value = "/principal/showMontosAcumulados")
	public ModelAndView showMontosAcumulados(HttpServletRequest request) {
		Double montoFondam = CommonUtilities.toDouble(request
				.getParameter("montoFondam"));
		Double montoContrapartida = CommonUtilities.toDouble(request
				.getParameter("montoContrapartida"));
		Double montoCofinanciador = CommonUtilities.toDouble(request
				.getParameter("montoCofinanciador"));

		MontosAcumulados montosAcumulados = new MontosAcumulados();
		montosAcumulados.setMontoFondam(montoFondam);
		montosAcumulados.setMontoCofinanciador(montoCofinanciador);
		montosAcumulados.setMontoContrapartida(montoContrapartida);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("montosAcumulados", montosAcumulados);

		return new ModelAndView("showMontosAcumulados", model);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/cargaGrillaResumenProyecto")
	public ModelAndView cargaGrillaResumenProyecto(
			@RequestParam(required = false, value = "tipoResumenId") Integer tipoResumenId,
			@RequestParam(required = false, value = "resumen") String resumen,
			@RequestParam(required = false, value = "flagModifica") String flagModifica,
			HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		List<ResumenProyecto> listResumenProyecto= new ArrayList<ResumenProyecto>();
		ResumenProyecto resumenProyecto = new ResumenProyecto();
		Integer cantidadResumenPerfil=0;
		if(tipoResumenId!=0){
			
			if (request.getSession().getAttribute("listResumenProyecto")!=null){
				listResumenProyecto= (List<ResumenProyecto>) request.getSession().getAttribute("listResumenProyecto");
			}
			if(flagModifica.equals("0")){
				resumenProyecto.setFkIdtablaespTipoResumenProy(tipoResumenId);
				resumenProyecto.setDescripcionTipoResumenProy(tablaEspecificaService.findTablaEspecificaById(tipoResumenId).getDescripcionCabecera());
				resumenProyecto.setDefinicion(resumen);
			
				listResumenProyecto.add(resumenProyecto);
			}else if(flagModifica.equals("m")){
				for (ResumenProyecto resumenProyectoTemp : listResumenProyecto) {
					if(resumenProyectoTemp.getFkIdtablaespTipoResumenProy()==tipoResumenId){
						resumenProyectoTemp.setDefinicion(resumen);
						//resumenProyectoTemp.setFkIdtablaespTipoResumenProy(tipoResumenId);
					}
				}
			}
			
			cantidadResumenPerfil= listResumenProyecto.size();
			model.put("listResumenProyecto", listResumenProyecto);
			request.getSession().setAttribute("listResumenProyecto", listResumenProyecto);
		
		}else{
			listResumenProyecto = (List<ResumenProyecto>) request.getSession().getAttribute("listResumenProyecto");
			if(listResumenProyecto != null){
				cantidadResumenPerfil= listResumenProyecto.size();
			}
		}
		model.put("cantidadResumenPerfil", cantidadResumenPerfil);
		return new ModelAndView("divGrillaResumenProyecto", model);

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/eliminarRegistroGrillaResumenProyecto")
	public ModelAndView eliminarRegistroGrillaResumenProyecto(
			@RequestParam(required = false, value = "tipoResumenId") Integer tipoResumenId,
			HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		List<ResumenProyecto> listResumenProyecto= new ArrayList<ResumenProyecto>();
		List<ResumenProyecto> listResumenProyectoTemp= new ArrayList<ResumenProyecto>();
		
			if (request.getSession().getAttribute("listResumenProyecto")!=null){
				listResumenProyecto= (List<ResumenProyecto>) request.getSession().getAttribute("listResumenProyecto");
			}
			
			for (ResumenProyecto resumenProyecto : listResumenProyecto) {
					if(resumenProyecto.getFkIdtablaespTipoResumenProy()!=tipoResumenId){
						listResumenProyectoTemp.add(resumenProyecto);
					}
				}			
			model.put("cantidadResumenPerfil", listResumenProyectoTemp.size());
			model.put("listResumenProyecto", listResumenProyectoTemp);
			request.getSession().setAttribute("listResumenProyecto", listResumenProyectoTemp);
		
		return new ModelAndView("divGrillaResumenProyecto", model);

	}
	
	// ************** listas ***********************//
	@SuppressWarnings("rawtypes")
	public void listadoProgramasFinal() {
		listProgramas = registroPerfilService.findProgramas();
		listProgramasFinal = new ArrayList<ProgramaBean>();
		ProgramaBean programaBean;
		Programa programa;
		for (Iterator iterador = listProgramas.listIterator(); iterador
				.hasNext();) {
			programaBean = new ProgramaBean();
			programa = new Programa();
			programa = (Programa) iterador.next();
			programaBean.setProgramaID(programa.getProgramaID());
			programaBean.setNombrePrograma(programa.getNombrePrograma());
			programaBean.setDuracionPrograma(programa.getDuracionPrograma());
			programaBean.setFkIdtablaespModalidadFinancia(programa
					.getFkIdtablaespModalidadFinancia());
			listProgramasFinal.add(programaBean);
		}
	}

}
