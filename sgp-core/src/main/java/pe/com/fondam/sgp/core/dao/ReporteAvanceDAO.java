package pe.com.fondam.sgp.core.dao;
import java.util.List;

import pe.com.fondam.sgp.core.domain.ReporteAvance;



public interface ReporteAvanceDAO {

	ReporteAvance updateReporteAvance(ReporteAvance reporteAvance);
	
		void deleteReporteAvance(ReporteAvance reporteAvance);
	
		List<ReporteAvance> findReporteAvanceXConsulta(String consulta,
				Object[] params);
		
		ReporteAvance findReporteAvanceById(Integer reporteAvanceId);
		
		public int getCantidadReporteAvanceByProyectoIdByEstadoRepAvanceGenerado(Integer datoProyectoID);
		
		public List<ReporteAvance> findReporteAvanceByProyectoIdByEstadoRepAvanceGenerado(Integer datoProyectoID);

}
