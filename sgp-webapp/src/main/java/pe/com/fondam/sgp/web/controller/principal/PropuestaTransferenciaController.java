package pe.com.fondam.sgp.web.controller.principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.bean.BeneficiariosPorResultadoBean;
import pe.com.fondam.sgp.core.bean.DirectivaBeneficiarioBean;
import pe.com.fondam.sgp.core.bean.ImagenOArchivoTempBean;
import pe.com.fondam.sgp.core.bean.OrgBienTranferidoBean;
import pe.com.fondam.sgp.core.bean.OrganizacionBean;
import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.Bien;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.DirectivaBeneficiario;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;
import pe.com.fondam.sgp.core.domain.OrgBienTranferido;
import pe.com.fondam.sgp.core.domain.Organizacion;
import pe.com.fondam.sgp.core.domain.PropuestaTransferencia;
import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBeneficiario;
import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBien;
import pe.com.fondam.sgp.core.domain.RecursoUtilizado;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.UbicacionProyecto;
import pe.com.fondam.sgp.core.service.ActivoService;
import pe.com.fondam.sgp.core.service.BeneficiariosPorResultadoService;
import pe.com.fondam.sgp.core.service.BienService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.DirectivaBeneficiarioService;
import pe.com.fondam.sgp.core.service.FuenteFinanciadoraService;
import pe.com.fondam.sgp.core.service.ImagenOArchivoService;
import pe.com.fondam.sgp.core.service.InformeFinalBienService;
import pe.com.fondam.sgp.core.service.LiquidacionGastoService;
import pe.com.fondam.sgp.core.service.OrgBienTranferidoService;
import pe.com.fondam.sgp.core.service.OrganizacionService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaBeneficiarioService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaBienService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaService;
import pe.com.fondam.sgp.core.service.RecursoUtilizadoService;
import pe.com.fondam.sgp.core.service.RegistroPerfilService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.UbicacionProyectoService;
import pe.com.fondam.sgp.web.InOutFiles.FileUploadDownload;
import pe.com.fondam.sgp.web.InOutFiles.LinkFile;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.controller.security.SecurityController;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class PropuestaTransferenciaController {

	// ********* inyecciones **************//
	@Resource
	private DetalleEstadoCabeceraService detalleEstadoCabeceraService;

	@Resource
	private TablaEspecificaService tablaEspecificaService;

	@Resource
	private PropuestaTransferenciaService propuestaTransferenciaService;

	@Resource
	private DatoProyectoService datoProyectoService;

	@Resource
	private DirectivaBeneficiarioService directivaBeneficiarioService;

	@Resource
	private UbicacionProyectoService ubicacionProyectoService;

	@Resource
	private BeneficiariosPorResultadoService beneficiariosPorResultadoService;

	@Resource
	private PropuestaTransferenciaBeneficiarioService propuestaTransferenciaBeneficiarioService;

	@Resource
	private OrganizacionService organizacionService;

	@Resource
	private LiquidacionGastoService liquidacionGastoService;

	@Resource
	private BienService bienService;

	@Resource
	private FuenteFinanciadoraService fuenteFinanciadoraService;

	@Resource
	private ActivoService activoService;

	@Resource
	PropuestaTransferenciaBienService propuestaTransferenciaBienService;

	@Resource
	RecursoUtilizadoService recursoUtilizadoService;

	@Resource
	OrgBienTranferidoService orgBienTranferidoService;

	@Resource
	ImagenOArchivoService imagenOArchivoService;
	
	@Resource
	RegistroPerfilService registroPerfilService;
	
	@Resource
	InformeFinalBienService informeFinalBienService;
	
	//************** variables globales **********************//
	FileUploadDownload fileUploadDownload = new FileUploadDownload();
	
	// ********* metodos **************//
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
	@RequestMapping(value = "/principal/showGestionarPropuestaTransferencia")
	public ModelAndView showGestionarPropuestaTransferencia(
			HttpServletRequest request, HttpServletResponse response) {

		request.getSession().removeAttribute("listRecursoUtilizado");

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		if (userSession == null) {
			return SecurityController.autenticateErrorRedirect(request);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect",
				"showGestionarPropuestaTransferencia.jspx");

		// creo la propuesta de transferencia
		PropuestaTransferencia propuestaTransferencia = propuestaTransferenciaService
				.findPropuestaTransferenciaByDatoProyectoId(userSession
						.getDatoProyectoID());
		if (propuestaTransferencia == null) {
			propuestaTransferencia = new PropuestaTransferencia();

			propuestaTransferencia.setDatoProyecto(datoProyectoService
					.findDatoProyectoById(userSession.getDatoProyectoID()));
			propuestaTransferencia
					.setFkIdDetalleEstadoCabEstInfPropTransfer(detalleEstadoCabeceraService
							.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraByPrefijoDetalleEstado(
									"estprtr", "elab")
							.getDetalleEstadoCabeceraID());

			propuestaTransferencia = propuestaTransferenciaService
					.updatePropuestaTransferencia(propuestaTransferencia);
		}

		model.put("propuestaTransferencia", propuestaTransferencia);
		model.put("estadoPropuestaTransferencia", detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(propuestaTransferencia.getFkIdDetalleEstadoCabEstInfPropTransfer()).getPrefijoEstado());
		
		ImagenOArchivo imagenOArchivo =imagenOArchivoService.findImagenOArchivoByPropuestaTransferenciaId(propuestaTransferencia.getPropuestaTransferenciaID());
		model.put("existeImagen",imagenOArchivo==null?0:1 );
		
		model.put("datoProyectoId", userSession.getDatoProyectoID());

		List<DetalleEstadoCabecera> listDetalleEstadoCabecera = detalleEstadoCabeceraService
				.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estprtr");
		List<DetalleEstadoCabecera> listDetalleEstadoCabeceraTemp = new ArrayList<DetalleEstadoCabecera>();
		for (DetalleEstadoCabecera detalleEstadoCabecera : listDetalleEstadoCabecera) {
			if ((detalleEstadoCabecera.getPrefijoEstado().equals("elab"))
					|| (detalleEstadoCabecera.getPrefijoEstado().equals("eval"))
					|| (detalleEstadoCabecera.getPrefijoEstado()
							.equals(detalleEstadoCabeceraService
									.findDetalleEstadoCabeceraById(
											propuestaTransferencia
													.getFkIdDetalleEstadoCabEstInfPropTransfer())
									.getPrefijoEstado()))) {
				listDetalleEstadoCabeceraTemp.add(detalleEstadoCabecera);
			}
		}
		model.put("listDetalleEstadoCabecera", listDetalleEstadoCabeceraTemp);

		model.put(
				"listCargo",
				tablaEspecificaService
						.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_CARGO));
		
		List<TablaEspecifica> listTipoDocumento=tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_TIPO_DOCUMENTO);
		List<TablaEspecifica> listTipoDocumentoTemp=new ArrayList<TablaEspecifica>();
		for (TablaEspecifica tablaEspecifica : listTipoDocumento) {
			if((tablaEspecifica.getTablaEspecificaID().equals(165))||(tablaEspecifica.getTablaEspecificaID().equals(166))){
				listTipoDocumentoTemp.add(tablaEspecifica);
			}
		}
		model.put(
				"listTipoDocumento",listTipoDocumentoTemp);
		model.put(
				"listTipoBien",
				tablaEspecificaService
						.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_TIPO_BIEN));
		model.put(
				"listEstadoConservacion",
				detalleEstadoCabeceraService
						.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estcons"));
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
		
		ImagenOArchivoTempBean imagenOArchivoTempBean= new ImagenOArchivoTempBean();
		model.put("imagenOArchivoTempBean",imagenOArchivoTempBean);
		
		model.put("cantMuestraMensajeObs",1);
		
		return new ModelAndView("divPropuestaTransferencia", model);
	}

	@RequestMapping(value = "/principal/grabarPropuestaTransferencia.jspx")
	public String grabarPropuestaTransferencia(
			@ModelAttribute("imagenOArchivoTempBean") ImagenOArchivoTempBean imagenOArchivoTempBean,
			HttpServletRequest request) {

		Integer datoProyectoID = Integer.parseInt(request
				.getParameter("datoProyectoId"));
		Integer propuestaTransferenciaId = Integer.parseInt(request
				.getParameter("propuestaTransferenciaId"));
		String resumenDescripcionTranferencia = request
				.getParameter("resumenDescripcionTranferencia");
		String resumenPlanTranferencia = request
				.getParameter("resumenPlanTransferencia");
		Integer sltEstadoPropuestaTransferencia = Integer.parseInt(request
				.getParameter("sltEstadoPropuestaTransferencia"));

		PropuestaTransferencia propuestaTransferencia = new PropuestaTransferencia();

		propuestaTransferencia.setDatoProyecto(datoProyectoService
				.findDatoProyectoById(datoProyectoID));
		propuestaTransferencia
				.setFkIdDetalleEstadoCabEstInfPropTransfer(sltEstadoPropuestaTransferencia);
		propuestaTransferencia
				.setPropuestaTransferenciaID(propuestaTransferenciaId);
		propuestaTransferencia
				.setResumenDescripTrans(resumenDescripcionTranferencia);
		propuestaTransferencia.setResumenPlanTrans(resumenPlanTranferencia);

		propuestaTransferenciaService
				.updatePropuestaTransferencia(propuestaTransferencia);

		//logger.info("-----GRABA O MODIFICA IMAGEN O ARCHIVO-----");
		if (!imagenOArchivoTempBean.getImagenODocumento().equals("") ) {
			String nombreArchivo= request.getParameter("nombreArchivo");
			String extencion= request.getParameter("extension");
			int codExtension =  tablaEspecificaService.findIdByDescripcionCabecera(extencion);// 234;
			
			if (codExtension != 0) {
				// Guardar el archivo en la base de datos
				ImagenOArchivo imagenOArchivo = imagenOArchivoService.findImagenOArchivoByPropuestaTransferenciaId(propuestaTransferenciaId);
				if ( imagenOArchivo!= null) {
					//imagenOArchivo = proyectoService.findProyetoByArchivoImagen(datoProyecto.getDatoProyectoID());
					imagenOArchivo.setDescripcionDocImg(nombreArchivo);
					imagenOArchivo.setFkIdtablaespTipoArchivo(codExtension);
					imagenOArchivo.setImagen(imagenOArchivoTempBean.getImagenODocumento());
					imagenOArchivoService
							.updateImagenOArchivo(imagenOArchivo);
				} else {
					imagenOArchivo= new ImagenOArchivo();
					imagenOArchivo.setDescripcionDocImg(nombreArchivo);
					imagenOArchivo.setFkIdtablaespTipoArchivo(codExtension);
					imagenOArchivo.setImagen(imagenOArchivoTempBean.getImagenODocumento());
					imagenOArchivo.setPropuestaTransferencia(propuestaTransferencia);
					imagenOArchivoService.updateImagenOArchivo(imagenOArchivo);
				}
			}
		}
		return "redirect:showGestionarPropuestaTransferencia.jspx";
	}

	@RequestMapping(value = "/principal/showImagenArchivoDownloadPropuestaTransferencia.jspx")
	public ModelAndView showImagenArchivoDownloadPropuestaTransferencia(
			@RequestParam(required = false, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<TablaEspecifica> tablaEspecificaListTipoFormatoArchivo = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(40);// listado de
															// extensiones
		List<LinkFile> listLinkFile = new ArrayList<LinkFile>();
		LinkFile linkFile = new LinkFile();
		ImagenOArchivo imagenOArchivo = null;
		//UserSession userSession = (UserSession) request.getSession().getAttribute(SgpWebConstants.SESSION_USER);
		//Usuario usuario = evaluarService.findUsuarioById(userSession.getUsuarioID());
		try {
			imagenOArchivo = imagenOArchivoService.findImagenOArchivoByPropuestaTransferenciaId(propuestaTransferenciaId);

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
		model.put("variable", "downloadImagenArchivoPropuestaTransferencia.jspx");
		return new ModelAndView("mostrarArchivoDownload", model);
	}

	@RequestMapping(value = "/principal/downloadImagenArchivoPropuestaTransferencia.jspx")
	public void downloadImagenArchivoPropuestaTransferencia(HttpServletRequest request,
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

	@RequestMapping(value = "/principal/cargaGrillaDirectivaBeneficiario")
	public ModelAndView cargaGrillaDirectivaBeneficiario(
			@RequestParam(required = false, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect",
				"showGestionarPropuestaTransferencia.jspx");

		// busco List<DirectivaBeneficiario>
		List<DirectivaBeneficiarioBean> listDirectivaBeneficiario =directivaBeneficiarioService.llenaListDirectivaBeneficiarioBean(directivaBeneficiarioService.llenaListDirectivaBeneficiario(directivaBeneficiarioService
				.findDirectivaBeneficiarioByPropuestaTransferenciaId(propuestaTransferenciaId)));
		model.put("listDirectivaBeneficiario", listDirectivaBeneficiario);
		return new ModelAndView("divGrillaDirectivaBeneficiario", model);
	}

	@RequestMapping(value = "/principal/grabarDirectivaBeneficiario")
	public void grabarDirectivaBeneficiario(
			@RequestParam(required = false, value = "nombreCompleto") String nombreCompleto,
			@RequestParam(required = false, value = "numeroDocumentoIdentidad") String numeroDocumentoIdentidad,
			@RequestParam(required = false, value = "sltTipoDocumento") Integer sltTipoDocumento,
			@RequestParam(required = false, value = "sltCargo") Integer sltCargo,
			@RequestParam(required = false, value = "acreditacion") String acreditacion,
			@RequestParam(required = false, value = "vigenciaPoderes") String vigenciaPoderes,
			@RequestParam(required = false, value = "directivaBeneficiarioId") Integer directivaBeneficiarioId,
			@RequestParam(required = false, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			HttpServletRequest request, HttpServletResponse response) {

		DirectivaBeneficiario directivaBeneficiario = new DirectivaBeneficiario();
		directivaBeneficiario.setAcreditacion(acreditacion);
		if (directivaBeneficiarioId != 0) {
			directivaBeneficiario
					.setDirectivaBeneficiarioID(directivaBeneficiarioId);
		}
		directivaBeneficiario.setFkIdtablaespCargo(sltCargo);
		directivaBeneficiario.setFkIdtablaespTipoDocumento(sltTipoDocumento);
		directivaBeneficiario.setNombreCompleto(nombreCompleto);
		directivaBeneficiario.setNumeroDocumento(numeroDocumentoIdentidad);
		directivaBeneficiario
				.setPropuestaTransferencia(propuestaTransferenciaService
						.findPropuestaTransferenciaById(propuestaTransferenciaId));
		directivaBeneficiario.setVigenciaPoder(vigenciaPoderes);

		directivaBeneficiarioService
				.updateDirectivaBeneficiario(directivaBeneficiario);
	}

	@RequestMapping(value = "/principal/cargaGrillaBeneficiarioPropuestaTransferencia")
	public ModelAndView cargaGrillaBeneficiarioPropuestaTransferencia(
			@RequestParam(required = false, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
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
		// model.put("reporteAvanceId", reporteAvanceId);

		return new ModelAndView("divGrillaBeneficiariosPropuestaTransferencia",
				model);
	}

	@RequestMapping(value = "/principal/cuerpoBeneficiariosResultadoLogradoPropuestaTransferencia")
	public ModelAndView cuerpoBeneficiariosResultadoLogradoPropuestaTransferencia(
			@RequestParam(required = true, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			@RequestParam(required = true, value = "beneficiarioPorResultadoId") Integer beneficiarioPorResultadoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("propuestaTransferenciaId", propuestaTransferenciaId);

		BeneficiariosPorResultadoBean beneficiariosPorResultadoBean = beneficiariosPorResultadoService
				.llenaBeneficiariosPorResultadoBean(beneficiariosPorResultadoService
						.findBeneficiariosPorResultadoById(beneficiarioPorResultadoId));
		model.put("beneficiariosPorResultadoBean",
				beneficiariosPorResultadoBean);

		return new ModelAndView(
				"divCuerpoBeneficiariosResultadoLogradoPropuestaTransferencia",
				model);
	}

	@RequestMapping(value = "/principal/grabarCantidadLogradaPropuestaTransferencia")
	public void grabarCantidadLogradaPropuestaTransferencia(
			@RequestParam(required = true, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			@RequestParam(required = true, value = "beneficiariosPorResultadoID") Integer beneficiarioPorResultadoId,
			@RequestParam(required = true, value = "cantidadFinal") Integer cantidadFinal,
			HttpServletRequest request, HttpServletResponse response) {

		// Map<String, Object> model = new HashMap<String, Object>();

		PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiario = new PropuestaTransferenciaBeneficiario();

		propuestaTransferenciaBeneficiario
				.setBeneficiariosPorResultado(beneficiariosPorResultadoService
						.findBeneficiariosPorResultadoById(beneficiarioPorResultadoId));
		propuestaTransferenciaBeneficiario.setCantidadFinal(cantidadFinal);
		propuestaTransferenciaBeneficiario
				.setPropuestaTransferencia(propuestaTransferenciaService
						.findPropuestaTransferenciaById(propuestaTransferenciaId));

		propuestaTransferenciaBeneficiarioService
				.savePropuestaTransferenciaBeneficiario(propuestaTransferenciaBeneficiario);
		// .saveReporteAvanceBeneficiario(reporteAvanceBeneficiario);
	}

	@RequestMapping(value = "/principal/cargaGrillaOrganizacion")
	public ModelAndView cargaGrillaOrganizacion(
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect",
				"showGestionarPropuestaTransferencia.jspx");

		// busco List<Organizacion>
		List<OrganizacionBean> listOrganizacionBean = organizacionService.llenaListOrganizacionBean(organizacionService
				.findOrganizacionByDatoProyectoId(datoProyectoId));
		model.put("listOrganizacion", listOrganizacionBean);
		return new ModelAndView("divGrillaOrganizacion", model);
	}

	@RequestMapping(value = "/principal/grabarOrganizacion")
	public void grabarOrganizacion(
			@RequestParam(required = false, value = "nombreOrganizacion") String nombreOrganizacion,
			@RequestParam(required = false, value = "situacionFinal") String situacionFinal,
			@RequestParam(required = false, value = "organizacionId") Integer organizacionId,
			@RequestParam(required = false, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Organizacion organizacion = new Organizacion();
		organizacion.setNombreOrganizacion(nombreOrganizacion);
		organizacion.setSituacionFinal(situacionFinal);
		if (organizacionId != 0) {
			organizacion.setOrganizacionID(organizacionId);
		}
		organizacion.setPropuestaTransferencia(propuestaTransferenciaService
				.findPropuestaTransferenciaById(propuestaTransferenciaId));
		organizacion.setDatoProyecto(datoProyectoService.findDatoProyectoById(datoProyectoId));

		organizacionService.updateOrganizacion(organizacion);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/cargaGrillaRecursoUtilizado")
	public ModelAndView cargaGrillaRecursoUtilizado(
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
		return new ModelAndView("divGrillaRecursoUtilizado", model);
	}

	@RequestMapping(value = "/principal/cargaGrillaModificarRecursoUtilizado.jspx")
	public ModelAndView cargaGrillaModificarRecursoUtilizado(
			@RequestParam(required = false, value = "bienID") Integer bienID,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		
		List<RecursoUtilizado> listRecursoUtilizado = recursoUtilizadoService.findRecursoUtilizadoByBienId(bienID);
		
				model.put("cantRecursoUtilizado", listRecursoUtilizado.size());
				request.getSession().setAttribute("listRecursoUtilizado",
						listRecursoUtilizado);
			
		model.put("listRecursoUtilizado", listRecursoUtilizado);
		return new ModelAndView("divGrillaRecursoUtilizado", model);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/eliminarRecursoUtilizado.jspx")
	public void eliminarRecursoUtilizado(
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

	@RequestMapping(value = "/principal/cargaGrillaBien")
	public ModelAndView cargaGrillaBien(
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect",
				"showGestionarPropuestaTransferencia.jspx");

		// busco List<Organizacion>
		List<Bien> listBien = bienService
				.findBienByDatoProyectoId(datoProyectoId);
		model.put("listBien", bienService.llenaListBien(listBien));
		return new ModelAndView("divGrillaBien", model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/grabarBien")
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
			@RequestParam(required = false, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
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

		PropuestaTransferenciaBien propuestaTransferenciaBien = propuestaTransferenciaBienService
				.findPropuestaTransferenciaBienByPropuestaTransferenciaIdByBienId(
						propuestaTransferenciaId, bien.getBienID());
		if (propuestaTransferenciaBien == null) {
			PropuestaTransferenciaBien propuestaTransferenciaBienSave = new PropuestaTransferenciaBien();
			propuestaTransferenciaBienSave.setBien(bien);
			propuestaTransferenciaBienSave
					.setPropuestaTransferencia(propuestaTransferenciaService
							.findPropuestaTransferenciaById(propuestaTransferenciaId));
			// propuestaTransferenciaBien.setPropuestaTransferenciaBienID(propuestaTransferenciaBienID);
			propuestaTransferenciaBienService
					.updatePropuestaTransferenciaBien(propuestaTransferenciaBienSave);
		}

		List<RecursoUtilizado> listRecursoUtilizado = (List<RecursoUtilizado>) request
				.getSession().getAttribute("listRecursoUtilizado");

		for (RecursoUtilizado recursoUtilizado : listRecursoUtilizado) {
			recursoUtilizado.setBien(bien);
			recursoUtilizadoService.updateRecursoUtilizado(recursoUtilizado);

		}
		request.getSession().removeAttribute("listRecursoUtilizado");
	}

	@RequestMapping(value = "/principal/eliminarBien.jspx")
	public void eliminarBien(
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
	
	@RequestMapping(value = "/principal/grabarBienTransferido")
	public void grabarBienTransferido(
			@RequestParam(required = false, value = "sltOrganizacionBienTrnasferido") Integer organizacionBienTrnasferido,
			@RequestParam(required = false, value = "sltBienBienTransferido") Integer bienBienTransferido,
			@RequestParam(required = false, value = "sltBeneficiarioBienTransferido") Integer beneficiarioBienTransferido,
			@RequestParam(required = false, value = "organizacionBienTranferidoId") Integer orgBienTranferidoId,
			@RequestParam(required = false, value = "observacionBienTransferido") String observacionBienTransferido, 
			HttpServletRequest request, HttpServletResponse response) {

		OrgBienTranferido orgBienTranferido = new OrgBienTranferido();
		if (orgBienTranferidoId != 0) {
			orgBienTranferido.setOrgBienTranferidoID(orgBienTranferidoId);
		}
		orgBienTranferido.setOrganizacion(organizacionService
				.findOrganizacionById(organizacionBienTrnasferido));
		orgBienTranferido
				.setPropuestaTransferenciaBeneficiario(propuestaTransferenciaBeneficiarioService
						.findPropuestaTransferenciaBeneficiarioById(beneficiarioBienTransferido));
		orgBienTranferido
				.setPropuestaTransferenciaBien(propuestaTransferenciaBienService
						.findPropuestaTransferenciaBienById(bienBienTransferido));
		orgBienTranferido.setObservaciones(observacionBienTransferido);

		orgBienTranferido = orgBienTranferidoService
				.updateOrgBienTranferido(orgBienTranferido);

	}

	@RequestMapping(value = "/principal/cargaGrillaBienTransferido")
	public ModelAndView cargaGrillaBienTransferido(
			@RequestParam(required = false, value = "datoProyectoId") Integer datoProyectoId,
			@RequestParam(required = false, value = "propuestaTransferenciaId") Integer propuestaTransferenciaId,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("funcionalidadSelect",
				"showGestionarPropuestaTransferencia.jspx");

		// busco List<Organizacion>
		List<OrgBienTranferidoBean> listOrgBienTranferidoBean =orgBienTranferidoService.llenaListOrgBienTranferidoBean(orgBienTranferidoService.llenaListOrgBienTranferido( orgBienTranferidoService
				.findOrgBienTranferidoByPropuestaTransferenciaId(propuestaTransferenciaId)));
		model.put("listOrgBienTranferido",listOrgBienTranferidoBean);
		return new ModelAndView("divGrillaBienTranferido", model);
	}

	@RequestMapping(value = "/principal/eliminarRegistroPropuestaTransferencia")
	public void eliminarRegistroPropuestaTransferencia(
			@RequestParam(required = false, value = "idRegistro") Integer idRegistro,
			@RequestParam(required = false, value = "tablaOpcion") String tablaOpcion,
			HttpServletRequest request, HttpServletResponse response) {

		if(tablaOpcion.equals("directivaBeneficiario")){
			directivaBeneficiarioService.deleteDirectivaBeneficiario(idRegistro);
		}else if(tablaOpcion.equals("organizacion")){
			organizacionService.deleteOrganizacion(idRegistro);
		}else if(tablaOpcion.equals("orgBienTranferido")){
			orgBienTranferidoService.deleteOrgBienTranferido(idRegistro);
		}else if(tablaOpcion.equals("bien")){
			bienService.deleteBien(idRegistro);
		}
	}

}
