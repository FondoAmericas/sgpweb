package pe.com.fondam.sgp.web.controller.principal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.com.fondam.sgp.core.bean.CronogramaMetaPorActividadBean;
import pe.com.fondam.sgp.core.bean.MetaPorActividadBean;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.Activo;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.DepProvDist;
import pe.com.fondam.sgp.core.domain.DescripcionEf;
import pe.com.fondam.sgp.core.domain.DetalleConcluIf;
import pe.com.fondam.sgp.core.domain.LiquidacionGasto;
import pe.com.fondam.sgp.core.domain.Organizacion;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBeneficiario;
import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBien;
import pe.com.fondam.sgp.core.domain.RestriccionDepProvDist;
import pe.com.fondam.sgp.core.domain.RestriccionSubAreaTematica;
import pe.com.fondam.sgp.core.domain.SubAreaTematica;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.UbicacionProyecto;
import pe.com.fondam.sgp.core.service.ActivoService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorActividadService;
import pe.com.fondam.sgp.core.service.DepProvDistService;
import pe.com.fondam.sgp.core.service.DescripcionEfService;
import pe.com.fondam.sgp.core.service.DetalleConcluIfService;
import pe.com.fondam.sgp.core.service.LiquidacionGastoService;
import pe.com.fondam.sgp.core.service.MetaPorActividadService;
import pe.com.fondam.sgp.core.service.OrganizacionService;
import pe.com.fondam.sgp.core.service.ProgramaService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaBeneficiarioService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaBienService;
import pe.com.fondam.sgp.core.service.RegistroPerfilService;
import pe.com.fondam.sgp.core.service.RestriccionDepProvDistService;
import pe.com.fondam.sgp.core.service.RestriccionSubAreaTematicaService;
import pe.com.fondam.sgp.core.service.SubAreaTematicaService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.UbicacionProyectoService;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class UtilController {

	/********** inyecciones *****************/
	@Resource
	RegistroPerfilService registroPerfilService;

	@Resource
	UbicacionProyectoService ubicacionProyectoService;

	@Resource
	DepProvDistService depProvDistService;

	@Resource
	LiquidacionGastoService liquidacionGastoService;

	@Resource
	TablaEspecificaService tablaEspecificaService;

	@Resource
	ActivoService activoService;

	@Resource
	PropuestaTransferenciaBeneficiarioService propuestaTransferenciaBeneficiarioService;
	
	@Resource
	PropuestaTransferenciaBienService propuestaTransferenciaBienService;
	
	@Resource
	OrganizacionService organizacionService;
	
	@Resource
	DescripcionEfService descripcionEfService;
	
	@Resource
	DetalleConcluIfService detalleConcluIfService;
	
	@Resource
	ProgramaService programaService;
	
	@Resource
	RestriccionSubAreaTematicaService restriccionSubAreaTematicaService;
	
	@Resource
	SubAreaTematicaService subAreaTematicaService;
	
	@Resource
	RestriccionDepProvDistService restriccionDepProvDistService;
	
	@Resource
	MetaPorActividadService metaPorActividadService;
	
	@Resource
	CronogramaMetaPorActividadService cronogramaMetaPorActividadService; 
	
	/********** metodos *****************/
	@RequestMapping(value = "/principal/cargaCombosDepProvDistRestriccionProyecto", method = RequestMethod.POST)
	public void cargaCombosDepProvDist(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(SgpWebConstants.SESSION_USER);

		List<UbicacionProyecto> listUbicacionProyecto = ubicacionProyectoService
				.findUbicacionProyectoXDatoProyectoId(userSession
						.getDatoProyectoID());
		List<DepProvDist> listDepartamentos = new ArrayList<DepProvDist>();
		List<DepProvDist> listProvincias = new ArrayList<DepProvDist>();
		List<DepProvDist> listDistritos = new ArrayList<DepProvDist>();
		List<DepProvDist> listDepProvDist = new ArrayList<DepProvDist>();

		for (UbicacionProyecto ubicacionProyecto : listUbicacionProyecto) {
			listDepProvDist.add(depProvDistService
					.findListDepProvDistById(ubicacionProyecto.getDepProvDist()
							.getDepProvDistID()));
		}

		for (DepProvDist depProvDist : listDepProvDist) {
			String departamentoId = depProvDist.getIdDepartamento();
			String provinciaId = depProvDist.getIdProvincia();
			String distritoId = depProvDist.getIdDistrito();

			listDepartamentos.add(depProvDistService
					.findListDepartamentos(departamentoId));
			listProvincias.add(depProvDistService.findListProvincia(
					departamentoId, provinciaId));
			listDistritos.add(depProvDistService.findListDistrito(
					departamentoId, provinciaId, distritoId));
		}

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, listDepartamentos);
		jsonArray.add(1, listProvincias);
		jsonArray.add(2, listDistritos);

		response.getWriter().write(jsonArray.toString());
	}

	@RequestMapping(value = "/principal/cargarCombo.jspx")
	public void cargarCombo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String metodo = request.getParameter("metodo");

		PrintWriter out = null;
		out = response.getWriter();
		//int actividadID = 0;

		if (metodo.equals("cargarActividad")) {
			out.println("<option value='0'>--Actividad--</option>");

			int resultadoID = Integer.parseInt(request
					.getParameter("resultadoID"));
			// actividadID=Integer.parseInt(request.getParameter("actividadID"));
			List<Actividad> listActividad = liquidacionGastoService
					.findActidadByResultadoID(resultadoID);
			if (!listActividad.isEmpty()) {
				for (Actividad actividad : listActividad) {
					out.printf("<option  value='%1s' title='%2s'>%3s</option>",
							actividad.getActividadID(),
							actividad.getDescripcionActividad(),
							actividad.getNombreActividad());
				}
			}

		}else if (metodo.equals("cargarMetaActividad")) {
			out.println("<option value='0'>--Meta Actividad--</option>");

			//int resultadoID = Integer.parseInt(request.getParameter("resultadoID"));
			int actividadID = Integer.parseInt(request
					.getParameter("actividadID"));
			// actividadID=Integer.parseInt(request.getParameter("actividadID"));
			List<MetaPorActividadBean> listMetaPorActividadBean = metaPorActividadService.llenaListMetaPorActividadBean(metaPorActividadService
					.findMetaPorActividadXActividadId(actividadID));
			if (!listMetaPorActividadBean.isEmpty()) {
				for (MetaPorActividadBean metaPorActividadBean : listMetaPorActividadBean) {
					out.printf("<option  value='%1s' title='%2s'>%3s</option>",
							metaPorActividadBean.getMetaPorActividadID() ,
							metaPorActividadBean.getDescripcionUnidadMedida(),
							metaPorActividadBean.getCantidadMetaActividad()+" - "+metaPorActividadBean.getDescripcionUnidadMedida());
				}
			}

		}else if (metodo.equals("cargarCronogramaMetaActividad")) {

			int metasXActividadId = Integer.parseInt(request
					.getParameter("metasXActividadId"));
			// actividadID=Integer.parseInt(request.getParameter("actividadID"));
			List<CronogramaMetaPorActividadBean> listCronogramaMetaPorActividadBean = cronogramaMetaPorActividadService.llenaCronogramaMetaPorActividadBean(
					cronogramaMetaPorActividadService
							.findCronogramaMetaPorActividadXMetaPorActividadId(metasXActividadId),
					metaPorActividadService
							.findMetaPorActividadById(metasXActividadId));
			
			if (!listCronogramaMetaPorActividadBean.isEmpty()) {
				Integer count = 0;
				for (CronogramaMetaPorActividadBean cronogramaMetaPorActividadBean : listCronogramaMetaPorActividadBean) {
					/*out.printf("<option  value='%1s'>%2s</option>",
							metaPorActividadBean.getMetaPorActividadID() ,
							metaPorActividadBean.getCantidadMetaActividad()+" - "+metaPorActividadBean.getDescripcionUnidadMedida());
							*/
					String classIdi="";
					if(count%2==0){
						classIdi="f1";
					}else{
						classIdi="f2";
					}
					out.println("<tr class=\" " +classIdi+" \">" +
							"<td style=\"text-align: left;\"><label>Periodo - " +
							cronogramaMetaPorActividadBean.getPeriodo()+
							"</label></td>" +
							"<td style=\"text-align: left;\"><label>" +
							cronogramaMetaPorActividadBean.getCantidadMetaActividadProgPorPeriodo() + " - "+
							cronogramaMetaPorActividadBean.getDescripcionUnidadMedida()+
							"</label></td></tr>");
					count+=1;
				}
			}

			
		}  else if (metodo.equals("cargarCostoActividad")) {
			out.println("<option value='0'>--Costo Actividad ** Rubro ** Partida Especifica--</option>");
			int actividadID = Integer.parseInt(request.getParameter("actividadID"));
			List<CostoActividad> listCostoActividad = liquidacionGastoService
					.findCostoActividadByActividadID(actividadID);
			if (!listCostoActividad.isEmpty()) {
				for (CostoActividad costoActividad : listCostoActividad) {
					out.printf(
							"<option value='%1s'>%2s</option>",
							costoActividad.getCostoActividadID(),
							costoActividad.getCantidadTotal()
									+ "-"
									+ tablaEspecificaService
											.findTablaEspecificaById(
													costoActividad
															.getFkIdtablaespUnidadMedida())
											.getDescripcionCabecera()
									+ "  **  "
									+ costoActividad.getPartidaGenerica()
											.getDescripcionPartidaGenerica()
									+ "  **  "
									+ costoActividad.getPartidaEspecifica()
											.getDescripcionPartidaEspecifica());
				}
			}

		} else if (metodo.equals("cargarPeriodo")) {
			out.println("<option value='0'>-- Periodo --</option>");
			int costoActividadID = Integer.parseInt(request
					.getParameter("costoActividadID"));
			int liquidacionGastoID = Integer.parseInt(request
					.getParameter("liquidacionGastoID"));
			LiquidacionGasto liquidacionGasto = liquidacionGastoService
					.findLiquidacionGastoByID(liquidacionGastoID);

			List<CronogramaCostoActividad> listCronogramaCostoActividad = liquidacionGastoService
					.findCronogramaCostoActividadByCostoActividadIDByFuenteFinanciadoraID(
							costoActividadID, liquidacionGasto
									.getFuenteFinanciadora()
									.getFuenteFinanciadoraID());

			if (!listCronogramaCostoActividad.isEmpty()) {
				for (CronogramaCostoActividad periodo : listCronogramaCostoActividad) {
					if (periodo.getCantidad() != 0) {
						out.printf("<option value='%1s'>%2s</option>",
								periodo.getCronogramaCostoActividadID(),
								periodo.getPeriodo());
					}

				}
			}
		} else if (metodo.equals("cargarBienActivo")) {
			out.println("<option value='0'>--Bien/Activo--</option>");
			int categoriaActivoId = Integer.parseInt(request
					.getParameter("categoriaActivoId"));
			List<Activo> listActivo = activoService
					.findActivoByCategoriaActivoId(categoriaActivoId);

			for (Activo activo : listActivo) {
				out.printf("<option value='%1s'>%2s</option>",
						activo.getActivoID(), activo.getDescripcionActivo());
			}
		}else if (metodo.equals("cargarBeneficiarioBienTransferido")) {
			out.println("<option value='0'>--Beneficiario--</option>");
	    	int propuestaTransferenciaId=Integer.parseInt(request.getParameter("propuestaTransferenciaId"));
	    	
	    	List<PropuestaTransferenciaBeneficiario> listPropuestaTransferenciaBeneficiario=propuestaTransferenciaBeneficiarioService.findPropuestaTransferenciaBeneficiarioByPropuestaTransferenciaId(propuestaTransferenciaId) ;
		
	    		for (PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiario : listPropuestaTransferenciaBeneficiario) {
	        			out.printf("<option value='%1s'>%2s</option>", propuestaTransferenciaBeneficiario.getPropuestaTransferenciaBeneficiarioID(), propuestaTransferenciaBeneficiario.getCantidadFinal() + " "+tablaEspecificaService.findTablaEspecificaById(propuestaTransferenciaBeneficiario.getBeneficiariosPorResultado().getFkidtablaespEstrato()).getDescripcionCabecera());		
	        }
    		 
        }else if (metodo.equals("cargarBienBienTransferido")) {
			out.println("<option value='0'>--Bien--</option>");
	    	int propuestaTransferenciaId=Integer.parseInt(request.getParameter("propuestaTransferenciaId"));
	    	
	    	List<PropuestaTransferenciaBien> listPropuestaTransferenciaBien=propuestaTransferenciaBienService.findPropuestaTransferenciaBienByPropuestaTransferenciaId(propuestaTransferenciaId);
		
	    		for (PropuestaTransferenciaBien propuestaTransferenciaBien : listPropuestaTransferenciaBien) {
	        			out.printf("<option value='%1s'>%2s</option>",propuestaTransferenciaBien.getPropuestaTransferenciaBienID(), propuestaTransferenciaBien.getBien().getCantidadTotal()+" "+tablaEspecificaService.findTablaEspecificaById(propuestaTransferenciaBien.getBien().getFkIdtablaespUnidadMedida()).getDescripcionCabecera() +" -- "+propuestaTransferenciaBien.getBien().getDescripcionBien());		
	        }
    		 
        }else if (metodo.equals("cargarOrganizacionBienTransferido")) {
			out.println("<option value='0'>--Organizacion--</option>");
	    	int datoProyectoId=Integer.parseInt(request.getParameter("datoProyectoId"));
	    	
	    	List<Organizacion> listOrganizacion=organizacionService.findOrganizacionByDatoProyectoId(datoProyectoId);
		
	    		for (Organizacion organizacion : listOrganizacion) {
	        			out.printf("<option value='%1s'>%2s</option>",organizacion.getOrganizacionID(), organizacion.getNombreOrganizacion());		
	        }
    		 
        }else if (metodo.equals("cargarDescripcionEvaluacionFinal")) {
			out.println("<option value='0'>--Descripcion Tipo de Evaluacion--</option>");
	    	int tipoEvaluacionFinalId=Integer.parseInt(request.getParameter("tipoEvaluacionFinalId"));
	    	
	    	List<DescripcionEf> listDescripcionEf=descripcionEfService.findDescripcionEfByEvaluacionFinalId(tipoEvaluacionFinalId);
		
	    		for (DescripcionEf descripcionEf : listDescripcionEf) {
	        			out.printf("<option value='%1s'>%2s</option>",descripcionEf.getDescripcionEFID(), descripcionEf.getDescripcionFinal());		
	        }
    		 
        }else if (metodo.equals("cargarDetalleConclusionFinal")) {
			out.println("<option value='0'>--Detalle Tipo de Conclusion--</option>");
	    	int tipoConclusionFinalId=Integer.parseInt(request.getParameter("tipoConclusionFinalId"));
	    	
	    	List<DetalleConcluIf> listDetalleConcluIf=detalleConcluIfService.findDetalleConcluIfByTipoConclusionFinalId(tipoConclusionFinalId);
		
	    		for (DetalleConcluIf detalleConcluIf : listDetalleConcluIf) {
	        			out.printf("<option value='%1s'>%2s</option>",detalleConcluIf.getDetalleConcluIFID(), detalleConcluIf.getDescripcionConclusion());		
	        }
    		 
        }else if (metodo.equals("cargarProgramas")) {
			out.println("<option value='0'>-- Programas --</option>");
	    	int modalidadFinanciamientoId=Integer.parseInt(request.getParameter("modalidadFinanciamientoId"));
	    	
	    	List<Programa> listPrograma=programaService.findProgramaByModalidadFinanciamientoId(modalidadFinanciamientoId);
		
	    		for (Programa programa : listPrograma) {
	        			out.printf("<option value='%1s'>%2s</option>",programa.getProgramaID(), programa.getNombrePrograma());		
	        }
    		 
        }else if (metodo.equals("cargarRestricAreaTematica")) {
			out.println("<option value='0'>-- Area Tematica --</option>");
	    	int programaId=Integer.parseInt(request.getParameter("programaId"));
	    	
	    	List<RestriccionSubAreaTematica> listRestriccionSubAreaTematica= restriccionSubAreaTematicaService.findRestriccionSubAreaTematicaByProgramaId(programaId);
	    	List<SubAreaTematica> listSubAreaTematica=new ArrayList<SubAreaTematica>();
	    	for (RestriccionSubAreaTematica restriccionSubAreaTematica : listRestriccionSubAreaTematica) {
				listSubAreaTematica.add(subAreaTematicaService.findSubAreaTematicaById(restriccionSubAreaTematica.getSubAreaTematica().getSubAreaTematicaID()));
			}
	    	List<TablaEspecifica> listAreaTematica= new ArrayList<TablaEspecifica>();
	    	for (SubAreaTematica subAreaTematica : listSubAreaTematica) {
				TablaEspecifica tablaEspecifica = tablaEspecificaService.findTablaEspecificaById(subAreaTematica.getFkIdtablaespAreaTematica());
				if (!listAreaTematica.contains(tablaEspecifica)){
					listAreaTematica.add(tablaEspecifica);
				}
			}
	    	for (TablaEspecifica tablaEspecifica : listAreaTematica) {
	        			out.printf("<option value='%1s'>%2s</option>",tablaEspecifica.getTablaEspecificaID(), tablaEspecifica.getDescripcionCabecera());		
	        }	 
        }else if (metodo.equals("cargarRestricSubAreaTematica")) {
			out.println("<option value='0'>-- Sub Area Tematica --</option>");
	    	int programaId=Integer.parseInt(request.getParameter("programaId"));
	    	int areaTematicaId=Integer.parseInt(request.getParameter("areaTematicaId"));
	    	
	    	List<SubAreaTematica> listSubAreaTematica= restriccionSubAreaTematicaService.findRestriccionSubAreaTematicaByProgramaIdByAreaTematicaId(programaId,areaTematicaId);

	    	for (SubAreaTematica subAreaTematica : listSubAreaTematica) {
	        			out.printf("<option value='%1s'>%2s</option>",subAreaTematica.getSubAreaTematicaID(), subAreaTematica.getDescripcionSubAt());		
	        }	 
        }else if (metodo.equals("cargarRestricDepartamento")) {
			out.println("<option value='0'>-- Departamento --</option>");
	    	int programaId=Integer.parseInt(request.getParameter("programaId"));
	    	
	    	List<RestriccionDepProvDist> listRestriccionDepProvDist= restriccionDepProvDistService.findRestriccionDepProvDistByProgramaId(programaId);
	    	List<DepProvDist> listDepProvDist= new ArrayList<DepProvDist>();
	    	
	    	for (RestriccionDepProvDist restriccionDepProvDist : listRestriccionDepProvDist) {
	    		//DepProvDist depProvDist =depProvDistService.findListDepartamentos(restriccionDepProvDist.getDepProvDist().getIdDepartamento());
	    		if((restriccionDepProvDist.getDepProvDist().getIdProvincia().equals("0")) && (restriccionDepProvDist.getDepProvDist().getIdDistrito().equals("0"))){
	    			if(!listDepProvDist.contains(restriccionDepProvDist.getDepProvDist())){
	    				listDepProvDist.add(restriccionDepProvDist.getDepProvDist());
	    			}
	    		}else{
	    			
	    			// Validar que no se repita el departamento
    				if(!listDepProvDist.contains(depProvDistService.findListDepartamentos(restriccionDepProvDist.getDepProvDist().getIdDepartamento()))){
	   					listDepProvDist.add(depProvDistService.findListDepartamentos(restriccionDepProvDist.getDepProvDist().getIdDepartamento()));
    				}
	    			//listDepProvDist.add(depProvDistService.findListDepartamentos(restriccionDepProvDist.getDepProvDist().getIdDepartamento()));
	    		}
			}
	    	
	    	for (DepProvDist depProvDist : listDepProvDist) {
	        			out.printf("<option value='%1s'>%2s</option>",depProvDist.getIdDepartamento(), depProvDist.getDescripcion());		
	        }	 
        }else if (metodo.equals("cargaRestricProvincia")) {
			out.println("<option value='0'>-- Provincia --</option>");
	    	int programaId=Integer.parseInt(request.getParameter("programaId"));
	    	String departamentoId=request.getParameter("departamentoId");
	    	
	    	List<RestriccionDepProvDist> listRestriccionDepProvDist= restriccionDepProvDistService.findRestriccionDepProvDistByProgramaId(programaId);
	    	List<DepProvDist> listDepProvDist= new ArrayList<DepProvDist>();
	    	
	    	for (RestriccionDepProvDist restriccionDepProvDist : listRestriccionDepProvDist) {
	    		if((restriccionDepProvDist.getDepProvDist().getIdDepartamento().equals(departamentoId))&& (!restriccionDepProvDist.getDepProvDist().getIdProvincia().equals("0"))&&(restriccionDepProvDist.getDepProvDist().getIdDistrito().equals("0"))){
	    			listDepProvDist.add(restriccionDepProvDist.getDepProvDist());
	    		}else if((restriccionDepProvDist.getDepProvDist().getIdDepartamento().equals(departamentoId))&& (!restriccionDepProvDist.getDepProvDist().getIdProvincia().equals("0"))&&(!restriccionDepProvDist.getDepProvDist().getIdDistrito().equals("0"))){
	    			if(!listDepProvDist.contains(depProvDistService.findListProvincia(departamentoId, restriccionDepProvDist.getDepProvDist().getIdProvincia()))){
	    				listDepProvDist.add(depProvDistService.findListProvincia(departamentoId, restriccionDepProvDist.getDepProvDist().getIdProvincia()));
	    			}
	    			//listDepProvDist.add(depProvDistService.findListProvincia(departamentoId, restriccionDepProvDist.getDepProvDist().getIdProvincia()));
	    		}
			}
	    	if(listDepProvDist.size()==0){
	    		listDepProvDist= depProvDistService.findListProvinciaByDepartamentoId(departamentoId);
	    	}
	    	
	    	for (DepProvDist depProvDist : listDepProvDist) {
	    				out.printf("<option value='%1s'>%2s</option>",depProvDist.getIdProvincia(), depProvDist.getDescripcion());		
	        }	 
        }else if (metodo.equals("cargaRestricDistrito")) {
			out.println("<option value='0'>-- Distrito --</option>");
	    	int programaId=Integer.parseInt(request.getParameter("programaId"));
	    	String departamentoId=request.getParameter("departamentoId");
	    	String provinciaId=request.getParameter("provinciaId");
	    	
	    	List<RestriccionDepProvDist> listRestriccionDepProvDist= restriccionDepProvDistService.findRestriccionDepProvDistByProgramaId(programaId);
	    	List<DepProvDist> listDepProvDist= new ArrayList<DepProvDist>();
	    	
	    	for (RestriccionDepProvDist restriccionDepProvDist : listRestriccionDepProvDist) {
	    		if((restriccionDepProvDist.getDepProvDist().getIdDepartamento().equals(departamentoId))&& (restriccionDepProvDist.getDepProvDist().getIdProvincia().equals(provinciaId))&&(!restriccionDepProvDist.getDepProvDist().getIdDistrito().equals("0"))){
	    			listDepProvDist.add(restriccionDepProvDist.getDepProvDist());
	    		}
			}
	    	if(listDepProvDist.size()==0){
	    		listDepProvDist= depProvDistService.findListProvinciaByDepartamentoIdBuProvinciaId(departamentoId,provinciaId);
	    	}
	    	
	    	for (DepProvDist depProvDist : listDepProvDist) {
	        			out.printf("<option value='%1s'>%2s</option>",depProvDist.getDepProvDistID(), depProvDist.getDescripcion());		
	        }	 
        }
	}

	/*
	@RequestMapping(value = "/principal/cargaUnidadMedida.jspx")
	public void cargaUnidadMedida(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		
		PrintWriter out = null;
		out = response.getWriter();
		//int actividadID = 0;

		
			int metasXActividadId = Integer.parseInt(request
					.getParameter("metasXActividadId"));
			MetaPorActividadBean metaPorActividadBean =metaPorActividadService.llenaMetaPorActividadBean(metaPorActividadService.findMetaPorActividadById(metasXActividadId));
			
			if (!metaPorActividadBean.equals(null)) {
					out.println(" value='" +
							metaPorActividadBean.getDescripcionUnidadMedida()+
							"' ");
				}
			}*/
}
