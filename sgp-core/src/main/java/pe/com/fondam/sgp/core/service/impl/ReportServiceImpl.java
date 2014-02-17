package pe.com.fondam.sgp.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.ActividadDAO;
import pe.com.fondam.sgp.core.dao.BeneficiariosPorResultadoDAO;
import pe.com.fondam.sgp.core.dao.CostoActividadDAO;
import pe.com.fondam.sgp.core.dao.CronogramaCostoActividadDAO;
import pe.com.fondam.sgp.core.dao.DatoPlanOperativoDAO;
import pe.com.fondam.sgp.core.dao.DetalleEstadoCabeceraDAO;
import pe.com.fondam.sgp.core.dao.FuenteFinanciadoraDAO;
import pe.com.fondam.sgp.core.dao.IndicadorResultadoDAO;
import pe.com.fondam.sgp.core.dao.MetaPorActividadDAO;
import pe.com.fondam.sgp.core.dao.PartidaGenericaDAO;
import pe.com.fondam.sgp.core.dao.ResultadoDAO;
import pe.com.fondam.sgp.core.dao.TablaEspecificaDAO;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.MetaPorActividad;
import pe.com.fondam.sgp.core.domain.PartidaGenerica;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.helper.PlanOperativoHelper;
import pe.com.fondam.sgp.core.report.RptBeneficiariosAreaIntervencion;
import pe.com.fondam.sgp.core.report.RptContraPartida;
import pe.com.fondam.sgp.core.report.RptContraPartidaDet;
import pe.com.fondam.sgp.core.report.RptContribucionDonacion;
import pe.com.fondam.sgp.core.report.RptContribucionDonacionDet;
import pe.com.fondam.sgp.core.report.RptCostoActividadFuente;
import pe.com.fondam.sgp.core.report.RptCronogramaActividad;
import pe.com.fondam.sgp.core.report.RptCronogramaActividadDet;
import pe.com.fondam.sgp.core.report.RptDesemboloRecursosDonacion;
import pe.com.fondam.sgp.core.report.RptDesemboloRecursosDonacionDet;
import pe.com.fondam.sgp.core.report.RptEstructuraInversionFinanciamiento;
import pe.com.fondam.sgp.core.report.RptEstructuraInversionFinanciamientoDet;
import pe.com.fondam.sgp.core.report.RptOperacionCostoCronogramaResultado;
import pe.com.fondam.sgp.core.report.RptPlanUsoRecursosOtrasFuentes;
import pe.com.fondam.sgp.core.report.RptPlanUsoRecursosOtrasFuentesDet;
import pe.com.fondam.sgp.core.report.RptResultados;
import pe.com.fondam.sgp.core.report.RptRptCostoActividadFuenteDet;
import pe.com.fondam.sgp.core.service.IndicadorResultadoService;
import pe.com.fondam.sgp.core.service.ReportService;
import pe.com.fondam.sgp.core.util.UtilList;

@Service
public class ReportServiceImpl implements ReportService {

	protected final Log logger = LogFactory.getLog(ReportServiceImpl.class);
	private String institucionNombre1;
	private String institucionNombre2;
	private String institucionNombre3;

	@Resource
	private DetalleEstadoCabeceraDAO detalleEstadoCabeceraDAO;
	
	@Resource
	private DatoPlanOperativoDAO datoPlanOperativoDAO;
	
	@Resource
	private CostoActividadDAO costoActividadDAO;
	
	@Resource
	private CronogramaCostoActividadDAO cronogramaCostoActividadDAO;

	@Resource
	private TablaEspecificaDAO tablaEspecificaDAO;

	@Resource
	private ResultadoDAO resultadoDAO;

	@Resource
	private IndicadorResultadoDAO indicadorResultadoDAO;
	
	@Resource
	private ActividadDAO actividadDAO;
	
	@Resource
	private MetaPorActividadDAO metaPorActividadDAO;
	
	@Resource
	private PartidaGenericaDAO partidaGenericaDAO;
	
	@Resource
	private FuenteFinanciadoraDAO fuenteFinanciadoraDAO;
	
	@Resource
	private PlanOperativoHelper planOperativoHelper;

	@Resource
	private BeneficiariosPorResultadoDAO beneficiariosPorResultadoDAO;

	@Resource
	IndicadorResultadoService indicadorResultadoService;
	
	public String getInstitucionNombre1() {
		return institucionNombre1;
	}

	public void setInstitucionNombre1(String institucionNombre1) {
		this.institucionNombre1 = institucionNombre1;
	}

	public String getInstitucionNombre2() {
		return institucionNombre2;
	}

	public void setInstitucionNombre2(String institucionNombre2) {
		this.institucionNombre2 = institucionNombre2;
	}

	public String getInstitucionNombre3() {
		return institucionNombre3;
	}

	public void setInstitucionNombre3(String institucionNombre3) {
		this.institucionNombre3 = institucionNombre3;
	}

	
	public DetalleEstadoCabeceraDAO getDetalleEstadoCabeceraDAO() {
		return detalleEstadoCabeceraDAO;
	}

	public void setDetalleEstadoCabeceraDAO(
			DetalleEstadoCabeceraDAO detalleEstadoCabeceraDAO) {
		this.detalleEstadoCabeceraDAO = detalleEstadoCabeceraDAO;
	}

	public DatoPlanOperativoDAO getDatoPlanOperativoDAO() {
		return datoPlanOperativoDAO;
	}

	public void setDatoPlanOperativoDAO(DatoPlanOperativoDAO datoPlanOperativoDAO) {
		this.datoPlanOperativoDAO = datoPlanOperativoDAO;
	}

	public CostoActividadDAO getCostoActividadDAO() {
		return costoActividadDAO;
	}

	public void setCostoActividadDAO(CostoActividadDAO costoActividadDAO) {
		this.costoActividadDAO = costoActividadDAO;
	}


	public CronogramaCostoActividadDAO getCronogramaCostoActividadDAO() {
		return cronogramaCostoActividadDAO;
	}

	public void setCronogramaCostoActividadDAO(
			CronogramaCostoActividadDAO cronogramaCostoActividadDAO) {
		this.cronogramaCostoActividadDAO = cronogramaCostoActividadDAO;
	}

	public TablaEspecificaDAO getTablaEspecificaDAO() {
		return tablaEspecificaDAO;
	}

	public void setTablaEspecificaDAO(TablaEspecificaDAO tablaEspecificaDAO) {
		this.tablaEspecificaDAO = tablaEspecificaDAO;
	}

	public ResultadoDAO getResultadoDAO() {
		return resultadoDAO;
	}

	public void setResultadoDAO(ResultadoDAO resultadoDAO) {
		this.resultadoDAO = resultadoDAO;
	}

	public IndicadorResultadoDAO getIndicadorResultadoDAO() {
		return indicadorResultadoDAO;
	}

	public void setIndicadorResultadoDAO(IndicadorResultadoDAO indicadorResultadoDAO) {
		this.indicadorResultadoDAO = indicadorResultadoDAO;
	}

	public ActividadDAO getActividadDAO() {
		return actividadDAO;
	}

	public void setActividadDAO(ActividadDAO actividadDAO) {
		this.actividadDAO = actividadDAO;
	}

	public MetaPorActividadDAO getMetaPorActividadDAO() {
		return metaPorActividadDAO;
	}

	public void setMetaPorActividadDAO(MetaPorActividadDAO metaPorActividadDAO) {
		this.metaPorActividadDAO = metaPorActividadDAO;
	}

	public PartidaGenericaDAO getPartidaGenericaDAO() {
		return partidaGenericaDAO;
	}

	public void setPartidaGenericaDAO(PartidaGenericaDAO partidaGenericaDAO) {
		this.partidaGenericaDAO = partidaGenericaDAO;
	}

	public FuenteFinanciadoraDAO getFuenteFinanciadoraDAO() {
		return fuenteFinanciadoraDAO;
	}

	public void setFuenteFinanciadoraDAO(FuenteFinanciadoraDAO fuenteFinanciadoraDAO) {
		this.fuenteFinanciadoraDAO = fuenteFinanciadoraDAO;
	}

	public PlanOperativoHelper getPlanOperativoHelper() {
		return planOperativoHelper;
	}

	public void setPlanOperativoHelper(PlanOperativoHelper planOperativoHelper) {
		this.planOperativoHelper = planOperativoHelper;
	}

	public BeneficiariosPorResultadoDAO getBeneficiariosPorResultadoDAO() {
		return beneficiariosPorResultadoDAO;
	}

	public void setBeneficiariosPorResultadoDAO(
			BeneficiariosPorResultadoDAO beneficiariosPorResultadoDAO) {
		this.beneficiariosPorResultadoDAO = beneficiariosPorResultadoDAO;
	}

	@Override
	public String planOperativoEstadoDescripcion(Integer detalleEstadoCabeceraID) {
		String estadoDescripcion = "";
		DetalleEstadoCabecera detalleEstadoCabecera = getDetalleEstadoCabeceraDAO()
				.findDetalleEstadoCabeceraById(detalleEstadoCabeceraID);
		if(detalleEstadoCabecera != null){
			estadoDescripcion = detalleEstadoCabecera.getDescripEstado();
		}
		return estadoDescripcion;
	}
	
	@Override
	public List<RptCostoActividadFuente> planOperativoCostoActividadFuente(Integer datoPlanOperativoID, Integer fuenteFinanciadoraID, Integer actividadID) {
		logger.info("start planOperativoCostoActividadFuente");
		List<RptCostoActividadFuente> listRptCostoActividadFuente = new ArrayList<RptCostoActividadFuente>(); 
		List<Integer> listPartida = new ArrayList<Integer>();
		List<CronogramaCostoActividad> listCronogramaCostoActividad  = getCronogramaCostoActividadDAO().findCronogramaCostoActividadByFuenteFinanciadoraID(fuenteFinanciadoraID);
		List<CostoActividad> listCostoActividad = new ArrayList<CostoActividad>();
		
		for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
//			Integer actividadId = cronogramaCostoActividad.getCostoActividad().getCostoActividadID();
//			logger.info("actividadID = " + actividadId);
//			logger.info("cronogramaCostoActividad - actividadId = " + actividadId);
//			if(actividadID.intValue() == actividadId.intValue()){
//				logger.info("son iguales actividadID = actividadId" + actividadID + " = "+ actividadId);
				CostoActividad costoActividad = cronogramaCostoActividad.getCostoActividad();
				if(!listCostoActividad.contains(costoActividad)){
					listCostoActividad.add(costoActividad);
				}	
//			}
		}
		for (CostoActividad costoActividad : listCostoActividad) {
			Integer partidaGenericaID = costoActividad.getPartidaGenerica().getPartidaGenericaID();
			if(!listPartida.contains(partidaGenericaID)){
				listPartida.add(partidaGenericaID);
			}
		}
		for (Integer partidaGenericaID : listPartida) {
			logger.info("partidaGenericaID = " + partidaGenericaID);
			RptCostoActividadFuente rptCostoActividadFuente = setDataRptCostoActividadFuente(fuenteFinanciadoraID, actividadID, partidaGenericaID, listCostoActividad, listCronogramaCostoActividad);
			listRptCostoActividadFuente.add(rptCostoActividadFuente);
		}		
		logger.info("finish planOperativoCostoActividadFuente");
		return listRptCostoActividadFuente;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RptResultados> planOperativoResultadosByDatoPlanOperativoID(Integer datoPlanOperativoID) {
		//logger.info("start planOperativoCronogramaActividadByDatoPlanOperativoID");
		List<RptResultados>  listResultadosReport = new ArrayList<RptResultados>();
		List<Resultado> listResultados = getResultadoDAO().findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		if(!listResultados.isEmpty()){
			listResultados = (List<Resultado>) UtilList.orderAscList(listResultados, "codigoResultado");
		}
		
		for (Resultado resultado : listResultados) {
			RptResultados resultadosReport = new RptResultados();
			
			resultadosReport.setCodigoResultado(resultado.getCodigoResultado());
			resultadosReport.setDefinicionResultado(resultado.getDefinicionResultado());
			resultadosReport.setSupuestoResultado(resultado.getSupuestoResultado());
			//Integer resultadoID = resultado.getResultadoID();
			resultadosReport.setListIndicadorResultado(indicadorResultadoService.llenaIndicadorResultadoCompleto( getIndicadorResultadoDAO().findIndicadorResultadoByResultadoID(resultado.getResultadoID())));
			/*if(!listIndicadorResultado.isEmpty()){
				IndicadorResultado indicadorResultado = listIndicadorResultado.get(0);
				resultadosReport.setIndicadorResultado(indicadorResultado.getDefinicionIndicador());
				resultadosReport.setDefinicionIndicador(indicadorResultado.getDefinicionIndicador());
				String unidadMedidaDescripcion = getTablaEspecificaDAO().findTablaEspecificaById(indicadorResultado.getFkIdtablaespUnidadMedida()).getDescripcionCabecera();
				resultadosReport.setUnidadMedida(unidadMedidaDescripcion);
				resultadosReport.setMedioVerificacion(indicadorResultado.getMedioVerificacion());
				
				String situacionInicial = "";
				String situacionFinal = "";
				if(indicadorResultado.getSituacionActual() != null){
					situacionInicial = indicadorResultado.getSituacionActual().toString();
				}
				if(indicadorResultado.getSituacionFinal() != null){
					situacionFinal = indicadorResultado.getSituacionFinal().toString();
				}
				resultadosReport.setSituacionInicial(situacionInicial);
				resultadosReport.setSituacionFinal(situacionFinal);
			}*/
			listResultadosReport.add(resultadosReport);
		}
		//logger.info("finish planOperativoCronogramaActividadByDatoPlanOperativoID");
		return listResultadosReport;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RptCronogramaActividad> planOperativoCronogramaActividadByDatoPlanOperativoID(Integer datoPlanOperativoID) {
		logger.info("start planOperativoCronogramaActividadByDatoPlanOperativoID");
		logger.info("datoPlanOperativoID = " + datoPlanOperativoID);
		List<RptCronogramaActividad> listRptCronogramaActividad = new ArrayList<RptCronogramaActividad>();
		List<Resultado> listResultados = getResultadoDAO().findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		if(!listResultados.isEmpty()){
			listResultados = (List<Resultado>) UtilList.orderAscList(listResultados, "codigoResultado");
		}
		for (Resultado resultado : listResultados) {
			RptCronogramaActividad rptCronogramaActividad = setDataRptCronogramaActividad(resultado);
			listRptCronogramaActividad.add(rptCronogramaActividad);
		}
		logger.info("finish planOperativoCronogramaActividadByDatoPlanOperativoID");
		return listRptCronogramaActividad;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RptEstructuraInversionFinanciamiento> planOperativoEstructuraInversionFinanciamiento(
			Integer datoPlanOperativoID) {
		List<RptEstructuraInversionFinanciamiento> listRptEstructuraInversionFinanciamiento = new ArrayList<RptEstructuraInversionFinanciamiento>();
		List<Resultado> listResultado = getResultadoDAO().findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		if(!listResultado.isEmpty()){
			listResultado = (List<Resultado>) UtilList.orderAscList(listResultado, "codigoResultado");
		}
		for (Resultado resultado : listResultado) {
			RptEstructuraInversionFinanciamiento rptEstructuraInversionFinanciamiento = setDataRptEstructuraInversionFinanciamiento(resultado);
			listRptEstructuraInversionFinanciamiento.add(rptEstructuraInversionFinanciamiento);
		}
		return listRptEstructuraInversionFinanciamiento;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RptBeneficiariosAreaIntervencion> planOperativoBeneficiariosAreaIntervencionDirectos(
			Integer datoPlanOperativoID) {
		logger.info("datoPlanOperativoID = " + datoPlanOperativoID);
		List<RptBeneficiariosAreaIntervencion> listRptBeneficiariosAreaIntervencion = new ArrayList<RptBeneficiariosAreaIntervencion>();
		List<Resultado> listResultado = getResultadoDAO().findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		if(!listResultado.isEmpty()){
			listResultado = (List<Resultado>) UtilList.orderAscList(listResultado, "codigoResultado");			
		}
		logger.info("listResultado.size = " + listResultado.size());
		for (Resultado resultado : listResultado) {
			RptBeneficiariosAreaIntervencion beneficiariosAreaIntervencion = setDataRptBeneficiariosAreaIntervencion(resultado);
			if(beneficiariosAreaIntervencion != null){
				listRptBeneficiariosAreaIntervencion.add(beneficiariosAreaIntervencion);	
			}
		}
		return listRptBeneficiariosAreaIntervencion;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RptOperacionCostoCronogramaResultado> planOperativoOperacionesCostosCronogramaResultados(
			Integer datoPlanOperativoID) {

		List<RptOperacionCostoCronogramaResultado> listRptOperacionCostoCronogramaResultado = new ArrayList<RptOperacionCostoCronogramaResultado>(); 
		List<Resultado> listResultado = getResultadoDAO().findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		if(!listResultado.isEmpty()){
			listResultado = (List<Resultado>) UtilList.orderAscList(listResultado, "codigoResultado");			
		}
		for (Resultado resultado : listResultado) {
			RptOperacionCostoCronogramaResultado operacionCostoCronogramaResultado = setDataRptOperacionCostoCronogramaResultado(resultado);
			if(operacionCostoCronogramaResultado != null){
				listRptOperacionCostoCronogramaResultado.add(operacionCostoCronogramaResultado);
			}
		}
		return listRptOperacionCostoCronogramaResultado;
	}
	
	@Override
	public List<RptPlanUsoRecursosOtrasFuentes> planOperativoPlanUsoRecursosOtrasFuentes(
			Integer datoPlanOperativoID) {
		logger.info("planOperativoPlanUsoRecursosOtrasFuentes start");
		List<RptPlanUsoRecursosOtrasFuentes> listRptPlanUsoRecursosOtrasFuentes = new ArrayList<RptPlanUsoRecursosOtrasFuentes>();
		RptPlanUsoRecursosOtrasFuentes rptPlanUsoRecursosOtrasFuentes = null;
		
		List<Resultado> listResultados = getResultadoDAO().findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		List<Actividad> listActividadAll = new ArrayList<Actividad>();
		List<CostoActividad> listCostoActividadAll = new ArrayList<CostoActividad>();
		
		for (Resultado resultado : listResultados) {
			Integer resultadoID = resultado.getResultadoID();
			listActividadAll.addAll(getActividadDAO().findActividadByResultadoID(resultadoID));
		}
		for (Actividad actividad : listActividadAll) {
			Integer actividadID = actividad.getActividadID();
			listCostoActividadAll.addAll(getCostoActividadDAO().findCostoActividadByActividadID(actividadID));
		}	
		
		List<Integer> listPartida = new ArrayList<Integer>();
		for (CostoActividad costoActividad : listCostoActividadAll) {
			Integer partidaGenericaID = costoActividad.getPartidaGenerica().getPartidaGenericaID();
			if(!listPartida.contains(partidaGenericaID)){
				listPartida.add(partidaGenericaID);
			}
		}
		for (Integer partidaGenericaID : listPartida) {
			logger.info("wrm  partidaGenericaID = " + partidaGenericaID);
			rptPlanUsoRecursosOtrasFuentes = setDataRptPlanUsoRecursosOtrasFuentes(listCostoActividadAll, partidaGenericaID);
			listRptPlanUsoRecursosOtrasFuentes.add(rptPlanUsoRecursosOtrasFuentes);
		}
		for (RptPlanUsoRecursosOtrasFuentes planUsoRecursosOtrasFuentes : listRptPlanUsoRecursosOtrasFuentes) {
			 List<RptPlanUsoRecursosOtrasFuentesDet> detalles = planUsoRecursosOtrasFuentes.getDetalles();
			logger.info("Rubro = " + planUsoRecursosOtrasFuentes.getRubro());
			for (RptPlanUsoRecursosOtrasFuentesDet rptPlanUsoRecursosOtrasFuentesDet : detalles) {
				logger.info("Actividad = " + rptPlanUsoRecursosOtrasFuentesDet.getActividad());
			}
		}
		logger.info("planOperativoPlanUsoRecursosOtrasFuentes finish");
		return listRptPlanUsoRecursosOtrasFuentes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RptContribucionDonacion> planOperativoContribucionDonacion(
			Integer datoPlanOperativoID, Integer fuenteFinanciadoraID) {
		logger.info("planOperativoContribucionDonacion start");
		logger.info("datoPlanOperativoID = " + datoPlanOperativoID);
		logger.info("fuenteFinanciadoraID = " + fuenteFinanciadoraID);
		List<RptContribucionDonacion> listRptContribucionDonacion = new ArrayList<RptContribucionDonacion>();
		RptContribucionDonacion rptContribucionDonacion = null;
		List<Resultado> listResultados = getResultadoDAO()
				.findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		listResultados = (List<Resultado>) UtilList.orderAscList(listResultados, "resultadoID");
		for (Resultado resultado : listResultados) {
			Integer resultadoID = resultado.getResultadoID();
			List<RptContribucionDonacionDet> detalles = setDataListRptContribucionDonacionDet(resultadoID, fuenteFinanciadoraID);
			rptContribucionDonacion = new RptContribucionDonacion();
			rptContribucionDonacion.setResultado(""
					+ resultado.getCodigoResultado() + " - "
					+ resultado.getDefinicionResultado());
			rptContribucionDonacion.setDetalles(detalles);
			listRptContribucionDonacion.add(rptContribucionDonacion);
		}
		BigDecimal montoTotal = montoTotalList(listRptContribucionDonacion);
		logger.info("montoTotal = " + montoTotal);
		for (RptContribucionDonacion contribucionDonacion : listRptContribucionDonacion) {
			List<RptContribucionDonacionDet> detalles = contribucionDonacion.getDetalles();
			for (RptContribucionDonacionDet rptContribucionDonacionDet : detalles) {
				if(rptContribucionDonacionDet.getMontoTotal() != null){
					BigDecimal v1 = rptContribucionDonacionDet.getMontoTotal();
					BigDecimal v2 = montoTotal;
					//BigDecimal porcentaje = v1.divide(v2, 2, RoundingMode.HALF_UP);
					BigDecimal porcentaje = v1.divide(v2);
					porcentaje = porcentaje.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP);
					logger.info("porcentaje = " + porcentaje);
					rptContribucionDonacionDet.setPorcentaje(porcentaje);
				}
			}
		}
		logger.info("planOperativoContribucionDonacion finish");
		return listRptContribucionDonacion;
	}
	
	private BigDecimal montoTotalList(List<RptContribucionDonacion> listRptContribucionDonacion){
		BigDecimal total = BigDecimal.ZERO; 
		for (RptContribucionDonacion rptContribucionDonacion : listRptContribucionDonacion) {
			List<RptContribucionDonacionDet> detalles = rptContribucionDonacion.getDetalles();
			for (RptContribucionDonacionDet rptContribucionDonacionDet : detalles) {
				if(rptContribucionDonacionDet.getMontoTotal() != null){
					total = total.add(rptContribucionDonacionDet.getMontoTotal());	
				}
			}
		}
		total = total.setScale(2, BigDecimal.ROUND_UP);
		return total;
	}
	
	@SuppressWarnings("unchecked")
	private List<RptContribucionDonacionDet> setDataListRptContribucionDonacionDet(Integer resultadoID, Integer fuenteFinanciadoraID){
		List<RptContribucionDonacionDet> detalles = new ArrayList<RptContribucionDonacionDet>();
		List<Actividad> listActividad = getActividadDAO() .findActividadByResultadoID(resultadoID);
		if(!listActividad.isEmpty()){
			listActividad = (List<Actividad>) UtilList.orderAscList(listActividad, "actividadID");	
		}
		for (Actividad actividad : listActividad) {
			RptContribucionDonacionDet detalle = setDataRptContribucionDonacionDet(actividad, fuenteFinanciadoraID);
			detalles.add(detalle);
		}
		return detalles;
	}
	
	private RptContribucionDonacionDet setDataRptContribucionDonacionDet(Actividad actividad, Integer fuenteFinanciadoraID){
		Integer actividadID = actividad.getActividadID();
		RptContribucionDonacionDet detalle = new RptContribucionDonacionDet();
		List<CostoActividad> listCostoActividad = new ArrayList<CostoActividad>();
		List<CronogramaCostoActividad> listCronogramaCostoActividad = new ArrayList<CronogramaCostoActividad>();
		listCostoActividad = getCostoActividadDAO().findCostoActividadByActividadID(actividadID);
		
		BigDecimal sueldoManoObrasAcumulado =  BigDecimal.ZERO;
		BigDecimal pasajesViaticosAcumulado =  BigDecimal.ZERO;
		BigDecimal bienesAcumulado =  BigDecimal.ZERO;
		BigDecimal serviciosAcumulado =  BigDecimal.ZERO;
		
		BigDecimal invObrasAcumulado =  BigDecimal.ZERO;
		BigDecimal invVehiculoEquiposAcumulado =  BigDecimal.ZERO;
		BigDecimal otrasInversionesAcumulado =  BigDecimal.ZERO;
		BigDecimal otrosAcumulado =  BigDecimal.ZERO;
		BigDecimal totalAcumulado =  BigDecimal.ZERO;
		
		for (CostoActividad costoActividad : listCostoActividad) {
			Integer costoActividadID = costoActividad.getCostoActividadID();
			listCronogramaCostoActividad.addAll(getCronogramaCostoActividadDAO()
							.findCronogramaCostoActividadByCostoActividadIdAndFuenteFinanciadoraID(
									costoActividadID, fuenteFinanciadoraID));
		}

		List<CostoActividad> listCostoActividadFuente = new ArrayList<CostoActividad>();
		for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
			CostoActividad costoActividad = cronogramaCostoActividad
					.getCostoActividad();
			if (!listCostoActividadFuente.contains(costoActividad)) {
				listCostoActividadFuente.add(costoActividad);
			}
		}
		logger.info("listCostoActividadFuente.size = " + listCostoActividadFuente.size());
		for (CostoActividad costoActividad : listCostoActividadFuente) {
			sueldoManoObrasAcumulado = sueldoManoObrasAcumulado.add(sueldoManoObras(costoActividad));
			pasajesViaticosAcumulado = pasajesViaticosAcumulado.add(pasajesViaticos(costoActividad));
			bienesAcumulado = bienesAcumulado.add(bienes(costoActividad));
			serviciosAcumulado = serviciosAcumulado.add(servicios(costoActividad));
			invObrasAcumulado = invObrasAcumulado.add(invObras(costoActividad));
			invVehiculoEquiposAcumulado = invVehiculoEquiposAcumulado.add(invVehiculoEquipos(costoActividad));
			otrasInversionesAcumulado = otrasInversionesAcumulado.add(otrasInversiones(costoActividad));
			otrosAcumulado = otrosAcumulado.add(otros(costoActividad));	
		}
		
		detalle.setActividad(actividad.getResultado().getCodigoResultado() +"."+ actividad.getCodigoActividad() + " - "
				+ actividad.getNombreActividad()); 
		
		sueldoManoObrasAcumulado = sueldoManoObrasAcumulado.setScale(2, BigDecimal.ROUND_UP);
		pasajesViaticosAcumulado = pasajesViaticosAcumulado.setScale(2, BigDecimal.ROUND_UP);
		bienesAcumulado = bienesAcumulado.setScale(2, BigDecimal.ROUND_UP);
		serviciosAcumulado = serviciosAcumulado.setScale(2, BigDecimal.ROUND_UP);
		invObrasAcumulado = invObrasAcumulado.setScale(2, BigDecimal.ROUND_UP);
		invVehiculoEquiposAcumulado = invVehiculoEquiposAcumulado.setScale(2, BigDecimal.ROUND_UP);
		otrasInversionesAcumulado = otrasInversionesAcumulado.setScale(2, BigDecimal.ROUND_UP);
		otrosAcumulado = otrosAcumulado.setScale(2, BigDecimal.ROUND_UP);
		
		detalle.setMontoSueloManoObras(returnNullIsZeroValue(sueldoManoObrasAcumulado));
		detalle.setMontoPasajeViaticos(returnNullIsZeroValue(pasajesViaticosAcumulado));
		detalle.setMontoBienes(returnNullIsZeroValue(bienesAcumulado));
		detalle.setMontoServicios(returnNullIsZeroValue(serviciosAcumulado));
		detalle.setMontoInvObras(returnNullIsZeroValue(invObrasAcumulado));
		detalle.setMontoInvVehiculoEquipos(returnNullIsZeroValue(invVehiculoEquiposAcumulado));
		detalle.setMontoOtrasInversiones(returnNullIsZeroValue(otrasInversionesAcumulado));
		detalle.setMontoOtros(returnNullIsZeroValue(otrosAcumulado));
		
		totalAcumulado = totalAcumulado.add(sueldoManoObrasAcumulado)
		.add(pasajesViaticosAcumulado).add(bienesAcumulado)
		.add(serviciosAcumulado).add(invObrasAcumulado)
		.add(invVehiculoEquiposAcumulado)
		.add(otrasInversionesAcumulado).add(otrosAcumulado);
		
		totalAcumulado = totalAcumulado.setScale(2, BigDecimal.ROUND_UP);
		
		detalle.setMontoTotal(returnNullIsZeroValue(totalAcumulado));
		return detalle;
	}

	private BigDecimal returnNullIsZeroValue(BigDecimal value){
		BigDecimal newValue = null;
		if (value.intValue() == 0) {
			newValue = null;
		}else{
			newValue = value;
		}
		return newValue;
	}
	
	private BigDecimal sueldoManoObras(CostoActividad costoActividad){
		BigDecimal sueldoManoObras = BigDecimal.ZERO;
		Integer partidaGenericaID5 = 5;
		Integer partidaGenericaID10 = 10;
		Integer partidaGenericaID15 = 15;
		
		if (costoActividad.getRubroGenerico() == null) {
			Integer partidaGenericaID = costoActividad.getPartidaGenerica().getPartidaGenericaID();
			if ((partidaGenericaID.intValue() == partidaGenericaID5.intValue())
					|| (partidaGenericaID.intValue() == partidaGenericaID10
							.intValue())
					|| (partidaGenericaID.intValue() == partidaGenericaID15
							.intValue())){
				
				Double costoTotal = costoActividad.getCantidadTotal() * costoActividad.getPrecioUnitario();
				sueldoManoObras = new BigDecimal(costoTotal);
				
			}	
		}
		return sueldoManoObras;
	}
	
	private BigDecimal pasajesViaticos(CostoActividad costoActividad) {
		BigDecimal sueldoPasajesViaticos = BigDecimal.ZERO;
		Integer partidaGenericaID2 = 2;
		Integer partidaGenericaID6 = 6;
		Integer partidaGenericaID16 = 16;

		if (costoActividad.getRubroGenerico() == null) {
			Integer partidaGenericaID = costoActividad.getPartidaGenerica()
					.getPartidaGenericaID();
			if ((partidaGenericaID.intValue() == partidaGenericaID2.intValue())
					|| (partidaGenericaID.intValue() == partidaGenericaID6
							.intValue())
					|| (partidaGenericaID.intValue() == partidaGenericaID16
							.intValue())) {

				Double costoTotal = costoActividad.getCantidadTotal()
						* costoActividad.getPrecioUnitario();
				sueldoPasajesViaticos = new BigDecimal(costoTotal);
			}
		}
		return sueldoPasajesViaticos;
	}
	
	private BigDecimal bienes(CostoActividad costoActividad) {
		BigDecimal bienes = BigDecimal.ZERO;
		Integer partidaGenericaID3 = 3;
		Integer partidaGenericaID7 = 7;
		Integer partidaGenericaID17 = 17;

		if (costoActividad.getRubroGenerico() == null) {
			Integer partidaGenericaID = costoActividad.getPartidaGenerica()
					.getPartidaGenericaID();
			if ((partidaGenericaID.intValue() == partidaGenericaID3.intValue())
					|| (partidaGenericaID.intValue() == partidaGenericaID7
							.intValue())
					|| (partidaGenericaID.intValue() == partidaGenericaID17
							.intValue())) {

				Double costoTotal = costoActividad.getCantidadTotal()
						* costoActividad.getPrecioUnitario();
				bienes = new BigDecimal(costoTotal);
			}
		}
		logger.info("monto bienes = " + bienes);
		return bienes;
	}
	
	private BigDecimal servicios(CostoActividad costoActividad) {
		BigDecimal servicios = BigDecimal.ZERO;
		Integer partidaGenericaID4 = 4;
		Integer partidaGenericaID8 = 8;
		Integer partidaGenericaID18 = 18;
		if (costoActividad.getRubroGenerico() == null) {
			Integer partidaGenericaID = costoActividad.getPartidaGenerica()
					.getPartidaGenericaID();
			if ((partidaGenericaID.intValue() == partidaGenericaID4.intValue())
					|| (partidaGenericaID.intValue() == partidaGenericaID8
							.intValue())
					|| (partidaGenericaID.intValue() == partidaGenericaID18
							.intValue())) {

				Double costoTotal = costoActividad.getCantidadTotal()
						* costoActividad.getPrecioUnitario();
				servicios = new BigDecimal(costoTotal);
			}
		}
		return servicios;
	}
	
	private BigDecimal invObras(CostoActividad costoActividad) {
		BigDecimal invObras = BigDecimal.ZERO;
		Integer rubroGenericaID1 = 1;
		Integer rubroGenericaID2 = 2;
		Integer rubroGenericaID3 = 3;

		Integer rubroGenericoID = null;
		if(costoActividad.getRubroGenerico() != null){
			rubroGenericoID = costoActividad.getRubroGenerico().getRubroGenericoID();	
		}
		if (rubroGenericoID != null) {
			if ((rubroGenericoID.intValue() == rubroGenericaID1.intValue())
					|| (rubroGenericoID.intValue() == rubroGenericaID2.intValue())
					|| (rubroGenericoID.intValue() == rubroGenericaID3.intValue())) {

				Double costoTotal = costoActividad.getCantidadTotal()
						* costoActividad.getPrecioUnitario();
				invObras = new BigDecimal(costoTotal);
			}
		}
		return invObras;
	}
	
	private BigDecimal invVehiculoEquipos(CostoActividad costoActividad) {
		BigDecimal invVehiculoEquipos = BigDecimal.ZERO;
		Integer rubroGenericaID4 = 4;
		Integer rubroGenericaID5 = 5;
		Integer rubroGenericaID6 = 6;
		Integer rubroGenericaID7 = 7;
		Integer rubroGenericaID8 = 8;

		Integer rubroGenericoID = null;
		if(costoActividad.getRubroGenerico() != null){
			rubroGenericoID = costoActividad.getRubroGenerico().getRubroGenericoID();	
		}
		if (rubroGenericoID != null) {
			if ((rubroGenericoID.intValue() == rubroGenericaID4.intValue())
					|| (rubroGenericoID.intValue() == rubroGenericaID5.intValue())
					|| (rubroGenericoID.intValue() == rubroGenericaID6.intValue())
					|| (rubroGenericoID.intValue() == rubroGenericaID7.intValue())
					|| (rubroGenericoID.intValue() == rubroGenericaID8.intValue())) {

				Double costoTotal = costoActividad.getCantidadTotal()
						* costoActividad.getPrecioUnitario();
				invVehiculoEquipos = new BigDecimal(costoTotal);
			}
		}
		return invVehiculoEquipos;
	}
	
	private BigDecimal otrasInversiones(CostoActividad costoActividad) {
		BigDecimal otrasInversiones = BigDecimal.ZERO;
		Integer rubroGenericaID9 = 9;
		Integer rubroGenericaID10 = 10;
		Integer rubroGenericaID11 = 11;
		Integer rubroGenericaID12 = 12;

		Integer rubroGenericoID = null;
		if(costoActividad.getRubroGenerico() != null){
			rubroGenericoID = costoActividad.getRubroGenerico().getRubroGenericoID();	
		}
		if (rubroGenericoID != null) {
			if ((rubroGenericoID.intValue() == rubroGenericaID9.intValue())
					|| (rubroGenericoID.intValue() == rubroGenericaID10.intValue())
					|| (rubroGenericoID.intValue() == rubroGenericaID11.intValue())
					|| (rubroGenericoID.intValue() == rubroGenericaID12.intValue())) {

				Double costoTotal = costoActividad.getCantidadTotal()
						* costoActividad.getPrecioUnitario();
				otrasInversiones = new BigDecimal(costoTotal);
			}
		}
		return otrasInversiones;
	}
	
	private BigDecimal otros(CostoActividad costoActividad) {
		BigDecimal otros = BigDecimal.ZERO;
		return otros;
	}
	
	private RptPlanUsoRecursosOtrasFuentes setDataRptPlanUsoRecursosOtrasFuentes(List<CostoActividad> listCostoActividadAll, Integer partidaGenericaID){

		RptPlanUsoRecursosOtrasFuentes planUsoRecursosOtrasFuentes = new RptPlanUsoRecursosOtrasFuentes();
		List<RptPlanUsoRecursosOtrasFuentesDet> detalles  = new ArrayList<RptPlanUsoRecursosOtrasFuentesDet>();
		RptPlanUsoRecursosOtrasFuentesDet detalle = null;
		
		for (CostoActividad costoActividad : listCostoActividadAll) {
			Integer partGenId = costoActividad.getPartidaGenerica().getPartidaGenericaID();
			if (partidaGenericaID.intValue() == partGenId.intValue()) {
				Integer actividadID = costoActividad.getActividad().getActividadID();
				String nombreActividad = getNombreActividad(actividadID);
				detalle = new RptPlanUsoRecursosOtrasFuentesDet();
				detalle.setActividad(nombreActividad);
				detalles.add(detalle);
				
			}
		}
		String rubro = getPartidaGenericaDescripcion(partidaGenericaID);
		planUsoRecursosOtrasFuentes.setRubro(rubro);
		planUsoRecursosOtrasFuentes.setDetalles(detalles);
		return planUsoRecursosOtrasFuentes;
	}
	
	private RptOperacionCostoCronogramaResultado setDataRptOperacionCostoCronogramaResultado(
			Resultado resultado) {
		RptOperacionCostoCronogramaResultado operacionCostoCronogramaResultado = null;

		operacionCostoCronogramaResultado = new RptOperacionCostoCronogramaResultado();
		String resultadoString = "" + resultado.getCodigoResultado() + " - "
				+ resultado.getDefinicionResultado();
		operacionCostoCronogramaResultado.setResultado(resultadoString);
		
		Integer metaResultado = resultado.getMetaResultado();
		Integer duracionMeses = resultado.getDuracionMeses();
		String meta = "";
		String tiempoEjecucion = "";
		
		if (metaResultado != null) {
			meta = metaResultado.toString(); 
		}
		
		if (duracionMeses != null) {
			tiempoEjecucion = duracionMeses.toString(); 
		}
		operacionCostoCronogramaResultado.setMeta(meta);
		operacionCostoCronogramaResultado.setTiempoEjecucion(tiempoEjecucion);
		operacionCostoCronogramaResultado.setPeriodo1(BigDecimal.ZERO);
		operacionCostoCronogramaResultado.setPeriodo2(BigDecimal.ZERO);
		operacionCostoCronogramaResultado.setPeriodo3(BigDecimal.ZERO);

		return operacionCostoCronogramaResultado;
	}
	
	private RptBeneficiariosAreaIntervencion setDataRptBeneficiariosAreaIntervencion(Resultado resultado){
		
		RptBeneficiariosAreaIntervencion beneficiariosAreaIntervencion = null;
		
		Integer resultadoID = resultado.getResultadoID();
		Integer tipoBeneficiarioID = 115; 
		Integer estratoID = 114;
		
		logger.info("resultadoID = " + resultadoID);
		logger.info("tipoBeneficiarioID = " + tipoBeneficiarioID);
		logger.info("estratoID = " + estratoID);
		BeneficiariosPorResultado beneficiariosPorResultado = getBeneficiariosPorResultadoDAO()
				.findBeneficiariosPorResultadoByResultadoIDAndTipoBeneficiarioIDAndEstratoID(
						resultadoID, tipoBeneficiarioID, estratoID);
		
		logger.info("beneficiariosPorResultado = " + beneficiariosPorResultado);
		
		if(beneficiariosPorResultado != null){
			beneficiariosAreaIntervencion = new RptBeneficiariosAreaIntervencion();
			String resultadoString = "" + resultado.getCodigoResultado() + " - "
					+ resultado.getDefinicionResultado();
			beneficiariosAreaIntervencion.setResultado(resultadoString);
			beneficiariosAreaIntervencion.setFamilias(new BigDecimal(
					beneficiariosPorResultado.getCantidadProgramado()));	
		}
		return beneficiariosAreaIntervencion;
	}
	
	@SuppressWarnings("unchecked")
	private RptCronogramaActividad setDataRptCronogramaActividad(Resultado resultado){
		List<RptCronogramaActividadDet> detalles = new ArrayList<RptCronogramaActividadDet>();
		RptCronogramaActividad rptCronogramaActividad = new RptCronogramaActividad();
		rptCronogramaActividad.setResultado(resultado.getCodigoResultado() + " - " + resultado.getDefinicionResultado());
		Integer resultadoID = resultado.getResultadoID();
		List<Actividad> listActividad = getActividadDAO().findActividadByResultadoID(resultadoID);
		if(!listActividad.isEmpty()){
			listActividad = (List<Actividad>) UtilList.orderAscList(listActividad, "codigoActividad");
		}
		RptCronogramaActividadDet detalle = null;
		int index = 1;
		for (Actividad actividad : listActividad) {
			Integer actividadID = actividad.getActividadID();
			MetaPorActividad metaPorActividad = getMetaPorActividadDAO().findMetaPorActividadByActividadId(actividadID);
			String unidadMedidaDescripcion = getTablaEspecificaDAO().findTablaEspecificaById(metaPorActividad.getFkIdtablaespUnidadMedida()).getDescripcionCabecera();
			detalle = new RptCronogramaActividadDet();
			detalle.setActividad(resultado.getCodigoResultado() + "." + actividad.getCodigoActividad() + " - " + actividad.getNombreActividad());
			detalle.setDuracion(actividad.getDuracionMeses().toString());
			detalle.setUnidad(unidadMedidaDescripcion);
			detalle.setCantidad(metaPorActividad.getCantidadMetaActividad().toString());
			
			if(actividad.getCodigoActividad().intValue()==1){
				detalle.setPeriodo1(new BigDecimal(metaPorActividad.getCantidadMetaActividad()));	
				detalle.setPeriodo2(BigDecimal.ZERO);
				detalle.setPeriodo3(BigDecimal.ZERO);
			}else if(actividad.getCodigoActividad().intValue()==2){
				detalle.setPeriodo1(BigDecimal.ZERO);
				detalle.setPeriodo2(new BigDecimal(metaPorActividad.getCantidadMetaActividad()));
				detalle.setPeriodo3(BigDecimal.ZERO);
			}else if(actividad.getCodigoActividad().intValue()==2){
				detalle.setPeriodo1(BigDecimal.ZERO);
				detalle.setPeriodo2(BigDecimal.ZERO);
				detalle.setPeriodo3(new BigDecimal(metaPorActividad.getCantidadMetaActividad()));
			}else{
				detalle.setPeriodo1(new BigDecimal(metaPorActividad.getCantidadMetaActividad()));
				detalle.setPeriodo2(BigDecimal.ZERO);
				detalle.setPeriodo3(BigDecimal.ZERO);
			}
			
			
			
			detalles.add(detalle);
			index ++;
		}
		rptCronogramaActividad.setDetalles(detalles);
		return rptCronogramaActividad;
	}

	private RptCostoActividadFuente setDataRptCostoActividadFuente(Integer fuenteFinanciadoraID, Integer actividadID, Integer partidaGenericaID, List<CostoActividad> listCostoActividad, List<CronogramaCostoActividad> listCronogramaCostoActividad){
		logger.info("============= WARREN AQUI =============");
		logger.info("fuenteFinanciadoraID = " + fuenteFinanciadoraID);
		logger.info("actividadID = " + actividadID);
		logger.info("partidaGenericaID = " + partidaGenericaID);
		RptCostoActividadFuente rptCostoActividadFuente = new RptCostoActividadFuente();
		List<RptRptCostoActividadFuenteDet> detalles = new ArrayList<RptRptCostoActividadFuenteDet>();
		RptRptCostoActividadFuenteDet detalle = null;
		for (CostoActividad costoActividad : listCostoActividad) {
			Integer costoActividadID = costoActividad.getCostoActividadID();
			Integer partGenId = costoActividad.getPartidaGenerica().getPartidaGenericaID();
			if (partidaGenericaID.intValue() == partGenId.intValue()) {
				detalle = new RptRptCostoActividadFuenteDet();
				for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
					Integer costoActividadId = cronogramaCostoActividad.getCostoActividad().getCostoActividadID();
					logger.info("CostoActividad-costoActividadID = " + costoActividadID);
					logger.info("CronogramaCostoActividad-costoActividadId = " + costoActividadId);
					if (costoActividadID.intValue() == costoActividadId.intValue()) {
						logger.info("ENTRO A LA CONDICION SON IGUALES  costoActividadID=costoActividadId " + costoActividadID + " = " + costoActividadId);
						logger.info("cronogramaCostoActividad = " + cronogramaCostoActividad.getCronogramaCostoActividadID());
						detalle.setDescripcion(costoActividad
								.getPartidaEspecifica().getDescripcionPartidaEspecifica()
								+ " - "
								+ costoActividad.getDetallePartidaGenerica());
						
						String unidad = getTablaEspecificaDAO().findTablaEspecificaById(costoActividad.getFkIdtablaespUnidadMedida()).getDescripcionCabecera();
						detalle.setUnidad(unidad);
						detalle.setCantidad(costoActividad.getCantidadTotal().toString());
						detalle.setPrecio(costoActividad.getPrecioUnitario().toString());
						Double costoTotal = costoActividad.getCantidadTotal().doubleValue() * costoActividad.getPrecioUnitario();  
						detalle.setCostoTotal(new BigDecimal(costoTotal));
						if("1".equals(cronogramaCostoActividad.getPeriodo())){
							Double monto1 = cronogramaCostoActividad.getCantidad() * costoActividad.getPrecioUnitario();
							detalle.setMontoPeriodo1(new BigDecimal(monto1));
						}
						if("2".equals(cronogramaCostoActividad.getPeriodo())){
							Double monto2 = cronogramaCostoActividad.getCantidad() * costoActividad.getPrecioUnitario();
							detalle.setMontoPeriodo2(new BigDecimal(monto2));
						}
						if("3".equals(cronogramaCostoActividad.getPeriodo())){
							Double monto3 = cronogramaCostoActividad.getCantidad() * costoActividad.getPrecioUnitario();
							detalle.setMontoPeriodo3(new BigDecimal(monto3));
						}
						if("4".equals(cronogramaCostoActividad.getPeriodo())){
							Double monto4 = cronogramaCostoActividad.getCantidad() * costoActividad.getPrecioUnitario();
							detalle.setMontoPeriodo4(new BigDecimal(monto4));
						}
						if("5".equals(cronogramaCostoActividad.getPeriodo())){
							Double monto5 = cronogramaCostoActividad.getCantidad() * costoActividad.getPrecioUnitario();
							detalle.setMontoPeriodo5(new BigDecimal(monto5));
						}
						if("6".equals(cronogramaCostoActividad.getPeriodo())){
							Double monto6 = cronogramaCostoActividad.getCantidad() * costoActividad.getPrecioUnitario();
							detalle.setMontoPeriodo6(new BigDecimal(monto6));
						}
						if("7".equals(cronogramaCostoActividad.getPeriodo())){
							Double monto7 = cronogramaCostoActividad.getCantidad() * costoActividad.getPrecioUnitario();
							detalle.setMontoPeriodo7(new BigDecimal(monto7));
						}
						if("8".equals(cronogramaCostoActividad.getPeriodo())){
							Double monto8 = cronogramaCostoActividad.getCantidad() * costoActividad.getPrecioUnitario();
							detalle.setMontoPeriodo8(new BigDecimal(monto8));
						}	
					}
				}
				detalles.add(detalle);
			}
		}
		String fuenteFinanciamientoDescripcion = getFuenteFinanciamientoDescripcion(fuenteFinanciadoraID);
		String nombreActividad = getNombreActividad(actividadID);
		String rubro = getPartidaGenericaDescripcion(partidaGenericaID);
		BigDecimal subTotalCostoTotal = sumSubTotalCostoTotal(detalles);
		BigDecimal subTotalMontoPeriodo1 = sumSubTotalMontoPeriodo1(detalles);
		BigDecimal subTotalMontoPeriodo2 = sumSubTotalMontoPeriodo2(detalles);
		BigDecimal subTotalMontoPeriodo3 = sumSubTotalMontoPeriodo3(detalles);
		
		rptCostoActividadFuente.setFuenteFinanciamientoDescripcion(fuenteFinanciamientoDescripcion);
		rptCostoActividadFuente.setNombreActividad(nombreActividad);
		rptCostoActividadFuente.setRubro(rubro);
		rptCostoActividadFuente.setDetalles(detalles);
		rptCostoActividadFuente.setSubTotalCostoTotal(subTotalCostoTotal);
		rptCostoActividadFuente.setSubTotalMontoPeriodo1(subTotalMontoPeriodo1);
		rptCostoActividadFuente.setSubTotalMontoPeriodo2(subTotalMontoPeriodo2);
		rptCostoActividadFuente.setSubTotalMontoPeriodo3(subTotalMontoPeriodo3);
		
		return rptCostoActividadFuente;
	}
	
	private String getFuenteFinanciamientoDescripcion(Integer fuenteFinanciadoraID){
		String descripcion ="";
		FuenteFinanciadora fuenteFinanciadora = fuenteFinanciadoraDAO.findFuenteFinanciadoraById(fuenteFinanciadoraID);
		if (fuenteFinanciadora != null) {
			descripcion = fuenteFinanciadora.getInstitucion().getNombreInstitucion();
		}
		return descripcion;
	}
	
	private String getNombreActividad(Integer actividadID){
		String descripcion ="";
		Actividad actividad = getActividadDAO().findActividadById(actividadID);
		if (actividad != null) {
			descripcion = actividad.getResultado().getCodigoResultado()+ "." + actividad.getCodigoActividad() + " - "
					+ actividad.getNombreActividad();
		}
		return descripcion;
	}
	
	private String getPartidaGenericaDescripcion(Integer partidaGenericaID){
		String descripcion ="";
		PartidaGenerica partidaGenerica = partidaGenericaDAO.findPartidaGenericaById(partidaGenericaID);
		if (partidaGenerica != null) {
			descripcion = partidaGenerica.getDescripcionPartidaGenerica();
		}
		return descripcion;
	}
	
	private BigDecimal sumSubTotalCostoTotal(List<RptRptCostoActividadFuenteDet> detalles){
		BigDecimal sum = BigDecimal.ZERO;
		for (RptRptCostoActividadFuenteDet detalle : detalles) {
			sum = sum.add(detalle.getCostoTotal());
		}
		return sum;
	}
	
	private BigDecimal sumSubTotalMontoPeriodo1(List<RptRptCostoActividadFuenteDet> detalles){
		BigDecimal sum = BigDecimal.ZERO;
		for (RptRptCostoActividadFuenteDet detalle : detalles) {
			sum = sum.add(detalle.getMontoPeriodo1());
		}
		return sum;
	}
	
	private BigDecimal sumSubTotalMontoPeriodo2(List<RptRptCostoActividadFuenteDet> detalles){
		BigDecimal sum = BigDecimal.ZERO;
		for (RptRptCostoActividadFuenteDet detalle : detalles) {
			sum = sum.add(detalle.getMontoPeriodo2());
		}
		return sum;
	}
	
	private BigDecimal sumSubTotalMontoPeriodo3(List<RptRptCostoActividadFuenteDet> detalles){
		BigDecimal sum = BigDecimal.ZERO;
		for (RptRptCostoActividadFuenteDet detalle : detalles) {
			sum = sum.add(detalle.getMontoPeriodo3());
		}
		return sum;
	}

	@SuppressWarnings("unchecked")
	private RptEstructuraInversionFinanciamiento setDataRptEstructuraInversionFinanciamiento(Resultado resultado){
		Integer resultadoID = resultado.getResultadoID();
		List<Actividad> listActividad = getActividadDAO().findActividadByResultadoID(resultadoID);
		if(!listActividad.isEmpty()){
			listActividad = (List<Actividad>) UtilList.orderAscList(listActividad, "codigoActividad");
		}

		DatoPlanOperativo datoPlanOperativo = resultado.getDatoPlanOperativo();
		Integer datoPlanOperativoID = datoPlanOperativo.getDatoPlanOperativoID();
		
		List<RptEstructuraInversionFinanciamientoDet> detalles = setDataListRptEstructuraInversionFinanciamientoDet(datoPlanOperativoID, listActividad);
		
		RptEstructuraInversionFinanciamiento rptEstructuraInversionFinanciamiento = new RptEstructuraInversionFinanciamiento();
		rptEstructuraInversionFinanciamiento.setResultado(resultado.getCodigoResultado() + " - " +resultado.getDefinicionResultado());
		rptEstructuraInversionFinanciamiento.setInstitucionNombre1(institucionNombre1);
		rptEstructuraInversionFinanciamiento.setInstitucionNombre2(institucionNombre2);
		rptEstructuraInversionFinanciamiento.setInstitucionNombre3(institucionNombre3);
		rptEstructuraInversionFinanciamiento.setDetalles(detalles);
		return rptEstructuraInversionFinanciamiento;
	}
	
	private List<RptEstructuraInversionFinanciamientoDet> setDataListRptEstructuraInversionFinanciamientoDet(Integer datoPlanOperativoID, List<Actividad> listActividad) {
		List<RptEstructuraInversionFinanciamientoDet> listRptEstructuraInversionFinanciamientoDet = new ArrayList<RptEstructuraInversionFinanciamientoDet>();
		for (Actividad actividad : listActividad) {
			RptEstructuraInversionFinanciamientoDet rptEstructuraInversionFinanciamientoDet = setDataRptEstructuraInversionFinanciamientoDet(datoPlanOperativoID, actividad);
			listRptEstructuraInversionFinanciamientoDet.add(rptEstructuraInversionFinanciamientoDet);
		}
		return listRptEstructuraInversionFinanciamientoDet;
	}
	
	private RptEstructuraInversionFinanciamientoDet setDataRptEstructuraInversionFinanciamientoDet(Integer datoPlanOperativoID, Actividad actividad) {
		Integer actividadID = actividad.getActividadID();
		RptEstructuraInversionFinanciamientoDet rptEstructuraInversionFinanciamientoDet = new RptEstructuraInversionFinanciamientoDet();
		rptEstructuraInversionFinanciamientoDet.setActividad(actividad
				.getResultado().getCodigoResultado()
				+ "." + actividad.getCodigoActividad() + " - " + actividad.getNombreActividad());
		
		List<FuenteFinanciadora> listFuenteFinanciadora = getPlanOperativoHelper().getListFuenteFinanciadoraByDatoPlanOperativoID(datoPlanOperativoID);
		
		BigDecimal totalMontoFuentes = BigDecimal.ZERO;
		BigDecimal montoFuente1 = getMontoFuenteByPosition(actividadID, listFuenteFinanciadora, 1);
		BigDecimal montoFuente2 = getMontoFuenteByPosition(actividadID, listFuenteFinanciadora, 2);
		BigDecimal montoFuente3 = getMontoFuenteByPosition(actividadID, listFuenteFinanciadora, 3);
		totalMontoFuentes = totalMontoFuentes.add(montoFuente1).add(montoFuente2).add(montoFuente3);
		
		rptEstructuraInversionFinanciamientoDet.setMontoFuente1(montoFuente1);
		rptEstructuraInversionFinanciamientoDet.setMontoFuente2(montoFuente2);
		rptEstructuraInversionFinanciamientoDet.setMontoFuente3(montoFuente3);
		rptEstructuraInversionFinanciamientoDet.setTotalMontoFuentes(totalMontoFuentes);
		return rptEstructuraInversionFinanciamientoDet;
	}

	private BigDecimal getMontoFuenteByPosition(Integer actividadID, List<FuenteFinanciadora> listFuenteFinanciadora, int postion){
		BigDecimal montoActividad = BigDecimal.ZERO;
		int index = 1;
		for (FuenteFinanciadora fuenteFinanciadora : listFuenteFinanciadora) {
			if(index==postion){
				Integer fuenteFinanciadoraID = fuenteFinanciadora.getFuenteFinanciadoraID();
				Double sumMontoActividad = getPlanOperativoHelper().sumMontoActividadByFuenteFinanciadoraID(actividadID, fuenteFinanciadoraID);
				montoActividad = new BigDecimal(sumMontoActividad);
				if(1==postion){
					this.institucionNombre1 = fuenteFinanciadora.getInstitucion().getAbreviatura();
				}
				if(2==postion){
					this.institucionNombre2 = fuenteFinanciadora.getInstitucion().getAbreviatura();
				}
				if(3==postion){
					this.institucionNombre3 = fuenteFinanciadora.getInstitucion().getAbreviatura();
				}
			}
			index++;
		}
		
		return montoActividad;
	}

//	@Override
//	public List<RptDesemboloRecursosDonacion> planOperativoDesembolsoRecursosDonacion(
//			Integer datoPlanOperativoID) {
//		List<RptDesemboloRecursosDonacion> list=new ArrayList<RptDesemboloRecursosDonacion>();
//		List<Resultado> listResultado = getResultadoDAO().findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
//		if (listResultado.size()!=0) {
//			for (Resultado resultado : listResultado) {
//				RptDesemboloRecursosDonacion bean=new RptDesemboloRecursosDonacion();
//				bean.setResultado(resultado.getDefinicionResultado());
//				List<Actividad> listActividad= new ArrayList<Actividad>();
//				listActividad = getActividadDAO().findActividadByResultadoID(resultado.getResultadoID());
//				List<RptDesemboloRecursosDonacionDet> detalles=new ArrayList<RptDesemboloRecursosDonacionDet>();
//				if (listActividad.size()!=0) {
//					for (Actividad actividad : listActividad) {
//						RptDesemboloRecursosDonacionDet detalle= new RptDesemboloRecursosDonacionDet();
//						detalle.setActividad(actividad.getDescripcionActividad());
//						detalles.add(detalle);
//					}
//				}
//				bean.setDetalles(detalles);	
//				list.add(bean);
//			}
//		}
//
//		return list;
//	}
	
	@Override
	public List<RptDesemboloRecursosDonacion> planOperativoDesembolsoRecursosDonacion(
			Integer datoPlanOperativoID) {
		List<RptDesemboloRecursosDonacion> list=new ArrayList<RptDesemboloRecursosDonacion>();
		List<Resultado> listResultadoAll = getResultadoDAO().findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		List<Resultado> listResultado = getResultadoDAO().findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		List<Actividad> listActividadAll = new ArrayList<Actividad>();
		List<CostoActividad> listCostoActividadAll= new ArrayList<CostoActividad>();
		List<CronogramaCostoActividad> listCronogramaCostoActividadAll = new ArrayList<CronogramaCostoActividad>();
		
		for (Resultado resultado : listResultado) {
			listActividadAll.addAll(getActividadDAO().findActividadByResultadoID(resultado.getResultadoID()));
		}
		for (Actividad actividad : listActividadAll) {
			listCostoActividadAll.addAll(getCostoActividadDAO().findCostoActividadByActividadID(actividad.getActividadID()));
		}
		for (CostoActividad costoActividad : listCostoActividadAll) {
			listCronogramaCostoActividadAll.addAll(getCronogramaCostoActividadDAO().findCronogramaCostoActividadByCostoActividadID(costoActividad.getCostoActividadID()));
		}


		if (listResultadoAll.size()!=0) {
			for (Resultado resultado : listResultadoAll) {
				RptDesemboloRecursosDonacion bean=new RptDesemboloRecursosDonacion();
				bean.setResultado(resultado.getDefinicionResultado());
				List<Actividad> listActividad= new ArrayList<Actividad>();
				listActividad = getActividadDAO().findActividadByResultadoID(resultado.getResultadoID());
				List<RptDesemboloRecursosDonacionDet> detalles=new ArrayList<RptDesemboloRecursosDonacionDet>();
				if (listActividad.size()!=0) {
					for (Actividad actividad : listActividad) {
						RptDesemboloRecursosDonacionDet detalle= new RptDesemboloRecursosDonacionDet();
						detalle=getRptDesemboloRecursosDonacionDet(listCronogramaCostoActividadAll, actividad);
						detalles.add(detalle);
					}
				}
				bean.setDetalles(detalles);	
				list.add(bean);
			}
		}
		return list;
	}
	
	public  RptDesemboloRecursosDonacionDet getRptDesemboloRecursosDonacionDet(List<CronogramaCostoActividad>  listCronogramaCostoActividadAll,Actividad actividad){
		RptDesemboloRecursosDonacionDet detalle= new RptDesemboloRecursosDonacionDet();

		detalle.setActividad(actividad.getDescripcionActividad());
	
		double periodo1=sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),1)!=0?sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),1):0;
		double periodo2=sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),2)!=0?sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),2):0;
		double periodo3=sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),3)!=0?sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),3):0;
		double periodo4=sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),4)!=0?sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),4):0;
		double periodo5=sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),5)!=0?sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),5):0;
		double periodo6=sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),6)!=0?sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),6):0;
		double periodo7=sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),7)!=0?sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),7):0;
		double periodo8=sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),8)!=0?sumaTotalPeriodo(listCronogramaCostoActividadAll,actividad.getActividadID(),8):0;
		detalle.setPeriodo1(new BigDecimal(periodo1));
		detalle.setPeriodo2(new BigDecimal(periodo2));
		detalle.setPeriodo3(new BigDecimal(periodo3));
		detalle.setPeriodo4(new BigDecimal(periodo4));
		detalle.setPeriodo5(new BigDecimal(periodo5));
		detalle.setPeriodo6(new BigDecimal(periodo6));
		detalle.setPeriodo7(new BigDecimal(periodo7));
		detalle.setPeriodo8(new BigDecimal(periodo8));
		double sumaTotalPeriodo=periodo1+periodo2+periodo3+periodo4+periodo5+periodo6+periodo7+periodo8;
		detalle.setTotalPeriodos(new BigDecimal(sumaTotalPeriodo));
	return detalle;
	}
	
	public  double sumaTotalPeriodo(List<CronogramaCostoActividad>  listCronogramaCostoActividadAll,Integer actividadID,Integer periodo){
		double sumaTotalPerido=0d;
			for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividadAll) {
			
				if (cronogramaCostoActividad.getCostoActividad().getActividad().getActividadID()==actividadID && periodo==Integer.parseInt(cronogramaCostoActividad.getPeriodo())) {
					sumaTotalPerido+=cronogramaCostoActividad.getCantidad() *cronogramaCostoActividad.getCostoActividad().getPrecioUnitario();
				}			
			}
		return sumaTotalPerido;
	}
	
	@Override
	public List<RptContraPartida> planOperativoContraPartida(
			Integer datoPlanOperativoID) {
		
		List<RptContraPartida> listRptContraPartida = new ArrayList<RptContraPartida>();
		List<Resultado> listResultado = getResultadoDAO().findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		
		List<Actividad> listActividadAll = new ArrayList<Actividad>();
		List<CostoActividad> listCostoActividadAll= new ArrayList<CostoActividad>();
		List<CronogramaCostoActividad> listCronogramaCostoActividadAll = new ArrayList<CronogramaCostoActividad>();
		List<FuenteFinanciadora> listFuenteFinancidoraAll=getFuenteFinanciadoras(datoPlanOperativoID);
		
		for (Resultado resultado : listResultado) {
			listActividadAll.addAll(getActividadDAO().findActividadByResultadoID(resultado.getResultadoID()));
		}
		for (Actividad actividad : listActividadAll) {
			listCostoActividadAll.addAll(getCostoActividadDAO().findCostoActividadByActividadID(actividad.getActividadID()));
		}
		for (CostoActividad costoActividad : listCostoActividadAll) {
			listCronogramaCostoActividadAll.addAll(getCronogramaCostoActividadDAO().findCronogramaCostoActividadByCostoActividadID(costoActividad.getCostoActividadID()));
		}
		for (FuenteFinanciadora fuenteFinanciadora : listFuenteFinancidoraAll) {
			Integer fuenteFinanciadoraID = fuenteFinanciadora.getFuenteFinanciadoraID();
			String aportante = fuenteFinanciadora.getInstitucion().getNombreInstitucion();
			RptContraPartida rptContraPartida = setDataRptContraPartida(fuenteFinanciadoraID, aportante, listCostoActividadAll, listCronogramaCostoActividadAll);
			if(rptContraPartida != null ){
				listRptContraPartida.add(rptContraPartida);	
			}
		}
		return listRptContraPartida;
	}

	private RptContraPartida setDataRptContraPartida(Integer fuenteFinanciadoraID, String aportante, List<CostoActividad> listCostoActividadAll, List<CronogramaCostoActividad> listCronogramaCostoActividad){
		RptContraPartida rptContraPartida = null;
		List<RptContraPartidaDet> detalles = new ArrayList<RptContraPartidaDet>();
		for (CostoActividad costoActividad : listCostoActividadAll) {
			RptContraPartidaDet rptContraPartidaDet = setDataRptContraPartidaDet(fuenteFinanciadoraID, costoActividad, listCronogramaCostoActividad);
			if(rptContraPartidaDet != null){
				detalles.add(rptContraPartidaDet);	
			}
		}
		if(!detalles.isEmpty()){
			rptContraPartida = new RptContraPartida();
			rptContraPartida.setAportante(aportante);
			rptContraPartida.setDetalles(detalles);
		}
		return rptContraPartida;
	}
	
	
	private RptContraPartidaDet setDataRptContraPartidaDet(Integer fuenteFinanciadoraID, CostoActividad costoActividad, List<CronogramaCostoActividad> listCronogramaCostoActividad){
		RptContraPartidaDet detalle = null;
		Integer cantidades = 0;
		Double precioUnitario = 0d;
		for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
			if(fuenteFinanciadoraID.intValue() == cronogramaCostoActividad.getFuenteFinanciadora().getFuenteFinanciadoraID().intValue()){
				Integer costoActividadID = costoActividad.getCostoActividadID();
				if (costoActividadID.intValue() == cronogramaCostoActividad
						.getCostoActividad().getCostoActividadID().intValue()) {
					precioUnitario = cronogramaCostoActividad.getCostoActividad().getPrecioUnitario();
					cantidades += cronogramaCostoActividad.getCantidad();
				}
			}
		}
		BigDecimal total = new BigDecimal(precioUnitario * cantidades);
		if(cantidades.intValue()>0){
			detalle = new RptContraPartidaDet();
			detalle.setDescripcionAportante(costoActividad.getDetallePartidaGenerica());
			detalle.setValorUnitario(new BigDecimal(precioUnitario));
			detalle.setCantidad(new BigDecimal(cantidades));
			detalle.setTotal(total);
			
		}
		return detalle;
	}
	
	
	private List<FuenteFinanciadora> getFuenteFinanciadoras(Integer datoPlanOperativoID){
		DatoPlanOperativo  datoPlanOperativo = getDatoPlanOperativoDAO().findDatoPlanOperativoById(datoPlanOperativoID);
		
		return getFuenteFinanciadoraDAO()
				.findFuenteFinanciadoraByDatoProyectoID(
						datoPlanOperativo.getDatoProyecto().getDatoProyectoID());
	}

}