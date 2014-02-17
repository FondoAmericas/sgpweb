package pe.com.fondam.sgp.web.controller.principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

import pe.com.fondam.sgp.core.bean.BeneficiariosPorResultadoBean;
import pe.com.fondam.sgp.core.bean.CuentaCorrienteBean;
import pe.com.fondam.sgp.core.bean.FuenteFinanciadoraBean;
import pe.com.fondam.sgp.core.bean.ImagenOArchivoTempBean;
import pe.com.fondam.sgp.core.bean.UbicacionProyectoBean;
import pe.com.fondam.sgp.core.conexion.ConexionJdbcTemplate;
import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.CuentaCorriente;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;
import pe.com.fondam.sgp.core.domain.Institucion;
import pe.com.fondam.sgp.core.domain.Perfil;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.ResumenProyecto;
import pe.com.fondam.sgp.core.domain.SubAreaTematica;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.UbicacionProyecto;
import pe.com.fondam.sgp.core.domain.Usuario;
import pe.com.fondam.sgp.core.service.CrearProgramaService;
import pe.com.fondam.sgp.core.service.CuentaCorrienteService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.EvaluarService;
import pe.com.fondam.sgp.core.service.ProyectoService;
import pe.com.fondam.sgp.core.service.RegistroPerfilService;
import pe.com.fondam.sgp.core.service.ResumenProyectoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.TablaTemporalService;
import pe.com.fondam.sgp.core.service.UtilService;
import pe.com.fondam.sgp.web.InOutFiles.FileUploadDownload;
import pe.com.fondam.sgp.web.InOutFiles.LinkFile;
import pe.com.fondam.sgp.web.constants.MenuConstants;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.controller.security.SecurityController;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class ProyectoController {

	//************************ inyecciones *****************************//
	@Resource
	CrearProgramaService crearProgramaService;

	@Resource
	EvaluarService evaluarService;

	@Resource
	CrearProgramaService programaService;

	@Resource
	TablaEspecificaService tablaEspecificaService;

	@Resource
	private RegistroPerfilService registroPerfilService;

	@Resource
	TablaTemporalService tablaTemporalService;

	@Resource
	ProyectoService proyectoService;

	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;

	@Resource
	UtilService utilService;

	@Resource
	DatoProyectoService datoProyectoService;

	@Resource
	ResumenProyectoService resumenProyectoService;

	@Resource
	CuentaCorrienteService cuentaCorrienteService;

	DatoUsuario datoUsuario = new DatoUsuario();
	DatoProyecto datoProyecto = new DatoProyecto();
	Perfil perfil;
	UserSession userSession = new UserSession();
	String modFinan;
	String[] codigoProyArray;
	Integer idPrograma;
	String nomPrograma;
	FileUploadDownload fileUploadDownload = new FileUploadDownload();

	List<UbicacionProyecto> listUbicacionProyecto;
	List<UbicacionProyectoBean> listUbicacionProyectoFinal;
	List<BeneficiariosPorResultado> listBeneficiariosPorResultado;
	List<BeneficiariosPorResultadoBean> listBeneficiariosPorResultadoFinal;
	List<Institucion> listInstitucion;
	List<FuenteFinanciadora> listFuentesFinancieras;
	List<FuenteFinanciadoraBean> listFuentesFinancierasFinal;
	// List<ResumenProyecto> listResumenProyecto;
	// List<ResumenProyectoBean> listResumenProyectoBean;
	List<TablaEspecifica> listBancos;
	// List<CuentaCorriente> listCuentaCorriente;
	List<CuentaCorrienteBean> listCuentaCorrienteFinal;
	// List<IndicadorMarcoLogico> listIndicadorMarcoLogico;
	// List<IndicadorMarcoLogicoBean> listIndicadorMarcoLogicoFinal;
	List<TablaEspecifica> tablaEspecificaListTipoFormatoArchivo;
	List<TablaEspecifica> listUnidadMedida;

	@Resource
	ConexionJdbcTemplate conexionJdbcTemplate;

	protected final Log logger = LogFactory.getLog(ProyectoController.class);

	
	//************************ metodos *****************************//
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/principal/showProyecto.jspx")
	public ModelAndView showProyecto(HttpServletRequest request) {

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		if (userSession == null) {
			return SecurityController.autenticateErrorRedirect(request);
		}
		
		datoUsuario = new DatoUsuario();
		datoUsuario = crearProgramaService.findDatoUsuarioById(userSession
				.getDatoUsuarioID());
		
		// datos del proyecto
		datoProyecto = evaluarService.findDatoProyectoById(datoUsuario
				.getDatoProyectoID());
		// datos del programa
		Programa programa = programaService.findProgramaById(datoProyecto
				.getPrograma().getProgramaID());
		idPrograma = programa.getProgramaID();
		nomPrograma = programa.getNombrePrograma();
		// obteniendo modalidad de financiamiento
		String codProy = datoProyecto.getCodigoProyecto();
		codigoProyArray = codProy.split("\\.");
		//logger.info("modFinan: " + codigoProyArray[0]);

		if (codigoProyArray[0].equalsIgnoreCase("CO"))
			modFinan = "Cofinanciamiento";
		else if (codigoProyArray[0].equalsIgnoreCase("PE"))
			modFinan = "Proyectos Especiales";
		else if (codigoProyArray[0].equalsIgnoreCase("ED"))
			modFinan = "Ejes de Desarrollo";
		else
			modFinan = "Concurso";

		// Areas tematicas y SubAreas Tematicas
		SubAreaTematica subAreaTematica = crearProgramaService
				.findSubAreaTematicaById(datoProyecto.getSubAreaTematica()
						.getSubAreaTematicaID());
		TablaEspecifica tablaEspecifica = tablaEspecificaService
				.findTablaEspecificaById(Integer.parseInt(codigoProyArray[2]));

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("codProyecto", datoProyecto.getCodigoProyecto());
		model.put("nombreProyecto", datoProyecto.getNombreProyecto());
		model.put("duracionProy", datoProyecto.getDuracionProyecto());
		model.put("modFinan", modFinan);
		model.put("idPrograma", idPrograma);
		model.put("nomPrograma", nomPrograma);
		model.put(
				"estadoProyecto",
				detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(
						datoProyecto.getFkIddetallestadocabEstproy())
						.getPrefijoEstado());
		model.put(
				"estadoProyectoDescripcion",
				detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(
						datoProyecto.getFkIddetallestadocabEstproy())
						.getDescripEstado());
		model.put("areaTematica", tablaEspecifica.getDescripcionCabecera());
		model.put("listTipoBeneficiario", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("TPBF"));
		model.put("subAreaTematica", subAreaTematica.getDescripcionSubAt());
		model.put("listBancos", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("BNCO"));
		model.put("listMoneda", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("TMND"));
		model.put("listEstrato", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("ESTT"));
		model.put("listTipoResumenProyecto", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("TRPY"));
		model.put("listUnidadMedida", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("UNMD"));
		model.put("listFuenteFinanciadora", tablaEspecificaService
				.findTablaEspecificabyPrefijoTablaGeneral("TFFN"));

		//model.put("funcionalidadSelect", "showProyecto.jspx");
        MenuConstants menuConstants = new MenuConstants("showProyecto.jspx");
		model.put("funcionalidadSelect", menuConstants.urlSelect );
		//request.getSession().setAttribute(menuConstants.funcionalidad_Select ,menuConstants.urlSelect);

		ImagenOArchivoTempBean imagenOArchivoTempBean= new ImagenOArchivoTempBean();
		model.put("imagenOArchivoTempBean",imagenOArchivoTempBean);
		
		model.put("cantMuestraMensajeObs",1);
		
		return new ModelAndView("mostrarProyecto", model);
	}

	@RequestMapping(value = "/principal/obtenerDataIntitucionFuenFinan")
	public void obtenerDataIntitucionFuenFinan(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		JSONObject objJsonIns = new JSONObject();
		objJsonIns.accumulate("estado", "si");
		// obtener listado de instituciones y listado de fuentes financieras
		// deacuerdo a un proyecto.
		listInstitucion = proyectoService
				.getInstitucionesFinanciadorasByProyectoId(datoProyecto
						.getDatoProyectoID());

		listadoFuentesFinancieras();
		// listadoInstitucionesFinal();
		JSONArray jsonArrayInstituciones = new JSONArray();
		jsonArrayInstituciones.add(0, listInstitucion);
		// jsonArrayInstituciones.add(0,listInstitucionesFinal2);
		jsonArrayInstituciones.add(1, listFuentesFinancierasFinal);
		jsonArrayInstituciones.add(2, objJsonIns);
		response.getWriter().write(jsonArrayInstituciones.toString());
	}

	@RequestMapping(value = "/principal/cargarCuentasCorrientes")
	public ModelAndView cargarCuentasCorrientes(
			@RequestParam(required = false, value = "bancoId") Integer bancoId,
			@RequestParam(required = false, value = "tipoMonedaId") Integer tipoMonedaId,
			@RequestParam(required = false, value = "numeroCuenta") String numeroCuenta,
			@RequestParam(required = false, value = "cuentaCorrienteId") Integer cuentaCorrienteId,
			@RequestParam(required = false, value = "flagElimina") String flagElimina,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if (bancoId != null) {
			CuentaCorriente cuentaCorriente = new CuentaCorriente();
			cuentaCorriente.setDatoProyecto(datoProyecto);
			cuentaCorriente.setFkIdtablaespBanco(bancoId);
			cuentaCorriente.setFkIdtablaespTipomoneda(tipoMonedaId);
			cuentaCorriente.setNumeroCuenta(numeroCuenta);
			if (cuentaCorrienteId != null) {
				cuentaCorriente.setCuentaCorrienteID(cuentaCorrienteId);
			}
			cuentaCorrienteService.updateCuentaCorriente(cuentaCorriente);
		}
		
		if(flagElimina!=null){
			cuentaCorrienteService.deleteCuentaCorriente(cuentaCorrienteId);
		}
		// traer listado de cuentas corrientes de un proyecto
		List<CuentaCorriente> listCuentaCorriente = evaluarService
				.findCuentaCorrienteByIdDatoProyecto(datoProyecto
						.getDatoProyectoID());

		Map<String, Object> model = new HashMap<String, Object>();

		model.put(
				"listCuentaCorrienteBean",
				cuentaCorrienteService.llenaListCuentasCorrientesBean(evaluarService
						.verificarSiCuentaCorrienteEstaReferencidaPorTablas(listCuentaCorriente)));

		return new ModelAndView("grillaCuentaCorriente", model);
	}

	/*
	 * @RequestMapping(value = "/principal/obtenerDataCuentasCorrientes") public
	 * void obtenerDataCuentasCorrientes(HttpServletRequest request,
	 * HttpServletResponse response) throws IOException {
	 * 
	 * //traer listado de cuentas corrientes de un proyecto listCuentaCorriente
	 * = evaluarService.findCuentaCorrienteByIdDatoProyecto(datoProyecto.
	 * getDatoProyectoID());
	 * 
	 * listCuentaCorriente =
	 * evaluarService.verificarSiCuentaCorrienteEstaReferencidaPorTablas
	 * (listCuentaCorriente);
	 * logger.info("-------------------listado cuenta corriente: "+
	 * listCuentaCorriente.size()); listadoCuentasCorrientes(); JSONArray
	 * jsonArrayCuentasCorrientes = new JSONArray();
	 * jsonArrayCuentasCorrientes.add(0,listCuentaCorrienteFinal);
	 * logger.info("cuentas corrientes: "
	 * +jsonArrayCuentasCorrientes.toString());
	 * response.getWriter().write(jsonArrayCuentasCorrientes.toString()); }
	 */

	@RequestMapping(value = "/principal/cargarDataCBXTipoBeneficiario")
	public void obtenerDataTipoBeneficiario(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		List<TablaEspecifica> lstTipoBeneficiario = utilService
				.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_BENEFICIARIO);

		String jsonTipoBeneficiarios = utilService
				.convertirTablaEspecificaToJSONArrayCBX(lstTipoBeneficiario);
		logger.info("tipos de beneficiario : " + jsonTipoBeneficiarios);
		response.getWriter().write(jsonTipoBeneficiarios);
	}

	@RequestMapping(value = "/principal/cargaComboTipoFuenteFinanciadora1.jspx")
	public void cargaComboTipoFuenteFinanciadora(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		List<TablaEspecifica> listFuenteFinanciadora = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(30);

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, listFuenteFinanciadora);
		//logger.info("-----------cargaComboTipoFuenteFinanciadora1.jspx "+ jsonArray.toString());
		response.getWriter().write(jsonArray.toString());
	}

	@RequestMapping(value = "/principal/obtenerDataUbicacionProyecto")
	public void obtenerDataUbicacionProyecto(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		/* UBICACION PROYECTO */
		// obtener perfil de un proyecto determinado
		perfil = evaluarService.findPerfilByDatoProyectoID(datoUsuario
				.getDatoProyectoID());

		listadoUbicacionProyectoFinal();
		listadoBeneficiariosPorResultadoFinal();

		JSONArray jsonArrayUbicaciones = new JSONArray();
		jsonArrayUbicaciones.add(0, listUbicacionProyectoFinal);
		jsonArrayUbicaciones.add(1, listBeneficiariosPorResultadoFinal);

		response.getWriter().write(jsonArrayUbicaciones.toString());
	}

	@RequestMapping(value = "/principal/obtenerDataResumenProyecto")
	public void obtenerDataResumenProyecto(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// listadoResumenProyecto();
		JSONArray jsonArrayResumenProyecto = new JSONArray();
		// jsonArrayResumenProyecto.add(listResumenProyectoBean);
		response.getWriter().write(jsonArrayResumenProyecto.toString());
	}

	@RequestMapping(value = "/principal/cargaResumenProyecto")
	public ModelAndView cargaResumenProyecto(
			@RequestParam(required = false, value = "tipoResumenId") Integer tipoResumenId,
			@RequestParam(required = false, value = "resumenProyectoId") Integer resumenProyectoId,
			@RequestParam(required = false, value = "resumenProyecto") String resumenProyectoDescripcion,
			@RequestParam(required = false, value = "flagElimina") String flagElimina,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		ResumenProyecto resumenProyecto = new ResumenProyecto();

		if (tipoResumenId != null) {
			resumenProyecto.setDefinicion(resumenProyectoDescripcion);
			resumenProyecto.setFkIdtablaespTipoResumenProy(tipoResumenId);
			resumenProyecto.setFkIdTablaGeneral(tablaEspecificaService
					.findTablaEspecificaById(tipoResumenId).getTablaGeneral()
					.getTablaGeneralID());
			resumenProyecto.setDatoProyecto(datoProyectoService
					.findDatoProyectoById(datoUsuario.getDatoProyectoID()));
			if (resumenProyectoId != null) {
				resumenProyecto.setResumenProyectoID(resumenProyectoId);
			}
			resumenProyectoService.updateResumenProyecto(resumenProyecto);
		}
		if (flagElimina != null) {
			resumenProyectoService.deleteResumenProyecto(resumenProyectoId);
		}

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("listResumenProyectoBean",
			resumenProyectoService.listadoResumenProyecto(datoUsuario.getDatoProyectoID()));

		return new ModelAndView("grillaResumenProyecto", model);

	}

	@RequestMapping(value = "/principal/obtenerBancos")
	public void obtenerBancos(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		listBancos = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(3);
		JSONArray jsonArrayBancos = new JSONArray();
		jsonArrayBancos.add(listBancos);
		response.getWriter().write(jsonArrayBancos.toString());
	}

	@RequestMapping(value = "/principal/saveRegistrarGestionProyecto", method = RequestMethod.POST)
	public ModelAndView saveGestionProyecto(
			@ModelAttribute("imagenOArchivoTempBean") ImagenOArchivoTempBean imagenOArchivoTempBean,
			HttpServletRequest request)
			throws Exception {

		Map<String, String> params = new HashMap<String, String>();

		params.put("txtListadoCuentaCorriente",request.getParameter("txtListadoCuentaCorriente"));
		params.put("txtListadoUbicacionBeneficiarios",request.getParameter("txtListadoUbicacionBeneficiarios"));
		params.put("txtListadoInstitucionFuenFinan",request.getParameter("txtListadoInstitucionFuenFinan"));

		boolean exito = proyectoService.saveGestionProyecto(datoProyecto,
				params);

		String pantalla= "principalMain";
		if (exito) {
			//logger.info("-----GRABA O MODIFICA IMAGEN O ARCHIVO-----");
			//Integer tamanio = imagenOArchivoTempBean.getImagenODocumento().length;
		//if(tamanio<8000000){	
			if (!imagenOArchivoTempBean.getImagenODocumento().equals("") ) {
				String nombreArchivo= request.getParameter("nombreArchivo");
				String extencion= request.getParameter("extension");
				int codExtension =  tablaEspecificaService.findIdByDescripcionCabecera(extencion);// 234;

				if (codExtension != 0) {
					// Guardar el archivo en la base de datos
					ImagenOArchivo imagenOArchivo = proyectoService.findProyetoByArchivoImagen(datoProyecto
							.getDatoProyectoID());
					if ( imagenOArchivo!= null) {
						//imagenOArchivo = proyectoService.findProyetoByArchivoImagen(datoProyecto.getDatoProyectoID());
						imagenOArchivo.setDescripcionDocImg(nombreArchivo);
						imagenOArchivo.setFkIdtablaespTipoArchivo(codExtension);
						imagenOArchivo.setImagen(imagenOArchivoTempBean.getImagenODocumento());
						crearProgramaService
								.updateImagenOArchivo(imagenOArchivo);
					} else {
						imagenOArchivo= new ImagenOArchivo();
						imagenOArchivo.setDescripcionDocImg(nombreArchivo);
						imagenOArchivo.setFkIdtablaespTipoArchivo(codExtension);
						imagenOArchivo.setImagen(imagenOArchivoTempBean.getImagenODocumento());
						imagenOArchivo.setDatoProyecto(datoProyecto);
						programaService.saveImagenOArchivo(imagenOArchivo);
					}
				}
			}
		//	pantalla="mostrarProyecto";
		//}
		}
		return new ModelAndView(pantalla);
	}

	@RequestMapping(value = "/principal/showImagenArchivoDownloadProyecto.jspx")
	public ModelAndView showImagenArchivoDownloadProyecto(
			@RequestParam(required = false, value = "datoProyectoId") String datoProyectoId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		//String datoProyectoId=(String) request.getSession().getAttribute("datoProyectoId");
		
		tablaEspecificaListTipoFormatoArchivo = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(40);// listado de
															// extensiones
		List<LinkFile> listLinkFile = new ArrayList<LinkFile>();
		LinkFile linkFile = new LinkFile();
		ImagenOArchivo imagenOArchivo = null;
		
		try {
			if(datoProyectoId==null){
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		Usuario usuario = evaluarService.findUsuarioById(userSession
				.getUsuarioID());
		
			imagenOArchivo = proyectoService.findProyetoByArchivoImagen(usuario
					.getDatoUsuario().getDatoProyectoID());
			}else{
				imagenOArchivo = proyectoService.findProyetoByArchivoImagen(Integer.valueOf(datoProyectoId));
			}
			
			if (imagenOArchivo != null) {
				linkFile.setId(imagenOArchivo.getImagenOArchivoID());
				linkFile.setNombre(imagenOArchivo.getDescripcionDocImg());
				String extension = fileUploadDownload.getArchivoExtensionById(
						tablaEspecificaListTipoFormatoArchivo,
						imagenOArchivo.getFkIdtablaespTipoArchivo());
				linkFile.setExtension(extension);
				listLinkFile.add(linkFile);
			}
		} catch (Exception e) {

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listLinkFile", listLinkFile);
		model.put("variable", "downloadImagenArchivoProyecto.jspx");
		return new ModelAndView("mostrarArchivoDownload", model);
	}

	@RequestMapping(value = "/principal/downloadImagenArchivoProyecto.jspx")
	public void downloadImagenArchivoProyecto(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
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

	@RequestMapping(value = "/principal/showArchivoUploadProyecto.jspx")
	public ModelAndView showArchivoUploadProyecto(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String peticion = "archivoUploadProyecto.jspx";
		String archivoResp = "false";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("peticion", peticion);
		model.put("archivoResp", archivoResp);
		return new ModelAndView("mostrarArchivoUpload", model);
	}

	@RequestMapping(value = "/principal/archivoUploadProyecto.jspx")
	public ModelAndView archivoUploadProyecto(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		fileUploadDownload.archivoUpload(request, response);
		String archivoResp = "true";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("archivoResp", archivoResp);
		model.put("nombreArchivoUP", fileUploadDownload.getNombreArchivo()
				+ fileUploadDownload.getExtension());

		return new ModelAndView("mostrarArchivoUpload", model);
	}

	/****************** listas **************************/
/*
	public List<CuentaCorrienteBean> llenaListCuentasCorrientesBean(
			List<CuentaCorriente> listCuentaCorriente) {

		List<CuentaCorrienteBean> listCuentaCorrienteBean = new ArrayList<CuentaCorrienteBean>();
		for (CuentaCorriente cuentaCorriente : listCuentaCorriente) {
			CuentaCorrienteBean cuentaCorrienteBean = new CuentaCorrienteBean();
			cuentaCorrienteBean.setCuentaCorrienteID(String
					.valueOf(cuentaCorriente.getCuentaCorrienteID()));
			cuentaCorrienteBean.setFkIdtablaespBanco(String
					.valueOf(cuentaCorriente.getFkIdtablaespBanco()));
			cuentaCorrienteBean.setDescripcionBanco(tablaEspecificaService
					.findTablaEspecificaById(
							cuentaCorriente.getFkIdtablaespBanco())
					.getDescripcionCabecera());
			cuentaCorrienteBean.setFkIdtablaespTipomoneda(String
					.valueOf(cuentaCorriente.getFkIdtablaespTipomoneda()));
			cuentaCorrienteBean.setDescripcionTipoMoneda(tablaEspecificaService
					.findTablaEspecificaById(
							cuentaCorriente.getFkIdtablaespTipomoneda())
					.getDescripcionCabecera());
			cuentaCorrienteBean.setNumeroCuenta(cuentaCorriente
					.getNumeroCuenta());
			cuentaCorrienteBean.setDatoProyecto(String.valueOf(cuentaCorriente
					.getDatoProyecto().getDatoProyectoID()));
			cuentaCorrienteBean.setFlagReferencia(cuentaCorriente
					.getFlagReferencia());
			listCuentaCorrienteBean.add(cuentaCorrienteBean);
		}
		return listCuentaCorrienteBean;
	}*/

	@SuppressWarnings("rawtypes")
	public void listadoFuentesFinancieras() {
		listFuentesFinancieras = evaluarService
				.findFuenteFinanciadorasByIdDatoProy(datoUsuario
						.getDatoProyectoID());
		listFuentesFinancierasFinal = new ArrayList<FuenteFinanciadoraBean>();
		// listInstitucionIDFinal=new ArrayList();
		FuenteFinanciadoraBean fuenteFinanciadoraBean;
		FuenteFinanciadora fuenteFinanciadora;
		for (Iterator iterador = listFuentesFinancieras.listIterator(); iterador
				.hasNext();) {
			fuenteFinanciadoraBean = new FuenteFinanciadoraBean();
			fuenteFinanciadora = new FuenteFinanciadora();
			fuenteFinanciadora = (FuenteFinanciadora) iterador.next();
			fuenteFinanciadoraBean.setFuenteFinanciadoraID(fuenteFinanciadora
					.getFuenteFinanciadoraID());
			fuenteFinanciadoraBean.setDefineSiEsEjecutor(fuenteFinanciadora
					.getDefineSiEsEjecutor().toString());
			fuenteFinanciadoraBean
					.setFkIdtablaespTipoFuenteFinanciadora(fuenteFinanciadora
							.getFkIdtablaespTipoFuenteFinanciadora());
			fuenteFinanciadoraBean.setMontoFinanciado(fuenteFinanciadora
					.getMontoFinanciado());
			fuenteFinanciadoraBean.setFkIdtablaespTipoMoneda(fuenteFinanciadora
					.getFkIdtablaespTipoMoneda());
			fuenteFinanciadoraBean.setDatoProyectoID(fuenteFinanciadora
					.getDatoProyecto().getDatoProyectoID());
			fuenteFinanciadoraBean.setInstitucionID(fuenteFinanciadora
					.getInstitucion().getInstitucionID());
			listFuentesFinancierasFinal.add(fuenteFinanciadoraBean);
			// listInstitucionIDFinal.add(fuenteFinanciadora.getInstitucion().getInstitucionID());
		}
	}

	@SuppressWarnings("rawtypes")
	public void listadoUbicacionProyectoFinal() {

		listUbicacionProyecto = evaluarService
				.findUbicacionProyectoByIdProyecto(datoUsuario
						.getDatoProyectoID());
		listUbicacionProyectoFinal = new ArrayList<UbicacionProyectoBean>();
		UbicacionProyectoBean ubicacionProyectoBean;
		UbicacionProyecto ubicacionProyecto;
		for (Iterator iterador = listUbicacionProyecto.listIterator(); iterador
				.hasNext();) {
			ubicacionProyectoBean = new UbicacionProyectoBean();
			ubicacionProyecto = new UbicacionProyecto();
			ubicacionProyecto = (UbicacionProyecto) iterador.next();
			ubicacionProyectoBean.setUbicacionProyectoID(ubicacionProyecto
					.getUbicacionProyectoID());
			ubicacionProyectoBean.setDetalleUbicacion(ubicacionProyecto
					.getDetalleUbicacion());
			ubicacionProyectoBean.setDatoProyectoID(ubicacionProyecto
					.getDatoProyecto().getDatoProyectoID());
			ubicacionProyectoBean.setDepProvDistID(ubicacionProyecto
					.getDepProvDist().getDepProvDistID());
			boolean flagReferencia = evaluarService
					.verificarSiUbicacionProyectoEstaReferenciado(ubicacionProyectoBean
							.getUbicacionProyectoID());
			ubicacionProyectoBean.setFlagReferencia(flagReferencia);
			listUbicacionProyectoFinal.add(ubicacionProyectoBean);
		}
	}

	@SuppressWarnings("rawtypes")
	public void listadoBeneficiariosPorResultadoFinal() {
		listBeneficiariosPorResultado = evaluarService
				.findBeneficiariosPorResultadoByIdPerfil(perfil.getPerfilID());
		listBeneficiariosPorResultado = evaluarService
				.verificarSiBeneficiarioEstaReferenciadoPorTablas(listBeneficiariosPorResultado);
		listBeneficiariosPorResultadoFinal = new ArrayList<BeneficiariosPorResultadoBean>();
		BeneficiariosPorResultadoBean beneficiariosPorResultadoBean;
		BeneficiariosPorResultado beneficiariosPorResultado;
		for (Iterator iterador = listBeneficiariosPorResultado.listIterator(); iterador
				.hasNext();) {
			beneficiariosPorResultadoBean = new BeneficiariosPorResultadoBean();
			beneficiariosPorResultado = new BeneficiariosPorResultado();
			beneficiariosPorResultado = (BeneficiariosPorResultado) iterador
					.next();
			beneficiariosPorResultadoBean
					.setBeneficiariosPorResultadoID(beneficiariosPorResultado
							.getBeneficiariosPorResultadoID());
			beneficiariosPorResultadoBean
					.setFkIdtablaespTipoBeneficiario(beneficiariosPorResultado
							.getFkIdtablaespTipoBeneficiario());
			beneficiariosPorResultadoBean
					.setCaracteristicasPoblacion(beneficiariosPorResultado
							.getCaracteristicasPoblacion());
			beneficiariosPorResultadoBean
					.setCantidadProgramado(beneficiariosPorResultado
							.getCantidadProgramado());
			beneficiariosPorResultadoBean
					.setFkidtablaespEstrato(beneficiariosPorResultado
							.getFkidtablaespEstrato());
			beneficiariosPorResultadoBean
					.setDescripcion(beneficiariosPorResultado.getDescripcion());
			beneficiariosPorResultadoBean.setPerfilID(beneficiariosPorResultado
					.getPerfil().getPerfilID());
			beneficiariosPorResultadoBean
					.setUbicacionProyectoID(beneficiariosPorResultado
							.getUbicacionProyecto().getUbicacionProyectoID());
			beneficiariosPorResultadoBean
					.setFlagReferencia(beneficiariosPorResultado
							.isFlagReferencia());
			listBeneficiariosPorResultadoFinal
					.add(beneficiariosPorResultadoBean);
		}
	}

	/*
	public List<ResumenProyectoBean> listadoResumenProyecto(
			Integer datoProyectoID) {
		List<ResumenProyecto> listResumenProyecto = registroPerfilService
				.findResumenProyectoByIdDatoProyByTablaGeneralId(
						datoProyectoID, 31);// 31 es tipo de resumen para
											// proyecto

		List<ResumenProyectoBean> listResumenProyectoBean = new ArrayList<ResumenProyectoBean>();
		for (ResumenProyecto resumenProyecto : listResumenProyecto) {
			ResumenProyectoBean resumenProyectoBean = new ResumenProyectoBean();
			resumenProyectoBean.setResumenProyectoID(resumenProyecto
					.getResumenProyectoID());
			resumenProyectoBean.setFkIdtablaespTipoResumenProy(resumenProyecto
					.getFkIdtablaespTipoResumenProy());
			resumenProyectoBean
					.setDescripcionTipoResumenProy(tablaEspecificaService
							.findTablaEspecificaById(
									resumenProyecto
											.getFkIdtablaespTipoResumenProy())
							.getDescripcionCabecera());
			resumenProyectoBean.setFkIdTablaGeneral(resumenProyecto
					.getFkIdTablaGeneral());
			resumenProyectoBean.setDefinicion(resumenProyecto.getDefinicion());
			resumenProyectoBean.setDatoProyectoID(resumenProyecto
					.getDatoProyecto().getDatoProyectoID());
			listResumenProyectoBean.add(resumenProyectoBean);
		}
		return listResumenProyectoBean;
	}
*/
}
