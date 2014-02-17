package pe.com.fondam.sgp.web.controller.principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.bean.ProgramaBean2;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.TipoPeriodo;
import pe.com.fondam.sgp.core.form.planOperativo.PlanOperativoForm;
import pe.com.fondam.sgp.core.service.CostoActividadService;
import pe.com.fondam.sgp.core.service.CrearProgramaService;
import pe.com.fondam.sgp.core.service.CronogramaCostoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorActividadService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorResultadoService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.EvaluarService;
import pe.com.fondam.sgp.core.service.InstalarComiteTecnicoService;
import pe.com.fondam.sgp.core.service.MarcoLogicoService;
import pe.com.fondam.sgp.core.service.MetaPorActividadService;
import pe.com.fondam.sgp.core.service.PlanOperativoService;
import pe.com.fondam.sgp.core.service.ProgramaService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.TablaTemporalService;
import pe.com.fondam.sgp.core.service.TipoPeriodoService;
import pe.com.fondam.sgp.core.service.UtilService;
import pe.com.fondam.sgp.core.util.UtilValidate;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class EvalPlanOperativoController {

	//***************** inyecciones ********************//
	protected final Log logger = LogFactory
			.getLog(EvalPlanOperativoController.class);

	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;

	@Resource
	TablaEspecificaService tablaEspecificaService;

	@Resource
	TablaTemporalService tablaTemporalService;

	@Resource
	CrearProgramaService crearProgramaService;

	@Resource
	EvaluarService evaluarService;

	@Resource
	MarcoLogicoService marcoLogicoService;

	@Resource
	InstalarComiteTecnicoService instalarComiteTecnicoService;

	@Resource
	PlanOperativoService planOperativoService;

	@Resource
	UtilService utilService;

	@Resource
	DatoProyectoService datoProyectoService;

	@Resource
	TipoPeriodoService tipoPeriodoService;

	@Resource
	CronogramaMetaPorResultadoService cronogramaMetaPorResultadoService;

	@Resource
	MetaPorActividadService metaPorActividadService;

	@Resource
	CronogramaMetaPorActividadService cronogramaMetaPorActividadService;

	@Resource
	CostoActividadService costoActividadService;

	@Resource
	CronogramaCostoActividadService cronogramaCostoActividadService;

	@Resource
	ProgramaService programaService;
	
	
	
	List<TipoPeriodo> listTipoPeriodos;
	List<TablaEspecifica> listTipoCuentas;
	List<TablaEspecifica> listModalidadFinanciera;
	List<Programa> listaPrograma = null;

	//***************** metodos ********************//
	void cargaCombos() {
		listTipoPeriodos = crearProgramaService.listTipoPeriodos();
		listTipoCuentas = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(1);
		listModalidadFinanciera = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(2);
	}

	@RequestMapping(value = "/principal/showEvaluarPlanOperativo.jspx")
	public ModelAndView showEvaluarPlanOperativo(HttpServletRequest request) {

		listaPrograma =llenaProgramaCompleto(crearProgramaService.listProgramas());

		request.getSession().setAttribute("listaProgramaAndProyecto",
				listaPrograma);
		cargaCombos();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("funcionalidadSelect", "showEvaluarPlanOperativo.jspx");
		model.put("cargarProyecto", 0);

		return new ModelAndView("mostrarEvaluarPlanOperativo", model);
	}

	@RequestMapping(value = "/principal/actionBuscarEvaluarPlanOperativo")
	public ModelAndView actionBuscarEvaluarPlanOperativo(
			HttpServletRequest request) {
		request.getSession().removeAttribute("listaProgramaAndProyecto");

		Programa programa = new Programa();
		Integer idTipoCuentas = Integer.parseInt(request.getParameter(
				"idTipoCuentas").toString());
		Integer idModalidadFinanciera = Integer.parseInt(request.getParameter(
				"idModalidadFinanciera").toString());
		Integer idTipoPeriodos = Integer.parseInt(request.getParameter(
				"idTipoPeriodos").toString());
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
			listaPrograma = instalarComiteTecnicoService.findProgramaByFiltro(
					programa, tipoPeriodo);
		} else if (idTipoCuentas != 0) {
			programa.setFkIdtablaespTipoCuenta(idTipoCuentas);
			listaPrograma = instalarComiteTecnicoService.findProgramaByFiltro(
					programa, tipoCuenta);
		} else if (idModalidadFinanciera != 0) {
			programa.setFkIdtablaespModalidadFinancia(idModalidadFinanciera);
			listaPrograma = instalarComiteTecnicoService.findProgramaByFiltro(
					programa, Modalidad);
		} else if (nombre == 103) {
			programa.setNombrePrograma(nombrePrograma);
			listaPrograma = instalarComiteTecnicoService.findProgramaByFiltro(
					programa, nombre);
		} else if (todosProgramas == 104) {
			listaPrograma = crearProgramaService.listProgramas();
		}
		request.getSession().setAttribute("listaProgramaAndProyecto",
				listaPrograma);
		cargaCombos();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("cargarProyecto", 0);
		return new ModelAndView("mostrarEvaluarPlanOperativo", model);
	}

	@RequestMapping(value = "/principal/actionCargarProyectosEvaluarPlanOperativo")
	public ModelAndView actionCargarProyectosEvaluarPlanOperativo(
			HttpServletRequest request) {
		
		if (request.getSession().getAttribute("listaDatoProyecto") != null) {
			request.getSession().removeAttribute("listaDatoProyecto");
		}
		Integer idPrograma = Integer.parseInt(request
				.getParameter("idPrograma"));
		List<DatoProyecto> listDatoProyecto =llenaListDatoProyectoCompleto( evaluarService.findDatoProyectoByProgramaID(idPrograma));

		if (listDatoProyecto != null && listDatoProyecto.size() != 0) {
			request.getSession().setAttribute("listDatoProyecto",
					listDatoProyecto);
		} else {
			request.getSession().setAttribute("listDatoProyecto", null);

		}
		request.getSession().setAttribute("listTipoPeriodos", listTipoPeriodos);
		request.getSession().setAttribute("listTipoCuentas", listTipoCuentas);
		request.getSession().setAttribute("listModalidadFinanciera",
				listModalidadFinanciera);

		ProgramaBean2 programaBean =programaService.llenarProgramaBean(crearProgramaService
				.findProgramaById(idPrograma));

		Map<String, Object> model = new HashMap<String, Object>();
		// model.put("mensajeEvaluador",mensaje);
		model.put("cargarProyecto", 1);
		model.put("programa", programaBean);

		return new ModelAndView("mostrarEvaluarPlanOperativo", model);
	}

	@RequestMapping(value = "/principal/actionShowEvaluarPlanOperativo")
	public ModelAndView actionShowEvaluarPlanOperativo(
			@RequestParam(required = false, value = "mensaje") String mensaje,
			HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		Integer datoProyectoID = Integer.parseInt(request
				.getParameter("datoProyectoID"));
		
		DatoProyecto datoProyecto = datoProyectoService
				.findDatoProyectoById(datoProyectoID);
		PlanOperativoForm planOperativo = null;
		if (UtilValidate.isNotEmpty(datoProyectoID)) {
			planOperativo = planOperativoService.llenaPlanOperativoTotal(planOperativoService
					.obtenerPlanOperativoByProyectoID(datoProyectoID));

		}
		
		if(planOperativo!=null){
		ProgramaBean2 programaBean = programaService.llenarProgramaBean(datoProyecto
				.getPrograma());
		List<DetalleEstadoCabecera> listDetalleEstadoCabecera = detalleEstadoCabeceraService
				.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estplo");
		List<DetalleEstadoCabecera> listDetalleEstadoCabeceraTemp = new ArrayList<DetalleEstadoCabecera>();
		for (DetalleEstadoCabecera detalleEstadoCabecera : listDetalleEstadoCabecera) {
			if ((detalleEstadoCabecera.getPrefijoEstado().equals("peval"))
					|| (detalleEstadoCabecera.getPrefijoEstado().equals("apro"))
					|| (detalleEstadoCabecera.getPrefijoEstado()
							.equals("recha"))
					|| (detalleEstadoCabecera.getPrefijoEstado().equals("elab"))
					||(detalleEstadoCabecera.getPrefijoEstado().equals(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(planOperativo.getEstadoPlanOperativo()).getPrefijoEstado()))) {
				listDetalleEstadoCabeceraTemp.add(detalleEstadoCabecera);
			}
		}
		
		model.put("listaEstadoCabecera", listDetalleEstadoCabeceraTemp);
		model.put("proyectoBean", datoProyecto);
		model.put("planOperativo", planOperativo);
		model.put("programa", programaBean);
		}
		
		UserSession userSession= (UserSession) request.getSession().getAttribute("USER_SESSION");
		model.put("userSession", userSession);
		model.put("datoProyectoID", datoProyectoID);
		model.put("cargarProyecto", 2);
		model.put("mensaje", mensaje);
		
		return new ModelAndView("mostrarEvaluarPlanOperativo", model);
	}

	@RequestMapping(value = "/principal/actionGrabarEvaluacionPlanOperativo", method = RequestMethod.POST)
	public String actionGrabarEvaluacionPlanOperativo(
			@RequestParam(required = true, value = "estado") Integer estadoId,
			@RequestParam(required = true, value = "datoPlanOperativoId") Integer datoPlanOperativoId,
			HttpServletRequest request) {
		
	
		DatoPlanOperativo planOperativo = planOperativoService
				.findPlanOperativoByID(datoPlanOperativoId);
		if (planOperativo != null) {
			planOperativo.setFkIdDetalleEstadoCabEstadoPlanOper(estadoId);
			planOperativoService.updatePlanOperativo(planOperativo);
		}
		String mensaje = "Se cambio el estado del Plan Operativo satisfactoriamente.";
	
		//return new ModelAndView("mostrarEvaluarPlanOperativo", model);
		return "redirect:actionShowEvaluarPlanOperativo.jspx?datoProyectoID="
				+ planOperativo.getDatoProyecto().getDatoProyectoID()+"&mensaje="+mensaje;
	}

	// ****************** listas *******************************
	private List<DatoProyecto> llenaListDatoProyectoCompleto(
			List<DatoProyecto> listDatoProyecto) {

		List<DatoProyecto> listDatoProyectoTemp= new ArrayList<DatoProyecto>();
		for (DatoProyecto datoProyecto : listDatoProyecto) {
			datoProyecto.setDescripcionEstadoProyecto(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(datoProyecto.getFkIddetallestadocabEstproy()).getDescripEstado());
			
			DatoPlanOperativo datoPlanOperativo = planOperativoService.findPlanOperativoByDatoProyectoID(datoProyecto.getDatoProyectoID()); 
			if (datoPlanOperativo!=null){
				listDatoProyectoTemp.add(datoProyecto);
			}
		}
		return listDatoProyectoTemp;
	}

	private List<Programa> llenaProgramaCompleto(List<Programa> listProgramas) {
		
		for (Programa programa : listProgramas) {
			programa.setDescripcionModalidadFinancia(tablaEspecificaService.findTablaEspecificaById(programa.getFkIdtablaespModalidadFinancia()).getDescripcionCabecera());
			programa.setDescripcionTipoCuenta(tablaEspecificaService.findTablaEspecificaById(programa.getFkIdtablaespTipoCuenta()).getDescripcionCabecera());
		}
		return listProgramas;
	}
	}
