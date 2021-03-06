package pe.com.fondam.sgp.core.service;

import java.util.List;

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
import pe.com.fondam.sgp.core.domain.MetaPorActividad;
import pe.com.fondam.sgp.core.domain.PartidaEspecifica;
import pe.com.fondam.sgp.core.domain.PartidaGenerica;
import pe.com.fondam.sgp.core.domain.Perfil;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.domain.Riesgo;
import pe.com.fondam.sgp.core.domain.RubroGenerico;
import pe.com.fondam.sgp.core.form.planOperativo.ActividadForm;
import pe.com.fondam.sgp.core.form.planOperativo.BeneficiarioForm;
import pe.com.fondam.sgp.core.form.planOperativo.CostoActividadForm;
import pe.com.fondam.sgp.core.form.planOperativo.CronogramaCostoActividadForm;
import pe.com.fondam.sgp.core.form.planOperativo.CronogramaMetaForm;
import pe.com.fondam.sgp.core.form.planOperativo.IndicadorForm;
import pe.com.fondam.sgp.core.form.planOperativo.MetaActividadForm;
import pe.com.fondam.sgp.core.form.planOperativo.PlanOperativoForm;
import pe.com.fondam.sgp.core.form.planOperativo.ResultadoForm;
import pe.com.fondam.sgp.core.view.planOperativo.BeneficiarioResultadoView;
import pe.com.fondam.sgp.core.view.planOperativo.CostoActividadView;
import pe.com.fondam.sgp.core.view.planOperativo.CronogramaCostoActividad2View;
import pe.com.fondam.sgp.core.view.planOperativo.CronogramaCostoActividadView;
import pe.com.fondam.sgp.core.view.planOperativo.CronogramaMetaActividadView;
import pe.com.fondam.sgp.core.view.planOperativo.IndicadorResultadoView;

public interface PlanOperativoService {

	DatoProyecto findDatoProyectoByDatoProyectoID(Integer datoProyectoID);

	PlanOperativoForm obtenerPlanOperativoByProyectoID(Integer datoProyectoID);

	PlanOperativoForm obtenerPlanOperativoByDatoPlanOperativoID(
			Integer datoPlanOperativoID);

	PlanOperativoForm mostrarPlanOperativoProyecto(Integer datoPlanOperativoID);

	PlanOperativoForm mostrarPlanOperativoDatoPlanOperativoID(
			Integer datoPlanOperativoID);

	List<Resultado> listResultadoByDatoPlanOperativoID(Integer datoPlanOperativoID);
	
	ResultadoForm obtenerResultadoByResultadoID(Integer resultadoID);

	List<IndicadorForm> obtenerListaIndicadorResultadoByResultadoID(
			Integer resultadoID);

	List<ActividadForm> obtenerListaActividadByResultadoID(Integer resultadoID);

	List<CostoActividadForm> obtenerListaCostoActividadByActividadID(Integer actividadID);
	
	List<MetaActividadForm> obtenerListaMetaActividadByActividadID(Integer actividadID);

	List<CostoActividadView> listCostoActividadViewByActividadID(
			Integer actividadID);

	List<CronogramaCostoActividadForm> obtenerListaCronogramaCostoActividadByActividadID(
			Integer actividadID);

	List<CronogramaCostoActividadView> listCronogramaCostoActividadViewByCostoActividadID(
			Integer costoActividadID);

	List<CronogramaCostoActividad2View> listCronogramaCostoActividad2ViewByCostoActividadID(Integer costoActividadID);
	
	List<CronogramaMetaActividadView> listCronogramaMetaActividadViewByMetaActividadID(Integer metaActividadID);

	ActividadForm obtenerActividadByActividadID(Integer actividadID);

	CostoActividadView findCostoActividadViewByID(Integer costoActividadID);

	DatoPlanOperativo obtenerDatoPlanOperativoByID(Integer datoPlanOperativoID);

	Resultado obtenerResultadoByID(Integer resultadoID);

	Actividad obtenerActividadByID(Integer actividadID);

	CategoriaActividad obtenerCategoriaActividadByID(
			Integer categoriaActividadID);

	RubroGenerico obtenerRubroGenericoByID(Integer rubroGenericoID);

	PartidaGenerica obtenerPartidaGenericaByID(Integer partidaGenericaID);

	PartidaEspecifica obtenerPartidaEspecificaByID(Integer partidaEspecificaID);

	ResultadoForm mostrarResultadoByResultadoID(Integer resultadoID);

	List<PlanOperativoForm> showPlanOperativo();

	Integer createPlanOperativo(DatoPlanOperativo datoPlanOperativo)
			throws Exception;

	Integer createPlanOperativo(PlanOperativoForm planOperativoForm)
			throws Exception;

	Integer createResultado(Resultado resultado) throws Exception;
	
	void deleteResultado(Integer resultadoID) throws Exception;

	Integer createBeneficiarioResultado(
			BeneficiariosPorResultado beneficiariosPorResultado)
			throws Exception;

	Integer createIndicadorResultado(IndicadorResultado indicadorResultado)
			throws Exception;
	
	Integer createCronogramaResultado(
			CronogramaMetaPorResultado cronogramaMetaPorResultado)
			throws Exception;
	
	List<CronogramaMetaPorResultado> findCronogramaMetaPorResultadoByResultadoID(Integer resultadoID);

	Integer createIndicador(Integer resultadoID, IndicadorForm indicador)
			throws Exception;
	
	Integer createActividad(Actividad actividad) throws Exception;

	Integer createMetaActividad(MetaPorActividad metaPorActividad) throws Exception;

	Integer createCostoActividad(CostoActividad costoActividad)
			throws Exception;

	Integer createCronogramaCostoActividad(CronogramaCostoActividad cronogramaCostoActividad) throws Exception;
	
	Integer createCronogramaMetaActividad(CronogramaMetaPorActividad cronogramaMetaPorActividad) throws Exception;
	
	CronogramaCostoActividad updateCronogramaCostoActividad(CronogramaCostoActividad cronogramaCostoActividad);
	
	CronogramaMetaPorActividad updateCronogramaMetaPorActividad(CronogramaMetaPorActividad cronogramaMetaPorActividad);

	DatoPlanOperativo findPlanOperativoByID(Integer datoPlanOperativoID);

	void updatePlanOperativo(DatoPlanOperativo dpo);

	List<CronogramaMetaPorResultado> findListaMetaResultadoByResultadoID(
			int resultadoID);

	List<BeneficiarioForm> findListaBeneficiariosByResultadoID(int resultadoID);

	MetaPorActividad findMetaPorActividadByActividadId(int actividadID);
	
	MetaActividadForm obtenerMetaPorActividadById(int actividadID);

	List<Riesgo> findRiesgoByActividadID(int id);

	List<CostoActividad> findCostoActividadByActividadID(Integer actividadID);

	/*List<ObservacionCostoActividad> finObservacionCostoActividadByCostoActividadID(
			int costoActividadID);

	List<ObservacionCronogramaCostoActividad> finObservacionCronogramaCostoActividadByCronogramaCostoActividadID(
			int cronogramaCostoActividadID);
*/
	CostoActividad findCostoActividadByID(int costoActividadID);
/*
	void saveObservacionCostoActividad(
			ObservacionCostoActividad observacionCostoActividad);

	ObservacionCostoActividad finObservacionCostoActividadByID(
			int observacionCostoActividadID);

	ObservacionCronogramaCostoActividad finObservacionCronogramaCostoActividadByID(
			int observacionCronogramaCostoActividadID);

	void updateObservacionCronogramaCostoActividad(
			ObservacionCronogramaCostoActividad observacionCronogramaCostoActividad);

	void saveObservacionCronogramaCostoActividad(
			ObservacionCronogramaCostoActividad observacionCronogramaCostoActividad);

	void updateObservacionCostoActividad(
			ObservacionCostoActividad observacionCostoActividad);
*/
	CronogramaCostoActividad findCronogramaCostoActividadByID(
			int cronogramaCostoActividadID);

	List<BeneficiarioResultadoView> findBeneficiarioResultadoByResultadoID(
			Integer resultadoID);

	List<IndicadorResultadoView> findIndicadorResultadoViewByResultadoID(Integer resultadoID);
	
	List<FuenteFinanciadora> findFuenteFinanciadoraByDatoProyectoID(
			Integer datoProyectoID);

	FuenteFinanciadora findFuenteFinanciadoraById(Integer fuenteFinanciadoraID);
	
	List<ActividadObligatoriaPrograma> findListActividadObligatoriaProgramaByProgramaId(
			Integer programaID);
	
	String obtenerDescripcionTablaEspecifica(Integer tablaEspecificaID);
	
	List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadID(Integer costoActividadID, Integer fuenteFinanciadoraID);
	
	List<CronogramaMetaPorActividad> findCronogramaMetaActividadByMetaActividadID(Integer metaActividadID);
	
	Perfil findPerfilByDatoProyectoID(Integer proyectoID);
	
	List<BeneficiarioResultadoView> findBeneficiariosPorResultadoByIdPerfil(Integer idPerfil);
	
	BeneficiariosPorResultado findBeneficiariosPorResultadoById(Integer id);
	
	double obtenerCostoTotalCostoActividadByDatoPlanOperativoID(Integer datoPlanOperativoID);
	double obtenerFinanciamientoTotalByDatoPlanOperativo(DatoPlanOperativo datoPlanOperativo);
	
	double obtenerCostoTotalCostoActividadByDatoPlanOperativoIDAndFuenteFinanciera(Integer datoPlanOperativoID, Integer fuenteFinanciadoraID, Integer costoActividadID);
	double obtenerFinanciamientoTotalByDatoPlanOperativoAndFuenteFinanciera(DatoPlanOperativo datoPlanOperativo, Integer fuenteFinanciadoraID);

	DatoPlanOperativo findPlanOperativoByDatoProyectoID(
			Integer datoProyectoID);

	List<BeneficiarioResultadoView> findBeneficiariosPorResultado(
			Integer perfilID);

	DatoPlanOperativo llenaPlanOperativoCompleto(
			DatoPlanOperativo datoPlanOperativo);

	List<CronogramaMetaForm> setDataListCronogramaMetaForm(
			List<CronogramaMetaPorResultado> listCronogramaMetaPorResultado);
	
	PlanOperativoForm llenaPlanOperativoTotal(
			PlanOperativoForm planOperativoForm);

	PlanOperativoForm obtenerPlanOperativoByProyectoIDByVersion(
			Integer datoProyectoId, String versionPo);

	List<DatoPlanOperativo> findListPlanOperativoByDatoProyectoID(
			Integer datoProyectoID); 
}
