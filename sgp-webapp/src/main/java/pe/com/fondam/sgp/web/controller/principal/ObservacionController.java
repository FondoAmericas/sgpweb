package pe.com.fondam.sgp.web.controller.principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.bean.DatoProyectoBean;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.Observacion;
import pe.com.fondam.sgp.core.domain.Usuario;
import pe.com.fondam.sgp.core.service.CrearProgramaService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DatoUsuarioService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.EvaluarService;
import pe.com.fondam.sgp.core.service.FuenteFinanciadoraService;
import pe.com.fondam.sgp.core.service.InstalarComiteTecnicoService;
import pe.com.fondam.sgp.core.service.InstitucionService;
import pe.com.fondam.sgp.core.service.ObservacionService;
import pe.com.fondam.sgp.core.service.TablaClaseService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.TablaProfundidadesService;
import pe.com.fondam.sgp.core.service.UsuarioService;
import pe.com.fondam.sgp.core.util.UtilList;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller

public class ObservacionController {
	protected final Log logger = LogFactory.getLog(PerfilController.class);
	
	//************ inyecciones ***********************//
	@Resource
	CrearProgramaService crearProgramaService;

	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	EvaluarService evaluarService;
	
	@Resource
	InstalarComiteTecnicoService instalarComiteTecnicoService;
	
	@Resource
	ObservacionService observacionService;
	
	@Resource
	TablaClaseService tablaClaseService;

	@Resource
	TablaProfundidadesService tablaProfundidadesService;
	
	@Resource
	DatoProyectoService datoProyectoService;
	
	@Resource
	UsuarioService usuarioService;
	
	@Resource
	FuenteFinanciadoraService fuenteFinanciadoraService;
	
	@Resource
	InstitucionService institucionService;
	
	@Resource
	DatoUsuarioService datoUsuarioService;
	
	Map<String, Object> model = new HashMap<String, Object>();
	
	//************ metodos ***********************//
	@RequestMapping(value = "/principal/showGestionarObservacion")
	public ModelAndView showGestionarObservacion(
			@RequestParam(required = true, value = "tablaId") Integer tablaId,
			@RequestParam(required = true, value = "datoProyectoID") Integer datoProyectoID,
			@RequestParam(required = true, value = "tablaClaseId") Integer tablaClaseId,
			@RequestParam(required = true, value = "tablaProfundidadId") Integer tablaProfundidadId,
			@RequestParam(required = true, value = "claseId") Integer claseId,
			HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		
		UserSession userSession = (UserSession) request.getSession()
		.getAttribute(SgpWebConstants.SESSION_USER);
		
		model.put("userSession", userSession);
		model.put("listTipoObservacion", tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral("TPOB"));
		model.put("listEstadoAtencion", detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estatob"));
		model.put("datoProyectoID", datoProyectoID);
		 SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
				   Date fechaDate = new Date();
				   String fechaObservacion = formateador.format(fechaDate);
		model.put("fechaObservacion", fechaObservacion);
		model.put("estado", -1);
		model.put("tablaId", tablaId);
		model.put("tablaClaseId", tablaClaseId);
		model.put("tablaClase", tablaClaseService.findTablaClaseById(tablaClaseId));
		model.put("tablaProfundidadId", tablaProfundidadId);
		model.put("profundidad",tablaProfundidadesService.findProfundidadText(tablaProfundidadesService.findTablaProfundidadesServiceById(tablaProfundidadId)));
		model.put("claseId",claseId);	
		return new ModelAndView("crearObservacion", model);
	}

	@RequestMapping(value = "/principal/gestionarObservacion")
	public ModelAndView gestionarObservacion(
			/*
			@RequestParam(required = true, value = "tablaId") Integer tablaId,
			@RequestParam(required = true, value = "datoProyectoID") Integer datoProyectoID,
			@RequestParam(required = true, value = "tablaClaseId") Integer tablaClaseId,
			@RequestParam(required = true, value = "tablaProfundidadId") Integer tablaProfundidadId,
			@RequestParam(required = true, value = "claseId") Integer claseId,*/
			HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		
		UserSession userSession = (UserSession) request.getSession()
		.getAttribute(SgpWebConstants.SESSION_USER);
		
		model.put("userSession", userSession);
		model.put("listTipoObservacion", tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral("TPOB"));
		model.put("listEstadoAtencion", detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estatob"));
		//model.put("datoProyectoID", datoProyectoID);
		 SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
				   Date fechaDate = new Date();
				   String fechaObservacion = formateador.format(fechaDate);
		model.put("fechaObservacion", fechaObservacion);
		model.put("estado", -1);
		/*model.put("tablaId", tablaId);
		model.put("tablaClaseId", tablaClaseId);
		model.put("tablaClase", tablaClaseService.findTablaClaseById(tablaClaseId));
		model.put("tablaProfundidadId", tablaProfundidadId);
		model.put("profundidad",tablaProfundidadesService.findProfundidadText(tablaProfundidadesService.findTablaProfundidadesServiceById(tablaProfundidadId)));
		model.put("claseId",claseId);*/	
		return new ModelAndView("divGestionarObservacion", model);
	}

	@RequestMapping(value = "/principal/cargaGrillaObservacion")
	public ModelAndView cargaGrillaObservacion(
			@RequestParam(required = true, value = "datoProyectoID") Integer datoProyectoID,
			HttpServletRequest request) {

		UserSession userSession = (UserSession) request.getSession()
		.getAttribute(SgpWebConstants.SESSION_USER);
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("listObservaciones",UtilList.orderDescList(observacionService.llenaListObservacionesCompleto( observacionService.findObservacionesByDatoProyectoId(datoProyectoID)),"flagEstado"));
		model.put("userSession", userSession);
		
		return new ModelAndView("divGrillaObservacion", model);
	}
	
	@RequestMapping(value = "/principal/cargaGrillaObservacionAtendidas")
	public ModelAndView cargaGrillaObservacionAtendidas(
			@RequestParam(required = true, value = "datoProyectoID") Integer datoProyectoID,
			@RequestParam(required = true, value = "fechaActual") String fechaActual,
			HttpServletRequest request) {

		UserSession userSession = (UserSession) request.getSession()
		.getAttribute(SgpWebConstants.SESSION_USER);
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("listObservaciones",UtilList.orderDescList(observacionService.llenaListObservacionesCompleto( observacionService.findObservacionesByDatoProyectoId(datoProyectoID)),"flagEstado"));
		model.put("userSession", userSession);
		model.put("fechaActual",fechaActual);
		
		return new ModelAndView("divGrillaObservacionesAtendidas", model);
	}

	@RequestMapping(value = "/security/cargaGrillaObservacionEjecutor")
	public ModelAndView cargaGrillaObservacionEjecutor(
			@RequestParam(required = true, value = "datoProyectoID") Integer datoProyectoID,
			@RequestParam(required = true, value = "tablaClaseId") Integer tablaClaseId,
			HttpServletRequest request) {

		UserSession userSession = (UserSession) request.getSession()
		.getAttribute(SgpWebConstants.SESSION_USER);
		Map<String, Object> model = new HashMap<String, Object>();
		
		if(tablaClaseId==0){
			model.put("listObservaciones",UtilList.orderAscList(observacionService.llenaListObservacionesCompleto( observacionService.findObservacionesByDatoProyectoId(datoProyectoID)),"flagEstado"));
		}else{
			model.put("listObservaciones",UtilList.orderAscList(observacionService.llenaListObservacionesCompleto( observacionService.findObservacionesByDatoProyectoIdByTablaClaseId(datoProyectoID, tablaClaseId)),"flagEstado"));
		}
		model.put("userSession", userSession);
		
		return new ModelAndView("divGrillaObservacionEjecutor", model);
	}

	@RequestMapping(value = "/principal/cargaGrillaObservacionEjecutor")
	public ModelAndView cargaGrillaObservacionEjecutor2(
			@RequestParam(required = true, value = "datoProyectoID") Integer datoProyectoID,
			@RequestParam(required = true, value = "tablaClaseId") Integer tablaClaseId,
			HttpServletRequest request) {

		UserSession userSession = (UserSession) request.getSession()
		.getAttribute(SgpWebConstants.SESSION_USER);
		Map<String, Object> model = new HashMap<String, Object>();
		
		if(tablaClaseId==0){
			model.put("listObservaciones",UtilList.orderAscList(observacionService.llenaListObservacionesCompleto( observacionService.findObservacionesByDatoProyectoId(datoProyectoID)),"flagEstado"));
		}else{
			model.put("listObservaciones",UtilList.orderAscList(observacionService.llenaListObservacionesCompleto( observacionService.findObservacionesByDatoProyectoIdByTablaClaseId(datoProyectoID, tablaClaseId)),"flagEstado"));
		}
		model.put("userSession", userSession);
		
		return new ModelAndView("divGrillaObservacionEjecutor", model);
	}

	@RequestMapping(value = "/principal/grabarObservacion.jspx")
	public void grabarObservacion(
			@RequestParam(required = true, value = "descripcion") String descripcion,
			@RequestParam(required = true, value = "tipoObservacion") Integer tipoObservacion,
			@RequestParam(required = true, value = "fechaObservacion") String fechaObservacion,
			@RequestParam(required = true, value = "estadoObservacion") Integer estadoObservacion,
			@RequestParam(required = true, value = "usuarioId") Integer usuarioId,
			@RequestParam(required = true, value = "datoProyectoID") Integer datoProyectoID,			
			@RequestParam(required = true, value = "tablaClaseId") Integer tablaClaseId,
			@RequestParam(required = true, value = "tablaId") Integer tablaId,
			@RequestParam(required = true, value = "tablaProfundidadesId") Integer tablaProfundidadesId,
			@RequestParam(required = true, value = "claseId") Integer claseId,
			@RequestParam(required = true, value = "observacionRelevanteProyecto") Integer observacionRelevanteProyecto,
			HttpServletRequest request, HttpServletResponse response) throws ParseException, AddressException, MessagingException {

		Observacion observacion= new Observacion();
		
		DatoProyecto datoProyecto = datoProyectoService.findDatoProyectoById(datoProyectoID);
		observacion.setDatoProyecto(datoProyecto);
		observacion.setDescripcion(descripcion);
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = formateador.parse(fechaObservacion);
		observacion.setFechaObservacion(fecha);
		
		observacion.setFkIdtablaespEstado(estadoObservacion);
		observacion.setFkIdtablaespTipoObservacion(tipoObservacion);
		observacion.setFlagEstado(-1);
		observacion.setTablaClase(tablaClaseService.findTablaClaseById(tablaClaseId));
		observacion.setTablaID(tablaId); //id de la parte del documento donde se esta poniendo la observacion, por ejemplo, en PO el resultado, la actividad, etc
		observacion.setTablaProfundidades(tablaProfundidadesService.findTablaProfundidadesServiceById(tablaProfundidadesId));
		observacion.setUsuario(usuarioService.findUsuarioById(usuarioId));
		observacion.setClaseID(claseId); //id del documento donde se esta poniendo la observacion, por ejemplo, PO, Reporte de avance, liquidacion de gastos, etc.
		observacion.setRelevanteProyecto(observacionRelevanteProyecto);
		
		observacionService.saveObservacion(observacion);
		
		FuenteFinanciadora fuenteFinanciadora= fuenteFinanciadoraService.findFuenteFinanciadoraByDatoProyectoIdByEjecutor(datoProyectoID);
		fuenteFinanciadora.setInstitucion(institucionService.findInstitucionById(fuenteFinanciadora.getInstitucion().getInstitucionID()));
		
		Session session = Session.getDefaultInstance(creaProp());
		session.setDebug(true);//para ver lo que esta sucediendo, una vez que funcione puede quitarse
		
		MimeMessage message = new MimeMessage(session);
		// Quien envia el correo
		message.setFrom(new InternetAddress("fondamObservaciones@gmail.com"));

		// A quien va dirigido
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(fuenteFinanciadora.getInstitucion().getCorreo()));
		message.setSubject("FONDAM: Observacion al proyecto.");
		
		/*String obsRelevante="NO";
		if(observacionRelevanteProyecto==1){
			obsRelevante="SI";
		}*/
		
		String ubicacion= tablaProfundidadesService.findProfundidadText(observacion.getTablaProfundidades());
		
		message.setText("<b>OBSERVACION INGRESADA POR FONDO DE LAS AMERICAS</b><br/><br/>" +
		"<b>Proyecto:</b> " +datoProyecto.getNombreProyecto()+"<br/>"+
		"<b>Observacion:</b> " +descripcion+"<br/>"+
		"<b>Ubicacion:</b> " +ubicacion+"<br/>",
		"ISO-8859-1",
		"html");
		
		Transport t = session.getTransport("smtp");
		t.connect("fondamObservaciones@gmail.com","f0ndam0bservaci0nes");
		t.sendMessage(message,message.getAllRecipients());
		t.close();		
		
	}

	@RequestMapping(value = "/principal/showGrillaProyectosObservaciones.jspx")
	public ModelAndView showGrillaProyectosObservaciones(HttpServletRequest request, HttpServletResponse response) {

		String cbxModalidadFinan = request.getParameter("cbxModalidadFinan");
		logger.info("parametros grilla: cbxModalidadFinan :"+cbxModalidadFinan);
		
		String cbxPrograma = request.getParameter("cbxPrograma");
		logger.info("parametros grilla: cbxPrograma :"+cbxPrograma);
		
		String txtCodProyecto = request.getParameter("txtCodProyecto");
		logger.info("parametros grilla: txtCodProyecto :"+txtCodProyecto);
		
		String txtNomProyecto = request.getParameter("txtNomProyecto");
		logger.info("parametros grilla: txtNomProyecto :"+txtNomProyecto);
		
				
		List<DatoProyectoBean>lstProyectosPorPrograma = observacionService.getLstProyectosPorProgramaConObservaciones(
				cbxModalidadFinan,cbxPrograma,txtCodProyecto,txtNomProyecto);
		
		model.put("lstProyectosPorPrograma",lstProyectosPorPrograma);
	
		return new ModelAndView("divGrillaObservacionesPorProyecto",model);
	}
	
	@RequestMapping(value = "/principal/mostrarObservacionPorProyecto.jspx")
	public ModelAndView mostrarObservacionPorProyecto(
			@RequestParam(required = true, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		
		List<DetalleEstadoCabecera> listEstadoAtencion = detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estatob");
		List<DetalleEstadoCabecera> listEstadoAtencionTEMP =new ArrayList<DetalleEstadoCabecera>(); 
		for (DetalleEstadoCabecera detalleEstadoCabecera : listEstadoAtencion) {
			if(!detalleEstadoCabecera.getDescripEstado().equals("Nuevo (Desatendido)")){
				listEstadoAtencionTEMP.add(detalleEstadoCabecera);
			}
		}
		model.put("listTipoObservacion",tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral("TPOB"));
		model.put("listEstadoAtencion", listEstadoAtencionTEMP );
		model.put("datoProyecto", datoProyectoService.findDatoProyectoById(datoProyectoId));
		/* SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
				   Date fechaDate = new Date();
				   String fechaActual = formateador.format(fechaDate);
		model.put("fechaActual",fechaActual);
		*/
		return new ModelAndView("divMostrarObservacionPorProyecto", model);
	}

	@RequestMapping(value = "/security/verObservacionesEjecutor.jspx")
	public ModelAndView verObservacionesEjecutor(
			@RequestParam(required = true, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		
		List<DetalleEstadoCabecera> listEstadoAtencion = detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estatob");
		List<DetalleEstadoCabecera> listEstadoAtencionTEMP =new ArrayList<DetalleEstadoCabecera>(); 
		for (DetalleEstadoCabecera detalleEstadoCabecera : listEstadoAtencion) {
			if(!detalleEstadoCabecera.getDescripEstado().equals("Nuevo (Desatendido)")){
				listEstadoAtencionTEMP.add(detalleEstadoCabecera);
			}
		}
		model.put("listTipoObservacion",tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral("TPOB"));
		model.put("listEstadoAtencion", listEstadoAtencionTEMP );
		model.put("datoProyecto", datoProyectoService.findDatoProyectoById(datoProyectoId));
		model.put("datoProyectoID", datoProyectoId);
		model.put("listTablaClase", tablaClaseService.findTablaClase());
		model.put("estado", 0);
		
		return new ModelAndView("divVerObservacionesEjecutor", model);
	}
	
	@RequestMapping(value = "/principal/verObservacionesEjecutor.jspx")
	public ModelAndView verObservacionesEjecutor2(
			@RequestParam(required = true, value = "datoProyectoId") Integer datoProyectoId,
			HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		
		List<DetalleEstadoCabecera> listEstadoAtencion = detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estatob");
		List<DetalleEstadoCabecera> listEstadoAtencionTEMP =new ArrayList<DetalleEstadoCabecera>(); 
		for (DetalleEstadoCabecera detalleEstadoCabecera : listEstadoAtencion) {
			if(!detalleEstadoCabecera.getDescripEstado().equals("Nuevo (Desatendido)")){
				listEstadoAtencionTEMP.add(detalleEstadoCabecera);
			}
		}
		model.put("listTipoObservacion",tablaEspecificaService.findTablaEspecificabyPrefijoTablaGeneral("TPOB"));
		model.put("listEstadoAtencion", listEstadoAtencionTEMP );
		model.put("datoProyecto", datoProyectoService.findDatoProyectoById(datoProyectoId));
		model.put("datoProyectoID", datoProyectoId);
		model.put("listTablaClase", tablaClaseService.findTablaClase());
		model.put("estado", 0);
		
		return new ModelAndView("divVerObservacionesEjecutor", model);
	}

	@RequestMapping(value = "/security/grabarLevanteObservacionEjecutor.jspx")
	public void grabarLevanteObservacionEjecutor(
			@RequestParam(required = true, value = "descripcionEjecutor") String descripcionEjecutor,
			@RequestParam(required = true, value = "observacionId") Integer observacionId,
			@RequestParam(required = true, value = "estadoObservacion") Integer estadoObservacion,
			@RequestParam(required = true, value = "fechaAtendidoPorEjecutor") String fechaAtendidoPorEjecutor,
			HttpServletRequest request, HttpServletResponse response) throws ParseException, MessagingException {

		Observacion observacion= observacionService.findObservacionById(observacionId);
		
		String observacionEjecutor=descripcionEjecutor==""?"Sin observaciones del ejecutor":descripcionEjecutor;
		observacion.setDescripcionEjecutor(observacionEjecutor);
		observacion.setFkIdtablaespEstado(estadoObservacion);
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = formateador.parse(fechaAtendidoPorEjecutor);
		observacion.setFechaAtencionObservacion(fecha);
		observacion.setFlagEstado(0);
		
		observacionService.updateObservacion(observacion);
		
		//busco usuario ejecutor, el que esta actualmente en la sesion
		UserSession userSession = (UserSession) request.getSession().getAttribute(SgpWebConstants.SESSION_USER);
		DatoUsuario datoUsuarioEjecutor = datoUsuarioService.findDatoUsuarioById(userSession.getDatoUsuarioID());
		
		//busco el personal de fondam que le puso la observacion
		Usuario usuario=usuarioService.findUsuarioById(observacion.getUsuario().getUsuarioID());
		DatoUsuario datoUsuarioFondam = datoUsuarioService.findDatoUsuarioById(usuario.getDatoUsuario().getDatoUsuarioID());
		
		Session session = Session.getDefaultInstance(creaProp());
		session.setDebug(true);//para ver lo que esta sucediendo, una vez que funcione puede quitarse
		
		MimeMessage message = new MimeMessage(session);
		// Quien envia el correo
		message.setFrom(new InternetAddress(datoUsuarioEjecutor.getEmail()));

		// A quien va dirigido
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(datoUsuarioFondam.getEmail()));
		message.setSubject("FONDAM: Observacion levantada por ejecutor de proyecto.");
		
		String obsRelevante="NO";
		if(observacion.getRelevanteProyecto()==1){
			obsRelevante="SI";
		}
		
		String ubicacion= tablaProfundidadesService.findProfundidadText(observacion.getTablaProfundidades());
		
		DatoProyecto datoProyecto=datoProyectoService.findDatoProyectoById(observacion.getDatoProyecto().getDatoProyectoID());
		
		message.setText("<b>OBSERVACION LEVANTADA POR EJECUTOR</b><br/><br/><br/>" +
		"<b>Proyecto:</b> " + datoProyecto.getNombreProyecto()+"<br/>"+
		"<b>Observacion:</b> " +observacion.getDescripcion()+"<br/>"+
		"<b>Observacion puesta por ejecutor:</b> " +observacionEjecutor+"<br/>"+
		"<b>Relevante al proyecto:</b> " +obsRelevante+"<br/>"+
		"<b>Ubicacion:</b> " +ubicacion+"<br/>",
		"ISO-8859-1",
		"html");
		
		Transport t = session.getTransport("smtp");
		t.connect("fondamObservaciones@gmail.com","f0ndam0bservaci0nes");
		t.sendMessage(message,message.getAllRecipients());
		t.close();		
	}

	@RequestMapping(value = "/principal/grabarLevanteObservacionEjecutor.jspx")
	public void grabarLevanteObservacionEjecutor2(
			@RequestParam(required = true, value = "descripcionEjecutor") String descripcionEjecutor,
			@RequestParam(required = true, value = "observacionId") Integer observacionId,
			@RequestParam(required = true, value = "estadoObservacion") Integer estadoObservacion,
			@RequestParam(required = true, value = "fechaAtendidoPorEjecutor") String fechaAtendidoPorEjecutor,
			HttpServletRequest request, HttpServletResponse response) throws ParseException, MessagingException {

		Observacion observacion= observacionService.findObservacionById(observacionId);
		
		String observacionEjecutor=descripcionEjecutor==""?"Sin observaciones del ejecutor":descripcionEjecutor;
		observacion.setDescripcionEjecutor(observacionEjecutor);
		observacion.setFkIdtablaespEstado(estadoObservacion);
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = formateador.parse(fechaAtendidoPorEjecutor);
		observacion.setFechaAtencionObservacion(fecha);
		observacion.setFlagEstado(0);
		
		observacionService.updateObservacion(observacion);
		
		//busco usuario ejecutor, el que esta actualmente en la sesion
		UserSession userSession = (UserSession) request.getSession().getAttribute(SgpWebConstants.SESSION_USER);
		DatoUsuario datoUsuarioEjecutor = datoUsuarioService.findDatoUsuarioById(userSession.getDatoUsuarioID());
		
		//busco el personal de fondam que le puso la observacion
		Usuario usuario=usuarioService.findUsuarioById(observacion.getUsuario().getUsuarioID());
		DatoUsuario datoUsuarioFondam = datoUsuarioService.findDatoUsuarioById(usuario.getDatoUsuario().getDatoUsuarioID());
		
		Session session = Session.getDefaultInstance(creaProp());
		session.setDebug(true);//para ver lo que esta sucediendo, una vez que funcione puede quitarse
		
		MimeMessage message = new MimeMessage(session);
		// Quien envia el correo
		message.setFrom(new InternetAddress(datoUsuarioEjecutor.getEmail()));

		// A quien va dirigido
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(datoUsuarioFondam.getEmail()));
		message.setSubject("FONDAM: Observacion levantada por ejecutor de proyecto.");
		
		String obsRelevante="NO";
		if(observacion.getRelevanteProyecto()==1){
			obsRelevante="SI";
		}
		
		String ubicacion= tablaProfundidadesService.findProfundidadText(observacion.getTablaProfundidades());
		
		DatoProyecto datoProyecto=datoProyectoService.findDatoProyectoById(observacion.getDatoProyecto().getDatoProyectoID());
		
		message.setText("<b>OBSERVACION LEVANTADA POR EJECUTOR</b><br/><br/><br/>" +
		"<b>Proyecto:</b> " + datoProyecto.getNombreProyecto()+"<br/>"+
		"<b>Observacion:</b> " +observacion.getDescripcion()+"<br/>"+
		"<b>Observacion puesta por ejecutor:</b> " +observacionEjecutor+"<br/>"+
		"<b>Relevante al proyecto:</b> " +obsRelevante+"<br/>"+
		"<b>Ubicacion:</b> " +ubicacion+"<br/>",
		"ISO-8859-1",
		"html");
		
		Transport t = session.getTransport("smtp");
		t.connect("fondamObservaciones@gmail.com","f0ndam0bservaci0nes");
		t.sendMessage(message,message.getAllRecipients());
		t.close();		
	}
	
	@RequestMapping(value = "/principal/grabarAprobarObservacion.jspx")
	public void grabarAprobarObservacion(
			@RequestParam(required = true, value = "observacionId") Integer observacionId,
			@RequestParam(required = true, value = "fechaAprobacionPorFondam") String fechaAprobacionPorFondam,
			@RequestParam(required = true, value = "estadoObservacion") Integer estadoObservacionId,
			@RequestParam(required = true, value = "check") Integer check,
			HttpServletRequest request, HttpServletResponse response) throws ParseException, MessagingException {

		Observacion observacion= observacionService.findObservacionById(observacionId);

		if (check==1){
		observacion.setFkIdtablaespEstado(estadoObservacionId);
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = formateador.parse(fechaAprobacionPorFondam);
		observacion.setFechaLevantamientoObservacion(fecha);
		observacion.setFlagEstado(1);
		}else{
			observacion.setFkIdtablaespEstado(detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraByPrefijoDetalleEstado("estcons", "reej").getDetalleEstadoCabeceraID());
			Date fecha = null;
			observacion.setFechaLevantamientoObservacion(fecha);
			observacion.setFlagEstado(0);
		}

		
		observacionService.updateObservacion(observacion);
		
		DatoProyecto datoProyecto = datoProyectoService.findDatoProyectoById(observacion.getDatoProyecto().getDatoProyectoID());
		FuenteFinanciadora fuenteFinanciadora= fuenteFinanciadoraService.findFuenteFinanciadoraByDatoProyectoIdByEjecutor(datoProyecto.getDatoProyectoID());
		fuenteFinanciadora.setInstitucion(institucionService.findInstitucionById(fuenteFinanciadora.getInstitucion().getInstitucionID()));
		
		Session session = Session.getDefaultInstance(creaProp());
		session.setDebug(true);//para ver lo que esta sucediendo, una vez que funcione puede quitarse
		
		MimeMessage message = new MimeMessage(session);
		// Quien envia el correo
		message.setFrom(new InternetAddress("fondamObservaciones@gmail.com"));

		// A quien va dirigido
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(fuenteFinanciadora.getInstitucion().getCorreo()));
		message.setSubject("FONDAM: Observacion al proyecto.");
		
		/*String obsRelevante="NO";
		if(observacion.getRelevanteProyecto() ==1){
			obsRelevante="SI";
		}*/
		
		String ubicacion= tablaProfundidadesService.findProfundidadText(observacion.getTablaProfundidades());
		String estadoObservacion = detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(observacion.getFkIdtablaespEstado()).getDescripEstado();
		
		message.setText("<b>OBSERVACION EVALUADA POR FONDO DE LAS AMERICAS</b><br/><br/>" +
		"<b>Proyecto:</b> " +datoProyecto.getNombreProyecto()+"<br/>"+
		"<b>Observacion:</b> " +observacion.getDescripcion()+"<br/>"+
		"<b>Observacion del ejecutor:</b> " +observacion.getDescripcionEjecutor() +"<br/>"+
		"<b>Ubicacion:</b> " +ubicacion+"<br/>"+
		"<b>Estado de Observacion:</b> " +estadoObservacion+"<br/>",
		"ISO-8859-1",
		"html");
		
		Transport t = session.getTransport("smtp");
		t.connect("fondamObservaciones@gmail.com","f0ndam0bservaci0nes");
		t.sendMessage(message,message.getAllRecipients());
		t.close();		
	}

	
	//************************* metodos privados ***********************//
	private Properties creaProp() {
		Properties props = new Properties();

		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", "smtp.gmail.com");

		// TLS si está disponible
		props.setProperty("mail.smtp.starttls.enable", "true");

		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port","587");

		// Nombre del usuario
		props.setProperty("mail.smtp.user", "fondamObservaciones@gmail.com");

		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", "true");
		
		return props;
	}
}
