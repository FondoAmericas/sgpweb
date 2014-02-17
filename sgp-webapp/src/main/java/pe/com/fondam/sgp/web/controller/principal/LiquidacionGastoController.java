package pe.com.fondam.sgp.web.controller.principal;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import pe.com.fondam.sgp.core.bean.CargaFormularioBean;
import pe.com.fondam.sgp.core.bean.CompromisoPagoBean;
import pe.com.fondam.sgp.core.bean.ImagenOArchivoTempBean;
import pe.com.fondam.sgp.core.bean.ReporteAvanceBean;
import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.ActividadDetallePago;
import pe.com.fondam.sgp.core.domain.Activo;
import pe.com.fondam.sgp.core.domain.CargaFormulario;
import pe.com.fondam.sgp.core.domain.CompromisoPago;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.DetallePagoLiquidacion;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;
import pe.com.fondam.sgp.core.domain.IngresoPropio;
import pe.com.fondam.sgp.core.domain.LiquidacionGasto;
import pe.com.fondam.sgp.core.domain.PagoLiquidacion;
import pe.com.fondam.sgp.core.domain.RaLg;
import pe.com.fondam.sgp.core.domain.ReporteAvance;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.TipoCambio;
import pe.com.fondam.sgp.core.service.ActividadDetallePagoService;
import pe.com.fondam.sgp.core.service.ActividadService;
import pe.com.fondam.sgp.core.service.ActivoService;
import pe.com.fondam.sgp.core.service.CargaFormularioService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.ImagenOArchivoService;
import pe.com.fondam.sgp.core.service.LiquidacionGastoService;
import pe.com.fondam.sgp.core.service.PagoLiquidacionService;
import pe.com.fondam.sgp.core.service.PlanOperativoService;
import pe.com.fondam.sgp.core.service.RegistroPerfilService;
import pe.com.fondam.sgp.core.service.ReporteAvanceService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.TipoCambioService;
import pe.com.fondam.sgp.core.service.UtilService;
import pe.com.fondam.sgp.web.InOutFiles.FileUploadDownload;
import pe.com.fondam.sgp.web.InOutFiles.LinkFile;
import pe.com.fondam.sgp.web.constants.MenuConstants;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class LiquidacionGastoController {
	protected final Log logger = LogFactory.getLog(LiquidacionGastoController.class);
	
	//**************** inyecciones ********************//
	@Resource
	LiquidacionGastoService liquidacionGastoService;
	
	@Resource
	ReporteAvanceService reporteAvanceService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	TipoCambioService tipoCambioService;
	
	@Resource
	UtilService utilService;
	
	@Resource
	ActivoService activoService;
	
	@Resource
	ImagenOArchivoService imagenOArchivoService;
	
	FileUploadDownload fileUploadDownload=new FileUploadDownload();

	@Resource
	RegistroPerfilService registroPerfilService;
	
	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	@Resource
	PlanOperativoService planOperativoService;
	
	@Resource
	PagoLiquidacionService pagoLiquidacionService;
	
	@Resource
	ActividadService actividadService;
	
	@Resource
	ActividadDetallePagoService actividadDetallePagoService;
	
	@Resource
	CargaFormularioService cargaFormularioService;
	
	UserSession getUserSession(HttpServletRequest request, HttpServletResponse response){
		UserSession userSession = (UserSession) request.getSession().getAttribute(SgpWebConstants.SESSION_USER);

		return userSession;
		
	}

	//**************** metodos ********************//
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/principal/showLiquidacionGasto.jspx")
	public ModelAndView showLiquidacionGasto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String mensaje=request.getParameter("mensaje")==null?"":request.getParameter("mensaje");
		Map<String, Object> model = new HashMap<String, Object>();
		UserSession userSession =getUserSession(request,response);
		
		//List<FuenteFinanciadora> listFuenteFinanciadora=liquidacionGastoService.findFuenteFinanciadoraByDatoProyectoID(userSession.getDatoProyectoID());
		List<ReporteAvanceBean> listReporteAvanceBean = llenaReporteAvanceBeanByDatoProyectoId(userSession);
		
		//List<DetalleEstadoCabecera> listDetalleEstadoCabecera=utilService.listarDetalleEstadoCabeceraByPrefijo(FondamConstans.estado.PREFIJO_ESTADO_LIQUIDACION_GASTO);
		//System.out.println("userSession.getDatoProyectoID()"+userSession.getDatoProyectoID());
        //List<Resultado> listResultado=liquidacionGastoService.findResultadoByDatoProyectoID(userSession.getDatoProyectoID());
        //List<TablaEspecifica> listTipoComprobante=utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO);
        model.put("listFuenteFinanciadora",liquidacionGastoService.findFuenteFinanciadoraByDatoProyectoID(userSession.getDatoProyectoID()));
        model.put("listReporteAvance",listReporteAvanceBean);
        model.put("listDetalleEstadoCabecera",utilService.listarDetalleEstadoCabeceraByPrefijo(FondamConstans.estado.PREFIJO_ESTADO_LIQUIDACION_GASTO));
        //model.put("listResultado", listResultado);        
        model.put("listTipoComprobante", utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO));
        model.put("mensaje", mensaje);

        MenuConstants menuConstants = new MenuConstants("showLiquidacionGasto.jspx");
		model.put("funcionalidadSelect", menuConstants.urlSelect );
		
		//busco el prefijo del estado del plan operativo
		String estadoPrefijoPlanOperativo="xxxx";
		DatoPlanOperativo planOperativo=planOperativoService.findPlanOperativoByDatoProyectoID(userSession.getDatoProyectoID());
		if(planOperativo!=null){
			estadoPrefijoPlanOperativo=detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(planOperativo.getFkIdDetalleEstadoCabEstadoPlanOper()).getPrefijoEstado();
		}
		model.put("estadoPrefijoPlanOperativo",estadoPrefijoPlanOperativo);
				
		model.put("cantMuestraMensajeObs",1);
		
		List<CargaFormulario> listCargaFormulario = cargaFormularioService.findFormularios();
		model.put("listCargaFormulario",listCargaFormulario);
		
	    return new ModelAndView("mostrarShowLiquidacionGasto",model);
	}	
	
	@RequestMapping(value = "/principal/actionEliminarLiquidacionGasto.jspx")
    public String actionEliminarLiquidacionGasto(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		 String mensaje="Se elimino exitosamente";
        int liquidacionGastoID=Integer.parseInt(request.getParameter("liquidacionGastoID"));
         List<IngresoPropio> listIngresoPropio=liquidacionGastoService.findIngresoPropioByLiquidacionGasto(liquidacionGastoID);
         List<CompromisoPago> listCompromisoPago=liquidacionGastoService.findCompromisoPagoByLiquidacionGasto(liquidacionGastoID);
         List<PagoLiquidacion> listPagoLiquidacion=liquidacionGastoService.findIngresoPagoByliquidacionGastoID(liquidacionGastoID);
         if (!listIngresoPropio.isEmpty() || !listCompromisoPago.isEmpty() || !listPagoLiquidacion.isEmpty()) {
			mensaje="No se puede eliminar, ya tiene asignados procesos";
		}else {
			RaLg raLg=liquidacionGastoService.findRaLgByLiquidcionGastoID(liquidacionGastoID);
			liquidacionGastoService.deleteRaLg(raLg);
			liquidacionGastoService.deleteLiquidacionGasto(liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID));
		}
         return "redirect:showLiquidacionGasto.jspx?mensaje="+mensaje;
    }    
	
	@RequestMapping(value = "/principal/actionRegistrarLiquidacionGasto.jspx")
	public String actionRegistrarLiquidacionGasto(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		
		int reporteAvance=Integer.parseInt(request.getParameter("reporteAvance"));
		int estado=52;//Liquidacion generada
		//int estado=Integer.parseInt(request.getParameter("estado"));
		int fuenteFinanciadora=Integer.parseInt(request.getParameter("fuenteFinanciadora"));
		String fechaInicio= request.getParameter("fechaInicio");
		String fechaFin= request.getParameter("fechaFin");
		//double saldoDisponible = Double.parseDouble(request.getParameter("saldoDisponible").toString());
		String observacion= request.getParameter("observacion");
		String codigo =request.getParameter("codVersion");

		UserSession userSession =getUserSession(request,response);
		
		DatoProyecto datoProyecto=new DatoProyecto();
		datoProyecto.setDatoProyectoID(userSession.getDatoProyectoID());
		
		FuenteFinanciadora fuenteFinanciadoraBean=new FuenteFinanciadora();
		fuenteFinanciadoraBean.setFuenteFinanciadoraID(fuenteFinanciadora);
		
		String[] list= codigo.split("\\."); 
		
		LiquidacionGasto liquidacionGasto =new LiquidacionGasto();
		liquidacionGasto.setDatoProyecto(datoProyecto);
		liquidacionGasto.setFechaInicio(formateador.parse(fechaInicio));
		liquidacionGasto.setFechaFin(formateador.parse(fechaFin));
		liquidacionGasto.setFkIdDetalleEstadoCabEstLiqGasto(estado);
		liquidacionGasto.setFuenteFinanciadora(fuenteFinanciadoraBean);
		liquidacionGasto.setNumLiqParcial(Integer.parseInt(list[2].toString()));
		liquidacionGasto.setObservacion(observacion);
		liquidacionGasto.setPeriodo(list[0].toString());
		liquidacionGasto.setSaldoDisponible(0);
		liquidacionGasto.setCodVersion(codigo);
		liquidacionGasto.setFkIdDetalleEstadoCabEstLiqGasto(estado);
		liquidacionGasto.setEstLiqGastoDesc(FondamConstans.ESTADO_LIQUIDACION_GASTO_GENERADA.toString());//52 = liquidacion generada
		liquidacionGastoService.saveLiquidacionGasto(liquidacionGasto);

		
		RaLg ralg=new RaLg();
		ralg.setLiquidacionGasto(liquidacionGasto);
		ralg.setReporteAvance(liquidacionGastoService.getReporteAvanceByID(reporteAvance));
		liquidacionGastoService.saveRaLg(ralg);

		
	    Map<String, Object> model = new HashMap<String, Object>();
		model.put("mensaje","");
	    
		return "redirect:showLiquidacionGasto.jspx?mensaje=Se registro exitosamente";
	}
	
	@RequestMapping(value = "/principal/grabarEstadoLiquidacionGastos.jspx")
	public void grabarEstadoLiquidacionGastos(
			@RequestParam(required = true, value = "sltEstadoLiquidacionGasto") Integer sltEstadoLiquidacionGasto,
			@RequestParam(required = true, value = "liquidacionGastoId") Integer liquidacionGastoId,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException {

		
		LiquidacionGasto liquidacionGasto = liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoId);
		liquidacionGasto.setFkIdDetalleEstadoCabEstLiqGasto(sltEstadoLiquidacionGasto);
		
		liquidacionGastoService.updateLiquidacionGasto(liquidacionGasto);
		
	}
	
	@RequestMapping(value = "/principal/showGrillaLiquidacionGastoByFuenteFinanciadora.jspx")
	public ModelAndView showGrillaLiquidacionGastoByFuenteFinanciadora(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		UserSession userSession =getUserSession(request,response);
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		int fuenteFinanciadoraID = Integer.parseInt(request.getParameter("fuenteFinanciadoraID"));
		List<LiquidacionGasto> listLiquidacionGasto=null;
	
		//System.out.println("fuenteFinanciadoraID"+fuenteFinanciadoraID);
		if (fuenteFinanciadoraID > 0) {
			/*LISTAR POR FUENTE FINANCIADORA*/
			listLiquidacionGasto=liquidacionGastoService.findLiquidacionGastoByFuenteFinanciadoraID(userSession.getDatoProyectoID(),fuenteFinanciadoraID);
		}else {
			/*LISTAR TODOS*/
			listLiquidacionGasto=liquidacionGastoService.findLiquidacionGastoByDatoProyectoID(userSession.getDatoProyectoID());
		}

		//estados
			List<DetalleEstadoCabecera> lstDetEstCab = utilService.listarDetalleEstadoCabeceraByPrefijo(FondamConstans.PREFIJO_ESTADO_LIQUIDACION_GASTO);
			//DetalleEstadoCabecera detalleEstadoCabeceraFinal;	
			List<DetalleEstadoCabecera> lstDetEstCabFinal = new ArrayList<DetalleEstadoCabecera>();
			for(DetalleEstadoCabecera detalleEstadoCabecera : lstDetEstCab ){
				if(detalleEstadoCabecera.getPrefijoEstado().equals("liqgen") ||detalleEstadoCabecera.getPrefijoEstado().equals("eval") ){
					DetalleEstadoCabecera detalleEstadoCabeceraFinal = new DetalleEstadoCabecera();
					detalleEstadoCabeceraFinal.setDescripEstado(detalleEstadoCabecera.getDescripEstado());
					detalleEstadoCabeceraFinal.setDetalleEstadoCabeceraID(detalleEstadoCabecera.getDetalleEstadoCabeceraID());	
					lstDetEstCabFinal.add(detalleEstadoCabeceraFinal);
				}
			}

			model.put("lstDetEstCabFinal", lstDetEstCabFinal);
			
		  model.put("listLiquidacionGasto",listLiquidacionGasto);
		  
	       return new ModelAndView("mostrarDivGrillaLiquidacionGasto",model);
	}

	@RequestMapping(value = "/principal/showIngresoPropio.jspx")
    public ModelAndView showIngresoPropio(
    		@RequestParam(required = true, value = "liquidacionGastoID") Integer liquidacionGastoID,
    		@RequestParam(required = true, value = "fuenteFinanciadora") String fuenteFinanciadora,
    		HttpServletRequest request, HttpServletResponse response) throws IOException {
       
		Map<String, Object> model = new HashMap<String, Object>();
		
        UserSession userSession =getUserSession(request,response);
        //int liquidacionGastoID= Integer.parseInt(request.getParameter("liquidacionGastoID"));
        //List<TablaEspecifica> listTipoComprobante=utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO);
        //List<TablaEspecifica> listTipoMoneda=utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_MONEDA);
        List<Resultado> listResultado=liquidacionGastoService.findResultadoByDatoProyectoID(userSession.getDatoProyectoID());

        List<Actividad> listActividadG= new ArrayList<Actividad>();
       for (Resultado resultado : listResultado) {
		List<Actividad> listActividad = new ArrayList<Actividad>();
		listActividad = actividadService.findActividadXResultadoId(resultado.getResultadoID());
		for (Actividad actividad : listActividad) {
			listActividadG.add(actividad);
		}
		}
        //List<Actividad> listActividadG=liquidacionGastoService.findActidad();
       LiquidacionGasto liquidacionGasto = liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID);  
       model.put("estado", detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(liquidacionGasto.getFkIdDetalleEstadoCabEstLiqGasto()).getPrefijoEstado());
        
        model.put("listIngresoPropio",liquidacionGastoService.findIngresoPropioByLiquidacionGasto(liquidacionGastoID));
        model.put("listTipoComprobante",utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO));
        model.put("listTipoMoneda",utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_MONEDA));
        model.put("ingresoPropioID",0);
          model.put("liquidacionGastoID",liquidacionGastoID);
          model.put("mensaje","");
          model.put("listActividadG",listActividadG);
          model.put("listResultado",listResultado);
          model.put("ingresoPropio.tasaIgv",18);
          model.put("fuenteFinanciadora",fuenteFinanciadora);
          
          return new ModelAndView("mostrarIngresoPropio",model);
    }
   
	@RequestMapping(value = "/principal/actionRegistrarModificarIngresoPropio.jspx")
    public ModelAndView actionRegistrarIngresoPropio(
    		HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
    	 
		Map<String, Object> model = new HashMap<String, Object>();
		
		UserSession userSession =getUserSession(request,response);
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        String mensaje="";
        
        String fuenteFinanciadora = request.getParameter("fuenteFinanciadora");
    	int ingresoPropioID=Integer.parseInt(request.getParameter("ingresoPropioID"));        
        int liquidacionGastoID=Integer.parseInt(request.getParameter("liquidacionGastoID"));
        int tipoComprobante=Integer.parseInt(request.getParameter("tipoComprobante"));
        String numeroComprobante=request.getParameter("numeroComprobante");
        String fechaEmision=request.getParameter("fechaEmision");
        String rucComprador= request.getParameter("rucComprador");
        String razonSocial= request.getParameter("razonSocial");
        String concepto= request.getParameter("concepto");
        int tipoMoneda =Integer.parseInt(request.getParameter("tipoMoneda"));
        double precioSinIgv = Double.parseDouble(request.getParameter("precioSinIgv"));
        double igv = Double.parseDouble(request.getParameter("igv"));
        double precioTotal = Double.parseDouble(request.getParameter("precioTotal"));
        int resultadoID=Integer.parseInt(request.getParameter("resultado"));
       
        String actividadID=request.getParameter("actividad");
       
        Resultado resultado=new Resultado();
        resultado.setResultadoID(resultadoID);
  	  	Actividad  actividad=new Actividad();
  	  	actividad.setActividadID((actividadID.equals("0")?null:Integer.parseInt(request.getParameter("actividad"))));
        
        IngresoPropio ingresoPropio= new IngresoPropio();
        ingresoPropio.setActividad((actividad.getActividadID()==null?null:actividad));
        ingresoPropio.setConcepto(concepto);
        ingresoPropio.setFechaEmision(formateador.parse(fechaEmision));
        ingresoPropio.setFkIdtablaespTipoComprobantePago(tipoComprobante);
        ingresoPropio.setFkIdtablaespTipoMoneda(tipoMoneda);
        ingresoPropio.setIgv(igv);
        ingresoPropio.setTasaIgv(Integer.parseInt(request.getParameter("igvMonto")));
        ingresoPropio.setResultado(resultado);
        ingresoPropio.setLiquidacionGasto(liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID));
        ingresoPropio.setPrecioSinIgv(precioSinIgv);
        ingresoPropio.setPrecioTotal(precioTotal);
        ingresoPropio.setRazonSocial(razonSocial);
        ingresoPropio.setNumeroComprobante(numeroComprobante);
        ingresoPropio.setRucComprador(rucComprador);
        
        if (ingresoPropioID == 0) {
        	//liquidacionGastoService.saveIngresoPropio(ingresoPropio);
        	mensaje="Se registr&oacute; exitosamente";
        	
       	}else {
       		ingresoPropio.setIngresoPropioID(ingresoPropioID);
			//liquidacionGastoService.updateIngresoPropio(ingresoPropio);
        	mensaje="Se modific&oacute; exitosamente";
	
		}
        liquidacionGastoService.updateIngresoPropio(ingresoPropio);
        
        //List<Actividad> listActividadG=liquidacionGastoService.findActidadByResultadoID(ingresoPropio.getResultado().getResultadoID()); 
        List<IngresoPropio> listIngresoPropio= liquidacionGastoService.findIngresoPropioByLiquidacionGasto(liquidacionGastoID);
        List<Resultado> listResultado=liquidacionGastoService.findResultadoByDatoProyectoID(userSession.getDatoProyectoID());
        //List<Actividad> listActividadG=liquidacionGastoService.findActidad();
        List<Actividad> listActividadG= new ArrayList<Actividad>();
        for (Resultado resultado2 : listResultado) {
	 		List<Actividad> listActividad = new ArrayList<Actividad>();
	 		listActividad = actividadService.findActividadXResultadoId(resultado2.getResultadoID());
	 		for (Actividad actividad2 : listActividad) {
	 			listActividadG.add(actividad2);
	 		}
 		}
        request.getSession().setAttribute("listIngresoPropio", listIngresoPropio);        
          
          model.put("listTipoComprobante",utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO));
          model.put("listTipoMoneda",utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_MONEDA));
          model.put("ingresoPropioID",0);
          model.put("liquidacionGastoID",liquidacionGastoID);
          model.put("mensaje",mensaje);
          model.put("igvMonto",18);
          //model.put("listActividad",listActividad);
          model.put("listActividadG",listActividadG);
          model.put("listResultado",listResultado);
          model.put("fuenteFinanciadora", fuenteFinanciadora);
          return new ModelAndView("mostrarIngresoPropio",model);
    }

    @RequestMapping(value = "/principal/actionCargarIngresoPropio.jspx")
    public ModelAndView actionCargarIngresoPropio(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

    	Map<String, Object> model = new HashMap<String, Object>();
    	
    	int ingresoPropioID=Integer.parseInt(request.getParameter("ingresoPropioID"));
        int liquidacionGastoID=Integer.parseInt(request.getParameter("liquidacionGastoID"));
        String fuenteFinanciadora=request.getParameter("fuenteFinanciadora");

    	IngresoPropio ingresoPropio= liquidacionGastoService.findIngresoPropioByID(ingresoPropioID);
    	IngresoPropio ingresoPropioNew=new IngresoPropio();

    	SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
    	ingresoPropioNew.setIngresoPropioID(ingresoPropio.getIngresoPropioID());
    	String fechaDate=formateador.format(ingresoPropio.getFechaEmision());
    	ingresoPropioNew.setFechaEmision(ingresoPropio.getFechaEmision());
    	ingresoPropioNew.setActividad(ingresoPropio.getActividad());
    	ingresoPropioNew.setConcepto(ingresoPropio.getConcepto());
    	ingresoPropioNew.setFkIdtablaespTipoComprobantePago(ingresoPropio.getFkIdtablaespTipoComprobantePago());
    	ingresoPropioNew.setFkIdtablaespTipoMoneda(ingresoPropio.getFkIdtablaespTipoMoneda());
    	ingresoPropioNew.setIgv(ingresoPropio.getIgv());
    	ingresoPropioNew.setResultado(ingresoPropio.getResultado());
        ingresoPropioNew.setLiquidacionGasto(ingresoPropio.getLiquidacionGasto());
        ingresoPropioNew.setPrecioSinIgv(ingresoPropio.getPrecioSinIgv());
        ingresoPropioNew.setPrecioTotal(ingresoPropio.getPrecioTotal());
        ingresoPropioNew.setRazonSocial(ingresoPropio.getRazonSocial());
    	ingresoPropioNew.setTasaIgv(ingresoPropio.getTasaIgv());
        ingresoPropioNew.setNumeroComprobante(ingresoPropio.getNumeroComprobante());
        ingresoPropioNew.setRucComprador(ingresoPropio.getRucComprador());
        UserSession userSession =getUserSession(request,response);
        List<Actividad> listActividadG=liquidacionGastoService.findActidadByResultadoID(ingresoPropio.getResultado().getResultadoID());
        List<Resultado> listResultado=liquidacionGastoService.findResultadoByDatoProyectoID(userSession.getDatoProyectoID());
        		
        List<IngresoPropio> listIngresoPropio= liquidacionGastoService.findIngresoPropioByLiquidacionGasto(liquidacionGastoID);
        //List<Actividad> listActividadG=liquidacionGastoService.findActidad();
        /*List<Actividad> listActividadG= new ArrayList<Actividad>();
        for (Resultado resultado : listResultado) {
 		//List<Actividad> listActividadTempG = new ArrayList<Actividad>();
 		//listActividad = actividadService.findActividadXResultadoId(resultado.getResultadoID());
 		for (Actividad actividad : listActividad) {
 			listActividadG.add(actividad);
 		}
 		}*/
        //double igvMonto=(ingresoPropio.getIgv()/ingresoPropio.getPrecioSinIgv())*100;
        request.getSession().setAttribute("listIngresoPropio", listIngresoPropio);        
          
          model.put("listTipoComprobante",utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO));
          model.put("listTipoMoneda",utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_MONEDA));
          model.put("ingresoPropio",ingresoPropioNew);
          model.put("fechaEmision",fechaDate);
          model.put("liquidacionGastoID",liquidacionGastoID);
          model.put("ingresoPropioID",ingresoPropioID);
          model.put("mensaje","");
          //model.put("igvMonto",igvMonto);
          model.put("listActividad",listActividadG);
          model.put("listActividadG",listActividadG);
          model.put("listResultado",listResultado);
          model.put("fuenteFinanciadora",fuenteFinanciadora);
          return new ModelAndView("mostrarIngresoPropio",model);
    }
    
    @RequestMapping(value = "/principal/actionEliminarIngresoPropio.jspx")
    public String actionEliminarIngresoPropio(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

    	int ingresoPropioID=Integer.parseInt(request.getParameter("ingresoPropioID"));
        int liquidacionGastoID=Integer.parseInt(request.getParameter("liquidacionGastoID"));
        String fuenteFinanciadora = request.getParameter("fuenteFinanciadora");
       
    	IngresoPropio ingresoPropio= liquidacionGastoService.findIngresoPropioByID(ingresoPropioID);
    	liquidacionGastoService.deleteIngresoPropio(ingresoPropio);
         return "redirect:showIngresoPropio.jspx?liquidacionGastoID=" + liquidacionGastoID+"&fuenteFinanciadora="+fuenteFinanciadora;
    }    
    
    String getUnidadDeMedida(int tablaEspecificaID ){
    	
    	TablaEspecifica tablaEspecifica=tablaEspecificaService.findTablaEspecificaById(tablaEspecificaID);
		return tablaEspecifica.getDescripcionCabecera();
    	
    }
   
    @RequestMapping(value = "/principal/actionGetMensajeComprobanteRuc.jspx", method = RequestMethod.GET)
    public void actionGetMensajeComprobanteRuc(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String numeroComprobante=request.getParameter("numeroComprobante");
    	String rucComprador=request.getParameter("rucComprador");
    	
    	IngresoPropio ingresoPropio=liquidacionGastoService.getIngresoPropioByComprobanteByRuc(numeroComprobante,rucComprador); 
    	
    	PagoLiquidacion pagoLiquidacion=liquidacionGastoService.getPagoLiquidacionByComprobanteByRuc(numeroComprobante, rucComprador);
    	String mensaje="";
        if (ingresoPropio!=null || pagoLiquidacion!=null) {
        	 mensaje="SI"; 	
		}else {
			mensaje="NO"; 
		       
		}
		response.getWriter().write(mensaje);
		}
    
    @RequestMapping(value = "/principal/cargarCronogramaCostoActividad.jspx")
	public ModelAndView cargarCronogramaCostoActividad(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	int periodoID=Integer.parseInt(request.getParameter("periodoID"));
    	List<CronogramaCostoActividad> listCronogramaCostoActividad=liquidacionGastoService.findCronogramaCostoActividadByPeriodoID(periodoID);
    	
    	Map<String, Object> model = new HashMap<String, Object>();
		model.put("listCronogramaCostoActividad",listCronogramaCostoActividad);
		model.put("listUnidadMedida",utilService.listaUnidadMedida());
		model.put("listTipoMoneda",utilService.listaTipoMoneda());

		return new ModelAndView("mostrarDivGrillaCronogramaCostoActividad",model);
	}
    
	@RequestMapping(value = "/principal/showCompromisoPago.jspx")
	public ModelAndView showCompromisoPago(
			@RequestParam(required = true, value = "liquidacionGastoID") Integer liquidacionGastoID,
    		@RequestParam(required = true, value = "fuenteFinanciadora") String fuenteFinanciadora,
    		HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		UserSession userSession =getUserSession(request,response);
		//int liquidacionGastoID= Integer.parseInt(request.getParameter("liquidacionGastoID"));
		LiquidacionGasto liquidacionGasto=liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID);
		List<CompromisoPago> listCompromisoPago= liquidacionGastoService.findCompromisoPagoByLiquidacionGasto(liquidacionGastoID);
        List<Actividad> listActividadG=liquidacionGastoService.findActidad();
		List<CronogramaCostoActividad> listPeriodo=liquidacionGastoService.findCronogramaCostoActividadByFuenteFinanciadoraID(liquidacionGasto.getFuenteFinanciadora().getFuenteFinanciadoraID());
		List<Resultado> listResultado=liquidacionGastoService.findResultadoByDatoProyectoID(userSession.getDatoProyectoID());
        request.getSession().setAttribute("listCompromisoPago", listCompromisoPago);
	    
	    
	       model.put("estado", detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(liquidacionGasto.getFkIdDetalleEstadoCabEstLiqGasto()).getPrefijoEstado());
	    model.put("liquidacionGastoID",liquidacionGastoID);
	    model.put("compromisoPagoID",0);
	    model.put("listUnidadMedida",utilService.listaUnidadMedida());
	    model.put("listTipoMoneda",utilService.listaTipoMoneda());
	    model.put("listPeriodo",listPeriodo);
	    model.put("listResultado",listResultado);
	    model.put("listActividadG",listActividadG);
	    model.put("mensaje","");
	    model.put("fuenteFinanciadora",fuenteFinanciadora);
	    return new ModelAndView("mostrarCompromisoPago",model);
	}

	@RequestMapping(value = "/principal/actionCargaCompromisoPago.jspx")
	public ModelAndView actionCargaCompromisoPago(HttpServletRequest request, HttpServletResponse response) throws IOException {
	 	 UserSession userSession =getUserSession(request,response);
		 int liquidacionGastoID=Integer.parseInt(request.getParameter("liquidacionGastoID"));
		 int compromisoPagoID=Integer.parseInt(request.getParameter("compromisoPagoID"));
		
		 CompromisoPago compromisoPago=liquidacionGastoService.findCompromisoPagoByID(compromisoPagoID); 
		 
		 CompromisoPagoBean CompromisoPagoBean=new CompromisoPagoBean();
		 CompromisoPagoBean.setMontoCompromiso(compromisoPago.getMontoCompromiso());
		 CompromisoPagoBean.setObservacion(compromisoPago.getObservacion());
		 CompromisoPagoBean.setCronogramaCostoActividad(compromisoPago.getCronogramaCostoActividad().getCronogramaCostoActividadID());
		 
		 CronogramaCostoActividad cronogramaCostoActividad=liquidacionGastoService.findCronogramaCostoActividadByID(compromisoPago.getCronogramaCostoActividad().getCronogramaCostoActividadID());
		 CompromisoPagoBean.setCostoActividad(cronogramaCostoActividad.getCostoActividad().getCostoActividadID());
		 
		 CostoActividad costoActividad=liquidacionGastoService.findCostoActividadByID(cronogramaCostoActividad.getCostoActividad().getCostoActividadID());
		 CompromisoPagoBean.setActividad(costoActividad.getActividad().getActividadID());
		 Actividad actividad=liquidacionGastoService.findActividadByID(costoActividad.getActividad().getActividadID());
		 CompromisoPagoBean.setResultado(actividad.getResultado().getResultadoID());
		 CompromisoPagoBean.setPeriodo(cronogramaCostoActividad.getCronogramaCostoActividadID());
		 CompromisoPagoBean.setCompromisoPagoID(compromisoPago.getCompromisoPagoID());
		 List<Actividad> listActividad= liquidacionGastoService.findActidadByResultadoID(actividad.getResultado().getResultadoID());
		 List<CostoActividad> listaCostoActividad= liquidacionGastoService.findCostoActividadByActividadID(costoActividad.getActividad().getActividadID());
		 List<CronogramaCostoActividad> listPeriodo=liquidacionGastoService.findCronogramaCostoActividadByCostoActividadIDByFuenteFinanciadoraID(costoActividad.getCostoActividadID(), cronogramaCostoActividad.getFuenteFinanciadora().getFuenteFinanciadoraID());
		 List<Resultado> listResultado=liquidacionGastoService.findResultadoByDatoProyectoID(userSession.getDatoProyectoID());
	     List<Actividad> listActividadG=liquidacionGastoService.findActidad();

	     Map<String, Object> model = new HashMap<String, Object>();
		  model.put("liquidacionGastoID",liquidacionGastoID);
		  model.put("compromisoPagoID",compromisoPagoID);
		  model.put("compromisoPagoBean",CompromisoPagoBean);
		  model.put("mensaje","");
		  model.put("listActividad",listActividad);
		  model.put("listActividadG",listActividadG);
		  model.put("listResultado",listResultado);
		  model.put("listaCostoActividad",listaCostoActividad);
		  model.put("listPeriodo",listPeriodo);
		  model.put("listTipoMoneda",utilService.listaTipoMoneda());
		  model.put("listUnidadMedida",utilService.listaUnidadMedida());
		  return new ModelAndView("mostrarCompromisoPago",model);
	}

    @RequestMapping(value = "/principal/actionRegistrarModificarCompromisoPago.jspx")
    public ModelAndView actionRegistrarModificarCompromisoPago(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
    	UserSession userSession =getUserSession(request,response);
    	String mensaje="";
        int compromisoPagoID=Integer.parseInt(request.getParameter("compromisoPagoID"));
        int liquidacionGastoID=Integer.parseInt(request.getParameter("liquidacionGastoID"));
        String observacion=request.getParameter("observacion");
        int cronogramaCostoActividadID =Integer.parseInt(request.getParameter("periodo"));
        double montoCompromiso = Double.parseDouble(request.getParameter("montoCompromisoH"));
        String fuenteFinanciadora = request.getParameter("fuenteFinanciadora");
      
        CronogramaCostoActividad cronogramaCostoActividad=liquidacionGastoService.findCronogramaCostoActividadByID(cronogramaCostoActividadID);
        
        LiquidacionGasto liquidacionGasto=new LiquidacionGasto();
        liquidacionGasto.setLiquidacionGastoID(liquidacionGastoID);
        
        CompromisoPago compromisoPago=new CompromisoPago();
        compromisoPago.setCronogramaCostoActividad(cronogramaCostoActividad);
        compromisoPago.setMontoCompromiso(montoCompromiso);
        compromisoPago.setObservacion(observacion);
        compromisoPago.setLiquidacionGasto(liquidacionGasto);

        if (compromisoPagoID == 0) {
        	liquidacionGastoService.saveCompromisoPago(compromisoPago);
        	mensaje="Se registro exitosamente Compromiso Pago";
       	}else {
       		compromisoPago.setCompromisoPagoID(compromisoPagoID);
			liquidacionGastoService.updateCompromisoPago(compromisoPago);
			mensaje="Se modifico exitosamente Compromiso Pago";

		}
		 List<Resultado> listResultado=liquidacionGastoService.findResultadoByDatoProyectoID(userSession.getDatoProyectoID());
        List<CompromisoPago> listCompromisoPago= liquidacionGastoService.findCompromisoPagoByLiquidacionGasto(liquidacionGastoID);

        request.getSession().setAttribute("listCompromisoPago", listCompromisoPago);    
        List<Actividad> listActividadG=liquidacionGastoService.findActidad();

          Map<String, Object> model = new HashMap<String, Object>();
          model.put("liquidacionGastoID",liquidacionGastoID);
          model.put("compromisoPagoID",compromisoPagoID);
          model.put("listResultado",listResultado);
          model.put("listUnidadMedida",utilService.listaUnidadMedida());
          model.put("mensaje",mensaje);
          model.put("listActividadG",listActividadG);
          model.put("listTipoMoneda",utilService.listaTipoMoneda());
          model.put("fuenteFinanciadora",fuenteFinanciadora);
           return new ModelAndView("mostrarCompromisoPago",model);
    }
   
    @RequestMapping(value = "/principal/actionEliminarCompromisoPago.jspx")
    public String actionEliminarCompromisoPago(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
   	 int liquidacionGastoID=Integer.parseInt(request.getParameter("liquidacionGastoID"));
	 int compromisoPagoID=Integer.parseInt(request.getParameter("compromisoPagoID"));
	 String fuenteFinanciadora= request.getParameter("fuenteFinanciadora");
       
    	CompromisoPago compromisoPago= liquidacionGastoService.findCompromisoPagoByID(compromisoPagoID);
    	liquidacionGastoService.deleteCompromisoPago(compromisoPago);
         return "redirect:showCompromisoPago.jspx?liquidacionGastoID="+ liquidacionGastoID+"&fuenteFinanciadora="+fuenteFinanciadora;
    }    
   
    @RequestMapping(value = "/principal/actionGetCronogramaDisponible.jspx", method = RequestMethod.GET)
    public void actionGetCronogramaDisponible(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	int cronograma=Integer.parseInt(request.getParameter("cronogramaID"));
    	int liquidacionGastoID=Integer.parseInt(request.getParameter("liquidacionID"));
    	CompromisoPago compromisoPago=liquidacionGastoService.findCompromisoPagoByCronogramaIDByLiquidacionID(cronograma,liquidacionGastoID);
    	ActividadDetallePago actividadDetallePago=liquidacionGastoService.findActividadDetallePagoByCronogramaIDByLiquidacionID(cronograma,liquidacionGastoID);
    	String mensaje="";
    	if (compromisoPago!=null) {
    		mensaje="Cronograma ya esta asignado a un Compromiso de Pago";
		}else if (actividadDetallePago!=null) {
    		mensaje="Cronograma ya esta asignado a una Actividad Detalle Pago";

		}else {
    		mensaje="SI";

		}
    	
    	response.getWriter().write(mensaje);

		}

    /*
    @RequestMapping(value = "/principal/showArchivoUploadPagoLiquidacion.jspx")
	public ModelAndView showArchivoUploadPagoLiquidacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
	      String peticion ="archivoUploadPagoLiquidacion.jspx";
	      String archivoResp = "false";
	      Map<String, Object> model = new HashMap<String, Object>();
		  model.put("peticion",peticion);
		  model.put("archivoResp",archivoResp);
	       return new ModelAndView("mostrarArchivoUpload",model);
	}
	
	@RequestMapping(value = "/principal/archivoUploadPagoLiquidacion.jspx")
	public ModelAndView archivoUploadPagoLiquidacion(HttpServletRequest request, HttpServletResponse response)throws IOException{
		fileUploadDownload.archivoUpload(request,response);
		String archivoResp = "true";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("archivoResp",archivoResp);
		model.put("nombreArchivoUP",fileUploadDownload.getNombreArchivo()+fileUploadDownload.getExtension());
    
		return new ModelAndView("mostrarArchivoUpload",model);
	}
*/
    
    @RequestMapping(value = "/principal/showIngresoPago.jspx")
	public ModelAndView showIngresoPago(HttpServletRequest request,
			@RequestParam(required = true, value = "liquidacionGastoID") Integer liquidacionGastoID,
    		@RequestParam(required = false, value = "fuenteFinanciadora") String fuenteFinanciadora,
			@RequestParam(required = false, value = "mensaje") String mensaje) throws ParseException {

    	Map<String, Object> model = new HashMap<String, Object>();
    	
       // int liquidacionGastoID=Integer.parseInt(request.getParameter("liquidacionGastoID"));
		
		List<PagoLiquidacion> listPagoLiquidacion=liquidacionGastoService.llenaListPagoLiquidacionCompleto(liquidacionGastoService.findIngresoPagoByliquidacionGastoID(liquidacionGastoID));
		request.getSession().setAttribute("listPagoLiquidacion",listPagoLiquidacion);
		
		LiquidacionGasto liquidacionGasto=liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID);
		double saldoDisponible=liquidacionGastoService.getSaldoDisponibleIngresoPago(liquidacionGasto);
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
		saldoDisponible =  format.parse(format.format(saldoDisponible)).doubleValue();
	    //List<TablaEspecifica>   listTipoDesembolso=utilService.listaTipoDesembolso();
	    //List<Desembolso> listDesembolso=liquidacionGastoService.findDesembolsoByBLiquidacionGasto(liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID));
	    //List<TablaEspecifica> listTipoComprobante=utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO);
	    
	    model.put("estado", detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(liquidacionGasto.getFkIdDetalleEstadoCabEstLiqGasto()).getPrefijoEstado());
	      model.put("liquidacionGastoID",liquidacionGastoID);
	      model.put("liquidacion",liquidacionGasto);
	      model.put("saldoDisponible",saldoDisponible);
	      model.put("listTipoDesembolso",utilService.listaTipoDesembolso());
	      model.put("listTipoComprobante",utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO));
	      model.put("listDesembolso",liquidacionGastoService.findDesembolsoByBLiquidacionGasto(liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID)));
	      model.put("listTipoMoneda",utilService.listaTipoMoneda());
          model.put("igvMonto",18);
          model.put("ingresoPagoID",0);
          model.put("mensaje",mensaje);
          model.put("fuenteFinanciadora",fuenteFinanciadora);

  		ImagenOArchivoTempBean imagenOArchivoTempBean= new ImagenOArchivoTempBean();
  		model.put("imagenOArchivoTempBean",imagenOArchivoTempBean);

	      return new ModelAndView("mostrarIngresoPago",model);
	}
  
    @RequestMapping(value = "/principal/actionCargarIngresoPago.jspx")
    public ModelAndView actionCargarIngresoPago(
    		@RequestParam(required = false, value = "ingresoPagoID") Integer ingresoPagoID,
    		HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

    	//int ingresoPagoID=Integer.parseInt(request.getParameter("ingresoPagoID"));
        int liquidacionGastoID=Integer.parseInt(request.getParameter("liquidacionGastoID"));
        LiquidacionGasto liquidacionGasto=liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID);


    	PagoLiquidacion pagoLiquidacion= liquidacionGastoService.findPagoLiquidacionByID(ingresoPagoID);
    	PagoLiquidacion ingresoPagoNew=new PagoLiquidacion();
    	SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
    	String fechaEmision=formateador.format(pagoLiquidacion.getFechaEmision());
       	ingresoPagoNew.setDesembolsoID(pagoLiquidacion.getDesembolsoID());
    	ingresoPagoNew.setFechaEmision(pagoLiquidacion.getFechaEmision());
    	ingresoPagoNew.setChequeCobrado(pagoLiquidacion.getChequeCobrado());
    	ingresoPagoNew.setFkIdtablaespTipoComprobantePago(pagoLiquidacion.getFkIdtablaespTipoComprobantePago());
    	ingresoPagoNew.setLiquidacionGasto(pagoLiquidacion.getLiquidacionGasto());
    	ingresoPagoNew.setMontoIgv(pagoLiquidacion.getMontoIgv());
    	ingresoPagoNew.setMontoTotal(pagoLiquidacion.getMontoTotal());
    	ingresoPagoNew.setNumeroCheque(pagoLiquidacion.getNumeroCheque());
    	ingresoPagoNew.setRazonSocial(pagoLiquidacion.getRazonSocial());
    	ingresoPagoNew.setRucProveedor(pagoLiquidacion.getRucProveedor());
    	ingresoPagoNew.setSaldoPagado(pagoLiquidacion.getSaldoPagado());
    	ingresoPagoNew.setSaldoDisponible(pagoLiquidacion.getSaldoDisponible());
    	ingresoPagoNew.setNumeroDocumento(pagoLiquidacion.getNumeroDocumento());
    	ingresoPagoNew.setFkIdtablaespTipoMoneda(pagoLiquidacion.getFkIdtablaespTipoMoneda());
    	ingresoPagoNew.setDesembolsoID(pagoLiquidacion.getDesembolsoID());
    	ingresoPagoNew.setTasaIgv(pagoLiquidacion.getTasaIgv());
      
    	List<TablaEspecifica>   listTipoDesembolso=utilService.listaTipoDesembolso();
	    List<Desembolso> listDesembolso=liquidacionGastoService.findDesembolsoByBLiquidacionGasto(liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID));
	    List<TablaEspecifica> listTipoComprobante=utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO);
	    double saldoDisponible=liquidacionGastoService.getSaldoDisponibleIngresoPago(liquidacionGasto);
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
		saldoDisponible =  format.parse(format.format(saldoDisponible)).doubleValue();
        
		Map<String, Object> model = new HashMap<String, Object>();
        model.put("liquidacionGastoID",liquidacionGastoID);
        model.put("fechaEmision",fechaEmision);
        model.put("liquidacion",liquidacionGasto);
	      model.put("pagoLiquidacion",ingresoPagoNew);
	      model.put("saldoDisponible",saldoDisponible);
	      model.put("listTipoDesembolso",listTipoDesembolso);
	      model.put("listTipoComprobante",listTipoComprobante);
	      model.put("listDesembolso",listDesembolso);
	      model.put("listTipoMoneda",utilService.listaTipoMoneda());
	      model.put("ingresoPagoID",ingresoPagoID);
	      return new ModelAndView("mostrarIngresoPago",model);
	
        }
   
    @RequestMapping(value = "/principal/actionRegistrarIngresoPago.jspx")
    public String actionRegistrarIngresoPago(
    		@ModelAttribute("imagenOArchivoTempBean") ImagenOArchivoTempBean imagenOArchivoTempBean,
    		HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

    	Map<String, Object> model = new HashMap<String, Object>();
    	
    	int ingresoPagoID=Integer.parseInt(request.getParameter("ingresoPagoID"));
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
    	String mensaje="";
        int liquidacionGastoID=Integer.parseInt(request.getParameter("liquidacionGastoID"));
        String numeroCheque=request.getParameter("numeroCheque");
        int chequeCobrado =(request.getParameter("chequeCobrado")==null)?0:Integer.parseInt(request.getParameter("chequeCobrado"));
        String numeroDocumento=request.getParameter("numeroDocumento");
        int tipoComprobante=Integer.parseInt(request.getParameter("tipoComprobante"));
        int tipoMoneda=Integer.parseInt(request.getParameter("tipoMoneda"));
        String fechaEmision=request.getParameter("fechaEmision");
        String rucProveedor=request.getParameter("rucProveedor");
        String razonSocial=request.getParameter("razonSocial");
        int desembolsoID=Integer.parseInt(request.getParameter("desembolsoID"));
        //String mt=request.getParameter("montoTotal");
        double montoTotal = Double.parseDouble(request.getParameter("montoTotal"));
        double montoIgv = Double.parseDouble(request.getParameter("montoIgv"));
        double saldoPagado = Double.parseDouble(request.getParameter("saldoPagado"));
        String fuenteFinanciadora = request.getParameter("fuenteFinanciadora");
		
        LiquidacionGasto liquidacionGasto=liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID);
		
       PagoLiquidacion pagoLiquidacion=new PagoLiquidacion();
       if(ingresoPagoID>0){
    	   pagoLiquidacion= liquidacionGastoService.findPagoLiquidacionByID(ingresoPagoID);
       }
       pagoLiquidacion.setChequeCobrado(chequeCobrado);
       pagoLiquidacion.setFechaEmision(formateador.parse(fechaEmision));
       pagoLiquidacion.setFkIdtablaespTipoComprobantePago(tipoComprobante);
       pagoLiquidacion.setLiquidacionGasto(liquidacionGasto);
       pagoLiquidacion.setMontoIgv(montoIgv);
       pagoLiquidacion.setMontoTotal(montoTotal);
       pagoLiquidacion.setNumeroCheque(numeroCheque);
       pagoLiquidacion.setRazonSocial(razonSocial);
       pagoLiquidacion.setRucProveedor(rucProveedor);
       pagoLiquidacion.setSaldoPagado(saldoPagado);
       pagoLiquidacion.setSaldoDisponible(montoTotal);
       pagoLiquidacion.setNumeroDocumento(numeroDocumento);
       pagoLiquidacion.setFkIdtablaespTipoMoneda(tipoMoneda);
       pagoLiquidacion.setDesembolsoID(desembolsoID);
       pagoLiquidacion.setTasaIgv(Integer.parseInt(request.getParameter("igvMonto")));
       
       pagoLiquidacion=liquidacionGastoService.updatePagoLiquidacion(pagoLiquidacion);
       if (ingresoPagoID == 0) {
    	//liquidacionGastoService.savePagoLiquidacion(pagoLiquidacion);	
       	mensaje="Se registro exitosamente la factura ingresada";
      	}else {
      		//pagoLiquidacion.setPagoLiquidacionID(ingresoPagoID);
			//liquidacionGastoService.updatePagoLiquidacion(pagoLiquidacion);
			mensaje="Se modifico exitosamente la factura ingresada";

		}

        List<PagoLiquidacion> listPagoLiquidacion=liquidacionGastoService.llenaListPagoLiquidacionCompleto(liquidacionGastoService.findIngresoPagoByliquidacionGastoID(liquidacionGastoID));
		request.getSession().setAttribute("listPagoLiquidacion",listPagoLiquidacion);
		
		//Obtener saldo disponible
		double saldoDisponible=liquidacionGastoService.getSaldoDisponibleIngresoPago(liquidacionGasto);
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
		saldoDisponible =  format.parse(format.format(saldoDisponible)).doubleValue();
		 List<TablaEspecifica>   listTipoDesembolso=utilService.listaTipoDesembolso();
		    List<Desembolso> listDesembolso=liquidacionGastoService.findDesembolsoByBLiquidacionGasto(liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID));
			
		  //logger.info("-----GRABA O MODIFICA IMAGEN O ARCHIVO-----");
			if (!imagenOArchivoTempBean.getImagenODocumento().equals("") ) {
				String nombreArchivo= request.getParameter("nombreArchivo");
				String extencion= request.getParameter("extension");
				int codExtension =  tablaEspecificaService.findIdByDescripcionCabecera(extencion);
				
				if (codExtension != 0) {
					// Guardar el archivo en la base de datos
					ImagenOArchivo imagenOArchivo = imagenOArchivoService.findImagenOArchivoByPagoLiquidacinId(ingresoPagoID);
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
						imagenOArchivo.setPagoLiquidacion(pagoLiquidacion);
						imagenOArchivoService.updateImagenOArchivo(imagenOArchivo);
					}
				}
			}
			
		 List<TablaEspecifica> listTipoComprobante=utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO);
	    
	      model.put("liquidacionGastoID",liquidacionGastoID);
	      model.put("liquidacion",liquidacionGasto);
	      model.put("saldoDisponible",saldoDisponible);
	      model.put("listTipoDesembolso",listTipoDesembolso);
	      model.put("listDesembolso",listDesembolso);
	      model.put("mensaje",mensaje);
	      model.put("listTipoComprobante",listTipoComprobante);
	      model.put("listTipoMoneda",utilService.listaTipoMoneda());
          model.put("pagoLiquidacion.igvMonto",18);
          model.put("ingresoPagoID",0);
          
	      //return new ModelAndView("mostrarIngresoPago",model);
          return "redirect:showIngresoPago.jspx?liquidacionGastoID="+ liquidacionGastoID+"&mensaje="+mensaje+"&fuenteFinanciadora="+fuenteFinanciadora;
    }
 
    @RequestMapping(value = "/principal/actionEliminarIngresoPago.jspx")
    public String actionEliminarIngresoPago(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
   	 
    	int liquidacionGastoID=Integer.parseInt(request.getParameter("liquidacionGastoID"));
    	int ingresoPagoID=Integer.parseInt(request.getParameter("ingresoPagoID"));
    	String fuenteFinanciadora =request.getParameter("fuenteFinanciadora");
    	
    	
    	//String mensaje="No se puede eliminar Ingreso Pago, ya tiene Detalle Pago";
    	//PagoLiquidacion pagoLiquidacion= liquidacionGastoService.findPagoLiquidacionByID(ingresoPagoID);
    	//List<DetallePagoLiquidacion> listDetallePagoLiquidacion=liquidacionGastoService.findDetallePagoLiquidacionByPagoLiquidacionID(ingresoPagoID);
    	
    	//if (listDetallePagoLiquidacion.isEmpty()) {
    	ImagenOArchivo imagenOArchivo= imagenOArchivoService.findImagenOArchivoByPagoLiquidacinId(ingresoPagoID);
    	imagenOArchivoService.deleteImagenOArchivo(imagenOArchivo.getImagenOArchivoID());
    	
    	liquidacionGastoService.deletePagoLiquidacion(ingresoPagoID);	
    	String mensaje="Se elimino exitosamente el Ingreso del Pago.";
        	
    		//}
    
    	return "redirect:showIngresoPago.jspx?liquidacionGastoID="+ liquidacionGastoID+"&mensaje="+mensaje+"&fuenteFinanciadora="+fuenteFinanciadora;
    }
    
    @RequestMapping(value = "/principal/showDetallePago.jspx")
    public ModelAndView showDetallePago(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

    	Map<String, Object> model = new HashMap<String, Object>();
        int pagoLiquidacionID=Integer.parseInt(request.getParameter("pagoLiquidacionID"));
        int liquidacionGastoID =Integer.parseInt(request.getParameter("liquidacionGastoID"));
        String mensaje=request.getParameter("mensaje");
        
        PagoLiquidacion pagoLiquidacion=liquidacionGastoService.findPagoLiquidacionByID(pagoLiquidacionID);
        //List<TablaEspecifica> listUnidadMedida=utilService.listaUnidadMedida();
        //List<TablaEspecifica> listCategoriaActivo=utilService.listaCategoriaActivo();
  
        List<DetallePagoLiquidacion> listDetallePagoLiquidacion=liquidacionGastoService.findDetallePagoLiquidacionByPagoLiquidacionID(pagoLiquidacionID);
		//request.getSession().setAttribute("listDetallePagoLiquidacion",llenaActivo(listDetallePagoLiquidacion));
		//request.getSession().setAttribute("listUnidadMedida",listUnidadMedida);
		//request.getSession().setAttribute("listCategoriaActivo",listCategoriaActivo);
		 //List<TablaEspecifica> listTipoComprobante=utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO);

          LiquidacionGasto liquidacionGasto= liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID);
          
          model.put("estado", detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(liquidacionGasto.getFkIdDetalleEstadoCabEstLiqGasto()).getPrefijoEstado());
          model.put("listUnidadMedida", utilService.listaUnidadMedida());
          model.put("listCategoriaActivo", utilService.listaCategoriaActivo());
          model.put("listDetallePagoLiquidacion",llenaActivo(listDetallePagoLiquidacion));
          model.put("pagoLiquidacionID",pagoLiquidacionID);
          model.put("pagoLiquidacion",pagoLiquidacion);
          model.put("detallePagoID",0);
          model.put("mensaje",mensaje); 
          model.put("igvMonto",18);
          model.put("listTipoMoneda",utilService.listaTipoMoneda());
          model.put("listTipoComprobante",utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO));
	      
           return new ModelAndView("mostrarDetallePago",model);
    }
    
    @RequestMapping(value = "/principal/actionRegistrarDetallePago.jspx")
    public ModelAndView actionRegistrarDetallePago(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

    	String mensaje="Se registro exitosamente Detalle Pago";
    	
    	int detallePagoID=Integer.parseInt(request.getParameter("detallePagoID"));
        int pagoLiquidacionID=Integer.parseInt(request.getParameter("pagoLiquidacionID"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        int unidadMedida = Integer.parseInt(request.getParameter("unidadMedida"));
        double precioUnitario = Double.parseDouble(request.getParameter("precioUnitario"));
        double precioTotal = Double.parseDouble(request.getParameter("precioTotal"));
        String concepto =request.getParameter("concepto");
        //int categoriaActivo=Integer.parseInt(request.getParameter("categoriaActivo"));
        int activoId=Integer.parseInt(request.getParameter("activo"));
        
        PagoLiquidacion pagoLiquidacion=liquidacionGastoService.findPagoLiquidacionByID(pagoLiquidacionID);
        
        Activo activo=new Activo();
        if(activoId!=0){
        	activo= activoService.findActivoById(activoId);
        }
        
        DetallePagoLiquidacion detallePagoLiquidacion=new DetallePagoLiquidacion();

        if (detallePagoID!=0) {
            detallePagoLiquidacion=liquidacionGastoService.findDetallePagoLiquidacionByID(detallePagoID);
		}
        detallePagoLiquidacion.setCantidad(cantidad);
        detallePagoLiquidacion.setFkIdDetalleEstadoCabEstadoPagoLiq(12);
        detallePagoLiquidacion.setFkIdtablaespUnidadMedida(unidadMedida);
        detallePagoLiquidacion.setPrecioTotal(precioTotal);
        detallePagoLiquidacion.setPrecioUnitario(precioUnitario);
        detallePagoLiquidacion.setPagoLiquidacion(pagoLiquidacion);
        detallePagoLiquidacion.setConcepto(concepto);
       detallePagoLiquidacion.setActivo(activo);
       
       if (detallePagoID==0) {
           liquidacionGastoService.saveDetallePagoLiquidacion(detallePagoLiquidacion);

		}else {
			mensaje="Se actualizo exitosamente Detalle Pago";
			detallePagoLiquidacion.setDetallePagoLiquidacionID(detallePagoID);
			liquidacionGastoService.updateDetallePagoLiquidacion(detallePagoLiquidacion);
		}
       
       List<DetallePagoLiquidacion> listDetallePagoLiquidacion=liquidacionGastoService.findDetallePagoLiquidacionByPagoLiquidacionID(pagoLiquidacionID); 
       double saldoPagado=0;
       for (DetallePagoLiquidacion detallePagoLiquidacion2 : listDetallePagoLiquidacion) {
    	   saldoPagado += detallePagoLiquidacion2.getPrecioTotal();
	}
    	   //double  saldoDisponible=pagoLiquidacion.getSaldoDisponible()-detallePagoLiquidacion.getPrecioTotal();
       	//double saldoPagado=pagoLiquidacion.getSaldoPagado()+detallePagoLiquidacion.getPrecioTotal();

       pagoLiquidacion.setSaldoDisponible(pagoLiquidacion.getMontoTotal()-saldoPagado);
       pagoLiquidacion.setSaldoPagado(saldoPagado);
       liquidacionGastoService.updatePagoLiquidacion(pagoLiquidacion);
       
       request.getSession().setAttribute("listDetallePagoLiquidacion",llenaActivo(listDetallePagoLiquidacion));
       //List<TablaEspecifica> listTipoComprobante=utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO);

		Map<String, Object> model = new HashMap<String, Object>();
          model.put("mensaje",mensaje);
          model.put("pagoLiquidacionID",pagoLiquidacionID);
          model.put("pagoLiquidacion",pagoLiquidacion);
          model.put("igvMonto",18);
          model.put("listTipoMoneda",utilService.listaTipoMoneda());
          model.put("listTipoComprobante",utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO));
          model.put("listUnidadMedida", utilService.listaUnidadMedida());
          model.put("listCategoriaActivo", utilService.listaCategoriaActivo());
          model.put("detallePagoID", 0);
          
          return new ModelAndView("mostrarDetallePago",model);
    }
    
    private List<DetallePagoLiquidacion>  llenaActivo(
			List<DetallePagoLiquidacion> listDetallePagoLiquidacion) {
		for (DetallePagoLiquidacion detallePagoLiquidacion : listDetallePagoLiquidacion) {
			Activo activo =activoService.findActivoById(detallePagoLiquidacion.getActivo().getActivoID());
			activo.setDescripcionCategoriaActivo(tablaEspecificaService.findTablaEspecificaById(activo.getFkIdtablaespCategoriaActivo()).getDescripcionCabecera());
			detallePagoLiquidacion.setActivo(activo);
			/*List<ActividadDetallePago> listActividadDetallePago = actividadDetallePagoService.findActividadDetallePagoByDetallePagoLiquidacion(detallePagoLiquidacion.getDetallePagoLiquidacionID());
			double montoActividadDetallePagoDeclarado=0;
			for (ActividadDetallePago actividadDetallePago : listActividadDetallePago) {
				montoActividadDetallePagoDeclarado += actividadDetallePago.getMontoPorGastarDeActiv();
			}
			detallePagoLiquidacion.setMontoActividadDetallePagoDeclarado(montoActividadDetallePagoDeclarado);*/
		}
		return listDetallePagoLiquidacion;
	}
    
    /*
	@RequestMapping(value = "/principal/actionCargarDetallePago.jspx")
    public ModelAndView actionCargarDetallePago(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

		Map<String, Object> model = new HashMap<String, Object>();
		
        int detallePagoID=Integer.parseInt(request.getParameter("detallePagoID"));
        int pagoLiquidacionID=Integer.parseInt(request.getParameter("pagoLiquidacionID"));
        
        DetallePagoLiquidacion detallePagoLiquidacion=liquidacionGastoService.findDetallePagoLiquidacionByID(detallePagoID);
        List<ActividadDetallePago> listActividadDetallePago = actividadDetallePagoService.findActividadDetallePagoByDetallePagoLiquidacion(detallePagoLiquidacion.getDetallePagoLiquidacionID());
        double montoActividadDetallePagoDeclarado=0;
        for (ActividadDetallePago actividadDetallePago : listActividadDetallePago) {
			montoActividadDetallePagoDeclarado += actividadDetallePago.getMontoPorGastarDeActiv();
		}
		//detallePagoLiquidacion.setMontoActividadDetallePagoDeclarado(montoActividadDetallePagoDeclarado);
      
        Activo activo=liquidacionGastoService.findActivoById(detallePagoLiquidacion.getActivo().getActivoID());
        List<Activo> listActivo= activoService.findActivoByCategoriaActivoId(activo.getFkIdtablaespCategoriaActivo());
        
       List<DetallePagoLiquidacion> listDetallePagoLiquidacion=liquidacionGastoService.findDetallePagoLiquidacionByPagoLiquidacionID(pagoLiquidacionID);
       request.getSession().setAttribute("listDetallePagoLiquidacion",llenaActivo(listDetallePagoLiquidacion));
       List<TablaEspecifica> listTipoComprobante=utilService.listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_COMPROBANTE_PAGO);

       PagoLiquidacion pagoLiquidacion=liquidacionGastoService.findPagoLiquidacionByID(pagoLiquidacionID);
       pagoLiquidacion.setSaldoDisponible(pagoLiquidacion.getSaldoDisponible()+detallePagoLiquidacion.getPrecioTotal());
       pagoLiquidacion.setSaldoPagado(pagoLiquidacion.getSaldoPagado()-detallePagoLiquidacion.getPrecioTotal());
       pagoLiquidacion = pagoLiquidacionService.updatePagoLiquidacion(pagoLiquidacion);   
       
		  model.put("pagoLiquidacionID",pagoLiquidacionID);
          model.put("pagoLiquidacion",pagoLiquidacion);
          model.put("detallePagoLiquidacion",detallePagoLiquidacion);
          model.put("montoActividadDetallePagoDeclarado",montoActividadDetallePagoDeclarado);
          model.put("activo",activo);
          model.put("listCategoriaActivo", utilService.listaCategoriaActivo());
          model.put("listActivo",listActivo);
          model.put("igvMonto",18);
          model.put("listTipoMoneda",utilService.listaTipoMoneda());
          model.put("listTipoComprobante",listTipoComprobante);
          model.put("detallePagoID",detallePagoID);
          model.put("listUnidadMedida", utilService.listaUnidadMedida());
   
          return new ModelAndView("mostrarDetallePago",model);
	
        }*/
	
    @RequestMapping(value = "/principal/actionEliminarDetallePago.jspx")
    public String actionEliminarDetallePago(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
   	 int detallePagoID=Integer.parseInt(request.getParameter("detallePagoID"));
     int pagoLiquidacionID=Integer.parseInt(request.getParameter("pagoLiquidacionID"));
     
     PagoLiquidacion pagoLiquidacion= pagoLiquidacionService.findPagoLiquidacionById(pagoLiquidacionID);
     //LiquidacionGasto liquidacionGasto = liquidacionGastoService.findLiquidacionGastoByID(pagoLiquidacion.getLiquidacionGasto().getLiquidacionGastoID());
     
       String mensaje="No se puede eliminar Detalle Pago, ya tiene Actividades";
    	DetallePagoLiquidacion detallePagoLiquidacion= liquidacionGastoService.findDetallePagoLiquidacionByID(detallePagoID);
    	List<ActividadDetallePago> listActividadDetallePago=liquidacionGastoService.findActividadDetallePagoByDetallePagoID(detallePagoID);
    	if (listActividadDetallePago.isEmpty()) {

        	pagoLiquidacion.setSaldoDisponible(pagoLiquidacion.getSaldoDisponible()+detallePagoLiquidacion.getPrecioTotal());
        	pagoLiquidacion.setSaldoPagado(pagoLiquidacion.getSaldoPagado()-detallePagoLiquidacion.getPrecioTotal());
        
    		mensaje="Se elimino exitosamente  Detalle Pago";
    		liquidacionGastoService.deleteDetallePago(detallePagoLiquidacion);	
    		pagoLiquidacionService.updatePagoLiquidacion(pagoLiquidacion);
		}
    
    	return "redirect:showDetallePago.jspx?pagoLiquidacionID="+ pagoLiquidacionID+"&mensaje="+mensaje+"&liquidacionGastoID="+pagoLiquidacion.getLiquidacionGasto().getLiquidacionGastoID();//liquidacionGasto.getLiquidacionGastoID();
    }
    
    @RequestMapping(value = "/principal/showActividadDetallePago.jspx")
    public ModelAndView showActividadDetallePago(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
    	
    	Map<String, Object> model = new HashMap<String, Object>();
    	
    	UserSession userSession =getUserSession(request,response);
    	
        int detallePagoID=Integer.parseInt(request.getParameter("detallePagoID"));
        String estado=request.getParameter("estado");
        String mensaje = request.getParameter("mensaje");
        
        DetallePagoLiquidacion detallePagoLiquidacion=liquidacionGastoService.findDetallePagoLiquidacionByID(detallePagoID);
		List<ActividadDetallePago> listActividadDetallePago=liquidacionGastoService.findActividadDetallePagoByDetallePagoID(detallePagoID);
		/*double montoTotalADPIngresado=0;
		for (ActividadDetallePago actividadDetallePago : listActividadDetallePago) {
			montoTotalADPIngresado += actividadDetallePago.getMontoPorGastarDeActiv();
		}
		model.put("montoTotalADPIngresado",montoTotalADPIngresado);
		*/
        List<Resultado> listResultado=liquidacionGastoService.findResultadoByDatoProyectoID(userSession.getDatoProyectoID());
		
		TipoCambio tipoCambio=tipoCambioService.findTipoCambioByDesembolsoIDByIngreso(detallePagoLiquidacion.getPagoLiquidacion().getDesembolsoID());
		Desembolso desembolso=liquidacionGastoService.findDesembolsoByID(detallePagoLiquidacion.getPagoLiquidacion().getDesembolsoID());
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
		double montoCambio;
		if(detallePagoLiquidacion.getPagoLiquidacion().getFkIdtablaespTipoMoneda().equals(desembolso.getFkIdtablaespTipoMoneda())){
			montoCambio=1*detallePagoLiquidacion.getPrecioTotal();
		}else{
			montoCambio=detallePagoLiquidacion.getPrecioTotal()/tipoCambio.getTipoCambio();
		}
		montoCambio =  format.parse(format.format(montoCambio)).doubleValue();
		
			model.put("detallePagoID",detallePagoID);
        	model.put("liquidacionGastoID",detallePagoLiquidacion.getPagoLiquidacion().getLiquidacionGasto().getLiquidacionGastoID());
        	model.put("listTipoDesembolso",utilService.listaTipoDesembolso());
        	model.put("desembolso",desembolso);
        	model.put("listActividadDetallePago",listActividadDetallePago);
        	model.put("listTipoMoneda",utilService.listaTipoMoneda());
        	model.put("listPeriodo",liquidacionGastoService.findCronogramaCostoActividadByFuenteFinanciadoraID(detallePagoLiquidacion.getPagoLiquidacion().getLiquidacionGasto().getFuenteFinanciadora().getFuenteFinanciadoraID()));
        	model.put("listResultado",listResultado);
	  		//model.put("listaCostoActividad",listaCostoActividad);
	  		model.put("listTipoMoneda",utilService.listaTipoMoneda());
	  		model.put("listUnidadMedida",utilService.listaUnidadMedida());
        	model.put("detallePagoLiquidacion",detallePagoLiquidacion);
        	model.put("mensaje",mensaje);
        	model.put("tipoCambio",tipoCambio);
        	model.put("montoCambio",montoCambio);
        	model.put("estado",estado);
        	return new ModelAndView("mostrarActividadDetallePago",model);
    }
    
    @RequestMapping(value = "/principal/actionEliminarActividadDetallePago.jspx")
    public String actionEliminarActividadDetallePago(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
    	
    	//UserSession userSession =getUserSession(request,response);
        int detallePagoID=Integer.parseInt(request.getParameter("detallePagoID"));
        String estado=request.getParameter("estado");
        int actividadDetallePagoID=Integer.parseInt(request.getParameter("actividadDetallePagoId"));
        
        ActividadDetallePago actividadDetallePago=liquidacionGastoService.findActividadDetallePagoByID(actividadDetallePagoID); 
        liquidacionGastoService.deleteActividadDetallePago(actividadDetallePago);
        String mensaje = "Se elimino exitosamente el registro!";
	  		return "redirect:showActividadDetallePago.jspx?detallePagoID="+ detallePagoID+"&estado="+estado+"&mensaje="+mensaje;
    }
    
    @RequestMapping(value = "/principal/actionRegistrarActividadDetallePago.jspx")
    public String actionRegistrarActividadDetallePago(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
    	
    	//UserSession userSession =getUserSession(request,response); 
    	//List<Resultado> listResultado=liquidacionGastoService.findResultadoByDatoProyectoID(userSession.getDatoProyectoID());

    	String mensaje="Se registro exitosamente el registro!";
        int detallePagoID=Integer.parseInt(request.getParameter("detallePagoID"));
        String estado = request.getParameter("estado");
        
        int desembolsoID = Integer.parseInt(request.getParameter("desembolsoID"));
        int cronogramaCostoActividadID = Integer.parseInt(request.getParameter("periodo"));
        double montoGastado = Double.parseDouble(request.getParameter("montoGastado"));
        double montoTotalDeCostoActividad = Double.parseDouble(request.getParameter("montoTotalDeCostoActividad"));
        double porcentajeMontoTotal = Double.parseDouble(request.getParameter("porcentajeMontoTotal"));

        DetallePagoLiquidacion detallePagoLiquidacion=liquidacionGastoService.findDetallePagoLiquidacionByID(detallePagoID);
        
        Desembolso desembolso=liquidacionGastoService.findDesembolsoByID(desembolsoID);
        
        CronogramaCostoActividad cronogramaCostoActividad= liquidacionGastoService.findCronogramaCostoActividadByID(cronogramaCostoActividadID);
		TipoCambio tipoCambio=tipoCambioService.findTipoCambioByTipoMonedaDeAByDesembolsoID(detallePagoLiquidacion.getPagoLiquidacion().getFkIdtablaespTipoMoneda(), liquidacionGastoService.findDesembolsoByID(detallePagoLiquidacion.getPagoLiquidacion().getDesembolsoID()).getFkIdtablaespTipoMoneda(),detallePagoLiquidacion.getPagoLiquidacion().getDesembolsoID());
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
		double montoCambio=tipoCambio.getTipoCambio()*detallePagoLiquidacion.getPrecioTotal();
		montoCambio =  format.parse(format.format(montoCambio)).doubleValue();
		
        ActividadDetallePago actividadDetallePago=new ActividadDetallePago();
        actividadDetallePago.setCronogramaCostoActividad(cronogramaCostoActividad);
        actividadDetallePago.setDesembolso(desembolso);
        actividadDetallePago.setDetallePagoLiquidacion(detallePagoLiquidacion);
        actividadDetallePago.setFkIdactividades(null);
        actividadDetallePago.setMontoGastado(montoGastado);
        actividadDetallePago.setPorcentajeMontoTotal(porcentajeMontoTotal);
        actividadDetallePago.setMontoTotalDeCostoActividad(montoTotalDeCostoActividad);
        actividadDetallePago.setFkIdtablaespTipoMoneda(desembolso.getFkIdtablaespTipoMoneda());
        actividadDetallePago.setFkIdactividades(cronogramaCostoActividad.getCostoActividad().getActividad().getActividadID());
        actividadDetallePago.setEstadoEliminado(1);
        
        //List<TablaEspecifica>   listTipoDesembolso=utilService.listaTipoDesembolso();
        
        detallePagoLiquidacion=liquidacionGastoService.findDetallePagoLiquidacionByID(detallePagoID);
        liquidacionGastoService.saveActividadDetallePago(actividadDetallePago);

	  		return "redirect:showActividadDetallePago.jspx?detallePagoID="+ detallePagoID+"&estado="+estado+"&mensaje="+mensaje;	
    }
 
    @RequestMapping(value = "/principal/actionGetMontoGastadoCostoActividad.jspx", method = RequestMethod.GET)
    public void actionGetMontoGastadoCostoActividad(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	int costoActividadID=Integer.parseInt(request.getParameter("costoActividadID"));
    	//System.out.println("costoActividadID ::"+costoActividadID);
    	double suma=0;
    	CostoActividad costo=liquidacionGastoService.findCostoActividadByID(costoActividadID);
    	//System.out.println("costo ::"+costo.getCantidadTotal());
    	List<ActividadDetallePago> listActividadDetallePago=liquidacionGastoService.findActividadDetallePagoByCostoActividad(costoActividadID); 
    	if (!listActividadDetallePago.isEmpty()) {
    		for (ActividadDetallePago actividadDetallePago : listActividadDetallePago) {
				suma +=actividadDetallePago.getMontoGastado();
			}
		}
    	double montogastado=(costo.getCantidadTotal()*costo.getPrecioUnitario())-suma;
    	
    	response.getWriter().write(montogastado+"");
		
    }

    @RequestMapping(value = "/principal/cargarFormulario.jspx")
	public ModelAndView cargarFormulario(HttpServletRequest request,
			@RequestParam(required = true, value = "liquidacionGastoID") Integer liquidacionGastoID,
    		@RequestParam(required = false, value = "fuenteFinanciadora") String fuenteFinanciadora){

    	Map<String, Object> model = new HashMap<String, Object>();
    	
    	model.put("liquidacionGastoID", liquidacionGastoID);
    	LiquidacionGasto liquidacionGasto=liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID); 
	    model.put("estado", detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(liquidacionGasto.getFkIdDetalleEstadoCabEstLiqGasto()).getPrefijoEstado());
	   
  		CargaFormularioBean cargaFormularioBean= new CargaFormularioBean();
  		model.put("cargaFormularioBean",cargaFormularioBean);
  		
  		//List<ImagenOArchivo> listImagenOArchivo = imagenOArchivoService.findImagenOArchivoByLiquidacionGastoId(liquidacionGastoID);
  		//model.put("listImagenOArchivo", listImagenOArchivo);

	    return new ModelAndView("mostrarFormularios",model);
	}    
    
    @RequestMapping(value = "/principal/grabarFormularioLiquidacion.jspx")
	public String grabarFormularioLiquidacion(
			@ModelAttribute("cargaFormularioBean") CargaFormularioBean cargaFormularioBean,
			HttpServletRequest request,
			HttpServletResponse resp) throws IOException {
		//securityController.removeSessionAttribute(request);
		//Map<String, Object> model = new HashMap<String, Object>();

		String nombreArchivo= request.getParameter("nombreArchivo");
		String liquidacionGastoID = request.getParameter("liquidacionGastoID");
		String formularioId = request.getParameter("formularioId");;
		String extencion= request.getParameter("extension");
		int codExtension =  tablaEspecificaService.findIdByDescripcionCabecera(extencion);
		
		ImagenOArchivo imagenOArchivo = imagenOArchivoService.findImagenOArchivoByLiquidacionGastoIdAndFormulario(Integer.valueOf(liquidacionGastoID),Integer.valueOf(formularioId));
		
		if(imagenOArchivo==null){
			imagenOArchivo= new ImagenOArchivo();
		}
		imagenOArchivo.setDescripcionDocImg(nombreArchivo);
		imagenOArchivo.setFkIdtablaespTipoArchivo(codExtension);
		imagenOArchivo.setImagen(cargaFormularioBean.getFormulario());
		imagenOArchivo.setLiquidacionGasto(liquidacionGastoService.findLiquidacionGastoByID(Integer.valueOf(liquidacionGastoID)));
		imagenOArchivo.setNumeroFormulario(Integer.valueOf(formularioId));

		imagenOArchivoService.updateImagenOArchivo(imagenOArchivo);
		
		 return "redirect:cargarFormulario.jspx?liquidacionGastoID="+Integer.valueOf(liquidacionGastoID);

	}
    
	@RequestMapping(value = "/principal/showFormularioPorNumero.jspx")
	public ModelAndView showFormularioPorNumero(
			@RequestParam(required = false, value = "liquidacionGastoID") Integer liquidacionGastoID,
			@RequestParam(required = false, value = "numeroFormulario") Integer numeroFormulario,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		List<TablaEspecifica> tablaEspecificaListTipoFormatoArchivo = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(40);// listado de
															// extensiones
		List<LinkFile> listLinkFile = new ArrayList<LinkFile>();
		LinkFile linkFile = new LinkFile();
		ImagenOArchivo imagenOArchivo = null;
		
		try {
			imagenOArchivo = imagenOArchivoService.findImagenOArchivoByLiquidacionGastoIdAndFormulario(liquidacionGastoID, numeroFormulario);

			if (imagenOArchivo != null) {
				linkFile.setId(imagenOArchivo.getImagenOArchivoID());
				linkFile.setNombre(imagenOArchivo.getDescripcionDocImg());
				String extension = fileUploadDownload.getArchivoExtensionById(
						tablaEspecificaListTipoFormatoArchivo,imagenOArchivo.getFkIdtablaespTipoArchivo());
				linkFile.setExtension(extension);
				listLinkFile.add(linkFile);
			}
		} catch (Exception e) {

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listLinkFile", listLinkFile);
		model.put("variable", "downloadFormularioLiquidacionPorNumero.jspx");
		return new ModelAndView("mostrarArchivoDownload", model);
	}

	@RequestMapping(value = "/principal/downloadFormularioLiquidacionPorNumero.jspx")
	public void downloadFormulario(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String idArchivo = request.getParameter("param");
		ImagenOArchivo imagenOArchivo = new ImagenOArchivo();
		imagenOArchivo = imagenOArchivoService.findImagenOArchivoById(Integer
				.parseInt(idArchivo));
		String extension = tablaEspecificaService.findTablaEspecificaById(imagenOArchivo.getFkIdtablaespTipoArchivo()).getDescripcionCabecera();
		String archivo = imagenOArchivo.getDescripcionDocImg() +"."+ extension;
		fileUploadDownload.downloadArchivo(request, response, archivo,
		imagenOArchivo.getImagen(),extension);
	}
    
    @RequestMapping(value = "/principal/showImagenArchivoDownloadPagoLiquidacion.jspx")
	public ModelAndView showImagenArchivoDownloadPagoLiquidacion(
			@RequestParam(required = false, value = "pagoLiquidacionId") Integer pagoLiquidacionId,
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
			imagenOArchivo = imagenOArchivoService.findImagenOArchivoByPagoLiquidacinId(pagoLiquidacionId);

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
		model.put("variable", "downloadImagenArchivoPagoLiquidacion.jspx");
		return new ModelAndView("mostrarArchivoDownload", model);
	}

	@RequestMapping(value = "/principal/downloadImagenArchivoPagoLiquidacion.jspx")
	public void downloadImagenArchivoPagoLiquidacion(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String idArchivo = request.getParameter("param");
		ImagenOArchivo imagenOArchivo = new ImagenOArchivo();
		imagenOArchivo = registroPerfilService.findImagenOArchivoById(Integer
				.parseInt(idArchivo));
		String extension = tablaEspecificaService.findTablaEspecificaById(imagenOArchivo.getFkIdtablaespTipoArchivo()).getDescripcionCabecera();
		String archivo = imagenOArchivo.getDescripcionDocImg() +"."+ extension;
		fileUploadDownload.downloadArchivo(request, response, archivo,
		imagenOArchivo.getImagen(),extension);
	}


	//****************** listas ***********************//
	private List<ReporteAvanceBean> llenaReporteAvanceBeanByDatoProyectoId(
			UserSession userSession) {
		List<ReporteAvance> listReporteAvance=reporteAvanceService.findReporteAvanceXDatoProyectoId(userSession.getDatoProyectoID());
		List<ReporteAvanceBean> listReporteAvanceBean=new ArrayList<ReporteAvanceBean>();
		
		for (ReporteAvance reporteAvance : listReporteAvance) {
			ReporteAvanceBean reporteAvanceBean=new ReporteAvanceBean();
			reporteAvanceBean.setDatoProyecto(reporteAvance.getDatoProyecto());
			reporteAvanceBean.setFkIdDetalleEstadoCabEstRepAvance(reporteAvance.getFkIdDetalleEstadoCabEstRepAvance());
			reporteAvanceBean.setPeriodo(reporteAvance.getPeriodo());
			reporteAvanceBean.setReporteAvanceID(reporteAvance.getReporteAvanceID());
			reporteAvanceBean.setResumen(reporteAvance.getResumen());
			reporteAvanceBean.setTotalLiquidaciones(liquidacionGastoService.finNumLiqParcialByPeriodoByDatoProyectoID(reporteAvance.getPeriodo(),userSession.getDatoProyectoID()));
			listReporteAvanceBean.add(reporteAvanceBean);
		}
		return listReporteAvanceBean;
	}
	
}
