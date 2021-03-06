package pe.com.fondam.sgp.web.controller.principal;

import java.io.IOException;
import java.util.ArrayList;
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

import pe.com.fondam.sgp.core.bean.NotasDiferencia;
import pe.com.fondam.sgp.core.bean.ProgramaBean2;
import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.domain.Evaluacion;
import pe.com.fondam.sgp.core.domain.Evaluador;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.PuntajeEvaluacion;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.TipoPeriodo;
import pe.com.fondam.sgp.core.domain.TmpDatoProyecto;
import pe.com.fondam.sgp.core.domain.TmpEvaluacion;
import pe.com.fondam.sgp.core.domain.TmpFuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.TmpImagenOArchivo;
import pe.com.fondam.sgp.core.domain.TmpInstitucion;
import pe.com.fondam.sgp.core.domain.TmpPerfil;
import pe.com.fondam.sgp.core.domain.TmpResumenProyecto;
import pe.com.fondam.sgp.core.domain.Usuario;
import pe.com.fondam.sgp.core.service.CrearProgramaService;
import pe.com.fondam.sgp.core.service.CuentaCorrienteService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.EvaluacionService;
import pe.com.fondam.sgp.core.service.EvaluarService;
import pe.com.fondam.sgp.core.service.FuenteFinanciadoraService;
import pe.com.fondam.sgp.core.service.InstalarComiteTecnicoService;
import pe.com.fondam.sgp.core.service.ObservacionService;
import pe.com.fondam.sgp.core.service.ResumenProyectoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.TablaTemporalService;
import pe.com.fondam.sgp.core.service.TipoPeriodoService;
import pe.com.fondam.sgp.core.service.TmpFuenteFinanciadoraService;
import pe.com.fondam.sgp.core.service.UbicacionProyectoService;
import pe.com.fondam.sgp.web.InOutFiles.FileUploadDownload;
import pe.com.fondam.sgp.web.InOutFiles.LinkFile;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class EvaluarController {

	protected final Log logger = LogFactory.getLog(EvaluarController.class);

	@Resource
	EvaluarService evaluarService;

	@Resource
	TablaEspecificaService tablaEspecificaService;

	@Resource
	TablaTemporalService tablaTemporalService;

	@Resource
	CrearProgramaService crearProgramaService;
	
	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;

	@Resource
	InstalarComiteTecnicoService instalarComiteTecnicoService;

	@Resource
	TipoPeriodoService tipoPeriodoService;
	
	@Resource
	DatoProyectoService datoProyectoService;

	@Resource
	EvaluacionService evaluacionService;
	
	@Resource
	CuentaCorrienteService cuentaCorrienteService;
	
	@Resource
	ResumenProyectoService resumenProyectoService;
	
	@Resource
	UbicacionProyectoService ubicacionProyectoService;
	
	@Resource
	FuenteFinanciadoraService fuenteFinanciadoraService;
	
	@Resource
	TmpFuenteFinanciadoraService tmpFuenteFinanciadoraService;
	
	@Resource
	ObservacionService observacionService;
	
	
	// ****************** variables internas **********************//
	List<Evaluador> listEvaluador = null;
	List<DatoProyecto> listDatoProyecto;
	List<Programa> listaPrograma = null;
	String mensaje = "no";
	ProgramaBean2 programaBean = null;
	PuntajeEvaluacion puntajeEvaluacion;
	List<TipoPeriodo> listTipoPeriodos;
	List<TablaEspecifica> listTipoCuentas;
	List<TablaEspecifica> listModalidadFinanciera;
	List<TablaEspecifica> listTipoRestriccion;
	List<TablaEspecifica> listResultadoActividadObligatoria;
	List<Programa> listPrograma;
	/************** download ************************ */
	List<TablaEspecifica> tablaEspecificaListTipoFormatoArchivo = null;
	FileUploadDownload fileUploadDownload = new FileUploadDownload();
	TmpPerfil tmpPerfil = null;

	// ********************* metodos ***************************///
	@RequestMapping(value = "/principal/showEvaluacionInstitucional.jspx")
	public ModelAndView showEvaluacionInstitucional(HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		obtenerEvaluacion(userSession.getUsuarioID(),
				SgpWebConstants.evaluar.INSTITUCION, request);
		logger.info("evaluacion institucion");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("mensajeEvaluador", mensaje);
		model.put("evaluacion", SgpWebConstants.evaluar.INSTITUCION);
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("cargarProyecto", 0);
		model.put("funcionalidadSelect", "showEvaluacionInstitucional.jspx");
		return new ModelAndView("mostrarEvaluar", model);
	}

	@RequestMapping(value = "/principal/showEvaluacionTecnica.jspx")
	public ModelAndView showEvaluacionTecnica(HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		obtenerEvaluacion(userSession.getUsuarioID(),
				SgpWebConstants.evaluar.PERFIL, request);

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("mensajeEvaluador", mensaje);
		model.put("evaluacion", SgpWebConstants.evaluar.PERFIL);
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("cargarProyecto", 0);
		model.put("funcionalidadSelect", "showEvaluacionTecnica.jspx");
		return new ModelAndView("mostrarEvaluar", model);
	}

	@RequestMapping(value = "/principal/showEvaluacionProyecto.jspx")
	public ModelAndView showEvaluacionProyecto(HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);

		obtenerEvaluacion(userSession.getUsuarioID(),
				SgpWebConstants.evaluar.PROGRAMA, request);

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("mensajeEvaluador", "no");
		model.put("evaluacion", SgpWebConstants.evaluar.PROGRAMA);
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("cargarProyecto", 0);
		model.put("funcionalidadSelect", "showEvaluacionProyecto.jspx");
		// model.put("datoProyecto",datoProyecto);
		return new ModelAndView("mostrarEvaluar", model);
	}

	@RequestMapping(value = "/principal/actionBuscarProgramaEvaluacion")
	public ModelAndView actionBuscarProgramaEvaluacion(
			HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		Usuario usuario = evaluarService.findUsuarioById(userSession
				.getUsuarioID());
		int tipoEvaluacion = Integer.parseInt(request.getParameter("evalu"));
		Integer idTipoCuentas = Integer.parseInt(request.getParameter(
				"idTipoCuentas").toString());
		Integer idModalidadFinanciera = Integer.parseInt(request
				.getParameter("idModalidadFinanciera"));
		Integer idTipoPeriodos = Integer.parseInt(request
				.getParameter("idTipoPeriodos"));
		Integer todosProgramas = Integer.parseInt(request
				.getParameter("idFiltro"));
		int tipoPeriodo = 100;
		int tipoCuenta = 101;
		int Modalidad = 102;
		if (idTipoPeriodos != 0) {
			listEvaluador = evaluarService
					.findEvaluadorByTipoEvaluacionByFiltroPrograma(usuario
							.getDatoUsuario().getDatoUsuarioID(),
							tipoEvaluacion, tipoPeriodo, idTipoPeriodos);
		} else if (idTipoCuentas != 0) {
			listEvaluador = evaluarService
					.findEvaluadorByTipoEvaluacionByFiltroPrograma(usuario
							.getDatoUsuario().getDatoUsuarioID(),
							tipoEvaluacion, tipoCuenta, idTipoCuentas);
		} else if (idModalidadFinanciera != 0) {
			listEvaluador = evaluarService
					.findEvaluadorByTipoEvaluacionByFiltroPrograma(usuario
							.getDatoUsuario().getDatoUsuarioID(),
							tipoEvaluacion, Modalidad, idModalidadFinanciera);
		} else if (todosProgramas == 104) {
			listEvaluador = evaluarService
					.findEvaluadorByTipoEvaluacion(usuario.getDatoUsuario()
							.getDatoUsuarioID(), tipoEvaluacion);
		}
		listaPrograma = new ArrayList<Programa>();
		if (listEvaluador != null && listEvaluador.size() != 0) {
			for (Evaluador evalua : listEvaluador) {
				listaPrograma.add(evalua.getPrograma());
			}
			System.out.println(listaPrograma.get(0).getTipoPeriodo()
					.getDescripPeriodo());
			request.getSession().setAttribute("listaProgramaAndProyecto",
					listaPrograma);
			System.out.println(listaPrograma.get(0).getTipoPeriodo()
					.getDescripPeriodo());
		} else {
			request.getSession().removeAttribute("listaProgramaAndProyecto");
		}
		cargaCombos();
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("mensajeEvaluador", mensaje);
		model.put("evaluacion", tipoEvaluacion);
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("cargarProyecto", 0);

		return new ModelAndView("mostrarEvaluar", model);
	}

	@RequestMapping(value = "/principal/actionCargarProyectos")
	public ModelAndView actionCargarProyectos(HttpServletRequest request) {
		if (request.getSession().getAttribute("listaDatoProyecto") != null) {
			request.getSession().removeAttribute("listaDatoProyecto");
		}
		Integer idPrograma = Integer.parseInt(request
				.getParameter("idPrograma"));
		int evaluacion = Integer.parseInt(request.getParameter("evalu"));
		logger.info("evaluaaaaaaaaaaa:" + evaluacion);

		listDatoProyecto = new ArrayList<DatoProyecto>();
		if (evaluacion == SgpWebConstants.evaluar.INSTITUCION) {
			List<TmpFuenteFinanciadora> listTmpFFinanciadora = tablaTemporalService
					.findTmpFuenteFinanciadoraByTmpDatoProyectoByEstadoInstitucion(idPrograma);
			if (listTmpFFinanciadora != null
					&& listTmpFFinanciadora.size() != 0) {
				for (TmpFuenteFinanciadora tmpFuenteFinanciadora : listTmpFFinanciadora) {
					if(tmpFuenteFinanciadora.getTMPInstitucion().getFkIdDetalleEstadoCabEstInstitucion()!=SgpWebConstants.evaluarEstadoInstitucion.APROBADO){
					listDatoProyecto
							.add(convertirTmpDatoProyectoToDatoProyecto(tmpFuenteFinanciadora
									.getTMPDatoProyecto()));
					}
				}
			}
		} else if (evaluacion == SgpWebConstants.evaluar.PERFIL) {
			List<TmpDatoProyecto> lisTmpDatoProyecto = tablaTemporalService
					.findTmpDatoProyectoByEstadoPerfil(idPrograma);

			if (lisTmpDatoProyecto != null && lisTmpDatoProyecto.size() != 0) {
				for (TmpDatoProyecto tmpDatoProyecto : lisTmpDatoProyecto) {
					if (tmpDatoProyecto.getFlagPasoTablaNormal() == 0) {
						
						listDatoProyecto
								.add(convertirTmpDatoProyectoToDatoProyecto(tmpDatoProyecto));
					}
				}
			}
		} else if (evaluacion == SgpWebConstants.evaluar.PROGRAMA) {
			List<DatoProyecto> lisDatoProyecto = evaluarService
					.findDatoProyectoByProgramaID(idPrograma);
			if (lisDatoProyecto != null && lisDatoProyecto.size() != 0) {
				for (DatoProyecto datoProyecto : lisDatoProyecto) {
					if(datoProyecto.getFkIddetallestadocabEstproy()!= SgpWebConstants.evaluarEstadoProyecto.APROBADO){
						datoProyecto.setDescripcionAreaTematica(tablaEspecificaService.findTablaEspecificaById(datoProyecto.getSubAreaTematica().getFkIdtablaespAreaTematica()).getDescripcionCabecera());
						datoProyecto.setDescripcionSubAreaTematica(datoProyecto.getSubAreaTematica().getDescripcionSubAt());
					listDatoProyecto.add(datoProyecto);	
					}
				}
			}
		}
		if (listDatoProyecto != null && listDatoProyecto.size() != 0) {
			request.getSession().setAttribute("listTmpDatoProyecto",
					listDatoProyecto);
		} else {
			request.getSession().setAttribute("listTmpDatoProyecto", null);

		}
		List<TablaEspecifica> listaAreaTematica = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(4);
		ProgramaBean2 programaBean = llenarProgramaBean(crearProgramaService
				.findProgramaById(idPrograma));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("mensajeEvaluador", mensaje);
		model.put("evaluacion", evaluacion);
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("listAreaTematica", listaAreaTematica);
		model.put("cargarProyecto", 1);
		model.put("programaBean", programaBean);

		return new ModelAndView("mostrarEvaluar", model);
	}

	@RequestMapping(value = "/principal/actionMostrarInformacionEvaluacion")
	public ModelAndView actionMostrarInformacionEvaluacion(
			HttpServletRequest request) {
		Integer idProyecto = Integer.parseInt(request.getParameter("idProy"));
		int tipoEvaluacion = Integer.parseInt(request.getParameter("evalu"));

		//System.out.println("idProyecto:_:" + idProyecto + "evaluacion_::_"+ tipoEvaluacion);
		TmpInstitucion institucion;
		TmpPerfil perfil;
		DatoProyecto proyecto;
		Integer cantObservacionesRelevantes=0;

		int evaluado = 1;
		Map<String, Object> model = new HashMap<String, Object>();
		TmpEvaluacion tmpEvaluacion = new TmpEvaluacion();
		PuntajeEvaluacion puntajeEvaluacion = new PuntajeEvaluacion();

		if (tipoEvaluacion == SgpWebConstants.evaluar.INSTITUCION) {
			tmpEvaluacion = verificarTmpEvaluacion(request, tipoEvaluacion,
					idProyecto);
			TmpFuenteFinanciadora tmpFuenteFinanciadora = tablaTemporalService
					.findTmpFuenteFinanciadoraByTmpIdDatoProyecto(idProyecto);
			institucion = tablaTemporalService
					.findTmpInstitucionById(tmpFuenteFinanciadora
							.getTMPInstitucion().getTMPInstitucionID());
			puntajeEvaluacion = crearProgramaService
					.findPuntajeEvaluacionBytipoEvaluacionByProgramaID(
							tmpFuenteFinanciadora.getTMPDatoProyecto()
									.getPrograma().getProgramaID(),
							tipoEvaluacion);
			ProgramaBean2 programaBean = llenarProgramaBean(tmpFuenteFinanciadora
					.getTMPDatoProyecto().getPrograma());
			model.put("programaBean", programaBean);

			model.put("moneda", tablaEspecificaService
					.findTablaEspecificaById(tmpFuenteFinanciadora
							.getFkIdtablaespTipoMoneda()));
			model.put("fuenteFinanciadora", tablaEspecificaService
					.findTablaEspecificaById(tmpFuenteFinanciadora
							.getFkIdtablaespTipoFuenteFinanciadora()));
			model.put("montoFinanciado",
					tmpFuenteFinanciadora.getMontoFinanciado());
			model.put("institucion", institucion);
			model.put("cargarProyecto", SgpWebConstants.evaluar.INSTITUCION);
			model.put("mensajeEvaluador", mensaje);
		} else if (tipoEvaluacion == SgpWebConstants.evaluar.PERFIL) {
			tmpEvaluacion = verificarTmpEvaluacion(request, tipoEvaluacion,
					idProyecto);
			perfil = llenaTempPerfilConDetalle( tablaTemporalService
					.findTmpPerfilByTmpDatoProyectoID(idProyecto));
			puntajeEvaluacion = crearProgramaService
					.findPuntajeEvaluacionBytipoEvaluacionByProgramaID(
							perfil.getTMPDatoProyecto().getPrograma()
									.getProgramaID(), tipoEvaluacion);
			//List<TablaEspecifica> listTipoResumen = tablaEspecificaService.listTablaEspecificaByTablaGeneralId(31);
			// List<TmpActividadPerfil>
			// listaActividadPerfil=tablaTemporalService.findTmpActividadPerfilByPerfilID(perfil.getTMPPerfilID());
			List<TmpResumenProyecto> listTmpResumenProyecto = tablaTemporalService
					.findTmpResumenProyectoByTmpDatoProyectoID(idProyecto);
			ProgramaBean2 programaBean = llenarProgramaBean(perfil
					.getTMPDatoProyecto().getPrograma());
			model.put("programaBean", programaBean);
			model.put("perfil", perfil);
			model.put("listTmpResumenProyecto", listTmpResumenProyecto);
			//model.put("listTipoResumen", listTipoResumen);
			model.put("listTipoResumen", tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral("TRPF"));
			model.put("cargarProyecto", SgpWebConstants.evaluar.PERFIL);
			model.put("mensajeEvaluador", mensaje);
		}

		if (tipoEvaluacion == SgpWebConstants.evaluar.PROGRAMA) {

			List<TablaEspecifica> listaAreaTematica = tablaEspecificaService
					.listTablaEspecificaByTablaGeneralId(4);
			proyecto = evaluarService.findDatoProyectoById(idProyecto);
			proyecto.setCantObservacionesRelevantes(observacionService.findObservacionesRelevatesAlDocumento(proyecto.getDatoProyectoID(),proyecto.getDatoProyectoID(),FondamConstans.TABLA_CLASE_NOMBRE_DATO_PROYECTO));
			cantObservacionesRelevantes=proyecto.getCantObservacionesRelevantes();
			
			puntajeEvaluacion = crearProgramaService
					.findPuntajeEvaluacionBytipoEvaluacionByProgramaID(proyecto
							.getPrograma().getProgramaID(), tipoEvaluacion);
			Evaluacion evaluacion = verificarEvaluacion(request,
					tipoEvaluacion, idProyecto);

			if (proyecto.getFkIddetallestadocabEstproy() == 13
					|| proyecto.getFkIddetallestadocabEstproy() == 14) {
				evaluado = 2;
			}
			ProgramaBean2 programaBean = llenarProgramaBean(proyecto
					.getPrograma());
			model.put("programaBean", programaBean);
			model.put("nombreProyecto", proyecto.getNombreProyecto());
			model.put("duracionProyecto", proyecto.getDuracionProyecto());
			model.put("codigoProyecto", proyecto.getCodigoProyecto());
			model.put("proyecto", proyecto);
			model.put("descripcionSubAt", proyecto.getSubAreaTematica()
					.getDescripcionSubAt());
			/*System.out.println(proyecto.getSubAreaTematica().getFkIdtablaespAreaTematica());
			System.out.println(proyecto.getSubAreaTematica().getDescripcionSubAt());
			System.out.println(listaAreaTematica.size());*/
			model.put("listAreaTematica", listaAreaTematica);
			model.put("nombrePrograma", proyecto.getPrograma()
					.getNombrePrograma());
			model.put("duracionPrograma", proyecto.getPrograma()
					.getDuracionPrograma());
			model.put("descripPeriodo", proyecto.getPrograma().getTipoPeriodo()
					.getDescripPeriodo());
			model.put("cargarProyecto", SgpWebConstants.evaluar.PROGRAMA);
			model.put(
			"listCuentaCorrienteBean",
			cuentaCorrienteService.llenaListCuentasCorrientesBean(evaluarService
					.findCuentaCorrienteByIdDatoProyecto(idProyecto)));
			model.put("listResumenProyectoBean",
					resumenProyectoService.listadoResumenProyecto(idProyecto));
			
			model.put("listUbicacionProyecto", ubicacionProyectoService.llenaListUbicacionProyectoCompleto(ubicacionProyectoService.findUbicacionProyectoXDatoProyectoId(idProyecto)));
			model.put("listFuenteFinanciamiento",fuenteFinanciadoraService.llenaFuenteFinanciadoraCompleto(fuenteFinanciadoraService.findFuenteFinanciadoraByDatoProyectoId(idProyecto)));
	
			if (evaluacion != null) {
				model.put("evaluacionId", evaluacion.getEvaluacionID());
				model.put("calificacion", evaluacion.getCalificacion());
				model.put("observacionE", evaluacion.getObservacion());
				model.put("variable", evaluado);
				model.put("mensajeEvaluador", "Ya tiene calificación");
			} else {
				model.put("mensajeEvaluador", mensaje);
			}

		}

		if (tmpEvaluacion != null
				&& (tipoEvaluacion == SgpWebConstants.evaluar.PERFIL || tipoEvaluacion == SgpWebConstants.evaluar.INSTITUCION)) {
			model.put("evaluacionId", tmpEvaluacion.getTmpEvaluacionID());
			model.put("calificacion", tmpEvaluacion.getCalificacion());
			model.put("observacionE", tmpEvaluacion.getObservacion());
			model.put("variable", evaluado);
			model.put("mensajeEvaluador", "Ya tiene calificación");
		}
		if (puntajeEvaluacion != null) {
			model.put("maximo", puntajeEvaluacion.getMaximo());
			model.put("minimo", puntajeEvaluacion.getMinimo());
			model.put("minimoAprobatorio",
					puntajeEvaluacion.getMinimoAprobatorio());
		}

		model.put("evaluacion", tipoEvaluacion);
		model.put("idProyectoForm", idProyecto);
		model.put("cantObservacionesRelevantes", cantObservacionesRelevantes);
		
		return new ModelAndView("mostrarEvaluar", model);
	}

	@RequestMapping(value = "/principal/actionRegistrarEvaluacionInstitucion")
	public ModelAndView actionRegistrarEvaluacionInstitucion(
			@RequestParam(required = false, value = "evaluacionId") Integer evaluacionId,
			@RequestParam(required = false, value = "idProyecto") Integer idProyecto,
			@RequestParam(required = false, value = "calificacion") double calificacion,
			@RequestParam(required = false, value = "observacionE") String observacionE,
			HttpServletRequest request) {
		String mensaje = "";
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		Usuario usuario = evaluarService.findUsuarioById(userSession
				.getUsuarioID());
		int TipoEvaluacion = Integer.parseInt(request.getParameter("evalu"));
		/*Integer idProyecto = Integer.parseInt(request.getParameter("idProyectoForm"));
		double calificacion = Double.parseDouble(request.getParameter(
				"calificacion").toString());
		String observacionE = request.getParameter("observacionE") != "" ? request
				.getParameter("observacionE") : "Sin Observaciones";*/
		int estadoEvaluacion = 0;

		TmpDatoProyecto tmpDatoProyecto = tablaTemporalService 
				.findTmpDatoProyectoById(idProyecto);
		Programa programa = crearProgramaService
				.findProgramaById(tmpDatoProyecto.getPrograma().getProgramaID());
		PuntajeEvaluacion puntajeEvaluacion = crearProgramaService
				.findPuntajeEvaluacionBytipoEvaluacionByProgramaID(
						programa.getProgramaID(), TipoEvaluacion);

		Evaluador evaluador2 = evaluarService
				.findEvaluadorByDatoUsuarioIDAndTipoEvaluacionAndProyectoID(llenaEvaluador(
						TipoEvaluacion, usuario.getDatoUsuario(), programa));

		TmpEvaluacion tmpEvaluacion = tablaTemporalService
				.updateTmpEvaluacion(llenaTempEvaluacion(calificacion,
						observacionE, tmpDatoProyecto, evaluador2, evaluacionId));
		if (tmpEvaluacion != null) {
			mensaje = "Se registro exitosamente la evaluación";

		} else {
			mensaje = "No se registro la evaluación";

		}
		System.out
				.println("puntajeEvaluacion.getMinimoAprobatorio()>calificacion>"
						+ calificacion
						+ ">"
						+ puntajeEvaluacion.getMinimoAprobatorio());
		if (calificacion >= puntajeEvaluacion.getMinimoAprobatorio()) {
			estadoEvaluacion = 1;
		} else {
			estadoEvaluacion = 0;
		}
		updateEstadoTablaPorTipoEvaluacion(TipoEvaluacion, idProyecto,
				estadoEvaluacion);

		programaBean = llenarProgramaBean(programa);
/*  funciona pero no limpoia la grilla
 * 
 *
		List<TmpFuenteFinanciadora> listTmpFFinanciadora = tablaTemporalService
		.findTmpFuenteFinanciadoraByTmpDatoProyectoByEstadoInstitucion(programa.getProgramaID());
if (listTmpFFinanciadora != null
		&& listTmpFFinanciadora.size() != 0) {
	for (TmpFuenteFinanciadora tmpFuenteFinanciadora : listTmpFFinanciadora) {
		if(tmpFuenteFinanciadora.getTMPInstitucion().getFkIdDetalleEstadoCabEstInstitucion()!=SgpWebConstants.evaluarEstadoInstitucion.APROBADO){
		listDatoProyecto
				.add(convertirTmpDatoProyectoToDatoProyecto(tmpFuenteFinanciadora
						.getTMPDatoProyecto()));
		}
	}
}
*/
		
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listDatoProyecto", listDatoProyecto);
		model.put("evaluacion", TipoEvaluacion);
		model.put("mensajeEvaluador", mensaje);
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("programaBean", programaBean);
		model.put("flag", 1);// para verificar en el jsp en que estado esta
		return new ModelAndView("mostrarEvaluar", model);
	}

	@RequestMapping(value = "/principal/actionRegistrarEvaluacionPerfil")
	public ModelAndView actionRegistrarEvaluacionPerfil(
			@RequestParam(required = false, value = "evaluacionId") Integer evaluacionId,
			@RequestParam(required = false, value = "idProyecto") Integer idProyecto,
			@RequestParam(required = false, value = "calificacion") double calificacion,
			@RequestParam(required = false, value = "observacionE") String observacionE,
			HttpServletRequest request) {
		String mensaje = "";
		Integer flag=0;
		/*capturo datos*/
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		Usuario usuario = evaluarService.findUsuarioById(userSession
				.getUsuarioID());
		int tipoEvaluacion = Integer.parseInt(request.getParameter("evalu"));
		/*Integer idProyecto = Integer.parseInt(request.getParameter("idProyectoForm"));
		double calificacion = Double.parseDouble(request.getParameter(
				"calificacion").toString());
		String observacionE = request.getParameter("observacionE") != "" ? request
				.getParameter("observacionE") : "Sin Observaciones";*/
		int estadoEvaluacion = 0;
		// double notaPerfil = 0;

		/*calculo y grabado de evaluacion*/
		TmpDatoProyecto tmpDatoProyecto = tablaTemporalService
				.findTmpDatoProyectoById(idProyecto);
		Programa programa = crearProgramaService
				.findProgramaById(tmpDatoProyecto.getPrograma().getProgramaID());
		PuntajeEvaluacion puntajeEvaluacion = crearProgramaService
				.findPuntajeEvaluacionBytipoEvaluacionByProgramaID(
						programa.getProgramaID(), tipoEvaluacion);

		Evaluador evaluador2 = evaluarService
				.findEvaluadorByDatoUsuarioIDAndTipoEvaluacionAndProyectoID(llenaEvaluador(
						tipoEvaluacion, usuario.getDatoUsuario(), programa));

		TmpEvaluacion tmpEvaluacion = tablaTemporalService
				.updateTmpEvaluacion(llenaTempEvaluacion(calificacion,
						observacionE, tmpDatoProyecto, evaluador2, evaluacionId));
		if (tmpEvaluacion != null) {
			mensaje = "Se registro exitosamente la evaluación";
			flag=1;

		} else {
			mensaje = "No se registro la evaluación";

		}

		List<TmpEvaluacion> listTmpEvaluacion = tablaTemporalService
				.findListTmpEvaluacionByTmpDatoProyectoIDByTipoEvaluacion(
						idProyecto, tipoEvaluacion);

		/*calculo de promedios y pase de proyecto a normales*/
		double promedioNotas = 0;
		if (listTmpEvaluacion.size() == 2) {
			double diferenciaNotas = 0;
			//double promedioNotas = 0;

			for (TmpEvaluacion tmpEvaluacion2 : listTmpEvaluacion) {
				diferenciaNotas = Math.abs(diferenciaNotas
						- tmpEvaluacion2.getCalificacion());
				promedioNotas = promedioNotas
						+ tmpEvaluacion2.getCalificacion();
			}

			promedioNotas = promedioNotas / listTmpEvaluacion.size();
			if (promedioNotas >= puntajeEvaluacion.getMinimoAprobatorio()) {
				estadoEvaluacion = 1;
			} else {
				estadoEvaluacion = 0;
			}

			if (diferenciaNotas < 10) {
				updateEstadoTablaPorTipoEvaluacion(tipoEvaluacion,idProyecto, estadoEvaluacion);
				if (estadoEvaluacion == 1) {
					tablaTemporalService
							.saveInformacionTMPatablas(tmpDatoProyecto
									.getTMPDatoProyectoID());
					flag=2;
					//updateEstadoTablaPorTipoEvaluacion(tipoEvaluacion,idProyecto, estadoEvaluacion);
					mensaje = mensaje
							+ "\n El perfil aprobo la evaluacion total.";					
				} else {
					flag=1;
					mensaje = mensaje
							+ "\n El perfil desaprobo la evaluacion total.";
				}
				
			} else {
				//mensaje = mensaje + "\n Se requiere un tercer evaluador";
				flag=1;
			}
		} else if (listTmpEvaluacion.size() == 3) {
			int diferenciaMenor = 0;
			List<NotasDiferencia> listNotasDiferencia = new ArrayList<NotasDiferencia>();
			
			NotasDiferencia notasDiferencia;
			for (TmpEvaluacion tmpEvaluacion2 : listTmpEvaluacion) {
				notasDiferencia= new NotasDiferencia();
				notasDiferencia.setNota01(tmpEvaluacion2.getCalificacion());
				notasDiferencia.setNota01EvaluacionId(tmpEvaluacion2
						.getTmpEvaluacionID());

				for (TmpEvaluacion tmpEvaluacion3 : listTmpEvaluacion) {
					notasDiferencia.setNota02(tmpEvaluacion3.getCalificacion());
					notasDiferencia.setNota02EvaluacionId(tmpEvaluacion3
							.getTmpEvaluacionID());

					notasDiferencia.setDiferencia((int) Math.abs(tmpEvaluacion2
							.getCalificacion()
							- tmpEvaluacion3.getCalificacion()));
					
					int flagIngresa = 0;
					/*revisar aqui*/
					if (notasDiferencia.getNota01EvaluacionId()!= notasDiferencia.getNota02EvaluacionId() ){
					
					for (NotasDiferencia notasDiferencia2 : listNotasDiferencia) {
						if (((notasDiferencia.getNota01EvaluacionId() == notasDiferencia2.getNota01EvaluacionId())
								&& (notasDiferencia.getNota02EvaluacionId() == notasDiferencia2.getNota02EvaluacionId()))
								|| ((notasDiferencia.getNota02EvaluacionId() == notasDiferencia2.getNota01EvaluacionId())
								&& (notasDiferencia.getNota02EvaluacionId() == notasDiferencia2.getNota01EvaluacionId()))) {
							flagIngresa = 1;
							break;
						}
					}
					
					if (flagIngresa == 0) {
						listNotasDiferencia.add(notasDiferencia);
						notasDiferencia = new NotasDiferencia();
						notasDiferencia.setNota01(tmpEvaluacion2.getCalificacion());
						notasDiferencia.setNota01EvaluacionId(tmpEvaluacion2
								.getTmpEvaluacionID());
					}
					}
				}
			}
			diferenciaMenor=listNotasDiferencia.get(0).getDiferencia();
			for (NotasDiferencia notasDiferenciaTemp : listNotasDiferencia) {
				if (diferenciaMenor > notasDiferenciaTemp.getDiferencia()){
					diferenciaMenor=notasDiferenciaTemp.getDiferencia();
				}
			}

			notasDiferencia= new NotasDiferencia();
			for (NotasDiferencia notasDiferencia02 : listNotasDiferencia) {
				if (notasDiferencia02.getDiferencia()==diferenciaMenor){
					notasDiferencia=notasDiferencia02;
				}
			}
			
			promedioNotas = (notasDiferencia.getNota01() + notasDiferencia.getNota02()) / 2;
			if (promedioNotas >= puntajeEvaluacion.getMinimoAprobatorio()) {
				estadoEvaluacion = 1;
			} else {
				estadoEvaluacion = 0;
			}
				if (estadoEvaluacion == 1) {
					updateEstadoTablaPorTipoEvaluacion(tipoEvaluacion,
							idProyecto, estadoEvaluacion);
					tablaTemporalService
							.saveInformacionTMPatablas(tmpDatoProyecto
									.getTMPDatoProyectoID());
					
					mensaje = mensaje
							+ "\n El perfil aprobo la evaluacion total.";
					flag=2;
				} else {
					mensaje = mensaje
							+ "\n El perfil desaprobo la evaluacion total.";
					flag=1;
				}
		}

		programaBean = llenarProgramaBean(programa);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("evaluacion", tipoEvaluacion);
		model.put("mensajeEvaluador", mensaje);
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("programaBean", programaBean);
		model.put("flag", flag);// para verificar en el jsp en que estado esta
		return new ModelAndView("mostrarEvaluar", model);
	}

	@RequestMapping(value = "/principal/actionRegistrarEvaluacionProyecto")
	public ModelAndView actionRegistrarEvaluacionProyecto(
			@RequestParam(required = false, value = "evaluacionId") Integer evaluacionId,
			@RequestParam(required = false, value = "idProyecto") Integer idProyecto,
			@RequestParam(required = false, value = "calificacion") double calificacion,
			@RequestParam(required = false, value = "observacionE") String observacionE,
			HttpServletRequest request) {
		String mensaje = "";
		/*capturo datos*/
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		Usuario usuario = evaluarService.findUsuarioById(userSession
				.getUsuarioID());
		int tipoEvaluacion = Integer.parseInt(request.getParameter("evalu"));
		/*Integer idProyecto = Integer.parseInt(request.getParameter("idProyectoForm"));
		double calificacion = Double.parseDouble(request.getParameter(
				"calificacion").toString());
		String observacionE = request.getParameter("observacionE") != "" ? request
				.getParameter("observacionE") : "Sin Observaciones";*/
		int estadoEvaluacion = 0;
		// double notaPerfil = 0;

		/*calculo y grabado de evaluacion*/
		DatoProyecto datoProyecto =evaluarService
		.findDatoProyectoById(idProyecto);
		Programa programa = crearProgramaService
				.findProgramaById(datoProyecto.getPrograma().getProgramaID());
		PuntajeEvaluacion puntajeEvaluacion = crearProgramaService
				.findPuntajeEvaluacionBytipoEvaluacionByProgramaID(
						programa.getProgramaID(), tipoEvaluacion);

		Evaluador evaluador2 = evaluarService
				.findEvaluadorByDatoUsuarioIDAndTipoEvaluacionAndProyectoID(llenaEvaluador(
						tipoEvaluacion, usuario.getDatoUsuario(), programa));

		Evaluacion evaluacion = evaluarService.updateEvaluacion((llenaEvaluacion(calificacion,
						observacionE, datoProyecto, evaluador2, evaluacionId)));
		if (evaluacion != null) {
			mensaje = "Se registro exitosamente la evaluación";

		} else {
			mensaje = "No se registro la evaluación";

		}

		List<Evaluacion> listEvaluacion =evaluarService.findEvaluacionByDatoProyectoIDAndTipoEvaluacionId(idProyecto, tipoEvaluacion);

		/*calculo de promedios y pase de proyecto a normales*/
		double promedioNotas = 0;
		if (listEvaluacion.size() == 2) {
			double diferenciaNotas = 0;
			//double promedioNotas = 0;

			for (Evaluacion evaluacion2 : listEvaluacion) {
				diferenciaNotas = Math.abs(diferenciaNotas
						- evaluacion2.getCalificacion());
				promedioNotas = promedioNotas
						+ evaluacion2.getCalificacion();
			}

			promedioNotas = promedioNotas / listEvaluacion.size();
			if (promedioNotas >= puntajeEvaluacion.getMinimoAprobatorio()) {
				estadoEvaluacion = 1;
			} else {
				estadoEvaluacion = 0;
			}

			if (diferenciaNotas < 10) {
				if (estadoEvaluacion == 1) {
					//aprobo proyecto
					//datoProyecto.setFkIddetallestadocabEstproy(SgpWebConstants.evaluarEstadoProyecto.APROBADO);
					//datoProyectoService.updateDatoProyecto(datoProyecto);
					mensaje = mensaje
							+ "\n El Proyecto aprobo la evaluacion total.";
				} else {
					//desaprobo proyecto
					//datoProyecto.setFkIddetallestadocabEstproy(SgpWebConstants.evaluarEstadoProyecto.RECHAZADO);
					//datoProyectoService.updateDatoProyecto(datoProyecto);
					
					mensaje = mensaje
							+ "\n El proyecto desaprobo la evaluacion total.";
				}
				
			} /*else {
				mensaje = mensaje + "\n Se requiere un tercer evaluador";
			}*/
		} else if (listEvaluacion.size() == 3) {
			int diferenciaMenor = 0;
			List<NotasDiferencia> listNotasDiferencia = new ArrayList<NotasDiferencia>();
			
			NotasDiferencia notasDiferencia;
			for (Evaluacion evaluacion2 : listEvaluacion) {
				notasDiferencia= new NotasDiferencia();
				notasDiferencia.setNota01(evaluacion2.getCalificacion());
				notasDiferencia.setNota01EvaluacionId(evaluacion2
						.getEvaluacionID());

				for (Evaluacion evaluacion3 : listEvaluacion) {
					notasDiferencia.setNota02(evaluacion3.getCalificacion());
					notasDiferencia.setNota02EvaluacionId(evaluacion3
							.getEvaluacionID());

					notasDiferencia.setDiferencia((int) Math.abs(evaluacion2
							.getCalificacion()
							- evaluacion3.getCalificacion()));
					
					int flagIngresa = 0;
					/*revisar aqui*/
					if (notasDiferencia.getNota01EvaluacionId()!= notasDiferencia.getNota02EvaluacionId() ){
					
					for (NotasDiferencia notasDiferencia2 : listNotasDiferencia) {
						if (((notasDiferencia.getNota01EvaluacionId() == notasDiferencia2.getNota01EvaluacionId())
								&& (notasDiferencia.getNota02EvaluacionId() == notasDiferencia2.getNota02EvaluacionId()))
								|| ((notasDiferencia.getNota02EvaluacionId() == notasDiferencia2.getNota01EvaluacionId())
								&& (notasDiferencia.getNota02EvaluacionId() == notasDiferencia2.getNota01EvaluacionId()))) {
							flagIngresa = 1;
							break;
						}
					}
					
					if (flagIngresa == 0) {
						listNotasDiferencia.add(notasDiferencia);
						notasDiferencia = new NotasDiferencia();
						notasDiferencia.setNota01(evaluacion2.getCalificacion());
						notasDiferencia.setNota01EvaluacionId(evaluacion2
								.getEvaluacionID());
					}
					}
				}
			}
			diferenciaMenor=listNotasDiferencia.get(0).getDiferencia();
			for (NotasDiferencia notasDiferenciaTemp : listNotasDiferencia) {
				if (diferenciaMenor > notasDiferenciaTemp.getDiferencia()){
					diferenciaMenor=notasDiferenciaTemp.getDiferencia();
				}
			}

			notasDiferencia= new NotasDiferencia();
			for (NotasDiferencia notasDiferencia02 : listNotasDiferencia) {
				if (notasDiferencia02.getDiferencia()==diferenciaMenor){
					notasDiferencia=notasDiferencia02;
				}
			}
			
			promedioNotas = (notasDiferencia.getNota01() + notasDiferencia.getNota02()) / 2;
			if (promedioNotas >= puntajeEvaluacion.getMinimoAprobatorio()) {
				estadoEvaluacion = 1;
			} else {
				estadoEvaluacion = 0;
			}
				if (estadoEvaluacion == 1) {
					
					//aprobo proyecto
					//datoProyecto.setFkIddetallestadocabEstproy(SgpWebConstants.evaluarEstadoProyecto.APROBADO);
					//datoProyectoService.updateDatoProyecto(datoProyecto);
					
					mensaje = mensaje
							+ "\n El proyecto aprobo la evaluacion total.";
				} else {
					//desaprobo proyecto
					//datoProyecto.setFkIddetallestadocabEstproy(SgpWebConstants.evaluarEstadoProyecto.RECHAZADO);
					//datoProyectoService.updateDatoProyecto(datoProyecto);
					
					mensaje = mensaje
							+ "\n El proyecto desaprobo la evaluacion total.";
				}
			}

		programaBean = llenarProgramaBean(programa);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("evaluacion", tipoEvaluacion);
		model.put("mensajeEvaluador", mensaje);
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("programaBean", programaBean);
		model.put("flag", 1);// para verificar en el jsp en que estado esta
		return new ModelAndView("mostrarEvaluar", model);
	}
	
	
//*********** download ************
	@RequestMapping(value = "/principal/showImagenArchivoDownloadEvaluarPerfil.jspx")
	public ModelAndView showImagenArchivoDownloadEvaluarPerfil(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> model = new HashMap<String, Object>();

		try {
			Integer idProyecto = Integer.parseInt(request
					.getParameter("idProyectoForm"));

			tmpPerfil = tablaTemporalService
					.findTmpPerfilByTmpDatoProyectoID(idProyecto);
			tablaEspecificaListTipoFormatoArchivo = tablaEspecificaService
					.listTablaEspecificaByTablaGeneralId(40);// listado de
																// extensiones
			List<TmpImagenOArchivo> tmpImagenOArchivo = tablaTemporalService
					.findTmpImagenOArchivoByPerfilByID(tmpPerfil
							.getTMPPerfilID());// listado de archivos de acuerdo
												// a un perfil

			List<LinkFile> listLinkFile = new ArrayList<LinkFile>();
			LinkFile linkFile;
			if (tmpImagenOArchivo.get(0) != null) {
				linkFile = new LinkFile();
				linkFile.setId(tmpImagenOArchivo.get(0)
						.getTmpImagenOArchivoID());
				linkFile.setNombre(tmpImagenOArchivo.get(0)
						.getDescripcionDocImg());
				String extension = fileUploadDownload.getArchivoExtensionById(
						tablaEspecificaListTipoFormatoArchivo,
						tmpImagenOArchivo.get(0).getFkIdtablaespTipoArchivo());
				linkFile.setExtension(extension);
				listLinkFile.add(linkFile);
			}
			
			model.put("listLinkFile", listLinkFile);
			model.put("cantLinkFile", listLinkFile.size());

			model.put("variable",
					"downloadImagenArchivoDownloadEvaluarPerfil.jspx");
		} catch (Exception e) {
			logger.info("error en showImagenArchivoDownloadEvaluarPerfil.jspx");
		}

		return new ModelAndView("mostrarArchivoDownload", model);
	}

	@RequestMapping(value = "/principal/downloadImagenArchivoDownloadEvaluarPerfil.jspx")
	public void downloadImagenArchivoDownloadEvaluarPerfil(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String idArchivo = request.getParameter("param");
		TmpImagenOArchivo imagenOArchivo = new TmpImagenOArchivo();
		
		imagenOArchivo = tablaTemporalService.findTmpImagenOArchivoById(Integer
				.parseInt(idArchivo));
		String extension = tablaEspecificaService.findTablaEspecificaById(imagenOArchivo.getFkIdtablaespTipoArchivo()).getDescripcionCabecera();
		String archivo = imagenOArchivo.getDescripcionDocImg() +"."+ extension;
		fileUploadDownload.downloadArchivo(request, response, archivo, imagenOArchivo.getImagen(), extension);
	}

	
	// ****************** listas y metodos internos ********************///
	private Evaluacion llenaEvaluacion(Double calificacion,
			String observacionE, DatoProyecto datoProyecto,
			Evaluador evaluador2,Integer evaluacionId) {

		Evaluacion evaluacion = new Evaluacion();
		if (evaluacionId!=null){
			evaluacion= evaluacionService.findEvaluacionByID(evaluacionId);
		}
		evaluacion.setCalificacion(calificacion);
		evaluacion.setObservacion(!observacionE.equals("")  ? observacionE : "Sin Observaciones");
		evaluacion.setDatoProyecto(datoProyecto);
		evaluacion.setEvaluador(evaluador2);
		return evaluacion;
	}
	
	private TmpEvaluacion llenaTempEvaluacion(Double calificacion,
			String observacionE, TmpDatoProyecto tmpDatoProyecto,
			Evaluador evaluador2, Integer evaluacionId) {

		TmpEvaluacion tmpEvaluacion = new TmpEvaluacion();
		if (evaluacionId!=null){
			tmpEvaluacion= tablaTemporalService.findTmpEvaluacionById(evaluacionId);
		}
			tmpEvaluacion.setCalificacion(calificacion);
			tmpEvaluacion.setObservacion(!observacionE.equals("")  ? observacionE : "Sin Observaciones");
			tmpEvaluacion.setTmpDatoProyectoID(tmpDatoProyecto);
			tmpEvaluacion.setEvaluadorID(evaluador2);
		
		return tmpEvaluacion;
	}

	private Evaluador llenaEvaluador(int tipoEvaluacion,
			DatoUsuario datoUsuario, Programa programa) {

		Evaluador evaluador = new Evaluador();
		evaluador.setFkIdtablaespTipoEvaluacion(tipoEvaluacion);
		evaluador.setDatoUsuario(datoUsuario);
		evaluador.setPrograma(programa);

		return evaluador;
	}

	void cargaCombos() {
		listTipoPeriodos = crearProgramaService.listTipoPeriodos();
		listTipoCuentas = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(1);
		listModalidadFinanciera = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(2);
	}

	void obtenerEvaluacion(Integer idUsuario, Integer tipoEvaluacion,
			HttpServletRequest request) {

		Usuario usuario = evaluarService.findUsuarioById(idUsuario);
		listEvaluador = evaluarService.findEvaluadorByTipoEvaluacion(usuario
				.getDatoUsuario().getDatoUsuarioID(), tipoEvaluacion);
		listaPrograma = new ArrayList<Programa>();
		if (listEvaluador != null && listEvaluador.size() != 0) {
			mensaje = "no";

			for (Evaluador evalua : listEvaluador) {
				listaPrograma.add(evalua.getPrograma());
			}
			request.getSession().setAttribute("listaProgramaAndProyecto",
					listaPrograma);
		} else {
			request.getSession().removeAttribute("listaProgramaAndProyecto");
			mensaje = "Usted no tiene asignado ningun proyecto para revisar";

		}
		cargaCombos();
	}

	private ProgramaBean2 llenarProgramaBean(Programa programa) {
		ProgramaBean2 programaBean = new ProgramaBean2();

		programaBean.setDuracionPrograma(programa.getDuracionPrograma());
		programaBean.setFkIdtablaespModalidadFinancia(programa
				.getFkIdtablaespModalidadFinancia());
		programaBean.setFkIdtablaespTipoCuenta(programa
				.getFkIdtablaespTipoCuenta());
		programaBean.setModalidadFinancia(tablaEspecificaService
				.findTablaEspecificaById(
						programa.getFkIdtablaespModalidadFinancia())
				.getDescripcionCabecera());
		programaBean.setNombrePrograma(programa.getNombrePrograma());
		programaBean.setNombreTipoPeriodo(tipoPeriodoService
				.findTipoPeriodoById(
						programa.getTipoPeriodo().getTipoPeriodoID())
				.getDescripPeriodo());

		return programaBean;
	}

	DatoProyecto convertirTmpDatoProyectoToDatoProyecto(
			TmpDatoProyecto tmpDatoProyecto) {

		DatoProyecto dtoProyecto = new DatoProyecto();
		dtoProyecto.setDatoProyectoID(tmpDatoProyecto.getTMPDatoProyectoID());
		dtoProyecto.setNombreProyecto(tmpDatoProyecto.getNombreProyecto());
		dtoProyecto.setCodigoProyecto(tmpDatoProyecto.getCodigoProyecto());
		dtoProyecto.setCantidadPeriodo(tmpDatoProyecto.getCantidadPeriodo());
		dtoProyecto.setDuracionProyecto(tmpDatoProyecto.getDuracionProyecto());
		dtoProyecto.setPrograma(tmpDatoProyecto.getPrograma());
		dtoProyecto.setDescripcionAreaTematica(tablaEspecificaService.findTablaEspecificaById(tmpDatoProyecto.getSubAreaTematica().getFkIdtablaespAreaTematica()).getDescripcionCabecera());
		dtoProyecto.setDescripcionSubAreaTematica(tmpDatoProyecto.getSubAreaTematica().getDescripcionSubAt());

		return dtoProyecto;

	}

	private Evaluacion verificarEvaluacion(HttpServletRequest request,
			int tipoEvaluacion, Integer idProyecto) {
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		Usuario usuario = evaluarService.findUsuarioById(userSession
				.getUsuarioID());

		DatoProyecto datoProyecto = evaluarService
				.findDatoProyectoById(idProyecto);
		Programa programa = crearProgramaService.findProgramaById(datoProyecto
				.getPrograma().getProgramaID());

		Evaluador evaluador = new Evaluador();
		evaluador.setFkIdtablaespTipoEvaluacion(tipoEvaluacion);
		evaluador.setDatoUsuario(usuario.getDatoUsuario());
		evaluador.setPrograma(programa);
		Evaluador evaluador2 = evaluarService
				.findEvaluadorByDatoUsuarioIDAndTipoEvaluacionAndProyectoID(evaluador);

		Evaluacion eval = new Evaluacion();
		eval.setEvaluador(evaluador2);
		eval.setDatoProyecto(datoProyecto);

		return evaluarService.findEvaluacion(eval);
	}

	TmpEvaluacion verificarTmpEvaluacion(HttpServletRequest request,
			int evaluacion, int idProyecto) {
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);
		Usuario usuario = evaluarService.findUsuarioById(userSession
				.getUsuarioID());

		TmpDatoProyecto tmpDatoProyecto = tablaTemporalService
				.findTmpDatoProyectoById(idProyecto);
		Programa programa = crearProgramaService
				.findProgramaById(tmpDatoProyecto.getPrograma().getProgramaID());

		/*Evaluador evaluador = new Evaluador();
		evaluador.setFkIdtablaespTipoEvaluacion(evaluacion);
		evaluador.setDatoUsuario(usuario.getDatoUsuario());
		evaluador.setPrograma(programa);*/
		Evaluador evaluador2 = evaluarService
				.findEvaluadorByDatoUsuarioIDAndTipoEvaluacionAndProyectoID(llenaEvaluador( evaluacion, usuario.getDatoUsuario(), programa));

		TmpEvaluacion tmpEval = new TmpEvaluacion();
		tmpEval.setEvaluadorID(evaluador2);
		tmpEval.setTmpDatoProyectoID(tmpDatoProyecto);

		return tablaTemporalService.findTmpEvaluacion(tmpEval);
	}

	private void updateEstadoTablaPorTipoEvaluacion(int tipoEvaluacion,
			Integer idProyecto, int estadoEvaluacion) {
		System.out.println("estadoEvaluacion::" + estadoEvaluacion);
		if (tipoEvaluacion == SgpWebConstants.evaluar.INSTITUCION) {
			TmpFuenteFinanciadora tmpFuenteFinanciadora = tablaTemporalService
					.findTmpFuenteFinanciadoraByTmpIdDatoProyecto(idProyecto);
			TmpInstitucion tmpInstituc = tablaTemporalService
					.findTmpInstitucionById(tmpFuenteFinanciadora
							.getTMPInstitucion().getTMPInstitucionID());
			if (estadoEvaluacion == 1) {
				// aprobado
				tmpInstituc.setFkIdDetalleEstadoCabEstInstitucion(SgpWebConstants.evaluarEstadoInstitucion.APROBADO);
			} else {
				// rechazado
				tmpInstituc.setFkIdDetalleEstadoCabEstInstitucion(SgpWebConstants.evaluarEstadoInstitucion.RECHAZADO);
			}
			tablaTemporalService.updateTmpInstitucion(tmpInstituc);
		} else if (tipoEvaluacion == SgpWebConstants.evaluar.PERFIL) {
			TmpPerfil tmpPerfil = tablaTemporalService
					.findTmpPerfilByTmpDatoProyectoID(idProyecto);
			if (estadoEvaluacion == 1) {
				// aprobado
				tmpPerfil.setFkIddetallestadocabEstperfil(SgpWebConstants.evaluarEstadoPerfil.APROBADO);
			} else {
				// rechazado
				tmpPerfil.setFkIddetallestadocabEstperfil(SgpWebConstants.evaluarEstadoPerfil.RECHAZADO);
			}
			tablaTemporalService.updateTmpPerfil(tmpPerfil);
		}

	}

	private TmpPerfil llenaTempPerfilConDetalle(
			TmpPerfil tmpPerfil) {

		tmpPerfil.setListTmpFuenteFinanciadoraCofinanciador(tmpFuenteFinanciadoraService.findTmpFuenteFinanciadoraByDatoProyectoIdByCofinanciador(tmpPerfil.getTMPDatoProyecto().gettMPDatoProyectoID(), SgpWebConstants.tipoFuenteFinanciadora.cofinanciador));
		tmpPerfil.setListTmpFuenteFinanciadoraContrapartida(tmpFuenteFinanciadoraService.findTmpFuenteFinanciadoraByDatoProyectoIdByContrapartida(tmpPerfil.getTMPDatoProyecto().gettMPDatoProyectoID(), SgpWebConstants.tipoFuenteFinanciadora.cofinanciador,SgpWebConstants.tipoFuenteFinanciadora.fondam));
		
		return tmpPerfil;
	}
}
