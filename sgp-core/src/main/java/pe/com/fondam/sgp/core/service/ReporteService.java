package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.report.RptResultados;

public interface ReporteService {

	List<RptResultados> llenaRptResultadosReporteCronogramaActividades(
			List<Resultado> listResultado);

	String pathCronogramaActividadPorCantidadPeriodos(
			DatoProyecto datoProyecto, String reportPath);


}
