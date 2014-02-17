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
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.bean.ProgramaBean;
import pe.com.fondam.sgp.core.domain.ActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.domain.Evaluador;
import pe.com.fondam.sgp.core.domain.PerfilUsuario;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.PuntajeEvaluacion;
import pe.com.fondam.sgp.core.domain.RestricionPrograma;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.TipoPeriodo;
import pe.com.fondam.sgp.core.domain.Usuario;
import pe.com.fondam.sgp.core.service.CrearProgramaService;
import pe.com.fondam.sgp.core.service.EvaluarService;
import pe.com.fondam.sgp.core.service.InstalarComiteTecnicoService;
import pe.com.fondam.sgp.core.service.SeguridadService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
@Controller
public class InstalarComiteTecnicoController {

	protected final Log logger = LogFactory.getLog(InstalarComiteTecnicoController.class);
	
	@Resource
	CrearProgramaService crearProgramaService;
	
	@Resource
	InstalarComiteTecnicoService instalarComiteTecnicoService;
	
	@Resource
	SeguridadService  seguridadservice;
  	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	EvaluarService evaluarService;
	
	// ProgramaBean programa;
	
	PuntajeEvaluacion puntajeEvaluacion;
	List<TipoPeriodo> listTipoPeriodos;
	List<TablaEspecifica> listTipoCuentas;
	List<TablaEspecifica> listModalidadFinanciera;
	List<RestricionPrograma> listaRestriccion;
	List<ActividadObligatoriaPrograma> listaActividadObligatoria;
	List<Usuario> listaEvaluador ;
	List<Evaluador> listaEvaluadorAsignado ;
	List<Programa> listPrograma;
	List<Evaluador> listComiteTecnico;
	List<TablaEspecifica> listTipoEvaluacion;
	List<ProgramaBean> listProgramaBean;


	@RequestMapping(value = "/principal/showInstalarComiteTecnico.jspx")
	public ModelAndView showInstalarComiteTecnico(HttpServletRequest request) {
		System.out.println("ingrese01");
		listComiteTecnico=instalarComiteTecnicoService.findEvaluador();
		List<Programa> listPrograma=new ArrayList<Programa>();
		List<Programa> listProgramaNew=new ArrayList<Programa>();
		
		if (listComiteTecnico!=null && listComiteTecnico.size()!=0) {
			for (Evaluador evaluador: listComiteTecnico) {
				listPrograma.add(evaluador.getPrograma());
			}
			for (Programa programa : listPrograma) {
				if (!listProgramaNew.contains(programa)) {
					listProgramaNew.add(programa);
				}
			
			}
		}
		request.getSession().setAttribute("listComiteTecnico",listProgramaNew);
		request.getSession().setAttribute("idPrograma",null);
		System.out.println("ingrese02");
		listTipoPeriodos=crearProgramaService.listTipoPeriodos();
		listTipoCuentas=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(1);
		listModalidadFinanciera=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(2);
		listTipoEvaluacion=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(27);
		System.out.println("ingrese03");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("listTipoEvaluacion", listTipoEvaluacion);
		model.put("funcionalidadSelect", "showInstalarComiteTecnico.jspx");
		return new ModelAndView("mostrarInstalarComiteTecnico",model);
	}
	
	@RequestMapping(value = "/principal/actionEliminarComiteTecnico")
	public ModelAndView actionEliminarComiteTecnico(HttpServletRequest request) {
		Integer idEvaluador = Integer.parseInt(request.getParameter("idEvaluador").toString());
		instalarComiteTecnicoService.deleteEvaluador(idEvaluador);
		return showInstalarComiteTecnico(request);
	}
	
	
	
	@RequestMapping(value = "/principal/actionBuscarComiteTecnico")
	public ModelAndView actionBuscarComiteTecnico(HttpServletRequest request) {
		Programa programa=new Programa();
		Evaluador evaluador=new Evaluador();
		Integer idTipoCuentas = Integer.parseInt(request.getParameter("idTipoCuentas").toString());
		Integer idModalidadFinanciera = Integer.parseInt(request.getParameter("idModalidadFinanciera"));
		Integer idTipoPeriodos = Integer.parseInt(request.getParameter("idTipoPeriodos"));
		Integer nombre = Integer.parseInt(request.getParameter("idFiltro"));
		String  nombrePrograma =request.getParameter("buscarNombre");
		Integer todosProgramas = Integer.parseInt(request.getParameter("idFiltro"));
		int tipoPeriodo=100;
		int tipoCuenta=101;
		int Modalidad=102;

		if ( idTipoPeriodos!=0) {
			TipoPeriodo tipPeriodo=new TipoPeriodo();
			tipPeriodo.setTipoPeriodoID(idTipoPeriodos);
			programa.setTipoPeriodo(tipPeriodo);
			evaluador.setPrograma(programa);
			listComiteTecnico=instalarComiteTecnicoService.findComiteTecnicoByFiltro(evaluador,tipoPeriodo );	
		}else if ( idTipoCuentas!=0) {
			programa.setFkIdtablaespTipoCuenta(idTipoCuentas);
			evaluador.setPrograma(programa);
			listComiteTecnico=instalarComiteTecnicoService.findComiteTecnicoByFiltro(evaluador, tipoCuenta);
		}else if (idModalidadFinanciera!=0) {
			programa.setFkIdtablaespModalidadFinancia(idModalidadFinanciera);
			evaluador.setPrograma(programa);
			listComiteTecnico=instalarComiteTecnicoService.findComiteTecnicoByFiltro(evaluador, Modalidad);
		}else if (nombre ==103) {
			programa.setNombrePrograma(nombrePrograma);
			evaluador.setPrograma(programa);
			listComiteTecnico=instalarComiteTecnicoService.findComiteTecnicoByFiltro(evaluador, nombre);
		}else if (todosProgramas ==104) {
			listComiteTecnico=instalarComiteTecnicoService.findEvaluador();
		}else if (nombre ==105) {
			DatoUsuario dUsuario=new DatoUsuario();
			dUsuario.setNombre(nombrePrograma);
			evaluador.setDatoUsuario(dUsuario);
			listComiteTecnico=instalarComiteTecnicoService.findComiteTecnicoByFiltro(evaluador, nombre);
		}
		List<Programa> listPrograma=new ArrayList<Programa>();
		List<Programa> listProgramaNew=new ArrayList<Programa>();
		
		for (Evaluador evaluad: listComiteTecnico) {
			listPrograma.add(evaluad.getPrograma());
		}
		for (Programa program : listPrograma) {
			if (!listProgramaNew.contains(program)) {
				listProgramaNew.add(program);
			}
		
		}
		
		listTipoPeriodos=crearProgramaService.listTipoPeriodos();
		listTipoCuentas=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(1);
		listModalidadFinanciera=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(2);
		listTipoEvaluacion=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(27);
		request.getSession().setAttribute("listComiteTecnico",listProgramaNew);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("listTipoEvaluacion", listTipoEvaluacion);
		
		return new ModelAndView("mostrarInstalarComiteTecnico",model);
	}
	
	
	@RequestMapping(value = "/principal/showListarProgramas")
	public ModelAndView showListarProgramas(HttpServletRequest request) {

		List<Programa> listPrograma= crearProgramaService.listProgramas();
		request.getSession().setAttribute("listarProgramas",listPrograma);
		listTipoPeriodos=crearProgramaService.listTipoPeriodos();
		listTipoCuentas=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(1);
		listModalidadFinanciera=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(2);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		
		return new ModelAndView("mostrarListarProgramas",model);
	}
	
	@RequestMapping(value = "/principal/actionCargarPrograma")
	public ModelAndView actionCargarPrograma(HttpServletRequest request) {
		
		Integer idPrograma = Integer.parseInt(request.getParameter("idPrograma").toString());
		logger.info("ingreseeeeee:"+idPrograma);
		listTipoPeriodos = crearProgramaService.listTipoPeriodos();
		listTipoCuentas = tablaEspecificaService.listTablaEspecificaByTablaGeneralId(1);
		listModalidadFinanciera = tablaEspecificaService.listTablaEspecificaByTablaGeneralId(2);
		listaRestriccion = crearProgramaService.findListRestricionProgramaById(idPrograma);
		listaActividadObligatoria = crearProgramaService.findListActividadObligatoriaProgramaByProgramaId(idPrograma);
		Programa programa=crearProgramaService.findProgramaById(idPrograma);
		listTipoEvaluacion=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(27);
		//datos del programa
		String nombrePrograma =programa.getNombrePrograma(); 
		String identificadorModFinan =programa.getIdentificadorModFinan();
		Integer idTipoCuentas =programa.getFkIdtablaespTipoCuenta();
		Integer idModalidadFinanciera = programa.getFkIdtablaespModalidadFinancia();
		Integer idTipoPeriodos = programa.getTipoPeriodo().getTipoPeriodoID();
		String fechaConvocatoria = programa.getFechaConvocatoria().toString();
		Integer duracionPrograma = programa.getDuracionPrograma();
		int chkRqPYG=0;
		if (programa.getReqProyGrobal()==1) {
			chkRqPYG=1;
		}
		
		//Puntaje
		int chkRqEI = 0;
		Integer puntajeMinRqEI =0;
		Integer puntajeMaxRqEI=0;
		Integer minimoAprobatorioEI=0;
		int chkRqET=0;
		Integer puntajeMinRqET=0;
		Integer puntajeMaxRqET=0;
		Integer minimoAprobatorioET=0;
		int chkRqPY=0;
		Integer puntajeMinRqPY=0;
		Integer puntajeMaxRqPY=0;
		Integer minimoAprobatorioPY=0 ;
		List<PuntajeEvaluacion> ListPuntajeE=crearProgramaService.findPuntajeEvaluacionByProgramaID(idPrograma);
		for (int i = 0; i < ListPuntajeE.size(); i++) {
			if (ListPuntajeE.get(i).getFkIdtablaespTipoEvaluacion()==173) {
				chkRqEI=1;
				puntajeMinRqEI = ListPuntajeE.get(i).getMinimo();
				puntajeMaxRqEI = ListPuntajeE.get(i).getMaximo();
				minimoAprobatorioEI = ListPuntajeE.get(i).getMinimoAprobatorio();
			}else if (ListPuntajeE.get(i).getFkIdtablaespTipoEvaluacion()==174) {
				chkRqET=1;
				puntajeMinRqET = ListPuntajeE.get(i).getMinimo();
				puntajeMaxRqET = ListPuntajeE.get(i).getMaximo();
				minimoAprobatorioET = ListPuntajeE.get(i).getMinimoAprobatorio();
			}else if (ListPuntajeE.get(i).getFkIdtablaespTipoEvaluacion()==175) {
				chkRqPY=1;
				puntajeMinRqPY = ListPuntajeE.get(i).getMinimo();
				puntajeMaxRqPY = ListPuntajeE.get(i).getMaximo();
				minimoAprobatorioPY = ListPuntajeE.get(i).getMinimoAprobatorio();
				
			}
		}
		request.getSession().setAttribute("idPrograma", idPrograma);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		model.put("listaRestriccion", listaRestriccion);
		model.put("listaActividadObligatoria",listaActividadObligatoria);
		model.put("nombrePrograma",nombrePrograma);
		model.put("identificadorModFinan",identificadorModFinan);
		model.put("fechaConvocatoria",fechaConvocatoria);
		model.put("duracionPrograma",duracionPrograma);
		model.put("idTipoCuentas",idTipoCuentas);
		model.put("idModalidadFinanciera",idModalidadFinanciera);
		model.put("idTipoPeriodos",idTipoPeriodos);
		model.put("listPrograma",listPrograma);
		model.put("variable","cargaActualizar");
		model.put("chkRqPYG",chkRqPYG);
		model.put("chkRqEI",chkRqEI);
		model.put("puntajeMinRqEI",puntajeMinRqEI);
		model.put("puntajeMaxRqEI",puntajeMaxRqEI);
		model.put("minimoAprobatorioEI",minimoAprobatorioEI);
		model.put("chkRqET",chkRqET);
		model.put("puntajeMinRqET",puntajeMinRqET);
		model.put("puntajeMaxRqET",puntajeMaxRqET);
		model.put("minimoAprobatorioET",minimoAprobatorioET);
		model.put("chkRqPY",chkRqPY);
		model.put("puntajeMinRqPY",puntajeMinRqPY);
		model.put("puntajeMaxRqPY",puntajeMaxRqPY);
		model.put("minimoAprobatorioPY",minimoAprobatorioPY);
		model.put("listTipoEvaluacion",listTipoEvaluacion);
		String actualizar="";
		try {
			actualizar=request.getParameter("variable").toString();

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (actualizar.equals("actualizar")) {
			model.put("actualizar",actualizar);
		}
		return new ModelAndView("mostrarInstalarComiteTecnico",model);
	}

	
	@RequestMapping(value = "/principal/actionBuscarPrograma")
	public ModelAndView actionBuscarPrograma(HttpServletRequest request) {
		request.getSession().removeAttribute("listarProgramas");

		Programa programa=new Programa();
		Integer idTipoCuentas = Integer.parseInt(request.getParameter("idTipoCuentas").toString());
		Integer idModalidadFinanciera = Integer.parseInt(request.getParameter("idModalidadFinanciera"));
		Integer idTipoPeriodos = Integer.parseInt(request.getParameter("idTipoPeriodos"));
		Integer nombre = Integer.parseInt(request.getParameter("idFiltro"));
		String  nombrePrograma =request.getParameter("buscarNombre");
		Integer todosProgramas = Integer.parseInt(request.getParameter("idFiltro"));
		int tipoPeriodo=100;
		int tipoCuenta=101;
		int Modalidad=102;

		if ( idTipoPeriodos!=0) {
			TipoPeriodo tipPeriodo=new TipoPeriodo();
			tipPeriodo.setTipoPeriodoID(idTipoPeriodos);
			programa.setTipoPeriodo(tipPeriodo);
			listPrograma=instalarComiteTecnicoService.findProgramaByFiltro(programa,tipoPeriodo );	
		}else if (idModalidadFinanciera!=0) {
			System.out.println("idModalidadFinanciera"+idModalidadFinanciera);
			programa.setFkIdtablaespModalidadFinancia(idModalidadFinanciera);
			listPrograma=instalarComiteTecnicoService.findProgramaByFiltro(programa, Modalidad);
		}else if (idTipoCuentas!=0) {
			System.out.println("idTipoCuentas"+idTipoCuentas);
			programa.setFkIdtablaespTipoCuenta(idTipoCuentas);
			listPrograma=instalarComiteTecnicoService.findProgramaByFiltro(programa, tipoCuenta);
		}else if (nombre ==103) {
			programa.setNombrePrograma(nombrePrograma);
			listPrograma=instalarComiteTecnicoService.findProgramaByFiltro(programa, nombre);
		}else if (todosProgramas ==104) {
			listPrograma=crearProgramaService.listProgramas();
		}
		request.getSession().setAttribute("listarProgramas",listPrograma);
		listTipoPeriodos=crearProgramaService.listTipoPeriodos();
		listTipoCuentas=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(1);
		listModalidadFinanciera=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(2);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listTipoPeriodos", listTipoPeriodos);
		model.put("listTipoCuentas", listTipoCuentas);
		model.put("listModalidadFinanciera", listModalidadFinanciera);
		
		return new ModelAndView("mostrarListarProgramas",model);
	}
	
	@RequestMapping(value = "/principal/showListarEvaluadores")
	public ModelAndView showListarEvaluadores(HttpServletRequest request) {
		Integer idPrograma = Integer.parseInt(request.getSession().getAttribute("idPrograma").toString());
		Programa progra=crearProgramaService.findProgramaById(idPrograma);
		int evalInstitucional=progra.getReqEvalInst();
		int evalPrograma=progra.getReqEvalProy();
		int evalPerfil=progra.getReqEvalTec();
		listaEvaluador=instalarComiteTecnicoService.findUsuarios();
		List<PerfilUsuario> perfilUsuarios = seguridadservice.listPerfilUsuariobyDMEbyDAFIandDT();
		listTipoEvaluacion=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(27);
		List<TablaEspecifica> listTipoEvaluacionNew=new ArrayList<TablaEspecifica>();
		for (TablaEspecifica tipoEvaluacion : listTipoEvaluacion) {
			if (tipoEvaluacion.getTablaEspecificaID()== 173 ) {
				if (evalInstitucional==1) {
				listTipoEvaluacionNew.add(tipoEvaluacion);
				}	
			}
			if (tipoEvaluacion.getTablaEspecificaID()== 174 ) {
				if (evalPerfil==1) {
					listTipoEvaluacionNew.add(tipoEvaluacion);
				}
			}
			if (tipoEvaluacion.getTablaEspecificaID()== 175 ) {
				if (evalPrograma==1) {
				listTipoEvaluacionNew.add(tipoEvaluacion);
				}
			}
		}
		request.getSession().setAttribute("listaEvaluador",listaEvaluador);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("perfilUsuarios", perfilUsuarios);
		model.put("listTipoEvaluacion", listTipoEvaluacionNew);
		
		return new ModelAndView("mostrarListarEvaluadores",model);
	}
	
	@RequestMapping(value = "/principal/actionBuscarEvaluador")
	public ModelAndView actionBuscarEvaluador(HttpServletRequest request) {
		Integer idPrograma = Integer.parseInt(request.getSession().getAttribute("idPrograma").toString());
		Integer idFiltro = Integer.parseInt(request.getParameter("idFiltro").toString());
		String nombreBuscar =request.getParameter("buscar");

		if (idFiltro!= 100 && idFiltro!= -1 ) {
			listaEvaluador=instalarComiteTecnicoService.findUsuarioByPerfilUsuarioId(idFiltro);
		}
		if (idFiltro== 100) {
			listaEvaluador=instalarComiteTecnicoService.findUsuarioByNombre(nombreBuscar);
		}
		if (idFiltro== 101) {
			listaEvaluador=instalarComiteTecnicoService.findUsuarios();
		}
		if (listaEvaluador!=null) {
			request.getSession().setAttribute("listaEvaluador",listaEvaluador);
		}else {
			request.getSession().setAttribute("listaEvaluador",null);

		}
	
		
		List<PerfilUsuario> perfilUsuarios = seguridadservice.listPerfilUsuariobyDMEbyDAFIandDT();
		
		Map<String, Object> model = new HashMap<String, Object>();	
		model.put("perfilUsuarios", perfilUsuarios);
		model.put("idPrograma", idPrograma);
		model.put("listTipoEvaluacion", listTipoEvaluacion);

		
		return new ModelAndView("mostrarListarEvaluadores",model);
	}
	
	@RequestMapping(value = "/principal/showListarEvaluadorAsignado")
	public ModelAndView showListarEvaluadorAsignado(HttpServletRequest request) {
		String sidPrograma="";

		Map<String, Object> model = new HashMap<String, Object>();
		try {
			sidPrograma=  request.getSession().getAttribute("idPrograma").toString();
			model.put("idPrograma",Integer.parseInt(sidPrograma));
		
		} catch (Exception e) {
			request.getSession().setAttribute("listaEvaluadorAsignado",null);
		}
		
		if (sidPrograma!="" && sidPrograma!=null) {
		listaEvaluadorAsignado=instalarComiteTecnicoService.findEvaluadorByProgramaId(Integer.parseInt(sidPrograma));
		}
	
		if (listaEvaluadorAsignado!=null) {
			request.getSession().setAttribute("listaEvaluadorAsignado",listaEvaluadorAsignado);
		}else {
			request.getSession().setAttribute("listaEvaluadorAsignado",null);

		}
		listTipoEvaluacion=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(27);
	
		
		model.put("listTipoEvaluacion", listTipoEvaluacion);
		return new ModelAndView("mostrarlistarEvaluadoresAsignados",model);
	}
	
	@RequestMapping(value = "/principal/actionAsignarEvaluador")
	public ModelAndView actionAsignarEvaluador(HttpServletRequest request) {
		Integer idPrograma=  Integer.parseInt(request.getSession().getAttribute("idPrograma").toString());
		String mensaje="";
		String cadena="";
		Integer valor=0;
		String lista[] = null;
		String ListaEvaluador="";
		ListaEvaluador=request.getParameter("listaEvaluadores");
		
		if (ListaEvaluador != null && ListaEvaluador!="" ) {
			lista=	ListaEvaluador.split(";");
		}
		listaEvaluadorAsignado=null;
	
		for (int i = 0; i < lista.length; i++) {
			String listaUsuarioEvaluacion[]=null; 
			listaUsuarioEvaluacion =(lista[i].toString()).split(",") ;
			DatoUsuario datoU=new DatoUsuario();
			datoU.setDatoUsuarioID(Integer.parseInt(listaUsuarioEvaluacion[0].toString()));
			Programa programa=new Programa();
			programa.setProgramaID(idPrograma);
			
			Evaluador evaluador= new Evaluador();
			evaluador.setPrograma(programa);
			evaluador.setDatoUsuario(datoU);
			evaluador.setFkIdtablaespTipoEvaluacion(Integer.parseInt(listaUsuarioEvaluacion[1].toString()));
			valor=instalarComiteTecnicoService.saveEvaluador(evaluador);
			if (valor==0) {
				cadena+=listaUsuarioEvaluacion[0].toString()+",";
			}
		}
		if (cadena!="") {
			mensaje="No se asigno los Evaluadores:"+cadena+",porque ya estan asignados";
		}else {
			mensaje="Se asigno exitosamente los Evaluadores";

		}
		listaEvaluadorAsignado=instalarComiteTecnicoService.findEvaluadorByProgramaId(idPrograma);
		request.getSession().setAttribute("listaEvaluadorAsignado",listaEvaluadorAsignado);
		listTipoEvaluacion=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(27);
		Map<String, Object> model = new HashMap<String, Object>();	
		model.put("mensaje", mensaje);
		model.put("listTipoEvaluacion", listTipoEvaluacion);
		return new ModelAndView("mostrarlistarEvaluadoresAsignados",model);

	}

	
	
	@RequestMapping(value = "/principal/actionEliminarEvaluadorAsignado")
	public ModelAndView actionEliminarEvaluadorAsignado(HttpServletRequest request) {
		String mensaje="";
		Integer idEvaluador = Integer.parseInt(request.getParameter("idEvaluador").toString());
		//Evaluador evaluador=evaluarService.findEvaluadorByID(idEvaluador);
		try {
			instalarComiteTecnicoService.deleteEvaluador(idEvaluador);
			mensaje="Se elimino exitosamente";
			logger.info("DeleteUsuarioDelEvaluadorAsignado1");
		//	instalarComiteTecnicoService.DeleteUsuarioDelEvaluadorAsignado(evaluador.getDatoUsuario().getDatoUsuarioID(),evaluador.getPrograma().getProgramaID());
			logger.info("DeleteUsuarioDelEvaluadorAsignado2");

			
		
		} catch (Exception e) {
			mensaje="No se puede eliminar el evaluador, porque ya realizo una Evaluaci&oacute;n";
		}
		
		String sidPrograma="";

		Map<String, Object> model = new HashMap<String, Object>();
		try {
			sidPrograma=  request.getSession().getAttribute("idPrograma").toString();
			model.put("idPrograma",Integer.parseInt(sidPrograma));
		
		} catch (Exception e) {
			request.getSession().setAttribute("listaEvaluadorAsignado",null);
		}
		
		if (sidPrograma!="" && sidPrograma!=null) {
		listaEvaluadorAsignado=instalarComiteTecnicoService.findEvaluadorByProgramaId(Integer.parseInt(sidPrograma));
		}
	
		if (listaEvaluadorAsignado!=null) {
			request.getSession().setAttribute("listaEvaluadorAsignado",listaEvaluadorAsignado);
		}else {
			request.getSession().setAttribute("listaEvaluadorAsignado",null);

		}
		listTipoEvaluacion=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(27);
	
		model.put("mensaje", mensaje);
		model.put("listTipoEvaluacion", listTipoEvaluacion);
		return new ModelAndView("mostrarlistarEvaluadoresAsignados",model);
	}
	
	
	
}
