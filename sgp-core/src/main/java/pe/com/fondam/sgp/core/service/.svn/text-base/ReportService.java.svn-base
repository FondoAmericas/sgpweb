package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.report.RptBeneficiariosAreaIntervencion;
import pe.com.fondam.sgp.core.report.RptContraPartida;
import pe.com.fondam.sgp.core.report.RptContribucionDonacion;
import pe.com.fondam.sgp.core.report.RptCostoActividadFuente;
import pe.com.fondam.sgp.core.report.RptCronogramaActividad;
import pe.com.fondam.sgp.core.report.RptDesemboloRecursosDonacion;
import pe.com.fondam.sgp.core.report.RptEstructuraInversionFinanciamiento;
import pe.com.fondam.sgp.core.report.RptOperacionCostoCronogramaResultado;
import pe.com.fondam.sgp.core.report.RptPlanUsoRecursosOtrasFuentes;
import pe.com.fondam.sgp.core.report.RptResultados;

public interface ReportService {

	String planOperativoEstadoDescripcion(Integer detalleEstadoCabeceraID);
	
	List<RptResultados> planOperativoResultadosByDatoPlanOperativoID(
			Integer datoPlanOperativoID);

	List<RptCronogramaActividad> planOperativoCronogramaActividadByDatoPlanOperativoID(
			Integer datoPlanOperativoID);

	List<RptCostoActividadFuente> planOperativoCostoActividadFuente(
			Integer datoPlanOperativoID, Integer fuenteFinanciadoraID,
			Integer actividadID);

	List<RptEstructuraInversionFinanciamiento> planOperativoEstructuraInversionFinanciamiento(
			Integer datoPlanOperativoID);

	List<RptDesemboloRecursosDonacion> planOperativoDesembolsoRecursosDonacion(
			Integer datoPlanOperativoID);

	List<RptContraPartida> planOperativoContraPartida(
			Integer datoPlanOperativoID);

	List<RptBeneficiariosAreaIntervencion> planOperativoBeneficiariosAreaIntervencionDirectos(
			Integer datoPlanOperativoID);

	List<RptOperacionCostoCronogramaResultado> planOperativoOperacionesCostosCronogramaResultados(
			Integer datoPlanOperativoID);

	List<RptPlanUsoRecursosOtrasFuentes> planOperativoPlanUsoRecursosOtrasFuentes(
			Integer datoPlanOperativoID);

	List<RptContribucionDonacion> planOperativoContribucionDonacion(
			Integer datoPlanOperativoID, Integer fuenteFinanciadoraID);

}