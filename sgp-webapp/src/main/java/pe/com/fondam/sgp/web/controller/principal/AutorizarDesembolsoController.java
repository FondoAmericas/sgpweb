package pe.com.fondam.sgp.web.controller.principal;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.service.AprobarDesembolsoService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DesembolsoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.UtilService;
import pe.com.fondam.sgp.core.util.CommonUtilities;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.controller.security.SecurityController;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class AutorizarDesembolsoController {

	//********************** inyecciones ********************//
	@Resource
	AprobarDesembolsoService aprobarDesembolsoService;
	
	@Resource
	UtilService utilService;
	
	@Resource
	DatoProyectoService datoProyectoService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	DesembolsoService desembolsoService;
	
	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	protected final Log logger = LogFactory.getLog(AutorizarDesembolsoController.class);
	private UserSession userSession;
	//private DatoProyecto datoProyecto=new DatoProyecto();
	//private String perfilUsuario;
	Map<String, Object> model = new HashMap<String, Object>();

	
	//********************** metodos ********************//
	@RequestMapping(value = "/principal/showAutorizarDesembolso.jspx")
	public ModelAndView showAutorizarDesembolso(HttpServletRequest request) {
		try {
			userSession = (UserSession) request.getSession().getAttribute(SgpWebConstants.SESSION_USER);
			if (userSession == null) {
				return SecurityController.autenticateErrorRedirect(request);
			}
		
			// aqui obtengo el camino absoluto de mi directorio actual
		 String directorio = System.getProperty("user.dir");
			// aqui obtengo el tipo de separador que hay entre las carpetas
			String separador = System.getProperty("file.separator");
			// cargo en mi clase Properties() el fichero que necesito
			//directorio + separador + "src\\nombre_proyecto\\ressources\\propiedades.pro perties"));
			//String elems = System.getProperty("user.dir");
			boolean status = new File("temp").mkdir();
			logger.info("Estado de la creacion de la carpeta temp: "+ status);
			String ruta=directorio+separador+"temp";
			model.put("ruta",ruta);
			model.put("funcionalidadSelect", "showAutorizarDesembolso.jspx");
			/*
			File f = new File("ruta.txt"); // Creamos un objeto file
			model.put("ruta",f.getAbsolutePath());
			 */
			 
		} catch (Exception e) {
			logger.error("Error en showAutorizarDesembolso.jspx");
		}
		
		return new ModelAndView("mostrarAutorizarDesembolso",model);
	}
	
	@RequestMapping(value = "/principal/cargaGrillaDesembolsoPorEvaluar.jspx")
	public ModelAndView cargaGrillaDesembolsoPorEvaluar(
			@RequestParam(required = false, value = "programaId") int programaId,
			@RequestParam(required = false, value = "filtroNombre") String filtroNombre,
			@RequestParam(required = false, value = "filtroCodigo") String filtroCodigo,
			@RequestParam(required = false, value = "opcionBusqueda") int opcionBusqueda,
			HttpServletRequest request) {
		
		List<DatoProyecto> listDatoProyecto =  new ArrayList<DatoProyecto>();
		
		if(opcionBusqueda==0){//sin nada
			listDatoProyecto=datoProyectoService.llenaDatoProyectoConDesembolsos(datoProyectoService.findDatoProyectoByPrograma(programaId));
		}else if(opcionBusqueda==1){ //nombre
			//model.put("listDesembolsoPorEvaluar", value)
			listDatoProyecto=datoProyectoService.llenaDatoProyectoConDesembolsos(datoProyectoService.findDatoProyectoByProgramaByNombre(programaId,filtroNombre));
		}else if(opcionBusqueda==2){//codigo
			listDatoProyecto=datoProyectoService.llenaDatoProyectoConDesembolsos(datoProyectoService.findDatoProyectoByProgramaByCodigo(programaId,filtroCodigo));
		}
		
		model.put("listDatoProyecto",listDatoProyecto);
		return new ModelAndView("divGrillaDesembolsoPorEvaluar",model);
	}
	
	@RequestMapping(value = "/principal/mostrarDesembolsoSelecionado.jspx")
	public ModelAndView mostrarDesembolsoSelecionado(
			@RequestParam(required = false, value = "desembolsoId") int desembolsoId,
			HttpServletRequest request) {
		
		userSession = (UserSession) request.getSession().getAttribute(SgpWebConstants.SESSION_USER);

		model.put("userSession",userSession);
		model.put("desembolso",desembolsoService.llenaDesembolsoCompleto(desembolsoService.findDesembolsoByID(desembolsoId)));		
		model.put("listEstadoDesembolso",detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estdese"));
		model.put("listTipoMoneda",tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral(FondamConstans.PREFIJO_TIPO_MONEDA));
		
		return new ModelAndView("divDesembolsoSeleccionado",model);
	}
	
	@RequestMapping(value = "/principal/buscarDesembolsoPorFiltro", method = RequestMethod.GET)
	public void buscarDesembolsoPorFiltro(HttpServletRequest request,
		HttpServletResponse response) throws IOException {		
		String opcionBusqueda = request.getParameter("opcionBusqueda");
		String filtroBusqueda = request.getParameter("filtroBusqueda");
		String programaID = request.getParameter("programaID");
		
		logger.error(" opcionBusqueda :: filtroBusqueda :: programaID " + opcionBusqueda + "::" +filtroBusqueda + "::" +programaID);
		
		//List <DetalleEstadoCabecera> listEstadosDesembolso = utilService.listarDetalleEstadoCabeceraByPrefijo(FondamConstans.PREFIJO_ESTADO_DESEMBOLSO);
		
		String jsonArrayDesembolsos = aprobarDesembolsoService.obtenerDesembolsosArrayJson(opcionBusqueda , filtroBusqueda, CommonUtilities.toInt(programaID));
		 
		logger.info("data listado desembolsos: "+jsonArrayDesembolsos);
		
		response.getWriter().write(jsonArrayDesembolsos);

	}
	
	@RequestMapping(value = "/principal/cargarDataCBXModalidadFinan")
	public void cargarDataCBXModalidadFinan(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		List<TablaEspecifica> lstModFinan = utilService.listaModalidadFinanciamiento();
		
		String listadoModFinanCBXJSON = utilService.convertirTablaEspecificaToJSONArrayCBX(lstModFinan);
	

		logger.info("data modalidad financiadora : "+listadoModFinanCBXJSON);
		response.getWriter().write(listadoModFinanCBXJSON); 
	} 

	@RequestMapping(value = "/principal/cargarDataCBXPrograma")
	public void cargarDataCBXPrograma(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String idModFinan = request.getParameter("idModFinan");
		List<Programa> lstPrograma = aprobarDesembolsoService.findProgramaByModFinan(CommonUtilities.toInt(idModFinan));
		
		String listadoProgramaCBXJson = aprobarDesembolsoService.convertirListProgramaToArrayJson(lstPrograma);
		
		logger.info("data programa : "+listadoProgramaCBXJson);
		response.getWriter().write(listadoProgramaCBXJson);
	} 
		
	@RequestMapping(value = "/principal/registrarAutoDesembolso")
	public void registrarSolicitudDesembolso(
			@RequestParam(required = false, value = "desembolsoId") int desembolsoId,
			@RequestParam(required = false, value = "estadoDesembolso") int estadoDesembolso,
			@RequestParam(required = false, value = "fechaAutorizado") String fechaAprobacion,
			@RequestParam(required = false, value = "montoAutorizado") double montoAutorizado,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ParseException {
		
		 //Map<String, String> parametrosAutoSolDesembolso = new HashMap<String, String>();
		 		 		
		 Desembolso desembolso= desembolsoService.findDesembolsoByID(desembolsoId);
		 
		 desembolso.setFkIdtablaespEstDesembolso(estadoDesembolso);
		 desembolso.setMontoAutorizado(montoAutorizado);
		 
		 SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaAprobado = formateador.parse(fechaAprobacion);
			desembolso.setFechaAprobacion(fechaAprobado);
			
		 aprobarDesembolsoService.updateAutorizacionDesembolso(desembolso);
		 
		 //model.put("perfilUsuario",perfilUsuario);
		 //model.put("periodoProyecto",datoProyecto.getCantidadPeriodo());
				
		//return new ModelAndView("mostrarAutorizarDesembolso",model);
	}
		 
}
