package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.dao.ActividadDAO;
import pe.com.fondam.sgp.core.dao.ActividadObligatoriaProgramaDAO;
import pe.com.fondam.sgp.core.dao.BeneficiariosPorResultadoDAO;
import pe.com.fondam.sgp.core.dao.CategoriaActividadDAO;
import pe.com.fondam.sgp.core.dao.CostoActividadDAO;
import pe.com.fondam.sgp.core.dao.CronogramaCostoActividadDAO;
import pe.com.fondam.sgp.core.dao.CronogramaMetaPorActividadDAO;
import pe.com.fondam.sgp.core.dao.CronogramaMetaPorResultadoDAO;
import pe.com.fondam.sgp.core.dao.DatoPlanOperativoDAO;
import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.dao.FuenteFinanciadoraDAO;
import pe.com.fondam.sgp.core.dao.IndicadorResultadoDAO;
import pe.com.fondam.sgp.core.dao.MetaPorActividadDAO;
import pe.com.fondam.sgp.core.dao.PartidaEspecificaDAO;
import pe.com.fondam.sgp.core.dao.PartidaGenericaDAO;
import pe.com.fondam.sgp.core.dao.PerfilDAO;
import pe.com.fondam.sgp.core.dao.ResultadoDAO;
import pe.com.fondam.sgp.core.dao.RiesgoDAO;
import pe.com.fondam.sgp.core.dao.RubroGenericoDAO;
import pe.com.fondam.sgp.core.dao.TablaEspecificaDAO;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.ActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.CategoriaActividad;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorActividad;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorResultado;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.IndicadorResultado;
import pe.com.fondam.sgp.core.domain.Institucion;
import pe.com.fondam.sgp.core.domain.MetaPorActividad;
import pe.com.fondam.sgp.core.domain.PartidaEspecifica;
import pe.com.fondam.sgp.core.domain.PartidaGenerica;
import pe.com.fondam.sgp.core.domain.Perfil;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.domain.Riesgo;
import pe.com.fondam.sgp.core.domain.RubroGenerico;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.form.planOperativo.ActividadForm;
import pe.com.fondam.sgp.core.form.planOperativo.BeneficiarioForm;
import pe.com.fondam.sgp.core.form.planOperativo.CostoActividadForm;
import pe.com.fondam.sgp.core.form.planOperativo.CronogramaCostoActividadForm;
import pe.com.fondam.sgp.core.form.planOperativo.CronogramaMetaForm;
import pe.com.fondam.sgp.core.form.planOperativo.IndicadorForm;
import pe.com.fondam.sgp.core.form.planOperativo.MetaActividadForm;
import pe.com.fondam.sgp.core.form.planOperativo.PeriodoMontoForm;
import pe.com.fondam.sgp.core.form.planOperativo.PlanOperativoForm;
import pe.com.fondam.sgp.core.form.planOperativo.ResultadoForm;
import pe.com.fondam.sgp.core.service.ActividadService;
import pe.com.fondam.sgp.core.service.CostoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaCostoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorActividadService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorResultadoService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.IndicadorResultadoService;
import pe.com.fondam.sgp.core.service.LiquidacionGastoService;
import pe.com.fondam.sgp.core.service.MetaPorActividadService;
import pe.com.fondam.sgp.core.service.ObservacionService;
import pe.com.fondam.sgp.core.service.PlanOperativoService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaBeneficiarioService;
import pe.com.fondam.sgp.core.service.ReporteAvanceBeneficiarioService;
import pe.com.fondam.sgp.core.service.ReporteAvanceService;
import pe.com.fondam.sgp.core.service.ResultadoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.util.UtilList;
import pe.com.fondam.sgp.core.util.UtilValidate;
import pe.com.fondam.sgp.core.view.planOperativo.BeneficiarioResultadoView;
import pe.com.fondam.sgp.core.view.planOperativo.CostoActividadView;
import pe.com.fondam.sgp.core.view.planOperativo.CronogramaCostoActividad2View;
import pe.com.fondam.sgp.core.view.planOperativo.CronogramaCostoActividadView;
import pe.com.fondam.sgp.core.view.planOperativo.CronogramaMetaActividadView;
import pe.com.fondam.sgp.core.view.planOperativo.IndicadorResultadoView;
import pe.com.fondam.sgp.core.view.planOperativo.PeriodoView;

@Service
public class PlanOperativoServiceImpl implements PlanOperativoService {

	private final Log logger = LogFactory
			.getLog(PlanOperativoServiceImpl.class);

	@Resource
	private DatoPlanOperativoDAO datoPlanOperativoDAO;

	@Resource
	private ResultadoDAO resultadoDAO;

	@Resource
	private IndicadorResultadoDAO indicadorResultadoDAO;

	@Resource
	private BeneficiariosPorResultadoDAO beneficiariosPorResultadoDAO;

	@Resource
	private CronogramaMetaPorResultadoDAO cronogramaMetaPorResultadoDAO;

	@Resource
	private ActividadDAO actividadDAO;

	@Resource
	private CostoActividadDAO costoActividadDAO;

	@Resource
	private CronogramaCostoActividadDAO cronogramaCostoActividadDAO;

	@Resource
	private MetaPorActividadDAO metaPorActividadDAO;

	@Resource
	private DatoProyectoDAO datoProyectoDAO;

	@Resource
	private TablaEspecificaDAO tablaEspecificaDAO;

	@Resource
	private RubroGenericoDAO rubroGenericoDAO;

	@Resource
	private RiesgoDAO riesgoDAO;

	@Resource
	private PartidaEspecificaDAO partidaEspecificaDAO;

	@Resource
	private PartidaGenericaDAO partidaGenericaDAO;

	@Resource
	private CategoriaActividadDAO categoriaActividadDAO;

	@Resource
	private FuenteFinanciadoraDAO fuenteFinanciadoraDAO;

	@Resource
	private ActividadObligatoriaProgramaDAO actividadObligatoriaProgramaDAO;

	@Resource
	private DatoProyectoService datoProyectoService;
	
	@Resource
	CronogramaMetaPorResultadoService cronogramaMetaPorResultadoService;
	
	@Resource
	CostoActividadService costoActividadService;

	@Resource
	IndicadorResultadoService indicadorResultadoService;

	@Resource
	CronogramaCostoActividadService cronogramaCostoActividadService;
	
	@Resource
	ResultadoService resultadoService;
	
	@Resource
	ActividadService actividadService;
	
	@Resource
	MetaPorActividadService metaPorActividadService;
	
	@Resource
	CronogramaMetaPorActividadService cronogramaMetaPorActividadService;

	@Resource
	private CronogramaMetaPorActividadDAO cronogramaMetaPorActividadDAO;

	@Resource
	PerfilDAO perfilDAO;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;

	@Resource
	ObservacionService observacionService;
	
	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	@Resource
	ReporteAvanceService reporteAvanceService;
	
	@Resource
	LiquidacionGastoService liquidacionGastoService;
	
	@Resource
	PropuestaTransferenciaBeneficiarioService propuestaTransferenciaBeneficiarioService;
	
	@Resource
	ReporteAvanceBeneficiarioService reporteAvanceBeneficiarioService;
	
	public DatoPlanOperativoDAO getDatoPlanOperativoDAO() {
		return datoPlanOperativoDAO;
	}

	public void setDatoPlanOperativoDAO(
			DatoPlanOperativoDAO datoPlanOperativoDAO) {
		this.datoPlanOperativoDAO = datoPlanOperativoDAO;
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

	public void setIndicadorResultadoDAO(
			IndicadorResultadoDAO indicadorResultadoDAO) {
		this.indicadorResultadoDAO = indicadorResultadoDAO;
	}

	public BeneficiariosPorResultadoDAO getBeneficiariosPorResultadoDAO() {
		return beneficiariosPorResultadoDAO;
	}

	public void setBeneficiariosPorResultadoDAO(
			BeneficiariosPorResultadoDAO beneficiariosPorResultadoDAO) {
		this.beneficiariosPorResultadoDAO = beneficiariosPorResultadoDAO;
	}

	public CronogramaMetaPorResultadoDAO getCronogramaMetaPorResultadoDAO() {
		return cronogramaMetaPorResultadoDAO;
	}

	public void setCronogramaMetaPorResultadoDAO(
			CronogramaMetaPorResultadoDAO cronogramaMetaPorResultadoDAO) {
		this.cronogramaMetaPorResultadoDAO = cronogramaMetaPorResultadoDAO;
	}

	public ActividadDAO getActividadDAO() {
		return actividadDAO;
	}

	public void setActividadDAO(ActividadDAO actividadDAO) {
		this.actividadDAO = actividadDAO;
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

	public MetaPorActividadDAO getMetaPorActividadDAO() {
		return metaPorActividadDAO;
	}

	public void setMetaPorActividadDAO(MetaPorActividadDAO metaPorActividadDAO) {
		this.metaPorActividadDAO = metaPorActividadDAO;
	}

	public DatoProyectoDAO getDatoProyectoDAO() {
		return datoProyectoDAO;
	}

	public void setDatoProyectoDAO(DatoProyectoDAO datoProyectoDAO) {
		this.datoProyectoDAO = datoProyectoDAO;
	}

	public TablaEspecificaDAO getTablaEspecificaDAO() {
		return tablaEspecificaDAO;
	}

	public void setTablaEspecificaDAO(TablaEspecificaDAO tablaEspecificaDAO) {
		this.tablaEspecificaDAO = tablaEspecificaDAO;
	}

	public RubroGenericoDAO getRubroGenericoDAO() {
		return rubroGenericoDAO;
	}

	public void setRubroGenericoDAO(RubroGenericoDAO rubroGenericoDAO) {
		this.rubroGenericoDAO = rubroGenericoDAO;
	}

	public PartidaEspecificaDAO getPartidaEspecificaDAO() {
		return partidaEspecificaDAO;
	}

	public void setPartidaEspecificaDAO(
			PartidaEspecificaDAO partidaEspecificaDAO) {
		this.partidaEspecificaDAO = partidaEspecificaDAO;
	}

	public PartidaGenericaDAO getPartidaGenericaDAO() {
		return partidaGenericaDAO;
	}

	public void setPartidaGenericaDAO(PartidaGenericaDAO partidaGenericaDAO) {
		this.partidaGenericaDAO = partidaGenericaDAO;
	}

	public CategoriaActividadDAO getCategoriaActividadDAO() {
		return categoriaActividadDAO;
	}

	public void setCategoriaActividadDAO(
			CategoriaActividadDAO categoriaActividadDAO) {
		this.categoriaActividadDAO = categoriaActividadDAO;
	}

	public FuenteFinanciadoraDAO getFuenteFinanciadoraDAO() {
		return fuenteFinanciadoraDAO;
	}

	public void setFuenteFinanciadoraDAO(
			FuenteFinanciadoraDAO fuenteFinanciadoraDAO) {
		this.fuenteFinanciadoraDAO = fuenteFinanciadoraDAO;
	}

	public ActividadObligatoriaProgramaDAO getActividadObligatoriaProgramaDAO() {
		return actividadObligatoriaProgramaDAO;
	}

	public void setActividadObligatoriaProgramaDAO(
			ActividadObligatoriaProgramaDAO actividadObligatoriaProgramaDAO) {
		this.actividadObligatoriaProgramaDAO = actividadObligatoriaProgramaDAO;
	}

	@Override
	public DatoProyecto findDatoProyectoByDatoProyectoID(Integer datoProyectoID) {
		DatoProyecto datoProyecto =  getDatoProyectoDAO()
				.findDatoProyectoById(datoProyectoID);
		
		return datoProyecto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PlanOperativoForm obtenerPlanOperativoByProyectoID(
			Integer datoProyectoID) {
		//logger.info("[PlanOperativoService:obtenerPlanOperativoByProyectoID] Start");
		//logger.info("datoProyectoID = " + datoProyectoID);
		PlanOperativoForm planOperativoForm = null;

		DatoPlanOperativo datoPlanOperativo = getDatoPlanOperativoDAO()
				.findDatoPlanOperativoByDatoProyectoID(datoProyectoID);
		//logger.info("datoPlanOperativo = " + datoPlanOperativo);

		if (UtilValidate.isNotEmpty(datoPlanOperativo)) {
			
			//Integer datoPlanOperativoID = datoPlanOperativo.getDatoPlanOperativoID();
			planOperativoForm = setDataPlanOperativoForm(datoPlanOperativo);
			List<Resultado> listResultado = getResultadoDAO()
					.findListResultadoByDatoPlanOperativoID(datoPlanOperativo.getDatoPlanOperativoID());
			if (UtilValidate.isNotEmpty(listResultado)) {
				List<ResultadoForm> listResultadoForm = setDataListResultadoForm(listResultado);
				listResultadoForm = (List<ResultadoForm>) UtilList
						.orderAscList(listResultadoForm, "codigoResultado");
				planOperativoForm.setListResultadoForm(listResultadoForm);
			}
		}
		//logger.info("[PlanOperativoService:obtenerPlanOperativoByProyectoID] Finished");
		return planOperativoForm;
	}

	@Override
	public PlanOperativoForm obtenerPlanOperativoByDatoPlanOperativoID(
			Integer datoPlanOperativoID) {
		//logger.info("[PlanOperativoService:obtenerPlanOperativoByDatoPlanOperativoID] Start");
		PlanOperativoForm planOperativoForm = null;

		DatoPlanOperativo datoPlanOperativo = getDatoPlanOperativoDAO()
				.findDatoPlanOperativoById(datoPlanOperativoID);
		if (UtilValidate.isNotEmpty(datoPlanOperativo)) {
			planOperativoForm = setDataPlanOperativoForm(datoPlanOperativo);
		}
		//logger.info("[PlanOperativoService:obtenerPlanOperativoByDatoPlanOperativoID] Finished");
		return planOperativoForm;
	}

	@Override
	public ResultadoForm obtenerResultadoByResultadoID(Integer resultadoID) {
		logger.info("[PlanOperativoService:obtenerResultadoByResultadoID] Start");
		ResultadoForm resultadoForm = null;

		Resultado resultado = getResultadoDAO().findResultadoById(resultadoID);
		if (UtilValidate.isNotEmpty(resultado)) {
			resultadoForm = setDataResultadoForm(resultado);
		}
		logger.info("[PlanOperativoService:obtenerResultadoByResultadoID] Finished");
		return resultadoForm;
	}

	@Override
	public List<IndicadorForm> obtenerListaIndicadorResultadoByResultadoID(
			Integer resultadoID) {
		logger.info("[PlanOperativoService:obtenerIndicadorResultadoByResultadoID] Start");
		List<IndicadorForm> listIndicadorForm = null;
		List<IndicadorResultado> listIndicadorResultado = getIndicadorResultadoDAO()
				.findIndicadorResultadoByResultadoID(resultadoID);
		if (UtilValidate.isNotEmpty(listIndicadorResultado)) {
			listIndicadorForm = setDataListIndicadorForm(listIndicadorResultado);
		}
		logger.info("[PlanOperativoService:obtenerIndicadorResultadoByResultadoID] Finished");
		return listIndicadorForm;
	}

	@Override
	public List<ActividadForm> obtenerListaActividadByResultadoID(
			Integer resultadoID) {
		List<ActividadForm> listaActividadForm = null;
		List<Actividad> listaActividad = getActividadDAO()
				.findActividadByResultadoID(resultadoID);
		if (UtilValidate.isNotEmpty(listaActividad)) {
			listaActividadForm = setDataListActividadForm(listaActividad);
		}
		return listaActividadForm;
	}

	public List<CostoActividad> findCostoActividadByActividadID(
			Integer actividadID) {
		return costoActividadDAO.findCostoActividadByActividadID(actividadID);
	}

	@Override
	public List<CostoActividadForm> obtenerListaCostoActividadByActividadID(
			Integer actividadID) {
		List<CostoActividadForm> listCostoActividadForm = null;
		List<CostoActividad> listCostoActividad = getCostoActividadDAO()
				.findCostoActividadByActividadID(actividadID);
		if (UtilValidate.isNotEmpty(listCostoActividad)) {
			listCostoActividadForm = setDataListCostoActividadForm(listCostoActividad);
		}
		return listCostoActividadForm;
	}

	@Override
	public List<MetaActividadForm> obtenerListaMetaActividadByActividadID(
			Integer actividadID) {
		List<MetaActividadForm> listMetaActividadForm = null;
		List<MetaPorActividad> listMetaActividad = metaPorActividadDAO
				.findMetasPorActividadByActividadId(actividadID);
		if (UtilValidate.isNotEmpty(listMetaActividad)) {
			listMetaActividadForm = this
					.setDataListMetaActividadForm(listMetaActividad);
		}
		return listMetaActividadForm;
	}

	@Override
	public List<CostoActividadView> listCostoActividadViewByActividadID(
			Integer actividadID) {
		List<CostoActividadView> listCostoActividadView = null;
		List<CostoActividad> listCostoActividad = getCostoActividadDAO()
				.findCostoActividadByActividadID(actividadID);
		if (UtilValidate.isNotEmpty(listCostoActividad)) {
			listCostoActividadView = setDataListCostoActividadView(listCostoActividad);
		}
		return listCostoActividadView;
	}

	@Override
	public List<CronogramaCostoActividadForm> obtenerListaCronogramaCostoActividadByActividadID(
			Integer actividadID) {

		List<CronogramaCostoActividadForm> listCronogramaCostoActividadForm = null;
		List<CostoActividad> listCostoActividad = getCostoActividadDAO()
				.findCostoActividadByActividadID(actividadID);
		Integer cronogramaCostoActividadID = null;
		if (UtilValidate.isNotEmpty(listCostoActividad)) {
			listCronogramaCostoActividadForm = setDataListCronogramaCostoActividadForm(listCostoActividad);
		}
		for (CronogramaCostoActividadForm conogramaCostoActividadForm : listCronogramaCostoActividadForm) {
			String fuenteFinanciamiento = null;
			List<PeriodoMontoForm> listPeriodo = new ArrayList<PeriodoMontoForm>();
			List<CronogramaCostoActividad> listCronogramaCostoActividad = getCronogramaCostoActividadDAO()
					.findCronogramaCostoActividadByCostoActividadID(
							conogramaCostoActividadForm.getCostoActividadID());
			for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
				if (cronogramaCostoActividad.getFuenteFinanciadora() != null) {
					if (cronogramaCostoActividad.getFuenteFinanciadora()
							.getInstitucion() != null) {
						fuenteFinanciamiento = cronogramaCostoActividad
								.getFuenteFinanciadora().getInstitucion()
								.getNombreInstitucion();
						cronogramaCostoActividadID = cronogramaCostoActividad
								.getCronogramaCostoActividadID();
					}
				}

				PeriodoMontoForm periodoMonto = new PeriodoMontoForm();
				periodoMonto.setNumeroPerido(cronogramaCostoActividad
						.getPeriodo());
				periodoMonto.setMontoTotal(cronogramaCostoActividad
						.getCantidad().doubleValue());
				listPeriodo.add(periodoMonto);
			}
			conogramaCostoActividadForm
					.setCronogramaCostoActividadID(cronogramaCostoActividadID);
			conogramaCostoActividadForm
					.setFuenteFinanciamiento(fuenteFinanciamiento);
			conogramaCostoActividadForm.setListPeriodo(listPeriodo);
		}
		return listCronogramaCostoActividadForm;
	}

	@Override
	public List<CronogramaCostoActividadView> listCronogramaCostoActividadViewByCostoActividadID(
			Integer costoActividadID) {

		List<Integer> listFuenteFinanciadora = new ArrayList<Integer>();
		CostoActividad costoActividad = getCostoActividadDAO()
				.findCostoActividadById(costoActividadID);
		List<CronogramaCostoActividad> listCronogramaCostoActividad = getCronogramaCostoActividadDAO()
				.findCronogramaCostoActividadByCostoActividadID(
						costoActividadID);
		for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
			Integer fuenteFinanciadoraID = cronogramaCostoActividad
					.getFuenteFinanciadora().getFuenteFinanciadoraID();
			if (!listFuenteFinanciadora.contains(fuenteFinanciadoraID)) {
				listFuenteFinanciadora.add(fuenteFinanciadoraID);
			}
		}
		List<CronogramaCostoActividadView> listCronogramaCostoActividadView = new ArrayList<CronogramaCostoActividadView>();
		for (Integer tmpFuenteFinanciadoraID : listFuenteFinanciadora) {
			CronogramaCostoActividadView cronogramaCostoActividadView = null;
			cronogramaCostoActividadView = setDataCronogramaCostoActividadViewComplete(
					tmpFuenteFinanciadoraID, costoActividad,
					listCronogramaCostoActividad);
			listCronogramaCostoActividadView.add(cronogramaCostoActividadView);
		}
		return listCronogramaCostoActividadView;
	}

	@Override
	public List<CronogramaCostoActividad2View> listCronogramaCostoActividad2ViewByCostoActividadID(
			Integer costoActividadID) {

		List<Integer> listFuenteFinanciadora = new ArrayList<Integer>();
		CostoActividad costoActividad = getCostoActividadDAO()
				.findCostoActividadById(costoActividadID);
		List<CronogramaCostoActividad> listCronogramaCostoActividad = getCronogramaCostoActividadDAO()
				.findCronogramaCostoActividadByCostoActividadID(
						costoActividadID);
		for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
			Integer fuenteFinanciadoraID = cronogramaCostoActividad
					.getFuenteFinanciadora().getFuenteFinanciadoraID();
			if (!listFuenteFinanciadora.contains(fuenteFinanciadoraID)) {
				listFuenteFinanciadora.add(fuenteFinanciadoraID);
			}
		}
		List<CronogramaCostoActividad2View> listCronogramaCostoActividadView = new ArrayList<CronogramaCostoActividad2View>();
		for (Integer tmpFuenteFinanciadoraID : listFuenteFinanciadora) {
			CronogramaCostoActividad2View cronogramaCostoActividadView = null;
			cronogramaCostoActividadView = setDataCronogramaCostoActividad2ViewComplete(
					tmpFuenteFinanciadoraID, costoActividad,
					listCronogramaCostoActividad);
			Integer cantidadTotal = getCantidadTotal(cronogramaCostoActividadView
					.getListPeriodo());
			Double costoTotal = getCostoTotal(cronogramaCostoActividadView
					.getListPeriodo());
			cronogramaCostoActividadView.setCantidadTotal(cantidadTotal);
			cronogramaCostoActividadView.setCostoTotal(costoTotal);
			listCronogramaCostoActividadView.add(cronogramaCostoActividadView);
		}
		return listCronogramaCostoActividadView;
	}

	@Override
	public List<CronogramaMetaActividadView> listCronogramaMetaActividadViewByMetaActividadID(
			Integer metaActividadID) {

		List<CronogramaMetaPorActividad> listCronogramaMetaActividad = cronogramaMetaPorActividadDAO
				.findCronorgramaMetaPorActividadesByMetaPorActivdadId(metaActividadID);
		
		MetaPorActividad metaPorActividad = metaPorActividadDAO
				.findMetaPorActividadById(metaActividadID);
		
		List<CronogramaMetaActividadView> listCronogramaMetaActividadView = new ArrayList<CronogramaMetaActividadView>();
		
		CronogramaMetaActividadView cronogramaMetaActividadView = setDataCronogramaMetaActividadViewComplete(
				metaPorActividad, listCronogramaMetaActividad);
		
		Integer cantidadTotal = getCantidadTotal(cronogramaMetaActividadView
				.getListPeriodo());
		cronogramaMetaActividadView.setCantidadTotal(cantidadTotal);
		listCronogramaMetaActividadView.add(cronogramaMetaActividadView);
		return listCronogramaMetaActividadView;
	}

	@Override
	public ActividadForm obtenerActividadByActividadID(Integer actividadID) {
		logger.info("[PlanOperativoService:obtenerResultadoByActividadID] Start");
		ActividadForm actividadForm = null;

		Actividad actividad = getActividadDAO().findActividadById(actividadID);
		if (UtilValidate.isNotEmpty(actividad)) {
			actividadForm = setDataActividadForm(actividad);
		}
		logger.info("[PlanOperativoService:obtenerResultadoByActividadID] Finished");
		return actividadForm;
	}

	@Override
	public CostoActividadView findCostoActividadViewByID(
			Integer costoActividadID) {
		logger.info("[PlanOperativoService:CostoActividadView] Start");
		CostoActividadView costoActividadView = null;

		CostoActividad costoActividad = getCostoActividadDAO()
				.findCostoActividadById(costoActividadID);
		if (UtilValidate.isNotEmpty(costoActividad)) {
			costoActividadView = setDataCostoActividadView(costoActividad);
		}
		logger.info("[PlanOperativoService:CostoActividadView] Finished");
		return costoActividadView;
	}

	@Override
	public DatoPlanOperativo obtenerDatoPlanOperativoByID(
			Integer datoPlanOperativoID) {
		DatoPlanOperativo datoPlanOperativo = getDatoPlanOperativoDAO()
				.findDatoPlanOperativoById(datoPlanOperativoID);
		return datoPlanOperativo;
	}

	@Override
	public Resultado obtenerResultadoByID(Integer resultadoID) {
		Resultado resultado = getResultadoDAO().findResultadoById(resultadoID);
		return resultado;
	}

	@Override
	public Actividad obtenerActividadByID(Integer actividadID) {
		Actividad actividad = getActividadDAO().findActividadById(actividadID);
		return actividad;
	}

	@Override
	public CategoriaActividad obtenerCategoriaActividadByID(
			Integer categoriaActividadID) {
		CategoriaActividad categoriaActividad = getCategoriaActividadDAO()
				.findCategoriaActividadById(categoriaActividadID);
		return categoriaActividad;
	}

	@Override
	public RubroGenerico obtenerRubroGenericoByID(Integer rubroGenericoID) {
		RubroGenerico rubroGenerico = getRubroGenericoDAO()
				.findRubroGenericoById(rubroGenericoID);
		return rubroGenerico;
	}

	@Override
	public PartidaGenerica obtenerPartidaGenericaByID(Integer partidaGenericaID) {
		PartidaGenerica partidaGenerica = getPartidaGenericaDAO()
				.findPartidaGenericaById(partidaGenericaID);
		return partidaGenerica;
	}

	@Override
	public PartidaEspecifica obtenerPartidaEspecificaByID(
			Integer partidaEspecificaID) {
		PartidaEspecifica partidaEspecifica = getPartidaEspecificaDAO()
				.findPartidaEspecificaById(partidaEspecificaID);
		return partidaEspecifica;
	}

	@Override
	public PlanOperativoForm mostrarPlanOperativoProyecto(
			Integer datoPlanOperativoID) {
		logger.info("[PlanOperativoService:mostrarPlanOperativoProyecto] Start");
		PlanOperativoForm planOperativoForm = null;

		DatoPlanOperativo datoPlanOperativo = getDatoPlanOperativoDAO()
				.findDatoPlanOperativoById(datoPlanOperativoID);
		if (UtilValidate.isNotEmpty(datoPlanOperativo)) {
			planOperativoForm = setDataPlanOperativoForm(datoPlanOperativo);
			List<Resultado> listResultado = getResultadoDAO()
					.findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
			if (UtilValidate.isNotEmpty(listResultado)) {
				List<ResultadoForm> listResultadoForm = setDataListResultadoForm(listResultado);
				planOperativoForm.setListResultadoForm(listResultadoForm);
			}
		}
		logger.info("[PlanOperativoService:mostrarPlanOperativoProyecto] Finished");
		return planOperativoForm;
	}

	@Override
	public PlanOperativoForm mostrarPlanOperativoDatoPlanOperativoID(
			Integer datoPlanOperativoID) {

		PlanOperativoForm planOperativoForm = null;
		DatoPlanOperativo datoPlanOperativo = getDatoPlanOperativoDAO()
				.findDatoPlanOperativoById(datoPlanOperativoID);
		if (UtilValidate.isNotEmpty(datoPlanOperativo)) {
			planOperativoForm = setDataPlanOperativoForm(datoPlanOperativo);
			List<Resultado> listResultado = getResultadoDAO()
					.findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
			if (UtilValidate.isNotEmpty(listResultado)) {
				List<ResultadoForm> listResultadoForm = setDataListResultadoForm(listResultado);
				planOperativoForm.setListResultadoForm(listResultadoForm);
			}
		}
		return planOperativoForm;
	}

	@Override
	public List<Resultado> listResultadoByDatoPlanOperativoID(
			Integer datoPlanOperativoID) {
		List<Resultado> listResultados = getResultadoDAO()
				.findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		return listResultados;
	}

	@Override
	public ResultadoForm mostrarResultadoByResultadoID(Integer resultadoID) {

		ResultadoForm resultadoForm = null;
		List<IndicadorForm> listIndicadorForm = null;
		List<BeneficiarioForm> listBeneficiarioForm = null;
		List<CronogramaMetaForm> listCronogramaMetaForm = null;
		List<ActividadForm> listActividadForm = null;

		Resultado resultado = getResultadoDAO().findResultadoById(resultadoID);
		List<IndicadorResultado> listIndicadorResultado = getIndicadorResultadoDAO()
				.findIndicadorResultadoByResultadoID(resultadoID);
		List<BeneficiariosPorResultado> listBeneficiariosPorResultado = getBeneficiariosPorResultadoDAO()
				.findBeneficiariosPorResultadoByResultadoID(resultadoID);
		List<CronogramaMetaPorResultado> listCronogramaMetaPorResultado = getCronogramaMetaPorResultadoDAO()
				.findCronogramaMetaPorResultadoByResultadoID(resultadoID);
		List<Actividad> listActividad = getActividadDAO()
				.findActividadByResultadoID(resultadoID);

		if (UtilValidate.isNotEmpty(resultado)) {
			resultadoForm = setDataResultadoForm(resultado);
			if (UtilValidate.isNotEmpty(listIndicadorResultado)) {
				listIndicadorForm = setDataListIndicadorForm(listIndicadorResultado);
			}
			if (UtilValidate.isNotEmpty(listBeneficiariosPorResultado)) {
				listBeneficiarioForm = setDataListBeneficiarioForm(listBeneficiariosPorResultado);
			}
			if (UtilValidate.isNotEmpty(listCronogramaMetaPorResultado)) {
				listCronogramaMetaForm = setDataListCronogramaMetaForm(listCronogramaMetaPorResultado);
			}
			if (UtilValidate.isNotEmpty(listActividad)) {
				listActividadForm = setDataListActividadForm(listActividad);
			}
			resultadoForm.setListIndicadorForm(listIndicadorForm);
			resultadoForm.setListBeneficiarioForm(listBeneficiarioForm);
			resultadoForm.setListCronogramaMetaForm(listCronogramaMetaForm);
			resultadoForm.setListActividadForm(listActividadForm);
		}
		return resultadoForm;
	}

	@Override
	public Integer createActividad(Actividad actividad) throws Exception {
		//logger.info("createActividad inicio");
		Integer codigoActividad = actividad.getCodigoActividad();
		if (codigoActividad == null) {
			codigoActividad = nextCodigoActividad(actividad.getResultado()
					.getResultadoID());
			actividad.setCodigoActividad(codigoActividad);
		}
		actividad = getActividadDAO().updateActividad(actividad);
		//logger.info("createActividad fin ");
		return actividad.getActividadID();
	}

	@Override
	public Integer createMetaActividad(MetaPorActividad metaPorActividad)
			throws Exception {
		//logger.info("createMetaActividad inicio");
		metaPorActividad = getMetaPorActividadDAO().updateMetaPorActividad(metaPorActividad);
		//logger.info("createMetaActividad fin ");
		return metaPorActividad.getMetaPorActividadID();
	}

	@Override
	public Integer createCostoActividad(CostoActividad costoActividad)
			throws Exception {
		//logger.info("createCostoActividad inicio");
		//Integer costoActividadID = null;
		costoActividad = getCostoActividadDAO().updateCostoActividad(costoActividad);
		//costoActividadID = costoActividad.getCostoActividadID();
		//logger.info("createCostoActividad fin");
		return costoActividad.getCostoActividadID();
	}

	@Override
	public Integer createCronogramaCostoActividad(
			CronogramaCostoActividad cronogramaCostoActividad) throws Exception {
		logger.info("createCronogramaCostoActividad inicio");
		Integer cronogramaCostoActividadID = null;
		getCronogramaCostoActividadDAO().saveCronogramaCostoActividad(
				cronogramaCostoActividad);
		cronogramaCostoActividadID = cronogramaCostoActividad
				.getCronogramaCostoActividadID();
		logger.info("createCronogramaCostoActividad fin");
		return cronogramaCostoActividadID;
	}

	@Override
	public Integer createCronogramaMetaActividad(
			CronogramaMetaPorActividad cronogramaMetaPorActividad)
			throws Exception {
		logger.info("createCronogramaMetaActividad inicio");
		cronogramaMetaPorActividadDAO
				.saveCronorgramaMetaPorActividad(cronogramaMetaPorActividad);
		logger.info("createCronogramaMetaActividad fin");
		return cronogramaMetaPorActividad.getCronogramaMetaPorActividadID();
	}

	@Override
	public CronogramaCostoActividad updateCronogramaCostoActividad(
			CronogramaCostoActividad cronogramaCostoActividad) {
		logger.info("updateCronogramaCostoActividad inicio");
		CronogramaCostoActividad tmpCronogramaCostoActividad = getCronogramaCostoActividadDAO()
				.updateCronogramaCostoActividad(cronogramaCostoActividad);
		logger.info("updateCronogramaCostoActividad fin");
		return tmpCronogramaCostoActividad;
	}

	@Override
	public CronogramaMetaPorActividad updateCronogramaMetaPorActividad(
			CronogramaMetaPorActividad cronogramaMetaPorActividad) {
		logger.info("updateCronogramaMetaPorActividad inicio");
		return cronogramaMetaPorActividadDAO
				.updateCronorgramaMetaPorActividad(cronogramaMetaPorActividad);
	}

	private ResultadoForm setDataResultadoForm(Resultado resultado) {
		ResultadoForm resultadoForm = new ResultadoForm();
		resultadoForm.setResultadoID(resultado.getResultadoID());
		resultadoForm.setCodigoResultado(resultado.getCodigoResultado());
		resultadoForm
				.setDefinicionResultado(resultado.getDefinicionResultado());
		resultadoForm.setSupuestoResultado(resultado.getSupuestoResultado());
		resultadoForm.setMetaResultado(resultado.getMetaResultado());
		Integer estratoId = resultado.getFkIdtablaespUnidadMedida();
		resultadoForm.setEstratoId(estratoId);
		resultadoForm
				.setEstratoNombre(getTablaEspecificaDescripcion(estratoId));
		resultadoForm.setDuracionMeses(resultado.getDuracionMeses());
		return resultadoForm;
	}

	private List<BeneficiarioForm> setDataListBeneficiarioForm(
			List<BeneficiariosPorResultado> listBeneficiariosPorResultado) {
		List<BeneficiarioForm> listBeneficiarioForm = new ArrayList<BeneficiarioForm>();

		for (BeneficiariosPorResultado indicadorResultado : listBeneficiariosPorResultado) {
			BeneficiarioForm beneficiarioForm = new BeneficiarioForm();
			beneficiarioForm.setBeneficiariosPorResultadoID(indicadorResultado
					.getBeneficiariosPorResultadoID());
			Integer tipoBeneficiarioId = indicadorResultado
					.getFkIdtablaespTipoBeneficiario();
			beneficiarioForm.setTipoBeneficiarioId(tipoBeneficiarioId);
			beneficiarioForm
					.setTipoBeneficiarioNombre(getTablaEspecificaDescripcion(tipoBeneficiarioId));
			beneficiarioForm.setCaracteristicasPoblacion(indicadorResultado
					.getCaracteristicasPoblacion());
			beneficiarioForm.setCantidadProgramado(indicadorResultado
					.getCantidadProgramado());
			Integer estratoId = indicadorResultado.getFkidtablaespEstrato();
			beneficiarioForm.setEstratoId(estratoId);
			beneficiarioForm
					.setEstratoNombre(getTablaEspecificaDescripcion(estratoId));
			beneficiarioForm
					.setDescripcion(indicadorResultado.getDescripcion());
			listBeneficiarioForm.add(beneficiarioForm);
		}
		return listBeneficiarioForm;
	}

	@Override
	public List<CronogramaMetaForm> setDataListCronogramaMetaForm(
			List<CronogramaMetaPorResultado> listCronogramaMetaPorResultado) {
		List<CronogramaMetaForm> listCronogramaMetaForm = new ArrayList<CronogramaMetaForm>();
		for (CronogramaMetaPorResultado cronogramaMetaPorResultado : listCronogramaMetaPorResultado) {
			CronogramaMetaForm cronogramaMetaForm = new CronogramaMetaForm();
			cronogramaMetaForm
					.setCronogramaMetaPorResultadoID(cronogramaMetaPorResultado
							.getCronogramaMetaPorResultadoID());
			cronogramaMetaForm.setAvanceMeta(cronogramaMetaPorResultado
					.getAvanceMeta());
			cronogramaMetaForm.setPeriodo(cronogramaMetaPorResultado
					.getPeriodo());
			listCronogramaMetaForm.add(cronogramaMetaForm);
		}
		return listCronogramaMetaForm;
	}

	private List<CostoActividadForm> setDataListCostoActividadForm(
			List<CostoActividad> listCostoActividad) {
		List<CostoActividadForm> listCostoActividadForm = new ArrayList<CostoActividadForm>();
		for (CostoActividad costoActividad : listCostoActividad) {
			CostoActividadForm costoActividadForm = setDataCostoActividadForm(costoActividad);
			listCostoActividadForm.add(costoActividadForm);
		}
		return listCostoActividadForm;
	}

	private List<MetaActividadForm> setDataListMetaActividadForm(
			List<MetaPorActividad> listMetaActividad) {
		
		List<MetaActividadForm> listMetaActividadForm = new ArrayList<MetaActividadForm>();
		for (MetaPorActividad metaPorActividad : listMetaActividad) {
			MetaActividadForm metaActividadForm = setDataMetaActividadForm(metaPorActividad);
			listMetaActividadForm.add(metaActividadForm);
		}
		return listMetaActividadForm;
	}

	private List<CostoActividadView> setDataListCostoActividadView(
			List<CostoActividad> listCostoActividad) {
		List<CostoActividadView> listCostoActividadView = new ArrayList<CostoActividadView>();
		for (CostoActividad costoActividad : listCostoActividad) {
			CostoActividadView costoActividadView = setDataCostoActividadView(costoActividad);
			listCostoActividadView.add(costoActividadView);
		}
		return listCostoActividadView;
	}

	private List<CronogramaCostoActividadForm> setDataListCronogramaCostoActividadForm(
			List<CostoActividad> listCostoActividad) {
		List<CronogramaCostoActividadForm> listCronogramaCostoActividadForm = new ArrayList<CronogramaCostoActividadForm>();
		for (CostoActividad costoActividad : listCostoActividad) {
			CronogramaCostoActividadForm cronogramaCostoActividadForm = setDataCronogramaCostoActividadForm(costoActividad);
			listCronogramaCostoActividadForm.add(cronogramaCostoActividadForm);
		}
		return listCronogramaCostoActividadForm;
	}

	private List<ActividadForm> setDataListActividadForm(
			List<Actividad> listActividad) {
		List<ActividadForm> listActividadForm = new ArrayList<ActividadForm>();
		for (Actividad actividad : listActividad) {
			listActividadForm.add(setDataActividadForm(actividad));
		}
		return listActividadForm;
	}

	private List<IndicadorForm> setDataListIndicadorForm(
			List<IndicadorResultado> listIndicadorResultado) {
		List<IndicadorForm> listIndicadorForm = new ArrayList<IndicadorForm>();
		for (IndicadorResultado indicadorResultado : listIndicadorResultado) {
			IndicadorForm indicadorForm = setDataIndicadorForm(indicadorResultado);
			listIndicadorForm.add(indicadorForm);
		}
		return listIndicadorForm;
	}

	private List<IndicadorResultadoView> setDataListIndicadorResultadoView(
			List<IndicadorResultado> listIndicadorResultado) {
		List<IndicadorResultadoView> listIndicadorResultadoView = new ArrayList<IndicadorResultadoView>();
		for (IndicadorResultado indicadorResultado : listIndicadorResultado) {
			IndicadorResultadoView indicadorResultadoView = setDataIndicadorResultadoView(indicadorResultado);
			listIndicadorResultadoView.add(indicadorResultadoView);
		}
		return listIndicadorResultadoView;
	}

	private ActividadForm setDataActividadForm(Actividad actividad) {
		ActividadForm actividadForm = new ActividadForm();
		actividadForm.setActividadID(actividad.getActividadID());
		Integer tipoActividadId = actividad.getFkIdtablaespTipoActividad();
		actividadForm.setCodigoActividad(actividad.getCodigoActividad());
		actividadForm.setTipoActividadId(tipoActividadId);
		actividadForm
				.setTipoActividadNombre(getTablaEspecificaDescripcion(tipoActividadId));
		actividadForm.setNombreActividad(actividad.getNombreActividad());
		actividadForm.setDescripcionActividad(actividad
				.getDescripcionActividad());
		actividadForm.setDuracionMeses(actividad.getDuracionMeses());
		Integer actividadTransfId = actividad.getFkIdtablaespActividadTransf();
		actividadForm.setActividadTransfId(actividadTransfId);
		actividadForm
				.setActividadTransfNombre(getTablaEspecificaDescripcion(actividadTransfId));
		return actividadForm;
	}

	private CostoActividadForm setDataCostoActividadForm(
			CostoActividad costoActividad) {
		
		CostoActividadForm costoActividadForm = new CostoActividadForm();
		costoActividadForm.setCostoActividadID(costoActividad.getCostoActividadID());
		if (costoActividad.getRubroGenerico() != null) {
			costoActividadForm.setRubroGenericoID(costoActividad
					.getRubroGenerico().getRubroGenericoID());
			costoActividadForm.setDescripCabeceraRubroGenerico(costoActividad
					.getRubroGenerico().getDescripCabeceraRubroGenerico());
			costoActividadForm.setCabeceraRubroGenericoID(costoActividad.getRubroGenerico().getIdcabeceraRubrogenerico());
		}
		costoActividadForm.setPartidaEspecificaID(costoActividad.getPartidaEspecifica().getPartidaEspecificaID());
		costoActividadForm.setDescripcionPartidaEspecifica(costoActividad.getPartidaEspecifica().getDescripcionPartidaEspecifica());
		
		Integer unidadMedidaId = costoActividad.getFkIdtablaespUnidadMedida();
		costoActividadForm.setUnidadMedidaId(unidadMedidaId);
		costoActividadForm.setUnidadMedidaNombre(getTablaEspecificaDescripcion(unidadMedidaId));
		costoActividadForm.setCantidadTotal(costoActividad.getCantidadTotal());
		Integer tipoMonedaPrecioUnitarioId = costoActividad.getFkIdtablaespTipoMoneda();
		costoActividadForm.setTipoMonedaPrecioUnitarioId(tipoMonedaPrecioUnitarioId);
		costoActividadForm.setTipoMonedaPrecioUnitarioNombre(getTablaEspecificaDescripcion(tipoMonedaPrecioUnitarioId));
		costoActividadForm.setPrecioUnitario(costoActividad.getPrecioUnitario());
		Integer tipoMonedaMontoTotalId = costoActividad.getFkIdtablaespTipoMoneda();
		costoActividadForm.setTipoMonedaMontoTotalId(tipoMonedaMontoTotalId);
		costoActividadForm.setTipoMonedaMontoTotalNombre(getTablaEspecificaDescripcion(tipoMonedaMontoTotalId));
		costoActividadForm.setMontoTotal(costoActividad.getCantidadTotal()* costoActividad.getPrecioUnitario());
		costoActividadForm.setDetallePartidaGenerica(costoActividad.getDetallePartidaGenerica());
		costoActividadForm.setDetalleRubroGenerico(costoActividad.getDetalleRubroGenerico());
		costoActividadForm.setCategoriaActividadID(costoActividad.getCategoriaActividad().getCategoriaActividadID());
		costoActividadForm.setDescripcionCategoriaActividad(costoActividad.getCategoriaActividad().getDescripcionCategoriaActividad());
		costoActividadForm.setPartidaGenericaID(costoActividad.getPartidaGenerica().getPartidaGenericaID());
		costoActividadForm.setDescripcionPartidaGenerica(costoActividad.getPartidaGenerica().getDescripcionPartidaGenerica());
		
		Integer cantTotalCronogramaCostoActividad=0;
		List<CronogramaCostoActividad> listCronogramaCostoActividad=cronogramaCostoActividadService.findCronogramaCostoActividadByCostoActividadID(costoActividad.getCostoActividadID());
		for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
			cantTotalCronogramaCostoActividad += cronogramaCostoActividad.getCantidad();
		}
		costoActividadForm.setCantTotalCronogramaCostoActividad(cantTotalCronogramaCostoActividad);
		
		return costoActividadForm;
	}

	private MetaActividadForm setDataMetaActividadForm(
			MetaPorActividad metaPorActividad) {
		
		MetaActividadForm metaActividadForm = new MetaActividadForm();
		metaActividadForm.setCantidadMetaActividad(metaPorActividad
				.getCantidadMetaActividad());
		metaActividadForm.setUnidadMedidaId(metaPorActividad.getFkIdtablaespUnidadMedida());
		metaActividadForm.setUnidadMedidaNombre(this
				.getTablaEspecificaDescripcion(metaPorActividad
						.getFkIdtablaespUnidadMedida()));
		metaActividadForm.setMetaActividadID(metaPorActividad
				.getMetaPorActividadID());
		metaActividadForm.setContribucionProposito(metaPorActividad
				.getContribucionProposito());
		metaActividadForm.setTipoIndicadorActividadId(metaPorActividad
				.getFkIdtablaespTipoIndicadorActividad());
		metaActividadForm.setTipoIndicadorActividadNombre(this
				.getTablaEspecificaDescripcion(metaPorActividad
						.getFkIdtablaespTipoIndicadorActividad()));
		
		List<CronogramaMetaPorActividad> listCronogramaMetaPorActividad = cronogramaMetaPorActividadService.findCronogramaMetaPorActividadXMetaPorActividadId(metaPorActividad.getMetaPorActividadID());
		Integer cantTotalCronogramaMetaActividad=0;
		for (CronogramaMetaPorActividad cronogramaMetaPorActividad : listCronogramaMetaPorActividad) {
			cantTotalCronogramaMetaActividad += cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo();
		}
		metaActividadForm.setCantTotalCronogramaMetaActividad(cantTotalCronogramaMetaActividad);
		
		return metaActividadForm;
	}

	private CostoActividadView setDataCostoActividadView(
			CostoActividad costoActividad) {
		CostoActividadView costoActividadView = new CostoActividadView();
		costoActividadView.setCostoActividadID(costoActividad
				.getCostoActividadID());
		costoActividadView.setActividadID(costoActividad.getActividad()
				.getActividadID());
		if (costoActividad.getCategoriaActividad() != null) {
			costoActividadView.setCategoriaActividadID(costoActividad
					.getCategoriaActividad().getCategoriaActividadID());
			costoActividadView
					.setDescripcionCategoriaActividad(costoActividad
							.getCategoriaActividad()
							.getDescripcionCategoriaActividad());
		}
		if (costoActividad.getRubroGenerico() != null) {
			costoActividadView.setRubroGenericoID(costoActividad
					.getRubroGenerico().getRubroGenericoID());
			costoActividadView.setDescripcionRubroGenerico(costoActividad
					.getRubroGenerico().getDescripCabeceraRubroGenerico());
		}
		if (costoActividad.getPartidaEspecifica() != null) {
			costoActividadView.setPartidaEspecificaID(costoActividad
					.getPartidaEspecifica().getPartidaEspecificaID());
			costoActividadView.setDescripcionPartidaEspecifica(costoActividad
					.getPartidaEspecifica().getDescripcionPartidaEspecifica());
		}
		if (costoActividad.getPartidaGenerica() != null) {
			costoActividadView.setPartidaEspecificaID(costoActividad
					.getPartidaEspecifica().getPartidaEspecificaID());
			costoActividadView.setDescripcionPartidaEspecifica(costoActividad
					.getPartidaEspecifica().getDescripcionPartidaEspecifica());
			costoActividadView.setPartidaGenericaID(costoActividad
					.getPartidaGenerica().getPartidaGenericaID());
			costoActividadView.setDescripcionPartidaGenerica(costoActividad
					.getPartidaGenerica().getDescripcionPartidaGenerica());
		}
		Integer unidadMedidaId = costoActividad.getFkIdtablaespUnidadMedida();
		costoActividadView.setUnidadMedidaId(unidadMedidaId);
		costoActividadView
				.setUnidadMedidaNombre(getTablaEspecificaDescripcion(unidadMedidaId));
		costoActividadView.setCantidadTotal(costoActividad.getCantidadTotal());
		Integer tipoMonedaPrecioUnitarioId = costoActividad
				.getFkIdtablaespTipoMoneda();
		costoActividadView
				.setTipoMonedaPrecioUnitarioId(tipoMonedaPrecioUnitarioId);
		costoActividadView
				.setTipoMonedaPrecioUnitarioNombre(getTablaEspecificaDescripcion(tipoMonedaPrecioUnitarioId));
		costoActividadView
				.setPrecioUnitario(costoActividad.getPrecioUnitario());
		Integer tipoMonedaMontoTotalId = costoActividad
				.getFkIdtablaespTipoMoneda();
		costoActividadView.setTipoMonedaMontoTotalId(tipoMonedaMontoTotalId);
		costoActividadView
				.setTipoMonedaMontoTotalNombre(getTablaEspecificaDescripcion(tipoMonedaMontoTotalId));

		costoActividadView.setMontoTotal(costoActividad.getCantidadTotal()
				* costoActividad.getPrecioUnitario());
		costoActividadView.setDetallePartidaGenerica(costoActividad
				.getDetallePartidaGenerica());
		costoActividadView.setDetalleRubroGenerico(costoActividad
				.getDetalleRubroGenerico());
		return costoActividadView;
	}

	private CronogramaCostoActividadForm setDataCronogramaCostoActividadForm(
			CostoActividad costoActividad) {
		CronogramaCostoActividadForm cronogramaCostoActividadForm = new CronogramaCostoActividadForm();
		cronogramaCostoActividadForm.setCostoActividadID(costoActividad
				.getCostoActividadID());
		if (costoActividad.getRubroGenerico() != null) {
			cronogramaCostoActividadForm.setRubroGenericoID(costoActividad
					.getRubroGenerico().getRubroGenericoID());
			cronogramaCostoActividadForm
					.setDescripCabeceraRubroGenerico(costoActividad
							.getRubroGenerico()
							.getDescripCabeceraRubroGenerico());
		}
		cronogramaCostoActividadForm.setPartidaEspecificaID(costoActividad
				.getPartidaEspecifica().getPartidaEspecificaID());
		cronogramaCostoActividadForm
				.setDescripcionPartidaEspecifica(costoActividad
						.getPartidaEspecifica()
						.getDescripcionPartidaEspecifica());
		Integer unidadMedidaId = costoActividad.getFkIdtablaespUnidadMedida();
		cronogramaCostoActividadForm.setUnidadMedidaId(unidadMedidaId);
		cronogramaCostoActividadForm
				.setUnidadMedidaNombre(getTablaEspecificaDescripcion(unidadMedidaId));
		cronogramaCostoActividadForm.setCantidadTotal(costoActividad
				.getCantidadTotal());
		Integer tipoMonedaPrecioUnitarioId = costoActividad
				.getFkIdtablaespTipoMoneda();
		cronogramaCostoActividadForm
				.setTipoMonedaPrecioUnitarioId(tipoMonedaPrecioUnitarioId);
		cronogramaCostoActividadForm
				.setTipoMonedaPrecioUnitarioNombre(getTablaEspecificaDescripcion(tipoMonedaPrecioUnitarioId));
		cronogramaCostoActividadForm.setPrecioUnitario(costoActividad
				.getPrecioUnitario());
		Integer tipoMonedaMontoTotalId = costoActividad
				.getFkIdtablaespTipoMoneda();
		cronogramaCostoActividadForm
				.setTipoMonedaMontoTotalId(tipoMonedaMontoTotalId);
		cronogramaCostoActividadForm
				.setTipoMonedaMontoTotalNombre(getTablaEspecificaDescripcion(tipoMonedaMontoTotalId));
		cronogramaCostoActividadForm.setMontoTotal(costoActividad
				.getMontoGastado());
		cronogramaCostoActividadForm.setDetallePartidaGenerica(costoActividad
				.getDetallePartidaGenerica());
		cronogramaCostoActividadForm.setDetalleRubroGenerico(costoActividad
				.getDetalleRubroGenerico());
		return cronogramaCostoActividadForm;
	}

	private IndicadorForm setDataIndicadorForm(
			IndicadorResultado indicadorResultado) {
		IndicadorForm indicador = new IndicadorForm();
		indicador.setIndicadorResultadoID(indicadorResultado
				.getIndicadorResultadoID());
		Integer tipoIndicadorId = indicadorResultado
				.getFkIdtablaespTipoIndicadorResultado();
		indicador.setTipoIndicadorId(tipoIndicadorId);
		indicador
				.setTipoIndicadorNombre(getTablaEspecificaDescripcion(tipoIndicadorId));
		indicador.setDefinicionIndicador(indicadorResultado
				.getDefinicionIndicador());
		Integer unidadMedidaId = indicadorResultado
				.getFkIdtablaespUnidadMedida();
		indicador.setUnidadMedidaId(unidadMedidaId);
		indicador
				.setUnidadMedidaNombre(getTablaEspecificaDescripcion(unidadMedidaId));
		indicador.setMedioVerificacion(indicadorResultado
				.getMedioVerificacion());
		indicador.setSituacionActual(indicadorResultado.getSituacionActual());
		indicador.setSituacionFinal(indicadorResultado.getSituacionFinal());
		indicador.setLogroAlcanzado(indicadorResultado.getLogroAlcanzado());
		indicador.setObservacion(indicadorResultado.getObservacion());
		return indicador;
	}

	private IndicadorResultadoView setDataIndicadorResultadoView(
			IndicadorResultado indicadorResultado) {
		
		IndicadorResultadoView indicador = new IndicadorResultadoView();
		
		indicador.setIndicadorResultadoID(indicadorResultado
				.getIndicadorResultadoID());
		Integer tipoIndicadorId = indicadorResultado
				.getFkIdtablaespTipoIndicadorResultado();
		indicador.setTipoIndicadorId(tipoIndicadorId);
		indicador
				.setTipoIndicadorNombre(getTablaEspecificaDescripcion(tipoIndicadorId));
		indicador.setDefinicionIndicador(indicadorResultado
				.getDefinicionIndicador());
		Integer unidadMedidaId = indicadorResultado
				.getFkIdtablaespUnidadMedida();
		indicador.setUnidadMedidaId(unidadMedidaId);
		indicador
				.setUnidadMedidaNombre(getTablaEspecificaDescripcion(unidadMedidaId));
		indicador.setMedioVerificacion(indicadorResultado
				.getMedioVerificacion());
		indicador.setMetodoCalculo(indicadorResultado.getMetodoCalculo());
		indicador.setSituacionActual(indicadorResultado.getSituacionActual());
		indicador.setSituacionFinal(indicadorResultado.getSituacionFinal());
		indicador.setLogroAlcanzado(indicadorResultado.getLogroAlcanzado());
		indicador.setObservacion(indicadorResultado.getObservacion());
		return indicador;
	}

	@Override
	public List<PlanOperativoForm> showPlanOperativo() {
		List<DatoPlanOperativo> listDatoPlanOperativo = getDatoPlanOperativoDAO()
				.findListDatoPlanOperativo();
		List<PlanOperativoForm> listPlanOperativoForm = new ArrayList<PlanOperativoForm>();
		PlanOperativoForm planOperativoForm = null;
		for (DatoPlanOperativo datoPlanOperativo : listDatoPlanOperativo) {
			planOperativoForm = new PlanOperativoForm();
			planOperativoForm.setDatoPlanOperativoID(datoPlanOperativo
					.getDatoPlanOperativoID());
			planOperativoForm.setVersion(datoPlanOperativo.getVersion());
			planOperativoForm.setEstadoPlanOperativo(datoPlanOperativo
					.getFkIdDetalleEstadoCabEstadoPlanOper());
			planOperativoForm.setEstadoPlanOperativoDescripcion("NUEVO");
			planOperativoForm.setDatoProyectoID(100);
			planOperativoForm.setNombreProyecto("Prueba 001");
			listPlanOperativoForm.add(planOperativoForm);
		}
		return listPlanOperativoForm;
	}

	@Override
	public Integer createPlanOperativo(DatoPlanOperativo datoPlanOperativo)
			throws Exception {
		Integer datoPlanOperativoID = null;
		getDatoPlanOperativoDAO().saveDatoPlanOperativo(datoPlanOperativo);
		datoPlanOperativoID = datoPlanOperativo.getDatoPlanOperativoID();
		// aqui tengo que crear los resultado y las actividades que vienen desde
		// la fase proyecto
		return datoPlanOperativoID;
	}

	@Override
	public Integer createPlanOperativo(PlanOperativoForm planOperativoForm)
			throws Exception {

		validateInputObject(planOperativoForm);

		DatoPlanOperativo datoPlanOperativo = null;
		Integer datoProyectoID = planOperativoForm.getDatoProyectoID();
		logger.info("datoProyectoID = " + datoProyectoID);
		datoPlanOperativo = getDatoPlanOperativoDAO()
				.findDatoPlanOperativoByDatoProyectoID(datoProyectoID);
		if (UtilValidate.isEmpty(datoPlanOperativo)) {
			datoPlanOperativo = savePlanOperativo(
					planOperativoForm.getVersion(),
					planOperativoForm.getEstadoPlanOperativo(),
					planOperativoForm.getDatoProyectoID());
		}
		Integer datoPlanOperativoID = datoPlanOperativo
				.getDatoPlanOperativoID();
		logger.info("datoPlanOperativoID = " + datoPlanOperativoID);
		List<Integer> listresultadoID = saveResultadoWithChilds(
				datoPlanOperativo, planOperativoForm.getListResultadoForm());
		for (Integer resultadoID : listresultadoID) {
			logger.info("resultadoID = " + resultadoID);
		}
		return datoPlanOperativoID;
	}

	@Override
	public Integer createResultado(Resultado resultado) throws Exception {
		//Integer resultadoID = null;
		Integer codigoResultado = resultado.getCodigoResultado();
		if (codigoResultado == null) {
			Integer datoPlanOperativoID = resultado.getDatoPlanOperativo()
					.getDatoPlanOperativoID();
			codigoResultado = nextCodigoResultado(datoPlanOperativoID);
			resultado.setCodigoResultado(codigoResultado);
		}
		resultado= getResultadoDAO().updateResultado(resultado);
		//resultadoID = resultado.getResultadoID();
		return resultado.getResultadoID();
	}

	public void deleteResultado(Integer resultadoID) throws Exception {
		Resultado resultado = new Resultado();
		resultado.setResultadoID(resultadoID);
		resultadoDAO.deleteResultado(resultado);
	}

	@Override
	public Integer createBeneficiarioResultado(
			BeneficiariosPorResultado beneficiariosPorResultado)
			throws Exception {
		//Integer beneficiariosPorResultadoID = null;
		beneficiariosPorResultado = getBeneficiariosPorResultadoDAO().updateBeneficiariosPorResultado(
				beneficiariosPorResultado);
		//beneficiariosPorResultadoID = beneficiariosPorResultado.getBeneficiariosPorResultadoID();
		return beneficiariosPorResultado.getBeneficiariosPorResultadoID();
	}

	@Override
	public Integer createIndicadorResultado(
			IndicadorResultado indicadorResultado) throws Exception {
		//Integer indicadorResultadoID = null;
		indicadorResultado = getIndicadorResultadoDAO().updateIndicadorResultado(indicadorResultado);
		//indicadorResultadoID = indicadorResultado.getIndicadorResultadoID();
		return indicadorResultado.getIndicadorResultadoID();
	}

	@Override
	public Integer createCronogramaResultado(
			CronogramaMetaPorResultado cronogramaMetaPorResultado)
			throws Exception {
		//logger.info("createCronogramaResultado inicio");
		Integer cronogramaMetaPorResultadoID = null;
		getCronogramaMetaPorResultadoDAO().updateCronogramaMetaPorResultado(
				cronogramaMetaPorResultado);
		cronogramaMetaPorResultadoID = cronogramaMetaPorResultado
				.getCronogramaMetaPorResultadoID();
		
		return cronogramaMetaPorResultadoID;
	}

	public List<CronogramaMetaPorResultado> findCronogramaMetaPorResultadoByResultadoID(
			Integer resultadoID) {
		return cronogramaMetaPorResultadoDAO
				.findCronogramaMetaPorResultadoByResultadoID(resultadoID);
	}

	@Override
	public Integer createIndicador(Integer resultadoID, IndicadorForm indicador)
			throws Exception {
		logger.info("createIndicador Start");

		Resultado resultado = getResultadoDAO().findResultadoById(resultadoID);

		IndicadorResultado indicadorResultado = new IndicadorResultado();
		indicadorResultado.setFkIdtablaespTipoIndicadorResultado(indicador
				.getTipoIndicadorId());
		indicadorResultado.setDefinicionIndicador(indicador
				.getDefinicionIndicador());
		indicadorResultado.setFkIdtablaespUnidadMedida(indicador
				.getUnidadMedidaId());
		indicadorResultado.setMedioVerificacion(indicador
				.getMedioVerificacion());
		indicadorResultado.setSituacionActual(indicador.getSituacionActual());
		indicadorResultado.setSituacionFinal(indicador.getSituacionFinal());
		indicadorResultado.setLogroAlcanzado(indicador.getLogroAlcanzado());
		indicadorResultado.setObservacion(indicador.getObservacion());
		indicadorResultado.setResultado(resultado);

		getIndicadorResultadoDAO().saveIndicadorResultado(indicadorResultado);
		Integer indicadorResultadoID = indicadorResultado
				.getIndicadorResultadoID();
		logger.info("indicadorResultadoID = " + indicadorResultadoID);

		logger.info("createIndicador Finish");
		return indicadorResultadoID;
	}

	@Override
	public DatoPlanOperativo findPlanOperativoByID(Integer datoPlanOperativoID) {
		return datoPlanOperativoDAO
				.findDatoPlanOperativoById(datoPlanOperativoID);
	}

	@Override
	public void updatePlanOperativo(DatoPlanOperativo dpo) {
		datoPlanOperativoDAO.updateDatoPlanOperativo(dpo);
	}

	@Override
	public List<CronogramaMetaPorResultado> findListaMetaResultadoByResultadoID(
			int resultadoID) {

		return cronogramaMetaPorResultadoDAO
				.findCronogramaMetaPorResultadoByResultadoID(resultadoID);
	}

	@Override
	public List<BeneficiarioForm> findListaBeneficiariosByResultadoID(
			int resultadoID) {
		List<BeneficiariosPorResultado> list = beneficiariosPorResultadoDAO
				.findBeneficiariosPorResultadoByResultadoID(resultadoID);
		List<BeneficiarioForm> listNew = new ArrayList<BeneficiarioForm>();

		for (BeneficiariosPorResultado bean : list) {
			BeneficiarioForm b = new BeneficiarioForm();
			b.setBeneficiariosPorResultadoID(bean
					.getBeneficiariosPorResultadoID());
			b.setCantidadProgramado(bean.getCantidadProgramado());
			b.setCaracteristicasPoblacion(bean.getCaracteristicasPoblacion());
			b.setDescripcion(bean.getDescripcion());
			String estato = tablaEspecificaDAO.findTablaEspecificaById(
					bean.getFkidtablaespEstrato()).getDescripcionCabecera();
			b.setEstratoNombre(estato);
			String tipoBeneficiario = tablaEspecificaDAO
					.findTablaEspecificaById(
							bean.getFkIdtablaespTipoBeneficiario())
					.getDescripcionCabecera();
			b.setTipoBeneficiarioNombre(tipoBeneficiario);
			listNew.add(b);

		}
		return listNew;
	}

	@Override
	public MetaPorActividad findMetaPorActividadByActividadId(int actividadID) {
		return metaPorActividadDAO
				.findMetaPorActividadByActividadId(actividadID);

	}

	public MetaActividadForm obtenerMetaPorActividadById(int actividadID) {
		MetaPorActividad metaPorActividad = metaPorActividadDAO
				.findMetaPorActividadById(actividadID);
		return setDataMetaActividadForm(metaPorActividad);
	}

	public List<Riesgo> findRiesgoByActividadID(int id) {
		return riesgoDAO.findRiesgoByActividadID(id);
	}

	@Override
	public CostoActividad findCostoActividadByID(int costoActividadID) {
		return costoActividadDAO.findCostoActividadById(costoActividadID);
	}

	@Override
	public List<BeneficiarioResultadoView> findBeneficiarioResultadoByResultadoID(
			Integer resultadoID) {
		List<BeneficiarioResultadoView> listBeneficiarioResultadoView = new ArrayList<BeneficiarioResultadoView>();
		List<BeneficiariosPorResultado> listBeneficiariosPorResultado = getBeneficiariosPorResultadoDAO()
				.findBeneficiariosPorResultadoByResultadoID(resultadoID);
		if (UtilValidate.isNotEmpty(listBeneficiariosPorResultado)) {
			listBeneficiarioResultadoView = setDataListBeneficiarioResultadoView(listBeneficiariosPorResultado);
		}
		return listBeneficiarioResultadoView;
	}

	@Override
	public List<IndicadorResultadoView> findIndicadorResultadoViewByResultadoID(
			Integer resultadoID) {

		//logger.info("[PlanOperativoService:obtenerIndicadorResultadoByResultadoID] Start");
		List<IndicadorResultadoView> listIndicadorResultadoView = null;
		List<IndicadorResultado> listIndicadorResultado = getIndicadorResultadoDAO()
				.findIndicadorResultadoByResultadoID(resultadoID);
		if (UtilValidate.isNotEmpty(listIndicadorResultado)) {
			listIndicadorResultadoView = setDataListIndicadorResultadoView(listIndicadorResultado);
		}
		//logger.info("[PlanOperativoService:obtenerIndicadorResultadoByResultadoID] Finished");
		return listIndicadorResultadoView;
	}

	@Override
	public CronogramaCostoActividad findCronogramaCostoActividadByID(
			int cronogramaCostoActividadID) {
		return cronogramaCostoActividadDAO
				.findCronogramaCostoActividadById(cronogramaCostoActividadID);
	}

	@Override
	public List<FuenteFinanciadora> findFuenteFinanciadoraByDatoProyectoID(
			Integer datoProyectoID) {
		return getFuenteFinanciadoraDAO().findFuenteFinanciadorasByIdDatoProy(
				datoProyectoID);
	}

	private List<BeneficiarioResultadoView> setDataListBeneficiarioResultadoView(
			List<BeneficiariosPorResultado> listBeneficiariosPorResultado) {
		List<BeneficiarioResultadoView> listBeneficiarioResultadoView = new ArrayList<BeneficiarioResultadoView>();
		for (BeneficiariosPorResultado beneficiariosPorResultado : listBeneficiariosPorResultado) {
			BeneficiarioResultadoView beneficiarioResultadoView = setDataBeneficiarioResultadoView(beneficiariosPorResultado);
			listBeneficiarioResultadoView.add(beneficiarioResultadoView);
		}
		return listBeneficiarioResultadoView;
	}

	private BeneficiarioResultadoView setDataBeneficiarioResultadoView(
			BeneficiariosPorResultado beneficiariosPorResultado) {

		BeneficiarioResultadoView beneficiarioResultadoView = new BeneficiarioResultadoView();
		beneficiarioResultadoView
				.setBeneficiariosPorResultadoID(beneficiariosPorResultado
						.getBeneficiariosPorResultadoID());
		Integer tipoBeneficiarioId = beneficiariosPorResultado
				.getFkIdtablaespTipoBeneficiario();
		beneficiarioResultadoView.setTipoBeneficiarioId(tipoBeneficiarioId);
		beneficiarioResultadoView
				.setTipoBeneficiarioNombre(getTablaEspecificaDescripcion(tipoBeneficiarioId));
		beneficiarioResultadoView
				.setCaracteristicasPoblacion(beneficiariosPorResultado
						.getCaracteristicasPoblacion());
		beneficiarioResultadoView
				.setCantidadProgramado(beneficiariosPorResultado
						.getCantidadProgramado());
		Integer estratoId = beneficiariosPorResultado.getFkidtablaespEstrato();
		beneficiarioResultadoView.setEstratoId(estratoId);
		beneficiarioResultadoView
				.setEstratoNombre(getTablaEspecificaDescripcion(estratoId));
		beneficiarioResultadoView.setDescripcion(beneficiariosPorResultado
				.getDescripcion());
		beneficiarioResultadoView.setCantPropuestaTransferenciaBeneficiario(propuestaTransferenciaBeneficiarioService.findPropuestaTransferenciaBeneficiarioByBeneficiarioPorResultadoId(beneficiariosPorResultado.getBeneficiariosPorResultadoID()).size());
		beneficiarioResultadoView.setCantReporteAvanceBeneficiario(reporteAvanceBeneficiarioService.findReporteAvanceBeneficiarioXBeneficiariosPorResultadoId(beneficiariosPorResultado.getBeneficiariosPorResultadoID()).size());

		return beneficiarioResultadoView;
	}

	private CronogramaCostoActividadView setDataCronogramaCostoActividadViewComplete(
			Integer tmpFuenteFinanciadoraID, CostoActividad costoActividad,
			List<CronogramaCostoActividad> ListCronogramaCostoActividad) {

		CronogramaCostoActividadView cronogramaCostoActividadView = new CronogramaCostoActividadView();
		List<PeriodoView> listPeriodo = new ArrayList<PeriodoView>();
		boolean loaded = false;
		for (CronogramaCostoActividad cronogramaCostoActividad : ListCronogramaCostoActividad) {
			Integer fuenteFinanciadoraID = cronogramaCostoActividad
					.getFuenteFinanciadora().getFuenteFinanciadoraID();
			if (tmpFuenteFinanciadoraID.intValue() == fuenteFinanciadoraID
					.intValue()) {
				if (!loaded) {
					cronogramaCostoActividadView
							.setCostoActividadID(costoActividad
									.getCostoActividadID());
					cronogramaCostoActividadView
							.setPartidaEspecificaID(costoActividad
									.getPartidaEspecifica()
									.getPartidaEspecificaID());
					cronogramaCostoActividadView
							.setDescripcionPartidaEspecifica(costoActividad
									.getPartidaEspecifica()
									.getDescripcionPartidaEspecifica());
					Integer unidadMedidaId = costoActividad
							.getFkIdtablaespUnidadMedida();
					cronogramaCostoActividadView
							.setUnidadMedidaId(unidadMedidaId);
					cronogramaCostoActividadView
							.setUnidadMedidaNombre(getTablaEspecificaDescripcion(unidadMedidaId));
					cronogramaCostoActividadView
							.setCantidadTotal(costoActividad.getCantidadTotal());
					Integer tipoMonedaPrecioUnitarioId = costoActividad
							.getFkIdtablaespTipoMoneda();
					cronogramaCostoActividadView
							.setTipoMonedaPrecioUnitarioId(tipoMonedaPrecioUnitarioId);
					cronogramaCostoActividadView
							.setTipoMonedaPrecioUnitarioNombre(getTablaEspecificaDescripcion(tipoMonedaPrecioUnitarioId));
					cronogramaCostoActividadView
							.setPrecioUnitario(costoActividad
									.getPrecioUnitario());
					cronogramaCostoActividadView.setMontoTotal(costoActividad
							.getMontoGastado());
					cronogramaCostoActividadView
							.setCronogramaCostoActividadID(cronogramaCostoActividad
									.getCronogramaCostoActividadID());
					FuenteFinanciadora fuenteFinanciadora = cronogramaCostoActividad
							.getFuenteFinanciadora();
					cronogramaCostoActividadView
							.setFuenteFinanciadoraID(fuenteFinanciadora
									.getFuenteFinanciadoraID());
					Institucion institucion = fuenteFinanciadora
							.getInstitucion();
					cronogramaCostoActividadView.setInstitucionID(institucion
							.getInstitucionID());
					cronogramaCostoActividadView
							.setNombreInstitucion(institucion
									.getNombreInstitucion());
					loaded = true;
				}
				PeriodoView periodoView = new PeriodoView();
				Integer numeroPerido = Integer
						.parseInt(cronogramaCostoActividad.getPeriodo());
				periodoView.setNumeroPerido(numeroPerido);
				periodoView.setMontoPerido(cronogramaCostoActividad
						.getCantidad().doubleValue());
				listPeriodo.add(periodoView);
			}
		}
		cronogramaCostoActividadView.setListPeriodo(listPeriodo);
		return cronogramaCostoActividadView;
	}

	private CronogramaCostoActividad2View setDataCronogramaCostoActividad2ViewComplete(
			Integer tmpFuenteFinanciadoraID, CostoActividad costoActividad,
			List<CronogramaCostoActividad> ListCronogramaCostoActividad) {

		CronogramaCostoActividad2View cronogramaCostoActividad2View = new CronogramaCostoActividad2View();
		List<PeriodoView> listPeriodo = new ArrayList<PeriodoView>();
		boolean loaded = false;
		for (CronogramaCostoActividad cronogramaCostoActividad : ListCronogramaCostoActividad) {

			Integer fuenteFinanciadoraID = cronogramaCostoActividad
					.getFuenteFinanciadora().getFuenteFinanciadoraID();
			if (tmpFuenteFinanciadoraID.intValue() == fuenteFinanciadoraID
					.intValue()) {
				if (!loaded) {
					cronogramaCostoActividad2View
							.setCostoActividadID(costoActividad
									.getCostoActividadID());
					cronogramaCostoActividad2View
							.setPartidaEspecificaID(costoActividad
									.getPartidaEspecifica()
									.getPartidaEspecificaID());
					cronogramaCostoActividad2View
							.setDescripcionPartidaEspecifica(costoActividad
									.getPartidaEspecifica()
									.getDescripcionPartidaEspecifica());
					Integer unidadMedidaId = costoActividad
							.getFkIdtablaespUnidadMedida();
					cronogramaCostoActividad2View
							.setUnidadMedidaId(unidadMedidaId);
					cronogramaCostoActividad2View
							.setUnidadMedidaNombre(getTablaEspecificaDescripcion(unidadMedidaId));
					Integer tipoMonedaPrecioUnitarioId = costoActividad
							.getFkIdtablaespTipoMoneda();
					cronogramaCostoActividad2View
							.setTipoMonedaPrecioUnitarioId(tipoMonedaPrecioUnitarioId);
					cronogramaCostoActividad2View
							.setTipoMonedaPrecioUnitarioNombre(getTablaEspecificaDescripcion(tipoMonedaPrecioUnitarioId));
					cronogramaCostoActividad2View
							.setPrecioUnitario(costoActividad
									.getPrecioUnitario());
					cronogramaCostoActividad2View
							.setCronogramaCostoActividadID(cronogramaCostoActividad
									.getCronogramaCostoActividadID());
					FuenteFinanciadora fuenteFinanciadora = cronogramaCostoActividad
							.getFuenteFinanciadora();
					cronogramaCostoActividad2View
							.setFuenteFinanciadoraID(fuenteFinanciadora
									.getFuenteFinanciadoraID());
					Institucion institucion = fuenteFinanciadora
							.getInstitucion();
					cronogramaCostoActividad2View.setInstitucionID(institucion
							.getInstitucionID());
					cronogramaCostoActividad2View
							.setNombreInstitucion(institucion
									.getNombreInstitucion());
					loaded = true;
				}
				PeriodoView periodoView = new PeriodoView();
				Integer numeroPerido = Integer
						.parseInt(cronogramaCostoActividad.getPeriodo());
				periodoView.setNumeroPerido(numeroPerido);
				periodoView.setCantidadPeriodo(cronogramaCostoActividad
						.getCantidad());
				periodoView.setMontoPerido(cronogramaCostoActividad
						.getCantidad().doubleValue()
						* costoActividad.getPrecioUnitario());
				listPeriodo.add(periodoView);
			}

		}
		cronogramaCostoActividad2View.setListPeriodo(listPeriodo);
		return cronogramaCostoActividad2View;
	}

	private CronogramaMetaActividadView setDataCronogramaMetaActividadViewComplete(
			MetaPorActividad metaPorActividad,
			List<CronogramaMetaPorActividad> ListCronogramaMetaPorActividad) {

		CronogramaMetaActividadView cronogramaMetaActividadView = new CronogramaMetaActividadView();
		
		List<PeriodoView> listPeriodo = new ArrayList<PeriodoView>();
		boolean loaded = false;
		for (CronogramaMetaPorActividad cronogramaMetaPorActividad : ListCronogramaMetaPorActividad) {
			if (!loaded) {
				cronogramaMetaActividadView
						.setMetaActividadID(cronogramaMetaPorActividad
								.getMetaPorActividad().getMetaPorActividadID());
				cronogramaMetaActividadView
						.setCronogramaMetaActividadID(cronogramaMetaPorActividad
								.getCronogramaMetaPorActividadID());
				loaded = true;
			}
			PeriodoView periodoView = new PeriodoView();
			Integer numeroPerido = Integer.parseInt(cronogramaMetaPorActividad
					.getPeriodo());
			periodoView.setNumeroPerido(numeroPerido);
			periodoView.setCantidadPeriodo(cronogramaMetaPorActividad
					.getCantidadMetaActividadProgPorPeriodo());
			periodoView.setPeriodoReportado(cronogramaMetaPorActividad.getPeriodoReportado());
			listPeriodo.add(periodoView);
		}
		cronogramaMetaActividadView.setListPeriodo(listPeriodo);
		return cronogramaMetaActividadView;
	}

	@Override
	public FuenteFinanciadora findFuenteFinanciadoraById(
			Integer fuenteFinanciadoraID) {
		return getFuenteFinanciadoraDAO().findFuenteFinanciadoraById(
				fuenteFinanciadoraID);
	}

	@Override
	public List<ActividadObligatoriaPrograma> findListActividadObligatoriaProgramaByProgramaId(
			Integer programaID) {
		return getActividadObligatoriaProgramaDAO()
				.findListActividadObligatoriaProgramaByProgramaId(programaID);
	}

	@Override
	public String obtenerDescripcionTablaEspecifica(Integer tablaEspecificaID) {
		return getTablaEspecificaDescripcion(tablaEspecificaID);
	}

	private Integer getCantidadTotal(List<PeriodoView> listPeriodo) {
		logger.info("obteneniendo getCantidadTotal ");
		Integer cantidadTotal = 0;
		for (PeriodoView periodoView : listPeriodo) {
			logger.info("periodoView.getCantidadPeriodo() = "
					+ periodoView.getCantidadPeriodo());
			cantidadTotal = cantidadTotal + periodoView.getCantidadPeriodo();
		}
		logger.info("cantidadTotal = " + cantidadTotal);
		return cantidadTotal;
	}

	private Double getCostoTotal(List<PeriodoView> listPeriodo) {
		logger.info("obteneniendo getCostoTotal ");
		Double costoTotal = (double) 0;
		for (PeriodoView periodoView : listPeriodo) {
			logger.info("periodoView.getMontoPerido() = "
					+ periodoView.getMontoPerido());
			costoTotal = costoTotal + periodoView.getMontoPerido();
		}
		logger.info("costoTotal = " + costoTotal);
		return costoTotal;
	}

	private void validateInputObject(PlanOperativoForm planOperativoForm)
			throws Exception {
		String message = null;
		if (UtilValidate.isEmpty(planOperativoForm)) {
			message = "El objecto PlanOperativoForm no debe ser nulo,";
			logger.error(message);
			throw new Exception(message);
		}
		validatePlanOperativoAttributes(planOperativoForm.getVersion(),
				planOperativoForm.getEstadoPlanOperativo(),
				planOperativoForm.getDatoProyectoID(),
				planOperativoForm.getListResultadoForm());

	}

	private void validatePlanOperativoAttributes(String version,
			Integer estado, Integer datoProyectoId,
			List<ResultadoForm> listResultadoForm) throws Exception {
		String message = null;
		if (UtilValidate.isEmpty(version)) {
			message = "El atributo version no debe ser nulo,";
			logger.error(message);
			throw new Exception(message);
		}

		if (UtilValidate.isEmpty(estado)) {
			message = "El atributo estado no debe ser nulo,";
			logger.error(message);
			throw new Exception(message);
		}

		if (UtilValidate.isEmpty(listResultadoForm)) {
			message = "El atributo listResultadoForm no debe ser nulo,";
			logger.error(message);
			throw new Exception(message);
		}

	}

	private DatoPlanOperativo savePlanOperativo(String version, Integer estado,
			Integer datoProyectoId) throws Exception {
		logger.info("savePlanOperativo start");
		DatoProyecto datoProyecto = getDataDatoProyecto(datoProyectoId);
		DatoPlanOperativo datoPlanOperativo = new DatoPlanOperativo();
		datoPlanOperativo.setVersion(version);
		datoPlanOperativo.setFkIdDetalleEstadoCabEstadoPlanOper(estado);
		datoPlanOperativo.setDatoProyecto(datoProyecto);
		getDatoPlanOperativoDAO().saveDatoPlanOperativo(datoPlanOperativo);
		logger.info("savePlanOperativo finish");
		return datoPlanOperativo;
	}

	private List<Integer> saveResultadoWithChilds(
			DatoPlanOperativo datoPlanOperativo,
			List<ResultadoForm> listResultadoForm) {

		List<Integer> listresultadoID = new ArrayList<Integer>();
		for (ResultadoForm resultadoForm : listResultadoForm) {
			Integer resultadoID = saveResultado(datoPlanOperativo,
					resultadoForm);
			listresultadoID.add(resultadoID);
		}

		return listresultadoID;
	}

	private DatoProyecto getDataDatoProyecto(Integer datoProyectoId)
			throws Exception {
		String message = null;
		DatoProyecto datoProyecto = getDatoProyectoDAO().findDatoProyectoById(
				datoProyectoId);
		if (UtilValidate.isEmpty(datoProyecto)) {
			message = "No existe informacion en la base de datos del datoProyectoId "
					+ datoProyectoId;
			logger.error(message);
			throw new Exception(message);
		}
		return datoProyecto;
	}

	private Integer saveResultado(DatoPlanOperativo datoPlanOperativo,
			ResultadoForm resultadoForm) {

		Resultado resultado = new Resultado();
		resultado
				.setDefinicionResultado(resultadoForm.getDefinicionResultado());
		resultado.setSupuestoResultado(resultadoForm.getSupuestoResultado());
		resultado.setMetaResultado(resultadoForm.getMetaResultado());
		resultado.setFkIdtablaespUnidadMedida(resultadoForm.getEstratoId());
		resultado.setDuracionMeses(resultadoForm.getDuracionMeses());
		resultado.setDatoPlanOperativo(datoPlanOperativo);
		getResultadoDAO().saveResultado(resultado);

		return resultado.getResultadoID();
	}

	private PlanOperativoForm setDataPlanOperativoForm(
			DatoPlanOperativo datoPlanOperativo) {

		logger.info("datoPlanOperativo.getFkIdDetalleEstadoCabEstadoPlanOper() = "
				+ datoPlanOperativo.getFkIdDetalleEstadoCabEstadoPlanOper());

		PlanOperativoForm planOperativoForm = new PlanOperativoForm();
		planOperativoForm.setDatoProyectoID(datoPlanOperativo.getDatoProyecto()
				.getDatoProyectoID());
		planOperativoForm.setDatoProyecto(datoProyectoService.findDatoProyectoById(datoPlanOperativo.getDatoProyecto()
				.getDatoProyectoID()));
		planOperativoForm.setCodigoProyecto(datoPlanOperativo.getDatoProyecto()
				.getCodigoProyecto());
		planOperativoForm.setNombreProyecto(datoPlanOperativo.getDatoProyecto()
				.getNombreProyecto());
		planOperativoForm.setCantidadPerido(datoPlanOperativo.getDatoProyecto()
				.getCantidadPeriodo().toString());
		planOperativoForm.setDatoPlanOperativoID(datoPlanOperativo
				.getDatoPlanOperativoID());
		planOperativoForm.setVersion(datoPlanOperativo.getVersion());
		//Integer estadoPlanOperativoId = datoPlanOperativo.getFkIdDetalleEstadoCabEstadoPlanOper();
		planOperativoForm.setEstadoPlanOperativo(datoPlanOperativo.getFkIdDetalleEstadoCabEstadoPlanOper());
		planOperativoForm
				.setEstadoPlanOperativoDescripcion(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(datoPlanOperativo.getFkIdDetalleEstadoCabEstadoPlanOper()).getDescripEstado());
		planOperativoForm.setIdTipoMoneda(datoPlanOperativo
				.getFkIdtablaespTipoMoneda());
		planOperativoForm
				.setTipoMonedaNombre(getTablaEspecificaDescripcion(datoPlanOperativo
						.getFkIdtablaespTipoMoneda()));
		//busco observaciones relevantes al documento
		planOperativoForm.setCantObservacionesRelevantes(observacionService.findObservacionesRelevatesAlDocumento(datoPlanOperativo.getDatoProyecto().getDatoProyectoID(),datoPlanOperativo.getDatoPlanOperativoID(),FondamConstans.TABLA_CLASE_NOMBRE_DATO_PLAN_OPERATIVO));
		
		//busca reportes sin aprobar
		planOperativoForm.setCantReportesSinAProbar(reporteAvanceService.findReporteAvanceXDatoProyectoIdSinAprobar(datoPlanOperativo.getDatoProyecto().getDatoProyectoID()).size());
		//busca liquidaciones sin aprobar
		planOperativoForm.setCantLiquidacionesSinAProbar(liquidacionGastoService.findLiquidacionGastoByDatoProyectoIDSinAprobar(datoPlanOperativo.getDatoProyecto().getDatoProyectoID()).size());
		
		return planOperativoForm;
	}

	private List<ResultadoForm> setDataListResultadoForm(
			List<Resultado> listResultado) {

		List<ResultadoForm> listResultadoForm = new ArrayList<ResultadoForm>();
		ResultadoForm resultadoForm = null;

		for (Resultado resultado : listResultado) {
			resultadoForm = new ResultadoForm();
			resultadoForm.setResultadoID(resultado.getResultadoID());
			resultadoForm.setCodigoResultado(resultado.getCodigoResultado());
			resultadoForm.setDefinicionResultado(resultado
					.getDefinicionResultado());
			resultadoForm
					.setSupuestoResultado(resultado.getSupuestoResultado());
			resultadoForm.setMetaResultado(resultado.getMetaResultado());

			Integer estratoId = resultado.getFkIdtablaespUnidadMedida();
			resultadoForm.setEstratoId(estratoId);
			resultadoForm
					.setEstratoNombre(getTablaEspecificaDescripcion(estratoId));

			resultadoForm.setDuracionMeses(resultado.getDuracionMeses());
			listResultadoForm.add(resultadoForm);
		}
		return listResultadoForm;
	}

	private String getTablaEspecificaDescripcion(Integer tablaEspecificaID) {
		String descripcion = "";
		if (tablaEspecificaID != null) {
			TablaEspecifica tablaEspecifica = getTablaEspecificaDAO()
					.findTablaEspecificaById(tablaEspecificaID);
			if (tablaEspecifica != null) {
				descripcion = tablaEspecifica.getDescripcionCabecera();
			}
		}
		return descripcion;
	}

	private Integer nextCodigoResultado(Integer datoPlanOperativoID) {
		Integer codigoResultado = 0;
		List<Resultado> listResultado = getResultadoDAO()
				.findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		if (!listResultado.isEmpty()) {
			Resultado resultado = listResultado.get(0);
			codigoResultado = resultado.getCodigoResultado() + 1;
		}
		return codigoResultado;
	}

	private Integer nextCodigoActividad(Integer resultadoID) {
		Integer codigoActividad = 1;
		List<Actividad> listActividad = getActividadDAO()
				.findActividadByResultadoID(resultadoID);
		if (!listActividad.isEmpty()) {
			Actividad actividad = listActividad.get(0);
			codigoActividad = actividad.getCodigoActividad() + 1;
		}
		return codigoActividad;
	}

	@Override
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadID(
			Integer costoActividadID, Integer fuenteFinanciadoraID) {

		List<CronogramaCostoActividad> listaCronogramaCostoActividad = cronogramaCostoActividadService.llenaMontoLiquidadoHastaElMomentoDeCCA( getCronogramaCostoActividadDAO()
				.findCronogramaCostoActividadByCostoActividadIdAndFuenteFinanciadoraID(costoActividadID, fuenteFinanciadoraID));

		/*for (CronogramaCostoActividad cronogramaCostoActividad : tmpListaCronogramaCostoActividad) {
			Integer tmpFuenteFinanciadoraID = cronogramaCostoActividad.getFuenteFinanciadora().getFuenteFinanciadoraID();
			if (tmpFuenteFinanciadoraID == fuenteFinanciadoraID.intValue()) {
				listaCronogramaCostoActividad.add(cronogramaCostoActividad);
			}
		}*/
		return listaCronogramaCostoActividad;
	}

	@Override
	public List<CronogramaMetaPorActividad> findCronogramaMetaActividadByMetaActividadID(
			Integer metaActividadID) {
		return cronogramaMetaPorActividadDAO
				.findCronorgramaMetaPorActividadesByMetaPorActivdadId(metaActividadID);
	}

	@Override
	public Perfil findPerfilByDatoProyectoID(Integer proyectoID) {
		return perfilDAO.findPerfilByDatoProyectoID(proyectoID);
	}

	@Override
	public List<BeneficiarioResultadoView> findBeneficiariosPorResultadoByIdPerfil(
			Integer idPerfil) {
		List<BeneficiarioResultadoView> listBeneficiarioResultadoView = new ArrayList<BeneficiarioResultadoView>();
		List<BeneficiariosPorResultado> listBeneficiariosPorResultado = beneficiariosPorResultadoDAO
				.findBeneficiariosPorResultadoByIdPerfil(idPerfil);
		if (UtilValidate.isNotEmpty(listBeneficiariosPorResultado)) {
			listBeneficiarioResultadoView = setDataListBeneficiarioResultadoView(listBeneficiariosPorResultado);
		}

		return listBeneficiarioResultadoView;
	}

	@Override
	public BeneficiariosPorResultado findBeneficiariosPorResultadoById(
			Integer id) {
		return beneficiariosPorResultadoDAO
				.findBeneficiariosPorResultadoById(id);
	}

	@Override
	public double obtenerCostoTotalCostoActividadByDatoPlanOperativoID(
			Integer datoPlanOperativoID) {

		double costoTotal = 0;

		List<Resultado> listResultado = resultadoDAO
				.findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		for (Resultado resultado : listResultado) {
			List<Actividad> listaActividad = actividadDAO
					.findActividadByResultadoID(resultado.getResultadoID());
			for (Actividad actividad : listaActividad) {
				List<CostoActividad> listCostoActividad = getCostoActividadDAO()
						.findCostoActividadByActividadID(
								actividad.getActividadID());
				for (CostoActividad costoActividad : listCostoActividad) {
					costoTotal += costoActividad.getPrecioUnitario()
							* costoActividad.getCantidadTotal();
				}
			}
		}

		return costoTotal;
	}

	@Override
	public double obtenerFinanciamientoTotalByDatoPlanOperativo(
			DatoPlanOperativo datoPlanOperativo) {

		double financiamientoTotal = 0;

		List<FuenteFinanciadora> listFuenteFinanciadora = fuenteFinanciadoraDAO
				.findFuenteFinanciadorasByIdDatoProy(datoPlanOperativo
						.getDatoProyecto().getDatoProyectoID());
		for (FuenteFinanciadora fuenteFinanciadora : listFuenteFinanciadora) {
			/*TipoCambio tipoCambio = tipoCambioService
					.findTipoCambioByTipoMonedaDeAByDatoPlanOperativoID(
							fuenteFinanciadora.getFkIdtablaespTipoMoneda(),
							datoPlanOperativo.getFkIdtablaespTipoMoneda(),
							datoPlanOperativo.getDatoPlanOperativoID());
			financiamientoTotal += tipoCambio.getTipoCambio()
					* fuenteFinanciadora.getMontoFinanciado();*/
			financiamientoTotal += fuenteFinanciadora.getMontoFinanciado();
		}
		return financiamientoTotal;
	}

	@Override
	public double obtenerCostoTotalCostoActividadByDatoPlanOperativoIDAndFuenteFinanciera(
			Integer datoPlanOperativoID, Integer fuenteFinanciadoraID, Integer costoActividadID) {

		double costoTotalByFuenteFinanciadora = 0;

		List<CronogramaCostoActividad> listCronogramaCostoActividad = cronogramaCostoActividadService.findCronogramaCostoActividadByFuenteFinanciamientoIDMenosCostoActividadId(fuenteFinanciadoraID, costoActividadID);
		
		for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
			costoTotalByFuenteFinanciadora += cronogramaCostoActividad.getCantidad() * cronogramaCostoActividad.getCostoActividad().getPrecioUnitario();
		}
		
		/*
		List<Resultado> listResultado = resultadoDAO
				.findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		for (Resultado resultado : listResultado) {
			List<Actividad> listaActividad = actividadDAO
					.findActividadByResultadoID(resultado.getResultadoID());
			for (Actividad actividad : listaActividad) {
				List<CostoActividad> listCostoActividad = getCostoActividadDAO()
						.findCostoActividadByActividadID(
								actividad.getActividadID());
				for (CostoActividad costoActividad : listCostoActividad) {
					List<CronogramaCostoActividad> listaCronogramaCostoActividad = this
							.findCronogramaCostoActividadByCostoActividadID(
									costoActividad.getCostoActividadID(),
									fuenteFinanciadoraID);
					int totalCantidad = 0;
					for (CronogramaCostoActividad cronogramaCostoActividad : listaCronogramaCostoActividad) {
						totalCantidad += cronogramaCostoActividad.getCantidad();
						// costoTotal += costoActividad.getPrecioUnitario() *
						// costoActividad.getCantidadTotal();
					}
					costoTotal += costoActividad.getPrecioUnitario()
							* totalCantidad;
				}
			}
		}*/

		return costoTotalByFuenteFinanciadora;

	}

	@Override
	public double obtenerFinanciamientoTotalByDatoPlanOperativoAndFuenteFinanciera(
			DatoPlanOperativo datoPlanOperativo, Integer fuenteFinanciadoraID) {

		double financiamientoTotal = 0;

		List<FuenteFinanciadora> listFuenteFinanciadora = fuenteFinanciadoraDAO
				.findFuenteFinanciadorasByIdDatoProy(datoPlanOperativo
						.getDatoProyecto().getDatoProyectoID());
		for (FuenteFinanciadora fuenteFinanciadora : listFuenteFinanciadora) {
			if (fuenteFinanciadora.getFuenteFinanciadoraID().equals(fuenteFinanciadoraID) ) {
				/*TipoCambio tipoCambio = tipoCambioService
						.findTipoCambioByTipoMonedaDeAByDatoPlanOperativoID(
								fuenteFinanciadora.getFkIdtablaespTipoMoneda(),
								datoPlanOperativo.getFkIdtablaespTipoMoneda(),
								datoPlanOperativo.getDatoPlanOperativoID());
				financiamientoTotal += tipoCambio.getTipoCambio()
						* fuenteFinanciadora.getMontoFinanciado();*/
				financiamientoTotal += fuenteFinanciadora.getMontoFinanciado();
			}
		}
		return financiamientoTotal;
	}

	@Override
	public DatoPlanOperativo findPlanOperativoByDatoProyectoID(
			Integer datoProyectoID) {

		return datoPlanOperativoDAO
				.findDatoPlanOperativoByDatoProyectoID(datoProyectoID);
	}


	@Override
	public List<BeneficiarioResultadoView> findBeneficiariosPorResultado(
			Integer perfilID) {

		String consulta = " from BeneficiariosPorResultado where perfil.perfilID = ? and resultado.resultadoID is null ";
		Object[] params = new Object[1];
		params[0] = perfilID;

		/*
		List<BeneficiariosPorResultado> listBeneficiariosPorResultado = beneficiariosPorResultadoDAO
				.findBeneficiariosPorResultadoXConsulta(consulta, params);
		List<BeneficiarioResultadoView> listBeneficiarioResultadoView = new ArrayList<BeneficiarioResultadoView>();
		if (UtilValidate.isNotEmpty(listBeneficiariosPorResultado)) {
			listBeneficiarioResultadoView = setDataListBeneficiarioResultadoView(listBeneficiariosPorResultado);
		}
		return listBeneficiarioResultadoView;
		*/
		return setDataListBeneficiarioResultadoView(beneficiariosPorResultadoDAO
				.findBeneficiariosPorResultadoXConsulta(consulta, params));
	}

	@Override
	public DatoPlanOperativo llenaPlanOperativoCompleto(
			DatoPlanOperativo datoPlanOperativo) {

		datoPlanOperativo
				.setListResultado(llenaResultadoCompleto(resultadoService
						.findResultadoXDatoPlanOperativoID(datoPlanOperativo
								.getDatoPlanOperativoID())));
		return datoPlanOperativo;
	}

	public List<Resultado> llenaResultadoCompleto(
			List<Resultado> listResultadoXDatoPlanOperativo) {

		for (Resultado resultado : listResultadoXDatoPlanOperativo) {
			resultado.setListActividad(llenaActividadCompleto(actividadService
					.findActividadXResultadoId(resultado.getResultadoID())));
			resultado
					.setListCronogramaMetaPorResultado(cronogramaMetaPorResultadoService
							.findCronogramaMetaPorResultadoByResultadoID(resultado
									.getResultadoID()));
		}
		return listResultadoXDatoPlanOperativo;
	}

	public List<Actividad> llenaActividadCompleto(
			List<Actividad> listActividadXResultado) {

		for (Actividad actividad : listActividadXResultado) {
			actividad
					.setListCostoActividad(llenaCostoActividadCompleto(costoActividadService
							.findCostoActividadByActividadID(actividad
									.getActividadID())));
			actividad
					.setListMetaPorActividad(llenaMetaPorActividadCompleto(metaPorActividadService
							.findMetaPorActividadXActividadId(actividad
									.getActividadID())));
		}
		return listActividadXResultado;
	}

	public List<CostoActividad> llenaCostoActividadCompleto(
			List<CostoActividad> listCostoActividad) {
		for (CostoActividad costoActividad : listCostoActividad) {
			costoActividad
					.setListCronogramaCostoActividad(cronogramaCostoActividadService
							.findCronogramaCostoActividadByCostoActividadID(costoActividad
									.getCostoActividadID()));
		}

		return listCostoActividad;
	}

	public List<MetaPorActividad> llenaMetaPorActividadCompleto(
			List<MetaPorActividad> listMetaPorActividad) {
		for (MetaPorActividad metaPorActividad : listMetaPorActividad) {
			metaPorActividad
					.setListCronogramaMetaPorActividad(cronogramaMetaPorActividadService
							.findCronogramaMetaPorActividadXMetaPorActividadId(metaPorActividad
									.getMetaPorActividadID()));
		}
		return listMetaPorActividad;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PlanOperativoForm llenaPlanOperativoTotal(
			PlanOperativoForm planOperativoForm) {

		if (planOperativoForm != null) {
			for (ResultadoForm resultadoForm : planOperativoForm
					.getListResultadoForm()) {
				List<ActividadForm> listActividadForm = obtenerListaActividadByResultadoID(resultadoForm
								.getResultadoID());
				resultadoForm
						.setListCronogramaMetaForm(setDataListCronogramaMetaForm((List<CronogramaMetaPorResultado>) UtilList.orderAscList(
										cronogramaMetaPorResultadoService
												.findCronogramaMetaPorResultadoByResultadoID(resultadoForm
														.getResultadoID()),
										"periodo")));
				if (listActividadForm != null) {
					resultadoForm
							.setListActividadForm(llenaListActividadCompleto((List<ActividadForm>) UtilList
									.orderAscList(listActividadForm,
											"codigoActividad")));
				}
			}
		}
		return planOperativoForm;
	}

	private List<ActividadForm> llenaListActividadCompleto(
			List<ActividadForm> listaActividadForm) {
		if (listaActividadForm != null) {
			for (ActividadForm actividadForm : listaActividadForm) {
				actividadForm
						.setListMetaPorActividad(llenaMetaPorActividad(metaPorActividadService
								.findMetaPorActividadXActividadId(actividadForm
										.getActividadID())));
				actividadForm
						.setListCostoActividad(llenaCostoActividad(costoActividadService
								.findCostoActividadByActividadID(actividadForm
										.getActividadID())));
			}
		}
		return listaActividadForm;
	}

	@SuppressWarnings("unchecked")
	private List<CostoActividad> llenaCostoActividad(
			List<CostoActividad> listCostoActividad) {

		if (listCostoActividad != null) {
			for (CostoActividad costoActividad : listCostoActividad) {
				costoActividad.setDescripcionTipoMoneda(tablaEspecificaService
						.findTablaEspecificaById(
								costoActividad.getFkIdtablaespTipoMoneda())
						.getDescripcionCabecera());
				costoActividad
						.setDescripcionUnidadMedida(tablaEspecificaService
								.findTablaEspecificaById(
										costoActividad
												.getFkIdtablaespUnidadMedida())
								.getDescripcionCabecera());
				List<CronogramaCostoActividad> listCronogramaCostoActividad=cronogramaCostoActividadService
				.findCronogramaCostoActividadByCostoActividadID(costoActividad
						.getCostoActividadID());
				List<CronogramaCostoActividad> listCronogramaCostoActividadTemp= new ArrayList<CronogramaCostoActividad>();
				for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
					if(cronogramaCostoActividad.getCantidad()>0){
						listCronogramaCostoActividadTemp.add(cronogramaCostoActividad);
					}
				}
				costoActividad.setListCronogramaCostoActividad((List<CronogramaCostoActividad>) UtilList.orderAscList(listCronogramaCostoActividadTemp,"periodo"));
			}
		}

		return listCostoActividad;
	}

	@SuppressWarnings("unchecked")
	private List<MetaPorActividad> llenaMetaPorActividad(
			List<MetaPorActividad> listMetaPorActividad) {

		if (listMetaPorActividad != null) {
			for (MetaPorActividad metaPorActividad : listMetaPorActividad) {
				metaPorActividad
						.setDescripcionTipoIndicadorActividad(tablaEspecificaService
								.findTablaEspecificaById(
										metaPorActividad
												.getFkIdtablaespTipoIndicadorActividad())
								.getDescripcionCabecera());
				metaPorActividad
						.setDescripcionUnidadMedida(tablaEspecificaService
								.findTablaEspecificaById(
										metaPorActividad
												.getFkIdtablaespUnidadMedida())
								.getDescripcionCabecera());
				List<CronogramaMetaPorActividad> listCronogramaMetaPorActividad = cronogramaMetaPorActividadService
				.findCronogramaMetaPorActividadXMetaPorActividadId(metaPorActividad
						.getMetaPorActividadID());
				List<CronogramaMetaPorActividad> listCronogramaMetaPorActividadTemp = new ArrayList<CronogramaMetaPorActividad>();
				for (CronogramaMetaPorActividad cronogramaMetaPorActividad : listCronogramaMetaPorActividad) {
					if(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()>0){
						listCronogramaMetaPorActividadTemp.add(cronogramaMetaPorActividad);
					}
				}
				metaPorActividad
						.setListCronogramaMetaPorActividad((List<CronogramaMetaPorActividad>) UtilList.orderAscList(listCronogramaMetaPorActividadTemp,"periodo"));
			}
		}
		return listMetaPorActividad;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PlanOperativoForm obtenerPlanOperativoByProyectoIDByVersion(
			Integer datoProyectoId, String versionPo) {
		//logger.info("[PlanOperativoService:obtenerPlanOperativoByProyectoID] Start");
		//logger.info("datoProyectoID = " + datoProyectoID);
		PlanOperativoForm planOperativoForm = null;

		DatoPlanOperativo datoPlanOperativo = getDatoPlanOperativoDAO()
				.findDatoPlanOperativoByDatoProyectoIDByVersion(datoProyectoId,versionPo);
		//logger.info("datoPlanOperativo = " + datoPlanOperativo);

		if (UtilValidate.isNotEmpty(datoPlanOperativo)) {
			//Integer datoPlanOperativoID = datoPlanOperativo.getDatoPlanOperativoID();
			planOperativoForm = setDataPlanOperativoForm(datoPlanOperativo);
			List<Resultado> listResultado = getResultadoDAO()
					.findListResultadoByDatoPlanOperativoID(datoPlanOperativo.getDatoPlanOperativoID());
			if (UtilValidate.isNotEmpty(listResultado)) {
				List<ResultadoForm> listResultadoForm = setDataListResultadoForm(listResultado);
				listResultadoForm = (List<ResultadoForm>) UtilList
						.orderAscList(listResultadoForm, "codigoResultado");
				planOperativoForm.setListResultadoForm(listResultadoForm);
			}
		}
		//logger.info("[PlanOperativoService:obtenerPlanOperativoByProyectoID] Finished");
		return planOperativoForm;
	}

	@Override
	public List<DatoPlanOperativo> findListPlanOperativoByDatoProyectoID(
			Integer datoProyectoID) {

		String queryString = "from DatoPlanOperativo where datoProyecto.datoProyectoID = ? ";
		Object[] params = new Object[1];
		params[0] = datoProyectoID;

		return datoPlanOperativoDAO.findDatoPlanOperativoByConsulta(queryString, params);
	}

}
