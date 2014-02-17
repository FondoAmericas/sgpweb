package pe.com.fondam.sgp.web.controller.principal;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.bean.ImagenOArchivoTempBean;
import pe.com.fondam.sgp.core.domain.ActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.DepProvDist;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.PuntajeEvaluacion;
import pe.com.fondam.sgp.core.domain.RestriccionDepProvDist;
import pe.com.fondam.sgp.core.domain.RestriccionSubAreaTematica;
import pe.com.fondam.sgp.core.domain.RestricionPrograma;
import pe.com.fondam.sgp.core.domain.SubAreaTematica;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.TipoActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.TipoPeriodo;
import pe.com.fondam.sgp.core.service.CrearProgramaService;
import pe.com.fondam.sgp.core.service.EvaluarService;
import pe.com.fondam.sgp.core.service.InstalarComiteTecnicoService;
import pe.com.fondam.sgp.core.service.RegistroPerfilService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.web.InOutFiles.FileUploadDownload;
import pe.com.fondam.sgp.web.InOutFiles.LinkFile;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.controller.security.SecurityController;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class ProgramaController {

	//************ inyecciones *****************//
	protected final Log logger = LogFactory.getLog(ProgramaController.class);
	
	@Resource
	CrearProgramaService crearProgramaService;

	@Resource
	TablaEspecificaService tablaEspecificaService;

	@Resource
	EvaluarService evaluarService;

	@Resource
	InstalarComiteTecnicoService instalarComiteTecnicoService;

	@Resource
	RegistroPerfilService registroPerfilService;

	FileUploadDownload fileUploadDownload = new FileUploadDownload();

	// ProgramaBean programa;
	PuntajeEvaluacion puntajeEvaluacion;
	List<TipoPeriodo> listTipoPeriodos;
	List<TablaEspecifica> listTipoCuentas;
	List<TablaEspecifica> listModalidadFinanciera;
	List<TablaEspecifica> listTipoRestriccion;
	List<TablaEspecifica> listResultadoActividadObligatoria;
	List<Programa> listPrograma;
	List<SubAreaTematica> listSubAreaTematica;
	List<TablaEspecifica> tablaEspecificaListTipoFormatoArchivo;
	List<DepProvDist> listDepartamento;

	//************ metodos *******************//
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
	// carga todos los combos de CrearPrograma.jsp
	void cargaCombos() {
		listTipoPeriodos = crearProgramaService.listTipoPeriodos();
		listTipoCuentas = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(1);
		listModalidadFinanciera = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(2);
		listTipoRestriccion = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(28);
		listResultadoActividadObligatoria = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(29);
		listPrograma = crearProgramaService.listProgramas();
		listSubAreaTematica = crearProgramaService
				.findSubAreaTematicaByDescripcion();
		listDepartamento = registroPerfilService.findDepProvDistritos(1, "0",
				"0", "0");
	}

	/*
	@RequestMapping(value = "/principal/showArchivoUploadPrograma.jspx")
	public ModelAndView showArchivoUploadPrograma(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String peticion = "archivoUploadPrograma.jspx";
		String archivoResp = "false";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("peticion", peticion);
		model.put("archivoResp", archivoResp);
		return new ModelAndView("mostrarArchivoUpload", model);
	}

	@RequestMapping(value = "/principal/archivoUploadPrograma.jspx")
	public ModelAndView archivoUploadPrograma(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		fileUploadDownload.archivoUpload(request, response);
		String archivoResp = "true";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("archivoResp", archivoResp);
		model.put("nombreArchivoUP", fileUploadDownload.getNombreArchivo()
				+ fileUploadDownload.getExtension());

		return new ModelAndView("mostrarArchivoUpload", model);
	}
*/
	
	@RequestMapping(value = "/principal/showImagenArchivoDownloadPrograma.jspx")
	public ModelAndView showImagenArchivoDownloadPrograma(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		tablaEspecificaListTipoFormatoArchivo = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(40);// listado de
															// extensiones
		List<LinkFile> listLinkFile = new ArrayList<LinkFile>();
		LinkFile linkFile = new LinkFile();
		ImagenOArchivo imagenOArchivo = null;
		try {
			imagenOArchivo = crearProgramaService
					.findProgramaByArchivoImagen(Integer
							.parseInt(request.getSession()
									.getAttribute("programaID").toString()));// listado
																				// de
																				// archivos
																				// de
																				// acuerdo
																				// a
																				// un
																				// perfil

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
		model.put("variable", "downloadImagenArchivoPrograma.jspx");
		return new ModelAndView("mostrarArchivoDownload", model);
	}

	@RequestMapping(value = "/principal/downloadImagenArchivoPrograma.jspx")
	public void downloadImagenArchivoPrograma(HttpServletRequest request,
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

	@RequestMapping(value = "/principal/showPrograma.jspx")
	public ModelAndView showPrograma(HttpServletRequest request) {

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		if (userSession == null) {
			return SecurityController.autenticateErrorRedirect(request);
		}
		request.getSession().removeAttribute("listRestriccionGrilla");
		request.getSession().removeAttribute("listActividadObligatoriaGrilla");
		request.getSession().removeAttribute("programaID");
		cargaCombos();

		request.getSession().setAttribute("listPrograma", listPrograma);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("listTipoRestriccion", listTipoRestriccion);
		model.put("listResultadoActividadObligatoria",
				listResultadoActividadObligatoria);
		model.put("listSubAreaTematica", listSubAreaTematica);
		model.put("funcionalidadSelect", "showPrograma.jspx");
		
		ImagenOArchivoTempBean imagenOArchivoTempBean= new ImagenOArchivoTempBean();
		model.put("imagenOArchivoTempBean",imagenOArchivoTempBean);
		
		return new ModelAndView("mostrarCrearPrograma", model);
	}

	@RequestMapping(value = "/principal/actionBuscarCrearPrograma")
	public ModelAndView actionBuscarCrearPrograma(HttpServletRequest request) {
		request.getSession().removeAttribute("listPrograma");

		Programa programa = new Programa();
		Integer idTipoCuentas = Integer.parseInt(request.getParameter(
				"idTipoCuenta").toString());
		Integer idModalidadFinanciera = Integer.parseInt(request.getParameter(
				"idModFinanciera").toString());
		Integer idTipoPeriodos = Integer.parseInt(request.getParameter(
				"idTipoPeriodo").toString());
		Integer nombre = Integer.parseInt(request.getParameter("idFiltro"));
		String nombrePrograma = request.getParameter("buscarNombre");
		Integer todosProgramas = Integer.parseInt(request
				.getParameter("idFiltro"));
		int tipoPeriodo = 100;

		int tipoCuenta = 101;
		int Modalidad = 102;

		if (idTipoPeriodos != 0) {
			TipoPeriodo tipPeriodo = new TipoPeriodo();
			tipPeriodo.setTipoPeriodoID(idTipoPeriodos);
			programa.setTipoPeriodo(tipPeriodo);
			listPrograma = instalarComiteTecnicoService.findProgramaByFiltro(
					programa, tipoPeriodo);
		} else if (idTipoCuentas != 0) {
			programa.setFkIdtablaespTipoCuenta(idTipoCuentas);
			listPrograma = instalarComiteTecnicoService.findProgramaByFiltro(
					programa, tipoCuenta);
		} else if (idModalidadFinanciera != 0) {
			programa.setFkIdtablaespModalidadFinancia(idModalidadFinanciera);
			listPrograma = instalarComiteTecnicoService.findProgramaByFiltro(
					programa, Modalidad);
		} else if (nombre == 103) {
			programa.setNombrePrograma(nombrePrograma);
			listPrograma = instalarComiteTecnicoService.findProgramaByFiltro(
					programa, nombre);
		} else if (todosProgramas == 104) {
			listPrograma = crearProgramaService.listProgramas();
		}
		request.getSession().setAttribute("listPrograma", listPrograma);
		listTipoPeriodos = crearProgramaService.listTipoPeriodos();
		listTipoCuentas = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(1);
		listModalidadFinanciera = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(2);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("listDepartamento", listDepartamento);
		
		ImagenOArchivoTempBean imagenOArchivoTempBean= new ImagenOArchivoTempBean();
		model.put("imagenOArchivoTempBean",imagenOArchivoTempBean);
		
		return new ModelAndView("mostrarCrearPrograma", model);
	}

	@RequestMapping(value = "/principal/actionCrearPrograma", method = RequestMethod.POST)
	public ModelAndView actionCrearPrograma(HttpServletRequest request)
			throws Exception {

		request.getSession().removeAttribute("listRestriccionGrilla");
		request.getSession().removeAttribute("listActividadObligatoriaGrilla");
		request.getSession().removeAttribute("programaID");

		String nombrePrograma = request.getParameter("nombrePrograma");
		String identificadorModFinan = request
				.getParameter("identificadorModFinan");
		Integer idTipoCuentas = Integer.parseInt(request.getParameter(
				"idTipoCuentas").toString());
		Integer idModalidadFinanciera = Integer.parseInt(request
				.getParameter("idModalidadFinanciera"));
		Integer idTipoPeriodos = Integer.parseInt(request
				.getParameter("idTipoPeriodos"));
		String fechaConvocatoria = request.getParameter("fechaConvocatoria");
		Integer duracionPrograma = Integer.parseInt(request.getParameter(
				"duracionPrograma").toString());

		//logger.info(fechaConvocatoria);
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

		Date fecha = formateador.parse(fechaConvocatoria);

		Programa programa = new Programa();
		programa.setNombrePrograma(nombrePrograma);
		programa.setFkIdtablaespModalidadFinancia(idModalidadFinanciera);
		programa.setFkIdtablaespTipoCuenta(idTipoCuentas);
		programa.setDuracionPrograma(duracionPrograma);
		programa.setFechaConvocatoria(fecha);
		programa.setIdentificadorModFinan(identificadorModFinan);
		TipoPeriodo tipo = new TipoPeriodo();
		tipo.setTipoPeriodoID(idTipoPeriodos);
		programa.setTipoPeriodo(tipo);

		cargaCombos();
		
		Map<String, Object> model = new HashMap<String, Object>();
		Integer programaID = crearProgramaService.savePrograma(programa);
		request.getSession().setAttribute("programaID", programaID.toString());
		request.getSession().setAttribute("listDatoProyecto", null);

		model.put("programaID", programaID.toString());
				
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("listTipoRestriccion", listTipoRestriccion);
		model.put("listResultadoActividadObligatoria",
				listResultadoActividadObligatoria);
		model.put("nombrePrograma", nombrePrograma);
		model.put("identificadorModFinan", identificadorModFinan);
		model.put("fechaConvocatoria", fechaConvocatoria);
		model.put("duracionPrograma", duracionPrograma);
		model.put("idTipoCuentas", idTipoCuentas);
		model.put("idModalidadFinanciera", idModalidadFinanciera);
		model.put("idTipoPeriodos", idTipoPeriodos);
		model.put("listPrograma", listPrograma);
		model.put("variable", "crear");
		model.put("listSubAreaTematica", listSubAreaTematica);
		model.put("listDepartamento", listDepartamento);
		
		ImagenOArchivoTempBean imagenOArchivoTempBean= new ImagenOArchivoTempBean();
		model.put("imagenOArchivoTempBean",imagenOArchivoTempBean);
		
		return new ModelAndView("mostrarCrearPrograma", model);
	}

	@RequestMapping(value = "/principal/actionModificarPrograma", method = RequestMethod.POST)
	public void actionModificarPrograma(HttpServletRequest request,
			HttpServletResponse resp) throws ParseException, IOException {
		//logger.info("Mofique el programa:::::");
		String nombrePrograma = request.getParameter("nombrePrograma");
		String identificadorModFinan = request.getParameter("identificadorModFinan");
		Integer idTipoCuentas = Integer.parseInt(request.getParameter("idTipoCuentas").toString());

		Integer idModalidadFinanciera = Integer.parseInt(request
				.getParameter("idModalidadFinanciera"));
		Integer idTipoPeriodos = Integer.parseInt(request
				.getParameter("idTipoPeriodos"));
		String fechaConvocatoria = request.getParameter("fechaConvocatoria");
		Integer duracionPrograma = Integer.parseInt(request.getParameter(
				"duracionPrograma").toString());
		Date fecha;
		SimpleDateFormat formateador = null;
		try {
			formateador = new SimpleDateFormat("dd/MM/yyyy");
			fecha = (Date) formateador.parse(fechaConvocatoria);
		} catch (Exception e) {
			formateador = new SimpleDateFormat("dd/MM/yyyy");
			fecha = (Date) formateador.parse(fechaConvocatoria);
		}

		Integer idProg = Integer.parseInt(request.getSession()
				.getAttribute("programaID").toString());
		Programa programa = new Programa();
		programa = crearProgramaService.findProgramaById(idProg);
		programa.setNombrePrograma(nombrePrograma);
		programa.setFkIdtablaespModalidadFinancia(idModalidadFinanciera);
		programa.setFkIdtablaespTipoCuenta(idTipoCuentas);
		programa.setDuracionPrograma(duracionPrograma);
		programa.setFechaConvocatoria(fecha);
		programa.setIdentificadorModFinan(identificadorModFinan);
		TipoPeriodo tipo = new TipoPeriodo();
		tipo.setTipoPeriodoID(idTipoPeriodos);
		programa.setTipoPeriodo(tipo);
		List<DatoProyecto> listaDatoProyectos = evaluarService
				.findDatoProyectoByProgramaID(idProg);
		request.getSession().setAttribute("listDatoProyecto",
				listaDatoProyectos);

		crearProgramaService.updatePrograma(programa);
		resp.getWriter().write(
				"<label>El programa fue exitosamente modificado</label>");
	}

	@RequestMapping(value = "/principal/actionCargarModificarDatosPrograma")
	public ModelAndView actionCargarModificarDatosPrograma(
			//@RequestParam(required = false, value = "variable") Integer idPrograma,
			HttpServletRequest request) throws Exception {
		
		request.getSession().removeAttribute("listRestriccionGrilla");
		request.getSession().removeAttribute("listActividadObligatoriaGrilla");
		request.getSession().removeAttribute("programaID");
		request.getSession().removeAttribute("listRestriccionEliminar");
		request.getSession()
				.removeAttribute("listActividadObligatoriaEliminar");
		Integer idPrograma = Integer.parseInt(request.getParameter("idPrograma").toString());
		Programa programa = crearProgramaService.findProgramaById(idPrograma);
		List<DatoProyecto> listaDatoProyectos = evaluarService.findDatoProyectoByProgramaID(idPrograma);
		
		// datos del programa
		String nombrePrograma = programa.getNombrePrograma();
		String identificadorModFinan = programa.getIdentificadorModFinan();
		Integer idTipoCuentas = programa.getFkIdtablaespTipoCuenta();
		Integer idModalidadFinanciera = programa
				.getFkIdtablaespModalidadFinancia();
		Integer idTipoPeriodos = programa.getTipoPeriodo().getTipoPeriodoID();
		Date fechaConvocatoria = programa.getFechaConvocatoria();
		SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		String fechaConvocatoriaS = fecha.format(fechaConvocatoria);
		Integer duracionPrograma = programa.getDuracionPrograma();
		int chkRqPYG = 0;
		if (programa.getReqProyGrobal() == 1) {
			chkRqPYG = 1;
		}

		// Puntaje
		int chkRqEI = 0;
		Integer puntajeMinRqEI = 0;
		Integer puntajeMaxRqEI = 0;
		Integer minimoAprobatorioEI = 0;
		int chkRqET = 0;
		Integer puntajeMinRqET = 0;
		Integer puntajeMaxRqET = 0;
		Integer minimoAprobatorioET = 0;
		int chkRqPY = 0;
		Integer puntajeMinRqPY = 0;
		Integer puntajeMaxRqPY = 0;
		Integer minimoAprobatorioPY = 0;
		List<PuntajeEvaluacion> ListPuntajeE = crearProgramaService
				.findPuntajeEvaluacionByProgramaID(Integer.parseInt(programa
						.getProgramaID().toString()));
		for (int i = 0; i < ListPuntajeE.size(); i++) {
			if (ListPuntajeE.get(i).getFkIdtablaespTipoEvaluacion() == 173) {
				chkRqEI = 1;
				puntajeMinRqEI = ListPuntajeE.get(i).getMinimo();
				puntajeMaxRqEI = ListPuntajeE.get(i).getMaximo();
				minimoAprobatorioEI = ListPuntajeE.get(i)
						.getMinimoAprobatorio();
			} else if (ListPuntajeE.get(i).getFkIdtablaespTipoEvaluacion() == 174) {
				chkRqET = 1;
				puntajeMinRqET = ListPuntajeE.get(i).getMinimo();
				puntajeMaxRqET = ListPuntajeE.get(i).getMaximo();
				minimoAprobatorioET = ListPuntajeE.get(i)
						.getMinimoAprobatorio();
			} else if (ListPuntajeE.get(i).getFkIdtablaespTipoEvaluacion() == 175) {
				chkRqPY = 1;
				puntajeMinRqPY = ListPuntajeE.get(i).getMinimo();
				puntajeMaxRqPY = ListPuntajeE.get(i).getMaximo();
				minimoAprobatorioPY = ListPuntajeE.get(i)
						.getMinimoAprobatorio();

			}
		}

		cargaCombos();
		request.getSession().setAttribute("programaID",
				programa.getProgramaID());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("listTipoRestriccion", listTipoRestriccion);
		model.put("listResultadoActividadObligatoria",
				listResultadoActividadObligatoria);
		model.put("nombrePrograma", nombrePrograma);
		model.put("identificadorModFinan", identificadorModFinan);
		model.put("fechaConvocatoria", fechaConvocatoriaS);
		model.put("duracionPrograma", duracionPrograma);
		model.put("idTipoCuentas", idTipoCuentas);
		model.put("idModalidadFinanciera", idModalidadFinanciera);
		model.put("idTipoPeriodos", idTipoPeriodos);
		model.put("listPrograma", listPrograma);
		// model.put("variable","cargaActualizar");
		model.put("chkRqPYG", chkRqPYG);
		model.put("chkRqEI", chkRqEI);
		model.put("puntajeMinRqEI", puntajeMinRqEI);
		model.put("puntajeMaxRqEI", puntajeMaxRqEI);
		model.put("minimoAprobatorioEI", minimoAprobatorioEI);
		model.put("chkRqET", chkRqET);
		model.put("puntajeMinRqET", puntajeMinRqET);
		model.put("puntajeMaxRqET", puntajeMaxRqET);
		model.put("minimoAprobatorioET", minimoAprobatorioET);
		model.put("chkRqPY", chkRqPY);
		model.put("puntajeMinRqPY", puntajeMinRqPY);
		model.put("puntajeMaxRqPY", puntajeMaxRqPY);
		model.put("minimoAprobatorioPY", minimoAprobatorioPY);
		model.put("variable", "actualizar");
		model.put("listDepartamento", listDepartamento);
		model.put("listDatoProyecto",listaDatoProyectos);

		ImagenOArchivoTempBean imagenOArchivoTempBean= new ImagenOArchivoTempBean();
		model.put("imagenOArchivoTempBean",imagenOArchivoTempBean);
		
		return new ModelAndView("mostrarCrearPrograma", model);
	}

	@SuppressWarnings({ "unchecked"})
	@RequestMapping(value = "/principal/actionModificarDatosPrograma", method = RequestMethod.POST)
	public ModelAndView actionModificarDatosPrograma(
			@ModelAttribute("imagenOArchivoTempBean") ImagenOArchivoTempBean imagenOArchivoTempBean,
			HttpServletRequest request)
			throws Exception {

		// 173
		PuntajeEvaluacion puntajeEvaluacionEI = new PuntajeEvaluacion();
		Integer puntajeMinRqEI = 0;
		Integer puntajeMaxRqEI = 0;
		Integer minimoAprobatorioEI = 0;
		// 174
		PuntajeEvaluacion puntajeEvaluacionET = new PuntajeEvaluacion();

		Integer puntajeMinRqET = 0;
		Integer puntajeMaxRqET = 0;
		Integer minimoAprobatorioET = 0;
		// 175
		PuntajeEvaluacion puntajeEvaluacionPY = new PuntajeEvaluacion();
		Integer puntajeMinRqPY = 0;
		Integer puntajeMaxRqPY = 0;
		Integer minimoAprobatorioPY = 0;

		String chkRqPYG = request.getParameter("chkRqPYG");
		String chkRqEI = request.getParameter("chkRqEI");
		String chkRqET = request.getParameter("chkRqET");
		String chkRqPY = request.getParameter("chkRqPY");

		Programa programa = new Programa();
		Integer id = Integer.parseInt(request.getSession()
				.getAttribute("programaID").toString());
		//System.out.println("ID del programa para modificar:" + id);
		programa = crearProgramaService.findProgramaById(id);

		List<PuntajeEvaluacion> listPuntaje = crearProgramaService
				.findPuntajeEvaluacionByProgramaID(programa.getProgramaID());
		if (chkRqPYG != null) {
			programa.setReqProyGrobal(1);
		} else {
			programa.setReqProyGrobal(0);
		}

		if (chkRqEI != null) {
			puntajeMinRqEI = Integer.parseInt(request.getParameter(
					"puntajeMinRqEI").toString());
			puntajeMaxRqEI = Integer.parseInt(request.getParameter(
					"puntajeMaxRqEI").toString());
			minimoAprobatorioEI = Integer.parseInt(request.getParameter(
					"minimoAprobatorioEI").toString());
		}
		if (chkRqET != null) {
			puntajeMinRqET = Integer.parseInt(request.getParameter(
					"puntajeMinRqET").toString());
			puntajeMaxRqET = Integer.parseInt(request.getParameter(
					"puntajeMaxRqET").toString());
			minimoAprobatorioET = Integer.parseInt(request.getParameter(
					"minimoAprobatorioET").toString());
		}
		if (chkRqPY != null) {
			puntajeMinRqPY = Integer.parseInt(request.getParameter(
					"puntajeMinRqPY").toString());
			puntajeMaxRqPY = Integer.parseInt(request.getParameter(
					"puntajeMaxRqPY").toString());
			minimoAprobatorioPY = Integer.parseInt(request.getParameter(
					"minimoAprobatorioPY").toString());
		}
		if (listPuntaje != null) {

			// 173 E.institucional+
			for (int i = 0; i < listPuntaje.size(); i++) {
				// 173
				if (listPuntaje.get(i).getFkIdtablaespTipoEvaluacion() == 173) {
					puntajeEvaluacionEI = listPuntaje.get(i);
					puntajeEvaluacionEI.setMaximo(puntajeMaxRqEI);
					puntajeEvaluacionEI.setMinimo(puntajeMinRqEI);
					puntajeEvaluacionEI
							.setMinimoAprobatorio(minimoAprobatorioEI);

					if (chkRqEI != null) {
						crearProgramaService
								.updatePuntajeEvaluacion(puntajeEvaluacionEI);
					} else {
						programa.setReqEvalInst(0);
						crearProgramaService
								.deletePuntajeEvaluacion(puntajeEvaluacionEI);
					}
				}
				// 174
				if (listPuntaje.get(i).getFkIdtablaespTipoEvaluacion() == 174) {
					puntajeEvaluacionET = listPuntaje.get(i);
					puntajeEvaluacionET.setFkIdtablaespTipoEvaluacion(174);
					puntajeEvaluacionET.setMaximo(puntajeMaxRqET);
					puntajeEvaluacionET.setMinimo(puntajeMinRqET);
					puntajeEvaluacionET
							.setMinimoAprobatorio(minimoAprobatorioET);
					if (chkRqET != null) {
						crearProgramaService
								.updatePuntajeEvaluacion(puntajeEvaluacionET);
					} else {
						programa.setReqEvalTec(0);
						crearProgramaService
								.deletePuntajeEvaluacion(puntajeEvaluacionET);
					}
				}

				// 175
				if (listPuntaje.get(i).getFkIdtablaespTipoEvaluacion() == 175) {

					puntajeEvaluacionPY = listPuntaje.get(i);
					puntajeEvaluacionPY.setFkIdtablaespTipoEvaluacion(175);
					puntajeEvaluacionPY.setMaximo(puntajeMaxRqPY);
					puntajeEvaluacionPY.setMinimo(puntajeMinRqPY);
					puntajeEvaluacionPY
							.setMinimoAprobatorio(minimoAprobatorioPY);
					if (chkRqPY != null) {
						crearProgramaService
								.updatePuntajeEvaluacion(puntajeEvaluacionPY);
					} else {
						programa.setReqEvalTec(0);
						crearProgramaService
								.deletePuntajeEvaluacion(puntajeEvaluacionPY);
					}
				}
			}
		}
		if (chkRqEI != null && programa.getReqEvalInst() == 0) {
			programa.setReqEvalInst(1);
			puntajeEvaluacionEI.setPuntajeEvaluacionID(null);
			puntajeEvaluacionEI.setMaximo(puntajeMaxRqEI);
			puntajeEvaluacionEI.setMinimo(puntajeMinRqEI);
			puntajeEvaluacionEI.setMinimoAprobatorio(minimoAprobatorioEI);
			puntajeEvaluacionEI.setFkIdtablaespTipoEvaluacion(173);
			puntajeEvaluacionEI.setPrograma(programa);
			logger.info("saveee---");
			crearProgramaService.savePuntajeEvaluacion(puntajeEvaluacionEI);
		}
		if (chkRqET != null && programa.getReqEvalTec() == 0) {
			programa.setReqEvalTec(1);
			puntajeEvaluacionET.setPuntajeEvaluacionID(null);
			puntajeEvaluacionET.setMaximo(puntajeMaxRqET);
			puntajeEvaluacionET.setMinimo(puntajeMinRqET);
			puntajeEvaluacionET.setMinimoAprobatorio(minimoAprobatorioET);
			puntajeEvaluacionET.setFkIdtablaespTipoEvaluacion(174);
			puntajeEvaluacionET.setPrograma(programa);
			crearProgramaService.savePuntajeEvaluacion(puntajeEvaluacionET);

		}
		if (chkRqPY != null && programa.getReqEvalProy() == 0) {
			programa.setReqEvalProy(1);
			puntajeEvaluacionPY.setPuntajeEvaluacionID(null);
			puntajeEvaluacionPY.setMaximo(puntajeMaxRqPY);
			puntajeEvaluacionPY.setMinimo(puntajeMinRqPY);
			puntajeEvaluacionPY.setMinimoAprobatorio(minimoAprobatorioPY);
			puntajeEvaluacionPY.setFkIdtablaespTipoEvaluacion(175);
			puntajeEvaluacionPY.setPrograma(programa);
			crearProgramaService.savePuntajeEvaluacion(puntajeEvaluacionPY);
		}
		crearProgramaService.updatePrograma(programa);
		//logger.info("updatePrograma");
		List<RestricionPrograma> listRestriccionP = crearProgramaService
				.findListRestricionProgramaById(programa.getProgramaID());

		List<RestricionPrograma> listRestriccionGrilla = (List<RestricionPrograma>) request
				.getSession().getAttribute("listRestriccionGrilla");
		List<RestricionPrograma> listRestriccionEliminar = (List<RestricionPrograma>) request
				.getSession().getAttribute("listRestriccionEliminar");

		for (int z = 0; z < listRestriccionP.size(); z++) {
			if (listRestriccionEliminar != null) {
				for (int y = 0; y < listRestriccionEliminar.size(); y++) {
					if (listRestriccionP.get(z)
							.getFkIdtablaespTipoRestriccionProg() == listRestriccionEliminar
							.get(y).getFkIdtablaespTipoRestriccionProg()) {
						crearProgramaService
								.deleteRestriccionPrograma(listRestriccionP
										.get(z));
					}
				}
			}
		}
		if (listRestriccionGrilla != null) {

			for (int j = 0; j < listRestriccionGrilla.size(); j++) {
				if (listRestriccionGrilla.get(j).getRestricionProgramaID() == null) {
					System.out.println("ente a saveeee");
					listRestriccionGrilla.get(j).setPrograma(programa);
					crearProgramaService
							.saveRestriccionPrograma(listRestriccionGrilla
									.get(j));
				}
			}
		}
		List<ActividadObligatoriaPrograma> listActividadObligatoria = crearProgramaService
				.findListActividadObligatoriaProgramaByProgramaId(programa
						.getProgramaID());
		List<ActividadObligatoriaPrograma> listActividadObligatoriaGrilla = (List<ActividadObligatoriaPrograma>) request
				.getSession().getAttribute("listActividadObligatoriaGrilla");
		List<ActividadObligatoriaPrograma> listActividadObligatoriaEliminar = (List<ActividadObligatoriaPrograma>) request
				.getSession().getAttribute("listActividadObligatoriaEliminar");

		for (int z = 0; z < listActividadObligatoria.size(); z++) {
			if (listActividadObligatoriaEliminar != null) {
				//System.out.println("listActividadObligatoriaEliminar:::::"+ listActividadObligatoriaEliminar.size());
				for (int y = 0; y < listActividadObligatoriaEliminar.size(); y++) {
					if (listActividadObligatoria.get(z)
							.getTipoActividadObligatoriaPrograma()
							.getTipoActividadObligatoriaProgramaID() == listActividadObligatoriaEliminar
							.get(y).getTipoActividadObligatoriaPrograma()
							.getTipoActividadObligatoriaProgramaID()) {
						crearProgramaService
								.deleteActividadObligatoriaPrograma(listActividadObligatoria
										.get(z));
					}
				}
			}
		}
		if (listActividadObligatoriaGrilla != null) {
			for (int j = 0; j < listActividadObligatoriaGrilla.size(); j++) {
				if (listActividadObligatoriaGrilla.get(j)
						.getActividadObligatoriaProgramaID() == null) {
					System.out.println("ente a saveeee");
					listActividadObligatoriaGrilla.get(j).setPrograma(programa);
					crearProgramaService
							.saveActividadObligatoriaPrograma(listActividadObligatoriaGrilla
									.get(j));
				}
			}
		}

		System.out.println("--Modificar Ubicacion Proyecto-");

		String txtListadoUbicacionBeneficiarios = request.getParameter(
				"txtListadoUbicacionBeneficiarios").toString();
		if (txtListadoUbicacionBeneficiarios != "") {
			List<RestriccionDepProvDist> ListRestriccionDepProvDist = null;
			JSONArray jsonListadoUbicacionBeneficiarios = (JSONArray) JSONSerializer
					.toJSON(txtListadoUbicacionBeneficiarios);
			ListRestriccionDepProvDist = crearProgramaService
					.findListRestriccionDepProvDistByProgramaId(programa
							.getProgramaID());
			;

			if (!ListRestriccionDepProvDist.isEmpty()) {
				for (int i = 0; i < ListRestriccionDepProvDist.size(); i++) {
					crearProgramaService
							.deleteRestriccionDepProvDist(ListRestriccionDepProvDist
									.get(i).getRestricciondepprovdistID());

				}
			}
			// para agregara nuevos
			for (int i = 0; i < jsonListadoUbicacionBeneficiarios.size(); i++) {
				JSONObject ObjJsonUbicacion = (JSONObject) jsonListadoUbicacionBeneficiarios
						.getJSONObject(i).getJSONObject("ubicacion");
				int idUbicacion = 0;

				RestriccionDepProvDist restriccionDepProvDist = new RestriccionDepProvDist();

				if (ObjJsonUbicacion.getInt("cbxProvValue") == 0
						&& ObjJsonUbicacion.getInt("cbxDistValue") == 0) {
					idUbicacion = ObjJsonUbicacion.getInt("cbxDepValue");
				}
				if (ObjJsonUbicacion.getInt("cbxProvValue") > 0
						&& ObjJsonUbicacion.getInt("cbxDistValue") == 0) {
					idUbicacion = ObjJsonUbicacion.getInt("cbxProvValue");
				}
				if (ObjJsonUbicacion.getInt("cbxProvValue") > 0
						&& ObjJsonUbicacion.getInt("cbxDistValue") > 0) {
					idUbicacion = ObjJsonUbicacion.getInt("cbxDistValue");
				}

				if (idUbicacion >= 1) {
					DepProvDist depProvDist = crearProgramaService
							.findDepProvDistById(idUbicacion);
					restriccionDepProvDist.setDepProvDist(depProvDist);
					restriccionDepProvDist.setPrograma(programa);
					crearProgramaService
							.saveRestriccionDepProvDist(restriccionDepProvDist);
				}
			}

		}

		System.out.println("--Modificar Sub areaTematica-");
		String txtListadoSubAreaTematica = request
				.getParameter("txtListadoSubAreaTematica");
		if (txtListadoSubAreaTematica != "") {

			JSONArray jsonListadoSubAreaTematica = (JSONArray) JSONSerializer
					.toJSON(txtListadoSubAreaTematica);
			List<RestriccionSubAreaTematica> ListRestriccionSubAreaTematica = crearProgramaService
					.findListRestriccionSubAreaTematicayProgramaId(id);
			if (!ListRestriccionSubAreaTematica.isEmpty()) {
				for (RestriccionSubAreaTematica restriccionSubAreaTematica1 : ListRestriccionSubAreaTematica) {
					crearProgramaService
							.deleteRestriccionSubAreaTematica(restriccionSubAreaTematica1
									.getRestriccionsubareatematicaID());
				}

			}

			for (int i = 0; i < jsonListadoSubAreaTematica.size(); i++) {
				JSONObject ObjJsonSubAreaTematica = (JSONObject) jsonListadoSubAreaTematica
						.getJSONObject(i).getJSONObject("areaTematica");
				int idSubAreaTematica = ObjJsonSubAreaTematica
						.getInt("cbxSATvalue");
				RestriccionSubAreaTematica restriccionSubAreaTematica = new RestriccionSubAreaTematica();
				SubAreaTematica subAreaTematica = crearProgramaService
						.findSubAreaTematicaById(idSubAreaTematica);
				restriccionSubAreaTematica.setSubAreaTematica(subAreaTematica);
				restriccionSubAreaTematica.setPrograma(programa);
				crearProgramaService
						.saveRestriccionSubAreaTematica(restriccionSubAreaTematica);
			}
		}
		if (imagenOArchivoTempBean != null) {
			//System.out.println("nombreArchivob: "+ fileUploadDownload.getNombreArchivo());
			//System.out.println("extensionb: "+ fileUploadDownload.getExtension());
			String nombreArchivo= request.getParameter("nombreArchivo");
			String extencion= request.getParameter("extension");
			
			// GUARDAR ARCHOVP
			int codExtension =  tablaEspecificaService.findIdByDescripcionCabecera(extencion);// 234;
			
			if (codExtension != 0) {

				// crear archivo, convertir a array de byte
				//File uploadedFile = new File(fileUploadDownload.getFilePath(),fileUploadDownload.getNombreArchivo()+ fileUploadDownload.getExtension());
				//byte archivoByte[] = FileUploadDownload.convertirFileToArrayByte(uploadedFile);
				//System.out.println("tamano de archivo al guardar en la BD :"+ archivoByte.length);

				// Guardar el archivo en la base de datos
				ImagenOArchivo imagenOArchivo = crearProgramaService.findProgramaByArchivoImagen(programa
						.getProgramaID());

				if (imagenOArchivo != null) {
					//imagenOArchivo = crearProgramaService.findProgramaByArchivoImagen(programa.getProgramaID());
					imagenOArchivo.setDescripcionDocImg(nombreArchivo);
					imagenOArchivo.setFkIdtablaespTipoArchivo(codExtension);
					imagenOArchivo.setImagen(imagenOArchivoTempBean.getImagenODocumento());
					crearProgramaService.updateImagenOArchivo(imagenOArchivo);

				} else {
					imagenOArchivo= new ImagenOArchivo();
					
					imagenOArchivo.setDescripcionDocImg(nombreArchivo);
					imagenOArchivo.setFkIdtablaespTipoArchivo(codExtension);
					imagenOArchivo.setImagen(imagenOArchivoTempBean.getImagenODocumento());
					imagenOArchivo.setPrograma(programa);
					crearProgramaService.saveImagenOArchivo(imagenOArchivo);

				}

			}
		}

		cargaCombos();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("listTipoRestriccion", listTipoRestriccion);
		model.put("listResultadoActividadObligatoria",
				listResultadoActividadObligatoria);
		model.put("listPrograma", listPrograma);
		model.put("listDepartamento", listDepartamento);
		return new ModelAndView("mostrarCrearPrograma", model);

	}

	@RequestMapping(value = "/principal/actionEliminarPrograma")
	public ModelAndView actionEliminarPrograma(HttpServletRequest request)
			throws Exception {

		//String sidPrograma = request.getParameter("idPrograma");
		Integer idPrograma = Integer.parseInt(request.getParameter("idPrograma"));
		Programa programa = crearProgramaService.findProgramaById(idPrograma);
		String mensaje = "Se elimino exitosamente";
		try {
			crearProgramaService.deletePrograma(programa);
		} catch (Exception e) {
			mensaje = "No se elimino el Programa";
		}

		cargaCombos();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("listTipoRestriccion", listTipoRestriccion);
		model.put("listResultadoActividadObligatoria",
				listResultadoActividadObligatoria);
		model.put("listPrograma", listPrograma);
		model.put("listDepartamento", listDepartamento);
		model.put("mensaje", mensaje);

		return new ModelAndView("mostrarCrearPrograma", model);
	}

	// Agregar restricciones a la grilla
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/actionAgregarRestriccion", method = RequestMethod.POST)
	public void actionAgregarRestriccion(HttpServletRequest request,
			HttpServletResponse resp) throws Exception {
		String descripcionRestriccion = request
				.getParameter("descripcionTipoRestriccion");

		if ("actualizar".equals(descripcionRestriccion)) {
			//logger.info("actionAgregarRestriccion ingreseeeeeeeee--Actualizar-");
			Integer idPrograma = (Integer) request.getSession().getAttribute(
					"programaID");
			//logger.info("actionAgregarRestriccion ingreseeeeeeeee--Actualizar-"+ idPrograma);
			List<RestricionPrograma> listRestriccionP = crearProgramaService
					.findListRestricionProgramaById(idPrograma);
			String grilla = "<caption style=\"text-align:center; \"><label>Lista de Restricciones</label></caption><thead><tr><td><label>Tipo Restriccion</label></td><td><label>Porcentaje Min</label></td><td><label>Porcentaje Max</label></td><td><label>Eliminar</label></td></tr></thead>";
			if (listRestriccionP != null) {
				for (int i = 0; i < listRestriccionP.size(); i++) {
					grilla += "<tbody><tr>";
					if (listRestriccionP.get(i)
							.getFkIdtablaespTipoRestriccionProg() == 176) {
						grilla += "<td colspan=1><label>Mano de Obra</label></td>";
					} else if (listRestriccionP.get(i)
							.getFkIdtablaespTipoRestriccionProg() == 177) {
						grilla += "<td colspan=1><label>Inversiones</label></td>";
					} else if (listRestriccionP.get(i)
							.getFkIdtablaespTipoRestriccionProg() == 178) {
						grilla += "<td colspan=1><label>Gastos Administrativos</label></td>";
					}

					grilla += "<td colspan=1><label>"
							+ listRestriccionP.get(i).getPorcenMinimo()
							+ "</label></td>"
							+ "<td colspan=1><label>"
							+ listRestriccionP.get(i).getPorcenMaximo()
							+ "</label></td>"
							+ "<td colspan=1><a href=\"javascript:fEliminarRestricionPrograma('"
							+ listRestriccionP.get(i)
									.getFkIdtablaespTipoRestriccionProg()
							+ "')\" class=\"ui-icon ui-icon-circle-close\" style=\"cursor:pointer\" title=\"Eliminar\"></a></td>"
							+ "</tr></tbody>";
				}
			}

			request.getSession().setAttribute("listRestriccionGrilla",
					listRestriccionP);
			resp.getWriter().write(
					"<table class=\"table-clasico\" style=\"width: 100%\">" + grilla
							+ "</table>");

		} else {

			Integer fkIdtablaespTipoRestriccionProg = Integer.parseInt(request
					.getParameter("idTipoRestriccion"));
			Integer porcenMaximo = Integer.parseInt(request.getParameter(
					"porcentajeMaxino").toString());
			Integer porcenMinimo = Integer.parseInt(request.getParameter(
					"porcentajeMinimo").toString());
			RestricionPrograma restricionPrograma = new RestricionPrograma();
			restricionPrograma
					.setFkIdtablaespTipoRestriccionProg(fkIdtablaespTipoRestriccionProg);
			restricionPrograma.setPorcenMaximo(porcenMaximo);
			restricionPrograma.setPorcenMinimo(porcenMinimo);

			List<RestricionPrograma> listRestriccionGrilla = null;
			listRestriccionGrilla = (List<RestricionPrograma>) request
					.getSession().getAttribute("listRestriccionGrilla");
			if (listRestriccionGrilla != null) {

				boolean agregar = true;

				for (int i = 0; i < listRestriccionGrilla.size(); i++) {
					if (listRestriccionGrilla.get(i)
							.getFkIdtablaespTipoRestriccionProg() == fkIdtablaespTipoRestriccionProg) {
						agregar = false;
						break;
					}
				}

				if (agregar) {
					listRestriccionGrilla.add(restricionPrograma);

				}
			} else {
				listRestriccionGrilla = new ArrayList<RestricionPrograma>();
				listRestriccionGrilla.add(restricionPrograma);
			}

			String grilla = "<caption style=\"text-align:center; \"><label>Lista de Restricciones</label></caption><thead><tr><td><label>Tipo Restriccion</label></td><td><label>Porcentaje Min</label></td><td><label>Porcentaje Max</label></td><td><label>Eliminar</label></td></tr></thead>";

			for (int i = 0; i < listRestriccionGrilla.size(); i++) {
				grilla += "<tbody><tr>";
				if (listRestriccionGrilla.get(i)
						.getFkIdtablaespTipoRestriccionProg() == 176) {
					grilla += "<td colspan=1><label>Mano de Obra</label></td>";
				} else if (listRestriccionGrilla.get(i)
						.getFkIdtablaespTipoRestriccionProg() == 177) {
					grilla += "<td colspan=1><label>Inversiones</label></td>";
				} else if (listRestriccionGrilla.get(i)
						.getFkIdtablaespTipoRestriccionProg() == 178) {
					grilla += "<td colspan=1><label>Gastos Administrativos</label></td>";
				}

				grilla += "<td colspan=1><label>"
						+ listRestriccionGrilla.get(i).getPorcenMinimo()
						+ "</label></td>"
						+ "<td colspan=1><label>"
						+ listRestriccionGrilla.get(i).getPorcenMaximo()
						+ "</label></td>"
						+ "<td colspan=1><a href=\"javascript:fEliminarRestricionPrograma('"
						+ listRestriccionGrilla.get(i)
								.getFkIdtablaespTipoRestriccionProg()
						+ "')\" class=\"ui-icon ui-icon-circle-close\" style=\"cursor:pointer\" title=\"Eliminar\"></a></td>"
						+ "</tr></tbody>";
			}
			request.getSession().setAttribute("listRestriccionGrilla",
					listRestriccionGrilla);
			resp.getWriter().write(
					"<table class=\"table-clasico\" style=\"width: 100%\">" + grilla
							+ "</table>");

		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/actionEliminarRestriccion", method = RequestMethod.POST)
	public void actionEliminarRestriccion(HttpServletRequest request,
			HttpServletResponse resp) throws Exception {
		String sidRestriccion = request.getParameter("idRestriccion");
		Integer idRestriccion = Integer.parseInt(sidRestriccion);

		List<RestricionPrograma> listRestriccionGrilla = (List<RestricionPrograma>) request
				.getSession().getAttribute("listRestriccionGrilla");
		List<RestricionPrograma> listRestriccionEliminar = (List<RestricionPrograma>) request
				.getSession().getAttribute("listRestriccionEliminar");

		for (int i = 0; i < listRestriccionGrilla.size(); i++) {
			if (listRestriccionGrilla.get(i)
					.getFkIdtablaespTipoRestriccionProg() == idRestriccion) {
				if (listRestriccionEliminar == null) {
					listRestriccionEliminar = new ArrayList<RestricionPrograma>();
					listRestriccionEliminar.add(listRestriccionGrilla.get(i));
				} else
					listRestriccionEliminar.add(listRestriccionGrilla.get(i));

				listRestriccionGrilla.remove(i);

				logger.info("Elimine  :" + idRestriccion);

			}
		}
		String grilla = "<caption style=\"text-align:center; \"><label>Lista de Restricciones</label></caption><thead><tr><td><label>Tipo Restriccion</label></td><td><label>Porcentaje Min</label></td><td><label>Porcentaje Max</label></td><td><label>Eliminar</label></td></tr></thead>";

		for (int i = 0; i < listRestriccionGrilla.size(); i++) {
			grilla += "<tbody><tr>";
			if (listRestriccionGrilla.get(i)
					.getFkIdtablaespTipoRestriccionProg() == 176) {
				grilla += "<td colspan=1><label>Mano de Obra</label></td>";
			} else if (listRestriccionGrilla.get(i)
					.getFkIdtablaespTipoRestriccionProg() == 177) {
				grilla += "<td colspan=1><label>Inversiones</label></td>";
			} else if (listRestriccionGrilla.get(i)
					.getFkIdtablaespTipoRestriccionProg() == 178) {
				grilla += "<td colspan=1><label>Gastos Administrativos</label></td>";
			}
			grilla += "<td colspan=1><label>"
					+ listRestriccionGrilla.get(i).getPorcenMinimo()
					+ "</label></td>"
					+ "<td colspan=1><label>"
					+ listRestriccionGrilla.get(i).getPorcenMaximo()
					+ "</label></td>"
					+ "<td colspan=1><a href=\"javascript:fEliminarRestricionPrograma('"
					+ listRestriccionGrilla.get(i)
							.getFkIdtablaespTipoRestriccionProg()
					+ "')\"  class=\"ui-icon ui-icon-circle-close\" style=\"cursor:pointer\" title=\"Eliminar\"></a></td>"
					+ "</tr></tbody>";
		}
		request.getSession().setAttribute("listRestriccionGrilla",
				listRestriccionGrilla);
		request.getSession().setAttribute("listRestriccionEliminar",
				listRestriccionEliminar);
		resp.getWriter().write(
				"<table class=\"table-clasico\" style=\"width: 100%\">" + grilla
						+ "</table>");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "principal/actionCargaActividadObligatoriaAgrilla")
	public void actionCargaActividadObligatoriaAgrilla(
			HttpServletRequest request, HttpServletResponse resp)
			throws IOException {
		String checked = request.getParameter("estado");
		if ("actualizar".equals(checked)) {
			String grilla = "<caption style=\"text-align:center; \"><label>Lista Actividad Oblig.</label></caption><thead><tr><td style=\"width: 100%;\"><label>Actividad</label></td></tr></thead>";
			Integer idPrograma = (Integer) request.getSession().getAttribute(
					"programaID");
			List<ActividadObligatoriaPrograma> listAOP = crearProgramaService
					.findListActividadObligatoriaProgramaByProgramaId(idPrograma);
			for (int i = 0; i < listAOP.size(); i++) {
				grilla += "<tbody><tr><td><label>"
						+ listAOP.get(i).getTipoActividadObligatoriaPrograma()
								.getDescripcion() + "</label>" + "</td>"
						+ "</tr></tbody>";
			}
			// enviar a session
			request.getSession().setAttribute("listActividadObligatoriaGrilla",
					listAOP);

			resp.getWriter().write(
					"<table class=\"table-clasico\" style=\"width: 100%;\" >"
							+ grilla + "</table>");

		} else {
			String descripccionActividad = request.getParameter("desActividad");
			Integer idActividad = Integer.parseInt(request.getParameter(
					"idTipoActividad").toString());

			ActividadObligatoriaPrograma actividadOP = new ActividadObligatoriaPrograma();

			TipoActividadObligatoriaPrograma tipoAOP = new TipoActividadObligatoriaPrograma();
			tipoAOP.setDescripcion(descripccionActividad);
			tipoAOP.setTipoActividadObligatoriaProgramaID(idActividad);

			actividadOP.setTipoActividadObligatoriaPrograma(tipoAOP);

			List<ActividadObligatoriaPrograma> listActividadObligatoriaGrilla = null;
			listActividadObligatoriaGrilla = (List<ActividadObligatoriaPrograma>) request
					.getSession()
					.getAttribute("listActividadObligatoriaGrilla");
			List<ActividadObligatoriaPrograma> listActividadObligatoriaEliminar = (List<ActividadObligatoriaPrograma>) request
					.getSession().getAttribute(
							"listActividadObligatoriaEliminar");

			if (listActividadObligatoriaGrilla != null) {
				Boolean agregar = true;
				for (int i = 0; i < listActividadObligatoriaGrilla.size(); i++) {
					if (checked.equals("0")
							&& actividadOP
									.getTipoActividadObligatoriaPrograma()
									.getTipoActividadObligatoriaProgramaID() == listActividadObligatoriaGrilla
									.get(i)
									.getTipoActividadObligatoriaPrograma()
									.getTipoActividadObligatoriaProgramaID()) {

						if (listActividadObligatoriaEliminar == null) {
							listActividadObligatoriaEliminar = new ArrayList<ActividadObligatoriaPrograma>();
							listActividadObligatoriaEliminar
									.add(listActividadObligatoriaGrilla.get(i));
						} else
							listActividadObligatoriaEliminar
									.add(listActividadObligatoriaGrilla.get(i));

						listActividadObligatoriaGrilla.remove(i);
						agregar = false;
						break;
					}
					if (actividadOP.getTipoActividadObligatoriaPrograma()
							.getTipoActividadObligatoriaProgramaID() == listActividadObligatoriaGrilla
							.get(i).getTipoActividadObligatoriaPrograma()
							.getTipoActividadObligatoriaProgramaID()) {
						System.out.println("si son iwales  ::::");
						agregar = false;
						break;
					}
				}

				if (agregar) {
					listActividadObligatoriaGrilla.add(actividadOP);
				}
			} else {
				listActividadObligatoriaGrilla = new ArrayList<ActividadObligatoriaPrograma>();
				listActividadObligatoriaGrilla.add(actividadOP);
			}
			String grilla = "<caption style=\"text-align:center; \"><label>Lista Actividad Oblig.</label></caption><thead><tr><td><label>Actividad</label></td></tr></thead>";

			for (int i = 0; i < listActividadObligatoriaGrilla.size(); i++) {
				grilla += "<tbody><tr>"
						+ "<td ><label>"
						+ listActividadObligatoriaGrilla.get(i)
								.getTipoActividadObligatoriaPrograma()
								.getDescripcion() + "</label></td>"
						+ "</tr></tbody>";
			}
			// enviar a session
			request.getSession().setAttribute("listActividadObligatoriaGrilla",
					listActividadObligatoriaGrilla);
			request.getSession().setAttribute(
					"listActividadObligatoriaEliminar",
					listActividadObligatoriaEliminar);
			resp.getWriter().write(
					"<table class=\"table-clasico\" style=\"width: 100%;\" >"
							+ grilla + "</table>");

		}
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/principal/actionCargaActividadObligatoria")
	public void actionCargaActividadObligatoria(HttpServletRequest request,
			HttpServletResponse resp) throws IOException {

		Integer idResultado = Integer.parseInt(request.getParameter(
				"idResultado").toString());

		List<ActividadObligatoriaPrograma> listActividadObligatoriaGrilla = null;
		listActividadObligatoriaGrilla = (List<ActividadObligatoriaPrograma>) request
				.getSession().getAttribute("listActividadObligatoriaGrilla");

		List<TipoActividadObligatoriaPrograma> listTipoActividadObligByResultado = crearProgramaService
				.findTipoActividadObligatoriaProgramasByIdtablaespTipo(idResultado);

		String cadena = "";

		for (TipoActividadObligatoriaPrograma tipoActividadObligatoriaPrograma : listTipoActividadObligByResultado) {

			boolean existe = false;

			if (listActividadObligatoriaGrilla != null) {

				for (ActividadObligatoriaPrograma actividadObligatoriaPrograma : listActividadObligatoriaGrilla) {

					if (tipoActividadObligatoriaPrograma
							.getTipoActividadObligatoriaProgramaID() == actividadObligatoriaPrograma
							.getTipoActividadObligatoriaPrograma()
							.getTipoActividadObligatoriaProgramaID()) {

						existe = true;

						break;
					}

				}

				if (existe) {

					cadena += "<input type= \"checkbox\" checked=\"checked\" name=\""
							+ tipoActividadObligatoriaPrograma.getDescripcion()
							+ "\" id=\""
							+ tipoActividadObligatoriaPrograma
									.getTipoActividadObligatoriaProgramaID()
							+ "\" onclick=\"fCargaActividadObligatoriaAgrilla(this)\" value=\""
							+ tipoActividadObligatoriaPrograma
									.getTipoActividadObligatoriaProgramaID()
							+ "\"/><label>"
							+ tipoActividadObligatoriaPrograma.getDescripcion()
							+ "</label>" + "<br />";

				} else {

					cadena += "<input type= \"checkbox\" name=\""
							+ tipoActividadObligatoriaPrograma.getDescripcion()
							+ "\" id=\""
							+ tipoActividadObligatoriaPrograma
									.getTipoActividadObligatoriaProgramaID()
							+ "\" onclick=\"fCargaActividadObligatoriaAgrilla(this)\" value=\""
							+ tipoActividadObligatoriaPrograma
									.getTipoActividadObligatoriaProgramaID()
							+ "\"/><label>"
							+ tipoActividadObligatoriaPrograma.getDescripcion()
							+ "</label>" + "<br />";

				}

			} else {

				cadena += "<input type= \"checkbox\" name=\""
						+ tipoActividadObligatoriaPrograma.getDescripcion()
						+ "\" id=\""
						+ tipoActividadObligatoriaPrograma
								.getTipoActividadObligatoriaProgramaID()
						+ "\" onclick=\"fCargaActividadObligatoriaAgrilla(this)\" value=\""
						+ tipoActividadObligatoriaPrograma
								.getTipoActividadObligatoriaProgramaID()
						+ "\"/><label>"
						+ tipoActividadObligatoriaPrograma.getDescripcion()
						+ "</label>" + "<br />";

			}

		}
		request.getSession().setAttribute("listActividadObligatoriaGrilla",
				listActividadObligatoriaGrilla);
		resp.getWriter().write(cadena);
	}

	@RequestMapping(value = "principal/actionCargaGrillaDepProvDist")
	public void actionCargaGrillaDepProvDist(HttpServletRequest request,
			HttpServletResponse resp) throws IOException {
		// obtiene el codigo de programa
		
		if(!request.getSession().getAttribute("programaID").equals("")){
		String programaIDSesion=request.getSession().getAttribute("programaID").toString();
		Integer idPrograma = Integer.valueOf(programaIDSesion);
		
		List<RestriccionDepProvDist> ListRestriccionDepProvDist = crearProgramaService
				.findListRestriccionDepProvDistByProgramaId(idPrograma);
		List<DepProvDist> newlistDepProvDist = new ArrayList<DepProvDist>();

		for (RestriccionDepProvDist restriccionDepProvDist : ListRestriccionDepProvDist) {
			newlistDepProvDist.add(restriccionDepProvDist.getDepProvDist());
		}

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, newlistDepProvDist);
		resp.getWriter().write(jsonArray.toString());
		}
	}

	@RequestMapping(value = "principal/actionCargaGrillaRestriccionSubAreaTematica")
	public void actionCargaGrillaRestriccionSubAreaTematica(
			HttpServletRequest request, HttpServletResponse resp)
			throws IOException {
		Integer idPrograma = (Integer) request.getSession().getAttribute(
				"programaID");

		List<RestriccionSubAreaTematica> ListRestriccionSubAreaTematica = crearProgramaService
				.findListRestriccionSubAreaTematicayProgramaId(idPrograma);
		List<SubAreaTematica> newlistSubAreaTematica = new ArrayList<SubAreaTematica>();

		for (RestriccionSubAreaTematica restriccionSubAreaTematica : ListRestriccionSubAreaTematica) {
			newlistSubAreaTematica.add(restriccionSubAreaTematica
					.getSubAreaTematica());
		}

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, newlistSubAreaTematica);
		resp.getWriter().write(jsonArray.toString());

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/principal/actionRegistrarDatosPrograma", method = RequestMethod.POST)
	public ModelAndView actionRegistrarDatosPrograma(
			@ModelAttribute("imagenOArchivoTempBean") ImagenOArchivoTempBean imagenOArchivoTempBean,
			HttpServletRequest request)
			throws Exception {
		// 173
		PuntajeEvaluacion puntajeEvaluacionEI = new PuntajeEvaluacion();
		Integer puntajeMinRqEI = 0;
		Integer puntajeMaxRqEI = 0;
		Integer minimoAprobatorioEI = 0;
		// 174
		PuntajeEvaluacion puntajeEvaluacionET = new PuntajeEvaluacion();

		Integer puntajeMinRqET = 0;
		Integer puntajeMaxRqET = 0;
		Integer minimoAprobatorioET = 0;
		// 175
		PuntajeEvaluacion puntajeEvaluacionPY = new PuntajeEvaluacion();
		Integer puntajeMinRqPY = 0;
		Integer puntajeMaxRqPY = 0;
		Integer minimoAprobatorioPY = 0;

		String chkRqPYG = request.getParameter("chkRqPYG");
		String chkRqEI = request.getParameter("chkRqEI");
		String chkRqET = request.getParameter("chkRqET");
		String chkRqPY = request.getParameter("chkRqPY");
		Programa programa = new Programa();
		Integer id = Integer.parseInt(request.getSession()
				.getAttribute("programaID").toString());
		programa = crearProgramaService.findProgramaById(id);
		if (chkRqEI != null) {
			puntajeMinRqEI = Integer.parseInt(request.getParameter(
					"puntajeMinRqEI").toString());
			puntajeMaxRqEI = Integer.parseInt(request.getParameter(
					"puntajeMaxRqEI").toString());
			minimoAprobatorioEI = Integer.parseInt(request.getParameter(
					"minimoAprobatorioEI").toString());
		}
		if (chkRqET != null) {
			puntajeMinRqET = Integer.parseInt(request.getParameter(
					"puntajeMinRqET").toString());
			puntajeMaxRqET = Integer.parseInt(request.getParameter(
					"puntajeMaxRqET").toString());
			minimoAprobatorioET = Integer.parseInt(request.getParameter(
					"minimoAprobatorioET").toString());
		}
		if (chkRqPY != null) {
			puntajeMinRqPY = Integer.parseInt(request.getParameter(
					"puntajeMinRqPY").toString());
			puntajeMaxRqPY = Integer.parseInt(request.getParameter(
					"puntajeMaxRqPY").toString());
			minimoAprobatorioPY = Integer.parseInt(request.getParameter(
					"minimoAprobatorioPY").toString());
		}

		if (chkRqPYG != null) {
			programa.setReqProyGrobal(1);
		} else
			programa.setReqProyGrobal(0);

		if (chkRqEI != null) {
			programa.setReqEvalInst(1);
			// 173 E.institucional
			puntajeEvaluacionEI.setFkIdtablaespTipoEvaluacion(173);
			puntajeEvaluacionEI.setMaximo(puntajeMaxRqEI);
			puntajeEvaluacionEI.setMinimo(puntajeMinRqEI);
			puntajeEvaluacionEI.setMinimoAprobatorio(minimoAprobatorioEI);
			puntajeEvaluacionEI.setPrograma(programa);
			crearProgramaService.savePuntajeEvaluacion(puntajeEvaluacionEI);
		} else
			programa.setReqEvalInst(0);
		if (chkRqET != null) {
			programa.setReqEvalTec(1);
			// 174 E.Tecnica
			puntajeEvaluacionET.setFkIdtablaespTipoEvaluacion(174);
			puntajeEvaluacionET.setMaximo(puntajeMaxRqET);
			puntajeEvaluacionET.setMinimo(puntajeMinRqET);
			puntajeEvaluacionET.setMinimoAprobatorio(minimoAprobatorioET);
			puntajeEvaluacionET.setPrograma(programa);
			crearProgramaService.savePuntajeEvaluacion(puntajeEvaluacionET);

		} else
			programa.setReqEvalTec(0);
		if (chkRqPY != null) {
			programa.setReqEvalProy(1);
			// 175 E.Proyecto
			puntajeEvaluacionPY.setFkIdtablaespTipoEvaluacion(175);
			puntajeEvaluacionPY.setMaximo(puntajeMaxRqPY);
			puntajeEvaluacionPY.setMinimo(puntajeMinRqPY);
			puntajeEvaluacionPY.setMinimoAprobatorio(minimoAprobatorioPY);
			puntajeEvaluacionPY.setPrograma(programa);
			crearProgramaService.savePuntajeEvaluacion(puntajeEvaluacionPY);
		} else
			programa.setReqEvalProy(0);

		crearProgramaService.updatePrograma(programa);
		logger.info("updatePrograma");

		// SUB AREA TEMATICA
		String txtListadoSubAreaTematica = request
				.getParameter("txtListadoSubAreaTematica");
		if (txtListadoSubAreaTematica != "") {

			JSONArray jsonListadoSubAreaTematica = (JSONArray) JSONSerializer
					.toJSON(txtListadoSubAreaTematica);
			List<RestriccionSubAreaTematica> ListRestriccionSubAreaTematica = crearProgramaService
					.findListRestriccionSubAreaTematicayProgramaId(id);
			if (!ListRestriccionSubAreaTematica.isEmpty()) {
				for (RestriccionSubAreaTematica restriccionSubAreaTematica1 : ListRestriccionSubAreaTematica) {
					crearProgramaService
							.deleteRestriccionSubAreaTematica(restriccionSubAreaTematica1
									.getRestriccionsubareatematicaID());
				}

			}

			for (int i = 0; i < jsonListadoSubAreaTematica.size(); i++) {
				JSONObject ObjJsonSubAreaTematica = (JSONObject) jsonListadoSubAreaTematica
						.getJSONObject(i).getJSONObject("areaTematica");
				int idSubAreaTematica = ObjJsonSubAreaTematica
						.getInt("cbxSATvalue");
				RestriccionSubAreaTematica restriccionSubAreaTematica = new RestriccionSubAreaTematica();
				SubAreaTematica subAreaTematica = crearProgramaService
						.findSubAreaTematicaById(idSubAreaTematica);
				restriccionSubAreaTematica.setSubAreaTematica(subAreaTematica);
				restriccionSubAreaTematica.setPrograma(programa);
				crearProgramaService
						.saveRestriccionSubAreaTematica(restriccionSubAreaTematica);

			}
		}

		// UBICION

		String txtListadoUbicacionBeneficiarios = request.getParameter(
				"txtListadoUbicacionBeneficiarios").toString();
		if (txtListadoUbicacionBeneficiarios != "") {
			System.out
					.println("request.getParameter(txtListadoUbicacionBeneficiarios).toString(");
			JSONArray jsonListadoUbicacionBeneficiarios = (JSONArray) JSONSerializer
					.toJSON(txtListadoUbicacionBeneficiarios);
			List<RestriccionDepProvDist> ListRestriccionDepProvDist = crearProgramaService
					.findListRestriccionDepProvDistByProgramaId(programa
							.getProgramaID());
			;

			if (!ListRestriccionDepProvDist.isEmpty()) {
				for (int i = 0; i < ListRestriccionDepProvDist.size(); i++) {
					crearProgramaService
							.deleteRestriccionDepProvDist(ListRestriccionDepProvDist
									.get(i).getRestricciondepprovdistID());

				}
			}
			// para agregara nuevos
			for (int i = 0; i < jsonListadoUbicacionBeneficiarios.size(); i++) {
				JSONObject ObjJsonUbicacion = (JSONObject) jsonListadoUbicacionBeneficiarios
						.getJSONObject(i).getJSONObject("ubicacion");
				int idUbicacion = 0;

				RestriccionDepProvDist restriccionDepProvDist = new RestriccionDepProvDist();
				if (ObjJsonUbicacion.getInt("cbxProvValue") == 0
						&& ObjJsonUbicacion.getInt("cbxDistValue") == 0) {
					idUbicacion = ObjJsonUbicacion.getInt("cbxDepValue");
				}
				if (ObjJsonUbicacion.getInt("cbxProvValue") > 0
						&& ObjJsonUbicacion.getInt("cbxDistValue") == 0) {
					idUbicacion = ObjJsonUbicacion.getInt("cbxProvValue");
				}
				if (ObjJsonUbicacion.getInt("cbxProvValue") > 0
						&& ObjJsonUbicacion.getInt("cbxDistValue") > 0) {
					idUbicacion = ObjJsonUbicacion.getInt("cbxDistValue");
				}

				if (idUbicacion >= 1) {
					DepProvDist depProvDist = crearProgramaService
							.findDepProvDistById(idUbicacion);
					restriccionDepProvDist.setDepProvDist(depProvDist);
					restriccionDepProvDist.setPrograma(programa);
					crearProgramaService
							.saveRestriccionDepProvDist(restriccionDepProvDist);
				}
			}

		}
		if (!imagenOArchivoTempBean.getImagenODocumento().equals("") ) {
			//System.out.println("nombreArchivob: "+ fileUploadDownload.getNombreArchivo());
			//System.out.println("extensionb: "+ fileUploadDownload.getExtension());
			String nombreArchivo= request.getParameter("nombreArchivo");
			String extencion= request.getParameter("extension");
			// GUARDAR ARCHOVP
			int codExtension =  tablaEspecificaService.findIdByDescripcionCabecera(extencion);// 234;
			
			// crear archivo, convertir a array de byte
			//File uploadedFile = new File(fileUploadDownload.getFilePath(),fileUploadDownload.getNombreArchivo()	+ fileUploadDownload.getExtension());
			//byte archivoByte[] = FileUploadDownload	.convertirFileToArrayByte(uploadedFile);
			//System.out.println("tamano de archivo al guardar en la BD :"+ archivoByte.length);
			if (codExtension != 0) {
			// Guardar el archivo en la base de datos
			ImagenOArchivo imagenOArchivo = new ImagenOArchivo();
			imagenOArchivo.setDescripcionDocImg(nombreArchivo);
			imagenOArchivo.setFkIdtablaespTipoArchivo(codExtension);
			imagenOArchivo.setImagen(imagenOArchivoTempBean.getImagenODocumento());
			imagenOArchivo.setPrograma(programa);
			crearProgramaService.saveImagenOArchivo(imagenOArchivo);
			}
		}
		List<RestricionPrograma> listRestriccionGrilla = null;
		listRestriccionGrilla = (List<RestricionPrograma>) request.getSession()
				.getAttribute("listRestriccionGrilla");
		// Restriccion ProgramaBean

		if (listRestriccionGrilla != null) {
			for (RestricionPrograma restricionPrograma : listRestriccionGrilla) {
				restricionPrograma.setPrograma(programa);
				crearProgramaService
						.saveRestriccionPrograma(restricionPrograma);
			}
		}
		List<ActividadObligatoriaPrograma> listActividadObligatoriaPrograma = null;
		listActividadObligatoriaPrograma = (List<ActividadObligatoriaPrograma>) request
				.getSession().getAttribute("listActividadObligatoriaGrilla");

		System.out.println("Actividad Obligatoria ProgramaBean:::"
				+ programa.getProgramaID());// Actividad Obligatoria
											// ProgramaBean
		if (listActividadObligatoriaPrograma != null) {
			for (ActividadObligatoriaPrograma actObliProgra : listActividadObligatoriaPrograma) {
				// actObliProgra.setActividadObligatoriaProgramaID(null);
				actObliProgra.setPrograma(programa);

				crearProgramaService
						.saveActividadObligatoriaPrograma(actObliProgra);
			}
		}

		
		request.getSession().removeAttribute("listRestriccionGrilla");
		request.getSession().removeAttribute("listActividadObligatoriaGrilla");
		request.getSession().removeAttribute("programaID");
		cargaCombos();

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("listTipoRestriccion", listTipoRestriccion);
		model.put("listResultadoActividadObligatoria",
				listResultadoActividadObligatoria);
		model.put("listPrograma", listPrograma);
		model.put("listDepartamento", listDepartamento);
		return new ModelAndView("mostrarCrearPrograma", model);

	}

	@RequestMapping(value = "/principal/cargarComboUbigeo.jspx")
	public void cargarComboUbigeo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String metodo = request.getParameter("metodo");

		PrintWriter out = null;
		out = response.getWriter();

		if (metodo.equals("departamento")) {
			out.println("<option value='0'>---Departamento---</option>");
			List<DepProvDist> listDepartamento = registroPerfilService
					.findDepProvDistritos(1, "0", "0", "0");
			if (!listDepartamento.isEmpty()) {
				for (DepProvDist depProvDist : listDepartamento) {
					out.printf("<option value='%1s'>%2s</option>",
							depProvDist.getDepProvDistID(),
							depProvDist.getDescripcion());
				}
			}

		} else if (metodo.equals("provincia")) {
			out.println("<option value='0'>---Provincia---</option>");
			String departamentoID = request.getParameter("departamentoID");
			List<DepProvDist> listProvincia = crearProgramaService.findUbigeo(
					2, departamentoID, "0", "0");
			
			if (!listProvincia.isEmpty()) {
				for (DepProvDist depProvDist : listProvincia) {
					out.printf("<option value='%1s'>%2s</option>",
							depProvDist.getDepProvDistID(),
							depProvDist.getDescripcion());
				}
			}

		} else if (metodo.equals("distrito")) {
			out.println("<option value='0'>---Distrito---</option>");
			String departamentoID = request.getParameter("departamentoID");
			String provinciaID = request.getParameter("provinciaID");
			List<DepProvDist> listDistritos = crearProgramaService.findUbigeo(
					3, departamentoID, provinciaID, "0");

			if (!listDistritos.isEmpty()) {
				for (DepProvDist depProvDist : listDistritos) {
					out.printf("<option value='%1s'>%2s</option>",
							depProvDist.getDepProvDistID(),
							depProvDist.getDescripcion());
				}
			}

		}

	}

}
